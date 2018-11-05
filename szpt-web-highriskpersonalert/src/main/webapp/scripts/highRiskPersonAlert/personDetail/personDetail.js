(function($){
	"use strict";
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	var fiveColorPerson_locus_layer ;//重点人轨迹点图层
	
	var heatMap ;//热力地图实例对象
	var heatBaseMapServiceLayer ;//热力地图基础图层
	var fiveColorPerson_heat_layer ;//重点人热力点图层
	var line_locus_layer;
	
	var idcode = null; //重点人id
	var personBean = null; //重点人bean
	var checkControlTable = null;// 查控情况列表Table
	var locusTable = null; //重点人轨迹列表Table
	var tableOrder = 1; //列表序号
	var wifiFenceHitBeanList = new Array();//wifi围栏命中信息Bean集合
	var isLocusInfoPage = true;//是否是轨迹信息页面
	var startTime;
	var endTime;
	var macList = new Array();
	var personId;
	var heatOnly=false;
	$(document).ready(function() {	
		idcode = $("#paraIdcode").val();
		personId = $("#paraPersonId").val();
		//指令跳转
		if(!$.util.isBlank(personId)&&personId!="null"){
			idcode=findIdCodeByPersonId(personId);
		}
		//初始化热力地图
		initHeatMap();
		//初始化轨迹地图
		initLocusMap();
		if(!$.util.isBlank(idcode)&&idcode!="null"){
			initPageField();
		}
		//初始化查控
		findCheckControl();
		/**
		 * 查控按钮
		 */
		$(document).on("click","#checkControl",function(){
			if(!$.util.exist(personBean)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请先查询人员信息。"}) ;
				return ;
			}
			var data = new Object();
			data["firstZllx"] = $.common.DICT.DICT_ZLLX_LDZL;
			data["secondZllx"] = $.common.DICT.DICT_ZLLX_LDZL_RYPC;
			data["name"] = personBean.name;
			data["idcode"] = personBean.idcode;
			
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/instruction/showNewInstructionPage.action',
				pageLoading : true,
				title:"新增指令",
				width : "450px",
				height : "480px",
				btn:["下发","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.szpt.util.newnIstruction ;
						cm.save();
					},
					btn2:function(index, layero){
						$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			id : null,
	    			type : "gwrqfx-ryda-ck",
	    			typeSetContent : data
	    		},
	    		end:function(){
	    			
	    		}
			});
		});
		//返回
		$(document).on("click", "#backViewBtn", function(){
			window.top.location.href = url;
		})
		
		$(document).on("click","#edit",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return false;
			}
			window.top.$.layerAlert.dialog({
				content : context +  '/personDetail/showPersonEditPage.action',
				pageLoading : true,
				title:"人员打标",
				width : "650px",
				height : "95%",
				btn:["确认","返回"],
				callBacks:{
					btn1:function(index, layero){
						var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
						cm.save();
//						initPageField();
					},
					btn2:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			personId : personId
	    		},
	    		end:function(){
	    		}
			});
		});
		/**
		 * 近一天按钮事件
		 */
		$(document).on("click","#recentlyDay",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return 
			}
			startTime = getRecentlyDay().startTime;
			endTime = getRecentlyDay().endTime;
			findPlaceSumByMacAndTime(macList, startTime, endTime);
			findMaxTimePlaceByMacAndTime(macList, startTime, endTime);
			findAllLocusByMacAndTime(macList, startTime, endTime)
			locusTable.draw(true);
		});
		/**
		 * 近一周按钮事件
		 */
		$(document).on("click","#recentlyWeek",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return 
			}
			startTime = getRecentlyWeek().startTime;
			endTime = getRecentlyWeek().endTime;
			findPlaceSumByMacAndTime(macList, startTime, endTime);
			findMaxTimePlaceByMacAndTime(macList, startTime, endTime);
			findAllLocusByMacAndTime(macList, startTime, endTime)
			locusTable.draw(true);
		});
		/**
		 * 近一月按钮事件
		 */
		$(document).on("click","#recentlyMonth",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return 
			}
			startTime = getRecentlyMonth().startTime;
			endTime = getRecentlyMonth().endTime;
			findPlaceSumByMacAndTime(macList, startTime, endTime);
			findMaxTimePlaceByMacAndTime(macList, startTime, endTime);
			findAllLocusByMacAndTime(macList, startTime, endTime)
			locusTable.draw(true);
		});
		/**
		 * 热力图查询按钮点击事件
		 */
		$(document).on("click","#heatSearch",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return 
			}
			var startTimeHeat = $.laydate.getTime("#heatDateRange", "start");
			var endTimeHeat = $.laydate.getTime("#heatDateRange", "end");
			heatOnly=true;
			findAllLocusByMacAndTime(macList, startTimeHeat, endTimeHeat);
		});
		/**
		 * 重置查询条件
		 */
		$(document).on("click","#resetTime",function(){
			$.laydate.reset("#heatDateRange");
		});
		$(document).on("click",".heatTime",function(){
			if($.util.isBlank(idcode)||idcode=="null"){
				return 
			}
			var time;
			if(this.id=="recentlyDayHeat"){
				time = getRecentlyDay();
			}else if(this.id=="recentlyWeekHeat"){
				time = getRecentlyWeek();
			}else if(this.id=="recentlyMonthHeat"){
				time = getRecentlyMonth();
			}else if(this.id=="recentlySixMonthHeat"){
				time = getRecentlySixMonth();
			}
			var startTimeHeat = time.startTime;
			var endTimeHeat = time.endTime;
			heatOnly=true;
			findAllLocusByMacAndTime(macList, startTimeHeat, endTimeHeat);
		});
		/**
		 * 查询他人按钮事件
		 */
		$(document).on("click","#search",function(){
			var demo = $.validform.getValidFormObjById("validform") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return false;
			}
			idcode = $("#inputIdCode").val();
			initPageField();
			findCheckControl();
			return false;
		});
		/**
		 * 轨迹信息tabs按钮事件
		 */
		$(document).on("click","#locusInfoTabsLi",function(){
			isLocusInfoPage = true;
		});
		/**
		 * 轨迹地图tabs按钮事件
		 */
		$(document).on("click","#locusMapTabsLi",function(){
			newLocusPoint(wifiFenceHitBeanList);
		});
		/**
		 * 轨迹地图tabs按钮事件
		 */
