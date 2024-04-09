<%@page import="it.eng.spagoCore.configuration.ConfigSingleton"%>
<%@page import="net.datasiel.webapp.WebappProperties"%>
<jsp:useBean id="webappProps" class="java.util.Properties" scope="application"/>
<%@page import="it.eng.parer.simparer.security.ClientUser"%>
<%@page import="it.eng.spagoLite.SessionManager"%>
<%@page import="javax.servlet.jsp.jstl.core.LoopTagStatus"
%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  
%><%@page import="java.util.LinkedHashMap"
%><%@page import = "java.lang.reflect.Method" 
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" 
%><%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" 
%><%@ taglib uri="/manydesigns-elements" prefix="md" 
%>
<%-- <%@ taglib	uri = "http://shiro.apache.org/tags" prefix="shiro"%> --%>
<%@ page import="net.datasiel.webapp.InfoToolbar"%>
<stripes:layout-definition><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="MessageResources" scope="request" />
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.BaseAction"/>
<% 	String skin = request.getParameter("skin"); 
	if(skin!=null) {
		session.setAttribute("skin",skin);
	} else {
		skin = (String)session.getAttribute("skin");
		if(skin==null) {
			skin = "skin1";
			session.setAttribute("skin",skin);
		}
	}
%>
<%
// favicon
String favIconRelative = ConfigSingleton.getInstance().getStringValue("FAV_ICON_RELATIVE");
String titoloApplicativo = ConfigSingleton.getInstance().getStringValue("TITOLO_APPLICATIVO");
%>
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
		<!-- default header name is X-CSRF-TOKEN -->
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		<meta name="_csrf" content="${_csrf.token}"/>
			
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/custom-theme/jquery-ui-1.13.1.min.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/jquery.jgrowl.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/icomoon.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>" />
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/bootstrap-tooltip.min.css"/>" />
		
		<!--[if IE 7]>
		<link rel="stylesheet" href="<c:url value="/css/font-awesome-ie7.min.css"/>" />
		<![endif]-->
<!--	<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/aca/base.css"/>"/> -->
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/aca/forms.css"/>"/>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/layoutDefinitions/layout.css"/>"/>
		
		<link rel="Shortcut Icon" type="image/icon" href="<c:url value="<%=favIconRelative%>" />"/>
		
  		<script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.4/jquery.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/elementsStripes.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.countdown.pack-it.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/collapsiblefieldset.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.jgrowl.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.dirtyform.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.cookie.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/webjars/jquery-ui/1.13.2/jquery-ui.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/layoutscripts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/menuscripts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/layoutDefinitions/layout.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/help/globalsetup.js"/>"></script>

		<%-- Commentato: la chiave funziona solo con host name = localhost 
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAyoyvcGEwRCv0OD-GF5azERT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRQmZrPO6S15KvVnpwwHezgpqZdww&sensor=false"
                    type="text/javascript"></script> 
         --%>

		<script type="text/javascript" >         
			$(document).ready(function() {
				//console.log('doc ready'+$('li.active').html());
				$('li.active').trigger("focus");
				$('li.active').attr('tabindex', -1).focus();
				
				$('fieldset legend').wrapInner('<a href="#" />');
				
				$(".btn").click(function(){
					//Disabilitazione pulsanti
 				    $.blockUI({showOverlay: false, message: $('#messaggioAttesa'), css: {width: '20em',height: '3em', padding: '15px'}  }); 
       			});
			});
		</script>
         
		<stripes:layout-component name="html_head">
			<title><%=titoloApplicativo%></title>
		</stripes:layout-component>
	</head>
	<body>		
		<div id="offset3 container">
			<div id="header">
				<jsp:include page="/pages/layoutDefinitions/header.jsp"></jsp:include>
			</div>
			
			
		<stripes:layout-component name="userInfo">
				<div class="row-fluid" >
					<div class="span6">

						<!-- ENTE/STRUTTURA SELEZIONATI -->
						<div>
							<ul class="nav nav-pills pull-left">
								<li class="dropdown" >
									<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			 							<i class="icon-sitemap"></i> <c:out value="${actionBean.enteStrutturaLabel}"/>
			 						</a>
								</li>
							</ul>
						</div>
							
					</div>
					
					<div class="span5" >
						<ul class="nav nav-pills pull-right">
							<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                            <%
        String userName = "";

        try {
            ClientUser user = (ClientUser) SessionManager.getUser(actionBean.getContext().getRequest().getSession());

            userName = user.getUsername();
        } catch (Exception e) {
            log("Impossibile recuperare l'username", e);
        }
                                                            
                                                            
                                                            
                                                            %>
                                                            <%= userName %>
								<i class="icon-user"></i>
								 <b class="caret"></b>
							</a> 
							<ul class="dropdown-menu">
								<li>
									<stripes:link beanclass="net.datasiel.simpaweb.actionbeans.Logout" class="checkChange"><i class="icon-off" style="color: #2F96B4"></i> Esci</stripes:link>
								</li>
							</ul>
							</li>
						</ul>
					</div>
					<div class="span1" >
					</div>
				</div>
