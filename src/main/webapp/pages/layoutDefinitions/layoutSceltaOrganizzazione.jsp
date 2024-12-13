<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%><%@ taglib
	uri="/manydesigns-elements" prefix="md"%>
<%-- 	<%@ taglib --%>
<%-- 	uri = "http://shiro.apache.org/tags" prefix="shiro"%> --%>
	<%@ page import="net.datasiel.webapp.InfoToolbar"%>
	<%@ page import="java.util.LinkedHashMap"%>
	<stripes:layout-definition>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<fmt:setBundle basename="MessageResources" scope="request" />
<jsp:useBean id="actionBean" scope="request"
	type="net.datasiel.simpaweb.actionbeans.SceltaOrganizzazioneAction" />
<%-- <% net.datasiel.simpaweb.actionbeans.HomePrivata actionBean= new net.datasiel.simpaweb.actionbeans.HomePrivata(); %> --%>



<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
		<!-- default header name is X-CSRF-TOKEN -->
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		<meta name="_csrf" content="${_csrf.token}"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/custom-theme/jquery-ui-1.13.3.min.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/jquery.jgrowl.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css"	href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/bootstrap-tooltip.min.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/bootstrap-popover.min.css"/>" />	
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/icomoon.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>" />
		<!--[if IE 7]>
		<link rel="stylesheet" href="<c:url value="/css/font-awesome-ie7.min.css"/>">
		<![endif]-->
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/aca/forms.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/layoutHome.css"/>" />	
		
		
		<script type="text/javascript" src="<c:url value="/webjars/jquery/3.7.1/jquery.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/elementsStripes.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.countdown.pack-it.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/collapsiblefieldset.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap-tooltip.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap-popover.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.jgrowl.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.dirtyform.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.cookie.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/webjars/jquery-ui/1.13.3/jquery-ui.min.js"/>"></script>		
		<script type="text/javascript" src="<c:url value="/js/layoutscripts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/menuscripts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/layoutDefinitions/layout.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/help/globalsetup.js"/>"></script>		
			
			
		<!-- SCELTA ENTE/STRUTTURA CON JAVASCRIPT 1/2-->	
		<script type="text/javascript">
			$(document).ready(function() {
				$('#jsAbilitato').removeAttr("style");
			});
		</script>
		
		<title><c:out value="${actionBean.titoloPagina}"/></title>
	</head>

	<body>
		<div id="wrap">
		<div class="container-fluid" >
			<jsp:include page="/pages/layoutDefinitions/header.jsp"></jsp:include>		

					<div class="row-fluid" >
						<div class="span6">
	<%
							LinkedHashMap options = null;
							String labelStrutturaSelezionata = "";
	
							// gestione visualizzazione pulsante per selezione altra struttura in caso di javascript disabilitato;
							// in caso di una sola struttura per l'utente autenticato il pulsante non deve comparire
							boolean visualizzaPulsante = false;
							try {
								com.manydesigns.elements.forms.Form enteStruttura = (com.manydesigns.elements.forms.Form)(((net.datasiel.simpaweb.actionbeans.SceltaOrganizzazioneAction)actionBean).getEnteStruttura());
								com.manydesigns.elements.fields.SelectField selectField = (com.manydesigns.elements.fields.SelectField)enteStruttura.get(0).get(0);
								options = (LinkedHashMap)selectField.getOptions();
								if (options.size() > 1) {
									visualizzaPulsante = true;
									labelStrutturaSelezionata = selectField.getStringValue();
								}
							} catch (Exception e) {}
	%>
	
							<!-- SCELTA ENTE/STRUTTURA SENZA JAVASCRIPT -->
							<noscript>
								<form id="enteStrutturaForm" style="margin: 0px;" method="post" enctype="multipart/form-data" >
									<md:write name="actionBean" property="enteStruttura"/>
	<%
									if (visualizzaPulsante) {
	%>							
										<button type="submit" name="executeDefalut" accesskey="S" title="Salva [Alt-S]" class="btn btn-sacer menuAzioni">Cambia ente/struttura</button>
	<%
									}
	%>								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								</form>
							</noscript>
	<%
							if (options != null) {
	%>
	
								<!-- SCELTA ENTE/STRUTTURA CON JAVASCRIPT 2/2 -->
								<div id="jsAbilitato" style="display: none;">
									<ul class="nav nav-pills pull-left">
	<%
										if (options.size() > 1) {
	%>										
											<li class="dropdown">
												<a class="dropdown-toggle" data-toggle="dropdown" href="#">
													<i class="icon-sitemap"></i> <%=labelStrutturaSelezionata %> <b class="caret"></b>
												</a> 
													<ul class="dropdown-menu">
														<c:forEach var="current" items="<%=options%>">
	<% 
															java.util.Map.Entry current = (java.util.Map.Entry)pageContext.getAttribute("current");
															com.manydesigns.elements.options.SelectionModel.Option currentOption = (com.manydesigns.elements.options.SelectionModel.Option)current.getValue();
															String labelStrutturaSelezionabile = currentOption.label;
															Long valueStrutturaSelezionabile = (Long)currentOption.value;
															if (!labelStrutturaSelezionata.equals(labelStrutturaSelezionabile)) {
	%>									
																<li class="" >
							 										<stripes:link beanclass="net.datasiel.simpaweb.actionbeans.HomePrivata"><i class="icon-chevron-right" style="color: #2F96B4"></i> <c:out value="<%=labelStrutturaSelezionabile %>"/>
							 											<stripes:param name="idStrut" value="<%=valueStrutturaSelezionabile %>"></stripes:param>
							 										</stripes:link>
																</li>
	<%
															}
	%>
														</c:forEach>
													</ul>
											</li>
	<%
											} else {
												com.manydesigns.elements.options.SelectionModel.Option opt = (com.manydesigns.elements.options.SelectionModel.Option)options.values().iterator().next(); 
	%>
												<li class="dropdown" >
													<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							 							<i class="icon-list-alt"></i> <%= opt.label%>
							 						</a>
												</li>
	<%											
											}
	%>											
									</ul>
								</div>
	<%
							}
	%>
	
						</div>
						
						<div class="span5" >
							<ul class="nav nav-pills pull-right">
								<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<i class="icon-user"></i> <shiro:principal property="displayName" /> <b class="caret"></b>
								</a> 
								<ul class="dropdown-menu">
									<li>
										<stripes:link beanclass="net.datasiel.simpaweb.actionbeans.Logout"><i class="icon-off" style="color: #2F96B4"></i> Esci</stripes:link>
									</li>
								</ul>
								</li>
							</ul>
						</div>
						<div class="span1" >
						</div>
						
					</div>

