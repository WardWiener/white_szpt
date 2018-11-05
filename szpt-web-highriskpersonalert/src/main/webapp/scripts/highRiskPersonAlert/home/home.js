$.highriskpersonalert = $.highriskpersonalert || {} ;
$.highriskpersonalert.home = $.highriskpersonalert.home || {};

$.highriskpersonalert.home.analyze = $.highriskpersonalert.home.analyze || {} ;

var startTime;
var endTime;

(function($){
	
	"use strict";
	
	var table2;
	var table3;
	$(document).ready(function() {	
		$("#recentlyDay").click();
		endTime = new Date().getTime();
		startTime = endTime - 1000*60*60*24;
		addSelectedStyle("recentlyDay");
		findWarnType();
		findPeopleType();
		initTable1();
		initTable2();
		initTable3();
		
		$.szpt.util.businessData.addToInitSuccessCallBack(refreshWithoutMap);
		
		$(document).on("click",".selectTime",function(){
			addSelectedStyle(this.id);
			var time;
			if(this.id=="recentlyWeek"){
				time = getRecentlyWeek();
				$(".searchRange").hide();
			}else if(this.id=="recentlyMonth"){
				time = getRecentlyMonth();
				$(".searchRange").hide();
			}else if(this.id=="recentlyDay"){
				time = getRecentlyDay();
				$(".searchRange").hide();
			}else if(this.id == "recentlyDiy"){
				$.laydate.reset("#searchRange");
				$(".searchRange").show();
				return ;
			}
			timeFind(time);
			
		});
		
		$(document).on("click",".accumulatePointsClass",function(){//点击进入人员积分详情页面
		   showAccumulatePointsDetails($(this).attr("id")); //积分详情弹窗页面
		});
		
		/**
		 * 统计点击事件
		 */
		$(document).on("click",'#statistics',function(){
			var nowTime = new Date().getTime();
			var time = getRecentlyDiy();
			if($.util.isBlank(time.startTime)){
				$.layerAlert.alert({icon:0, msg:"请选择开始时间！"});
				return ;
			}else if($.util.isBlank(time.endTime)){
				$.layerAlert.alert({icon:0, msg:"请选择结束时间！"});
				return ;
			}
			if(!$.util.isBlank(time.endTime) && time.endTime > nowTime){
				$.layerAlert.alert({icon:0, msg:"结束时间不可大于当前时间！"});
				return ;
			}
			timeFind(time);
		});
		
		/**
		 * 重置按钮点击事件
		 */
		$(document).on('click','#reset',function(){
			$.laydate.reset("#searchRange");
			endTime = new Date().getTime();
			startTime = endTime - 1000*60*60*24;
		});
	});
	
	/**
	 * 时间查询
	 * 
	 * @param time 时间
	 * @returns
	 */
	function timeFind(time){
		startTime = time.startTime;
		endTime = time.endTime;
		
		initTable1();
		table2.draw();
		$.highriskpersonalert.home.peopleShiduan.initOrRefresh();
		$.highriskpersonalert.home.peopleChusuo.initOrRefresh();
	}
	
	var refreshWithoutMap = function (){
		$.highriskpersonalert.home.peopleShiduan.initOrRefresh();
		$.highriskpersonalert.home.peopleChusuo.initOrRefresh();
	}
	
	/**
	 * 人员积分详情弹窗
	 */
	function showAccumulatePointsDetails(data){
			window.top.$.layerAlert.dialog({
				content : context +  '/highriskPerson/showAccumulatePointsDetails.action',
				pageLoading : true,
				title:"积分详情",
				width : "70%",
				height : "80%",
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					lockerId : data
				},
				end:function(){
					initTable3.draw(true);
				}
			});
	}
	
	function addSelectedStyle(id){
		for(var i=0;i<$(".selectTime").length;i++){
			var item = $(".selectTime")[i];
			if(item.id==id){
				$(item).css("background-color","#337ab7");
				$(item).css("border-color","#2e6da4");
			}else{
				$(item).css("background-color","#1d98de");
				$(item).css("border-color","#1d98de");
			}
		}
	}
	/**
	 * 初始化人员调整记录表格
	 */
	function initTable1(){
		$.ajax({
			url:context + '/highriskPerson/findPersonChangeRecord.action',
			type:'post',
			adtaType:'json',
			data:{startTime:startTime,endTime:endTime},
			success:function(data){
				$("#newAddHighriskPerson").text(data.newAddHighriskPerson==null?"":data.newAddHighriskPerson)
				$("#warnTypeChange").text(data.warnTypeStatistic==null?"":data.warnTypeStatistic);
				$("#peopleTypeChange").text(data.peopleTypeStatistic==null?"":data.peopleTypeStatistic);
			}
		});
	}
	function initTable2(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/highriskPerson/findPersonCheckStatistics.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "15%",
				"title" : "单位",
				"data" : "checkUnitName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 1,
				"width" : "15%",
				"title" : "核查人数",
				"data" : "sum",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "15%",
				"title" : "橙色",
				"data" : "orange",
				"render" : function(data, type, full, meta) {
					return '<span class="color-orange1">' + data + '</span>';
				}
			},
			{
				"targets" : 3,
				"width" : "15%",
				"title" : "白色",
				"data" : "white",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "15%",
				"title" : "刑事前科",
				"data" : "criminalRecord",
				"render" : function(data, type, full, meta) {
					return '<span class="color-blue1">' + data + '</span>';
				}
			},
			{
				"targets" : 5,
				"width" : "15%",
				"title" : "涉毒人员",
				"data" : "drugRelatedPerson",
				"render" : function(data, type, full, meta) {
					return '<span class="color-blue1">' + data + '</span>';
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.paging = false;
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
            d["startTime"] = startTime;
            d["endTime"] = endTime;
		};
		tb.paramsResp = function(json) {
			var personCheckStatisticsInfoBeanList = json.personCheckStatisticsInfoBeanList;
			json.recordsTotal = json.personCheckStatisticsInfoBeanList.length;
			json.recordsFiltered = json.personCheckStatisticsInfoBeanList.length;
			json.data = personCheckStatisticsInfoBeanList;
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		table2 = $("#table2").DataTable(tb);
	}
	/**
	 * 积分预警人员
	 */
	function initTable3(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/highriskPerson/findHPersonScoreByPage.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"title" : "姓名",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 1,
				"title" : "身份证号",
				"data" : "idcode",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"title" : "最新积分值",
				"data" : "accumulatePoints",
				"render" : function(data, type, full, meta) {
					return '<a href="javascript:void(0)" id="'+full.id+'" class="color-yellow1 accumulatePointsClass">'+data+'</a>';
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
		//是否分页
		tb.paging=true;
		//请求参数		
		tb.paramsReq = function(d, pagerReq){
			var data = {
					"start":d.start,
					"length":d.length
				};
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			var personCheckStatisticsInfoBeanList = json.personCheckStatisticsInfoBeanList;
			json.recordsTotal = json.resultMap.personBean.length;
			json.recordsFiltered = json.resultMap.personBean.length;
			json.data = json.resultMap.personBean;
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		table3 = $("#table3").DataTable(tb);
	}
	
	function findWarnType(){
		$.ajax({
			url:context +'/highriskPerson/sumupByWarnType.action',
			type:'post',
			dataType:'json',
			ata:{startTime:startTime,endTime:endTime},
			success:function(successData){
				var list=[];
				$.each(successData.statisticsInfoList,function(i,val){
					list.push(parseInt(val.value));
				});
				initWarnType(list);
				initWarnType2(list);
			},
			error:function(errorData){
				
			}
		});
	}
	function findPeopleType(){
		$.ajax({
			url:context +'/highriskPerson/sumupByPepleType.action',
			type:'post',
			dataType:'json',
			success:function(successData){
				var namelist=[];
				var numlist=[];
				$.each(successData.statisticsInfoList,function(i,val){
					namelist.push(val.name);
					numlist.push(parseInt(val.value));
				});
				initPeopleType(namelist,numlist);
			},
			error:function(errorData){
			}
		});
	}

	function initWarnType(list){
		 $('#highchart-container1').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		        },
		        title: {
		            text: '预警人数占比'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '占比',
		            data: [
		                ['红色',   list[0]!=null?list[0]:0],
						['橙色',   list[1]!=null?list[1]:0],
		                ['黄色',   list[2]!=null?list[2]:0],
		                ['白色',   list[3]!=null?list[3]:0],
		                ['蓝色',   list[4]!=null?list[4]:0],
		                ['其他',   list[5]!=null?list[5]:0],
		            ]
		        }]
		    });
	}
	function initWarnType2(list){
		Highcharts.theme = {
				colors: [ "#0fb6e6", "#ff0022", "#00cb5b", "#fff",
					"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
					
				}
		 var colors = Highcharts.getOptions().colors;
		$('#highchart-container2').highcharts({
			chart: {                                                          
	        },                                                                
	        title: {                                                          
	            text: '预警人数'                                     
	        },                                                                
	        xAxis: {                                                          
	            categories: ['红色', '橙色', '黄色', '白色', '蓝色', '其他']
	        }, 
	        yAxis: {
	            title: {
	                text: ''
	            }
	        },
	        tooltip: {                                                        
	            formatter: function() {                                       
	                var s;                                                    
	                if (this.point.name) { // the pie chart                   
	                    s = ''+                                               
	                        this.point.name +': '+ this.y +' fruits';         
	                } else {                                                  
	                    s = ''+                                               
	                        this.x  +': '+ this.y;                            
	                }                                                         
	                return s;                                                 
	            }                                                             
	        },   
	        plotOptions: {
	            series: {
	                cursor: 'pointer',
	            }
	        },
	        series: [{                                                        
	            type: 'column',                                               
	            name: '预警类型',                                                 
	            data: [{
	                y: list[0],
	                color: colors[0],
	            }, {
	                y: list[1],
	                color: colors[1],
	            }, {
	                y: list[2],
	                color: colors[2],
	            }, {
	                y: list[3],
	                color: colors[3],
	            }, {
	                y: list[4],
	                color: colors[4],
	            }, {
	                y: list[5],
	                color: colors[5],
	            }]                                         
	        }] 
	    });
		Highcharts.setOptions(Highcharts.theme);
	}
	function initPeopleType(namelist,numlist){
		Highcharts.theme = {
				colors: [ "#5683ae", "#fff", "#b7b6e9", "#eeaaee",
					"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
					
				}

			// Apply the theme
			Highcharts.setOptions(Highcharts.theme);
				
			    $('#highchart-container3').highcharts({                                           
			        chart: {                                                           
			            type: 'bar'                                                    
			        },                                                                 
			        title: {
			            text: '高危人员类别统计',
						style: {
						fontSize: '20px',
					},

			        },				                                                                                                                                 
			        xAxis: {                                                           
			            categories: namelist,
			            title: {                                                       
			                text: null                                                 
			            }                                                              
			        },                                                                 
			        yAxis: {                                                           
			            min: 0,                                                        
			            title: {                                                       
			                text: '高危类别人数',                             
			                align: 'high'                                              
			            },                                                             
			            labels: {                                                      
			                overflow: 'justify'                                        
			            }                                                              
			        },                                                                 
			        tooltip: {                                                         
			            valueSuffix: ' 个'                                       
			        },                                                                 
			        plotOptions: {                                                     
			            bar: {                                                         
			                dataLabels: {                                              
			                    enabled: true                                          
			                }                                                          
			            }                                                              
			        },                                                                 
			        legend: {                                                          
			            layout: 'vertical',                                            
			            align: 'right',                                                
			            verticalAlign: 'top',                                          
			            x: -40,                                                        
			            y: 100,                                                        
			            floating: true,                                                
			            borderWidth: 1,                                                
			            backgroundColor: '#FFFFFF',                                    
			            shadow: true                                                   
			        },                                                                 
			        credits: {                                                         
			            enabled: false                                                 
			        },                                                                 
			        series: [{                                                         
			            name: '人数',   
			            data: numlist                                   
			        }]                                                                 
			    });                           
	}

	
	

	/**
	 * 获取最近一天时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyDay(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近一周时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyWeek(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*7;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取最近一月时间段
	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyMonth(){
		var endTime = new Date().getTime();
		var startTime = endTime - 1000*60*60*24*31;
		return {startTime : startTime , endTime : endTime};
	}
	
	/**
	 * 获取自定义时间
	 * @retruns {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
	 */
	function getRecentlyDiy(){
		var endTime = $.laydate.getTime("#searchRange", "end");
		var startTime = $.laydate.getTime("#searchRange", "start");
		return {startTime : startTime , endTime : endTime};
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.highriskpersonalert.home.analyze, { 
		getRecentlyDay :getRecentlyDay ,
		getRecentlyWeek : getRecentlyWeek,
		getRecentlyMonth: getRecentlyMonth,
		getRecentlyDiy : getRecentlyDiy
	});	
})(jQuery);

