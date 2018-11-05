var tag = false;
//highchart主题
(function($){
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
                fontSize: '14px'

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

// Apply the theme
    Highcharts.setOptions(Highcharts.theme);

})(jQuery);



$.zhzats = $.zhzats || {} ;
$.zhzats.gaofa = $.zhzats.gaofa || {} ;

$.zhzats.gaofa.util = $.zhzats.gaofa.util || {} ;
(function($){
	
	"use strict";
	
	$(document).ready(function() {	
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		
		$(document).on("click" , "#statistics", function(){
			if(tag){
				if("" != checkTime()){
					$.util.topWindow().$.layerAlert.alert({msg:checkTime(),title:"提示",icon:0,time:3000,});	
					return false;
				}
			}
			refreshAll();
		})
		
		
		$(document).on("click" , ".statistics", function(){
			tag = false;
			$("#searchRange").hide();
			refreshAll();
		});
		$(document).on("click" , "#reset", function(){
			$.select2.val("#pcsSelect","all",true);
			$.szpt.util.businessData.destroyTree("gaofaJqlx");
			$.szpt.util.businessData.initJqlxTree("gaofaJqlx");
			refreshAll();
		});
		
		$(document).on("click", "#gaofaJqlxSearch", function(){
			$.szpt.util.businessData.showMenuByzTreeSelectInfo("gaofaJqlx");
		});
		
		$(document).on("click","#dateCustomized",function(){
			tag = true;
			$("#searchRange").show();
		});
    });
	function init() {
		$.szpt.util.businessData.initJqlxTree("gaofaJqlx");
	}
	
	var pageInit = function init(){
		initWidget() ;
		refreshAll() ;
	}
	$.szpt.util.analyzeData.addToInitSuccessCallBack(pageInit) ;
	
	function initWidget(){
		$.szpt.util.searchTime.initBtns() ;
		initJqlxTree() ;
		$.szpt.util.analyzeData.initPcsSelect("#pcsSelect") ;
	}

	function initJqlxTree(){
		$.zTreeMenu.init("dimJqlxId", context + "/gaofa/queryJqlxTree.action", {
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
		$.zhzats.gaofa.jingqing.initOrRefresh(); 
		$.zhzats.gaofa.jqpie.initOrRefresh() ;
	}

	jQuery.extend($.zhzats.gaofa.util, { 
		refreshAll:refreshAll,
		getCheckJqlxCodes:function(){
			var list = [] ;
			$.each($.zTreeMenu.getCheckedValue("dimJqlxId").data, function(i, val){
				list.push(val.diyMap.code) ;
			});
			return list ;
		}
	});
	
	function getTime(){
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
	
	jQuery.extend( $.zhzats.gaofa.util, { 
		getTime:getTime,
		checkTime:checkTime
	});
	
})(jQuery);	

$.zhzats.gaofa.jingqing = $.zhzats.gaofa.jingqing || {} ;
(function($){	
	"use strict";
	
	var chart ;
	
	$(document).ready(function() {	
		

    });
	
	function initOrRefresh(){
		var queryTime ;
		if(tag){
			queryTime = $.zhzats.gaofa.util.getTime();
		}else{
			queryTime = $.szpt.util.searchTime.getDates($.szpt.util.searchTime.getTimeValueType());
		}
		var selectJqlx = $.szpt.util.businessData.getSelectByTree("gaofaJqlx");
		var jqlxCodes = $.zhzats.gaofa.util.getCheckJqlxCodes();
		var list = [];
		if($.util.exist(selectJqlx)){
			list.push(selectJqlx)
			jqlxCodes = list;
		}
		var data = {
				"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),//$.szpt.util.analyzeData.getPcsCodes(),
				"jqlxCodes":jqlxCodes
			};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/gaofa/findGaofaJq.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){

				initOrRefreshChart("gaofa-jingqing", successData.resultMap.list) ;
			}
		});
	}
	
	function initOrRefreshChart(domId, data){
		var fmtData = fmt_data(data) ;
		toZongjie(fmtData.valuesPcs, fmtData.valuesShiDuan) ;
		
		var series = [] ;
		var i = 0 ;
		$.each(fmtData.values, function(key, val){
			var s = {
			   name:key,
		       data:val,
		       visible: (i==0)?true:false
	        };

			series.push(s);
			
			i++ ;
		});
		
		if(chart){
			chart.destroy();
			return ;
		}
		
		init_chart(domId, fmtData, series) ;
	}
	
	function toZongjie(pcs, shiduan){
		var jq = [] ;
		$.each(pcs, function(i, val){
			jq.push(val.name + '<span class="num color-red2">'+val.count+'</span>例') ;
		});
		$("#zongjie-jq").html(jq.join("、")) ;
		
		var sd = [] ;
		$.each(shiduan, function(i, val){
			sd.push(val.name + '<span class="num color-red2">'+val.count+'</span>例') ;
		});
		$("#zongjie-sd").html(sd.join("、")) ;
	}
	
	function init_chart(domId, fmtData, series){
		
	    var chart = $('#' + domId).highcharts({
	        chart: {
	            type: 'bubble',
	            backgroundColor: 'rgba(0,0,0,0)'
	        },
	        colors:["#ce8199", "#70ceb3", "#cacb83",  "#ff1b49", "#dc69aa", "#4aabfd"],
	        title: {
	            text: ''
	        },
	        credits:{
	        	enabled:false
	        },
	        legend:{
	        	enabled:true,
	        	itemStyle:{
	        		color:"#ffffff"
	        	}
	        },
	        series: series,
	        yAxis:{
	        	categories:fmtData.ys,
	        	gridLineColor:"#32415a",
	        	endOnTick:false,
	        	startOnTick:false,
	        	title:{
	        		text:""
	        	},
	        	labels:{
	        		style:{
	        			color:"#59a4c5",
	        			cursor: "pointer"
	        		},
	            	formatter:function(){
	            		if($.util.isNum(this.value)){
	            			return "" ;
	            		}
		        		return '<a href="javascript:void(0)" class="faanchusuo_hc-yName">'+this.value+'</a>';
		        	}
	        	},
	        	type:"category",
	
	        },
	        xAxis:{
	        	categories:fmtData.xs,
	        	gridLineColor:"#32415a",
	        	lineColor:"#32415a",
	        	tickColor:"#32415a"
	        },
	        tooltip:{
	        	formatter:function(){
	        		var y = this.y ;
	        		var yName = this.series.yAxis.categories[y];
	        		var num = this.point.z ;
	        		return num + "("+this.x+", "+yName+")" ;
	        	}
	        },
	    });
	}
	
	function fmt_data(data){

		var ys = [] ;
		var xs = [] ;
		var values = {
			"本期":[],
			"同比":[],
			"环比":[]
		} ;
		var valuesHB = [] ;
		var valuesTB = [] ;
		
		var valuesPcs = [] ;
		var valuesShiDuan = [] ;
		
		$.each(data, function(i, val){
			
			var x_index = $.inArray(val.name, xs) ;
			if(x_index<0){
				xs.push(val.name) ;
				x_index = $.inArray(val.name, xs) ;
			}
			
			var y_index = $.inArray(val.nameAdd1, ys) ;
			if(y_index<0){
				ys.push(val.nameAdd1) ;
				y_index = $.inArray(val.nameAdd1, ys) ;
			}
			
			values["本期"].push([x_index, y_index, val.count]) ;
			values["同比"].push([x_index, y_index, val.tbCount]) ;
			values["环比"].push([x_index, y_index, val.hbCount]) ;
			
			var obj = {
				name:val.nameAdd1,
				count:val.count
			}
			
			var flag = false ;
			$.each(valuesPcs, function(k, val1){
				if(val.nameAdd1 == val1.name){
					flag = true ;
					obj = val1 ;
					obj.count += val.count ;
				}
			});
			
			if(!flag){
				valuesPcs.push(obj) ;
			}
			
			var obj1 = {
				name:val.name,
				count:val.count	
			}
			
			var flag = false ;
			$.each(valuesShiDuan, function(k, val1){
				if(val.name == val1.name){
					flag = true ;
					obj1 = val1 ;
					obj1.count += val.count ;
				}
			});
			
			if(!flag){
				valuesShiDuan.push(obj1) ;
			}
			
		});
		
		valuesPcs.sort(function(a, b){
			return b.count - a.count ;
		});
		
		valuesShiDuan.sort(function(a, b){
			return b.count - a.count ;
		});
		
		return {
			ys:ys,
			xs:xs,
			valuesPcs:valuesPcs,
			valuesShiDuan:valuesShiDuan,
			values:values
		};
	}

	jQuery.extend($.zhzats.gaofa.jingqing, { 
		initOrRefresh:initOrRefresh
	});

})(jQuery);	

