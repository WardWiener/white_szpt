/**
 * 全文检索首页
 */
$.szpt = $.szpt || {} ;
$.szpt.fullSearch = $.szpt.fullSearch || {} ;
(function($){
	"use strict";
	$(document).ready(function() {	
		
		$.laydate.setRange($.date.timeToStr(new Date().getTime(),"yyyy-MM-dd 00:00:00"), $.date.timeToStr(new Date().getTime(),"yyyy-MM-dd HH:mm:ss"), "#messageDate", "yyyy-MM-dd HH:mm:ss");
		initSearch();
		/**
		 * 首页的搜索按钮
		 */
		$(document).on("click","#search",function(){
			if($.util.isBlank($.laydate.getTime("#messageDate","start"))||$.util.isBlank($.laydate.getTime("#messageDate","end"))){
				window.top.$.layerAlert.alert({msg:"查询时间不完整！"}) ;
				return;
			}
			if($.util.isEmpty($("#infoInput").val())){
				location.reload(true);
				return;
			}
			searchData();
			$("#indexTitle").hide();
			$("#infoTitle").show();
		});
		/**
		 * 返回高级查询按钮
		 */
		$(document).on("click","#back",function(){
			 $("#infoTitle").hide();
			 $("#indexTitle").show();
		});
		
		$("#search-advanced-btn").click(function() {
		    $("#search-advanced-query").slideToggle(500);
		}); 
		
		/**
		 * 首页的搜索输入框，按键弹起 内容改变事件
		 */
		$(document).on("keyup ","#indexInput",function(){
			$("#infoInput").val($("#indexInput").val());
			$("#searchInput").val($("#indexInput").val());
		});
		
		/**
		 * 首页的搜索输入框，按键弹起 内容改变事件
		 */
		$(document).on("keyup ","#searchInput",function(){
			$("#infoInput").val($("#searchInput").val());
			$("#indexInput").val($("#searchInput").val());
		});
		
		/**
		 * 点完搜索后，出现的输入框，按键弹起 内容改变事件
		 */
		$(document).on("keyup ","#infoInput",function(){
			$("#searchInput").val($("#infoInput").val());
			$("#indexInput").val($("#infoInput").val());
		});
		selectAllCategory();
		/**
		 * 点完搜索后，出现的搜索按钮
		 */
		$(document).on("click","#query",function(){
			searchData();
		});
	});
	
	//点击人员类别中的全部选型时
	$(document).on("ifChecked", "#allCategory", function(e){		
		$.each( $("#category .icheckbox"), function(e,m){
			if($(m).attr("id") != "allRylb"){
				$(m).iCheck('check');
			}
		} ); 
	});
	//点击全部取消选中
	$(document).on("ifUnchecked", "#allCategory", function(e){		
		$.each( $("#category .icheckbox"), function(e,m){
				$(m).iCheck('uncheck');
		} ); 
	});
	//点击类别(非全部)
	$(document).on("ifUnchecked", ".category", function(e){
		var obj = this;
		if($("#allCategory")[0].checked){
			$("#allCategory").iCheck('uncheck');
			$.each( $(".category"), function(e,m){
				if(m !=obj){
					$(m).iCheck('check');
				}
			});
		}
	})
	
	function selectAllCategory(){
		$("#allCategory").iCheck('check');
	}
	
	//查询所有类别选中项
	function findAllCategory(){
		var data = [];
		$.each( $(".category"), function(){
			if(this.checked){
				data.push($(this).attr("valName"))
			}
		} ) 
		return data;
	}
	
	/**
	 * 搜索方法
	 */
	function searchData(){
		var categorys = findAllCategory();
		if(!categorys || categorys.length <=0){
			$.szpt.fullSearch.event.eventTableInitOrRefresh();
		    $.szpt.fullSearch.cases.casesTableInitOrRefresh();
		    $.szpt.fullSearch.highriskperson.highriskpersonInitOrRefresh();
		    $.szpt.fullSearch.site.siteTableInitOrRefresh();
		    $.szpt.fullSearch.command.commandTableInitOrRefresh();
		    $.szpt.fullSearch.clue.clueTableInitOrRefresh();
		    $("#inputResult").text("“"+$("#searchInput").val()+"”");
		}else{
			for(var i in categorys){
				if(categorys[i] == "jq"){
					$.szpt.fullSearch.event.eventTableInitOrRefresh();
				}else if(categorys[i] == "aj"){
					 $.szpt.fullSearch.cases.casesTableInitOrRefresh();
				}else if(categorys[i] == "gwr"){
					  $.szpt.fullSearch.highriskperson.highriskpersonInitOrRefresh();
				}else if(categorys[i] == "cs"){
					 $.szpt.fullSearch.site.siteTableInitOrRefresh();
				}else if(categorys[i] == "qbxs"){
				    $.szpt.fullSearch.command.commandTableInitOrRefresh();
				}else if(categorys[i] == "zl"){
				    $.szpt.fullSearch.command.commandTableInitOrRefresh();
				}
			}
			$("#inputResult").text("“"+$("#searchInput").val()+"”");
		}
	    
	   
	}
	
	/**
	 * 更多 点击事件
	 */
	$(document).on("click",".btn-link",function(){
		var dataId = $(this).attr("id");
		if(dataId == "eventMore"){	//警情 更多
			location.href = context + "/fullsearch/showEventMore.action?title="+$("#infoInput").val()+"&&startTime="+$.laydate.getTime("#messageDate","start")+"&&endTime="+$.laydate.getTime("#messageDate","end");
		}else if(dataId == "commandMore"){	//指令 更多
			location.href = context + "/fullsearch/showCommandMore.action?title="+$("#infoInput").val()+"&&startTime="+$.laydate.getTime("#messageDate","start")+"&&endTime="+$.laydate.getTime("#messageDate","end");
		}else if(dataId == "clueMore"){		//线索 更多
			location.href = context + "/fullsearch/showCuleMore.action?title="+$("#infoInput").val()+"&&startTime="+$.laydate.getTime("#messageDate","start")+"&&endTime="+$.laydate.getTime("#messageDate","end");
		}
	});
	
	function initSearch(){
		if(search != null && search != undefined && search != 'null' && search != ''){
			$("#infoInput").val(search);
			console.log($("#infoInput").val());
			infoTitleShow();
			searchData();
			 $("#inputResult").text("“"+search+"”");
			 $("#indexInput").val(search);
		}
	}
	
	function infoTitleShow(){
		$("#indexTitle").hide();
		$("#infoTitle").show();
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch, { 
		searchData:searchData
	});	
})(jQuery);

