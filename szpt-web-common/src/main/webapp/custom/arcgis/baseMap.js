
var map;//地图对象
var base_mapServiceLayer ;
var layer_graphicsGridding ;
var wangge_name_layer ;//网格名称
var layer_graphicsBoundary ;
var bianjie_name_layer ;//边界名称（派出所）
var layer_graphicsCommunity ;//社区图层
var community_name_layer ;//社区名称图层

var jinqing_heat_layer ;

var twoLevelCode ;//二级code
var threeLevelCodes ;//三级code数组

var layer_xcllTrajectory ;//巡查力量轨迹图层

var jq_layer ;
var aj_layer ;

var layer_graphicsPoliceForce ;

var search_layer ;

$.baseMap = $.baseMap || {};
(function($){
	
	"use strict";
	
	var mapBeforeReadyExeList = [] ;
	var mapReadyExeList = [] ;
	
	$(document).ready(function() {	
		
		require([
	        "esri/map",
			"esri/layers/ArcGISDynamicMapServiceLayer",
			"esri/layers/ArcGISTiledMapServiceLayer",
			"esri/dijit/Search", 
			"esri/layers/FeatureLayer", 
			"esri/InfoTemplate", 
			"esri/dijit/Scalebar",
			"esri/tasks/FindTask",
			"esri/tasks/FindParameters",
			"esri/tasks/IdentifyTask",
			"esri/tasks/IdentifyParameters",
			"dojo/dom",	      
			"esri/tasks/query", 
			"esri/tasks/QueryTask",
			"esri/symbols/PictureMarkerSymbol",
			"esri/graphic",
			"esri/geometry/Point",
			"esri/basemaps",
			"esri/SpatialReference",
			"esri/renderers/SimpleRenderer",
			"esri/symbols/SimpleMarkerSymbol",
			"esri/symbols/SimpleLineSymbol",
			"esri/Color",
			"esri/symbols/Font",
			"esri/toolbars/draw",
			"esri/symbols/SimpleFillSymbol",
			"dojo/parser",
			"dijit/registry",
			"esri/geometry/Polygon",  
			"esri/layers/GraphicsLayer",  
			"esri/geometry/Circle",  
			"esri/dijit/Popup", "esri/dijit/PopupTemplate",
			"dojo/dom-class", "dojo/dom-construct",
			"esri/symbols/TextSymbol",
			"esri/renderers/HeatmapRenderer",
			"esri/geometry/Polyline",
			"esri/symbols/CartographicLineSymbol",
			"extras/ClusterLayer",
			"dojo/_base/array",
			"esri/geometry/webMercatorUtils",
			"esri/renderers/ClassBreaksRenderer",
			"esri/geometry/geometryEngine",
			
			
			"dijit/layout/BorderContainer",
			"dijit/layout/ContentPane", 
			"dijit/form/Button",
			"dijit/WidgetSet",
			"dojo/domReady!"
	], function (	      
			Map,	  
			ArcGISDynamicMapServiceLayer, 
			ArcGISTiledMapServiceLayer,
			Search,
			FeatureLayer,
			InfoTemplate,
			Scalebar,
			FindTask,
			FindParameters,
			IdentifyTask,
			IdentifyParameters,
			dom,
			Query,
			QueryTask,
			PictureMarkerSymbol,
			Graphic,
			Point,
			esriBasemaps,
			SpatialReference,
			SimpleRenderer,
			SimpleMarkerSymbol,
			SimpleLineSymbol,
			Color,
			Font,
			Draw,
			SimpleFillSymbol,
			parser,
			registry,
			Polygon,
			GraphicsLayer,
			Circle, Popup, PopupTemplate,
			domClass, domConstruct,
			TextSymbol,
			HeatmapRenderer,
			Polyline,
			CartographicLineSymbol,
			ClusterLayer,
			arrayUtils,
			webMercatorUtils,
			ClassBreaksRenderer,
			geometryEngine) {
			
					executedBeforeMapReadyExeList();
			
					map = initBaseMap("mapContent") ;
					var mapPoint = new esri.geometry.Point(106.70641873100396, 26.51829942387364, map.spatialReference);
					map.centerAt(mapPoint);//将地图中心定位到事件中心

					base_mapServiceLayer = initBaseMapLayer() ;
					
					map.addLayer(base_mapServiceLayer);
					
					jq_layer = new GraphicsLayer({ 
				    	id: "jq_layer"
				    });
				    map.addLayer(jq_layer);
				    
				    aj_layer = new GraphicsLayer({ 
				    	id: "aj_layer"
				    });
				    map.addLayer(aj_layer);
				    
				    //警力图层
			        layer_graphicsPoliceForce = new GraphicsLayer({
				    	id: "layer_graphicsPoliceForce"
				    });
				    map.addLayer(layer_graphicsPoliceForce);
					
				    //巡查力量轨迹图层
				    layer_xcllTrajectory = new GraphicsLayer({
				    	id: "layer_xcllTrajectory"
				    });
				    map.addLayer(layer_xcllTrajectory);
				    layer_xcllTrajectory.hide();
				    
				    search_layer = new GraphicsLayer({
				    	id: "search_layer"
				    });
				    map.addLayer(search_layer);
				    
				    jinqing_heat_layer = new esri.layers.FeatureLayer({
			        	   layerDefinition:{
			            	   "geometryType": "esriGeometryPoint",  
				               "fields": ["*"]  
			               },
			               featureSet:null
			        	}, {
			        	   mode: esri.layers.FeatureLayer.MODE_SNAPSHOT,
					       outFields: ["*"]
			        }) ;
				    
			        var heatmapRenderer = new HeatmapRenderer({  
			        	// field: "ID",  
			            blurRadius: 10,  
			            maxPixelIntensity: 30,  
			            minPixelIntensity: 0  
			        });  
			        
			        jinqing_heat_layer.setRenderer(heatmapRenderer);      
			        jinqing_heat_layer.setVisibility(true) ;//显示巡查力量热力图层
			        
			        map.addLayer(jinqing_heat_layer);  
					
					initWangge() ;
				    
					initBianjie() ;
					
					initSheQu() ;
					
					executedMapReadyExeList() ;
					
				    map.on("click", function(e){
			            $.util.log("墨卡托坐标系：(x)："+e.mapPoint.x+",(y)："+e.mapPoint.y);
			            var lonlat = mercatorToLonlat(e.mapPoint.x, e.mapPoint.y);
			            $.util.log("经纬度坐标系：经度(x)："+lonlat.longitude+",纬度(y)："+lonlat.latitude);
			            
/*			            //测试点
			            var pt1 = new Point(e.mapPoint.x, e.mapPoint.y, map.spatialReference);  
			            var g1= new Graphic(pt1, null, {
			        	   //Graphic的额外属性
			        	   "attr":"热力测试"
			            }); 
			            jinqing_heat_layer.add(g1) ;
			            jinqing_heat_layer.show() ;*/
			          
			        });	
			
		});	
    });
	
	function executedBeforeMapReadyExeList(){
		$.each(mapBeforeReadyExeList, function(i, val){
			val() ;
		});
	}
	
	function executedMapReadyExeList(){
		$.each(mapReadyExeList, function(i, val){
			val() ;
		});
	}
	
	function initWangge(){
		
	    //网格图层
	    layer_graphicsGridding = new esri.layers.FeatureLayer(wangge_layer_server + "/0" ,{
	          //"opacity" : 0.4,
	          outFields: ["*"]
	    });
	    map.addLayer(layer_graphicsGridding);
	    layer_graphicsGridding.setVisibility(false) ;//隐藏
		
        //网格名称图层
        wangge_name_layer = new esri.layers.GraphicsLayer({ id: "wangge_name_layer" });
        map.addLayer(wangge_name_layer);
        
	    //网格添加事件
        layer_graphicsGridding.on('graphic-add', function(e) {
        	var graphic = e.graphic ;
        	var id = graphic.attributes.id;
        	
        	if($.util.isBlank(twoLevelCode) && $.util.isBlank(threeLevelCodes)){
        		setGriddingLayerStyle(graphic);
	    		return true;
	    	}
	        
	        if(id == twoLevelCode || $.inArray(id,threeLevelCodes) != -1){
	        	setGriddingLayerStyle(graphic);
	        }else{
	        	graphic.hide();
	        }
        }); 
        //更新开始事件
        layer_graphicsGridding.on('update-start', function(e) {
        	wangge_name_layer.clear();
        	layer_graphicsGridding.refresh();
        }); 
	}
	
	function initBianjie(){
	    //边界图层
	    layer_graphicsBoundary = new esri.layers.FeatureLayer(bianjie_layer_server + "/0" ,{
	    	//"opacity" : 0.5
	          outFields: ["*"]
	    });
	    map.addLayer(layer_graphicsBoundary);
	    layer_graphicsBoundary.setVisibility(false) ;//隐藏
	    
	    //派出所边界名称图层
	    bianjie_name_layer = new esri.layers.GraphicsLayer({ id: "bianjie_name_layer" });
        map.addLayer(bianjie_name_layer);
        
        //派出所图层添加事件
	    layer_graphicsBoundary.on('graphic-add', function(e) {
	    	var graphic = e.graphic ;
	    	var id = graphic.attributes.DATA_CODE;
	    	
	    	if($.util.isBlank(twoLevelCode) && $.util.isBlank(threeLevelCodes)){
	    		setBoundaryLayerStyle(graphic);
	    		return true;
	    	}
	        
	        if(id == twoLevelCode || $.inArray(id,threeLevelCodes) != -1){
	        	setBoundaryLayerStyle(graphic);
	        }else{
	        	graphic.hide();
	        }
        }); 
	    //更新开始事件
	    layer_graphicsBoundary.on('update-start', function(e) {
	    	bianjie_name_layer.clear();
	    	layer_graphicsBoundary.refresh();
        }); 
	}
	
	function initSheQu(){
		//社区图层
	    layer_graphicsCommunity = new esri.layers.FeatureLayer(community_layer_server + "/0" ,{
	          //"opacity" : 0.4,
	          outFields: ["*"]
	    });
	    map.addLayer(layer_graphicsCommunity);
	    layer_graphicsCommunity.setVisibility(false) ;//隐藏
	    
	    //社区名称图层
	    community_name_layer = new esri.layers.GraphicsLayer({ id: "community_name_layer" });
        map.addLayer(community_name_layer);
        
        //社区图层添加事件
        layer_graphicsCommunity.on('graphic-add', function(e) {
	    	var graphic = e.graphic ;
	    	var id = graphic.attributes.community_id;
	    	
	    	if($.util.isBlank(twoLevelCode) && $.util.isBlank(threeLevelCodes)){
	    		setCommunityLayerStyle(graphic);
	    		return true;
	    	}
	        
	        if(id == twoLevelCode || $.inArray(id,threeLevelCodes) != -1){
	        	setCommunityLayerStyle(graphic);
	        }else{
	        	graphic.hide();
	        }
        }); 
	    //更新开始事件
        layer_graphicsCommunity.on('update-start', function(e) {
        	community_name_layer.clear();
	    	layer_graphicsCommunity.refresh();
        }); 
	}
	
	/**
	 * 设置社区名称
	 */
	function setCommunityLayerName(graphic){
		var name = graphic.attributes.name ;
		var geometry = graphic.geometry ;
		var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
		new dojo.Color([0, 0, 0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
		new esri.symbol.Font("8pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
		var graphic = new esri.Graphic(geometry, textSymbol);
		community_name_layer.add(graphic) ;
	}
	
	/**
	 * 设置社区区域透明样式
	 */
	function setCommunityLayerStyle(graphic){
        
        var simpleLineSymbol = new esri.symbol.SimpleLineSymbol() ;
        simpleLineSymbol.setWidth(1) ;
        var highlightSymbol = new esri.symbol.SimpleFillSymbol() ;
        
        var communityCode = graphic.attributes.community_code ;
        switch(communityCode){
        	case "52019962000005"://竹林村
        		simpleLineSymbol.setColor(new dojo.Color([0, 79, 197])) ;
    	        highlightSymbol.setColor(new dojo.Color([0, 79, 197, arcgis_community_layer_opacity]));
        		break;
        	case "52019962000004"://金山村
        		simpleLineSymbol.setColor(new dojo.Color([1, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([1, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019962000003"://烂泥村
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019962000001"://金溪居委会
        		simpleLineSymbol.setColor(new dojo.Color([91, 217, 153])) ;
        		highlightSymbol.setColor(new dojo.Color([91, 217, 153, arcgis_community_layer_opacity]));
        		break;
        	case "52019962000002"://金农居委会
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000008"://龙王村
        		simpleLineSymbol.setColor(new dojo.Color([0, 79, 197])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 79, 197, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000007"://尖山村
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000001"://中曹居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000002"://五山居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000003"://北门居委会
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000001"://兴隆居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000006"://中院村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000004"://锦江居委会
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000003"://淮河居委会
        		simpleLineSymbol.setColor(new dojo.Color([91, 217, 153])) ;
        		highlightSymbol.setColor(new dojo.Color([91, 217, 153, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000005"://漓江居委会
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000005"://漓江居委会
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000006"://大寨村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019961000004"://滨河居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000014"://场坝村
        		simpleLineSymbol.setColor(new dojo.Color([203, 112, 215])) ;
        		highlightSymbol.setColor(new dojo.Color([203, 112, 215, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000013"://周家寨村
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000003"://红艳村
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000004"://王宽村
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000007"://翁岩村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000012"://麦乃村
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000009"://陈亮村
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000011"://把伙村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000010"://扬中村
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000008"://付官村
        		simpleLineSymbol.setColor(new dojo.Color([0, 129, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 129, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000005"://丰报云村
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000002"://红林居委会
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000006"://王武村
        		simpleLineSymbol.setColor(new dojo.Color([91, 217, 153])) ;
        		highlightSymbol.setColor(new dojo.Color([91, 217, 153, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000006"://松花江居委会
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019963000001"://航天园居委会
        		simpleLineSymbol.setColor(new dojo.Color([91, 217, 153])) ;
        		highlightSymbol.setColor(new dojo.Color([91, 217, 153, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000007"://毛寨村
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000005"://枫阳居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 129, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 129, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000001"://清水江居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 129, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 129, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000004"://大兴居委会
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000008"://珠显村
        		simpleLineSymbol.setColor(new dojo.Color([91, 217, 153])) ;
        		highlightSymbol.setColor(new dojo.Color([91, 217, 153, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000003"://华阳居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000007"://大坡村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019964000002"://盘江居委会
        		simpleLineSymbol.setColor(new dojo.Color([230, 86, 122])) ;
        		highlightSymbol.setColor(new dojo.Color([230, 86, 122, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000005"://瑞和居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000002"://香江居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 129, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 129, 228, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000006"://云凯居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 79, 197])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 79, 197, arcgis_community_layer_opacity]));
        		break;
        	case "52019960000005"://浦江居委会
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000001"://长江居委会
        		simpleLineSymbol.setColor(new dojo.Color([118, 88, 248])) ;
        		highlightSymbol.setColor(new dojo.Color([118, 88, 248, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000002"://黄河居委会
        		simpleLineSymbol.setColor(new dojo.Color([234, 192, 80])) ;
        		highlightSymbol.setColor(new dojo.Color([234, 192, 80, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000004"://洛解村
        		simpleLineSymbol.setColor(new dojo.Color([128, 218, 0])) ;
        		highlightSymbol.setColor(new dojo.Color([128, 218, 0, arcgis_community_layer_opacity]));
        		break;
        	case "52019965000003"://珠江居委会
        		simpleLineSymbol.setColor(new dojo.Color([0, 192, 228])) ;
        		highlightSymbol.setColor(new dojo.Color([0, 192, 228, arcgis_community_layer_opacity]));
        		break;
        	default :
        		simpleLineSymbol.setColor(new dojo.Color([0, 0, 0])) ;
    			highlightSymbol.setColor(new dojo.Color([0, 0, 0, arcgis_community_layer_opacity]));
        		break;
        }
        highlightSymbol.setOutline(simpleLineSymbol) ;
        graphic.setSymbol(highlightSymbol);
        graphic.draw() ;
        setCommunityLayerName(graphic);
	}
	
	/**
	 * 设置派出所名称
	 */
	function setBoundaryLayerName(graphic){
		var name = graphic.attributes.name ;
		var geometry = graphic.geometry ;
		var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
		new esri.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
		new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
		
		var graphic = new esri.Graphic(geometry, textSymbol);
		bianjie_name_layer.add(graphic) ;
	}
	
	/**
	 * 设置派出所区域透明样式
	 */
	function setBoundaryLayerStyle(graphic){
        var id = graphic.attributes.id;
        
        var simpleLineSymbol = new esri.symbol.SimpleLineSymbol() ;
        var highlightSymbol = new esri.symbol.SimpleFillSymbol() ;
        
        switch (id){
        	case "jinzhu":
        		simpleLineSymbol.setColor(new dojo.Color([234,192,80])) ;
        		highlightSymbol.setColor(new esri.Color([234,192,80, arcgis_pcs_layer_opacity])); 
        	break;
        	case "sanjiang":
        		simpleLineSymbol.setColor(new dojo.Color([0,192,228])) ;
        		highlightSymbol.setColor(new esri.Color([0,192,228, arcgis_pcs_layer_opacity])); 
        	break;
        	case "pingqiao":
        		simpleLineSymbol.setColor(new dojo.Color([91,217,153])) ;
        		highlightSymbol.setColor(new esri.Color([91,217,153, arcgis_pcs_layer_opacity])); 
        	break;
        	case "daxing":
        		simpleLineSymbol.setColor(new dojo.Color([118,88,248])) ;
        		highlightSymbol.setColor(new esri.Color([118,88,248, arcgis_pcs_layer_opacity])); 
        	break;
        	case "hunaghe":
        		simpleLineSymbol.setColor(new dojo.Color([203,112,215])) ;
        		highlightSymbol.setColor(new esri.Color([203,112,215, arcgis_pcs_layer_opacity])); 
        	break;
        	case "changjiang":
        		simpleLineSymbol.setColor(new dojo.Color([230,86,122])) ;
        		highlightSymbol.setColor(new esri.Color([230,86,122, arcgis_pcs_layer_opacity])); 
        	break;
        }
        highlightSymbol.setOutline(simpleLineSymbol) ;
        graphic.setSymbol(highlightSymbol);
        graphic.draw() ;
        setBoundaryLayerName(graphic);
	}
	
	/**
	 * 设置网格名称
	 */
	function setGriddingLayerName(graphic){
		var id = graphic.attributes.id ;
		var name = graphic.attributes.name ;
		if(id.substring(0,1) == "J"){
			var geometry = graphic.geometry ;
			var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
			new esri.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
			new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
			var graphic = new esri.Graphic(geometry, textSymbol);
			wangge_name_layer.add(graphic) ;
		}
		if(id.substring(0,1) == "B"){
			var geometry = graphic.geometry ;
			var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
			new esri.Color([230,86,122])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
			new esri.symbol.Font("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
			var graphic = new esri.Graphic(geometry, textSymbol);
			wangge_name_layer.add(graphic) ;
		}	
	}
	
	/**
	 * 设置网格区域透明样式
	 */
	function setGriddingLayerStyle(graphic){
		var id = graphic.attributes.id ;
        
        if(id.substring(0,1) == "J"){
        	var simpleLineSymbol = new esri.symbol.SimpleLineSymbol() ;
        	switch(id){
	        	case "JA01" :
	        		simpleLineSymbol.setColor(new dojo.Color([0,79,197])) ;
	        		break;
	        	case "JA02" :
	        		simpleLineSymbol.setColor(new dojo.Color([118,88,248])) ;
	        		break;
	        	case "JA03" :
	        		simpleLineSymbol.setColor(new dojo.Color([230,86,122])) ;
	        		break;
	        	case "JA04" :
	        		simpleLineSymbol.setColor(new dojo.Color([0,192,228])) ;
	        		break;
        	}
	        simpleLineSymbol.setWidth(3) ;
	        
	        var highlightSymbol = new esri.symbol.SimpleFillSymbol() ;
	        highlightSymbol.setColor(new dojo.Color([0,0,0, 0.0])); 
	        highlightSymbol.setOutline(simpleLineSymbol) ;
	        graphic.setSymbol(highlightSymbol);
        }else{
        	var simpleLineSymbol = new esri.symbol.SimpleLineSymbol() ;
	        var highlightSymbol = new esri.symbol.SimpleFillSymbol() ;
	        
	        switch (id){
	        	case "B01":
	        		highlightSymbol.setColor(new dojo.Color([234,192,80, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([234,192,80, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B02":
	        		highlightSymbol.setColor(new dojo.Color([0,79,197, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([0,79,197, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B03":
	        		highlightSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B04":
	        		highlightSymbol.setColor(new dojo.Color([118,88,248, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([118,88,248, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B05":
	        		highlightSymbol.setColor(new dojo.Color([255,242,93, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([255,242,93, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B06":
	        		highlightSymbol.setColor(new dojo.Color([128,218,0, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([128,218,0, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B07":
	        		highlightSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B08":
	        		highlightSymbol.setColor(new dojo.Color([230,86,122, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([230,86,122, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B09":
	        		highlightSymbol.setColor(new dojo.Color([118,88,248, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([118,88,248, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B10":
	        		highlightSymbol.setColor(new dojo.Color([128,218,0, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([128,218,0, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B11":
	        		highlightSymbol.setColor(new dojo.Color([230,86,122, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([230,86,122, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B12":
	        		highlightSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([0,192,228, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        	case "B13":
	        		highlightSymbol.setColor(new dojo.Color([0,79,197, arcgis_zgfg_layer_opacity])); 
	        		simpleLineSymbol.setColor(new dojo.Color([0,79,197, arcgis_zgfg_line_layer_opacity])) ;
	        	break;
	        }
	        highlightSymbol.setOutline(simpleLineSymbol) ;
	        graphic.setSymbol(highlightSymbol);
        }
        graphic.draw() ;
        setGriddingLayerName(graphic);
	}
	
	function add_Jq_Pos(list, jqTypes){
		$.each(list, function(i, val){
			if($.inArray(val.type, jqTypes)>-1 && val.type=="1"){
				var lonlat = lonlatToMercator(val.longitude, val.latitude);
				var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
				var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-orange.png", 15, 15);
				var graphic = new esri.Graphic(mapPoint, symbol);
				jq_layer.add(graphic);
			}
		});
	}
	
	function addJqPos(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-orange.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_za(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-za.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_xs(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-xs.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_qz(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-qz.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_sw(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-sw.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_ts(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-ts.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_sa(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-sa.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_jf(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-jf.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addJqlx_qt(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/map-jqlx-qt.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			jq_layer.add(graphic);
		});
	}
	
	function addAjPos(list){
		$.each(list, function(i, val){
			var lonlat = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
			var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-red.png", 15, 15);
			var graphic = new esri.Graphic(mapPoint, symbol);
			aj_layer.add(graphic);
		});
	}
	
	function add_Aj_Pos(list, jqTypes){
		$.each(list, function(i, val){
			if($.inArray(val.type, jqTypes)>-1 && val.type=="2"){
				var lonlat = lonlatToMercator(val.longitude, val.latitude);
				var mapPoint = esri.geometry.Point(lonlat.x, lonlat.y, map.spatialReference);
				var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-red.png", 15, 15);
				var graphic = new esri.Graphic(mapPoint, symbol);
				jq_layer.add(graphic);
			}
		});
	}
	
	/*
	* 获取随机颜色值
	* @param min，最小取值，取值范围0-255
	* @param max，最大取值，取值范围0-255
	*/ 
	function getColor(min,max){ 
	    var co = ""; 
	    co+=(getRandom(min,max)+","); 
	    co+=(getRandom(min,max)+","); 
	    co+=getRandom(min,max); 
	    return RGB2Hex(co); 
	} 
	 
	/*
	* 获取指定范围随机数
	* @param min，最小取值
	* @param max，最大取值
	*/ 
	function getRandom(min,max){ 
	    //x上限，y下限  
	       var x = max;  
	       var y = min;  
	    if(x<y){ 
	        x=min; 
	        y=max; 
	    } 
	       var rand = parseInt(Math.random() * (x - y + 1) + y); 
	    return rand; 
	} 
	 
	/*
	* 获取颜色值
	* @param rgb, RGB颜色值，如 "23,189,246"
	*/ 
	function RGB2Hex(rgb){ 
	    var re = rgb.replace(/(?:\(|\)|rgb|RGB)*/g,"").split(",");//利用正则表达式去掉多余的部分 
	    var hexColor = "#"; 
	    var hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']; 
	    for (var i = 0; i < 3; i++) { 
	        var r = null; 
	        var c = re[i]; 
	        var hexAr = []; 
	        while (c > 16) { 
	            r = c % 16; 
	            c = (c / 16) >> 0; 
	            hexAr.push(hex[r]); 
	        } 
	        hexAr.push(hex[c]); 
	        hexColor += hexAr.reverse().join(''); 
	    } 
		return hexColor; 
	}
	
	jQuery.extend($.baseMap, { 
		addToMapBeforeReadyExeList:function(func){
			mapBeforeReadyExeList.push(func) ;
		},
		addToMapReadyExeList:function(func){
			mapReadyExeList.push(func) ;
		},
		showWange:function(showOrHide){
			if(showOrHide){
				layer_graphicsGridding.show() ;
				wangge_name_layer.show() ;
				return ;
			}
			
			layer_graphicsGridding.hide() ;
			wangge_name_layer.hide() ;
		},
		showBianjie:function(showOrHide){
			if(showOrHide){
				layer_graphicsBoundary.show() ;
				bianjie_name_layer.show() ;
				return ;
			}
			
			layer_graphicsBoundary.hide() ;
			bianjie_name_layer.hide() ;
		},
		showSheQu:function(showOrHide){
			if(showOrHide){
				layer_graphicsCommunity.show() ;
				community_name_layer.show() ;
				return ;
			}
			
			layer_graphicsCommunity.hide() ;
			community_name_layer.hide() ;
		},
		refreshJqPos:function(list, jqTypes){
			jq_layer.clear() ;
			aj_layer.clear() ;
			add_Jq_Pos(list, jqTypes) ;
			add_Aj_Pos(list, jqTypes) ;
		},
		refresh_Jq_Pos:function(list){
			jq_layer.clear() ;
			addJqPos(list) ;
		},
		refresh_Jqlx_za:function(list){
			addJqlx_za(list) ;
		},
		refresh_Jqlx_xs:function(list){
			addJqlx_xs(list) ;
		},
		refresh_Jqlx_qz:function(list){
			addJqlx_qz(list) ;
		},
		refresh_Jqlx_sw:function(list){
			addJqlx_sw(list) ;
		},
		refresh_Jqlx_ts:function(list){
			addJqlx_ts(list) ;
		},
		refresh_Jqlx_sa:function(list){
			addJqlx_sa(list) ;
		},
		refresh_Jqlx_jf:function(list){
			addJqlx_jf(list) ;
		},
		refresh_Jqlx_qt:function(list){
			addJqlx_qt(list) ;
		},
		refresh_Aj_Pos:function(list){
			aj_layer.clear() ;
			addAjPos(list) ;
		},
		refreshJinQingHeat:function(positions, type){
			jinqing_heat_layer.clear() ;
			
			if(!type){
				jinqing_heat_layer.renderer.maxPixelIntensity = 30 ;
				jinqing_heat_layer.redraw();
			}
			
			if(type && type=="xcll"){
				jinqing_heat_layer.renderer.maxPixelIntensity = 2 ;
				jinqing_heat_layer.redraw();
			}

			$.each(positions, function(i, val){
				var mercator = lonlatToMercator(val.longitude,val.latitude);
	            var pt = new esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);  
	            var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-red.png", 15, 15);
	            var g = new esri.Graphic(pt, symbol, {
	        	   //Graphic的额外属性
	        	   "attr":"巡查力量热力图"
	            }); 
	            jinqing_heat_layer.add(g) ;
			});
			jinqing_heat_layer.show() ;
		},
		refreshHeat:function(positions){
			jinqing_heat_layer.clear() ;
			jinqing_heat_layer.renderer.maxPixelIntensity = 30 ;
			jinqing_heat_layer.redraw();
			$.each(positions, function(i, val){
				var mercator = lonlatToMercator(val.longitude,val.latitude);
	            var pt = new esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);  
	            var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-red.png", 15, 15);
	            var g = new esri.Graphic(pt, symbol, {
	        	   "attr":"巡查力量热力图"
	            }); 
	            jinqing_heat_layer.add(g) ;
			});
			jinqing_heat_layer.show() ;
		},
		refreshXcllHeat:function(positions){
			jinqing_heat_layer.clear() ;
			
			jinqing_heat_layer.renderer.maxPixelIntensity = 2 ;
			jinqing_heat_layer.redraw();
			
			$.each(positions, function(i, val){
				var mercator = lonlatToMercator(val.longitude,val.latitude);
	            var pt = new esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);  
	            var symbol =  new esri.symbol.PictureMarkerSymbol(context+"/images/map/dot-mark-red.png", 15, 15);
	            var g = new esri.Graphic(pt, symbol, {
	        	   "attr":"巡查力量热力图"
	            }); 
	            jinqing_heat_layer.add(g) ;
			});
			jinqing_heat_layer.show() ;
		},
		layersClear:function(){  //ADD BY XIEHF  清除警情、案件、巡查力量图层。
			jq_layer.clear() ;
			aj_layer.clear() ;
			jinqing_heat_layer.clear() ;
		},
		showLayerByLayerTypeAndIds:function(oneLevelType,twoLevelValue,threeLevelValues){
			
			twoLevelCode = twoLevelValue;
			if($.util.exist(threeLevelValues) && threeLevelValues.length > 0){
				threeLevelCodes = threeLevelValues;
			}else{
				threeLevelCodes = new Array();
			}
			
			switch(oneLevelType){
			case $.common.Constant.ZG()://网格
				
				layer_graphicsGridding.refresh();//刷新网格
			
				break;
			case $.common.Constant.PCS()://派出所
				
				layer_graphicsBoundary.refresh();//刷新边界
			
				if($.util.exist(threeLevelValues) && threeLevelValues.length > 0){
					$.baseMap.showSheQu(true);
					layer_graphicsCommunity.refresh();//刷新社区
				}
				break;
			case $.common.Constant.SQ():
				
				layer_graphicsCommunity.refresh();//刷新社区
			
				break;
			}
		},
		showTrajectoryLine:function(trajectoryList){
			layer_xcllTrajectory.clear();
			if(!$.util.exist(trajectoryList) || trajectoryList.length < 1){
				return ;
			}
			var pathsArray = new Array();
			var pathArray = new Array();
			$.each(trajectoryList,function(t,trajectory){
				var mercator = lonlatToMercator(trajectory.longitude,trajectory.latitude);
				var pointArray = new Array();
				pointArray[0] = mercator.x;
				pointArray[1] = mercator.y;
				pathArray.push(pointArray);
			});
			pathsArray.push(pathArray);
			
			var line = new esri.geometry.Polyline({
				"paths": pathsArray,  
				"spatialReference": map.spatialReference
			});
			var lineSymbol = new esri.symbol.CartographicLineSymbol(
				esri.symbol.CartographicLineSymbol.STYLE_SOLID,
				new dojo.Color(getColor(0,255)), 3,
				esri.symbol.CartographicLineSymbol.CAP_ROUND,
				esri.symbol.CartographicLineSymbol.JOIN_MITER, 10
			);
			var graphic = new esri.Graphic(line, lineSymbol);
			layer_xcllTrajectory.add(graphic);
			layer_xcllTrajectory.show();
		},
		searchBaseMap:function(searchText, isClear, isCenterToFirstPoint, imgInfo){
			
			if(isClear){
				search_layer.clear() ;
			}
			
			var findTask = new esri.tasks.FindTask(base_map_server);
			var findParams = new esri.tasks.FindParameters();
			
			//设置需要查询那些具体图层
			var layerIdArray = new Array();
			for(var i=0;i<59;i++){
				layerIdArray.push(i);
			}
			
			findParams.layerIds = layerIdArray;
			findParams.searchFields = ["Name","Ctype","Address"];
			findParams.searchText = searchText;
			findParams.returnGeometry = true;
			
			findTask.execute(findParams, function(arg0){
				$.each(arg0, function(i, val){
	
					if(!$.util.exist(val.feature)){
						return true;
					}
					if(!$.util.exist(val.feature.attributes) || !$.util.exist(val.feature.geometry)){
						return true;
					}
					if($.util.isBlank(val.feature.geometry.x) || $.util.isBlank(val.feature.geometry.y)){
						return true;
					}
			
					var mapPoint = esri.geometry.Point(val.feature.geometry.x, val.feature.geometry.y, map.spatialReference);
					
					if(i==0 && isCenterToFirstPoint){
						map.centerAt(mapPoint);
					}
					
					var image = {
						url:context+"/images/map/map-mark1.png",
						width:60,
						height:60
					} ;
					if(imgInfo){
						image = imgInfo ;
					}
					
					var symbol =  new esri.symbol.PictureMarkerSymbol(image.url, image.width, image.height);
					symbol.setOffset(+0, +(symbol.height/2));
					var graphic = new esri.Graphic(mapPoint, symbol);					
					graphic.attributes = val.feature.attributes ;
					search_layer.add(graphic) ;
				});
			});
		}
	});

})(jQuery);	