$.zhzats.gaofa.jqpie = $.zhzats.gaofa.jqpie || {} ;
(function($){	

	"use strict";
	
	var grandparentJqlxCode = "" ;
	var parentJqlxCode = "" ;
	var level = 1;
	var jqlxNameToCode = {} ;
	
	var chart ;
	
	$(document).ready(function() {	
		
    });
	
	$(document).on("click", "#toUpPage", function(){
		parentJqlxCode = grandparentJqlxCode ;
		grandparentJqlxCode = "" ;
		level = level - 1;
		$.zhzats.gaofa.jqpie.initOrRefresh("drillChild") ;
	});
	
	function initOrRefresh(searchType){
		var queryTime ;
		if(tag){
			queryTime = $.zhzats.gaofa.util.getTime();
		}else{
			queryTime = $.szpt.util.searchTime.getDates($.szpt.util.searchTime.getTimeValueType());
		}
		var selectJqlx = $.szpt.util.businessData.getSelectByTree("gaofaJqlx");
		var jqlxCodes = $.zhzats.gaofa.util.getCheckJqlxCodes();
		var list = [];
		if($.util.exist(selectJqlx)){
			list.push(selectJqlx)
			jqlxCodes = list;
		}
		var data = {
				"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),//$.szpt.util.analyzeData.getSeletedByPcsSelect(),
				"jqlxCodes":jqlxCodes,
				"searchType":$.util.isBlank(searchType)?"":searchType,
			    "parentJqlxCode":parentJqlxCode,
			    "level":level
			};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/gaofa/findGaofaJqlx.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){

				initOrRefreshChart("gaofa-jingqing-lx", successData.resultMap.list) ;
				
				grandparentJqlxCode = $.util.isBlank(successData.resultMap.grandparentJqlxCode)?"":successData.resultMap.grandparentJqlxCode ;
				level = successData.resultMap.level;
				if(level==1){
					$("#toUpPage").hide() ;
				}else if($.util.isBlank(grandparentJqlxCode)){
					$("#toUpPage").hide() ;
					level = 1;
				}else{
					$("#toUpPage").show() ;
				}
			}
		});
	}
	
	function initOrRefreshChart(domId, data){
		var fmtData = fmt_data(data);
		
		
		var color = ["#FF4040", "#ff9900", "#ffde00", "#7cb5ec", "#fff", "#b7b6e9", "#eeaaee", "#4fcbd6", "#DF5353", "#7798BF", "#aaeeee"];
		$.each(fmtData, function(i, val){
			if(i<color.length){
				val["color"] = color[i]
			}
			
			val.events = {
					click:function(){
						var jqlxCode = this.code ;
						parentJqlxCode = jqlxCode ;
						level = level+1;
						$.zhzats.gaofa.jqpie.initOrRefresh("drill") ;
	                }
			}
		});
		
		var series = [{
			name:"",
			//radius : '50%',
		    //center: ['50%', '60%'],
			type:"pie",
			data:fmtData
		}] ;
		
		if(chart){
			chart.destroy() ;
		}

		initChart(domId, series) ;
	}
	
	function initChart(domId, series){
		
		chart = $('#' + domId).highcharts({
		        chart: {
		            backgroundColor: 'rgba(0,0,0,0)',
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		        },
		        credits:{
		        	enabled:false
		        },
		        title: {
		            text: '警情高发类型',
		            style:{
		            	color: '#ffffff',
		            }
		        },
		        tooltip: {
		    	    pointFormat: '<b>{point.y}({point.percentage:.1f}%)</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#b0b0b3',
		                    connectorColor: '#b0b0b3',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series:series
		  });
	
		
	}

	function fmt_data(data){
		
		jqlxNameToCode = {} ;
		
		var rsdata = [];
		$.each(data, function(i, val){
			jqlxNameToCode[val.name] = val.nameCode ;
			rsdata.push({
				name:val.name,
				code:val.nameAdd1,
				y:val.count
			}) ;
		});
		
		rsdata.sort(function(a, b){
			return b.y - a.y ;
		});
		
		toZongjie(rsdata) ;
		
		return rsdata ;
	}
	
	function toZongjie(data){
		var lx = [] ;
		$.each(data, function(i, val){
			lx.push(val.name + '<span class="num color-red2">'+val.y+'</span>例') ;
		});
		$("#zongjie-lx").html(lx.join("、")) ;
	}
	
	jQuery.extend($.zhzats.gaofa.jqpie, { 
		initOrRefresh:initOrRefresh
	});
})(jQuery);	
