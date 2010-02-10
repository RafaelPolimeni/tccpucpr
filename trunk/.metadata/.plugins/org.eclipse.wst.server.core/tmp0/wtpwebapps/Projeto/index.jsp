<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
	<head>
		<title>Menu</title>
		
		<style>
			body{
				margin-left: 0px;
				margin-right: 0px;
				margin-top: 0px;
			}
		</style>
	</head>
	<body>
		<f:view>
			<a4j:form>
				<rich:toolBar contentClass="menu">
		            <rich:dropDownMenu>
		                <f:facet name="label"> 
		                    <h:panelGroup>
		                        <h:outputText value="File"/>
		                    </h:panelGroup>
		                </f:facet>
		                <rich:menuItem submitMode="ajax" value="New" >
		                </rich:menuItem>
		                <rich:menuItem submitMode="ajax" value="Open" />
		                <rich:menuGroup value="Save As...">
		                    <rich:menuItem submitMode="ajax" value="Save" />
		                    <rich:menuItem submitMode="ajax" value="Save All" >
		                        <f:facet name="icon">
		                            <h:graphicImage />
		                        </f:facet>
		                    </rich:menuItem>
		                </rich:menuGroup>
		                <rich:menuItem submitMode="ajax" value="Close" />
		                <rich:menuSeparator id="menuSeparator11" />
		                <rich:menuItem submitMode="ajax" value="Exit" />
		            </rich:dropDownMenu>
		
		            <rich:dropDownMenu>
		                <f:facet name="label">
		                    <h:panelGroup>
		                        <h:outputText value="Links" ></h:outputText>
		                    </h:panelGroup>
		                </f:facet>
		
		                <rich:menuItem submitMode="none" onclick="document.location.href='http://labs.jboss.com/jbossrichfaces/'">
		                    <h:outputLink value="http://labs.jboss.com/jbossrichfaces/">
		                        <h:outputText value="RichFaces Home Page"></h:outputText>
		                    </h:outputLink>
		                </rich:menuItem>
		
		                <rich:menuItem submitMode="none" onclick="document.location.href='http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261'">
		                    <h:outputLink
		                        value="http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261">
		                        <h:outputText value="RichFaces Forum"></h:outputText>
		                    </h:outputLink>
		                </rich:menuItem>
		            </rich:dropDownMenu>
		        </rich:toolBar>
			</a4j:form>
		</f:view>
	</body>
</html>