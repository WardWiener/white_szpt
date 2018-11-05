(function($){
	"use strict";
	$(document).ready(function() {	
		
	});
	
	function setTypeContentText(instructionBean){ 
		if(showDiv(instructionBean)){
			var typeContent=JSON.parse(instructionBean.typeContent);
			$.each($(".typeContent"),function(i,val){
				$(val).html(typeContent[$(val).attr("name")]);
			})
		}
	}
	function setTypeContentValue(instructionBean){ 
		if(showDiv(instructionBean)){
			var typeContent=JSON.parse(instructionBean.typeContent);
			$.each($(".typeContent"),function(){
				$(this).val(typeContent[$(this).attr("name")]);
			})
		}
	}
	function showDiv(instructionBean){
		if(instructionBean.type==$.common.Constant.ZLLX_RYPC()){
			$("#personDiv").show();
			$(".personDiv").show();
			return true;
		}
		if(instructionBean.type==$.common.Constant.ZLLX_CLPC()){
			$("#carDiv").show();
			$(".carDiv").show();
			return true;
		}
		return false;
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		typeContentCommon :{
			setTypeContentText : setTypeContentText,
			setTypeContentValue : setTypeContentValue
		}
	});	
})(jQuery);