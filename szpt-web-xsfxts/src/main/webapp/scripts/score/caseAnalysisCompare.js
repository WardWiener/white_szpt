$.caseAnalysisCompare = $.caseAnalysisCompare || {};

(function($){
	
	"use strict";
	
	var hideItemNameArray = [];//隐藏显示的项目名
	
	var mainCase = null;// 主案件对象
	var cacbs = [];// 可能的串并案集合
	
	$(document).ready(function() {	
		initPageCaseInfo();
		/**
		 * 返回
		 */
		$(document).on("click","#backBut",function(){
			history.back();
		});
		
		/**
		 * 显示隐藏不同项
		 */
		$(document).on("click","#showHideBut",function(){
			//根据状态设置but名字
			if($(this).attr("state") == "hide"){
				hideImparityItem();
				$(this).attr("state","show").text("显示不同项");
			}else{
				showImparityItem();
				$(this).attr("state","hide").text("隐藏不同项");
			}
		});
		
		/**
		 * 高亮显示相同项
		 */
		$(document).on("click","#highlightBut",function(){
			highlightShowEqualItem();
		});
		
		/**
		 * 刷新
		 */
		$(document).on("click","#refreshBut",function(){
			initPageCaseInfo();
			hideItemNameArray = [];
			setHideItemInfo();
			$("#showHideBut").attr("state","hide").text("隐藏不同项");
		});
		
		/**
		 * 快照
		 */
		$(document).on("click","#snapshotbut",function(){
			var data = {
				"mainCaseCode" : mainCaseCode,
				"cacbs":cacbs
			};
			var dataStr = $.util.toJSONString(data) ;
			$.ajax({
				url:context +'/snapshot/createSnapshot.action',
				data:dataStr,
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
			    customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
				success:function(successData){
					var flag = successData.resultMap.flag;
					if(flag){
						$.util.topWindow().$.layerAlert.alert({icon:6, msg:"生成快照成功。"}) ;
					}else{
						$.util.topWindow().$.layerAlert.alert({icon:5, msg:"生成快照失败。"}) ;
					}
				}
			});
		});
		
		/**
		 * 生成报告
		 */
		$(document).on("click","#createReportBut",function(){
			window.open(context + "/carReport/showNewCreateReportPage.action");
		});
		
		/**
		 * 导出Excel
		 */
		$(document).on("click","#exportExcelBut",function(){
			var form = $.util.getHiddenForm(context +'/caseAnalysisCompare/exportExcel.action', getQueryConditionObj());
			$.util.subForm(form);
		});
	});
	
	/**
	 * 显示不同项
	 */
	function showImparityItem(){
		$("#cacTable thead th:hidden").show();
		$("#cacTable tbody td:hidden").show();
		hideItemNameArray = [];
		setHideItemInfo();
	}
	
	/**
	 * 隐藏不同项
	 */
	function hideImparityItem(){
		var tdLength = $("#cacTable tbody tr").eq(0).find("td").length;
		for(var i=2;i<tdLength;i++){
			var flag = false;// 是否有相同项
			if(i == 6){
				var mainCodeArray = [];
				$("#cacTable tbody tr[compareTr='true'").each(function(t,tr){
					var trData = $(tr).data("data");
					if(t == 0){
						mainCodeArray = trData.featureCodes;
					}else{
						var td = $(tr).find("td").eq(i - 1);
						$.each(trData.featureCodes,function(f,fc){
							if($.inArray(fc,mainCodeArray) != -1){//相同
								flag = true;
								return false;
							}
						});
					}
				});
			}else{
				var mainTdText = "";
				$("#cacTable tbody tr[compareTr='true'").each(function(t,tr){
					if(t == 0){
						mainTdText = $(tr).find("td").eq(i).text();
					}else{
						var td = $(tr).find("td").eq(i - 1);
						var tdText = $(td).text();
						if(mainTdText == tdText){
							if($.util.isBlank(mainTdText) && $.util.isBlank(tdText)){
								
							}else{
								flag = true;
								return false;
							}
							
						}
					}
				});
			}
			//隐藏列
			if(!flag){
				//隐藏数据
				$("#cacTable tbody tr[compareTr='true'").each(function(t,tr){
					if(t == 0){
						$(tr).find("td").eq(i).hide();
					}else{
						$(tr).find("td").eq(i - 1).hide();
					}
				});
				//隐藏表头
				var th = $("#cacTable thead tr").find("th").eq(i);
				hideItemNameArray.push($(th).text());
				$(th).hide();
			}
		}
		setHideItemInfo();
	}
	
	/**
	 * 高亮显示相同项
	 */
	function highlightShowEqualItem(){
		var tdLength = $("#cacTable tbody tr").eq(0).find("td").length;
		for(var i=2;i<tdLength;i++){
			if(i == 6){
				var mainCodeArray = [];
				$("#cacTable tbody tr[compareTr='true'").each(function(t,tr){
					var trData = $(tr).data("data");
					if(t == 0){
						mainCodeArray = trData.featureCodes;
					}else{
						var td = $(tr).find("td").eq(i - 1);
						$.each(trData.featureCodes,function(f,fc){
							if($.inArray(fc,mainCodeArray) != -1){//相同
								$(td).css("color","#EF5151");
								return false;
							}
						});
					}
				});
			}else{
				var mainTdText = "";
				$("#cacTable tbody tr[compareTr='true'").each(function(t,tr){
					if(t == 0){
						mainTdText = $(tr).find("td").eq(i).text();
						if($.util.isBlank(mainTdText)){
							return false;
						}
					}else{
						var td = $(tr).find("td").eq(i - 1);
						var tdText = $(td).text();
						if(mainTdText == tdText){
							$(td).css("color","#EF5151");
						}
					}
				});
			}
		}
	}
	
	/**
	 * 设置隐藏说明
	 */
	function setHideItemInfo(){
		if(hideItemNameArray.length < 1){
			$("#hideItemNum").text(0);
			$("#hideItemName").text("无");
		}else{
			var itemNames = "";
			for(var i=0;i<hideItemNameArray.length;i++){
				itemNames += hideItemNameArray[i];
				if(i < hideItemNameArray.length - 1){
					itemNames += "；";
				}
			}
			$("#hideItemNum").text(hideItemNameArray.length);
			$("#hideItemName").text(itemNames);
		}
	}
	
	/**
	 * 获取查询条件对象
	 * 
	 * @param obj 查询条件对象
	 */
	function getQueryConditionObj(){
		var obj = new Object();
		$.util.objToStrutsFormData(mainCaseCode,"mainCaseCode",obj);
		$.util.objToStrutsFormData(caseCodes,"caseCodes",obj);
		return obj;
	}
	
	/**
	 * 查询页面主案件、主案件已知串并案、主案件可能的串并案信息
	 */
	function initPageCaseInfo(){
		$.ajax({
			url:context +'/caseAnalysisCompare/queryMainCaseCorrelationInfo.action',
			data:getQueryConditionObj(),
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				mainCase = successData.resultMap.mainCase;
				cacbs = successData.resultMap.cacbs;
				
				//设置主案件简略信息
				$.validform.setFormTexts("#mainCaseInfo", mainCase);
				
				cacbs.unshift(mainCase);
				$.caseAnalysisCompareCommon.initAnalysisCompareTable("#cacTable", cacbs);
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseAnalysisCompare, { 
		
	});	
})(jQuery);