
(function($){
	var id="";
	var fjid=""
	var index;
	var p$;
	var up$;
	var changeType = "modify";
	$(document).ready(function() {
		p$ = $.util.topWindow().$ ;
		var frameData = p$.layerAlert.getFrameInitData(window) ;
		index = frameData.index ;
		var initData = frameData.initData ;
		id = initData.lockerId;	//报告id
		fjid=initData.fjid;    //附件id
		//iweboffice
		var setting = {
			//installOcxManual:true,
			containerId:"iwebOffice-container"
			
		}
		$.iWebOffice2000.init(setting) ;   
		
		//文件上传
		var setting = $.plupload.getBasicSettings() ;
		setting.container = "container" ; //容器id
		setting.url = context + "/planManagement/uploadFile.action";
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
			className:"upload-text"
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
		setting.filters = {
				mime_types: [
				    {title : "word files", extensions : "doc,docx"},
				],
				max_file_size : '15mb',
			    prevent_duplicates : true
		  }
		setting.callBacks = {
				uploadAllFinish:function(up, finishedFiles){
					save(finishedFiles);
				}
		};
		//设置只能单选文件
		setting.multi_selection = false;
		setting.multi_file_num = 1 ;
		setting.multi_file_num_callback = function(max_file_size, totalSize){
			//数量限制回调
		};
		setting.callBacks.multi_file_num_callback = function(max_file_size, totalSize){
			
		};
		setting.callBacks.filesAdded = function(up, files){
			$(".container-hadlistDom").remove();
		};
		$.plupload.init(setting) ;

		$(document).on("click","#wordDiv .pldownload",function() {  //下载
			var fid = $(this).parents(".finishedFile, .hadFile").attr("finishedFileId");
			var b=window.open(context + "/wdza/downloadFile.action?attachmentId=" + fid);
		});
		
		$(document).on("click", ".btn-reupload", function(){ //重新上传按钮
			changeType = "reupload";
            $("#hasWordDiv").css("display","none");
            $("#reuploadDiv").css("display","block");
        });
		$(document).on("click", ".btn-cancelUpload", function(){  //取消按钮
			changeType = "modify";
			$("#hasWordDiv").css("display","block");
			$("#reuploadDiv").css("display","none");
        });
		
		
		$(document).on("keypress", "#keyInput", function(){
            if(event.keyCode == "13")    
            {
            	$('.btn-create').click();
            }
        });
		/**
		 * 返回按钮
		 */
		$(document).on("click", "#returnBtn", function(){
			$.util.topWindow().$.layerAlert.closeWithLoading(index); 
        });
		/**
		 * 保存按钮
		 */
		$(document).on("click", "#saveBtn", function(){
			saveFile();
        });
		$(document).on("click", ".btn-create", function(){
			var flag = true;
			if($("#keyInput").val() == ""){
				return;
			}
			var str = "";
			$(".key").each(function(){
				str += $(this).text() + ",";
				if($("#keyInput").val() ==  $(this).text()){
					flag = false;
					$.layerAlert.alert({msg:"请不要输入重复的关键词！"});
				}
			})
			str += $("#keyInput").val();
			if(str.length>85){
				$.layerAlert.tips({
					msg:'关键词长度请不要超过85！',
					selector:"#keyInput",  //需要弹出层的元素选择器
					color:'#FF0000',
					position:1,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return;
			}
			if(flag){
				var str = "<div class='alert alert-warning m-strip list'><span class='key'>" + $("#keyInput").val() + "</span><button class='btn btn-primary btn-x'>×</button></div>";
				$("#keyInput").val("");
				$("#keyWord").append(str);
			}
		});
		$(document).on("click", ".btn-x", function(){
			$($(this).parent()).remove();
		});
		planShow();
	});
	
	/**
	 *更改附件 
	 */
	function saveFile(){
		//删除前验证
		$.plupload.start("container");  //保存附件  --成功后调用setting.callBacks--save()方法
	}
	
	/**
	 * 页面展示附件
	 */
	function planShow(){
			var data={
				 reportId:id
	             }
	     	var queryStr = $.util.toJSONString(data);
			
			$.ajax({
				url:context +'/wdza/findReport.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var report=successData.resultMap.result;
	 				$('#reportName').text(report.name);
	 				$('#createPerson').text(report.createPerson);
	 				$('#createdTime').text($.date.timeToStr(report.createdTime, "yyyy-MM-dd HH:mm:ss"));
	 				var ss={
	 						reportId : id,
							fjid:fjid
	 			             }
	 			    var queryStr = $.util.toJSONString(ss);
	 				$.ajax({
						url : context + '/wdza/findAccessoryById.action',
						type:"post",
						contentType: "application/json; charset=utf-8",
						dataType:"json",
						data:queryStr,
						success : function(data) {
//							var bean = data.reservePlan;
//							if(bean == null){
//								return;
//							}
//							$("#name").val(bean.name);
//							$("#code").val(bean.code);
//							$.select2.val("#state",bean.state);
//							$.select2.val("#classify",bean.planClassify.id);
//							if(!$.util.isBlank(bean.keyword)){
//								var arr = [];
//								var str = "";
//								arr = bean.keyword.split(","); 
//								for(var i in arr){
//									str += "<div class='alert alert-warning m-strip list'><span class='key'>" + arr[i] + "</span><button class='btn btn-primary btn-x'>×</button></div>";
//								}
//								$("#keyWord").append(str);
//							}
							$(".container-hadlistDom").remove();
							var str = '<div class="container-hadlistDom upload-text">';
							if (data.resultMap.fileBean != null){
								str += '<div id="'
										+ data.resultMap.fileBean .id
										+ '" class="item hadFile" finishedFileId="'
										+ data.resultMap.fileBean .id
										+ '">'
										+ '<a href="###" class="name">'
										+ data.resultMap.fileBean .name
										+ '</a>'
										+ '<span class="size">('
										+ $.util
												.formatFileSize(data.resultMap.fileBean .size)
										+ ')</span>'
										+ '<span class="schedule"></span>'
										+ '<span class="downloadSpan">'
										+ '<button class="pldownload btn btn-link btn-xs">下载</button>'
										+ '</span>';
								str += '<span class="err"></span>' + '</div>';
							
							}
							str += '</div>';
							$("#wordDiv").html(str);
							if(!$.util.isBlank(data.resultMap.wordName)){
								$.iWebOffice2000.openServerDoc("iwebOffice-container", data.resultMap.wordName, $.iWebOffice2000.Constant["EditType"]["ReadOnly"], $.iWebOffice2000.Constant["ShowMenu"]["hide"]);
								$('.webOffice-statusBar').html("");
							}else{
								$.layerAlert.alert({msg:"文档打开失败！"});
							}
						},
					});
				}
			});
	}
	function planSub(){  //提交验证
		var demo = $.validform.getValidFormObjById("validformId");
		var flag = $.validform.check(demo);
		if (!flag) {
			return;
		}
		if($.util.isBlank($.select2.val("#classify"))){
			$.layerAlert.alert({msg:"请选择预案类型！"});
			return;
		}
		var map = new Object();
		map['id']=id;
		map['changeType']=changeType;
		map['reservePlan.code']=$("#code").val();
		map['reservePlan.name']=$("#name").val();
		map['reservePlan.planClassify.id']=$("#classify").val();
		map['reservePlan.state']=$("#state").val();
		var str = "";
		$(".key").each(function(){
			str += $(this).text() + ",";
		})
		if(str != ""){
			map['reservePlan.keyword']= str.substr(0,str.length-1);	
		}else{
			map['reservePlan.keyword']= "";
		}
		$.ajax({
			url : context + '/planManagement/checkPlanCodeAndName.action',
			type : "POST",
			dataType : "json",
			data :map,
			global: false,
			success : function(data) {
				if(data.flag == "1"){
					if(changeType == "reupload"){
						$.plupload.start("container");			
					}else{
						save();
					}
				}else if(data.flag == "2"){
					$.layerAlert.tips({
						msg:'名称重复！',
						selector:"#name",  //需要弹出层的元素选择器
						color:'#FF0000',
						position:1,
						closeBtn:2,
						time:2000,
						shift:1
					});
				}else if(data.flag == "3"){
					$.layerAlert.tips({
						msg:'编码重复！',
						selector:"#code",  //需要弹出层的元素选择器
						color:'#FF0000',
						position:1,
						closeBtn:2,
						time:2000,
						shift:1
					});
				}
			},
		});
	}
	function save(fileObj){   //保存按钮 ---里面方法需要更给(先删除 ,在新建附件)
		var map = new Object();
		map['id']=id;
		map['changeType']=changeType;
		map['reservePlan.code']=$("#code").val();
		map['reservePlan.name']=$("#name").val();
		map['reservePlan.planClassify.id']=$("#classify").val();
		map['reservePlan.state']=$("#state").val();
		var str = "";
		$(".key").each(function(){
			str += $(this).text() + ",";
		})
		if(str != ""){
			map['reservePlan.keyword']= str.substr(0,str.length-1);	
		}else{
			map['reservePlan.keyword']= "";
		}
		
		if(changeType == "modify"){
			$.iWebOffice2000.saveServerDoc("iwebOffice-container");
			map['wordName'] = $.iWebOffice2000.getServerDocName("iwebOffice-container");				
		}else if(changeType == "reupload"){
			var i = 0;
			for (var key in fileObj) {
				map["wordId"] = key;
				i++;
	        }
		}
		$.ajax({
			url : context + '/planManagement/modifyPlan.action',
			type : "POST",
			dataType : "json",
			data :map,
			global: false,
			success : function(data) {
				if(data.flag == "1"){
					up$.common.closeAndDraw(index);					
				}else{
					$.layerAlert.alert({msg:"操作失败！"});
				}
			},
		});				
	}
	/**
	 * 向父页面暴露的方法
	 */
	jQuery.extend($.common, { 
		save:function(obj){
			up$ = obj;
			planSub();
		},
		setIndex:function(diaIndex){
			index=diaIndex; 
		}
	});	
})(jQuery);

