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
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"
	%><%@ taglib uri="/manydesigns-elements" prefix="md"
	%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
	%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" 
	%>
<%-- 	<%@ taglib --%>
<%-- 	uri = "http://shiro.apache.org/tags" prefix="shiro" --%>
<%-- 	%> --%>
	<jsp:useBean id="actionBean" scope="request"
	type="net.datasiel.simpaweb.actionbeans.HomePrivata" />
		<ul class="media-list">
		    <c:forEach var="current"  items="${actionBean.udPaginaBozze}">
	    		<stripes:url var="urlModifica" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"></stripes:param>
	    		</stripes:url>
	    		<stripes:url var="urlElimina" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"></stripes:param>
			      <stripes:param name="${_csrf.parameterName}" value="${_csrf.token}"></stripes:param>
   			      <stripes:param name="elimina"/>
	    		</stripes:url>
	    		<stripes:url var="urlVerifica" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"/>
			      <stripes:param name="verifica"/>
			      <stripes:param name="daHome" value="1"/>
				</stripes:url>
	    		<stripes:url var="urlComunica" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"/>
			      <stripes:param name="verifica"/>
			      <stripes:param name="comunica" value="1"/>
				</stripes:url>
				<stripes:url var="urlVersa" beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" >
			      <stripes:param name="idrecord" value="${current.idunitadoc}"/>
			      <stripes:param name="${_csrf.parameterName}" value="${_csrf.token}"></stripes:param>
			      <stripes:param name="versa"/>
				</stripes:url>		
			  <li class="media rigaUd">
			    <stripes:link title="${current.oggetto}" href="${urlModifica}"  class="pull-left" >
			      <img alt="Immagine Modifica" class="media-object" src='<stripes:url value="/img/skin1/64x64/contents2.png" />'>
				</stripes:link>
			    <div class="media-body">
			 		<div class="pull-right text-right" ><small><strong>Creazione:  <fmt:formatDate type="date" value="${current.dtins}"/></strong></small><br/>
			 			<small><strong>Ultima modifica: <fmt:formatDate type="date" value="${current.dtagg}"/></strong></small> 
					    <div >
					    <c:if test="${current.stato == 0 || current.stato==-5}">
						    <stripes:link title="Esegui verifica ${current.oggetto}" href="${urlVerifica}"  class="btn btn-sacer btn-small" >
					    	Verifica
					    	</stripes:link>
						</c:if>
					    <c:if test="${current.stato == 1}">
						    <stripes:link title="Comunica verifica fallita" href="${urlComunica}"  class="btn btn-sacer btn-small" >
					    	Comunica Verifica Fallita
					    	</stripes:link>
						</c:if>
					    <c:if test="${current.stato < 3}">
						    <stripes:link title="Modifica ${current.oggetto}" href="${urlModifica}"  class="btn btn-sacer btn-small" >
							      Modifica
						    </stripes:link>
    					    <stripes:link title="Elimina ${current.oggetto}" href="${urlElimina}"  class="btn btn-sacer btn-small deleteUD" >
							      Elimina
						    </stripes:link>
					    </c:if>
					    <c:if test="${current.stato == 2}">
						    <stripes:link title="Versa ${current.oggetto}" href="${urlVersa}" class="btn btn-sacer btn-small" >
						      <stripes:param name="idrecord" value="${current.idunitadoc}"></stripes:param>
						      Versa
						    </stripes:link>
					    </c:if>
					    </div>

			 		</div>
			      <h4 class="media-heading"><c:out value="${current.cdRegistroUnitaDoc}/${current.anno}/${current.numero}"/></h4>
			      	 <div>
			      	 	<small><strong>${current.descTipoUnitaDoc}</strong></small>
			      	 </div>
			      	 <div>
			      	 	<c:choose>
			      	 		<c:when test="${current.stato == 0 }">
						      	 <span class="label">Bozza</span>
			      	 		</c:when>
			      	 		<c:when test="${current.stato == 1 }">
			      	 			<a href="#" id="${current.idunitadoc}" >
						      	 	<span class="label label-warning">Verifica fallita</span>
								</a>
								<script type="text/javascript">
									$(function ()	{ 
										var titolo = 'UD ' + '${current.numero}' + ' non versabile ';
										var options = {animation: true,placement: 'top', trigger: 'hover', title: titolo, content: '${current.note}'};
										var id = '#' + ${current.idunitadoc}; 
										$(id).popover(options);
									});  
								</script>
			      	 		</c:when>
			      	 		<c:when test="${current.stato == -2 }">
			      	 			<a href="#" id="${current.idunitadoc}" >
						      	 	<span class="label label-error">Versamento fallito</span>
								</a>
								<script type="text/javascript">
									$(function ()	{ 
										var titolo = 'UD ' + '${current.numero}' + ' non versabile ';
										var options = {animation: true,placement: 'top', trigger: 'hover', title: titolo, content: '${current.note}'};
										var id = '#' + ${current.idunitadoc}; 
										$(id).popover(options);
									});  
								</script>
			      	 		</c:when>
			      	 		<c:when test="${current.stato == 2 }">
						      	 <span class="label label-success">Verifica riuscita</span>
			      	 		</c:when>
			      	 		
			      	 		<c:when test="${current.stato == -5 }">
						      	 <span class="label label-warning">Verifica fallita Comunicata</span>
			      	 		</c:when>
			      	 		<c:when test="${current.stato == -6 }">
						      	 <span class="label label-warning">Dati Incompleti</span>
			      	 		</c:when>
			      	 		<c:when test="${current.stato == 3 }">
						      	 <span class="label label-warning">Versabile</span>
			      	 		</c:when>
			      	 	</c:choose>
			      	 </div>
			    </div>
			  </li>
	    </c:forEach>
	</ul>	
	
	
	<script type="text/javascript">
	
		var sbloccaPagina2 = function(){
			//Elimino l'elemento hidden aggiunta dalla funzione di gestione del blocco della UI altrimenti riesegue l'operazione che vi è stata impostata
			$.unblockUI();
			$('#daEliminareDopoUso').remove();
		};

		$('a.deleteUD').click(function(event){
			var title=this.title;
			var href = this.href;
			var paramTesto = title.substring(8);
			if (paramTesto != null && paramTesto != "") {
				paramTesto = " ''" + paramTesto + "''";
			}
			var testoMessaggio = templateMessaggioEliminaUD.replace('[param]', paramTesto);
			$("div#confermaEliminazioneUD span").text(testoMessaggio);
			
			$("div#confermaEliminazioneUD" ).dialog({
				zIndex: 3999,
				resizable: false,
				height:185,
				close: sbloccaPagina2,
				modal: true,
				buttons: {
					"Continua": function() {
						document.location = href;
					},
					"Annulla": function() {
						$( this ).dialog( "close" );
					}
				}
			});
			return false;	
		});
	</script>
