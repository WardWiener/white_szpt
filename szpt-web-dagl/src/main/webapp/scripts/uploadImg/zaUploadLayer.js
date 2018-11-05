$.zaUpload = $.zaUpload || {};
(function($){
	
	$(document).ready(function() {	
		
		init();
	});
	
	function init(){
		zlType =  [{title: "图片类型", extensions: "jpg,JPG,jpeg,JPEG,gif,GIF,png,PNG,bmp,BMP"}];
		
		$("#container").empty();
		initPlupload();
	}
	
	//上传
	$(document).on("click", "#uploadBtn", function(){
		$.plupload.start("container");
	})
	
	function initPlupload(){
			var setting = $.plupload.getBasicSettings() ;
			setting.container = "container" ; //容器id
			setting.url = context + "/yrydSnapshot/uploadFile.action";
			setting.controlBtn = {
				container:{
					className:"upload-btn"
				},
				selectBtn:{
					className:"btn btn-primary",
					html:'<span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-right:10px;"></span>选择'//这个地方是设置上传按钮的
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
				testCustom1:"123",
				testCustom:function(up, file){
					return Math.random() ;
				}
			} ;
			setting.chunk_size = '0' ;
			setting.filters.max_file_size = '15mb';
			setting.filters.mime_types = zlType;//此行配置的是只能上传这几种后缀的文件
			setting.filters.prevent_duplicates = true ;
			setting.multi_selection = false;
			//setting.multi_file_num = 1 ;
			setting.multi_file_num_callback = function(max_file_size, totalSize){
			};
			setting.callBacks = {
					uploadAllFinish:function(up, finishedFiles){
						save(finishedFiles);//此处是上传完毕后的回调，调用保存方法，参数为附件的信息
					}
			}
			$.plupload.init(setting) ;
		}
	
		function save(finishedFiles){
			var fileBeanLst = [];
			for (var key in finishedFiles) {
				var obj = {};
				obj.id = key;
				obj.name = finishedFiles[key].name;
				fileBeanLst.push(obj);
	        }
			fjnr=fileBeanLst;
			var data = {
				"fileBeanLst" : fileBeanLst,
				"idcode":$("#idcard").val()
			}
			var dataMap = $.util.toJSONString(data);
			$.ajax({
				url:context +'/yrydSnapshot/saveImg.action',
				type:'post',
				data:dataMap,
				contentType: "application/json; charset=utf-8",
				dataType:'json',
				success:function(successData){

				}
			});
		}
		
		function  saveFJID(){
			return fjnr;
		}
		
		/**
		 * 暴露本js方法，让其它js可调用
		 */
		jQuery.extend($.zaUpload, {
			saveFJID : saveFJID
		});		
		
})(jQuery);
