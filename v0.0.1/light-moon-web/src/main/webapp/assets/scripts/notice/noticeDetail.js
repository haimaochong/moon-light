(function ($) {
    var noticeDetailPage = function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
        	$("body").on("click", ".all-notice-tip", function() {
    			window.location.href = BASE_PATH + "/notice";
        	});
        	
        	$("body").on("click", ".other-notice", function() {
        		var noticeId = $(this).attr("data");
        		if(noticeId) {
        			window.location.href = BASE_PATH + "notice/detail/" + noticeId;
        		}
        	});
        };

        return {
            "init": init
        };
    };

    noticeDetailPage().init();
})(jQuery);