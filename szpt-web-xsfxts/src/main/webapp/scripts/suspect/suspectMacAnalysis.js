$.suspectMacAnalysis = $.suspectMacAnalysis || {};
(function($){
	
	"use strict";
	
	var caseTable = null;
	var knownCaseTable = null;
	var knownCaseArray = [];
	var caseSnapshootObject = new Object();// 快照对象
	
	$(document).ready(function() {	
		initCaseTable();
		/**
		 * 查询
		 */
		$(document).on("click","#queryBut",function(){
			caseTable.draw(true);
		});
		
		/**
		 * 重置
		 */
		$(document).on("click","#resetBut",function(){
			$("#caseCode").val("");
			$("#caseName").val("");
			caseTable.draw(true);
		});
		
		/**
		 * 表格行点击事件
		 */
		$(document).on("click","#caseTable tbody tr",function(){
			var radioicheck = $(this).find("input[name='caseCode']");
			$(radioicheck).iCheck('check');
		});
		
		/**
		 * 表格行点击事件
		 */
		$(document).on("click","#knownCaseTable tbody tr",function(){
			var rowData = $(this).data("data");
			if($.util.isBlank(rowData.longitude) || $.util.isBlank(rowData.latitude)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"当前案件经纬度信息不完整。"}) ;
				return ;
			}
			$.suspectMacAnalysisMap.setMapCenterAt(rowData.longitude, rowData.latitude);
		});
		
		
		/**
		 * radio选中事件
		 */
		$(document).on("ifChecked","input[name='caseCode']",function(){
			$.suspectMacAnalysisMap.clearWifiPointLayer();
			$.suspectMacAnalysisMap.clearKnownCasePointLayer();
			$("#allMatching").html('<li class="list-group-item" style="text-align:center;"><p>无结果</p></li>');
			$("#portionMatching").html('<li class="list-group-item" style="text-align:center;"><p>无结果</p></li>');
			var caseCode = $(this).val();
			$("#caseDetailBut").attr("caseCode", caseCode);
			queryKnownCaseByCaseCode(caseCode);
			
//			queryAllPlaceBasicInfo();//TODO 测试用--完后删除
		});
		
		/**
		 * 分析按钮
		 */
		$(document).on("click","#analysisBut",function(){
			
			//取到最外层div的根据id，flag=true:验证通过
			var demo = $.validform.getValidFormObjById("knownCaseValidform") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return ;
			}
			queryWifiPointByCaseLonlat();
			
//			showCaseCircle();//TODO 测试用--完后删除
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
		 * 发送指令
		 */
		$(document).on("click",".sendInstruct",function(){
			var data = $(this).data("data");
			data["firstZllx"] = $.common.DICT.DICT_ZLLX_LDZL;
			data["secondZllx"] = $.common.DICT.DICT_ZLLX_LDZL_RYPC;
			data["content"] = "请及时到盘查地点查证。";
			$.szpt.util.instruction.addInstruction(null,"xsfxts-macAnalysis",data);
		});
		
		/**
		 * 生成快照
		 */
		$(document).on("click","#caseSnapshootBut",function(){
			var mainCaseCode = $($(".lookYayd")[0]).attr("caseCode");
			if($.util.isBlank(mainCaseCode)){
				return ;
			}
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/suspectMacAnalysisSnapshot/showNewSuperctMacAnalyusisSnapshotPage.action',
				pageLoading : true,
				title:"分析快照生成",
				width : "500px",
				height : "400px",
				btn:["保存","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.newSuperctMacAnalyusisSnapshot ;
						cm.saveSnapshot();
					},
					btn2:function(index, layero){
						$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					"caseSnapshootObject" : caseSnapshootObject ,
					"mainCaseCode" : mainCaseCode
				},
				end:function(){
					
				}
			});
			
			
		});
		
		
		
	});
	
	/**
	 * 查询所有的监控场所
	 * @returns
	 */
