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

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" 
%><%@ taglib uri="/manydesigns-elements" prefix="md" 
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%><jsp:useBean id="actionBean" scope="request" type="net.datasiel.simpaweb.common.crud.SimpaAbstractCrudAction"
/><stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">
		<stripes:layout-component name="html_head">
			<title><c:out value="${actionBean.titoloPagina}"/></title>
			<script type="text/javascript" src="<c:url value="/js/crud/edit.js"/>"></script>
			<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/css/crud/edit.css"/>"/>		
		</stripes:layout-component>

		<stripes:layout-component name="menuAzioni">
			<input type="hidden" name="idrecord" value="<c:out value='${actionBean.idrecord}'/>"/>
			<input type="hidden" name="idStrut" value="<c:out value='${actionBean.idStrut}'/>"/>
			
			<c:if test="${not actionBean.readOnly}">			
				<button type="submit" name="update" accesskey="S" title="Salva [Alt-S]" class="btn btn-sacer menuAzioni">Salva</button>
				<br />
				<c:if test="${fn:length(actionBean.crudModel.righe ) < actionBean.maxRighe || actionBean.maxRighe == 0}">
					<button id = "createRiga" type="submit" name="createRiga" accesskey="N" title="Inserisci nuova riga [Alt-N]" class="checkChange btn btn-sacer menuAzioni">Inserisci nuova riga</button>
					<br />
				</c:if>
				<!-- 
				<c:if test="${fn:length(actionBean.crudModel.righe ) < actionBean.maxRighe || actionBean.maxRighe == 0}">
					<div id = "createTipiSpecifici"  class="checkChange btn btn-sacer menuAzioni">Inserisci Dati Specifici</div>
					<br />
				</c:if>
				-->
				<br />
				
				<!-- il bottone "verifica" deve comparire solo se l'unità documentaria è ancora in bozza (stato = 0)  -->				
				<c:if test="${actionBean.datiUnitaDoc.stato == 0}">
					<button type="submit" name="verifica" accesskey="V" title="Verifica [Alt-V]" class="btn btn-sacer menuAzioni">Verifica</button>
					<br />
				</c:if>
			    	
				<!-- il bottone "versa" deve comparire solo se l'unità documentaria ha passato la verifica (stato = 2)-->  
			    <c:if test="${actionBean.datiUnitaDoc.stato == 2}">
			    	<button type="submit" name="versa" accesskey="E" title="Versa [Alt-E]" class="btn btn-sacer menuAzioni">Versa</button>
			    	<br />
		    	</c:if> 
			</c:if>
			<button type="submit" name="goHome" accesskey="H" class="checkChange btn btn-sacer menuAzioni"><span class="fontIcoMoon">&#x0021;</span> Home</button>
			<br />
		</stripes:layout-component>
		<stripes:layout-component name="toolbarMenu">
			<c:if test="${not actionBean.readOnly}">			
				<button type="submit" name="update" accesskey="S" title="Salva [Alt-S]" class="btn btn-mini btn-sacer toolbarMenu"><span class="fontIcoMoon">R</span></button>
				<br />
				<c:if test="${fn:length(actionBean.crudModel.righe ) < actionBean.maxRighe || actionBean.maxRighe == 0}">
					<button id = "createRiga" type="submit" name="createRiga" accesskey="N" title="Inserisci nuova riga [Alt-N]" class="checkChange btn btn-sacer toolbarMenu"><span class="fontIcoMoon">&#xe11e;</span></button>
					<br />
				</c:if>
				
				<!-- il bottone "verifica" deve comparire solo se l'unità documentaria è ancora in bozza (stato = 0)  -->
				<c:if test="${actionBean.datiUnitaDoc.stato == 0}">
					<button type="submit" name="verifica" accesskey="V" title="Verifica [Alt-V]" class="btn btn-sacer toolbarMenu"><span class="fontIcoMoon">&#xe03c;</span></button>
					<br />
				</c:if>



				<!-- il bottone "versa" deve comparire solo se l'unità documentaria ha passato la verifica (stato = 2) -->  
			    <c:if test="${actionBean.datiUnitaDoc.stato == 2}">
			    	<button type="submit" name="versa" accesskey="E" title="Versa [Alt-E]" class="btn btn-sacer toolbarMenu"><span class="fontIcoMoon">&#xe03c;</span></button>
			    	<br />
		    	</c:if>
				
			</c:if>
		</stripes:layout-component>
				
		
	<stripes:layout-component name="body">
		<md:write name="actionBean" property="crudUi"/>
	</stripes:layout-component>
	
	
	<stripes:layout-component name="customDialogs">	
		<!-- div segnaPosto per creare modal dialog di inserimento ajax -->
		<div id="elNewRiga" title="<c:out value="${actionBean.creaRigaTitle}"/>">
		</div>
		<div id="elNewDettaglio1" title="<c:out value="${actionBean.creaDettaglio1Title}"/>">
		</div>
		<div id="elNewDettaglio2" title="<c:out value="${actionBean.creaDettaglio2Title}"/>">
		</div>
	</stripes:layout-component>
							
</stripes:layout-render>

<script type="text/javascript">

</script>
