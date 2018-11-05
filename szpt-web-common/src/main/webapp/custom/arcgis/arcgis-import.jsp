<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.taiji.pubsec.szpt.util.ArcgisConstant,net.sf.json.JSONObject"%> 

<script>
var arcgis_server = '<%=ArcgisConstant.MAP_JS_SERVER%>' + '/arcgis_js_api/library/3.15/3.15/';
</script>

<link rel="stylesheet" type="text/css" href="http://<%=ArcgisConstant.MAP_JS_SERVER%>arcgis_js_api/library/3.15/3.15/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css" href="http://<%=ArcgisConstant.MAP_JS_SERVER%>arcgis_js_api/library/3.15/3.15/esri/css/esri.css" />
<script type="text/javascript" src="http://<%=ArcgisConstant.MAP_JS_SERVER%>arcgis_js_api/library/3.15/3.15/init.js"></script>

<script>
	var base_map_server = '<%=ArcgisConstant.BASE_MAP_SERVER%>' + 'arcgis/rest/services/GYMap2/MapServer' ;
	var layer_map_server = '<%=ArcgisConstant.BASE_MAP_SERVER%>' + 'arcgis/rest/services/EmergencyData/MapServer' ;
	var base_map_layer_type = '<%=ArcgisConstant.BASE_MAP_LAYER_TYPE%>' ;
	var base_map_layer_type_dynamic = '<%=ArcgisConstant.BASE_MAP_LAYER_TYPE_DYNAMIC%>' ;
	var base_map_layer_type_tiled = '<%=ArcgisConstant.BASE_MAP_LAYER_TYPE_TILED%>' ;
	
	var map_camera_url = '<%=ArcgisConstant.MAP_CAMERA%>' ;
	var map_carCamera_url = '<%=ArcgisConstant.MAP_CAR_CAMERA%>' ;
	
	//派出所网格透明度
	var arcgis_pcs_layer_opacity = 0.4 ;
	//主格、辅格透明度
	var arcgis_zgfg_layer_opacity = 0.25 ;
	//辅格边线透明度
	var arcgis_zgfg_line_layer_opacity = 0.2 ;
	//社区透明度
	var arcgis_community_layer_opacity = 0.25 ;
	
	var wangge_layer_server = '<%=ArcgisConstant.BASE_MAP_SERVER%>' + 'arcgis/rest/services/zhuge_MapService/MapServer' ;		
	
	var bianjie_layer_server = '<%=ArcgisConstant.BASE_MAP_SERVER%>' + 'arcgis/rest/services/policeStation_MapService/MapServer' ;	
	//社区
	var community_layer_server = '<%=ArcgisConstant.BASE_MAP_SERVER%>' + 'arcgis/rest/services/Community_MapService/MapServer' ;
	
	function arcgis_perpoint(x0, y0, x1, y1, posPercent){

		var x = (parseInt(posPercent, 10)/100)*(x1-x0) + x0 ;
		var y = (parseInt(posPercent, 10)/100)*(y1-y0) + y0 ;
		
		return [x, y] ;
	}
	
	function arcgis_getArrowCoordinates(x0, y0, x_end, y_end, alpha, l, posPercent) {
		if(!$.util.exist(posPercent)){
			posPercent = 100 ;
		}
		
		var pt = arcgis_perpoint(x0, y0, x_end, y_end, posPercent) ;
		
		var x1 = pt[0] ;
		var y1 = pt[1] ;
		
		var h = Math.sqrt((x1-x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
		
		var c = (x1 - x0) / h;
		var s = (y1 - y0) / h;
		
		var x_bis = x0 + h - l * Math.cos(Math.PI / 180 * alpha);
		var yb_bis = y0 + l * Math.sin(Math.PI / 180 * alpha);
		var yc_bis = y0 - l * Math.sin(Math.PI / 180 * alpha);
		
		var xb = c * x_bis - s * yb_bis + (1 - c) * x0 + s * y0;
		var yb = s * x_bis + c * yb_bis + (1 - c) * y0 - s * x0;
		var xc = c * x_bis - s * yc_bis + (1 - c) * x0 + s * y0;
		var yc = s * x_bis + c * yc_bis + (1 - c) * y0 - s * x0;
		
		return {
			"arrow" : [xb, yb, xc, yc],
			"start" : [x1, y1]
		}
	}

	function initBaseMap(domId, mapPopup){

		/* var mapPopup = new esri.dijit.Popup({
	        fillSymbol: new esri.symbol.SimpleFillSymbol("solid", null, new esri.Color("#A4CE67")),
	        titleInBody: false
	  	}, $("<div />")[0]); */
		
		if(base_map_layer_type==base_map_layer_type_dynamic){
			return new esri.Map(domId, {
		        logo : false,
  		        sliderOrientation : "horizontal",
  		        scale: 100500,
  		        infoWindow: mapPopup
  		    });
		}
		
		if(base_map_layer_type==base_map_layer_type_tiled){
			return new esri.Map(domId, { 	
		    	logo : false,
  		       	sliderOrientation : "horizontal",
  		        zoom: 5,
  		        infoWindow: mapPopup
  		    });
		}
	}
	
	function initBaseMapLayer(){
		if(base_map_layer_type==base_map_layer_type_dynamic){
			return new esri.layers.ArcGISDynamicMapServiceLayer(base_map_server, {}); 
		}
		
		if(base_map_layer_type==base_map_layer_type_tiled){
			return new esri.layers.ArcGISTiledMapServiceLayer(base_map_server, {}); 
		}
	}
	
	/**
	 * 墨卡托转经纬度
	 * @param x 经度
	 * @param y 纬度
	 * @returns {longitude:经度,latitude:纬度}
	 */
	function mercatorToLonlat(x,y){
		 var x = parseFloat(x) ;
		 var y = parseFloat(y) ;
		 var lonlat={longitude:0,latitude:0};
		 var longitude = x/20037508.34*180;
		 var latitude = y/20037508.34*180;
		 latitude= 180/Math.PI*(2*Math.atan(Math.exp(latitude*Math.PI/180))-Math.PI/2);
		 lonlat.longitude = longitude;
		 lonlat.latitude = latitude;
		 return lonlat;
	}

	/**
	 * 经纬度转墨卡托
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @returns {x:经度,y:纬度}
	 */
	function lonlatToMercator(longitude,latitude){
		 var longitude = parseFloat(longitude) ;
		 var latitude = parseFloat(latitude) ;
		 var mercator={x:0,y:0};
		 var x = longitude *20037508.34/180;
		 var y = Math.log(Math.tan((90+parseFloat(latitude))*Math.PI/360))/(Math.PI/180);
		 y = y *20037508.34/180;
		 mercator.x = x;
		 mercator.y = y;
		 return mercator ;
	}	
</script>

<!-- acrgis地图弹窗样式 -->
<style type="text/css">
	.esriPopup .sizer {
	    position: relative;
	    width: 338px;
	    z-index: 1;
	}
	.esriPopup .contentPane {
	    position: relative;
	    max-height: 500px;
	    overflow: auto;
	    padding: 0px;
	    background-color: #F7F7F7;
	    color: #333333;
	}
	.maximize{
		display:none;
	}
</style>
