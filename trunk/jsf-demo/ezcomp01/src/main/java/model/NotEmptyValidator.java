// $Id: NotEmptyValidator.java 15846 2009-02-02 11:56:15Z hardy.ferentschik $
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,  
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Hardy Ferentschik
 * @todo Extend to not only support strings, but also collections and maps. Needs to be specified first though.
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

	public void initialize(NotEmpty parameters) {
	}

	public boolean isValid(String object, ConstraintValidatorContext constraintValidatorContext) {
		if ( object == null ) {
			return true;
		}
		int length = object.length();
		return length > 0;
	}
}
