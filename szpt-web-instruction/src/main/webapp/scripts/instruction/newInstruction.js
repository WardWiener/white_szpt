$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;
$.szpt.util.newnIstruction = $.szpt.util.newnIstruction || {} ;
(function($){
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var typeTemp=initData.type;
	var typeSetContent=initData.typeSetContent;
	var relatedObjectType;
	var relatedObjectId;
	var relateObjectContent;
	var ids="";
	var first = true;
	var types=[];
	
	//文件上传
	var setting = $.plupload.getBasicSettings() ;
	
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		initDictionaryItem();
		if(!$.util.isBlank(id)){
			initPageField();
		}else{
			if($.util.isBlank($.laydate.getDate("#backTime", "date"))){
				var dateTime = new Date().getTime();
				$.laydate.setDate($.date.dateToStr(new Date(dateTime + 1000*60*60*12), $.laydate.getFmt("#backTime")), "#backTime"); 
			}
			$("#fixed_end").val("");
		}
//		initPageField();
		//initZtree();
		$(document).on("click" , "#unitName,#unitSearch", function(){
			if(first){
				$("#ztree-MenuContent-ul-unitName_1_switch").click();
				first = false;
			}
			//var tree = $.zTreeMenu.getTree("unitName") ;
			//tree.showMenu() ;
			$(".m-ui-layer-box")[0].style.height="830px";
			return false;
		});
		$(document).on("click" , ".m-ui-layer-box", function(){
			$(".m-ui-layer-box")[0].style.height="331px";
			return true;
		});
		
		$(document).on("select2:unselect", "#type", function(e){	
			$.select2.clear("#type2",true);
			$("#type2").empty();
			$("#type2Div").hide();
		});
		$(document).on("select2:select", "#type", function(e){	
			typeSelected(this.value);
		});
		
		$(document).on("select2:select", "#type2", function(e){	
			type2Selected(this.value);
		});
		
		$(document).on("select2:unselect", "#type2", function(e){	
			$("#personDiv").hide();
			$("#carDiv").hide();
		});
		
		initPlupload();
	});
	
	function typeSelected(selectedValue){
		findSubItems(selectedValue);
		if($("#type2Div").css("display") == "none" || $.util.isBlank($.select2.val("#type2"))){
			$("#personDiv").hide();
			$("#carDiv").hide();
		}
	}
	
	function type2Selected(selectedValue){
		if(selectedValue==$.common.Constant.ZLLX_RYPC()){
			$("#personDiv").show();
		}else{
			$("#personDiv").hide();
		}
		if(selectedValue==$.common.Constant.ZLLX_CLPC()){
			$("#carDiv").show();
		}else{
			$("#carDiv").hide();
		}
	}
	
	function findSubItems(code){
		var list=[];
		var itemId;
		for(var i in types){
			if(types[i].code==code){
				itemId=types[i].id;
			}
		}
		for(var i in types){
			if(types[i].parentItemId==itemId){
				list.push(types[i]);
			}
		}
		$.select2.empty("#type2");
		if(list.length!=0){
			$.select2.addByList("#type2", list,"code","name",true,true);
			
			//刑事分析态势-嫌疑人mac地址分析页面发送指令设置默认值
			if(typeTemp == "xsfxts-macAnalysis" && $.util.exist(typeSetContent)){
				$.select2.val("#type2",typeSetContent.secondZllx);
				type2Selected(typeSetContent.secondZllx);
			}
			if(typeTemp == "gwrqfx-ryda-ck" && $.util.exist(typeSetContent)){
				$.select2.val("#type2",typeSetContent.secondZllx);
				type2Selected(typeSetContent.secondZllx);
			}
			$("#type2Div").show();
		}else{
			$("#type2Div").hide();
		}
		$.validform.closeAllTips("validform");
	}
	function ifHasSubItems(code){
		var itemId;
		var flag=false;
		for(var i in types){
			if(types[i].code==code){
				itemId=types[i].id;
			}
		}
		for(var i in types){
			if(types[i].parentItemId==itemId){
				flag = true;
				break;
			}
		}
		return flag;
	}
	function isParent(code){
		var flag=false;
		for(var i in types){
			if(types[i].code==code&&types[i].parentItemId==null){
				flag=true
			}
		}
		return flag;
	}
	function findParent(code){
		var parentCode="";
		var parentItemId;
		for(var i in types){
			if(types[i].code==code){
				parentItemId=types[i].parentItemId;
			}
		}
		for(var i in types){
			if(types[i].id==parentItemId){
				parentCode = types[i].code;
				break;
			}
		}
		return parentCode ;
	}

	function initDictionaryItem(){

		$.ajax({
			url:context +'/webUtil/findAllSubDictionaryItemsByTypeCode.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.ZLLX()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					types=successData.dictionaryItemLst;
					var list=[];
					for(var i in successData.dictionaryItemLst){
						if(successData.dictionaryItemLst[i].parentItemId==null){
							list.push(successData.dictionaryItemLst[i]);
						}
					}
					$.select2.addByList("#type", list,"code","name");//设置下拉框选项
					$.validform.closeAllTips("validform");
					if($.util.exist(typeTemp) && typeTemp=="zhzats"){
						//设置指令类型
						$.select2.val("#type",typeSetContent.type);
						typeSelected(typeSetContent.type);
						$("#dwTree").val(typeSetContent.unitName);
						//$.select2.val("#dwTree",typeSetContent.dwTree,true);//设置指令接收单位
						$("#content").val(typeSetContent.content);//设置指令内容
						var dateTime = new Date().getTime();
						$.laydate.setDate($.date.dateToStr(new Date(dateTime + 1000*60*60*12), $.laydate.getFmt("#backTime")), "#backTime"); 
						$("#tongzhi_1").iCheck('check');
					}
					
					if(typeTemp == "xsfxts-macAnalysis" && $.util.exist(typeSetContent)){
						$.select2.val("#type",typeSetContent.firstZllx);
						typeSelected(typeSetContent.firstZllx);
						$("#name").val(typeSetContent.name);
						$("#idcode").val(typeSetContent.idcode);
						$("#personLocation").val(typeSetContent.localAddressDetail);
						$("#content").val(typeSetContent.content);
						var dateTime = new Date().getTime();
						$.laydate.setDate($.date.dateToStr(new Date(dateTime + 1000*60*60*12), $.laydate.getFmt("#backTime")), "#backTime"); 
						$("#tongzhi_0").iCheck('check');
					}
					
					if(typeTemp == "xsfxts-yayyp-zlxf" && $.util.exist(typeSetContent)){
						$.laydate.setDate(typeSetContent.requireFeedbackTime, "#backTime"); 
					}
					
					if(typeTemp == "gwrqfx-ryda-ck" && $.util.exist(typeSetContent)){
						$.select2.val("#type",typeSetContent.firstZllx);
						typeSelected(typeSetContent.firstZllx);
						$("#name").val(typeSetContent.name);
						$("#idcode").val(typeSetContent.idcode);
					}
				}
			},
			error:function(errorData){
				
			}
		})
	}
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(id)){//修改
			$.ajax({
				url:context +'/instructionManage/findInstruction.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var instructionBean = successData.instructionBean;
					if(instructionBean!=null){
						$("#content").val(instructionBean.content);
						if(isParent(instructionBean.type)){
							$.select2.val("#type",instructionBean.type,true);
						}else{
							$.select2.val("#type",findParent(instructionBean.type),true);
							var list=[];
							var obj={
									"code":instructionBean.type,
									"name":instructionBean.typeName
							}
							list.push(obj);
							$.select2.empty("#type2");
							$.select2.addByList("#type2", list,"code","name");
							$.select2.val("#type2",instructionBean.type,true);
							$("#type2Div").show();
						}
						$.common.typeContentCommon.setTypeContentValue(instructionBean);
//						$("#unitName").val(instructionBean.names);
						ids=instructionBean.ids;
//						$.laydate.setTime(instructionBean.requireFeedbackTime,"#backTime"); 
						var strDate=$.date.timeToStr(instructionBean.requireFeedbackTime,"yyyy-MM-dd HH:mm");
						$.laydate.setDate(strDate,"#backTime"); 
						$.icheck.check("#tongzhi_"+instructionBean.isNofityDepartmentLeader,true);
						$("#content").attr("disabled","disabled");
						$.select2.able("#type",false);
						$.select2.able("#type2",false);
						$("#backTime").addClass("date-disabled");
						$.icheck.able(".icheckradio",false);
						$("input").attr("readonly","readonly");
					}
				},
				error:function(errorData){
					
				}
			});
		}
	}
	
	/**
	 * 获取页面所有字段
	 */
	function getAllField(){
		var instructionBean = new Object();
		instructionBean["id"] = $.util.isBlank(id)?null:id;
		if(ifHasSubItems($.select2.val("#type"))){
			instructionBean["type"] =  $.select2.val("#type2");
		}else{
			instructionBean["type"] =  $.select2.val("#type");
		}
		if($.select2.val("#type")!=$.common.Constant.ZLLX_YPJGTSZL()){
			var date = $.date.strToDate($.laydate.getDate("#backTime", "date"), $.laydate.getFmt("#backTime"));
			instructionBean["requireFeedbackTime"] = date.getTime(); 
		}
		instructionBean["content"] = $("#content").val();
		instructionBean["isNofityDepartmentLeader"] = $.icheck.val("tongzhi");
		//var list=$.szpt.util.businessData.getCheckedByTree("dwTree");
		var tobj = $.zTreeMenu.getTree("dwTree");
		var nodeArray = tobj.tree.getCheckedNodes();
		/*var unitId="";
		if($.util.exist(list) &&list.length>0 ){
			for(var i=0;i<list.length;i++){
				unitId=list[i].id+','+unitId;
			}	
		}
		instructionBean["unitId"] = unitId;*/
		//$.szpt.util.businessData.getCheckedByTree("dwTree").ids;
		//指令接收主体
		var subs = new Array();
		$.each(nodeArray,function(n, node){
			var obj = new Object();
			obj.receiveSubjectType = $.common.XSFXTS_CONSTANT.UNIT_CLASS_NAME;
			obj.receiveSubjectId = node.diyMap.id;
			subs.push(obj);
		});
		instructionBean["subs"] = subs;
		
		var feedbackId = "";
		if($.util.exist(typeSetContent)){
			instructionBean["relatedObjectType"]=typeSetContent["relatedObjectType"];
			instructionBean["relatedObjectId"]=typeSetContent["relatedObjectId"];
			instructionBean["relateObjectContent"]=typeSetContent["relateObjectContent"];
			feedbackId = typeSetContent["feedbackId"];
			instructionBean["typeContent"] = feedbackId;
		}
		
		if($.select2.val("#type2")==$.common.Constant.ZLLX_RYPC()){
			var typeContent = new Object();
			typeContent.name=$("#name").val();
			typeContent.idcode=$("#idcode").val();
			typeContent.personLocation=$("#personLocation").val();
			typeContent.feedbackId = feedbackId;
			instructionBean.typeContent=JSON.stringify(typeContent);
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_CLPC()){
			var typeContent = new Object();
			typeContent.carNum=$("#carNum").val();
			typeContent.carLocation=$("#carLocation").val();
			typeContent.feedbackId = feedbackId;
			instructionBean.typeContent=JSON.stringify(typeContent);
		}
		return instructionBean;
	}
	
	
	/**
	 * 保存
	 */
	function save(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		if(ifHasSubItems($.select2.val("#type"))&&$.select2.val("#type2")==undefined){
			$.layerAlert.tips({
				msg:'请选择指令类型',
				selector:"#type2",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_RYPC()&&($("#name").val().length==0||$("#name").val().length>10)){
			$.layerAlert.tips({
				msg:'请输入正确的姓名',
				selector:"#name",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_RYPC()&&!($("#idcode").val().length==18 || $("#idcode").val().length==15)){
			$.layerAlert.tips({
				msg:'请输入正确的身份证号',
				selector:"#idcode",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_RYPC()){
			if($("#personLocation").val().length==0 ){
				$.layerAlert.tips({
					msg:'盘查地点不能为空',
					selector:"#personLocation",
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return ;
			}else if($("#personLocation").val().length>80){
				$.layerAlert.tips({
					msg:'您输入的盘查地点过长',
					selector:"#personLocation",
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return ;
			}
			
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_CLPC()&&($("#carNum").val().length==0||$("#carNum").val().length>20)){
			$.layerAlert.tips({
				msg:'请输入正确的车牌号',
				selector:"#carNum",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.select2.val("#type2")==$.common.Constant.ZLLX_CLPC()&&($("#carLocation").val().length==0||$("#carLocation").val().length>200)){
			$.layerAlert.tips({
				msg:'请输入正确的盘查地点',
				selector:"#carLocation",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		var dateTime = $.date.strToDate($.laydate.getDate("#backTime", "date"), $.laydate.getFmt("#backTime")).getTime()
		if($.select2.val("#type")!=$.common.Constant.ZLLX_YPJGTSZL()&&$.util.isBlank(dateTime)){
			$.layerAlert.tips({
				msg:'请选择反馈时间',
				selector:"#fixed_end",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.select2.val("#type")!=$.common.Constant.ZLLX_YPJGTSZL()&&new Date().getTime()>dateTime){
			$.layerAlert.tips({
				msg:'请选择正确的反馈时间',
				selector:"#fixed_end",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		if($.util.isBlank($.zTreeMenu.getCheckedNodes("dwTree"))){
			$.layerAlert.tips({
				msg:'请选择接收单位',
				selector:"#dwTree",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
//		if($.util.isBlank($.icheck.val("tongzhi"))){
//			$.layerAlert.tips({
//				msg:'请选择是否通知',
//				selector:"#tongzhi_1",
//				color:'#FF0000',
//				position:2,
//				closeBtn:2,
//				time:2000,
//				shift:1
//			});
//			return ;
//		}
		flag=true;
		$.each($.zTreeMenu.getCheckedNodes("dwTree"), function(i,item){
			if(ids.indexOf(","+item.id+",")>-1){
				$.layerAlert.tips({
					msg:item.name+'已下发此项指令，不能重复下发',
					selector:"#dwTree",
					color:'#FF0000',
					position:1,
					closeBtn:2,
					time:2000,
					shift:1
				});
				flag=false;
				return;
			}
		 }); 
		if(!flag){
			return ;
		}
		$.plupload.start("pcrUpload");
	}
	
	function init() {
		if($.util.exist(typeSetContent)){
			if(!$.util.exist(typeSetContent)){
				$.szpt.util.businessData.initDwTree("dwTree", null);
			}else if($.util.exist(typeSetContent.hideUnitIdArray)){
				$.szpt.util.businessData.initDwTree("dwTree", typeSetContent.hideUnitIdArray);
			}else if(typeSetContent.unitCode != "all"){
				$.szpt.util.businessData.initDwTree("dwTree", null, typeSetContent.unitCode);
				$("#dwTree").val(typeSetContent.unitName);
			}else{
				$.szpt.util.businessData.initDwTree("dwTree");
			}
		}else{
			$.szpt.util.businessData.initDwTree("dwTree", null);
		}
	}
	
	/**
	 * 初始化附件上传
	 * 
	 * @returns
	 */
	function initPlupload(){
		setting.container = "pcrUpload" ; //容器id
		setting.url = context + "/instructionManage/uploadFile.action";
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
				saveInstruction(fileObjectBeans);
			}
		}
		$.plupload.init(setting) ;
	}
	
	/**
	 * 保存指令
	 * 
	 * @param fileObjectBeans 附件Bean集合
	 * @returns
	 */
	function saveInstruction(fileObjectBeans){
		var instructionBean = getAllField();
		var dataMap = {};
		$.util.objToStrutsFormData(instructionBean, "instructionBean", dataMap);
		$.util.objToStrutsFormData(fileObjectBeans, "fileObjectBeans", dataMap);
		
		$.ajax({
			url:context +'/instructionManage/saveInstruction.action',
			type:'post',
			dataType:'json',
			data:dataMap,
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if($.util.exist(typeSetContent) && $.util.isFunction(typeSetContent.callbackFunction)){
					typeSetContent.callbackFunction();
				}
				window.top.$.layerAlert.alert({msg:'指令下发成功！',title:"温馨提示",icon : 6,time:3000});
				$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
			},
			error:function(errorData){
				window.top.$.layerAlert.alert({msg:'指令下发失败！',title:"温馨提示",icon : 5,time:3000});
			}
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.newnIstruction, { 
		save : save
	});	
})(jQuery);