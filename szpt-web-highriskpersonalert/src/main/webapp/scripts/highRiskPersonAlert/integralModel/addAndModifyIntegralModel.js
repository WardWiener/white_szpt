$.szpt = $.szpt || {} ;
$.szpt.integralModel = $.szpt.integralModel || {} ;
$.szpt.integralModel.addAndModify  = $.szpt.integralModel.addAndModify || {} ;
(function($){
	"use strict";
	
	var table=null;
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var dictionaryItem = null;
	var integralModel = null;
	var msg="保存";
	$(document).ready(function() {
		initNum();
		//新增操作
		if(initData.add==true){
			
		}else{	//修改操作
			msg = "修改";
			integralModel = initData.integralModelData;
			setValue(integralModel);
		}
	});
	
	/**
	 * 取值做保存或修改操作
	 */
	function getValue(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;     //判断是否有没有通过的验证
		if(!flag){
		   return;
		}
		var qzValue = 0;
		$.each($(".qz"),function(i,val){
			qzValue+=parseInt($(this).val());
		});
		if(qzValue!=100){
			window.top.$.layerAlert.alert({msg:"各项权重之和不等于100！",title:"提示"});
			return;
		}
//		var qzfdValue = 0;
//		$.each($(".qzfd"),function(i,val){
//			qzfdValue+=parseInt($(this).val());
//		});
//		if(qzfdValue!=100){
//			window.top.$.layerAlert.alert({msg:"各项权重封顶之和不等于100！",title:"提示"});
//			return;
//		}
		if($("#state").val()==$.common.DICT.DICT_ENABLED){
			$.layerAlert.confirm({
	    		msg:"启用本模版后上一模板将停用，您确定要进行启用操作？",
	    		title:"提示",
	    		yes:function(index, layero){
	    			saveOrUpdate()
				}
			});
		}else{
			saveOrUpdate()
		}
	}
	
	/**
	 * 保存或更新
	 */
	function saveOrUpdate(){
		var dataMap = new Object();
		var dataMapResult = new Object();
		dataMap.num = $("#num").val();
		dataMap.name = $("#name").val();
		dataMap.status = $("#state").val();
		dataMap.alertPoint = $("#alertPoint").val();
		dataMap.note = $("#remark").val();
		var url = "/integralModel/saveIntegralModel.action";
		if(initData.add==false){
			dataMap.id = integralModel.id;
			url="/integralModel/updateIntegralModel.action";
		}
		var arr = new Array();
		$.each($(".valCell"),function(i,val){
			var rule = new Object();
			rule.key=$(this).attr("valName");
			rule.value=$(this).val();
			arr.push(rule);
		});
		dataMap.integralModelRule = arr;
		$.util.objToStrutsFormData(dataMap,"integralModelBean",dataMapResult);
		$.ajax({
			url:context + url,
			type:'post',
			dataType:'json',
			shadeClose : false,
			data:dataMapResult,
			success:function(successData){
				var flag = successData.flag;
				if(flag){
					window.top.$.layerAlert.alert({msg:msg+"成功！",title:"提示",time:1500});
					window.top.$.layerAlert.closeWithLoading(pageIndex);    //关闭弹出页面
				}else{
					window.top.$.layerAlert.alert({msg:msg+"失败！",title:"提示"});
				}
			}
		});
	}
	
	/**
	 * 设置值
	 */
	function setValue(integralModel){
		var integralModelRule = integralModel.integralModelRule;
		$("#num").val(integralModel.num);
		$("#name").val(integralModel.name);
		$.select2.val("#state",integralModel.status);
		$("#alertPoint").val(integralModel.alertPoint);
		$("#remark").val(integralModel.note);
		$("#modifyPeople").text(integralModel.modifyPeopleName);
		$("#modifyTime").text(integralModel.modifyTime);
		$.each($(".valCell"),function(i,val){
			$.each(integralModelRule,function(y,itm){
				if(itm.key == $(val).attr("valName")){
					$(val).val(itm.value);
				}
			})
		});
	}
	
	/**
	 * 初始化
	 */
	function initNum(){
		$.ajax({
			url:context +'/integralModel/generateNumber.action',
			type:'post',
			dataType:'json',
			success:function(successData){
				var num = successData.num;
				dictionaryItem = successData.dictionaryItem;
				$.select2.addByList("#state", dictionaryItem, "code", "name", true, true); 
				$.select2.val("#state",$.common.DICT.DICT_ENABLED,true);//设置默认值
				if(initData.add == false){
//					alert("11");
					$.select2.val("#state",integralModel.status);
				}
				if(initData.add==true){
					$("#num").val(num);
				}
			}
		});
	}
	
	
	jQuery.extend($.szpt.integralModel, { 
		getValue : getValue
	});	

})(jQuery);