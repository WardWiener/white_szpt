$.loading = $.loading || {};
(function($){
	
	"use strict";
	
	/**
	 * 弹出loading
	 * 
	 * @param func 回调方法，一个参数，弹出页面的index
	 * 
	 */
	function alertLoading (func){
		$.util.topWindow().$.layerAlert.dialog({
			content:'<img id="loadingImg" src="' + context + '/images/loading/loading-84.gif"></img><br/><span id="loadingText">正在同步数据</span>',
			type:0,
			maxmin:false,//是否可以最大化
			closeBtn:false,//是否显示关闭按钮
			pageLoading:true,//是否在等待过程中显示转圈
			skin:'layui-laye',//是否显示边框，默认带边框
			title:'',
			shade: [0.7,'black'],
			marginSet:"0px",
			width:"128px",
			height:"15px",
			shadeClose:false,//点击最外层是否关闭弹窗
			initData:{
					
			},
			btn:null,
			success:function(layero, index){
				
				var loadingImg = $("#loadingImg");
				$(loadingImg).css("margin-left","100px");
				$("#loadingText")
					.css("margin-left","105px")
					.css("color","#EEE");
				$(loadingImg)
					.parent("div").css("background","rgba(255, 255, 255, 0)")
						.css("padding","0px").css("height","auto")
					.parent("div").css("background","rgba(255, 255, 255, 0)")
					.css("box-shadow","0px 0px 0px rgba(0,0,0,0)");
				if($.util.isFunction(func)){
					func(index);
				}
			}
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.loading, { 
		alertLoading : alertLoading
	});	
})(jQuery);