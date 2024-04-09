<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="/manydesigns-elements" prefix="md" %>
<jsp:useBean id="actionBean" scope="request" type="net.datasiel.webapp.crud.AbstractCrudAction"/>
<stripes:layout-render name="/pages/layoutDefinitions/layout.jsp">

	<stripes:layout-component name="html_head">
		<title><c:out value="${actionBean.creaRigaTitle}"/></title>
	</stripes:layout-component>

	<stripes:layout-component name="body">
		<md:write name="actionBean" property="elNewRiga"/>
	</stripes:layout-component>

	<stripes:layout-component name="menuAzioni">
		<input id="salva" class="btn btn-sacer " accesskey="S" type="submit" value="Salva" name="insertRiga"  title="Salva [Alt-S]"/>
		<br />
		<input id="annulla" class="btn checkChange" accesskey="A" type="submit" value="Annulla" name="cancel"  title="Annulla"/>
		<br />
	</stripes:layout-component>

</stripes:layout-render>