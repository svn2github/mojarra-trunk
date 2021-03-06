#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# Copyright 2008 Sun Microsystems, Inc. All rights reserved.
#
# The contents of this file are subject to the terms of either the GNU
# General Public License Version 2 only ("GPL") or the Common Development
# and Distribution License("CDDL") (collectively, the "License").  You
# may not use this file except in compliance with the License. You can obtain
# a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
# or updatetool/LICENSE.txt.  See the License for the specific
# language governing permissions and limitations under the License.
#
# When distributing the software, include this License Header Notice in each
# file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
# Sun designates this particular file as subject to the "Classpath" exception
# as provided by Sun in the GPL Version 2 section of the License file that
# accompanied this code.  If applicable, add the following below the License
# Header, with the fields enclosed by brackets [] replaced by your own
# identifying information: "Portions Copyrighted [year]
# [name of copyright owner]"
#
# Contributor(s):
#
# If you wish your version of this file to be governed by only the CDDL or
# only the GPL Version 2, indicate your decision by adding "[Contributor]
# elects to include this software in this distribution under the [CDDL or GPL
# Version 2] license."  If you don't indicate a single choice of license, a
# recipient has the option to distribute your version of this file under
# either the CDDL, the GPL Version 2 or to extend the choice of license to
# its licensees as provided above.  However, if you add GPL Version 2 code
# and therefore, elected the GPL Version 2 license, then the option applies
# only if the new code is made subject to such option by the copyright
# holder.
#
pkg = {
    "name"          : "glassfish-jsf",
    "version"       : "2.0.4,0-7",
    "depends"       : {
                       "pkg:/glassfish-jsf@2.1" : {"type" : "incorporate"},
                       "pkg:/glassfish-common" : { "type" : "require" }
                      },
    "attributes"    : { 
                        "pkg.summary" : "JSF 2.1.0- Project Mojarra 2.1.0 Patch Release",
                        "pkg.description" : "Updated Mojarra 2.1 implementation.  \
Current version supports JSF 2.1.  This version replaces the default version \
of Mojarra that ships with Glassfish.",
                        "info.classification" : "Application Servers"
                      },

    "files"         : {
                       "jsf2.1/LICENSE"      : {"mode" : "0644"},
                       "jsf2.1/README"       : {"mode" : "0644"},
                       },

    "licenses"      : {
                       "jsf2.1/LICENSE"      : {"license" : "JSF-LICENSE"},
                       },

    "dirtrees"      : [
                        "glassfish",
                        "jsf2.1",
                      ]
}

