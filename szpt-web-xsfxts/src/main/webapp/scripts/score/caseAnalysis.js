$.caseAnalysis = $.caseAnalysis || {};
(function($){
	
	"use strict";
	
	var possibleCaseTable = null;
	var knownCaseTable = null;
	
	$(document).ready(function() {	
		$.szpt.util.businessData.addToInitSuccessCallBack(initQueryConditionDict);
		
		queryCaseByCaseCode();
		queryKnownCaseList();
		
		/**
		 * 分析对比
		 */
		$(document).on("click","#analysisCompareBut",function(){
			//获取案件编号集合
			var caseCodeArray = new Array();
			$("#possibleCaseTable").find("button").each(function(b,but){
				if($(but).hasClass("btn-primary")){
					var data = $(but).parents("tr").data("data");
					if($.util.exist(data)){
						caseCodeArray.push(data.caseCode);
					}
				}
			});
			
			var obj = new Object();
			$.util.objToStrutsFormData(caseCode,"mainCaseCode",obj);
			$.util.objToStrutsFormData(caseCodeArray,"caseCodes",obj);
			var form = $.util.getHiddenForm(context +'/caseAnalysisCompare/showCaseAnalysisComparePage.action', obj);
			$.util.subForm(form);
		});
		
		/**
		 * 操作
		 */
		$(document).on("click","#operation",function(){
			if($(this).hasClass("btn-primary")){
				updateCaseScoreResultIgnored(true, $(this).attr("caseCode"), this);
			}else{
				updateCaseScoreResultIgnored(false, $(this).attr("caseCode"), this);
			}
		});
		
		/**
		 * 查询
		 */
		$(document).on("click",".query",function(){
			queryPossibleCaseList();
		});
		
		/**
		 * 重置
		 */
		$(document).on("click","#reset",function(){
			resetQueryCondition();
		});
		
		/**
		 * 可能的串并案tabs
		 */
		$(document).on("click","#possibleCaseTabs",function(){
			$("#queryCondition").slideDown(500);
			resetQueryCondition();
		});
		
		/**
		 * 已知的串并案tabs
		 */
		$(document).on("click","#knownCaseTabs",function(){
			$("#queryCondition").slideUp(500);
		});
		
		/**
		 * 返回
		 */
		$(document).on("click","#back",function(){
			history.back();
		});
		
		/**
		 * 导出Excel
		 */
		$(document).on("click","#exportExcelBut",function(){
			if("block" == $("#queryCondition").css("display")){
				var queryCondition = $.validform.getFormVals("#queryCondition");
				queryCondition.community = $.szpt.util.businessData.getCheckedByTree("community");
				queryCondition.caseCode = caseCode;
				var dataStr = $.util.toJSONString(queryCondition) ;
				
				var obj = new Object();
				$.util.objToStrutsFormData(dataStr,"queryStr",obj);
				var form = $.util.getHiddenForm(context +'/caseAnalysis/exportExcelByPossibleCaseList.action', obj);
				$.util.subForm(form);
			}else{
				var obj = new Object();
				$.util.objToStrutsFormData(caseCode,"caseCode",obj);
				var form = $.util.getHiddenForm(context +'/criminalBasicCase/exportExcelByKnownCaseList.action', obj);
				$.util.subForm(form);
			}
		});
		
		/**
		 * 查看快照
		 */
		$(document).on("click","#lookSnapshotBut",function(){
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/snapshot/showLookSnapshotListPage.action',
				pageLoading : true,
				title:caseCode + "案件串并分析快照记录",
				width : "550px",
				height : "550px",
				btn:[],
				callBacks:{
					
				},
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					caseCode : caseCode
				},
				end:function(){
					
				}
			});
		});
		
	});
	
	/**
	 * 初始化查询条件字典
	 */
	function initQueryConditionDict(){
		$.szpt.util.businessData.initZatdSelect("#feature");
		$.szpt.util.businessData.initFacsSelect("#occurPlace");
		$.szpt.util.businessData.initCjTree("community");
		$.szpt.util.businessData.initZasdSelect("#period");
		$.szpt.util.businessData.initZarsSelect("#peopleNum");
		$.szpt.util.businessData.initZajckSelect("#entrance");
		$.szpt.util.businessData.initZajckSelect("#exit");
		
		queryPossibleCaseList();
	}
	
	/**
	 * 重置查询条件
	 */
	function resetQueryCondition(){
		$("#minScore").val("");
		$.select2.clear("#feature");
		$.select2.clear("#occurPlace");
		$.select2.clear("#period");
		$.select2.clear("#peopleNum");
		$.select2.clear("#entrance");
		$.select2.clear("#exit");
		$.szpt.util.businessData.destroyTree("community");
		$.szpt.util.businessData.initCjTree("community");
		$("input[name='showHide']").iCheck('uncheck');
		$("input[name='caseState']").iCheck('uncheck');
	}
	
	/**
	 * 显示忽略操作
	 * 
	 * @param state 操作状态
	 * @param possibleCaseCode 可能的串并案编号
	 * @param but 操作按钮
	 */
	function updateCaseScoreResultIgnored(state, possibleCaseCode, but){
		var data = {
			"mainCaseCode" : caseCode ,
			"possibleCaseCode" : possibleCaseCode ,
			"ignored" : state
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/caseAnalysis/updateCaseScoreResultIgnored.action',
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
					if(state){
						$(but).addClass("btn-success").removeClass("btn-primary").text("显示");
					}else{
						$(but).addClass("btn-primary").removeClass("btn-success").text("忽略");
					}
				}
			}
		});
	}
	
	/**
	 * 根据案件编号查询案件
	 */
	function queryCaseByCaseCode(){
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalBasicCaseByCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cbcb = successData.resultMap.cbcb;
				cbcb.caseTimeStart = $.date.timeToStr(cbcb.caseTimeStart,"yyyy-MM-dd HH:mm");
				$.validform.setFormTexts("#mainCaseInfo", cbcb);
			}
		});
	}
	
	/**
	 * 查询可能的串并案
	 */
	function queryPossibleCaseList(){
		var queryCondition = $.validform.getFormVals("#queryCondition");
		queryCondition.community = $.szpt.util.businessData.getCheckedByTree("community");
		queryCondition.caseCode = caseCode;
		var dataStr = $.util.toJSONString(queryCondition) ;
		$.ajax({
			url:context +'/caseAnalysis/queryPossibleCaseList.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var pacbs = successData.resultMap.pacbs;
				initPossibleCaseTable(pacbs);
			}
		});
	}
	
	/**
	 * 查询已知的串并案
	 */
	function queryKnownCaseList(){
		$.ajax({
			url:context +'/criminalBasicCase/queryKnownCaseByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var ctcbs = successData.resultMap.ctcbs;
				initKnownCaseTable(ctcbs);
			}
		});
	}
	
	/**
	 * 初始化可能的串并案列表
	 * 
	 * @param dataArray 数据集合
	 */
	function initPossibleCaseTable(dataArray){
		if($.util.exist(possibleCaseTable)){
			possibleCaseTable.destroy();
			$("#possibleCaseTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "3%",
			"title" : "序号",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				return meta.row + 1;
			}
		}, {
			"targets" : 1,
			"width" : "3%",
			"title" : "分值",
			"data" : "minScore",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "15%",
			"title" : "案件编号",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "16%",
			"title" : "案件名称",
			"data" : "caseName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 4,
			"width" : "8%",
			"title" : "案发时间",
			"data" : "caseTimeStart",
			"render" : function(data, type, full, meta) {
				var td = $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				return td;
			}
		},
		{
			"targets" : 5,
			"width" : "5%",
			"title" : "案件类别",
			"data" : "typeName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 6,
			"width" : "5%",
			"title" : "案件性质",
			"data" : "firstSortName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 7,
			"width" : "5%",
			"title" : "二级案件性质",
			"data" : "secondSortName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 8,
			"width" : "5%",
			"title" : "作案特点",
			"data" : "featureNames",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 9,
			"width" : "5%",
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
		},
		{
			"targets" : 14,
			"width" : "5%",
			"title" : "发案社区",
			"data" : "communityName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 15,
			"width" : "5%",
			"title" : "操作",
			"data" : "ignored",
			"render" : function(data, type, full, meta) {
				var className = "btn-primary";
				var butName = "忽略";
				if(data){
					className = "btn-success";
					butName = "显示";
				}
				return '<button id="operation" caseCode="' + full.caseCode + '" class="btn ' + className + ' btn-sm">' + butName + '</button>';
			}
		}];
		
		st1.ordering = false;
		st1.paging = false; // 是否分页
        st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ dataArray.length ];
		st1.rowCallback = function(row,data, index) {
			$(row).data("data",data);
			if(!$.util.isBlank(data.scoreTemplateMinScore) && parseFloat(data.minScore) > parseInt(data.scoreTemplateMinScore)){
				$(row).find("td").eq(1).css("color","#FF3232");
			}
		};
		
		possibleCaseTable = $("#possibleCaseTable").DataTable(st1);
	}
	
	/**
	 * 初始化已知的串并案列表
	 * 
	 * @param dataArray 数据集合
	 */
	function initKnownCaseTable(dataArray){
		if($.util.exist(knownCaseTable)){
			knownCaseTable.destroy();
			$("#knownCaseTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "",
			"title" : "序号",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				return meta.row + 1;
			}
		}, {
			"targets" : 1,
			"width" : "",
			"title" : "案件编号",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "",
			"title" : "案件名称",
			"data" : "caseName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "",
			"title" : "案发时间",
			"data" : "caseTimeStart",
			"render" : function(data, type, full, meta) {
				var td = $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				return td;
			}
		},
		{
			"targets" : 4,
			"width" : "",
			"title" : "办案民警",
			"data" : "handlePolice",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 5,
			"width" : "",
			"title" : "案件类别",
			"data" : "typeName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 6,
			"width" : "",
			"title" : "案件性质",
			"data" : "firstSortName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 7,
			"width" : "",
			"title" : "二级案件性质",
			"data" : "secondSortName",
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
		},
		{
			"targets" : 14,
			"width" : "",
			"title" : "发案社区",
			"data" : "communityName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}];
		
		st1.ordering = false;
		st1.paging = true; // 是否分页
        st1.info = true; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ 5 ];
		
		knownCaseTable = $("#knownCaseTable").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseAnalysis, { 
		
	});	
})(jQuery);



