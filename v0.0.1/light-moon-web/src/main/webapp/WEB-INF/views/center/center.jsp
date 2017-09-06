<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/center.css" rel="stylesheet" type="text/css" />
    
    <link href="${mimeBase}/images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>个人中心</title>
</head>

<body>
	<%@ include file="../commons/head.jsp"%>
    <div class="page-index">首页 &gt; 个人中心</div>
    <div class="center-div">
    	<input id="redirectPage" type="hidden" value="${page}" />
    	<div class="center-menu-div">
    		<div class="center-menu center-menu-selecttd" data="person-info-panel">我的资料</div>
    		<div class="center-menu" data="safe-set-panel">安全设置</div>
    		<div class="center-menu" data="account-panel">账户信息</div>
    		<div class="center-menu" id="apply-center-panel" data="apply-center-panel">订单管理</div>
    	</div>
    	<div class="center-right">
    		<div class="person-info-panel">
    			<div class="person-info-div">
    			
    			</div>
    		</div>
    		<div class="safe-set-panel form hide">
	    		<div class="safe-set-div">
	    			<ul>
	    				<li>
	    					<div class="form-label">新密码</div>
	    					<div class="form-item"><input type="text" class="f-text" name="newPwd" /></div>
	    				</li>
	    				<li>
	    					<div class="form-label">确认密码</div>
	    					<div class="form-item"><input type="text" class="f-text" name="confirmPwd" /></div>
	    				</li>
	    				<li>
	    					<div class="form-label">手机验证码</div>
	    					<div class="form-item">
	    						<input type="text" class="f-valid-text" name="validNo" />
	    						<input type="button" class="f-valid-btn" value="获取验证码" />
	    						<div class="valid-notice">验证码已发送至您的手机 <font style="color:red">180****3445</font>，请注意查收</div>
	    					</div>
	    				</li>
	    			</ul>
	    			<div class="f-clear"></div>
	    			<div class="submit-psw-div">
						<input type="button" class="f-btn f-org-btn" value="确认修改"/>
					</div>
    			</div>
    		</div>
    		<div class="account-panel hide">
    		</div>
    		<div class="apply-center-panel hide">
    			<div class="apply-center-top">
    				<div class="apply-center-menus">
    					<div class="apply-menu apply-menu-selecttd">
    						<div class="apply-menu-btn">我要交单</div>
    					</div>
    					<div class="apply-menu">
    						<div class="apply-menu-btn">全部订单</div>
    					</div>
    				</div>
    			</div>
   				<div class="apply-center-content-div">
   					<div class="submit-panel hide">
   						
   					</div>
   					<div class="apply-submit">
   						<div class="apply-list">
   							
   						</div>
   					</div>
   					<div class="apply-manage">
   						<div class="apply-list">
   							
   						</div>
   						<div class="apply-center-notice">
		    				交单注意事项<br/>
		    				1.24小时随时可以交单<br/>
		    				2.您可以直接填写交单信息，也可以下载交单模板进行批量交单(模板必须用我们的固定格式)<br/>
		    				3.每个工作日会结算投资时间为上一个工作日的单子，并在每天下午的16:00-24:00时间段进行打款<br/>
		    				<div style="color: #FF5522">
		    				4.未通过审核的回单有两种处理方式：<br/>
		    				&nbsp;&nbsp;&nbsp;&nbsp;①信息填写错误：您需要重新提交这笔单子；<br/>
		    				&nbsp;&nbsp;&nbsp;&nbsp;②信息填写正确：需要在该回单尾部上传含"用户信息、投资金额、投资项目"的截图(提交后自动生成新回单)
		    				</div>
		    			</div>
   					</div>
   				</div>
    		</div>
    	</div>
    </div>
    <div class="f-clear"></div>
    <%@ include file="../commons/foot.jsp"%>
</body>

<script src="${scripts}/center/center.js?version=${version}"></script>
</html>
