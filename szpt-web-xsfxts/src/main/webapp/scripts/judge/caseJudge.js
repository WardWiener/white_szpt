$.caseJudge = $.caseJudge || {};

/**
 * 未破案件列表版块
 */
$.caseJudge.caseList = $.caseJudge.caseList || {};
(function($){

	"use strict";
	
	var caseTable = null;// 案件table
	
	$(document).ready(function() {	
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$.szpt.util.noData.initDictionaryCheckTree("ajlbTree", $.common.DICT.DICT_TYPE_V3AJLB);
		$.szpt.util.noData.initDictionaryCheckTree("ajxzTree", $.common.DICT.DICT_TYPE_V3AJXZ);
		
		/**
		 * 查询
		 */
		$(document).on("click","#query",function(){
			if($.util.exist(caseTable)){
				caseTable.draw(true);
			}
		});
		
		/**
		 * 查看一案一档
		 */
		$(document).on("click",".lookYayd",function(){
			var caseId = $(this).attr("caseCode");
			if($.util.isBlank(caseId)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请先选择案件。"}) ;
				return ;
			}
			sessionStorage.setItem("yaydDetailBackUrl", window.location.href);
			window.top.location.href = context + "/yayd/showYaydDetailPage.action?clickOrder=0&&clickListOrder=0&&id=" + caseId;
		});
		
		/**
		 * 案件列表行点击事件
		 */
		$(document).on("click","#caseTable tbody tr",function(){
			$(this).find("input").iCheck('check');
		});
		
		/**
		 * radio选中事件
		 */
		$(document).on("ifChecked","input[name='caseCode']",function(){
			var caseCode = $(this).val();
			$.caseJudge.detail.queryCriminalBasicCaseByCode(caseCode);
			$.caseJudge.detail.queryCrimialCaseNoteByCaseCode(caseCode);
			$.caseJudge.detail.queryCriminalPersonByCaseCode(caseCode);
			$.caseJudge.detail.queryCriminalObjectByCaseCode(caseCode);
			$.caseJudge.detail.queryAlarmBasicByCaseCode(caseCode);
			$.caseJudge.detail.findVideoByCaseCode(caseCode);
			
			//查询指令列表
			$.caseJudge.instruct.initInstructTable();
			//查询所有指令反馈
			$.caseJudge.judgeResultFeedback.queryJudgeFeedbackResultTable();
			
			$("#lookYayd").attr("caseCode", caseCode);
			$("#caseInfoDiv").show();
			$("#instructInfoDiv").show();
			$("#ypResultDiv").show();
		});
		
		/**
		 * 重置按钮
		 */
		$(document).on("click","#reset",function(){
			$("#caseCode").val("");
			$("#caseName").val("");
			$.select2.clear("#ajdbztSelect");
			if($.util.exist(caseTable)){
				caseTable.draw(true);
			}
			$.szpt.util.businessData.destroyTree("ajxzTree");
			$.szpt.util.noData.initDictionaryCheckTree("ajxzTree", $.common.DICT.DICT_TYPE_V3AJXZ);
			$.szpt.util.businessData.destroyTree("ajlbTree");
			$.szpt.util.noData.initDictionaryCheckTree("ajlbTree", $.common.DICT.DICT_TYPE_V3AJLB);
		});
	});
	
	/**
	 * 页面预加载初始化
	 */
	function init(){
		$.szpt.util.businessData.initDbztSelect("#ajdbztSelect");
		initCaseTable();
	}
	
	/**
	 * 初始化案件列表
	 */
	function initCaseTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/caseJudge/queryCaseListByCondition.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "5%",
				"title" : "选择",
				"data" : "caseTagId",
				"render" : function(data, type, full, meta) {
					var td = '<input type="radio" class="icheckradio" name="caseCode" value="' + full.caseCode + '">';
					if(!$.util.isBlank(data)){
						td += '<span class="glyphicon glyphicon-star font16 color-red2 mar-left"></span>';
					}
					return td;
				}
			},
			{
				"targets" : 1,
				"width" : "5%",
				"title" : "序号",
				"data" : "caseTagId",
				"render" : function(data, type, full, meta) {
					return meta.row + 1;
				}
			}, 
			{
				"targets" : 2,
				"width" : "15%",
				"title" : "案件编号",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 3,
				"width" : "10%",
				"title" : "案件名称",
				"data" : "caseName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 4,
				"width" : "5%",
				"title" : "案件类别",
				"data" : "caseSortName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 5,
				"width" : "5%",
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
					if($.util.isBlank(data)){
						return "";
					}else{
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				}
			}, 
			{
				"targets" : 7,
				"width" : "12%",
				"title" : "发案地点",
				"data" : "caseAddress",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 8,
				"width" : "25%",
				"title" : "简要案情",
				"data" : "details",
				"render" : function(data, type, full, meta) {
					var td = $("<span />",{ 
						"text" : data.substring(0,101) + "...",
						"title" : data
					});
					return td[0].outerHTML;
				}
			}, 
			{
				"targets" : 9,
				"width" : "8%",
				"title" : "办案单位",
				"data" : "handleUnit",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 5 ];
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
		    d["caseCode"] = $('#caseCode').val();
			d["caseName"] = $('#caseName').val();
			d["tagState"] = $.select2.val('#ajdbztSelect');
			var caseSortArray = $.szpt.util.noData.getCheckedByTree("ajlbTree");
			var caseKindArray = $.szpt.util.noData.getCheckedByTree("ajxzTree");
			$.each(caseSortArray,function(i,val){
				d["caseSortList[" + i + "]"] = val;
			});
			$.each(caseKindArray,function(i,val){
				d["caseKindList[" + i + "]"] = val;
			});
		};
		tb.paramsResp = function(json) {
			var cbcbs = json.resultMap.cbcbs;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = cbcbs;
		};
		tb.rowCallback = function(row,data, index) {
					
		};
		tb.drawCallback = function(){
			$("#lookYayd").removeAttr("caseCode");
			$("#caseInfoDiv").hide();
			$("#instructInfoDiv").hide();
			$("#ypResultDiv").hide();
		};
		caseTable = $("#caseTable").DataTable(tb);
	}
	
	/**
	 * 数组值转换为间隔字符串
	 * @param array 数组
	 * @param split 字符串间隔符
	 * @returns 字符串
	 */
	function arrToStr(array, split){
		if($.util.isBlank(split)){
			split = ",";
		}
		if($.util.exist(array) && array.length > 0){
			var str = "";
			$.each(array,function(i,val){
				str += val;
				if(i < array.length - 1){
					str += split;
				}
			});
			return str;
		}else{
			return "";
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseJudge.caseList, { 
		
	});	
})(jQuery);

