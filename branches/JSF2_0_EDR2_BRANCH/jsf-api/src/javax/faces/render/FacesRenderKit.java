/*
 * $Id: Renderer.java,v 1.39.12.7 2008/04/17 18:51:29 edburns Exp $
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

package javax.faces.render;

import javax.faces.convert.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p class="changed_added_2_0">The presence of this annotation on a
 * class automatically registers the class with the runtime as a {@link
 * RenderKit}.  The value of the {@link #value} attribute is taken to be
 * the <em>render-kit-id</em>.  The implementation must guarantee that
 * for each class annotated with <code>FacesRenderer</code>, found with
 * the algorithm "<em><a target="_"
 * href="../component/FacesComponent.html#componentConfigAnnotationScanningSpecification">componentConfigAnnotationScanningSpecification</a></em>",
 * {@link
 * javax.faces.render.RenderKitFactory#addRenderKit(java.lang.String,RenderKit)}
 * is called, passing the derived <em>render-kit-id</em> as the first
 * argument and an instance of this class as the second argument.  There
 * must be a public zero-argument constructor on any class where this
 * annotation appears.  The implementation must indicate a fatal error
 * if such a constructor does not exist and the application must not be
 * placed in service.  The implementation must guarantee that all such
 * calls to <code>addRenderKit()</code> happen during application
 * startup time and before any requests are serviced.  After performing
 * the scanning algorithm, the implementation must guarantee that all
 * classes annotated with <code>FacesRenderKit</code> have their
 * annotations processed before any classes annotated with {@link
 * FacesRenderer}.</p>
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FacesRenderKit {

    /**
     * <p class="changed_added_2_0">The value of this annotation
     * attribute is taken to be the <em>render-kit-id</em> with which a
     * reference to an instance of this class of <code>RenderKit</code>
     * can be obtained by calling {@link
     * javax.faces.render.RenderKitFactory#getRenderKit}.</p>
     */ 

    String value();


}
