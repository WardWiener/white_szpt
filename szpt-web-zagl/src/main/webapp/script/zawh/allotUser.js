$.specialCaseRoleAssignment = $.specialCaseRoleAssignment || {};
(function($){
	"use strict";
	var id = "";
	var name = "";
	var table = null;
    var table2=null;	
	var zNodesT1 = [];
	var unitId = "";
	
	var sex = null;
	$(document).ready(function(){
		  
		  init();
		  
		    /**
			 * 可分配角色选择样式更改
			 */
			$(document).on("click", "#role li", function(e){
				$('#role li').attr("class","");
				 $(this).attr("class","active");
			});
			
			/**
			 * 已分配角色选择样式更改
			 */
			$(document).on("click", "#role2 li", function(e){
				$('#role2 li').attr("class","");
				 $(this).attr("class","active");
				 creatTable2(); //点击角色可查询已分配的角色名单
			});
			/**
			 * 查询按钮
			 */
			$(document).on("click", "#searchBtn", function(){
				//zazllxCode = null;
				creatTable();
			})
			/**
			 * 分配用户按钮
			 */
			$(document).on("click", "#save", function(){
				addRole();
			})
			/**
			 * 取消分配角色按钮
			 */
			$(document).on("click", "#save2", function(){
				removeRole();
			})
			/**
			 * 取消分配角色按钮
			 */
			$(document).on("click", "#res2", function(){
				var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
				var pageIndex = frameData.index ;
				$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
			})
			/**
			 * 取消分配角色按钮
			 */
			$(document).on("click", "#res", function(){
				var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
				var pageIndex = frameData.index ;
				$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
			})
			/**
			 * 取消分配角色的取消按钮
			 */
			$(document).on("click", "#res2", function(){
				var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
				var pageIndex = frameData.index ;
				$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
			})
			/**
			 * 已分配切换页面--更改角色样式
			 */
			$(document).on("click", "#yfpBtn", function(){
				$('#role').hide();
				$('#role2').show();
			})
			/**
			 * 可分配切换页面--更改角色样式
			 */
			$(document).on("click", "#kfpBtn", function(){
				$('#role').show();
				$('#role2').hide();
			})
	})

	function removeRole(){

		var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==0){
			$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
		}else{
			var arr=new Array();//人员id
			var arr2=new Array();//角色id
			$('input[type="checkbox"]:checked').each(function(){
				arr.push($(this).attr("bb"));
				arr2.push($(this).attr("js"));
			})
			var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	     	var initData = frameData.initData.lockerId ;
			var data={
					personId:arr, //人员id数组
					caseId:initData, //专案id
					roleId:arr2    //角色id
			}
			var queryStr = $.util.toJSONString(data);
			$.ajax({
				url:context +'/zawh/removeRole.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var a=successData.resultMap.result;
					if(a==true){
						$.layerAlert.alert({title:"提示",msg:"取消分配操作成功"});
						creatTable();
						creatTable2();
					}else{
						$.layerAlert.alert({title:"提示",msg:"取消分配操作失败"});
					}
				},
				error:function(data){
					alert("系统繁忙。。。。");
				}
			});	
        
		}
	
	}

	
	function addRole(){
		var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==0){
			$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
		}else{
			var arr=new Array();
			$('input[type="checkbox"]:checked').each(function(){
				arr.push($(this).attr("bb"));
			})
			var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	     	var initData = frameData.initData.lockerId ;
	     	var jsid= $('#role .active a').attr("bb");
			var data={
					personId:arr,   //人员id数组
					caseId:initData, //专案id
					roleId:jsid      //角色id
			}
			var queryStr = $.util.toJSONString(data);
			$.ajax({
				url:context +'/zawh/saveRole.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var a=successData.resultMap.result;
					if(a==true){
						$.layerAlert.alert({title:"提示",msg:"分配成功"});
						creatTable();
						creatTable2();						
					}else{
						$.layerAlert.alert({title:"提示",msg:"分配失败"});
					}
				},
				error:function(data){
					$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
				}
			});	
        
		}
	}
	
	
	/**
	 * 初始化
	 */
	function init(){	
		onloadData();
		cshTree();
		creatTable();
		cshTree2();
		creatTable2();
	}
	
	/**
	 * 初始化树状结构并绑定点击事件
	 * @returns
	 */
	function cshTree2(){
		$.ajax({
			url:context +'/zazlwh/findAllUnitAndDepartment.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				zNodesT1 = [];
				initDwLst(zNodesT1,successDate.resultMap.result);
				var setting = {
						callback: {
							onClick: zTreeOnClick2
						},
						
						data: {
							simpleData: {
								enable: true
							}
						}
					};
				$.fn.zTree.init($("#treeDemo2"), setting, zNodesT1);
			}
		})
	}
	
	/**
	 * 点击事件--已分配角色 
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @returns
	 */
	function zTreeOnClick2(event, treeId, treeNode) {
		unitId = treeNode.id;
		creatTable2();
	}
	
	
	
	/**
	 * 初始化树状结构并绑定点击事件
	 * @returns
	 */
	function cshTree(){
		$.ajax({
			url:context +'/zazlwh/findAllUnitAndDepartment.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				zNodesT1 = [];
				initDwLst(zNodesT1,successDate.resultMap.result);
				var setting = {
						callback: {
							onClick: zTreeOnClick
						},
						
						data: {
							simpleData: {
								enable: true
							}
						}
					};
				$.fn.zTree.init($("#treeDemo1"), setting, zNodesT1);
			}
		})
	}
	
	/**
	 * 点击事件--分配角色 
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @returns
	 */
	function zTreeOnClick(event, treeId, treeNode) {
		unitId = treeNode.id;
		creatTable();
	}
	
	
	//初始化ztree
	function initDwLst(zNodesT1, data){
		if(null != data.dwLst && data.dwLst.length > 0){
			var cell = data.dwLst;
			for(var i in cell){
				if(null != cell[i].dwLst && null != cell[i].length >0){
					var dataCell = {
							id:cell[i].id, pId:data.id, name:cell[i].name, open:false, iconOpen:"../images/ztree/ztree-icon_dw.png", iconClose:"../images/ztree/ztree-icon_dw.png"	
					}
					zNodesT1.push(dataCell);
					initDwLst(zNodesT1,cell[i]);
				}else{
					var dataCell = {
							id:cell[i].id, pId:data.id, name:cell[i].name, icon:"../images/ztree/ztree-icon_dot.png"	
					}
					zNodesT1.push(dataCell);
				}
			}
		}
		zNodesT1=[];
	}
	
	
	
	$(document).on("ifChecked", ".icheckbox", function(e){			
			id = $(this).attr("valId");
			name = $(this).attr("valName");			
			$.each( $(".icheckbox"), function(e,m){
				if($(m).attr("valId") != id){
					$(m).iCheck('uncheck');
				}
			} ); 
		});
	
	//初始化表格
	function creatTable(){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zawh/findAssignableRole.action';
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "7%",
					"title" : "",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return '<input type="checkbox" name="askRoom" bb='+full.id+' class="icheckbox"/>';
					}
				},
				{
					"targets" : 1,
					"width" : "7%",
					"title" : '序号',
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "10%",
					"title" : "性别",
					"data" : "sex",
					"render" : function(data, type, full, meta) {						
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "18%",
					"title" : "所属机构",
					"data" : "unit",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 5 ];
		var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
		var initData = frameData.initData.lockerId ;
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"organization" : unitId,//单位id
				"name" : $("#personName").val(),
				"sex" : $('#sex option:selected') .val(),
				"ifSub" : $('#radio1 input:radio:checked').val(),
				"caseId":initData,
				"start":d.start,
				"length":d.length
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = json.resultMap.result;
		};
		table = $("#tavleId").DataTable(tb);
	}
	
	
	/**
	 * 初始化表格--已分配
	 */
	function creatTable2(){
		if(table2 != null) {
			table2.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zawh/findAlreadyAssignableRole.action';
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "7%",
					"title" : "",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return '<input type="checkbox" name="askRoom" bb='+full.id+' js='+full.roleId+' class="icheckbox"/>';
					}
				},
				{
					"targets" : 1,
					"width" : "7%",
					"title" : '序号',
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "10%",
					"title" : "性别",
					"data" : "sex",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "18%",
					"title" : "所属机构",
					"data" : "unit",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "15%",
					"title" : "角色",
					"data" : "roleName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 5 ];
		var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
		var initData = frameData.initData.lockerId ;
		var jsid="";
		jsid= $('#role2 .active a').attr("bb");
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"organization" : unitId,//单位id
				"caseId":initData, //专案id
				"roleId":jsid,  //角色id
				"start":d.start,
				"length":d.length
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.totalNum;
			json.recordsFiltered = json.resultMap.totalNum;
			json.data = json.resultMap.result;
		};
		table2 = $("#tavleId2").DataTable(tb);
	}
	  
	/**
	 * 角色分配加载项
	 */
	function onloadData(){
		$.ajax({
			url:context+'/zawh/findRole.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				var specialCaseRole=successData.resultMap.specialCaseRole;
				var sexList=successData.resultMap.sexList;
				var b="";
				for(var i=0;i<sexList.length;i++){
					if(sexList[i].name=='未知'){
						
					}else{
						b+='<option value="'+sexList[i].code+'" >'+sexList[i].name+'</option>';
					}
				}
				$('#sex').append(b);
				$('#sex2').append(b);
				for(var i=0;i<specialCaseRole.length;i++){
					var objTableTemplate = $("#role #model");
					var objTable = objTableTemplate.clone(true);
					objTable.show();
					objTable.insertBefore(objTableTemplate);					
					$.each(objTable.find(".valCell"),function(){
						$(this).text(specialCaseRole[i][$(this).attr("valName")]);
						$(this).attr("bb",specialCaseRole[i].id);
					})
					objTable.attr("id",objTable.attr("id")+i);
					if(i==0){
						objTable.attr("class","active");
					}
				}
				//已分配 的角色样式
				for(var i=0;i<specialCaseRole.length;i++){
					var objTableTemplate = $("#role2 #model2");
					var objTable = objTableTemplate.clone(true);
					objTable.show();
					objTable.insertBefore(objTableTemplate);					
					$.each(objTable.find(".valCell"),function(){
						$(this).text(specialCaseRole[i][$(this).attr("valName")]);
						$(this).attr("bb",specialCaseRole[i].id);
					})
					objTable.attr("id",objTable.attr("id")+i);
					objTable.attr("bb",specialCaseRole[i].id);
					
				}
				
				
			},
			error:function(data){
				$.layerAlert.alert({title:"提示",msg:"分配用户加载NG"});
			}
			
		
		});
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
//	jQuery.extend($.specialCaseRoleAssignment, { 
//		update : update,
//	});	
	
})(jQuery);