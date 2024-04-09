<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" />
<md:sessionMessages/>
<div class="forms">
	<form action="<c:out value="${actionBean.absoluteOriginalPath}"/>" style="margin: 0px;" method="post">
		<md:write name="actionBean" property="elNewRiga"/>
		<input type="hidden" name="indiceRiga" value="<c:out value="${actionBean.indiceRiga}"/>"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>  	
</div>