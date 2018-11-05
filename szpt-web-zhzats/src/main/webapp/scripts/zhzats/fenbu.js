$.zhzats = $.zhzats || {} ;
$.zhzats.fenbu = $.zhzats.fenbu || {} ;

$.zhzats.fenbu.util = $.zhzats.fenbu.util || {} ;
(function($){
	
	"use strict";
	
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$(document).on("click" , "#fenbuSelect", function(){
			$.zhzats.fenbu.map.initOrRefresh() ;
		});
		$(document).on("click" , "#reset", function(){
			$.szpt.util.businessData.destroyTree("fenbuJqlx");
			$.szpt.util.businessData.initJqlxsTree("fenbuJqlx");
			$("#fxjc").val("派出所");
			$.zhzats.fenbu.map.initOrRefresh() ;
		});

    });
	//部署建议
	$(document).on("click" , ".bushu", function(e){
		var type = "zhzats";
		var typeSetContent = new Object();
		typeSetContent.relatedObjectId = "fenbu";
		typeSetContent.relatedObjectType = "135798642";
		typeSetContent.relateObjectContent = "fenbu";
		$.szpt.util.instruction.addInstruction(null, type, typeSetContent);
	});
	//查看警情数量详情
	$(document).on("click" , ".showJq", function(){
		var code = $(this).find("span").attr("unitCode");
		var lx = $(this).find("span").attr("jingqing");
		var data = $.zhzats.fenbu.map.dataBase();
		var temp = JSON.parse(data);
		var list = new Array();
		list[0] = code;
		temp['code'] = list;
		data = $.util.toJSONString(temp);
		window.top.$.layerAlert.dialog({
			content : context +  '/fenbu/showJqslDetail.action',
			pageLoading : true,
			title:"查看警情数量详情",
			width : "900px",
			height : "400px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				dataBase : data
			},
			end:function(){
				
			}
		});
	});
	//查看报备警力详情
	$(document).on("click" , ".showBb", function(){
		var code = $(this).find("span").attr("unitCode");
		var lx = $(this).find("span").attr("baobei");
		var data = $.zhzats.fenbu.map.dataBase();
		var temp = JSON.parse(data);
		var list = new Array();
		list[0] = code;
		temp['code'] = list;
		data = $.util.toJSONString(temp);
		window.top.$.layerAlert.dialog({
			content : context +  '/fenbu/showBbjlDetail.action',
			pageLoading : true,
			title:"查看报备警力详情",
			width : "500px",
			height : "500px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				bbjlData : data
			},
			end:function(){
				
			}
		});
		
	});
	//查看实际警力详情
	$(document).on("click" , ".showSj", function(){
		var code = $(this).find("span").attr("unitCode");
		var lx = $(this).find("span").attr("shiji");
		var data = $.zhzats.fenbu.map.dataBase();
		var temp = JSON.parse(data);
		var list = new Array();
		list[0] = code;
		temp['code'] = list;
		data = $.util.toJSONString(temp);
		window.top.$.layerAlert.dialog({
			content : context +  '/fenbu/showSjjlDetail.action',
			pageLoading : true,
			title:"查看实际警力详情",
			width : "500px",
			height : "500px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				sjjlData : data
			},
			end:function(){
				
			}
		});
	});
	
	function init() {
		$.szpt.util.businessData.initJqlxsTree("fenbuJqlx");
	}
	
	var pageInit = function init(){
		initWidget() ;
		refreshWithoutMap() ;
	}
	
	$.szpt.util.analyzeData.addToInitSuccessCallBack(pageInit) ;
	
	function initWidget(){
		//$.szpt.util.searchTime.initBtns() ;
		//$.szpt.util.analyzeData.initPcsSelect("#pcsSelect") ;
		initJqlxTree() ;
	}

	function initJqlxTree(){
		$.zTreeMenu.init("dimJqlxId", context + "/fenbu/queryJqlxTree.action", {
			async:{
				enable:true
			},
			check:{
		  		type:"onlySelectChildren"
			},
			callBacks:{
				formatNodeData:function(nodeData){
					nodeData.iconSkin = "dw" ;
				}
			}
		}) ;
	}
	
	function refreshAll(){
		if(!validateParam()){
			return ; 
		}
		refreshWithoutMap() ;
		refreshMap() ;
	}
	
//	function validateParam(){
//		var queryTime = $.szpt.util.searchTime.getCustomDates();
//		
//		if(!$.util.exist(queryTime.startTime) || !$.util.exist(queryTime.endTime)){
//			$.layerAlert.alert({msg:"时间不能为空！"}) ;
//			return false;
//		}
//	}

	function refreshWithoutMap(){

	}
	
	function refreshMap(){
		$.zhzats.fenbu.map.initOrRefresh() ;
	}

	jQuery.extend($.zhzats.fenbu.util, { 
		refreshAll:refreshAll,
		getCheckJqlxCodes:function(){
			var list = [] ;
			$.each($.zTreeMenu.getCheckedValue("dimJqlxId").data, function(i, val){
				list.push(val.diyMap.code) ;
			});
			return list ;
		}
	});
	
})(jQuery);	

