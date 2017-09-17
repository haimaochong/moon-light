<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${mimeBase}/styles/globe.css?version=${version}" rel="stylesheet" type="text/css" />

<div class="lm-head-div">
	<div class="head-content">
		<div class="logo-div f-nocopy">
			<div class="logo-left f-left"></div>
			<img class="f-left" src="${mimeBase}/images/logo.png">
			<div class="logo-right f-left"></div>
			<div class="logo-font f-left ml-10">专注网贷推广返利的平台</div>
		</div>
		<div class="head-menu-div">
			<div class="head-menu js-indexPage">首页</div>
			<div class="head-menu-split"></div>
			<div class="head-menu js-order-center">交单中心</div>
		</div>
		<div class="head-btn-div">
			<c:if test="${!empty loginName}">
				<div class="login-user-tip">
					<span>您好，</span> <span class="login-user-name">${loginName}</span> - <span class="logout-btn form-logout-btn">安全退出</span>
				</div>
			</c:if>
			<c:if test="${empty loginName}">
				<div class="no-login-tip">
					<span>您好，</span> <span class="login-btn js-login">请登录</span> - <span class="logout-btn js-regist">免费注册</span>
				</div>
			</c:if>
		</div>
	</div>
</div>

<div id="regist_div" class="regist_div hide">
	<div class="regist-content-div form">
		<div class="warning-div hide"></div>
		<ul>
			<li>
				<div class="form-label">手机号码:</div>
				<div class="form-item"><input type="text" placeholder="请输入手机号码" class="f-text" name="login-name" /></div>
			</li>
			<li>
				<div class="form-label">登录密码:</div>
				<div class="form-item"><input type="password" placeholder="请输入6-18位密码" class="f-text" name="password" /></div>
			</li>
			<li>
				<div class="form-label">确认密码:</div>
				<div class="form-item"><input type="password" placeholder="再次输入密码" class="f-text" name="confirmPassword" /></div>
			</li>
			<li>
				<div class="form-label">手机验证码:</div>
				<div class="form-item">
					<input type="text" class="f-valid-text form-valid-text" name="validNo" />
					<input type="button" class="f-valid-btn form-valid-btn" value="获取验证码" />
					<div class="valid-notice hide">验证码已发送至您的手机 <font style="color:red">180****3445</font>，请注意查收</div>
				</div>
			</li>
		</ul>
		<div class="agree-check">
			<input type="checkbox" name="agreePlan" class="mt-20" />我已阅读并同意《安心返利用户注册协议》
		</div>
		<input type="button" class="f-btn f-org-btn form-regist-btn mt-45" value="免费注册" />
	</div>
</div>

<div id="login_div" class="login_div hide">
	<div class="login-content-div">
		<div class="login-logo-div">
			<img src="${mimeBase}/images/login-logo.png">
		</div>
		<div class="warning-div login-warning hide"></div>
		<div class="login-content-div">
			<input type="text" placeholder="请输入手机号码"  class="f-text form-login-name mt-50" name="loginName" />
			<input type="text" placeholder="请输入6-18位密码"  class="f-text form-login-pwd mt-20" name="password" />
			<input type="button" class="f-btn f-org-btn form-login-btn mt-50" value="登录" />
		</div>
	</div>
</div>

<script src="${vendorsBase}/jquery.md5.js?version=${version}"></script>
<script src="${scripts}/commons/head.js?version=${version}"></script>