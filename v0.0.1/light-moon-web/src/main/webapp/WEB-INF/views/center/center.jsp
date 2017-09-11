<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.Date" %>
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
    			<div class="person-info-div form">
	    			<ul>
	    				<li>
	    					<div class="form-label">绑定手机</div>
	    					<div class="form-item f-label-org">18059833445</div>
	    				</li>
	    				<div class="f-clear"></div>
	    				<li>
	    					<div class="form-label">昵 称</div>
	    					<div class="form-item"><input type="text" class="f-text" name="newPwd" /></div>
	    				</li>
	    				<li>
	    					<div class="form-label">性 别</div>
	    					<div class="form-item">
	    						<input type="radio" value="0" name="sex" />男
	    						<input type="radio" value="1" name="sex" />女
	    					</div>
	    				</li>
	    				<li>
	    					<div class="form-label">生 日</div>
	    					<div class="form-item"><input id="birthday" type="text" class="f-text" name="birthday" /></div>
	    				</li>
	    				<li>
	    					<div class="form-label">所在地</div>
	    					<div class="form-item"><input type="text" class="f-text" name="confirmPwd" /></div>
	    				</li>
	    				<li>
	    					<div class="form-label">邮 箱</div>
	    					<div class="form-item"><input type="text" class="f-text" name="confirmPwd" /></div>
	    				</li>
	    				<div class="f-clear"></div>
	    				<li>
	    					<div class="form-label">备 注</div>
	    					<div class="form-item"><textarea class="f-textarea" rows="5" cols="60"></textarea></div>
	    				</li>
	    			</ul>
	    			<div class="f-clear"></div>
	    			<div class="submit-person-div">
						<input type="button" class="f-btn f-org-btn" value="确认修改"/>
					</div>
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
	    						<div class="valid-notice hide">验证码已发送至您的手机 <font style="color:red">180****3445</font>，请注意查收</div>
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
    			<div class="account-div">
	    			<div class="default-account-div">
	   					<span>默认收款账户 : </span>
	   					<span>
	   						<input type="radio" value="0" name="sex" checked="checked" />支付宝
  							<input type="radio" value="1" name="sex" />Q Q
	   					</span>
	   					<div class="f-clear"></div>
   					</div>
   					<div class="account-item">
   						<div class="account-item-top">支付宝</div>
   						<div class="account-item-content">
   							<div class="a-label">支付宝账号</div>
   							<div class="a-item"><input type="text" class="f-text" name="validNo" /></div>
   						</div>
   						<div class="f-clear"></div>
   					</div>
   					<div class="account-item">
   						<div class="account-item-top">Q Q</div>
   						<div class="account-item-content">
   							<div class="a-label">Q Q号</div>
   							<div class="a-item"><input type="text" class="f-text" name="validNo" /></div>
   						</div>
   						<div class="f-clear"></div>
   					</div>
   					<div class="account-item">
   						<div class="account-item-top">银行账户</div>
   						<div class="account-item-content">
   							<div class="a-label">收款人</div>
   							<div class="a-item"><input type="text" class="f-text" name="validNo" /></div>
   						</div>
   						<div class="f-clear"></div>
   						<div class="account-item-content">
   							<div class="a-label">银行账号</div>
   							<div class="a-item"><input type="text" class="f-text" name="validNo" /></div>
   						</div>
   						<div class="account-item-content">
   							<div class="a-label">开户行</div>
   							<div class="a-item"><input type="text" class="f-text" name="validNo" /></div>
   						</div>
   						<div class="f-clear"></div>
   					</div>
	    			<div class="f-clear"></div>
	    			<div class="submit-psw-div">
						<input type="button" class="f-btn f-org-btn" value="确认修改"/>
					</div>
    			</div>
    		</div>
    		<div class="apply-center-panel hide">
    			<div class="apply-center-top">
    				<div class="apply-center-menus">
    					<div class="apply-menu apply-menu-selecttd" data="apply-submit">
    						<div class="apply-menu-btn js-apply-selected">我要交单</div>
    					</div>
    					<div class="apply-menu" data="apply-manage">
    						<div class="apply-menu-btn">订单管理</div>
    					</div>
    				</div>
    			</div>
   				<div class="apply-center-content-div">
   					<div class="apply-submit">
   						<div class="submit-list">
							<div class="table-top-tip f-text-left f-left">
								当前日期 : <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" />
							</div>
							<div class="f-right">
								<input type="button" class="f-btn f-org-btn list-right-tip js-add-row" value="添加一行"/>
							</div>
							<table class="order-table">
								<thead>
									<tr>
										<th style="width:6%"> </th>
										<th style="width:12%">平台名称</th>
										<th style="width:15%">注册手机</th>
										<th style="width:15%">用户名</th>
										<th style="width:10%">投资金额</th>
										<th style="width:10%">标的期限</th>
										<th style="width:15%">投资时间</th>
										<th style="width:17%">备注(复投等信息)</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<div class="submit-order-div">
								<input type="button" class="f-btn f-org-btn submit-order-btn" value="提交"/>
							</div>
							<div class="batch-submit f-text-left">
								<div class="submit-model-download">
									模板下载 : <input type="button" class="f-btn file-btn js-model-download" value="下载交单模板"/>
								</div>
								<div class="submit-file-choose f-left">
									文件上传 : <input type="button" class="f-btn file-btn" id="js-file-upload" value="选择文件"/>
									<input id="fileupload" class="hide" type="file" name="file" data-url="${base}/file/uploadApplyBook" />
								</div>
								<div class="choose-file-tip f-left">
									<div class="no-file-tip">未选择文件</div>
									<div class="has-file-tip hide">您已选择文件：<br/><div class="selected-file f-label-org">c://test/sa/test.xlx</div></div>
								</div>
								<div class="f-clear"></div>
								<div class="submit-div">
									<input type="button" class="f-btn f-org-btn f-left" value="上传"/>
									<div class="submit-tip f-left">提交的文件请至 "订单管理" 页面查看</div>
								</div>
								<div class="f-clear"></div>
							</div>
   						</div>
   					</div>
   					<div class="apply-manage hide">
   						<div class="order-list">
   							<div class="table-top-tip f-text-left f-left">
								订单列表
							</div>
							<div class="f-right">
								<input type="button" class="f-btn f-org-btn list-right-tip" value="导出Excel表格"/>
							</div>
							<table class="order-table">
								<thead>
									<tr>
										<th style="width:6%">序号</th>
										<th style="width:12%">平台名称</th>
										<th style="width:15%">注册手机</th>
										<th style="width:15%">用户名</th>
										<th style="width:10%">投资金额</th>
										<th style="width:10%">标的期限</th>
										<th style="width:15%">投资时间</th>
										<th style="width:9%">状态</th>
										<th style="width:8%">备注</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>陆金所</td>
										<td>18059833445</td>
										<td>li2963298</td>
										<td>50000</td>
										<td>3月标</td>
										<td>2017-09-08</td>
										<td>已返现</td>
										<td>复投</td>
									</tr>
									<tr>
										<td>2</td>
										<td>陆金所</td>
										<td>18059833445</td>
										<td>li2963298</td>
										<td>50000</td>
										<td>3月标</td>
										<td>2017-09-08</td>
										<td>待返现</td>
										<td>复投</td>
									</tr>
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
    
    <div id="js-submit-order-add-model" class="hide">
    	<tr>
			<td><input type="checkbox" /></td>
			<td><input type="text" class="f-text" placeholder="平台名称" /></td>
			<td><input type="text" class="f-text" placeholder="注册手机" /></td>
			<td><input type="text" class="f-text" placeholder="用户名" /></td>
			<td><input type="text" class="f-text f-text-right" placeholder="投资金额" /></td>
			<td><input type="text" class="f-text f-text-right" placeholder="标的期限" /></td>
			<td><input type="text" class="f-text f-date" placeholder="投资时间" /></td>
			<td><input type="text" class="f-text" placeholder="备注" /></td>
		</tr>
    </div>
</body>

<script src="${vendorsBase}/fileupload/jquery.ui.widget.js?version=${version}"></script>
<script src="${vendorsBase}/fileupload/jquery.iframe-transport.js?version=${version}"></script>
<script src="${vendorsBase}/fileupload/jquery.fileupload.js?version=${version}"></script>
<script src="${scripts}/center/center.js?version=${version}"></script>
</html>
