/*
 * $Id: UpdateModelValuesPhase.java,v 1.27 2003/10/21 16:41:48 eburns Exp $
 */

/*
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.faces.lifecycle;

import com.sun.faces.context.FacesContextImpl;

import org.mozilla.util.Assert;

import javax.faces.application.Message;
import javax.faces.application.MessageResources;
import com.sun.faces.util.Util;

import javax.faces.FacesException;
import javax.faces.event.PhaseId;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * UpdateModelValuesPhase executes <code>processUpdates</code> on each 
 * component in the tree so that it may have a chance to update its model value.
 */
public class UpdateModelValuesPhase extends Phase {
//
// Protected Constants
//

//
// Class Variables
//

//
// Instance Variables
//
// Log instance for this class
protected static Log log = LogFactory.getLog(UpdateModelValuesPhase.class);

// Attribute Instance Variables

// Relationship Instance Variables


//
// Constructors and Genericializers    
//

public UpdateModelValuesPhase() {
}

//
// Class methods
//

//
// General Methods
//

//
// Methods from Phase
//

public PhaseId getId() {
    return PhaseId.UPDATE_MODEL_VALUES;
}

public void execute(FacesContext facesContext) 
{
    UIComponent component = facesContext.getViewRoot();
    Assert.assert_it(null != component);
    String exceptionMessage = null;
    
    try {
        component.processUpdates(facesContext);
    } 
    catch (IllegalStateException e) {
	exceptionMessage = e.getMessage();
    }
    catch (FacesException fe) {
	exceptionMessage = fe.getMessage();
    }

    if (null != exceptionMessage) {
        Object[] params = new Object[3];
        ValueHolder valueHolder = null;
        if ( component instanceof ValueHolder) {
            valueHolder= (ValueHolder) component;
            params[0] = valueHolder.getValue();
            params[1] = valueHolder.getValueRef();
        }  
        params[2] = exceptionMessage;
        MessageResources resources = Util.getMessageResources();
        Assert.assert_it( resources != null );
        Message msg = resources.getMessage(facesContext,
            Util.MODEL_UPDATE_ERROR_MESSAGE_ID,params);
        facesContext.addMessage(component.getClientId(facesContext), msg);
        if (log.isErrorEnabled()) {
	    log.error(exceptionMessage);
	}
    }
}



// The testcase for this class is TestUpdateModelValuesPhase.java


} // end of class UpdateModelValuesPhase
