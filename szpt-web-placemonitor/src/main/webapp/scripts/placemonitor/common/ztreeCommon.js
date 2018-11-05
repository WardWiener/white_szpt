(function($){
	"use strict";
	var criminalRecordZtree;//人员类别树
	
	$(document).ready(function() {	
		/**
		 * 单位树搜索按钮点击事件
		 */
		$(document).on("click","#criminalRecordName",function(){
			showMenu();
		});
	});
	
	var criminalRecordSetting = {
		view:{
			selectedMulti:false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check : {
			enable : true,
			chkStyle: "checkbox",
			radioType: "all",
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		},
		callback:{
			onCheck : function(event, treeId, treeNode) {
				var pNode = treeNode.getParentNode();
				
				var nodes = criminalRecordZtree.getCheckedNodes(true);
				for(var i in nodes){
					if(null != pNode && pNode != nodes[i].getParentNode()){
						criminalRecordZtree.checkNode(nodes[i], false, false);
					}else if(null == pNode && null !=  nodes[i].getParentNode()){
						criminalRecordZtree.checkNode(nodes[i], false, false);
					}
				}
				
				
				var selectedItemArray = getCheckedValue();
				var name = "";
				$.each(selectedItemArray,function(o,oib){
					name += oib.name + ",";
				});
				$("#criminalRecordName").val(name.substring(0,name.length-1));
			}
		}
	};
	
	/**
	 * 获取选中结果
	 * @returns selectedItemArray:选中字典项Bean集合
	 */
	function getCheckedValue(){
		var treeObj = $.fn.zTree.getZTreeObj("ztree-criminalRecord");
		var nodes = treeObj.getCheckedNodes(true);
		
		var selectedItemArray = new Array();
		$.each(nodes,function(n,node){
//			if(!node.isParent){
				var oib = new Object();
				oib["id"] = node.id;
				oib["code"] = node.code;
				oib["parentItemId"] = node.pId;
				oib["name"] = node.name;
				selectedItemArray.push(oib);
//			}
		});
		return selectedItemArray;
	}
	
	/**
	 * 设置选中的人员类别
	 * @param criminalRecordList 人员类别Bean集合
	 */
	function setSelectedChecked(criminalRecordList){
		var treeObj = $.fn.zTree.getZTreeObj("ztree-criminalRecord");
		$.each(criminalRecordList,function(c,crb){
			var node = treeObj.getNodeByParam("id", crb.criminalRecord, null);
			if($.util.exist(node)){
				node.checked = true;
				treeObj.updateNode(node);
			}
		});
	}
	
	/**
	 * 初始化人员类别树
	 * @param dictionaryItemLst 字典项Bean集合
	 * @param isOpen 是否展开
	 */
	function initCriminalRecordZtree(dictionaryItemLst,isOpen){
		var zNodes = new Array();
		if(!$.util.exist(dictionaryItemLst)){
			return ;
		}
		$.each(dictionaryItemLst,function(o,oib){
			var obj = new Object();
			obj["id"] = oib.id;
			obj["code"] = oib.code;
			obj["pId"] = oib.parentItemId;
			obj["name"] = oib.name;
			obj["open"] = isOpen;
			zNodes.push(obj);
		});
		criminalRecordZtree = $.fn.zTree.init($("#ztree-criminalRecord"), criminalRecordSetting, zNodes);
	}
	
	/**
	 * 显示单位树
	 */
	function showMenu() {
		var obj = $("#criminalRecordName");
		var oOffset = $("#criminalRecordName").offset();
		$("#menuContent").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
		$("body").on("mousedown", onBodyDown);
	}
	
	/**
	 * 隐藏单位树
	 */
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").off("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		ztreeCommon :{
			initCriminalRecordZtree : initCriminalRecordZtree ,
			getCheckedValue : getCheckedValue ,
			setSelectedChecked : setSelectedChecked
		}
	});	
})(jQuery);