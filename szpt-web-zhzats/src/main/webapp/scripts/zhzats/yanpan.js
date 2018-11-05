$.zhzats = $.zhzats || {} ;
$.zhzats.yanpan = $.zhzats.yanpan || {} ;

$.yanpanList = $.yanpanList || {};
(function($) {
	"use strict"
	var xsjqTable;
	var feedbackCaseTable;
	var tableData=null;
	var dataList = [];
	
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
	});
	
	function init() {
		$.szpt.util.businessData.initCountrySelect("#cunjuSelect");
		$.szpt.util.businessData.destroyTree("jqlx");
		$.szpt.util.businessData.initJqlxsTree("jqlx");
		initXsjqTable();
	}
	function refreshWithoutMap(){
		$.zhzats.yanpan.instruction.initInstructionByYanpanTable(tableData);
		$.zhzats.yanpan.feeedBack.findYanpanResultReturnTable(tableData);
		$.zhzats.yanpan.jqVideoList.initjqVideoTable(tableData);
		initFeedbackCaseTable();
	}
	$(document).on("click" , "#findjqlx", function(e){
		$('#jqlx').trigger("click");
	});
	//查看警情详情
	$(document).on("click" , "#showJqDetail", function(e){
		if(tableData && tableData.id){
			alertShowDetail(tableData.id);
		}else{
			$.util.topWindow().$.layerAlert.alert({msg:"您没有选择任何一行警情",title:"提示"});
		}
	});
	
	function alertShowDetail(jqId){
		
		window.top.$.layerAlert.dialog({
			content : context +  '/yanpan/showJqDetail.action',
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
				jqId : jqId
			},
			end:function(){
				
			}
		});
	}
	//查询
	$(document).on("click" , ".query", function(e){
		xsjqTable.draw(true);
		$("#jqxx").hide();
		$("#zlxf").hide();
		$("#ypjgfk").hide();
		
	});
	//重置
	$(document).on("click" , ".reset", function(e){
		$("#keyword").val("");
		$("#occurPlace").val("");
		$.select2.val("#firstSelect","");
		$.select2.val("#secondSelect","");
		$.select2.val("#thidlySelect","");
		$.select2.val("#cunjuSelect","");
		$.laydate.reset("#dateRange");
		document.getElementById("yanpanState").selectedIndex=0;
		$.szpt.util.businessData.destroyTree("jqlx");
		$.szpt.util.businessData.initJqlxsTree("jqlx");
		xsjqTable.draw(true);
		$(".thidlySelect").hide();
	});
	//新增指令
	$(document).on("click" , "#newAddInstruction", function(e){
		$.zhzats.yanpan.instruction.newAddInstruction(tableData);	
	});
	//结果下发指令
	$(document).on("click" , ".resultSend", function(e){
		var id = $(this).attr("feedbackId");
		$.zhzats.yanpan.feeedBack.newAddInstruction(id);	
	});
	
	//处警单位选择设置
	$(document).on("select2:select","#firstSelect",function(){
		var tempName = $.szpt.util.businessData.getSeletedBySelector("#firstSelect");
		if(tempName=="派出所"){
			var type = "2";
			$.ajax({
				url:context +'/queryUnitAllByType.action',
				data:{queryStr:type},
				type:"post",
				dataType:"json",
				success:function(successData){
					var list = successData.resultMap.list;
					$("#thidlySelect").empty();
					$("#secondSelect").empty();
					$.select2.addByList("#secondSelect",list,"code","name",true,true);
					$.select2.addByList("#thidlySelect",[],"code","name",true,true);
					$(".thidlySelect").hide();
				}
			});
		}else {
			var type = "主格";
			$.ajax({
				url:context +'/findOrderCellByType.action',
				data:{queryStr:type},
				type:"post",
				dataType:"json",
				success:function(successData){
					var list = successData.resultMap.list;
					$("#thidlySelect").empty();
					$("#secondSelect").empty();
					$.select2.addByList("#secondSelect",list,"code","name",true,true);
					$.select2.addByList("#thidlySelect",[],"code","name",true,true);
					$(".thidlySelect").show();
				}
			});
		}
	});	
	
	$(document).on("select2:select","#secondSelect",function(){
		var code = $.szpt.util.businessData.getSeletedBySelector("#secondSelect");
		$.ajax({
			url:context +'/findOrderCellByCode.action',
			data:{queryStr:code},
			type:"post",
			dataType:"json",
			success:function(successData){
				var list = successData.resultMap.list;
				$("#thidlySelect").empty();
				$.select2.addByList("#thidlySelect",list,"code","name",true,true);
			}
		});
	});
	//获取查询条件的值
	function queryCondition(d){
		var pcsCode = $.szpt.util.businessData.getSeletedBySelector("#secondSelect");
		var thidlySelect = $.szpt.util.businessData.getSeletedBySelector("#thidlySelect");
		if($.util.exist(thidlySelect)){
			pcsCode = thidlySelect;
		}
		var jqlx = [];
		if( $.szpt.util.businessData.getCheckedByTree("jqlx") &&  $.szpt.util.businessData.getCheckedByTree("jqlx").length > 0 ){
			jqlx = $.szpt.util.businessData.getCheckedByTree("jqlx");
		}else{
			jqlx = ["01"];
		}
		var data = {
				"keyword":$("#keyword").val(),
				"addr":$("#occurPlace").val(),
				"yanpanState":$('#yanpanState option:selected').val(),
				"pcsCode":pcsCode,
				"countryCode":$.szpt.util.businessData.getSeletedBySelector("#cunjuSelect"),
				"jqlx":jqlx,
				"occurTimeStart":$.laydate.getTime("#dateRange", "start"),
				"occurTimeEnd":$.laydate.getTime("#dateRange", "end"),
				"start":d.start,
				"length":d.length
			};
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
	}
	//警情基本信息
	$(document).on("click" , "#xsjqTable tbody tr", function(e){
		tableData = $(this).data("factJq");
		$.each($("#xsjqTable tbody tr"), function(i,item){
			$(item).removeAttr("style");
		 }); 
		$(this).attr("style","background:#013;")
		var obj = new Object();
		obj.queryStr = tableData.id;
		$("#showJqDetail").data("jqDetail",tableData);
		$.ajax({
			url: context + '/yanpan/jqBasicMessageQuery.action',
			type:"POST",	
			data:obj,
			customizedOpt:{
				//设置是否loading
				ajaxLoading:true,
			},
			dataType:"json",
			success:function(data){
				$.validform.setFormTexts("#factJq", data.resultMap.factJq);
				$(".tabs-2").text("");
				$.validform.setFormTexts("#jqAnalyze", data.resultMap.jqAnalyze);
				$("#jqxx").show();
				$("#zlxf").show();
				$("#ypjgfk").show();
			}
		});
		refreshWithoutMap();
	});
	//刑事类警情
	function initXsjqTable() {
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/yanpan/xsjqYanpanQuery.action";
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "",
					"title" : "序号",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				},
				{
					"targets" : 1,
					"width" : "",
					"title" : "警情名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "",
					"title" : "警情类别",
					"data" : "jqlxName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "",
					"title" : "紧急程度",
					"data" : "urgencyLevel",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "",
					"title" : "发生地点",
					"data" : "addr",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "",
					"title" : "发生时间",
					"data" : "occurrenceTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				}, {
					"targets" : 6,
					"width" : "",
					"title" : "警情来源",
					"data" : "jqSource",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}];
		// 是否排序
		tb.ordering = false;
		// 是否分页
		tb.paging = true; 
		// 每页条数
		tb.lengthMenu = [ 5 ];
		// 默认搜索框
		tb.searching = false;
		// 能否改变lengthMenu
		tb.lengthChange = false;
		// 自动TFoot
		tb.autoFooter = false;
		// 自动列宽
		tb.autoWidth = false;
		//是否显示loading效果
		tb.bProcessing = true;
		// 请求参数
		tb.paramsReq = function(d, pagerReq) {
			queryCondition(d);
		};
		tb.paramsResp = function(json) {
			var cgtbs = json.resultMap.list;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = cgtbs;
		};
		tb.rowCallback = function(row, data, index) {
			$(row).data("factJq",data);
		};
		xsjqTable = $("#xsjqTable").DataTable(tb);
	}
	//反馈情况
	function initFeedbackCaseTable(){
		var jqId = null;
		if($.util.exist(tableData)){
			jqId = tableData.id;
		}
		$.ajax({
			url:context + "/yanpan/feedbackCaseQuery.action",
			data:{"queryStr":jqId},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				dataList = successData.resultMap.list;
				feedbackCase(dataList);
			}
		});
	}
	function feedbackCase(dataList){
		if($.util.exist(feedbackCaseTable)){
			feedbackCaseTable.destroy();
		}
		
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataList;
		st1.columnDefs = [{
				"targets" : 0,
				"width" : "6%",
				"title" : "反馈时间",
				"data" : "feedbackTime",
				"render" : function(data, type, full, meta) {
					return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				}
			},
             {
            	 "targets" : 1,
            	 "width" : "5%",
            	 "title" : "反馈单位",
            	 "data" : "fkOrderCellName",
            	 "render" : function(data, type, full, meta) {
            		 return data;
            	 }
             },
             {
            	 "targets" : 2,
            	 "width" : "6%",
            	 "title" : "反馈警情类型",
            	 "data" : "feedbackType",
            	 "render" : function(data, type, full, meta) {
            		 return data;
            	 }
             },
             {
            	 "targets" : 3,
            	 "width" : "5%",
            	 "title" : "反馈人",
            	 "data" : "fkPerson",
            	 "render" : function(data, type, full, meta) {
            		 return data;
            	 }
             },
             {
            	 "targets" : 4,
            	 "width" : "10%",
            	 "title" : "反馈内容",
            	 "data" : "content",
            	 "render" : function(data, type, full, meta) {
            		 return data;
            	 }
             }];
		st1.ordering = false;
		st1.paging = true; // 是否分页
		st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [10];
		st1.rowCallback = function(row,data, index) {
				
		};		
		feedbackCaseTable = $("#feedbackCase").DataTable(st1);
	}

	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.yanpanList, {

	});
})(jQuery);

