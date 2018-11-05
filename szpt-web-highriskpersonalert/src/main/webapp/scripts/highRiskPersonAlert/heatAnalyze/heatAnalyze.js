(function($){
	"use strict";
	var heatMap ;//热力地图实例对象
	var heatBaseMapServiceLayer ;//热力地图基础图层
	var fiveColorPerson_heat_layer ;//重点人热力点图层
	
	var layer_JqPoint ;// 警情图层
	
	var layer_AjPoint ;// 案件类别图层
	
	var flag = true;
	
	var list = [];
	$(document).ready(function() {	
		
		initDate();
		initAllDictionaryItem();
//		initPersonTypeName();
		//初始化热力地图
		initHeatMap();
		
//		refresh();
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#search",function(){
			refresh();
		});
		
		/**
		 * 人员类型点击事件
		 */
		$(document).on("click" , "#criminalRecordName", function(e){
			var tree = $.zTreeMenu.getTree("criminalRecordName") ;
			tree.showMenu() ;
		});
		
		//返回
		$(document).on("click", "#backViewBtn", function(){
			window.top.location.href = url;
		})
	
		/**
		 * 重置查询条件
		 */
		$(document).on("click","#reset",function(){
			$.laydate.reset("#dateRange");
			$("#criminalRecordName").val("");
			$("#dimJqlxId").val("");
			$("#ajlb").val("");
			
			$.zTreeMenu.destroy("criminalRecordName");
			initAllDictionaryItem();
			
//			var treeObj = $.zTreeMenu.getTree("dimJqlxId");
//			treeObj.tree.reAsyncChildNodes(null,"refresh");
			$.icheck.check("#Jqcheck",false);
			$.icheck.check("#Ajcheck",false);
			$(".close").click();
		});
	});
	
	function initDate(){
		var now = new Date();
		$('#fixed_start').val($.date.dateToStr(now,"yyyy-MM-dd 00:00:00"));
		$('#fixed_end').val($.date.dateToStr(now,"yyyy-MM-dd HH:mm:ss"));
	}

//	function initPersonTypeName (){
//		if(null != personTypeName){
//			$("#criminalRecordName").val(personTypeName);
//		}
//	}
	
	function refresh(){
		var gData = new Object();
		var startTime = $.laydate.getTime("#dateRange", "start");
		var endTime = $.laydate.getTime("#dateRange", "end");
		if($.util.isBlank(startTime)){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写开始时间。"}) ;
			return ;
		}else if($.util.isBlank(endTime)){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写结束时间。"}) ;
			return ;
		}
		
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
//		if(flag && null != personType && "null" != personType && "" != personType){
//			var data = personType.split("、"); 
//			$.each(data,function(c,ct){
//				gData["peopleTypeList["+c+"]"] = ct;
//			});
//			flag = false;
//		}else 
		if(!$.util.isBlank($("#criminalRecordName").val())){
			var obj = $.zTreeMenu.getCheckedValue("criminalRecordName");
			$.util.objToStrutsFormData(obj.codes, "peopleTypeList", gData);
		}
		
		$.ajax({
			url:context +'/heatAnalyze/findHeatByPeopleType.action',
			type:'post',
			dataType:'json',
			data:gData,
			customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var placeCountBeanLst = successData.placeCountBeanLst;
				list = placeCountBeanLst;
				showMapHeats();
			},
			error:function(errorData){
				
			}
		});
		//通过警情类型查询
		if($.icheck.getChecked("Jqcheck").length!=0){
			gData = new Object();
			$.util.objToStrutsFormData(startTime, "startTime", gData);
			$.util.objToStrutsFormData(endTime, "endTime", gData);
			var jqlxPlace = $.zTreeMenu.getTree("dimJqlxId") ;
			if (null != jqlxPlace) {
				var obj = $.zTreeMenu.getCheckedValue("dimJqlxId");
				var dimJqlxIdArr = [];
				var data = obj.data;
				var count=0;
				for(var key in data) {  
					gData["qjTypeList["+count+"]"] = data[key].diyMap.code;
					count++;
				}  
			}
			
			$.ajax({
				url:context +'/heatAnalyze/findHeatByJqlx.action',
				type:'post',
				dataType:'json',
				data:gData,
				customizedOpt:{
				     ajaxLoading:true,//设置是否loading
				},
				success:function(successData){
					var alarmPosLst = successData.alarmPosLst;
					newJqPoint(alarmPosLst);
				},
				error:function(errorData){
					
				}
			});
		}else if(layer_JqPoint!=null){
			layer_JqPoint.clear();
		}
