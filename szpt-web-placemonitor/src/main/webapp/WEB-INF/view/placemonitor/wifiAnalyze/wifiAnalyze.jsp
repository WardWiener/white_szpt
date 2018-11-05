<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–高危人WIFI监测</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/placemonitor/common/constant.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-placemonitor.jsp"%>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">场所监控分析</a></li>
					<li><a href="#">高危人WIFI监测</a></li>
				</ol>
				<div id="waDateRange" class="row row-mar dateRange">
					<div class="col-xs-1">
						<label class="control-label">时间：</label>
					</div>
					<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
					<div class="col-xs-2 input-group">
						<div class="input-group" style="margin-right:10px;">
							<input type="text" id="fixed_start" class="laydate-start form-control input-sm"readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
					<div class="col-xs-2 input-group">
						<div class="input-group" style="padding-left:5px;">
							<input type="text" id="fixed_end" class="laydate-end form-control input-sm"readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>

					<div class="col-xs-2">
						<label class="control-label">人员类型：</label>
					</div>
					<div class="col-xs-2">
						<input id="criminalRecordName" readonly type="text" class="form-control input-sm">
					</div>
					<div class="col-xs-2">
						<button id="search" class="btn btn-primary btn-sm">查询</button>
						<button id="reset" class="btn btn-default btn-sm" style="margin-left: 2px;">重置</button>
					</div>
				</div>
				<div id="searchResult" style="display:none;">
					<div class="m-ui-title1">
						<h1>WIFI分析</h1>
					</div>
					<div class="row" style="">
						<div class="col-xs-6">
							<div class="m-ui-table" style="margin-right: 10px">
								<table id="wifiAnalyzeTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
									
								</table>
							</div>
						</div>
						<div class="col-xs-6 mar-top">
						   <div align="right" id="returnDIVBtn" style="display: none"> <button id="returnBtn" class="btn btn-primary btn-sm">返回</button> </div>
							<div id="highchart-container" style="height: 400px;"></div>
						</div>
					</div>
				</div>
				<div id="nullSearchResult" style="text-align:center;display:none;">
					<h3>无查询结果</h3>
				</div>
			</div>
		</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<ul id="ztree-criminalRecord" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script>
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

<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/wifiAnalyze/wifiAnalyze.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/common/zTreeMenu.js"></script>
</html>