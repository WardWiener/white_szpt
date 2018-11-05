
var pcs_name_info = {"三江派出所":"三江所","平桥派出所":"平桥所","长江派出所":"长江所","金竹派出所":"金竹所","黄河派出所":"黄河所","大兴派出所":"大兴所"};

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
$.zhzats.welcome = $.zhzats.welcome || {} ;

$.zhzats.welcome.util = $.zhzats.welcome.util || {} ;
(function($){
	
	"use strict";
	var tag = false;
	var unit_pcs = [] ;
	$(document).ready(function() {	
		$("#suggestUnit").text("分局");
		$(document).on("click" , ".search", function(){
			tag = false;
			$("#searchRange").hide();
			var list = $.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect");
			if(list.length==1){
				$("#suggestUnit").text($.select2.text("#pcsSelect"));
				$("#jqHight").hide() ;
			}else{
				$("#suggestUnit").text("分局");
				$("#jqHight").show() ;
			}
			var timeValueType = $.szpt.util.searchTime.getTimeValueType() ;
			changeTitle(timeValueType) ;
			$.zhzats.welcome.util.refreshAll();
		});
		//发送指令
		$(document).on("click" , "#sendInstruction", function(){
			var type = "zhzats";
			var typeSetContent = new Object();
			typeSetContent.relatedObjectId = "123456789";
			typeSetContent.relateObjectContent = "打放管控分析指令";
			typeSetContent.type = "0000000011002";
			typeSetContent.content = document.getElementById('suggestContent').innerText;
			typeSetContent.unitCode = $.select2.val("#pcsSelect");
			typeSetContent.unitName = $.select2.text("#pcsSelect");
			$.szpt.util.instruction.addInstruction(null, type, typeSetContent);
		});
		
		$(document).on("click","#dateCustomized",function(){
			tag = true;
			$("#searchRange").show();
		});
		
		$(document).on("click" , "#search", function(){
			if(tag){
				if("" != checkTime()){
					$.util.topWindow().$.layerAlert.alert({msg:checkTime(),title:"提示",icon:0,time:3000,});	
					return false;
				}
				changeTitle() ;
			}else{
				var timeValueType = $.szpt.util.searchTime.getTimeValueType() ;
				changeTitle(timeValueType) ;
			}
			var list = $.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect");
			if(list.length==1){
				$("#suggestUnit").text($.select2.text("#pcsSelect"));
				$("#jqHight").hide() ;
			}else{
				$("#suggestUnit").text("分局");
				$("#jqHight").show() ;
			}
			$.zhzats.welcome.util.refreshAll();
		});
    });
	
	function changeTitle(timeValueType){
		changeGundongTitle(timeValueType) ;
	}
	
	function changeGundongTitle(timeValueType){
		if(tag){
			$("#todayJqData").text("自定义警情") ;
			$("#gundongJqTitle").text("自定义警情最多") ;
			$("#gundongPanChaTitle").text("自定义盘查人员、车辆最多") ;
			$("#gundongLiuDongTitle").text("自定义流动人口增长最多") ;
			return;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.DAY.toLowerCase()){
			$("#todayJqData").text("今日警情") ;
			$("#gundongJqTitle").text("今日警情最多") ;
			$("#gundongPanChaTitle").text("今日盘查人员、车辆最多") ;
			$("#gundongLiuDongTitle").text("今日流动人口增长最多") ;
		}else if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.WEEK.toLowerCase()){
			$("#todayJqData").text("近一周警情") ;
			$("#gundongJqTitle").text("近一周警情最多") ;
			$("#gundongPanChaTitle").text("近一周盘查人员、车辆最多") ;
			$("#gundongLiuDongTitle").text("近一周流动人口增长最多") ;
		}else if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.MONTH.toLowerCase()){
			$("#todayJqData").text("近一月警情") ;
			$("#gundongJqTitle").text("近一月警情最多") ;
			$("#gundongPanChaTitle").text("近一月盘查人员、车辆最多") ;
			$("#gundongLiuDongTitle").text("近一月流动人口增长最多") ;
		}
	}
	
	var pageInit = function init(){
		initWidget() ;
		refreshWithoutMap() ;
	}
	
	$.szpt.util.analyzeData.addToInitSuccessCallBack(pageInit) ;
	
	function initWidget(){
		$.szpt.util.searchTime.initBtns() ;
		$.szpt.util.analyzeData.initPcsSelect("#pcsSelect") ;
	}
	
	function refreshAll(){
		refreshWithoutMap() ;
		refreshMap() ;
	}

	function refreshWithoutMap(){
		$.zhzats.welcome.gundong.initOrRefresh() ;
		$.zhzats.welcome.jinri.initOrRefresh() ;
		$.zhzats.welcome.changliang.initOrRefresh() ;
		//$.zhzats.welcome.faanchusuo.initOrRefresh() ;
		$.zhzats.welcome.faanchusuo_hc.initOrRefresh() ;
		$.zhzats.welcome.faanshiduan_hc.initOrRefresh() ;
		//$.zhzats.welcome.wifiweilan.initOrRefresh() ;
        $.zhzats.welcome.wifiweilan_hc.initOrRefresh() ;
		//$.zhzats.welcome.gaowei.initOrRefresh() ;
        $.zhzats.welcome.gaowei_hc.initOrRefresh() ;
		//$.zhzats.welcome.liudong.initOrRefresh() ;
        $.zhzats.welcome.liudong_hc.initOrRefresh() ;
		$.zhzats.welcome.pancha_hc.initOrRefresh() ;
        //$.zhzats.welcome.pancha.initOrRefresh() ;
		$.zhzats.welcome.dafang.initOrRefresh() ;
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
	
	function refreshMap(){
		$.zhzats.welcome.map.initOrRefresh() ;
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
	

	jQuery.extend($.zhzats.welcome.util, { 
		refreshAll:refreshAll,
		getTime:getTime
	});
	
})(jQuery);	

$.zhzats.welcome.gundong = $.zhzats.gundong || {} ;
(function($){
	
	"use strict";
	

	
	$(document).ready(function(){	
		
    });
	
	function initOrRefresh(){
		gundongInfo() ;
	}
	
	function gundongInfo(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"jqlxCodes":[] ,
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
			
		var dataStr = $.util.toJSONString(data) ;
			
		$.ajax({
			url:context +'/zhzats/gundongInfo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				dataToView(successData.resultMap);
				scrollGundong();
			}
		});
	}
	
	function dataToView(data){
		var jqList = data.jqList ;
		var panchaList = data.panchaList ;
		var liudongrenkouList = data.liudongrenkouList ;

		toJqView(jqList) ;
		toPanChaView(panchaList) ;
		toLiuDongView(liudongrenkouList) ;
	}
	
	function toJqView(list){
		var dataStr = "" ;
		$.each(list, function(i, val){
			if(val.count<1){
				return true ;
			}
			
			dataStr+= val.name ;
			dataStr+= "("+ val.count + "起)、" ;
			
		});
		$("#gundongJqData").text(dataStr.substr(0,dataStr.length-1)) ;
	}
	
	function toPanChaView(list){
		var dataStr = "" ;
		$.each(list, function(i, val){
			var num = val.newCarSum + val.newCarNotKySum ;
			var newManSum = val.newManpowerSum ;
			if(num.count<1){
				return true ;
			}
			
			dataStr+= val.name ;
			dataStr+= "( "+ newManSum +"人、"+ num + "辆 )、" ;
			
		});
		$("#gundongPanChaData").text(dataStr.substr(0,dataStr.length-1)) ;
	}
	
	function toLiuDongView(list){
		var dataStr = "" ;
		$.each(list, function(i, val){
			if(val.floatingNumSum<1){
				return true ;
			}
			
			dataStr+= val.name ;
			dataStr+= "("+ val.floatingNumSum + "人)、" ;

		});
		$("#gundongLiuDongData").text(dataStr.substr(0,dataStr.length-1)) ;
	}
	
	var timer ;
	function scrollGundong(){
		
		if(timer){
			clearInterval(timer);
		}
		
		timer = setInterval(function(){
			$("#gundong_ul").animate({
				marginTop : "-25px"
				},500,function(){
				$(this).css({marginTop : "0px"}).find("li:first").appendTo(this);
			});
		}, 3000);
	}

	jQuery.extend($.zhzats.welcome.gundong, { 
		initOrRefresh:initOrRefresh
	});
	
})(jQuery);	

