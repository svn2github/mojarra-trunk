/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
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
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright 2005-2007 The Apache Software Foundation
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

package com.sun.faces.facelets.tag.jsf.core;

import com.sun.faces.facelets.tag.TagHandlerImpl;
import com.sun.faces.facelets.tag.jsf.CompositeComponentTagHandler;
import com.sun.faces.facelets.util.ReflectionUtil;

import javax.el.ValueExpression;
import javax.faces.application.Resource;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.view.ActionSource2AttachedObjectHandler;
import javax.faces.view.facelets.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * Register an ActionListener instance on the UIComponent associated with the
 * closest parent UIComponent custom action. <p/> See <a target="_new"
 * href="http://java.sun.com/j2ee/javaserverfaces/1.1_01/docs/tlddocs/f/actionListener.html">tag
 * documentation</a>.
 *
 * @author Jacob Hookom
 * @see javax.faces.event.ActionListener
 * @see javax.faces.component.ActionSource
 */
public final class ActionListenerHandler extends TagHandlerImpl
      implements ActionSource2AttachedObjectHandler {

    private final static class LazyActionListener
          implements ActionListener, Serializable {

        private static final long serialVersionUID = -9202120013153262119L;

        private final String type;
        private final ValueExpression binding;

        public LazyActionListener(String type, ValueExpression binding) {
            this.type = type;
            this.binding = binding;
        }

        public void processAction(ActionEvent event)
              throws AbortProcessingException {
            ActionListener instance = null;
            FacesContext faces = FacesContext.getCurrentInstance();
            if (faces == null) {
                return;
            }
            if (this.binding != null) {
                instance = (ActionListener) binding
                      .getValue(faces.getELContext());
            }
            if (instance == null && this.type != null) {
                try {
                    instance = (ActionListener) ReflectionUtil
                          .forName(this.type).newInstance();
                } catch (Exception e) {
                    throw new AbortProcessingException(
                          "Couldn't Lazily instantiate ValueChangeListener",
                          e);
                }
                if (this.binding != null) {
                    binding.setValue(faces.getELContext(), instance);
                }
            }
            if (instance != null) {
                instance.processAction(event);
            }
        }
    }

    private final TagAttribute binding;

    private String listenerType;

    private final TagAttribute typeAttribute;

    /**
     * @param config
     */
    public ActionListenerHandler(TagConfig config) {
        super(config);
        this.binding = this.getAttribute("binding");
        this.typeAttribute = this.getAttribute("type");
        if (null != this.typeAttribute) {
            String stringType = null;
            if (!this.typeAttribute.isLiteral()) {
                FacesContext context = FacesContext.getCurrentInstance();
                FaceletContext ctx = (FaceletContext) context.getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
                stringType = (String) this.typeAttribute.getValueExpression(ctx, String.class).getValue(ctx);
            } else {
                stringType = this.typeAttribute.getValue();
            }
            checkType(stringType);
            this.listenerType = stringType;
        } else {
            this.listenerType = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sun.facelets.FaceletHandler#apply(com.sun.facelets.FaceletContext,
     *      javax.faces.component.UIComponent)
     */
    public void apply(FaceletContext ctx, UIComponent parent)
          throws IOException {
        if (null == parent || !(ComponentHandler.isNew(parent))) {
            return;
        }

        if (parent instanceof ActionSource) {
            applyAttachedObject(ctx.getFacesContext(), parent);
        } else if (parent.getAttributes()
              .containsKey(Resource.COMPONENT_RESOURCE_KEY)) {
            if (null == getFor()) {
                // PENDING(): I18N
                throw new TagException(this.tag,
                                       "actionListener tags nested within composite components must have a non-null \"for\" attribute");
            }
            // Allow the composite component to know about the target
            // component.
            CompositeComponentTagHandler.getAttachedObjectHandlers(parent)
                  .add(this);

        } else {
            throw new TagException(this.tag,
                                   "Parent is not of type ActionSource, type is: "
                                   + parent);
        }
    }

    public void applyAttachedObject(FacesContext context, UIComponent parent) {
        FaceletContext ctx = (FaceletContext) context.getAttributes()
              .get(FaceletContext.FACELET_CONTEXT_KEY);
        ActionSource as = (ActionSource) parent;
        ValueExpression b = null;
        if (this.binding != null) {
            b = this.binding.getValueExpression(ctx, ActionListener.class);
        }
        ActionListener listener = new LazyActionListener(this.listenerType, b);
        as.addActionListener(listener);
    }


    public String getFor() {
        String result = null;
        TagAttribute attr = this.getAttribute("for");

        if (null != attr) {
            if (attr.isLiteral()) {
                result = attr.getValue();
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                FaceletContext ctx = (FaceletContext) context.getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
                result = (String)attr.getValueExpression(ctx, String.class).getValue(ctx);
            }
        }
        return result;

    }

    private void checkType(String type) {
        try {
            ReflectionUtil.forName(type);
        } catch (ClassNotFoundException e) {
            throw new TagAttributeException(typeAttribute,
                "Couldn't qualify ActionListener", e);
        }
    }
}
