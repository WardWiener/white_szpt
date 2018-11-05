$.common =  $.common || {};
(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var irsId = initData.irsId;

	$(document).ready(function(){
		findInstructById();
	});
	
	function sign(){
		$.ajax({
			url:context +'/instruction/signInstruction.action',
			type:'post',
			dataType:'json',
			data:{id:irsId},
			success:function(successData){
				window.top.$.layerAlert.alert({msg:"签收成功!",
					title:"提示",
					icon:1,
					yes:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(pageIndex); 
					}
				});
			},
		});
//		return false;
	}
	
	function findInstructById(){
		$.ajax({
			url:context +'/instruction/findInstruction.action',
			type:'post',
			dataType:'json',
			data:{id:irsId},
			success:function(successData){
				var data = successData.instructionBean;
				$("#.content").text(data.content);
				$("#.createPeopleDepar").text(data.createPeopleDepartmentName);
				$("#.createTime").text($.date.timeToStr(data.createTime,"yyyy-MM-dd HH:mm"));
			},
		});
		
	}
	
	jQuery.extend($.common, { 
		sign:sign
	});	
})(jQuery);
