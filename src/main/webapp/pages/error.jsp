<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="/manydesigns-elements" prefix="md"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="actionBean" scope="request"
	type="net.datasiel.webapp.BaseAction" />
<stripes:layout-render name="/pages/layoutDefinitions/layoutHome.jsp">
	<stripes:layout-component name="userInfo">
	</stripes:layout-component>
	<stripes:layout-component name="body">
	
			<div class="alert alert-error">
				<a href="javascript: history.go(-1)">Indietro</a>
				Errore critico sulla funzionalita, contattare l'assistenza tecnica.
			</div>
			
	</stripes:layout-component>
</stripes:layout-render>
