<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--
 Copyright 2004 Sun Microsystems, Inc. All Rights Reserved.
 
 Redistribution and use in source and binary forms, with or
 without modification, are permitted provided that the following
 conditions are met:
 
 - Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
 
 - Redistribution in binary form must reproduce the above
   copyright notice, this list of conditions and the following
   disclaimer in the documentation and/or other materials
   provided with the distribution.
    
 Neither the name of Sun Microsystems, Inc. or the names of
 contributors may be used to endorse or promote products derived
 from this software without specific prior written permission.
  
 This software is provided "AS IS," without a warranty of any
 kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
  
 You acknowledge that this software is not designed, licensed or
 intended for use in the design, construction, operation or
 maintenance of any nuclear facility.
-->

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head> <title>Hello</title> </head>
    <%@ page contentType="application/xhtml+xml" %>
    <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
    <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
    <body bgcolor="white">
    <f:view>
    <h:form id="helloForm" >
      <h2>Hi. My name is Duke.  I'm thinking of a number from
      <h:outputText lang="en_US" value="#{UserNumberBean.minimum}"/> to
      <h:outputText value="#{UserNumberBean.maximum}"/>.  Can you guess
      it?</h2>

        <h:graphicImage id="waveImg" url="/wave.med.gif" />
  	<h:inputText id="userNo" value="#{UserNumberBean.userNumber}"
                      validator="#{UserNumberBean.validate}"/>          
	 <h:commandButton id="submit" action="success" value="Submit" />
         <p />
	 <h:message showSummary="true" showDetail="false" style="color: red; font-family: 'New Century Schoolbook', serif; font-style: oblique; text-decoration: overline" id="errors1" for="userNo"/>

    </h:form>
    </f:view>

    <p>
      <a href="http://validator.w3.org/check?uri=referer"><img
          src="http://www.w3.org/Icons/valid-xhtml10"
          alt="Valid XHTML 1.0!" height="31" width="88" /></a>
    </p>
    </body>
</html>  
