;(function($){
"use strict";
var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
var pageIndex = frameData.index ;//当前弹窗index	
function initSelect(){
	$.ajax({
		url: context + '/dictionaryType/initDictItemStatus.action',
		type:"POST",
		dataType:"json",
		data:{
			dictionaryType:constantStauts
		},
		success:function(data){
			$.select2.addByList("#itemStatus", data.dictionaryItemLst,"code","name");
		},
	})
}
$(document).ready(function() {	
	var itemId ;
	var searchingTypeId ;
	var itemName;
	var itemCode;
	var itemDescription;
	var flag;
	initSelect();
	document.onkeydown = function () {
        if (window.event && window.event.keyCode == 13) {
         return false;
        }
    }
	$(document).ready(function() {
		var p$ = $.util.topWindow().$ ;
		var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
		var index = frameData.index ;
		var initData = frameData.initData ;
		itemId = initData.itemId;
		//编辑
		if(initData.type == "modify"){
			$.ajax({
				url:context+'/dictionaryType/lookOverItem.action',
				type:"POST",
				data:{"itemId":itemId},
				dataType:"json",
				success:function(data){
					var item = data.dicItemBean;
					//modifyId = data.dicItem.id;
					$("#itemName").val(item.name);
					$("#itemCode").val(item.code);
					$("#itemdescription").val(item.description);
					if(item.state=="启用"){
						$("#itemStatus").val(constantStauts_qy);
					}else{
						$("#itemStatus").val(constantStauts_ty);
					}
					
				}
			});
		}
		
		else if(initData.type == "detail"){
			//点击查看数据字典项
				$.ajax({
					url:context+'/dictionaryType/detailItem.action',
					type:"POST",
					data:{"itemId":itemId},
					dataType:"json",
					success:function(data){
						var item=data.dicItem;
						$("#itemName").val(item.name);
						$("#itemCode").val(item.code);
						$("#itemdescription").val(item.description);
						$("#itemStatus").val(item.state)
						$("#itemName").attr("readonly","readonly");
						$("#itemCode").attr("readonly","readonly");
						$("#itemdescription").attr("readonly","readonly");
						$("#itemStatus").attr("readonly","readonly");
					}
				});
		}
		//更新方法
		function updateObj(){
			// XIEHF 20100225 修改。统一使用框架的数据校验。
			
			var demo = $.validform.getValidFormObjById("validformId") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return;
			}
			var dataMap = new Object();
			dataMap["dicItemBean.name"] = $("#itemName").val();
			dataMap["dicItemBean.code"] = $("#itemCode").val();
			dataMap["dicItemBean.description"] = $("#itemdescription").val();
			dataMap["dicItemBean.dicTypeId"] = initData.searchingTypeId;
			dataMap["dicItemBean.id"] = itemId;
			dataMap["dicItemBean.state"] = $("#itemStatus").val();
			$.ajax({
				url: context + '/dictionaryType/updateItem.action',
				type:"POST",
				data:dataMap,
				dataType:"json",
				success:function(data){
					if(data.flag=="false"){
						$.util.topWindow().$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
					}else{
						$.util.topWindow().$.layerAlert.alert({msg:"修改成功。",title:"提示",icon:6,yes:function(){
							$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex)
	    				}})
					}
					
				}
			});
		}
		//新增保存方法
		function saveObj() {
			// XIEHF 20100225 修改。统一使用框架的数据校验。
			var demo = $.validform.getValidFormObjById("validformId") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return;
			}			
			var dataMap = new Object();
			dataMap["dicItemBean.name"] = $("#itemName").val();
			dataMap["dicItemBean.code"] = $("#itemCode").val();
			dataMap["dicItemBean.description"] = $("#itemdescription").val();
			dataMap["dicItemBean.dicTypeId"] = initData.searchingTypeId;
			dataMap["dicItemBean.parentItemId"] = itemId;
			dataMap["dicItemBean.state"] = $("#itemStatus").val();
			$.ajax({
				url: context + '/dictionaryType/saveItem.action',
				type:"POST",
				data:dataMap,
				dataType:"json",
				success:function(data){
					if(data.flag=="false"){
						$.util.topWindow().$.layerAlert.alert({msg:data.msg,title:"提示",icon:5,yes:function(){
	    				}})
					}else{
						$.util.topWindow().$.layerAlert.alert({msg:"保存成功。",title:"提示",icon:6,yes:function(){
							$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex)
	    				}})
					}
	    		},
	    		error:function(){
	    			$.util.topWindow().$.layerAlert.alert({msg:"保存失败.",title:"提示",icon:5});
	    		}
			})
		}
		jQuery.extend($.common, { 
			save:function(){
				saveObj();
			},
			update:function(){
				updateObj();
			}
		});	
    });
})
})(jQuery);	