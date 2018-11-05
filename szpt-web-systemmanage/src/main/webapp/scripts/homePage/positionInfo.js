(function($){
	"use strict"
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;	//当前弹窗index
	var initData = frameData.initData ;		//父页面传的参数，在initData中
	
	$(document).ready(function() {	
		var dailyReport = initData.dailyReport
		var unitName = initData.unitName;
		var positionName = initData.positionName; 
		$("#name").text(positionName);
		var str = "";
		var i = 0;
		for(i; i<dailyReport.length; i++ ){
			if(dailyReport[i].unitName == unitName){
				$.each(dailyReport[i].positionBean,function(y, val) {
					if(val.name == positionName){
						$.each(val.daiPost,function(i, val2) {
							if(i%3 == 0){
								str += "<tr>";
							}
							str += "<td>"
								+ "<span class='m-ui-marleft1'>" + val2.name + "</span></td>";
							if(i%3 == 2){
								str += "</tr>";
							}
						});
					}
				});
			}
		}
		if(i%3 != 0){
			str += "</tr>";
		}
		$("#people tbody").append(str);
	});
	
	
	

	
})(jQuery)