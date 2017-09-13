var services = {
		QUERY_ORDER_LIST:"center/queryOrderList"
};

(function ($) {
    var centerPage = function () {
        var init = function () {
        	initComponent();
            initEvent();
            
            var redirectPage = $("#redirectPage").val();
            if(redirectPage == "orderCenter") {
            	$("#apply-center-panel").click();
            }
        }, initComponent = function() {
        	$("#birthday").datepicker({
                dateFormat: 'yy-mm-dd',
                changeYear: true,
                monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
                dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"]
        	});
        	
        	$('#fileupload').fileupload({
                dataType: 'json',
                add: function (e, data) {
                	var file = data.files[0];
                	if(file.size > 1024*1024) {
                		$.layout.alert("上传的文件大小不能超过1MB，请重新选择！");
                		return;
                	}
                	if(file.name.toUpperCase().lastIndexOf(".XLS") != file.name.length - 4) {
                		$.layout.alert("上传的文件大小不能超过1MB，请重新选择！");
                		return;
                	}
                	data.submit();
                },
                done: function (e, data) {
                    if(data && data.result && data.result.code == 0) {
                    	var file = data.result.body;
                    	$(".selected-file").attr("data", file.fileKey);
                    	$(".selected-file").text(file.fileName);
                    	$(".no-file-tip").hide();
                    	$(".has-file-tip").show();
                    } else {
                    	$.layout.alert("文件上传发生异常，请重试！");
                    }
                }
            });
        }; initEvent = function () {
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
                var panelDiv = $(this).attr("data");
                $("."+panelDiv).removeClass("hide").siblings("div").addClass("hide");
                
                if(panelDiv == "apply-manage") {
                	searchOrderList(1);
                }
            });
            
            $(".js-model-download").click(function() {
            	var form = $("<form>");
            	form.attr("style","display:none");
            	form.attr("target","");
            	form.attr("method","post");
            	form.attr("action", BASE_PATH + "/file/downloadApplyModel");
            	$("body").append(form);
            	form.submit();
            	form.remove();
            });
            
            $("#js-file-upload").click(function() {
            	$("#fileupload").click();
            });
            
            $(".js-add-row").click(function() {
            	var trHtml = '<tr><td algin="center"><input type="checkbox" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="平台名称" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="注册手机" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="用户名" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text f-text-right" placeholder="投资金额" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text f-text-right" placeholder="标的期限" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="投资时间" /></td>'
            		+ '<td algin="center"><input type="text" class="f-text" placeholder="备注" /></td>'
            		+ '</tr>';
            	var tbody = $(this).parents(".apply-submit").find(".order-table tbody");
            	$(tbody).append(trHtml);
            	
            	$(".f-date").datepicker();
            	$(".f-date").datepicker("option", "dateFormat", "yy-mm-dd");
            });
        },
        searchOrderList = function(pageIndex) {
        	$.post(BASE_PATH + services.QUERY_ORDER_LIST, {pageIndex:pageIndex}, function (result) {
                if (result) {
                	var data = eval('(' + result + ')'); 
                	var rows = data.rows;
                	
                	var contentHtml = "";
                    var trHtml = $("#platform-item-model").html();
                    for (var i = 0; i < rows.length; i++) {
                    	contentHtml += '<tr>'
										+ '<td>'+(i+1)+'</td>'
										+ '<td>'+rows[i].platForm.name+'</td>'
										+ '<td>'+rows[i].applyTelephone+'</td>'
										+ '<td>'+rows[i].applyUserName+'</td>'
										+ '<td>'+rows[i].amount+'</td>'
										+ '<td>'+rows[i].timeRange+'</td>'
										+ '<td>'+rows[i].investTime+'</td>'
										+ '<td>'+rows[i].status.text+'</td>'
										+ '<td>'+rows[i].note+'</td>'
										+ '</tr>';
                    }
                	$(".apply-manage .order-table tbody").html(contentHtml);
                }
            });
        };

        return {
            "init": init
        };
    };

    centerPage().init();
})(jQuery);