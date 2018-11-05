
$.lookData = $.lookData || {};
(function($){
	"use strict"
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var fileId = initData.fileId;
	
	$(document).ready(function(){
		var setting = {
				containerId:"iwebOffice-container"
		}
		$.iWebOffice2000.init(setting) ;
//	    $.iWebOffice2000.openTemplateServerDoc("iwebOffice-container",$.iWebOffice2000.Constant.blankDocName.word,$.iWebOffice2000.Constant.EditType.EditWithTraceWithoutRevise) ;
		planShow();
	});
	
	function planShow(){
		var data = {
				"fileId" : fileId 
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url : context + '/zazlwh/findAttachmentById.action',
			type : "POST",
			data :dataMap,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(successDate) {	
				var file = successDate.resultMap.result.file;
				var arr =  $(this).attr("valName").split(".");
				var postfix = arr[arr.length-1];
				var str = '<div class="container-hadlistDom upload-text">';
				if (file != null){
					str += '<div id="'
							+ file.id
							+ '" class="item hadFile" finishedFileId="'
							+ file.id
							+ '">'
							+ file.name
							+ '<span class="size">('
							+ $.util
									.formatFileSize(file.size)
							+ ')</span>'
							+ '<span class="schedule"></span>'
							+ '<span class="downloadSpan">'
							+ '<button class="pldownload btn btn-link btn-xs">下载</button>'
							+ '</span>';
					str += '<span class="err"></span>' + '</div>';
				}
				str += '</div>';
				$("#wordDiv").html(str);
				var arr =  file.name.split(".");
				var postfix = arr[arr.length-1];
				if(postfix == doc || postfix == docx){
					if(!$.util.isBlank(successDate.resultMap.result)){
						$.iWebOffice2000.openServerDoc("iwebOffice-container", successDate.resultMap.result.fileName);					
					}else{
						$.layerAlert.alert({msg:"文档打开失败！"});
					}
				}
			},
		});
	}
	
	$(document).on("click","#wordDiv .pldownload",function() {
		var fid = $(this).parents(".finishedFile, .hadFile").attr("finishedFileId");
		window.open(context + "/wdza/downloadFile.action?attachmentId=" + fid);
	});
	jQuery.extend($.lookData , { 
	});	
	
	
})(jQuery);
