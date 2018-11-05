(function($){
	"use strict";
	var WifiAnalyzeTable = null; //WIFI分析表格
	var placeCode;       //场所code
	var criminalTypeArrayByArr = new Array();
//	var count =1;
	var countSonBtn=0;
	$(document).ready(function() {	
//		initDate(); //初始化日期
		initAllDictionaryItem();//初始化页面字典项
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#search",function(){
			var startTime = $.laydate.getTime("#waDateRange", "start");
			var endTime = $.laydate.getTime("#waDateRange", "end");
			if($.util.isBlank(startTime)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写开始时间。"}) ;
				return ;
			}else if($.util.isBlank(endTime)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写结束时间。"}) ;
				return ;
			}
			$("#searchResult").show();
			if($.util.exist(WifiAnalyzeTable)){
				WifiAnalyzeTable.draw(true);
			}else{
				initWifiAnalyzeTable();
			}
		});
	
		/**
		 * 重置查询条件
		 */
		$(document).on("click","#reset",function(){
			$.laydate.reset("#waDateRange");
			$("#criminalRecordName").val("");
			$.zTreeMenu.destroy("criminalRecordName");
			initAllDictionaryItem();
			$("#searchResult").hide();
		});
		
		/**
		 * 人员类型点击事件
		 */
		$(document).on("click" , "#criminalRecordName", function(e){
			var tree = $.zTreeMenu.getTree("criminalRecordName") ;
			tree.showMenu() ;
		});
		
		/**
		 * 表格行点击事件
		 */
		$(document).on("click","#wifiAnalyzeTable tr",function(){
			//改变行颜色
			$("#wifiAnalyzeTable tr").each(function(i,val){
				$(val).css("background","#132643");
			});
			$(this).css("background","#203456");
			var row = WifiAnalyzeTable.row(this) ;
			var full = row.data() ;
			placeCode=full.placeCode;    //需要修改为placeCode***
			findCriminalTypeScale(full.placeCode); //table
		});
		
		/**
		 * 返回按钮
		 */
		$(document).on("click","#returnBtn",function(){		  
		  returnMethod();
		});
		
		/**
		 * 数量事件
		 */
		$(document).on("click",".count",function(){		  
			var tr = $(this).parents("tr")
			var row = WifiAnalyzeTable.row(tr) ;
			var full = row.data() ;
			
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/wifiAnalyze/showHighriskPersonListPage.action',
				pageLoading : true,
				title:"高危人列表",
				width : "800px",
				height : "600px",
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
					macList : full.macs
				},
				end:function(){
					
				}
			});
			return false;
		});
	});
	
	//初始化日期
	function initDate(){
		var now = new Date();
		$('#fixed_start').val($.date.dateToStr(now,"yyyy-MM-dd 00:00:00"));
		$('#fixed_end').val($.date.dateToStr(now,"yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 初始化页面字典项
	 */
	function initAllDictionaryItem(){
		//人员类别
		$.zTreeMenu.init("criminalRecordName", context + "/wifiAnalyze/queryPersonTypeTree.action", {
			async:{
				enable:true
			},
			callBacks:{
				formatNodeData:function(nodeData){
//					nodeData.iconSkin = "dw" ;
		  		}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "all",
				chkboxType: {"Y":"ps", "N":"ps"}
			},
		}) ;
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
	
	function initWifiAnalyzeTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/wifiAnalyze/countMonitorResultByTimeAndCriminalType.action";
		tb.columnDefs = [
 			{
 				"targets": 0,
      	    	"width": "50px",
      	    	"title": "序号",
      	    	"className":"table-checkbox",
      	    	"data": "" ,
      	    	"render": function ( data, type, full, meta ) {
      	    			  return meta.row + 1;
      	    	}
 			},
 			{
 				"targets" : 1,
 				"width" : "270px",
 				"title" : "场所名称",
 				"data" : "placeName",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 2,
 				"width" : "100px",
 				"title" : "监测重点人次",
 				"data" : "count",
 				"render" : function(data, type, full, meta) {
 					return "<a href='javascript:void(0);' class='count'>" + data + "</a>";
 				}
 			},
 			{
 				"targets" : 3,
 				"width" : "140px",
 				"title" : "监测时长",
 				"data" : "stayTime",
 				"render" : function(data, type, full, meta) {
 					return millisecondToDate(data);
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
 		//是否显示loading效果
 		tb.bProcessing = true;
 		//请求参数
 		tb.paramsReq = function(d, pagerReq){
 			if(!$.util.isBlank($("#criminalRecordName").val())){
// 				$.each($.common.ztreeCommon.getCheckedValue(),function(c,ct){
// 					d["criminalTypeList["+c+"]"] = ct.code;
// 				});
 				//var criminalTypeList = new Array();
 				var obj = $.zTreeMenu.getCheckedValue("criminalRecordName");
 				d["criminalTypeCodeList"]= $.util.cloneObj(obj.codes);
 			}
 			d["startTime"] = $.laydate.getTime("#waDateRange", "start");
 			d["endTime"] = $.laydate.getTime("#waDateRange", "end");
 		};
 		tb.paramsResp = function(json) {
 			var hitSumBeanList = json.resultMap.list;
 			json.recordsTotal = json.resultMap.totalNum;
 			json.recordsFiltered = json.resultMap.totalNum;
 			json.data = hitSumBeanList;
 		};
 		tb.rowCallback = function(row,data, index) {
 			if(index == 0){
 				$(row).css("background","#203456");
 				placeCode=data.placeCode;  //需要修改为placeCode***
 				findCriminalTypeScale(data.placeCode)  //table
 			}
 		};
 		tb.fnDrawCallback = function(json){
 			if(json._iRecordsTotal==0){
 				findCriminalTypeScale("");
 			}
 		}
 		WifiAnalyzeTable = $("#wifiAnalyzeTable").DataTable(tb);
	}
	
	/**
	 * 初始化图标插件
	 */
	function initHHighcharts(dataArray){
		if(!$.util.exist(dataArray)){
			return;
		}
		Highcharts.theme = {
			colors: ["#7cb5ec", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
				"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
		};
		
		$('#highchart-container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '人员类型人次占比'
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#000000',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                },events:{
	                	click:function(e){
	                		//window.open(e.point.name);
	                		//alert(e.point.code);
	                		sonMethod(e.point.code);//钻取方法
	                		
	                	}
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '占比',
	            data:dataArray
	        }]
	    });
		Highcharts.setOptions(Highcharts.theme);
	}
	
	/**
	 * 向下钻取显示内容
	 */
	function sonMethod(data){  //data 人员类型

//		var criminalTypeList = new Array();
//		var array = $.common.ztreeCommon.getCheckedValue();
//		var arr='';
//		for(var i in array){
//			criminalTypeList.push(array[i].code);
//			arr+=array[i].code+',';
//		}
		
		var startTime = $.laydate.getTime("#waDateRange", "start");
		var endTime = $.laydate.getTime("#waDateRange", "end");
			
		var gData = new Object();
	//	$.util.objToStrutsFormData(arr, "criminalTypeStrToList", gData);
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(placeCode, "placeCode", gData); //全局变量 场所Code***
		$.util.objToStrutsFormData(data, "personSort", gData); //人员类别
		$.ajax({
			url:context +'/wifiAnalyze/findCriminalTypeScale.action',
			type:'post',
			dataType:'json',
			data:gData,
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var listWifi = successData.listWifi;
				var criminalTypeSonArray = new Array();
				$.each(listWifi,function(w,wifi){
					var obj = new Object();
					obj["name"] = wifi.key;
					obj["y"] = wifi.count;
					obj["code"] = wifi.code;  //需要添加的code;
					criminalTypeSonArray.push(obj);
				});
//				for(var i=1;i<5;i++){
//					var obj = new Object();
//					obj["name"] = "小偷子级"+count+i;
//					obj["y"] = i;
//					criminalTypeSonArray.push(obj);
//				}
//				count++;
				if(criminalTypeSonArray.length > 0){
					criminalTypeArrayByArr.push(criminalTypeSonArray); //记录查询的arr集合
					initHHighcharts(criminalTypeSonArray);  //加载图表数据
					countSonBtn++; //记录点击子目录的次数
				}
				if(countSonBtn > 0){
					$('#returnDIVBtn').show();
				}else{
					$('#returnDIVBtn').hide();
				}
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 返回按钮,返回上一级内容
	 */
	function returnMethod(){
		if(countSonBtn==criminalTypeArrayByArr.length-1){
			criminalTypeArrayByArr.pop();
		}
		if(criminalTypeArrayByArr.length==1){ //加载图表数据 criminalTypeArray 到一级时的状态
			initHHighcharts(criminalTypeArrayByArr[0]); 
			countSonBtn=0; 
//			count =1;		
			$('#returnDIVBtn').hide();
		}else{
			initHHighcharts(criminalTypeArrayByArr.pop());  //加载图表数据 criminalTypeArray 为存储的数据
		}
	}
	/**
	 * 根据场所名称、时间段、人员类别查询命中记录计算人员类别百分比
	 * 
	 * @param placeCode 场所名称
	 */
	function findCriminalTypeScale(placeCode){
//		if($.util.isBlank(placeCode)){
//			return ;
//		}
		var criminalTypeCodeList = new Array();
		if(!$.util.isBlank($("#criminalRecordName").val())){
//			$.each($.common.ztreeCommon.getCheckedValue(),function(c,ct){
//				criminalTypeList.push(ct.code);
//			});
			var obj = $.zTreeMenu.getCheckedValue("criminalRecordName");
			criminalTypeCodeList = obj.codes;
		}
		var startTime = $.laydate.getTime("#waDateRange", "start");
		var endTime = $.laydate.getTime("#waDateRange", "end");
			
		var gData = new Object();
		$.util.objToStrutsFormData(criminalTypeCodeList, "criminalTypeCodeList", gData);
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.util.objToStrutsFormData(placeCode, "placeCode", gData);
		$.ajax({
			url:context +'/wifiAnalyze/findCriminalTypeScale.action',
			type:'post',
			dataType:'json',
			data:gData,
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var listWifi = successData.listWifi;
				var criminalTypeArray = new Array();
				$.each(listWifi,function(w,wifi){
					var obj = new Object();
					obj["name"] = wifi.key;
					obj["y"] = wifi.count;
					obj["code"] = wifi.code; //获取向下钻取的code值 ***&&&&
					criminalTypeArray.push(obj);
				});
//				for(var i=1;i<5;i++){  //伪数据
//					var obj = new Object();
//					obj["name"] = "小偷父级"+i;
//					obj["y"] = i;
//				    obj["code"] = 1;
//					criminalTypeArray.push(obj);
//				}
				countSonBtn=0; 
//				count =1; 
				criminalTypeArrayByArr=[];
				criminalTypeArrayByArr.push(criminalTypeArray); //记录查询的arr集合
				initHHighcharts(criminalTypeArray);  //加载图表数据
				$('#returnDIVBtn').hide();
			},
			error:function(errorData){
				
			}
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
//		findAllAutoFlow:findAllAutoFlow
	});	
})(jQuery);