$.highriskpersonalert.home.peopleShiduan = $.highriskpersonalert.peopleShiduan || {} ;
(function($){
	
	function initOrRefresh(){
		findHighriskPeopleAnalyze();
	}
	function findHighriskPeopleAnalyze(){
		//人员时段统计
		var rylxCodes = $.szpt.util.businessData.getRylxCodes();
//		var data = {
//				"peopleTypeCodes":rylxCodes//["000", "001", "002", "003", "004", "005", "014", "015", "016", "017"]
//			};
//		var dataStr = $.util.toJSONString(data) ;
		var obj = new Object();
		$.util.objToStrutsFormData(rylxCodes,"peopleTypeCodes",obj);
		$.util.objToStrutsFormData(startTime,"startTime",obj);
		$.util.objToStrutsFormData(endTime,"endTime",obj);
		
		$.ajax({
			url:context +'/highriskPerson/highriskPeopleShiduan.action',
			data:obj,
			type:"post",
			dataType:"json",
			success:function(successData){
				initOrRefreshChart("echarts-main1", successData.resultMap.list) ;
			},
			error:function(errorData){
				
			}
		});
	}
	
	var fmtdt = {} ;
	function initOrRefreshChart(domId, data){

		var fmtData = fmt_data(data) ;
		fmtdt = fmtData ;
		var series = [] ;
		$.each(fmtData.values, function(key, val){
			var s = {
			        name: key,
			        type: 'scatter',
			        symbolSize: function (vl) {
			            return vl[2];
			        },
			        data: val,
			        animationDelay: function (idx) {
			            return idx * 5;
			        },
			        itemStyle:{
			        	normal:{
			        		color:"#dc69aa"
			        	}
			        }
			};
			series.push(s);
		});
		
		var chart = $.echart.getEchart(domId) ;
		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.xAxis[0].data = fmtData.types;
			opt.yAxis[0].data = fmtData.places;
			opt.series = series ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		init_chart(domId, fmtData, series) ;
	}

	function init_chart(domId, fmtData, series){

		var option = {
			    title:  {
			        left: 'center',
			        text: '人员时段统计',
			        textStyle:{
			        	color:"#E0E0E3",
			        	fontSize:20
			        }
			    },
			    tooltip: {
			       // position: 'top',
			        formatter: function (params) {
			        	return fmtdt.types[params.value[0]] + " , " + fmtdt.places[params.value[1]] + " : " + params.value[2] ;
			        }
			    },
			    grid: {
			        left: 2,
			        bottom: 10,
			        right: 10,
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        data:fmtData.places ,
			        'axisLabel':{'interval':0},//使X轴项名称全部显示出来
			        boundaryGap: false,
			        axisLabel:{
			        	textStyle:{
			        		color:"#dbdde1"
			        	}
			        },
			        splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#465068',
			                type: 'dotted'
			            }
			        },
			        axisLine: {
			            show: true
			        }
			    },
			    yAxis: {
			        type: 'category',
			        data:fmtData.types ,
			        'axisLabel':{'interval':0},//使X轴项名称全部显示出来
			        axisLabel:{
			        	textStyle:{
			        		color:"#dbdde1"
			        	}
			        },
					splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#465068',
			                type: 'dotted'
			            }
			        },
			        axisLine: {
			            show: true
			        }
			    },
			    series:series
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
	}
	
	function fmt_data(data){
		
		ysNameToCode = {} ;
		var places = [] ;
		var types = [] ;
		var values = {} ;
		
		$.each($.common.SHI_DUAN, function(key, val){
			types.push(key) ;
		});
		
		$.each(data, function(i, val){
			ysNameToCode[val.name] = val.nameCode ;
			var type_index = $.inArray(val.name,  types) ;
			if(type_index<0){
				types.push(val.name) ;
				type_index = $.inArray(val.name, types) ;
			}
			var place_index = $.inArray(val.nameAdd1, places) ;
			if(place_index<0){
				places.push(val.nameAdd1) ;
				place_index = $.inArray(val.nameAdd1, places) ;
				values[val.nameAdd1] = [] ;
			}
			values[val.nameAdd1].push([type_index, place_index, val.count]);
		});
		
		if(places.length==0){
			types = [] ;
		}
		
		return {
			places:places,
			types:types,
			values:values
		};
	}
	
	jQuery.extend($.highriskpersonalert.home.peopleShiduan, { 
		initOrRefresh:initOrRefresh,
	});	
})(jQuery);

