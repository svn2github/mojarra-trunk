/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.faces.config;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.ls.LSResourceResolver;
import org.w3c.dom.ls.LSInput;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.sun.faces.util.FacesLogger;

/**
 * <p>Create and configure DocumentBuilderFactory instances.</p>
 */
public class DbfFactory {

    private static final Logger LOGGER = FacesLogger.CONFIG.getLogger();
    
    /**
     * Location of the facelet-taglib 2.0 Schema
     */
    private static final String FACELET_TAGLIB_2_0_XSD =
        "/com/sun/faces/web-facelettaglibrary_2_0.xsd";

    /**
     * Location of the Faces 2.0 Schema
     */
    private static final String FACES_2_0_XSD =
        "/com/sun/faces/web-facesconfig_2_0.xsd";

    /**
     * Location of the Faces 1.2 Schema
     */
    private static final String FACES_1_2_XSD =
         "/com/sun/faces/web-facesconfig_1_2.xsd";


    /**
     * Location of the Faces private 1.1 Schema
     */
    private static final String FACES_1_1_XSD =
         "/com/sun/faces/web-facesconfig_1_1.xsd";

    /**
     * Our cached 2.0 facelet-taglib Schema object for validation
     */
    private static Schema FACELET_TAGLIB_20_SCHEMA;

    /**
     * Our cached 2.0 Schema object for validation
     */
    private static Schema FACES_20_SCHEMA;

    /**
     * Our cached 1.2 Schema object for validation
     */
    private static Schema FACES_12_SCHEMA;

    /**
     * Our cached 1.1 Schema object for validation
     */
    private static Schema FACES_11_SCHEMA;

    /**
     * EntityResolver
     */
    public static final EntityResolver FACES_ENTITY_RESOLVER =
         new FacesEntityResolver();


    public enum FacesSchema {

        FACES_20(FACES_20_SCHEMA),
        FACES_12(FACES_12_SCHEMA),
        FACES_11(FACES_11_SCHEMA),
        FACELET_TAGLIB_20(FACELET_TAGLIB_20_SCHEMA);

        private Schema schema;

        FacesSchema(Schema schema) {
            this.schema = schema;
        }

        public Schema getSchema() {
            return schema;
        }

    }


    /**
     * ErrorHandler
     */
    public static final FacesErrorHandler FACES_ERROR_HANDLER =
         new FacesErrorHandler();


    static {
        initSchema();
    }



    // ---------------------------------------------------------- Public Methods