<!-- 			</shiro:authenticated> -->
<!-- 			<shiro:notAuthenticated> -->
<!-- 				<div class="row-fluid"><span class="span12" ></span></div> -->
<!-- 			</shiro:notAuthenticated> -->
		</stripes:layout-component>
			<div>
				<%
					Class actionClass = actionBean.getClass();
					InfoToolbar actionInfoToolbar = (InfoToolbar) actionClass.getAnnotation(InfoToolbar.class);

				%>
				
				<form  action="<c:out value="${actionBean.absoluteOriginalPath}"/>?${_csrf.parameterName}=${_csrf.token}${empty param.idStrut ? '': '&idStrut='}${empty param.idStrut ? '': param.idStrut}"  class="form-horizontal" style="margin: 0px;" method="post" enctype="multipart/form-data" >
					<div id="toolbar">
						<div class="intestazionePagina">
							<stripes:layout-component name="breadcrumb">
								<div>
									<ul class="breadcrumb">
								<% 
									
									if ( actionInfoToolbar != null ) {
										int count = 0;
										for (String current : actionInfoToolbar.breadcrumbs()) {
											pageContext.setAttribute("current",current);
											%>
											<li>
											<c:out value="${current}"/>
											<span class="divider">/</span>
											</li>
											
											<%												
										}
										
									}
								%>
									<li>
										
										<%
										String titoloToolbar;
										if ( actionInfoToolbar == null ) {
											titoloToolbar = actionClass.getName();
										} else {
											titoloToolbar = actionInfoToolbar.titolo();
										}
										
										//Dato che EL non consente di invocare metodi bisogna
										//passare da un attributo di context per poter usare c:out
										//ed avere l'escaping del testo visualizzato.
										pageContext.setAttribute("current",titoloToolbar); 
										%>
										<c:out value="${current}"/>
									</li>
									</ul>
								</div>
							</stripes:layout-component>
						</div>

						<div id="tabButtons" style="margin-left: .7em;">
							<stripes:layout-component name="toolbar">						
								<ul class="nav nav-pills">
								<c:forEach var="current" varStatus="statoIterazione"  items="${actionBean.toolbarActions}">
									<% 
										Class myClass = (Class) pageContext.getAttribute("current");
										LoopTagStatus statoIter = (LoopTagStatus) pageContext.getAttribute("statoIterazione");
										InfoToolbar infoToolbar = (InfoToolbar) myClass.getAnnotation(InfoToolbar.class);

										Boolean isTabEnabled = actionBean.isTabEnabled(statoIter.getIndex());
										String cssClassi;
										String cssClasseLi = "";
										if (myClass == actionBean.getClass()) {
											cssClassi = "submitPasso submitPassoSel checkChange";
											cssClasseLi = "active";
										} else {
											cssClassi = "submitPasso checkChange";
										}
										if(!isTabEnabled) {
											cssClasseLi += " disabled";
										}
										String titolo;
										String accelerator;
										String title;
										if (infoToolbar != null) {
											titolo = infoToolbar.titolo();
											accelerator = infoToolbar.accelerator();
											title = String.format("%s [Alt-%s]",titolo,accelerator);
										} else {
											titolo = myClass.getSimpleName();
											accelerator = "";
											title = titolo;
										}
										pageContext.setAttribute("titolo", titolo);
										pageContext.setAttribute("title", title);
									%>
									<li class="<%=cssClasseLi%>">
									<% if (isTabEnabled) { %>
									<stripes:link  accesskey="<%=accelerator%>" beanclass="<%=myClass.getName()%>" class="<%=cssClassi%>" title="${title}" >
										<stripes:param name="idrecord" value="${actionBean.idrecord}"></stripes:param>
										<c:out value="${titolo}"/>
									</stripes:link>
									<% } else { %>
										<a href="#">
											<c:out value="${titolo}"/>
										</a>
									<%}%>
									</li>
								</c:forEach>
								</ul>
							</stripes:layout-component>
							<div class="clear"></div>
						</div>

					</div>
					<div id="wrapper">
						<div class="forms">
							<stripes:layout-component name="body">
								<div>Body</div>
							</stripes:layout-component>			
						</div>
					</div>	
					<div id="toolbarMenu">
						<stripes:layout-component name="toolbarMenu"></stripes:layout-component>
					</div>
					<div id="showPanel" title="Mostra il menu"><span>&laquo;</span></div>
					<div id="messageAnchor"></div>
					<div id="menu">
						<stripes:layout-component name="nascondiMenu">
							<div id="hidePanel" title="Nascondi">&raquo;</div>
						</stripes:layout-component>
						<div >
							<div id="azioni">
								<stripes:layout-component name="menuAzioni">
									<input id="phButton" class="submitAzione" accesskey="a" type="submit" value="Salva" name="update" title="Salva"/>
								</stripes:layout-component>
							</div>
							
							<md:sessionMessages/>

							<div id="aiuti">
								<div class="iconaAiuti">&nbsp;</div>
								<div>
									<span><c:out value="${actionBean.testoHelp}" escapeXml="false" /></span>
								</div>
							</div>
						</div>
						<div id="messaggioConfermaPresenta" title="info.domanda.warning.titolo" style="display: none;">
							<p class="iconaMessaggi">&nbsp;</p>
							<p>
								<span>info.domanda.warning.presenta</span>
							</p>
						</div>	
						<div id="messaggioNascosto" title="Avvertenza" style="display: none;">
							<span>info.domanda.confirmDSN</span>
						</div>	
					</div>					
				</form>
			</div>
			<div><span style="display:none">-</span></div>
			<div id="footer">
				<jsp:include page="/pages/layoutDefinitions/footer.jsp" />
			</div>
		</div>

		<div id="dialogs" class = "hidden">
			<div id="confermaModifiche" title="Avvertenza">
				<span><fmt:message key="info.generic.warning.modnonsalvate" /></span>
			</div>	
			<div id="confermaEliminazione" title="Avvertenza" >
				<span><fmt:message key="info.generic.warning.eliminazione" /></span>
			</div>
			<div id="confermaEliminazioneRighe" title="Avvertenza" >
				<span><fmt:message key="info.generic.warning.eliminazioneRighe" /></span>
			</div>
			<div id="messaggioAttesa"  style="display: none;">  
			    <img alt="Loader" src="<c:url value="/img/ajax-loader3.gif"/>"/>
			     Attendere, prego...
			</div> 
			<stripes:layout-component name="customDialogs">
			
			</stripes:layout-component>						
				
		</div>
	</body>
</html>
<script type="text/javascript">
	//history.forward();
</script>
</stripes:layout-definition>