$.highriskpersonalert.home.peopleChusuo = $.highriskpersonalert.peopleChusuo || {} ;
(function($){
	
	function initOrRefresh(){
		findHighriskPeopleAnalyze();
	}
	function findHighriskPeopleAnalyze(){
		//人员处所统计
//		var data = {
//				"peopleTypeCodes":$.szpt.util.businessData.getRylxCodes()
//			};
//		var dataStr = $.util.toJSONString(data) ;
		
		var rylxCodes = $.szpt.util.businessData.getRylxCodes();
		var obj = new Object();
		$.util.objToStrutsFormData(rylxCodes,"peopleTypeCodes",obj);
		$.util.objToStrutsFormData(startTime,"startTime",obj);
		$.util.objToStrutsFormData(endTime,"endTime",obj);
		
		$.ajax({
			url:context +'/highriskPerson/highriskPeopleChusuo.action',
			data:obj,
			type:"post",
			dataType:"json",
			success:function(successData){
				initOrRefreshChart("echarts-main2", successData.resultMap.list) ;
			},
			error:function(errorData){
			}
		});
	}

	var fmdt = {} ;
	function initOrRefreshChart(domId, data){

		var fmtData = fmt_data(data) ;
		fmdt = fmtData ;
			
		var series = [] ;
		$.each(fmtData.values, function(key, val){
			var s = {
			        name: key,
			        type: 'scatter',
			        symbolSize: function (vl) {
			            return vl[2];
			        },
			        data: val,
			        animationDelay: function (idx) {
			            return idx * 5;
			        },
			        itemStyle:{
			        	normal:{
			        		color:"#dc69aa"
			        	}
			        }
			};
			series.push(s);
		});
		
		var chart = $.echart.getEchart(domId) ;
		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.yAxis[0].data = fmtData.places ;
			opt.xAxis[0].data = fmtData.types ;
			opt.series = series ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		init_chart(domId, fmtData, series) ;
	}

	function init_chart(domId, fmtData, series){
			option = {
			    title:  {
			        left: 'center',
			        text: '人员处所统计',
			        textStyle:{
			        	color:"#E0E0E3",
			        	fontSize:20
			        }
			    },
			    tooltip: {
			        position: 'top',
			        formatter: function (params) {
			        	return fmdt.places[params.value[1]] + " , " + fmdt.types[params.value[0]] + " : " + params.value[2] ;
			        }
			    },
			    grid: {
			        left: 2,
			        bottom: 10,
			        right: 10,
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        data: fmtData.types,
			        axisLabel:{
			        	textStyle:{
			        		color:"#dbdde1"
			        	}
			        },
			        boundaryGap: false,
			        splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#465068',
			                type: 'dotted'
			            }
			        },
			        axisLine: {
			            show: true
			        }
			    },
			    yAxis: {
			        type: 'category',
			        data:fmtData.places,
			        axisLabel:{
			        	textStyle:{
			        		color:"#dbdde1"
			        	}
			        },
					splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#465068',
			                type: 'dotted'
			            }
			        },
			        axisLine: {
			            show: true
			        }
			    },
			    series: series
			};
			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
	}
	
	function fmt_data(data){
		
		ysNameToCode = {} ;
		var places = [] ;
		var types = [] ;
		var values = {} ;
		
		$.each(data, function(i, val){
			ysNameToCode[val.name] = val.nameCode ;
			var place_index = $.inArray(val.nameAdd1, places) ;
			if(place_index<0){
				places.push(val.nameAdd1) ;
				place_index = $.inArray(val.nameAdd1, places) ;
				values[val.nameAdd1] = [] ;
			}
			var type_index = $.inArray(val.name, types) ;
			if(type_index<0){
				types.push(val.name) ;
				type_index = $.inArray(val.name, types) ;			
			}
			values[val.nameAdd1].push([type_index, place_index, val.count]);
		});
		
		return {
			places:places,
			types:types,
			values:values
		};
	}
	
	jQuery.extend($.highriskpersonalert.home.peopleChusuo, { 
		initOrRefresh:initOrRefresh
	});	
	
})(jQuery);