$.zhzats.welcome.jinri = $.zhzats.jinri || {} ;
(function($){
	
	"use strict";
	
	var jinri_table ;
	var level = 1;
	var parentJqlxCode = "" ;
	var grandparentJqlxCode = "" ;
	var searType = "";
	$(document).ready(function(){	
		$(document).on("click", ".jinri-table-jqlx", function(){
			var code = $(this).attr("code");
			var count = $(this).attr("count");
			searType = "child";
			parentJqlxCode = code;
			level = level + 1;
			if(count > 0){
				$.zhzats.welcome.jinri.initOrRefresh() ;
			}
		});
		$(document).on("click", "#toUpPage", function(){
			parentJqlxCode = grandparentJqlxCode ;
			grandparentJqlxCode = "" ;
			searType = "parent";
			level = level - 1;
			$.zhzats.welcome.jinri.initOrRefresh() ;
		});
		
    });
	
	function initOrRefresh(){
		
		if(jinri_table){
			//jinri_table.draw(true) ;
            jinri_table.destroy() ;
			//return ;
		}
		initTable() ;
	}
	
	function initTable(){

		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/zhzats/jinriInfo.action" ;
		tb.columnDefs = [
			     			{
			     				"targets": 0,
			          	    	//"width": "5%",
			          	    	"title": "警情类型",
			          	    	"className":"table-checkbox",
			          	    	"data": "name" ,
			          	    	"render": function (data, type, full, meta ) {
			          	    		return '<a class="jinri-table-jqlx" href="javascript:void(0)" code='+full.nameAdd1+' count='+full.count+'>'+data+'</a>';
			          	    	}
			     			},
			     			{
			     				"targets" : 1,
			     				//"width" : "12%",
			     				"title" : "本期",
			     				"data" : "count",
			     				"render" : function(data, type, full, meta) {
			     					return '<span class="font16">'+data+' 起</span>';
			     				}
			     			},
			     			{
			     				"targets" : 2,
			     				//"width" : "12%",
			     				"title" : "环比",
			     				"data" : "hbCount",
			     				"render" : function(data, type, full, meta) {
			     					return '<span class="glyphicon ' + getArrowClass(full.count-full.hbCount) + '"></span>';
			     				}
			     			},
			     			{
			     				"targets" : 3,
			     				//"width" : "12%",
			     				"title" : "环比",
			     				"data" : "hbCount",
			     				"render" : function(data, type, full, meta) {
			     					var hb = full.hbCount ;
			     					var bq = full.count ;
			     					if(!$.util.exist(hb)){
			     						hb = 0 ;
			     					}
			     					if(!$.util.exist(bq)){
			     						bq = 0 ;
			     					}
			     					var mn = Math.abs(bq - hb) ;
			     					var rs = "" ;
			     					if(mn == 0 || hb == 0){
			     						rs = "" ;
			     					}else{
			     						var temp = (mn/hb).toFixed(1)*100 ;
			     						var rs = temp.toString() + "%" ;
			     					}
			     					
			     					return '<span class="font16 ' + getPercentClass(bq,hb) + '">'+rs+'</span>';
			     				}
			     			}
			     		];
		tb.hideHead = true ; 
		tb.serverSide = false ;
		tb.lengthChange = false ;
        tb.pageLength = 8,
		tb.dom = 'rt<"dtBottom"p<"clear">>',
		//tb.pagingType = "simple" ;
		tb.paging = true;

		tb.paramsReq = function(d, pagerReq){
	
			var queryTime = $.zhzats.welcome.util.getTime();
			
			var data = {
				"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),
				"parentJqlxCode":parentJqlxCode,
				"searType":searType,
				"level":level
			};
	
			$.util.mergeObject(data, queryTime) ;
			var dataStr = $.util.toJSONString(data) ;
			d.queryStr = dataStr ;
			
		};
		tb.paramsResp = function(json) {
			var list = json.resultMap.list;
			json.data = list;
			json.recordsTotal = list.length ;
			json.recordsFiltered = list.length ;
			
			var total = 0 ;
			$.each(list, function(i, val){
				total += val.count ;
			});
			$("#jinri-total").text(total) ;
			
			grandparentJqlxCode = $.util.isBlank(json.resultMap.grandparentJqlxCode)?"":json.resultMap.grandparentJqlxCode ;
			level = json.resultMap.level;
			 
				if($.util.isBlank(grandparentJqlxCode)){
				$("#toUpPage").hide() ;
				level = 1;
				}else if(level==1){
					$("#toUpPage").hide() ;
				}else{
				$("#toUpPage").show() ;
			}
		};
		
		jinri_table = $("#jiri_table").DataTable(tb);
		
	}
	
	function getArrowClass(pOrM){
		if(pOrM>0){
			return "glyphicon-arrow-up color-red1" ;
		}
		if(pOrM==0){
			return "glyphicon-arrow-right color-green1" ;
		}
		if(pOrM<0){
			return "glyphicon-arrow-down color-green1" ;
		}
	}
	
	function getPercentClass(bq,hb){
		var pOrM = bq - hb ;
		if(pOrM>0 && hb != 0){
			return "color-red2" ;
		}
		if(pOrM==0 || hb == 0){
			return "glyphicon glyphicon-minus color-green1" ;
		}
		if(pOrM<0 && hb != 0){
			return "color-green1" ;
		}
	}

	jQuery.extend($.zhzats.welcome.jinri, { 
		initOrRefresh:initOrRefresh
	});
	
})(jQuery);	

$.zhzats.welcome.changliang = $.zhzats.changliang || {} ;
(function($){
	
	"use strict";
	var list = [];
	var radius = '65%';
	$(document).ready(function(){	
	
    });
	
	function initOrRefresh(){
		initOrRefreshChart() ;
	}
	
	function initOrRefreshChart(){
		var queryTime = $.zhzats.welcome.util.getTime();
		list = $.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect");
		var data = {
				"jqlxCodes":["01"],
				"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
			
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/changliangInfo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				refresh_chart("changliang_chart", successData.resultMap.list) ;
			}
		});
	}
	
	function refresh_chart(domId, data){

		var fmtData = fmt_Data(data) ;
		
		var chart = $.echart.getEchart(domId) ;
		if(list.length==1){
			centers = [['50%', '50%']];
			radius = '90%';
		}else{
			radius = '65%';
			var centers = [['20%', '35%'], ['50%', '35%'], ['80%', '35%'], ['20%', '83%'], ['50%', '83%'], ['80%', '83%']] ;
		}
		var series = [] ;
		$.each(fmtData, function(i, val){
			series.push(fmtSeries(val, centers[i])) ;
		});
		
		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.series = series ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		
		init_chart(domId, series) ;
	}
	
	function init_chart(domId, series){
		var option = {
			    tooltip : {
			        formatter: "{b} {c}"
			    },
			    series:series
			};
		
		$.echart.init(domId, "infographic", option, {
			initType:"dojo"
		});
	}
	
	function fmtSeries(data, center){
		
		var threshold = $.szpt.util.analyzeData.getPcsThreshold(data.name, timeTypeToThresholdType()) ;
		if($.util.exist(threshold)){
			
			var max = threshold.redHoldValue ;
			if(data.num > threshold.redHoldValue){
				max = data.num + 10 ;
			}
			var lineColor = [[threshold.blueHoldValue/max, "#66cbff"], [threshold.yellowHoldValue/max, "#f2cd00"], [threshold.orangeHoldValue/max, "#ff9626"], [1, "#fe1b49"]] ;
			var pointerColor="#66cbff";//蓝色
			if((threshold.blueHoldValue) <= (data.num) && (data.num) < (threshold.yellowHoldValue)){
				pointerColor="#f2cd00";//黄色
			}else if((threshold.yellowHoldValue) <= (data.num) && (data.num) < (threshold.orangeHoldValue)){
				pointerColor="#ff9626";//橘色
			}else if((threshold.orangeHoldValue) <= (data.num)){
				pointerColor="#fe1b49";//红色
			} 
		}
		var sr = {
	            name: data.name,
	            center: center,
	            type: 'gauge',
	            min: 0,
	            max: max,
	            splitNumber: max,
	            radius:radius , //半径
	            startAngle:180, //起始角度
	            endAngle:0, //起始角度
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    width: 7, //线宽
	                    color:lineColor
	                }
	            },
	            axisLabel:{
	            	textStyle:{
	            		color:"#fff",
	            		fontSize:10
	            			
	            	},
	            	formatter: function(v){ 
	            		switch (v){
	                        case threshold.blueHoldValue: return threshold.blueHoldValue;
	                        case threshold.yellowHoldValue: return threshold.yellowHoldValue;
	                        case threshold.orangeHoldValue: return threshold.orangeHoldValue;
	                        case threshold.redHoldValue: return threshold.redHoldValue;
	                        default: return '';
	            		}
	            	}
	            },
	            axisTick: {            // 坐标轴小标记
	                show:false
	            },
	            pointer:{
	            	width:5 //指针宽度
	            },
	            itemStyle:{
	            	normal:{
	            		color:pointerColor//指针的颜色
	            	}
	            },
	            splitLine: {           // 分隔线
	                length: 5,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    color: 'auto'
	                }
	            },
	            title : {
	            	show:true,
	            	offsetCenter:['-25%', '30%'],
	            	textStyle:{
	            		color:"#fff",
	            		fontSize:12
	            	}
	            },
	            detail : {
	            	 formatter:data.num,
	                 textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                     color: 'auto',
	                     fontWeight: 'bolder'
	                 },
	            	show:true,
	            	offsetCenter: ['25%', '23%']
	            },
	            data:[{value: data.num, name: data.name}]
	    } ;		
		
		return sr ;
	}
	
	function timeTypeToThresholdType(){
		var timeValueType = $.szpt.util.searchTime.getTimeValueType() ;
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.DAY.toLowerCase()){
			return $.common.PCS_THRESHOLD_TYPE_CONSTANT.DAY ;
		}
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.WEEK.toLowerCase()){
			return $.common.PCS_THRESHOLD_TYPE_CONSTANT.WEEK ;
		}
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.MONTH.toLowerCase()){
			return $.common.PCS_THRESHOLD_TYPE_CONSTANT.MONTH ;
		}
	}
	
	function fmt_Data(data){
		var rs = [] ;
		$.each(data, function(i, val){
			rs.push({
				name:val.name,
				num:parseInt(val.count, 10)
			});
		});
		return rs ;
	}
	
	jQuery.extend($.zhzats.welcome.changliang, { 
		initOrRefresh:initOrRefresh
	});
	
})(jQuery);	

