$.caseTagList = $.caseTagList || {} ;

(function($){
	
	"use strict";
	
	var table;
	
	$(document).ready(function(){
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$.szpt.util.noData.initDictionaryTree("ajlbTree",false,$.common.DICT.DICT_TYPE_V3AJLB);
		$.szpt.util.noData.initDictionaryTree("ajxzTree",false,$.common.DICT.DICT_TYPE_V3AJXZ);
		
		/**
		 * 打标按钮
		 */
		$(document).on("click", ".mark", function(e){
			var id = $(this).attr("caseCode");
			var data = $(this).parents("tr").data("data");
			if($.util.isBlank(data.caseTagId)){
				window.location.href = $.util.fmtUrl(context+"/caseTag/showNewCaseTagPage.action?id="+id);
			}else{
				window.location.href = $.util.fmtUrl(context+"/caseTag/showModifyCaseTagPage.action?id="+id);
			}
			return false;//阻止事件冒泡，以免触发tr点击事件
		});
		
		/**
		 * 查看打标详情
		 */
		$(document).on("click","#table tbody tr",function(){
			var id = $(this).data("data").caseCode;
			window.location.href = $.util.fmtUrl(context+"/caseTag/showLookCaseTagPage.action?id="+id);
		});
		
		/**
		 * 发案辖区选择事件
		 */
		$(document).on("select2:select","#pcsSelect",function(){
			var pcsCode = $.select2.val("#pcsSelect");
			findCommunityByPcs(pcsCode);
		});
		
		/**
		 * 发案辖区选择事件
		 */
		$(document).on("select2:unselect","#pcsSelect",function(){
			$.select2.empty("#countrySelect");
		});
		
		/**
		 * 查询按钮
		 */
		$(document).on("click" , ".search", function(e){
			table.draw(true);
		});
		
		/**
		 * 重置
		 */
		$(document).on("click" , "#reset", function(e){
			$("#name").val("");
			$("#code").val("");
			$("#address").val("");
			$.select2.clear("#ajlbSelect");
			$.select2.clear("#pcsSelect");
			$.select2.clear("#countrySelect");
			$.select2.clear("#markState");
			$.select2.clear("#tagState");
			$.laydate.reset("#dateRange");
			$.szpt.util.businessData.destroyTree("ajxzTree");
			$.szpt.util.noData.initDictionaryTree("ajxzTree",false,$.common.DICT.DICT_TYPE_V3AJXZ);
			$.szpt.util.businessData.destroyTree("ajlbTree");
			$.szpt.util.noData.initDictionaryTree("ajlbTree",false,$.common.DICT.DICT_TYPE_V3AJLB);
			table.draw(true);
		});
	});
	
	/**
	 * 初始化查询条件
	 */
	function init(){
		$.szpt.util.businessData.initPcsSelect("#pcsSelect");
		$.szpt.util.businessData.initDbztSelect("#tagState");
		initTable();
	}
	
	/**
	 * 根据派出所查询村居
	 * @param pcsCode 派出所code
	 */
	function findCommunityByPcs(pcsCode){
		$.select2.empty("#countrySelect");
		var data = {
			"pcsCode":pcsCode
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/caseTag/findCommunityByPcs.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				$.select2.addByList("#countrySelect",resultMap.countrys,"code","name",true,true);
			}
		});
	}
	
	/**
	 * 初始化table
	 */
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/caseTag/queryCaseTagList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "3%",
         	    	"title": "序号",
         	    	"className":"table-checkbox",
         	    	"data": "caseCode" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return meta.row+1;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "14%",
					"title" : "案件编号",
					"data" : "caseCode",
					"render" : function(data, type, full, meta) {
						return '<a href="javascript:void(0);" class="showMarkDetail" caseCode="' + full.caseCode + '">' + data + '</a>';
					}
				},
				{
					"targets" : 2,
					"width" : "13%",
					"title" : "案件名称",
					"data" : "caseName",
					"render" : function(data, type, full, meta) {
						return '<a href="javascript:void(0);" class="showMarkDetail" caseCode="' + full.caseCode + '">' + data + '</a>';
					}
				},
				{
					"targets" : 3,
					"width" : "8%",
					"title" : "案件类别",
					"data" : "caseSortName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "8%",
					"title" : "案件性质",
					"data" : "caseKindName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "8%",
					"title" : "案件状态",
					"data" : "caseStateName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 6,
					"width" : "10%",
					"title" : "发现时间",
					"data" : "discoverTimeStart",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 7,
					"width" : "10%",
					"title" : "发案地点",
					"data" : "caseAddress",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 8,
					"width" : "15%",
					"title" : "简要案情",
					"data" : "details",
					"render" : function(data, type, full, meta) {
						data = $.trim(data);
						var p = data.length>30?data.substring(0,30):data;
						return '<p title="'+data+'">'+p+'...</p>';
					}
				},
				{
					"targets" : 9,
					"width" : "10%",
					"title" : "办案单位",
					"data" : "handleUnit",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 10,
					"width" : "10%",
					"title" : "操作",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return '<button caseCode="'+full.caseCode+'"class="mark btn btn-sm btn-primary">打标</button>';
					}
				}
			];
			//是否排序
			tb.ordering = false ;
			//每页条数
			tb.lengthMenu = [ 10 ];
			//默认搜索框
			tb.searching = false ;
			//能否改变lengthMenu
			tb.lengthChange = false ;
			//自动TFoot
			tb.autoFooter = false ;
			//自动列宽
			tb.autoWidth = false ;
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				if($.common.isFullConditionSearch()){
					var data = {
						"caseCode":$("#code").val(),
						"caseName":$("#name").val(),
						"caseAddress":$("#address").val(),
						"caseSort":$.szpt.util.businessData.getSeletedBySelector("#ajlbSelect"),
						"caseKind":$.szpt.util.businessData.getSelectByTree("ajxzTree"),
						"regionCode":$.szpt.util.businessData.getSeletedBySelector("#pcsSelect"),
						"communityCode":$.szpt.util.businessData.getSeletedBySelector("#countrySelect"),
						"discoverTimeStart":$.laydate.getTime("#dateRange", "start"),
						"discoverTimeEnd":$.laydate.getTime("#dateRange", "end"),
						"tagState":$.select2.val("#tagState"),
						"ifSolved":null //$.common.DICT.DICT_NO
					};
					var queryStr = $.util.toJSONString(data);
					$.util.objToStrutsFormData(queryStr,"queryStr",d);
				}else{
					var data = {
						"caseName" : $("#mohu").val() == "案件名称模糊查询" ? "" : $("#mohu").val(),
						"tagState" : $.common.DICT.DICT_TAG_NO,
						"ifSolved":null //$.common.DICT.DICT_NO
					};
					var queryStr = $.util.toJSONString(data);
					$.util.objToStrutsFormData(queryStr,"queryStr",d);
				}
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.cbcbs;
			};
			tb.rowCallback = function(row,data, index) {
				$(row).data("data",data);
			};
			table = $("#table").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseTagList, { 
		
	});	
	
})(jQuery);	