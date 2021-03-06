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

package com.sun.faces.facelets.el;

import javax.el.*;
import javax.faces.view.facelets.TagAttribute;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Jacob Hookom
 * @version $Id$
 */
public final class TagValueExpression extends ValueExpression implements
        Externalizable {

    private static final long serialVersionUID = 1L;

    private ValueExpression orig;

    private String attr;

    public TagValueExpression() {
        super();
    }

    public TagValueExpression(TagAttribute attr, ValueExpression orig) {
        this.attr = attr.toString();
        this.orig = orig;
    }

    public Class getExpectedType() {
        return this.orig.getExpectedType();
    }

    public Class getType(ELContext context) {
        try {
            return this.orig.getType(context);
        } catch (PropertyNotFoundException pnfe) {
            throw new PropertyNotFoundException(this.attr + ": "
                    + pnfe.getMessage(), pnfe.getCause());
        } catch (ELException e) {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    public Object getValue(ELContext context) {
        try {
            return this.orig.getValue(context);
        } catch (PropertyNotFoundException pnfe) {
            throw new PropertyNotFoundException(this.attr + ": "
                    + pnfe.getMessage(), pnfe.getCause());
        } catch (ELException e) {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    public boolean isReadOnly(ELContext context) {
        try {
            return this.orig.isReadOnly(context);
        } catch (PropertyNotFoundException pnfe) {
            throw new PropertyNotFoundException(this.attr + ": "
                    + pnfe.getMessage(), pnfe.getCause());
        } catch (ELException e) {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    public void setValue(ELContext context, Object value) {
        try {
            this.orig.setValue(context, value);
        } catch (PropertyNotFoundException pnfe) {
            throw new PropertyNotFoundException(this.attr + ": "
                    + pnfe.getMessage(), pnfe.getCause());
        } catch (PropertyNotWritableException pnwe) {
            throw new PropertyNotWritableException(this.attr + ": "
                    + pnwe.getMessage(), pnwe.getCause());
        } catch (ELException e) {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    @Override
    public boolean equals(Object obj) {
        return this.orig.equals(obj);
    }

    public String getExpressionString() {
        return this.orig.getExpressionString();
    }

    public int hashCode() {
        return this.orig.hashCode();
    }

    public boolean isLiteralText() {
        return this.orig.isLiteralText();
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.orig = (ValueExpression) in.readObject();
        this.attr = in.readUTF();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.orig);
        out.writeUTF(this.attr);
    }

    public ValueExpression getWrapped() {
        return orig;
    }

    public String toString() {
        return this.attr;
    }
}