    public static DocumentBuilderFactory getFactory() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        return factory;

    }   


    /**
     * Init our cache objects.
     */
    private static void initSchema() {       
        // First, cache the various files
        // PENDING_RELEASE (rlubke) clean this up
        try {
            URL url = DbfFactory.class.getResource(FACES_1_2_XSD);
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            InputStream in = conn.getInputStream();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setResourceResolver((LSResourceResolver) DbfFactory.FACES_ENTITY_RESOLVER);
            FACES_12_SCHEMA = factory.newSchema(new StreamSource(in));

            url = DbfFactory.class.getResource(FACES_1_1_XSD);
            conn = url.openConnection();
            conn.setUseCaches(false);
            in = conn.getInputStream();
            factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setResourceResolver((LSResourceResolver) DbfFactory.FACES_ENTITY_RESOLVER);
            FACES_11_SCHEMA = factory.newSchema(new StreamSource(in));

            url = DbfFactory.class.getResource(FACES_2_0_XSD);
            conn = url.openConnection();
            conn.setUseCaches(false);
            in = conn.getInputStream();
            factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setResourceResolver((LSResourceResolver) DbfFactory.FACES_ENTITY_RESOLVER);
            FACES_20_SCHEMA = factory.newSchema(new StreamSource(in));

            url = DbfFactory.class.getResource(FACELET_TAGLIB_2_0_XSD);
            conn = url.openConnection();
            conn.setUseCaches(false);
            in = conn.getInputStream();
            factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setResourceResolver((LSResourceResolver) DbfFactory.FACES_ENTITY_RESOLVER);
            FACELET_TAGLIB_20_SCHEMA = factory.newSchema(new StreamSource(in));

        } catch (Exception e) {
            throw new ConfigurationException(e);
        }

    }

    // ----------------------------------------------------------- Inner Classes

   private static class FacesEntityResolver extends DefaultHandler implements LSResourceResolver {

        /**
         * <p>Contains associations between grammar name and the physical
         * resource.</p>
         */
        private static final String[][] DTD_SCHEMA_INFO = {
            {
                "web-facesconfig_1_0.dtd",
                "/com/sun/faces/web-facesconfig_1_0.dtd"
            },
            {
                "web-facesconfig_1_1.dtd",
                "/com/sun/faces/web-facesconfig_1_1.dtd"
            },
            {
                "web-facesconfig_2_0.xsd",
                 FACES_2_0_XSD
            },
            {
                "facelet-taglib_1_0.dtd",
                "/com/sun/faces/facelet-taglib_1_0.dtd"
            },
            {
                "web-facelettaglibrary_2_0.xsd",
                 FACELET_TAGLIB_2_0_XSD
            },
            {
                "web-facesconfig_1_2.xsd",
                FACES_1_2_XSD
            },
            {
                "web-facesconfig_1_1.xsd",
                FACES_1_1_XSD
            },
            {
                "javaee_5.xsd",
                "/com/sun/faces/javaee_5.xsd"
            },
            {
                "javaee_web_services_client_1_2.xsd",
                "/com/sun/faces/javaee_web_services_client_1_2.xsd"
            },
            {
                "xml.xsd",
                "/com/sun/faces/xml.xsd"
            },
            {
                "datatypes.dtd",
                "/com/sun/faces/datatypes.dtd"
            },
            {
                "XMLSchema.dtd",
                "/com/sun/faces/XMLSchema.dtd"
            }
        };

        /**
         * <p>Contains mapping between grammar name and the local URL to the
         * physical resource.</p>
         */
        private HashMap<String,String> entities =
             new HashMap<String, String>(12, 1.0f);

        // -------------------------------------------------------- Constructors


        public FacesEntityResolver() {

            // Add mappings between last segment of system ID and
            // the expected local physical resource.  If the resource
            // cannot be found, then rely on default entity resolution
            // and hope a firewall isn't in the way or a proxy has
            // been configured
            for (String[] aDTD_SCHEMA_INFO : DTD_SCHEMA_INFO) {
                URL url = this.getClass().getResource(aDTD_SCHEMA_INFO[1]);
                if (url == null) {
                    if (LOGGER.isLoggable(Level.WARNING)) {
                        LOGGER.log(Level.WARNING,
                                   "jsf.config.cannot_resolve_entities",
                                   new Object[]{
                                        aDTD_SCHEMA_INFO[1],
                                        aDTD_SCHEMA_INFO[0]
                                   });
                    }
                } else {
                    entities.put(aDTD_SCHEMA_INFO[0], url.toString());
                }
            }


        } // END JsfEntityResolver


        // ----------------------------------------- Methods from DefaultHandler


        /**
         * <p>Resolves the physical resource using the last segment of
         * the <code>systemId</code>
         * (e.g. http://java.sun.com/dtds/web-facesconfig_1_1.dtd,
         * the last segment would be web-facesconfig_1_1.dtd).  If a mapping
         * cannot be found for the segment, then defer to the
         * <code>DefaultHandler</code> for resolution.</p>
         */
        public InputSource resolveEntity(String publicId, String systemId)
        throws SAXException {

            // publicId is ignored.  Resolution performed using
            // the systemId.

            // If no system ID, defer to superclass
            if (systemId == null) {
                InputSource result;
                try {
                    result = super.resolveEntity(publicId, null);
                }
                catch (Exception e) {
                    throw new SAXException(e);
                }
                return result;
            }

            String grammarName =
                systemId.substring(systemId.lastIndexOf('/') + 1);

            String entityURL = entities.get(grammarName);

            InputSource source;
            if (entityURL == null) {
                // we don't have a registered mapping, so defer to our
                // superclass for resolution

                LOGGER.log(Level.FINE, "Unknown entity, deferring to superclass.");

                try {
                    source = super.resolveEntity(publicId, systemId);
                }
                catch (Exception e) {
                    throw new SAXException(e);
                }

            } else {

                try {
                    source = new InputSource(new URL(entityURL).openStream());
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING,
                               "jsf.config.cannot_create_inputsource",
                               entityURL);
                    source = null;
                }
            }

            // Set the System ID of the InputSource with the URL of the local
            // resource - necessary to prevent parsing errors
            if (source != null) {
                source.setSystemId(entityURL);

                if (publicId != null) {
                    source.setPublicId(publicId);
                }
            }

            return source;

        } // END resolveEntity

       public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
           try {
               InputSource source = resolveEntity(publicId, systemId);
               if (source != null) {
                   return new Input(source.getByteStream());
               }               
           } catch (Exception e) {
               throw new ConfigurationException(e);
           }
           return null;
       }

       // ------------------------------------------------------ Public Methods

        public Map<String,String> getKnownEntities() {

            return Collections.unmodifiableMap(entities);

        }
    } // END FacesEntityResolver


    private static class FacesErrorHandler implements ErrorHandler {
        public void warning(SAXParseException exception) throws SAXException {
            // do nothing
        }

        public void error(SAXParseException exception) throws SAXException {
            throw exception;
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            throw exception;
        }
    } // END FacesErrorHandler


    private static final class Input implements LSInput {
        InputStream in;
        public Input(InputStream in) {
           this.in = in;
        }
        public Reader getCharacterStream() {
            return null;
        }

        public void setCharacterStream(Reader characterStream) { }

        public InputStream getByteStream() {
            return in;
        }

        public void setByteStream(InputStream byteStream) { }

        public String getStringData() {
            return null;
        }

        public void setStringData(String stringData) { }

        public String getSystemId() {
            return null;
        }

        public void setSystemId(String systemId) { }

        public String getPublicId() {
            return null;
        }

        public void setPublicId(String publicId) { }

        public String getBaseURI() {
            return null;
        }

        public void setBaseURI(String baseURI) { }

        public String getEncoding() {
            return null;
        }

        public void setEncoding(String encoding) { }

        public boolean getCertifiedText() {
            return false;
        }

        public void setCertifiedText(boolean certifiedText) { }
    }
}
