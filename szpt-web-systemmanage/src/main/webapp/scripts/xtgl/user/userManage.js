(function($) {
	"use strict";
	var table;
/**
 * 
 */
$(document).ready(function(){
	intiUnitTree();
	setstatus();

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
			$.select2.addByList("#state", data.dictionaryItemLst, "code", "name");
		},
	});
}
$(document).on('click','#reset',function() {
	$.layerAlert.dialog({
		content : context+'/userManage/resetpw.action',
		pageLoading : true,
		width : "30%",
		height : "30%",
		title:"重置密码",
		btn:["保存","关闭"],
		shadeClose : false,
		success:function(layero, index){
			
		},
		callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
				btn1:function(index, layero){
			 		//index:弹出层的标识；layero
					//layero:层的jquery对象
					var win = $.layerAlert.frameWindow(index) ;
    				var mima1 = win.$.common.getmima1() ;
    				var mima2 = win.$.common.getmima2() ;
					if(mima1==""||mima1=="null"||mima2==""||mima2=="null"){
						$.layerAlert.alert({msg:"请输入密码",title:"提示"}) ;
					}else if(mima1.length<6){
						$.layerAlert.alert({msg:"不得少于6位",title:"提示"}) ;
					}else if(mima1==mima2){
    					setpw(mima1);
    					layer.close(index);	//关闭层
					}
    				else{
    					$.layerAlert.alert({msg:"输入不一致",title:"提示"}) ;
    				}
			    },
			    btn2:function(index, layero){
    				layer.close(index);		//关闭弹窗
    				table.draw(false) ;
    			}
			 }
	});
	resetbtn();
});
var setpw=function(mima){
	var arr = $.icheck.getChecked("check");
	var ids = [];
	var rows = [];
	$.each(arr,function(i,val) {
						var tr = $(val).parents("tr");
						var row = table.row(tr);
						rows.push(row);
						var data = row.data();
						var id = data.id;
						ids.push(id);
					});

	var idsStr = ids.join(",");
	$.ajax({
		url : context+ '/userManage/resetPassWord.action',
		data : {
			ids : idsStr,
			password:mima
		},
		type : "POST",
		btn : '#delete',
		dataType : "json",
		success : function(data) {
		   $.layerAlert.alert({msg:"重置成功",title:"提示",icon:6}) ;
			table.draw(false) ;
		}
	});
}

$(document).on('click', "#add", function () {	
	var win = $.util.rootWindow();
	$.util.topWindow().$.layerAlert.dialog({
		content : context+'/userManage/addUser.action',
		pageLoading : true,
		width : "800px",
		height : "85%",
		title:"新增用户信息",
		btn:["保存", "取消"],
		shadeClose : false,
		initData:{
			id:"",
			cellId:$("#scell").val()
		},
		success:function(layero, index){


		},
		end:function(){
			table.draw(false)	
		},
		callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
				btn1:function(index, layero){
					$(this).attr("disabled","true");
					var win = $.layerAlert.frameWindow(index) ;
    				win.$.common.save(index) ;
    				table.draw(true);
    				$(this).removeAttr("disabled","true");
			    },
			    // cancel默认代表btn数组中第二个名称的按钮和取消按钮并且点击会默认关闭层
			    btn2:function(index, layero){
			    	layer.close(index);		//关闭弹窗
			    	table.draw(false) ;
			    }
			 }
	});
	resetbtn();
});


var edit=function(check){
	var r = $(check).parents("tr") ;
	var row = table.row(r) ;
	var full = row.data() ;
	var win = $.util.rootWindow();
	$.util.topWindow().$.layerAlert.dialog({
		content : context+'/userManage/addUser.action',
		pageLoading : true,
		width : "800px",
		height : "100%",
		title:"修改用户信息",
		btn:["保存", "取消"],
		shadeClose : false,
		initData:{
			id:full.id,
			cellId:$("#scell").val(),
			cellName:full.orderCellName
		},
		success:function(layero, index){

		},
		end:function(){
			table.draw(false)	
		},
		callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
				btn1:function(index, layero){
					var win = $.layerAlert.frameWindow(index) ;
					win.$.common.save(index);  				
			    },
			    // cancel默认代表btn数组中第二个名称的按钮和取消按钮并且点击会默认关闭层
			    btn2:function(index, layero){
			    	layer.close(index);		//关闭弹窗
			    }
			    
			 }
		
	});
	
	
}
$(document).on('click', "#edit", function () {
	edit($.icheck.getChecked("check") );
	resetbtn();
	
});

