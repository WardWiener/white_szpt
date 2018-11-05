$.xsfxts = $.xsfxts || {};
$.xsfxts.welcome = $.xsfxts.welcome || {};

(function($){
	
	"use strict";
	
	var caseTagAjlbDicItem ;
	
	
	$(document).ready(function() {	
		$.szpt.util.businessData.addToInitSuccessCallBack(initPageDIct) ;
		initHighcharts();
		initCaseTagAjlb();
		setDefaultTimeCondition();
		
		//执行默认查询
		queryLarcenyCaseByTime();
		queryRobCaseByTime();
		queryOccurPlaceCount();
		queryCaseFeatureCount();
		queryCasePeriodCount();
		
		/**
		 * 分析
		 */
		$(document).on("click","#analysis",function(){
			queryLarcenyCaseByTime();
			queryRobCaseByTime();
			queryOccurPlaceCount();
			queryCaseFeatureCount();
			queryCasePeriodCount();
		});
	});
	
	/**
	 * 初始化页面字典
	 */
	function initPageDIct(){
		$.szpt.util.businessData.initPcsSelect("#region");
	}
	
	/**
	 * 设置默认时间条件
	 */
	function setDefaultTimeCondition(){
		var nowDate = new Date();
		$.laydate.setRange($.date.dateToStr(new Date(nowDate.getTime() - 1000*60*60*24), "yyyy-MM-dd HH"), $.date.dateToStr(nowDate, "yyyy-MM-dd HH"), "#welcomeDateRange");
	}
	
	/**
	 * 初始化页面案件类别相关字典项
	 */
	function initCaseTagAjlb(){
		var dicItemSettings = {
			id:"caseTagAjlb",
			dicTypeCode : $.common.DICT.DICT_TYPE_AJXZ
		};
		caseTagAjlbDicItem = $.cascadedDicItem.init(dicItemSettings);
		
		var selects = [{
			selector : "#type",
			selectedCode : null
		},
		{
			selector : "#firstSort",
			selectedCode : null,
			selectEventCallback : function(select){
				
			},
			unselectEventCallback : function(select){
				
			}
		},
		{
			selector : "#secondSort",
			selectedCode : null
		}]
		
		caseTagAjlbDicItem.refreshBySelectedCodes(selects);
	}
	
	/**
	 * 查询案发处所统计数量
	 */
	function queryOccurPlaceCount(){
		$.ajax({
			url:context +'/xsfxts/queryOccurPlaceCount.action',
			data:getQueryCondition(),
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cpcs = successData.resultMap.cpcs;
				setOpOrCfOrCpResult("#occurPlaceTable", cpcs);
			}
		});
	}
	
	/**
	 * 查询作案特点统计数量
	 */
	function queryCaseFeatureCount(){
		$.ajax({
			url:context +'/xsfxts/queryCaseFeatureCount.action',
			data:getQueryCondition(),
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cpcs = successData.resultMap.cpcs;
				setOpOrCfOrCpResult("#caseFeatureTable", cpcs);
			}
		});
	}
	
	/**
	 * 查询作案时段统计数量
	 */
	function queryCasePeriodCount(){
		$.ajax({
			url:context +'/xsfxts/queryCasePeriodCount.action',
			data:getQueryCondition(),
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var cpcs = successData.resultMap.cpcs;
				setOpOrCfOrCpResult("#casePeriodTable", cpcs);
			}
		});
	}
	
	/**
	 * 获取查询条件（查询案发处所、作案特点、作案时段用）
	 */
	function getQueryCondition(){
		var data = {
			"startTime" : $.laydate.getTime("#welcomeDateRange", "start") ,
			"endTime" : $.laydate.getTime("#welcomeDateRange", "end") ,
			"region" : $.select2.val("#region") == "all" ? "" : $.select2.val("#region") ,
			"type" : $.select2.val("#type") ,
			"firstSort" : $.select2.val("#firstSort") ,
			"secondSort" : $.select2.val("#secondSort") 
		};
		var dataStr = $.util.toJSONString(data) ;
		return dataStr;
	}
	
	/**
	 * 设置案发处所、作案特点、作案时段结果列表
	 * 
	 * @param selector 表选择器
	 * @param dataArray 数据数组
	 */
	function setOpOrCfOrCpResult(selector, dataArray){
		$(selector).find("tbody").html("");
		if(!$.util.isArray(dataArray) || dataArray.length < 1){
			return ;
		}
		$.each(dataArray,function(d,data){
			if(d >= 5){
				return false;
			}
			var tr = $('<tr />',{});
			//打标值
			$("<td />",{
				text : data.tagValue
			}).appendTo(tr);
			//数量
			var numberTd = $("<td />",{}).appendTo(tr);
			$("<span />",{
				"class" : "font16" ,
				"text" : data.number
			}).appendTo(numberTd);
			//增长还是下降
			var thirdTd = $("<td />",{}).appendTo(tr);
			
			
			var proportion = data.proportion;
			if(proportion.indexOf(".") != -1){
				proportion = proportion.substring(0,proportion.indexOf(".") + 2);
			}
			if(proportion.indexOf("-") == -1){//上升
				if(proportion == 0 || proportion == 0.0){
					$("<span />",{
						"text" : "--"
					}).appendTo(thirdTd);
//					proportion = data.proportion;
				}else{//大于0
					$("<span />",{
						"class" : "glyphicon glyphicon-arrow-up color-red1"
					}).appendTo(thirdTd);
					proportion += "%";
				}
			}else{//下降或者不显示百分比
				if(proportion.length == 1){//不显示
					$("<span />",{
						"text" : "--"
					}).appendTo(thirdTd);
					proportion = "--"
				}else{//下降
					$("<span />",{
						"class" : "glyphicon glyphicon-arrow-down color-green1"
					}).appendTo(thirdTd);
					proportion = proportion.substring(1,proportion.length) + "%";
				}
			}
			
			//环比值
			var proportionTd = $("<td />",{}).appendTo(tr);
			$("<span />",{
				"class" : "font16" ,
				"text" : proportion
			}).appendTo(proportionTd);
			
			$(selector).append(tr);
		});
		
	}
	
	/**
	 * 根据时间段查询盗窃案（饼图）
	 */
	function queryLarcenyCaseByTime(){
		var data = {
			"startTime" : $.laydate.getTime("#welcomeDateRange", "start") ,
			"endTime" : $.laydate.getTime("#welcomeDateRange", "end")
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/xsfxts/queryLarcenyCaseByTime.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var pcsLarcenyCaseNum = successData.resultMap.pcsLarcenyCaseNum;
				setLarcenyCase(pcsLarcenyCaseNum);
			}
		});
	}
	
	/**
	 * 根据时间段查询抢劫、抢夺案（饼图）
	 */
	function queryRobCaseByTime(){
		var data = {
			"startTime" : $.laydate.getTime("#welcomeDateRange", "start") ,
			"endTime" : $.laydate.getTime("#welcomeDateRange", "end")
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/xsfxts/queryRobberyCaseByTime.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var pcsRobberyCaseNum = successData.resultMap.pcsRobberyCaseNum;
				setRobCase(pcsRobberyCaseNum);
			}
		});
	}
	
	/**
	 * 设置盗窃案饼图
	 * 
	 * @param objArray 数据数组
	 */
	function setLarcenyCase(objArray){
		var count = 0;
		var dataArray=[];
		$.each(objArray,function(i,val){
			var obj = new Object();
			$.each(val,function(p,v){
				obj["name"] = p;
				obj["y"] = parseInt(v);
				count += parseInt(v);
			});
			dataArray.push(obj);
		});
		$("#larcenyCaseCount").text(count + "起");
		
		//如果案件数量<1则不显示图表
		if(count < 1){
			$("#highchartLarcenyCase").hide();
		}else{
			$("#highchartLarcenyCase").show();
		}
		
		Highcharts.theme = {
			colors: ["#7cb5ec", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee","#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]
		}
		Highcharts.setOptions(Highcharts.theme);
		$('#highchartLarcenyCase').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: null,
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
	            data: dataArray
	        }]
	    });
	}
	
	/**
	 * 设置抢劫、抢夺案件发案情况饼图
	 * 
	 * @param objArray 数据数组
	 */
	function setRobCase(objArray){
		var count = 0;
		var dataArray=[];
		$.each(objArray,function(i,val){
			var obj = new Object();
			$.each(val,function(p,v){
				obj["name"] = p;
				obj["y"] = parseInt(v);
				count += parseInt(v);
			});
			dataArray.push(obj);
		});
		$("#robCaseCount").text(count + "起");
		
		//如果案件数量<1则不显示图表
		if(count < 1){
			$("#highchartRobCase").hide();
		}else{
			$("#highchartRobCase").show();
		}
		
		Highcharts.theme = {
			colors: ["#7cb5ec", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee","#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]
		}
		Highcharts.setOptions(Highcharts.theme);
		$('#highchartRobCase').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: null,
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
	            data: dataArray
	        }]
	    });
	}
	
	/**
	 * 初始化Highcharts，配置setting
	 */
	function initHighcharts(){
		Highcharts.theme = {
			colors: ["#ff0022", "#ff9900", "#ffde00", "#7cb5ec", "#fff", "#b7b6e9", "#eeaaee",
				"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
				
			chart: {
				backgroundColor: {
					linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
					stops: [
						[0, '#15243a'],
						[1, '#1e3353']
					]
				},
				style: {
					fontFamily: "Microsoft Yahei, sans-serif",
				},
				plotBorderColor: '#606063'
			},
			
		title: {
				style: {
					color: '#E0E0E3',
					textTransform: 'uppercase',
					fontSize: '20px'
				}
			},
			subtitle: {
				style: {
					color: '#E0E0E3',
					textTransform: 'uppercase',
					fontSize: '16px'
				}
			},
			xAxis: {
				gridLineColor: '#707073',
				labels: {
					style: {
						color: '#E0E0E3'
					}
				},
				lineColor: '#707073',
				minorGridLineColor: '#505053',
				tickColor: '#707073',
				title: {
					style: {
						color: '#A0A0A3'

					}
				}
			},
			yAxis: {
				gridLineColor: '#707073',
				labels: {
					style: {
						color: '#E0E0E3'
					}
				},
				lineColor: '#707073',
				minorGridLineColor: '#505053',
				tickColor: '#707073',
				tickWidth: 1,
				title: {
					style: {
						color: '#fff'
					}
				}
			},
			tooltip: {
				backgroundColor: 'rgba(0, 0, 0, 0.85)',
				style: {
					color: '#F0F0F0',
					fontSize: '16px'
					
				}
			},
			plotOptions: {
				series: {
					dataLabels: {
						color: '#B0B0B3'
					},
					marker: {
						lineColor: '#333'
					}
				},
				boxplot: {
					fillColor: '#505053'
				},
				candlestick: {
					lineColor: 'white'
				},
				errorbar: {
					color: 'white'
				}
			},
			legend: {
				backgroundColor: '#132643',
				itemStyle: {
					color: '#aaa',
				},
				itemHoverStyle: {
					color: '#fff'
				},
				itemHiddenStyle: {
					color: '#ccc'
				}
			},
			credits: {
				text: '',
				style: {
					color: '#fff'
				}
			},
			labels: {
				style: {
					color: '#fff'
				}
			},

			drilldown: {
				activeAxisLabelStyle: {
					color: '#F0F0F3'
				},
				activeDataLabelStyle: {
					color: '#F0F0F3'
				}
			},

			navigation: {
				buttonOptions: {
					symbolStroke: '#DDDDDD',
					theme: {
						fill: '#505053'
					}
				}
			},

			// scroll charts
			rangeSelector: {
				buttonTheme: {
					fill: '#505053',
					stroke: '#000000',
					style: {
						color: '#CCC'
					},
					states: {
						hover: {
							fill: '#707073',
							stroke: '#000000',
							style: {
								color: 'white'
							}
						},
						select: {
							fill: '#000003',
							stroke: '#000000',
							style: {
								color: 'white'
							}
						}
					}
				},
				inputBoxBorderColor: '#505053',
				inputStyle: {
					backgroundColor: '#333',
					color: 'silver'
				},
				labelStyle: {
					color: 'silver'
				}
			},

			navigator: {
				handles: {
					backgroundColor: '#666',
					borderColor: '#AAA'
				},
				outlineColor: '#CCC',
				maskFill: 'rgba(255,255,255,0.1)',
				series: {
					color: '#7798BF',
					lineColor: '#A6C7ED'
				},
				xAxis: {
					gridLineColor: '#505053'
				}
			},

			scrollbar: {
				barBackgroundColor: '#808083',
				barBorderColor: '#808083',
				buttonArrowColor: '#CCC',
				buttonBackgroundColor: '#606063',
				buttonBorderColor: '#606063',
				rifleColor: '#FFF',
				trackBackgroundColor: '#404043',
				trackBorderColor: '#404043'
			},

			// special colors for some of the
			legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
			background2: '#505053',
			dataLabelsColor: '#B0B0B3',
			textColor: '#C0C0C0',
			contrastTextColor: '#F0F0F3',
			maskColor: 'rgba(255,255,255,0.3)'		

		};
		Highcharts.setOptions(Highcharts.theme);
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.xsfxts.welcome, { 
		
	});	
})(jQuery);