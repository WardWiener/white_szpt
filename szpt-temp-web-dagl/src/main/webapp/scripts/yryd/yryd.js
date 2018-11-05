$.dagl = $.dagl || {} ;
$.dagl.yryd = $.dagl.yryd || {} ;
(function($){
	"use strict";
	
	$(document).ready(function(){
		
		$(document).on("click", "#searchBtn", function(){
			searchClick();
		});
		
		$(document).on("click", "#concernBtn", function(){
			concernBtnClick();
		});
		
	});
	
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

	function concernBtnClick(){
		$.ajax({
			url:context +'/yryd/createAttentionPerson.action',
			type:"post",
			data:{
				queryStr : $("#yrydIdNum").text()
			},
			dataType:"json",
			success:function(){
				$("#concernBtn").attr("visible", false);
				$("#cancelConcernBtn").attr("visible", true);
				window.top.$.layerAlert.alert({msg:"关注成功!",title:"提示"});
			}
		})
		
	}
	
	
	$(document).on("click", ".attention", function(e){
		
		var id = $(this).attr("caseCode");
		window.location.href = $.util.fmtUrl(context+"/yryd/showWdgzPage.action");
		
		
	});
	
	function searchClick(){
		$.ajax({
			url:context +'/yryd/findPersonInfo.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$("#concernBtn").attr("visible", true);
				viewPager(successDate.resultMap.result["photoLst"]);//图片轮播
				showBasicInfo(successDate.resultMap.result);//显示基本信息
				censusInfoTable(successDate.resultMap.result['censusInfoLst']);//户籍信息列表
				stayInfoLstTable(successDate.resultMap.result['stayInfoLst']);//暂住信息
				vehicleInfoLstTable(successDate.resultMap.result['vehicleInfoLst']);//车辆信息
				cameraInfoLstTable(successDate.resultMap.result['cameraInfoLst']);//卡口记录
				phoneInfoLstTable(successDate.resultMap.result['phoneInfoLst']);//手机信息记录
				trainGoOutInfoLstTable(successDate.resultMap.result['trainGoOutInfoLst']);//火车出行信息记录
				planeGoOutInfoLstTable(successDate.resultMap.result['planeGoOutInfoLst']);//飞机出行信息记录
				hotelInfoLstTable(successDate.resultMap.result['hotelInfoLst']);//旅馆住宿信息记录
				cybercafeInfoLstTable(successDate.resultMap.result['cybercafeInfoLst']);//网吧上网信息记录
				recordInfoLstTable(successDate.resultMap.result['recordInfoLst']);//前科信息记录
			}
		});
		
	}
	
	//图片轮播
	function viewPager(imgSrcs){
		if(!$.util.exist(imgSrcs)){
			return;
		}
		var imgSrcs = [] ;
		for(var i in imgSrcs){
			imgSrcs.push($("<img />", {
				"src":"data:image/png;base64," + imgSrcs[i]
			})) ;
		}
		var settings = {
				"id":"topthumbslider",
				"tabDoms":imgSrcs
		};
		$.thumbslider.initOrRefresh(settings) ;
	}

	function showBasicInfo(data){
		$("#yrydId").val(data["id"]);
		$.each($("#personalInfo .valCell"),function(){
			$(this).text(data[$(this).attr("valName")]);
		})
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
					"width" : "20%",
					"title" : "购票时间",
					"data" : "buyTicketTime",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '发车时间',
					"data" : "departTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : '车次',
					"data" : "trainNumber",
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
					"title" : '车厢号',
					"data" : "carriageNum",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 6,
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
					"width" : "20%",
					"title" : "航班日期",
					"data" : "stratTime",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="id" value="'+full.id+'">';
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
	
	//前科信息
	function recordInfoLstTable(recordInfoLst){
		for(var i in recordInfoLst){
			var objTableTemplate = $("#recordInfo");
			var objTable = objTableTemplate.clone(true);
			objTable.show();
			objTable.insertBefore(objTableTemplate);
			$.each(objTable.find(".valCell"),function(){
				$(this).text(recordInfoLst[i][$(this).attr("valName")]);
			})
		}
		
		
	}
	
	
})(jQuery);	