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
    		<input type="button" class="f-btn f-org-btn apply-btn apply-submit-btn js-submit-btn" value="提交投资回单" />
    		<input type="button" id="js-copy-btn" data-clipboard-target="content" class="f-btn f-org-btn apply-btn js-copy-btn" value="一键复制攻略" />
	   		<div class="apply-desc-div">以上为 [安心返利] 为您提供的攻略，大家为了更高效地做单可以复制加以改进</div>
    	</div>
    </div>
    <%@ include file="../commons/foot.jsp"%>
    
    <div id="submit_order_div" class="submit-order-div hide">
		<div class="submit-content-div form">
			<div class="submit-warning-div">手机号码格式不多</div>
			<ul>
				<li>
					<div class="form-label">平台名称:</div>
					<div class="form-item" style="font-weight: bold;">宜人贷</div>
				</li>
				<div class="f-clear"></div>
				<li>
					<div class="form-label">收款账户:</div>
					<div class="form-item account-item">
						<span class="account-code"></span>
						<span class="change-account js-change-account"></span>
					</div>
				</li>
				<div class="f-clear"></div>
				<li>
					<div class="form-label"><span class="f-label-org">* </span>注册手机:</div>
					<div class="form-item"><input type="text" placeholder="注册手机" class="f-text" name="password" /></div>
				</li>
				<li>
					<div class="form-label">用户名:</div>
					<div class="form-item"><input type="text" placeholder="用户名" class="f-text" name="confirmPassword" /></div>
				</li>
				<li>
					<div class="form-label"><span class="f-label-org">* </span>投资金额:</div>
					<div class="form-item"><input type="text" placeholder="投资金额" class="f-text" name="confirmPassword" /></div>
				</li>
				<li>
					<div class="form-label"><span class="f-label-org">* </span>标的期限:</div>
					<div class="form-item"><input type="text" placeholder="标的期限" class="f-text" name="confirmPassword" /></div>
				</li>
				<li>
					<div class="form-label"><span class="f-label-org">* </span>投资日期:</div>
					<div class="form-item"><input type="text" placeholder="投资日期" class="f-text" name="confirmPassword" /></div>
				</li>
				<div class="f-clear"></div>
				<li>
					<div class="form-label">备注:</div>
					<div class="form-item"><textarea class="f-textarea" rows="3" cols="50" name="note"></textarea></div>
				</li>
			</ul>
			<div class="f-clear"></div>
			<input type="button" class="f-btn f-org-btn form-regist-btn mt-45" value="提交订单" />
		</div>
	</div>
	
	<div id="change_account_div" class="submit-order-div hide">
		<div class="change-content-div form">
			<ul>
				<li>
					<div class="form-label">账户类型:</div>
					<div class="form-item">
						<span>
	   						<input type="radio" value="PAY_ZFB" name="payType" />支付宝
  							<input type="radio" value="PAY_QQ" name="payType" />Q Q
	   					</span>
	   				</div>
				</li>
				<li class="js-account-li js-zfb-li hide">
					<div class="form-label">姓 名:</div>
					<div class="form-item">
						<input type="text" placeholder="姓名" class="f-text" name="accountForZfbUser" />
					</div>
				</li>
				<li class="js-account-li js-zfb-li hide">
					<div class="form-label">支付宝账号:</div>
					<div class="form-item">
						<input type="text" placeholder="支付宝账号" class="f-text" name="accountForZfb" />
					</div>
				</li>
				<li class="js-account-li js-qq-li hide">
					<div class="form-label">Q Q号:</div>
					<div class="form-item">
						<input type="text" placeholder="QQ号" class="f-text" name="accountForQq" />
					</div>
				</li>
				<li>
					<div class="form-label">设置为默认账户:</div>
					<div class="form-item">
   						<input type="checkbox" checked="checked" />
	   				</div>
				</li>
			</ul>
			<div class="f-clear"></div>
			<input type="button" class="f-btn f-org-btn form-change-btn mb-40" value="确认更改" />
		</div>
	</div>
    
</body>

<script src="${mimeBase}/vendors/ZeroClipboard/ZeroClipboard.min.js?version=${version}"></script>
<script src="${scripts}/apply/apply.js?version=${version}"></script>
</html>
