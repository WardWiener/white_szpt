$.modifyScoreTemplate = $.modifyScoreTemplate || {};
(function($){
	"use strict";
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData;
	var cgtbId = initData.cgtbId;
	
	var cascadedDicItem = null;
	var secondSortIsNotNull = false; //二级案件性质默认不必填
	var dicItemCodeObj = new Object();// 字典项code数据
	
	$(document).ready(function(){
		$.scoreTemplateCommon.setGradeItemScoreTableTrAndInputName();
		findGradeTemplateById();
		
		/**
		 * 模版编码输入改变事件
		 */
		$(document).on("keyup change","input[name='code']",function(){
			var code = $(this).val();
			if(code == $(this).attr("oldValue")){
				return;
			}
			$.scoreTemplateCommon.verifyCodeOnly(code);
		});
		
		/**
		 * 模版名称输入改变事件
		 */
		$(document).on("keyup change","input[name='name']",function(){
			var name = $(this).val();
			if(name == $(this).attr("oldValue")){
				return;
			}
			$.scoreTemplateCommon.verifyNameOnly(name);
		});
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
				dicItemCodeObj["state"] = cgtb.state;
				dicItemCodeObj["type"] = cgtb.type;
				dicItemCodeObj["firstSort"] = cgtb.firstSort;
				dicItemCodeObj["secondSort"] = cgtb.secondSort;
				
				$.szpt.util.businessData.addToInitSuccessCallBack(initZt);
				initAjlb();
				
				//设置页面值
				$.validform.setFormVals("#cbaGradeTemplateData", cgtb);
				$("input[name='name']").attr("oldValue",cgtb.name);
				$("input[name='code']").attr("oldValue",cgtb.code);
				
				$.scoreTemplateCommon.setGradeItemScore(gisbs);
			}
		});
	}
	
	
	/**
	 * 初始化页面状态字典项
	 */
	function initZt(){
		$.szpt.util.businessData.initZtSelect("#state");
		$.select2.val("#state",dicItemCodeObj["state"]);
	}
	
	/**
	 * 初始化页面案件类别相关字典项
	 */
	function initAjlb(){
		var dicItemSettings = {
				id:"cgtAjxz",
				dicTypeCode : $.common.DICT.DICT_TYPE_AJXZ
			};
			cascadedDicItem = $.cascadedDicItem.init(dicItemSettings);
			
			var selects = [{
				selector : "#type",
				selectedCode : dicItemCodeObj["type"]
			},
			{
				selector : "#firstSort",
				selectedCode : dicItemCodeObj["firstSort"],
				selectEventCallback : function(select){
					setSecondLevelNatureIsNotNull(select);
				},
				unselectEventCallback : function(select){
					setSecondLevelNatureIsNotNull(select);
				}
			},
			{
				selector : "#secondSort",
				selectedCode : dicItemCodeObj["secondSort"]
			}]
			
			cascadedDicItem.refreshBySelectedCodes(selects);
	}
	
	/**
	 * 设置二级案件性质是否必填
	 */
	function setSecondLevelNatureIsNotNull(select){
		var selected = $.select2.val("#" + $(select).attr("id"));
		if(selected == $.common.DICT.DICT_AJXZ_QTDQA || selected == $.common.DICT.DICT_AJXZ_YBDQA){
			$("#twoNatureSpan").show();
			secondSortIsNotNull = true;
		}else{
			$("#twoNatureSpan").hide();
			secondSortIsNotNull = false;
		}
	}
	
	/**
	 * 更新
	 */
	function update(){
		var demo = $.validform.getValidFormObjById("cbaGradeTemplate") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		
		var msg = $.scoreTemplateCommon.verifySelect(secondSortIsNotNull);
		if(!$.util.isBlank(msg)){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:msg});
			return ;
		}
		
		if(!$.scoreTemplateCommon.verifyQuanZhong()){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"权重值总和需=10"});
			return ;
		}
		
		var data = {
			"rtcstb" : $.validform.getFormVals("#cbaGradeTemplateData") ,
			"rtcstrs" : $.scoreTemplateCommon.getGradeItemScore() //获取评分项得分
		}
		var dataStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/scoreTemplate/modify.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			customizedOpt:{
				ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				if(resultMap.flag){
					$.util.topWindow().$.layerAlert.alert({icon:6, closeBtn:false, msg:"修改成功。", yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}else{
					$.util.topWindow().$.layerAlert.alert({icon:0, msg:resultMap.msg});
				}
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.modifyScoreTemplate, { 
		update : update
	});	
	
})(jQuery);