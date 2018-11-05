$.lookScoreTemplate = $.lookScoreTemplate || {};
(function($){
	"use strict";
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var cgtbId = initData.cgtbId;
	
	$(document).ready(function() {	
		$.scoreTemplateCommon.setGradeItemScoreTableTrAndInputName();
		findGradeTemplateById();
	});
	
	/**
	 * 根据id查询串并案分析评分模版
	 */
	function findGradeTemplateById(){
		if($.util.isBlank(cgtbId)){
			return ;
		}
		var data = {
			"rtcstId" : cgtbId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/scoreTemplate/queryById.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cgtb = successData.resultMap.rtcstb;
				var gisbs = successData.resultMap.rtcstrs;
				if(!$.util.exist(cgtb)){
					return ;
				}
				
				//设置页面值
				$.validform.setFormTexts("#cbaGradeTemplateData", cgtb);
				
				setGradeItemScore(gisbs,"span");
			}
		});
	}
	
	/**
	 * 设置评分项得分
	 * @param objArray 评分项数组 
	 */
	function setGradeItemScore(objArray){
		if(!$.util.exist(objArray) || objArray.length < 1){
			return ;
		}
		$.each(objArray,function(i,val){
			$("tr[name='"+ val.item +"']").find("span").eq(0).text(val.weight);
			var inputValueArr = val.rule.split(",");
			$.each(inputValueArr,function(v,inputValue){
				$("tr[name='"+ val.item +"']").find("span").eq(v+1).text(inputValue);
			});
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookScoreTemplate, { 
		
	});	
})(jQuery);