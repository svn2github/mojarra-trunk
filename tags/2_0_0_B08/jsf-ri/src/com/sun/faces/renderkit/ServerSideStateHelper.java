package com.sun.faces.renderkit;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.context.ResponseWriter;
import javax.faces.FacesException;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIViewRoot;

import com.sun.faces.config.WebConfiguration.WebContextInitParameter;
import static com.sun.faces.config.WebConfiguration.BooleanWebContextInitParameter.SerializeServerState;
import com.sun.faces.util.FacesLogger;
import com.sun.faces.util.TypedCollections;
import com.sun.faces.util.LRUMap;
import com.sun.faces.util.Util;

import static com.sun.faces.config.WebConfiguration.WebContextInitParameter.NumberOfLogicalViews;
import static com.sun.faces.config.WebConfiguration.WebContextInitParameter.NumberOfViews;

/**
 * <p>
 * This <code>StateHelper</code> provides the functionality associated with server-side state saving,
 * though in actuallity, it is a hybrid between client and server.
 * </p>
 */
public class ServerSideStateHelper extends StateHelper {

    private static final Logger LOGGER = FacesLogger.APPLICATION.getLogger();

    /**
     * Key to store the <code>AtomicInteger</code> used to generate
     * unique state map keys.
     */
    public static final String STATEMANAGED_SERIAL_ID_KEY =
          ServerSideStateHelper.class.getName() + ".SerialId";

    /**
     * The top level attribute name for storing the state structures within
     * the session.
     */
    public static final String LOGICAL_VIEW_MAP =
          ServerSideStateHelper.class.getName() + ".LogicalViewMap";

    /**
     * The number of logical views as configured by the user.
     */
    protected final Integer numberOfLogicalViews;


    /**
     * The number of views as configured by the user.
     */
    protected final Integer numberOfViews;


    // ------------------------------------------------------------ Constructors


    /**
     * Construct a new <code>ServerSideStateHelper</code> instance.
     */
    public ServerSideStateHelper() {

        numberOfLogicalViews = getIntegerConfigValue(NumberOfLogicalViews);
        numberOfViews = getIntegerConfigValue(NumberOfViews);

    }


    // ------------------------------------------------ Methods from StateHelper


    /**
     * <p>
     * Stores the provided state within the session obtained from the provided
     * <code>FacesContext</code>
     * </p>
     *
     * <p>If <code>stateCapture</code> is <code>null</code>, the composite
     * key used to look up the actual and logical views will be written to
     * the client as a hidden field using the <code>ResponseWriter</code>
     * from the provided <code>FacesContext</code>.</p>
     *
     * <p>If <code>stateCapture</code> is not <code>null</code>, the compisite
     * key will be appended to the <code>StringBuilder<code> without any markup
     * included or any content written to the client.
     *
     * @see {@link com.sun.faces.renderkit.StateHelper#writeState(javax.faces.context.FacesContext, Object, StringBuilder)}
     */
    public void writeState(FacesContext ctx,
                           Object state,
                           StringBuilder stateCapture)
    throws IOException {

        Util.notNull("context", ctx);
        Util.notNull("state", state);

        Object[] stateToWrite = (Object[]) state;
        ExternalContext externalContext = ctx.getExternalContext();
        Object sessionObj = externalContext.getSession(true);
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        synchronized (sessionObj) {
            Map<String, Map> logicalMap = TypedCollections.dynamicallyCastMap(
                  (Map) sessionMap
                        .get(LOGICAL_VIEW_MAP), String.class, Map.class);
            if (logicalMap == null) {
                logicalMap = new LRUMap<String, Map>(numberOfLogicalViews);
                sessionMap.put(LOGICAL_VIEW_MAP, logicalMap);
            }

            String idInLogicalMap = createUniqueRequestId(ctx);
            String idInActualMap = createUniqueRequestId(ctx);

            Map<String, Object[]> actualMap =
                  TypedCollections.dynamicallyCastMap(
                        logicalMap.get(idInLogicalMap), String.class, Object[].class);
            if (actualMap == null) {
                actualMap = new LRUMap<String, Object[]>(numberOfViews);
                logicalMap.put(idInLogicalMap, actualMap);
            }

            String id = idInLogicalMap + ':' + idInActualMap;
            Object[] stateArray = actualMap.get(idInActualMap);
            // reuse the array if possible
            if (stateArray != null) {
                stateArray[0] = stateToWrite[0];
                stateArray[1] = handleSaveState(stateToWrite[1]);
            } else {
                actualMap.put(idInActualMap, new Object[]{stateToWrite[0],
                                                          stateToWrite[1]});
            }

            // always call put/setAttribute as we may be in a clustered environment.
            sessionMap.put(LOGICAL_VIEW_MAP, logicalMap);

             if (stateCapture != null) {
                stateCapture.append(id);
             } else {
                 ResponseWriter writer = ctx.getResponseWriter();
                 writer.write(stateFieldStart);
                 writer.write(id);
                 writer.write(STATE_FIELD_END);
                 writeRenderKitIdField(ctx, writer);
             }
        }


    }


