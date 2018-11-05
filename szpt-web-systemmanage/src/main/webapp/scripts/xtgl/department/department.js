;(function($){
	"use strict";
	$(document).ready(function(){
		$.ajax({
			url : context + "/department/initInfo.action",
			type : "POST",
			data:{
				"dictionaryType":constantStauts
			},
			dataType : "json",
			success : function(data) {
				stateInti(data.dictionaryItemLst);
				controlDisable();
			},
		});
		bindingEvent();
		intiUnitTree();
		intiSelectUnitTree();
		lookOver(constantInit, null);
	});

	function bindingEvent() {
		$(document).on("click", ".upUnit", function() {
			upUnitChange();
		})		
		$(document).on("click", "#addBtn", function() {
			addBtn();
		});
		$(document).on("click", "#modifyBtn", function() {
			modifyBtn();
		});
		$(document).on("click", "#deleteBtn", function() {
			deleteBtn();
		});
		$(document).on("click", "#enableBtn", function() {
			enableBtn();
		});
		$(document).on("click", "#disableBtn", function() {
			disableBtn();
		});
		$(document).on("click", "#save", function() {
			save();
		});
		$(document).on("click", "#cancel", function() {
			cancel();
		});
		$(document).on("click", ".upUnit", function(){
			showMenu();
		});
		$(document).on("change", "#upUnitType", function(){
			upUnitTypeChange();
		});
		$(document).on("keyup change", "#name", function(){
			unitNameChange();
		});
	}
	//页面控制********************************************************************************
	function controlClean(){
		$("#organizationId").val("");
		$("#code").val("");
		$("#name").val("");
		$("#shortName").val("");
		$.select2.selectByOrder("#state", 1, true);
		$("#telephone").val("");
		$("#portraiture").val("");
		$("#address").val("");
		$("#remarks").val("");
	}
	function controlSetData(data){
		$("#organizationId").val(data.id);
		$("#code").val(data.departmentNum);
		$("#name").val(data.departmentName);
		$("#shortName").val(data.shortName);
		$("#upUnit").val(data.parDepartmentName);
		$("#upUnitCode").val(data.parDepartmentNum);
		$("#upUnitId").val(data.parDepartmentId);
		$.select2.val("#state", data.status, true);
		if(data.status == $.select2.val("#state")){//启用
			$("#enableBtn").css("display","none");
			$("#disableBtn").css("display","inline");
		}else{
			$("#enableBtn").css("display","inline");
			$("#disableBtn").css("display","none");
		}
		$("#telephone").val(data.tel);
		$("#portraiture").val(data.fax);
		$("#address").val(data.addr);
		$("#remarks").val(data.into);
	}
	
	function controlGetData(){
		var data = new Object();
		data.id = $("#organizationId").val();
		data.departmentNum = $("#code").val();
		data.departmentName = $("#name").val();
		data.shortName = $("#shortName").val();
		data.parDepartmentName = $("#upUnit").val();
		data.parDepartmentId = $("#upUnitId").val();
		data.parDepartmentType = $("#upUnitType").val();
		data.status = $.select2.val("#state");
		data.prop =  $("#property").val();
//      不需要通过isDepartment判断data.type的值，data.type值恒为"department" XIEHF 添加注释 20160222
		data.type = "department";
		data.tel = $("#telephone").val();
		data.fax = $("#portraiture").val();
		data.addr = $("#address").val();
		data.into = $("#remarks").val();
		return data;
	}
	function controlEnable(){
		$("#organizationId").removeAttr("readonly");
		$("#code").removeAttr("disabled");
		$("#name").removeAttr("disabled");
		$("#shortName").removeAttr("readonly");
		$.select2.able("#state", true);
		//为【上级机构】信息项注册点击事件。   XIEHF
		$(document).off("click", ".upUnit");
		$(document).on("click", ".upUnit", function() {
			upUnitChange();  
		});
		$("#telephone").removeAttr("readonly");
		$("#portraiture").removeAttr("readonly");
		$("#address").removeAttr("readonly");
		$("#remarks").removeAttr("readonly");
		$("#btnUpDiv").css("display","none");
		$("#btnDownDiv").css("display","block");
	}
	function controlEnableForModify(){
		$("#organizationId").removeAttr("readonly");
		$("#code").removeAttr("disabled");
		$("#name").removeAttr("disabled");
		$("#shortName").removeAttr("readonly");
		$.select2.able("#state", true);
		$(document).off("click", ".upUnit");
		$(document).on("click", ".upUnit", function() {
			upUnitChange();
		});
		$("#telephone").removeAttr("readonly");
		$("#portraiture").removeAttr("readonly");
		$("#address").removeAttr("readonly");
		$("#remarks").removeAttr("readonly");
		$("#btnUpDiv").css("display","none");
		$("#btnDownDiv").css("display","block");
	}
	function controlDisable(){
		$("#organizationId").attr("readonly","readonly");
		$("#code").attr("disabled","disabled");
		$("#name").attr("disabled","disabled");
		$("#shortName").attr("readonly","readonly");
		$.select2.able("#state", false);
		$(document).off("click", ".upUnit");
		$("#telephone").attr("readonly","readonly");
		$("#portraiture").attr("readonly","readonly");
		$("#address").attr("readonly","readonly");
		$("#remarks").attr("readonly","readonly");
		$("#btnUpDiv").css("display","block");
		$("#btnDownDiv").css("display","none");
	}
	//操作********************************************************************************
	/**
	 * 查看
	 */
	function lookOver(id, isDep){
		controlClean();
		controlDisable();
		$(".select2-selection--single").css("background-color","transparent");
		if(id == null || isDep != "department" ){
			$(".changeBtn").css("display", "none");
			$("#operateType").val("lookOver");
			return;
		}else{
			$.ajax({
				url : context + "/department/findDeparmentById.action",
				type : "POST",
				data : {
					"departmentBean.id" : id,
				},
				dataType : "json",
				success : function(data) {
					$(".red_star_canHide").hide();
					if(data.flag == "true"){
						controlSetData(data.departmentBean);
						controlDisable();
						$(".select2-selection--single").css("background-color","transparent");
						$("#operateType").val("lookOver");
						$(".changeBtn").css("display", "inline");
						var testData = $.select2.val("#state");
						if($.select2.val("#state") == constantStauts_qy){
							$("#enableBtn").css("display","none");
							$("#disableBtn").css("display","inline");
						}else{
							$("#enableBtn").css("display","inline");
							$("#disableBtn").css("display","none");
						}
					}else{
						$("#enableBtn").css("display","none");
						$("#disableBtn").css("display","none");
					}
				},
			});
		}
	}
	/**
	 * 保存
	 */
	function save() {
		if($.util.isBlank($("#upUnitCode").val())) {
			$.layerAlert.alert({msg:"上级机构不能为空!",title:"提示"});
			$("#save").removeAttr("disabled");
			return;
		}
		var demo = $.validform.getValidFormObjById("validformId") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			$("#save").removeAttr("disabled");
			return;
		}
		var isDep = "department";
		var operateUrl;
		if($("#operateType").val() == "add"){
			operateUrl = context + "/department/addDeparment.action";
		}else{
			operateUrl = context + "/department/modifyDepartment.action";
		}
		var dataMap = new Object();
		$.util.objToStrutsFormData(controlGetData(), "departmentBean", dataMap) ;
		$.util.objToStrutsFormData(isDep, "ifDepartment", dataMap) ;
		$.ajax({
			url : operateUrl,
			type : "POST",
			data : dataMap,
			btn : "#save",
			dataType : "json",
			success : function(data) {
				$("#save").removeAttr("disabled");
				
				reBuildSelectUnitTree();
				if($("#operateType").val() == "add"){
				  if(data.flag == "false"){
						$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
				    }
				  else{
						//展开指定节点开始
						var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
						var newTreeZNode = new ZtreeNode(data.departmentBean.id, data.departmentBean.parDepartmentId, data.departmentBean.departmentName,"../images/xtgl_icon/ztree-icon_bm.png"); 
						var parentZNode = treeIn.getNodeByParam("id", data.departmentBean.parDepartmentId, null); //获取父节点
						treeIn.addNodes(parentZNode, newTreeZNode, true);  
						var node = treeIn.getNodeByParam("id",data.departmentBean.id);  
						treeIn.selectNode(node,true);//指定选中ID的节点  
						treeIn.expandNode(node, true, false);//指定选中ID节点展开  
						//展开指定节点结束
						$.layerAlert.alert({msg:"保存成功!",title:"提示",icon:6});
						lookOver(data.departmentBean.id, data.departmentBean.type);
				  }
				}else{
					  if(data.flag == "false"){
							$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
					    }
					  else{
						//展开指定节点开始
							var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
							var node = treeIn.getNodeByParam("id",data.departmentBean.id);  
							treeIn.selectNode(node,true);//指定选中ID的节点  
							treeIn.expandNode(node, true, false);//指定选中ID节点展开  
							//展开指定节点结束
							$.layerAlert.alert({msg:"修改成功!",title:"提示",icon:6});
							lookOver(data.departmentBean.id, data.departmentBean.type);
					  }
					}
				reBuildUnitTree();
			},
		});
	}
	/**
	 * 控件验证
	 */
	function checkVal() {
		var demo = $.validform.getValidFormObjById("validformId");
		return $.validform.check(demo);
	}
	//按钮********************************************************************************
	/**
	 * 确定按钮
	 */
	function saveBtn(){
		if(!($("#oldCode").val()!=""&&$("#oldCode").val()==$("#organizationId").val())){
			if(checkVal()){
				checkCode(save);
			}else{
				$("#save").removeAttr("disabled");
			}
		}else{
			save();
		}
	}          
	/**
	 * 取消按钮
	 */
	function cancel() {
		$.validform.closeAllTips("validformId");
		if($("#oldCode").val() == ""){
			lookOver(constantInit, null);
		}else{
			lookOver($("#organizationId").val(), null);
		}
	}
	/**
	 * 新增按钮
	 */
	function addBtn(){
		$("#oldCode").val("");
		$(".red_star_canHide").show();
		$("#operateType").val("add");
		var unitCode = $("#upUnitCode").val();
		var unitName = $("#upUnit").val();
		var unitId=$("#upUnitId").val();
		var isDep = "department";
		controlClean();
		$("#upUnit").val(unitName);
		$("#upUnitCode").val(unitCode);
		$("#upUnitType").val(isDep);
		$("#upUnitId").val(unitId)
			
		
		controlEnable();
		$("#enterBtnDiv").css("display", "inline");
	}
	/**
	 * 修改按钮
	 */
	function modifyBtn(){
		$(".red_star_canHide").show();
		$("#oldCode").val($("#organizationId").val());
		$("#oldDep").val("department");
		$("#operateType").val("modify");
		controlEnableForModify();
		$("#enterBtnDiv").css("display", "inline");
	}
	/**
	 * 删除按钮
	 */
	function deleteBtn(){
		if($.select2.val("#state") == constantStauts_qy){
			$.layerAlert.alert({msg:"该部门已启用，不可以直接删除！",title:"提示",icon:5});
			return ;
		}
		$.layerAlert.confirm({
			msg : "确定要删除吗？",
			title:"提示",
			yes : function(index,layero) {
				$.ajax({
					url : context + "/department/deleteDepartment.action",
					type : "POST",
					data : {
						"departmentBean.id" : $("#organizationId").val(),
					},
					btn : "#deleteBtn",
					dataType : "json",
					success : function(data) {
						   if(data.flag == "false"){
								$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
						   }else{
							   //删除指定节点开始
								var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
								var node = treeIn.getNodeByParam("id",$("#organizationId").val());  
								treeIn.selectNode(node,true);//指定选中ID的节点  
								treeIn.removeNode(node, false);//指定节点删除
								//删除指定节点结束
								reBuildSelectUnitTree();
								lookOver(constantInit,null);
								$.layerAlert.alert({msg:"删除成功!",title:"提示",icon:6});
						   }
							
					},
				});
			},
			no : function(index,layero) {
				$("#deleteBtn").removeAttr("disabled");
			}
		});
	}
	/**
	 * 启用按钮
	 */
	function enableBtn(){
		var isDep = "department";
		$.ajax({
			url : context + "/department/enableControl.action",
			type : "POST",
			data : {
				"departmentBean.departmentCode" : $("#code").val(),
				"departmentBean.status":constantStauts_qy
			},
			btn : "#enableBtn",
			dataType : "json",
			success : function(data) {
				$("#enableBtn").css("display","none");
				$("#disableBtn").css("display","inline");
				lookOver($("#organizationId").val(), isDep);
			},
		});
	}
	/**
	 * 停用按钮
	 */
	function disableBtn() {
//		var arr = $.icheck.getChecked("isDepartment");
		var isDep = "department";
		$.ajax({
			url : context + "/department/disableControl.action",
			type : "POST",
			data : {
				"departmentBean.departmentCode" : $("#code").val(),
				"departmentBean.status":constantStauts_ty
			},
			btn : "#disableBtn",
			dataType : "json",
			success : function(data) {
				$("#enableBtn").css("display","inline");
				$("#disableBtn").css("display","none");
			//	var isDep = "unit";
				lookOver($("#organizationId").val(), isDep);
			},
		});
		
	}
	//初始化********************************************************************************
	
	/**
	 * 状态初始化
	 */
	function stateInti(list){
		$.select2.addByList("#state", list, "code", "name");
	}
	function ZtreeNode(id, pId, name,icon) {//定义ztree的节点类  
	    this.id = id;  
	    this.pId = pId;  
	    this.name = name;  
	    this.icon = icon;
	}  
	//选择单位树setting
	var settingSelect = {
			view: {
				fontCss: getFontCss
			},
			async: {
				enable: true,
				global: false,
				url:context+"/department/initDepartmentTree.action",
				autoParam:["id=unitId"],
				dataFilter: function(treeId, parentNode, childNodes) {
					return childNodes.departmentTree;
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
					$("#upUnit").val(treeNode.name);
					$("#upUnitCode").val(treeNode.id);
					$("#upUnitId").val(treeNode.id);
					hideMenu();
				}
			}
		};
	//单位树setting
	var settingIn = {
			view: {
				fontCss: getFontCss
			},
			async: {
				enable: true,
				global: false,
				url:context+"/department/initDepartmentTree.action",
				autoParam:["id=unitId"],
				dataFilter: function(treeId, parentNode, childNodes) {
//					$("#ifInUnit").val(childNodes.ifInUnit);
					return childNodes.departmentTree;
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
					$(".red_star_canHide").hide();
					$("#upUnit").val(treeNode.name);
					$("#upUnitCode").val(treeNode.id);
					$("#upUnitId").val(treeNode.id);
					if($("#operateType").val() == "lookOver"){
						lookOver(treeNode.id, treeNode.isUnit);
					}
				}
			}
		};
	function reBuildSelectUnitTree() {
		settingSelect.async.url = context+"/department/initDepartmentTree.action";
		var treeSelect = $.fn.zTree.getZTreeObj("ztree-unitSelect");
		treeSelect.destroy();
		treeSelect = $.fn.zTree.init($("#ztree-unitSelect"), settingSelect);
		treeSelect.lastSearchValue = "" ;
		treeSelect.nodeSearchList = [] ;
		treeSelect.fontSearchCss = {} ;
	}
	function reBuildUnitTree() {
		var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
		treeIn.destroy();
		treeIn = $.fn.zTree.init($("#ztree-inUnitTree"), settingIn);
		treeIn.lastSearchValue = "" ;
		treeIn.nodeSearchList = [] ;
		treeIn.fontSearchCss = {} ;
	}
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
	function intiUnitTree() {
		//内部单位部门树
		var treeIn = $.fn.zTree.init($("#ztree-inUnitTree"), settingIn);
		treeIn.lastSearchValue = "" ;
		treeIn.nodeSearchList = [] ;
		treeIn.fontSearchCss = {} ;
		
		$(document).on('focus', '#keyIn', function () {
			var key = $(this) ;
			 focusKey(key) ;
		});
		
		$(document).on('blur', '#keyIn', function () {
			var key = $(this) ;
			blurKey(key) ;
		});		
		
		$(document).on('keyup change', '#keyIn', function () {
			var key = $(this) ;
			searchNode(key, "ztree-inUnitTree") ;
		});
	}
	//事件********************************************************************************
	/**
	 * 上级单位变化
	 */
	function upUnitChange(){
	    showMenu();
	}
	
	/**
	 * 简称截取
	 */
	function unitNameChange(){
		var str = $("#name").val().substr(0,6);
		$("#shortName").val(str);
	}
	
	//看不懂的Tree********************************************************************************
	/**
	 * 初始化已选树
	 */
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
		var zTreeSelected = $.fn.zTree.init($("#ztree-roleSelected"), setting);
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
		
		$(document).on('keyup change', '#keySelected', function () {
			var key = $(this) ;
			searchNode(key, "ztree-roleSelected") ;
		});	
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
	//common********************************************************************************
	jQuery.extend($.common, {

	});
})(jQuery);