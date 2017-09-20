var services = {
		QUERY_ORDER_LIST:"center/queryOrderList",
		QUERY_USER_INFO:"center/queryUserInfo",
		SAVE_NORMAL_INFO:"center/saveNormalInfo",
		SAVE_ACCOUNT_INFO:"center/saveAccountInfo",
		UPDATE_PASSWORD:"center/updatePassword"
};

(function ($) {
	
	var _userInfo = null;
	
	//===========================================personInfoPanel======================================
	
	var personInfoPanel = (function() {
		var init = function() {
			initComp();
			initEvent();
			initData();
		},
		initComp = function() {
			$("#birthday").datepicker({
                dateFormat: 'yy-mm-dd',
                changeYear: true,
                monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
                dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"]
        	});
		},
		initEvent = function() {
			$("body").off("click", ".js-updateUserInfo-btn").on("click", ".js-updateUserInfo-btn", function() {
				$(this).attr("disabled", true);
				var params = getParams();
				if(!validParams(params)) {
					return;
				}
				
				$.post(BASE_PATH + services.SAVE_NORMAL_INFO, params, function (result) {
					$(".js-resetPwd-btn").attr("disabled", false);
					
	                if (result) {
	                	if(result.code == 0) {
	                		$.layout.alert("修改成功");
	                	} else {
	                		$.layout.alert(result.msg);
	                	}
	                } else {
	                	$.layout.alert("网络连接超时！");
	                }
	            });
			});
		},
		getParams = function() {
			var params = {};
			params["userName"] = $(".person-info-div input[name=userName]").val().trim();
			params["sex"] = $(".person-info-div input[name=sex]:checked").val();
			params["birthday"] = $(".person-info-div input[name=birthday]").val();
			params["email"] = $(".person-info-div input[name=email]").val();
			params["qq"] = $(".person-info-div input[name=qq]").val();
			params["weixin"] = $(".person-info-div input[name=weixin]").val();
			params["note"] = $(".person-info-div textarea[name=note]").val();
			return params;
		},
		validParams = function(params) {
			if(params["userName"] && params["userName"].length > 16) {
				$.layout.alert("昵称最大长度为16位");
				return false;
			}
			return true;
		},
		initData = function() {
    		$(".js-tel").text(_userInfo.loginName);
    		$("input[name=userName]").val(_userInfo.userName);
    		$("input[name=birthday]").val(_userInfo.birthday);
    		$("input[name=sex][value="+_userInfo.sex+"]").attr("checked", true);
    		$("input[name=email]").val(_userInfo.email);
    		$("input[name=qq]").val(_userInfo.qq);
    		$("input[name=weixin]").val(_userInfo.weixin);
    		$("textarea[name=note]").text(_userInfo.note);
		};
		
		return {
            "init": init
        };
	})();
	
	
	//===========================================safeSetPanel======================================
	
	var safeSetPanel = (function() {
		var init = function() {
			initEvent();
		},
		initEvent = function() {
			$("body").off("click", ".f-valid-btn").on("click", ".f-valid-btn", sendValidCode);
			
			$("body").off("click", ".js-resetPwd-btn").on("click", ".js-resetPwd-btn", function() {
				$(".js-resetPwd-btn").attr("disabled", true);
				var newPwd = $(".safe-set-div input[name=newPwd]").val().trim();
            	var confirmPwd = $(".safe-set-div input[name=confirmPwd]").val().trim();
            	var validNo = $(".safe-set-div input[name=validNo]").val().trim();
            	
            	if(!newPwd) {
            		$.layout.alert("请输入新密码");
            		return;
            	}
            	
            	if(!/^[0-9a-zA-Z\~!@#$%^&*\_]{6,18}$/.test(newPwd)) {
            		showWarning(dialog, "密码只能以字母、数字、下划线和特殊字符组合<br/>且长度为6~18位");
            	    return false;
            	}
            	
            	if(newPwd != confirmPwd) {
            		$.layout.alert("两次密码不一致");
            		return;
            	}
            	
            	if(!validNo) {
            		$.layout.alert("请输入验证码");
            		return;
            	}
            	
				$.post(BASE_PATH + services.UPDATE_PASSWORD, {newPwd:$.md5(newPwd), validCode:validNo}, function (result) {
	                if (result) {
	                	if(result.code == 0) {
	                		$.layout.alert("密码修改成功，3秒后自动退出登录，请使用新密码登录！");
	                		setTimeout(function() {
	                			$("..form-logout-btn").click();
	                		}, 3000);
	                	} else {
	                		$(".js-resetPwd-btn").attr("disabled", false);
	                		$.layout.alert(result.msg);
	                	}
	                } else {
	                	$(".js-resetPwd-btn").attr("disabled", false);
	                	$.layout.alert("网络连接超时！");
	                }
	            });
			});
		},
		sendValidCode = function() {
			$(".f-valid-btn").attr("disabled", true);
			
			$.post(BASE_PATH + head_services.SEND_VALID_CODE, {phoneNum:_userInfo.loginName,type:"updatePwd"}, function (result) {
                if (result) {
                	if(result.code == 0) {
                		var phone = PubUtils.encryPhone(_userInfo.loginName);
                		$(".js-phone").text(phone);
                		$(".valid-notice").show();
                		
                		var time = 10;
                		var i = setInterval(function() {
                			$(".f-valid-btn").val("倒计时("+(time--)+")");
                			if(time < 0) {
                				$(".f-valid-btn").attr("disabled", false);
                				$(".f-valid-btn").val("获取验证码");
                				$(".valid-notice").hide();
                				clearInterval(i);
                			}
                		}, 1000);
                	} else {
                		$.layout.alert("验证码发送失败！，请重试");
                		$(".f-valid-btn").attr("disabled", false);
        				$(".f-valid-btn").val("获取验证码");
        				$(".valid-notice").hide();
                	}
                } else {
                	$(".f-valid-btn").attr("disabled", false);
                	$.layout.alert("网络连接超时！");
                }
            });
		};
		
		return {
            "init": init
        };
	})();
	
	
	//===========================================accountPanel======================================
	
	var accountPanel = (function() {
		var init = function() {
			initEvent();
			initData();
		},
		initEvent = function() {
			$("body").off("click", ".js-updateAccount-btn").on("click", ".js-updateAccount-btn", function() {
				$(this).attr("disabled", true);
				var params = getParams();
				if(!validParams(params)) {
					return;
				}
				
				$.post(BASE_PATH + services.SAVE_ACCOUNT_INFO, params, function (result) {
					$(".js-updateAccount-btn").attr("disabled", false);
					
	                if (result) {
	                	if(result.code == 0) {
	                		$.layout.alert("修改成功");
	                	} else {
	                		$.layout.alert(result.msg);
	                	}
	                } else {
	                	$.layout.alert("网络连接超时！");
	                }
	            });
			});
		},
		getParams = function() {
			var params = {};
			params["payType"] = $(".account-div input[name=payType]:checked").val();
			params["accountForZfbUser"] = $(".account-div input[name=accountForZfbUser]").val().trim();
			params["accountForZfb"] = $(".account-div input[name=accountForZfb]").val();
			params["accountForQq"] = $(".account-div input[name=accountForQq]").val();
			params["bankAccount"] = $(".account-div input[name=bankAccount]").val();
			params["bankAccountCode"] = $(".account-div input[name=bankAccountCode]").val();
			params["openBank"] = $(".account-div input[name=openBank]").val();
			return params;
		},
		validParams = function(params) {
			if(params["payType"] == "PAY_ZFB") {
				if(!params["accountForZfbUser"]) {
					$.layout.alert("支付宝收款人姓名不能为空");
					return false;
				}
				if(!params["accountForZfb"]) {
					$.layout.alert("支付宝账号不能为空");
					return false;
				}
			} else if(params["payType"] == "PAY_QQ") {
				if(!params["accountForQq"]) {
					$.layout.alert("收款QQ账号不能为空");
					return false;
				}
			} else {
				$.layout.alert("默认收款方式错误，请重新选择");
				return false;
			}
			
			return true;
		},
		initData = function() {
    		$(".account-panel").find("input[name=payType][value="+_userInfo.payType.name+"]").attr("checked", true);
    		$(".account-panel").find("input[name=accountForZfbUser]").val(_userInfo.accountForZfbUser);
    		$(".account-panel").find("input[name=accountForZfb]").val(_userInfo.accountForZfb);
    		$(".account-panel").find("input[name=accountForQq]").val(_userInfo.accountForQq);
    		$(".account-panel").find("input[name=bankAccount]").val(_userInfo.bankAccount);
    		$(".account-panel").find("input[name=bankAccountCode]").val(_userInfo.bankAccountCode);
    		$(".account-panel").find("input[name=openBank]").val(_userInfo.openBank);
		};
		
		return {
            "init": init
        };
	})();
	
	
	//===========================================applyCenterPanel======================================
	
	var applyCenterPanel = (function() {
		var init = function() {
			initComp();
			initEvent();
			initData();
			
			$(".apply-menu[data=apply-submit]").click();
		},
		initComp = function() {
			$('#fileupload').fileupload({
                dataType: 'json',
                add: function (e, data) {
                	var file = data.files[0];
                	if(file.size > 1024*1024) {
                		$.layout.alert("上传的文件大小不能超过1MB，请重新选择！");
                		return;
                	}
                	if(file.name.toUpperCase().lastIndexOf(".XLS") != file.name.length - 4) {
                		$.layout.alert("上传的文件大小不能超过1MB，请重新选择！");
                		return;
                	}
                	data.submit();
                },
                done: function (e, data) {
                    if(data && data.result && data.result.code == 0) {
                    	var file = data.result.body;
                    	$(".selected-file").attr("data", file.fileKey);
                    	$(".selected-file").text(file.fileName);
                    	$(".no-file-tip").hide();
                    	$(".has-file-tip").show();
                    } else {
                    	$.layout.alert("文件上传发生异常，请重试！");
                    }
                }
            });
		},
		initData = function() {
			var tbody = $(".apply-submit").find(".order-table tbody");
        	$(tbody).html("");
        	
    		var payType = _userInfo.payType.text;
        	var account = "";
        	if(_userInfo.payType.name == "PAY_ZFB") {
        		account = _userInfo.accountForZfb;
        	} else if(_userInfo.payType.name == "PAY_QQ") {
        		account = _userInfo.accountForQq;
        	}
        	
        	if(account) {
        		$(".submit-list .js-change-account").text("更换");
        		$(".submit-list .js-account-span").text(payType + " -> " + account);
        	} else {
        		$(".submit-list .js-change-account").text("设置");
        		$(".submit-list .js-account-span").text("无");
        	}
		},
		initEvent = function() {
			$("body").off("hover", ".apply-menu").on("hover", ".apply-menu", function () {
                $(this).addClass("apply-menu-hover");
            }, function() {
            	$(this).removeClass("apply-menu-hover");
            });
            
			$("body").off("click", ".apply-menu").on("click", ".apply-menu", function () {
                $(this).addClass("apply-menu-selecttd").siblings("div").removeClass("apply-menu-selecttd");
                var panelDiv = $(this).attr("data");
                $("."+panelDiv).removeClass("hide").siblings("div").addClass("hide");
                
                if(panelDiv == "apply-manage") {
                	searchOrderList(1);
                }
            });
            
			$("body").off("click", ".js-model-download").on("click", ".js-model-download", function() {
            	var form = $("<form>");
            	form.attr("style","display:none");
            	form.attr("target","");
            	form.attr("method","post");
            	form.attr("action", BASE_PATH + "/file/downloadApplyModel");
            	$("body").append(form);
            	form.submit();
            	form.remove();
            });
            
			$("body").off("click", "#js-file-upload").on("click", "#js-file-upload", function() {
            	$("#fileupload").click();
            });
            
			$("body").off("click", ".js-add-row").on("click", ".js-add-row", function() {
				var allRecord = $(".apply-submit").find(".order-table tbody tr");
				if(allRecord.length == 15) {
					$.layout.alert("一次性最多只能添加<span class='f-label-org'>15</span>条订单！");
					return;
				}
            	var trHtml = '<tr><td algin="center"><img src="'+BASE_PATH+'/assets/images/del-ico.png?version='+version+'" class="js-del-img"></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="平台名称" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="注册手机" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="用户名" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text f-text-right" placeholder="投资金额" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text f-text-right" placeholder="标的期限" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="投资时间" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="备注" /></td>'
            		+ '</tr>';
            	var tbody = $(this).parents(".apply-submit").find(".order-table tbody");
            	$(tbody).append(trHtml);
            	
            	$(".f-date").datepicker();
            	$(".f-date").datepicker("option", "dateFormat", "yy-mm-dd");
            });
			
			$("body").off("click", ".js-del-img").on("click", ".js-del-img", function() {
				$(this).parents("tr").remove();
            });
            
			$("body").off("click", ".js-export-order").on("click", ".js-export-order", function() {
            	var form = $("<form>");
            	form.attr("style","display:none");
            	form.attr("target","");
            	form.attr("method","post");
            	form.attr("action", BASE_PATH + "/file/downloadOrderList");
            	$("body").append(form);
            	form.submit();
            	form.remove();
            });
			
			$("body").off("click", ".js-change-account").on("click", ".js-change-account", function() {
            	var d = $.layout.dialog({
        			"height":"auto",
        			"width":"460px",
        			"localId":"#change_account_div"
        		});
            	
            	if(_userInfo) {
            		$(d).find("input[name=payType][value="+_userInfo.payType.name+"]").attr("checked","checked");
            		$(d).find("input[name=payType]").change();
            		$(d).find("input[name=accountForZfbUser]").val(_userInfo.accountForZfbUser);
            		$(d).find("input[name=accountForZfb]").val(_userInfo.accountForZfb);
            		$(d).find("input[name=accountForQq]").val(_userInfo.accountForQq);
            	}
            });
            
            $("body").off("click", ".change-content-div input[name=payType]").on("change", ".change-content-div input[name=payType]", function() {
            	$(".js-account-li").hide();
            	var payType = $(".change-content-div input[name=payType]:checked").val();
            	if(payType == "PAY_ZFB") {
            		$(".js-zfb-li").show();
            	} else if(payType == "PAY_QQ") {
            		$(".js-qq-li").show();
            	}
            });
		},
        searchOrderList = function(pageIndex) {
        	$.post(BASE_PATH + services.QUERY_ORDER_LIST, {pageIndex:pageIndex}, function (result) {
                if (result) {
                	var data = eval('(' + result + ')'); 
                	var rows = data.rows;
                	
                	var contentHtml = "";
                    for (var i = 0; i < rows.length; i++) {
                    	contentHtml += '<tr>'
										+ '<td>'+(i+1)+'</td>'
										+ '<td>'+rows[i].platForm.name+'</td>'
										+ '<td>'+rows[i].applyTelephone+'</td>'
										+ '<td>'+rows[i].applyUserName+'</td>'
										+ '<td>'+rows[i].amount+'</td>'
										+ '<td>'+rows[i].timeRange+'</td>'
										+ '<td>'+rows[i].investTime+'</td>'
										+ '<td>'+rows[i].status.text+'</td>'
										+ '<td title="'+rows[i].note+'">'+rows[i].note+'</td>'
										+ '</tr>';
                    }
                	$(".apply-manage .order-table tbody").html(contentHtml);
                	
                	PubUtils.initPage({
        				"pageId" : "list-page",
        				"total" : data.total,
        				"pageIndex" : data.page,
        				"callBack" : function(pageIndex, pageSize) {
        					searchOrderList(pageIndex);
        				}
        			});
                }
            });
        };
		
		return {
            "init": init
        };
	})();
	
	
	//===========================================centerPage======================================
	
    var centerPage = (function () {
        var init = function () {
            initEvent();
            getUserInfo(function() {
            	var redirectPage = $("#redirectPage").val();
            	if(redirectPage == "orderCenter") {
            		$(".center-menu[data=apply-center-panel]").click();
            	} else {
            		$(".center-menu[data=person-info-panel]").click();
            	}
            });
            
        }, 
        initEvent = function () {
            $(".center-menu").click(function () {
                $(this).addClass("center-menu-selecttd").siblings("div").removeClass("center-menu-selecttd");
                var panelDiv = $(this).attr("data");
                $("."+panelDiv).removeClass("hide").siblings("div").addClass("hide");
                
                if(panelDiv == "person-info-panel") {
                	personInfoPanel.init();
                } else if(panelDiv == "safe-set-panel") {
                	safeSetPanel.init();
                } else if(panelDiv == "account-panel") {
                	accountPanel.init();
                } else if(panelDiv == "apply-center-panel") {
                	applyCenterPanel.init();
                }
            });
        },
        getUserInfo = function(callBack) {
        	$.post(BASE_PATH + services.QUERY_USER_INFO, {}, function (result) {
                if (result && result.code == 0) {
                	if(result.body) {
                		_userInfo = result.body;
                		callBack();
                	}
                } else {
                	window.location.href = BASE_PATH + "index";
                }
            });
        };

        return {
            "init" : init
        };
    })();

    centerPage.init();
})(jQuery);