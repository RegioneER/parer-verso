<%--
 Engineering Ingegneria Informatica S.p.A.

 Copyright (C) 2023 Regione Emilia-Romagna
 <p/>
 This program is free software: you can redistribute it and/or modify it under the terms of
 the GNU Affero General Public License as published by the Free Software Foundation,
 either version 3 of the License, or (at your option) any later version.
 <p/>
 This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU Affero General Public License for more details.
 <p/>
 You should have received a copy of the GNU Affero General Public License along with this program.
 If not, see <https://www.gnu.org/licenses/>.
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.crud.AbstractCrudAction" />
<md:sessionMessages/>
<div class="forms">
	<form action="<c:out value="${actionBean.absoluteOriginalPath}"/>?${_csrf.parameterName}=${_csrf.token}${empty param.idStrut ? '': '&idStrut='}${empty param.idStrut ? '': param.idStrut}" style="margin: 0px;" method="post" enctype="multipart/form-data" >
		<md:write name="actionBean" property="elNewRiga"/>
		<input type="hidden" name="indiceRiga" value="<c:out value="${actionBean.indiceRiga}"/>"/>		
	</form>  	
</div>
