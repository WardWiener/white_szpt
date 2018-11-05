(function($){
	"use strict";
	var snapshot = null;
	var knownCaseTable = null;//表格
	var map = null;//地图
	var suspectMacSnapshotTable = null;// 嫌疑人mac地址分析快照列表
	$(document).ready(function() {
		initSuspectMacSnapshotInfo();
	});
	/**
	 * 查询嫌疑人MAC分析快照
	 * 
	 * @returns
	 */
	function initSuspectMacSnapshotInfo(){
		var data = {
			"caseCode" : $("#caseId").val()
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/yayd/querySuspectMacSnapshot.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var data = successData.resultMap.result;
				if(!$.util.isArray(data) || data.length == 0){
					$("#suspectMacSnapshotTableDiv").hide();
					$("#suspectMacSnapshotInfoDiv").hide();
					$("#nullSuspectMacSnapshotTableDiv").show();
				}else if(data.length == 1){
					$("#suspectMacSnapshotTableDiv").hide();
					$("#nullSuspectMacSnapshotTableDiv").hide();
					$("#suspectMacSnapshotInfoDiv").show();
					snapshot = $.util.parseJsonByJSON(data[0].snapshot);
					initKnownCasePager(getCaseArray());
					setAnalysisResult(getAllMatchingArray(),getPortionMatchingArray())
					initMap();
					$.multiBaseMap.addToMapReadyExeList(mapReady);
				}else{
					$("#suspectMacSnapshotInfoDiv").hide();
					$("#nullSuspectMacSnapshotTableDiv").hide();
					$("#suspectMacSnapshotTableDiv").show();
					initSuspectMacSnapshotTable(data);
				}
			}
		});
	}
	
	function initSuspectMacSnapshotTable(dataArray){
		if($.util.exist(suspectMacSnapshotTable)){
			suspectMacSnapshotTable.destroy();
			$("#suspectMacSnapshotTable").empty();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = dataArray;
		tb.columnDefs = [
		{
			"targets" : 0,
			"width" : "10%",
			"title" : "序号",
			"data" : "",
			"render" : function(data, type, full, meta) {
				return meta.row + 1;
			}
		}, {
			"targets" : 1,
			"width" : "25%",
			"title" : '分析人',
			"data" : "createPerson",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "25%",
			"title" : '快照生成时间',
			"data" : "createdDate",
			"render" : function(data, type, full, meta) {
				return data.replace("T"," ");
			}
		}, {
			"targets" : 3,
			"width" : "40%",
			"title" : '保存快照原因',
			"data" : "intro",
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
		suspectMacSnapshotTable = $("#suspectMacSnapshotTable").DataTable(tb);
	}
	 
	$(document).on("click","#suspectMacSnapshotTable tr",function(){
		var rowData =  $(this).data("data")
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/yayd/showXyyMacAddressFxDetail.action',
			pageLoading : true,
			title:"嫌疑人MAC地址分析",
			width : "1000px",
			height : "670px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
			
			},
			initData:{
				rowData : rowData
			},
			end:function(){
				
			}
		});
	});
	
	
	function initMap(){
		$.multiBaseMap.init(["mapContent"]);
	}
	//地图加载完成加载
	var mapReady = function (){
		map = $.multiBaseMap.getInstance("mapContent");
		map.setLoadCallback(function(e){
			$.xyyMacAddressMap.newPlaceInfoPoint(map,getCaseArray());
		});
	}
	//创建串并案案件表格
	function initKnownCasePager(data){
		initKnownCaseTable(data);		
	}
	
	//通过快照字符串转list
	function getCaseArray(){
		var array = [];
		for(var i in snapshot.knownCaseArray){
			array.push(snapshot.knownCaseArray[i]);
		}
		return array;
	}
	
	//通过快照得到所有案件完全匹配list
	function getAllMatchingArray(){
		var array = [];
		for(var i in snapshot.allMatchingList){
			array.push(snapshot.allMatchingList[i]);
		}
		return array;
	}
	//通过快照得到部分案件匹配list
	function getPortionMatchingArray(){
		var array = [];
		for(var i in snapshot.portionMatchingList){
			array.push(snapshot.portionMatchingList[i]);
		}
		return array;
	}
	
	/**
	 * 初始化已知串并案Table
	 * 
	 * @param dataArray 数据集合
	 */
	function initKnownCaseTable(dataArray){
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = dataArray;
		tb.columnDefs = [
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
		//是否排序
		tb.ordering = false ;
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
	
		knownCaseTable = $("#knownCaseTable").DataTable(tb);
	}
	
	
	/**
	 * 设置分析结果
	 */
	function setAnalysisResult(allMatchingArray, portionMatchingArray){
		//所有案件完全匹配
		if($.util.isArray(allMatchingArray) && allMatchingArray.length > 0){
			$("#allMatching").html("");
			$.each(allMatchingArray,function(c, cmmrb){
				var liTemplate = $("#allMatchingTemplate li").eq(0);
				var li = liTemplate.clone(true);
				$.each(cmmrb, function(k, val){
					$(li).find("span[name='" + k + "']").text(val);
				});
				if($.util.isBlank(cmmrb.name)){
					$(li).find("button").hide();
				}else{
					$(li).find("button").data("data",cmmrb);
				}
				$("#allMatching").append(li);
			});
		}
		//部分案件匹配
		if($.util.isArray(portionMatchingArray) && portionMatchingArray.length > 0){
			$("#portionMatching").html("");
			$.each(portionMatchingArray,function(c, cmmrb){
				/*var caseCodeStr = "";
				var caseCodes = cmmrb.matchingCaseCodes;
				for(var i=0;i<caseCodes.length;i++){
					caseCodeStr += caseCodes[i];
					if(i < caseCodes.length - 1){
						caseCodeStr += "、";
					}
				}
				cmmrb.caseCodes = caseCodeStr;*/
				var liTemplate = $("#portionMatchingTemplate li").eq(0);
				var li = liTemplate.clone(true);
				$.each(cmmrb, function(k, val){
					if(!$.util.isBlank(val)){
						$(li).find("span[name='" + k + "']").text(val);
					}
				});
				if($.util.isBlank(cmmrb.name)){
					$(li).find("button").hide();
				}else{
					$(li).find("button").data("data",cmmrb);
				}
				$("#portionMatching").append(li);
			});
		}
	}
	
})(jQuery);