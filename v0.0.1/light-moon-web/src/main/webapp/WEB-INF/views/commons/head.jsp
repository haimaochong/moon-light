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
			<div class="login-user-tip hide">
				<span>您好，</span> <span class="login-user-name">李海洪</span> - <span class="logout-btn">安全退出</span>
			</div>
			<div class="no-login-tip">
				<span>您好，</span> <span class="login-btn">请登录</span> - <span class="logout-btn">免费注册</span>
			</div>
		</div>
	</div>
</div>

<div id="regist_div" class="regist_div hide">
	<div class="regist-content-div form">
		<ul>
			<li>
				<div class="form-label">手机号码:</div>
				<div class="form-item"><input type="text" class="f-text" name="phone" /></div>
			</li>
			<li>
				<div class="form-label">登录密码:</div>
				<div class="form-item"><input type="text" class="f-text" name="phone" /></div>
			</li>
			<li>
				<div class="form-label">确认密码:</div>
				<div class="form-item"><input type="text" class="f-text" name="phone" /></div>
			</li>
			<li>
				<div class="form-label">手机验证码:</div>
				<div class="form-item"><input type="text" class="f-text" name="phone" /></div>
			</li>
			<li>
				<input type="checkbox" />
			</li>
		</ul>
	</div>
</div>

<script src="${scripts}/commons/head.js?version=${version}"></script>