$(document).on('click','#delete',function() {
	var arr = $.icheck.getChecked("check");
	var ids = [];
	var rows = [];
	$.each(arr,function(i,val) {
						var tr = $(val).parents("tr");
						var row = table.row(tr);
						rows.push(row);
						var data = row.data();
						var id = data.id;
						ids.push(id);
					});

	var idsStr = ids.join(",");
	
	$.layerAlert.confirm({
		msg : "确认删除吗？",
		title:"提示",
		yes : function(index,layero) {
			$.ajax({
				url : context+ '/userManage/delete.action',
				data : {
					ids : idsStr
				},
				type : "POST",
				btn : '#delete',
				dataType : "json",
				success : function(data) {
					if(data.flag=="true"){
						table.draw(false) ;
						resetbtn();
						$.layerAlert.alert({msg:"删除成功",title:"提示",icon:6}) ;
					}else{
						$.layerAlert.alert({msg:"删除失败",title:"提示",icon:5}) ;
					}
					
				}
			});
		}
	});
});

$(document).on('click', "#stop", function () {
	var arr = $.icheck.getChecked("check");
	var r = $(arr).parents("tr") ;
	var row = table.row(r) ;
	var full = row.data() ;
	var dataMap ="";

	$.layerAlert.confirm({
 		msg:"用户停用后，该用户将失效，您确定停用吗？",
 		title:"提示",
 		yes:function(index, layero){
 			$.ajax({
 				url : context+ '/userManage/stop.action',
 				data : {
 					"id":full.id
 				},
 				type : "POST",
 				dataType : "json",
 				success : function(data) {
 					if(data.flag=="true"){
 						table.draw(false) ;
 						$.layerAlert.alert({msg:"停用成功",title:"提示",icon:6}) ;
 					}else{
 						$.layerAlert.alert({msg:"停用失败",title:"提示",icon:6}) ;
 						
 					}
 				}
 			});
 		}
 	});
	
	resetbtn();
});
$(document).on('click', "#start2", function () {
	var arr = $.icheck.getChecked("check");
	var r = $(arr).parents("tr") ;
	var row = table.row(r) ;
	var full = row.data() ;
	var dataMap ="";
	$.ajax({
		url : context+ '/userManage/start.action',
		data : {
			"id":full.id
		},
		type : "POST",

		dataType : "json",
		success : function(data) {
			if(data.flag=="true"){
				table.draw(false) ;
				$.layerAlert.alert({msg:"启用成功",title:"提示",icon:6}) ;
				
			}else{
				$.layerAlert.alert({msg:"启用失败",title:"提示",icon:6}) ;
				
			}
		}
	});
	resetbtn();
});


var tb = $.uiSettings.getOTableSettings() ;
tb.ajax.url = context+"/userManage/queryAccountByConditions.action"; 
tb.columnDefs = [
	{
		"targets" : 0,
		"width" : "50px",
		"title" : '<input type="checkbox" name="check2" class="icheckbox" id="allcheck"/>',
		"className" : "table-checkbox",
		"data" : "",
		"render" : function(
				data, type,
				full, meta) {
			var a = '<input type="checkbox" name="check" class="icheckbox icheckbox2"/>';
			return a;
		}
	},
	{
		"targets" : 1,
		"width" : "",
		"title" : "用户名",
		"data" : "accountName",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	},
	{
		"targets" : 2,
		"width" : "",
		"title" : "姓名",
		"data" : "personBean.name",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	},
	{
		"targets" : 3,
		"width" : "",
		"title" : "用户所属单位",
		"data" : "unitName",
		"render" : function(
				data, type,
				full, meta) {
			if(data==null){
				return "";
			}
			return data;
		}
	},
	
	{
		"targets" :4,
		"width" : "",
		"title" : "生效时间",
		"data" : "strStartDate",
		"className" : "table-edit",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	},
	{
		"targets" :5,
		"width" : "",
		"title" : "失效时间",
		"className" : "",
		"data" : "strEndDate",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	},
	{
		"targets" : 6,
		"width" : "",
		"title" : "状态",
		"className" : "",
		"data" : "status",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	}
	,
	{
		"targets" : 7,
		"width" : "",
		"title" : "备注",
		"className" : "",
		"data" : "intro",
		"render" : function(
				data, type,
				full, meta) {
			return data;
		}
	},
	{
		"targets" : 8,
		"width" : "",
		"title" : "操作",
		"className" : "",
		"data" : "",
		"render" : function(
				data, type,
				full, meta) {
			return "<button id='chakan' class='btn btn-link btn-sm  '>查看用户</button>";
		}
	}         
] ;
 tb.paging = true ; 
 tb.lengthMenu = [ 10 ];
 tb.lengthChange = false;
 tb.searching = false ;
 tb.ordering = false ;
 tb.paramsReq = function(d, pagerReq){
		var testReq = new Object();
		testReq.accountName = $("#sname").val() ;
		testReq.status=$("#state").val();
		var fmt = $.laydate.getFmt("#dateRangeId") ;
		testReq.startDate = $.date.strFmt($.laydate.getDate("#dateRangeId", "start"), fmt) ;
		testReq.endDate = $.date.endRange($.laydate.getDate("#dateRangeId", "end"), fmt) ;
		testReq.unitId=$("#scell").val();
		var start = $("#start").val() ;
		var end = $("#end").val() ;
		if(!$.util.isBlank(start) || !$.util.isBlank(end)){
			if($.util.isBlank(start) || $.util.isBlank(end)){
				$.layerAlert.alert({msg:"日期范围请选择全！"}) ;
		//有类名'disableControl'的按钮在点击时会被禁用（防止重复点击）
		//if为false时不用手动解锁是因为draw处理了
				$('#delete').removeAttr("disabled") ;
				return false ;
			}
		}
		$.util.objToStrutsFormData(testReq, "accountBean", d);
 } ;
	
 tb.paramsResp=function(json){
 json.data=json.accountBeanPager.pageList;
 json.recordsFiltered=json.accountBeanPager.totalNum;
 json.recordsTotal=json.accountBeanPager.totalNum;
 };
 tb.rowCallback = function( row, data, index ) {
	 $($(row).children("td")).each(function(i, val) {

			if(i!=0){
				$(val).on("dblclick",function() {
					var r = $(this).parents("tr") ;
					var row = table.row(r) ;
					var full = row.data() ;
					jQuery.extend($.common, { 
						getdata:function(){
							return full ;
						}
					});
					$.layerAlert.dialog({
						content : context+'/userManage/detail.action',
						pageLoading : true,
						width : "46%",
						height : "75%",
						title:"查看用户信息",
						shadeClose : false,
						success:function(layero, index){

			    		},
			    		callBacks : {}
					});
				})
			}
		});
 };
 table = $("#datatable").DataTable(tb);
 
 
