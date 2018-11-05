$.multiBaseMap = $.multiBaseMap || {};

(function($){
	
	"use strict";
	
	var mapBeforeReadyExeList = [] ;
	var mapReadyExeList = [] ;
	
	var mapObjs = {};
	
	$(document).ready(function() {	
		
    });
	
	function createMap(mapIds){
		
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
			
					$.each(mapIds,function(i,mapId){
						
						var fill = new SimpleFillSymbol("solid", null, new Color("#A4CE67"));
						var popup = new Popup({
				              fillSymbol: fill,
				              titleInBody: false
				        }, domConstruct.create("div"));
						
						var map = initBaseMap(mapId, popup) ;
						var mapPoint = new esri.geometry.Point(106.70641873100396, 26.51829942387364, map.spatialReference);
						map.centerAt(mapPoint);//将地图中心定位到事件中心

						var base_mapServiceLayer = initBaseMapLayer() ;
						
						map.addLayer(base_mapServiceLayer);
					    
					    var mapObj = new Object();
					    mapObj.arcgisMap = map ;
					    mapObj.popup = popup ;
					    mapObj.arcgis_webMercatorUtils = webMercatorUtils ;
					    mapObj.arcgis_ClusterLayer = ClusterLayer ;
					    mapObj.arcgis_geometryEngine = geometryEngine ;
					    mapObj.griddingLayer = null ;// 网格图层
					    mapObj.pcsBoundaryLayer = null ;// 派出所图层
					    mapObj.setClickCallback = setClickCallback ;
					    mapObj.setLoadCallback = setLoadCallback ;
					    mapObj.setMapCenterAt = setMapCenterAt ;
					    mapObj.getLayerById = getLayerById ;
					    mapObj.getAddressByLonlat = getAddressByLonlat ;
					    mapObj.queryLocationByKeyword = queryLocationByKeyword ;
					    mapObj.queryCommunityCodeByLonlat = queryCommunityCodeByLonlat ;
					    mapObj.createPoint = createPoint ;//创建点
					    mapObj.createLine = createLine ; //创建线
					    mapObj.createSymbol = createSymbol ;//创建样式点
					    mapObj.createLayer = createLayer ;
					    mapObj.createHeatLayer = createHeatLayer ;
					    mapObj.createCircle = createCircle ;
					    mapObj.createClusters = createClusters ;
					    mapObj.createInfoTemplate = createInfoTemplate ;
					    mapObj.showMenuContent = showMenuContent ;
					    mapObj.showHeatLayer = showHeatLayer ;
					    mapObj.findBaseDataLayer = findBaseDataLayer ;
					    mapObj.initGriddingLayer = initGriddingLayer ;
					    mapObj.initGriddingNameLayer = initGriddingNameLayer ;
					    mapObj.initPcsBoundaryLayer = initPcsBoundaryLayer ;
					    mapObj.initPcsBoundaryNameLayer = initPcsBoundaryNameLayer ;
					    mapObjs[mapId] = mapObj;
					});
					
					executedMapReadyExeList() ;
				});	
	}
	
	/**
	 * 创建热力图层
	 */
	var createHeatLayer = function(){
		var map = this.arcgisMap;
		var heatmapRenderer = new esri.renderer.HeatmapRenderer({  
	        //field: "ID",  
	        blurRadius: 10,  
	        maxPixelIntensity: 30,  
	        minPixelIntensity: 0  
		}); 
		//热力图层
		var heat_layer = new esri.layers.FeatureLayer({
		 	   layerDefinition:{
		     	   "geometryType": "esriGeometryPoint",  
		            "fields": ["*"]  
		        },
		        featureSet:null
		 	}, {
		 	   mode: esri.layers.FeatureLayer.MODE_SNAPSHOT,
			       outFields: ["*"]
		 }) ;
		 heat_layer.setRenderer(heatmapRenderer);      
		 map.addLayer(heat_layer); 
		 return heat_layer;
	}
	
	/**
	 * 显示热力图图层
	 * 
	 * @param heatLayer 热力图图层
	 * @param lonlatArray 经纬度数组
	 */ 
	var showHeatLayer = function(heatLayer, lonlatArray){
		var map = this.arcgisMap;
		
		if($.util.exist(heatLayer)){
			heatLayer.clear() ;
			heatLayer.redraw() ;
		}
		
		$.each(lonlatArray, function(i, val){
			if($.util.isBlank(val.longitude) || $.util.isBlank(val.latitude)){
				return true;
			}
			
			var mercator = lonlatToMercator(val.longitude, val.latitude);
			var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
			var graphic = new esri.Graphic(mapPoint, null);
			
			heatLayer.add(graphic) ;
		});
	}
	
	/**
	 * 初始化派出所边界名称图层
	 * 
	 * @param flag 初始化完成后是否显示图层
	 */
	var initPcsBoundaryNameLayer = function(flag){
		var map = this.arcgisMap;
		var pcsBoundaryNameLayer = this.createLayer("graphics", "pcsBoundaryNameLayer") ;
        var queryTask = new esri.tasks.QueryTask(bianjie_layer_server+"/0");
    	var query = new esri.tasks.Query();
    	query.outFields = ["*"];
    	query.where = "1=1" ;
    	query.returnGeometry = true;
    	queryTask.execute(query, function(arg0){
    		pcsBoundaryNameLayer.clear();
    		$.each(arg0.features, function(i, val){
				var name = val.attributes.name ;
				var geometry = val.geometry ;
    			var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
    			new esri.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
    			new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
    			
    			var graphic = new esri.Graphic(geometry, textSymbol);
    			pcsBoundaryNameLayer.add(graphic);
    		});
    	});
        map.addLayer(pcsBoundaryNameLayer);
        if(flag != true){
        	pcsBoundaryNameLayer.hide();
	    }
        return pcsBoundaryNameLayer;
	}
	
	/**
	 * 初始化派出所边界图层
	 * 
	 * @param flag 初始化完成后是否显示图层
	 */
	var initPcsBoundaryLayer = function (flag){
		var map = this.arcgisMap;
		if($.util.exist(this.pcsBoundaryLayer)){
			return ;
		}
		var pcsBoundaryLayer = new esri.layers.FeatureLayer(bianjie_layer_server + "/0" ,{
	          outFields: ["*"]
	    });
		map.addLayer(pcsBoundaryLayer);
		this.pcsBoundaryLayer = pcsBoundaryLayer;
		if(flag != true){
			pcsBoundaryLayer.hide();
	    }
		
		//设置派出所区域透明样式
		pcsBoundaryLayer.on('graphic-add', function(e) {
	        var graphic = e.graphic ;
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
        });  
		
		return pcsBoundaryLayer;
	}
	
	/**
	 * 初始化网格名称图层
	 * 
	 * @param flag 初始化完成后是否显示图层
	 */
	var initGriddingNameLayer = function(flag){
		var map = this.arcgisMap;
		var griddingNameLayer = this.createLayer("graphics", "griddingNameLayer") ;
        var queryTask = new esri.tasks.QueryTask(wangge_layer_server+"/0");
    	var query = new esri.tasks.Query();
    	query.outFields = ["*"];
    	query.where = "1=1" ;
    	query.returnGeometry = true;
    	queryTask.execute(query, function(arg0){
    		griddingNameLayer.clear();
    		$.each(arg0.features, function(i, val){
				var id = val.attributes.id ;
				var name = val.attributes.name ;
				if(id.substring(0,1) == "J"){
					var geometry = val.geometry ;
        			var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
        			new esri.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
        			new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
        			var graphic = new esri.Graphic(geometry, textSymbol);
        			griddingNameLayer.add(graphic) ;
				}
				if(id.substring(0,1) == "B"){
					var geometry = val.geometry ;
        			var textSymbol =  new esri.symbol.TextSymbol(name).setColor(
        			new esri.Color([230,86,122])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
        			new esri.symbol.Font("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD)) ;
        			var graphic = new esri.Graphic(geometry, textSymbol);
        			griddingNameLayer.add(graphic) ;
				}
    		});
    	});
        map.addLayer(griddingNameLayer);
        if(flag != true){
        	griddingNameLayer.hide();
	    }
        return griddingNameLayer;
	}
	
	/**
	 * 初始化网格图层
	 * 
	 * @param flag 初始化完成后是否显示图层
	 */
	var initGriddingLayer = function(flag){
		var map = this.arcgisMap;
		if($.util.exist(this.griddingLayer)){
			return ;
		}
		var griddingLayer = new esri.layers.FeatureLayer(wangge_layer_server + "/0" ,{
	          //"opacity" : 0.4,
	          outFields: ["*"]
	    });
	    map.addLayer(griddingLayer);
	    this.griddingLayer = griddingLayer;
	    if(flag != true){
	    	griddingLayer.hide();
	    }
	    
	    //设置网格区域透明样式
	    griddingLayer.on('graphic-add', function(e) {

	        var graphic = e.graphic ;
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
        });
	    
	    return griddingLayer;
	};
	
	/**
	 * 查询地图基础数据图层
	 * 
	 * @param sql 查询条件
	 * @param layerServer 查询的图层地址
	 * @param resultFunc 查询出来的结果处理回调函数
	 */
	var findBaseDataLayer = function(sql, layerServer, resultFunc){
		var sql = $.util.isBlank(sql) ? "1=1" : sql ;
		var layerServer = $.util.isBlank(layerServer) ? "/1" : layerServer ;
		
		var queryTask = new esri.tasks.QueryTask(layer_map_server + layerServer);
		var query = new esri.tasks.Query();
		query.outFields = ["*"];
		query.where = sql ;
		query.returnGeometry = true;
		queryTask.execute(query, function(arg0){
			if($.util.isFunction(resultFunc)){
				resultFunc(arg0.features);
			}
		});
	};
	
	/**
	 * 将地图中心定位到给定的点
	 * 
	 * @param lonlat 经纬度对象，例如：{longitude:经度,latitude:纬度}
	 */
	var setMapCenterAt = function(lonlat){
		var map = this.arcgisMap;
		
		//将经纬度转换为墨卡托，创建一个Point对象
		var mercator = lonlatToMercator(lonlat.longitude, lonlat.latitude);
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		map.centerAt(mapPoint);
		return mapPoint;
	};
	
	/**
	 * 创建详细信息模版
	 * 
	 * @param title 模版抬头
	 * @param contentFunc 内容回调函数
	 */
	var createInfoTemplate = function(title, contentFunc){
		var template = new esri.InfoTemplate({
			title : title ,
			content : contentFunc
		});
		return template;
	};
	
	/**
	 * 创建图层
	 * 
	 * @param layerType 图层类型
	 * @param layerId 图层id
	 * @param infoTemplate 弹出框模版
	 */
	var createLayer = function(layerType, layerId, infoTemplate){
		var map = this.arcgisMap;
		var layer = null;
		switch(layerType){
			case "graphics":
				layer = new esri.layers.GraphicsLayer({ 
					id: layerId ,
					infoTemplate : infoTemplate
				});
				break;
		}
		if($.util.exist(layer)){
			map.addLayer(layer);
		}
		return layer;
	}
	
	/**
	 * 创建圆
	 * 
	 * @param layerId 点图层id
	 * @param lonlat 经纬度对象，例如：{longitude:经度, latitude:纬度}
	 * @param scope 范围，半径
	 * @returns circle 圈圈对象
	 */
	var createCircle = function(layerId, lonlat, scope){
		var map = this.arcgisMap;
		//判断传入的坐标点是否正确
		if(!$.util.exist(lonlat) || $.util.isBlank(lonlat.longitude) || $.util.isBlank(lonlat.latitude)){
			return null;
		}
		//判断该图层是否已存在，存在直接添加点，不存在创建一个新的图层添加点
		var layer = map.getLayer(layerId);
		if(!$.util.exist(layer)){
			layer = this.createLayer("graphics", layerId);
		}
		//将经纬度转换为墨卡托，创建一个Point对象
		var mercator = lonlatToMercator(lonlat.longitude, lonlat.latitude);
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		
		var symbol = new esri.symbol.SimpleFillSymbol().setColor(null).outline.setColor("blue");
		var circle = new esri.geometry.Circle({
			center: mapPoint,
			geodesic: false,
			radius: scope
		});
		var graphic = new esri.Graphic(circle, symbol);
		layer.add(graphic);
		
		return circle;
	}
	
	/**
	 * 创建点样式symbol
	 * 
	 * @param image 点图片样式，例如：{url:图片地址url,width:宽度,height:高度}
	 */
	var createSymbol = function(image){
		var symbol =  new esri.symbol.PictureMarkerSymbol(image.url, image.width, image.height);
		symbol.setOffset(+0, +(symbol.height/2));
		return symbol;
	};
	
	/**
	 * 创建聚合点
	 * 
	 * @param layerId 图层id
	 * @param graphicArray 地图点对象数组
	 * @param image1-4 四种聚合状态的图标样式
	 * @param infoTemplate 弹出框模版
	 */
	var createClusters = function(layerId, graphicArray, image1, image2, image3, image4, infoTemplate){
		var map = this.arcgisMap;
		var arcgis_webMercatorUtils = this.arcgis_webMercatorUtils;
		var info = {};
	    var wgs = map.patialReference ;
	    
	    info.data = [] ;
	    $.each(graphicArray, function(i, p){
	        var latlng = new  esri.geometry.Point(parseFloat(p.geometry.x), parseFloat(p.geometry.y), wgs);
	        var webMercator = arcgis_webMercatorUtils.geographicToWebMercator(latlng);

	        info.data.push({
	          "x": webMercator.x,
	          "y": webMercator.y,
	          "attributes": p.attributes
	        });
	    });
	    var layer = map.getLayer(layerId);
		if($.util.exist(layer)){
			$.each(info.data, function(i, val){
				layer.add(val) ;
	    	});
			layer.refreshLayer() ;
		}else{
			layer = layer = new this.arcgis_ClusterLayer({
	            "data": new Array(),
	            "distance": 30,//1000000
	            "id": layerId,
	            "labelColor": "#fff",
	            "labelOffset": 10,
	            "resolution": map.extent.getWidth() / map.width,
	            "singleColor": "#888",
	            "singleTemplate": infoTemplate
	        });
		    map.addLayer(layer);
		    
		    $.each(info.data, function(i, val){
				layer.add(val) ;
	    	});
			layer.refreshLayer() ;
		}
		
		var defaultSym = new esri.symbol.SimpleMarkerSymbol().setSize(4);
        var renderer = new esri.renderer.ClassBreaksRenderer(defaultSym, "clusterCount");

        var defaultIcon = new esri.symbol.PictureMarkerSymbol(image1.url, image1.width, image1.height).setOffset(0, 15);
        var blue = new esri.symbol.PictureMarkerSymbol(image2.url, image2.width, image2.height).setOffset(0, 15);
        var green = new esri.symbol.PictureMarkerSymbol(image3.url, image3.width, image3.height).setOffset(0, 15);
        var red = new esri.symbol.PictureMarkerSymbol(image4.url, image4.width, image4.height).setOffset(0, 15);
        renderer.addBreak(0, 1, defaultIcon);
        renderer.addBreak(1, 2, blue);
        renderer.addBreak(2, 20, green);
        renderer.addBreak(20, 1001, red);

        layer.setRenderer(renderer);
	};
	
	/**
	 * 创建点
	 * 
	 * @param layerId 点图层id
	 * @param lonlat 经纬度对象，例如：{longitude:经度, latitude:纬度}
	 * @param image 点图片样式，例如：{url:图片地址url,width:宽度,height:高度}
	 * @param data 绑定到点上的业务数据对象
	 * @param infoTemplate 弹出框模版
	 * 
	 * @returns graphic 当前点对象
	 */
	var createPoint = function(layerId, lonlat, image , data, infoTemplate){
		var map = this.arcgisMap;
		//判断传入的坐标点是否正确
		if(!$.util.exist(lonlat) || $.util.isBlank(lonlat.longitude) || $.util.isBlank(lonlat.latitude)){
			return null;
		}
		//判断该图层是否已存在，存在直接添加点，不存在创建一个新的图层添加点
		var layer = map.getLayer(layerId);
		if(!$.util.exist(layer)){
			layer = this.createLayer("graphics", layerId, infoTemplate);
		}
		//将经纬度转换为墨卡托，创建一个Point对象
		var mercator = lonlatToMercator(lonlat.longitude, lonlat.latitude);
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		//判断是否有传点样式对象，若没传，则默认创建一个
		if(!$.util.exist(image)){
			image = {
				url:context+"/images/map/map-mark1.png",
				width:60,
				height:60
			} ;
		}
		var symbol =  new esri.symbol.PictureMarkerSymbol(image.url, image.width, image.height);
		symbol.setOffset(+0, +(symbol.height/2));
		var graphic = new esri.Graphic(mapPoint, symbol);
		graphic.data = data ;
		layer.add(graphic) ;
		
		return graphic;
	}
	
	/**
	 * 创建线
	 * 
	 * @param layerId 点图层id
	 * @param lonlatArray 经纬度对象数组，例如：[{longitude:经度, latitude:纬度}]
	 * @param style 线样式，例如：{r:颜色值,g:颜色值,b:颜色值,width:线宽}
	 * @param data 绑定到线上的业务数据对象
	 * 
	 * @returns graphic 当前线对象
	 */
	var createLine = function(layerId, lonlatArray, style , data){
		var map = this.arcgisMap;
		
		if(!$.util.isArray(lonlatArray) || lonlatArray.length < 1){
			return null;
		}
		
		//判断该图层是否已存在，存在直接添加点，不存在创建一个新的图层添加点
		var layer = map.getLayer(layerId);
		if(!$.util.exist(layer)){
			layer = new esri.layers.GraphicsLayer({
		    	id: layerId
		    });
		    map.addLayer(layer);
		}
		
		var pathsArray = new Array();
		var pathArray = new Array();
		$.each(lonlatArray, function(l, lonlat){
			//判断传入的坐标点是否正确
			if(!$.util.exist(lonlat) || $.util.isBlank(lonlat.longitude) || $.util.isBlank(lonlat.latitude)){
				return true;
			}
			var mercator = lonlatToMercator(lonlat.longitude, lonlat.latitude);
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
		
		var r = 255; var g = 0; var b = 0;var width = 3;
		if($.util.exist(style)){
			r = style.r;
			g = style.g;
			b = style.b;
			width = style.width;
		}
		var lineSymbol = new esri.symbol.CartographicLineSymbol(
			esri.symbol.CartographicLineSymbol.STYLE_SOLID,
			new dojo.Color([r, g, b]), width,
			esri.symbol.CartographicLineSymbol.CAP_ROUND,
			esri.symbol.CartographicLineSymbol.JOIN_MITER, 10
		);
		var graphic = new esri.Graphic(line, lineSymbol);
		graphic.data = data ;
		layer.add(graphic) ;
		
		return graphic;
	}
	
	/**
	 * 根据图层id获取图层
	 * 
	 * @param layerId 图层id
	 */
	var getLayerById = function(layerId){
		var map = this.arcgisMap;
		var layer = map.getLayer(layerId);
		return layer;
	}
	
	/**
	 * 设置map点击事件回调
	 * 
	 * @param func 点击地图后回调函数，两个参数 
	 * 	lonlat 当前点击点的经纬度 {longitude:经度,latitude:纬度}
	 * 	e:事件对象
	 */
	var setClickCallback = function(func){
		var map = this.arcgisMap;
		map.on("click", function(e){
            $.util.log("墨卡托坐标系：(x)："+e.mapPoint.x+",(y)："+e.mapPoint.y);
            var lonlat = mercatorToLonlat(e.mapPoint.x, e.mapPoint.y);
            $.util.log("经纬度坐标系：经度(x)："+lonlat.longitude+",纬度(y)："+lonlat.latitude);
            
            if($.util.isFunction(func)){
            	func(lonlat, e);
            }
        });	
	}
	
	/**
	 * 设置map加载完成事件回调
	 * 
	 * @param func 地图加载完成后回调函数，一个参数 
	 * 	e:事件对象
	 */
	var setLoadCallback = function(func){
		var map = this.arcgisMap;
		map.on("load", function(e){
			if($.util.isFunction(func)){
	        	func(e);
	        }
		});
	}
	
	/**
	 * 根据坐标找地理位置
	 * 
	 * @param lonlat 经纬度 {longitude:经度,latitude:纬度}
	 * @param func 查询出地址后执行的函数，一个参数
	 * 	address 地址字符串
	 */
	var getAddressByLonlat = function (lonlat, func){
		var map = this.arcgisMap;
		
		//判断传入的坐标点是否正确
		if(!$.util.exist(lonlat) || $.util.isBlank(lonlat.longitude) || $.util.isBlank(lonlat.latitude)){
			return null;
		}
		var identify = new esri.tasks.IdentifyTask(base_map_server);
		var identifyParams = new esri.tasks.IdentifyParameters();
		//设置需要查询那些具体图层
		var layerIdArray = new Array();
		for(var i=0;i<59;i++){
			layerIdArray.push(i);
		}
		identifyParams.layerIds = layerIdArray;
		
		identifyParams.returnGeometry = true;
		identifyParams.mapExtent = map.extent;
		identifyParams.tolerance = 100;
		identifyParams.width = map.width;
		identifyParams.height = map.height;
		var mercator = lonlatToMercator(lonlat.longitude,lonlat.latitude);
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		identifyParams.geometry = new esri.geometry.Circle({
			center: mapPoint,
			geodesic: false,
			radius: 20 //搜索20米范围内的
		});
		//执行查询
		identify.execute(identifyParams, function(arg0){
			 var address = "";
			 if(arg0.length > 0 && $.util.exist(arg0[0].feature) && $.util.exist(arg0[0].feature.attributes)){
				 var attributeObj = arg0[0].feature.attributes;
				 address = ($.util.isBlank(attributeObj.Address)?"":attributeObj.Address) + ($.util.isBlank(attributeObj.Name)?"":attributeObj.Name);
			 }
			 
			 if($.util.isFunction(func)){
				 func(address);
			 }
		 });
	}
	
	/**
	 * 根据经纬度获取村居编码
	 * 
	 * @param lonlat 经纬度 {longitude:经度,latitude:纬度}
	 * @param func 查询出地址后执行的函数，一个参数
	 */
	var queryCommunityCodeByLonlat = function (lonlat, func){
		var map = this.arcgisMap;
		//查询村居图层
        var queryTask = new esri.tasks.QueryTask(community_layer_server+"/0");
    	var query = new esri.tasks.Query();
    	query.outFields = ["*"];
    	query.where = "1=1" ;
    	query.returnGeometry = true;
    	queryTask.execute(query, function(arg0){
    		var communityCode = null;
    		var mercator = lonlatToMercator(lonlat.longitude,lonlat.latitude);
    		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
    		$.each(arg0.features, function(i, val){
    			if(val.geometry.contains(mapPoint)){
    	    		communityCode = val.attributes.community_code;
    	    		return false;
    	    	}
    		});
    		
    		if($.util.isFunction(func)){
				 func(communityCode);
			}
    	});
	}
	
	/**
	 * 根据关键词搜索位置
	 * 
	 * @param keyword 关键字
	 * @param func 查询出位置后执行的函数，一个参数
	 * 	resultArray 位置结果集合[{name:名称,address:地址,geometry:坐标信息}]
	 */
	var queryLocationByKeyword = function (keyword, func){
		var map = this.arcgisMap;
		
		if($.util.isBlank(keyword)){
			return ;
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
		findParams.searchText = keyword;
		findParams.returnGeometry = true;
		findTask.execute(findParams, function(arg0){
			var resultArray = new Array();
			//循环添加基础数据搜索结果
			$.each(arg0,function(d,data){
				if(!$.util.exist(data.feature)){
					return true;
				}
				if(!$.util.exist(data.feature.attributes) || !$.util.exist(data.feature.geometry)){
					return true;
				}
				if($.util.isBlank(data.feature.geometry.x) || $.util.isBlank(data.feature.geometry.y)){
					return true;
				}
				var obj = new Object();
				obj.name = data.feature.attributes.Name;
				obj.address = data.feature.attributes.Address;
				obj.geometry = data.feature.geometry;
				resultArray.push(obj);
			});
			
			if($.util.isFunction(func)){
				func(resultArray);
			}
		});
	}
	
	/**
	 * 更新展示搜索结果
	 * 
	 * @param dataArray 搜索结果数组
	 * @param selecter input下拉展示input标签 id
	 * @param func 列表点击行选中回调，一个参数
	 * 	data 列表中的对象
	 */
	var showMenuContent = function (dataArray, selecter, func){
		//验证传入参数是否合格
		if(!$.util.isArray(dataArray)){
			return ;
		}
		if($.util.isBlank(selecter)){
			return ;
		}
		if(selecter.substring(0,1) != "#"){
			return ;
		}
		//判断页面是否已有下拉别表基础Html
		var width = 135;
		if(!$.util.isBlank($(selecter).width()) && $(selecter).width() > 0){
			width = $(selecter).width() - 10;
		}
		
		if($(selecter + "Mc").length < 1){
			var mcDiv = $("<div />",{
				"id" : selecter.substring(1,selecter.length) + "Mc",
				"class" : "ztree-MenuContent",
				"style" : "z-index:100",
			});
			$("<ul />",{
				"style" : "width:" + width + "px; height: 150px;overflow:auto"
			}).appendTo(mcDiv);
			$(selecter).parents("body").append(mcDiv);
		}else{
			$($(selecter + "Mc")[0]).find("ul").html("");
		}
		
		//向列表添加记录
		$.each(dataArray,function(i,val){
			var li = $("<li />",{
				"style":"width:" + width + "px;",
				"click" : function(){
					var obj = $(this).data("data");
					if($.util.isFunction(func)){
						var dataObj = new Object();
						dataObj.name = obj.name;
						dataObj.address = obj.address;
						var lonlat = mercatorToLonlat(obj.geometry.x, obj.geometry.y);
						dataObj.longitude = lonlat.longitude ;
						dataObj.latitude = lonlat.latitude ;
						func(dataObj);
					}
					hideMenu(selecter);
				}
			});
			var div = $("<div />",{
				"id":i + selecter,
				"class":"t-over",
				"style":"width:" + (width-25) + "px;cursor:pointer;margin-top:4px;",
				"tooltippos":"bottom",
				"mousetrack":"true",
				"my":"center bottom-20",
				"at":"center top",
				"title":val.name + val.address,
				"text":val.name + val.address
			}).appendTo(li);
			$(li).data("data",val);
			$($(selecter + "Mc")[0]).find("ul").append(li);
		});
		showMenu(selecter);
	}
	
	/**
	 * 显示发生地址搜索结果
	 */
	function showMenu(selecter) {
		var obj = $(selecter);
		var oOffset = $(selecter).offset();
		$(selecter + "Mc").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
		$("body").on("mousedown", function(event){
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == selecter.substring(1,selecter.length) + "Mc" || $(event.target).parents(selecter + "Mc").length>0)) {
				hideMenu(selecter);
			}
		});
	}
	/**
	 * 隐藏发生地址搜索结果
	 */
	function hideMenu(selecter) {
		$(selecter + "Mc").fadeOut("fast");
		$("body").off("mousedown");
	}
	
	/**
	 * map初始化之前执行
	 */
	function executedBeforeMapReadyExeList(){
		$.each(mapBeforeReadyExeList, function(i, val){
			val() ;
		});
	}
	
	/**
	 * map初始化之后执行
	 */
	function executedMapReadyExeList(){
		$.each(mapReadyExeList, function(i, val){
			val() ;
		});
	}
	
	/**
	 * 初始化地图
	 * @param mapIds 地图容器id数组，id不要#号，例如：["a","b"]
	 */
	function init(mapIds){
		if($.util.isArray(mapIds)){
			createMap(mapIds);
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.multiBaseMap, { 
		init : init ,
		getInstance:function(id){
			return mapObjs[id] ;
		},
		addToMapBeforeReadyExeList:function(func){
			mapBeforeReadyExeList.push(func) ;
		},
		addToMapReadyExeList:function(func){
			mapReadyExeList.push(func) ;
		}
	});	
})(jQuery);