//		通过案件类别查询
//		if($.icheck.getChecked("Ajcheck").length!=0){
//			gData = new Object();
//			$.util.objToStrutsFormData(startTime, "startTime", gData);
//			$.util.objToStrutsFormData(endTime, "endTime", gData);
//			var ajlbPlace = $.zTreeMenu.getTree("ajlb") ;
//			if (null != ajlbPlace) {
//				var obj = $.zTreeMenu.getCheckedNodes("ajlb");
//				var dimJqlxIdArr = [];
////				var data = obj.data;
//				var count=0;
//				for(var key in obj) {  
//					gData["peopleTypeList["+count+"]"] = obj[count].diyMap.code;
//					count++;
//				}  
//			}
//			$.ajax({
//				url:context +'/heatAnalyze/findHeatByAjlb.action',
//				type:'post',
//				dataType:'json',
//				data:gData,
//				success:function(successData){
//					var ajList = successData.ajList;
//					newAjPoint(ajList);
//				},
//				error:function(errorData){
//					
//				}
//			});
//		}else if(layer_AjPoint!=null){
//			layer_AjPoint.clear();
//		}
	}
	/**
	 * 初始化页面字典项
	 */
	function initAllDictionaryItem(){
		//人员类别
		$.zTreeMenu.init("criminalRecordName", context + "/heatAnalyze/queryPersonTypeTree.action", {
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
				chkboxType: {"Y":"s", "N":"s"}
			},
		}) ;
		
		//加载警情类型树
		$.zTreeMenu.init("dimJqlxId", context + "/heatAnalyze/queryJqlxTree.action", {
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
				chkboxType: {"Y":"s", "N":"s"}
			},
		}) ;
		
		$(document).on("click" , "#dimJqlxId", function(e){
			var tree = $.zTreeMenu.getTree("dimJqlxId") ;
			tree.showMenu() ;
		});
		
		//加载案件类别树
		$.zTreeMenu.init("ajlb", context + "/heatAnalyze/queryAjlbTree.action", {
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
				chkboxType: {"Y":"s", "N":"s"}
			},
		}) ;
		
		$(document).on("click" , "#ajlb", function(e){
			var tree = $.zTreeMenu.getTree("ajlb") ;
			tree.showMenu() ;
		});
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
	        
	        //警情图层
	        var jqTemplate = new arcgisEsri.InfoTemplate({
	        	title:"警情",
	        	content:function(arg0){
		        	var jq = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:240px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(jq)){
		        		return baseDiv[0] ;
		        	}
		        	//警情名称
		        	if(!$.util.isBlank(jq.alarmName)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"警情名称："+jq.alarmName
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//场所名称
		        	if(!$.util.isBlank(jq.jqSummary)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"警情概要："+jq.jqSummary
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	return baseDiv[0] ;
	        	}
		    });
	        layer_JqPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_JqPoint",
		    	infoTemplate:jqTemplate
		    });
	        heatMap.addLayer(layer_JqPoint);
	        //案件图层
	        var ajTemplate = new arcgisEsri.InfoTemplate({
	        	title:"案件",
	        	content:function(arg0){
		        	var aj = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:240px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(aj)){
		        		return baseDiv[0] ;
		        	}
		        	//案件编号
		        	if(!$.util.isBlank(aj.caseCode)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"案件编号："+aj.caseCode
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//案件名称
		        	if(!$.util.isBlank(aj.caseName)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"案件名称："+aj.caseName
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	return baseDiv[0] ;
	        	}
		    });
	        layer_AjPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_AjPoint",
		    	infoTemplate:ajTemplate
		    });
	        heatMap.addLayer(layer_AjPoint);
		}
		
		$.common.arcgisMapCommon.newArcgisMap(callback);
	}
	/**
	 * 显示热力图层
	 * @param list 轨迹点
	 */
	function showMapHeats(){
		if(!$.util.exist(fiveColorPerson_heat_layer)){
			setTimeout(function(){showMapHeats()},2000);
			return;
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
	
	function newJqPoint(jqList){
		if(!$.util.exist(jqList) || !$.util.exist(layer_JqPoint)){
			return ;
		}
		layer_JqPoint.clear();
		$.each(jqList,function(i,val){
			var longitude = val.longitude;
			var latitude = val.latitude;
			var layer = layer_JqPoint;
			var symbolObj = {url:context+"/images/map/map-mark2.png", width:60, height:60};
			var data = val;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, heatMap, symbolObj, data);
		});
	}
	function newAjPoint(alarmPosLst){
		if(!$.util.exist(alarmPosLst) || !$.util.exist(layer_AjPoint)){
			return ;
		}
		layer_AjPoint.clear();
		$.each(alarmPosLst,function(i,val){
			var longitude = val.longitude;
			var latitude = val.latitude;
			var layer = layer_AjPoint;
			var symbolObj = {url:context+"/images/map/map-mark1.png", width:60, height:60};
			var data = val;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, heatMap, symbolObj, data);
		});
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
//		findAllAutoFlow:findAllAutoFlow
	});	
})(jQuery);