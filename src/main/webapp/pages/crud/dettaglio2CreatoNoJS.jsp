<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.crud.AbstractCrudAction"/>

<stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">

		<stripes:layout-component name="html_head">
			<title><c:out value="${actionBean.creaDettaglio1Title}"/></title>
		</stripes:layout-component>

		<stripes:layout-component name="body">
			<md:write name="actionBean" property="elNewRiga"/>
			<input type="hidden" name="indiceRiga" value="<c:out value="${actionBean.indiceRiga}"/>"/>  	
		</stripes:layout-component>

		<stripes:layout-component name="menuAzioni">
			<input id="salva" class="btn btn-sacer " accesskey="S" type="submit" value="Salva" name="insertDettaglio2"  title="Salva [Alt-S]"/>
			<br />
			
			<input id="annulla" class="btn checkChange" accesskey="A" type="submit" value="Annulla" name="cancel"  title="Annulla"/>
			<br />
		</stripes:layout-component>



</stripes:layout-render>