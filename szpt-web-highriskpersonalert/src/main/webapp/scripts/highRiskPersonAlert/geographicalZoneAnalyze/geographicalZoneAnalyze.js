(function($){
	"use strict";
	var table ; //WIFI分析表格
	var zone = "";
	$(document).ready(function() {	
		
		initTable();
		initHHighcharts1();
		initHHighcharts2();
		refresh();
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#search",function(){
			refresh();
		});
	
		/**
		 * 重置查询条件
		 */
		$(document).on("click","#reset",function(){
			$.laydate.reset("#dateRange");
		});
		$(document).on("click",".detail",function(){
			var idcode = $(this).attr("idcode");
			if(findPersonByIdcode(idcode)){
				window.location.href = context +"/personDetail/showPersonDetailPage.action?clickOrder=2&&clickListOrder=0&&idcode=" + idcode;
			}else{
				window.top.$.layerAlert.alert({msg:"未查到相关人员"}) ;
			}
		});
		$(document).on("click",".track",function(){
			var idcode = $(this).attr("idcode");
			if(findPersonByIdcode(idcode)){
				window.location.href = context +"/trackAnalyze/showTrackAnalyzePage.action?clickOrder=5&&clickListOrder=0&&idcode=" + idcode;
			}else{
				window.top.$.layerAlert.alert({msg:"未查到相关人员"}) ;
			}
		});
		
		$(document).on("click",".instruction",function(){
			window.location.href = context +"/instruction/showInstructionListPage.action?clickOrder=0&&clickListOrder=0";
		});
		
	});
	function findPersonByIdcode(idcode){
		var flag=false;
		$.ajax({
			url:context +'/highriskPerson/findPersonByIdcode.action',
			type:'post',
			dataType:'json',
			data:{idcode:idcode},
			async:false,
			success:function(successData){
				if(successData.highriskPersonBean!=null){
					flag=true;
				}
			},
			error:function(errorData){
				
			}
		});
		return flag;
	}
	function refresh(){
		var startTime = $.laydate.getTime("#dateRange", "start");
		var endTime = $.laydate.getTime("#dateRange", "end");
		var gData = new Object();
		$.util.objToStrutsFormData(startTime, "startTime", gData);
		$.util.objToStrutsFormData(endTime, "endTime", gData);
		$.ajax({
			url:context +'/geographicalZoneAnalyze/findByGeographicalZone.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				var statisticsInfoList = successData.statisticsInfoList;
				var list = new Array();
				var nameList=[];
				var numList=[];
				$.each(statisticsInfoList,function(i,val){
					var obj = new Object();
					obj["name"] = val.name;
					obj["y"] = parseInt(val.value);
					list.push(obj);
					nameList.push(val.name);
					numList.push(parseInt(val.value));
				});
				initHHighcharts1(list);
				initHHighcharts2(nameList,numList);
				zone = "";
				table.draw();
			},
			error:function(errorData){
				
			}
		});			
	}
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/geographicalZoneAnalyze/findPeopleByZone.action";
		tb.columnDefs = [
 			{
 				"targets": 0,
      	    	"width": "4%",
      	    	"title": "序号",
      	    	"className":"table-checkbox",
      	    	"data": "" ,
      	    	"render": function ( data, type, full, meta ) {
      	    			  return meta.row+1;;
      	    	}
 			},
 			{
 				"targets" : 1,
 				"width" : "8%",
 				"title" : "姓名",
 				"data" : "personName",
 				"render" : function(data, type, full, meta) {
 					return '<a href="'+context + '/personDetail/showPersonDetailPage.action?idcode='+full.idCode+'">'+data+'</a>';
 				}
 			},
 			{
 				"targets" : 2,
 				"width" : "15%",
 				"title" : "身份证号",
 				"data" : "idCode",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 3,
 				"width" : "8%",
 				"title" : "户籍地",
 				"data" : "nationalAddress",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 4,
 				"width" : "8%",
 				"title" : "现住地",
 				"data" : "address",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 5,
 				"width" : "10%",
 				"title" : "手机号",
 				"data" : "telephoneNumber",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 6,
 				"width" : "12%",
 				"title" : "最近进入经开区时间",
 				"data" : "lastEntertime",
 				"render" : function(data, type, full, meta) {
 					return data.replace("T"," ");
 				}
 			},
 			{
 				"targets" : 7,
 				"width" : "8%",
 				"title" : "是否查控",
 				"data" : "isCheck",
 				"render" : function(data, type, full, meta) {
 					return data=="1"?"是":"否";
 				}
 			},
 			{
 				"targets" : 8,
 				"width" : "16%",
 				"title" : "该区域常见作案类型",
 				"data" : "commomCaseType",
 				"render" : function(data, type, full, meta) {
 					return data;
 				}
 			},
 			{
 				"targets" : 9,
 				"width" : "14%",
 				"title" : "操作",
 				"data" : "",
 				"render" : function(data, type, full, meta) {
 					var a = '<button idcode="'+full.idCode+'"class="btn btn-bordered btn-xs detail">详情</button>';
 					a+='<button idcode="'+full.idCode+'"class="btn btn-bordered btn-xs track">轨迹</button>';
 					a+='<button idcode="'+full.idCode+'"class="btn btn-bordered btn-xs instruction">指令</button>';
 					return a;
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
 		//请求参数
 		tb.paramsReq = function(d, pagerReq){
 			d["startTime"] = $.laydate.getTime("#dateRange", "start");
 			d["endTime"] = $.laydate.getTime("#dateRange", "end");
 			d["zone"] = zone;
 		};
 		tb.paramsResp = function(json) {
 			var list = json.pager.pageList;
 			json.recordsTotal = json.pager.totalNum;
 			json.recordsFiltered = json.pager.totalNum;
 			json.data = list;
 		};
 		tb.rowCallback = function(row,data, index) {


 		};
 		table = $("#table").DataTable(tb);
	}
	
	/**
	 * 初始化图标插件
	 */
	function initHHighcharts1(dataArray){
		Highcharts.theme = {
				colors: ["#0fb6e6", "#ff9900", "#ffde00", "#ff5f85", 
					"#9e5ffc", "#DF5353", "#7798BF", "#aaeeee"],
					
				}
		
		$('#highchart-container1').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '人员分布'
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
	                },
	                events: {
	                    click: function(e) {
		        			findPlace(e.point.name);
		        	    }
	        		}
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '占比',
	            data:dataArray
	        }]
	    });
		Highcharts.setOptions(Highcharts.theme);
	}
	function initHHighcharts2(nameList,numList){
		Highcharts.theme = {
				colors: [ "#0fb6e6", "#ff0022", "#00cb5b", "#fff",
					"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
					
				}
		
		$('#highchart-container2').highcharts({
			chart: {                                                          
	        },                                                                
	        title: {                                                          
	            text: '人员数量'                                     
	        },                                                                
	        xAxis: {                                                          
	            categories: nameList
	        },    
	        yAxis: {                                                          
	        	title: {text: ''},
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
	                events: {
	                    click: function(e) {
	                    	findPlace(e.point.category);
	        	    }
	        	},
	            }
	        },
	        series: [{                                                        
	            type: 'column',                                               
	            name: '地区',                                                 
	            data: numList                                         
	        }] 
	    });
		Highcharts.setOptions(Highcharts.theme);
	}
	function findPlace(name){
		zone=name;
		table.draw(true);
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
//		findAllAutoFlow:findAllAutoFlow
	});	
})(jQuery);