$.zhzats.fenbu.map = $.zhzats.fenbu.map || {} ;
(function($){	
	"use strict";
	var tag = false;
	$(document).ready(function() {	
		$(document).on("click","#dateCustomized",function(){
			tag = true;
			$("#searchRange").show();
		});
		
		
		$(document).on("click",".search",function(){
			tag = false;
			$("#searchRange").hide();
			initOrRefresh()
		});
		
    });
	
	//得到自定义时间
	function getZdyTime(){
		var queryTime = {};
		
		var startTime = $.laydate.getTime("#searchRange","start");
		var endTime = $.laydate.getTime("#searchRange","end");
		
		queryTime["startTime"] = startTime;
		queryTime["endTime"] = endTime;
		
		queryTime["startTimeHB"] = startTime-(endTime-startTime);
		queryTime["endTimeHB"] = startTime;
				
		var startDate = new Date(startTime);
		var endDate = new Date(endTime); 

		queryTime["startTimeTB"] = startDate.setFullYear(startDate.getFullYear()-1)
		queryTime["endTimeTB"] = endDate.setFullYear(endDate.getFullYear()-1);
		return queryTime;
	}
	
	function getTime(){
		var queryTime = {};
		if(tag){
			queryTime = getZdyTime();
		}else{
			queryTime = $.szpt.util.searchTime.getDates($.szpt.util.searchTime.getTimeValueType());
		}
		return queryTime;
	}
	
	function checkTime(){
		var str = "";
		var searchTime = 1000*60*60*24*31*6;
		var data = new Date().getTime();
		var endTimeLong ;
		if(!$("#fixed_start").val()){
			str = str + "查询开始时间不能为空<br/>"
		}
		if($("#fixed_end").val()){
			endTimeLong = $.laydate.getTime("#searchRange","end");
			if(endTimeLong > data){
				str = str + "查询结束时间不能大于当下时间 <br/>"
			}
		}else{
			str = str + "查询结束时间不能为空 <br/>"
		}
		if($("#fixed_start").val() ){
			var startTimeLong;
			startTimeLong = $.laydate.getTime("#searchRange","start");
			if(startTimeLong > data){
				str = str + "查询开始时间不能大于当下时间 <br/>"
			}
//			if(endTimeLong - $.laydate.getTime("#searchRange","start") > searchTime){
//				str = str + "查询时间范围最大为半年"
//			}
		}
		return str;
	}
		
	function dataBase(){

		var queryTime = getTime();
		
		//TODO 临时改变开始时间用于测试
/*		var date = new Date() ;
		date.setFullYear(2015) ;
		queryTime.startTime = date.getTime() ;*/
		
		var selectJqlx = $.szpt.util.businessData.getSelectByTree("fenbuJqlx");
		var jqlxCodes = $.zhzats.fenbu.util.getCheckJqlxCodes();
		var list = [];
		if($.util.exist(selectJqlx)){
			list.push(selectJqlx)
			jqlxCodes = list;
		}
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getPcsCodes(),
			"jqlxCodes":jqlxCodes,
			"analyzeBase" : $('#analyzeBase option:selected').text()
		};
		
		$.util.mergeObject(data, queryTime) ;
		
		var dataStr = $.util.toJSONString(data) ;
		return dataStr;
	}
	
	
	var initOrRefresh = function(){
		
		if(tag){
			if("" != checkTime()){
				$.util.topWindow().$.layerAlert.alert({msg:checkTime(),title:"提示",icon:0,time:3000,});	
				return false;
			}
		}
		
		var dataStr = dataBase() ;
		
		$.ajax({
			url:context +'/fenbu/findFenbuPos.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				var data = successData.resultMap ;
				toView(data) ;
			}
		});
	}
	
	function toView(data){
		var fmtData = fmt_data(data) ;
		var jqType = toMapJqlx(data);
		toTableUl(fmtData.list) ;
		$.baseMap.refreshHeat(fmtData.shijiMap) ;
		//$.baseMap.refresh_Jq_Pos(fmtData.pos) ;
		$.baseMap.refresh_Jqlx_za(jqType.zaJq) ;
		$.baseMap.refresh_Jqlx_xs(jqType.xsJq) ;
		$.baseMap.refresh_Jqlx_qz(jqType.qzJq) ;
		$.baseMap.refresh_Jqlx_sw(jqType.swJq) ;
		$.baseMap.refresh_Jqlx_ts(jqType.tsJq) ;
		$.baseMap.refresh_Jqlx_sa(jqType.saJq) ;
		$.baseMap.refresh_Jqlx_jf(jqType.jfJq) ;
		$.baseMap.refresh_Jqlx_qt(jqType.qtJq) ;
	}
	
	function toMapJqlx(data){
		var list = data.list ;
		var zaJq = [] ;
		var xsJq = [] ;
		var qzJq = [] ;
		var swJq = [] ;
		var tsJq = [] ;
		var saJq = [] ;
		var jfJq = [] ;
		var qtJq = [] ;
		$.each(list, function(i, val){
			if(val.typeCode != null){
				var type = val.typeCode.substring(0,2) ;
				if(type == "02"){
					zaJq.push(val) ;
				}else if(type == "01"){
					xsJq.push(val) ;
				}else if(type == "05"){
					qzJq.push(val) ;
				}else if(type == "07"){
					swJq.push(val) ;
				}else if(type == "06"){
					tsJq.push(val) ;
				}else if(type == "04"){
					saJq.push(val) ;
				}else if(type == "08"){
					jfJq.push(val) ;
				}else{
					qtJq.push(val) ;
				}
			}
		})
		return{
			zaJq:zaJq,
			xsJq:xsJq,
			qzJq:qzJq,
			swJq:swJq,
			tsJq:tsJq,
			saJq:saJq,
			jfJq:jfJq,
			qtJq:qtJq
		}
	}
	
	function toTableUl(list){

		$("#litable").html("") ;
		$.each(list, function(i, val){
			var li = $(".tableLiTemplate").clone() ;
			li.removeClass("tableLiTemplate") ;
			$(".name", li).text(val.name) ;
			$(".jingqing", li).text(val.jingqing) ;
			$(".baobei", li).text(val.baobei) ;
			$(".shiji", li).text(val.shiji) ;
			
			var threshold = val.threshold ;
			if($.util.exist(threshold)){
				$(li).find("span[class='jingqing']").attr("unitCode",threshold.code).attr("jingqing","jingqing");
				$(li).find("span[class='baobei']").attr("unitCode",threshold.code).attr("baobei","baobei");
				$(li).find("span[class='shiji']").attr("unitCode",threshold.code).attr("shiji","shiji");
			}
			if(val.jingqing<=threshold.blueHoldValue){
				$(".jingqing-color", li).addClass("sq-blue") ;
			}
			
			if(val.jingqing>threshold.blueHoldValue && val.jingqing<=threshold.yellowHoldValue){
				$(".jingqing-color", li).addClass("sq-yellow") ;
			}
			
			if(val.jingqing>threshold.yellowHoldValue){
				$(".jingqing-color", li).addClass("sq-red") ;
			}
			
			$("#litable").append(li) ;
		});
	}
	
	function fmt_data(data){

		var list = data.list ;
		var baobeiList = data.baobeiList ;
		var shijiList = data.shijiList ;
		
		var rsList = [] ;
		$.each(list, function(i, val){

			var obj = {
				name:val.name,
				jingqing:1,
				baobei:0,
				shiji:0
			} ;
			
			var flag = false ;
			$.each(rsList, function(j, val1){
				if(val1.name == val.name){
					val1.jingqing++;
					flag = true ;
					return false ;
				}
			});
			if(!flag){
				rsList.push(obj) ;
			}
		});
		var queryTime = $.szpt.util.searchTime.getDates($.common.TIME_VALUE_TYPE_CONSTANT.DAY);
		//var queryTime = $.szpt.util.searchTime.getCustomDates();
	$.each(shijiList, function(j, vall){
		$.each(rsList, function(i, val){
			var threshold = $.szpt.util.analyzeData.get_pcs_fenbu_threshold(val.name, queryTime.startTime, queryTime.endTime) ;
			val.threshold = threshold ;
			if(vall.name == val.name){
				val.shiji++;
			}
			
			
		});
	});
	
	$.each(baobeiList, function(j, val1){
		$.each(rsList, function(i, val){
			var threshold = $.szpt.util.analyzeData.get_pcs_fenbu_threshold(val.name, queryTime.startTime, queryTime.endTime) ;
			val.threshold = threshold ;
			if(val1.name == val.name){
				val.baobei++;
			}
		});
	});
		return {
			shijiMap:shijiList,
			pos:list,
			list:rsList
		}
	}
	
	$.baseMap.addToMapReadyExeList(initOrRefresh) ;
	
	jQuery.extend($.zhzats.fenbu.map, { 
		initOrRefresh:initOrRefresh,
		dataBase:dataBase
	});

})(jQuery);	
