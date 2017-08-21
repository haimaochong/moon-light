require(['jquery'], function ($) {
    var applyPage = function () {
        var init = function () {
            initEvent();
        }, initEvent = function () {
            $(".apply-desc-up-btn").click(function () {
                $(".apply-desc-content").slideToggle(500);
                if($(this).val() == "收起说明") {
                    $(this).val("展开说明");
                } else {
                    $(this).val("收起说明");
                }
            });
        };

        return {
            "init": init
        };
    };

    applyPage().init();
});