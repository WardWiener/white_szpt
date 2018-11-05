(function($){
	
	"use strict";
	
	var caseId = $("#caseId").val();
	var caseNoteTable = null; // 证据笔录表
	var judgeFeedbackTable = null;// 一案一研判结果列表
	var knownCaseTable = null;// 串并案件列表
	var shPersonTable = null;
	var dsPersonTable = null;
	var baPersonTable = null;
	var criminalCaseTeamTable = null;
	var xianyirenTable = null;
	
	var yaydDetailBackUrl = "";
	$(document).ready(function() {
		yaydDetailBackUrl = sessionStorage.getItem("yaydDetailBackUrl");
		initCaseDetail();//案件基本信息
		initCasePoliceInfo();//接触警
		initCaseFeedbackInfo();//反馈信息
		initPoliceInfoDeal();//警情处置
		initCaseTagDetail();//案件打标
		initInvolvePersonInfo();//涉案人员
		initCriminalFord();//犯罪团伙
		initCriminalObjectDetail();//涉案物品
		initArchivedFileDetail();
		initCaseNoteDetail();
		initInstructionAndFeedback();//指令反馈
		queryJudgeFeedbackResultTable();
		initVideo();
		
		$(document).on("click",".con-box",function(){
			var id = $(this).attr("href").split("#")[1];
			setAnchorExcursion(id);
		});
	})
	$(document).on("click",".showWrit",function(){
		window.top.open(context+"/showWrit/checkWrit.action?archivedFileId=" + $(this).attr("writId"));
	});
	$(document).on("click", ".showSufferCaseRelation", function(){
		window.top.open(context+"/yayd/showSufferCaseRelation.action?dataId=" + $(this).attr("scrId"));
	})
	$(document).on("click", ".showCaseSupectRelation", function(){
		window.top.open(context+"/yayd/showCaseSupectRelation.action?dataId=" + $(this).attr("csrId"));
	})
	$(document).on("click", ".showCriminalObject", function(){
		window.top.open(context+"/yayd/showCriminalObject.action?dataId=" + $(this).attr("coId"));
	})
	
	$(document).on("click", "#backViewBtn", function(){
		window.top.location.href = yaydDetailBackUrl;
	})
	
	$(document).on("click", ".yrydDetail", function(){
		sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
		window.top.location.href = context + "/yryd/showYrydHighriskPersonAlertPage.action?clickOrder=1&&clickListOrder=0&&idcard="+$(this).attr("id");
	});
	onclick=""
	$(document).on("click",".openSp a",function(){
		var url = $(this).attr("srcData");
		var initData = {
			type : "video",
			src : url
		}
		window.top.$.layerAlert.dialog({
			content:context + '/yayd/showAttachment.action',
			pageLoading:true,
			title:"视频",
			width:"800px",
			height:"590px",
			initData:function(){
				return $.util.exist(initData)?initData:{} ;
			},
			shadeClose: false,
			btn: ['关闭'],
			callBacks:{
			 	  btn1:function(index, layero){
			 		 layer.close(index);
			    }
			}
		});
	});
	$(document).on("click",".openYp a",function(){
		var url = $(this).attr("srcData");
		var initData = {
			type : "audio",
			src : url
		}
		window.top.$.layerAlert.dialog({
			content:context + '/yayd/showAttachment.action',
			pageLoading:true,
			title:"音频",
			width:"300px",
			height:"145px",
			initData:function(){
				return $.util.exist(initData)?initData:{} ;
			},
			shadeClose: false,
			btn: ['关闭'],
			callBacks:{
			 	  btn1:function(index, layero){
			 		 layer.close(index);
			    }
			}
		});
	});
	$(document).on("click",".showUpload",function(){
		var initData = {
				caseId : caseId
			}
		window.top.$.layerAlert.dialog({
			content:context + '/yayd/showUploadMedia.action',
			pageLoading:true,
			title:"上传音视频",
			width:"300px",
			height:"200px",
			initData:function(){
				return $.util.exist(initData)?initData:{} ;
			},
			shadeClose: false,
			btn: ['上传','关闭'],
			callBacks:{
			 	btn1:function(index, layero){
			 		var cm = window.top.frames["layui-layer-iframe"+index].$.common;
					cm.upload($, index);
			    },
				btn2:function(index, layero){
			 		 layer.close(index);
			    }
			},
			end:function(){
				initVideo();
			}
		});
	});
	
	/**
	 * 设置锚点偏移
	 * 
	 * @returns
	 */
	function setAnchorExcursion(selector){
		var target = $("#" + selector);
		if(target.length == 1){
			var top = target.offset().top-82;
			if(top > 0){
				$('html,body').animate({scrollTop:top}, 1);
			}
		}
		return false;
	}
	
	/**
	 * 初始化案件基本信息
	 * 
	 * @returns
	 */
	function initCaseDetail(){
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalBasicCaseByCode.action',
			type:'post',
			data:{caseCode : caseId},
			dataType:'json',
			success:function(successData){
				var data = successData.resultMap.cbcb;
				if($.util.exist(data)){
					setBasicCaseInfo(data);
				}
			}
		});
	}
	//初始化案件接处警信息信息
	function initCasePoliceInfo(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryCriminalPoliceCaseByCode.action',
			type:'post',
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			dataType:'json',
			 customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
			success:function(successData){
				var data = successData.resultMap.result;
				if($.util.exist(data)){
					if(data.length>0){
						setBasicPoliceInfo(data[0]);
					}
					
				}
			}
		});
	}
	//初始化案件反馈信息
	function initCaseFeedbackInfo(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryCriminalFeedbackByCode.action',
			type:'post',
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			dataType:'json',
			 customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
			success:function(successData){
				var data = successData.resultMap.result;
				if($.util.exist(data)){
					if(data.length>0){
					setBasicFeedBackInfo(data[0]);
					}
				}
			}
		});
	}
	//初始化警情处置信息
	function initPoliceInfoDeal(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryAcceptPoliceInfoByCaseCode.action',
			type:'post',
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			dataType:'json',
			 customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
			success:function(successData){
				var data = successData.resultMap.accPoliceResult;
				
				if(data.length > 0){
					$("#policeDeal").parent("div").css("height","400px");
				}else{
					$("#policeDeal").parent("div").css("height","");
				}
				
				var acceptPolice = "";
				for(var i = 0 ; i < data.length ; i++){
					if(i%2 == 0){
						acceptPolice += '<li class="odd">';
					}else{
						acceptPolice += '<li class="even">';
					}
					acceptPolice += '<span class="icon-red-dot"></span>'
					+	'<div class="time-box">'
					+	'<p><span valName="reportTime" class="alarmInfo">'+data[i].accTime+'</span></p></div>'
					+	'<div class="con-box">'
					+	'<span class="arrow"></span><span valName="receiveUnit" class="alarmInfo">'+data[i].accOrderCell+'</span>'
					+'接警</div></li>';
					var accId = data[i].id;
				 	var feedbackData = successData.feedbackMap[accId];
					for(var j = 0 ; j < feedbackData.length ; j++){
						if((i+j+1)%2 == 0){
							acceptPolice += '<li class="odd">';
						}else{
							acceptPolice += '<li class="even">';
						}
						acceptPolice += '<span class="icon-red-dot"></span>'
							             +'<div class="time-box">'
						                 +'<p>'+feedbackData[j].feedbackTime+'</p></div>'
						             	 +'<div class="con-box">'
						                 +'<span class="arrow"></span>'+feedbackData[j].feedbackOrderCell+' 反馈：'+feedbackData[j].feedbackContent+'</div></li>'
					}
				}
				acceptPolice += '<li class="clear"></li>'
				$("#policeDeal").html(acceptPolice);
			}
		});
	}
	/**
	 * 初始化案件打标信息
	 * 
	 * @returns
	 */
	function initCaseTagDetail(){
		var data = {
			"caseCode" : caseId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryCaseTagByCaseCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.ctb;
				if($.util.exist(data)){
					setCaseTagInfo(data);
				}
			}
		});
	}
	
	/**
	 * 初始化涉案物品信息
	 * 
	 * @returns
	 */
	function initCriminalObjectDetail(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryInvolvedThing.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.result;
				if($.util.exist(data)){
					setCriminalObjectInfo(data);
				}
			}
		});
	}
	
	/**
	 * 初始化卷宗信息
	 * 
	 * @returns
	 */
	function initArchivedFileDetail(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryJZWS.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			customizedOpt:{
				ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.result;
				if($.util.exist(data)){
					setArchivedFileInfo(data);
				}
			}
		});
	}
	
	/**
	 * 初始化证据笔录信息
	 * 
	 * @returns
	 */
	function initCaseNoteDetail(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryZjbl.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			customizedOpt:{
				ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.result;
				if($.util.exist(data)){
					setCaseNoteInfo(data);
				}
			}
		});
	}
	
	/**
	 * 查询指令及反馈
	 * 
	 * @returns
	 */
	function initInstructionAndFeedback(){
		var data = {
			"caseCode" : caseId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryInstructionAndFeedbackByCaseCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.result;
				
				if(data.length > 0){
					$("#instructive").parent("div").css("height","400px");
				}else{
					$("#instructive").parent("div").css("height","");
				}
				
				var instructiveContent = "";
				for(var i = 0 ; i < data.length ; i++){
					if( i%2 == 0){
						instructiveContent += '<li class="odd">';
					}
					else{
						instructiveContent += '<li class="even">';
					}
					instructiveContent += '<span class="icon-red-dot"></span><div class="time-box">'
					                      +' <p>'+data[i].createDate +'</p></div><div class="con-box"><span class="arrow"></span>'
						                  + data[i].createDepartment + ' 下发指令给 ' + data[i].acceptDepartments +'</div></li>';
					var instructid = data[i].acceptObjectId;
					var feedbackData = successData.instructionfeedbackMap[instructid];
					for(var j = 0 ; j < feedbackData.length ; j++){
						if( (i+1+j)%2 == 0){
							instructiveContent += '<li class="odd">';
						}
						else{
							instructiveContent += '<li class="even">';
						}
						instructiveContent += '<span class="icon-red-dot"></span><div class="time-box">'
		                      +' <p>'+ feedbackData[j].feedbackTime +'</p></div><div class="con-box"><span class="arrow"></span>'
			                  + feedbackData[j].feedbackDepartment + ' 反馈' +'<p> 反馈内容：'+feedbackData[j].feedbackContent+'</p></div></li>';
					}
				}
				instructiveContent +='<li class="clear"></li>';
               $("#instructive").html(instructiveContent);
			}
		});
	}
	
	/**
	 * 根据主体id查询研判结果反馈
	 * 
	 * @returns
	 */
	function queryJudgeFeedbackResultTable(){
		$.ajax({
			url:context +'/instruction/queryJudeResultFeedback.action',
			data:{"queryStr" : caseId},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var irsfbs = successData.resultMap.irsfbs;
				initJudgeFeedbackResultTable(irsfbs);
			}
		});
	}
	
	/**
	 * 根据案件编号查询涉案团伙
	 * 
	 * @returns
	 */
	function initCriminalFord(){
		var data = {
			"caseCode" : caseId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryCriminalFordByCaseCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.cfsMap;
				if($.util.exist(data)){
					$("#teamNum").text("犯罪团伙("+data.length+")")
					setCriminalFordTable(data);
				}
			}
		});
	}
	
	/**
	 * 设置涉案团伙
	 * 
	 * @param dataArray 数据集合
	 * @returns
	 */
	function setCriminalFordTable(dataArray){
		if($.util.exist(criminalCaseTeamTable)){
			criminalCaseTeamTable.destroy();
			$("#criminalCaseTeamTable").empty();
		}
    	var thtb = $.uiSettings.getLocalOTableSettings();
    	 thtb = initCriminalTeamTable(dataArray,thtb);
    	criminalCaseTeamTable = $("#criminalCaseTeamTable").DataTable(thtb);
		
	}
	
	/**
	 * 查询涉案人员
	 * @returns
	 */
	
	
	function initInvolvePersonInfo(){
		var data = {
				"caseCode" : caseId
			};
			var dataStr = $.util.toJSONString(data) ;
			$.ajax({
				url:context +'/yayd/queryInvolvedCasePerson.action',
				data:dataStr,
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
			    customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
				success:function(successData){
					if($.util.exist(successData.shrBeanList)){
						$("#shrNum").text("受害人("+successData.shrBeanList.length+")");
						initshPersonInfo(successData.shrBeanList);
					}
					if($.util.exist(successData.dsrBeanList)){
						$("#dsrNum").text("当事人("+successData.dsrBeanList.length+")");
						initdsPersonInfo(successData.dsrBeanList);
					}
					if($.util.exist(successData.barBeanList)){
						$("#barNum").text("报案人("+successData.barBeanList.length+")");
						initbaPersonInfo(successData.barBeanList);
					}
					if($.util.exist(successData.xyrBeanList)){
						$("#xianyirenNum").text("嫌疑人("+successData.xyrBeanList.length+")");
						initxyPersonInfo(successData.xyrBeanList);
					}
					
				}
			});
	}
	
	/**
	 * 初始化报案人
	 * @returns
	 */
    function initbaPersonInfo(dataArray){
    	if($.util.exist(baPersonTable)){
    		baPersonTable.destroy();
			$("#baPersonTable").empty();
		}
    	var bartb = $.uiSettings.getLocalOTableSettings();
    	bartb = initInvolvePersonTable(dataArray,bartb);
    	baPersonTable = $("#baPersonTable").DataTable(bartb);
    }
    /**
     * 初始化当事人table
     * @param dataArray
     * @returns
     */
    function initdsPersonInfo(dataArray){
    	if($.util.exist(dsPersonTable)){
    		dsPersonTable.destroy();
			$("#dsPersonTable").empty();
		}
    	var dsrtb = $.uiSettings.getLocalOTableSettings();
    	dsrtb = initInvolvePersonTable(dataArray,dsrtb);
    	dsPersonTable = $("#dsPersonTable").DataTable(dsrtb);
    }
    /**
     * 初始化受害人
     * @param dataArray
     * @returns
     */
    function initshPersonInfo(dataArray){
    	if($.util.exist(shPersonTable)){
    		shPersonTable.destroy();
			$("#shPersonTable").empty();
		}
    	var shrtb = $.uiSettings.getLocalOTableSettings();
    	var shrDatatb = initInvolvePersonTable(dataArray,shrtb);
    	shPersonTable = $("#shPersonTable").DataTable(shrDatatb);
    }
    /**
	 * 初始化嫌疑人
	 * @returns
	 */
    function initxyPersonInfo(dataArray){
    	if($.util.exist(xianyirenTable)){
    		xianyirenTable.destroy();
			$("#xianyirenTable").empty();
		}
    	var xyrtb = $.uiSettings.getLocalOTableSettings();
    	xyrtb = initSuspectPersonTable(dataArray,xyrtb);
    	xianyirenTable = $("#xianyirenTable").DataTable(xyrtb);
    }
    
	/**
	 * 初始化涉案人员Table
	 * 
	 * @param dataArray 数据集合
	 * @returns
	 */
	function initInvolvePersonTable(dataArray,settings){
		var tb = settings;
		tb.data = dataArray;
		tb.columnDefs = [
		 {
			"targets" : 0,
			"width" : "25%",
			"title" : '姓名',
			"data" : "name",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 1,
			"width" : "25%",
			"title" : '身份证号',
			"data" : "idcardNo",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "25%",
			"title" : '别名',
			"data" : "aliasName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 3,
			"width" : "25%",
			"title" : '性别',
			"data" : "sex",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		tb.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		return tb;
	}
	/**
	 * 初始化嫌疑人员列表
	 * 
	 * @param dataArray 数据集合
	 * @returns
	 */
	function initSuspectPersonTable(dataArray,settings){
		var tb = settings;
		tb.data = dataArray;
		tb.columnDefs = [
		 {
			"targets" : 0,
			"width" : "25%",
			"title" : '姓名',
			"data" : "name",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 1,
			"width" : "25%",
			"title" : '身份证号',
			"data" : "idcardNo",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "25%",
			"title" : '别名',
			"data" : "aliasName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 3,
			"width" : "25%",
			"title" : '性别',
			"data" : "sex",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 4,
			"width" : "25%",
			"title" : '操作',
			"data" :  "idcardNo",
			"render" : function(data, type, full, meta) {
				return '<button class="btn btn-bordered btn-xs yrydDetail" id="'+data+'" >详情</button>';
			}
		}];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		tb.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		return tb;
	}
	/**
	 * 构造犯罪团伙Table
	 * 
	 * @param dataArray 数据集合
	 * @returns
	 */
	function initCriminalTeamTable(dataArray,settings){
		var tb = settings;
		tb.data = dataArray;
		tb.columnDefs = [
		 {
			"targets" : 0,
			"width" : "15%",
			"title" : '团伙名称',
			"data" : "name",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 1,
			"width" : "10%",
			"title" : '人数',
			"data" : "count",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "15%",
			"title" : '主犯名称',
			"data" : "mainPeople",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 3,
			"width" : "15%",
			"title" : '组合形式',
			"data" : "form",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 4,
			"width" : "15%",
			"title" : '作案手段',
			"data" :  "resort",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},{
			"targets" : 5,
			"width" : "20%",
			"title" : '作案特点',
			"data" :  "peculiarity",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		tb.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		return tb;
	}
	/**
	 * 设置证据笔录信息
	 * 
	 * @param dataArray 证据笔录信息集合
	 * @returns
	 */
	function setCaseNoteInfo(dataArray){
		if($.util.exist(caseNoteTable)){
			caseNoteTable.destroy();
			$("#caseNoteTable").empty();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = dataArray;
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "60%",
				"title" : "证据笔录名称",
				"data" : "wsName",
				"render" : function(data, type, full, meta) {
					return '<a href='+full.askAddress+'>'+data+'</a>';
				}
			},
			{
				"targets" : 1,
				"width" : "40%",
				"title" : '创建时间',
				"data" : "lrsj",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data);
				}
			}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = true;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		caseNoteTable = $("#caseNoteTable").DataTable(tb);
	}
	
	/**
	 * 初始化研判结果反馈表
	 * 
	 * @param dataArray
	 * @returns
	 */
	function initJudgeFeedbackResultTable(dataArray){
		if($.util.exist(judgeFeedbackTable)){
			judgeFeedbackTable.destroy();
			$("#judgeFeedbackTable").empty();
		}
		
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
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
				"width" : "20%",
				"title" : "已推送单位",
				"data" : "resultSendUnits",
				"render" : function(data, type, full, meta) {
					var div = $("<div />",{
						'class' : "fi-ceng-out"
					});
					var span = $("<span />",{
						"class" : "color-red2 font16 mar-right" ,
						"text" : data.length
					}).appendTo(div);
					$("<span />",{
						"text" : " 家"
					}).appendTo(span);
					var unitListDiv = $("<div />",{
						"class" : "fi-ceng row-ceng"
					}).appendTo(div);
					
					$.each(data,function(i, val){
						$("<p />",{
							"text" : val.name
						}).appendTo(unitListDiv);
					});
					return div[0].outerHTML;
				}
			}
		];
		st1.ordering = false;
		st1.paging = false; // 是否分页
		st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ dataArray.length ];
		st1.rowCallback = function(row,data, index) {
			$(row).data("data", data);
		};		
		judgeFeedbackTable = $("#judgeFeedbackTable").DataTable(st1);
	}
	
	
	/**
	 * 设置卷宗信息
	 * 
	 * @param dataArray 卷宗信息集合
	 * @returns
	 */
	function setArchivedFileInfo(dataArray){
		if(dataArray.length < 1){
			$("#archivedFileUl").html("");
			return ;
		}
		$.each(dataArray,function(d,data){
			var str = '<li><span class="right color-green1"></span><p><span class="glyphicon glyphicon-open-file color-red2">'
				+'</span><a href="'+data.askAddress+'">'+data.wsName+'</a></p></li>'
			$("#archivedFileUl").append(str);
		});
	}
	
	/**
	 * 设置涉案物品信息
	 * 
	 * @param dataArray 涉案物品信息集合
	 * @returns
	 */
	function setCriminalObjectInfo(dataArray){
		if(dataArray.length < 1){
			$("#criminalObjectUl").html("");
			return ;
		}
		$.each(dataArray,function(d,data){
			var objTemplate = $("#criminalObjectTemplate");
			var cloneObjTemplate = objTemplate.clone();
			cloneObjTemplate.removeAttr("id");
			
			$(cloneObjTemplate).find("span").each(function(s,span){
				if($.util.isBlank($(span).attr("name"))){
					return true;
				}
				var value = data[$(span).attr("name")];
				if($.util.isBlank(value)){
					$(span).text("");
				}else{
					$(span).text(value);
				}
			});
			$("#criminalObjectUl").append(cloneObjTemplate[0]);
		});
	}
	
	/**
	 * 设置案件打标信息
	 * 
	 * @param data 案件打标信息
	 * @returns
	 */
	function setCaseTagInfo(data){
		$.each(data,function(i,val){
			$("#caseTagInfo td[name='" + i + "']").text($.util.isBlank(val)?"":val);
		});
	}
	
	/**
	 * 设置案件基本信息
	 * 
	 * @param data 案件信息
	 * @returns
	 */
	function setBasicCaseInfo(data){
		
		data.caseTimeStart = $.date.timeToStr(data.caseTimeStart, "yyyy-MM-dd HH:mm:ss");
		//设置案件基本信息
		$.each(data,function(i,val){
			var value = $.util.isBlank(val)?"":val;
			$("#caseBasicInfo td[name='" + i + "']").text(value);
			$("#caseBasicInfo span[name='" + i + "']").text(value);
			$("#bs-scroll-1 span[name='" + i + "']").text(value);
		});
	}
	//接触警信息
	function setBasicPoliceInfo(data){
		$("#reportTime").text(data.reportTime == null ?"":data.reportTime);
		$("#receivePerson").text("" );
		$("#alarmType").text(data.reportMode == null ?"":data.reportMode);
		$("#baojingren").text(data.rvcallPerson == null ?"":data.rvcallPerson);
		$("#popedom").text(data.happenPlace == null ?"":data.happenPlace);
		$("#reportDetails").text(data.reportDetails == null ?"":data.reportDetails);
	}
	//反馈信息
	function setBasicFeedBackInfo(data){
		$("#feedbackContent").text(data.feedbackContent == null ? "" : data.feedbackContent);
		$("#feedbackType").text(data.feedbackType == null ? "" : data.feedbackType);
		$("#feedbackPerson").text(data.feedbackPerson == null ? "" : data.feedbackPerson);
		$("#feedbackTime").text(data.feedbackTime == null ? "" : data.feedbackTime);
	}
	//证据笔录
	function testimonyTakeDownTable(tableInfoLst){
		var count = 0;
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = tableInfoLst;
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "60%",
				"title" : "证据笔录名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					if(count == 0){
						$("#blattachMent").html('<img src="../images/photo/bl-example.png" width="550"height="382">');
					}
					count++;
					return '<a href="#">'+data+'</a>';
				}
			},
			{
				"targets" : 1,
				"width" : "40%",
				"title" : '创建时间',
				"data" : "createTime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = true;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#testimonyTakeDown").DataTable(tb);
	}
	
	/**
	 * 初始化已知串并案Table
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
		st1.columnDefs = [
			{
				"targets" : 0,
				"width" : "",
				"title" : "序号",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
						
						return meta.row + 1;
				}
			}, 
			{
				"targets" : 1,
				"width" : "",
				"title" : "案件编号",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
						var td = '<a href="javascript:void(0);" class="lookYayd" caseCode="' + data + '">' + data + '</a>'
						return td;
				}
			}, 
			{
				"targets" : 2,
				"width" : "",
				"title" : "案件名称",
				"data" : "caseName",
				"render" : function(data, type, full, meta) {
						var td = '<a href="javascript:void(0);" class="lookYayd" caseCode="' + full.caseCode + '">' + data + '</a>'
						return td;
				}
			}, 
			{
				"targets" : 3,
				"width" : "",
				"title" : "案件状态",
				"data" : "caseStateName",
				"render" : function(data, type, full, meta) {
						
						return data;
				}
			}, 
			{
				"targets" : 4,
				"width" : "",
				"title" : "案发时间",
				"data" : "caseTimeStart",
				"render" : function(data, type, full, meta) {
					
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				}
			}
		];
		st1.ordering = false;
		st1.paging = false; // 是否分页
		st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ dataArray.length ];
		st1.rowCallback = function(row,data, index) {
			$(row).data("data", data);	
		};		
		knownCaseTable = $("#knownCaseTable").DataTable(st1);
	}
	
	function initVideo(){
		$(".jcjsp").empty();
		$(".jcjyp").empty();
		$(".qtsp").empty();
		$(".qtyp").empty();

		var data = {
			"caseCode" : caseId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/queryVideoByCaseCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap;
				if(!$.util.isArray(data["jcjsp"]) || data["jcjsp"].length < 1){
					var str = '<li style="text-align:center;">无记录</li>';
					$(".jcjsp").append(str);
				}else{
					$(".jcjsp").html("");
					for(var i in data["jcjsp"]){
						var li = $("<li />",{});
						var span = $("<span />",{
							"class" : "glyphicon glyphicon-headphones color-blue1 mar-right"
						}).appendTo(li);
						var a = $("<a />",{
							"href" : "javascript:void(0);" ,
							"srcData" : data["jcjsp"][i].playAddress ,
							"text" : data["jcjsp"][i].fileName
						}).appendTo(li);
						$(".jcjsp").append(li);
					}
				}
				if(!$.util.isArray(data["jcjyp"]) || data["jcjyp"].length < 1){
					var str = '<li style="text-align:center;">无记录</li>';
					$(".jcjyp").append(str);
				}else{
					$(".jcjyp").html("");
					for(var i in data["jcjyp"]){
						var li = $("<li />",{});
						var span = $("<span />",{
							"class" : "glyphicon glyphicon-headphones color-blue1 mar-right"
						}).appendTo(li);
						var a = $("<a />",{
							"href" : "javascript:void(0);" ,
							"srcData" : data["jcjyp"][i].playAddress ,
							"text" : data["jcjyp"][i].fileName
						}).appendTo(li);
						$(".jcjyp").append(li);
					}
				}
				if(!$.util.isArray(data["qtsp"]) || data["qtsp"].length < 1){
					var str = '<li style="text-align:center;">无记录</li>';
					$(".qtsp").append(str);
				}else{
					$(".qtsp").html("");
					for(var i in data["qtsp"]){
						var li = $("<li />",{});
						var span = $("<span />",{
							"class" : "glyphicon glyphicon-headphones color-blue1 mar-right"
						}).appendTo(li);
						var a = $("<a />",{
							"href" : "javascript:void(0);" ,
							"srcData" : data["qtsp"][i].playAddress ,
							"text" : data["qtsp"][i].fileName
						}).appendTo(li);
						$(".qtsp").append(li);
					}
				}
				if(!$.util.isArray(data["qtyp"]) || data["qtyp"].length < 1){
					var str = '<li style="text-align:center;">无记录</li>';
					$(".qtyp").append(str);
				}else{
					$(".qtyp").html("");
					for(var i in data["qtyp"]){
						var li = $("<li />",{});
						var span = $("<span />",{
							"class" : "glyphicon glyphicon-headphones color-blue1 mar-right"
						}).appendTo(li);
						var a = $("<a />",{
							"href" : "javascript:void(0);" ,
							"srcData" : data["qtyp"][i].playAddress ,
							"text" : data["qtyp"][i].fileName
						}).appendTo(li);
						$(".qtyp").append(li);
					}
				}
			}
		});
	}
	
	/**
	 * 向父页面暴露的方法
	 */
	jQuery.extend($.common, {
		closeWindow:function(index){
			layer.close(index);
		}
	});
})(jQuery);	