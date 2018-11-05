(function($){
	"use strict";
	$(document).ready(function() {	
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#add",function(){
			window.top.$.layerAlert.dialog({
				content : context +  '/contactAnalyze/showNewPersonPage.action',
				pageLoading : true,
				title:"人员及话单增加",
				width : "520px",
				height : "700px",
				btn:["确定","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
						cm.save();
					},
					btn2:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    		},
	    		end:function(){
	    			table.draw(false);
	    		}
			});
		});
		$(document).on("click","#reset",function(){
		});
	});
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
	});	
})(jQuery);