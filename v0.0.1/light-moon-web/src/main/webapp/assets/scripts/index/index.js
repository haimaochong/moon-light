require(['jquery'], function ($) {
    var indePage = function () {
        var init = function () {
            initTable();
            initEvent();
        }, initTable = function () {
            var data = [];
            for (var i = 0; i < 10; i++) {
                var record = {};
                record["img"] = BASE_PATH + "/images/test.png";
                record["type"] = (i == 2 || i == 6 || i == 7) ? "仅限首投" : "可复投";
                record["minCount"] = 1000;
                record["date"] = "一个月及以上";
                record["a"] = parseInt(Math.random() * 50 + 20, 10);
                ;
                record["num"] = parseInt(Math.random() * 99999, 10);
                data.push(record);
            }

            var contentHtml = "";
            var trHtml = $("#result-tr").html();
            for (var i = 0; i < data.length; i++) {
                contentHtml += trHtml.replace("%img%", data[i].img).replace("%type%", data[i].type).replace("%minCount%", data[i].minCount)
                    .replace("%date%", data[i].date).replace("%a%", data[i].a).replace("%num%", data[i].num);
            }
            $(".search-result-items").html(contentHtml);
        }, initEvent = function () {
            $(".top-title-menus div").click(function () {
                $(this).addClass("selected-menu").siblings("div").removeClass("selected-menu");
            });
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
        };

        return {
            "init": init
        };
    };

    indePage().init();
});