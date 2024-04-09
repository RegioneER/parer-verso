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
			<div class="offset3 span6">
				<div class="well well-large">
					<div class="tab-pane active in" id="login">
						<form class="form-horizontal" action="<c:url value='j_spring_security_check' />" method="POST">
							<fieldset>
								<div id="legend">
									<legend class="">Login</legend>
								</div>    
								<div class="control-group">
									<!-- Username -->
									<label class="control-label"  for="username">Username</label>
									<div class="controls">
										<input type="text" id="username" name="username" placeholder="" class="input-xlarge">
									</div>
								</div>
								
								<div class="control-group">
									<!-- Password-->
									<label class="control-label" for="password">Password</label>
									<div class="controls">
										<input type="password" id="password" name="password" placeholder="" class="input-xlarge">
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
	</stripes:layout-component>

</stripes:layout-render>