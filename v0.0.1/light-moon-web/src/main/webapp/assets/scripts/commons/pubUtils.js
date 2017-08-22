/**
 * Created by lihh on 2017/8/21.
 * 
 * 组件工具类。依赖于jQuery
 * 
 */
(function($) {
	var _pubUtils = (function() {
		var _page = {
			_conf : {
				
			},
			init : function(conf) {
				var t = _page;
				t._conf = $.extend({}, t._default_conf, conf);
				t.initPageComponent(t._conf.page);
				t._event();
			},
			_default_conf : {
				"pageIndex" : 1,
				"pageSize" : 20,
				"maxShowPage" : 3
			},
			initPageComponent:function(page) {
				var conf = _page._conf;
				
				conf.total = parseInt(conf.total);
		        if(!conf.total){
		        	conf.total = 0;
		        }
		        conf.page = parseInt(page);
		        if(!conf.page){
		        	conf.page = 1;
		        }
		        
		        var appendHTML = _page.initPageModel(conf.total, conf.page);
		        $("#" + conf.pageId).html(appendHTML);
			},
			initPageModel:function(pageCount, currentPage) {
				var conf = _page._conf;
				
				var prePage = currentPage - 1;
		        var nextPage = currentPage + 1;
		        
		        var prePageClass = "pageItem";
		        var nextPageClass = "pageItem";
		        if(prePage <= 0){
		            prePageClass = "pageItemDisable";
		        }
		        if(nextPage > pageCount){
		            nextPageClass = "pageItemDisable";
		        }
		        
		        var appendStr = "";
		        appendStr += "<li class='" + prePageClass + "' page-data='1' onselectstart='return false' page-rel='firstpage'>首页</li>";
		        appendStr += "<li class='" + prePageClass + "' page-data='" + prePage + "' onselectstart='return false' page-rel='prepage'>&lt;上一页</li>";
		        var minPageNumber = 1;
		        if(currentPage - parseInt(conf.maxShowPage / 2) > 0 && currentPage + parseInt(conf.maxShowPage / 2) <= pageCount){
		            minPageNumber = currentPage - parseInt(conf.maxShowPage / 2);
		        } else if(currentPage - parseInt(conf.maxShowPage / 2) > 0 && currentPage + parseInt(conf.maxShowPage / 2) > pageCount){
		            minPageNumber = pageCount - conf.maxShowPage + 1;
		            if(minPageNumber <= 0){
		                minPageNumber = 1;
		            }
		        }
		        var showPageNum = parseInt(conf.maxShowPage);
		        if(pageCount < showPageNum){
		            showPageNum = pageCount;
		        }
		        for(var i=0; i<showPageNum; i++){
		            var pageNumber = minPageNumber ++;
		            var itemPageClass = "pageItem";
		            if(pageNumber == currentPage){
		                itemPageClass = "pageItemActive";
		            }
		            appendStr+="<li class='" + itemPageClass + "' page-data='" + pageNumber + "' onselectstart='return false' page-rel='itempage'>" + pageNumber + "</li>";
		        }
		        appendStr += "<li class='" + nextPageClass + "' page-data='" + nextPage + "' onselectstart='return false' page-rel='nextpage'>下一页&gt;</li>";
		        appendStr += "<li class='" + nextPageClass + "' page-data='" + pageCount + "' onselectstart='return false' page-rel='lastpage'>尾页</li>";
		        
		        return appendStr;
			},
			_event:function() {
				var conf = _page._conf;
				$(document).on("click", "#"+conf.pageId + ">li[class='pageItem']", function() {
					_page.initPageComponent($(this).attr("page-data"));
					conf.callBack(page, conf.pageSize);
				});
			}
		};

		return {
			"initPage" : _page.init
		};
	})();
	
	window.PubUtils = _pubUtils;
	
})(jQuery);