$.szpt = $.szpt || {} ;
$.szpt.fullSearch = $.szpt.fullSearch || {} ;
$.szpt.fullSearch.eventMore = $.szpt.fullSearch.eventMore || {} ;
(function($){
	"use strict";
	
	var eventTable = null;
	
	$(document).ready(function() {	
		$("#key").val(title);	//title是在jsp页面中获取的，上个页面传过来的参数
		$('#alertTime_start').val($.date.timeToStr(parseInt(startTime), "yyyy-MM-dd HH:mm:ss"));
		$('#alertTime_end').val($.date.timeToStr(parseInt(endTime),"yyyy-MM-dd HH:mm:ss"));
		$.szpt.util.noData.initJqlxTree("dimJqlxId",true);	//初始化警情树
		initDate($("#time").val());	//初始化时间
		eventTableInitOrRefresh();	//初始化表格
		/**
		 * 发生时间（今天，本周，本月，自定义）时间初始化
		 */
		$(document).on("change", "#time", function(){
			initDate($("#time").val());
		});
		/**
		 * 查询
		 */
		$(document).on("click","#query",function(){
			eventTableInitOrRefresh();
		});
		/**
		 * 重置
		 */
		$(document).on("click","#reset",function(){
			$.select2.val("#firstSelect","");
			$.select2.val("#secondSelect","");
			$.select2.val("#thidlySelect","");
			$("#key").val("");
			$.szpt.util.noData.destroyTree("dimJqlxId");//销毁树
			$.szpt.util.noData.initJqlxTree("dimJqlxId",true);	//重新初始化警情树
			//TODO 单位重置
			$.laydate.reset("#messageDate");
		});
	});

	
	/**
	 * 初始化时间
	 */
	function initDate(type){
		//方法来自：dateOperation.js
		var week_ = dateRangeUtil.getCurrentWeek();
		var month_ = dateRangeUtil.getCurrentMonth();
		if(type == "today"){	//今天
			var now = new Date();
			$.laydate.setRange($.date.dateToStr(now,"yyyy-MM-dd 00:00:00"), $.date.dateToStr(now,"yyyy-MM-dd HH:mm:ss"), "#messageDate", "yyyy-MM-dd HH:mm:ss");
		}
		if(type == "week"){		//本周
			$.laydate.setRange($.date.dateToStr(week_[0],"yyyy-MM-dd 00:00:00"), $.date.dateToStr(week_[1],"yyyy-MM-dd 23:59:59"), "#messageDate", "yyyy-MM-dd HH:mm:ss");
		}
		if(type == "month"){	//本月
			$.laydate.setRange($.date.dateToStr(month_[0],"yyyy-MM-dd 00:00:00"), $.date.dateToStr(month_[1],"yyyy-MM-dd 23:59:59"), "#messageDate", "yyyy-MM-dd HH:mm:ss");
		}
		if(type == "zdy"){		//自定义
			$.laydate.reset("#messageDate");
		}
	}
	
	var index = 1;	//表格中的序号
	/**
	 * 初始化表格
	 */
	function eventTableInitOrRefresh(){
		if(eventTable!=null){
			eventTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchEvent.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "5%",
				"title" : "序号",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					  return index++;
				}
			},
			{
				"targets" : 1,
				"width" : "",
				"title" : "警情名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 2,
				"width" : "",
				"title" : "警情类型",
				"data" : "type",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 3,
				"width" : "",
				"title" : "紧急程度",
				"data" : "level",
				"render" : function(data, type, full, meta) {
				//	return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
					return data
				}
			},
			{
				"targets" : 4,
				"width" : "",
				"title" : "发生地点",
				"data" : "occuraddress",
				"render" : function(data, type, full, meta) {
				//	return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
					return data
				}
			},
			{
				"targets" : 5,
				"width" : "",
				"title" : "发生时间",
				"data" : "occurtime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 6,
				"width" : "",
				"title" : "警情来源",
				"data" : "source",
				"render" : function(data, type, full, meta) {
				//	return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
					return data
				}
			},
			{
				"targets" : 7,
				"width" : "",
				"title" : "警情状态",
				"data" : "state",
				"render" : function(data, type, full, meta) {
//					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
					return data
				}
			},
			{
				"targets" : 8,
				"width" : "",
				"title" : "接警时间",
				"data" : "answertime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}/*,
			{
				"targets" : 9,
				"width" : "",
				"title" : "处警次数",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			},
			{
				"targets" : 10,
				"width" : "",
				"title" : "指令次数",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			}*/
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.paging = [10];
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
			d["title"] = $("#key").val();
			d["startTime"] = $.laydate.getTime("#alertsituationDate","start");
			d["endTime"] = $.laydate.getTime("#alertsituationDate","end");
			d["requireDateStartTime"] = $.laydate.getTime("#messageDate","start");
			d["requireDateEndTime"] = $.laydate.getTime("#messageDate","end");
			if($("#dimJqlxId").val()!=""){
				d["type"] = $("#dimJqlxId").val();
			}
		};
		tb.paramsResp = function(json) {
			var eventBeanList = json.eventBeanList;
			json.data = eventBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
		};
		tb.rowCallback = function(row,data, index) {
			//行单击事件
			$(row).on("click",function(){
				// TODO
				 alertShowDetail(data.id)
			});
			
		};
		eventTable = $("#eventTable").DataTable(tb);
	}
	function alertShowDetail(id){
		
		window.top.$.layerAlert.dialog({
			content : context +  '/zhzats/showJqDetail.action',
			pageLoading : true,
			title:"查看警情详情",
			width : "630px",
			height : "560px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				ajCode : id
			},
			end:function(){
				
			}
		});
	}
	
	
	
})(jQuery);