$.zhzats.welcome.map = $.zhzats.map || {} ;
(function($){
	
	"use strict";
	
	$(document).ready(function(){	
	
		$(document).on("ifChanged", ".map-tuceng", function(e){
			var flag = $.icheck.isChecked(this) ;
			var val = $(this).attr("value") ;
			if(val=="pcs"){
				$.baseMap.showBianjie(flag) ;
			}
			if(val=="wg"){
				$.baseMap.showWange(flag) ;			
			}
			if(val=="sq"){
				$.baseMap.showSheQu(flag);
			}
		});
		
		$(document).on("ifChanged", ".map-pos", function(e){	
			
			initOrRefresh() ;
		});
		
    });
	
	function initOrRefresh(){
		$(".map-pos").each(function(i, val){
			var flag = $.icheck.isChecked(val) ;
			var vl = $(val).attr("value") ;
			if(vl=="jq"){
				initOrRefreshJqPos(flag) ;
			}
			if(vl=="aj"){
				initOrRefreshXsAjPos(flag) ;
			}
			if(vl=="xcll"){
				initOrRefreshXcllPos(flag) ;
			}
		});
	}
	
	function initOrRefreshJqPos(flag){
		
		if(!flag){
			$.baseMap.refresh_Jq_Pos([]) ;
			return ;
		}
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
			
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findJqPos.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				$.baseMap.refresh_Jq_Pos(successData.resultMap.list) ;
			}
		});
	}
	
	function initOrRefreshXsAjPos(flag){
		
		if(!flag){
			$.baseMap.refresh_Aj_Pos([]) ;
			return ;
		}
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
			
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findXsAjPos.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				$.baseMap.refresh_Aj_Pos(successData.resultMap.list) ;
			}
		});
	}
	
	function initOrRefreshXcllPos(flag){
		
		if(!flag){
			$.baseMap.refreshXcllHeat([]) ;
			return ;
		}
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var now = new Date() ;
		now.setHours(now.getHours() - 10) ;
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
			
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findXcllPos.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				$.baseMap.refreshXcllHeat(successData.resultMap.list) ;
			}
		});
	}
	
	jQuery.extend($.zhzats.welcome.map, { 
		initOrRefresh:initOrRefresh
	});
	
})(jQuery);