$(document).on('click','#search',function(){
	table.draw(true,"#search");
	resetbtn();
});

$(document).on("ifChanged", ".icheckbox2", function () {
	var arr=$.icheck.getChecked("check") ;
	if(arr.length==1){
		var r = $(arr).parents("tr") ;
		var row = table.row(r) ;
		var full = row.data() ;
		var zhuangtai=full.status;
	}
	if(arr.length!=1){
		$("#start2").hide();
		$("#stop").show();
		$("#stop").attr("disabled","true");
	}else if(zhuangtai=="停用"){
		$("#start2").show();
		$("#stop").hide();
	}else if(zhuangtai=="启用"){
		$("#stop").removeAttr("disabled");
		$("#start2").hide();
		$("#stop").show();
	}else{
		$("#stop").removeAttr("disabled");
	}
	if(arr.length!=1){
		$("#edit").attr("disabled","true");
	}else{
		$("#edit").removeAttr("disabled");
	}
	if(arr.length==0){
		$("#delete").attr("disabled","true");
		$("#reset").attr("disabled","true");
	}else{
		$("#delete").removeAttr("disabled");
		$("#reset").removeAttr("disabled");

	}

});
$(document).on("ifChanged", "#allcheck", function () {
	var arr = $.icheck.getChecked("check");
	var count=arr.length;
	var all=0;
	$(".icheckbox2").each(function(){all+=1})
	if($.icheck.getChecked("check2").length==0){
		$.icheck.selectCheck("check") ;
		$.icheck.reverseCheck("check");
	}else if((count==all)&($("#allcheck").val()=="on")){
		return
	}else if (count<all) {
		$.icheck.selectCheck("check") ;
	}else{
		$.icheck.selectCheck("check") ;
		$.icheck.reverseCheck("check");
	}
});
$(document).on("click", "#chakan", function () {
	var val=$(this);
	var r = $(this).parents("tr") ;
	var row = table.row(r) ;
	var full = row.data() ;
	jQuery.extend($.common, { 
		getdata:function(){
			return full ;
		}
	});
	
	$.layerAlert.dialog({
		content : context+'/userManage/detail.action',
		pageLoading : true,
		width : "45%",
		height : "75%",
		title:"查看用户信息",
		shadeClose : false,
		success:function(layero, index){
		},
		callBacks : {}
	});
});

function intiUnitTree() {
	var settingIn = {
			view: {
				fontCss: getFontCss
			},
			async: {
				enable: true,
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
					//lookOver(treeNode.id, treeNode.diyMap.type);
					$("#scell").val(treeNode.id);
					table.draw(true) ;
				}
			}
		};
		
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
$(document).on("click", "#chongzhi", function () {
	$("#sname").val("");
	$.laydate.reset("#dateRangeId");
	$.select2.val("#state","",true);
	table.draw("false");
});
function resetbtn(){
	$("#delete").attr("disabled","true");
	$("#reset").attr("disabled","true");
	$("#edit").attr("disabled","true");
	$("#stop").attr("disabled","true");
	$("#start2").hide();
	$("#stop").show();
}
jQuery.extend($.common, { 
	closeDialog:function(index){
		layer.close(index);
	}
});
})(jQuery);