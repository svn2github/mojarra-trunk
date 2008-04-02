/*
 * $Id: FactoryBean.java,v 1.7 2006/02/24 18:05:08 edburns Exp $
 */

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

package com.sun.faces.config.beans;

import com.sun.faces.config.DigesterFactory;
import com.sun.faces.config.DigesterFactory.VersionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Configuration bean for <code>&lt;factory&gt; element.</p>
 */

public class FactoryBean {


    // -------------------------------------------------------------- Properties


    private List<String> applicationFactories = new ArrayList<String>();
    public List<String> getApplicationFactories() { return applicationFactories; }
    public void addApplicationFactory(String applicationFactory)
    { 
        VersionListener listener = DigesterFactory.getVersionListener();
        if (null != listener) {
            listener.takeActionOnArtifact(applicationFactory);
        }
        applicationFactories.add(applicationFactory); }


    private List<String> facesContextFactories = new ArrayList<String>();
    public List<String> getFacesContextFactories() { return facesContextFactories; }
    public void addFacesContextFactory(String facesContextFactory)
    { 
        VersionListener listener = DigesterFactory.getVersionListener();
        if (null != listener) {
            listener.takeActionOnArtifact(facesContextFactory);
        }
        facesContextFactories.add(facesContextFactory); }


    private List<String> lifecycleFactories = new ArrayList<String>();
    public List<String> getLifecycleFactories() { return lifecycleFactories; }
    public void addLifecycleFactory(String lifecycleFactory)
    { 
        VersionListener listener = DigesterFactory.getVersionListener();
        if (null != listener) {
            listener.takeActionOnArtifact(lifecycleFactory);
        }
        lifecycleFactories.add(lifecycleFactory); }


    private List<String> renderKitFactories = new ArrayList<String>();
    public List<String> getRenderKitFactories() { return renderKitFactories; }
    public void addRenderKitFactory(String renderKitFactory)
    { 
        VersionListener listener = DigesterFactory.getVersionListener();
            if (null != listener) {
                listener.takeActionOnArtifact(renderKitFactory);
            }
        renderKitFactories.add(renderKitFactory); }


}
