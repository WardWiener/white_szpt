(function($) {
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var number = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	$("#id").val(id);
	accountRoles("");
	$("#upPerson").val(initData.personName);
	$("#unitName").val(initData.unitName);
	$("#unitId").val(initData.unitId);
	$("#personNo").val(initData.personId);
	$("#pa_newPage_cellId").val(initData.cellId);
	$("#zhihuidanyuanName").val(initData.cellName);
	var close=function(number){
		$.util.topWindow().$.layerAlert.closeWithLoading(number);
	}
	$(document).ready(function() {
		
		if(($("#start").val())==""){
			var mydate=new Date();
			var hour=(mydate.getHours()<10?"0"+mydate.getHours():mydate.getHours());
			var minute=(mydate.getMinutes()<10?"0"+mydate.getMinutes():mydate.getMinutes());
			var second=(mydate.getSeconds()<10?"0"+mydate.getSeconds():mydate.getSeconds());
			$.laydate.setRange(laydate.now()+" "+hour+":"+minute+":"+second, "2029-01-01 00:00:00", "#dateRangeId","info@yyyy-MM-dd  hh:mm:ss");
		}
		setstatus();
		intiSelectUnitTree();
	});
	
	$("#personBelongUnit").on("click",function(){//选择单位
		showMenuUnit();
	});
	$("#personBelongUnitName").on("click",function(){//选择单位
		showMenuUnit();
	});
	var setstatus=function(){
		$.ajax({
			url : context + "/userManage/setstatus.action",
			type : "POST",
			data:{
				"dictionaryType":constantStauts
			},
			dataType : "json",
			success : function(data) {
				$.select2.addByList("#selecttest1", data.dictionaryItemLst, "code", "name");
			},
		});
	}
	//初始化所有角色,已选角色树
	function initAllRole(allRoleTree,roleTree){
		var setting = {
				view: {
					fontCss: getFontCss
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
		var zTreeRoleLeft = $.fn.zTree.init($("#ztree-leftTree"), setting,allRoleTree);
		zTreeRoleLeft.lastSearchValue = "" ;
		zTreeRoleLeft.nodeSearchList = [] ;
		zTreeRoleLeft.fontSearchCss = {} ;
		var zTreeRoleRight = $.fn.zTree.init($("#ztree-rightTree"), setting,roleTree);
		zTreeRoleRight.lastSearchValue = "" ;
		zTreeRoleRight.nodeSearchList = [] ;
		zTreeRoleRight.fontSearchCss = {} ;
		$(document).on("click", "#toRight", function(){
			var tree1 = $.fn.zTree.getZTreeObj("ztree-leftTree");
			var tree2 = $.fn.zTree.getZTreeObj("ztree-rightTree");
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
			var tree2 = $.fn.zTree.getZTreeObj("ztree-rightTree");
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
			searchNode(key, "ztree-leftTree") ;
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
			searchNode(key, "ztree-rightTree") ;
		});
		$(document).on("click", "#personLeft", function() {
			var num = parseInt($("#personNumber").text()) - 1;
			if(num >= 1){
				$("#personNumber").text(num);
				initPersonZtree();
			}
		});
		$(document).on("click", "#personRight", function() {
			var num = parseInt($("#personNumber").text()) + 1;
			var total = parseInt($("#personTotal").text());
			if(num <= total){
				$("#personNumber").text(num);
				initPersonZtree();
			}
		});
		$(document).on('keyup change', '#keySelectPerson', function () {
			$("#personNumber").text(1);
			$("#personTotal").text(1);
			initPersonZtree();
		});
	}
	function copyNodesToRight(nodes){
		var tree1 = $.fn.zTree.getZTreeObj("ztree-leftTree");
		var tree2 = $.fn.zTree.getZTreeObj("ztree-rightTree");
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
	function getFontCss(treeId, treeNode){
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
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
	function initUnitTree(unitRoleTree){
		var setting = {
				view: {
					addDiyDom: addDiyDom
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId",
						rootPId: "0"
					}
				},
				IDMark_Switch : "_switch" ,
				IDMark_Icon : "_ico" ,
				IDMark_Span : "_span" ,
				IDMark_Input : "_input" ,
				IDMark_Check :  "_check" ,
				IDMark_Edit :  "_edit" ,
				IDMark_Remove : "_remove" ,
				IDMark_Ul : "_ul" ,
				IDMark_A : "_a" ,
				IDMark_Radio : "_radio" 
		};
		var tree = $.fn.zTree.init($("#ztree-demo"), setting, unitRoleTree);
	}
	
	function addDiyDom(treeId, treeNode) {
		var tree = $.fn.zTree.getZTreeObj(treeId);
		var aObj = $("#" + treeNode.tId + tree.setting.IDMark_A);
		var check="";
		if(treeNode.diyMap.isCheck=="1"){
			check=' checked="checked"';
		}
		var editStr = '<input type="checkbox" id="diyBtn_'+treeNode.id+'" name="excludecheck" '
			+'class="icheckbox" value="'+treeNode.id+'" icheckStyle="ztreecancel-red"'+check+'>' ;
		aObj.after(editStr);
	}
	function updateNodes(highlight, treeId) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodeList = zTree.nodeSearchList ;
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
		}
	}
	//人员选择
	$(document).on("click", "#renyuan", function () {
		showMenuPerson();
	});
	$(document).on("click", "#upPerson", function () {
		showMenuPerson();
	});
function uploadeTree(allRoleTree,unitRoleTree,roleTree){
	//所有角色
	reAddAllNodes("ztree-leftTree", allRoleTree);
	//用户已有角色
	reAddAllNodes("ztree-rightTree", roleTree);
	//已选单位选择
	reAddAllNodes("ztree-demo", unitRoleTree);
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
	//父节点Id，节点List，后边别管了
	treeTemp.addNodes(null, nodes, null, null);
}
/**
 * 获取节点信息
 */
function findNodes(treeId,type){
	var treeTemp = $.fn.zTree.getZTreeObj(treeId);
	//获取所有节点
	var nodes = treeTemp.getNodes();
	if(type=="1"){
		//获取选中节点
		nodes=treeTemp.getCheckedNodes(true);
	}
	var obj;
	var treeNodes=new Array();
	for(var i in nodes){
		obj = new Object();
		obj.id = nodes[i].id;
		//nodes[i].diyMap.type;
		treeNodes[i]=nodes[i].id;
	}
	return treeNodes;
}

function intiSelectUnitTree() {
	//单位选择树
	var treeSelect = $.fn.zTree.init($("#ztree-unitSelect"), settingUnitSelect);
	treeSelect.lastSearchValue = "" ;
	treeSelect.nodeSearchList = [] ;
	treeSelect.fontSearchCss = {} ;
	$(document).on('focus', '#keyUnitSelect', function () {
		var key = $(this) ;
		 focusKey(key) ;
	});
	$(document).on('blur', '#keyUnitSelect', function () {
		var key = $(this) ;
		blurKey(key) ;
	});		
	
	$(document).on('keyup change', '#keyUnitSelect', function () {
		var key = $(this) ;
		searchNode(key, "ztree-unitSelect") ;
	});
}
/**
 * 初始化页面
 */
function accountRoles(unitNo){
	var dataMap = new Object();
	var id=unitNo==""?$("#id").val():"";
	dataMap["accountBean.id"]=id;
	dataMap["accountBean.unitId"]=unitNo;
	$.ajax({
		url : context+ '/userManage/initAddPageInfo.action',
		data : dataMap,
		type : "POST",
		btn : '#delete',
		dataType : "json",
		success : function(data) {
			if(id!=""){
				var acc=data.accountBean;
				$("#id").val(acc.id);//id
				$("#unitId").val(acc.unitId);//单位id
				$("#personNo").val(acc.personBean.personId);//人员Id
				$("#zhihuidanyuanName").val(acc.orderCellName);
				$("#pa_newPage_cellId").val(acc.orderCellId);
				$("#pa_newPage_cellType").val(acc.orderCellType);
				$("#personBelongUnitName").val(acc.unitName);//单位名称
				$("#upPerson").val(acc.personBean.name);//人员名称
				$("#username").val(acc.accountName);//帐号名
				$("#password").val(acc.password);//密码
				if(acc.strStartDate!=""&&acc.strStartDate!=null){
					$("#start").val(acc.strStartDate);//开始时间,
					}
				if(acc.strEndDate!=""&&acc.strEndDate!=null){
					$("#end").val(acc.strEndDate);//结束时间
				}
				if(acc.status == "启用"){
					$.select2.val("#selecttest1",constantStauts_qy, true);
				}else{
					$.select2.val("#selecttest1",constantStauts_ty, true);
				}
				$("#intro").val(acc.intro);//备注
			}else if(initData.personName != null && initData.personName != "" ){
				$("#upPerson").val(initData.personName);//人员名称
                $("#upPersonId").val(initData.personId);
                $("#pa_newPage_unitId").val(initData.unitId);
                $("#personBelongUnitName").val(initData.unitName);
                $("#username").val(initData.personPoliceNo);
                $("#password").val(initData.personPoliceNo);
                $("#personDev").show();
				$("#unitDev").show();
			}
			var allRoleTree=data.allRoles;//所有角色
			var roleTree=data.accountRoles;//以选择部分角色
			if(unitNo!=""){
				//跟新角色树数据(已选数据和节点数据)
				uploadeTree(allRoleTree,unitRoleTree,roleTree);
			}else{
				//初始化角色树
				initAllRole(allRoleTree,roleTree);
			}
		}
	});
}	
function initPersonZtree(){
	var dataMap=new Object();
	dataMap["accountBean.unitId"]=$("#unitId").val();
	dataMap["accountBean.personName"]= $("#keySelectPerson").val();
	dataMap["accountBean.num"]= $("#personNumber").text();
	$.ajax({
		url : context + "/userManage/initPersonTree.action",
		type : "POST",
		global : false,
		data : dataMap,
		dataType : "json",
		success : function(data) {
			if(data.totalNum>0){
				$("#personTotal").text(data.totalNum);
			}else{
				$("#personTotal").text(1);
			}
			var treeSelect = $.fn.zTree.init($("#ztree-personSelect"), settingSelectPerson, data.cellTree);
			treeSelect.lastSearchValue = "" ;
			treeSelect.nodeSearchList = [] ;
			treeSelect.fontSearchCss = {} ;
		},
	});
}
var settingSelectPerson = {
		view: {
			fontCss: getFontCss
		},
		async: false,
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
			}
		},
		callback:{
			onClick : function(event, treeId, treeNode) {
				$("#upPerson").val(treeNode.name);
				$("#personNo").val(treeNode.id);
				hideMenuPerson();
				$.validform.closeAllTips("validformId");

			}
		}
	};
//重构人员树
function reBuildPersonTree() {
	var treeIn = $.fn.zTree.getZTreeObj("ztree-personSelect");
	treeIn.destroy();
	treeIn = $.fn.zTree.init($("#ztree-personSelect"), settingSelectPerson);
	treeIn.lastSearchValue = "" ;
	treeIn.nodeSearchList = [] ;
	treeIn.fontSearchCss = {} ;
	$("#personTotal").text(1);
	
}
//选择单位树setting
var settingUnitSelect = {
		view: {
			fontCss: getFontCss
		},
		async: {
			enable: true,
			global: false,
			url:context+"/unit/initUnitTree.action",
			autoParam:["id=id"],
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
				$("#personBelongUnitName").val(treeNode.name);
				$("#unitId").val(treeNode.id);
				$("#personDev").show();
				$("#upPerson").val("");
				$("#upPersonId").val("");
				hideMenuUnit();
				initPersonZtree();
				
			}
		}
	};


