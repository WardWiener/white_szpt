(function($){
	"use strict";
	var hotelList = [];
	var internetBarList = [];
	var trainInList = [];
	var trainOutList = [];
	var airPlaneInList = [];
	var airPlaneOutList = [];
	var monitoringGuardList = [];
	var wifiList = [];
	var trainAndAirPlaneList = [];
	
	var hotelTrackList=[];
	var internetBarTrackList=[];
	var trainInTrackList=[];
	var trainOutTrackList=[];
	var airPlaneInTrackList=[];
	var airPlaneOutTrackList=[];
	var wifiTrackList=[];
	var monitoringGuardTrackList=[];
	
	var highriskPersonBean = null;
	
	var personTrackInfoList = null;

	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	var fiveColorPerson_locus_layer ;//重点人轨迹点图层
	
	var heatMap ;//热力地图实例对象
	var heatBaseMapServiceLayer ;//热力地图基础图层
	var fiveColorPerson_heat_layer ;//重点人热力点图层
	var line_locus_layer;
	var table;
	var idcode;
	$(document).ready(function() {	
		idcode = $("#paraIdcode").val();
		if(idcode!="null"){
			findPersonTrackByIdCodeAndTime(idcode);
			findPersonBaseInfoByIdCode(idcode);
		}
		clearPersonBaseInfoField(); // 清空人员基础信息字段
		//初始化热力地图
		initHeatMap();
		//初始化轨迹地图
		initLocusMap();
		
		initLocasTable();
		
		//返回
		$(document).on("click", "#backViewBtn", function(){
			window.top.location.href = url;
		})
		
		//查询按钮事件
		$(document).on("click","#search",function(){
			
			if("" != checkTime()){
				$.util.topWindow().$.layerAlert.alert({msg:checkTime(),title:"提示",icon:0,time:3000,});	
				return false;
			}
			
			idcode = $("#idcode").val();
			if($.util.isBlank(idcode)){
				tableDraw();
				rightTableRefresh();
				$("#trackTimeShaft").html("");
				hotelList = [];
				internetBarList = [];
				trainInList = [];
				trainOutList = [];
				airPlaneInList = [];
				airPlaneOutList = [];
				monitoringGuardList = [];
				wifiList = [];
				trainAndAirPlaneList = [];
				return ;
			}
			findPersonBaseInfoByIdCode(idcode);
			findPersonTrackByIdCodeAndTime(idcode);
		});
		
		//校验时间
		function checkTime(){
			var str = "";
			var searchTime = 1000*60*60*24*31*6;
			var data = new Date().getTime();
			var endTimeLong ;
			if(!$("#fixed_start").val()){
				str = str + "查询开始时间不能为空<br/>"
			}
			if($("#fixed_end").val()){
				endTimeLong = $.laydate.getTime("#trackDateRange","end");
				if(endTimeLong > data){
					str = str + "查询结束时间不能大于当下时间 <br/>"
				}
			}else{
				endTimeLong = data;
			}
			if($("#fixed_start").val() ){
				var startTimeLong;
				startTimeLong = $.laydate.getTime("#trackDateRange","start");
				if(startTimeLong > data){
					str = str + "查询开始时间不能大于当下时间 <br/>"
				}
				if(endTimeLong - $.laydate.getTime("#trackDateRange","start") > searchTime){
					str = str + "查询时间范围最大为半年"
				}
			}
			return str;
		}
		/**
		 * 快照按钮事件
		 */
		$(document).on("click","#snapshot",function(){
			var snapshotInfo = {
				"baseInfo" : highriskPersonBean,
				"personTrackInfoLst" : personTrackInfoList,
				"hotelList" : hotelList,
				"internetBarList" : internetBarList,
				"trainInList" : trainInList,
				"trainOutList" : trainOutList,
				"airPlaneInList" : airPlaneInList,
				"airPlaneOutList" : airPlaneOutList,
				"monitoringGuardList" : monitoringGuardList,
				"wifiList" : wifiList,
				"trainAndAirPlaneList" :  trainAndAirPlaneList,
			}; 
			var str =  $.util.toJSONString(snapshotInfo);
			
			
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showNewTrackAnalyzeSnapshot.action',
				pageLoading : true,
				title:"新增轨迹快照",
				width : "500px",
				height : "400px",
				btn:["保存","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = window.top.frames["layui-layer-iframe"+index].$.newTrackAnalyzeSnapshot ;
						cm.saveSnapshot();
					},
					btn2:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					"snapshotObject" : str ,
					"idCode" : $("#idcode").val(),
				},
				end:function(){
					
				}
			});
		});
		$(document).on("click","#reset",function(){
			location.replace(location.href);
//			$.laydate.reset("#trackDateRange");
//			$("#idcode").val("");
		});
		$(document).on("click",".all",function(){
			var data = $(this).attr("data")
			var dataList=[];
			if(data=="旅馆酒店"){
				dataList=hotelTrackList;
			}else if(data=="从贵阳乘火车 - 目的地"){
				dataList=trainOutTrackList;
			}else if(data=="网吧"){
				dataList=internetBarTrackList;
			}else if(data=="乘火车到贵阳 - 起始地"){
				dataList=trainInTrackList;
			}
			else if(data=="乘飞机到贵阳 - 起始地"){
				dataList=airPlaneInTrackList;
			}
			else if(data=="从贵阳乘飞机- 目的地"){
				dataList=airPlaneOutTrackList;
			}
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:data,
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : dataList,
	    			type:1
	    		},
	    		end:function(){
	    		}
			});
		});
		$(document).on("click","#wifiAll",function(){
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:"WIFI",
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : wifiTrackList,
	    			type:"wifi"
	    		},
	    		end:function(){
	    		}
			});
		});
		$(document).on("click","#monitoringGuardAll",function(){
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:"监所",
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : monitoringGuardTrackList,
	    			type:"monitoringGuard"
	    		},
	    		end:function(){
	    		}
			});
		});
		$(document).on("click",".count",function(){
			var dataList=[];
			var list=[];
			var name = $(this).attr("name");
			if($(this).hasClass("hotelCount")){
				list = hotelTrackList;
			}else if($(this).hasClass("internetBarCount")){
				list = internetBarTrackList;
			}else if($(this).hasClass("trainInCount")){
				list = trainInTrackList;
			}else if($(this).hasClass("trainOutCount")){
				list = trainOutTrackList;
			}else if($(this).hasClass("airPlaneOutCount")){
				list = airPlaneOutTrackList;
			}else if($(this).hasClass("airPlaneInCount")){
				list = airPlaneInTrackList;
			}else{
				return ;
			}
			for(var i in list){
				if(list[i].placeName==name){
					dataList.push(list[i]);
				}
			}
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:"详情",
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : dataList,
	    			type:1
	    		},
	    		end:function(){
	    		}
			});
		});
		$(document).on("click",".wifiCount",function(){
			var name = $(this).attr("name");
			var dataList=[];
			for(var i in wifiTrackList){
				if(wifiTrackList[i].place==name){
					dataList.push(wifiTrackList[i]);
				}
			}
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:"详情",
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : dataList,
	    			type:"wifiCount"
	    		},
	    		end:function(){
	    		}
			});
		});
		$(document).on("click",".monitoringGuardCount",function(){
			var name = $(this).attr("name");
			var dataList=[];
			for(var i in monitoringGuardTrackList){
				if(monitoringGuardTrackList[i].monitoredSite==name){
					dataList.push(monitoringGuardTrackList[i]);
				}
			}
			window.top.$.layerAlert.dialog({
				content : context +  '/trackAnalyze/showAllTrackPage.action',
				pageLoading : true,
				title:"详情",
				width : "550px",
				height : "600px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			list : dataList,
	    			type:"monitoringGuardCount"
	    		},
	    		end:function(){
	    		}
			});
		});
	});
	function tableDraw(){
		$(".m-ui-table").empty();
		$(".m-ui-table").append('<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>');
		initLocasTable();
	}
	function rightTableRefresh(){
		$(".hotel-pannel .dataTr td").html("");
	}
	/**
	 * 根据身份证号和时间段查询人员轨迹
	 */
	function findPersonTrackByIdCodeAndTime(idCode){
		var dataMap = new Object();
		
		
		if($.laydate.getTime("#trackDateRange","start")){
			dataMap["startTime"] = $.laydate.getTime("#trackDateRange","start");
		}else{
			dataMap["startTime"] = new Date(0).getTime();
		}
		
		if($.laydate.getTime("#trackDateRange","end")){
			dataMap["endTime"] = $.laydate.getTime("#trackDateRange","end");
		}else{
			dataMap["endTime"] = new Date().getTime();
		}
	
		dataMap["idCode"] = idCode;
		$.ajax({
			url: context + '/trackAnalyze/findPersonTrackByIdCodeAndTimeBD.action',
			type:"POST",
			data:dataMap,
//			customizedOpt:{
//			    ajaxLoading:true,//设置是否loading
//			},
			dataType:"json",
			success:function(data){
				personTrackInfoList = data.personTrackInfoList ;
				setTrackTimeShaft(personTrackInfoList);
				showMapHeats(personTrackInfoList);
				newLocusPoint(personTrackInfoList);
				hotelList = data.hotelList;
				internetBarList = data.internetBarList;
				trainInList = data.trainInList;
				trainOutList = data.trainOutList;
				airPlaneInList = data.airPlaneInList;
				airPlaneOutList = data.airPlaneOutList;
				monitoringGuardList = data.monitoringGuardList;
				wifiList = data.wifiList;
				trainAndAirPlaneList = data.trainAndAirPlaneList;
				rightTableRefresh();
				setTrackCountTable("countTable1",hotelList,trainOutList);
				setTrackCountTable("countTable2",internetBarList,trainInList);
				setTrackCountTable("countTable3",monitoringGuardList,airPlaneOutList);
				setTrackCountTable("countTable4",wifiList,airPlaneInList);
				tableDraw();
				
				hotelTrackList= data.hotelTrackList;
				internetBarTrackList= data.internetBarTrackList;
				trainInTrackList= data.trainInTrackList;
				trainOutTrackList= data.trainOutTrackList;
				airPlaneInTrackList= data.airPlaneInTrackList;
				airPlaneOutTrackList= data.airPlaneOutTrackList;
				wifiTrackList= data.wifiTrackList;
				monitoringGuardTrackList= data.monitoringGuardTrackList;
				
				//设置轨迹信息页面的显示影藏
				if(personTrackInfoList.length > 0 || 
						trainInList.length > 0 || 
						trainOutList.length > 0 || 
						airPlaneInList.length > 0 || 
						airPlaneOutList.length > 0 || 
						monitoringGuardList.length > 0 || 
						wifiList.length > 0 || 
						trainAndAirPlaneList.length > 0 || 
						hotelTrackList.length > 0 || 
						internetBarTrackList.length > 0 || 
						trainInTrackList.length > 0 || 
						trainOutTrackList.length > 0 || 
						airPlaneInTrackList.length > 0 || 
						airPlaneOutTrackList.length > 0 || 
						wifiTrackList.length > 0 || 
						monitoringGuardTrackList.length > 0){
					$("#trackDiv").show();
					$("#snapshot").show();
					$("#nullTrack").hide();
				}else{
					$("#trackDiv").hide();
					$("#snapshot").hide();
					$("#nullTrack").show();
				}
			}
		});
	}
	
	/**
	 * 根据身份证号查询人员
	 * @param idCode 身份证号
	 */
	function findPersonBaseInfoByIdCode(idcode){
		$.ajax({
			url: context + '/highriskPerson/findPersonByIdcode.action',
			type:"POST",
			data:{
				idcode : idcode
			},
			dataType:"json",
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(data){
				highriskPersonBean =  null;
				highriskPersonBean = data.highriskPersonBean ;
				if($.util.exist(highriskPersonBean)){
					$("#nullPerson").hide();
					$("#c-center-right-content-block").show();
				}else{
					$("#c-center-right-content-block").hide();
					$("#nullPerson").show();
				}
				setPersonBaseInfo(highriskPersonBean);
			}
		});
	}
	
	/**
	 * 设置轨迹统计表
	 * @param tableId table表id
	 * @param leftDataArr 左边统计项数据{name:,count,[详细数据数组]}
	 * @param rightDataArr 右边统计项数据
	 */
	function setTrackCountTable(tableId,leftDataArr,rightDataArr){
		var length = 3;//表默认最大行数
		
		//设置左边统计数据
		if(leftDataArr.length < 3){
			length = leftDataArr.length;
		}
		var leftClass="";
		var rightClass="";
		if(tableId=="countTable1"){
			leftClass="hotelCount";
			rightClass="trainOutCount";
		}else if(tableId=="countTable2"){
			leftClass="internetBarCount";
			rightClass="trainInCount";
		}else if(tableId=="countTable3"){
			leftClass="monitoringGuardCount";
			rightClass="trainInCount";
		}else if(tableId=="countTable4"){
			leftClass="wifiCount";
			rightClass="airPlaneInCount";
		}
		for(var i=0; i<length; i++){
			var tr = $("#" + tableId + " tbody tr")[i];
			$(tr).children('td').eq(0).text(leftDataArr[i].groupName);
			$(tr).children('td').eq(1).html('<a name="'+leftDataArr[i].groupName+'"class="'+leftClass+' count font16 color-red1 mar-right-sm">'+leftDataArr[i].totalInterval+'</a>次');
		}
		//设置右边统计数据
		if(rightDataArr.length < 3){
			length = rightDataArr.length;
		}
		for(var i=0; i<length; i++){
			var tr = $("#" + tableId + " tbody tr")[i];
			$(tr).children('td').eq(2).text(rightDataArr[i].groupName);
			$(tr).children('td').eq(3).html('<a name="'+rightDataArr[i].groupName+'"class="'+rightClass+' count font16 color-red1 mar-right-sm">'+rightDataArr[i].totalInterval+'</a>次');
		}
	}
	
	/**
	 * 设置轨迹时间轴
	 */
	function setTrackTimeShaft(trackArray){
	
		$("#trackTimeShaft").html("");
		if(!$.util.exist(trackArray) || trackArray.length < 1){
			return ;
		}
		trackArray.sort(function(a,b){
            return b.appearTime-a.appearTime;
         });
		var arr = [];
		for(var i in  trackArray){
			if(!trackArray[i].isWifi){
				arr.push(trackArray[i]);
			}
		}
		$.each(arr,function(i,track){
			if(track.isWifi){
				return;
			}
			var className = "odd";
			if(i % 2 == 0){
				className = "even";
			}
			var li = $("<li />",{
				"class" : className
			});
			$("<span />",{
				"class" : "icon-red-dot"
			}).appendTo(li);
			var titleDiv = $("<div />",{
				"class" : "time-box"
			}).appendTo(li);
			$("<p />",{
				"class" : "color-yellow2",
				"text" : track.trackTypeDescription
			}).appendTo(titleDiv);
			var describeDiv = $("<div />",{
				"class" : "con-box",
				"text" : track.trackDescription
			}).appendTo(li);
			$("<span />",{
				"class" : "arrow"
			}).prependTo(describeDiv);
			$("#trackTimeShaft").append(li);
		});
		$("#trackTimeShaft").append($("<li />",{"class" : "clear"}));
	}
	//cheng
	function initLocasTable(){
		var dataSet = trainAndAirPlaneList;
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataSet;
		st1.columnDefs = [ 
			{ 
				"targets": 0,
				"width": "20%",
				"title": '轨迹类型',
				"className":"",
				"data": "trackTypeDescription" ,
				"render": function ( data, type, full, meta ) {		
				      return data;
				}
			},
			{ 
				"targets": 1,
				"width": "20%",
				"title": "始发地",
				"className":"",
				"data": "originLocation" ,
				"render": function ( data, type, full, meta ) {		
				      return data;
				}
			},
			{ 
				"targets": 2,
				"width": "20%",
				"title": '目的地',
				"searchable" : true,
				"data": "destinationLocation" ,
				"render": function ( data, type, full, meta ) {
					return data;
				}
			},
			{ 
				"targets": 3,
				"width": "25%",
				"title": '时间',
				"searchable" : true,
				"data": "appearTime" ,
				"render": function ( data, type, full, meta ) {
					return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");;
				}
			}
		];

		st1.ordering = false;
		st1.paging = false;
		st1.hideHead = false;
		st1.searching = false;
		st1.info = false ; 
		st1.myDrawCallback = function(settings){
		 };
		st1.myDrawCallback = function(settings){
		 };
		table = $("#table").DataTable(st1);
	}
	
	/**
	 * 设置人员基本信息
	 * @param personObj 人员对象
	 */
	function setPersonBaseInfo(personObj){
		clearPersonBaseInfoField(); // 清空人员基础信息字段
		if(!$.util.exist(personObj)){
			return ;
		}
		idcode = personObj.idcode;
		$("#name").text($.util.isBlank(personObj.name)?"":personObj.name);
		$("#idCode").text(personObj.idcode);
		$("#sex").text($.util.isBlank(personObj.sexName)?"":personObj.sexName);
		$("#criminalRecord").text($.util.isBlank(personObj.criminalRecordName)?"":personObj.criminalRecordName);
		$("#status").text($.util.isBlank(personObj.statusName)?"":personObj.statusName);
		$("#liveAddress").text($.util.isBlank(personObj.liveAddress)?"":personObj.liveAddress);
		$("#registerAddress").text($.util.isBlank(personObj.registerAddress)?"":personObj.registerAddress);
		$("#profession").text($.util.isBlank(personObj.profession)?"":personObj.profession);
		$("#income").text($.util.isBlank(personObj.income)?"":personObj.income + " ￥");
		$("#createdTime").text($.date.timeToStr(personObj.createdTime, "yyyy/MM/dd"));
		$("#urineTest").text($.util.isBlank(personObj.urineTest)?"":personObj.urineTest);
		$("#peopleType").text($.util.isBlank(personObj.peopleTypeName)?"":personObj.peopleTypeName);
		var phone = "";
		$.each(personObj.phone,function(i,val){
			phone += val + "<br/>";
		});
		$("#phone").html(phone.substring(0,phone.length-1));

		var mac = "";
		$.each(personObj.mac,function(i,val){
			mac += val + "<br/>";
			$("#macs").append('<input type="checkbox" class="icheckbox" name="mac" value="'+val+'"id="">');
		});
		$("#mac").html(mac.substring(0,mac.length-1));
		
		switch (personObj.warnType){
			case $.common.Constant.YJLX_HONG_SE():
				$("#warnType").text(personObj.warnTypeName);
				$("#warnType").addClass("btn sq-red selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_CHENG_SE():
				$("#warnType").text(personObj.warnTypeName);
				$("#warnType").addClass("btn sq-orange selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_HUANG_SE():
				$("#warnType").text(personObj.warnTypeName);
				$("#warnType").addClass("btn sq-yellow selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_BAI_SE():
				$("#warnType").text(personObj.warnTypeName);
				$("#warnType").addClass("btn sq-white selected");
				$("#warnType").parent("div").show();
				break;
			case $.common.Constant.YJLX_LAN_SE():
				$("#warnType").text(personObj.warnTypeName);
				$("#warnType").addClass("btn sq-blue selected");
				$("#warnType").parent("div").show();
				break;
			default:
				$("#warnType").parent("div").hide();
		}
	}
	
	/**
	 * 清空人员基础信息字段
	 */
	function clearPersonBaseInfoField(){
		$("#name").text("");
		$("#idCode").text("");
		$("#sex").text("");
		$("#criminalRecord").text("");
		$("#status").text("");
		$("#liveAddress").text("");
		$("#registerAddress").text("");
		$("#profession").text("");
		$("#income").text("");
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
	        	title:"地点",
	        	content:function(arg0){
		        	var personTrackInfoBean = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:200px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(personTrackInfoBean)){
		        		return baseDiv[0] ;
		        	}
		        	//场所名称
		        	if(!$.util.isBlank(personTrackInfoBean.placeName)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"地点名称："+personTrackInfoBean.placeName
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//进入时间
		        	if(!$.util.isBlank(personTrackInfoBean.appearTime)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"进入时间："+$.date.timeToStr(personTrackInfoBean.appearTime, "yyyy-MM-dd HH:mm")
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//离开时间
		        	if(!$.util.isBlank(personTrackInfoBean.leaveTime)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"离开时间："+$.date.timeToStr(personTrackInfoBean.leaveTime, "yyyy-MM-dd HH:mm")
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
			setTimeout(function(){showMapHeats(list)},1000);
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
	 * @param list wifi围栏命中信息Bean集合
	 */
	function newLocusPoint(list){
		if(!$.util.exist(list) || !$.util.exist(fiveColorPerson_locus_layer)){
			setTimeout(function(){newLocusPoint(list)},1000);
			return ;
		}
		fiveColorPerson_locus_layer.clear();
		$.each(list,function(i,personTrackInfoBean){
			var longitude = personTrackInfoBean.longitude;
			var latitude = personTrackInfoBean.latitude;
			var layer = fiveColorPerson_locus_layer;
			var url="/images/map/map-icon-trail.png"
			var symbolObj = {url:context+url, width:22, height:35};
			var data = personTrackInfoBean;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
			if(i < 1 && $.util.exist(locusMap)){
				$.common.arcgisMapCommon.setMapCentreAtByLonlat(longitude, latitude, locusMap);//将地图中心定位到监测中心
			}
		});
		var lineList=[];
		for(var i in list){
			if(list[i].longitude!=null&list[i].latitude!=null){
				lineList.push(list[i]);
			}
		}
		$.common.arcgisMapCommon.drawLine(line_locus_layer, locusMap, lineList);
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.trackAnalyze, { 
		
	});	
})(jQuery);