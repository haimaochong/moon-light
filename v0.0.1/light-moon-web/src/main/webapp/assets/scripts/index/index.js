var services = {
		QUERY_PLATFORM_LIST:"/index/queryPlatformList"
};

(function ($) {
    var indexPage = (function () {
        var init = function () {
        	search(1);
            initEvent();
        }, initEvent = function () {
            $(".search-order-type li").click(function () {
                $(this).addClass("selected-menu").siblings("li").removeClass("selected-menu");
            });
            $(".search-up-btn").click(function () {
                $(".search-content").slideToggle(500);
                if($(this).val() == "收起筛选") {
                    $(this).val("展示筛选");
                } else {
                    $(this).val("收起筛选");
                }
            });
            $(".search-menu").click(function () {
                $(this).parents(".search-item").find(".search-menu").removeClass("search-selected-menu");
                $(this).addClass("search-selected-menu");
            });
            $(".search-content-more div").click(function() {
                $($(".search-content-div ul")[1]).slideToggle(500);
            });
            
            $(document).on("click", ".js-apply-btn", function() {
            	$("#dialog-confirm").dialog({
            		width: 600,
            		modal: true,
            		buttons: {
            			"登陆": function() {
            				$( this ).dialog( "close" );
            			},
            			"跳过 >>": function() {
            				$( this ).dialog( "close" );
            			}
            		}
            	});
            	
            	$('.ui-dialog-buttonpane').find('button:contains("登陆")').attr("disabled", "disabled");
            	initNoCaptcha();
            });
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
        }, search = function(pageIndex) {
        	$.post(BASE_PATH + services.QUERY_PLATFORM_LIST, {pageIndex: pageIndex}, function (result) {
                if (result) {
                	var data = eval('(' + result + ')'); 
                	var total = data.total;
                	var rows = data.rows;
                	
                	var contentHtml = "";
                    var trHtml = $("#result-tr").html();
                    for (var i = 0; i < rows.length; i++) {
                        contentHtml += trHtml.replace("%img%", rows[i].img).replace("%type%", rows[i].type).replace("%minInvestAccount%", rows[i].minInvestAccount)
                            .replace("%date%", rows[i].date).replace("%a%", rows[i].a).replace("%num%", rows[i].num);
                    }
                    $(".search-result-items").html(contentHtml);
                    
                    PubUtils.initPage({
                		"pageId":"page",
                		"total":data.total,
                		callBack:function(pageIndex, pageSize) {
                			search(pageIndex);
                		}
                	});
                }
            });
        };

        return {
            "init": init
        };
    })();

    indexPage.init();
    
})(jQuery);