function showMenuPerson() {
	var obj = $("#upPerson");
	var oOffset = $("#upPerson").offset();
	$("#menuContentPerson").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
	$("body").on("mousedown", onBodyDown);
	initPersonZtree();
}
function hideMenuPerson() {
	$("#menuContentPerson").fadeOut("fast");
	$("body").off("mousedown", onBodyDown);
}
function showMenuUnit() {
	var obj = $("#personBelongUnitName");
	var oOffset = $("#personBelongUnitName").offset();
	$("#menuUnitContent").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
	$("body").on("mousedown", onBodyDown);
}
function hideMenuUnit() {
	$("#menuUnitContent").fadeOut("fast");
	$("body").off("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuUnitContent" || $(event.target).parents("#menuUnitContent").length>0)) {
		hideMenuUnit();
	}
	if (!(event.target.id == "menuContentPerson" || $(event.target).parents("#menuContentPerson").length>0)) {
		hideMenuPerson();
	}
	
}
jQuery.extend($.common, { 
	//获取当前页面的值   用于按钮拿到当前页面的值进行 操作
	save:function(index){
		//取到最外层div的根据id
		var demo = $.validform.getValidFormObjById("myvf") ;
		//是否有没通过的验证
		var flag = $.validform.check(demo) ;
		//是给予提示  
		if(flag){
			var te2 = new Object();
			var url="";
			if($("#id").val()!=null&&$("#id").val()!=""){
				url="/userManage/updateAccount.action";
			}else{
				url="/userManage/saveAccount.action";
			}
			te2["accountBean.id"]=$("#id").val();//id
			te2["accountBean.unitId"]=$("#unitId").val();//单位id
			te2["accountBean.personBean.personId"]=$("#personNo").val();//人员编码
			te2["accountBean.accountName"]=$("#username").val();//帐号名
			te2["accountBean.password"]=$("#password").val();//密码
			te2["accountBean.strStartDate"]=$("#start").val();//开始时间
			te2["accountBean.strEndDate"]=$("#end").val();//结束时间
			te2["accountBean.status"]=$("#selecttest1").val();//状态
			te2["accountBean.intro"]=$("#intro").val();//备注
			te2["accountBean.orderCellId"]=$("#pa_newPage_cellId").val();
			//获取已选角色
			var roleNods=findNodes("ztree-rightTree","0");
			var lent=0;
			for(var i in roleNods){
				te2["accountBean.roleBeans["+i+"].id"]=roleNods[i];
				te2["accountBean.roleBeans["+i+"].flag"]=true;
				lent=lent+1;
			}
			var i=lent+0;
			$('input[name="excludecheck"]:checked').each(function(){ 
				te2["accountBean.roleBeans["+i+"].id"]=$(this).val();
				te2["accountBean.roleBeans["+i+"].flag"]=false;
				i++;
			});
			$.ajax({
				url : context+url,
				data : te2,
				type : "POST",
				btn : '#delete',
				dataType : "json",
				success : function(data) {
					if(data.flag == "false"){
						$.layerAlert.alert({msg:data.msg,icon:2}) ;
					}else{
						if(data.flag == "true"){
							if($("#id").val()!=null&&$("#id").val()!=""){
								$.util.topWindow().$.layerAlert.alert({msg:"修改成功。",title:"提示",icon:6,yes:function(){
									$.util.topWindow().$.layerAlert.closeWithLoading(number);
			    				}})
							}else{
								$.util.topWindow().$.layerAlert.alert({msg:"保存成功。",title:"提示",icon:6,yes:function(){
									$.util.topWindow().$.layerAlert.closeWithLoading(number)
			    				}})
							}
						}
						else{
							if($("#id").val()!=null&&$("#id").val()!=""){
								$.layerAlert.alert({msg:"修改失败！",icon:5}) ;
							}else{
								$.layerAlert.alert({msg:"保存失败！",icon:5}) 
							}
						}
					}
				}
			});
		}
		//return(flag)
	},
	setAccountId:function (id){
		$("#id").val(id);
		accountRoles("");
	},
	setTestObj2:function(index){
		number=index;	//从父页面获取当前弹出层   用于关闭
	},
	setPerson:function (personName,unitName,unitId,personNo){
		$("#upPerson").val(personName);
		$("#unitName").val(unitName);
		$("#unitId").val(unitId);
		$("#personNo").val(personNo);
	}
});	
})(jQuery);