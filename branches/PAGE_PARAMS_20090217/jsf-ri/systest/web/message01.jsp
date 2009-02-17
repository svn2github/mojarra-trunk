<%--
 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 
 Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 
 The contents of this file are subject to the terms of either the GNU
 General Public License Version 2 only ("GPL") or the Common Development
 and Distribution License("CDDL") (collectively, the "License").  You
 may not use this file except in compliance with the License. You can obtain
 a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 language governing permissions and limitations under the License.
 
 When distributing the software, include this License Header Notice in each
 file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 Sun designates this particular file as subject to the "Classpath" exception
 as provided by Sun in the GPL Version 2 section of the License file that
 accompanied this code.  If applicable, add the following below the License
 Header, with the fields enclosed by brackets [] replaced by your own
 identifying information: "Portions Copyrighted [year]
 [name of copyright owner]"
 
 Contributor(s):
 
 If you wish your version of this file to be governed by only the CDDL or
 only the GPL Version 2, indicate your decision by adding "[Contributor]
 elects to include this software in this distribution under the [CDDL or GPL
 Version 2] license."  If you don't indicate a single choice of license, a
 recipient has the option to distribute your version of this file under
 either the CDDL, the GPL Version 2 or to extend the choice of license to
 its licensees as provided above.  However, if you add GPL Version 2 code
 and therefore, elected the GPL Version 2 license, then the option applies
 only if the new code is made subject to such option by the copyright
 holder.
--%>

<%@ page contentType="text/html"
%><%@ page import="java.util.Locale"
%><%@ page import="javax.faces.FactoryFinder"
%><%@ page import="javax.faces.application.Application"
%><%@ page import="javax.faces.application.ApplicationFactory"
%><%@ page import="javax.faces.application.FacesMessage"
%><%@ page import="javax.faces.context.FacesContext"
%><%@ page import="com.sun.faces.util.MessageFactory"
%><%@ page import="javax.faces.component.UIViewRoot, javax.faces.render.RenderKitFactory"
%><%

    // Initialize list of message ids
    String list[] = {
          "javax.faces.validator.NOT_IN_RANGE",
          "javax.faces.validator.DoubleRangeValidator.MAXIMUM",
          "javax.faces.validator.DoubleRangeValidator.MINIMUM",
          "javax.faces.validator.DoubleRangeValidator.TYPE",
          "javax.faces.validator.LengthValidator.MAXIMUM",
          "javax.faces.validator.LengthValidator.MINIMUM",
          "javax.faces.validator.LongRangeValidator.MAXIMUM",
          "javax.faces.validator.LongRangeValidator.MINIMUM",
          "javax.faces.validator.LongRangeValidator.TYPE",
          "javax.faces.component.UIInput.REQUIRED"
    };

    // Acquire the FacesContext instance for this request
    FacesContext facesContext = FacesContext.getCurrentInstance();
    // Acquire our Application instance
    ApplicationFactory afactory = (ApplicationFactory)
          FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
    Application appl = afactory.getApplication();
     if (appl == null) {
        out.println("/message01.jsp FAILED - No Application returned");
        return;
    }
    if (facesContext == null) {
        out.println("/message01.jsp FAILED - No FacesContext returned");
        return;
    }
    UIViewRoot root = (UIViewRoot)
          appl.createComponent(UIViewRoot.COMPONENT_TYPE);
    root.setRenderKitId(RenderKitFactory.HTML_BASIC_RENDER_KIT);
    facesContext.setViewRoot(root);   

    // Test for replacing a Standard Validator Message
    facesContext.getViewRoot().setLocale(new Locale("en", "US"));
    FacesMessage msg = MessageFactory.getMessage(facesContext,
                                                 "javax.faces.validator.DoubleRangeValidator.LIMIT");
    if (!msg.getSummary()
          .equals("Validation Error:This summary replaces the RI summary")) {
        out.println("/message01.jsp FAILED - Missing replacement message");
        return;
    }

    // Check message identifiers that should be present (en_US)
    facesContext.getViewRoot().setLocale(new Locale("en", "US"));
    for (int i = 0; i < list.length; i++) {
        FacesMessage message = MessageFactory.getMessage(facesContext, list[i]);
        if (message == null) {
            out.println("/message01.jsp FAILED - Missing en_US message '" +
                        list[i] + "'");
            return;
        }
    }

    // Check message identifiers that should be present (fr_FR)
    facesContext.getViewRoot().setLocale(new Locale("fr", "FR"));
    for (int i = 0; i < list.length; i++) {
        FacesMessage message = MessageFactory.getMessage(facesContext, list[i]);
        if (message == null) {
            out.println("/message01.jsp FAILED - Missing fr_FR message '" +
                        list[i] + "'");
            return;
        }
    }

    // All tests passed
    out.println("/message01.jsp PASSED");

%>
