var head_services = {
		LOGOUT:"/user/logout",
		LOGIN:"/user/login",
		REGIST:"/user/regist",
		CHECK_LOGIN:"/user/checkLogin",
		SEND_VALID_CODE:"/message/sendValidCode"
};

(function ($) {
    var headPage = (function () {
    	var registDialog = null;
    	var loginDialog = null;
    	
        var init = function () {
            initEvent();
        }, initEvent = function () {
        	$(".logo-div,.js-indexPage").off("click").on("click", function() {
        		window.location=BASE_PATH+"index";
        	});
        	
        	$(".js-order-center").off("click").on("click", function() {
        		$.post(BASE_PATH + head_services.CHECK_LOGIN, {}, function (result) {
                    if (result && result.code == 0) {
                		window.location = BASE_PATH + "center?page=orderCenter";
                    } else {
                    	$(".js-login").click();
                    }
                });
        	});
        	
        	$(".login-user-name").off("click").on("click", function() {
        		window.open(BASE_PATH+"center");
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
                        		window.location.href = BASE_PATH + "center";
                        	} else {
                        		showWarning(d, data.msg);
                        	}
                        } else {
                        	showWarning("网络连接异常");
                        }
                    });
        		}
        	});
        	
        	$("body").on("click", ".form-logout-btn", function() {
    			$.post(BASE_PATH + head_services.LOGOUT, {}, function (data) {
                    if (data) {
                    	if(data.code == 0) {
                    		window.location.href = BASE_PATH + "index";
                    	} else {
                    		$.layout.alert("系统异常，退出失败！");
                    	}
                    } else {
                    	showWarning("网络连接异常");
                    }
                });
        	});
        	
        	$(".js-regist").off("click").on("click", function() {
        		registDialog = $.layout.dialog({
        			"height":"500px",
        			"width":"410px",
        			"localId":"#regist_div"
        		});
        	});
        	
        	$("body").off("click", ".js-regist-valid-btn").on("click", ".js-regist-valid-btn", sendValidCode);
        	$("body").off("click", ".form-regist-btn").on("click", ".form-regist-btn", function() {
        		var d = $(this).parents(".regist-content-div");
        		var params = validRegist(d);
        		if(params) {
        			$.post(BASE_PATH + head_services.REGIST, params, function (data) {
                        if (data) {
                        	if(data.code == 0) {
                        		window.location.href = BASE_PATH + "index";
                        	} else {
                        		showWarning(d, data.msg);
                        	}
                        } else {
                        	showWarning("网络连接异常");
                        }
                    });
        		}
        	});
        }, 
        sendValidCode = function() {
			$(".js-regist-valid-btn").attr("disabled", true);
			
			var phoneNumber = $(registDialog).find(".regist-content-div input[name=loginName]").val().trim();
			if(!phoneNumber) {
				showWarning("请输入手机号码");
				return;
			}
			
			$.post(BASE_PATH + head_services.SEND_VALID_CODE, {phoneNum:phoneNumber,type:"regist"}, function (result) {
                if (result) {
                	if(result.code == 0) {
                		var phone = PubUtils.encryPhone(phoneNumber);
                		$(".js-regist-phone").text(phone);
                		$(".valid-notice").show();
                		
                		var time = 10;
                		var i = setInterval(function() {
                			$(".js-regist-valid-btn").val("倒计时("+(time--)+")");
                			if(time < 0) {
                				$(".js-regist-valid-btn").attr("disabled", false);
                				$(".js-regist-valid-btn").val("获取验证码");
                				$(".valid-notice").hide();
                				clearInterval(i);
                			}
                		}, 1000);
                	} else {
                		showWarning(registDialog, result.msg);
                		$(".js-regist-valid-btn").attr("disabled", false);
        				$(".js-regist-valid-btn").val("获取验证码");
        				$(".valid-notice").hide();
                	}
                } else {
                	$(".js-regist-valid-btn").attr("disabled", false);
                	showWarning(registDialog, "网络连接超时！");
                }
            });
		},
		validLogin = function(dialog) {
    		var loginName = $(dialog).find("input[name=loginName]").val().trim();
        	var password = $(dialog).find("input[name=password]").val();

        	if(!loginName) {
        		showWarning(dialog, "请输入手机号码");
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
        	if(!/^[0-9a-zA-Z\~!@#$%^&*\_]{6,18}$/.test(password)) {
        		showWarning(dialog, "密码只能以字母、数字、下划线和特殊字符组合<br/>且长度为6~18位");
        	    return false;
        	}
        	
        	var params = {};
        	params["loginName"] = loginName;
        	params["password"] = $.md5(password);
        	return params;
    	}, 
    	validRegist = function(dialog) {
    		var loginName = $(dialog).find("input[name=loginName]").val().trim();
        	var password = $(dialog).find("input[name=password]").val();
        	var confirmPassword = $(dialog).find("input[name=confirmPassword]").val();
        	var validCode = $(dialog).find("input[name=validCode]").val().trim();
        	var agreeCheck = $(dialog).find("input[name=agreePlan]").is(":checked");
        	
        	if(!agreeCheck) {
        		showWarning(dialog, "请阅读并同意注册协议");
        		return false;
        	}
        	if(!loginName) {
        		showWarning(dialog, "请输入手机号码");
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
        	if(!/^[0-9a-zA-Z\~!@#$%^&*\_]{6,18}$/.test(password)) {
        		showWarning(dialog, "密码只能以字母、数字、下划线和特殊字符组合<br/>且长度为6~18位");
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
        	if(!validCode) {
        		showWarning(dialog, "请输入手机验证码");
        		return false;
        	}
        	
        	var params = {};
        	params["loginName"] = loginName;
        	params["password"] = $.md5(password);
        	params["validCode"] = validCode;
        	return params;
    	}, 
    	showWarning = function(dialog, msg) {
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
    
    headPage.init();
    
})(jQuery);