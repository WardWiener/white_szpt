(function($){
	"use strict";
	var pageIndex = window.top.$.layerAlert.getFrameInitData(window).index;
	$(document).ready(function() {
		initPlupload();
	});
	
	function initPlupload(){
		var setting = $.plupload.getBasicSettings() ;
		setting.container = "container" ; //容器id
		setting.url = context + "/personDetail/uploadExcel.action";
		setting.controlBtn = {
			container:{
				className:"upload-btn"
			},
			selectBtn:{
				className:"btn btn-primary",
				html:'<span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-right:10px;"></span>选择'
			},
			uploadBtn:{
				init:false
			}
		} ;
		setting.finishlistDom = {
			className:"upload-text",
			downloadBtn:{
				init:false
			},
			deleteBtn:{
				init:false
			}
		};
		setting.filelistDom = {
			className:"upload-text"
		};
		setting.totalInfoDom = {
			className:"upload-text"
		};
		setting.customParams = {
		} ;
		setting.chunk_size = '0' ;
		setting.filters.max_file_size = '50mb';
		setting.filters.prevent_duplicates = true ;
		setting.multi_selection = false;
		setting.multi_file_num = 1 ;
		setting.filters.mime_types = [{title : "excel files", extensions : "xls"}];
		setting.multi_file_num_callback = function(max_file_size, totalSize){
		};
		setting.callBacks = {
				uploadAllFinish:function(up, finishedFiles, savedErrorFiles, uploadErrorFiles){
					uploadFinish(finishedFiles, savedErrorFiles);
				}
		}
		$.plupload.init(setting) ;
	}
	function uploadFinish(finishedFiles, savedErrorFiles){
		var str = "";
		for(var i in savedErrorFiles){
			str = savedErrorFiles[i].name;
		}
		if(str != ""){
			window.top.$.layerAlert.alert({msg:'导入失败！',title:"提示",icon : 2,time:3000, end:function(){window.top.$.layerAlert.closeWithLoading(pageIndex);}});
		}
		window.top.$.layerAlert.alert({msg:'导入成功！',title:"提示",icon : 1,time:3000, end:function(){window.top.$.layerAlert.closeWithLoading(pageIndex);}});
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		save : function(){
			$.plupload.start("container");
		}
	});	
})(jQuery);