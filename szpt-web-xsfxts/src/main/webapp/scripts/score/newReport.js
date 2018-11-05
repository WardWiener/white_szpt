$.newReport = $.newReport || {};
(function($){
	
	"use strict";
	
	$(document).ready(function() {	
		
		/**
		 * 保存
		 */
		$(document).on("click","#saveBut",function(){
			$("#newBut").hide();
			$("#lookBut").show();
		});
		
		/**
		 * 取消
		 */
		$(document).on("click","#cancelBut",function(){
			window.close();
		});
		
		/**
		 * 导出
		 */
		$(document).on("click","#exportWordBut",function(){
			alert("导出Word。");
		});
		
		/**
		 * 通报
		 */
		$(document).on("click","#informBut",function(){
			alert("通报。");
		});
		
		
	});
	
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.newReport, { 
		
	});	
})(jQuery);
