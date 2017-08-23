<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link type="text/css" href="//g.alicdn.com/sd/ncpc/nc.css?t=1503384835379" rel="stylesheet"/>
    <link href="${mimeBase}/styles/index.css?version=${version}" rel="stylesheet" type="text/css" />
</head>

<body>
    <%@ include file="../commons/head.jsp"%>
    <jsp:include page="search.jsp"></jsp:include>
    <div class="search-result mt-15">
        <div class="search-order-type">
            <ul>
                <li class="selected-menu"><div class="search-type">投标数</div></li>
                <li><div class="search-type">创建时间</div></li>
            </ul>
        </div>
        <div class="lihh-clear"></div>
        <div class="search-result-items"></div>
        <div class="pageDiv">
	        <ul class="page" id="page"></ul>
        </div>
    </div>

    <div id="result-tr" class="hide">
        <div class="search-result-tr">
            <div class="search-result-item">
                <div class="result-ico">
                    <div class="result-ico-content">
                            <span class="result-ico-img">
                                <img src="${mimeBase}/images/test.png">
                            </span>
                        <div class="lihh-clear"></div>
                        <span class="result-type">
                                %type%
                        </span>
                    </div>
                </div>
                <div class="result-item-content">
                    <table>
                        <tr class="result-item-head">
                            <td width="23%">
                                起投金额
                            </td>
                            <td width="23%">
                                投资周期
                            </td>
                            <td width="23%">
                                预计年华
                            </td>
                            <td rowspan="2" width="31%">
                                已有%investNum%人参与
                            </td>
                        </tr>
                        <tr class="result-item-body">
                            <td>
                                %minInvestAccount%
                            </td>
                            <td>
                                %date%
                            </td>
                            <td class="lhh-c-ori">
                                %a%%
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <input class="result-item-btn js-apply-btn" type="button" value="我要投资" />
                </div>
            </div>
        </div>
    </div>
    
    <div id="dialog-confirm" title="登录" class="hide">
    	<div id="_umfp" style="display:inline;width:1px;height:1px;overflow:hidden;display: none"></div>
    	<div class="moon-form">
    		<ul>
    			<li>
    				<div class="t-label">手机号码</div>
    				<div class="t-item"><input type="text" /><a h>注册</a></div>
    			</li>
    			<li>
    				<div class="t-label">登录密码</div>
    				<div class="t-item"><input type="password" /><a>忘记密码？</a></div>
    			</li>
    			<li>
    				<div class="t-label"> </div>
    				<div class="t-item">
	    				<div class="ln">
							<div id="dom_id"></div>
						</div>
				
						<input type='hidden' id='csessionid' name='csessionid'/>
						<input type='hidden' id='sig' name='sig'/>
						<input type='hidden' id='token' name='token'/>
						<input type='hidden' id='scene' name='scene'/>
					</div>
    			</li>
    		</ul>
    	</div>
	</div>
</body>

<script type="text/javascript" src="//g.alicdn.com/sd/ncpc/nc.js?t=1503384835379"></script>
<script src="${scripts}/index/index.js?version=${version}"></script>
</html>