//		$(document).on("click","#heatMapTabsLi",function(){
//			isLocusInfoPage = false;
//			var startTime = getRecentlyYear().startTime;
//			var endTime = getRecentlyYear().endTime;
//			findAllLocusByMacAndTime(macList, startTime, endTime);
//		});
		
		$(document).on("ifChanged",".icheckbox",function(){
			macList = new Array();
			var checked = $.icheck.getChecked("mac")
			for(var i in checked){
				macList.push($(checked[i]).val());
			}
			locusTable.draw(true);
			findPlaceSumByMacAndTime(macList, startTime, endTime);
			findMaxTimePlaceByMacAndTime(macList, startTime, endTime);
			var startTimeHeat = $.laydate.getTime("#heatDateRange", "start");
			var endTimeHeat = $.laydate.getTime("#heatDateRange", "end");
			heatOnly=false;
			findAllLocusByMacAndTime(macList, startTimeHeat, endTimeHeat);
		});
		$(document).on("click",".criminalRecordMessage",function(){ //点击前科 案件名称进入案件详情页面
			var caseId=$(this).attr("caseId");
			window.location.href = $.util.fmtUrl(context+"/yayd/showYaydDetailPage.action?caseId="+caseId);
		});
		
	});
	
	/**
	 * 查询查控记录
	 * @returns
	 */
	function findCheckControl(){
		$.ajax({
			url:context +'/personDetail/findPersonCheckInfoByPersonIdcode.action',
			data:{idcode : idcode},
			type:"post",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var pcibs = successData.resultMap.pcibs;
				initCheckControlTable(pcibs);
			}
		});
		
	}
	
	/**
	 * 初始化查控情况表
	 * 
	 * @param dataArray
	 * @returns
	 */
	function initCheckControlTable(dataArray){
		
		if($.util.exist(checkControlTable)){
			checkControlTable.destroy();
			$("#checkControlTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
			st1.data = dataArray;
			st1.columnDefs = [
			{
				"targets" : 0,
				"width" : "10%",
				"title" : "序号",
				"data" : "",
				"render" : function(data, type, full, meta) {
					
					return meta.row + 1;
				}
			}, 
			{
				"targets" : 1,
				"width" : "40%",
				"title" : "查控内容",
				"data" : "checkAddress",
				"render" : function(data, type, full, meta) {
					var str = "";
					if(!$.util.isBlank(full.involveDrugName)){
						str += "涉毒：" + full.involveDrugName ;
					}
					if(!$.util.isBlank(full.involveCriminalRecordName)){
						if(!$.util.isBlank(str)){
							str += "；"
						}
						str += "刑事前科：" + full.involveCriminalRecordName ;
					}
					if(!$.util.isBlank(full.colorTypeName)){
						if(!$.util.isBlank(str)){
							str += "；"
						}
						str += "五色预警类型：" + full.colorTypeName ;
					}
					return str;
				}
			}, 
			{
				"targets" : 2,
				"width" : "25%",
				"title" : "查控单位",
				"data" : "unitName",
				"render" : function(data, type, full, meta) {
					
					return data;
				}
			}, 
			{
				"targets" : 3,
				"width" : "25%",
				"title" : "查控时间",
				"data" : "interrogatDate",
				"render" : function(data, type, full, meta) {
					return $.util.isBlank(data)?"":$.date.timeToStr(data,"yyyy-MM-dd HH:mm:ss");
				}
			}];
			st1.ordering = false;
			st1.paging = true; // 是否分页
			st1.info = true; // 是否显示表格左下角分页信息
			st1.autoFoot = false;
			st1.dom = null;
			st1.searching = false;
			st1.lengthChange = false;
			st1.lengthMenu = [ 5 ];
			st1.rowCallback = function(row,data, index) {
						
			};		
			checkControlTable = $("#checkControlTable").DataTable(st1);
	}
	
	/**
	 * 初始化轨迹信息表格
	 */
	function initLocusTable(){
		if(locusTable != null) {
			locusTable.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/personDetail/findLocusByMacs.action";
		tb.columnDefs = [
			{
				"targets": 0,
     	    	"width": "50px",
     	    	"title": "序号",
     	    	"className":"table-checkbox",
     	    	"data": "" ,
     	    	"render": function ( data, type, full, meta ) {
     	    			  return tableOrder ++;
     	    	}
			},
			{
				"targets" : 1,
				"width" : "315px",
				"title" : "场所名称",
				"data" : "placeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "100px",
				"title" : "进入时间",
				"data" : "enterTime",
				"render" : function(data, type, full, meta) {
					var data = $.date.timeToStr(data, "yyyy-MM-dd HH:mm:ss");
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "100px",
				"title" : "离开时间",
				"data" : "leaveTime",
				"render" : function(data, type, full, meta) {
					var data = $.date.timeToStr(data, "yyyy-MM-dd HH:mm:ss");
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 10 ];
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
			var data = {
					"startTime" : startTime,
					"endTime" : endTime,
					"idcode" : null == idcode ? null : idcode,
					"macList" : null == macList ? []:macList,
					"start":d.start,
					"pageSize":d.length
				}
				var queryStr = $.util.toJSONString(data);
				$.util.objToStrutsFormData(queryStr,"queryStr",d);
//			tableOrder = 1;
//			startTime = getRecentlyDay().startTime;
//			endTime = getRecentlyDay().endTime;
//			if($.util.isBlank(idcode)||idcode=="null"){
//				return 
//			}else{
//				$.each(macList,function(m,mac){
//					d["macList["+m+"]"] = mac;
//				});
//			}
		};
		tb.paramsResp = function(json) {
			var wifiPlaceInAndOutInfoBeanList = json.resultMap.list;
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = wifiPlaceInAndOutInfoBeanList;
			newLocusPoint(wifiPlaceInAndOutInfoBeanList);
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		locusTable = $("#locusTable").DataTable(tb);
	}
	function findIdCodeByPersonId(personId){
		var idcode="";
		$.ajax({
			url:context +'/highriskPerson/findPersonByPersonId.action',
			type:'post',
			dataType:'json',
			data:{id : personId},
			async:false,
			success:function(successData){
				personBean = successData.highriskPersonBean;
				if($.util.exist(personBean)){
					idcode=personBean.idcode;
				}else{
					window.top.$.layerAlert.alert({msg:"未查到相关人员"}) ;
				}
			},
			error:function(errorData){
				
			}
		});
		return idcode;
	}
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(idcode)){//查看
			$.ajax({
				url:context +'/highriskPerson/findPersonByIdcode.action',
				type:'post',
				dataType:'json',
				data:{idcode : idcode},
				success:function(successData){
					personBean = successData.highriskPersonBean;
					if($.util.exist(personBean)){
						setAllField(personBean);
						
						//查询某人员某时间段经过次数最多的场所
						macList = new Array();
						macList=personBean.mac;
						startTime = getRecentlyDay().startTime;
						endTime = getRecentlyDay().endTime;
						//初始化轨迹表格
						initLocusTable();
				
						findPlaceSumByMacAndTime(macList, startTime, endTime);
						
						findMaxTimePlaceByMacAndTime(macList, startTime, endTime);
						
//						var startTime = getRecentlyYear().startTime;
//						var endTime = getRecentlyYear().endTime;
						heatOnly=false;
						findAllLocusByMacAndTime(macList, startTime, endTime);
						
					}else{
						window.top.$.layerAlert.alert({msg:"未查到相关人员"}) ;
					}
					
					
				},
				error:function(errorData){
					
				}
			});
		}
	}
	
	/**
	 * 设置重点人基本信息所有字段$.util.isBlank(object.)?"":
	 */
	function setAllField(object){
		personId = object.id;
		$("#name").text($.util.isBlank(object.name)?"":object.name);
		$("#idCode").text(object.idcode);
		$("#sex").text($.util.isBlank(object.sexName)?"":object.sexName);
		$("#criminalRecord").text($.util.isBlank(object.criminalRecordName)?"":object.criminalRecordName);
		$("#status").text($.util.isBlank(object.statusName)?"":object.statusName);
		$("#liveAddress").text($.util.isBlank(object.liveAddress)?"":object.liveAddress);
		$("#registerAddress").text($.util.isBlank(object.registerAddress)?"":object.registerAddress);
		$("#profession").text($.util.isBlank(object.professionName)?"":object.professionName);
		$("#income").text($.util.isBlank(object.income)?"":object.income + " ￥");
		$("#createdTime").text($.date.timeToStr(object.createdTime, "yyyy/MM/dd"));
		$("#urineTest").text($.util.isBlank(object.urineTest)?"":object.urineTest);
		$("#peopleType").text($.util.isBlank(object.peopleTypeName)?"":object.peopleTypeName);
		var phone = "";
		$.each(object.phone,function(i,val){
			if(val!=""&&val!="null"&&val!=null){
				phone += val + "<br/>";
			}
		});
		if(phone.length>0){
			$("#phone").html(phone.substring(0,phone.length-5));
		}

		var mac = "";
		$("#macs").html("");
		$.each(object.mac,function(i,val){
			if(val!=""&&val!="null"&&val!=null){
				mac += val + "<br/>";
				$("#macs").append('<input checked type="checkbox" class="icheckbox" name="mac" value="'+val+'"id=""><label for="ip-1" style="margin-left:10px; margin-right:30px;" class="">'+val+'</label>');
			}
		});
		if(mac.length>0){
			$("#mac").html(mac.substring(0,mac.length-5));
		}
		
		switch (object.warnType){
			case $.common.Constant.YJLX_HONG_SE():
				$("#warnType").text(object.warnTypeName);
				$("#warnType").addClass("btn sq-red selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_CHENG_SE():
				$("#warnType").text(object.warnTypeName);
				$("#warnType").addClass("btn sq-orange selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_HUANG_SE():
				$("#warnType").text(object.warnTypeName);
				$("#warnType").addClass("btn sq-yellow selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_BAI_SE():
				$("#warnType").text(object.warnTypeName);
				$("#warnType").addClass("btn sq-white selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_LAN_SE():
				$("#warnType").text(object.warnTypeName);
				$("#warnType").addClass("btn sq-blue selected");
				$("#warnType").parent("div").show();
				break;
			default:
				$("#warnType").parent("div").hide();
		}
		
//		if($.util.exist(object.attachList)){
//			for(var i in object.attachList){
//				$("#fcpAttach").append("<div><a href='###' class='fcpDlws' fileId='" + object.attachList[i].id + "'>" + object.attachList[i].name + "</a></div>");
//			}
//			$(document).on("click",".fcpDlws",function(){
//				window.open(context + "/highriskPerson/downloadFile.action?attachmentId="+ $(this).attr("fileId"));					
//			});
//		}else{
//			$("#fcpAttach").html("");
//		}
	}
	
	/**
	 * 清空页面字段
	 */
	function clearAllField(){
		$("#name").text("");
		$("#idCode").text("");
		$("#sex").text("");
		$("#criminalRecord").text("");
		$("#status").text("");
		$("#liveAddress").text("");
		$("#registerAddress").text("");
		$("#profession").text("");
		$("#income").text("￥");
		$("#phone").html("");
		$("#createdTime").text("");
		$("#urineTest").text("");
		$("#peopleType").text("");
		$("#terminalDeviceInfo").text("");
		$("#warnType").parent("div").hide();
		$("#placeFrequencyOrderTable tbody").html("");
		$("#maxTimeplaceOrderTable tbody").html("");
		$("#macs").html("");
	}
	
	/**
	 * 查询某人员某时间段经过次数最多的场所
	 * @param macList mac地址集合
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 */
	function findPlaceSumByMacAndTime(macList, startTime, endTime){
		var gData = new Object();
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(macList, "macList", gData);
		$.ajax({
			url:context +'/personDetail/findPlaceSumByMacAndTime.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				var hitSumBeanList = successData.resultMap.list;
				setPlaceFrequencyOrderList(hitSumBeanList);
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 设置该人员经过次数最多的场所列表
	 * @param hitSumBeanList 场所次数降序列表
	 */
	function setPlaceFrequencyOrderList(hitSumBeanList){
		console.log(hitSumBeanList);
		if(hitSumBeanList == null || hitSumBeanList.length <= 0 || hitSumBeanList == undefined){
			$("#placeFrequencyOrderTable tbody").html("无轨迹记录");
			$("#placeFrequencyOrderTable").css("border-top","none") ;
			return;
		}
		$("#placeFrequencyOrderTable tbody").html("");
		$.each(hitSumBeanList,function(h,hitSumBean){
			if(h >= 3){
				return false;
			}
			if(hitSumBean.count < 1 || $.util.isBlank(hitSumBean.placeName)){
				return true;
			}
			var tr = $("<tr>",{});
			$("<td>",{
				"width" : "70%",
				"text" : hitSumBean.placeName
			}).appendTo(tr);
			var td = $("<td>",{}).appendTo(tr);
			var tdDiv = $("<div>",{
				"class" : "fi-ceng-out"
			}).appendTo(td);
			//总数button
			$("<button>",{
				"class" : "btn btn-xs btn-bordered fi-ceng-out-but",
				"text" : hitSumBean.count + " 次"
			}).appendTo(tdDiv);
			//详细时间表div
			var timeInfoDiv = $("<div>",{
				"class" : "fi-ceng alert-info"
			}).appendTo(tdDiv);
			//标题
			$("<h4>",{
				"class" : "row-mar",
				"text" : hitSumBean.placeName
			}).appendTo(timeInfoDiv);
			//时间表ul
			var timeUl = $("<ul>",{
				"class" : "list-group"
			}).appendTo(timeInfoDiv);
			
			$.each(hitSumBean.timeIntervalList,function(t,timeInterval){
				//时间表li
				var timeLi = $("<li>",{
					"class" : "list-group-item"
				}).appendTo(timeUl);
				//时间表信息
				$("<span>",{
					"class" : "badge alert-warning",
					"text" : t + 1
				}).appendTo(timeLi);
				$("<span>",{
					"text" : "mac地址：" + timeInterval.macAddress
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "进入时间：" + $.date.timeToStr(timeInterval.enterTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "离开时间：" + $.date.timeToStr(timeInterval.leaveTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
			});
			$("#placeFrequencyOrderTable tbody").append(tr);
		});
	}
	
	/**
	 * 查询某人员某时间段驻留时间最长的场所
	 * @param macList mac地址集合
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 */
	function findMaxTimePlaceByMacAndTime(macList, startTime, endTime){
		var gData = new Object();
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(macList, "macList", gData);
		$.ajax({
			url:context +'/personDetail/findMaxTimePlaceByMacAndTime.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				var hitSumBeanList = successData.resultMap.list;
				setMaxTimePlaceOrderList(hitSumBeanList);
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 查询某个人某个时间范围内所有的轨迹记录(热力图)
	 * @param macList mac地址集合
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 */
	function findAllLocusByMacAndTime(macList, startTime, endTime){
		var gData = new Object();
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(macList, "macList", gData);
		$.ajax({
			url:context +'/personDetail/findLocusMapByMacs.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				var wifiPlaceInAndOutInfoBeanList = successData.resultMap.list;
				showMapHeats(wifiPlaceInAndOutInfoBeanList);
				if(!heatOnly){
					newLocusPoint(wifiPlaceInAndOutInfoBeanList);
				}
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 设置该人员驻留时间最长的场所列表
	 * @param wifiFenceHitBeanList wifi围栏命中信息Bean集合
	 */
	function setMaxTimePlaceOrderList(hitSumBeanList){
        if(hitSumBeanList == null ||  hitSumBeanList.length<=0 || hitSumBeanList == undefined) {
        	$("#maxTimeplaceOrderTable tbody").html("无轨迹记录");
        	$("#maxTimeplaceOrderTable").css("border-top","none") ;
        	return;
        }
		$("#maxTimeplaceOrderTable tbody").html("");
		if(!$.util.isArray(hitSumBeanList) || hitSumBeanList < 1){
			return ;
		}
		//按照间距排序
		hitSumBeanList.sort(function(a,b){
	        return b.stayInterval-a.stayInterval
	    });
		
		$.each(hitSumBeanList,function(w,hitSumBean){
			if(w >= 3){
				return false;
			}
			var tr = $("<tr>",{});
			$("<td>",{
				"width" : "70%",
				"text" : hitSumBean.placeName
			}).appendTo(tr);
			var td = $("<td>",{}).appendTo(tr);
			var tdDiv = $("<div>",{
				"class" : "fi-ceng-out"
			}).appendTo(td);
			//总时间button
			$("<button>",{
				"class" : "btn btn-xs btn-bordered fi-ceng-out-but",
				"text" : millisecondToDate(hitSumBean.stayInterval)
			}).appendTo(tdDiv);
//			//详细时间表div
//			var timeInfoDiv = $("<div>",{
//				"class" : "fi-ceng alert-info"
//			}).appendTo(tdDiv);
//			//标题
//			$("<h4>",{
//				"class" : "row-mar",
//				"text" : hitSumBean.place
//			}).appendTo(timeInfoDiv);
//			$("<span>",{
//				"text" : "进入时间：" + $.date.timeToStr(hitSumBean.startTime,"yyyy-MM-dd HH:mm:ss")
//			}).appendTo(timeInfoDiv);
//			$("<br>").appendTo(timeInfoDiv);
//			$("<span>",{
//				"text" : "离开时间：" + $.date.timeToStr(hitSumBean.endTime,"yyyy-MM-dd HH:mm:ss")
//			}).appendTo(timeInfoDiv);
			//详细时间表div
			var timeInfoDiv = $("<div>",{
				"class" : "fi-ceng alert-info"
			}).appendTo(tdDiv);
			//标题
			$("<h4>",{
				"class" : "row-mar",
				"text" : hitSumBean.placeName
			}).appendTo(timeInfoDiv);
			//时间表ul
			var timeUl = $("<ul>",{
				"class" : "list-group"
			}).appendTo(timeInfoDiv);
			
			$.each(hitSumBean.timeIntervalList,function(t,timeInterval){
				//时间表li
				var timeLi = $("<li>",{
					"class" : "list-group-item"
				}).appendTo(timeUl);
				//时间表信息
				$("<span>",{
					"class" : "badge alert-warning",
					"text" : t + 1
				}).appendTo(timeLi);
				$("<span>",{
					"text" : "mac地址：" + timeInterval.macAddress
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "进入时间：" + $.date.timeToStr(timeInterval.enterTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "离开时间：" + $.date.timeToStr(timeInterval.leaveTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
			});
			$("#maxTimeplaceOrderTable tbody").append(tr);
		});
	}
	
	
	/**
	 * 获取最近一天时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyDay(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近一周时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyWeek(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*7;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近一月时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyMonth(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*31;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近半年时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlySixMonth(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*31*6;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近1年时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyYear(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*365;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 初始化热力图
	 */
	function initHeatMap(){
		var callback = function(arcgisEsri){
	        //初始化map
			heatMap = initBaseMap("locusHotMapConten");
	        //map事件
	        heatMap.on("click", function(e){
	        	$.common.arcgisMapCommon.consoleLogCoordinate(e);
	        });
		    
	        heatMap.on("load", function(e){
		    	var mapPoint = arcgisEsri.Point(11876079.465427278, 3062348.6424484807, heatMap.spatialReference);
		    	heatMap.centerAt(mapPoint);//将地图中心定位到经开区中心
		    }); 
	        
	        //地图底图
	        heatBaseMapServiceLayer = initBaseMapLayer();
	        heatMap.addLayer(heatBaseMapServiceLayer);
	        //热力图
	        var heatmapRenderer = new arcgisEsri.HeatmapRenderer({  
		           // field: "ID",  
		           blurRadius: 10,  
		           maxPixelIntensity: 30,  
		           minPixelIntensity: 0  
			    }); 
	        
	        //重点人热力点图层
	        fiveColorPerson_heat_layer = new arcgisEsri.FeatureLayer({
	        	   layerDefinition:{
	            	   "geometryType": "esriGeometryPoint",  
		               "fields": ["*"]  
	               },
	               featureSet:null
	        	}, {
	        	   mode: arcgisEsri.FeatureLayer.MODE_SNAPSHOT,
			       outFields: ["*"]
	        }) ;
	        fiveColorPerson_heat_layer.setRenderer(heatmapRenderer);      
	        heatMap.addLayer(fiveColorPerson_heat_layer); 
		}
		
		$.common.arcgisMapCommon.newArcgisMap(callback);
	}
	
	/**
	 * 初始化轨迹地图
	 */
	function initLocusMap(){
		var callback = function(arcgisEsri){
	        //初始化map
			locusMap = initBaseMap("locusMapConten");
	        //map事件
	        locusMap.on("click", function(e){
	        	$.common.arcgisMapCommon.consoleLogCoordinate(e);
	        });
		    
	        locusMap.on("load", function(e){
		    	var mapPoint = arcgisEsri.Point(11876079.465427278, 3062348.6424484807, locusMap.spatialReference);
		    	locusMap.centerAt(mapPoint);//将地图中心定位到经开区中心
		    }); 
	        
	        //地图底图
	        locusBaseMapServiceLayer = initBaseMapLayer();
	        locusMap.addLayer(locusBaseMapServiceLayer);
	        
	        //重点人轨迹infoTemplate
		    var fiveColorPerson_locus_Template = new arcgisEsri.InfoTemplate({
	        	title:"重点人轨迹详细信息",
	        	content:function(arg0){
		        	var wifiFenceHitBean = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:300px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(wifiFenceHitBean)){
		        		return baseDiv[0] ;
		        	}
		        	//场所名称
		        	if(!$.util.isBlank(wifiFenceHitBean.place)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"场所名称："+wifiFenceHitBean.place
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//MAC
		        	if(!$.util.isBlank(wifiFenceHitBean.place)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"MAC地址："+wifiFenceHitBean.mac
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//进入时间
		        	if(!$.util.isBlank(wifiFenceHitBean.enterTime)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"进入时间："+$.date.timeToStr(wifiFenceHitBean.enterTime, "yyyy-MM-dd HH:mm:ss")
			        	}).appendTo(baseDiv_contentDiv);
		        	}
				    //离开时间
		        	if(!$.util.isBlank(wifiFenceHitBean.leaveTime)){
		        		$("<div />", {
			        		"class":"row",
			        		"style":"margin-top:5px",
			        		"text":"离开时间："+$.date.timeToStr(wifiFenceHitBean.leaveTime, "yyyy-MM-dd HH:mm:ss")
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	return baseDiv[0] ;
	        	}
		    }); 
	        
	        //重点人轨迹点图层
	        fiveColorPerson_locus_layer = new arcgisEsri.GraphicsLayer({ 
		    	id: "fiveColorPerson_locus_layer",
		    	infoTemplate:fiveColorPerson_locus_Template
		    });
	        locusMap.addLayer(fiveColorPerson_locus_layer);
	        
	        line_locus_layer = new arcgisEsri.GraphicsLayer({ 
		    	id: "line_locus_layer",
		    	infoTemplate:null
		    });
	        locusMap.addLayer(line_locus_layer);
		}
		
		$.common.arcgisMapCommon.newArcgisMap(callback);
		
	}
	
	/**
	 * 显示热力图层
	 * @param list 轨迹点
	 */
	function showMapHeats(list){
		if(!$.util.exist(fiveColorPerson_heat_layer)){
			return ;
		}
		fiveColorPerson_heat_layer.clear() ;
		fiveColorPerson_heat_layer.redraw() ;
		
		$.each(list, function(i, val){
			if($.util.isBlank(val.longitude) || $.util.isBlank(val.latitude)){
				return true;
			}
			var longitude = val.longitude;
			var latitude = val.latitude;
			var layer = fiveColorPerson_heat_layer;
			var symbolObj = null;
			var data = null;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, heatMap, symbolObj, data);
			if(i < 1 && $.util.exist(heatMap)){
				$.common.arcgisMapCommon.setMapCentreAtByLonlat(longitude, latitude, heatMap);//将地图中心定位到监测中心
			}
		});
	}
	
	/**
	 * 显示轨迹点
	 * @param wifiPlaceInAndOutInfoBeanList wifi围栏命中信息Bean集合
	 */
	function newLocusPoint(wifiPlaceInAndOutInfoBeanList){
		if(!$.util.exist(wifiPlaceInAndOutInfoBeanList) || !$.util.exist(fiveColorPerson_locus_layer)){
			return ;
		}
		fiveColorPerson_locus_layer.clear();
		$.each(wifiPlaceInAndOutInfoBeanList,function(w,wifiFenceHitBean){
			var longitude = wifiFenceHitBean.longitude;
			var latitude = wifiFenceHitBean.latitude;
			var layer = fiveColorPerson_locus_layer;
			var symbolObj = {url:context+"/images/map/map-icon-trail.png", width:22, height:35};
			var data = wifiFenceHitBean;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
			if(w < 1 && $.util.exist(locusMap)){
				$.common.arcgisMapCommon.setMapCentreAtByLonlat(longitude, latitude, locusMap);//将地图中心定位到监测中心
			}
		});
		$.common.arcgisMapCommon.drawLine(line_locus_layer, locusMap, wifiPlaceInAndOutInfoBeanList);
	}
	

	function timeToDate(time){
		var str = parseInt(time / 86400.0) + "天 ";
		time -= parseInt(time / 86400.0) * 86400;
		return str + timeToHour(time);
	}
	
	function timeToHour(time){
		var str = parseInt(time / 3600.0) + "小时 ";
		time -= parseInt(time / 3600.0) * 3600;
		return str + timeToMinute(time);
	}
	
	function timeToMinute(time){
		var str = parseInt(time / 60.0) + "分钟 ";
		time -= parseInt(time / 60.0) * 60;
		return str + timeToSecond(time);
	}
	
	function timeToSecond(time){
		return time + "秒";
	}
	
	function millisecondToDate(msd) {
	    var time = parseFloat(msd) / 1000;
	    if(time > 86400){
	    	return timeToDate(time);
	    }else if(time > 3600){
	    	return timeToHour(time);
	    }else if(time > 60){
	    	return timeToMinute(time);
	    }else{
	    	return timeToSecond(time);
	    }
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		refresh : function(){
			initPageField();
		}
	});	
})(jQuery);