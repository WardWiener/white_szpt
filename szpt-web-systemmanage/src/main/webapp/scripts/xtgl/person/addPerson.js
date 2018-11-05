(function($){
	"use strict";
	//拿到初始化的值
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var table = initData.table;//dataTable对象
	var personAdminBean = initData.personAdminBean;//行数据对象
	var AjaxPath = "save";//请求路径
	var unitBean = initData.unitBean;//人员单位名称
	var mobility = initData.mobility;//是否点击的跨单位调动
	
	$(document).ready(function() {
		$("#pa_newPage_close").on("click",function(){//关闭
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
		});
		$("#pa_newPage_cancel").on("click",function(){//取消
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
		});
		$("#pa_newPage_save").on("click",function(){//保存
			if(!mobility){
				saveNewPersonPageDate();//普通新增/修改保存方法
			}else{
				updateNewPersonUnit();//跨单位调动
			}
		});
		$("#pa_newPage_unitFind").on("click",function(){//选择单位
			showMenu();
		});
		$("#pa_newPage_policeNo").on("keyup change",function(){//警号验证
			if($("#pa_newPage_policeNo").val(),length == 7){
				verifyPoliceNo(true);//验证类别名称是否重复
			}
		});
		$("#pa_newPage_statusCardNo").on("keyup change",function(){//身份证号验证
			if($("#pa_newPage_statusCardNo").val(),length == 18){
				verifyStatusCardNo(true);//验证类别名称是否重复
			}
		});
		$("#pa_newPage_configUser").on("click",function(){//配置用户
			
			var userData = $("#pa_newPage_configUser").data("userData");
			var checkAcount= hasConfiguerUser(userData.personId);
			if(checkAcount==false){
				alertHint("人员已配置",5);
				return;
			}
			var win = $.util.rootWindow();
			$.util.topWindow().$.layerAlert.dialog({
				content : context+'/userManage/addUser.action',
				pageLoading : true,
				width : "800px",
				height : "100%",
				title:"新增用户信息",
				btn:["保存", "取消"],
				shadeClose : false,
				initData:{
					personId:userData.personId,
					personName:userData.name,
					unitName:userData.unitFullName,
					unitId:userData.unitId,
					personPoliceNo:userData.policeNo
					
				},
				success:function(layero, index){

				},
				callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
					btn1:function(index, layero){
						var win = $.layerAlert.frameWindow(index) ;
	    				win.$.common.save(index) ;
	    				table.draw(true);
	    				
	    				
				    },
				    // cancel默认代表btn数组中第二个名称的按钮和取消按钮并且点击会默认关闭层
				    btn2:function(index, layero){
				    	$.util.topWindow().$.layerAlert.closeWithLoading(index);		//关闭弹窗
				    }
				 }
			});
			

		});
		loadingPageDictionary();
		initNewPersonPage(personAdminBean,mobility);
		intiSelectUnitTree();
	});
function hasConfiguerUser(personId){
	        var checkAccount;
             $.ajax({
            	 url:context +'/personManage/checkPersonHasAccount.action',
                 async:false,
         		 type:'post',
         		 dataType:'json',
         		 data:{
         			"personBean.personId":personId
         			},
         		 success:function(data){
         			 if(data.flag == "true"){
         				checkAccount=true;
         			 }else{
         				checkAccount=false;
         			 }
         		 }	
            	 
             })	
             return checkAccount;
}
/**
 * 初始化新建人员页面
 */	
function initNewPersonPage(object,mobility){
	if($.util.exist(object)){//修改
		if(mobility){
			$("#pa_newPage_title").text("跨单位调度");
		}else{
			$("#pa_newPage_title").text("修改人员");
		}
		
		personAdminBean = object;
		setNewPersonPageDate(object);
		AjaxPath = "update";//修改路径为修改路径
		if(!mobility){//不是调动就禁用
			$("#pa_newPage_unit").attr("readOnly","true");//禁用单位输入
			$("#pa_newPage_unitFind").unbind();
		}
	}else{//新增
		$("#pa_newPage_title").text("新增人员");
		$("#pa_newPage_unit").attr("readOnly","true");//禁用单位输入
		
		cleanNewPersonPageDate();//清空页面字段
		
		$("#pa_newPage_unit").val(unitBean.unitFullName);
		$("#pa_newPage_unitCode").val(unitBean.unitCode);
		$("#pa_newPage_unitId").val(unitBean.unitId);
		
		
		AjaxPath = "save";//修改路径为修改路径
	}
	if(mobility){//禁用除单位以外其他所有输入框
//		$("#pa_newPage_name").attr("readOnly","true");
		$("#pa_newPage_name").css("background-color","#eee !important");
		$.select2.able("#pa_newPage_sex",false);
		$("#pa_newPage_policeNo").attr("readOnly","true");
		$("#pa_newPage_statusCardNo").attr("readOnly","true");
		$.select2.able("#pa_newPage_job",false);
		$.select2.able("#pa_newPage_nationality",false);
		$.select2.able("#pa_newPage_politicsType",false);
		$.select2.able("#pa_newPage_diploma",false);
		$("#pa_newPage_movePhone").attr("readOnly","true");
		$("#pa_newPage_officePhone").attr("readOnly","true");
		$.select2.able("#pa_newPage_status",false);
		
	}
}

/**
 * 加载页面需要的字典项(select下拉列表)
 */
function loadingPageDictionary(){
	$.ajax({
		url:context +'/personManage/initNewPersonPage.action',
		async:false,
		type:'post',
		dataType:'json',
		data:{},
		success:function(successData){
			$.select2.addByList("#pa_newPage_sex", successData.newPersonPageBean.sexList,"code","name");
			$.select2.addByList("#pa_newPage_job", successData.newPersonPageBean.jobList,"code","name");
			$.select2.addByList("#pa_newPage_nationality", successData.newPersonPageBean.nationalityList,"code","name");
			$.select2.addByList("#pa_newPage_politicsType", successData.newPersonPageBean.politicsTypeList,"code","name");
			$.select2.addByList("#pa_newPage_diploma", successData.newPersonPageBean.diplomaList,"code","name");
			$.select2.addByList("#pa_newPage_status", successData.newPersonPageBean.statusList,"code","name");
		},
		error:function(errorData){
			
		}
	});
}
	
/**
 * 设置新建人员页面所有字段的值
 */
function setNewPersonPageDate(personAdminBean){
	$("#pa_newPage_personId").val(personAdminBean.personId);
	$("#pa_newPage_personCode").val(personAdminBean.personCode);
	$("#pa_newPage_unitCode").val(personAdminBean.unitCode);
	$("#pa_newPage_unit").val(personAdminBean.shortUnitName);
	$("#pa_newPage_unitId").val(unitBean.unitId);
	
	if(!mobility){
		//姓名
		$("#pa_newPage_name").val(personAdminBean.name);
		//性别
		$("#pa_newPage_sex option").each(function(i,val){
			if($(val).text() == personAdminBean.sex){
			$.select2.val("#pa_newPage_sex",$(val).val(),true);
			}
		});
		//警号
		$("#pa_newPage_policeNo").val(personAdminBean.policeNo);
		//身份证号
		$("#pa_newPage_statusCardNo").val(personAdminBean.statusCardNo);
		//民族
		$("#pa_newPage_nationality option").each(function(i,val){
			if($(val).text() == personAdminBean.nationality){
				$.select2.val("#pa_newPage_nationality",$(val).val(),true);
			}
		});
		//政治面貌 
		$("#pa_newPage_politicsType option").each(function(i,val){
			if($(val).text() == personAdminBean.politicsType){
				$.select2.val("#pa_newPage_politicsType",$(val).val(),true);
			}
		});
		//学历 
		$("#pa_newPage_diploma option").each(function(i,val){
			if($(val).text() == personAdminBean.diploma){
				$.select2.val("#pa_newPage_diploma",$(val).val(),true);
			}
		});
		//职务
		$("#pa_newPage_job option").each(function(i,val){
			if($(val).text() == personAdminBean.job){
				$.select2.val("#pa_newPage_job",$(val).val(),true);
			}
		});
		//移动电话
		$("#pa_newPage_movePhone").val(personAdminBean.movePhone);
		//办公电话
		$("#pa_newPage_officePhone").val(personAdminBean.officePhone);
		//状态
		$("#pa_newPage_status option").each(function(i,val){
			if($(val).text() == personAdminBean.status){
				$.select2.val("#pa_newPage_status",$(val).val(),true);
			}
		});
	}else {
		//姓名
		$("#pa_newPage_name").attr("placeholder",personAdminBean.name);//设置姓名
		$("#pa_newPage_name").attr("readOnly","true");
		$("#pa_newPage_name").removeAttr("datatype");
		//性别
		$("#sex").html('<input id="pa_newPage_name" type="text" class="form-control input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.sex + '" >')
		//警号
		$("#pa_newPage_policeNo").attr("placeholder",personAdminBean.policeNo);//设置姓名
		$("#pa_newPage_policeNo").attr("readOnly","true");
		$("#pa_newPage_policeNo").removeAttr("datatype");
		//身份证号
		$("#pa_newPage_statusCardNo").attr("placeholder",personAdminBean.statusCardNo);//设置姓名
		$("#pa_newPage_statusCardNo").attr("readOnly","true");
		$("#pa_newPage_statusCardNo").removeAttr("datatype");
		//民族
		$("#nationality").html('<input id="pa_newPage_name" type="text" class="form-control  input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.nationality + '" >')
		//政治面貌 
		$("#politicsType").html('<input id="pa_newPage_name" type="text" class="form-control  input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.politicsType + '" >')
		//学历 
		$("#diploma").html('<input id="pa_newPage_name" type="text" class="form-control  input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.diploma + '" >')
		//职务
		$("#job").html('<input id="pa_newPage_name" type="text" class="form-control  input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.job + '" >')
		//移动电话
		$("#pa_newPage_movePhone").attr("placeholder",personAdminBean.movePhone);//设置姓名
		$("#pa_newPage_movePhone").attr("readOnly","true");
		//办公电话
		$("#pa_newPage_officePhone").attr("placeholder",personAdminBean.officePhone);//设置姓名
		$("#pa_newPage_officePhone").attr("readOnly","true");
		//状态
		$("#status").html('<input id="pa_newPage_name" type="text" class="form-control  input-sm" style="background-color:#eee"  readOnly="true" placeholder="' + personAdminBean.status + '" >')
	}
//	$("#pa_newPage_unit").val(personAdminBean.unitFullName);
//	$("#pa_newPage_unitCode").val(personAdminBean.unitCode);
}

/**
 * 获取新建人员页面所有字段的值
 */
function getNewPersonPageDate(){
	var personAdminBean;
	if(!mobility){
		personAdminBean = {
				"personId":$("#pa_newPage_personId").val(),
				"personCode":$("#pa_newPage_personCode").val(),
				"name":$("#pa_newPage_name").val(),
				"sex":$.select2.val("#pa_newPage_sex"),
				"unitId":$("#pa_newPage_unitId").val(),
				"unitCode":$("#pa_newPage_unitCode").val(),
				"unitFullName":$("#pa_newPage_unit").val(),
				"policeNo":$("#pa_newPage_policeNo").val(),
				"statusCardNo":$("#pa_newPage_statusCardNo").val(),
				"job":$.select2.val("#pa_newPage_job"),
				"nationality":$.select2.val("#pa_newPage_nationality"),
				"politicsType":$.select2.val("#pa_newPage_politicsType"),
				"diploma":$.select2.val("#pa_newPage_diploma"),
				"movePhone":$("#pa_newPage_movePhone").val(),
				"officePhone":$("#pa_newPage_officePhone").val(),
				"status":$.select2.val("#pa_newPage_status")
			}
	}else{
		personAdminBean = {
				"personId":$("#pa_newPage_personId").val(),
				"personCode":$("#pa_newPage_personCode").val(),
				"unitId":$("#pa_newPage_unitId").val(),
				"unitCode":$("#pa_newPage_unitCode").val(),	
				"status":$.select2.val("#pa_newPage_status")

		}
	}
	
	
	return personAdminBean;
	
}

/**
 * 禁用所有字段
 */
function disableAllField(){
	$("#pa_newPage_unit").attr("readOnly","true");//禁用单位输入
	$("#pa_newPage_name").attr("readOnly","true");
	$("#pa_newPage_unitFind").unbind();
	$.select2.able("#pa_newPage_sex",false);
	$("#pa_newPage_policeNo").attr("readOnly","true");
	$("#pa_newPage_statusCardNo").attr("readOnly","true");
	$.select2.able("#pa_newPage_job",false);
	$.select2.able("#pa_newPage_nationality",false);
	$.select2.able("#pa_newPage_politicsType",false);
	$.select2.able("#pa_newPage_diploma",false);
	$("#pa_newPage_movePhone").attr("readOnly","true");
	$("#pa_newPage_officePhone").attr("readOnly","true");
	$.select2.able("#pa_newPage_status",false);
}

/**
 * 启用所有字段
 */
function enableAllField(){
	$("#pa_newPage_unit").removeAttr("readOnly");//禁用单位输入
	$("#pa_newPage_name").removeAttr("readOnly");
	$("#pa_newPage_unitFind").on("click",function(){//选择单位
		findUnit();
	});
	$.select2.able("#pa_newPage_sex",true);
	$("#pa_newPage_policeNo").removeAttr("readOnly");
	$("#pa_newPage_statusCardNo").removeAttr("readOnly");
	$.select2.able("#pa_newPage_job",true);
	$.select2.able("#pa_newPage_nationality",true);
	$.select2.able("#pa_newPage_politicsType",true);
	$.select2.able("#pa_newPage_diploma",true);
	$("#pa_newPage_movePhone").removeAttr("readOnly");
	$("#pa_newPage_officePhone").removeAttr("readOnly");
	$.select2.able("#pa_newPage_status",true);
}
	
/**
 * 清空新建人员页面所有字段的值
 */
function cleanNewPersonPageDate(){
	$("#pa_newPage_personId").val("");
	$("#pa_newPage_personCode").val("");
	$("#pa_newPage_name").val("");
	$.select2.selectByOrder("#pa_newPage_sex",1,true);
	$("#pa_newPage_unitId").val("");
	$("#pa_newPage_unitCode").val("");
	$("#pa_newPage_unit").val("");
	$("#pa_newPage_policeNo").val("");
	$("#pa_newPage_statusCardNo").val("");
	$.select2.selectByOrder("#pa_newPage_job",1,true);
	$.select2.selectByOrder("#pa_newPage_nationality",1,true);
	$.select2.selectByOrder("#pa_newPage_politicsType",1,true);
	$.select2.selectByOrder("#pa_newPage_diploma",1,true);
	$("#pa_newPage_movePhone").val("");
	$("#pa_newPage_officePhone").val("");
	$.select2.selectByOrder("#pa_newPage_status",1,true);
}
/**
 * 保存人员页面所有字段的值
 */
function saveNewPersonPageDate(){
	if($("#pa_newPage_name").attr("readOnly") == undefined){//保存
		if($("#pa_newPage_unit").val()==null||$("#pa_newPage_unit").val()==""){ 
			alertHint("请选择人员所属单位",5);
			return;
		}
		//保存之前验证表单数据是否正确
		var demo = $.validform.getValidFormObjById("pa_newPage") ;//取到最外层div的根据id
		var flag = $.validform.check(demo) ;//是否有没通过的验证
		if(flag){
			var gObject = new Object();
			$.util.objToStrutsFormData(getNewPersonPageDate(), "personBean", gObject);
			$.ajax({
				url:context +'/personManage/'+AjaxPath+'Person.action',
				async:true,
				type:'post',
				dataType:'json',
				btn: '#pa_newPage_save',
				data:gObject,
				success:function(successData){
					if(successData.flag == "true"){
						$("#pa_newPage_save").text("修改");
						$("#pa_newPage_cancel").attr("disabled","true");//取消按钮不可点击
						disableAllField();//禁用新建页面所有字段
						$("#pa_newPage_personId").val(successData.personBean.personId);
						$("#pa_newPage_personCode").val(successData.personBean.personCode);
						table.draw(false);//重绘表格
				 	    $("#pa_newPage_configUser").css("display","inline-block");
					    $("#pa_newPage_configUser").data("userData",getNewPersonPageDate());
						personAdminBean = successData.personBean;
						if(AjaxPath == "save"){
							alertHint("保存成功",6);
						}else{
							alertHint("修改成功",6);
						}
						AjaxPath = "update";//修改路径为修改路径
					}else{
					    $.layerAlert.alert({msg:successData.msg,title:"提示",icon:5});
					}
					
					
				},
				error:function(errorData){
					alertHint(AjaxPath == "save"?"保存失败":"修改失败",5);
				}
			});
		}
		
	}else{//修改
		$("#pa_newPage_save").text("保存");
		$("#pa_newPage_cancel").removeAttr("disabled");//取消按钮可点击
		enableAllField();//启用新建页面所有字段
		$("#pa_newPage_unitFind").unbind();
		$("#pa_newPage_unit").attr("readOnly","true");//禁用单位输入
		AjaxPath = "update";//修改路径为保存路径
	}
}


/**
 * 修改人员单位信息(跨单位调动)
 */
function updateNewPersonUnit(){
	
	if($("#pa_newPage_unitFind").attr("isUpdate") == undefined){//保存
		//保存之前验证表单数据是否正确
		var demo = $.validform.getValidFormObjById("pa_newPage") ;//取到最外层div的根据id
		var flag = $.validform.check(demo) ;//是否有没通过的验证
		if(flag){
			var gObject = new Object();
			$.util.objToStrutsFormData(getNewPersonPageDate(), "personBean", gObject);
			$.util.objToStrutsFormData(mobility, "mobility", gObject);
			
			$.ajax({
				url:context +'/personManage/updatePerson.action',
				async:true,
				type:'post',
				dataType:'json',
				btn: '#pa_newPage_save',
				data:gObject,
				success:function(successData){
					if(successData.flag == "true"){
						$("#pa_newPage_save").text("修改");
						$("#pa_newPage_cancel").attr("disabled","true");//取消按钮不可点击
						$("#pa_newPage_unitFind").attr("isUpdate","true");
						$("#pa_newPage_unitFind").unbind();
						$("#pa_newPage_personId").val(successData.personBean.personId);
						table.draw(false);//重绘表格
						AjaxPath = "update";//修改路径为修改路径
						personAdminBean = successData.personBean;
						$.layerAlert.confirm({
				    		msg:successData.msg,
				    		title:"提示",		//弹出框标题
							shade: [0.5,'black'],  //遮罩
							icon:6,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
							shift:1,			//弹出时的动画效果  有0-6种
				    		yes:function(index, layero){	
				    			$.util.topWindow().$.layerAlert.closeAll();//点击确定按钮
				    		}
				    	});
					}else{
						alertHint(successData.msg,5);
					}
					
				},
				error:function(errorData){
					alertHint(AjaxPath == "save"?"保存失败":"修改失败",5);
				}
			});
		}
	}else{
		$("#pa_newPage_save").text("保存");
		$("#pa_newPage_cancel").removeAttr("disabled");//取消按钮可点击
		$("#pa_newPage_unitFind").on("click",function(){//选择单位
			findUnit();
		});
		$("#pa_newPage_unitFind").removeAttr("isUpdate");//删除属性
		AjaxPath = "update";//修改路径为保存路径
	}
	
}



/**
 * 查询单位
 */
function findUnit(){
	
	var win = $.util.rootWindow();
	$.util.topWindow().$.layerAlert.dialog({
		content:context+'/personManage/showPaFindUnitPage.action',
		maxmin:false,//是否可以最大化
		closeBtn:false,//是否显示关闭按钮
		pageLoading:true,//是否在等待过程中显示转圈
		skin:'layui-laye',//是否显示边框，默认带边框
		title:'',
		width:"602px",
		height:"443px",
		shadeClose:false,//点击最外层是否关闭弹窗
		btn:null,
		success:function(layero, index){
//			//打开弹出框后回调方法
//			var win2 = win.$.layerAlert.frameWindow(index) ;pageIndex
//			win2.$.common.setTestObj2(index,"2") ;
		},
		end:function() {
			var win = $.util.rootWindow();
			$("#pa_newPage_unit").val(win.$.rowData.unitName);
			$("#pa_newPage_unitCode").val(win.$.rowData.unitCode);
			$("#pa_newPage_unitId").val(win.$.rowData.id);
			
		}
	});
}



/**
 * 验证身份证号是否唯一
 * @param async 是否异步执行 true/false
 */
function verifyStatusCardNo(async){
	
	var flag = false;
	$.ajax({
		url:context +'/personManage/verifyStatusCardNo.action',
		async:async,
		type:'post',
		global:false,//是否显示自定义效果(Ajax自带参数)
		dataType:'json',
		data:{
			"statusCardNo":$("#pa_newPage_statusCardNo").val()
			},
		success:function(successData){
			flag = false;
			if(successData.statusCardNoFlag == "1"){//身份证号重复
				$.layerAlert.tips({
					msg:'身份证号已存在',
					selector:'#pa_newPage_statusCardNo',
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:1000,
					shift:1
				})
			}else{//身份证号不重复
				flag = true;
			}
		},
		error:function(errorData){
			flag = false;
		}
	});
	return flag;
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
				$("#pa_newPage_unit").val(treeNode.name);
				$("#pa_newPage_unitCode").val(treeNode.id);
				$("#pa_newPage_unitId").val(treeNode.id);
				$.validform.closeAllTips("validformId");
				hideMenu();
			}
		}
	};

function showMenu() {
	var obj = $("#pa_newPage_unit");
	var oOffset = $("#pa_newPage_unit").offset();
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
function reBuildSelectUnitTree(type) {
	
	settingSelect.async.url = context+"/unit/initUnitTree.action";
var treeSelect = $.fn.zTree.getZTreeObj("ztree-unitSelect");
treeSelect.destroy();
treeSelect = $.fn.zTree.init($("#ztree-unitSelect"), settingSelect);
treeSelect.lastSearchValue = "" ;
treeSelect.nodeSearchList = [] ;
treeSelect.fontSearchCss = {} ;
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
	//将符合条件的  节点  更新
	updateNodes(true, treeId);
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
/**节点样式*/
function getFontCss(treeId, treeNode){
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

})(jQuery);	