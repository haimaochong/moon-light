require(['jquery'], function ($) {
    var centerPage = function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
            $(".operate-menu").click(function () {
                $(this).addClass("choose-menu").siblings("div").removeClass("choose-menu");
            });
        };

        return {
            "init": init
        };
    };

    centerPage().init();
});