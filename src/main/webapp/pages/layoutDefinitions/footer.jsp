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

<%@page import="it.eng.spagoCore.configuration.ConfigSingleton"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="net.datasiel.webapp.WebappProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
// logo 2
String logo2Url = ConfigSingleton.getInstance().getStringValue("LOGO_2_URL");
String logo2Title = ConfigSingleton.getInstance().getStringValue("LOGO_2_TITLE");
String logo2Alt = ConfigSingleton.getInstance().getStringValue("LOGO_2_ALT");
String logo2Relative = ConfigSingleton.getInstance().getStringValue("LOGO_2_RELATIVE");
// logo 3
String logo3Alt = ConfigSingleton.getInstance().getStringValue("LOGO_3_ALT");
String logo3Relative = ConfigSingleton.getInstance().getStringValue("LOGO_3_RELATIVE");
%>

<div class="row-fluid">
	<div class="span12" style="background: #FFFFFF;" >
	    <a href="<%=logo2Url%>" title="<%=logo2Title%>">
		    <img alt="<%=logo2Alt%>" class="media-object pull-left" src="<c:url value="<%=logo2Relative%>"/>" style="background: #FFFFFF;"/>
        </a>
           <img alt="<%=logo3Alt%>" class="media-object pull-right" src="<c:url value="<%=logo3Relative%>"/>"/>
	</div>
</div>
<div class="row-fluid">
	<div class="span12" style="background: #FFFFFF;" >
	</div>
</div>

<div id="buildInfo" style="display: none;">
	<%=request.getSession().getAttribute("buildInfo") %>
</div>