//警情视频展示
$.zhzats.yanpan.jqVideoList = $.zhzats.jqVideoList || {} ;
(function($){
	var jqVideoTable = null ;
	//var tableData = null;
	function initjqVideoTable(tableData){
		var jqCode = null;
		if($.util.exist(tableData)){
			jqCode = tableData.code;
		}
		$.ajax({
			url:context + "/yanpan/jqVideoQuery.action",
			data:{"queryStr":jqCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				jqVideoListTable(successData.resultMap.list);
			}
		});
	}
	function jqVideoListTable(dataList){
		if($.util.exist(jqVideoTable)){
			jqVideoTable.destroy();
			$("#jqVideoList").empty();
		}
		
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataList;
		st1.columnDefs = [{
				"targets" : 0,
				"width" : "6%",
				"title" : "视频名称",
				"data" : "fileName",
				"render" : function(data, type, full, meta) {
					var td = $("<a />",{ 
						"text" : data.fileName,
						"href" : data.playAdd
					});
					return td[0].outerHTML;
				}
             }];
		st1.ordering = false;
		st1.paging = true; // 是否分页
		st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [10];
		st1.rowCallback = function(row,data, index) {
				
		};		
		jqVideoTable = $("#jqVideoList").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.zhzats.yanpan.jqVideoList, {
		initjqVideoTable : initjqVideoTable
	});
})(jQuery);

