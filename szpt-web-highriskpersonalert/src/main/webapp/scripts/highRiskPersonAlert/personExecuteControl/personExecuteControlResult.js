(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var code=initData.code;
	var table;
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	var tableLst;
	
	var layer_resultInfoPoint ;// WIFI场所监控点图层
	$(document).ready(function() {	
		if(!$.util.isBlank(id)){
			initLocusMap();
			initPageField();
			initTable();
		}
	});
	
	/**
	 * 生成布控快照
	 */
	$(document).on("click", "#executeComtrolSnapshot", function(){
		createExecuteComtrolSnapshotClick();
	})
	
	/**
	 * 查看具体某一项的详情页面
	 */
	$(document).on("click", ".detail", function(){
	   openDetail($(this).attr("detail"));
	})
	
	
	/**
	 * 忽略--点击事件
	 */
	$(document).on("click", ".ignore", function(){
	   saveIgnore($(this).attr("id"));
	})
	/**
	 * 确认--点击事件
	 */
	$(document).on("click", ".confirm", function(){
		saveConfirm($(this).attr("id"));
	})
	
	/**
	 * 确认--点击事件
	 */
	function saveConfirm(id){
		$.ajax({
			url:context +'/personExecuteControl/saveConfirm.action',
			type:'post',
			dataType:'json',
			data:{id : id},
			async:false,
			success:function(successData){
				var a=successData.isPass;
				if(a==true){
					$.layerAlert.alert({title:"提示",msg:"操作成功"});
					table.draw();
				}else{
					$.layerAlert.alert({title:"提示",msg:"操作成功"});
				}
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 忽略--点击事件
	 */
	function saveIgnore(id){
		$.ajax({
			url:context +'/personExecuteControl/saveIgnore.action',
			type:'post',
			dataType:'json',
			data:{id : id},
			async:false,
			success:function(successData){
				var a=successData.isPass;
				if(a==true){
					$.layerAlert.alert({title:"提示",msg:"操作成功"});
					table.draw();
				}else{
					$.layerAlert.alert({title:"提示",msg:"操作成功"});
				}
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 查看具体某一项的详情页面
	 */
	function openDetail(detail){
		//弹出框
		window.top.$.layerAlert.dialog({
				content : context +  '/personExecuteControl/showPersonExecuteControlDetail.action',
				pageLoading : true,
				title:"布控结果详情",
				width : "600px",
				height : "500px",
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
					detail : detail
				},
				end:function(){
					lockerTable.draw(true);
				}
			});

	}
	
	
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(id)){//修改
			$.ajax({
				url:context +'/personExecuteControl/findPersonExecuteControlById.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var personExecuteControlBean = successData.personExecuteControlBean;
					if(personExecuteControlBean!=null){
						$.validform.setFormTexts("#validform",personExecuteControlBean);
						$("#time").text($.date.timeToStr(personExecuteControlBean.startTime, "yyyy-MM-dd HH:mm")+'----'+
								$.date.timeToStr(personExecuteControlBean.endTime, "yyyy-MM-dd HH:mm"));
					}
				},
				error:function(errorData){
					
				}
			});
		}
	}
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/personExecuteControl/findPersonExecuteControlResult.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "5%",
         	    	"title": "序号",
         	    	"data": "id" ,
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
					"data" : "id",
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
							if(full.resultStatus!="1"){
								a+=' <a id="'+full.id+'" class="ignore" href="#" target="">忽略</a> ';
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
				d["id"] = code;
			};
			tb.paramsResp = function(json) {
	
				json.data = json.personExecuteControlResultBeanPager.pageList;
				tableLst = json.personExecuteControlResultBeanPager.pageList;
				json.recordsTotal = json.personExecuteControlResultBeanPager.totalNum;
				json.recordsFiltered = json.personExecuteControlResultBeanPager.totalNum;
				$("#resultNum").text(json.personExecuteControlResultBeanPager.totalNum);
				if(json.personExecuteControlResultBeanPager.totalNum==0){
					$("#locusMapConten").hide();
//					$("#table_wrapper").hide();
				}else{
					newPoint(json.personExecuteControlResultBeanPager.pageList);
				}
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	}
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
		    	title:"详细",
	        	content:function(arg0){
		        	var result = arg0.data ;
		        	//显示详细信息面板
		        	var baseDiv = $("<div />", {
		        		"class":"layer-police",
		        		"style":"width:300px; padding:10px;",
		        	});
		        	var baseDiv_contentDiv = $("<div />",{
		        		"class":"content"
		        	}).appendTo(baseDiv);
		        	
		        	if(!$.util.exist(result)){
		        		return baseDiv[0] ;
		        	}
		        	//捕捉对象
		        	if(!$.util.isBlank(result.catchObject)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"捕捉对象："+result.catchObject
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//捕捉时间
		        	if(!$.util.isBlank(result.catchTime)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"捕捉时间："+$.date.timeToStr(result.catchTime, "yyyy-MM-dd HH:mm")
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	//捕捉内容
		        	if(!$.util.isBlank(result.catchContent)){
		        		$("<div />", {
			        		"class":"row",
		        			"style":"margin-top:5px",
		        			"text":"捕捉内容："+result.catchContent
			        	}).appendTo(baseDiv_contentDiv);
		        	}
		        	return baseDiv[0] ;
	        	}
		    }); 
	        
	        layer_resultInfoPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_resultInfoPoint",
		    	infoTemplate:fiveColorPerson_locus_Template
		    });
	        locusMap.addLayer(layer_resultInfoPoint);
	        
		}
		
		$.common.arcgisMapCommon.newArcgisMap(callback);
		
	}
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
	//生成布控快照
	
	function createExecuteComtrolSnapshotClick(){
		var idCode = $("#idCardNo").text();
		var basicMessage = {
				"idCardNo":$("#idCardNo").text(),//身份证号
				"personName":$("#personName").text(),//姓名
				"sexName":$("#sexName").text(),//性别
				"phone":$("#phone").text(),//手机号
				"ethnicGroup":$("#ethnicGroup").text(),//民族
				"residenceAddress":$("#residenceAddress").text(),//户籍地
				"localAddressDetail":$("#localAddressDetail").text(),//现居地
				"time":$("#time").text(),//布控时间
				"note":$("#note").text(),//布控原因
		}
		var mapLst = tableLst;
		var data={
			"basicMessage":basicMessage,
			"tableLst":tableLst,
			"mapLst":mapLst
		}
		var str =  JSON.stringify(data);
		
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
				"idCode" : idCode,
			},
			end:function(){
				
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		createExecuteComtrolSnapshotClick:createExecuteComtrolSnapshotClick
	});	
})(jQuery);