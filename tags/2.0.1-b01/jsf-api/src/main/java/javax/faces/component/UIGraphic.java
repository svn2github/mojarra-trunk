/*
 * $Id: UIGraphic.java,v 1.43 2007/04/27 22:00:04 ofung Exp $
 */

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

package javax.faces.component;


import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;


/**
 * <p><strong>UIGraphic</strong> is a {@link UIComponent} that displays
 * a graphical image to the user.  The user cannot manipulate this component;
 * it is for display purposes only.</p>
 *
 * <p>By default, the <code>rendererType</code> property must be set to
 * "<code>javax.faces.Image</code>".  This value can be changed by calling the
 * <code>setRendererType()</code> method.</p>
 */

public class UIGraphic extends UIComponentBase {

    /**
     * Properties that are tracked by state saving.
     */
    enum PropertyKeys {

        /**
         * <p>The local value of this {@link UIComponent}.</p>
         */
        value
    }

    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "javax.faces.Graphic";


    /**
     * <p>The standard component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = "javax.faces.Graphic";


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Create a new {@link UIGraphic} instance with default property
     * values.</p>
     */
    public UIGraphic() {

        super();
        setRendererType("javax.faces.Image");

    }


    // ------------------------------------------------------ Instance Variables


    //private Object value = null;


    // -------------------------------------------------------------- Properties


    public String getFamily() {

        return (COMPONENT_FAMILY);

    }


    /**
     * <p>Return the image URL for this {@link UIGraphic}.  This method is a
     * typesafe alias for <code>getValue()</code>.</p>
     */
    public String getUrl() {

        return ((String) getValue());

    }


    /**
     * <p>Set the image URL for this {@link UIGraphic}.  This method is a
     * typesafe alias for <code>setValue()</code>.</p>
     *
     * @param url The new image URL
     */
    public void setUrl(String url) {

        setValue(url);

    }




    /**
     * <p>Returns the <code>value</code> property of the
     * <code>UIGraphic</code>. This will typically be rendered as an URL.</p>
     */
    public Object getValue() {

        return getStateHelper().eval(PropertyKeys.value);

    }


    /**
     * <p>Sets the <code>value</code> property of the <code>UIGraphic</code>.
     * This will typically be rendered as an URL.</p>
     * 
     * @param value the new value
     */
    public void setValue(Object value) {

        getStateHelper().put(PropertyKeys.value, value);

    }


    // ---------------------------------------------------------------- Bindings


    /**
     * <p>Return any {@link ValueBinding} set for <code>value</code> if a
     * {@link ValueBinding} for <code>url</code> is requested; otherwise,
     * perform the default superclass processing for this method.</p>
     *
     * @param name Name of the attribute or property for which to retrieve
     *  a {@link ValueBinding}
     *
     * @throws NullPointerException if <code>name</code>
     *  is <code>null</code>
     *
     * @deprecated This has been replaced by {@link #getValueExpression(java.lang.String)}.
     */
    public ValueBinding getValueBinding(String name) {

        if ("url".equals(name)) {
            return (super.getValueBinding("value"));
        } else {
            return (super.getValueBinding(name));
        }

    }


    /**
     * <p>Store any {@link ValueBinding} specified for <code>url</code>
     * under <code>value</code> instead; otherwise, perform the default
     * superclass processing for this method.  In all cases, the
     * superclass is relied on to convert the <code>ValueBinding</code>
     * to a <code>ValueExpression</code>.</p>
     *
     * @param name Name of the attribute or property for which to set
     *  a {@link ValueBinding}
     * @param binding The {@link ValueBinding} to set, or <code>null</code>
     *  to remove any currently set {@link ValueBinding}
     *
     * @throws NullPointerException if <code>name</code>
     *  is <code>null</code>
     *
     * @deprecated This has been replaced by {@link #setValueExpression}.
     */
    public void setValueBinding(String name, ValueBinding binding) {

        if ("url".equals(name)) {
            super.setValueBinding("value", binding);
        } else {
            super.setValueBinding(name, binding);
        }

    }

    /**
     * <p>Return any {@link ValueExpression} set for <code>value</code> if a
     * {@link ValueExpression} for <code>url</code> is requested; otherwise,
     * perform the default superclass processing for this method.</p>
     *
     * @param name Name of the attribute or property for which to retrieve
     *  a {@link ValueExpression}
     *
     * @throws NullPointerException if <code>name</code>
     *  is <code>null</code>
     * @since 1.2
     */
    public ValueExpression getValueExpression(String name) {

        if ("url".equals(name)) {
            return (super.getValueExpression("value"));
        } else {
            return (super.getValueExpression(name));
        }

    }
    
    /**
     * <p>Store any {@link ValueExpression} specified for <code>url</code> under
     * <code>value</code> instead; otherwise, perform the default superclass
     * processing for this method.</p>
     *
     * @param name Name of the attribute or property for which to set
     *  a {@link ValueExpression}
     * @param binding The {@link ValueExpression} to set, or <code>null</code>
     *  to remove any currently set {@link ValueExpression}
     *
     * @throws NullPointerException if <code>name</code>
     *  is <code>null</code>
     * @since 1.2
     */
    public void setValueExpression(String name, ValueExpression binding) {

        if ("url".equals(name)) {
            super.setValueExpression("value", binding);
        } else {
            super.setValueExpression(name, binding);
        }

    }

    // ----------------------------------------------------- StateHolder Methods




}