/**
 * 案件、警情基本信息和警情研判结果版块
 */
$.caseJudge.detail = $.caseJudge.detail || {};
(function($){

	"use strict";
	
	var suspectQkCaseTable = null; // 嫌疑人前科案件table
	var cpbs = [];// 嫌疑人数组
	var cobs = [];// 涉案物品数组
	var feedbackTable = null;// 各单位警情研判结果table
	
	$(document).ready(function() {	
		/**
		 * 问询笔录
		 */
		$(document).on("click",".ccnBut",function(){
			var url = $(this).attr("url");
			window.open(url);
//			$.layerAlert.img("data:image/png;base64," + contentbase64, 400);
		});
		
		/**
		 * 嫌疑人详情
		 */
		$(document).on("click","#suspectDetailsBut",function(){
			var personId = $(this).attr("personId");
			var isHighrisk = $(this).attr("isHighrisk");
			if(isHighrisk=="是"){
//				sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
				window.open(context + "/yryd/showYrydHighriskPersonDetailNoMenuPage.action?idcard="+personId);
			}else{
//				sessionStorage.setItem("yrydOrdinaryPersonDetailBackUrl", window.location.href);
				window.open(context + "/yryd/showYrydOrdinaryPersonDetailNoMenuPage.action?idcard="+personId);
			}
		});
		
		/**
		 * 嫌疑人详情翻页减
		 */
		$(document).on("click","#suspectLeft",function(){
			var nowPageNum = parseInt($("#suspectPageNum").val());
			setCriminalPersonInfo(nowPageNum - 1);
			$("#suspectPageNum").val(nowPageNum - 1);
		});
		
		/**
		 * 嫌疑人详情翻页加
		 */
		$(document).on("click","#suspectRight",function(){
			var nowPageNum = parseInt($("#suspectPageNum").val());
			setCriminalPersonInfo(nowPageNum + 1);
			$("#suspectPageNum").val(nowPageNum + 1);
		});
		
		/**
		 * 涉案物品详情翻页减
		 */
		$(document).on("click","#objectLeft",function(){
			var nowPageNum = parseInt($("#objectPageNum").val());
			setCriminalObjectInfo(nowPageNum - 1);
			$("#objectPageNum").val(nowPageNum - 1);
		});
		
		/**
		 * 涉案物品详情翻页加
		 */
		$(document).on("click","#objectRight",function(){
			var nowPageNum = parseInt($("#objectPageNum").val());
			setCriminalObjectInfo(nowPageNum + 1);
			$("#objectPageNum").val(nowPageNum + 1);
		});
		
		/**
		 * 更多警情反馈
		 */
		$(document).on("click","#gdFeedback",function(){
			var feedbackArray = $(this).data("data");
			$("#gdFeedbackTable tbody").html("");
			
			$.each(feedbackArray, function(f, feedback){
				var tr = "<tr><td>" + (f+1) + "</td>";
					tr += "<td>" + feedback.fkPerson + "</td>";
					tr += "<td>" + $.date.timeToStr(feedback.feedbackTime,"yyyy-MM-dd HH:mm:ss") + "</td>";
					tr += "<td>" + feedback.fkOrderCellName + "</td>";
					tr += "<td>" + feedback.content + "</td></tr>";
				$("#gdFeedbackTable tbody").append(tr);
			});
			
			layer.open({
				type : 1,
				title : false,
				closeBtn : 1,
				anim : 2,
				shadeClose : true,
				skin : 'layui-layer-nobg', //没有背景色
				content : $('#con-gengduoFeedback'),
			});
			$(".layui-layer").css("width","600px");
		});
	});
	
	/**
	 * 根据案件编号查询案件
	 * 
	 * @param caseCode 案件编号
	 */
	function queryCriminalBasicCaseByCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalBasicCaseByCode.action',
			data:{caseCode : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cbcb = successData.resultMap.cbcb;
				
				var format = "yyyy-MM-dd HH:mm";
				cbcb.discoverTimeStart = $.util.isBlank(cbcb.discoverTimeStart)?"":$.date.timeToStr(cbcb.discoverTimeStart, format);
				cbcb.discoverTimeEnd = $.util.isBlank(cbcb.discoverTimeEnd)?"":$.date.timeToStr(cbcb.discoverTimeEnd, format);
				cbcb.caseTimeStart = $.util.isBlank(cbcb.caseTimeStart)?"":$.date.timeToStr(cbcb.caseTimeStart, format);
				cbcb.caseTimeEnd = $.util.isBlank(cbcb.caseTimeEnd)?"":$.date.timeToStr(cbcb.caseTimeEnd, format);
				$.validform.setFormTexts("#tabs-3", cbcb);
			}
		});
	}
	
	/**
	 * 根据案件编号查询涉案物品
	 * 
	 * @param caseCode 案件编号
	 */
	function queryCriminalObjectByCaseCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalObjectByCaseCode.action',
			data:{caseCode : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				cobs = successData.resultMap.cobs;
				setCriminalObjectInfo(0);
				$("#objectPageNum").val(0);
			}
		});
	}
	
	/**
	 * 设置涉案物品基本信息
	 * 
	 * @param pageNum 页码
	 */
	function setCriminalObjectInfo(pageNum){
		if(pageNum == 0 && cobs.length > 1){
			$("#objectLeft").hide();
			$("#objectRight").show();
		}else if(pageNum == 0  && cobs.length < 2){
			$("#objectLeft").hide();
			$("#objectRight").hide();
		}else if(pageNum == cobs.length - 1){
			$("#objectLeft").show();
			$("#objectRight").hide();
		}else{
			$("#objectLeft").show();
			$("#objectRight").show();
		}
		
		var cob = cobs[pageNum];
		$.each(cob, function(i, val){
			if(i == "name"){
				i = "objName";
			}
			if($.util.isBlank(val)){
				$("span[name='" + i + "']").parent("p").hide();
			}else{
				$("span[name='" + i + "']").parent("p").show();
				$("span[name='" + i + "']").text(val);
			}
		});
//		$.validform.setFormTexts("#objectDetailsDiv", cobs[pageNum]);
	}
	
	
	/**
	 * 根据案件编号查询嫌疑人
	 * 
	 * @param caseCode 案件编号
	 */
	function queryCriminalPersonByCaseCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryCriminalPersonByCaseCode.action',
			data:{caseCode : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				cpbs = successData.resultMap.cpbs;
				setCriminalPersonInfo(0);
				$("#suspectPageNum").val(0);
			}
		});
	}
	
	/**
	 * 设置嫌疑人基本信息
	 * 
	 * @param pageNum 页码
	 */
	function setCriminalPersonInfo(pageNum){
		if(pageNum == 0 && cpbs.length > 1){
			$("#suspectLeft").hide();
			$("#suspectRight").show();
		}else if(pageNum == 0  && cpbs.length < 2){
			$("#suspectLeft").hide();
			$("#suspectRight").hide();
		}else if(pageNum == cpbs.length - 1){
			$("#suspectLeft").show();
			$("#suspectRight").hide();
		}else{
			$("#suspectLeft").show();
			$("#suspectRight").show();
		}
		$(".suspectDetailsDiv").text("");
		$.validform.setFormTexts("#suspectDetailsDiv", cpbs[pageNum]);
		var personId = $.util.exist(cpbs[pageNum])?cpbs[pageNum].personId:"";
		var isHighrisk = $.util.exist(cpbs[pageNum])?cpbs[pageNum].isHighrisk:"";
		$("#suspectDetailsBut").attr("personId", personId);
		$("#suspectDetailsBut").attr("isHighrisk", isHighrisk);
		
		querySuspectQkCase(personId);
	}
	
	/**
	 * 查询嫌疑人前科案件
	 * 
	 * @param suspectCode 嫌疑人编号
	 */
	function querySuspectQkCase(suspectCode){
		var data = {
			"suspectCode" : suspectCode
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/criminalBasicCase/querySuspectQkCaseBySuspectCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cbcbs = successData.resultMap.cbcbs;
				initSuspectQkCaseTable(cbcbs);
			}
		});
	}
	
	/**
	 * 初始化嫌疑人前科案件表
	 * 
	 * @param dataArray 数据集合
	 */
	function initSuspectQkCaseTable(dataArray){
		$("#suspectQkCaseTable tbody").empty();
		if(dataArray.length < 1 || !$.util.exist(dataArray)){
			var tr = $("<tr />",{});
			$("<td />",{
				"style" : "text-align:center;" ,
				"colspan" : 4 ,
				"text" : "无结果"
			}).appendTo(tr);
			$("#suspectQkCaseTable tbody").append(tr);
			return ;
		}
		$.each(dataArray, function(d, data){
			//如果是当前案件就过滤掉不展示
			var tr = $("<tr />",{});
			if($("#lookYayd").attr("caseCode") == data.caseCode){
				return true;
			}
			var caseCodeTd = $("<td />",{
				"width" : "30%"
			}).appendTo(tr);
			$("<a />",{
				"href" : "javascript:void(0);" ,
				"caseCode" : data.caseCode ,
				"class" : "lookYayd" ,
				"text" : data.caseCode
			}).appendTo(caseCodeTd);
			var caseNameTd = $("<td />",{}).appendTo(tr);
			$("<a />",{
				"href" : "javascript:void(0);" ,
				"caseCode" : data.caseCode ,
				"class" : "lookYayd" ,
				"text" : data.caseName
			}).appendTo(caseNameTd);
			$("<td />",{
				"width" : "20%" ,
				"text" : data.caseStateName
			}).appendTo(tr);
			var caseTimeStartStr = data.caseTimeStart;
			$("<td />",{
				"width" : "25%" ,
				"text" : $.util.isBlank(caseTimeStartStr)?"":$.date.timeToStr(caseTimeStartStr, "yyyy-MM-dd HH:mm")
			}).appendTo(tr);
			$("#suspectQkCaseTable tbody").append(tr);
		});
		if($("#suspectQkCaseTable tbody tr").length < 1){
			var tr = $("<tr />",{});
			$("<td />",{
				"style" : "text-align:center;" ,
				"colspan" : 4 ,
				"text" : "无结果"
			}).appendTo(tr);
			$("#suspectQkCaseTable tbody").append(tr);
		}
	}
	
	/**
	 * 根据案件编号查询问询笔录
	 * 
	 * @param caseCode 案件编号
	 */
	function queryCrimialCaseNoteByCaseCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryCrimialCaseNoteByCaseCode.action',
			data:{caseCode : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var ccnbs = successData.resultMap.ccnbs;
				
				setCrimialCaseNoteInfo(ccnbs);
			}
		});
	}
	
	/**
	 * 设置问询笔录基本信息
	 * 
	 * @param objArray 询笔录录信息对象集合
	 */
	function setCrimialCaseNoteInfo(objArray){
		$("#crimialCaseNotes").html("");
		if($.util.isArray(objArray) && objArray.length > 0){
			$("#noteNullInfo").hide();
			$("#crimialCaseNotes").show();
			$.each(objArray,function(o,obj){
				var li = $("<li />",{});
				var p = $("<p />",{}).appendTo(li);
				$("<span />",{
					"class" : "glyphicon glyphicon-open-file color-red2"
				}).appendTo(p);
				$("<a />",{
					"href" : "javascript:void(0);" ,
					"class" : "ccnBut" ,
					"text" : obj.name ,
					"url" : obj.url
				}).appendTo(p);
				$("#crimialCaseNotes").append(li);
			});
		}else{
			$("#crimialCaseNotes").hide();
			$("#noteNullInfo").show();
		}
	}
	
	/**
	 * 根据案件查询视频信息
	 * 
	 * @param caseCode 案件编码
	 * @returns
	 */
	function findVideoByCaseCode(caseCode){
		$(".jcjsp").empty();
		$(".jcjyp").empty();

		var data = {
			"caseCode" : caseCode
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
			}	
		});
	}
	
	/**
	 * 根据案件编号查询警情基本信息
	 * 
	 * @param caseCode 案件编号
	 */
	function queryAlarmBasicByCaseCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryAlarmByCaseCode.action',
			data:{caseCode : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var jq = successData.resultMap.jq;
				
				if(jq != null){
					$("#alarmNullInfo").hide();
					$("#judgeResultNullInfo").hide();
					
					$("#alarmInfoTable").show();
					$("#judgeResultInfo").show();
					
					$(".tabs-2-jq").text("");
					$.validform.setFormTexts("#tabs-1", jq);
					jq.pcsNameAndcountryName = jq.pcsName + "，" + jq.countryName;
					$.validform.setFormTexts("#alarmAndFeedbackInfo", jq);
					
					queryAlarmJudgeResultByAlarmId(jq.id); 
					
					queryJudgeResultFeedbackByAlarmId(jq.id);
				}else{
					$("#alarmInfoTable").hide();
					$("#judgeResultInfo").hide();
					
					$("#alarmNullInfo").show();
					$("#judgeResultNullInfo").show()
				}
				
				var feedbacks = successData.resultMap.feedbacks;
				setFeedbackContent(feedbacks);
			}
		});
	}
	
	/**
	 * 设置反馈类容
	 * 
	 * @param feedbackArray 反馈数组
	 * @returns
	 */
	function setFeedbackContent(feedbackArray){
		if(feedbackArray.length < 1){
			return ;
		}
		var span = $("<span />",{
			"text" : feedbackArray[0].content
		});
		if(feedbackArray.length > 1){
			var but = $("<a />",{
				"href" : "###" ,
				"id" : "gdFeedback" ,
				"class" : "mar-left" ,
				"text" : "更多"
			});
			$("#feedbackContent").html(span[0].outerHTML + "&nbsp;&nbsp;" + but[0].outerHTML);
		}else{
			$("#feedbackContent").html(span);
		}
		$("#feedbackContent").find("a").data("data",feedbackArray);
	}
	
	/**
	 * 根据警情id查询警情研判结果
	 * 
	 * @param alarmId 警情id
	 */
	function queryAlarmJudgeResultByAlarmId(alarmId){
		var data = {
			"jqId" : alarmId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/caseJudge/queryAlarmJudgeResultByAlarmId.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var ja = successData.resultMap.ja;
				$(".tabs-2").text("");
				$.validform.setFormTexts("#tabs-2", ja);
			}
		});
	}
	
	/**
	 * 查询各单位反馈列表
	 * 
	 * @param alarmId 警情id
	 * @returns
	 */
	function queryJudgeResultFeedbackByAlarmId(alarmId){
		var data = {
			"jqId" : alarmId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/instruction/queryFeedbackByInstructionBodyId.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var irsfbs = successData.resultMap.irsfbs;
				
				initFeedbackTable(irsfbs);
			}
		});
	}
	
	/**
	 * 初始化各单位警情研判结果列表
	 * 
	 * @param dataArray 对象数组
	 * @returns
	 */
	function initFeedbackTable(dataArray){
		if($.util.exist(feedbackTable)){
			feedbackTable.destroy();
			$("#feedbackTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [
			{
				"targets" : 0,
				"width" : "",
				"title" : "接收单位",
				"data" : "receiveSubjectName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 1,
				"width" : "",
				"title" : "反馈内容",
				"data" : "feedbackContent",
				"render" : function(data, type, full, meta) {
					
					return data;
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
				
		};		
		feedbackTable = $("#feedbackTable").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseJudge.detail, { 
		queryCriminalBasicCaseByCode : queryCriminalBasicCaseByCode ,
		queryCrimialCaseNoteByCaseCode : queryCrimialCaseNoteByCaseCode ,
		queryCriminalPersonByCaseCode : queryCriminalPersonByCaseCode ,
		queryCriminalObjectByCaseCode : queryCriminalObjectByCaseCode ,
		queryAlarmBasicByCaseCode : queryAlarmBasicByCaseCode ,
		findVideoByCaseCode : findVideoByCaseCode 
	});	
})(jQuery);

/**
 * 指令板块
 */
$.caseJudge.instruct = $.caseJudge.instruct || {};
(function($){
	
	"use strict";
	
	var firstInstructTypeArray = [];// 一级指令类型
	var instructTable = null;// 指令列表
	
	$(document).ready(function() {	
		/**
		 * 指令下发
		 */
		$(document).on("click","#addInstructionBut",function(){
			addInstruction();
		});
	});
	
	/**
	 * 新建指令
	 */
	function addInstruction(){
		var arr = $.icheck.getChecked("caseCode");
		var caseCode = $(arr[0]).val();
		
		var obj = new Object();
		obj.relatedObjectId = caseCode;
		obj.relatedObjectType = $.common.XSFXTS_CONSTANT.CRIMINALBASICCASE_CLASS_NAME;
		obj.relateObjectContent = "";
		obj.callbackFunction = function(){
			if($.util.exist(instructTable)){
				instructTable.draw(true);
			}
		};
		var dateTime = new Date().getTime();
		obj.requireFeedbackTime = $.date.dateToStr(new Date(dateTime + 1000*60*60*3), "yyyy-MM-dd HH:mm");
		$.szpt.util.instruction.addInstruction(null,"xsfxts-yayyp-zlxf",obj);
	}
	
	/**
	 * 初始化指令列表
	 */
	function initInstructTable(){
		if($.util.exist(instructTable)){
			instructTable.draw(true);
			return ;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/instruction/queryInstructionPagerByBodyId.action";
		tb.columnDefs = [
			{
				"targets": 0,
		        "width": "10%",
		        "title": "发送时间",
		        "data": "createTime" ,
		        "render": function ( data, type, full, meta ) {
		         	var td = $.util.isBlank(data)?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
		         	return td;
		         }
			},
			{
				"targets": 1,
		        "width": "50%",
		        "title": "指令内容",
		        "data": "content" ,
		        "render": function ( data, type, full, meta ) {
		         	
		         	return data;
		         }
			},
			{
				"targets": 2,
		        "width": "10%",
		        "title": "指令类型",
		        "data": "typeName" ,
		        "render": function ( data, type, full, meta ) {
		         	
		         	return data;
		         }
			},
			{
				"targets": 3,
		        "width": "20%",
		        "title": "指令接收单位",
		        "data": "subs" ,
		        "render": function ( data, type, full, meta ) {
		         	var td = "";
		         	$.each(data, function(i,item){
		         		td += item.receiveSubjectName;
		         		if(i < data.length - 1){
		         			td += "、";
		         		}
					 }); 
		         	return td;
		         }
			},
			{
				"targets": 4,
		        "width": "10%",
		        "title": "要求反馈时间",
		        "data": "requireFeedbackTime" ,
		        "render": function ( data, type, full, meta ) {
		        	var td = $.util.isBlank(data)?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
		         	return td;
		         }
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 5 ];
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
			var arr = $.icheck.getChecked("caseCode");
		    d["queryStr"] = $(arr[0]).val();
		};
		tb.paramsResp = function(json) {
			var instructions = json.resultMap.instructions;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = instructions;	
		};
		tb.rowCallback = function(row,data, index) {
					
		};
		instructTable = $("#instructTable").DataTable(tb);
		
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseJudge.instruct, { 
		initInstructTable : initInstructTable 
	});	
})(jQuery);

/**
 * 研判结果反馈板块
 */
$.caseJudge.judgeResultFeedback = $.caseJudge.judgeResultFeedback || {};
(function($){
	
	"use strict";
	
	var judgeFeedbackTable = null;
	
	$(document).ready(function() {	
		/**
		 * 结果下发
		 */
		$(document).on("click",".resultSend",function(){
			var feedbackId = $(this).attr("feedbackId");
			var resultSendUnits = $(this).parents("tr").data("data").resultSendUnits;
			addInstruction(feedbackId, resultSendUnits);
		});
	});
	
	/**
	 * 新建指令
	 * 
	 * @param feedbackId 反馈id
	 * @param resultSendUnits 结果下发的单位列表
	 */
	function addInstruction(feedbackId, resultSendUnits){
		var arr = $.icheck.getChecked("caseCode");
		var caseCode = $(arr[0]).val();
		
		//设置需要单位树隐藏的单位id数组
		var hideUnitIdArray = new Array();
		$.each(resultSendUnits, function(u, unit){
			hideUnitIdArray.push(unit.id);
		});
		
		var obj = new Object();
		obj.relatedObjectId = caseCode;
		obj.relatedObjectType = $.common.XSFXTS_CONSTANT.CRIMINALBASICCASE_CLASS_NAME;
		obj.relateObjectContent = "";
		obj.feedbackId = feedbackId ;
		obj.hideUnitIdArray = hideUnitIdArray ;
		obj.callbackFunction = function(){
			$.caseJudge.instruct.initInstructTable();
			queryJudgeFeedbackResultTable();
		}
		$.szpt.util.instruction.addInstruction(null,"zlxf-ypjgxf",obj);
	}
	
	/**
	 * 根据主体id查询研判结果反馈
	 * @returns
	 */
	function queryJudgeFeedbackResultTable(){
		var arr = $.icheck.getChecked("caseCode");
		$.ajax({
			url:context +'/instruction/queryJudeResultFeedback.action',
			data:{"queryStr":$(arr[0]).val()},
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
				"width" : "10%",
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
			}, 
			{
				"targets" : 5,
				"width" : "10%",
				"title" : "操作",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					var td = '<button class="btn btn-sm btn-primary resultSend" feedbackId="' + data + '">结果下发</button>';
					return td;
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
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseJudge.judgeResultFeedback, { 
		queryJudgeFeedbackResultTable : queryJudgeFeedbackResultTable 
	});	
})(jQuery);
