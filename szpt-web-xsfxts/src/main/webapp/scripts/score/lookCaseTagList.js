$.lookCaseTagList = $.lookCaseTagList || {};

(function($) {
	
	"use strict"
	
	var fxcxTable;

	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		
		/**
		 * 案件类别清空事件
		 */
		$(document).on("select2:unselect", "#ajlbSelect", function(e) {
			$.select2.empty("#caseNature");
			$.select2.empty("#ejcaseNature");
			
		});
		
		/**
		 * 案件类别选择事件
		 */
		$(document).on("select2:select", "#ajlbSelect", function(e) {
			var caseType=$.select2.val("#ajlbSelect");
			var list = new Array();
			list=caseType;
			loadajxz(list,"#caseNature");
		});
		
		/**
		 * 案件性质清空事件
		 */
		$(document).on("select2:unselect", "#caseNature", function(e) {
			var caseNature=$.select2.val("#caseNature");
			var list = new Array();
			list=caseNature;
			if(list==null || list.size()==0){
				$.select2.empty("#ejcaseNature");
			}else if(list.size()>0){
				loadajxz(list,"#ejcaseNature");
			}
		});
		
		/**
		 * 案件性质选择事件
		 */
		$(document).on("select2:select", "#caseNature", function(e) {
			var caseNature=$.select2.val("#caseNature");
			var list = new Array();
			list=caseNature;
			loadajxz(list,"#ejcaseNature");
		});
		
		/**
		 * 分析结果查询导出EXCEL
		 */
		$(document).on('click','#fxjgExcel',function(){
			var d= new Object();
			queryCondition(d);
			var form = $.util.getHiddenForm(context +'/caseAnalysis/exportExcelByCaseTagList.action', d);
			$.util.subForm(form);
		});	
		
		/**
		 * 查询
		 */
		$(document).on("click" , ".query", function(e){
			fxcxTable.draw(true);
		});
		
		/**
		 * 重置
		 */
		$(document).on("click" , ".search", function(e){
			$("#ajcode").val("");
			$.select2.val("#ajlbSelect","");
			$.select2.val("#caseNature","");
			$("#caseNature").empty();
			$.select2.val("#caseNature","");
			$.select2.val("#ejcaseNature","");
			$("#ejcaseNature").empty();
			$.select2.val("#zatdSelect", "");
			$.select2.val("#choosecs", "");
			$.select2.val("#zasdSelect", "");
			$.select2.val("#zarsSelect", "");
			$.select2.val("#zajkSelect", "");
			$.select2.val("#zackSelect", "");
			$.szpt.util.businessData.destroyTree("countryTree");
			$.szpt.util.businessData.initCjTree("countryTree");
			fxcxTable.draw(true);
		});
		
		/**
		 * 比对
		 */
		$(document).on("click" , "#comparison", function(e){
			var list = $.icheck.getChecked("fxjg");
			if($.util.exist(list) && list.length > 1){
				 var caseCodes = new Array();
				for(var i=0;i<list.length;i++){
					caseCodes.push($(list[i]).val());
				}
				var obj = new Object();
				$.util.objToStrutsFormData(caseCodes,"caseCodes",obj);
				var form = $.util.getHiddenForm(context +'/caseAnalysis/showCaseTagComparePage.action', obj);
				$.util.subForm(form);
			}else {
				$.util.topWindow().$.layerAlert.alert({msg:"请选择两个以上案件进行对比。",icon:0,time:3000});
			}
		});
		
		/**
		 * 串并案分析
		 */
		$(document).on("click" , "#cbaAnalyse", function(e){
			 var array = $.icheck.getChecked("fxjg");
			if(array.length != 1){
				$.util.topWindow().$.layerAlert.alert({icon:0,msg:"请选择一个案件。",time:3000}) ;
				return ;
			}
			var caseCode = $(array[0]).val();
			$.util.topWindow().location.href = context + "/caseAnalysis/showCaseAnalysisPage.action?clickOrder=4&&clickListOrder=1&&caseCode=" + caseCode;
		});
		
	});
	
	/**
	 * 初始化页面查询条件字典
	 */
	function init() {
		$.szpt.util.businessData.initAjlbSelect("#ajlbSelect");
		$.szpt.util.businessData.initZasdSelect("#zasdSelect");
		$.szpt.util.businessData.initZarsSelect("#zarsSelect");
		$.szpt.util.businessData.initZajckSelect("#zajkSelect");
		$.szpt.util.businessData.initZajckSelect("#zackSelect");
		$.szpt.util.businessData.initZatdSelect("#zatdSelect");
		$.szpt.util.businessData.initFacsSelect("#choosecs");
		$.szpt.util.businessData.initCjTree("countryTree");
		initCgtbTable();
	}
	
	/**
	 * 案件性质查询下级字典项
	 */
	function loadajxz(list,id){
		var ajxz=$.common.DICT.DICT_TYPE_AJXZ;
		var obj = new Object();
		obj.dictionaryType=ajxz;
		$.util.objToStrutsFormData(list,"dictionaryItemCodeList",obj);
		$.ajax({
			url: context + '/findAllSubDictionaryItemsByParentItemCodeAndTypeCodeList.action',
			type:"POST",	
			data:obj,
			customizedOpt:{
				//设置是否loading
				ajaxLoading:true,
			},
			dataType:"json",
			success:function(data){
				$.select2.addByList(id,data.dictionaryItemLst,"code","name",true,true);
			}
		});
	}
	
	/**
	 * 获取查询条件的值
	 */
	function queryCondition(d){
		if($.common.isFullConditionSearch()){
			var data = {
				"caseCode":$("#ajcode").val(),
				"type":$.szpt.util.businessData.getSeletedBySelector("#ajlbSelect"),
				"firstSorts":$.szpt.util.businessData.getSeletedBySelector("#caseNature"),
				"secondSorts":$.szpt.util.businessData.getSeletedBySelector("#ejcaseNature"),
				"features":$.szpt.util.businessData.getSeletedBySelector("#zatdSelect"),
				"occurPlace":$.szpt.util.businessData.getSeletedBySelector("#choosecs"),
				"period":$.szpt.util.businessData.getSeletedBySelector("#zasdSelect"),
				"peopleNum":$.szpt.util.businessData.getSeletedBySelector("#zarsSelect"),
				"entrance":$.szpt.util.businessData.getSeletedBySelector("#zajkSelect"),
				"exit":$.szpt.util.businessData.getSeletedBySelector("#zackSelect"),
				"communitys":$.szpt.util.businessData.getCheckedByTree("countryTree")
			};
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		}else{
			var data = {
				"caseCode":$("#fuzzyQuery").val() == "案件编号模糊查询" ? "" : $("#fuzzyQuery").val(),
			};
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		}
	}
	
	/**
	 * 初始化两抢一盗案串并案分析结果查询Table
	 */
	function initCgtbTable() {
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/caseAnalysis/queryCaseTagListByCondition.action";
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "5%",
					"title" : "序号",
					"className" : "table-checkbox",
					"data" : "caseCode",
					"render" : function(data, type, full, meta) {
						var a = '<input type="checkbox" name="fxjg" class="icheckbox" value="'+data+'"/>';
						a += '&nbsp;&nbsp;' + (meta.row + 1);
						return a;
					}
				}, {
					"targets" : 1,
					"width" : "10%",
					"title" : "案件编号",
					"data" : "caseCode",
					"render" : function(data, type, full, meta) {
						return '<a href="javascript:void(0);" caseCode="' + full.caseCode + '">' + data + '</a>';
					}
				}, {
					"targets" : 2,
					"width" : "15%",
					"title" : "案件名称",
					"data" : "caseName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 3,
					"width" : "5%",
					"title" : "案件类别",
					"data" : "typeName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 4,
					"width" : "5%",
					"title" : "案件性质",
					"data" : "firstSortName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 5,
					"width" : "5%",
					"title" : "二级案件性质",
					"data" : "secondSortName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 6,
					"width" : "10%",
					"title" : "发案时间",
					"data" : "caseTimeStart",
					"render" : function(data, type, full, meta) {
						var td = $.date.timeToStr(data, "yyyy-MM-dd HH:mm") ;
						return td;
					}
				}, {
					"targets" : 7,
					"width" : "15%",
					"title" : "作案特点",
					"data" : "featureNames",
					"render" : function(data, type, full, meta) {
						return data;
					}

				}, {
					"targets" : 8,
					"width" : "5%",
					"title" : "选择处所",
					"data" : "occurPlaceName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 9,
					"width" : "5%",
					"title" : "作案时段",
					"data" : "periodName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 10,
					"width" : "5%",
					"title" : "作案人数",
					"data" : "peopleNumName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 11,
					"width" : "5%",
					"title" : "作案进口",
					"data" : "entranceName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 12,
					"width" : "5%",
					"title" : "作案出口",
					"data" : "exitName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 13,
					"width" : "5%",
					"title" : "发案社区",
					"data" : "communityName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				} ];
		// 是否排序
		tb.ordering = false;
		// 每页条数
		tb.lengthMenu = [ 10 ];
		// 默认搜索框
		tb.searching = false;
		// 能否改变lengthMenu
		tb.lengthChange = false;
		// 自动TFoot
		tb.autoFooter = false;
		// 自动列宽
		tb.autoWidth = false;
		// 请求参数
		tb.paramsReq = function(d, pagerReq) {
			queryCondition(d);
		};
		tb.paramsResp = function(json) {
			var ctbs = json.resultMap.ctbs;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = ctbs;

		};
		tb.rowCallback = function(row, data, index) {

		};
		fxcxTable = $("#analyseResult").DataTable(tb);
	}

	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookCaseTagList, {

	});
})(jQuery);
