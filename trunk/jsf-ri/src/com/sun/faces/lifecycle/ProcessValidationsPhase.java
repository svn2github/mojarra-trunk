/*
 * $Id: ProcessValidationsPhase.java,v 1.4 2002/06/21 00:31:22 eburns Exp $
 */

/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

// ProcessValidationsPhase.java

package com.sun.faces.lifecycle;

import org.mozilla.util.Assert;
import org.mozilla.util.ParameterCheck;

import javax.faces.FacesException;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.Phase;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;

import java.util.Iterator;


/**

 * <B>Lifetime And Scope</B> <P> Same lifetime and scope as
 * DefaultLifecycleImpl.
 *
 * @version $Id: ProcessValidationsPhase.java,v 1.4 2002/06/21 00:31:22 eburns Exp $
 * 
 * @see	com.sun.faces.lifecycle.DefaultLifecycleImpl
 * @see	javax.faces.lifecycle.Lifecycle#PROCESS_VALIDATIONS_PHASE
 *
 */

public class ProcessValidationsPhase extends GenericPhaseImpl
{
//
// Protected Constants
//

//
// Class Variables
//

//
// Instance Variables
//

// Attribute Instance Variables

// Relationship Instance Variables

//
// Constructors and Genericializers    
//

public ProcessValidationsPhase(Lifecycle newDriver, int newId)
{
    super(newDriver, newId, 
	  new LifecycleCallback() {
	      public int takeActionOnComponent(FacesContext context,
					       UIComponent comp) throws FacesException {
                  comp.validate(context);                                 
		  Iterator validators = comp.getValidators();
		  
		  Assert.assert_it(null != validators);
		  while (validators.hasNext()) {
		      ((Validator) validators.next()).validate(context, comp);
		  }

		  return Phase.GOTO_NEXT;
	      }
	  });
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

public int execute(FacesContext facesContext) throws FacesException
{
    int rc = Phase.GOTO_NEXT;
    Iterator messageIter = null;

    rc = traverseTreeInvokingCallback(facesContext);
    if (rc != Phase.GOTO_NEXT) {
	return rc;
    }

    Assert.assert_it(false, "PENDING(): fixme");

    messageIter = facesContext.getMessages();

    Assert.assert_it(null != messageIter);

    if (messageIter.hasNext()) {
	// Proceed based on the number of errors present
	rc = Phase.GOTO_RENDER;
    }
    return rc;
}


// The testcase for this class is TestProcessValidationsPhase.java


} // end of class ProcessValidationsPhase
