var head_services = {
		USER_LOGOUT:"/user/logout",
		LOGIN:"/user/login",
		REGIST:"/user/regist",
};

(function ($) {
    var headPage = (function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
        	$(".logo-div,.js-indexPage").off("click").on("click", function() {
        		window.location=BASE_PATH+"/index";
        	});
        	
        	$(".js-order-center").off("click").on("click", function() {
        		window.location = BASE_PATH+"/center?page=orderCenter";
        	});
        	
        	$(".login-user-name").off("click").on("click", function() {
        		window.open(BASE_PATH+"/center");
        	});
        	
        	$(".js-login").off("click").on("click", function() {
        		var d = $.layout.dialog({
        			"height":"470px",
        			"width":"410px",
        			"localId":"#login_div"
        		});
        	});
        	
        	$("body").on("click", ".form-login-btn", function() {
        		var d = $(this).parents(".login-content-div");
        		var params = validLogin(d);
        		if(params) {
        			$.post(BASE_PATH + head_services.LOGIN, params, function (data) {
                        if (data) {
                        	if(data.code == 0) {
                        		window.location.href = BASE_PATH + "/center";
                        	} else {
                        		showWarning(d, data.msg);
                        	}
                        } else {
                        	showWarning("网络连接异常");
                        }
                    });
        		}
        	});
        	
        	$(".js-regist").off("click").on("click", function() {
        		var d = $.layout.dialog({
        			"height":"500px",
        			"width":"410px",
        			"localId":"#regist_div"
        		});
        	});
        	
        	$("body").on("click", ".form-regist-btn", function() {
        		var d = $(this).parents(".regist-content-div");
        		var params = validRegist(d);
        		if(params) {
        			$.post(BASE_PATH + head_services.REGIST, params, function (data) {
                        if (data) {
                        	if(data.code == 0) {
                        		window.location.href = BASE_PATH + "/center";
                        	} else {
                        		showWarning(d, data.msg);
                        	}
                        } else {
                        	showWarning("网络连接异常");
                        }
                    });
        		}
        	});
        }, validLogin = function(dialog) {
    		var loginName = $(dialog).find("input[name=loginName]").val().trim();
        	var password = $(dialog).find("input[name=password]").val();

        	if(!loginName) {
        		showWarning(dialog, "请输入用户名");
        		return false;
        	}
        	if(!/^1[0-9]{10}$/.test(loginName)) {
        		showWarning(dialog, "请输入有效的手机号码");
        	    return false; 
        	} 
        	if(!password) {
        		showWarning(dialog, "请输入登录密码");
        		return false;
        	}
        	if(!/^[a-zA-Z][0-9a-zA-Z\_]{5,17}$/.test(password)) {
        		showWarning(dialog, "密码以字母开头，长度在6~18之间<br/>只能包含字符、数字和下划线");
        	    return false;
        	}
        	
        	var params = {};
        	params["loginName"] = loginName;
        	params["password"] = $.md5(password);
        	return params;
    	}, validRegist = function(dialog) {
    		var loginName = $(dialog).find("input[name=login-name]").val().trim();
        	var password = $(dialog).find("input[name=password]").val();
        	var confirmPassword = $(dialog).find("input[name=confirmPassword]").val();
        	var agreeCheck = $(dialog).find("input[name=agreePlan]").attr("checked");
        	
        	if(!agreeCheck) {
        		showWarning(dialog, "请阅读并同意注册协议");
        		return false;
        	}
        	if(!loginName) {
        		showWarning(dialog, "请输入用户名");
        		return false;
        	}
        	if(!/^1[0-9]{10}$/.test(loginName)) {
        		showWarning(dialog, "请输入有效的手机号码");
        	    return false; 
        	} 
        	if(!password) {
        		showWarning(dialog, "请输入登录密码");
        		return false;
        	}
        	if(!/^[a-zA-Z][0-9a-zA-Z\_]{5,17}$/.test(password)) {
        		showWarning(dialog, "密码以字母开头，长度在6~18之间<br/>只能包含字符、数字和下划线");
        	    return false;
        	}
        	if(!confirmPassword) {
        		showWarning(dialog, "请输入确认密码");
        		return false;
        	}
        	if(password != confirmPassword) {
        		showWarning(dialog, "两次密码不一致，请检查");
        		return false;
        	}
        	
        	var params = {};
        	params["loginName"] = loginName;
        	params["password"] = $.md5(password);
        	return params;
    	}, showWarning = function(dialog, msg) {
        	$(dialog).find(".warning-div").html(msg);
    		$(dialog).find(".warning-div").slideDown(500);
    		setTimeout(function() {
    			$(dialog).find(".warning-div").slideUp(500);
    		}, 3000);
        };

        return {
            "init": init
        };
    })();
    
    var loginDialog = (function() {
    	var show = function(allowSkip, direction) {
    		if(!direction) {
    			direction = BASE_PATH + "/index";
    		}
    		var buttons = {};
    		buttons["登录"] = function() {
    			var loginName = $("#login-dialog").find("input[name=login-name]").val().trim();
	        	var password = $("#login-dialog").find("input[name=password]").val().trim();
	        	if(!loginName) {
	        		showWarning("请输入用户名");
	        		return;
	        	}
	        	if(!password) {
	        		showWarning("请输入密码");
	        		return;
	        	}
	        	login(loginName, password, direction);
    		};
    		
    		if(allowSkip) {
    			buttons["跳过 >>"] = function() {
    				window.location.href = direction;
    			};
    		} else {
    			buttons["取消"] = function() {
    				$( this ).dialog( "close" );
    			};
    		}
    		
    		$("#login-dialog").dialog({
        		width: 450,
        		modal: true,
        		buttons: buttons
        	});
    		initNoCaptcha();
    	}, login = function(loginName, password, direction) {
        	$.post(BASE_PATH + head_services.LOGIN, {loginName: loginName, password: password}, function (data) {
                if (data) {
                	if(data.code == 0) {
                		window.location.href = direction;
                	} else {
                		showWarning(data.msg);
                	}
                } else {
                	showWarning("网络连接异常");
                }
            });
        }, showWarning = function( msg) {
        	$("#login-dialog").find(".warning-div").text(msg);
    		$("#login-dialog").find(".warning-div").slideDown(500);
    		setTimeout(function() {
    			$("#login-dialog").find(".warning-div").slideUp(500);
    		}, 2000);
        }, initNoCaptcha = function() {
        	var nc = new noCaptcha();
        	var nc_appkey = 'FFFF0000000001774593';  // 应用标识,不可更改
            var nc_scene = 'login';  //场景,不可更改
        	var nc_token = [nc_appkey, (new Date()).getTime(), Math.random()].join(':');
        	var nc_option = {
        		renderTo: '#dom_id',//渲染到该DOM ID指定的Div位置
        		appkey: nc_appkey,
                scene: nc_scene,
        		token: nc_token,
                trans: '{"name1":"code100"}',//测试用，特殊nc_appkey时才生效，正式上线时请务必要删除；code0:通过;code100:点击验证码;code200:图形验证码;code300:恶意请求拦截处理
        		callback: function (data) {
        			document.getElementById('csessionid').value = data.csessionid;
        			document.getElementById('sig').value = data.sig;
        			document.getElementById('token').value = nc_token;
                    document.getElementById('scene').value = nc_scene;
                    $('.ui-dialog-buttonpane').find('button:contains("登陆")').removeAttr("disabled");
        		}
        	};
        	nc.init(nc_option);
        };
        
        return {
        	"show" : show
        };
    })();
    window.loginDialog = loginDialog;
    
    headPage.init();
    
})(jQuery);