//	function queryAllPlaceBasicInfo(){//TODO 测试用--完后删除
//		$.ajax({
//			url:context +'/suspectMacAnalysis/queryAllPlaceBasicInfo.action',
//			data:{},
//			type:"post",
//			dataType:"json",
//		    customizedOpt:{
//			     ajaxLoading:true,//设置是否loading
//			},
//			success:function(successData){
//				var wmpbs = successData.resultMap.wmpbs;
//				//循环调用创建点方法
//				$.each(wmpbs,function(w, wi){
//					$.suspectMacAnalysisMap.addWifiPoint(wi);
//				});
//			}
//		});
//	}
	
	/**
	 * 根据主案件编码查询已知串并案
	 * 
	 * @param caseCode 案件编码
	 */
	function queryKnownCaseByCaseCode(caseCode){
		$.ajax({
			url:context +'/criminalBasicCase/queryKnownCaseByCaseCode.action',
			data:{"caseCode" : caseCode},
			type:"post",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cbcbs = successData.resultMap.cbcbs;
				caseSnapshootObject["knownCaseArray"] = cbcbs;
				initKnownCasePager(cbcbs);
			}
		});
	}
	
	/**
	 * 根据案件经纬度查询wifi点集合
	 * 
	 */
	function queryWifiPointByCaseLonlat(){
		var data = {
			"knownCaseArray" : knownCaseArray ,
			"scope" : $("#scope").val() ,
			"hour" : $("#hour").val() 
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/suspectMacAnalysis/queryWifiPointByCaseLonlat.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var wmpbs = successData.resultMap.wmpbs;
				var allMatchingList = successData.resultMap.allMatchingList;
				var portionMatchingList = successData.resultMap.portionMatchingList;
				
				setWifiMapPoint(wmpbs);
				setAnalysisResult(allMatchingList, portionMatchingList);
				//设置快照对象
				caseSnapshootObject["wmpbs"] = wmpbs;
				caseSnapshootObject["allMatchingList"] = allMatchingList;
				caseSnapshootObject["portionMatchingList"] = portionMatchingList;
				caseSnapshootObject["scope"] = $("#scope").val();
				caseSnapshootObject["hour"] = $("#hour").val();
			}
		});
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
	
	/**
	 * 在地图展示wifi地点
	 * 
	 * @param wifiInfoArray wifi信息集合
	 */
	function setWifiMapPoint(wifiInfoArray){
		$.suspectMacAnalysisMap.clearWifiPointLayer();
		//循环调用创建点方法
		$.each(wifiInfoArray,function(w, wi){
			$.suspectMacAnalysisMap.addWifiPoint(wi);
		});
	}
	
	/**
	 * 在地图展示案件地点
	 * 
	 * @param knownCaseArray 已知串并案集合
	 */
	function setKnownCaseMapPoint(knownCaseArray){
//		var lonlatArray = new Array();//TODO 测试用--完后删除
		//循环调用创建点方法
		$.each(knownCaseArray,function(k, kc){
			$.suspectMacAnalysisMap.addKnownCasePoint(kc);
			if(k == 0){
				$.suspectMacAnalysisMap.setMapCenterAt(kc.longitude, kc.latitude);
			}
//			lonlatArray.push({longitude:kc.longitude,latitude:kc.latitude});//TODO 测试用--完后删除
		});
		
//		$.suspectMacAnalysisMap.createCaseCircle(lonlatArray,$("#scope").val());//TODO 测试用--完后删除
	}
	
	/**
	 * 显示案件的圈圈
	 * @returns
	 */
//	function showCaseCircle(){//TODO 测试用--完后删除
//		var lonlatArray = new Array();
//		$.each(knownCaseArray,function(k, kc){
//			lonlatArray.push({longitude:kc.longitude,latitude:kc.latitude});
//		});
//		$.suspectMacAnalysisMap.createCaseCircle(lonlatArray,$("#scope").val());
//	}
	
	/**
	 * 初始化串并案案件页面
	 * 
	 * @param dataArray 已知串并案集合
	 */
	function initKnownCasePager(dataArray){
		if(!$.util.isArray(dataArray) || dataArray.length < 1){
			$("#knownCaseDiv").hide();
			$("#caseSnapshootBut").hide();
			$("#knownCaseExistDiv").show();
			return ;
		}
		$("#knownCaseExistDiv").hide();
		$("#knownCaseDiv").show();
		$("#caseSnapshootBut").show();
		
		knownCaseArray = dataArray;
		initKnownCaseTable(dataArray);
		setKnownCaseMapPoint(dataArray);
		
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
	
	/**
	 * 初始化案件表格
	 */
	function initCaseTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/criminalBasicCase/queryCaseByCondition.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "3%",
				"title" : "选择",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
					var td = '<input type="radio" class="icheckradio" name="caseCode" value="' + data + '">'
					return td;
				}
			},{
				"targets" : 1,
				"width" : "12%",
				"title" : "案件编号",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, {
				"targets" : 2,
				"width" : "10%",
				"title" : "案件名称",
				"data" : "caseName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, {
				"targets" : 3,
				"width" : "10%",
				"title" : "案件类别",
				"data" : "caseSortName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, {
				"targets" : 4,
				"width" : "10%",
				"title" : "案件状态",
				"data" : "caseStateName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			},
			{
				"targets" : 5,
				"width" : "8%",
				"title" : "发现时间",
				"data" : "discoverTimeStart",
				"render" : function(data, type, full, meta) {
					
					return $.date.timeToStr(data,"yyyy-MM-dd HH:mm");
				}
			},
			{
				"targets" : 6,
				"width" : "12%",
				"title" : "发案地点",
				"data" : "caseAddress",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			},
			{
				"targets" : 7,
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
				"targets" : 8,
				"width" : "10%",
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
			d["caseCode"] =  $("#caseCode").val();
			d["caseName"] =  $("#caseName").val();
		};
		tb.paramsResp = function(json) {
			var caseArray = json.resultMap.caseArray;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = caseArray;	
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		tb.drawCallback = function(){
			$("#caseDetailBut").removeAttr("caseCode");
			$("#knownCaseExistDiv").hide();
			$("#knownCaseDiv").hide();
			$("#caseSnapshootBut").hide();
		}
		caseTable = $("#caseTable").DataTable(tb);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.suspectMacAnalysis, { 
		
	});	
})(jQuery);