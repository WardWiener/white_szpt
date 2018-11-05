$.newInstruct = $.newInstruct || {};

(function($){

	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var personArray = initData.personArray;
	
	var len  = 0;
	var instructTypeArray = [];// 所有指令类型字典项
	//文件上传
	var setting = $.plupload.getBasicSettings() ;
	
	$(document).ready(function() {	
		
		console.log(personArray);
		initPersonCheckbox();
		initPersonTabs();
		
		/**
		 * 预警人员取消选择状态
		 */
		$(document).on("ifUnchecked",".personCheckbox",function(){
			var idCode = $(this).val();
			$("#tabs-" + idCode).hide();
			$("#li-" + idCode).hide();
			$("#tabsUl li").each(function(l,li){
				if($(li).css("display") == "none"){
					return true;
				}else{
					$(li).find("a").click();
					return false;
				}
			});
		})
		
		/**
		 * 预警人员勾选状态
		 */
		$(document).on("ifChecked",".personCheckbox",function(){
			var idCode = $(this).val();
			$("#tabs-" + idCode).show();
			$("#li-" + idCode).show();
			$("#li-" + idCode).find("a").click();
		})
		
		/**
		 * 一级指令类型选择事件
		 */
		$(document).on("select2:select", "select[name='instructTypeFirst']",function(){
			var selectedCode = $(this).val();
			var idCode = $(this).attr("idCode");
			initInstructTypeSecond(selectedCode, "instructTypeSecond" + idCode);
			setPersonAndCarInfo(idCode);
		});
		
		/**
		 * 一级指令类型取消选择事件
		 */
		$(document).on("select2:unselect", "select[name='instructTypeFirst']",function(){
			var idCode = $(this).attr("idCode");
			$.select2.empty("#instructTypeSecond" + idCode);
			$("#instructTypeSecond" + idCode).parents("div").eq(1).hide();
			setPersonAndCarInfo(idCode);
		});
		
		/**
		 * 二级指令类型选择事件
		 */
		$(document).on("select2:select", "select[name='instructTypeSecond']",function(){
			var selectedCode = $(this).val();
			var idCode = $(this).attr("idCode");
			setPersonAndCarInfo(idCode);
		});
		
	});
	
	
	
	/**
	 * 设置预警人员勾选列表
	 * 
	 * @returns
	 */
	function initPersonCheckbox(){
		$("#personCheckboxDiv").html("");
		if(!$.util.exist(personArray) || personArray.length < 1){
			return ;
		}
		$.each(personArray,function(p, person){
			var p = $("<p />",{
				"class" : "col-xs-1" ,
				"style" : "width: 100px",
				"text" : person.personName
			});
			$("<input />",{
				"type" : "checkbox" ,
				"class" : "icheckbox personCheckbox" ,
				"checked" : "checked" ,
				"value" : person.idCode ,
				"name" : "personCheckbox"
			}).prependTo(p);
			$("#personCheckboxDiv").append(p);
		});
	}
	
	/**
	 * 初始化页签
	 * @returns
	 */
	function initPersonTabs(){
		$("#tabsUl").html("");
		$("#tabsDiv").html("");
		if(!$.util.exist(personArray) || personArray.length < 1){
			return ;
		}
		$.each(personArray,function(p, person){
			//添加页签
			var li = $("<li />",{
				"id" : "li-" + person.idCode ,
			});
			$("<a />",{
				"href" : "#tabs-" + person.idCode ,
				"text" : person.personName
			}).appendTo(li);
			$("#tabsUl").append(li);
			
			//添加页签内容
			var instructTemplate = $("#instructTemplate");
			var instructPage = instructTemplate.clone(true);
			$(instructPage).attr("id", "tabs-" + person.idCode);
			$("#tabsDiv").append(instructPage);
			
			//设置一级指令类型
			$("#tabs-" + person.idCode + " select[name='instructTypeFirst']").attr("id","instructTypeFirst" + person.idCode).attr("idCode", person.idCode);
			initInstructTypeFirst("instructTypeFirst" + person.idCode);
			
			//设置二级指令类型id
			$("#tabs-" + person.idCode + " select[name='instructTypeSecond']").attr("id","instructTypeSecond" + person.idCode).attr("idCode", person.idCode);
			
			//设置人员盘查信息id
			$("#tabs-" + person.idCode + " div[name='personInfo']").attr("id","personInfo" + person.idCode);
			
			//设置车辆盘查信息id
			$("#tabs-" + person.idCode + " div[name='carInfo']").attr("id","carInfo" + person.idCode);
			
			//设置反馈时间id
			$("#tabs-" + person.idCode + " div[name='feedbackTime']").attr("id","feedbackTime" + person.idCode);
			
			//设置通知本单位负责人name
			$("#tabs-" + person.idCode + " input[name='tongzhi']").attr("name","tongzhi" + person.idCode).addClass("icheckradio");
			
			//设置接收单位树
			$("#tabs-" + person.idCode + " input[name='unit']").attr("id","unit" + person.idCode);
			$.szpt.util.businessData.initDwTree("unit" + person.idCode, null);
			
			//初始化附件上传
			$("#tabs-" + person.idCode + " div[name='pcrUpload']").attr("id","pcrUpload" + person.idCode);
			$("#tabs-" + person.idCode + " div[name='showAttach']").attr("id","showAttach" + person.idCode);
			initPlupload(person.idCode);
		});
		$("#tabs1").tabs();
	}
	
	/**
	 * 设置一级指令类型
	 * 
	 * @param selectId 指令选择框id
	 * @returns
	 */
	function initInstructTypeFirst(selectId){
		$.ajax({
			url:context +'/webUtil/findAllSubDictionaryItemsByTypeCode.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : "zllx"},//$.common.DICT.DICT_ZLLX
			success:function(successData){
				instructTypeArray = successData.dictionaryItemLst;
				var list= [];
				for(var i in instructTypeArray){
					if(instructTypeArray[i].parentItemId==null){
						list.push(instructTypeArray[i]);
					}
				}
				$.select2.addByList("#" + selectId, list,"code","name");//设置下拉框选项
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 设置二级指令类型
	 * 
	 * @param instructTypeFirstCode 一级指令类型code
	 * @param selectId 二级指令select id
	 * @returns
	 */
	function initInstructTypeSecond(instructTypeFirstCode, selectId){
		$.select2.empty("#" + selectId);
		var list = [];
		for(var i in instructTypeArray){
			if(instructTypeArray[i].parentItemId == instructTypeFirstCode){
				list.push(instructTypeArray[i]);
			}
		}
		if(list.length > 0){
			$("#" + selectId).parents("div").eq(1).show();
			$.select2.addByList("#" + selectId, list,"code","name");//设置下拉框选项
		}else{
			$("#" + selectId).parents("div").eq(1).hide();
		}
	}
	
	/**
	 * 设置是否显示人员和车辆盘查信息
	 * 
	 * @param idCode 身份证号，唯一标识
	 * @returns
	 */
	function setPersonAndCarInfo(idCode){
		var instructTypeSecondCode = $.select2.val("#instructTypeSecond" + idCode);
		if(instructTypeSecondCode == "0000000011005001"){//人员盘查，显示人员盘查相关字段
			$("#carInfo" + idCode).hide();
			$("#personInfo" + idCode).show();
		}else if(instructTypeSecondCode == "0000000011005002"){//车辆盘查，显示车辆盘查相关字段
			$("#personInfo" + idCode).hide();
			$("#carInfo" + idCode).show();
		}else{// 都不是，影藏人员和车辆盘查
			$("#personInfo" + idCode).hide();
			$("#carInfo" + idCode).hide();
		}
	}
	
	/**
	 * 初始化附件上传
	 * 
	 * @param id 附件上传容器id
	 * @returns
	 */
	function initPlupload(idCode){
		var idCodeNumber = idCode;
		setting.container = "pcrUpload" + idCode ; //容器id
		setting.url = context + "/personAttention/uploadFile.action";
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
			testCustom1:"123",
			testCustom:function(up, file){
				return Math.random() ;
			}
		} ;
		setting.chunk_size = '0' ;
		setting.filters.max_file_size = '15mb';
		setting.filters.mime_types = [{title: "word文档类型", extensions: "doc,docx"}];
		setting.filters.prevent_duplicates = true ;
		setting.multi_selection = false;
		setting.multi_file_num = 1 ;
		setting.multi_file_num_callback = function(max_file_size, totalSize){
		};
		setting.callBacks = {
			uploadAllFinish:function(up, finishedFiles){
				var i = 0;
				var fileObjectBeans = new Array();
				for (var key in finishedFiles) { 
					var obj = new Object();
					obj["id"] = key;
					fileObjectBeans.push(obj);
					i ++;
		        }
				var instructionBean = getinstructionBean(idCodeNumber);
				saveInstruction(instructionBean, fileObjectBeans);
			}
		}
		$.plupload.init(setting) ;
	}
	
	/**
	 * 调用action保存指令
	 * @param instructionBean 指令Bean
	 * @param fileObjectBeans 附件Bean集合
	 * @returns
	 */
	function saveInstruction(instructionBean, fileObjectBeans){
		var arr = $.icheck.getChecked("personCheckbox");
		var dataMap = {};
		$.util.objToStrutsFormData(instructionBean, "instructionBean", dataMap);
		$.util.objToStrutsFormData(fileObjectBeans, "fileObjectBeans", dataMap);
		$.ajax({
			url:context +'/personAttention/saveInstruction.action',
			type:'post',
			dataType:'json',
			async:false, 
			data:dataMap,
			success:function(successData){
				window.top.$.layerAlert.alert({msg:'指令下发成功！',title:"温馨提示",icon : 6,time:3000});
				if(len >= arr.length){
					$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
				}
			},
			error:function(errorData){
				window.top.$.layerAlert.alert({msg:'指令下发失败！',title:"温馨提示",icon : 5,time:3000});
			}
		});
		
	}
	
	/**
	 * 验证指令
	 * 
	 * @returns
	 */
	function verifyInstruction(){
		var arr = $.icheck.getChecked("personCheckbox");
		var flag = true;
		$.each(arr,function(i,input){
			var idCode = $(input).val();
			
			$("#li-" + idCode).find("a").click();
			
			//验证指令类型
			var type = "";
			if($.util.isBlank($.select2.val("#instructTypeSecond" + idCode))){
				type =  $.select2.val("#instructTypeFirst" + idCode);
			}else{
				type =  $.select2.val("#instructTypeSecond" + idCode);
			}
			if($.util.isBlank(type)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择指令类型。"}) ;
				flag = false;
				return false;
			}
			
			//验证指令内容
			var content = $("#tabs-" + idCode + " textarea[name='content']").val();
			if($.util.isBlank(content)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写指令类容。"}) ;
				flag = false;
				return false;
			}
			if(content.length > 300){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请填写1-300个字符。"}) ;
				flag = false;
				return false;
			}
			
			//验证反馈时间
			var date = $.laydate.getDate("#feedbackTime" + idCode, "date");
			if($.util.isBlank(date)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择要求反馈时间。"}) ;
				flag = false;
				return false;
			}
			
			//验证接收单位
			var tobj = $.zTreeMenu.getTree("unit" + idCode);
			var nodeArray = tobj.tree.getCheckedNodes();
			if(nodeArray.length < 1){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择接收单位。"}) ;
				flag = false;
				return false;
			}
			
			//验证是否通知领导
			var isNofityDepartmentLeader = $.icheck.val("tongzhi" + idCode);
			if($.util.isBlank(isNofityDepartmentLeader)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择是否通知本单位负责人。"}) ;
				flag = false;
				return false;
			}
		});
		return flag;
	}
	
	/**
	 * 获取页面指令数据
	 * 
	 * @param idCode 身份证号，唯一标识
	 * @returns
	 */
	function getinstructionBean(idCode){
		var instructionBean = new Object();
		if($.util.isBlank($.select2.val("#instructTypeSecond" + idCode))){
			instructionBean["type"] =  $.select2.val("#instructTypeFirst" + idCode);
		}else{
			instructionBean["type"] =  $.select2.val("#instructTypeSecond" + idCode);
		}
		instructionBean["content"] = $("#tabs-" + idCode + " textarea[name='content']").val();
		instructionBean["isNofityDepartmentLeader"] = $.icheck.val("tongzhi" + idCode);
		var tobj = $.zTreeMenu.getTree("unit" + idCode);
		var nodeArray = tobj.tree.getCheckedNodes();
		//指令接收主体
		var subs = new Array();
		$.each(nodeArray,function(n, node){
			var obj = new Object();
			obj.receiveSubjectType = $.common.XSFXTS_CONSTANT.UNIT_CLASS_NAME;
			obj.receiveSubjectId = node.diyMap.id;
			subs.push(obj);
		});
		instructionBean["subs"] = subs;
		
		//落地指令--人员盘查
		if($.select2.val("#instructTypeSecond" + idCode) == $.common.DICT.DICT_ZLLX_LDZL_RYPC){
			var typeContent = new Object();
			typeContent.name = $("#tabs-" + idCode + " input[name='personName']").val();
			typeContent.idcode = $("#tabs-" + idCode + " input[name='idCode']").val();
			typeContent.personLocation = $("#tabs-" + idCode + " input[name='personQuestionAddress']").val();
			instructionBean["typeContent"] = JSON.stringify(typeContent);
		}
		
		//落地指令--车辆盘查
		if($.select2.val("#instructTypeSecond" + idCode) == $.common.DICT.DICT_ZLLX_LDZL_RYPC){
			var typeContent = new Object();
			typeContent.name = $("#tabs-" + idCode + " input[name='personName']").val();
			typeContent.idcode = $("#tabs-" + idCode + " input[name='idCode']").val();
			typeContent.personLocation = $("#tabs-" + idCode + " input[name='personQuestionAddress']").val();
			instructionBean["typeContent"] = JSON.stringify(typeContent);
		}
		//要求反馈时间
		var date = $.date.strToDate($.laydate.getDate("#feedbackTime" + idCode, "date"), $.laydate.getFmt("#feedbackTime" + idCode));
		instructionBean["requireFeedbackTime"] = date.getTime();
		return instructionBean;
	}
	
	function save(){
		var arr = $.icheck.getChecked("personCheckbox");
		if(arr.length < 1){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请勾选预警人员。"}) ;
			return ;
		}
		var flag = verifyInstruction();
		if(flag){
			len = 0;
			$.each(arr,function(i,input){
				len++;
				var idCode = $(input).val();
				$.plupload.start("pcrUpload" + idCode);
			});
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.newInstruct, { 
		save : save
	});	
})(jQuery);