$.szpt = $.szpt || {} ;
$.szpt.fullSearch = $.szpt.fullSearch || {} ;
$.szpt.fullSearch.clueMore = $.szpt.fullSearch.clueMore || {} ;
(function($){
	"use strict";
	var clueTable = null ;
	
	$(document).ready(function() {	
		$("#key").val(title);	//title是在jsp页面中获取的，上个页面传过来的参数
		
		clueTableInitOrRefresh();
		/**
		 * 查询
		 */
		$(document).on("click","#query",function(){
			clueTableInitOrRefresh();
		});
		/**
		 * 重置
		 */
		$(document).on("click","#reset",function(){
			$("#key").val("");
			$("#clueSource").val("");	//TODO 更改线索来源数据源
			$("#values").val("");
		});
	});
	
	var index = 1 ; //序号自增
	
	function clueTableInitOrRefresh(){
		if(clueTable!=null){
			clueTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchClue.action";
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
				"title" : "线索内容",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 2,
				"width" : "",
				"title" : "线索来源",
				"data" : "state",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 3,
				"width" : "",
				"title" : "关联案事件/高危人/重点场所",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			},
			{
				"targets" : 4,
				"width" : "",
				"title" : "推送时间",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			},
			{
				"targets" : 5,
				"width" : "",
				"title" : "推送原因",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
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
			d["title"] = $("#key").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
			d["type"] = $("#dimJqlxId").val();
		};
		tb.paramsResp = function(json) {
			var clueBeanList = json.clueBeanList;
			json.data = clueBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#clueTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
			$(row).on("click",function(){
				// TODO
				 alert("查看详情");
			});
			
		};
		clueTable = $("#clueTable").DataTable(tb);
	}
	
	
})(jQuery);