$.zhzats.welcome.faanchusuo = $.zhzats.faanchusuo || {} ;
(function($){
	
	"use strict";
	
	var parentAjTypeCode = "" ;
	var grandparentAjTypeCode = "" ;
	var ysNameToCode = {} ;
	
	$(document).ready(function(){	
		
	});
	
	function initOrRefresh(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),
			"parentAjTypeCode":parentAjTypeCode,
			"level":level
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findFaAnChuSuo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				initOrRefreshChart("faanchusuochart", successData.resultMap.list) ;
			}
		});
		
	}

	function initOrRefreshChart(domId, data){

		var fmtData = fmt_data(data) ;

		var colors = ["#8a6f4d", "#7d6196", "#4fcbd6",  "#ff1b49", "#dc69aa", "#4aabfd"] ;
		
		var series = [] ;
		var i = 0 ;
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
			        		
			        	}
			        }
			};
			if(i < colors.length){
				s.itemStyle.normal.color = colors[i] ;
			}
			series.push(s);
			i++ ;
		});
		
		var chart = $.echart.getEchart(domId) ;
		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.xAxis.data = fmtData.places ;
			opt.yAxis.data = fmtData.types ;
			opt.series = series ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		
		init_chart(domId, fmtData, series) ;
	}
	
	function init_chart(domId, fmtData, series){
		var option = {
			    title: {
			    	show:false,
			        text: '发案处所统计',
			        textStyle:{
			        	color:"#E0E0E3",
			        	fontSize:20
			        },
			        left: 'center'
			    },
			    tooltip: {
				        formatter: function (params) {
				        	return fmtData.places[params.value[0]] + "发生了" + fmtData.types[params.value[1]] + "案件" + params.value[2] + "起";
				        }
			    },
			    legend: {
			        data: ['数量'],
			        top:35 ,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        top:"0%", //图表整体的位置
			        bottom: '10%', //图表整体的位置
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        data: fmtData.places,
			        boundaryGap: false,
			        axisLabel:{
			        	textStyle:{
			        		color:"#dbdde1"
			        	}
			        },
			        splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#797d98',
			                type: 'solid'
			            }
			        },
			        axisLine: {
			            show: true,
			            lineStyle:{
			            	color:"#797d98",
			            	type:"solid"
			            }
			        }
			    },
			    yAxis: {
			        type: 'category',
			        data: fmtData.types,
			        axisLabel:{
			        	textStyle:{
			        		color:"#59a4c5"
			        	},
			        	formatter:function(value, index){
			        		return '<a href="www.baidu.com">'+value+'</a>';
			        	}
			        },
			        splitLine: {
			            show: true,
			            lineStyle: {
			                color: '#797d98',
			                type: 'solid'
			            }
			        },
			        axisLine: {
			            show: false
			        }
			    },
			    series: series
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
			
			$.echart.getEchart(domId).on("click", function(e){

			});
	}
	
	function fmt_data(data){
		
		ysNameToCode = {} ;
		var places = [] ;
		var types = [] ;
		var values = {} ;
		
		$.each(data, function(i, val){
			ysNameToCode[val.tagType] = val.tagTypeCode ;
			var place_index = $.inArray(val.tagValue, places) ;
			if(place_index<0){
				places.push(val.tagType) ;
				place_index = $.inArray(val.tagValue, places) ;
			}
			var type_index = $.inArray(val.tagType, types) ;
			if(type_index<0){
				types.push(val.tagValue) ;
				type_index = $.inArray(val.tagType, types) ;
				values[val.tagType] = [] ;
			}
			values[val.tagType].push([place_index,type_index, val.number]);
		});
		
		return {
			places:places,
			types:types,
			values:values
		};
	}
	
	jQuery.extend($.zhzats.welcome.faanchusuo, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.faanchusuo_hc = $.zhzats.faanchusuo_hc || {} ;
(function($){
	
	"use strict";
	var level;
	$(document).ready(function(){	
		level = 1;
		$(document).on("click", ".faanchusuo_hc-yName", function(){
			var name = $(this).text() ;
			var code = ysNameToCode[name] ;
			parentYsCode = code ;
			level = level + 1;
			$.zhzats.welcome.faanchusuo_hc.initOrRefresh() ;
		});
		
		$(document).on("click", "#faanchusuo_hc-toUpPage", function(){
			parentYsCode = grandparentYsCode ;
			grandparentYsCode = "" ;
			level = level-1;
			$.zhzats.welcome.faanchusuo_hc.initOrRefresh() ;
		});
	});
	
	var ysNameToCode = {} ;
	var parentYsCode = "" ;
	var grandparentYsCode = "" ;

	var chart ;
	
	function initOrRefresh(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),
			"parentAjTypeCode":parentYsCode,
			"level":level
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findFaAnChuSuo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				grandparentYsCode = $.util.isBlank(successData.resultMap.grandparentAjTypeCode)?"":successData.resultMap.grandparentAjTypeCode ;
				var grandparentsYsCode = $.util.isBlank(successData.resultMap.grandparentsAjTypeCode)?"":successData.resultMap.grandparentsAjTypeCode ;
				if(level > 1){
					$("#faanchusuo_hc-toUpPage").show() ;
				}
				else if($.util.isBlank(grandparentYsCode)){
					$("#faanchusuo_hc-toUpPage").hide() ;
					level = 1;
				}else{
					$("#faanchusuo_hc-toUpPage").show() ;
					
				}
				var list = successData.resultMap.list;
				if(list != null && list.length > 0){
					initOrRefreshChart("faanchusuochart", list) ;
				}else{
					level = level - 1 ;
					grandparentYsCode = grandparentsYsCode ;
					window.top.$.layerAlert.alert({msg:'无数据！',title:"温馨提示",icon : 5,time:2000});
				}
			}
		});
		
	}
	
	function initOrRefreshChart(domId, data){

		var fmtData = fmt_data(data) ;

		var series = [] ;

		$.each(fmtData.values, function(key, val){
			var s = {
		            data: val
	        };

			series.push(s);
		});

		if(chart){
			chart.destroy();
			return ;
		}
		
		init_chart(domId, fmtData, series) ;
	}
	
	function init_chart(domId, fmtData, series){
		
	    var chart = $('#' + domId).highcharts({
	        chart: {
	            type: 'bubble',
	            zoomType: 'xy',
	            backgroundColor: 'rgba(0,0,0,0)'
	        },
	        colors:["#8a6f4d", "#7d6196", "#4fcbd6",  "#ff1b49", "#dc69aa", "#4aabfd"],
	        title: {
	            text: ''
	        },
	        credits:{
	        	enabled:false
	        },
	        legend:{
	        	enabled:false
	        },
	        series: series,
	        yAxis:{
	        	categories:fmtData.types,
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
		        		return '<a href="javascript:void(0)" class="faanchusuo_hc-yName">'+this.value+'</a>';
		        	}
	        	},
	        	type:"category",
	
	        },
	        xAxis:{
	        	categories:fmtData.places,
	        	gridLineColor:"#32415a",
	        	lineColor:"#32415a",
	        	tickColor:"#32415a",
				labels:{
	        		style:{
	        			color:"#ccc"
					}
				}
	        },
	        tooltip:{
	        	formatter:function(){
	        		var y = this.y ;
	        		var yName = this.series.yAxis.categories[y];
	        		var num = this.point.z ;
	        		return num + "("+this.x+", "+yName+")" ;
	        	}
	        },
	        plotOptions: {
	            series: {
	                dataLabels: {
	                    enabled:true,
	                    format:series.data
	                }
	            }
	        }
	    });
	}
	
	function fmt_data(data){
		
		ysNameToCode = {} ;
		var places = [] ;
		var types = [] ;
		var values = {} ;
		
		$.each(data, function(i, val){
			ysNameToCode[val.tagType] = val.tagTypeCode ;
			var place_index = $.inArray(val.tagValue, places) ;
			if(place_index<0){
				places.push(val.tagValue) ;
				place_index = $.inArray(val.tagValue, places) ;
			}
			var type_index = $.inArray(val.tagType, types) ;
			if(type_index<0){
				types.push(val.tagType) ;
				type_index = $.inArray(val.tagType, types) ;
				values[val.tagType] = [] ;
			}
			values[val.tagType].push([place_index, type_index, val.number]);
		});
		
		return {
			places:places,
			types:types,
			values:values
		};
	}
	
	jQuery.extend($.zhzats.welcome.faanchusuo_hc, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.faanshiduan_hc = $.zhzats.faanshiduan_hc || {} ;
(function($){
	
	"use strict";
	
	var ysNameToCode = {} ;
	var parentYsCode = "" ;
	var grandparentYsCode = "" ;
	var level;
	$(document).ready(function(){	
		level = 1;
		$(document).on("click", ".faanshiduan_hc-yName", function(){
			var name = $(this).text() ;
			var code = ysNameToCode[name] ;
			parentYsCode = code ;
			level = level + 1;
			$.zhzats.welcome.faanshiduan_hc.initOrRefresh() ;
		});
		
		$(document).on("click", "#faanshiduan_hc-toUpPage", function(){
			parentYsCode = grandparentYsCode ;
			grandparentYsCode = "" ;
			level = level - 1; 
			$.zhzats.welcome.faanshiduan_hc.initOrRefresh() ;
		});
	});
	
	var chart ;
	
	function initOrRefresh(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect"),
			"parentAjTypeCode":parentYsCode,
			"level":level
		};
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context +'/zhzats/findFaAnShiDuan.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				grandparentYsCode = $.util.isBlank(successData.resultMap.grandparentAjTypeCode)?"":successData.resultMap.grandparentAjTypeCode ;
				var grandparentsYsCode = $.util.isBlank(successData.resultMap.grandparentsAjTypeCode)?"":successData.resultMap.grandparentsAjTypeCode ;
				if(level > 1){
					$("#faanshiduan_hc-toUpPage").show() ;
				}
				else if($.util.isBlank(grandparentYsCode)){
					$("#faanshiduan_hc-toUpPage").hide() ;
					level = 1;
				}else{
					$("#faanshiduan_hc-toUpPage").show() ;
				}
				var list = successData.resultMap.list;
				if(list != null && list.length > 0){
					initOrRefreshChart("faanshiduanchart", list) ;
				}else{
					level = level - 1 ;
					grandparentYsCode = grandparentsYsCode ;
					window.top.$.layerAlert.alert({msg:'无数据！',title:"温馨提示",icon : 5,time:2000});
				}
			}
		});
		
	}
	
	function initOrRefreshChart(domId, data){

		var fmtData = fmt_data(data) ;

		var series = [] ;

		$.each(fmtData.values, function(key, val){
			var s = {
		            data: val
	        };

			series.push(s);
		});

		if(chart){
			chart.destroy();
			return ;
		}
		
		init_chart(domId, fmtData, series) ;
	}
	
	function init_chart(domId, fmtData, series){
		
	    var chart = $('#' + domId).highcharts({
	        chart: {
	            type: 'bubble',
	            zoomType: 'xy',
	            backgroundColor: 'rgba(0,0,0,0)'
	        },
	        colors:["#8a6f4d", "#7d6196", "#4fcbd6",  "#ff1b49", "#dc69aa", "#4aabfd"],
	        title: {
	            text: ''
	        },
	        credits:{
	        	enabled:false
	        },
	        legend:{
	        	enabled:false
	        },
	        series: series,
	        yAxis:{
	        	categories:fmtData.types,
	        	gridLineColor:"#32415a",
	        	endOnTick:false,
	        	startOnTick:false,
	        	//max:(fmtData.types.length-1),
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
	            		
		        		return '<a href="javascript:void(0)" class="faanshiduan_hc-yName">'+this.value+'</a>';
		        	}
	        	},
	        	type:"category",
	
	        },
	        xAxis:{
	        	categories:fmtData.places,
	        	gridLineColor:"#32415a",
	        	lineColor:"#32415a",
	        	tickColor:"#32415a",
                labels:{
                    style:{
                        color:"#ccc"
                    }
                }
	        },
	        tooltip:{
	        	show:true,
	        	formatter:function(){
	        		var y = this.y ;
	        		var yName = this.series.yAxis.categories[y];
	        		var num = this.point.z ;
	        		return num + "("+this.x+", "+yName+")" ;
	        	}
	        },
	        plotOptions: {
	            series: {
	                dataLabels: {
	                    enabled:true,
	                    format:series.data
	                }
	            }
	        }
	    });
	}
	
	function fmt_data(data){
		
		ysNameToCode = {} ;
		var places = [] ;
		var types = [] ;
		var values = {} ;
		
		$.each(data, function(i, val){
			ysNameToCode[val.tagType] = val.tagTypeCode ;
			var place_index = $.inArray(val.tagValue, places) ;
			if(place_index<0){
				places.push(val.tagValue) ;
				place_index = $.inArray(val.tagValue, places) ;
			}
			var type_index = $.inArray(val.tagType, types) ;
			if(type_index<0){
				types.push(val.tagType) ;
				type_index = $.inArray(val.tagType, types) ;
				values[val.tagType] = [] ;
			}
			values[val.tagType].push([place_index, type_index, val.number]);
		});
		
		return {
			places:places,
			types:types,
			values:values
		};
	}
	
	jQuery.extend($.zhzats.welcome.faanshiduan_hc, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.wifiweilan_hc = $.zhzats.welcome.wifiweilan_hc || {} ;
(function($){
    "use strict";
    $(document).ready(function(){

    });

    function initOrRefresh(){
        var queryTime = $.zhzats.welcome.util.getTime();

        var data = {
            "pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect") //$.szpt.util.analyzeData.getPcsCodes()
        };

        $.util.mergeObject(data, queryTime) ;
        var dataStr = $.util.toJSONString(data) ;

        $.ajax({
            url:context + "/zhzats/wifiStatistics.action",
            data:dataStr,
            type:"post",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function(data){
                var list = data.resultMap.list ;
                refreshChart("wifiweilan", list) ;
            }
        });

    }

    function refreshChart(domId, data){
        var fmtData = fmt_data(data) ;
        initChart(domId, fmtData) ;
    }

    function initChart(domId, fmtData){

        $('#' + domId).highcharts({
            chart: {
                type: 'column'
            },
			//覆盖主题设置
            colors:[ "#18629f", "#b66497"],
            title: {
                text: 'WIFI围栏'
            },
            credits:{
                enabled:false
            },
            xAxis: {
                categories: fmtData.names
            },
            yAxis: {
                gridLineColor: '#3d466b',
                min: 0,
                title: {
                    text: '采集数量'
                },
                //显示总数
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'normal',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || '#fff',
                    }
                },

            },
            legend: {
                backgroundColor:'rgba(0,0,0,0)',
                borderColor: '#465068',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black'
                        }
                    }
                }
            },
            series: [{
                name: '采集数量',
                data: fmtData.caiji,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'transparent',
                borderColor: '#5093ca',
                borderWidth: 1,
                dataLabels: {enabled: true }
            },
                {
                    name: '五色人员',
                    data: fmtData.wuse,
                    borderColor: '#f08aca',
                    borderWidth: 1

                }]
        });
    }

    function fmt_data(data){

        var names = [] ;
        var caiji = [] ;
        var wuse = [] ;
        var map = {"三江派出所":"三江所","平桥派出所":"平桥所","长江派出所":"长江所","金竹派出所":"金竹所","黄河派出所":"黄河所","大兴派出所":"大兴所"};
        $.each(data, function(i, val){

            if($.inArray(val.name, names)==-1){

                names.push(map[val.name]) ;
                if(val["value"] == ""){
                    val["value"] = 0;
                }
                if(val["value_two"] == ""){
                    val["value_two"] = 0;
                }
                caiji.push(parseInt(val["value"], 10)) ;
                wuse.push(parseInt(val["value_two"], 10)) ;
            }
        });

        return {
            names : names,
            caiji : caiji,
            wuse : wuse
        } ;
    }

    jQuery.extend($.zhzats.welcome.wifiweilan_hc, {
        initOrRefresh:initOrRefresh
    });
})(jQuery);