<%-- 			<stripes:layout-component name="areaMessaggi"> --%>
<%-- 				<c:if test="${actionBean.messaggiPresenti}"> --%>
<!-- 					<div class="row-fluid" > -->
<!-- 							<div class="span6" > -->
<%-- 								<md:sessionMessages/> --%>
<!-- 							</div> -->
<!-- 							<div class="span3 offset3" > -->
<!-- 							</div> -->
<!-- 					</div> -->
<%-- 				</c:if> --%>
<%-- 			</stripes:layout-component> --%>
<%-- 			<stripes:layout-component name="body"> --%>
<!-- 				<div class="row-fluid" > -->
<!-- 					<div class="offset3 span6" > -->
<!-- 						  <div class="well well-large"> -->
<%-- 							  <stripes:form class="inline" beanclass="net.datasiel.simpaweb.actionbeans.Login"> --%>
<%-- 							  <img src="<c:url value='/img/Logo-SACER-VERSO_Beta.png' />" alt=""> --%>
<!-- 							  <p>Esegui la login per accedere al sistema di archiviazione.</p> -->
<!-- 							  <p> -->
<%-- 							  	<stripes:text name="userName" class="input-large" ></stripes:text> --%>
<!-- 							  	<input type="password" class="input-large" placeholder="password" name="psswrd"/> -->
<!-- 							  	<button type="submit" value="Accedi" class="btn btn-sacer btn-medium" name="doLogin">Accedi</button> -->
							    
<!-- 							  </p> -->
<%-- 							  </stripes:form> --%>
<!-- 						  </div> -->
<!-- 					</div> -->
<!-- 				</div>	 -->
<%-- 			</stripes:layout-component> --%>
		</div>
		<div id="push"></div>
		</div>
		<div id="footer">
			<jsp:include page="/pages/layoutDefinitions/footer.jsp" />
		</div>
		
<!-- 		<div id="dialogs" class = "hidden"> -->
<!-- 			<div id="confermaEliminazioneUD" title="Avvertenza" > -->
<%-- 				<span><fmt:message key="info.generic.warning.eliminazioneUD" /></span> --%>
<!-- 			</div> -->
<%-- 			<stripes:layout-component name="customDialogs"> --%>
<%-- 			</stripes:layout-component>						 --%>
<!-- 		</div> -->
	
	</body>
</html>
<script type="text/javascript">
	//history.forward();
</script>
</stripes:layout-definition>