$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;
$.szpt.util.newnIstruction = $.szpt.util.newnIstruction || {} ;
(function($){
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var typeSetContent=initData.typeSetContent;
	var relatedObjectType;
	var relatedObjectId;
	var relateObjectContent;
	var ids="";
	var first = true;
	var types=[];
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		initDictionaryItem();
			if($.util.isBlank($.laydate.getDate("#backTime", "date"))){
				var dateTime = new Date().getTime();
				$.laydate.setDate($.date.dateToStr(new Date(dateTime + 1000*60*60*12), $.laydate.getFmt("#backTime")), "#backTime"); 
			}
			$("#fixed_end").val("");
		$(document).on("click" , "#unitName,#unitSearch", function(){
			if(first){
				$("#ztree-MenuContent-ul-unitName_1_switch").click();
				first = false;
			}
			$(".m-ui-layer-box")[0].style.height="830px";
			return false;
		});
		$(document).on("click" , ".m-ui-layer-box", function(){
			$(".m-ui-layer-box")[0].style.height="331px";
			return true;
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
			$.select2.val("#type2","0000000011004001",true);
			$.select2.able("#type2",false);
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
			url:context +'/newInstruct/findAllSubDictionaryItemsByTypeCode.action',
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
					$.select2.val("#type","0000000011004",true);
					$.select2.able("#type",false);
					typeSelected("0000000011004");
					$.validform.closeAllTips("validform");
				}
			},
			error:function(errorData){
				
			}
		})
	}

	
	/**
	 * 获取页面所有字段
	 */
	function getAllField(){
		var instructionBean = new Object();
		instructionBean.relatedObjectId = initData.idcard;
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
		var tobj = $.zTreeMenu.getTree("dwTree");
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
		var feedbackId = "";
		if($.util.exist(typeSetContent)){
			instructionBean["relatedObjectType"]=typeSetContent["relatedObjectType"];
			instructionBean["relatedObjectId"]=typeSetContent["relatedObjectId"];
			instructionBean["relateObjectContent"]=typeSetContent["relateObjectContent"];
			feedbackId = typeSetContent["feedbackId"];
			instructionBean["typeContent"] = feedbackId;
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
		if($.select2.val("#type2")==$.common.Constant.ZLLX_RYPC()&&($("#personLocation").val().length==0||$("#personLocation").val().length>200)){
			$.layerAlert.tips({
				msg:'请输入正确的盘查地点',
				selector:"#personLocation",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
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
		if($.util.isBlank($.icheck.val("tongzhi"))){
			$.layerAlert.tips({
				msg:'请选择是否通知',
				selector:"#tongzhi_1",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return ;
		}
		
		var instructionBean = getAllField();
		var dataMap = {};
		$.util.objToStrutsFormData(instructionBean, "instructionBean", dataMap);
		$.ajax({
			url:context +'/newInstruct/saveInstruction.action',
			type:'post',
			dataType:'json',
			data:dataMap,
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
	
	function init() {
		if($.util.exist(typeSetContent) && $.util.exist(typeSetContent.hideUnitIdArray)){
			$.szpt.util.businessData.initDwTree("dwTree", typeSetContent.hideUnitIdArray);
		}else{
			$.szpt.util.businessData.initDwTree("dwTree", null);
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.newnIstruction, { 
		save : save
	});	
})(jQuery);