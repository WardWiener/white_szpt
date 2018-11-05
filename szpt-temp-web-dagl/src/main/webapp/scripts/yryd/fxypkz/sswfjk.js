/**
 * 实时wifi监控快照展示js
 */
$.sswijk = $.sswijk || {};

(function($){

	"use strict";
	
	var wifiSnapshotTable = null;// wifi快照表格
	var wifiSnapshotArray = [];// wifi快照集合
	
	$(document).ready(function() {	
		/**
		 * 快照表格行点击事件
		 */
		$(document).on("click","#wifiSnapshotTable tbody tr",function(){
			var rowData = $(this).data("data");
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/yryd/showLookSswfjkDetailPage.action',
				pageLoading : true,
				title:"查看实时WIfI设备监控快照",
				width : "1000px",
				height : "670px",
				btn:["关闭"],
				callBacks:{
					btn1:function(index, layero){
						$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					rowData : rowData
				},
				end:function(){
					
				}
			});
		});
	});
	
	/**
	 * 根据人员身份证号码查询wifi快照
	 * 
	 * @returns
	 */
	function findWifiSnapshotByIdCode(){
		var data = {
			"idCode" : idcard //"52252319570213001X"
		};
		$.ajax({
			url:context +'/yrydSnapshot/findWifiLocusSnapshotInfo.action',
			data:data,
			type:"post",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				wifiSnapshotArray = successData.resultMap.result;
				if(wifiSnapshotArray.length > 1){
					$("#wifiSnapshotInfoDiv").hide();
					$("#nullWifiSnapshotTableDiv").hide();
					$("#wifiSnapshotTableDiv").show();
					wifiSnapshotTable = $.dagl.yryd.yrydHighriskPersonAlert.createTrackAnalyzeLstTable("wifiSnapshotTable", wifiSnapshotArray);
				}else if(wifiSnapshotArray.length == 1){
					setSnapshotPage(wifiSnapshotArray[0]);
				}else{
					$("#wifiSnapshotTableDiv").hide();
					$("#wifiSnapshotInfoDiv").hide();
					$("#nullWifiSnapshotTableDiv").show();
				}
			}
		});
		$.lookSswfjkSnapshot.setMapObject(realTimeWifiMapConten);
	}
	
	/**
	 * 设置快照页面
	 * @returns
	 */
	function setSnapshotPage(snapshot){
		$("#wifiSnapshotTableDiv").hide();
		$("#nullWifiSnapshotTableDiv").hide();
		$("#wifiSnapshotInfoDiv").show();
		
		var snapshotObject = $.parseJSON(snapshot.snapshot);
		$("#wifiSnapshotInfo").text(snapshot.intro);
		
		$.lookSswfjkSnapshot.updateWifiPlaceUl(snapshotObject.data.baseBeanList);
		$.lookSswfjkSnapshot.newPlaceInfoPoint(snapshotObject.data.placeBasicInfoBeanList);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.sswijk, { 
		findWifiSnapshotByIdCode : findWifiSnapshotByIdCode
	});	
})(jQuery);