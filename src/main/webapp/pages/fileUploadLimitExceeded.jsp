<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="/manydesigns-elements" prefix="md"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<stripes:layout-render name="/pages/layoutDefinitions/layoutHome.jsp">
	<stripes:layout-component name="userInfo">
	</stripes:layout-component>
	<stripes:layout-component name="body">

				<div class="errors">
				
			<div class="alert alert-error">
				<a href="javascript: history.go(-1)">Indietro</a>
				<p>Attenzione si Ã¨ tentato di caricare un file che eccede i limiti definiti</p> 
			</div>
				</div>
	</stripes:layout-component>
</stripes:layout-render>
