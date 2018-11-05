
$.zaData = $.zaData || {};
(function($){
	"use strict"
	var table = null;
	var uploadPensonId = null;

	
	/**
	 * 查询
	 */
	$(document).on("click", "#searchBtn", function(){
		zazllxCode = null;
		creatTable();
	})
	
	//点击专案资料查看资料的内容
	$(document).on("click", ".lookZaFileA", function(){
		var fileId = $(this).attr("valId");
		if($.zaxq.getZazllxCode() != "02" &&  $.zaxq.getZazllxCode() != "03" && $.zaxq.getZazllxCode() != "04"){
			var arr =  $(this).attr("valName").split(".");
			var postfix = arr[arr.length-1];
				$.util.topWindow().$.layerAlert.dialog({
					content : context +  '/zazlwh/showLookData.action',
					pageLoading : true,
					title:"查看资料内容",
					width : "900px",
					height : "630px",
					btn:["返回"],
					callBacks:{
						btn1:function(index, layero){
							$.util.topWindow().$.layerAlert.closeWithLoading(index);
						}
					},
					shadeClose : false,
					success:function(layero, index){
					},
					initData:{
						"fileId" : fileId
					}
				});
		}else if($.zaxq.getZazllxCode() == "04"){

			var data = {
					"fileId" : $(this).attr("valId")
				}
			var dataMap = $.util.toJSONString(data);
			$.ajax({
				url:context +'/zazlwh/findImgAttachmentById.action',
				type:"post",
				data : dataMap,
				contentType : "application/json; charset=utf-8",
				dataType:"json",
				success:function(successDate){
					$.layerAlert.img("data:image/jpeg;random=123;base64,"+successDate.resultMap.result,400);
				}
			})
		}else if($.zaxq.getZazllxCode() == "03"){
			var data = {
					"fileId" : $(this).attr("valId")
				}
			var dataMap = $.util.toJSONString(data);
			$.ajax({
				url:context +'/zazlwh/attachmentId.action',
				type:"post",
				data : dataMap,
				contentType : "application/json; charset=utf-8",
				dataType:"json",
				success:function(successDate){
					window.top.$.layerAlert.dialog({
						content:context + '/zazlwh/attachment.action',
						pageLoading:true,
						title:"视频附件",
						width:"800px",
						height:"600px",
						initData:{
							"path" : context + "/wdza/downloadFile.action?attachmentId=" + successDate.resultMap.result                     
						},
						shadeClose: false,
						btn: ['关闭'],
						callBacks:{
							btn1:function(index, layero){
								$.util.topWindow().$.layerAlert.closeWithLoading(index);
							}
						}
					});
				}
			})
		}
	})
	
	
	function showTitle(name){
		$("#dataNameH2").empty();
		$("#dataNameH2").text(name+"查看");
	}

	//查询所有的专案资料类型
	function initZazllx(zazllx){
		$("#zazllxUl").empty();
		for(var i in zazllx){
			$("#zazllxUl").append('<a href="#"  valCode="'+zazllx[i].code+'" class="zazllA list-group-item">'+zazllx[i].name+'</a>')
		}
	}
	
	//创建表
	function creatTable(){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zazlwh/searchZazl.action',
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "",
					"title" : "序号",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return  meta.row+1;
					}
				},
				{
					"targets" : 1,
					"width" : "30%",
					"title" : '名称',
					"data" : "fileName",
					"render" : function(data, type, full, meta) {
						
						return '<div class="pull-left mar-right"><a href="javascript:void(0);" class="lookZaFileA" valId="'+full.id+'" valName="'+data+'" target="_blank"><span class="zl-icon zl-icon-basic"></span></a></div>'
                        +'<h2 class="row-mar font14"><a href="javascript:void(0);" class="lookZaFileA" valId="'+full.id+'" valName="'+data+'" target="_blank">'+data+'</a></h2>'
					      +'<p class="font11 color-gray2">上传时间：'+full.uploadTime+'</p>';
					}
				},
				{
					"targets" : 2,
					"title" : "类型",
					"data" : "fileType",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"title" : "大小",
					"data" : "fileSize",
					"render" : function(data, type, full, meta) {
						return data+"k";
					}
				},
				{
					"targets" : 4,
					"title" : "资料上传人",
					"data" : "fileUploadPerson",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 3 ];
		
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"caseId" : $.zaxq.getCaseId(),
				"fileName" : $("#fileName").val(),
				"uploadPenson" : uploadPensonId,
				"zazllxCode" : $.zaxq.getZazllxCode(),
				"start":d.start,
				"length":d.length
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.result.totalNum;
			json.recordsFiltered = json.resultMap.result.totalNum;
			json.data = json.resultMap.result.pageList;
		};
		table = $("#dataTable").DataTable(tb);
	}
	
	jQuery.extend($.zaData , { 
		datacCreatTable:creatTable,
		showTitle:showTitle
	});	
	
	
})(jQuery);
