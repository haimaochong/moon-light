<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/noticeDetail.css" rel="stylesheet" type="text/css" />
    
    <link href="${mimeBase}/images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>公告</title>
</head>

<body>
    <%@ include file="../commons/head.jsp"%>
    <div class="page-index">首页 &gt; <span class="all-notice-tip">公告</span> &gt; ${notice.title}</div>
    <div class="notice-detail-div">
    	<div class="notice-title">${notice.title}</div>
    	<div class="notice-create-time">发布时间 : <fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
    	<div class="notice-content-div">
    		<pre>${notice.content}</pre>
    	</div>
    	<div class="other-notice f-left" <c:if test="${!empty preNotice}">title="${preNotice.title}"</c:if> data="${preNotice.id}">前一条：<c:if test="${!empty preNotice}">${preNotice.title }</c:if><c:if test="${empty preNotice}">没有了~</c:if></div>
    	<div class="other-notice f-right" <c:if test="${!empty nextNotice}">title="${nextNotice.title}"</c:if> data="${nextNotice.id}">后一条：<c:if test="${!empty nextNotice}">${nextNotice.title }</c:if><c:if test="${empty nextNotice}">没有了~</c:if></div>
    </div>
    <%@ include file="../commons/foot.jsp"%>
</body>

<script src="${scripts}/notice/noticeDetail.js?version=${version}"></script>
</html>
