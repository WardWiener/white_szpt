$.szpt = $.szpt || {} ;
$.szpt.fullSearch = $.szpt.fullSearch || {} ;
$.szpt.fullSearch.commandMore = $.szpt.fullSearch.commandMore || {} ;
(function($){
	"use strict";
	var commandTable = null ;
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$("#content").val(title);	//title是在jsp页面中获取的，上个页面传过来的参数
		$('#create_start').val($.date.timeToStr(parseInt(startTime), "yyyy-MM-dd HH:mm:ss"));
		$('#create_end').val($.date.timeToStr(parseInt(endTime),"yyyy-MM-dd HH:mm:ss"));
		$.common.intiSelectUnitTree();//初始化单位树 ajglUtil.js
		commandTableInitOrRefresh();
	});
	/**
	 * 查询
	 */
	$(document).on("click","#query",function(){
		commandTableInitOrRefresh();
	});
	/**
	 * 重置
	 */
	$(document).on("click","#reset",function(){
		$("#content").val("");
		$("#commandType").val("");
		$("#unit").val("");
		$.laydate.reset("#requireDate");
		$.laydate.reset("#createDate");
	});
	
	/**
	 * 初始化表格
	 */
	function commandTableInitOrRefresh(){
		if(commandTable!=null){
			commandTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchCommand.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "",
				"title" : "创建时间",
				"data" : "createtime",
				"render" : function(data, type, full, meta) {
	    			  return data;
				}
			},
			{
				"targets" : 1,
				"width" : "",
				"title" : "指令内容",
				"data" : "content",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "",
				"title" : "关联内容",
				"data" : "reletedcontent",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "",
				"title" : "指令类型",
				"data" : "type",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "",
				"title" : "要求反馈时间",
				"data" : "askfeedbacktime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 5,
				"width" : "",
				"title" : "下发单位",
				"data" : "sendunit",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.paging = [5];
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
		tb.info = false ;
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#content").val();
			d["startTime"] = $.laydate.getTime("#createDate","start");
			d["endTime"] = $.laydate.getTime("#createDate","end");
			d["type"] = $("#commandType").find("option:selected").text();
			if($("#unitTree").val()!=''){
				d["receiveunit"] = $("#unitTree").val();
			}
			d["requireDateStartTime"] = $.laydate.getTime("#requireDate","start");
			d["requireDateEndTime"] = $.laydate.getTime("#requireDate","end");
		};
		tb.paramsResp = function(json) {
			var commandBeanList = json.commandBeanList;
			json.data = commandBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#commandTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//行单击事件
			$(row).on("click",function(){
				showInstructionPage(data);
			});
			
		};
		commandTable = $("#commandTable").DataTable(tb);
	}
	
	function showInstructionPage(data){
		var id = data.id;
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/instruction/showFindInstructionPage.action',
			pageLoading : true,
			title:"查看指令详情",
			width : "870px",
			height : "500px",
			btn:["返回"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			id : id
    		},
    		end:function(){
    		}
		});
	}
	function init() {
		$.szpt.util.businessData.iniOnetDwTree("unitTree");
	}
})(jQuery);