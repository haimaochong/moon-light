<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/apply.css" rel="stylesheet" type="text/css" />
    
    <link href="${mimeBase}/images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>项目详情</title>
</head>

<body>
    <%@ include file="../commons/head.jsp"%>
    <div class="page-index">首页 &gt; 项目详情</div>
    <div class="apply-div">
    	<div class="apply-top-div">
    		<div class="apply-logo-div">
    			<img class="platform-logo" src="${base}/image/read/${platformInfo.icoUrl}">
    			<div class="platform-name">${platformInfo.name}</div>
    			<div class="f-clear">
    				<div class="platform-keyword">网贷之家第一名</div>
    				<div class="platform-keyword">纽交所IPO上市系</div>
    				<div class="platform-keyword">广发银行存管</div>
    			</div>
    		</div>
    		<img class="apply-desc" src="${mimeBase}/images/apply-desc.png">
    	</div>
    	<div class="f-clear"></div>
    	<div class="apply-content-div">
			<pre class="js-platform-desc">${platformInfo.desc}</pre>
    	</div>
    	<div class="f-clear"></div>
    	<div class="apply-operate-div">
    		<input type="button" class="f-btn f-org-btn apply-btn apply-submit-btn" value="提交投资回单" />
    		<input type="button" id="js-copy-btn" data-clipboard-target="content" class="f-btn f-org-btn apply-btn js-copy-btn" value="一键复制攻略" />
	   		<div class="apply-desc-div">以上为安心返利为您提供的攻略，大家为了更高效地做单可以复制加以改进</div>
    	</div>
    </div>
    <%@ include file="../commons/foot.jsp"%>
</body>

<script src="${mimeBase}/vendors/ZeroClipboard/ZeroClipboard.min.js?version=${version}"></script>
<script src="${scripts}/apply/apply.js?version=${version}"></script>
</html>
