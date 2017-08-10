<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--版本变量 --%>
<c:set var="version" value="v0.0.1"></c:set>
<%--定义资源路径变量 --%>
<c:set var="mimeBase" value="/assets" />
<c:set var="scripts" value="/assets/scripts" />
<c:set var="vendorsBase" value="/assets/vendors" />

<%
    String path = request.getContextPath();
%>
<script language="javascript">
    var BASE_PATH = "<%=path%>";
</script>