$.zhzats.welcome.wifiweilan = $.zhzats.welcome.wifiweilan || {} ;
(function($){
	
	"use strict";

	$(document).ready(function(){	
		
	});
	
	function initOrRefresh(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect") //$.szpt.util.analyzeData.getPcsCodes()
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context + "/zhzats/wifiStatistics.action",
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				var list = data.resultMap.list ;
				refreshChart("wifiweilan", list) ;
			}
		});
	}
	
	function refreshChart(domId, data){
		var fmtData = fmt_data(data) ;
		var chart = $.echart.getEchart(domId) ;

		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.xAxis[0].data = fmtData.names ;
            opt.series[0].data = fmtData.wuse ;
			opt.series[1].data = fmtData.caiji ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		
		initChart(domId, fmtData);
	}
	
	function initChart(domId, fmtData){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			    	data:['五色人员','采集数量'],
			        top:0 ,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        top:"15%",
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : fmtData.names,
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	},
				        	margin:30
				        }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        }
			        }
			    ],
			    series : [
			        {
			        	barWidth : 30,//柱图宽度
			            name:'五色人员',
			            type:'bar',
			            stack: 'wifi',
		   	            itemStyle:{
		   	            	normal:{
		   	            		color:"#7d6196"
		   	            	}
		   	            },
			            label: {
			                normal: {
			                    show: true,
			                    position: 'insideTop',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
			            data:fmtData.wuse
			        },
			        {
			        	barMaxWidth: 30,//柱图宽度
			            name:'采集数量',
			            type:'bar',
			            stack: 'wifi',
		   	            itemStyle:{
		   	            	normal:{
		   	            		color:"#28acb1"
		   	            	}
		   	            },
			            label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
			            data:fmtData.caiji
			        }
			    ]
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
	}
	
	function fmt_data(data){
	
		var names = [] ;
		var caiji = [] ;
		var wuse = [] ;
		var map = {"三江派出所":"三江所","平桥派出所":"平桥所","长江派出所":"长江所","金竹派出所":"金竹所","黄河派出所":"黄河所","大兴派出所":"大兴所"};
		$.each(data, function(i, val){

			if($.inArray(val.name, names)==-1){

				names.push(map[val.name]) ;
				if(val["value"] == ""){
					val["value"] = 0;
				}
				if(val["value_two"] == ""){
					val["value_two"] = 0;
				}
				caiji.push(parseInt(val["value"], 10)) ;
				wuse.push(parseInt(val["value_two"], 10)) ;
			}
		});

		return {
			names : names,
			caiji : caiji,
			wuse : wuse
		} ;
	}
	
	jQuery.extend($.zhzats.welcome.wifiweilan, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);


$.zhzats.welcome.gaowei_hc = $.zhzats.welcome.gaowei_hc || {} ;
(function($){

    "use strict";

    $(document).ready(function(){

    });

    function initOrRefresh(){
        var queryTime = $.zhzats.welcome.util.getTime();

        var data = {
            "pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
        };

        $.util.mergeObject(data, queryTime) ;
        var dataStr = $.util.toJSONString(data) ;

        $.ajax({
            url:context + "/zhzats/gaoweiStatistics.action",
            data:dataStr,
            type:"post",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function(data){
                var list = data.resultMap.list ;
                refreshChart("gaoweichart", list) ;
            }
        });
    }

    function refreshChart(domId, data) {
        var fmtData = fmt_data(data);
        initChart(domId, fmtData) ;
    }

    function initChart(domId, fmtData){
        $('#' + domId).highcharts({
            chart: {
                type: 'column'
            },
            //覆盖主题设置
            colors:[ "#18629f", "#b66497"],
            title: {
                text: '高危人员统计'
            },
            credits:{
                enabled:false
            },
            xAxis: {
                categories: fmtData.names
            },
            yAxis: {
                gridLineColor: '#3d466b',
                allowDecimals:false,
                min: 0,
                title: {
                    text: '数量'
                },
				//显示总数
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'normal',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || '#fff'
                    }
                },

            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                borderColor: '#465068',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y+ '<br/>' +
                        '高危人总数: ' + this.point.stackTotal;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black'
                        }
                    }
                }
            },
            series: [{
                name: '原有高危人',
                data: fmtData.oldNum,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'transparent',
                borderColor: '#5093ca',
                borderWidth: 1
            },
                {
                    name: '新增高危人',
                    data: fmtData.newNum,
                    borderColor: '#f08aca',
                    borderWidth: 1

                }]
        });
	}

    function fmt_data(data){

        var names = [] ;
        var totalNum = [] ;
        var newNum = [] ;
        var oldNum = [] ;

        var map = {"三江派出所":"三江所","平桥派出所":"平桥所","长江派出所":"长江所","金竹派出所":"金竹所","黄河派出所":"黄河所","大兴派出所":"大兴所"};
        $.each(data, function(i, val){
            if( val.name == null || val.name == "" ){
                return ;
            }
            if($.inArray(val.name, names)){
                names.push(map[val.name]) ;
                if(val["value"] == ""){
                    val["value"] = 0;
                }
                if(val["value_two"] == ""){
                    val["value_two"] = 0;
                }
                var countSum = parseInt(val["value"], 10) + parseInt(val["value_two"], 10);
                totalNum.push(countSum) ;
                newNum.push(parseInt(val["value_two"], 10)) ;
                oldNum.push(parseInt(val["value"], 10)) ;

            }
        });

        return {
            names : names,
            totalNum : totalNum,
            newNum : newNum,
			oldNum:oldNum
        } ;
    }

    jQuery.extend($.zhzats.welcome.gaowei_hc, {
        initOrRefresh:initOrRefresh
    });

})(jQuery);

