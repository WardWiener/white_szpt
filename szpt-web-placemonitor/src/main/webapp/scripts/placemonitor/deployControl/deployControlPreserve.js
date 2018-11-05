(function($){
	"use strict";
	var table;
	$(document).ready(function() {	
		
		init();
		//点击查询按钮
		$(document).on("click","#searchBtn",function(){
			creatTable();
		});
		
		//点击查询按钮
		$(document).on("click","#reset",function(){
			$("#nameInput").val("");
			$("#codeInput").val("");
			$("#addressInput").val("");
			$("#isLocaCheck").iCheck('check');
			creatTable();
		});
		
		//点击维护按钮
		$(document).on("click",".preserveBtn",function(){
			var id = $(this).attr("valId");
			tck(id);
		});
	});
	
	//初始化方法
	function init(){
		$("#isLocaCheck").iCheck('check');
		creatTable();
	}
	
	//维护弹出框
	function tck(id){
		
		window.top.$.layerAlert.dialog({
			content : context +  '/deployControl/showDeployControlDetails.action',
			pageLoading : true,
			title:"控制点详情",
			width : "1000px",
			height : "600px",
			btn:["修改","返回"],
			callBacks:{
				
				btn1:function(index, layero){
					var wd = window.top.frames["layui-layer-iframe"+index].$.wifiDeployControlDetails ;
					wd.commit();
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
			},
			initData:{
				id:id
			},
			end:function(){
				creatTable();
			}
		});

	}
	
	// 表格
	function creatTable(){
		$("#tableIdDiv").show();
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/deployControl/querySurveillanceByCondition.action',
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "5%",
					"title" : "序号",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return  meta.row+1;
					}
				},
				{
					"targets" : 1,
					"width" : "25%",
					"title" : '名称',
					"data" : "internetServicePlaceName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '编码',
					"data" : "internetServicePlaceCode",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "25%",
					"title" : '地址',
					"data" : "detailedAddress",
					"render" : function(data, type, full, meta) {
					
							return data;
					}
				},
				{
					"targets" : 4,
					"width" : "14%",
					"title" : '所在派出所',
					"data" : "areaDepartmentId",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "8%",
					"title" : '是否小区',
					"data" : "isLive",
					"render" : function(data, type, full, meta) {
						if(data == 1 ){
							return "是";
						}else if (data == 0){
							return "否";
						}else{
							return "未知";
						}
					}
				},
				{
					"targets" : 6,
					"width" : "8%",
					"title" : '操作',
					"data" : "id",
					"render" : function(data, type, full, meta) {	
						return '<button valId="' + data + '" class="preserveBtn btn btn-bordered btn-xs">维护</button>';
					}
				}
		];
		tb.ordering = false;
		tb.paging = true; //分页是否
		tb.hideHead = false; //是否隐藏表头
		tb.dom = null; 
		tb.searching = false; //是否有查询输入框
		tb.lengthChange = false; //是否可以改变每页显示条数
		tb.info = true; //是否显示详细信息
		tb.lengthMenu = [ 10 ]; //每页条数
		tb.paramsReq = function(d, pagerReq){ //传入后台的请求参数
			var bool = false;
			if($("#isLocaCheck")[0].checked){
				bool = true;
			}
			var data = {
					"name" : $("#nameInput").val(),
					"code": $("#codeInput").val(),
					"address":$("#addressInput").val(),
					"isLoca" : bool,
					"start":d.start,
					"pageSize":d.length
				}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.result.totalNum;
			json.recordsFiltered = json.resultMap.result.totalNum;
			json.data = json.resultMap.result.pageList;
		};
		table = $("#tableId").DataTable(tb);//在哪个table标签中展示这个表格
	}

})(jQuery);