$.zhzats.yanpan.instruction = $.zhzats.instruction || {} ;
(function($){
	var instructionByYanpanTable = null;
	var tableData = null;
	//指令新增
	function newAddInstruction(tableData){
		var type = "zhzats";
		var jqId = null;
		if($.util.exist(tableData)){
			var jqName = tableData.name;
			jqId = tableData.id;
		}
		var typeSetContent = new Object();
		typeSetContent.relatedObjectId = jqId;
		typeSetContent.relateObjectContent = "关联警情：" + jqName;
		typeSetContent.relatedObjectType = $.common.XSFXTS_CONSTANT.FACTJQ_CLASS_NAME;
		typeSetContent.type = "0000000011004";
		typeSetContent.callbackFunction = function(){
			if($.util.exist(instructionByYanpanTable)){
				instructionByYanpanTable.draw(true);
			}
		};
		if($.util.exist(jqId)){
			$.szpt.util.instruction.addInstruction(null, type, typeSetContent);
		}else{
			window.top.$.layerAlert.alert({msg:'请您在选择警情后新增指令！谢谢',title:"温馨提示",icon : 5,time:3000});
		}
	}
	//指令下发列表
	function initInstructionByYanpanTable(tableData){
		if(instructionByYanpanTable != null) {
			instructionByYanpanTable.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/yanpan/queryInstructionPagerByBodyId.action";
		tb.columnDefs = [{
					"targets" : 0,
					"width" : "10%",
					"title" : "发送时间",
					"data" : "createTime",
					"render" : function(data, type, full, meta) {
						return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : "指令内容",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "10%",
					"title" : "指令类型",
					"data" : "typeName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "10%",
					"title" : "指令接收单位",
					"data" : "subs",
					"render" : function(data, type, full, meta) {
						var a="";
						$.each(data, function(i,item){
							 a=item.receiveSubjectName+"  "+a;
						 }); 
						return a;
					}
				},
				{
					"targets" : 4,
					"width" : "10%",
					"title" : "要求反馈时间",
					"data" : "requireFeedbackTime",
					"render" : function(data, type, full, meta) {
						return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				}
				];
		// 是否排序
		tb.ordering = false ;
		tb.paging = true; // 是否分页
		// 每页条数
		tb.lengthMenu = [ 5 ];
		// 默认搜索框
		tb.searching = false ;
		// 能否改变lengthMenu
		tb.lengthChange = false ;
		// 自动TFoot
		tb.autoFooter = false ;
		// 自动列宽
		tb.autoWidth = false ;
		// 请求参数
		tb.paramsReq = function(d, pagerReq){
			var jqId = null;
			if($.util.exist(tableData)){
				jqId = tableData.id;
			}
			d["queryStr"] = jqId;
		};
		tb.paramsResp = function(json) {
			var cgtbs = json.resultMap.instructions;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = cgtbs;
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		instructionByYanpanTable = $("#instructionByYanpanTable").DataTable(tb);
	}
	
	jQuery.extend($.zhzats.yanpan.instruction, {
		initInstructionByYanpanTable:initInstructionByYanpanTable,
		newAddInstruction:newAddInstruction
	});
})(jQuery);

$.zhzats.yanpan.feeedBack = $.zhzats.feeedBack || {} ;
(function($){
	var yanpanResultReturnTable = null;
	var tableData = null;
	//结果下发
	function newAddInstruction(tableData){
		var type = "zhzats";
		var jqId = null;
		if($.util.exist(tableData)){
			var jqName = tableData.name;
			jqId = tableData.id;
		}
		var typeSetContent = new Object();
		typeSetContent.relatedObjectId = jqId;
		typeSetContent.relateObjectContent = "";
		typeSetContent.relatedObjectType = $.common.XSFXTS_CONSTANT.FACTJQ_CLASS_NAME;
		typeSetContent.code = "0000000011007";
		typeSetContent.callbackFunction = function(){
			$.zhzats.yanpan.instruction.initInstructionByYanpanTable(tableData);
			findYanpanResultReturnTable();
		};
			$.szpt.util.instruction.addInstruction(tableData, type, typeSetContent);
	}
	//研判结果反馈
	/**
	 * 根据主体id查询研判结果反馈
	 * @returns
	 */
	function findYanpanResultReturnTable(tableData){
		var jqId = null;
		if($.util.exist(tableData)){
			jqId = tableData.id;
		}
		$.ajax({
			url:context +'/yanpan/queryYanPanJudeResultFeedback.action',
			data:{"queryStr":jqId},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var irsfbs = successData.resultMap.irsfbs;
				initYanpanResultReturnTable(irsfbs);
			}
		});
	}
	function initYanpanResultReturnTable(dataList){
		if($.util.exist(yanpanResultReturnTable)){
			yanpanResultReturnTable.destroy();
			$("#yanpanResultReturnTable").empty();
		}
		
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataList;
		st1.columnDefs = [
			{
				"targets" : 0,
				"width" : "5%",
				"title" : "序号",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					
					return meta.row + 1;
				}
			}, 
			{
				"targets" : 1,
				"width" : "10%",
				"title" : "接收单位",
				"data" : "receiveSubjectName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 2,
				"width" : "55%",
				"title" : "反馈内容",
				"data" : "feedbackContent",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 3,
				"width" : "10%",
				"title" : "反馈人",
				"data" : "feedbackPeopleName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 4,
				"width" : "10%",
				"title" : "已推送单位",
				"data" : "resultSendUnits",
				"render" : function(data, type, full, meta) {
					var div = $("<div />",{
						'class' : "fi-ceng-out"
					});
					var span = $("<span />",{
						"class" : "color-red2 font16 mar-right" ,
						"text" : data.length + "家"
					}).appendTo(div);
					var unitListDiv = $("<div />",{
						"class" : "fi-ceng row-ceng",
					}).appendTo(div);
					
					$.each(data,function(i, val){
						$("<p />",{
							"text" : val.name
						}).appendTo(unitListDiv);
					});
					return div[0].outerHTML;
				}
			}, 
			{
				"targets" : 5,
				"width" : "10%",
				"title" : "操作",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					var td = '<button class="btn btn-sm btn-primary resultSend" feedbackId="' + full.instructionId + '">结果下发</button>';
					return td;
				}
			}
		];
		st1.ordering = false;
		st1.paging = true; // 是否分页
		st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ 5 ]; //每页条数
		st1.rowCallback = function(row,data, index) {
				
		};		
		yanpanResultReturnTable = $("#yanpanResultReturnTable").DataTable(st1);
	}
	jQuery.extend($.zhzats.yanpan.feeedBack, { 
		findYanpanResultReturnTable : findYanpanResultReturnTable,
		newAddInstruction:newAddInstruction
	});	
})(jQuery);

