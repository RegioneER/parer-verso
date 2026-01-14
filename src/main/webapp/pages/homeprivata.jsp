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

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="net.datasiel.simpaweb.actionbeans.HomePrivata,net.datasiel.simpaweb.actionbeans.GestionePassword"%>
<%@taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="actionBean" scope="request"
           type="net.datasiel.simpaweb.actionbeans.HomePrivata" />
<stripes:layout-render name="/pages/layoutDefinitions/layoutHome.jsp">
    <stripes:layout-component name="body">
        <div class="row-fluid" >
            <div class="span6" >
                <%--				  <div class='well'>
                                                                  <div class='alert alert-block<c:if test="${sessionScope.passwordVersatore ne null}"> alert-success</c:if>'>
                                                                  <p>
                                                                          <c:if test="${sessionScope.passwordVersatore eq null}"> Dati versatore imcompleti: password versatore non presente</c:if>
                                                                          <c:if test="${sessionScope.passwordVersatore ne null}"> Dati versatore completi</c:if>
                                                                  </p>
                                                                  </div>
                                                          </div>--%>
            </div>
            <c:if test="${actionBean.messaggiPresenti}">
                <div class="span6" >
                    <md:sessionMessages/>
                </div>
            </c:if>
        </div> 
        <div class="row-fluid" >
            <div class="span6" >
                <div class="well well-large">
                    <h3>
                        Informazioni sui versamenti fatti
                    </h3>

                    <div id="listaVersamenti">
                        <jsp:include page="/pro/strut/${actionBean.idStrut}?listaVersamenti="/>
                    </div>
                    <% 
                                                                    int numeroPagineVers = actionBean.getUdTrovateVersamenti().size() / HomePrivata.ITEM_PER_PAGINA;
                                                                    if (numeroPagineVers > 0 && actionBean.getUdTrovateVersamenti().size() > HomePrivata.ITEM_PER_PAGINA) {	
                    %>
                    <div class="pagination pagination-centered homeprivata">
                        <ul id = "paginatoreVersamenti">
                            <% 
                                if (actionBean.getUdTrovateVersamenti().size() % HomePrivata.ITEM_PER_PAGINA > 0) {
                                        numeroPagineVers++;
                                }

                                for(int i = 0; i < numeroPagineVers; i++) {
                                        String cssClass = "";
                                        if (i == actionBean.getPaginaVersamenti() ){
                                                cssClass = "active";
                                }
                            %>
                            <li id="pagina<%= i %>" class="<%=cssClass%>">
                                <a href="#" onclick="javascript:formSubmit(<%= i %>);" >
                                    <%= i+1 %>
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
            </div>
            <div class="span6" >
                <div class="well well-large">
                    <h3>Attività</h3>
                    <p>Nuovo versamento...</p>
                    <p>
                    <stripes:link title="Nuova unità documentaria"  beanclass="net.datasiel.simpaweb.actionbeans.crudud.UnitaDocumentaria" class="btn btn-sacer btn-medium" >
                        <stripes:param name="idStrut" value="${actionBean.idStrut}"></stripes:param>
                        <stripes:param name="idrecord" value="0"></stripes:param>
                        Nuovo
                    </stripes:link>

                    </p>
                    <div id="listaBozze">					    
                        <jsp:include page="/pro/strut/${actionBean.idStrut}?listaBozze="/>
                    </div>
                    <% 
                            int numeroPagine = actionBean.getUdTrovateBozze().size() / HomePrivata.ITEM_PER_PAGINA ;
                            if (numeroPagine > 0 && actionBean.getUdTrovateBozze().size() > HomePrivata.ITEM_PER_PAGINA) {	
                    %>

                    <div class="pagination pagination-centered homeprivata">
                        <ul id = "paginatoreBozze">
                            <% 
                                    if (actionBean.getUdTrovateBozze().size() % HomePrivata.ITEM_PER_PAGINA > 0) {
                                            numeroPagine++;
                                    }

                                    for(int i=0;i<numeroPagine;i++) {
                                            String cssClass = "";
                                            if (i == actionBean.getPaginaBozze() ){
                                                    cssClass = "active";
                                            }
						   	
                            %>
                            <li id="pagina<%=i%>" class="<%=cssClass%>">
                            <stripes:link beanclass="net.datasiel.simpaweb.actionbeans.HomePrivata" >
                                <stripes:param name="idStrut" value="${actionBean.idStrut}"/>
                                <stripes:param name="paginaBozze" value="<%=i%>"/>
                                <%=i+1%>
                            </stripes:link>
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
            </div>
        </div>	
        <script type="text/javascript">

            function formSubmit(paginaVersamenti) {
                document.getElementById("searchFormVersamenti").cambiaPagina = true;
                document.getElementById("searchFormVersamenti").ricercaEseguita.value = true;
                document.getElementById("searchFormVersamenti").paginaVersamenti.value = paginaVersamenti;
                document.getElementById("searchFormVersamenti").submit();
            }

            // lista bozze
            $('ul#paginatoreBozze li').click(function () {
                var href = $(this).find('a').attr('href');
                var elementoCliccato = $(this);
                $('#listaBozze').load(href + '&listaBozze=', function () {
                    $('ul#paginatoreBozze li').removeClass('active');
                    elementoCliccato.addClass('active');
                });
                return false;
            });

            // lista versamenti
            $('ul#paginatoreVersamenti li').click(function () {
                var href = $(this).find('a').attr('href');
                var elementoCliccato = $(this);
                $('#listaVersamenti').load(href + '&listaVersamenti=', function () {
                    $('ul#paginatoreVersamenti li').removeClass('active');
                    elementoCliccato.addClass('active');
                });
                return false;
            });
            // ogni link con parametro comunica=1 viene nascosto dopo il click.
            $(document).ready(function () {
                $('a[href*="comunica=1"]').click(function () {
                    $(this).hide();
                });
                // Se l'utente loggato è stato ricondotto all'utenza PARER mostra un alert di notifica
                <c:if test="${not empty actionBean.msgUtenteRicondotto}">
                    alert("" + "<c:out value="${actionBean.msgUtenteRicondotto}" escapeXml="false" />");
                </c:if>
            });
        </script>	
    </stripes:layout-component>

</stripes:layout-render>
