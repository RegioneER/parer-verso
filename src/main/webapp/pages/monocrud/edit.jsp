<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.monocrud.MonoCrudAction"/>
<stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">
	<stripes:layout-component name="html_head">
		<title><c:out value="${actionBean.titoloPagina}"/></title>
		<script type="text/javascript" src="<c:url value="/js/monocrud/edit.js"/>"></script>
		<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/monocrud/edit.css"/>"/>		
	</stripes:layout-component>
	<stripes:layout-component name="menuAzioni">
		<input type="hidden" name="idrecord" value="<c:out value='${actionBean.idrecord}'/>"/>
		<input type="hidden" name="idStrut" value="<c:out value='${actionBean.idStrut}'/>"/>
		<c:if test="${not actionBean.readOnly}">			
			<button type="submit" name="update" accesskey="S" title="Salva [Alt-S]" class="btn btn-sacer menuAzioni">Salva</button>
			<br />
			
			<!-- il bottone "verifica" deve comparire solo se l'unità documentaria è ancora in bozza (stato = 0)  -->
			<c:if test="${actionBean.datiUnitaDoc.stato == 0}">
				<button type="submit" name="verifica" accesskey="V" title="Verifica [Alt-V]" class="btn btn-sacer menuAzioni">Verifica</button>
				<br />
			</c:if>
			<!-- il bottone "versa" deve comparire solo se l'unità documentaria ha passato la verifica (stato = 2)  -->
		    <c:if test="${actionBean.datiUnitaDoc.stato == 2}">
		    	<button type="submit" name="versa" accesskey="E" title="Versa [Alt-E]" class="btn btn-sacer menuAzioni">Versa</button>
		    	<br />
		    </c:if>
		    
		</c:if>
		<button type="submit" name="goHome" accesskey="H" class="checkChange btn btn-sacer menuAzioni"><span class="fontIcoMoon">&#x0021;</span> Home</button>
	</stripes:layout-component>
	<stripes:layout-component name="toolbarMenu">
		<c:if test="${not actionBean.readOnly}">			
			<button type="submit" name="update" accesskey="S" title="Salva [Alt-S]" class="btn btn-mini btn-sacer toolbarMenu"><span class="fontIcoMoon">R</span></button>
			<br />
			<button type="submit" name="updateAndContinue" accesskey="C" title="Salva e continua [Alt-C]" class="btn btn-mini btn-sacer toolbarMenu"><span class="fontIcoMoon">RV</span></button>
			<br />
			<h1></h1>
			<!-- il bottone "verifica" deve comparire solo se l'unità documentaria è ancora in bozza (stato = 0)  -->
			<c:if test="${actionBean.datiUnitaDoc.stato == 0}">
				<button type="submit" name="verifica" accesskey="V" title="Verifica [Alt-V]" class="btn btn-sacer toolbarMenu"><span class="fontIcoMoon">&#xe03c;</span></button>
				<br />
			</c:if>
			
			<!-- il bottone "versa" deve comparire solo se l'unità documentaria ha passato la verifica (stato = 2)  -->
			<c:if test="${actionBean.datiUnitaDoc.stato == 2}">
			  	<button type="submit" name="versa" accesskey="E" title="Versa [Alt-E]" class="btn btn-sacer toolbarMenu"><span class="fontIcoMoon">&#xe03c;</span></button>
			   	<br />
		    </c:if>
		    
		</c:if>
		<button type="submit" name="goHome" accesskey="H" title="Torna alla pagina iniziale" class="checkChange btn btn-mini btn-sacer toolbarMenu"><span class="fontIcoMoon">&#x0021;</span></button>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<md:write name="actionBean" property="uiRO"/>
		<md:write name="actionBean" property="uiRW"/>
		
		<input name='createDatiSpecifici' id='datiSpecificiButton' type="button" class="btn btn-sacer dettaglioDatiSpecifici" value="Dati Specifici">
		<div id="elNewDettaglio2" title="<c:out value="${actionBean.creaDettaglio2Title}"/>">
		</div>		
	</stripes:layout-component>
</stripes:layout-render>