$.zhzats.welcome.gaowei = $.zhzats.welcome.gaowei || {} ;
(function($){
	
	"use strict";

	$(document).ready(function(){	
		
	});
	
	function initOrRefresh(){
		
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context + "/zhzats/gaoweiStatistics.action",
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				var list = data.resultMap.list ;
				refreshChart("gaoweichart", list) ;
			}
		});
	}
	
	function refreshChart(domId, data){
		var fmtData = fmt_data(data) ;
		
		var chart = $.echart.getEchart(domId) ;
		if(chart){
			var opt = $.echart.getOption(domId) ;
			opt.xAxis[0].data = fmtData.names ;
			opt.series[0].data = fmtData.one ;
			opt.series[1].data = fmtData.two ;
			$.echart.setOption(domId, opt, true) ;
			return ;
		}
		
		initChart(domId, fmtData);
	}
	
	function initChart(domId, fmtData){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			    	data:['当日新增','总数'],
			        top:0 ,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        top:"15%",
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : fmtData.names,
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        }
			        }
			    ],
			    series : [
			        {
			        	barMaxWidth: 30,//柱图宽度
			            name:'总数',
			            type:'bar',
			            stack: 'zaikong',
		   	            itemStyle:{
		   	            	normal:{
		   	            		color:"#7d6196"
		   	            	}
		   	            },
			            label: {
			                normal: {
			                    show: true,
			                    position: 'insideTop',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
			            data:fmtData.one
			        },
			        {
			        	barMaxWidth: 30,//柱图宽度
			            name:'当日新增',
			            type:'bar',
			            stack: 'zaikong',
		   	            itemStyle:{
		   	            	normal:{
		   	            		color:"#28acb1"
		   	            	}
		   	            },
		   	            barWidth:30,
			            label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
			            data:fmtData.two
			        }
			    ]
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;	
	}
	
	function fmt_data(data){
		
		var names = [] ;
		var one = [] ;
		var two = [] ;
		var map = {"三江派出所":"三江所","平桥派出所":"平桥所","长江派出所":"长江所","金竹派出所":"金竹所","黄河派出所":"黄河所","大兴派出所":"大兴所"};
		$.each(data, function(i, val){
			if( val.name == null || val.name == "" ){
				return ;
			}
			if($.inArray(val.name, names)){
				names.push(map[val.name]) ;
				if(val["value"] == ""){
					val["value"] = 0;
				}
				if(val["value_two"] == ""){
					val["value_two"] = 0;
				}
				var countSum = parseInt(val["value"], 10) + parseInt(val["value_two"], 10);
				one.push(countSum) ;
				two.push(parseInt(val["value_two"], 10)) ;
			}
		});
		
		return {
			names : names,
			one : one,
			two : two
		} ;
	}
	
	jQuery.extend($.zhzats.welcome.gaowei, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.liudong_hc = $.zhzats.welcome.liudong_hc || {} ;
(function($){
    "use strict";
    $(document).ready(function(){

    });

    function initOrRefresh(){
        var queryTime = $.zhzats.welcome.util.getTime();

        var data = {
            "pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
        };

        $.util.mergeObject(data, queryTime) ;
        var dataStr = $.util.toJSONString(data) ;

        $.ajax({
            url:context + "/zhzats/findFloatingPopInfo.action",
            data:dataStr,
            type:"post",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function(data){
                var list = data.resultMap.list ;
                refreshChart("liudongchart", list) ;
            }
        });
    }
    function refreshChart(domId, data){
        var fmtData = format_data(data) ;
       initChart(domId, fmtData) ;
    }

    function initChart(domId, fmtData){
        $('#'+ domId).highcharts({
            chart: {
                type: 'bar'
            },
            //覆盖主题设置
            colors:["#18629f", "#9579dc"],
            title: {
                text: '流动人口变化'
            },
            xAxis: {
                categories: fmtData.names,
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '数量',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                borderColor: '#465068',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                valueSuffix: ' 人'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '本期',
                data: fmtData.series
            }, {
                name: '环比',
                data: fmtData.hbSeries
            }]
        });
    }

    function format_data(data){

        var names = [] ;
        var series = [] ;
        var hbSeries = [] ;

        $.each(data, function(i, val){
            if($.inArray(val.name, names)<0){
                names.push(val.name) ;
            }
            series.push(val.floatingNumSum);
            hbSeries.push(val.hbFloatingNumSum);
        });

        $.each(pcs_name_info, function(key, val){
			if($.inArray(key, names)<0){
                names.push(key);
                series.push(0) ;
                hbSeries.push(0) ;
			}
		});

        return {
            names:names,
            series:series,
            hbSeries:hbSeries
        };
    }

    jQuery.extend($.zhzats.welcome.liudong_hc, {
        initOrRefresh:initOrRefresh
    });
})(jQuery);

$.zhzats.welcome.liudong = $.zhzats.welcome.liudong || {} ;
(function($){
	"use strict";
	$(document).ready(function(){	
		
	});
	
	function initOrRefresh(){
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context + "/zhzats/findFloatingPopInfo.action",
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				var list = data.resultMap.list ;
				refreshChart("liudongchart", list) ;
			}
		});
	}
	
	function refreshChart(domId, data){
		var fmtData = format_data(data) ;
		
	    var series = [
				        {
				        	barMaxWidth: 30,//柱图宽度
				            name: '本期',
				            type: 'bar',
			   	            itemStyle:{
			   	            	normal:{
			   	            		color:"#7798BF"
			   	            	}
			   	            },
			   	            label: {
				                normal: {
				                    show: true,
				                    position: 'right',
				                    textStyle:{
				                    	fontSize:10
				                    }
				                }
				            },
				            data: fmtData.series,
				        },
				        {
				        	barMaxWidth: 30,//柱图宽度
				            name: '环比',
				            type: 'bar',
			   	            itemStyle:{
			   	            	normal:{
			   	            		color:"#4fcbd6"
			   	            	}
			   	            },
				            label: {
				                normal: {
				                    show: true,
				                    position: 'right',
				                    textStyle:{
				                    	fontSize:10
				                    }
				                }
				            },
				            data: fmtData.hbSeries
				        }
			] ;
			
			var chart = $.echart.getEchart(domId) ;

			if(chart){
				var opt = $.echart.getOption(domId) ;
				opt.series = series ;
				opt.yAxis[0].data = fmtData.names ;
				$.echart.setOption(domId, opt, true) ;
				return ;
			}
			
			initChart(domId, fmtData.names, series) ;
	}
	
	function initChart(domId, names, series){
		var option = {
			    title: {
			        text: '人口流动变化',
			        //  subtext: '数据来自网络',
			        textStyle:{
			        	color:"#E0E0E3",
			        	fontSize:20
			        },
			        left: 'center'
			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: ['本期', '环比'],
			        top:35 ,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'value',
			        nameTextStyle:{
			        	color:"#E0E0E3"
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:"#292e36"
			        	}
			        },
			        axisLabel:{
			        	textStyle:{
			        		color:"#E0E0E3"
			        	}
			        },
			        boundaryGap: [0, 0.01]
			    },
			    yAxis: {
			        type: 'category',
			        nameTextStyle:{
			        	color:"#E0E0E3"
			        },
			        splitLine:{
			        	show:false,
			        },
			        axisLabel:{
			        	textStyle:{
			        		color:"#E0E0E3"
			        	}
			        },
			        data: names
			    },
			    series: series
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
	}
	
	function format_data(data){

		var names = [] ;
		var series = [] ;
		var hbSeries = [] ;
		
		$.each(data, function(i, val){
			if($.inArray(val.name, names)<0){
				names.push(val.name) ;
			}
			series.push(val.floatingNumSum);
			hbSeries.push(val.hbFloatingNumSum);
		});
		
		return {
			names:names,
			series:series,
			hbSeries:hbSeries
		};
	}
	
	jQuery.extend($.zhzats.welcome.liudong, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.pancha_hc = $.zhzats.welcome.pancha_hc || {} ;
(function($){

    "use strict";
    $(document).ready(function(){

    });

    function initOrRefresh(){
        var queryTime = $.zhzats.welcome.util.getTime();

        var data = {
            "pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
        };
        
        if(data.pcsCodes!=null && data.pcsCodes.length>1){
        	data.pcsCodes.push("5201995C0000") ;
        }

        $.util.mergeObject(data, queryTime) ;
        var dataStr = $.util.toJSONString(data) ;

        $.ajax({
            url:context + "/zhzats/findPanCha.action",
            data:dataStr,
            type:"post",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function(data){
                var list = data.resultMap.list ;
                refreshChart("container-pancha", list) ;
            }
        });
    }

    function refreshChart(domId, data){
        var fmtData = format_data(data) ;

        initChart(domId, fmtData) ;
    }

    function initChart(domId, fmtData){
        $('#'+domId).highcharts({
            chart: {
                type: 'bar'
            },
            colors:["#18629f", "#9579dc"],
            title: {
                text: '盘查情况'
            },
            xAxis: {
                categories: fmtData.names,
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '数量',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                borderColor: '#465068',
                borderWidth: 1,
                shadow: false
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '新增盘查人员',
                data: fmtData.series1,
                tooltip: {
                    valueSuffix: '人'
                },
            }, {
                name: '新增车辆',
                data: fmtData.series,
                tooltip: {
                    valueSuffix: '辆'
                },
            }]
        });
    }

    function format_data(data){

        var names = [] ;
        var series = [] ;
        var series1 = [] ;

        $.each(data, function(i, val){
            if($.inArray(val.name, names)<0){
                names.push(val.name) ;
            }
            series.push(val.newCarSum + val.newCarNotKySum);
            series1.push(val.newManpowerSum);
        });

        $.each(pcs_name_info, function(key, val){
            if($.inArray(key, names)<0){
                names.push(key);
                series.push(0) ;
                series1.push(0) ;
            }
        });
        
        if($.inArray("巡（特）大队", names)<0){
            names.push("巡（特）大队");
            series.push(0) ;
            series1.push(0) ;
        }

        return {
            names:names,
            series:series,
            series1:series1
        };
    }

    jQuery.extend($.zhzats.welcome.pancha_hc, {
        initOrRefresh:initOrRefresh
    });
})(jQuery);

