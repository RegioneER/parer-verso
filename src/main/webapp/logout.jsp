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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<fmt:setBundle basename="MessageResources" scope="request" />



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
		
		<title>Verso: Logged out</title>
	</head>

	<body>
		<div id="wrap">
		<div class="container-fluid" >
			<jsp:include page="/pages/layoutDefinitions/header.jsp"></jsp:include>		
		<div class="row-fluid">
			<div class="offset3 span6">
				<div class="well well-large">
	
	                <ul>
	                    <li class="message  info  ">Logout eseguito correttamente. <a href="<c:url value="/"/>" title="Vai alla pagina di login ">Effettua un nuovo accesso</a></li>
	                </ul>
	
				</div>
			</div>
		</div>

		</div>
		<div id="push"></div>
		</div>
		<div id="footer">
			<jsp:include page="/pages/layoutDefinitions/footer.jsp" />
		</div>
		
	</body>
</html>
<script type="text/javascript">
	//history.forward();
</script>
