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