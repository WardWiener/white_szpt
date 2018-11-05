(function($) {
	"use strict";
/**
 * 
 */
$(document).ready(function(){
	var win = $.util.rootWindow() ;
	var	data = win.$.common.getdata() ;
	initSelectedRoleTree();
	initSelectedRoleUnitTree();
	intiRoleTree(data.id);
    if(data.personBean != null){
    $("#name").text(data.personBean.name);
    }
    $("#danwei").text(data.unitName == null ?"":data.unitName);
	$("#username").text(data.accountName);
	$("#password").text(data.password);
	$("#beizhu").text(data.intro!=null?data.intro:"");
	$("#qixian").text(data.strStartDate+"至"+data.strEndDate);
	$("#zhuangtai").text(data.status);
    $("#orderCellName").text(data.orderCellName);
	
	$(document).on('click', "#add", function () {		
		$.layerAlert.dialog({
			content : context+'/userManage/add.action',
			pageLoading : true,
			width : "70%",
			height : "100%",
			title:"常用正则表达式",
			btn:["保存", "取消"],
			shadeClose : false,
			success:function(layero, index){

    		},
    		callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
    				btn1:function(index, layero){
    					var win = $.layerAlert.frameWindow(index) ;
	    				var flag=win.$.common.save() ;
	    				if(flag){
	    					layer.close(index);
	    				}
    					
				    },
				    // cancel默认代表btn数组中第二个名称的按钮和取消按钮并且点击会默认关闭层
				    btn2:function(index, layero){
				    	layer.close(index);		//关闭弹窗
				    }
				 }
		});
	});
	jQuery.extend($.common, { 
		setdata:function(full){
			data=full;	//从父页面获取当前弹出层   用于关闭
		},
	});	
	function intiRoleTree(code){
		$.ajax({
			url : context + "/userManage/roleTree.action",
			type : "POST",
			data : {
				"id" : code,
			},
			btn : "#deleteBtn",
			dataType : "json",
			success : function(data) {
				reAddAllNodes("ztree-roleTree", data.accountRoles);
			},
		});
		
	}
	function removeAllNodes(treeId){
		var treeTemp = $.fn.zTree.getZTreeObj(treeId);
		if(!$.util.exist(treeTemp)){
			return;
		}
		var nodes = treeTemp.getNodes();
		if(!$.util.exist(nodes)){
			return;
		}
		$.each(nodes, function(i, val){
			treeTemp.removeNode(val);
		});
	}

	function reAddAllNodes(treeId, nodes){
		removeAllNodes(treeId);
		var treeTemp = $.fn.zTree.getZTreeObj(treeId);
		if(!$.util.exist(treeTemp)){
			return;
		}
		//父节点Id，节点List，后边别管了
		treeTemp.addNodes(null, nodes, null, null);
	}
	function initSelectedRoleTree(){
		var setting = {
				view: {
					fontCss: getFontCss
				},
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						autoExpandTrigger: true
					}
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback : {
					beforeDrop: function(treeId, treeNodes, targetNode, moveType, isCopy) {
						return false ;
					}
				}
			};
		var zTreeSelected = $.fn.zTree.init($("#ztree-roleTree"), setting);
		zTreeSelected.lastSearchValue = "" ;
		zTreeSelected.nodeSearchList = [] ;
		zTreeSelected.fontSearchCss = {} ;
		
		$(document).on('focus', '#keySelected', function () {
			var key = $(this) ;
			 focusKey(key) ;
		});
		
		$(document).on('blur', '#keySelected', function () {
			var key = $(this) ;
			blurKey(key) ;
		});		
		
		$(document).on('propertychange input', '#keySelected', function () {
			var key = $(this) ;
			searchNode(key, "ztree-roleTree") ;
		});	
	}
	function getFontCss(treeId, treeNode){
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	function focusKey(key) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	
	function blurKey(key) {
		if (key.get(0).value == "") {
			key.addClass("empty");
		}
	}
	function updateNodes(highlight, treeId) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodeList = zTree.nodeSearchList ;
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
		}
	}
	function searchNode(key, treeId){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var value = $.trim(key.get(0).value);
		var keyType = "name";
		if (key.hasClass("empty")) {
			value = "";
		}
		if (zTree.lastSearchValue === value) return;
		zTree.lastSearchValue = value ;
		updateNodes(false, treeId); 
		if (value === "") {
			return;
		}
		
		zTree.nodeSearchList = zTree.getNodesByParamFuzzy(keyType, value);
		updateNodes(true, treeId);
	}
	
	function initSelectedRoleUnitTree(){
		var setting = {
				view: {
					fontCss: getFontCss
				},
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						autoExpandTrigger: true
					}
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback : {
					beforeDrop: function(treeId, treeNodes, targetNode, moveType, isCopy) {
						return false ;
					}
				}
			};
		var zTreeSelected = $.fn.zTree.init($("#ztree-roleunitTree"), setting);
		zTreeSelected.lastSearchValue = "" ;
		zTreeSelected.nodeSearchList = [] ;
		zTreeSelected.fontSearchCss = {} ;
		
		$(document).on('focus', '#keySelected', function () {
			var key = $(this) ;
			 focusKey(key) ;
		});
		
		$(document).on('blur', '#keySelected', function () {
			var key = $(this) ;
			blurKey(key) ;
		});		
		
		$(document).on('propertychange input', '#keySelected', function () {
			var key = $(this) ;
			searchNode(key, "ztree-roleunitTree") ;
		});	
	}
});



})(jQuery);