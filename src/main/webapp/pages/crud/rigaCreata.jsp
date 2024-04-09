<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.crud.AbstractCrudAction" />
<md:sessionMessages/>
<div class="forms">
	<form action='<c:out value="${actionBean.absoluteOriginalPath}"/>?${_csrf.parameterName}=${_csrf.token}${empty param.idStrut ? '': '&idStrut='}${empty param.idStrut ? '': param.idStrut}' style="margin: 0px;" method="post" enctype="multipart/form-data" >
		<md:write name="actionBean" property="elNewRiga"/>
		
	</form>
</div>
