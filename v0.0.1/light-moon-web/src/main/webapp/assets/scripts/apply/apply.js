(function ($) {
    var applyPage = function () {
        var init = function () {
        	initClip();
            initEvent();
        }, initEvent = function () {
            $(".js-copy-btn").click(function () {
            	$.layout.alert("复制成功");
            });
        }, initClip = function() {
        	var clip = new ZeroClipboard(document.getElementById("js-copy-btn"));
        	clip.on("copy", function(e){
        		clip.setText($(".js-platform-desc").text());
        	});
        };

        return {
            "init": init
        };
    };

    applyPage().init();
})(jQuery);