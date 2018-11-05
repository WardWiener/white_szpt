(function($) {
	"use strict";
var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	
	var pageIndex = frameData.index ;//当前弹窗index
	var number;		//当前弹出层
	var bj;			//当前处于那个阶段 ：  修改  复制  新增
	var roleId;
	$(document).ready(function() {
	
		if(!+"\v1"){
			var win = $.util.rootWindow() ;		//找父级页面
			var	flag = win.$.common.getParentObj() ;	//取父级页面数据
			var	ids = win.$.common.getParentObj1() ;
			bj=flag;
			roleId=ids;
			modifyRoleInit();
		}else{
			jQuery.extend($.common, { 
				setTestObj2:function(index){
					number=index;	//从父页面获取当前弹出层   用于关闭
				},
				setTestObj1:function(index,id){
					bj=index;	//判断属于那个阶段
					roleId=id;
					modifyRoleInit();
				}
			});	
		}
		initSelect2();
	});
	
	/**修改资源初始化*/
	function modifyRoleInit(){
		if(roleId!=null){
			$.ajax({url : context + '/resource/queryResourceById.action',
				type : "POST",
				dataType : "json",
				data : {
					"resourceBean.id" :roleId,
				},
				success : function(data) {
					 $("#pa_newPage_name").val(data.resourceBean.resourceName);
					 $("#pa_newPage_type").val(data.resourceBean.type);
					 $("#pa_newPage_link").val(data.resourceBean.resourceUrl);
				}
			});
		}
	}
	
	
	//$(document).ready(function(){});
	var ma;
	
	/**
	 * 保存
	 */
	$(document).on('click','#pa_newPage_save',function(){
	
		var demo = $.validform.getValidFormObjById("pa_newPage") ;//取到最外层div的根据id
		var flag = $.validform.check(demo) ;
		var dataMap=new Object();
		dataMap["resourceBean.type"]=$("#pa_newPage_type").val();
		dataMap["resourceBean.resourceName"]=$("#pa_newPage_name").val();
		dataMap["resourceBean.resourceUrl"]=$("#pa_newPage_link").val();
		if(!$.util.isEmpty(roleId)){
			dataMap["resourceBean.id"]=roleId;
			var flagName=veryMessage(dataMap);
			if(flag&&flagName){
				$.ajax({url : context + '/resource/updateResource.action',
				type : "POST",
				async: true,
				dataType : "json",
				data :dataMap,
				success : function(data) {
					if(data.flag == "true"){
						$.util.topWindow().$.layerAlert.alert({msg:"修改成功。",title:"提示",icon:6,yes:function(){
							$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex)
	    				}})
						
					}else{
						alertHint(data.msg,5);
						return;
					}
				}
			});
		  }
			
		}else{
			var flagName=veryMessage(dataMap);
			if(flag&&flagName){
				$.ajax({url : context + '/resource/saveResource.action',
					type : "POST",
					async: true,
					dataType : "json",
					data : dataMap,
					success : function(data) {
						if(data.flag == "true"){
							$.util.topWindow().$.layerAlert.alert({msg:"保存成功。",title:"提示",icon:6,yes:function(){
								$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex)
		    				}})
						}else{
							alertHint(data.msg,5);
							return;
						}
					}
				});
			}
			
		}
		
	});
	
	function veryMessage(dataMap){
	  var	flag=false;
		$.ajax({url : context + '/resource/veryResource.action',
			type : "POST",
			async: false,
			dataType : "json",
			data : dataMap,
			success : function(data) {
				if(data.flag == "false"){
					$.layerAlert.alert({msg:"名称重复！"});
				}else
					{
					flag=true;
					}
				
			}
		});
		return flag;
	}
	/**
	 * 取消
	 */
	$("#pa_newPage_cancel").on("click",function(){//取消
		$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
	});
function initSelect2(){
		    	$.ajax({
		    		    url: context + '/resource/queryResourceType.action',
			    		type:"POST",	
			    		data:{
			    		"dictionaryType":constantResourceType//字典项编码
			    		},
			    		dataType:"json",
			    		async:false,
			    		success:function(data){
			    			$.select2.addByList("#pa_newPage_type", data.dictionaryItemLst,"code","name");

			    		}
		    	});
		    }
	
function alertHint(msg,icon){
		$.layerAlert.alert({
			msg:msg,
			title:"提示",		//弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:icon,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,			//弹出时的动画效果  有0-6种
		});
	}
})(jQuery);