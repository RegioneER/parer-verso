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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.crud.AbstractCrudAction"/>
<stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">

	<stripes:layout-component name="html_head">
		<title><c:out value="${actionBean.creaRigaTitle}"/></title>
	</stripes:layout-component>

	<stripes:layout-component name="body">
		<md:write name="actionBean" property="elNewRiga"/>
	</stripes:layout-component>

	<stripes:layout-component name="menuAzioni">
		<input id="salva" class="btn btn-sacer " accesskey="S" type="submit" value="Salva" name="insertRiga"  title="Salva [Alt-S]"/>
		<br />
		<input id="annulla" class="btn checkChange" accesskey="A" type="submit" value="Annulla" name="cancel"  title="Annulla"/>
		<br />
	</stripes:layout-component>

</stripes:layout-render>
