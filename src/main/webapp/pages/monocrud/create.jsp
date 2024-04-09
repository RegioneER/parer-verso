<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.datasiel.webapp.InfoToolbar"%>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.monocrud.MonoCrudAction"/>

<stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">
	<stripes:layout-component name="html_head">
		<title><c:out value="${actionBean.titoloPagina}"/></title>
		<c:if test="${actionBean.ajaxEnabled eq true}">
		<script type="text/javascript" src="<c:url value="/js/monocrud/edit.js"/>"></script>
		</c:if>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/monocrud/edit.css"/>"/>		
	</stripes:layout-component>
	<stripes:layout-component name="menuAzioni">
		<input type="hidden" name="idStrut" value="<c:out value='${actionBean.idStrut}'/>"/>
	
		<c:if test="${not actionBean.readOnly}">			
			<button type="submit" name="save" accesskey="S" title="Salva [Alt-S]" class="btn btn-sacer menuAzioni">Salva</button>
			<br />
		</c:if>
		<br />
		<button type="submit" name="goHome" accesskey="H" class="checkChange btn btn-sacer"><span class="fontIcoMoon">&#x0021;</span> Home</button>
	</stripes:layout-component>
	<stripes:layout-component name="toolbarMenu">
		<c:if test="${not actionBean.readOnly}">			
			<button type="submit" name="save" accesskey="S" title="Salva [Alt-S]" class="btn btn-mini btn-sacer toolbarMenu"><span class="fontIcoMoon">R</span></button>
			<br />
		</c:if>
		<button type="submit" name="goHome" accesskey="H" class="checkChange btn btn-mini btn-sacer"><span class="fontIcoMoon">&#x0021;</span></button>
	</stripes:layout-component>		
	<stripes:layout-component name="body">
		<md:write name="actionBean" property="uiRO"/>
		<md:write name="actionBean" property="uiRW"/>
	</stripes:layout-component>
	<stripes:layout-component name="toolbar">
		<ul class="nav nav-pills">
		<c:forEach var="current" items="${actionBean.toolbarActions}">
			<% 
				Class myClass = (Class) pageContext.getAttribute("current");
				InfoToolbar infoToolbar = (InfoToolbar) myClass.getAnnotation(InfoToolbar.class);

				String cssClassi;
				boolean activeTab;
				if (myClass == actionBean.getClass()) {
					cssClassi = "submitPasso submitPassoSel checkChange";
					activeTab = true;
				} else {
					cssClassi = "submitPasso checkChange";
					activeTab = false;
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
				if (myClass == actionBean.getClass()) {
					cssClassi = "submitPasso submitPassoSel checkChange";
					activeTab = true;
					%>
					<li class="active">
					<stripes:link  accesskey="<%=accelerator%>" beanclass="<%=myClass.getName()%>" class="<%=cssClassi%>" title="${title}" >
						<stripes:param name="idrecord" value="${actionBean.idrecord}"></stripes:param>
						<stripes:param name="idStrut" value="${actionBean.idStrut}" ></stripes:param>
						<c:out value="${titolo}"/>
					</stripes:link>
					</li>
					<% 
				} else {
					cssClassi = "submitPassoDis";
					activeTab = false;
					%>
					<li class="disabled">
						<a href="#">
						 <c:out value="${titolo}"/>
						</a> 
					</li>
					<%
				}
			%>
		</c:forEach>
		</ul>
	</stripes:layout-component>
</stripes:layout-render>
