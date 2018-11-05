(function($) {
	"use strict";
	var number;		//当前弹出层
	var bj;			//当前处于那个阶段 ：  修改  复制  新增
	var roleId;
	$(document).ready(function() {
		initStatus();
		$.ajax({
			url : context + "/role/queryAuthorityZTree.action",
			type : "POST",
			async: false,
			dataType : "json",
			success : function(data) {
				initMoveRoleTree(data.authorityZTreeBean);
			},
		});
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
	});
	//初始化状态列表
	function initStatus(){
		$.ajax({
			url : context + "/role/findDictionaryItemsByType.action",
			type : "POST",
			data :{
				"dictionaryType":constantStauts
			},
			dataType : "json",
			success : function(data) {
				$.select2.addByList("#status", data.dictionaryItemLst, "code", "name");
			},
		});
	}
	
	/**修改角色初始化*/
	function modifyRoleInit(){
		if(roleId!=null){
			$.ajax({url : context + '/role/queryRoleById.action',
				type : "POST",
				dataType : "json",
				data : {
					"id" :roleId,
				},
				success : function(data) {
					 $("#roleName").val(data.roleBean.roleName);
					 if(data.roleBean.status ==  "启用"){
						 $("#status").select2("val",constantStauts_qy);
					 }else{
						 $("#status").select2("val",constantStauts_ty);
					 }
					 $("#roleCode").val(data.roleBean.roleCode);
					 zTreeSetValue(data.authorityZTreeBean);
				}
			});
		}
	}
	var ma;
	
	/**
	 * 保存
	 */
	$(document).on('click','#saveRole',function(){
		/**表单验证*/
		var demo = $.validform.getValidFormObjById("validformId") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return;
		}
		var dataMap=new Object();
		dataMap["roleBean.roleName"]=$("#roleName").val();
		dataMap["roleBean.status"]=$("#status").select2("val");
		dataMap["roleBean.roleCode"]=$("#roleCode").val();
		var powerKey=takeValue();
		$(powerKey).each(function(index,itm){
			dataMap["roleBean.authorityBeanBeans["+index+"].id"]=itm.id;
		});
		if(!$.util.isEmpty(roleId)){
			dataMap["roleBean.id"]=roleId;
			$.ajax({url : context + '/role/updateRole.action',
				type : "POST",
				async: true,
				dataType : "json",
				data :dataMap,
				success : function(data) {
					if(data.flag == "true"){
						roleId=data.roleBean.id
						$("#modify").css("display","inline");
						$("#saveRole").css("display","none");
						$("#roleCode").attr("readonly","readonly");
						$("#toRight").attr("disabled","true");
						$("#remove").attr("disabled","true");
						$("#roleName").attr("readonly","readonly");
						$.select2.able("#status", false);
						$("#cancel").attr("disabled","true");
						$.layerAlert.alert({msg:"修改成功",title:"提示",icon:"6"});

					}else{
						$.layerAlert.alert({msg:data.msg,title:"提示"});
						return;
					}
				}
			});
		}else{
			$.ajax({url : context + '/role/saveRole.action',
				type : "POST",
				async: true,
				dataType : "json",
				data : dataMap,
				success : function(data) {
					if(data.flag == "true"){
						$("#modify").css("display","inline");
						$("#saveRole").css("display","none");
						$("#roleCode").attr("readonly","readonly");
						$("#toRight").attr("disabled","true");
						$("#remove").attr("disabled","true");
						$("#roleName").attr("readonly","readonly");
						$.select2.able("#status", false);
						$("#cancel").attr("disabled","true");
						$.layerAlert.alert({msg:"保存成功",title:"提示",icon:"6"});
						roleId=data.roleBean.id
					}else{
						$.layerAlert.alert({msg:data.msg,title:"提示"});
						return;
					}
					
				}
			
			});
		}
		
	});
	
	$(document).on('click','#modify',function(){
				$("#modify").css("display","none");
				$("#saveRole").css("display","inline");
				$("#roleCode").css("display","inline");
				$('#toRight').removeAttr("disabled");
				$('#remove').removeAttr("disabled");
				$("#roleName").removeAttr("readOnly");
				$("#roleCode").removeAttr("readOnly");
				$.select2.able("#status", true);
				$(this).removeClass().addClass("btn btn-success");
				$('#cancel').removeAttr("disabled");		//移除disabled状态
	});
	/**
	 * 取消
	 */
	$(document).on('click','#cancel',function(){
		$.util.topWindow().$.layerAlert.closeAll();	//关闭所有层
	});
	
	function takeValue(){
		//右树取值#ztree-roleRight"
		//var data = new Object();
		var rbsl = [];
		var treeTemp = $.fn.zTree.getZTreeObj("ztree-roleRight");	//找到取值的树
		var nodes = treeTemp.getNodes();
		var obj;
		for(var i in nodes){
			var arr=nodes[i];
			if($.util.exist(arr)){
					obj = new Object();
					obj.id =arr.id;
					rbsl.push(obj);
				
			}
		}
		return rbsl;
	}
	
	/**初始化已选树*/
	function zTreeSetValue(data){
		//右树赋值
		reAddAllNodes("ztree-roleRight", data);
	}
	
	function cleanZTree(){
		//清空已选树
		removeAllNodes("ztree-roleSelected");
		//清空右树
		removeAllNodes("ztree-roleRight");
	}
	
	/****************************************************************************************/
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
			check : {
				enable : true,
				chkStyle: "checkbox",
				radioType: "all",
				chkboxType : {
					"Y" : "s",
					"N" : "s"
				}
			},
			callback : {
				beforeDrop: function(treeId, treeNodes, targetNode, moveType, isCopy) {
					return false ;
				}
			}
		};
		/**
		 * 初始化已选树
		 */
		function initSelectedRoleTree(){
			$.fn.zTree.init($("#ztree-ztree-roleSelected"), setting);
		}
		function initMoveRoleTree(powerList) {
			$(document).on("click", "#toRight", function(){
				var tree1 = $.fn.zTree.getZTreeObj("ztree-roleLeft");
				var tree2 = $.fn.zTree.getZTreeObj("ztree-roleRight");
				var nodes = tree1.getCheckedNodes(true);
				var cpNodes = [] ;
				$.each(nodes, function(i, val){
					if(!val.getCheckStatus().half){
						cpNodes.push(val) ;
					}
				});
				copyNodesToRight(cpNodes) ;
			});
			
			$(document).on("click", "#remove", function(){
				var tree2 = $.fn.zTree.getZTreeObj("ztree-roleRight");
				var nodes = tree2.getCheckedNodes(true);
				$.each(nodes, function(i, val){
					tree2.removeNode(val);
				});
			});
			
			$(document).on('focus', '#keyLeft', function () {
				var key = $(this) ;
				 focusKey(key) ;
			});
			
			$(document).on('blur', '#keyLeft', function () {
				var key = $(this) ;
				blurKey(key) ;
			});		
			
			$(document).on('keyup change', '#keyLeft', function () {
				var key = $(this) ;
				searchNode(key, "ztree-roleLeft") ;
			});	
			
			$(document).on('focus', '#keyRight', function () {
				var key = $(this) ;
				 focusKey(key) ;
				
			});
			
			$(document).on('blur', '#keyRight', function () {
				var key = $(this) ;
				blurKey(key) ;
			});		
			
			$(document).on('keyup change', '#keyRight', function () {
				var key = $(this) ;
				searchNode(key, "ztree-roleRight") ;
			});
			
			var zTreeRoleLeft = $.fn.zTree.init($("#ztree-roleLeft"), setting, powerList);
			zTreeRoleLeft.lastSearchValue = "" ;
			zTreeRoleLeft.nodeSearchList = [] ;
			zTreeRoleLeft.fontSearchCss = {} ;
			var zTreeRoleRight = $.fn.zTree.init($("#ztree-roleRight"), setting);
			zTreeRoleRight.lastSearchValue = "" ;
			zTreeRoleRight.nodeSearchList = [] ;
			zTreeRoleRight.fontSearchCss = {} ;
		}
		
		function copyNodesToRight(nodes){
			var tree1 = $.fn.zTree.getZTreeObj("ztree-roleLeft");
			var tree2 = $.fn.zTree.getZTreeObj("ztree-roleRight");
			$.each(nodes, function(i, val){
				var parent = val.getParentNode() ;
				var id = val.id ;
				var parentId = val.parentId ;
				var self = tree2.getNodeByParam("id", id, null) ;
				if(self!=null){
					return true ;
				}
				var copyList = [] ;
				copyList.push($.util.cloneObj(val)) ;
				var tree2ParentNode = tree2.getNodeByParam("id", parentId, null) ;
				if(tree2ParentNode == null){
					while(parent!=null){
						var parentTree2Parent = tree2.getNodeByParam("id", parent.parentId, null) ;
						if(parentTree2Parent!=null){
							break ;
						}else{
							copyList.push($.util.cloneObj(parent)) ;
							parent = parent.getParentNode() ;
						}
					}
				}
				for(var j=copyList.length-1; j>=0; j--){
					var copyNode = copyList[j] ;
					copyNode.children = null ;
					if(copyNode.getParentNode()!=null){
						var target = tree2.getNodeByParam("id", copyNode.parentId, null) ;
						tree2.copyNode(target, copyNode, "inner") ;
					}else{
						tree2.copyNode(null, copyNode, "inner") ;
					}
				}
				var children = tree1.getNodesByParam("parentId", val.id, val);
				$.each(children, function(k, val1){
					var target = tree2.getNodeByParam("id", val.id, null) ;
					tree2.copyNode(target, val1, "inner") ;
				});
			});
		}
		
		function focusKey(key) {
			if (key.hasClass("empty")) {
				key.removeClass("empty");
			}
		}
		
		function blurKey(key) {
			if (key.get(0).value === "") {
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
			$.each(nodes, function(i, val){
				treeTemp.removeNode(val);
			});
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
			//父节点Id，节点List，后边别管了
			treeTemp.addNodes(null, nodes, null, null);
		}
		
		function showMenu() {
			var obj = $("#upUnit");
			var oOffset = $("#upUnit").offset();
			$("#menuContent").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
			$("body").on("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").off("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
	/****************************************************************************************/
	
	
	
})(jQuery);