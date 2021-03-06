/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at
 * https://javaserverfaces.dev.java.net/CDDL.html or
 * legal/CDDLv1.0.txt. 
 * See the License for the specific language governing
 * permission and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at legal/CDDLv1.0.txt.    
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * [Name of File] [ver.__] [Date]
 * 
 * Copyright 2005 Sun Microsystems Inc. All Rights Reserved
 */

var g_url = null;
var g_request = null;


/**
 * Process the response - extract the the 'action' attribute value from
 * the response and use that to cause the rendering of the next view.
 * If any errors are encountered, the current view is re-rendered.
 *
 * Note that this string parsing does not need to occur if we can get
 * the response back as XML - in which case we can more readily find
 * the 'action' attribute value.  Curretnly, if the next view to be
 * rendered is an HTML page, the response content-type is "text/html". 
 */  
function processResponse() {
    var request = getXMLHttpRequest();
    if (request.readyState == 4) {
        if (request.status == 200) {
            var action = request.getResponseHeader("VIEW-URI");
            window.location.href = action;
            return;
        }
   }
}

/**
 * Get an instance of request object.
 */
function getXMLHttpRequest() {
  if (!g_request) {
    if (window.XMLHttpRequest) {
      g_request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
      isIE = true;
      g_request = new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
  return (g_request); 
}

/**
 * Send the request (as a POST).
 */
function sendRequest(url, postData) {
  g_url= url;
  var request = getXMLHttpRequest();
  request.open("POST", url, true);
  request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  request.setRequestHeader("XML-HTTP", "XML-HTTP");
  request.onreadystatechange = processResponse; 
  request.send(postData);
}

/**
 * Retrieve a control's 'form' element.  The control's 'form' element
 * is identified as having an 'action' attribute.
 */ 
function getForm(control) {
    var form = null;
    var namespaceURI = control.namespaceURI;
    var parent = control.parentNode;
    while (parent != null && parent.nodeType == 1) {
        if (parent.namespaceURI == namespaceURI &&
            parent.localName == "g" &&
            parent.getAttribute("action") != null) {
            form = parent;
            break;
        }
        parent = parent.parentNode;
    }
    return form;
}

/**
 * Collect all input field data for the current form, and create a 
 * 'post data' string (used to submit to a server);  Input data is 
 * identified as as 'text' elements that have a 'value' attribute.
 */
function getPostData(form, control) {
    /*
     * Add the control that caused the submission, and the form..
     */
    var formValues = new Array();
    formValues[0] = new Object();
    formValues[0].id = control.parentNode.id;
    formValues[0].value = control.parentNode.id;
    formValues[1] = new Object();
    formValues[1].id = form.id;
    formValues[1].value = form.id;

    var child = form.firstChild;
    while (child != null) {
        if (child.nodeType == 1 && 
            child.localName == "text" &&
            child.getAttribute("value") != null) {
            var len = formValues.length;
            formValues[len] = new Object();
            formValues[len].id = child.id;
            formValues[len].value = child.getAttribute("value");
        }
        child = child.nextSibling;
    }
    var postData = ""; 
    for (var i=0; i<formValues.length; i++) {
        if (formValues[i].id == "javax.faces.ViewState") {
            var re = new RegExp("\\+", "g");
            var val = formValues[i].value;
            formValues[i].value = val.replace(re, "\%2B");
        } 
        postData += formValues[i].id + "=" + formValues[i].value; 
        if (i != formValues.length-1) {
            postData += "&";
        }
    }
    return postData;
}
