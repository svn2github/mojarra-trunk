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

package com.sun.faces.util;

import javax.faces.application.FacesMessage;

import java.text.MessageFormat;

/**
 * <p>This class contains all message constants and utility methods
 * for creating <code>FacesMessage</code> instances or localized 
 * <code>String</code>s for said constants.</p> 
 */
public class MessageUtils {


    // IMPORTANT - ensure that any new message constant is properly
    // tested in test/com/sun/faces/util/TestUtil_messages (see comments
    // in test class for details on the test).    

    public static final String APPLICATION_ASSOCIATE_CTOR_WRONG_CALLSTACK_ID =
          "com.sun.faces.APPLICATION_ASSOCIATE_CTOR_WRONG_CALLSTACK";
    public static final String APPLICATION_ASSOCIATE_EXISTS_ID =
          "com.sun.faces.APPLICATION_ASSOCIATE_EXISTS";
    public static final String ASSERTION_FAILED_ID =
          "com.sun.faces.ASSERTION_FAILED";
    public static final String ATTRIBUTE_NOT_SUPORTED_ERROR_MESSAGE_ID =
          "com.sun.faces.ATTRIBUTE_NOT_SUPORTED";
    public static final String CANT_CLOSE_INPUT_STREAM_ID =
          "com.sun.faces.CANT_CLOSE_INPUT_STREAM";
    public static final String CANT_CONVERT_VALUE_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_CONVERT_VALUE";
    public static final String CANT_CREATE_CLASS_ERROR_ID =
          "com.sun.faces.CANT_CREATE_CLASS_ERROR";
    public static final String CANT_CREATE_LIFECYCLE_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_CREATE_LIFECYCLE_ERROR";
    public static final String CANT_INSTANTIATE_CLASS_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_INSTANTIATE_CLASS";
    public static final String CANT_INTROSPECT_CLASS_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_INTROSPECT_CLASS";
    public static final String CANT_LOAD_CLASS_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_INSTANTIATE_CLASS";
    public static final String CANT_PARSE_FILE_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_PARSE_FILE";
    public static final String CANT_WRITE_ID_ATTRIBUTE_ERROR_MESSAGE_ID =
          "com.sun.faces.CANT_WRITE_ID_ATTRIBUTE";
    public static final String CHILD_NOT_OF_EXPECTED_TYPE_ID =
          "com.sun.faces.CHILD_NOT_OF_EXPECTED_TYPE";
    public static final String COMMAND_LINK_NO_FORM_MESSAGE_ID =
          "com.sun.faces.COMMAND_LINK_NO_FORM_MESSAGE";
    public static final String COMPONENT_NOT_FOUND_ERROR_MESSAGE_ID =
          "com.sun.faces.COMPONENT_NOT_FOUND_ERROR";
    public static final String COMPONENT_NOT_FOUND_IN_VIEW_WARNING_ID =
          "com.sun.faces.COMPONENT_NOT_FOUND_IN_VIEW_WARNING";
    public static final String CONTENT_TYPE_ERROR_MESSAGE_ID =
          "com.sun.faces.CONTENT_TYPE_ERROR";
    public static final String CONVERSION_ERROR_MESSAGE_ID =
          "com.sun.faces.TYPECONVERSION_ERROR";
    public static final String CYCLIC_REFERENCE_ERROR_ID =
          "com.sun.faces.CYCLIC_REFERENCE_ERROR";
    public static final String DUPLICATE_COMPONENT_ID_ERROR_ID =
          "com.sun.faces.DUPLICATE_COMPONENT_ID_ERROR";
    public static final String EL_OUT_OF_BOUNDS_ERROR_ID =
          "com.sun.faces.OUT_OF_BOUNDS_ERROR";
    public static final String EL_PROPERTY_TYPE_ERROR_ID =
          "com.sun.faces.PROPERTY_TYPE_ERROR";
    public static final String EL_SIZE_OUT_OF_BOUNDS_ERROR_ID =
          "com.sun.faces.SIZE_OUT_OF_BOUNDS_ERROR";
    public static final String EMPTY_PARAMETER_ID =
          "com.sun.faces.EMPTY_PARAMETER";
    public static final String ENCODING_ERROR_MESSAGE_ID =
          "com.sun.faces.ENCODING_ERROR";
    public static final String ERROR_GETTING_VALUEREF_VALUE_ERROR_MESSAGE_ID =
          "com.sun.faces.ERROR_GETTING_VALUEREF_VALUE";
    public static final String ERROR_GETTING_VALUE_BINDING_ERROR_MESSAGE_ID =
          "com.sun.faces.ERROR_GETTING_VALUE_BINDING";
    public static final String ERROR_OPENING_FILE_ERROR_MESSAGE_ID =
          "com.sun.faces.ERROR_OPENING_FILE";
    public static final String ERROR_PROCESSING_CONFIG_ID =
         "com.sun.faces.ERROR_PROCESSING_CONFIG";
    public static final String ERROR_REGISTERING_DTD_ERROR_MESSAGE_ID =
          "com.sun.faces.ERROR_REGISTERING_DTD";
    public static final String ERROR_SETTING_BEAN_PROPERTY_ERROR_MESSAGE_ID =
          "com.sun.faces.ERROR_SETTING_BEAN_PROPERTY";
    public static final String EVAL_ATTR_UNEXPECTED_TYPE =
          "com.sun.faces.EVAL_ATTR_UNEXPECTED_TYPE";
    public static final String FACES_CONTEXT_CONSTRUCTION_ERROR_MESSAGE_ID =
          "com.sun.faces.FACES_CONTEXT_CONSTRUCTION_ERROR";
    public static final String FACES_SERVLET_MAPPING_CANNOT_BE_DETERMINED_ID =
          "com.sun.faces.FACES_SERVLET_MAPPING_CANNOT_BE_DETERMINED";
    public static final String FACES_SERVLET_MAPPING_INCORRECT_ID = 
          "com.sun.faces.FACES_SERVLET_MAPPING_INCORRECT";
    public static final String FACES_CONTEXT_NOT_FOUND_ID=
          "com.sun.faces.FACES_CONTEXT_NOT_FOUND";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE_ID =
          "com.sun.faces.FILE_NOT_FOUND";
    public static final String ILLEGAL_ATTEMPT_SETTING_APPLICATION_ARTIFACT_ID =
          "com.sun.faces.ILLEGAL_ATTEMPT_SETTING_APPLICATION_ARTIFACT";    
    public static final String ILLEGAL_CHARACTERS_ERROR_MESSAGE_ID =
          "com.sun.faces.ILLEGAL_CHARACTERS_ERROR";
    public static final String ILLEGAL_IDENTIFIER_LVALUE_MODE_ID =
          "com.sun.faces.ILLEGAL_IDENTIFIER_LVALUE_MODE";
    public static final String ILLEGAL_MODEL_REFERENCE_ID =
          "com.sun.faces.ILLEGAL_MODEL_REFERENCE";
    public static final String ILLEGAL_VIEW_ID_ID =
          "com.sun.faces.ILLEGAL_VIEW_ID";
    public static final String INCORRECT_JSP_VERSION_ID =
          "com.sun.faces.INCORRECT_JSP_VERSION";
    public static final String INVALID_EXPRESSION_ID =
          "com.sun.faces.INVALID_EXPRESSION";
    public static final String INVALID_INIT_PARAM_ERROR_MESSAGE_ID =
          "com.sun.faces.INVALID_INIT_PARAM";
    public static final String INVALID_MESSAGE_SEVERITY_IN_CONFIG_ID =
          "com.sun.faces.INVALID_MESSAGE_SEVERITY_IN_CONFIG";
    public static final String INVALID_SCOPE_LIFESPAN_ERROR_MESSAGE_ID =
          "com.sun.faces.INVALID_SCOPE_LIFESPAN";
    public static final String LIFECYCLE_ID_ALREADY_ADDED_ID =
          "com.sun.faces.LIFECYCLE_ID_ALREADY_ADDED";
    public static final String LIFECYCLE_ID_NOT_FOUND_ERROR_MESSAGE_ID =
          "com.sun.faces.LIFECYCLE_ID_NOT_FOUND";
    public static final String MANAGED_BEAN_CANNOT_SET_LIST_ARRAY_PROPERTY_ID =
          "com.sun.faces.MANAGED_BEAN_CANNOT_SET_LIST_ARRAY_PROPERTY";    
    public static final String MANAGED_BEAN_EXISTING_VALUE_NOT_LIST_ID =
          "com.sun.faces.MANAGED_BEAN_EXISTING_VALUE_NOT_LIST";
    public static final String MANAGED_BEAN_TYPE_CONVERSION_ERROR_ID =
          "com.sun.faces.MANAGED_BEAN_TYPE_CONVERSION_ERROR";
    public static final String MANAGED_BEAN_CLASS_NOT_FOUND_ERROR_ID =
          "com.sun.faces.MANAGED_BEAN_CLASS_NOT_FOUND_ERROR";
    public static final String MANAGED_BEAN_CLASS_DEPENDENCY_NOT_FOUND_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_CLASS_DEPENDENCY_NOT_FOUND_ERROR";
    public static final String MANAGED_BEAN_CLASS_IS_NOT_PUBLIC_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_CLASS_IS_NOT_PUBLIC_ERROR";
    public static final String MANAGED_BEAN_CLASS_IS_ABSTRACT_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_CLASS_IS_ABSTRACT_ERROR";
    public static final String MANAGED_BEAN_CLASS_NO_PUBLIC_NOARG_CTOR_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_CLASS_NO_PUBLIC_NOARG_CTOR_ERROR";
    public static final String MANAGED_BEAN_INJECTION_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_INJECTION_ERROR";
    public static final String MANAGED_BEAN_LIST_PROPERTY_CONFIG_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_LIST_PROPERTY_CONFIG_ERROR";
    public static final String MANAGED_BEAN_AS_LIST_CONFIG_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_AS_LIST_CONFIG_ERROR";
    public static final String MANAGED_BEAN_AS_MAP_CONFIG_ERROR_ID = 
         "com.sun.faces.MANAGED_BEAN_AS_MAP_CONFIG_ERROR";
    public static final String MANAGED_BEAN_MAP_PROPERTY_CONFIG_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_MAP_PROPERTY_CONFIG_ERROR";
    public static final String MANAGED_BEAN_MAP_PROPERTY_INCORRECT_SETTER_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_MAP_PROPERTY_INCORRECT_SETTER_ERROR";
    public static final String MANAGED_BEAN_MAP_PROPERTY_INCORRECT_GETTER_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_MAP_PROPERTY_INCORRECT_GETTER_ERROR";
    public static final String MANAGED_BEAN_DEFINED_PROPERTY_CLASS_NOT_COMPATIBLE_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_DEFINED_PROPERTY_CLASS_NOT_COMPATIBLE_ERROR";
    public static final String MANAGED_BEAN_INTROSPECTION_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_INTROSPECTION_ERROR";
    public static final String MANAGED_BEAN_PROPERTY_DOES_NOT_EXIST_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_PROPERTY_DOES_NOT_EXIST_ERROR";
    public static final String MANAGED_BEAN_PROPERTY_HAS_NO_SETTER_ID =
         "com.sun.faces.MANAGED_BEAN_PROPERTY_HAS_NO_SETTER_ERROR";
    public static final String MANAGED_BEAN_PROPERTY_INCORRECT_ARGS_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_PROPERTY_INCORRECT_ARGS_ERROR";
    public static final String MANAGED_BEAN_LIST_SETTER_DOES_NOT_ACCEPT_LIST_OR_ARRAY_ERROR_ID = 
         "com.sun.faces.MANAGED_BEAN_LIST_SETTER_DOES_NOT_ACCEPT_LIST_OR_ARRAY_ERROR";
    public static final String MANAGED_BEAN_LIST_GETTER_DOES_NOT_RETURN_LIST_OR_ARRAY_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_LIST_SETTER_DOES_NOT_RETURN_LIST_OR_ARRAY_ERROR";
    public static final String MANAGED_BEAN_LIST_GETTER_ARRAY_NO_SETTER_ERROR_ID = 
         "com.sun.faces.MANAGED_BEAN_LIST_GETTER_ARRAY_NO_SETTER_ERROR";
    public static final String MANAGED_BEAN_UNABLE_TO_SET_PROPERTY_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_UNABLE_TO_SET_PROPERTY_ERROR";
    public static final String MANAGED_BEAN_PROBLEMS_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_PROBLEMS_ERROR";
    public static final String MANAGED_BEAN_PROBLEMS_STARTUP_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_PROBLEMS_STARTUP_ERROR";
    public static final String MANAGED_BEAN_UNKNOWN_PROCESSING_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_UNKNOWN_PROCESSING_ERROR";
    public static final String MANAGED_BEAN_PROPERTY_UNKNOWN_PROCESSING_ERROR_ID =
         "com.sun.faces.MANAGED_BEAN_PROPERTY_UNKNOWN_PROCESSING_ERROR";
    public static final String MANAGED_BEAN_INVALID_SCOPE_ERROR_ID =
          "com.sun.faces.MANAGED_BEAN_INVALID_SCOPE";
    public static final String MAXIMUM_EVENTS_REACHED_ERROR_MESSAGE_ID =
          "com.sun.faces.MAXIMUM_EVENTS_REACHED";
    public static final String MISSING_CLASS_ERROR_MESSAGE_ID =
          "com.sun.faces.MISSING_CLASS_ERROR";
    public static final String MISSING_RESOURCE_ERROR_MESSAGE_ID =
          "com.sun.faces.MISSING_RESOURCE_ERROR";
    public static final String MODEL_UPDATE_ERROR_MESSAGE_ID =
          "com.sun.faces.MODELUPDATE_ERROR";
    public static final String NAMED_OBJECT_NOT_FOUND_ERROR_MESSAGE_ID =
          "com.sun.faces.NAMED_OBJECT_NOT_FOUND_ERROR";
    public static final String NOT_NESTED_IN_FACES_TAG_ERROR_MESSAGE_ID =
          "com.sun.faces.NOT_NESTED_IN_FACES_TAG_ERROR";
    public static final String NOT_NESTED_IN_TYPE_TAG_ERROR_MESSAGE_ID =
          "com.sun.faces.NOT_NESTED_IN_TYPE_TAG_ERROR";
    public static final String NOT_NESTED_IN_UICOMPONENT_TAG_ERROR_MESSAGE_ID =
          "com.sun.faces.NOT_NESTED_IN_UICOMPONENT_TAG_ERROR";
    public static final String NO_DTD_FOUND_ERROR_ID =
          "com.sun.faces.NO_DTD_FOUND_ERROR";
    public static final String NO_COMPONENT_ASSOCIATED_WITH_UICOMPONENT_TAG_MESSAGE_ID =
          "com.sun.faces.NO_COMPONENT_ASSOCIATED_WITH_UICOMPONENT_TAG";
    public static final String NULL_BODY_CONTENT_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_BODY_CONTENT_ERROR";
    public static final String NULL_COMPONENT_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_COMPONENT_ERROR";
    public static final String NULL_CONFIGURATION_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_CONFIGURATION";
    public static final String NULL_CONTEXT_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_CONTEXT_ERROR";
    public static final String NULL_EVENT_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_EVENT_ERROR";
    public static final String NULL_FORVALUE_ID =
          "com.sun.faces.NULL_FORVALUE";
    public static final String NULL_HANDLER_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_HANDLER_ERROR";
    public static final String NULL_LOCALE_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_LOCALE_ERROR";
    public static final String NULL_MESSAGE_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_MESSAGE_ERROR";
    public static final String NULL_PARAMETERS_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_PARAMETERS_ERROR";
    public static final String NULL_REQUEST_VIEW_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_REQUEST_VIEW_ERROR";
    public static final String NULL_RESPONSE_STREAM_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_RESPONSE_STREAM_ERROR";
    public static final String NULL_RESPONSE_VIEW_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_RESPONSE_VIEW_ERROR";
    public static final String NULL_RESPONSE_WRITER_ERROR_MESSAGE_ID =
          "com.sun.faces.NULL_RESPONSE_WRITER_ERROR";
    public static final String OBJECT_CREATION_ERROR_ID =
          "com.sun.faces.OBJECT_CREATION_ERROR";
    public static final String OBJECT_IS_READONLY =
          "com.sun.faces.OBJECT_IS_READONLY";
    public static final String PHASE_ID_OUT_OF_BOUNDS_ERROR_MESSAGE_ID =
          "com.sun.faces.PHASE_ID_OUT_OF_BOUNDS";
    public static final String RENDERER_NOT_FOUND_ERROR_MESSAGE_ID =
          "com.sun.faces.RENDERER_NOT_FOUND";
    public static final String REQUEST_VIEW_ALREADY_SET_ERROR_MESSAGE_ID =
          "com.sun.faces.REQUEST_VIEW_ALREADY_SET_ERROR";
    public static final String RESTORE_VIEW_ERROR_MESSAGE_ID =
          "com.sun.faces.RESTORE_VIEW_ERROR";
    public static final String SAVING_STATE_ERROR_MESSAGE_ID =
          "com.sun.faces.SAVING_STATE_ERROR";
    public static final String SUPPORTS_COMPONENT_ERROR_MESSAGE_ID =
          "com.sun.faces.SUPPORTS_COMPONENT_ERROR";
    public static final String VALIDATION_COMMAND_ERROR_ID =
          "com.sun.faces.VALIDATION_COMMAND_ERROR";
    public static final String VALIDATION_EL_ERROR_ID =
          "com.sun.faces.VALIDATION_EL_ERROR";
    public static final String VALIDATION_ID_ERROR_ID =
          "com.sun.faces.VALIDATION_ID_ERROR";   
    public static final String VALUE_NOT_SELECT_ITEM_ID =
          "com.sun.faces.OPTION_NOT_SELECT_ITEM";
    public static final String CANNOT_CONVERT_ID =
          "com.sun.faces.CANNOT_CONVERT";
    public static final String CANNOT_VALIDATE_ID =
          "com.sun.faces.CANNOT_VALIDATE";
    public static final String VERIFIER_CLASS_NOT_FOUND_ID =
           "com.sun.faces.verifier.CLASS_NOT_FOUND";
    public static final String VERIFIER_CLASS_MISSING_DEP_ID =
            "com.sun.faces.verifier.CLASS_MISSING_DEP";
    public static final String VERIFIER_CTOR_NOT_PUBLIC_ID =
            "com.sun.faces.verifier.NON_PUBLIC_DEF_CTOR";
    public static final String VERIFIER_NO_DEF_CTOR_ID =
            "com.sun.faces.verifier.NO_DEF_CTOR";
    public static final String VERIFIER_WRONG_TYPE_ID =
            "com.sun.faces.verifier.WRONG_TYPE";
    public static final String RENDERER_CANNOT_BE_REGISTERED_ID =
          "com.sun.faces.CONFIG_RENDERER_REGISTRATION_MISSING_RENDERKIT";
    public static final String COMMAND_NOT_NESTED_WITHIN_FORM_ID =
          "com.sun.faces.COMMAND_NOT_NESTED_WITHIN_FORM";
    public static final String NAVIGATION_NO_MATCHING_OUTCOME_ID =
          "com.sun.faces.NAVIGATION_NO_MATCHING_OUTCOME";
    public static final String NAVIGATION_NO_MATCHING_OUTCOME_ACTION_ID =
          "com.sun.faces.NAVIGATION_NO_MATCHING_OUTCOME_ACTION";
	public static final String OUTCOME_TARGET_BUTTON_NO_MATCH =
		  "com.sun.faces.OUTCOME_TARGET_BUTTON_NO_MATCH";
	public static final String OUTCOME_TARGET_LINK_NO_MATCH =
		  "com.sun.faces.OUTCOME_TARGET_LINK_NO_MATCH";
    public static final String NO_RESOURCE_TARGET_AVAILABLE =
          "com.sun.faces.RESOURCE_TARGET_NOT_AVAILABLE";
   
    
    public static final String JS_RESOURCE_WRITING_ERROR_ID =
        "com.sun.faces.JS_RESOURCE_WRITING_ERROR";


    // ------------------------------------------------------------ Constructors


    private MessageUtils() {}


    // ---------------------------------------------------------- Public Methods
    

    /**
     * <p>Creates a new <code>FacesMessage</code> instance using the
     * specified #messageId.</p>
     * 
     * @param messageId the message ID
     * @param params an array of substitution parameters
     * @return a new <code>FacesMessage</code> based on the provided
     *  <code>messageId</code>
     */
    public static FacesMessage getExceptionMessage(
          String messageId,
          Object... params) {

        return MessageFactory.getMessage(messageId, params);

    }    


    /**
     * <p>Returns the localized message for the specified 
     * #messageId.</p>
     * 
     * @param messageId the message ID
     * @param params an array of substitution parameters
     * @return the localized message for the specified 
     *  <code>messageId</code>
     */
    public static String getExceptionMessageString(
          String messageId,
          Object... params) {

        String result = null;

        FacesMessage message = MessageFactory.getMessage(messageId, params);
        if (null != message) {
            result = message.getSummary();
        }


        if (null == result) {
            result = "null MessageFactory";
        } else {
            if (params != null) {
                result = MessageFormat.format(result, params);
            }
        }
        return result;

    }

} // END MessageUtils
