(function($){
	"use strict";

	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	
	$(document).ready(function() {
		setValue(initData.integralModel);
	});
	
	
	function setValue(integralModel){
		var integralModelRule = integralModel.integralModelRule;
		$("#num").val(integralModel.num);
		$("#name").val(integralModel.name);
		$("#state").val(integralModel.statusName);
		$("#alertPoint").val(integralModel.alertPoint);
		$("#remark").val(integralModel.note);
		$("#modifyPeople").text(integralModel.modifyPeopleName);
		$("#modifyTime").text(integralModel.modifyTime);
		$.each($(".valCell"),function(i,val){
			$.each(integralModelRule,function(y,itm){
				if(itm.key == $(val).attr("valName")){
					$(val).val(itm.value);
				}
			})
		});
	}
	
	
	
	
})(jQuery);