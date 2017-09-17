var services = {
		CHECK_LOGIN:"user/checkLogin",
		QUERY_USER_INFO:"center/queryUserInfo"
};

(function ($) {
	var _userInfo = null;
	
    var applyPage = function () {
        var init = function () {
        	initClip();
            initEvent();
        }, initEvent = function () {
            $(".js-copy-btn").click(function () {
            	$.layout.alert("复制成功");
            });
            
            $("body").on("click", ".js-submit-btn", function() {
            	$.post(BASE_PATH + services.CHECK_LOGIN, {}, function (result) {
                    if (result && result.code == 0) {
                    	_userInfo = result.body;
                		openSubmitPanel(_userInfo);
                    } else {
                    	$(".js-login").click();
                    }
                });
            });
            
            $("body").on("click", ".js-change-account", function() {
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
            
            $("body").on("change", ".change-content-div input[name=payType]", function() {
            	$(".js-account-li").hide();
            	var payType = $(".change-content-div input[name=payType]:checked").val();
            	if(payType == "PAY_ZFB") {
            		$(".js-zfb-li").show();
            	} else if(payType == "PAY_QQ") {
            		$(".js-qq-li").show();
            	}
            });
        }, initClip = function() {
        	var clip = new ZeroClipboard(document.getElementById("js-copy-btn"));
        	clip.on("copy", function(e){
        		clip.setText($(".js-platform-desc").text());
        	});
        }, openSubmitPanel = function(userInfo) {
        	var d = $.layout.dialog({
    			"height":"520px",
    			"width":"800px",
    			"localId":"#submit_order_div"
    		});
        	
        	var payType = userInfo.payType.text;
        	var account = "";
        	if(userInfo.payType.name == "PAY_ZFB") {
        		account = userInfo.accountForZfb;
        	} else if(userInfo.payType.name == "PAY_QQ") {
        		account = userInfo.accountForQq;
        	}
        	
        	if(account) {
        		$(d).find(".js-change-account").text("更换");
        		$(d).find(".account-code").text(payType + " -> " + account);
        	} else {
        		$(d).find(".js-change-account").text("设置");
        		$(d).find(".account-code").text("无");
        	}
        	
        };

        return {
            "init": init
        };
    };

    applyPage().init();
})(jQuery);