<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="base"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<%--版本变量 --%>
<c:set var="version" value="v0.0.1"></c:set>
<%--定义资源路径变量 --%>
<c:set var="mimeBase" value="${base }/assets" />
<c:set var="scripts" value="${base }/assets/scripts" />
<c:set var="vendorsBase" value="${base }/assets/vendors" />

<%
    String path = request.getContextPath();
%>
<script language="javascript">
    var BASE_PATH = "<%=path%>";
</script>