/**
 * 警情
 */
$.szpt.fullSearch.event = $.szpt.fullSearch.event || {} ;

(function($){
	"use strict";
	var eventTable = null;
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
				"width" : "40%",
				"title" : "警情名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "30%",
				"title" : "警情类型",
				"data" : "type",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "30%",
				"title" : "接警时间",
				"data" : "answertime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false;
		//分页是否
		tb.paging = true; 
		//默认搜索框
		tb.searching = false;
		//是否显示loading效果
		tb.bProcessing = true;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
		};
		tb.paramsResp = function(json) {
			var eventBeanList = json.eventBeanList;
			json.data = eventBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#eventTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
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
				jqId : id
			},
			end:function(){
				
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.event, { 
		eventTableInitOrRefresh:eventTableInitOrRefresh
	});	
})(jQuery);

/**
 * 案件
 */
$.szpt.fullSearch.cases = $.szpt.fullSearch.cases || {} ;

(function($){
	"use strict";
	var casesTable = null;
	
	function casesTableInitOrRefresh(){
		if(casesTable!=null){
			casesTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchCases.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "40%",
				"title" : "案件名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "30%",
				"title" : "案由类别",
				"data" : "type",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "30%",
				"title" : "案发时间",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//分页是否
		tb.paging = true;
		//是否显示loading效果
		tb.bProcessing = true;
		//默认搜索框
		tb.searching = false;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
		};
		tb.paramsResp = function(json) {
			var casesBeanList = json.casesBeanList;
			json.data = casesBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#casesTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
			$(row).on("click",function(){
				sessionStorage.setItem("yaydDetailBackUrl", window.location.href + "?caseCode=" + data.id);
				window.top.location.href = context + "/yayd/showYaydDetailPage.action?clickOrder=0&&clickListOrder=0&&id=" + data.id ;
			});
		};
		casesTable = $("#casesTable").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.cases, { 
		casesTableInitOrRefresh:casesTableInitOrRefresh
	});	
})(jQuery);

/**
 * 高危人
 */
$.szpt.fullSearch.highriskperson = $.szpt.fullSearch.highriskperson || {} ;

