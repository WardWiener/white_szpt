<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/highRiskPersonAlert/common/echarts.js"></script>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts-more.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
 <!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">

<div id="c-center-right-content">

<ol class="breadcrumb m-ui-breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="#">高危人群分析</a></li>
</ol>


<div class="m-ui-title1"><h1>高危人群分析</h1></div>
<div class="row">
	<div class="col-xs-3">
		<button id="recentlyDay"class="btn btn-primary btn-sm selectTime">近一天</button>
		<button id="recentlyWeek"class="btn btn-primary btn-sm selectTime">近一周</button>
		<button id="recentlyMonth"class="btn btn-primary btn-sm selectTime">近一月</button>
		<button id="recentlyDiy"class="btn btn-primary btn-sm selectTime">自定义</button>
	</div>
	<div id="searchRange" class="dateRange col-xs-6 searchRange" style="display: none;">
		<div class="col-xs-2">
			<label class="control-label">自定义时间：</label>
		</div>
			<input type="hidden" id="dtfmt" class="dateFmt"
				value="info@yyyy-MM-dd HH:mm:ss" />
			<div class="col-xs-4 input-group">
				<div class="input-group" style="margin-right: 10px;">
					<input type="text" id="fixed_start"
						class="laydate-start form-control input-sm"
						readonly="readonly"> <span
						class="laydate-start-span input-group-addon m-ui-addon">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
					</span>
				</div>
			</div>
		<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
			<div class="col-xs-4 input-group">
				<div class="input-group" style="padding-left: 5px;">
					<input type="text" id="fixed_end"
						class="laydate-end form-control input-sm" readonly="readonly">
					<span class="laydate-end-span input-group-addon m-ui-addon">
					<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
				</span>
	 		</div>
		</div>
	</div>
	<div class="col-xs-2 searchRange" style="display: none;height: 30px;">
		<button id="statistics" class="btn btn-primary" style="width:50px;"><span class="glyphicon glyphicon-stats" aria-hidden="true" style="margin-right:10px;"></span>统计</button>
		<button id="reset" class="btn btn-default m-ui-btn3" style="margin-left:5px;">重置</button>
	</div>
</div>
	

<div class="row">
<div class="col-xs-9">
      <div class="row row-mar mar-top">
                <div class="col-xs-6"><div id="highchart-container1" style="height:400px;"></div></div>
                <div class="col-xs-6"><div id="highchart-container2" style="height:400px;"></div></div>
       </div>
     <div class="row row-mar mar-top"><div id="highchart-container3" style="height:400px;"></div></div>
     
     <div class="row row-mar mar-top">
                <div class="col-xs-6"><div id="echarts-main1" style="height:400px;"></div>
                </div>
                <div class="col-xs-6"><div id="echarts-main2" style="height:400px;"></div>
                </div>
       </div>

</div>
<div class="col-xs-3">
<div style="margin-left:15px;">

<div class="m-ui-title3 mar-top"><h2>积分预警人员</h2> </div>            
 <div class="m-ui-table">
                  <table id="table3" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                  </table>
              </div> 

<div class="m-ui-title3 mar-top"><h2>人员数据动态情况</h2> </div> 
<div class="m-ui-table" style="margin-top:10px">
                  <table id="table1" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                      <thead>
                          <tr>
                              <th width="30%">指标</th>
                              <th>人员新增与调整情况</th>
                        </tr>
                      </thead>
              
                     <tbody>
                  					   <tr>
											<td>新增高危人</td>
											<td id="newAddHighriskPerson"></td>
										</tr>
                     
										<tr>
											<td>五色预警人员调整</td>
											<td id="warnTypeChange"></td>
										</tr>
										<tr>
											<td>人员类型调整</td>
											<td id="peopleTypeChange"></td>
										</tr>
									</tbody>
                  </table>
              </div>
<div class="m-ui-title3" style="margin-top:10px"><h2>五色预警人员查控数量（人）</h2> </div>             
 <div class="m-ui-table">
                  <table id="table2" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                  </table>
              </div>             
</div>
</div>
<!----right---->              
              

</div>
<!----row----> 


</div>
</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

<!-----------------主题代码------------------------>  
<script>

	Highcharts.theme = {
	colors: ["#ff0022", "#ff9900", "#ffde00", "#fff", "#7cb5ec", "#b7b6e9", "#eeaaee",
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
				color: '#A0A0A3'
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
		itemStyle: {
			color: '#E0E0E3'
		},
		itemHoverStyle: {
			color: '#FFF'
		},
		itemHiddenStyle: {
			color: '#606063'
		}
	},
	credits: {
		 enabled: false
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

</script>

<!-----------------主题代码---end---------------------->  
  
  

<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/home/home.js"></script>
</html>