    /**
     * <p>Inspects the incoming request parameters for the standardized state
     * parameter name.  In this case, the parameter value will be the composite
     * ID generated by {@link com.sun.faces.renderkit.ServerSideStateHelper#writeState(javax.faces.context.FacesContext, Object, StringBuilder)}.</p>
     *
     * <p>The composite key will be used to find the appropriate view within the
     * session obtained from the provided <code>FacesContext</code>
     *
     * @see {@link com.sun.faces.renderkit.StateHelper#getState(javax.faces.context.FacesContext, String)}
     */
    public Object getState(FacesContext ctx, String viewId) {

        String compoundId = getStateParamValue(ctx);
        if (compoundId == null) {
            return null;
        }

        int sep = compoundId.indexOf(':');
        assert (sep != -1);
        assert (sep < compoundId.length());

        String idInLogicalMap = compoundId.substring(0, sep);
        String idInActualMap = compoundId.substring(sep + 1);

        ExternalContext externalCtx = ctx.getExternalContext();
        Object sessionObj = externalCtx.getSession(false);

        // stop evaluating if the session is not available
        if (sessionObj == null) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                           "Unable to restore server side state for view ID {0} as no session is available",
                           viewId);
            }
            return null;
        }

        synchronized (sessionObj) {
            Map logicalMap = (Map) externalCtx.getSessionMap() .get(LOGICAL_VIEW_MAP);
            if (logicalMap != null) {
                Map actualMap = (Map) logicalMap.get(idInLogicalMap);
                if (actualMap != null) {
                    return actualMap.get(idInActualMap);
                }
            }
        }

        return null;
        
    }

   
    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Utility method for obtaining the <code>Integer</code> based configuration
     * values used to change the behavior of the <code>ServerSideStateHelper</code>.
     * @param param the paramter to parse
     * @return the Integer representation of the parameter value
     */
    protected Integer getIntegerConfigValue(WebContextInitParameter param) {

        String noOfViewsStr = webConfig.getOptionValue(param);
        Integer value = null;
        try {
            value = Integer.valueOf(noOfViewsStr);
        } catch (NumberFormatException nfe) {
            String defaultValue = param.getDefaultValue();
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING,
                           "Error parsing the context init parameter {0}.  Using default value {1} instead.",
                           new Object[] { param.getQualifiedName(),
                                          defaultValue} );
            }
            try {
                value = Integer.valueOf(defaultValue);
            } catch (NumberFormatException ne) {
                // won't occur
            }
        }

        return value;

    }


    /**
     * @param state the object returned from <code>UIView.processSaveState</code>
     * @return If {@link com.sun.faces.config.WebConfiguration.BooleanWebContextInitParameter#SerializeServerState} is
     *  <code>true</code>, serialize and return the state, otherwise, return
     *  <code>state</code> unchanged.
     */
    protected Object handleSaveState(Object state) {

        if (webConfig.isOptionEnabled(SerializeServerState)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            ObjectOutputStream oas = null;
            try {
                oas = serialProvider
                      .createObjectOutputStream(((compressViewState)
                                                 ? new GZIPOutputStream(baos, 1024)
                                                 : baos));
                //noinspection NonSerializableObjectPassedToObjectStream
                oas.writeObject(state);
                oas.flush();
            } catch (Exception e) {
                throw new FacesException(e);
            } finally {
                if (oas != null) {
                    try {
                        oas.close();
                    } catch (IOException ignored) { }
                }
            }
            return baos.toByteArray();
        } else {
            return state;
        }

    }


    /**
     * @param state the state as it was stored in the session
     * @return an object that can be passed to <code>UIViewRoot.processRestoreState</code>.
     *  If {@link com.sun.faces.config.WebConfiguration.BooleanWebContextInitParameter#SerializeServerState} de-serialize the
     *  state prior to returning it, otherwise return <code>state</code> as is.
     */
    protected Object handleRestoreState(Object state) {

        if (webConfig.isOptionEnabled(SerializeServerState)) {
            ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) state);
            ObjectInputStream ois = null;
            try {
                ois = serialProvider
                      .createObjectInputStream(((compressViewState)
                                                ? new GZIPInputStream(bais, 1024)
                                                : bais));
                return ois.readObject();
            } catch (Exception e) {
                throw new FacesException(e);
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ignored) { }
                }
            }
        } else {
            return state;
        }

    }


    /**
     * @param ctx the <code>FacesContext</code> for the current request
     * @return a unique ID for building the keys used to store
     *  views within a session
     */
    private String createUniqueRequestId(FacesContext ctx) {

        Map<String, Object> sm = ctx.getExternalContext().getSessionMap();
        AtomicInteger idgen =
              (AtomicInteger) sm.get(STATEMANAGED_SERIAL_ID_KEY);
        if (idgen == null) {
            idgen = new AtomicInteger(1);
        }
        
        // always call put/setAttribute as we may be in a clustered environment.
        sm.put(STATEMANAGED_SERIAL_ID_KEY, idgen);
        return (UIViewRoot.UNIQUE_ID_PREFIX + idgen.getAndIncrement());

    }
    
}
