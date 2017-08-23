var services = {
		USER_LOGOUT:"/logout"
};

(function ($) {
    var headPage = (function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
        	$(".js-myCenter").click(function() {
        		window.open(BASE_PATH + "/center");
        	});
        	$(".js-logout").click(function() {
        		$.post(BASE_PATH + services.USER_LOGOUT, {userId: 1}, function (result) {
        			window.location.href = BASE_PATH + "/index"
                });
        	});
        };

        return {
            "init": init
        };
    })();

    headPage.init();
    
})(jQuery);