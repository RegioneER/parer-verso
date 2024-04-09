<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%-- 	<%@ taglib --%>
<%-- 	uri = "http://shiro.apache.org/tags" prefix="shiro"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="_csrf" content="${_csrf.token}"/>
		<!-- default header name is X-CSRF-TOKEN -->
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/custom-theme/jquery-ui-1.13.1.min.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/jquery.jgrowl.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/bootstrap-tooltip.min.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/bootstrap-popover.min.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/icomoon.css" />"/>
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/font-awesome.min.css"/>" />
<!--[if IE 7]>
		<link rel="stylesheet" href="<c:url value="/css/font-awesome-ie7.min.css"/>">
		<![endif]-->
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/aca/forms.css"/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value="/css/layoutHome.css"/>" />


<script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/elementsStripes.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.countdown.pack-it.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/collapsiblefieldset.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-tooltip.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-popover.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jgrowl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.dirtyform.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.cookie.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/webjars/jquery-ui/1.13.2/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/layoutscripts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/menuscripts.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/layoutDefinitions/layout.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/help/globalsetup.js"/>"></script>



<!-- SCELTA ENTE/STRUTTURA CON JAVASCRIPT 1/2-->
<script type="text/javascript">
	$(document).ready(function() {
		$('#jsAbilitato').removeAttr("style");
	});
</script>

<title>Verso: Login</title>
</head>

<body>
	<div id="wrap">
		<div class="container-fluid">




			<div class="row-fluid">
				<div class="span12, headerSacer">
					<img alt="Logo Applicazione" class="media-object pull-left"
						src="<c:url value="/img/Logo-SACER-VERSO_Beta.png"/>" /> .
						<a href="https://poloarchivistico.regione.emilia-romagna.it"
                                title="ParER - Polo Archivistico Regionale dell'Emilia-Romagna">
                            <img alt="Logo Parer" class="media-object pull-right" src="<c:url value="/img/LogoParer.png"/>" />
                        </a>
				</div>
			</div>

			<div class="row-fluid">
				<div class="offset3 span6">
					<div class="well well-large">
						<div class="tab-pane active in" id="login">
							<form class="form-horizontal" action="<c:url value="/login.do"/>"
								method="post">

								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
									<div class="alert alert-danger">
										<strong>Errore </strong>. Il tentativo di login ha restituito
										il seguente messaggio : <br />
										<br />
										<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
										.
									</div>

								</c:if>

								<fieldset>
									<div id="legend">
										<legend class="">Login</legend>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="username">Utente</label>
										<div class="controls">
											<input type="text" id="username" name="j_username"
												placeholder="" class="input-xlarge">
										</div>
									</div>

									<div class="control-group">
										<!-- Password-->
										<label class="control-label" for="password">Password</label>
										<div class="controls">
											<input type="password" id="password" name="j_password"
												placeholder="" class="input-xlarge">
										</div>
									</div>


									<div class="control-group">
										<!-- Button -->
										<div class="controls">
											<button class="btn btn-success">Accedi</button>
										</div>
									</div>
								</fieldset>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div id="push"></div>
	</div>
	<div id="footer">






		<div class="row-fluid">
			<div class="span12" style="background: #FFFFFF;">
			    <a href="http://www.regione.emilia-romagna.it" title="Regione Emilia-Romagna">
                    <img alt="Logo Regione Emilia Romagna" class="media-object pull-left" src="<c:url value="/img/LogoER.png"/>"
                        style="background: #FFFFFF;" /> <img alt="Logo IBC"
                        class="media-object pull-right" src="<c:url value="/img/LogoIbc.png"/>" />
                </a>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12" style="background: #FFFFFF;"></div>
		</div>



	</div>


	</div>

</body>
</html>