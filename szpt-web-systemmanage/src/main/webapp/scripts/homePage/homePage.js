(function($) {
	'use strict'
	
	/************全局变量**************/
	var date = new Date();
	var dailyReport ;	//各单位的报备
	
	/************全局变量**************/
	$(document).ready(function() {
		/**
		 * tabs控件
		 */
		$("#index-tabs").tabs();
		$("#index-sub1-tabs").tabs();
		$("#index-sub2-tabs").tabs();
		showUnsignInfoList();
		uploadHolidayTask();
		uploadPresentBeOnDuty();
		/**
		 * 分局登录进入 警情处置
		 * 三级单位登录进入 派警查询
		 */
		if(currentUnitName == "经开公安分局"){
			$("#userLevel").attr("href",context+"/dynInfoDisposal/disposeNewEventJsp.action");
		}else{
			$("#userLevel").attr("href",context+"/dynInfoDisposal/disposeIssuedRecordJsp.action?jspType=signTab&&clickOrder=0&&clickListOrder=1");
		}
		
		$(document).on("click",".createNewEvent",function(){
			location.href = context +"/dynInfoDisposal/disposeNewEventJsp.action";
		});
		
		$(document).on("click",".signIssuedRecord",function(){
			location.href = context +"/dynInfoDisposal/disposeIssuedRecordJsp.action";
		});
		
		$(document).on("click",".signReportRecord",function(){
			location.href = context +"/dynInfoDisposal/disposeReportRecordJsp.action";
		});
		
		/**
		 * 跳转接收节假日任务页面
		 */
		$(document).on("click",".info",function(){
			location.href = context + "/holidayTaskManage/showHolidayTaskManageJsp.action?indexSkip=indexSkip";
		});
		
		/**
		 * 点击待办事项页签
		 */
		$(document).on("click","#backlog",function(){
			showUnsignInfoList();
			uploadHolidayTask();
		});
		
		/**
		 * 点击当日事项页签
		 */
		$(document).on("click","#beOnDuty",function(){
			uploadPresentBeOnDuty();
		});
		
	});
	function showUnsignInfoList(){
		$("#unsignInfoList").empty();
		var str = "";
		$.ajax({
			url:context + '/dynInfoDisposal/unsignInfo.action',
			async:false,
			global:false,
			type:'post',
			dataType:'json',
			success:function(json){
				var infoLst = json.result;
				for(var i in infoLst){
					var content = "";
					if(infoLst[i].content!=null){
						content = infoLst[i].content
					}
					str += "<li class='" + infoLst[i].hasClass + "'><div class='state-icon'><span class='read-state unread' title='未读'></span></div>"
						+ "<div class='l'><h4><a href='#'>" + infoLst[i].title + "</a></h4><p>"
						+ infoLst[i].typeName + " <span class='fline'>|</span> " + infoLst[i].creatorUnitName + "</p>"
						+ "<p class='overflow-text'>" + content + "</p>"
						+ "</div><div class='r'><p class='time'>" + infoLst[i].occurTime.split("T")[1] + "</p><p class='date'>" + infoLst[i].occurTime.split("T")[0] + "</p><p class='state'>" + infoLst[i].sourceName + "</p></div></li>";
				}
				$("#unsignInfoList").append(str);
			}
		});
	
	
	
	}
	/**
	 * 打开地图页面
	 */
	$(document).on("click","#open",function(){
		window.open(context +"/mapCommand/showMapIndex.action");
	});
	/**
	 * 查看岗位人员
	 */
	$(document).on("click",".position",function(){
		$.layerAlert.dialog({
			content : context +  '/homePage/showPositionInfoJsp.action',
			pageLoading : true,
			title:"查看岗位人员",
			width : "30%",
			height : "300px",
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			dailyReport:dailyReport,
    			unitName:this.id,
    			positionName:this.innerText
    		},
    		end:{
    			
    		}
		});
	});
	

	/**
	 * 加载节假日任务管理
	 */
	function uploadHolidayTask() {
		var start = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" 00:00:00";
		var end = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" 23:59:59"
		$.ajax({url : context + '/holidayTaskManage/queryReceiveTask.action',
			async : true,
			global : false,
			type : 'post',
			dataType : 'json',
			data : {
				"taskName" : "",
				"deadLineTimeStart" : start,
				"deadLineTimeEnd" : end,
				"start" : 0,
				"length" : 1000000
			},
			success : function(json) {
				$("#report").empty();
				if (!$.util.isEmpty(json.receiverHolidayReportTaskBeanList)) {
					$.each(json.receiverHolidayReportTaskBeanList,function(i, val) {
						$("#report").append(
								"<li class='info'>"
									+ "<div class='state-icon'>"
									+ "<span class='read-state unread'></span>"
									+ "</div>"
									+ "<div class='l'>"
									+ "<h4><a href='#'>"
									+ val.name
									+ "</a></h4>"
									+ "<p class='overflow-text'>"
									+ "<div class='t-over' style='width:330px;' tooltipPos='bottom' mouseTrack='true'"
									+ " my='center bottom-20' at='center top' title='"+ val.content + "'>"+ val.content + " </div>"
									+ "</p>"
									+ "</div>"
									+ "<div class='r'><p class='time'>"
									+ val.sendTime.substring(11)
									+ "</p>"
									+ "<p class='date'>"
									+ val.sendTime.substring(0,10)
									+ "</p>"
									+ "</div>"
									+ "</li>");
					});
				} else {
					$("#report").append("<li><div align='center'><h3 algin='center'>今天没有任务</h3></div></li>");
				}
			}
		});

	}

	/**
	 * 加载当前值班
	 */
	function uploadPresentBeOnDuty() {
		$.ajax({
			url:context + "/dailyDutyStatistics/queryUnitTempletById.action",
			async:false,
			global:false,
			type:'post',
			dataType:'json',
			data:{
				"reportTime":date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate(),
			},
			success:function(json){
				$("#now").empty();
				dailyReport = json.dailyReportBeanList;
				if(!$.util.isEmpty(dailyReport)){
					var li="";
					for(var i=1;i<=dailyReport.length;i++){
						if(i%2 == 1 && i!=dailyReport.length){
							li+="<li>" +
							"<div class='l'>" +
							"<h4><a href='javascript:void(0)'>"+dailyReport[i-1].unitName+"</a></h4>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								if(val.name=="带班局领导"){
									$.each(val.daiPost,function(i, val2) {
										if(val2.principalFlag=="1"){
											li+="<p>"+val.name+":"+val2.name+"</p>";
										}
									});
								}
							});
							li+="<p>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								var br = "";
								if(val.name!="带班局领导"){
									if((y+1)%3==0){
										br="<br>"
									}
									li+=" <a href='javascript:void(0)' id='"+dailyReport[i-1].unitName+"' class='position'>"+val.name+"</a> "+ val.daiPost.length+" 人 <span class='sper2'> | </span>"+br;
								}
							});
							li+="</p></div>";
						}
						if(i%2 == 0){
							li+="<div class='l'>" +
							"<h4><a href='javascript:void(0)'>"+dailyReport[i-1].unitName+"</a></h4>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								if(val.name=="带班局领导"){
									$.each(val.daiPost,function(i, val2) {
										if(val2.principalFlag=="1"){
											li+="<p>"+val.name+":"+val2.name+"</p>";
										}
									});
								}
							});
							li+="<p>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								var br = "";
								if(val.name!="带班局领导"){
									if((y+1)%3==0){
										br="<br>"
									}
									li+=" <a href='javascript:void(0)' id='"+dailyReport[i-1].unitName+"' class='position'>"+val.name+"</a> " +val.daiPost.length+" 人 <span class='sper2'> | </span>"+br;
								}
							});
							li+="</p></div><div class='clear'>" +
							"</div>" +
							"</li>";
							$("#now").append(li);
							li="";
						}
						//就一条数据
						if(i==dailyReport.length && i%2 != 0){
							li+="<li><div class='l'>" +
							"<h4><a href='javascript:void(0)'>"+dailyReport[i-1].unitName+"</a></h4>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								if(val.name=="带班局领导"){
									$.each(val.daiPost,function(i, val2) {
										if(val2.principalFlag=="1"){
											li+="<p>"+val.name+":"+val2.name+"</p>";
										}
									});
								}
							});
							li+="<p>";
							$.each(dailyReport[i-1].positionBean,function(y, val) {
								var br = "";
								if(val.name!="带班局领导"){
									if((y+1)%3==0){
										br="<br>"
									}
									li+=" <a href='javascript:void(0)' id='"+dailyReport[i-1].unitName+"' class='position'>"+val.name+"</a> " +val.daiPost.length+" 人 <span class='sper2'> | </span>"+br;
								}
							});
							li+="</p></div><div class='clear'>" +
							"</div>" +
							"</li>";
							$("#now").append(li);
							li="";
						}
					}
				}else{
					$("#now").append("<li><div align='center'><h3 algin='center'>没有报备数据</h3></div></li>");
				}
			}
		});
	}

	})(jQuery)