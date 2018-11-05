var flagname=true;
var flagcode=true;
;(function($){
	"use strict";
	$(document).ready(function(){
		$.ajax({
			url : context + "/unit/initInfo.action",
			type : "POST",
			dataType : "json",
			success : function(data) {
				stateInti(data.stateList);
				typeInti(data.typeList);
				controlDisable();
			},
		});
		
		bindingEvent();
		intiUnitTree();
		intiSelectUnitTree();
		lookOver(constantInit, null);
	});

	function bindingEvent() {
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
			if($("#upUnit").val() == null || $("#upUnit").val() == ""){
				$.layerAlert.alert({msg:"此为根目录不允许修改",title:"提示",icon:5});
			}else{
				showMenu();
			}
		});
		$(document).on("keyup change", "#name", function(){
			unitNameChange();
		});
		
		/*
		 * 同步按钮
		 */
		$(document).on("click", "#synchronousBtn", function(){
			var synchronousUnit = function(pageIndex){
				$.ajax({
					url:context +'/unit/synchronousUnit.action',
					data:{},
					type:"post",
					dataType:"json",
				    customizedOpt:{
					     ajaxLoading:false,//设置是否loading
					},
					success:function(successData){
						
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
						
						var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
						treeIn.reAsyncChildNodes(null, "refresh");
						
						if(successData.flag == "true"){
							$.util.topWindow().$.layerAlert.alert({icon:6, msg:"同步成功。"}) ;
						}else{
							$.util.topWindow().$.layerAlert.alert({icon:0, msg:"连接超时。"}) ;
						}
					},
					error:function(errorData){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
						$.util.topWindow().$.layerAlert.alert({icon:0, msg:"连接超时。"}) ;
					}
				});
			}
			
			$.loading.alertLoading(synchronousUnit);
			
		});
	}
	//页面控制********************************************************************************
	/**
	 * 同步单位
	 */
	function synchronousUnit(){
		
	}
	
	function controlClean(){
		$("#organizationId").val("");
		$("#code").val("");
		$("#name").val("");
		$("#shortName").val("");
		$("#upUnit").val("");
		$("#upUnitCode").val("");
		$("#upUnitType").val("");
		$("#upUnitId").val("")
		$.select2.selectByOrder("#state", 1, true);
		$.icheck.check("input[name='unitLevel']", false, true);
		$.select2.selectByOrder("#type", 1, true);
		$("#telephone").val("");
		$("#portraiture").val("");
		$("#address").val("");
		$("#remarks").val("");
		//清空已选树
		removeAllNodes("ztree-roleSelected");
		//清空右树
		removeAllNodes("ztree-roleRight");
	}
	function controlSetData(data){
		$("#organizationId").val(data.id);
		$("#code").val(data.unitNum);
		$("#name").val(data.unitName);
		$("#shortName").val(data.shortName);
		$("#upUnit").val(data.parUnitName);
		$("#upUnitCode").val(data.parUnitNum);
		$("#upUnitId").val(data.parUnitId);
		$.select2.val("#state", data.status, true);
		$.select2.val("#type", data.type, true);
		if(data.status == $.select2.val("#state")){//启用
			$("#enableBtn").css("display","none");
			$("#disableBtn").css("display","inline");
		}else{
			$("#enableBtn").css("display","inline");
			$("#disableBtn").css("display","none");
		}
		$.icheck.check("input[name='unitLevel'][value='"+ data.dptType +"']", true);
		$("#telephone").val(data.tel);
		$("#portraiture").val(data.fax);
		$("#address").val(data.addr);
		$("#remarks").val(data.into);
		if($.util.exist(data.ztbl)){
			//已选角色树赋值
			reAddAllNodes("ztree-roleSelected", data.ztbl);
			//右树赋值
			reAddAllNodes("ztree-roleRight", data.ztbl);
		}
	}
	
	function controlGetData(){
		var data = new Object();
		data.id = $("#organizationId").val();
		data.unitNum = $("#code").val();
		data.unitName = $("#name").val();
		data.shortName = $("#shortName").val();
		data.parUnitName = $("#upUnit").val();
		data.parUnitNum = $("#upUnitId").val();
		data.parUnitType = $("#upUnitType").val();
		data.status = $.select2.val("#state");
		data.parUnitId=$("#upUnitId").val();
		data.type = $.select2.val("#type");
		var arr = $.icheck.getChecked("unitLevel");
		data.tel = $("#telephone").val();
		data.fax = $("#portraiture").val();
		data.addr = $("#address").val();
		data.into = $("#remarks").val();
		return data;
	}
	function controlEnable(){
		$("#organizationId").removeAttr("readonly");
		$("#code").removeAttr("readonly");
		$("#name").removeAttr("readonly");
		$("#shortName").removeAttr("readonly");
		$.select2.able("#state", true);
		$.select2.able("#type", true);
		$(document).off("click", ".upUnit");
		$(document).on("click", ".upUnit", function() {
			if($("#upUnit").val() == null || $("#upUnit").val() == ""){
				$.layerAlert.alert({msg:"此为根目录不允许修改",title:"提示",icon:5});
			}else{
				showMenu();
			}
		});
		$("#telephone").removeAttr("readonly");
		$("#portraiture").removeAttr("readonly");
		$("#address").removeAttr("readonly");
		$("#remarks").removeAttr("readonly");
		$("#roleSelected").css("display","none");
		$("#roleSelecte").css("display","inline");
		$("#btnUpDiv").css("display","none");
		$("#btnDownDiv").css("display","block");
	}
	//单位类型初始化
	function typeInti(list){
		$.select2.addByList("#type", list, "code", "name");
	}
	function controlEnableForModify(){
		$("#organizationId").removeAttr("readonly");
		$("#code").removeAttr("readonly");
		$("#name").removeAttr("readonly");
		$("#shortName").removeAttr("readonly");
		$.select2.able("#state", true);
		$.select2.able("#type", true);
		$.icheck.able("#isDepartment", false);
		$(document).off("click", ".upUnit");
		$(document).on("click", ".upUnit", function() {
			if($("#upUnit").val() == null || $("#upUnit").val() == ""){
				$.layerAlert.alert({msg:"此为根目录不允许修改",title:"提示",icon:5});
			}else{
				showMenu();
			}
		});
		var arr = $.icheck.getChecked("isDepartment");
		$("#telephone").removeAttr("readonly");
		$("#portraiture").removeAttr("readonly");
		$("#address").removeAttr("readonly");
		$("#remarks").removeAttr("readonly");
		$("#roleSelected").css("display","none");
		$("#roleSelecte").css("display","inline");
		$("#btnUpDiv").css("display","none");
		$("#btnDownDiv").css("display","block");
	}
	function controlDisable(){
		$("#organizationId").attr("readonly","readonly");
		$("#code").attr("readonly","readonly");
		$("#name").attr("readonly","readonly");
		$("#shortName").attr("readonly","readonly");
		$.select2.able("#state", false);
		$.select2.able("#type", false);
		$(document).off("click", ".upUnit");
		$.icheck.able("input[name='unitLevel']", false);
		$("#telephone").attr("readonly","readonly");
		$("#portraiture").attr("readonly","readonly");
		$("#address").attr("readonly","readonly");
		$("#remarks").attr("readonly","readonly");
		$("#roleSelected").css("display","inline");
		$("#roleSelecte").css("display","none");
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
		$.ajax({
				url : context + "/unit/findUnitById.action",
				type : "POST",
				data : {
					"unitBean.id" : id,
				},
				dataType : "json",
				success : function(data) {
					controlSetData(data.unitBean);
					controlDisable();
					$(".select2-selection--single").css("background-color","transparent")
					$("#operateType").val("lookOver");
					$(".changeBtn").css("display", "inline");
					var testData=$.select2.val("#state");
					if($.select2.val("#state") == constantStauts_qy){
						$("#enableBtn").css("display","none");
						$("#disableBtn").css("display","inline");
					}else{
						$("#enableBtn").css("display","inline");
						$("#disableBtn").css("display","none");
					}
					$("#select2-state-container").css("color","#888");
					$("#select2-type-container").css("color","#888");
				},
			});
		
	}
	/**
	 * 保存
	 */
	function save() {
		var operateUrl;
		var dataMap = new Object();
		if($.util.isBlank($("#upUnitCode").val()) && $("#code").val() != constantRootUnit) {
			$.layerAlert.alert({msg:"上级单位不能为空!",title:"提示"});
			$("#save").removeAttr("disabled");
			return;
		}
		var demo = $.validform.getValidFormObjById("validformId") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			$("#save").removeAttr("disabled");
			return;
		}
		if($("#operateType").val() == "add"){
			operateUrl = context + "/unit/addUnit.action";
		}else{
			operateUrl = context + "/unit/modifyUnit.action";
		}
		$.util.objToStrutsFormData(controlGetData(), "unitBean", dataMap) ;
		$.ajax({
			url : operateUrl,
			type : "POST",
			data : dataMap,
			btn : "#save",
			dataType : "json",
			success : function(data) {
				$("#save").removeAttr("disabled");
				if($("#operateType").val() == "add"){
					if(data.flag == "false"){
						$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
					}else{
						//展开指定节点开始
						var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
						var newTreeZNode = new ZtreeNode(data.unitBean.id, data.unitBean.parUnitId, data.unitBean.unitName,"../images/xtgl_icon/ztree-icon_dw.png"); 
						var parentZNode = treeIn.getNodeByParam("id", data.unitBean.parUnitId, null); //获取父节点
						treeIn.addNodes(parentZNode, newTreeZNode, true);  
						var node = treeIn.getNodeByParam("id",data.unitBean.id);  
						treeIn.selectNode(node,true);//指定选中ID的节点  
						treeIn.expandNode(node, true, false);//指定选中ID节点展开  
						//展开指定节点结束
						reBuildSelectUnitTree();
						$.layerAlert.alert({msg:"保存成功!",title:"提示",icon:6});
						lookOver(data.unitBean.id, data.unitBean.type);
					}
				}else{
					if(data.flag == "false"){
						$.layerAlert.alert({msg:data.msg,title:"提示",icon:5});
					}else{
						//展开指定节点开始
						var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
						var node = treeIn.getNodeByParam("id",data.unitBean.id);  
						treeIn.selectNode(node,true);//指定选中ID的节点  
						treeIn.expandNode(node, true, false);//指定选中ID节点展开  
						//展开指定节点结束
						reBuildSelectUnitTree();
						$.layerAlert.alert({msg:"修改成功!",title:"提示",icon:6});
						lookOver(data.unitBean.id, data.unitBean.type);
					}
					
				}
				
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
	function checkNameAndCode(dataMap){
		$.ajax({
			url : context + "/unit/checkCodeAndName.action",
			type : "POST",
			data : dataMap,
			success:function(){
				
			}
		});
	}
	/**
	 * 取消按钮
	 */
	function cancel() {
		$.validform.closeAllTips("validformId");
		if($("#oldCode").val() == ""){
			lookOver(constantInit, null);
		}else{
			lookOver($("#oldCode").val(), null);
		}
	}
	/**
	 * 新增按钮
	 */
	function addBtn(){
		$("#oldCode").val("");
		$(".red_star_canHide").show();
		$("#operateType").val("add");
		if($.select2.val("#property") != "1") {
			var unitCode = $("#code").val();
			var unitName = $("#name").val();
			var unitId=$("#organizationId").val();
			var arr = $.icheck.getChecked("isDepartment");
			var isDep = "unit";
			controlClean();
			$("#upUnit").val(unitName);
			$("#upUnitCode").val(unitCode);
			$("#upUnitType").val(isDep);
			$("#upUnitId").val(unitId)
			
		}else{
			controlClean();
		}
		controlEnable();
		$("#enterBtnDiv").css("display", "inline");
	}
	/**
	 * 修改按钮
	 */
	function modifyBtn(){
		$(".red_star_canHide").show();
		$("#oldCode").val($("#organizationId").val());
		controlEnableForModify();
		$("#enterBtnDiv").css("display", "inline");
		$("#select2-state-container").css("color","#fff");
		$("#select2-type-container").css("color","#fff");
	}
	/**
	 * 删除按钮
	 */
	function deleteBtn(){
		if($.select2.val("#state") == constantStauts_qy){
			$.layerAlert.alert({msg:"该单位已启用，不可以直接删除！",title:"提示",icon:5});
			return ;
		}
		$.layerAlert.confirm({
			msg : "请谨慎删除单位",
			title:"提示",
			yes : function(index,layero) {
				$.ajax({
					url : context + "/unit/deleteUnit.action",
					type : "POST",
					data : {
						"unitBean.id" : $("#organizationId").val(),
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
			
		});
	}
	/**
	 * 启用按钮
	 */
	function enableBtn(){
		$.ajax({
			url : context + "/unit/enableControl.action",
			type : "POST",
			data : {
				"unitBean.unitNum" : $("#code").val(),
				"unitBean.status":constantStauts_qy
			},
			btn : "#enableBtn",
			dataType : "json",
			success : function(data) {
				$("#enableBtn").css("display","none");
				$("#disableBtn").css("display","inline");
				lookOver($("#organizationId").val(), null);
				$.util.topWindow().$.layerAlert.alert({msg:"启用成功!",title:"提示",icon:1});
			},
		});
	}
	/**
	 * 停用按钮
	 */
	function disableBtn() {
		$.ajax({
			url : context + "/unit/disableControl.action",
			type : "POST",
			data : {
				"unitBean.unitNum" : $("#code").val(),
				"unitBean.status":constantStauts_ty
			},
			btn : "#disableBtn",
			dataType : "json",
			success : function(data) {
				$("#enableBtn").css("display","inline");
				$("#disableBtn").css("display","none");
				lookOver($("#organizationId").val(), null);
				$.util.topWindow().$.layerAlert.alert({msg:"停用成功!",title:"提示",icon:1});
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
					if($("#organizationId").val() == treeNode.id){
						$.layerAlert.alert({msg:"不允许将本单位选择为上级单位",title:"提示",icon:5});
					}else{
						$("#upUnit").val(treeNode.name);
						$("#upUnitCode").val(treeNode.id);
						$("#upUnitId").val(treeNode.id);
						hideMenu();
					}
					
				}
			}
		};
	//内部单位树setting
	var settingIn = {
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
					$("#curSelectUnit").val(treeNode.tId);
					if($("#operateType").val() == "lookOver"){
						lookOver(treeNode.id, treeNode.diyMap.type);
						$(".red_star_canHide").hide();
					}
				}
			}
		};
	function reBuildSelectUnitTree() {
		settingSelect.async.url = context + "/unit/initUnitTree.action";
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