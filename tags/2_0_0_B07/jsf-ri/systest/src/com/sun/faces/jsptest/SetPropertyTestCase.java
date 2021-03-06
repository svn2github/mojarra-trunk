/*
 * $Id: SetPropertyTestCase.java,v 1.7 2007/04/27 22:01:09 ofung Exp $
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

package com.sun.faces.jsptest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.sun.faces.htmlunit.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Test Case for f:setProperty.</p>
 */

public class SetPropertyTestCase extends AbstractTestCase {

    // ------------------------------------------------------------ Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public SetPropertyTestCase(String name) {
        super(name);
    }

    // ------------------------------------------------------ Instance Variables

    // ---------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() throws Exception {
        super.setUp();
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(SetPropertyTestCase.class));
    }


    /**
     * Tear down instance variables required by this test case.
     */
    public void tearDown() {
        super.tearDown();
    }

    // ------------------------------------------------- Individual Test Methods

    public void testSetPropertyPositive() throws Exception {
        HtmlPage page = getPage("/faces/jsp/jsp-setProperty-01.jsp");

        // press the button to increment the property
        assertTrue(page.asText().contains("Integer Property is: 123."));
        assertTrue(page
                .asText().contains("String Property is: This is a String property."));
        List buttons = getAllElementsOfGivenClass(page, new ArrayList(),
                HtmlSubmitInput.class);
        page = (HtmlPage) ((HtmlSubmitInput) buttons.get(0)).click();
        assertTrue(page.asText().contains("Integer Property is: 100."));
        assertTrue(page
                .asText().contains("String Property is: This is a String property."));

        buttons = getAllElementsOfGivenClass(page, new ArrayList(),
                HtmlSubmitInput.class);
        page = (HtmlPage) ((HtmlSubmitInput) buttons.get(1)).click();
        assertTrue(page.asText().contains("Integer Property is: 100."));
        assertTrue(page.asText().contains("String Property is: 100."));

        buttons = getAllElementsOfGivenClass(page, new ArrayList(),
                HtmlSubmitInput.class);
        page = (HtmlPage) ((HtmlSubmitInput) buttons.get(2)).click();
        assertTrue(page.asText().contains("Integer Property is: 100."));
        assertTrue(page.asText().contains("String Property is: String."));

        buttons = getAllElementsOfGivenClass(page, new ArrayList(),
                HtmlSubmitInput.class);
        page = (HtmlPage) ((HtmlSubmitInput) buttons.get(3)).click();
        assertTrue(page.asText().contains("Integer Property is: 100."));
        assertTrue(page
                .asText().contains("String Property is: com.sun.faces.context.FacesContextImpl"));

    }


}