$.zhzats.welcome.pancha = $.zhzats.pancha || {} ;
(function($){
	
	"use strict";
	$(document).ready(function(){	
		
	});
	
	function initOrRefresh(){
		var queryTime = $.zhzats.welcome.util.getTime();
		
		var data = {
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context + "/zhzats/findPanCha.action",
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				var list = data.resultMap.list ;
				refreshChart("container-pancha", list) ;
			}
		});
	}
	
	function refreshChart(domId, data){
		var fmtData = format_data(data) ;
		
	    var series = [
				        {
				        	barMaxWidth: 30,//柱图宽度
				            name: '新增车辆',
				            type: 'bar',
			   	            itemStyle:{
			   	            	normal:{
			   	            		color:"#7798BF"
			   	            	}
			   	            },
			   	            label: {
				                normal: {
				                    show: true,
				                    position: 'right',
				                    textStyle:{
				                    	fontSize:10
				                    }
				                }
				            },
				            data: fmtData.series,
				        },
				        {
				        	barMaxWidth: 30,//柱图宽度
				            name: '新增人员',
				            type: 'bar',
			   	            itemStyle:{
			   	            	normal:{
			   	            		color:"#4fcbd6"
			   	            	}
			   	            },
			   	            label: {
				                normal: {
				                    show: true,
				                    position: 'right',
				                    textStyle:{
				                    	fontSize:10
				                    }
				                }
				            },
				            data: fmtData.series1
				        }
			] ;
			
			var chart = $.echart.getEchart(domId) ;

			if(chart){
				var opt = $.echart.getOption(domId) ;
				opt.series = series ;
				opt.yAxis[0].data = fmtData.names ;
				$.echart.setOption(domId, opt, true) ;
				return ;
			}
			
			initChart(domId, fmtData.names, series) ;
	}
	
	function initChart(domId, names, series){
		var option = {
			    title: {
			        text: '盘查情况',
			        //  subtext: '数据来自网络',
			        textStyle:{
			        	color:"#E0E0E3",
			        	fontSize:20
			        },
			        left: 'center'
			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: ['新增车辆', '新增人员'],
			        top:35 ,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'value',
			        nameTextStyle:{
			        	color:"#E0E0E3"
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:"#292e36"
			        	}
			        },
			        axisLabel:{
			        	textStyle:{
			        		color:"#E0E0E3"
			        	}
			        },
			        boundaryGap: [0, 0.01]
			    },
			    yAxis: {
			        type: 'category',
			        nameTextStyle:{
			        	color:"#E0E0E3"
			        },
			        splitLine:{
			        	show:false,
			        },
			        axisLabel:{
			        	textStyle:{
			        		color:"#E0E0E3"
			        	}
			        },
			        data: names
			    },
			    series: series
			};

			$.echart.init(domId, "infographic", option, {
				initType:"dojo"
			}) ;
	}
	
	function format_data(data){

		var names = [] ;
		var series = [] ;
		var series1 = [] ;
		
		$.each(data, function(i, val){
			if($.inArray(val.name, names)<0){
				names.push(val.name) ;
			}
			series.push(val.newCarSum + val.newCarNotKySum);
			series1.push(val.newManpowerSum);
		});
		
		return {
			names:names,
			series:series,
			series1:series1
		};
	}
	
	jQuery.extend($.zhzats.welcome.pancha, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);

