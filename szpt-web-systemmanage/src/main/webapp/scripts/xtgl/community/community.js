(function($){
	"use strict";
	
	var communityTable;		//社区table
	$(document).ready(function(){
		intiSelectUnitTree() ;
		initTable();
	});
	
	/**
	 * 查询
	 */
	$(document).on("click","#query",function(){
		communityTable.draw(true);
	});
	
	/**
	 * 重置
	 */
	$(document).on("click","#reset",function(){
		$("#communityName").val("");
		$("#unitId").val("");
		$("#unitName").val("");
		communityTable.draw(true);
	});
	
	/**
	 * 新建
	 */
	$(document).on("click","#addCommunity",function(){
		$.layerAlert.dialog({
			content : context +  '/community/showNewCommunityJsp.action',
			pageLoading : true,
			title:"新增社区",
			width : "40%",
			height : "400px",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.common ;
					cm.saveCommunity();
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		initData:{
    			
    		},
    		end:function(){
    			communityTable.draw(true);
    		}
		});
	});
	
	/**
	 * 修改
	 */
	$(document).on("click",".modify",function(){
		var tr = $(this).parents("tr");		//按钮所在的tr
		var row=communityTable.row(tr);	//行对象
		var data=row.data();	//行所有数据
		$.layerAlert.dialog({
			content : context +  '/community/showUpdateCommunityJsp.action',
			pageLoading : true,
			title:"修改社区信息",
			width : "40%",
			height : "400px",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.common ;
					cm.updateCommunity();
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		initData:{
    			community:data
    		},
    		end:function(){
    			communityTable.draw(false);
    		}
		});
	});
	
	/**
	 * 删除
	 */
	$(document).on("click",".delete",function(){
		var tr = $(this).parents("tr");		//按钮所在的tr
		var row=communityTable.row(tr);	//行对象
		var data=row.data();	//行所有数据
		$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){
				//点击确定按钮后执行
				$.ajax({
					url:context +'/community/deleteCommunityById.action',
					type:'post',
					dataType:'json',
					data:{"communityId":data.id},
					success:function(json){
						if(json.flag){
							$.util.topWindow().$.layerAlert.alert({msg:json.msg,title:"提示",time:1500}) ;
							communityTable.draw(false);
						}else{
							$.util.topWindow().$.layerAlert.alert({msg:json.msg,title:"提示"}) ;
						}
					}
				});	
			}
		});
	});
	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +"/community/findAllCommunity.action";		
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "",
					"title" : "社区名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 1,
					"width" : "",
					"title" : "社区编码",
					"data" : "code",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "",
					"title" : "所属单位",
					"data" : "unitName",
					"render" : function(data, type, full, meta) {
						
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "",
					"title" : "所属大社区",
					"data" : "bigCommunity",
					"render" : function(data, type, full, meta) {
						
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "",
					"title" : "修改时间",
					"data" : "updateTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "15%",
					"title" : "操作",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						var da="<button class='table-row-save btn btn-primary btn-xs m-ui-btnxs modify'>" +
							"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>" +
							"</span>修改</button>" +
							"<button class='table-row-delete btn btn-danger btn-xs m-ui-btnxs delete'>" +
							"<span class='glyphicon glyphicon-remove' aria-hidden='true'>" +
							"</span>删除</button>";
						return da;
					}
				}

		];
		tb.lengthChange = false;
		// 每页条数
		tb.lengthMenu = [ 10 ];
		tb.searching = false;
		tb.ordering = false;
		//是否排序
		tb.ordering = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = true ;
		tb.paramsReq = function(d, pagerReq) {
			var name = $("#communityName").val();
			var unitId = $("#unitId").val();	
			$.util.objToStrutsFormData(name, "name", d);
			$.util.objToStrutsFormData(unitId, "unitId", d);
		};
		tb.paramsResp = function(json) {
			json.data = json.communityBeanList;
			json.recordsFiltered = json.totalNum;
			json.recordsTotal = json.totalNum;
		},
		tb.initComplete = function(){ //表格加载完成后执行的函数
			
		};

		communityTable = $("#communityTable").DataTable(tb);
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
		if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
})(jQuery);