$.lookCriminalBasicCase = $.lookCriminalBasicCase || {};
(function($){
	
	"use strict";
	
	$(document).ready(function() {	
		/**
		 * 嫌疑人名称按钮
		 */
		$(document).on("click",".cpBut",function(){
			var obj = $(this).data("data");
			//设置值
			$.validform.setFormTexts("#tabs-3", obj);
			//设置按钮颜色
			$(this).siblings().each(function(i,val){
				$(val).removeClass("btn-primary").addClass("btn-default");
			});
			$(this).removeClass("btn-default").addClass("btn-primary");
		});
		
		/**
		 * 问询笔录
		 */
		$(document).on("click",".ccnBut",function(){
			var contentbase64 = $(this).attr("ccnImg");
			$.layerAlert.img("data:image/png;base64," + contentbase64, 400);
		});
		
		/**
		 * 涉案物品名称按钮
		 */
		$(document).on("click",".coBut",function(){
			var obj = $(this).data("data");
			//设置值
			$.validform.setFormTexts("#tabs-5", obj);
			//设置按钮颜色
			$(this).siblings().each(function(i,val){
				$(val).removeClass("btn-primary").addClass("btn-default");
			});
			$(this).removeClass("btn-default").addClass("btn-primary");
		});
		
		/**
		 * 卷宗文书
		 */
		$(document).on("click",".afBut",function(){
			var afId = $(this).attr("afId");
			var afType = $(this).attr("afType");
			
		});
		
	});
	
	/**
	 * 根据案件编号查询案件基本信息、接处警信息、嫌疑人信息、问询笔录信息、涉案物品信息、卷宗文书信息
	 * 
	 * @param caseCode 案件编号
	 */
	function caseTagQueryCaseRelatedInfoByCaseCode(caseCode){
		//基本案件信息
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalBasicCaseByCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				setBasicCaseInfo(resultMap.cbcb);
			}
		});
		//接处警
		$.ajax({
			url:context +'/criminalBasicCase/queryAlarmByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var jq = successData.resultMap.jq;
				setAlarmInfo(jq);
			}
		});
		//嫌疑人
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalPersonByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				setCriminalPersonInfo(resultMap.cpbs);
			}
		});
		//问询笔录
		$.ajax({
			url:context +'/criminalBasicCase/queryCrimialCaseNoteByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				setCrimialCaseNoteInfo(resultMap.ccnbs);
			}
		});
		//涉案物品
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalObjectByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				setCriminalObjectInfo(resultMap.cobs);
			}
		});
		//卷宗
		$.ajax({
			url:context +'/criminalBasicCase/queryArchivedFileByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				setArchivedFileInfo(resultMap.afbs);
			}
		});
	}
	
	/**
	 * 设置案件基本信息
	 * 
	 * @param obj 案件信息对象
	 */
	function setBasicCaseInfo(obj){
		if($.util.exist(obj)){
			obj.discoverTimeStart = $.util.isBlank(obj.discoverTimeStart) ? "" : $.date.timeToStr(obj.discoverTimeStart,"yyyy-MM-dd HH:mm");
			obj.discoverTimeEnd = $.util.isBlank(obj.discoverTimeEnd) ? "" : $.date.timeToStr(obj.discoverTimeEnd,"yyyy-MM-dd HH:mm");
			obj.caseTimeStart = $.util.isBlank(obj.caseTimeStart) ? "" : $.date.timeToStr(obj.caseTimeStart,"yyyy-MM-dd HH:mm");
			obj.caseTimeEnd = $.util.isBlank(obj.caseTimeEnd) ? "" : $.date.timeToStr(obj.caseTimeEnd,"yyyy-MM-dd HH:mm");
			$.validform.setFormTexts("#tabs-1", obj);
			
			$("#caseTitleUp").text(obj.caseName);
			$("#caseStateUp").text(obj.caseStateName);
		}else{
			$("#tabs-1").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	/**
	 * 设置接处警基本信息
	 * 
	 * @param obj 接处警信息对象
	 */
	function setAlarmInfo(obj){
		if($.util.exist(obj)){
			$.validform.setFormTexts("#tabs-2", obj);
		}else{
			$("#tabs-2").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	/**
	 * 设置嫌疑人基本信息
	 * 
	 * @param objArray 嫌疑人信息对象集合
	 */
	function setCriminalPersonInfo(objArray){
		if($.util.isArray(objArray) && objArray.length > 0){
			//设置人员名称按钮列表
			var btnStateClass = "btn-primary";
			$.each(objArray,function(o,obj){
				obj.birthday = $.util.isBlank(obj.birthday)?null:$.date.timeToStr(obj.birthday,"yyyy-MM-dd HH:mm");
				console.log(obj);
				if(o > 0){
					btnStateClass = "btn-default";
				}
				var a = $("<a />",{
					"href" : "javascript:void(0);",
					"class" : "cpBut btn " + btnStateClass,
					"text" : obj.name
				});
				$(a).data("data",obj);
				$("#criminalPersonNames").append(a);
			});
			$.validform.setFormTexts("#tabs-3", objArray[0]);
		}else{
			$("#tabs-3").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	/**
	 * 设置问询笔录基本信息
	 * 
	 * @param objArray 询笔录录信息对象集合
	 */
	function setCrimialCaseNoteInfo(objArray){
		if($.util.isArray(objArray) && objArray.length > 0){
			$.each(objArray,function(o,obj){
				var li = $("<li />",{});
				var p = $("<p />",{}).appendTo(li);
				$("<span />",{
					"class" : "glyphicon glyphicon-open-file color-red2"
				}).appendTo(p);
				$("<a />",{
					"href" : "javascript:void(0);" ,
					"class" : "ccnBut" ,
					"ccnImg" : obj.contentBase64 ,
					"text" : obj.name
				}).appendTo(p);
				$("#crimialCaseNotes").append(li);
			});
		}else{
			$("#tabs-4").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	/**
	 * 设置涉案物品基本信息
	 * 
	 * @param objArray 涉案物品信息对象集合
	 */
	function setCriminalObjectInfo(objArray){
		if($.util.isArray(objArray) && objArray.length > 0){
			//设置涉案物品名称按钮列表
			var btnStateClass = "btn-primary";
			$.each(objArray,function(o,obj){
				obj.purchaseDate = $.date.timeToStr(obj.purchaseDate,"yyyy-MM-dd HH:mm");
				if(o > 0){
					btnStateClass = "btn-default";
				}
				var a = $("<a />",{
					"href" : "javascript:void(0);",
					"class" : "coBut btn " + btnStateClass,
					"text" : obj.name
				});
				$(a).data("data",obj);
				$("#criminalObjectNames").append(a);
			});
			$.validform.setFormTexts("#tabs-5", objArray[0]);
		}else{
			$("#tabs-5").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	/**
	 * 设置卷宗文书基本信息
	 * 
	 * @param objArray 卷宗文书信息对象集合
	 */
	function setArchivedFileInfo(objArray){
		if($.util.isArray(objArray) && objArray.length > 0){
			$.each(objArray,function(o,obj){
				var li = $("<li />",{});
				var p = $("<p />",{}).appendTo(li);
				$("<span />",{
					"class" : "glyphicon glyphicon-open-file color-red2"
				}).appendTo(p);
				$("<a />",{
					"href" : "javascript:void(0);" ,
					"class" : "afBut" ,
					"afId" : obj.id ,
					"afType" : obj.type ,
					"text" : obj.name
				}).appendTo(p);
				$("#archivedFiles").append(li);
			});
		}else{
			$("#tabs-6").html('<h3 style="text-align: center;padding: 10px;">无此信息</h3>');
		}
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookCriminalBasicCase, { 
		caseTagQueryCaseRelatedInfoByCaseCode : caseTagQueryCaseRelatedInfoByCaseCode
	});	
})(jQuery);
