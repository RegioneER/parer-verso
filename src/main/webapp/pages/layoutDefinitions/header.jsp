<%@page import="it.eng.spagoCore.configuration.ConfigSingleton"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<%
// logo app
String logoAppAlt = ConfigSingleton.getInstance().getStringValue("LOGO_APP_ALT");
String logoAppRelative = ConfigSingleton.getInstance().getStringValue("LOGO_APP_RELATIVE");
String logoAppUrl = ConfigSingleton.getInstance().getStringValue("LOGO_APP_URL");
String logoAppTitle = ConfigSingleton.getInstance().getStringValue("LOGO_APP_TITLE");
// logo 1
String logo1Alt = ConfigSingleton.getInstance().getStringValue("LOGO_1_ALT");
String logo1Title = ConfigSingleton.getInstance().getStringValue("LOGO_1_TITLE");
String logo1Url = ConfigSingleton.getInstance().getStringValue("LOGO_1_URL");
String logo1Relative = ConfigSingleton.getInstance().getStringValue("LOGO_1_RELATIVE");
%>
<div class="row-fluid">
    <div class="span12, headerSacer"  >
        <img alt="<%=logoAppAlt%>" class="media-object pull-left" src="<c:url value="<%=logoAppRelative%>"/>"/>
        <a href="<%=logo1Url%>"
        title="<%=logo1Title%>">
            <img alt="<%=logo1Alt%>" class="media-object pull-right" src="<c:url value="<%=logo1Relative%>"/>"/>
        </a>
    </div>

    <div class="version">
        <%= "Versione " + ConfigSingleton.getInstance().getAppVersion() + " | " + ConfigSingleton.getInstance().getAppBuildDate() %>
    </div>

</div>
