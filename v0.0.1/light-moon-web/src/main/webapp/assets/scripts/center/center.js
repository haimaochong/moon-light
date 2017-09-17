var services = {
		QUERY_ORDER_LIST:"center/queryOrderList",
		QUERY_USER_INFO:"center/queryUserInfo"
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
			
		},
		initData = function() {
    		$(".account-panel").find("input[name=payType][value="+_userInfo.payType.name+"]").attr("checked", true);
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