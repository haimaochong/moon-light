(function($) {
    'use strict';
    
    var _layout = {
    		_conf:{},
    		_default_conf:{
    			"width":"auto",
    			"height":"auto",
    			"model":true,
    			"localId":null
    		},
    		init:function(type, message, options) {
    			var activeElement = document.activeElement;
    	        activeElement.blur();
    	        
    			var t = _layout,zIndex;
    			var c = t._conf = $.extend({}, t._default_conf, options);
    			if(c.model){
    				zIndex = t.loading() + 1;
    			} else {
    				zIndex = $.zIndex?$.zIndex:9000;
    			}
    			$.zIndex = zIndex;;
    	    	t._conf.zIndex = zIndex;
    	    	
    	    	t.show(type, message);
    	    	t._conf.ele = $("div[name^=layout_"+zIndex+"]");
    	    	t._initEvent();
    	    	return t._conf.ele;
    		},
    		show:function(type, message) {
    			var t = _layout,c = t._conf;
    			var dialogHTML = t.initModel(t._getModel(type, message));
    			$("body").append(dialogHTML);
    			if(c.model) {
    				$("div[name^=layout_"+c.zIndex+"]").add("div[name^=layout_"+(c.zIndex-1)+"]").fadeIn(150);
    			} else {
    				$("div[name^=layout_"+c.zIndex+"]").fadeIn(150);
    			}
    		},
    		_getModel: function(type, message) {
    			var c = _layout._conf;
    			var model = '<div name="layout_'+c.zIndex+'_'+type+'" style="z-index:'+c.zIndex+'" class="layout-index" ><div style="width:%width%;height:%height%" class="layout-dialog">';
    			switch (type) {
				case "_alert":
					model += '<div class="notice-top">信息</div><div class="notice-content">'+message+'</div><div class="notice-btns"><input type="button" class="layout-btn js-close" value="关 闭"></div>';
					break;
				case "_dialog":
					model += $(c.localId).html();
				}
    			return model+"</div><div class='close-tip'>[ 关闭 ]</div</div>";
    		},
    		initModel:function(contentHTML) {
    			var c = _layout._conf;
    			for(var key in c) {
    				contentHTML = contentHTML.replace(new RegExp("%"+key+"%",'g'), c[key]);
    			}
    			return contentHTML;
    		},
    		_initEvent: function() {
    			var t=_layout,c=t._conf,ele=c.ele;
    			
    			$(ele).find(".js-close,.close-tip").off("click").on("click", function() {
    				t.close(c.zIndex);
    				t.close(c.zIndex-1);
    			});
    		},
    		_getLoadingModel: function(zIndex) {
    			return "<div name='layout_"+zIndex+"_loading' style='z-index:"+zIndex+"' class='layout-loading'></div>";
    		},
    		loading: function() {
    	    	var zIndex = $.zIndex?++$.zIndex:9000;
    	    	$.zIndex = zIndex;
    	    	$("body").append(_layout._getLoadingModel(zIndex));
    	    	$("div[name^=layout_"+zIndex+"]").height($("body").height());
    	    	return zIndex;
    	    },
    	    close: function(dialogIndex) {
    	    	$("div[name^=layout_"+dialogIndex+"]").fadeOut(150, function() {
    	    		$(this).remove();
    	    	});
    	    },
    	    closeAll: function() {
    	    	$("div[name^=layout_]").remove();
    	    	$("body").css({overflow:"auto"});
    	    }
    };
    
    $.layout = {
        loading: _layout.loading,
        alert: function(message, options) {
        	_layout.init("_alert", message, options);
        },
        dialog: function(options) {
        	return _layout.init("_dialog", null, options);
        },
        close: function(dialogIndex) {
        	_layout.close(dialogIndex);
        },
        closeAll: _layout.closeAll
    };
    
})(jQuery);