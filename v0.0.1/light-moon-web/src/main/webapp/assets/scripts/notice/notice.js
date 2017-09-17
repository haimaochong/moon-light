var services = {
		QUERY_NOTICE:"notice/queryNotice"
};

(function ($) {
    var noticePage = function () {
        var init = function () {
            initEvent();
            search(1);
        }, initEvent = function () {
        	$("body").on("click", ".js-notice-tr", function() {
        		var noticeId = $(this).attr("data");
        		window.location.href = BASE_PATH + "notice/detail/" + noticeId;
        	});
        }, search = function(pageIndex) {
        	$.post(BASE_PATH + services.QUERY_NOTICE, {pageIndex:pageIndex}, function (result) {
                if (result) {
                	var data = eval('(' + result + ')'); 
                	var rows = data.rows;
                	
                	var contentHtml = "";
                    for (var i = 0; i < rows.length; i++) {
                    	contentHtml += '<tr class="js-notice-tr" data="'+rows[i].id+'">'
										+ '<td>'+new Date(rows[i].createTime).pattern("yyyy-MM-dd")+'</td>'
										+ '<td align="left" title="'+rows[i].title+'">'+rows[i].title+'</td>'
										+ '</tr>';
                    }
                	$(".notice-list tbody").html(contentHtml);
                	
                	PubUtils.initPage({
        				"pageId" : "list-page",
        				"total" : data.total,
        				"pageIndex" : data.page,
        				"callBack" : function(pageIndex, pageSize) {
        					search(pageIndex);
        				}
        			});
                }
            });
        };

        return {
            "init": init
        };
    };

    noticePage().init();
})(jQuery);