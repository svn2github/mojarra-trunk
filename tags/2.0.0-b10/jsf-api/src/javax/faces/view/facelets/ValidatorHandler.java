/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
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
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.faces.view.facelets;



import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.EditableValueHolderAttachedObjectHandler;

/**
 * <p class="changed_added_2_0">Handles setting a {@link
 * javax.faces.validator.Validator} instance on an {@link
 * javax.faces.component.EditableValueHolder} parent. Will wire all
 * attributes set to the <code>Validator</code> instance
 * created/fetched. Uses the "binding" attribute for grabbing instances
 * to apply attributes to.</p> 

 * <p>Will only set/create Validator is the passed UIComponent's parent
 * is null, signifying that it wasn't restored from an existing
 * tree.</p>

 */
public class ValidatorHandler extends FaceletsAttachedObjectHandler implements EditableValueHolderAttachedObjectHandler {

    private String validatorId;
    
    private TagHandlerDelegate helper;

    public ValidatorHandler(ValidatorConfig config) {
        super(config);
        this.validatorId = config.getValidatorId();
    }

    @Override
    protected TagHandlerDelegate getTagHandlerDelegate() {
        if (null == helper) {
            helper = delegateFactory.createValidatorHandlerDelegate(this);
        }
        return helper;
    }

    /**
     * <p>Retrieve the id of the validator that is to be created an added to the parent <code>EditableValueHolder</code>.
     * All subclasses should override this method because it is important for Facelets to have a unique way of
     * identifying the validators that are added to this <code>EditableValueHolder</code> and allows exclusions
     * to work properly. An exclusion is a validator declaration that has the attribute "disabled" which resolves
     * to false, instructing Facelets not to register a default validator with the same id.</p>
     * <p>TODO could support binding by evaluating and reflecting its type for the value of the VALIDATOR_ID field,
     * though technically the validatorId is always required, even when using a binding</p>
     */
    public String getValidatorId(FaceletContext ctx) {
        if (validatorId == null) {
            TagAttribute idAttr = getAttribute("validatorId");
            if (idAttr == null) {
                return null;
            } else {
                return idAttr.getValue(ctx);
            }
        }
        return validatorId;
    }

}
