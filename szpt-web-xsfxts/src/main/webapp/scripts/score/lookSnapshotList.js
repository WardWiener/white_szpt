$.lookSnapshotList = $.lookSnapshotList || {};
(function($){
	
	"use strict";
	
	var sTable = null;
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var caseCode = initData.caseCode;
	
	$(document).ready(function() {	
		querySnapshotList();
		
		/**
		 * 查看详情
		 */
		$(document).on("click",".lookInfo",function(){
			var sanpshotId = $(this).attr("sanpshotId");
			var no = $(this).attr("no");
			window.open(context + "/snapshot/showLookSnapshotDetailPage.action?sanpshotId=" + sanpshotId + "&&no=" + no);
		});
		
	});
	
	/**
	 * 查询快照记录
	 */
	function querySnapshotList(){
		var data = {
			"mainCaseCode" : caseCode
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/snapshot/querySnapshotListByCaseCode.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var csrsbs = successData.resultMap.csrsbs;
				initSnapshotListTable(csrsbs);
			}
		});
	}
	
	/**
	 * 初始化快照记录列表
	 * 
	 * @param dataArray 数据集合
	 */
	function initSnapshotListTable(dataArray){
		if($.util.exist(sTable)){
			sTable.destroy();
			$("#sTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "",
			"title" : "序号",
			"data" : "id",
			"render" : function(data, type, full, meta) {
				
				return meta.row + 1;
			}
		}, {
			"targets" : 1,
			"width" : "",
			"title" : "快照记录生成时间",
			"data" : "createdDate",
			"render" : function(data, type, full, meta) {
				
				return $.date.timeToStr(data,"yyyy-MM-dd HH:mm");
			}
		}, {
			"targets" : 2,
			"width" : "",
			"title" : "创建人",
			"data" : "createPerson",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "",
			"title" : "操作",
			"data" : "id",
			"render" : function(data, type, full, meta) {
				var button = $("<button />",{
					"class" : "btn btn-xs btn-primary lookInfo" ,
					"sanpshotId" : data ,
					"no" : meta.row + 1 ,
					"text" : "查看详情"
				});
				return button[0].outerHTML ;
			}
		}];
		
		st1.ordering = false;
		st1.paging = false; // 是否分页
        st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ 10 ];
		
		sTable = $("#sTable").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookSnapshotList, { 
		
	});	
})(jQuery);