$.dagl = $.dagl || {} ;
$.dagl.yryd = $.dagl.yryd || {} ;
$.dagl.yryd .yrydHighriskPersonAlert = $.dagl.yryd .yrydHighriskPersonAlert || {};
(function($){
	"use strict";
	
	var personTrackMapLayer = null;//轨迹地图图层
	var  personTrackLineLayer = null;//创建wifi轨迹线
	var yrydHighriskPersonDetailBackUrl = "";
	
	$(document).ready(function(){
		yrydHighriskPersonDetailBackUrl = sessionStorage.getItem("yrydHighriskPersonDetailBackUrl");
		init();
		$(document).on("click", "#concernBtn", function(){
			concernBtnClick();
		});
		
		//如果是情指中心则显示新建指令按钮
//		if(currentUnitCode == $.common.UNIT_CODE.UNIT_CODE_QZZX){
//			$("#addInstruct").show();
//		}else{
//			$("#addInstruct").hide();
//		}
	});
	
	//点击取消关注
	$(document).on("click", "#cancelConcernBtn", function(){
		cancelConcernBtnClick();
	});
	
	//点击返回
	$(document).on("click", "#backViewBtn", function(){
		window.top.location.href = yrydHighriskPersonDetailBackUrl;
	})
	
	//点击轨迹详情
	$(document).on("click", ".trackDetails", function(){
		$('#trackClick').trigger("click");
	})
	
	$(document).ready(function(){
		$(document).on("click", "#topthumbslider .thumbslider-next", function(){
			var obj = $.thumbslider.getSilder("topthumbslider") ;
			obj.next() ;
		});
		
		$(document).on("click", "#topthumbslider .thumbslider-prev", function(){
			var obj = $.thumbslider.getSilder("topthumbslider") ;
			obj.prev() ;
		});
		
	});

	//图片轮播
	function imgCarousel(){
		var data = {
				"idcard": idcard,	
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yryd/findHiPersonAvatarById.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data  = successDate.resultMap.result
				var imgSrcs = [] ;
				for(var i in data){
					imgSrcs.push($("<img />", {
						"src":'data:image/jpg;base64,'+ data[i]
					}))
				}
				var settings = {
						"id":"topthumbslider",
						"tabDoms":imgSrcs
				};
				$.thumbslider.initOrRefresh(settings) ;	
			}
		})
	}

	
	
	function concernBtnClick(){
		$.ajax({
			url:context +'/wdgz/followInterestedPerson.action',
			type:"post",
			data:{
				idCode : $("#yrydIdNum").text(),
				name:$("#name").text()
			},
			dataType:"json",
			success:function(){
				$("#concernBtn").hide();
				$("#cancelConcernBtn").show();
				window.top.$.layerAlert.alert({msg:"关注成功!",title:"提示",icon:1});
			}
		})
	}
	
	//取消关注
	function cancelConcernBtnClick(){
		$.ajax({
			url:context +'/wdgz/yrydDetailCancelInterestedPerson.action',
			type:"post",
			data:{
				idCode : $("#yrydIdNum").text(),
			},
			dataType:"json",
			success:function(){
				$("#concernBtn").show();
				$("#cancelConcernBtn").hide();
				window.top.$.layerAlert.alert({msg:"取消关注成功!",title:"提示",icon:1});
			}
		})
	}
	
	$(document).on("click", ".attention", function(e){
		
		var id = $(this).attr("caseCode");
		window.location.href = $.util.fmtUrl(context+"/yryd/showWdgzPage.action");
	});
	
	//点击新增指令
	$(document).on("click", "#addInstruct", function(e){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/newInstruct/showNewInstruct.action',
			pageLoading : true,
			title:"新增指令",
			width : "450px",
			height : "480px",
			btn:["新增","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.szpt.util.newnIstruction ;
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
    			idcard : idcard
    		},
    		end:function(){
    		}
		});
	});
	
	//初始化页面
	function init(){
		searchClick();
		initInstruct();
		imgCarousel();
		findCollectInfoSituationByIdcard();// 查询采集信息
	}
	
	function searchClick(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*31;
		var data = {
				"idcard": idcard,	
				"startTime":startTime,
				"endTime":endTime
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yryd/findOnePersonInfo.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$("#concernBtn").attr("visible", true);
				//viewPager(successDate.resultMap.result["photoLst"]);//图片轮播
				showBasicInfo(successDate.resultMap.result);//显示基本信息
//				censusInfoTable(successDate.resultMap.result['censusInfoLst']);//户籍信息列表
//				stayInfoLstTable(successDate.resultMap.result['stayInfoLst']);//暂住信息
//				vehicleInfoLstTable(successDate.resultMap.result['vehicleInfoLst']);//车辆信息
//				cameraInfoLstTable(successDate.resultMap.result['cameraInfoLst']);//卡口记录
//				phoneInfoLstTable(successDate.resultMap.result['phoneInfoLst']);//手机信息记录
				trainGoOutInfoLstTable(successDate.resultMap.train);//火车出行信息记录
				planeGoOutInfoLstTable(successDate.resultMap.plane);//飞机出行信息记录
				hotelInfoLstTable(successDate.resultMap.hotel);//旅馆住宿信息记录
				cybercafeInfoLstTable(successDate.resultMap.cybercafe);//网吧上网信息记录
				recordInfoLstTable(successDate.resultMap.recordInfo)//前科信息
			}
		});
	}
	
	/**
	 * 根据身份证查询信息采集信息
	 * @returns
	 */
	function findCollectInfoSituationByIdcard(){
		var data = {
			"idcard" : idcard
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yryd/findCollectInfoSituationByIdcard.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data = successDate.resultMap.result;
				if(!$.util.exist(data)){
					return ;
				}
				setPhoneInfo(data.phoneInfo);
				setQQInfo(data.qqNum);
				setWeiXinInfo(data.weixinNum);
				setCollectInfo(data.collectProject);
			}
		});
	}
	
	/**
	 * 设置手机信息
	 * @param phoneInfo 手机信息
	 * @returns
	 */
	function setPhoneInfo(phoneInfo){
		$("#phoneInfoOneDiv").html("");
		$("#phoneInfoMoreDiv").html("");
		if($.util.isBlank(phoneInfo)){
			return ;
		}
		var phoneInfoArray = [];
		if(phoneInfo.indexOf(";") > 0){
			phoneInfoArray = phoneInfo.split(";");
		}else{
			phoneInfoArray = phoneInfo.split("；");
		}
		$.each(phoneInfoArray,function(p,phone){
			
			var phoneArray = [];
			if(phoneInfo.indexOf(",") > 0){
				phoneArray = phone.split(",");
			}else{
				phoneArray = phone.split("，");
			}
			var phoneInfoTemplate = $("#phoneInfoTemplate").find("p");
			var pnoneInfoHtml = phoneInfoTemplate.clone(true);
			$.each(phoneArray,function(i,val){
				$(pnoneInfoHtml[i+1]).find("span").eq(1).text(val);
			});
			if(p == 0){
				$("#phoneInfoOneDiv").append(pnoneInfoHtml);
				$("#phoneInfoOneDiv").find("p").eq(0).remove();
			}else{
				$("#phoneInfoMoreDiv").append(pnoneInfoHtml);
			}
		});
	}
	
	/**
	 * 设置qq信息
	 * 
	 * @param qqInfo QQ信息
	 * @returns
	 */
	function setQQInfo(qqInfo){
		$("#qqInfoOneDiv").html("");
		$("#qqInfoMoreDiv").html("");
		if($.util.isBlank(qqInfo)){
			return ;
		}
		var qqInfoArray = qqInfo.split("，");
		$.each(qqInfoArray,function(q,qq){
			
			var qqInfoTemplate = $("#qqInfoTemplate").find("p");
			var qqInfoHtml = qqInfoTemplate.clone(true);
			$(qqInfoHtml).find("span").eq(1).text(qq);
			
			if(q < 3){
				$("#qqInfoOneDiv").append(qqInfoHtml);
			}else{
				$("#qqInfoMoreDiv").append(qqInfoHtml);
			}
		});
	}
	
	/**
	 * 设置微信信息
	 * 
	 * @param weiXinInfo 微信信息
	 * @returns
	 */
	function setWeiXinInfo(weiXinInfo){
		$("#weiXinInfoOneDiv").html("");
		$("#weiXinInfoMoreDiv").html("");
		if($.util.isBlank(weiXinInfo)){
			return ;
		}
		var weiXinInfoArray = weiXinInfo.split("，");
		$.each(weiXinInfoArray,function(w,weiXin){
			
			var weiXinInfoTemplate = $("#weiXinInfoTemplate").find("p");
			var weiXinInfoHtml = weiXinInfoTemplate.clone(true);
			$(weiXinInfoHtml).find("span").eq(1).text(weiXin);
			
			if(w < 3){
				$("#weiXinInfoOneDiv").append(weiXinInfoHtml);
			}else{
				$("#weiXinInfoMoreDiv").append(weiXinInfoHtml);
			}
		});
	}
	
	/**
	 * 设置采集信息
	 * 
	 * @returns
	 */
	function setCollectInfo(data){
		if($.util.isBlank(data)){
			return ;
		}
		if(data.indexOf("指纹") == -1){
			$("#zw").attr("class","icon-ban-circle color-red1 font18").attr("title","未采集");
		}else{
			$("#zw").attr("class","icon-ok-circle color-green1 font18").attr("title","已采集");
		}
		
		if(data.indexOf("尿液") == -1){
			$("#ny").attr("class","icon-ban-circle color-red1 font18").attr("title","未采集");
		}else{
			$("#ny").attr("class","icon-ok-circle color-green1 font18").attr("title","已采集");
		}
		
		if(data.indexOf("血液") == -1){
			$("#dna").attr("class","icon-ban-circle color-red1 font18").attr("title","未采集");
		}else{
			$("#dna").attr("class","icon-ok-circle color-green1 font18").attr("title","已采集");
		}
	}
	
	//加载指令反馈
	function initInstruct(){
		var data = {
				"idcard": idcard,	
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/newInstruct/findInstructionAndfeedbackBeanLst.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				//指令反馈信息记录
				var instructionLst = successDate.resultMap.result;
				instructionLst.sort(function(x1, x2){return x2.createTimeLong - x1.createTimeLong});
				instructInfoLstTable(instructionLst);
			}
		});
	}
	
	function initPersonTrankInfo(){
		
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*31;
		var data = {
				"idcard": idcard,	
				"startTime":startTime,
				"endTime":endTime
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yryd/findOnePersonTrankInfo.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				newPlaceInfoPoint(personTrankMapConten,successDate.resultMap.result)
			}
		});
	}

	
	function showBasicInfo(data){
		$.each($("#personalInfo .valCell"),function(){
			$(this).text(data[$(this).attr("valName")]);
		})
		if(data.sfbGz){
			$("#concernBtn").hide();
			$("#cancelConcernBtn").show();
		}else{
			$("#concernBtn").show();
			$("#cancelConcernBtn").hide();
		}

		if(data.sfsXyy == "1"){
			$("#personalInfo").find(".xyr").show();
		}
		var alertlevel = "";
		if(data.alertlevel == "0"){
			alertlevel += '<span class="btn warningType sq-red" title="红色"></span>';
		}else if(data.alertlevel == "1"){
			alertlevel += '<span class="btn warningType sq-orange" title="橙色"></span>';
		}else if(data.alertlevel == "2"){
			alertlevel += '<span class="btn warningType sq-yellow" title="黄色"></span>';
		}else if(data.alertlevel == "3"){
			alertlevel += '<span class="btn warningType sq-white" title="白色"></span>';
		}else if(data.alertlevel == "4"){
			alertlevel += '<span class="btn warningType sq-blue" title="蓝色"></span>';
		}else if(data.alertlevel == "5"){
			alertlevel += '<span class="btn warningType sq-other" title="其他"></span>';
		}
		$("#warning-type").html(alertlevel);
	}
	
	function censusInfoTable(tableInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = tableInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="field＿name" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '性别',
					"data" : "sex",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '身份证号',
					"data" : "idNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "20%",
					"title" : '与户主关系',
					"data" : "relationWithHouseholder",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"title" : '户口详址',
					"data" : "censusAddress",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#censusInfo").DataTable(tb);
	}
	
	function stayInfoLstTable(stayInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = stayInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "登记日期",
					"data" : "startDate",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '到期日期',
					"data" : "endDate",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '暂住事由',
					"data" : "reason",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "20%",
					"title" : '暂住场所',
					"data" : "place",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"title" : '区县',
					"data" : "district",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#stayInfo").DataTable(tb);
		
	}
	
	function vehicleInfoLstTable(vehicleInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = vehicleInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "13%",
					"title" : "号牌号码",
					"data" : "licenseNumber",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '号码种类',
					"data" : "licenseType",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '车辆类型',
					"data" : "vehicleClass",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : '品牌型号',
					"data" : "model",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "10%",
					"title" : '车身颜色',
					"data" : "color",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "15%",
					"title" : '发机动车状态',
					"data" : "engineStatus",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 6,
					"title" : '',
					"data" : "",
					"render" : function(data, type, full, meta) {
						return "";
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#vehicleInfo").DataTable(tb);
	}
	
	function cameraInfoLstTable(cameraInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = cameraInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "通过日期",
					"data" : "passDate",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '通过时间',
					"data" : "passTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '卡口名称',
					"data" : "cameraName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '',
					"title" : '卡口地址',
					"data" : "cameraAddress",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#cameraInfo").DataTable(tb);
		
	}
	
	//手机信息记录表
	function phoneInfoLstTable(phoneInfoLst){
		
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = phoneInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "号码",
					"data" : "phoneNum",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '运营商',
					"data" : "yunYingShang",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : 'IMEI号',
					"data" : "imelNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '',
					"title" : 'MAC地址',
					"data" : "macAddress",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#phoneInfo").DataTable(tb);
		
	}
	
	function trainGoOutInfoLstTable(trainGoOutInfoLst){
		
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = trainGoOutInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "25%",
					"title" : '发车时间',
					"data" : "departTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 1,
					"width" : "25%",
					"title" : '车次',
					"data" : "trainNumber",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : '25%',
					"title" : '始发地',
					"data" : "startPlace",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '25%',
					"title" : '目的地',
					"data" : "endPlace",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#trainGoOutInfo").DataTable(tb);
		
	}
	
	//飞机出行信息
	
	function planeGoOutInfoLstTable(planeGoOutInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = planeGoOutInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "10",
					"title" : "序号",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row;
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '航班号',
					"data" : "flyNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '登机时间',
					"data" : "goAboardTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '',
					"title" : '始发地',
					"data" : "startPlace",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : '',
					"title" : '目的地',
					"data" : "endPlace",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : '',
					"title" : '座位号',
					"data" : "seatNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#planeGoOutInfo").DataTable(tb);
		
	}
	
	//旅馆住宿信息
	function hotelInfoLstTable(hotelInfoLst){
		
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = hotelInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "宾馆名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '宾馆地址',
					"data" : "address",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '入住时间',
					"data" : "stratTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '20%',
					"title" : '退宿时间',
					"data" : "endTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : '20%',
					"title" : '房间号',
					"data" : "roomNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#hotelInfo").DataTable(tb);
	}
	
	//网吧表格
	function cybercafeInfoLstTable(cybercafeInfoLst){

		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = cybercafeInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "20%",
					"title" : "网吧名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '网吧地址',
					"data" : "address",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : "上网时间",
					"data" : "startTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : '20%',
					"title" : "下网时间",
					"data" : "endTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : '20%',
					"title" : "终端号",
					"data" : "terminalNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
				
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#cybercafeInfo").DataTable(tb);		
	}
	
	//指令反馈信息
	function instructInfoLstTable(instructInfoLst){
		if(instructInfoLst.length > 0){
			var oddTableTemplate = $("#instructUl #odd");
			var evenTableTemplate = $("#instructUl #even");
			for(var i in instructInfoLst ){
				if(instructInfoLst[i].isFeedBack){
					var objTable = oddTableTemplate.clone(true);
					objTable.show();
					objTable.insertBefore($("#instructClear"));
					$.each(objTable.find(".valCell"),function(){
						$(this).text(instructInfoLst[i][$(this).attr("valName")]);
					})
				}else{
					var objTable = evenTableTemplate.clone(true);
					objTable.show();
					objTable.insertBefore($("#instructClear"));
					$.each(objTable.find(".valCell"),function(){
						$(this).text(instructInfoLst[i][$(this).attr("valName")]);
					})
				}
			}		
		}else{
			$("#zlfkDiv").removeAttr("style");
			$("#zlfkDiv").html('<div  align="center">无数据</div>');
		}
	}
	
	
	//前科信息
	function recordInfoLstTable(recordInfoLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = recordInfoLst;
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "25%",
				"title" : "案件编号",
				"data" : "caseCode",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 1,
				"width" : "25%",
				"title" : '案件名称',
				"data" : "caseName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "25%",
				"title" : "案件类别",
				"data" : "caseTypeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : '25%',
				"title" : "发案时间",
				"data" : "caseTimeStart",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		tb.ordering = false;
		tb.paging = true; // 是否分页
		tb.info = true; // 是否显示表格左下角分页信息
		tb.autoFoot = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.lengthMenu = [ 3 ];
		$("#qkxxTable").DataTable(tb);	
	}
	
	function createTrackAnalyzeLstTable(snapshotTableId,snapshotLst){
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = snapshotLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "10%",
					"title" : "序号",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				}, {
					"targets" : 1,
					"width" : "25%",
					"title" : '分析人',
					"data" : "createPerson",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}, {
					"targets" : 2,
					"width" : "25%",
					"title" : '快照生成时间',
					"data" : "createdDate",
					"render" : function(data, type, full, meta) {
						return data.replace("T"," ");
					}
				}, {
					"targets" : 3,
					"width" : "40%",
					"title" : '保存快照原因',
					"data" : "intro",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		tb.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		return $("#"+snapshotTableId+"").DataTable(tb);
	}
	
	//创建地图
	function newPlaceInfoPoint(map,wifiInfoBeanList){
		
		if(!$.util.exist(wifiInfoBeanList)){
			return;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("人员轨迹点详细信息", placeInfoPointLayerContent);
		if($.util.exist(personTrackMapLayer)){
			personTrackMapLayer.clear();
		}
		var trackLineLst = [];
		$.each(wifiInfoBeanList,function(p,trackInfo){  //每个点的定义
			if(trackInfo.longitude && trackInfo.latitude){
				var lonlat = {
						longitude : trackInfo.longitude ,
						latitude : trackInfo.latitude
					}
					var graphic = map.createPoint("personTrackMapLayer", lonlat, null, trackInfo, placeInfoPointTemplate);
					if(!$.util.exist(personTrackMapLayer) && $.util.exist(graphic)){
						personTrackMapLayer = graphic.getLayer();
					}
				trackLineLst.push(lonlat);
			}
		});
		//* @param style 线样式，例如：{r:颜色值,g:颜色值,b:颜色值,width:线宽}
		var style = {r:255,g:0,b:0,width:3}
		map.createLine("personTrackLineLayer", trackLineLst, style , null);//创建线
	}

	var placeInfoPointLayerContent = function(graphic){
    	var wifiFenceHitBean = graphic.data ;
		//显示详细信息面板
    	var baseDiv = $("<div />", {
    		"class":"layer-police",
    		"style":"width:300px; padding:10px;",
    	});
    	var baseDiv_contentDiv = $("<div />",{
    		"class":"content"
    	}).appendTo(baseDiv);
    	
    	if(!$.util.exist(wifiFenceHitBean)){
    		return baseDiv[0] ;
    	}
    	//场所名称
    	if(!$.util.isBlank(wifiFenceHitBean.placeName)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所名称："+wifiFenceHitBean.placeName
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//进入时间
    	if(!$.util.isBlank(wifiFenceHitBean.enterTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"进入时间："+$.date.timeToStr(wifiFenceHitBean.enterTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
	    //离开时间
    	if(!$.util.isBlank(wifiFenceHitBean.leaveTime)){
    		$("<div />", {
        		"class":"row",
        		"style":"margin-top:5px",
    			"text":"离开时间："+$.date.timeToStr(wifiFenceHitBean.leaveTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
		return baseDiv[0];
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.dagl.yryd .yrydHighriskPersonAlert, { 
		createTrackAnalyzeLstTable:createTrackAnalyzeLstTable,
		initPersonTrankInfo : initPersonTrankInfo
	});
	
})(jQuery);	