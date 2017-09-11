<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/notice.css" rel="stylesheet" type="text/css" />
    
    <link href="${mimeBase}/images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>公告</title>
</head>

<body>
    <%@ include file="../commons/head.jsp"%>
    <div class="page-index">首页 &gt; 公告</div>
    <div class="notice-list-div">
    	<table class="notice-list">
    		<thead>
    			<tr>
    				<th style="width:17%">发布日期</th>
    				<th style="width:83%">标题</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${noticeList}" var="item">
	    			<tr class="js-notice-tr" data="${item.id}">
	    				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
	    				<td align="left">${item.title}</td>
	    			</tr>
    			</c:forEach>
    		</tbody>
    	</table>
   		<div class="list-page-div">
			<span class="page-div page-menu">首页</span>
			<span class="page-div page-menu">前一页</span>
			<span class="page-div">22</span>
			<span class="page-div page-menu">后一页</span>
			<span class="page-div page-menu">末页</span>
		</div>
    </div>
    <%@ include file="../commons/foot.jsp"%>
</body>

<script src="${scripts}/notice/notice.js?version=${version}"></script>
</html>