$.zhzats.welcome.dafang = $.zhzats.welcome.dafang || {} ;
(function($){
	
	"use strict";
	
	var fmted_text_data = {} ;
	
	var barOrLine = "line";
	
	$(document).ready(function(){
		
		$(document).on("click", "#dafang-export", function(){
			exportText();
		});
		
	});	
	
	function exportText(){
		
		var data = {
			imgbase64:$.echart.getBase64Str("dafangChart",$.echart.getDataURL("dafangChart"),null)	
		}
		
		$.util.mergeObject(data, fmted_text_data) ;
		var dataStr = $.util.toJSONString(data) ;
		
		var form = $.util.getHiddenForm(context +'/zhzats/daFangGuanKongWordExport.action', {queryStr:dataStr});
		$.util.subForm(form);
	}
	
	function initOrRefresh(){
		var queryTime = $.zhzats.welcome.util.getTime();
		var list = $.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect");
		if(list.length==1){
			barOrLine = "bar";
		}else{
			barOrLine = "line";
		}
		var data = {
			"jqlxCodes": [],
			"pcsCodes":$.szpt.util.analyzeData.getSeletedByPcsSelect("#pcsSelect")
		};
		
		$.util.mergeObject(data, queryTime) ;
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url:context + "/zhzats/findDaFangGuanKong.action",
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				var data = data.resultMap ;
				refreshText(data) ;
				refreshChart_hc("dafangChart", data) ;
			}
		});
	}
	
	function refreshText(data){
		var fmtData = fmt_text(data) ;
		fmted_text_data = fmtData ;
		fmtTextToVeiw(fmtData) ;
	}
	
	function fmtTextToVeiw(fmtData){
		$("#dafang-startDay").text(fmtData.startDay) ;
		$("#dafang-endDay").text(fmtData.endDay) ;
		$("#dafang-jqTotal").text(fmtData.jqTotal + "起，") ;
		$("#dafang-jqTotalHB").text(fmtData.jqTotalHB + "起，") ;
		$("#dafang-jqTotalPercent").text(percent(fmtData.jqTotal, fmtData.jqTotalHB)) ;
		
		$("#dafang-xingshiJq").text(fmtData.xingshiTotal + "起，") ;
		$("#dafang-xingshiJqHB").text(fmtData.xingshiTotalHB + "起，") ;
		$("#dafang-xingshiJqPercent").text(percent(fmtData.xingshiTotal, fmtData.xingshiTotalHB)) ;
		
		$("#dafang-zhianJq").text(fmtData.zhianTotal + "起，") ;
		$("#dafang-zhianJqHB").text(fmtData.zhianTotalHB + "起，") ;
		$("#dafang-zhianJqPercent").text(percent(fmtData.zhianTotal, fmtData.zhianTotalHB)) ;
		
		$("#dafang-zhianJq").text(fmtData.zhianTotal + "起，") ;
		$("#dafang-zhianJqHB").text(fmtData.zhianTotalHB + "起，") ;
		$("#dafang-zhianJqPercent").text(percent(fmtData.zhianTotal, fmtData.zhianTotalHB)) ;
		
		$("#dafang-qitaJq").text(fmtData.qitaTotal + "起，") ;
		if(fmtData.qitaTotalHB == 0){
			$("#dafang-qitaJqHB").text(fmtData.qitaTotalHB + "起。") ;
		}else{
			$("#dafang-qitaJqHB").text(fmtData.qitaTotalHB + "起,") ;
		}
		
		$("#dafang-qitaJqPercent").text(percent(fmtData.qitaTotal, fmtData.qitaTotalHB)) ;
		
		$("#dafang-pancha_ren").text(fmtData.pancha_ren_total + "人，") ;
		$("#dafang-pancha_renHB").text(fmtData.pancha_ren_total_hb + "人，") ;
		$("#dafang-pancha_renPercent").text(percent(fmtData.pancha_ren_total, fmtData.pancha_ren_total_hb)) ;
		
		$("#dafang-pancha_che").text(fmtData.pancha_che_total + "辆，") ;
		$("#dafang-pancha_cheHB").text(fmtData.pancha_che_total_hb + "辆，") ;
		$("#dafang-pancha_chePercent").text(percent(fmtData.pancha_che_total,fmtData.pancha_che_total_hb)) ;
		
		$("#dafang-pancha_fche").text(fmtData.pancha_fche_total + "辆，") ;
		if(fmtData.pancha_fche_total_hb == 0){
			$("#dafang-pancha_fcheHB").text(fmtData.pancha_fche_total_hb + "辆。") ;
		}else{
			$("#dafang-pancha_fcheHB").text(fmtData.pancha_fche_total_hb + "辆，") ;
		}
		$("#dafang-pancha_fchePercent").text(percent(fmtData.pancha_fche_total,fmtData.pancha_fche_total_hb)) ;
		
		$("#dafang-gaofa_pcs").text(fmtData.gaofa_pcs) ;
		$("#dafang-gaofa_jqlx").text(fmtData.gaofa_jqlx) ;
		$("#dafang-gaofa_shiduan").text(fmtData.gaofa_shi_duan) ;
		
		$("#dafang-panchalidu").text(fmtData.pancha_li_du) ;
	}
	
	function percent(total,total_hb){
		var minus = Math.abs(total - total_hb) ;
		var mn_tp = total_hb ;
		var percent="";
		if(total > total_hb && total_hb != 0){
			percent = "上升"+(minus/total_hb).toFixed(1)*100+"%；";
			
		}
		if(total == total_hb || total_hb != 0){
			percent="持平；";
		}
		if(total < total_hb && total_hb != 0){
			percent = "下降"+(minus/total_hb).toFixed(1)*100+"%；";
		}
		if(total_hb == 0){
			percent="";
		}
		return  percent;
	}
	
	
	function fmt_text(data){
		var queryTime = $.zhzats.welcome.util.getTime();
		var startDay = $.date.timeToStr(queryTime.startTime, "yyyy年MM月dd日") ;
		var endDate = new Date(queryTime.endTime) ;
		endDate.setDate(endDate.getDate() - 1) ;
		var endDay = $.date.dateToStr(endDate, "yyyy年MM月dd日") ;
		
		var jqTotal = 0 ;
		var jqTotalHB = 0 ;
		var jqTotal_hb_text = "上升" ;
		$.each(data.jqList, function(i, val){
			jqTotal += val.count ;
			jqTotalHB +=val.hbCount ;
		});
		if(jqTotal<jqTotalHB){
			jqTotal_hb_text = "下降" ;
		}
		
		var pancha_ren_total = 0 ;
		var pancha_ren_total_hb = 0 ;
		var pancha_ren_total_hb_text = "上升" ;
		
		var pancha_che_total = 0 ;
		var pancha_che_total_hb = 0 ;
		var pancha_che_total_hb_text = "上升" ;

		var pancha_fche_total = 0 ;
		var pancha_fche_total_hb = 0 ;
		var pancha_fche_total_hb_text = "上升" ;
		
		$.each(data.panchaList, function(i, val){
			pancha_ren_total += val.newManpowerSum ;
			pancha_ren_total_hb += val.newManpowerSumHB ;
			
			pancha_che_total += val.newCarSum ;
			pancha_che_total_hb += val.newCarSumHB ;

			pancha_fche_total += val.newCarNotKySum ;
			pancha_fche_total_hb += val.newCarNotKySumHB ;
		});
		
		if(pancha_ren_total<pancha_ren_total_hb){
			pancha_ren_total_hb_text = "下降" ;
		}
		if(pancha_che_total<pancha_che_total_hb){
			pancha_che_total_hb_text = "下降" ;
		}
		if(pancha_fche_total<pancha_fche_total_hb){
			pancha_fche_total_hb_text = "下降" ;
		}
		
		var pancha_li_du_Arr = [] ;
		$.each(data.panchaList, function(i, val){
			var num = val.newCarSum + val.newCarNotKySum+val.newManpowerSum;
			if(num==0){
				return false ;
			}
			
			if(i>2){
				return false ;
			}
			
			pancha_li_du_Arr.push(val.name) ;
		});
		
		var pancha_li_du = pancha_li_du_Arr.join("、") ;
		return {
			startDay:startDay,
			endDay:endDay,
			jqTotal:jqTotal,
			jqTotalHB:jqTotalHB,
			jqTotal_hb_text:jqTotal_hb_text,
			jqTotal_hb_percent:minusPercent(jqTotal, jqTotalHB),
			xingshiTotal:data.xingshiTotal,
			xingshiTotalHB:data.xingshiTotalHB,
			xingshiTotal_hb_text:(data.xingshiTotal<data.xingshiTotalHB)?"下降":"上升",
			xingshiTotal_hb_percent:minusPercent(data.xingshiTotal, data.xingshiTotalHB),
			zhianTotal:data.zhianTotal,
			zhianTotalHB:data.zhianTotalHB,
			zhianTotal_hb_text : (data.zhianTotal<data.zhianTotalHB)?"下降":"上升",
			zhianTotal_hb_percent : minusPercent(data.zhianTotal, data.zhianTotalHB),
			qitaTotal:data.qitaTotal,
			qitaTotalHB:data.qitaTotalHB,
			qitaTotal_hb_text : (data.qitaTotal<data.qitaTotalHB)?"下降":"上升",
			qitaTotal_hb_percent : minusPercent(data.qitaTotal, data.qitaTotalHB),
			
			pancha_ren_total : pancha_ren_total,
			pancha_ren_total_hb : pancha_ren_total_hb,
			pancha_ren_total_hb_text:pancha_ren_total_hb_text,
			pancha_ren_total_hb_percent : minusPercent(pancha_ren_total, pancha_ren_total_hb),
			pancha_che_total : pancha_che_total,
			pancha_che_total_hb : pancha_che_total_hb,
			pancha_che_total_hb_text:pancha_che_total_hb_text,
			pancha_che_total_hb_percent : minusPercent(pancha_che_total, pancha_che_total_hb),
			pancha_fche_total : pancha_fche_total,
			pancha_fche_total_hb : pancha_fche_total_hb,
			pancha_fche_total_hb_text:pancha_fche_total_hb_text,
			pancha_fche_total_hb_percent : minusPercent(pancha_fche_total, pancha_fche_total_hb),
			
			gaofa_pcs:data.jqList.length==0?"":data.jqList[0].name,
			gaofa_jqlx:data.jqlistByJqlx.length==0?"":data.jqlistByJqlx[0].name,
			gaofa_shi_duan:data.shiduanList.length==0?"":data.shiduanList[0].name,
			pancha_li_du:pancha_li_du
		};
	}
	
	function minusPercent(me, mn){
		var minus = Math.abs(me-mn) ;
		var mn_tp = mn ;
		if(mn_tp==0){
			mn_tp=1;
		}
		return (minus/mn_tp).toFixed(1) ;
	}

    function refreshChart_hc(domId, data){
        var fmtData = fmt_data(data) ;
        initChart_hc(domId, fmtData) ;
    }

    function initChart_hc(domId, fmtData){

        $('#' + domId).highcharts({
            chart: {
            },
            colors: [ "#4fcbd6", "#18629f", "#ff9900", "#fff","#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
            credits:{
                enabled:false
            },
            title: {
                text: null
            },
            xAxis: {
                categories: fmtData.names
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                borderColor: '#465068',
                borderWidth: 1,
                shadow: false
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
			yAxis:[
				{
                    min: 0,
                    title: {
                        text: '数量'
                    }
				},
				{
                    min: 0,
                    title: {
                        text: '盘查数量'
                    },
                    opposite:true
				}
			],
            series: [{
                type: 'column',
                name: '警情数量',
                data: fmtData.series
            }, {
                type: 'column',
                name: '警情环比数量',
                data: fmtData.hbSeries
            },
                {
                    type: 'spline',
                    name: '街面盘查数量',
                    data: fmtData.panchaSeries,
                    yAxis:1,
                    marker: {
                        lineWidth: 2,
                        lineColor: Highcharts.getOptions().colors[3],
                        fillColor: '#ff9900'
                    }
                }]
        });
    }
	
	function refreshChart(domId, data){
		var fmtData = fmt_data(data) ;
	    var series = [
	  		        {
	  		        	barMaxWidth: 40,//柱图宽度
	  		            name:'警情数量',
	  		            type:'bar',
	  	   	            itemStyle:{
	  	   	            	normal:{
	  	   	            		color:"#4fcbd6"
	  	   	            	}
	  	   	            },
		  	   	       label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
	  		            data:fmtData.series
	  		        },
	  		        {
	  		        	barMaxWidth: 40,//柱图宽度
	  		            name:'警情环比数量',
	  		            type:'bar',
	  	   	            itemStyle:{
	  	   	            	normal:{
	  	   	            		color:"#18629f"
	  	   	            	}
	  	   	            },
		  	   	       label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
	  		            data:fmtData.hbSeries
	  		        },
	  		        {
	  		        	barMaxWidth: 40,//柱图宽度
	  		            name:'街面盘查数量',
	  		            type:barOrLine,
	  	   	            itemStyle:{
	  	   	            	normal:{
	  	   	            		color:"#ff9900"
	  	   	            	}
	  	   	            },
		  	   	       label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    textStyle:{
			                    	fontSize:10
			                    }
			                }
			            },
	  		            lineStyle:{
	  	   	            	normal:{
	  	   	            		color:"#ff9900"
	  	   	            	}
	  	   	            },		            
	  		            yAxisIndex: 1,
	  		            data:fmtData.panchaSeries
	  		        }
	  		] ;
	  			
	  		var chart = $.echart.getEchart(domId) ;

	  		if(chart){
	  			$.echart.refreshSeries(domId, series) ;
	  			var opt = $.echart.getOption(domId) ;
	  			opt.series = series ;
	  			opt.xAxis[0].data = fmtData.names ;
	  			$.echart.setOption(domId, opt, true) ;
	  			return ;
	  		}
	  	
	  		initChart(domId, fmtData.names, series) ;
	}

	function initChart(domId, names, series){
		var option = {
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data:['警情数量','警情环比数量','街面盘查数量'],
			        bottom:10,
			        left: 'right',
			        textStyle: {
			            color: '#E0E0E3'
			        }
			    },
			    xAxis: [
			        {
			            type: 'category',
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        },		
				        splitLine:{
				        	show: true,
				        	lineStyle:{
				        		color:"#292e36"
				        	}
				        },
			            data: names
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '警情数量',
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLine: {
				        	show: true,
				        	lineStyle:{
				        		color:"#292e36"
				        	}
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        }
			        },
			        {
			            type: 'value',
			            name: '盘查数量',
				        nameTextStyle:{
				        	color:"#E0E0E3"
				        },
				        splitLine:{
				        	show:false,
				        },
				        axisLine: {
				        	show: true,
				        	lineStyle:{
				        		color:"#292e36"
				        	}
				        },
				        axisLabel:{
				        	textStyle:{
				        		color:"#E0E0E3"
				        	}
				        }
			        }
			    ],
			    series:series
		};
		
		$.echart.init(domId, "infographic", option, {
			initType:"dojo"
		}) ;
	}
	
	function fmt_data(data){
		
		var names = [] ;
		var series = [] ;
		var hbSeries = [] ;
		var panchaSeries = [] ;
		
		var jqList = data.jqList ;
		var panchaList = data.panchaList;

		$.each(jqList, function(i, val){
			if($.inArray(val.name, names)<0){
				names.push(val.name) ;
			}
			series.push(val.count) ;
			hbSeries.push(val.hbCount) ;
			
			$.each(panchaList, function(j, val1){
				if(val1.name==val.name){
					panchaSeries.push(val1.newCarSum + val1.newCarNotKySum+val1.newManpowerSum) ;
				}
			});
		});
		
		return {
			names:names,
			series:series,
			hbSeries:hbSeries,
			panchaSeries:panchaSeries
		};
	}
	
	jQuery.extend($.zhzats.welcome.dafang, {
		initOrRefresh:initOrRefresh
	});
})(jQuery);