<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"
	%><%@ taglib uri="/manydesigns-elements" prefix="md"
	%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
	%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" 
	%>
<%-- 	<%@ taglib --%>
<%-- 	uri = "http://shiro.apache.org/tags" prefix="shiro"%> --%>
	<jsp:useBean id="actionBean" scope="request"
	type="net.datasiel.simpaweb.actionbeans.HomePrivata" />

		<form id="searchFormVersamenti" action="<c:out value="${actionBean.absoluteOriginalPath}"/>?${_csrf.parameterName}=${_csrf.token}${empty param.idStrut ? '': '&idStrut='}${empty param.idStrut ? '': param.idStrut}" style="margin: 0px; padding-bottom: 25px;" method="post" enctype="multipart/form-data" >
			<md:write name="actionBean" property="searchFormVersamenti"/>
			<button type="submit" name="cerca" accesskey="C" title="Cerca tra le Unità Documentarie già versate [Alt-C]" class="btn btn-sacer menuAzioni">Cerca</button>
			<input type="hidden" name="cambiaPagina" />
			<input type="hidden" name="paginaVersamenti" />
			<input type="hidden" name="ricercaEseguita" />
		</form>

		<ul class="media-list">

			<c:choose>
      			<c:when test="${actionBean.ricercaEseguita}">
      				<strong>Trovati <c:out value="${actionBean.udTrovateVersamenti.size()}" /> versamenti</strong>
      			</c:when>
      			<c:otherwise>
      				<strong>Ultimi <c:out value="${actionBean.udTrovateVersamenti.size()}" /> versamenti</strong>
      			</c:otherwise>
			</c:choose>
		
		    <c:forEach var="current"  items="${actionBean.udPaginaVersamenti}">
	    		<stripes:url var="urlVisualizzazione" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"></stripes:param>
	    		</stripes:url>
			  <li class="media rigaUd">
			    <stripes:link title="${current.oggetto}" href="${urlVisualizzazione}"  class="pull-left" >
			      <img class="media-object" src='<stripes:url value="/img/skin1/64x64/mail-receive.png" />'>
				</stripes:link>
			    <div class="media-body">
			 		<div class="pull-right text-right" ><small><strong>Versamento:  <fmt:formatDate type="date" value="${current.dataversamento}"/></strong></small><br/>
<%-- 			 		
					    <div >
						    <stripes:link title="Visualizza ${current.oggetto}" href="${urlVisualizzazione}"  class="btn btn-sacer btn-small" >
								Visualizza
						    </stripes:link>
					    </div>
--%>						    
					</div>
			      	<h4 class="media-heading"><c:out value="${current.cdRegistroUnitaDoc}/${current.anno}/${current.numero}"/></h4>
					<div>
						<small><strong>${current.descTipoUnitaDoc}</strong></small>
			      	 </div>
			    </div>
			  </li>
	    </c:forEach>
	</ul>	