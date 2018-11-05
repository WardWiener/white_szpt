(function($){
	"use strict";
	var searchTimeBtn = null;//查询事件按钮
	var flag = false;
	var idCode = null;
	
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	var fiveColorPerson_locus_layer ;//重点人轨迹点图层
	
	var heatMap ;//热力地图实例对象
	var heatBaseMapServiceLayer ;//热力地图基础图层
	var fiveColorPerson_heat_layer ;//重点人热力点图层
	var line_locus_layer;
	
	var fcpBean = null; //重点人bean
	var locusTable = null; //重点人轨迹列表Table
	var tableOrder = 1; //列表序号
	var wifiPlaceInAndOutInfoBeanList = new Array();//wifi围栏命中信息Bean集合
	var hitLongSumBeanList  = [];//停留最长时间场所
	var hitMaximumSumBeanList = [];//停留最多的场所
	var isLocusInfoPage = true;//是否是轨迹信息页面
	
	var macList=[];
	
	$(document).ready(function() {	
		//初始化轨迹table
		initLocusTable();
		//初始化热力地图
		initHeatMap();
		//初始化轨迹地图
		initLocusMap();
		
		$(document).on("click","#dateCustomized",function(){
			$("#searchRange").show();
		});
		
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#search",function(){
			
			if("" !=  checkTime()){
				$.util.topWindow().$.layerAlert.alert({msg:checkTime(),title:"提示",icon:0,time:3000});	
				return false;
			}
			searchPersonLocus();
		});
		
		/**
		 * 轨迹表格行点击事件
		 */
		$(document).on("click",'#locusTable tr',function(){
			var row = locusTable.row(this) ;
			var data = row.data() ;
			$.common.arcgisMapCommon.setMapCentreAtByLonlat(data.longitude, data.latitude, locusMap);
		});
		
		//校验输入时间
		function checkTime(){
			var str = "";
			var searchTime = 1000*60*60*24*31*6;
			var date = new Date().getTime()+1000*60*60*24;
			var selectTime =  $(".btSelected").attr("id") ;
			if(selectTime == "dateCustomized"){
				
				var datetime
				if($("#fixed_start").val()){
					try{
						datetime = $.szpt.util.searchTime.getAllDays($.szpt.util.searchTime.getTimeValueType())
					}catch(e){
						return "查询结束时间不能大于今天";
					}
					var startTime =  datetime.startTime;
					var endTime = datetime.endTime;
					
					if(endTime > date){
						str = str + "查询结束时间不能大于今天";
					}
					if($("#fixed_start").val() && endTime - startTime > searchTime){
						str = str + "查询结束时间范围最多为半年";
					} 
				}else{
					str = str + "查询开始时间不能为空"
				}
			}
			return str;
		}
		/**
		 * 生成快照按钮事件
		 */
		$(document).on("click","#createSnapshotBtn",function(){
			if(flag){
				var selectTime =  $(".btSelected").attr("id") ;
				var datetime;
				try{
					datetime = $.szpt.util.searchTime.getAllDays($.szpt.util.searchTime.getTimeValueType())
				}catch(e){
					return;
				}
				var searchInfo = {
					"search":$("#searchText").val(),
					"searchTimeBtn" : selectTime,
					"fixed_start":$.date.timeToStr(datetime.startTime, "yyyy-MM-dd HH:mm:ss"),
					"fixed_end":$.date.timeToStr(datetime.endTime, "yyyy-MM-dd HH:mm:ss")
				}
				var snapshotInfo = {
					"searchInfo" : searchInfo,
					"wifiPlaceInAndOutInfoBeanList":wifiPlaceInAndOutInfoBeanList,
					"hitLongSumBeanList":hitLongSumBeanList,
					"hitMaximumSumBeanList":hitMaximumSumBeanList
				}; 
				var strData =  JSON.stringify(snapshotInfo);
				
				window.top.$.layerAlert.dialog({
					content : context +  '/wifiFenceHit/showNewWifiPersonLocusSnapshot.action',
					pageLoading : true,
					title:"新增人员轨迹分析快照",
					width : "500px",
					height : "400px",
					btn:["保存","取消"],
					callBacks:{
						btn1:function(index, layero){
							var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.newWifiPersonLocusSnapshot ;
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
						snapshotObject : strData ,
						search : $("#searchText").val()
					},
					end:function(){
						
					}
				});
			}else{
				return;
			}
			
		});
		
		
		
		//点击近一天.近一月.近一小时.近半年.近一周事件
		$(document).on("click",".searchTimeBtn",function(){
			$("#searchRange").hide();
			searchPersonLocus();
		})
		
		/**
		 * 轨迹信息tabs按钮事件
		 */
		$(document).on("click","#locusInfoTabsLi",function(){
			isLocusInfoPage = true;
			searchPersonLocus();
		});
		/**
		 * 轨迹地图tabs按钮事件
		 */
		$(document).on("click","#locusMapTabsLi",function(){
			isLocusInfoPage = false;
			searchPersonLocus();
		});
		/**
		 * 轨迹地图tabs按钮事件
		 */
		$(document).on("click","#heatMapTabsLi",function(){
			isLocusInfoPage = false;
			searchPersonLocus();
		});
		/**
		 * 重置查询条件
		 */
		$(document).on("click","#reset",function(){
			history.go(0);
		});
	});
	
	/**
	 * 初始化轨迹信息表格
	 */
	function initLocusTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/wifiFenceHit/findLocusByMacs.action";
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
				"width" : "440px",
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
			tableOrder = 1;
//			var macList = new Array();
//			macList.push($.util.exist(fcpBean)?fcpBean.terminalDeviceInfo:"");
			if($("#searchText").val()!=""){
//				macList.push($("#searchText").val());
				$.each(macList,function(m,mac){
					d["macList["+m+"]"] = mac;
				});
				var datetime;
				try{
					datetime = $.szpt.util.searchTime.getAllDays($.szpt.util.searchTime.getTimeValueType())
				}catch(e){
					return;
				}
				var startTime =  datetime.startTime;
				var endTime = datetime.endTime;
				
				d["startTime"] = startTime;
				d["endTime"] = endTime;
			}
		};
		tb.paramsResp = function(json) {
			wifiPlaceInAndOutInfoBeanList = json.resultMap.list;
			wifiPlaceInAndOutInfoBeanList.sort(function(a,b){return b.enterTime - a.enterTime});
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = wifiPlaceInAndOutInfoBeanList;
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		locusTable = $("#locusTable").DataTable(tb);
	}
	
	/**
	 * 设置时间范围快捷按钮样式
	 * @param button 需要设置为选中样式的按钮
	 */
	function setTimeButStyle(button){
		$(".selectTime").each(function(b,but){
			$(but).removeClass("selectTime");
			$(but).removeClass("btn-danger");
			$(but).addClass("btn-primary");
		});

		$(button).addClass("selectTime");
		$(button).addClass("btn-danger");
		$(button).removeClass("btn-primary");
		$.laydate.reset("#diyDateRange");
	}
	
	/**
	 * 查询人员轨迹
	 * @param 
	 */
	function searchPersonLocus(){
		var datetime
		try{
			datetime = $.szpt.util.searchTime.getAllDays($.szpt.util.searchTime.getTimeValueType())
		}catch(e){
			console.log(e);
			return;
		}
		var startTime =  datetime.startTime;
		var endTime = datetime.endTime;
		
		var searchText = $("#searchText").val();
		if($.common.wifiCommon.macValid(searchText)){//验证是否是mac地址
			macList = new Array();
			macList.push(searchText);
            updatePageInfo(startTime,endTime);
		}else if($.common.wifiCommon.idCodeValid(searchText)){ //验证是否是身份证
			$.ajax({
				url:context +'/wifiFenceHit/findMacsByIdCode.action',
				type:'post',
				dataType:'json',
				data:{idCode : searchText},
				success:function(successData){
					if(successData.flag==false||successData.macList.length==0){
						$.layerAlert.tips({
							msg:'未查到该人员相关信息',
							selector:"#searchText",
							color:'#FF0000',
							position:2,
							closeBtn:2,
							time:2000,
							shift:1
						});
						return false;
					}else{
						flag = true;
						macList = successData.macList;
						updatePageInfo(startTime,endTime);
					}
				},
				error:function(errorData){
					
				}
			});
		}else if($.common.wifiCommon.phoneNumberValid(searchText)){ //验证是否是手机号
			$.ajax({
				url:context +'/wifiFenceHit/findMacsByPhoneNumber.action',
				type:'post',
				dataType:'json',
				data:{idCode : searchText},
				success:function(successData){
					if(successData.flag==false||successData.macList.length==0){
						$.layerAlert.tips({
							msg:'未查到该人员相关信息',
							selector:"#searchText",
							color:'#FF0000',
							position:2,
							closeBtn:2,
							time:2000,
							shift:1
						});
						return false;
					}else{
						macList = successData.macList;
						updatePageInfo(startTime,endTime);
					}
				},
				error:function(errorData){
					
				}
			});
		}else{
			$.layerAlert.tips({
				msg:'请填写正确的身份证号或MAC地址或手机号',
				selector:"#searchText",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return false;
//			updatePageInfo();
		}
	}
	
	/**
	 * 更新页面信息
	 * @param fiveColorPersonBean 重点人bean对象
	 */
	function updatePageInfo(startTime,endTime){
		locusTable.draw();
		var gData = new Object();
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(macList, "macList", gData);
		
		var countMaxAjax = $.ajax({
		    url: context +'/wifiFenceHit/findPlaceSumByMacAndTime.action',
		    type:"POST",  
		    dataType:"json",
		    data:gData,
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
		    success:function(data){
		        hitMaximumSumBeanList = data.resultMap.list;
		        setPlaceFrequencyOrderList(hitMaximumSumBeanList);
		    },
            error:function(xhr, error, thrown){
                $.util.log(xhr) ;
                $.util.log(xhr.responseText) ;
                $.util.log(error) ;
                $.util.log(thrown) ;
            }
		});
		
		var timeMaxAjax = countMaxAjax.then(function(){
		   var ajax = $.ajax({
		        url: context +'/wifiFenceHit/findMaxTimePlaceByMacAndTime.action',
		        type:"POST",  
		        dataType:"json",
		        data:gData,
		        customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
		        success:function(data){
		           hitLongSumBeanList = data.resultMap.list;
		           setMaxTimePlaceOrderList(hitLongSumBeanList);
		        }
		   })
		   return ajax ;
		})
		
		var trackMaxAjax = timeMaxAjax.then(function(){
		   var ajax = $.ajax({
		        url: context +'/wifiFenceHit/findLocusByMacs.action',
		        type:"POST",  
		        dataType:"json",
		        data:gData,
		        customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
		        success:function(data){
		           wifiPlaceInAndOutInfoBeanList = data.resultMap.list;
		           showMapHeats(wifiPlaceInAndOutInfoBeanList);
		           newLocusPoint(wifiPlaceInAndOutInfoBeanList);
		        }
		   })
		   return ajax ;
		}).done(function(data){
		   if(hitMaximumSumBeanList.length > 0 || hitLongSumBeanList.length > 0 || wifiPlaceInAndOutInfoBeanList.length > 0){
			   $("#nullSearchResult").hide();
			   $("#searchResult").show();
			   $("#createSnapshotBtn").show();
		   }else{
			   $("#searchResult").hide();
			   $("#createSnapshotBtn").hide();
			   $("#nullSearchResult").show();
		   }
		}).fail(function(){
		   
		});
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
			url:context +'/wifiFenceHit/findPlaceSumByMacAndTime.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				hitMaximumSumBeanList = successData.resultMap.list;
				setPlaceFrequencyOrderList(hitMaximumSumBeanList);
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
			url:context +'/wifiFenceHit/findMaxTimePlaceByMacAndTime.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				hitLongSumBeanList = successData.resultMap.list;
				setMaxTimePlaceOrderList(hitLongSumBeanList);
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 设置该人员驻留时间最长的场所列表
	 * @param wifiPlaceInAndOutInfoBeanList wifi围栏命中信息Bean集合
	 */
	function setMaxTimePlaceOrderList(hitSumBeanList){
		$("#maxTimeplaceOrderTable tbody").html("");
		if(!$.util.isArray(hitSumBeanList) || hitSumBeanList.length < 1){
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
	 * 查询某个人某个时间范围内所有的轨迹记录(热力图/轨迹图)
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
			url:context +'/wifiFenceHit/findLocusByMacs.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				wifiPlaceInAndOutInfoBeanList = successData.resultMap.list;
				showMapHeats(wifiPlaceInAndOutInfoBeanList);
				newLocusPoint(wifiPlaceInAndOutInfoBeanList);
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 获取最近一小时时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyHour(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60;
		return {startTime : startTime , endTime : endTime};
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
		        	if(!$.util.isBlank(wifiFenceHitBean.placeName)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"场所名称："+wifiFenceHitBean.placeName
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
		
	});	
})(jQuery);