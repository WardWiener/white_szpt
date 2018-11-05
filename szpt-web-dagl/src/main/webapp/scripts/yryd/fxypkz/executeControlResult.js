(function($){
	"use strict";
	var table;
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	
	var layer_resultInfoPoint ;// WIFI场所监控点图层
	$(document).ready(function() {	
		
	});
	
	$(document).on("click", "#controlLi", function(){
		init();
	})
	
	
	function init(){
		$.ajax({
			url:context +'/yrydSnapshot/findControlkSnapshotInfo.action',
			type:'post',
			dataType:'json',
			data:{idCode:idcard},
			async:false,
			success:function(successData){
				var data = successData.resultMap.result;
				initPageField(data.basicMessage);
				initTable(data.tableLst);
			}
		})
	}
	

	/**
	 * 初始化页面基本信息
	 */
	function initPageField(data){
		$.each($("#executeControlId .valCell"),function(){
			$(this).text(data[$(this).attr("name")]);
		})
	}
	function initTable(tableLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = tableLst;
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "5%",
         	    	"title": "序号",
         	    	"data": "" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		return meta.row+1;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "12%",
					"title" : "捕捉对象",
					"data" : "catchObject",
					"render" : function(data, type, full, meta) {
						if(full.imgBase64!=null){ //如果是图片的话 捕捉对象是布控单号
//							var a='';
//							$.each(full.attachmentId,function(i,val){
//								a+='<p style="width:80px; overflow:hidden"><img src="'+context+'/personDetail/downloadFile.action?attachmentId='+val+'" width="100%"></p>';
//							});
							return full.surveilListNum;
						}
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "捕捉时间",
					"data" : "catchTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 3,
					"width" : "12%",
					"title" : "捕捉内容",
					"data" : "catchContent",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "12%",
					"title" : "操作",
					"data" : "",
					"render" : function(data, type, full, meta) {
//						var a='<a id="'+full.id+'"class="detail"href="#" target="">详情</a> '
//						if(full.attachmentId!=null){
//							a+=' <a id="'+full.id+'"class=""href="#" target="">忽略</a> ';
//							a+=' <a id="'+full.id+'"class=""href="#" target="">确认</a>';
//						}
						var a='';
						if(full.imgBase64!=null){
							a+='<p style="width:80px; overflow:hidden"><img src="data:image/png;base64,'+full.imgBase64+'"/></p>';
							a+='<a id="'+full.id+'" class="detail" href="#" target="" detail='+full.catchDetail+'>详情</a> ';
							a+=' <a id="'+full.id+'" class="ignore" href="#" target="">忽略</a> ';
							if(full.resultStatus!="1"){
								a+=' <a id="'+full.id+'" class="confirm" href="#" target="">确认</a>';
							}
						}else{
							a+='<a id="'+full.id+'" class="detail" href="#" target="" detail='+full.catchDetail+'>详情</a> ';
						}
						return a;
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
			table = $("#executeControlTable").DataTable(tb);
	}
	
//	function initLocusMap(){
//		var callback = function(arcgisEsri){
//	        //初始化map
//			locusMap = initBaseMap("locusMapConten");
//	        //map事件
//	        locusMap.on("click", function(e){
//	        	$.common.arcgisMapCommon.consoleLogCoordinate(e);
//	        });
//		    
//	        locusMap.on("load", function(e){
//		    	var mapPoint = arcgisEsri.Point(11876079.465427278, 3062348.6424484807, locusMap.spatialReference);
//		    	locusMap.centerAt(mapPoint);//将地图中心定位到经开区中心
//		    }); 
//	        
//	        //地图底图
//	        locusBaseMapServiceLayer = initBaseMapLayer();
//	        locusMap.addLayer(locusBaseMapServiceLayer);
//	        
//	        //重点人轨迹infoTemplate
//		    var fiveColorPerson_locus_Template = new arcgisEsri.InfoTemplate({
//		    	title:"详细",
//	        	content:function(arg0){
//		        	var result = arg0.data ;
//		        	//显示详细信息面板
//		        	var baseDiv = $("<div />", {
//		        		"class":"layer-police",
//		        		"style":"width:300px; padding:10px;",
//		        	});
//		        	var baseDiv_contentDiv = $("<div />",{
//		        		"class":"content"
//		        	}).appendTo(baseDiv);
//		        	
//		        	if(!$.util.exist(result)){
//		        		return baseDiv[0] ;
//		        	}
//		        	//捕捉对象
//		        	if(!$.util.isBlank(result.catchObject)){
//		        		$("<div />", {
//			        		"class":"row",
//		        			"style":"margin-top:5px",
//		        			"text":"捕捉对象："+result.catchObject
//			        	}).appendTo(baseDiv_contentDiv);
//		        	}
//		        	//捕捉时间
//		        	if(!$.util.isBlank(result.catchTime)){
//		        		$("<div />", {
//			        		"class":"row",
//		        			"style":"margin-top:5px",
//		        			"text":"捕捉时间："+$.date.timeToStr(result.catchTime, "yyyy-MM-dd HH:mm")
//			        	}).appendTo(baseDiv_contentDiv);
//		        	}
//		        	//捕捉内容
//		        	if(!$.util.isBlank(result.catchContent)){
//		        		$("<div />", {
//			        		"class":"row",
//		        			"style":"margin-top:5px",
//		        			"text":"捕捉内容："+result.catchContent
//			        	}).appendTo(baseDiv_contentDiv);
//		        	}
//		        	return baseDiv[0] ;
//	        	}
//		    }); 
//	        
//	        layer_resultInfoPoint = new arcgisEsri.GraphicsLayer({ 
//		    	id: "layer_resultInfoPoint",
//		    	infoTemplate:fiveColorPerson_locus_Template
//		    });
//	        locusMap.addLayer(layer_resultInfoPoint);
//		}
//		$.common.arcgisMapCommon.newArcgisMap(callback);
//	}
	
	function newPoint(resultList){
		if(!$.util.exist(resultList) || !$.util.exist(layer_resultInfoPoint)){
			setTimeout(function(){newPoint(resultList)},500);
			return ;
		}
		layer_resultInfoPoint.clear();
		var r;
		$.each(resultList,function(i,result){
			var longitude = result.catchLongitude;
			var latitude = result.catchLatitude;
			var layer = layer_resultInfoPoint;
			var symbolObj = {url:context+"/images/map/map-mark1.png", width:60, height:60};
			var data = result;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
			r=result;
		});
		$.common.arcgisMapCommon.setMapCentreAtByLonlat(r.catchLongitude, r.catchLatitude, locusMap);
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
	});	
})(jQuery);