(function ($) {
    var noticePage = function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
        	$("body").on("click", ".js-notice-tr", function() {
        		var noticeId = $(this).attr("data");
        		window.location.href = BASE_PATH + "/notice/detail/" + noticeId;
        	});
        };

        return {
            "init": init
        };
    };

    noticePage().init();
})(jQuery);