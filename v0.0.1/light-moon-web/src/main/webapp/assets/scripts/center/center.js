(function ($) {
    var centerPage = function () {
        var init = function () {
            initEvent();
            
            var redirectPage = $("#redirectPage").val();
            if(redirectPage == "orderCenter") {
            	$("#apply-center-panel").click();
            }
        }, initEvent = function () {
            $(".center-menu").click(function () {
                $(this).addClass("center-menu-selecttd").siblings("div").removeClass("center-menu-selecttd");
                var panelDiv = $(this).attr("data");
                $("."+panelDiv).removeClass("hide").siblings("div").addClass("hide");
            });
            
            $(".apply-menu").hover(function () {
                $(this).addClass("apply-menu-hover");
            }, function() {
            	$(this).removeClass("apply-menu-hover");
            });
            
            $(".apply-menu").click(function () {
                $(this).addClass("apply-menu-selecttd").siblings("div").removeClass("apply-menu-selecttd");
            });
        };

        return {
            "init": init
        };
    };

    centerPage().init();
})(jQuery);