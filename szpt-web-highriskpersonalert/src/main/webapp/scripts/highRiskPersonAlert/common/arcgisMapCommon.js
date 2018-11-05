(function($){
	"use strict";
	$(document).ready(function() {});
	
	/**
	 * 墨卡托转经纬度
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @returns {x:经度,y:纬度}
	 */
	function mercatorToLonlat(longitude,latitude){
		 var lonlat={x:0,y:0};
		 var x = longitude/20037508.34*180;
		 var y = latitude/20037508.34*180;
		 y= 180/Math.PI*(2*Math.atan(Math.exp(y*Math.PI/180))-Math.PI/2);
		 lonlat.x = x;
		 lonlat.y = y;
		 return lonlat;
	}

	/**
	 * 经纬度转墨卡托
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @returns {x:经度,y:纬度}
	 */
	function lonlatToMercator(longitude,latitude){
		 var mercator={x:0,y:0};
		 var x = longitude *20037508.34/180;
		 var y = Math.log(Math.tan((90+parseFloat(latitude))*Math.PI/360))/(Math.PI/180);
		 y = y *20037508.34/180;
		 mercator.x = x;
		 mercator.y = y;
		 return mercator ;
	}
	
	/**
	 * 地图上新创建点（根据经纬度坐标创建）
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param layer 图层对象
	 * @param map 地图实例对象
	 * @param symbolObj 图标基本信息对象{url:图片路径,width:图片宽度,height:图片高度}
	 * @param data 业务对象
	 */
	function newPointByLonlat(longitude, latitude, layer, map, symbolObj, data){
		if($.util.isBlank(longitude) || $.util.isBlank(latitude)){
			return ;
		}
		var mercator = lonlatToMercator(longitude, latitude);//经度坐标转墨卡托
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		var symbol = null;
		if($.util.exist(symbolObj)){
			symbol = new esri.symbol.PictureMarkerSymbol(symbolObj.url, symbolObj.width, symbolObj.height);
		}
		var graphic = new esri.Graphic(mapPoint, symbol);
		graphic["data"] = data ;
		layer.add(graphic);
	}
	
	/**
	 * 以某点为中心将地图居中到窗口
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param map 地图实例对象
	 */
	function setMapCentreAtByLonlat(longitude, latitude, map){
		if($.util.isBlank(longitude) || $.util.isBlank(latitude)){
			return ;
		}
		var mercator = lonlatToMercator(longitude, latitude);
		var mapPoint = esri.geometry.Point(mercator.x, mercator.y, map.spatialReference);
		map.centerAt(mapPoint);
	}
	
	/**
	 * 浏览器控制台输出鼠标点击的坐标
	 * @param e 鼠标点击事件对象
	 */
	function consoleLogCoordinate(e){
        var lonlat = mercatorToLonlat(e.mapPoint.x, e.mapPoint.y);
        $.util.log("经纬度坐标系：经度(x)："+lonlat.x+",纬度(y)："+lonlat.y + "；墨卡托坐标系：(x)："+e.mapPoint.x+",(y)："+e.mapPoint.y);
	}
	function drawLine(layer, map, trajectoryList,mac){
		layer.clear();
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
			new dojo.Color("#FF0000"), 3,
			esri.symbol.CartographicLineSymbol.CAP_ROUND,
			esri.symbol.CartographicLineSymbol.JOIN_MITER, 10
		);
		var graphic = new esri.Graphic(line, lineSymbol);
		graphic["mac"] = mac;
		layer.add(graphic);
		layer.show();
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

	/**
	 * 新建arcgis地图
	 * @param callback 回调方法，用作map初始化
	 */
	function newArcgisMap(callback){
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
   	        "dojo/domReady!"], function (	      
   	    	Map,	  
   	        ArcGISDynamicMapServiceLayer, 
   	        ArcGISTiledMapServiceLayer,
   	        Search,
   	        FeatureLayer,
   	        InfoTemplate,
   		    Scalebar,
   		    FindTask,
   		    FindParameters,
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
			//执行回调
			var arcgisEsri = new Object();
			arcgisEsri.Map = Map ;
			arcgisEsri.ArcGISDynamicMapServiceLayer = ArcGISDynamicMapServiceLayer ;
			arcgisEsri.ArcGISTiledMapServiceLayer = ArcGISTiledMapServiceLayer ;
			arcgisEsri.Search = Search ;
			arcgisEsri.FeatureLayer = FeatureLayer ;
			arcgisEsri.InfoTemplate = InfoTemplate ;
			arcgisEsri.Scalebar = Scalebar ;
			arcgisEsri.FindTask = FindTask ;
			arcgisEsri.FindParameters = FindParameters ;
			arcgisEsri.dom = dom ;
			arcgisEsri.Query = Query ;
			arcgisEsri.QueryTask = QueryTask ;
			arcgisEsri.PictureMarkerSymbol = PictureMarkerSymbol ;
			arcgisEsri.Graphic = Graphic ;
			arcgisEsri.Point = Point ;
			arcgisEsri.esriBasemaps = esriBasemaps ;
			arcgisEsri.SpatialReference = SpatialReference ;
			arcgisEsri.SimpleRenderer = SimpleRenderer ;
			arcgisEsri.SimpleMarkerSymbol = SimpleMarkerSymbol ;
			arcgisEsri.SimpleLineSymbol = SimpleLineSymbol ;
			arcgisEsri.Color = Color ;
			arcgisEsri.Font = Font ;
			arcgisEsri.Draw = Draw ;
			arcgisEsri.SimpleFillSymbol = SimpleFillSymbol ;
			arcgisEsri.parser = parser ;
			arcgisEsri.registry = registry ;
			arcgisEsri.Polygon = Polygon ;
			arcgisEsri.GraphicsLayer = GraphicsLayer ;
			arcgisEsri.Circle = Circle ;
			arcgisEsri.Popup = Popup ;
			arcgisEsri.PopupTemplate = PopupTemplate ;
			arcgisEsri.domClass = domClass ;
			arcgisEsri.domConstruct = domConstruct ;
			arcgisEsri.TextSymbol = TextSymbol ;
			arcgisEsri.HeatmapRenderer = HeatmapRenderer ;
			arcgisEsri.Polyline = Polyline ;
			arcgisEsri.CartographicLineSymbol = CartographicLineSymbol ;
			arcgisEsri.ClusterLayer = ClusterLayer ;
			arcgisEsri.arrayUtils = arrayUtils ;
			arcgisEsri.webMercatorUtils = webMercatorUtils ;
			arcgisEsri.ClassBreaksRenderer = ClassBreaksRenderer ;
			arcgisEsri.geometryEngine = geometryEngine ;
			callback(arcgisEsri);
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		arcgisMapCommon : {
			newArcgisMap : newArcgisMap ,
			setMapCentreAtByLonlat : setMapCentreAtByLonlat ,
			consoleLogCoordinate : consoleLogCoordinate ,
			mercatorToLonlat : mercatorToLonlat ,
			lonlatToMercator : lonlatToMercator ,
			newPointByLonlat : newPointByLonlat,
			drawLine:drawLine
		}
	});	
})(jQuery);