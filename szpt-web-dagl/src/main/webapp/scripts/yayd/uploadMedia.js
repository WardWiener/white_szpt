(function($){
		var caseId = "";
		var par$ = "";
		var index = "";
		$(document).ready(function() {
			p$ = window.top.$ ;
			var frameData = p$.layerAlert.getFrameInitData(window) ;
			index = frameData.index ;
			var initData = frameData.initData ;
			caseId = initData.caseId;
			initPlupload();
		});
		
		function initPlupload(){
			var setting = $.plupload.getBasicSettings() ;
			setting.container = "container" ; //容器id
			setting.url = context + "/yayd/uploadFile.action";
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
			setting.filters.mime_types = [ //只允许上传mp3mp4文件
			    { title : "mp3 files", extensions : "mp3,mpeg3" }, 
			    { title : "mp4 files", extensions : "mp4,mpeg4" }
			  ];//此行配置的是只能上传这几种后缀的文件
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
				fileBeanLst.push(key);
	        }
			if(fileBeanLst.length == 0){
				window.top.$.layerAlert.alert({msg:"请选择音视频文件！",icon:"1",end : function(){
					return;
				}});
				return;
			}else{
				var data = {
					"caseCode" : caseId,
					"fileBeanLst" : fileBeanLst
				}
				var dataMap = $.util.toJSONString(data);
				$.ajax({
					url:context +'/yayd/saveVideoForCaseCode.action',
					data:dataMap,
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
				    customizedOpt:{
					    ajaxLoading:true,//设置是否loading
					},
					success:function(successData){
						window.top.$.layerAlert.alert({msg:"保存成功！",icon:"1",end : function(){
							par$.common.closeWindow(index);
							return;
						}});
					}
				});
			}
			
		}
		/**
		 * 向父页面暴露的方法
		 */
		jQuery.extend($.common, {
			upload:function(parl, ind){
				par$ = parl;
				index = ind;
				$.plupload.start("container");
			}
		});
	})(jQuery);

