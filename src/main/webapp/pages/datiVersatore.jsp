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

<%@ page language="java" contentType="text/html; charset=UTF-8" %><%@page
	import="net.datasiel.simpaweb.actionbeans.GestionePassword"%><%@ taglib
	prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib uri="/manydesigns-elements" prefix="md"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:useBean id="actionBean" scope="request"
	type="net.datasiel.simpaweb.actionbeans.GestionePassword" />
<stripes:layout-render name="/pages/layoutDefinitions/layoutHome.jsp">
	<stripes:layout-component name="body">
		<div class="row-fluid">
					<div class="span6" >
						<md:sessionMessages/>
					</div>
			<div class="offset3 span6">
				<div class="well well-large">
					<stripes:form class="form-inline"
						beanclass="net.datasiel.simpaweb.actionbeans.GestionePassword">
						<c:if test="${sessionScope.passwordVersatore eq null}">
							<h4>Inserire la Password Versatore :</h4>
						</c:if>
						<c:if test="${sessionScope.passwordVersatore ne null}">
							<h4>Modifica Password Versatore</h4>
						</c:if>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<stripes:password name="passwordVersatore" class="input-large"></stripes:password>
							<button type="submit" value="confermaPassword"
								class="btn btn-sacer btn-medium"
								name="doConfermaPassword">
								Conferma
							</button>
						</div>
						<stripes:hidden name="idStrut"></stripes:hidden>
						<stripes:hidden name="idrecord"></stripes:hidden>
						<stripes:hidden name="azione"></stripes:hidden>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</stripes:form>
				</div>
			</div>
		</div>
	</stripes:layout-component>

</stripes:layout-render>
