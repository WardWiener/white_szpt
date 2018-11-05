$.caseTagCompare = $.caseTagCompare || {};
(function($){
	
	"use strict";
	
	var ctcTable = null;
	var hideItemNameArray = [];//隐藏显示的项目名
	
	$(document).ready(function() {	
		queryCaseTagsByCaseCodes();
		
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
		 * 串并案分析
		 */
		$(document).on("click","#caseAnalysisBut",function(){
			var array = $.icheck.getChecked("caseCode");
			if(array.length < 1){
				$.util.topWindow().$.layerAlert.alert({icon:0,msg:"请选择一个案件。"}) ;
				return ;
			}
			var caseCode = $(array[0]).val();
			$.util.topWindow().location.href = context + "/caseAnalysis/showCaseAnalysisPage.action?clickOrder=2&&clickListOrder=1&&caseCode=" + caseCode;
		});
		
		/**
		 * 刷新
		 */
		$(document).on("click","#refreshBut",function(){
			queryCaseTagsByCaseCodes();
		});
		
		/**
		 * 导出Excel
		 */
		$(document).on("click","#exportExcelBut",function(){
			var obj = new Object();
			$.util.objToStrutsFormData(caseCodes,"caseCodes",obj);
			var form = $.util.getHiddenForm(context +'/caseAnalysis/exportExcelByCaseTagCompareList.action', obj);
			$.util.subForm(form);
		});
		
		/**
		 * 返回
		 */
		$(document).on("click","#back",function(){
			history.back();
		});
	});
	
	/**
	 * 高亮显示相同项
	 */
	function highlightShowEqualItem(){
		//获取相同项td的索引
		var tdIndexArray = getEqualTdIndexArray();
		$.each(tdIndexArray,function(i,tdIndex){
			$("#ctcTable tbody tr").each(function(t,tr){
				$(tr).find("td").eq(tdIndex).css("color","#EF5151");
			});
		});
	}
	
	/**
	 * 获取相同项（td）的索引数组
	 */
	function getEqualTdIndexArray(){
		var equalTdIndexArray = [];
		var tdLength = $("#ctcTable tbody tr").eq(0).find("td").length;
		for(var i=2;i<tdLength;i++){
			var tdTextArray = [];
			var equalFlag = true;//项是否全部相同
			
			var codeArray = [];
			if(i == 8){
				$("#ctcTable tbody tr").each(function(t,tr){
					var trData = $(tr).data("data");
					codeArray.push(trData.featureCodes);
				});
				var resultArray = getMultiArrayIntersection(codeArray);
				if(resultArray.length < 1){
					equalFlag = false;
				}
			}else{
				$("#ctcTable tbody tr").each(function(t,tr){
					var tdText = $(tr).find("td").eq(i).text();
					if(tdTextArray.length > 0 && $.inArray(tdText,tdTextArray) == -1){// 不存在，设置flag，退出当前循环
						equalFlag = false;
						return false;
					}
					//存在，添加
					tdTextArray.push(tdText);
				});
			}
			if(equalFlag){
				equalTdIndexArray.push(i);
			}
		}
		return equalTdIndexArray;
	}
	
	/**
	 * 获取多个数组的交集
	 * 
	 * @param dataArray 二维数组，例如：[[1,2,3],[1,2,4]]
	 * @returns intersectionArray 交集数组，例如：[1,2]
	 */
	function getMultiArrayIntersection(dataArray){
		if(!$.util.isArray(dataArray) || dataArray.length < 2){
			return [];
		}
		if(!$.util.isArray(dataArray[0]) || !$.util.isArray(dataArray[1]) || dataArray[0].length < 1 || dataArray[1].length < 1){
			return [];
		}
		
		var intersectionArray = [];
		$.each(dataArray,function(a, array){
			if(a == 1){
				$.each(dataArray[0],function(d,data){
					if($.inArray(data, array) != -1){
						intersectionArray.push(data);
					}
				});
			}else if(a > 1){
				var tArray = [];
				$.each(intersectionArray,function(d,data){
					if($.inArray(data, array) != -1){
						tArray.push(data);
					}
				});
				intersectionArray = tArray;
				if(tArray.length == 0){
					return false;
				}
			}
		});
		return intersectionArray;
	}
	
	/**
	 * 显示不同项
	 */
	function showImparityItem(){
		$("#ctcTable thead th:hidden").show();
		$("#ctcTable tbody td:hidden").show();
		hideItemNameArray = [];
		setHideItemInfo();
	}
	
	/**
	 * 隐藏不同项
	 */
	function hideImparityItem(){
		//获取相同项td的索引
		var tdIndexArray = getEqualTdIndexArray();
		var tdLength = $("#ctcTable tbody tr").eq(0).find("td").length;
		
		for(var i=2;i<tdLength;i++){
			if($.inArray(i,tdIndexArray) == -1){
				var th = $("#ctcTable thead tr").find("th").eq(i);
				$(th).hide();
				hideItemNameArray.push($(th).text());
				$("#ctcTable tr").each(function(t,tr){
					$(tr).find("td").eq(i).hide();
				});
			}
		}
		setHideItemInfo();
	}
	
	/**
	 * 设置隐藏说明
	 */
	function setHideItemInfo(){
		if(hideItemNameArray.length < 1){
			$("#hideItemInfo").text("无");
		}else{
			var itemNames = "";
			for(var i=0;i<hideItemNameArray.length;i++){
				itemNames += hideItemNameArray[i];
				if(i < hideItemNameArray.length - 1){
					itemNames += "；";
				}
			}
			$("#hideItemInfo").text("共隐藏" + hideItemNameArray.length + "项，" + itemNames);
		}
	}
	
	/**
	 * 查询对比结果
	 */
	function queryCaseTagsByCaseCodes(){
		var data = {
			"caseCodes" : caseCodes
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/caseAnalysis/queryCaseTagsByCaseCodes.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				initCtcTable(resultMap.ctcbs);
			}
		});
	}
	
	/**
	 * 初始化结果表
	 * 
	 * @param dataArray 数据数组
	 */
	function initCtcTable(dataArray){
		console.log(dataArray);
		if($.util.exist(ctcTable)){
			ctcTable.destroy();
			$("#ctcTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "",
			"title" : "选择",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				var td = '<input type="radio" class="icheckradio" name="caseCode" value="' + data + '">';
				return td;
			}
		}, {
			"targets" : 1,
			"width" : "",
			"title" : "案件基本信息",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				var td = '<p><a href="javascript:void(0);" class="caseCode">' + data + '</a></p>';
				td += '<h4 class="row-mar">' + full.caseName + '</h4>';
				td += '<p><span class="color-gray">办案民警：</span>' + full.handlePolice + '</p>';
				td += '<p><span class="color-gray">发案时间：</span>' + $.date.timeToStr(full.caseTimeStart,"yyyy-MM-dd HH:mm") + '</p>';
				td += '<p><span class="color-gray">犯罪嫌疑人：</span>' + full.suspectName + '</p>';
				td += '<p><span class="color-gray">案件当前状态：</span><span class="color-green1">' + full.caseStateName + '</span></p>';
				return td;
			}
		}, {
			"targets" : 2,
			"width" : "",
			"title" : "案件类别",
			"data" : "typeName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "",
			"title" : "案件性质",
			"data" : "firstSortName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 4,
			"width" : "",
			"title" : "二级案件性质",
			"data" : "secondSortName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 5,
			"width" : "",
			"title" : "犯罪嫌疑人",
			"data" : "suspectName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 6,
			"width" : "",
			"title" : "案发地点",
			"data" : "address",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 7,
			"width" : "",
			"title" : "案发社区",
			"data" : "communityName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 8,
			"width" : "",
			"title" : "作案特点",
			"data" : "featureNames",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 9,
			"width" : "",
			"title" : "选择处所",
			"data" : "occurPlaceName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 10,
			"width" : "",
			"title" : "作案时段",
			"data" : "periodName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 11,
			"width" : "",
			"title" : "作案人数",
			"data" : "peopleNumName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 12,
			"width" : "",
			"title" : "作案进口",
			"data" : "entranceName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 13,
			"width" : "",
			"title" : "作案出口",
			"data" : "exitName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}];
		
		st1.ordering = false;
		st1.paging = false; // 是否分页
        st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ 5 ];
		st1.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		
		ctcTable = $("#ctcTable").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseTagCompare, { 
		getMultiArrayIntersection : getMultiArrayIntersection
	});	
})(jQuery);

