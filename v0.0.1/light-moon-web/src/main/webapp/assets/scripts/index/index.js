var services = {
		QUERY_PLATFORM_LIST:"/index/queryPlatformList",
		CHECK_LOGIN:"/user/checkLogin"
};

(function ($) {
	var currentPageIndex = 0;
	
    var indexPage = (function () {
        var init = function () {
        	initComponent();
        	search();
            initEvent();
        }, initComponent = function() {
        	$('.js-notice-div').liMarquee({
                direction: 'up',
                scrollamount: 20
            });
        }, initEvent = function () {
            $("body").on("click", ".show-more-btn", function() {
            	var platformId = $(this).parents(".platform-item").attr("data");
            	window.location.href = BASE_PATH + "/apply?platformInfoId="+platformId;
            });
            
            $(".show-more-tip").click(function() {
            	search();
            });
            
            $(".js-platformSearch").click(function() {
            	currentPageIndex = 0;
            	search();
            });
        }, search = function() {
        	var params = {};
        	params["pageIndex"] = ++currentPageIndex;
        	params["platformName"] = $(".js-platformName").val().trim();
        	
        	$.post(BASE_PATH + services.QUERY_PLATFORM_LIST, params, function (result) {
                if (result) {
                	var data = eval('(' + result + ')'); 
                	var rows = data.rows;
                	
                	var contentHtml = "";
                    var trHtml = $("#platform-item-model").html();
                    for (var i = 0; i < rows.length; i++) {
                        contentHtml += trHtml.replace("%platformName%", rows[i].name).replace("%icoUrl%", rows[i].icoUrl).replace("%platformId%", rows[i].id);
                    }
                    if(currentPageIndex == 1) {
                    	$(".platform-list").html(contentHtml);
                    } else {
                    	$(".platform-list").append(contentHtml);
                    }
                    var currentNum = (currentPageIndex-1)*15 + rows.length;
                    if(currentNum < data.records) {
                    	$(".show-more-tip").show();
                    	$(".no-more-tip").hide();
                    } else {
                    	$(".show-more-tip").hide();
                    	$(".no-more-tip").show();
                    }
                }
            });
        };

        return {
            "init": init
        };
    })();

    indexPage.init();
    
})(jQuery);