/*
 * $Id: StoreServletContext.java,v 1.2 2004/07/17 01:37:14 jayashri Exp $
 */

/*
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package com.sun.faces.config;

import javax.servlet.ServletContext;
import javax.faces.context.ExternalContext;

import java.util.Map;
import java.util.Set;
import java.util.Locale;
import java.util.Iterator;
/**
 * <p>The purpose of this class is to call the package private
 * getThreadLocalServletContext() method to set the ServletContext into
 * ThreadLocalStorage, if IS_UNIT_TEST_MODE == true.</p>
 */

public class StoreServletContext extends Object {
    ExternalContext ec = null;
    public void setServletContext(ServletContext sc) {
        ec = new ServletContextAdapter(sc);
	ConfigureListener.getThreadLocalExternalContext().set(new ServletContextAdapter(sc));
    }
    
    public ExternalContext getServletContextWrapper() {
        return ec;
    }

    public class ServletContextAdapter extends ExternalContext {
        
        private ServletContext servletContext = null;
        private ApplicationMap applicationMap = null;
        
        public ServletContextAdapter(ServletContext sc) {
            this.servletContext = sc;
        }
        
        public void dispatch(String path) throws java.io.IOException {
        }
    
        public String encodeActionURL(String url) {
            return null;
        }   

        public String encodeNamespace(String name) {
            return null;
        }


        public String encodeResourceURL(String url) {
            return null;
        }

       public Map getApplicationMap() {
            if (applicationMap == null) {
                applicationMap = new ApplicationMap(servletContext);
            }
            return applicationMap;
        }
        
        public String getAuthType() {
            return null;
        }

        public Object getContext() {
            return servletContext;
        }

        public String getInitParameter(String name) {
            return null;
        }

        public Map getInitParameterMap() {
            return null;
        }

        public String getRemoteUser() {
            return null;
        }


        public Object getRequest() {
            return null;
        }

        public String getRequestContextPath() {
            return null;
        }

        public Map getRequestCookieMap() {
            return null;
        }

        public Map getRequestHeaderMap() {
            return null;
        }


        public Map getRequestHeaderValuesMap() {
            return null;
        }


        public Locale getRequestLocale() {
            return null;
        }

        public Iterator getRequestLocales() {
            return null;
        }



        public Map getRequestMap() {
            return null;
        }


        public Map getRequestParameterMap() {
            return null;
        }


        public Iterator getRequestParameterNames() {
            return null;
        }


        public Map getRequestParameterValuesMap() {
            return null;
        }


        public String getRequestPathInfo() {
            return null;
        }


        public String getRequestServletPath() {
            return null;
        }

        public java.net.URL getResource(String path) throws 
            java.net.MalformedURLException {
            return null;
        }


        public java.io.InputStream getResourceAsStream(String path) {
            return null;
        }

        public Set getResourcePaths(String path) {
            return null;
        }

        public Object getResponse() {
            return null;
        }

        public Object getSession(boolean create) {
            return null;
        }

        public Map getSessionMap() {
            return null;
        }

        public java.security.Principal getUserPrincipal() {
            return null;
        }
        
        public boolean isUserInRole(String role) {
            return false;
        }

        public void log(String message) {
        }
        
        public void log(String message, Throwable exception){
        }
        
        public void redirect(String url) throws java.io.IOException {
        }

    }
    
    class ApplicationMap extends java.util.AbstractMap {

        private ServletContext servletContext = null;

        ApplicationMap(ServletContext servletContext) {
            this.servletContext = servletContext;
        }


        public Object get(Object key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return servletContext.getAttribute(key.toString());
        }


        public Object put(Object key, Object value) {
            if (key == null) {
                throw new NullPointerException();
            }
            String keyString = key.toString();
            Object result = servletContext.getAttribute(keyString);
            servletContext.setAttribute(keyString, value);
            return (result);
        }


        public Object remove(Object key) {
            if (key == null) {
                return null;
            }
            String keyString = key.toString();
            Object result = servletContext.getAttribute(keyString);
            servletContext.removeAttribute(keyString);
            return (result);
        }


        public Set entrySet() {
           throw new UnsupportedOperationException();
        }


        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ApplicationMap))
                return false;
            return super.equals(obj);
        }
        
        public void clear() {
            throw new UnsupportedOperationException();
        }

        public void putAll(Map t) {
            throw new UnsupportedOperationException();
        }
       

    } // END ApplicationMap
}

