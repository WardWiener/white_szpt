$.zaUpload = $.zaUpload || {};
(function($){
	//弹出框中拿到请求参数
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var zaId = initData.lockerId;
	var fjnr=[];
	var zlType = [];
	var ziliaoId = null;
	$(document).ready(function() {	
		init();
	})
	$(document).on("click", "#resBtn", function(){
		$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
	})
	$(document).on("click", "#uploadBtn", function(){
		upload();
	})
	$(document).on("change", "#first", function(){
		var bgid=$(this).children('option:selected').attr("id");
		onloadSecondLevel(bgid);		
	})
	
	/**
	 * 二级加载
	 */
	function onloadSecondLevel(data){
		var data = {
				"bgid" : data,
			}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/wdza/onloadSecondLevel.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			data:queryStr,
			success:function(successDate){
				var ListTwo=successDate.resultMap.resultTwo;
				$('#bglx').empty();
				var str='<option>全部</option>';
				for(var i=0;i<ListTwo.length;i++){
					str+='<option code="'+ListTwo[i].code+'">'+ListTwo[i].name+'</option> ';
				}
				$('#bglx').append(str);
			}
		})
	}
	
	/**
	 * 加载
	 */
	function init(){	
		onloadSelect();
		
		initPlupload();
	}
	
	
	/**
	 * 保存按钮--上传附件
	 */
	function  upload(){
		var demo = $.validform.getValidFormObjById("yanzheng") ;
		//是否有没通过的验证
		var flag = $.validform.check(demo) ;
		//是给予提示  
		if(!flag){
			return;
		}else{
			var data = {
					"zaId" : zaId,
					"bglx":$("#bglx").find("option:selected").attr("code"),//选中的文本值
					"bgmc":$('#bgmc').val(),
				}
			var dataMap = $.util.toJSONString(data);
			$.ajax({
				url : context + '/wdza/existsYZ.action',
				type : 'post',
				data : dataMap,
				contentType : "application/json; charset=utf-8",
				dataType : 'json',
				success : function(successData) {
					if(successData.resultMap.resultBG == true){
						$.util.topWindow().$.layerAlert.alert({msg:"该报告名称已存在!"});
						return;
					}else{
						var fileA = $("a[class='name']");
						if(null == fileA || fileA.length <= 0){
							$.util.topWindow().$.layerAlert.alert({msg:"请选择资料!",title:"提示"});
							return;
						}
						if($('.uploadFile,.finishedFile,.failedFile').length>1){
							$.util.topWindow().$.layerAlert.alert({msg:"只能添加一个附件!",title:"提示"});
							return;
						}
						if($('.finishedFile').length>0){
							$.util.topWindow().$.layerAlert.alert({msg:"已上传成功!",title:"提示"});
							return;
						}
						//checkRepeatFile();
						$.plupload.start("container");
					}
				}
			});
			
			
		}
		
	}
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
	
//	
//	function initZazllx(zazllx){
//		$("#zllxSel").empty();
//		$.select2.addByList("#zllxSel", zazllx,"code","name");
//		$("#zllxSel").select2("val", zazllxCode);
//	}
	
	

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
			setting.filters.mime_types = [{title: "报告附件", extensions: "doc,docx,wps"}];//报告附件只能上传doc类型
			setting.filters.prevent_duplicates = true ;
			setting.multi_selection = false;
			//setting.multi_file_num = 1 ;
			setting.multi_file_num_callback = function(max_file_size, totalSize){
			};
			setting.callBacks = {
					uploadAllFinish:function(up, finishedFiles){
						//$.util.topWindow().$.layerAlert.alert({msg:"保存成功!"});
						save(finishedFiles);//此处是上传完毕后的回调，调用保存方法，参数为附件的信息
					}
			}
			$.plupload.init(setting) ;
		}
		
	    /**
	     * 保存--内容
	     */
		function save(finishedFiles){
			var fileBeanLst = [];
			for (var key in finishedFiles) {
				var obj = {};
				obj.id = key;
				obj.name = finishedFiles[key].name;
				fileBeanLst.push(obj);
	        }
//			fjnr=fileBeanLst;
			//$("#ddlregtype ").val(); 
			var data = {
				"zaId" : zaId,
				"fileBeanLst" : fileBeanLst,
				"bglx":$("#bglx").find("option:selected").attr("code"),//选中的文本值
				"bgmc":$('#bgmc').val(),
			}
			var dataMap = $.util.toJSONString(data);

			$.ajax({
				url:context +'/wdza/saveSpecialCaseReport.action',
				type:'post',
				data:dataMap,
				contentType: "application/json; charset=utf-8",
				dataType:'json',
				success:function(successData){
						if(successData.resultMap.result == true){
							$.layerAlert.confirm({
					    		msg:'报告保存成功,是否继续添加',
					    		title:"confiem弹框",		//弹出框标题
					    		width:'300px',
								hight:'200px',
								shade: [0.5,'black'],  //遮罩
								icon:3,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
								shift:1,			//弹出时的动画效果  有0-6种
								btn:['继续','取消'],
					    		yes:function(index, layero){	//点击确定按钮
					    			 reset();
					    		},
					    		cancel:function(){
					    			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
								}
					    	});
						}else{
							$.util.topWindow().$.layerAlert.alert({msg:"保存失败!"});
						}
										
				}
			});
		}
		
		function onloadSelect(){
			$.ajax({
				url:context +'/wdza/onLoadAddReport.action',
				type:"post",
				dataType:"json",
				success:function(successDate){
					var listOne=successDate.resultMap.resultOne;
					$('#first').empty();
					var str='<option>全部</option> ';
					for(var i=0;i<listOne.length;i++){
						str+='<option id="'+listOne[i].id+'">'+listOne[i].name+'</option> ';
					}
					$('#first').append(str);
					$("#first").focus();
				}
			})
		}
		function  reset(){
			onloadSelect();
			$('#bglx').html('<option>全部</option>');
			$('#bgmc').val("");
			$('#container').html("");
			initPlupload();
			//$('#container').append('<span>（请上传一个word文档！）</span>');
		}
	
})(jQuery);
