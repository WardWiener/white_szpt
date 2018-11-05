$.zaUpload = $.zaUpload || {};
(function($){
	//弹出框中拿到请求参数
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var pageIndex = frameData.index ;
	var zazllxCode = initData.zazllxCode;
	var zaId = initData.zaId;
	var fjnr=[];
	var opt = null;
	var zlType = [];
	$(document).ready(function() {	
		init();
	});
	
	function init(){
		$.ajax({
			url:context +'/zazlwh/findUseZazllx.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				initZazllx(successDate.resultMap.result);
			}
		})
	}
	
	$(document).on("change", "#zllxSel", function(){
		selectChange();
	})
	
	function selectChange(){
		opt = $("#zllxSel").select2("val");
		if(null == opt){
			$("#zllxSel").select2("val", "01");
		}
		if(opt == '01'){
			zlType = [{title: "基本资料", extensions: "txt,doc,docx,xls,pdf,wps,ppt,rtf"}];
		}else
		if(opt == '04'){
			zlType = [{title: "图片类型", extensions: "jpg,JPG,jpeg,JPEG,gif,GIF,png,PNG,bmp,BMP"}];
		}else
		if(opt == '02'){
			zlType = [{title: "音频类型", extensions: "Mp3,wma,rm,wav,midi"}];
		}else
		if(opt == '03'){
			zlType = [{title: "视频类型", extensions: "mp4,ogg"}];
		}else{
			zlType = [{title: "其他",extensions:"txt,doc,xls,pdf,wps,ppt,jpg,JPG,jpeg,JPEG,gif,GIF,png,PNG,bmp,BMP,Mp3,wma,rm,wav,midi,avi,rmvb,asf,divx,mpg,mpeg,mpe,wmv,mp4,mkv,vob"}]
		}
		
		$("#container").empty();
		initPlupload();
	}
	
	//上传
	$(document).on("click", "#uploadBtn", function(){
		opt == null
		opt = $("#zllxSel").select2("val");
		if(opt == null){
			$.util.topWindow().$.layerAlert.alert({msg:"请选择资料类型!",title:"提示"});
			return;
		} 
		var fileA = $("a[class='name']");
		if(null == fileA || fileA.length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"请选择资料!",title:"提示"});
			return;
		}
		checkRepeatFile();
		//$.plupload.start("container");
	})
	//检测文件是否已上传
	function checkRepeatFile(){
		
		var fileNames = [];
		var fileA = $("a[class='name']");
		var len = fileA.length;
		for (var i = 0 ;i < len ;i++) {
			var h = $(fileA[i]).html()
			fileNames.push(h);
        }
		var data = {
			"zllx" : opt,
			"zaId" : zaId,
			"fileNames" : fileNames 
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url : context + '/zazlwh/findZazlByFileNameAndZaIdAndZllx.action',
			type : 'post',
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType : 'json',
			success : function(successData) {
				if(null != successData.resultMap.result){
					$.layerAlert.confirm({
			    		msg:successData.resultMap.result,
			    		title:"confiem弹框",		//弹出框标题
			    		width:'300px',
						hight:'200px',
						shade: [0.5,'black'],  //遮罩
						icon:3,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
						shift:1,			//弹出时的动画效果  有0-6种
						btn:['覆盖','取消'],
			    		yes:function(index, layero){	//点击确定按钮
			    	    	$.ajax({
		    	    		   url: context + '/zazlwh/coverZllx.action',
			    	    	   data:dataMap,
			    	    		contentType : "application/json; charset=utf-8",
			    	    		 type:"POST",
			    	    		 btn:'#delete',		
			    	    		 dataType:"json",
			    	    		 success:function(data){			
			    	    			var tr = $(this).parents("tr");		
			    	    			$.plupload.start("container");		
			    	    		}
			    	    	});
			    		},
			    		cancel:function(){
			    			selectChange();
						}
			    	});
				}else{
					$.plupload.start("container");
				}
			}
		});
	}
	
	function initZazllx(zazllx){
		$("#zllxSel").empty();
		$.select2.addByList("#zllxSel", zazllx,"code","name");
		$("#zllxSel").select2("val", zazllxCode);
	}
	function initPlupload(){
			var setting = $.plupload.getBasicSettings() ;
			setting.container = "container" ; //容器id
			setting.url = context + "/zazlwh/uploadFile.action";
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
				"zllx" : opt,
				"zaId" : zaId,
				"fileBeanLst" : fileBeanLst
			}
			var dataMap = $.util.toJSONString(data);

			$.ajax({
				url:context +'/zazlwh/saveStorageOut.action',
				type:'post',
				data:dataMap,
				contentType: "application/json; charset=utf-8",
				dataType:'json',
				success:function(successData){
					if(successData.resultMap.result == "true"){
						$.layerAlert.confirm({
				    		msg:"保存完成,是否继续上传",
				    		title:"confiem弹框",		//弹出框标题
				    		width:'300px',
							hight:'200px',
							shade: [0.5,'black'],  //遮罩
							icon:1,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
							btn:['继续','完成'],
				    		yes:function(){	//点击确定按钮
				    			selectChange();
				    		},
				    		cancel:function(index, layero){
				    			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); 
				    		}
				    	});
					}else{
						$.util.topWindow().$.layerAlert.alert({msg:"保存失败!"});
					}
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
