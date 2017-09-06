<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link type="text/css" href="//g.alicdn.com/sd/ncpc/nc.css?t=1503384835379" rel="stylesheet"/>
    <link href="${mimeBase}/styles/index.css?version=${version}" rel="stylesheet" type="text/css" />
    
    <link href="${mimeBase}/images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>安心返利</title>
</head>

<body>
    <%@ include file="../commons/head.jsp"%>
    <div class="index-content">
    	<div class="show-amount-div">
    		<div class="show-all-amount">
    			<div class="show-amount-label f-nocopy">总成交金额（元）</div>
    			<div class="show-amount-item">￥2,208,999,450</div>
    		</div>
    		<div class="show-all-amount all-amount-bottom">
    			<div class="show-amount-label f-nocopy">总返利金额（元）</div>
    			<div class="show-amount-item">￥2,208,999,450</div>
    		</div>
    	</div>
    	<div class="show-others">
    		<div class="notice-div">
    			<img class="mt-10" src="${mimeBase}/images/notice-logo.png">
    			<div class="js-notice-div">
	    			<ul>
	    				<c:forEach items="${noticeList }" var="item">
		    				<li class="js-notice" data="${item.id }">${item.title }</li>
	    				</c:forEach>
	    			</ul>
    			</div>
    		</div>
    		<div class="relate-div others-bottom">
    			<div class="rela-left">
					<img class="f-left" src="${mimeBase}/images/rela-logo1.gif">
					<div class="rela-item f-left">在线客服QQ<br/>292071141</div>
    			</div>
    			<div class="rela-right">
					<img class="f-left" src="${mimeBase}/images/rela-logo2.gif">
					<div class="rela-item f-left">官方Q群<br/>292071141</div>
    			</div>
    		</div>
    	</div>
    	<div class="f-clear"></div>
    	<div class="ad-div">
    		<div class="ad-item f-nocopy">
    			<img src="${mimeBase}/images/ad1.png">
    		</div>
    		<div class="ad-item f-nocopy">
    			<img src="${mimeBase}/images/ad2.png">
    		</div>
    		<div class="ad-item f-nocopy">
    			<img src="${mimeBase}/images/ad3.png">
    		</div>
    	</div>
    	<div class="platform-list-div">
    		<div class="platform-search-div">
    			<div class="search-label">平台名称</div>
    			<div class="search-item"><input type="text" placeholder="请输入要查找的平台名称" class="f-text js-platformName" /></div>
    			<img class="search-img js-platformSearch" src="${mimeBase}/images/search.png">
    			<img class="search-notice" src="${mimeBase}/images/search-notice.png">
    		</div>
    		<div class="search-split"></div>
    		<div class="platform-list">
				   		
    		</div>
    		<div class="show-more-div">
    			<input type="button" class="f-btn show-more-tip hide" value="显示更多"/>
    			<div class="no-more-tip hide">没有更多~</div>
    		</div>
    	</div>
    </div>
    
    <div id="platform-item-model" class="hide">
    	<div class="platform-item" data="%platformId%">
			<div class="platform-item-top">
				<div class="platform-name">%platformName%</div>
				<div class="platform-keyword-list f-nocopy">
					<div class="platform-keyword">网贷之家第一名</div>
					<div class="platform-keyword">纽交所IPO上市系</div>
					<div class="platform-keyword">兴业银行存管</div>
				</div>
				<div class="platform-type-list">
					<div class="platform-type-item f-nocopy">
						<div class="platform-type-left"></div>
						<div class="platform-type">推荐标</div>
						<div class="platform-type-right"></div>
					</div>
					<div class="platform-type-item f-nocopy">
						<div class="platform-type-left"></div>
						<div class="platform-type">明星标</div>
						<div class="platform-type-right"></div>
					</div>
					<div class="platform-type-item f-nocopy">
						<div class="platform-type-left"></div>
						<div class="platform-type">可复投</div>
						<div class="platform-type-right"></div>
					</div>
				</div>
			</div>
			<div class="platform-item-content">
				<img class="platform-logo" src="${base}/image/read/%icoUrl%">
				<div class="platform-desc-div">
					<div class="platform-desc">
						<div class="desc-1 f-text-right"><span class="desc-font">1</span>月标</div>
						<div class="desc-2 f-text-left">每投资 <span class="desc-font">20000</span></div>
						<div class="desc-3 f-text-left">返 <span class="desc-font">230</span></div>
					</div>
				</div>
				<div class="plaform-show-more-div">
					<input type="button" class="f-btn show-more-btn" value="查看攻略"/>
				</div>
			</div>
		</div> 
    </div>
    
    <%@ include file="../commons/foot.jsp"%>
</body>

<script type="text/javascript" src="//g.alicdn.com/sd/ncpc/nc.js?t=1503384835379"></script>
<script src="${vendorsBase}/jquery.liMarquee.js?version=${version}"></script>
<script src="${scripts}/index/index.js?version=${version}"></script>
</html>
