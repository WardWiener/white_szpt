(function($){
	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var infoData = initData.infoData;
	
	$(document).ready(function() {
		queryIntegralInfo();
	});
	/**
	 * 初始化人员信息、查询积分规则、命中的属性
	 */
	function queryIntegralInfo(){
		$("#peopleName").text(infoData.name);
		$("#peopleIdcode").text(infoData.idcode);
		var peopleTypeName = infoData.peopleTypeName;
		var arr = peopleTypeName.split("、");
		$.each(arr,function(index,itm){
			$("#peopleType").append('<span class="state state-blue2">'+itm+'</span> ');
		})
		$.ajax({
			url:context +'/highriskPerson/queryIntegralInfo.action',
			type:'post',
			dataType:'json',
			data:{"id":infoData.id},
			success:function(json){
				var date = json.hrpScoreResultBean;
				var integralModeInfo = json.integralModel.integralModelRule;
				$.each($(".valCell"),function(i,val){
					$.each(integralModeInfo,function(y,itm){
						if(itm.key == $(val).attr("valName")){
							$(val).val(itm.value);
						}
					})
				});
				/**
				 * 初始化echarts图表
				 */
				initEcharts(date.totalScore);
				/**
				 * 匹配命中的属性，改变样式
				 */
				$.each($(".titleName"),function(i,val){
					$.each(date.hrpScoreResultDetails,function(a,info){
						$.each(info.resultInfoBean.hitInfos,function(b,hitInfos){
							if(hitInfos == $(val).attr("titalName")){
								$(val).removeClass().addClass("color-red2");
								var text = $(val)[0].innerHTML;
								$(val)[0].innerHTML=text+'<span class="glyphicon glyphicon-ok"></span>';
								
							}
						})
					})
				});
				
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 出事化图表
	 */
	function initEcharts(val){
		var dom = document.getElementById("echart-container");
		var myChart = echarts.init(dom);
		var app = {};
		var option = null;
		option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}分"
		    },
		   
		    series: [
		        {
		            name: '当前分数',
		            type: 'gauge',
		            detail: {formatter:'{value}'},
		            data: [{value: val, name: '积分'}]
		        }
		    ]
		};
		/*
		//动态的指针
		app.timeTicket = setInterval(function () {
		    option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
		    myChart.setOption(option, true);
		},2000);
		;*/
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
	}

})(jQuery);