(function($){
	"use strict";
	var highriskpersonTable = null ;
	
	
	function highriskpersonInitOrRefresh(){
		if(highriskpersonTable!=null){
			highriskpersonTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchHighriskperson.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "10%",
				"title" : "姓名",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "20%",
				"title" : "性别",
				"data" : "sex",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "20%",
				"title" : "前科类型",
				"data" : "qianKeType",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "15%",
				"title" : "人员类别",
				"data" : "persontypeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "20%",
				"title" : "预警类别",
				"data" : "alertlevel",
				"render" : function(data, type, full, meta) {
					var alertlevel = "";
					if(data == "0"){
						alertlevel += '<span class="btn warningType sq-red" title="红色"></span>';
					}else if(data == "1"){
						alertlevel += '<span class="btn warningType sq-orange" title="橙色"></span>';
					}else if(data == "2"){
						alertlevel += '<span class="btn warningType sq-yellow" title="黄色"></span>';
					}else if(data == "3"){
						alertlevel += '<span class="btn warningType sq-white" title="白色"></span>';
					}else if(data == "4"){
						alertlevel += '<span class="btn warningType sq-blue" title="蓝色"></span>';
					}else if(data == "5"){
						alertlevel += '<span class="btn warningType sq-other" title="其他"></span>';
					}
					return alertlevel;
				}
			}
		];
		//是否排序
		tb.ordering = false;
		//分页是否
		tb.paging = true; 
		//默认搜索框
		tb.searching = false;
		//是否显示loading效果
 		tb.bProcessing = true;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
		};
		tb.paramsResp = function(json) {
			var highriskpersonBeanList = json.highriskpersonBeanList;
			json.data = highriskpersonBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#highriskpersonTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
			$(row).on("click",function(){
				if(data.type=="是" || data.sfsXyy=="1"){
					sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
					window.top.location.href = context + "/yryd/showYrydHighriskPersonAlertPage.action?clickOrder=1&&clickListOrder=0&&idcard="+data.idcard;
				}else{
					sessionStorage.setItem("yrydOrdinaryPersonDetailBackUrl", window.location.href);
					window.top.location.href = context + "/yryd/showYrydOrdinaryPersonPage.action?clickOrder=1&&clickListOrder=0&&idcard="+data.idcard;
				}
			});
			
		};
		highriskpersonTable = $("#highriskpersonTable").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.highriskperson, { 
		highriskpersonInitOrRefresh:highriskpersonInitOrRefresh
	});	
})(jQuery);

/**
 * 场所
 */
$.szpt.fullSearch.site = $.szpt.fullSearch.site || {} ;

(function($){
	"use strict";
	
	var siteTable = null ;
	
	function siteTableInitOrRefresh(){
		if(siteTable!=null){
			siteTable.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/fullsearch/searchSite.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "40%",
				"title" : "场所名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "30%",
				"title" : "场所类别",
				"data" : "siteType",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "30%",
				"title" : "预警等级",
				"data" : "alertLevel",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false;
		//分页是否
		tb.paging = true; 
		//默认搜索框
		tb.searching = false;
		//是否显示loading效果
		tb.bProcessing = true;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
		};
		tb.paramsResp = function(json) {
			var siteBeanList = json.siteBeanList;
			json.data = siteBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#siteTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
			$(row).on("click",function(){
				
				 alert("查看详情");
			});
			
		};
		siteTable = $("#siteTable").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.site, { 
		siteTableInitOrRefresh:siteTableInitOrRefresh
	});	
})(jQuery);

/**
 * 指令
 */
$.szpt.fullSearch.command = $.szpt.fullSearch.command || {} ;

(function($){
	"use strict";
	
	var commandTable = null ;
	
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
				"width" : "40%",
				"title" : "指令内容",
				"data" : "content",
				"render" : function(data, type, full, meta) {
						var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "30%",
				"title" : "指令类型",
				"data" : "type",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "30%",
				"title" : "创建时间",
				"data" : "createtime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false;
		//分页是否
		tb.paging = true; 
		//默认搜索框
		tb.searching = false;
		//是否显示loading效果
		tb.bProcessing = true;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
		};
		tb.paramsResp = function(json) {
			var commandBeanList = json.commandBeanList;
			json.data = commandBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			$("#commandTd").text(json.totalNum);
		};
		tb.rowCallback = function(row,data, index) {
			//单击事件
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
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.command, { 
		commandTableInitOrRefresh:commandTableInitOrRefresh
	});	
})(jQuery);

/**
 * 线索
 */
$.szpt.fullSearch.clue = $.szpt.fullSearch.clue || {} ;

(function($){
	"use strict";
	
	var clueTable = null ;
	
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
				"width" : "40%",
				"title" : "线索标题",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					var d = data.replace(eval("/"+$("#infoInput").val()+"/g"),"<span style='color:red;'>"+$("#infoInput").val()+"</span>"); 
	    			  return d;
				}
			},
			{
				"targets" : 1,
				"width" : "30%",
				"title" : "事件状态",
				"data" : "state",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "30%",
				"title" : "填报时间",
				"data" : "date",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			}
		];
		//是否排序
		tb.ordering = false;
		//分页是否
		tb.paging = true; 
		//默认搜索框
		tb.searching = false;
		//是否显示loading效果
		tb.bProcessing = true;
		//能否改变lengthMenu
		tb.lengthChange = false;
		//自动TFoot
		tb.autoFooter = false;
		//自动列宽
		tb.autoWidth = false;
		tb.lengthMenu = [ 5 ]; //每页条数
		tb.info = true; //是否显示详细信息
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			d["title"] = $("#infoInput").val();
			d["startTime"] = $.laydate.getTime("#messageDate","start");
			d["endTime"] = $.laydate.getTime("#messageDate","end");
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
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.fullSearch.clue, { 
		clueTableInitOrRefresh:clueTableInitOrRefresh
	});	
})(jQuery);