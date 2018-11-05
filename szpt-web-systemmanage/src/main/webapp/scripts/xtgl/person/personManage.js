(function($){
	"use strict";
	var table = null;//dataTable对象
	var unitBean = null;//当前选中单位名称
	var mobility = false;//是否是单位调动
	$(document).ready(function() {	
		intiUnitTree();	//初始化内部单位树
		initPersonAdminPage();//初始化页面
		
		/*
		 * 同步按钮
		 */
		$(document).on("click", "#synchronousBtn", function(){
			var synchronousUnit = function(pageIndex){
				$.ajax({
					url:context +'/personManage/synchronousUser.action',
					data:{},
					type:"post",
					dataType:"json",
				    customizedOpt:{
					     ajaxLoading:false,//设置是否loading
					},
					success:function(successData){
						
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
						table.draw(true);
						
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
		
		$("#pa_search").on("click",function(){//查询按钮
			table.draw(true);//重绘表格
		});
		$("#pa_resetting").on("click",function(){//重置
			$("#pa_name").val("");//姓名重置
			$.select2.selectByOrder("#pa_sex", 1, true);
			$.select2.selectByOrder("#pa_status",1, true);
			$('#pa_unit').iCheck('check');
			table.draw(false);
		});
		$("#pa_new").on("click",function(){//新增
			mobility = false;
			if($("#pa_unitCode").val().length == 0){
				alertHint("请选择单位",0);//自动关闭的提示框
			}else{
				alertUpdatePersonPage(null);//新增人员
			}
		});
		$("#pa_update").on("click",function(){//修改
			if($.icheck.getChecked("pa_dataTable_trCheckbox").length == 0){
				$.layerAlert.alert({msg:"请选择人员！"}) ;
			}else if($.icheck.getChecked("pa_dataTable_trCheckbox").length == 1){
				var clickIcheck = $.icheck.getChecked("pa_dataTable_trCheckbox");
				var clickRowData = $(clickIcheck).parents("tr").data("rowData");
				mobility = false;
				alertUpdatePersonPage(clickRowData);//调用弹窗方法
			}else{
				alertHint("只能对一个人员进行修改",0);//自动关闭的提示框
			}
		});
		$("#pa_delete").on("click",function(){//删除
			
			if($.icheck.getChecked("pa_dataTable_trCheckbox").length > 0){
				var parsonIdArray = new Array();
				$($.icheck.getChecked("pa_dataTable_trCheckbox")).each(function(i,val){
					var rowData = $(val).parents("tr").data("rowData");
					parsonIdArray.push(rowData.personId);
				});
				
				$.layerAlert.confirm({
		    		msg:"删除后不可恢复，确定要删除吗？",
		    		title:"提示",		//弹出框标题
		    		width:'300px',
					hight:'200px',
					shade: [0.5,'black'],  //遮罩
					icon:3,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
					shift:1,			//弹出时的动画效果  有0-6种
		    		yes:function(index, layero){	//点击确定按钮
		    			deletePerson(parsonIdArray);
		    		}
		    	});
			}else{
				$.layerAlert.alert({msg:"请选择人员！"}) ;
			}
			
		});
		$("#pa_shift").on("click",function(){//单位
			if($.icheck.getChecked("pa_dataTable_trCheckbox").length == 0){
				$.layerAlert.alert({msg:"请选择人员！"}) ;
			}else if($.icheck.getChecked("pa_dataTable_trCheckbox").length == 1){
				var clickIcheck = $.icheck.getChecked("pa_dataTable_trCheckbox");
				var clickRowData = $(clickIcheck).parents("tr").data("rowData");
				mobility = true;
				alertUpdatePersonPage(clickRowData);//调用弹窗方法
			}else{
				alertHint("只能对一个人员进行调动",0);//自动关闭的提示框
			}
		});
		
		
		
		
	});

	
/**
 * 初始化人员管理页面
 */
function initPersonAdminPage(){
	//加载下拉列表
	$.ajax({
		url:context +'/personManage/initPersonManagePage.action',
		async:false,
		type:'post',
		dataType:'json',
		data:{},
		success:function(successData){
			var sexList = new Array();
			sexList.push({"code":"00","name":"全部"});
			$(successData.personAdminPageBean.sexList).each(function(i,val){
				sexList.push(val);
			});
			$.select2.addByList("#pa_sex", sexList,"code","name");//添加性别默认值
			var statusList = new Array();
			statusList.push({"code":"00","name":"全部"});
			$(successData.personAdminPageBean.statusList).each(function(i,val){
				statusList.push(val);
			});
			$.select2.addByList("#pa_status",statusList,"code","name");//添加状态默认值
			$("#pa_unitCode").val(successData.personAdminPageBean.unitCode);
			unitBean = {
			    "unitId":successData.personAdminPageBean.unitId,
				"unitCode":successData.personAdminPageBean.unitCode,
				"unitName":	successData.personAdminPageBean.unitName,//选择的单位名称
				"unitFullName":successData.personAdminPageBean.unitName
			}
		},
		error:function(errorData){
			
		}
	});
	
	//加载表格
	var tb = $.uiSettings.getOTableSettings();
	tb.ajax.url = context+ "/personManage/searchPersonList.action";
	tb.columnDefs = [
	    {
			"targets" : 0,
			"width" : "5%",
			"title" : '选择',
			"className" : "table-checkbox",
			"data" : "",
			"render" : function(data, type,full, meta) {
					var a = '<input type="checkbox" name="pa_dataTable_trCheckbox" class="icheckbox" />';
					return a;
			}
	    },
	    {
			"targets" : 1,
			"width" : "20%",
			"title" : "姓名",
			"data" : "name",
			"render" : function(data, type,full, meta) {
					return data;
			}
	    },
	    {
			"targets" : 2,
			"width" : "20%",
			"title" : "性别",
			"data" : "sex",
			"render" : function(data, type,full, meta) {
					return data;
			}
	    },
	    {
			"targets" : 3,
			"width" : "20%",
			"title" : "所属单位",
			"data" : "unitFullName",
			"render" : function(data, type,full, meta) {
					return data;
			}
	    },
	    {
			"targets" : 4,
			"width" : "20%",
			"title" : "状态",
			"data" : "status",
			"render" : function(data, type,full, meta) {
					return data;
			}
	    }];
	tb.ordering = false;
	tb.lengthMenu = [10],
	tb.searching = false;
	tb.lengthChange = false;
	tb.autoFooter = false;
	tb.paramsReq = function(d, pagerReq) {//查询条件回调，初始化和重绘时候都会先执行该回调
		var arr = $.icheck.getChecked("pa_iCheck");//获取是否选择查询下级单位
		var personBean = {
			"name":$("#pa_name").val(),
			"sex":$.select2.val("#pa_sex")=="00"?"":$.select2.val("#pa_sex"),
			"status":$.select2.val("#pa_status")=="00"?"":$.select2.val("#pa_status"),
			"unitCode":$("#pa_unitCode").val(),
		}
		$.util.objToStrutsFormData(personBean, "personBean", d);
	};
	tb.paramsResp = function(json) {//处理返回的数据的回调
		json.data = json.personAdminBeanList;
		json.recordsTotal = json.totalNum;
		json.recordsFiltered = json.totalNum;
	};
	
	tb.rowCallback = function(row, data, index) {//行绘制完毕之后回调
		$(row).data("rowData",data);
		//行双击事件
		$(row).on("dblclick",function(){
			var rowData = $(this).data("rowData");
			alertLookPersonPage(rowData);//调用弹出查看人员页面的方法
		});
	};
	
	tb.myDrawCallback = function(settings){//表格重绘完成之后回调方法
		$("input[name='pa_dataTable_AllCheckbox']").on("ifChanged", function(event){
			if($("#pa_dataTable tbody tr").length == $.icheck.getChecked("pa_dataTable_trCheckbox").length){
				
				$.icheck.reverseCheck("pa_dataTable_trCheckbox") ;
			}else{
				$.icheck.selectCheck("pa_dataTable_trCheckbox") ;
			}
		});
		
		//checkBox选中事件
		$("input[name='pa_dataTable_trCheckbox']").on("ifChanged", function(event){
			$("#pa_dataTable tr").css("background-color","");
			
			var arr = $.icheck.getChecked("pa_dataTable_trCheckbox");
			$(arr).each(function(i,val){
				$(val).parents("tr").css("background-color","");
			});
		});
		
    };
	table = $("#pa_dataTable").DataTable(tb);
	
}	
/**
 * 弹出保存/修改页面
 * @param personAdminBean 点击行数据对象
 */
function alertUpdatePersonPage(personAdminBean){
	var titleTemp;
	if(personAdminBean==null){
		titleTemp="新增人员";
	}else{
		if(mobility){
			titleTemp="跨单位调度";

		}else{
			titleTemp="修改人员";

		}
	}
	$.util.topWindow().$.layerAlert.dialog({
		content:context+'/personManage/showPaNewPage.action',
		title:titleTemp,
		width:"802px",
		height:"522px",
		shadeClose:false,//点击最外层是否关闭弹窗
		initData:{
			table:table,
			personAdminBean:personAdminBean,
			unitBean:unitBean,
			mobility:mobility			
		},
		btn:null,
		success:function(layero, index){
			table.draw(false);
		}
	});
}
/**
 * 弹出人员信息查看页面
 * @param object 选择行人员对象数据
 */
function alertLookPersonPage(object){
	$.layerAlert.dialog({
		content:context+'/personManage/showPaLookPage.action',
		title:'查看人员信息',
		width:"602px",
		height:"416px",
		shadeClose:false,//点击最外层是否关闭弹窗
		initData:{
			object:object
		},
		btn:null,
		success:function(layero, index){
			
		}
	});
}

/**
 * 删除人员
 */
function deletePerson(parsonIdArray){
	var gData = new Object();//新建一个存放转换后的空对象
	$.util.objToStrutsFormData(parsonIdArray, "personIdList", gData);
	
	$.ajax({
		url:context +'/personManage/deletePerson.action',
		async:true,
		type:'post',
		dataType:'json',
		data:gData,
		success:function(successData){
			if(successData.flag == "false" ){
				alertHint(successData.msg,5);
			}else{
				table.draw(false);
				alertHint("删除成功",6);
			}
			
		},
		error:function(errorData){
			alertHint("删除失败",6);
		}
	});
}
	
/**树的设置*/
var settingIn = {
		view: {
			fontCss: getFontCss
		},
		//点击+号时查询子节点
		async: {
			enable: true,
			global:false,
			url:context+"/department/initDepartmentTree.action",
			autoParam:["id=unitId"],	//前一个参数  节点id   后一个参数  后台接受的参数（作为查询条件，查询子节点）
			dataFilter: function(treeId, parentNode, childNodes) {
				return childNodes.departmentTree;		//inTreeNodesResult为查询出的子节点集合
			}
		},
		//这块就这样不用修改
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
			}
		},
		//节点单机事件
		callback:{	
			onClick : function(event, treeId, treeNode) {
				$("#pa_unitCode").val(treeNode.code);
				unitBean = {
					"unitId":treeNode.id,
					"unitCode":treeNode.code,
					"unitName":	treeNode.name,//选择的单位名称
					"unitFullName":treeNode.name
				}
				$("#pa_name").val("");//姓名重置
				$.select2.selectByOrder("#pa_sex", 1, true);
				$.select2.selectByOrder("#pa_status",1, true);
				$('#pa_unit').iCheck('check');
				table.draw(true) ;
			}
		}
	};

	function reBuildUnitTree() {
	var treeIn = $.fn.zTree.getZTreeObj("ztree-inUnitTree");
	treeIn.destroy();
	treeIn = $.fn.zTree.init($("#ztree-inUnitTree"), settingIn);
	treeIn.lastSearchValue = "" ;
	treeIn.nodeSearchList = [] ;
	treeIn.fontSearchCss = {} ;
}
	/**初始化树*/
function intiUnitTree() {
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
	/**失去焦点事件*/
function focusKey(key) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
/**获得焦点事件*/
function blurKey(key) {
	if (key.get(0).value == "") {
		key.addClass("empty");
	}
}
/**更新节点方法*/
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
/**查询树节点*/
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
	//将符合条件的  节点  更新
	updateNodes(true, treeId);
}
/**节点样式*/
function getFontCss(treeId, treeNode){
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

/**
 * alter自动关闭提示框
 */
function alertHint(msg,icon){
	$.layerAlert.alert({
		msg:msg,
		title:"提示",		//弹出框标题
		width:'300px',
		hight:'200px',
		shade: [0.5,'black'],  //遮罩
		icon:icon,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
		shift:1,			//弹出时的动画效果  有0-6种
	});
}
//测试
jQuery.extend($.common, { 
	testA:function(canshu1){
	}
});	
})(jQuery);	