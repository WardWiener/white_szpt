(function($){
	"use strict";
	
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	
	var layer_placeInfoPoint ;// WIFI场所监控点图层
	var layer_personLocusPoint ;// 重点人轨迹点图层
	
	var alertTable = null; //重点人关注列表
	var alertBeanList = null; //预警信息集合
	
	$(document).ready(function() {	
		initPersonAttentionTable();
		//初始化轨迹地图
		initLocusMap();
		
		/**
		 * 操作按钮点击事件
		 */
		$(document).on("click",".status",function(){
			var alertId = $(this).attr("alertId");
			var status = $(this).attr("status");
			$.layerAlert.confirm({
	    		msg:"操作后不可恢复，您确定要操作？",
	    		title:"提示",
	    		yes:function(index, layero){
	    			$.ajax({
	    				url:context +'/personAttention/updateAlertStatus.action',
	    				type:'post',
	    				dataType:'json',
	    				data:{alertId : alertId,status : status},
	    				success:function(successData){
	    					alertTable.draw(true);
	    				},
	    				error:function(errorData){
	    					
	    				}
	    			});
	    		}
	    	});
			
			return false;
		});
		
		/**
		 * 列表行点击事件
		 */
		$(document).on("click",'#alertTable tr',function(){
			//改变行颜色
			$("#alertTable tr").each(function(i,val){
				$(val).css("background","#132643");
			});
			$(this).css("background","#203456");
			//定位场所在地图上的位置
			var row = alertTable.row(this) ;
			var full = row.data() ;
			if($.util.exist(full) && $.util.exist(locusMap)){
				var place = full.placeLocationList[0];
				if(place!=null){
					$.common.arcgisMapCommon.setMapCentreAtByLonlat(place.longitude,place.latitude, locusMap);
					//改变场所图标
					updatePlaceInfoPointImg(place.identifying);
				}
//				newPersonLocusPoint(full.personLocationList);
			}
		});
		
		/**
		 * 新增指令
		 */
		$(document).on("click",".newInstruct",function(){
			var row = alertTable.row($(this).parents("tr")) ;
			var full = row.data() ;
			var personArray = full.personLocationList;
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/personAttention/showNewInstructPage.action',
				pageLoading : true,
				title:"新增指令",
				width : "610px",
				height : "600px",
				btn:["下发指令","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.newInstruct ;
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
					personArray : personArray
				},
				end:function(){
					
				}
			});
		});
	});
	
	function initPersonAttentionTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/personAttention/findAllUntreatedAlert.action";
		tb.columnDefs = [
			{
				"targets": 0,
     	    	"width": "158px",
     	    	"title": "报警地点",
     	    	"className":"table-checkbox",
     	    	"data": "place" ,
     	    	"render": function ( data, type, full, meta ) {
     	    			  return data;
     	    	}
			},
			{
				"targets" : 1,
				"width" : "100px",
				"title" : "重点人",
				"data" : "personNames",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "158px",
				"title" : "关注内容",
				"data" : "warning",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "100px",
				"title" : "操作",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					var td = 
						'<button alertId="' + data + '" status="' + $.common.Constant.YJJLZT_HL() + '" class="btn btn-bordered btn-xs status">忽略</button>'+
						'<button alertId="' + data + '" status="' + $.common.Constant.YJJLZT_YCL() + '" class="btn btn-bordered btn-xs status">已处理</button>'
						+ '<button alertId="' + data + '" class="btn btn-bordered btn-xs newInstruct">新增指令</button>';
					return td;
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
           
		};
		tb.paramsResp = function(json) {
			alertBeanList = json.alertBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			json.data = alertBeanList;
			newPlaceInfoPoint(alertBeanList);
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		alertTable = $("#alertTable").DataTable(tb);
	}
	
	/**
	 * 初始化轨迹地图
	 */
	function initLocusMap(){
		var callback = function(arcgisEsri){
	        //初始化map
			locusMap = initBaseMap("personArrentionMapConten");
	        //map事件
	        locusMap.on("click", function(e){
	        	$.common.arcgisMapCommon.consoleLogCoordinate(e);
	        });
		    
	        locusMap.on("load", function(e){
		    	var mapPoint = arcgisEsri.Point(11876079.465427278, 3062348.6424484807, locusMap.spatialReference);
		    	locusMap.centerAt(mapPoint);
		    	
		    	if($.util.exist(alertBeanList)){
		    		newPlaceInfoPoint(alertBeanList);
		    	}
		    }); 
	        
	        //地图底图
	        locusBaseMapServiceLayer = initBaseMapLayer();
	        locusMap.addLayer(locusBaseMapServiceLayer);
	        
	        //WIFI场所监控点infoTemplate
		    var placeInfoPointTemplate = new arcgisEsri.InfoTemplate({
	        	title:"WIFI监测点详细信息",
	        	content:function(arg0){
		        	var alertBean = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:300px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(alertBean)){
		        		return baseDiv[0] ;
		        	}
		        	//场所名称
		        	if(!$.util.isBlank(alertBean.place)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"场所名称："+alertBean.place
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//重点人
		        	if(!$.util.isBlank(alertBean.personNames)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"重点人："+alertBean.personNames
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//关注类容
		        	if(!$.util.isBlank(alertBean.warning)){
		        		$("<div />", {
		        			"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"关注类容："+alertBean.warning
		        		}).appendTo(baseDiv_contentDiv);
		        	}
		        	//操作
		        	var butDiv = $("<div />", {
		        		"class":"row",
	        			"style":"margin-top:5px",
	        			"text":"操作："
		        	}).appendTo(baseDiv_contentDiv);
		        	$("<button />",{
		        		"alertId" : alertBean.id,
		        		"status" : $.common.Constant.YJJLZT_HL(),
		        		"class" : "btn  btn-xs status",
		        		"text" : "忽略"
		        	}).appendTo(butDiv);
		        	$("<button />",{
		        		"alertId" : alertBean.id,
		        		"status" : $.common.Constant.YJJLZT_YCL(),
		        		"class" : "btn  btn-xs status",
		        		"text" : "已处理"
		        	}).appendTo(butDiv);
		        	return baseDiv[0] ;
	        	}
		    }); 
	        
		    //WIFI场所监控点图层
	        layer_placeInfoPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_placeInfoPoint",
		    	infoTemplate:placeInfoPointTemplate
		    });
	        locusMap.addLayer(layer_placeInfoPoint);
		    
		    //重点人轨迹图层
	        layer_personLocusPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_personLocusPoint",
		    	infoTemplate:null
		    });
	        locusMap.addLayer(layer_personLocusPoint);
	        
		}
		
		$.common.arcgisMapCommon.newArcgisMap(callback);
	}
	
	/**
	 * 创建WIFI场所监控点
	 * @param alertBeanList 预警信息集合
	 */
	function newPlaceInfoPoint(beanList){
		if(!$.util.exist(alertBeanList) || !$.util.exist(layer_placeInfoPoint)){
			return ;
		}
		locusMap.infoWindow.hide();
		layer_personLocusPoint.clear();
		layer_placeInfoPoint.clear();
		$.each(beanList,function(i,val){
			$.each(val.placeLocationList,function(p,place){
				var longitude = place.longitude;
				var latitude = place.latitude;
				var layer = layer_placeInfoPoint;
				var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
				var data = val;
				$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
			});
		});
	}
	
	/**
	 * 创建人员轨迹点
	 * @param personLocationList 人员经纬度
	 */
	function newPersonLocusPoint(personLocationList){
		if(!$.util.exist(personLocationList) || !$.util.exist(layer_personLocusPoint)){
			return ;
		}
		layer_personLocusPoint.clear();
		$.each(personLocationList,function(p,personLocation){
			var longitude = personLocation.longitude;
			var latitude = personLocation.latitude;
			var layer = layer_personLocusPoint;
			var symbolObj = {url:context+"/images/map/map-icon-trail.png", width:22, height:35};
			var data = null;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
		});
	}
	
	/**
	 * 更新wifi监控点图标
	 * 
	 * @param placeId 场所标识
	 * @returns
	 */
	function updatePlaceInfoPointImg(placeId){
		var graphics = layer_placeInfoPoint.graphics;
		$.each(graphics,function(i,graphic){
			var identifying = graphic.data.placeLocationList[0].identifying;
			if(identifying == placeId){
				graphic.symbol.url = context+"/images/map/map-icon-trail.png";
				graphic.symbol.width = 22;
				graphic.symbol.height = 35;
				graphic.draw();
			}else{
				graphic.symbol.url = context+"/images/map/map-icon-wifi.png";
				graphic.symbol.width = 22;
				graphic.symbol.height = 35;
				graphic.draw();
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		
	});	
})(jQuery);