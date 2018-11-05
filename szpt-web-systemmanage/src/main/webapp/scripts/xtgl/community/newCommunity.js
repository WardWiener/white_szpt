(function($){
	"use strict";
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData;
	$(document).ready(function(){
		intiSelectUnitTree();
		initBigCommunitySelect();
	});
	
	function initBigCommunitySelect(){
		 $.ajax({
	            url: context + '/community/initSelect.action',
	            type:"POST",  
	            dataType:"json",
	            success:function(json){
	            	$.select2.addByList("#bigCommunity", json.dictionaryItemBeanList, "id", "name", true, true);    //初始化
	            }
	        });
	}
	
	/**
	 * 保存
	 */
	function saveCommunity(){
		var demo = $.validform.getValidFormObjById("validformId") ;
		var flag = $.validform.check(demo) ;     //判断是否有没有通过的验证
		  if(!flag){
		   return;
		  }
		  var dataMap = new Object();
		  dataMap["commBean.name"] = $("#communityName").val();
		  dataMap["commBean.code"] = $("#communityCode").val();
		  dataMap["commBean.bigCommunityId"] = $("#bigCommunity").val();
		  dataMap["commBean.unitId"] = $("#unitId").val();
		  $.ajax({
	            url: context + '/community/creadCommunity.action',
	            type:"POST",  
	            dataType:"json",
	            data:dataMap,
	            success:function(json){
	                if(json.flag){
	                	$.util.topWindow().$.layerAlert.alert({msg:json.msg,title:"提示",time:1500});
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);    //关闭弹出页面
	                }else{
	                	$.util.topWindow().$.layerAlert.alert({msg:json.msg,title:"提示"});
	                }
	            }
	        });
	}
	
	/****************************************下面是单位选择树的相关操作*********************************************/
	//选择单位树setting
	var settingSelect = {
			view: {
				fontCss: getFontCss
			},
			async: {
				enable: true,
				global: false,
				url:context+"/unit/initUnitTree.action",
				autoParam:["id=unitId"],
				dataFilter: function(treeId, parentNode, childNodes) {
					return childNodes.unitTree;
				}
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId"
				}
			},
			callback:{
				onClick : function(event, treeId, treeNode) {
						$("#unitName").val(treeNode.name);
						$("#unitId").val(treeNode.id);
						hideMenu();
						$.validform.closeAllTips("validformId") ;
				}
			}
		};
	
	function intiSelectUnitTree() {
		//单位选择树
		var treeSelect = $.fn.zTree.init($("#ztree-unitSelect"), settingSelect);
		treeSelect.lastSearchValue = "" ;
		treeSelect.nodeSearchList = [] ;
		treeSelect.fontSearchCss = {} ;
		
		$(document).on('focus', '#keySelect', function () {
			var key = $(this) ;
			 focusKey(key) ;
		});
		$(document).on('blur', '#keySelect', function () {
			var key = $(this) ;
			blurKey(key) ;
		});		
		
		$(document).on('keyup change', '#keySelect', function () {
			var key = $(this) ;
			searchNode(key, "ztree-unitSelect") ;
		});
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
			zTree.expandNode(nodeList[i], true, true, true);
			zTree.selectNode(nodeList[i]);
			zTree.updateNode(nodeList[i]);
		}
	}
	function searchNode(key, treeId){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var value = $.trim(key.get(0).value);
		if (value === "") {
			var nodes = zTree.getNodesByParam("isHidden", true);
			zTree.showNodes(nodes);
			zTree.expandAll(false);
			return;
		}
		var keyType = "name";
		if (key.hasClass("empty")) {
			value = "";
		}
		zTree.lastSearchValue = value ;
		updateNodes(false, treeId); 
		zTree.nodeSearchList = zTree.getNodesByParamFuzzy(keyType, value);
		updateNodes(true, treeId);
	}
	
	function getFontCss(treeId, treeNode){
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	/**
	 * 清空树节点
	 */
	function removeAllNodes(treeId){
		var treeTemp = $.fn.zTree.getZTreeObj(treeId);
		if(!$.util.exist(treeTemp)){
			return;
		}
		var nodes = treeTemp.getNodes();
		if(!$.util.exist(nodes)){
			return;
		}
		for(var i = nodes.length - 1; i >= 0; i--){
			treeTemp.removeNode(nodes[i]);
		}
	}
	/**
	 * 重新添加树的节点
	 */
	function reAddAllNodes(treeId, nodes){
		removeAllNodes(treeId);
		var treeTemp = $.fn.zTree.getZTreeObj(treeId);
		if(!$.util.exist(treeTemp)){
			return;
		}
		treeTemp.addNodes(null, nodes, null, null);
	}
	$(document).on("click", ".unit", function(){
	showMenu();
	});
	function showMenu() {
		var obj = $("#unitName");
		var oOffset = $("#unitName").offset();
		$("#menuContent").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
		$("body").on("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").off("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		$.validform.closeAllTips("validformId") ;
		if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
			$.validform.closeAllTips("validformId") ;
		}
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		saveCommunity : saveCommunity
	});
})(jQuery);