$.lookCaseTag = $.lookCaseTag || {} ;

(function($){
	
	"use strict";
	
	var caseCode;
	var orbTable = null;
	
	$(document).ready(function(){
		caseCode = $("#paraId").val();
		initPage();
		//设置案件相关信息
		$.lookCriminalBasicCase.caseTagQueryCaseRelatedInfoByCaseCode(caseCode);
		
		/**
		 * 修改
		 */
		$(document).on("click","#modify",function(){
			window.location.href = $.util.fmtUrl(context+"/caseTag/showModifyCaseTagPage.action?id="+caseCode);
		});
		
		/**
		 * 返回
		 */
		$(document).on("click" , "#back", function(e){
			$.util.topWindow().location.href = context + "/caseTag/showCaseTagListPage.action?clickOrder=1&&clickListOrder=1";
		});
	});
	
	/**
	 * 初始化页面
	 */
	function initPage(){
		if($.util.isBlank(caseCode)){
			return ;
		}
		//查询案件分析打标信息
		var data = {"caseCode" : caseCode};
		var dataStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/caseTag/queryCaseTagByCaseCode.action',
			type:'post',
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			dataType:'json',
			success:function(successData){
				var ctb = successData.resultMap.ctb;
				setCaseTagInfo(ctb);
				if($.util.exist(ctb)){
					$("#modify").show();
				}
			}
		});
		//查询案件分析打标操作记录
		$.ajax({
			url:context +'/caseTag/queryCaseTagOpertionRecordByCaseCode.action',
			type:'post',
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			dataType:'json',
			success:function(successData){
				setOptionRecordTable(successData.resultMap.orbs);
			}
		});
	}
	
	/**
	 * 设置案件打标信息
	 * @param obj
	 */
	function setCaseTagInfo(obj){
		$.validform.setFormTexts("#caseTagInfo", obj);
	}

	/**
	 * 设置案件打标操作记录
	 * 
	 * @param dataArray 操作记录集合
	 */
	function setOptionRecordTable(dataArray){
		if($.util.exist(orbTable)){
			orbTable.destroy();
			$("#operateRecordTable").empty();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
			tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "",
					"title" : "操作时间",
					"data" : "operateTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 1,
					"width" : "",
					"title" : "操作内容",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "",
					"title" : "操作单位",
					"data" : "operateUnit",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "",
					"title" : "操作人",
					"data" : "operator",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
			];
			tb.ordering = false ;
			tb.paging = false;
			tb.searching = false ;
			tb.lengthChange = false ;
			tb.autoFooter = false ;
			tb.autoWidth = false ;
			tb.data = dataArray;
			orbTable = $("#operateRecordTable").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookCaseTag, { 
		
	});	
	
})(jQuery);
