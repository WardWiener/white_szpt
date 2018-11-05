(function($){
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var caseId = initData.caseId;
	var caseName = null;
	var personId = null;
	var table = null;
	var topAndPersonAndRoleTable = null;
	var tableInfoLst = null;
	var num = 1;
	$(document).ready(function(){
		init();
		$("#topBtn").hide();
		$("#untopBtn").hide();
	});
	//部门改变事件
	$(document).on("change", "#zaDepartmentSel", function(){
		showAllTopMessages();
		creatTable();
	})
	
	//筛选留言
	$(document).on("click", "#chooseLiuYanBtn", function(){
		//$("#topAndPersonAndRoleTable").hide();
		showAllTopMessages();
		creatTable();
	})
	
	//点击某角色
	$(document).on("click", ".roleA", function(){
		$("#tableIdDiv").hide();
		$("#topBtn").hide();
		$("#untopBtn").hide();
		var data = {
				"caseId" : caseId,
				"roleId":$(this).attr("varId")
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findLiuYanByRoleIdAndCaseId.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				caretTopAndPersonAndRoleTable(successDate.resultMap.result);
			}
		})
	})
	
	//点击某人
	$(document).on("click", ".personA", function(){
		$("#topBtn").hide();
		$("#untopBtn").hide();
		$("#tableIdDiv").hide();
		var data = {
				"caseId" : caseId,
				"personId":$(this).attr("varId")
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findLiuYanByPersonIdAndCaseId.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				caretTopAndPersonAndRoleTable(successDate.resultMap.result);
			}
		})
	})
	
	//置顶
	$(document).on("click", "#topBtn", function(){
		topOrUntop();
		$(this).hide();
	})
	
	//取消置顶
	$(document).on("click", "#untopBtn", function(){
		topOrUntop();
		$(this).hide();
	})
	
	//置顶或取消置顶方法
	function topOrUntop(){
		var liuYanIds = [];
		var arr = $.icheck.getChecked("idCheck");
		for (var i = 0; i < arr.length; i++) {
			liuYanIds.push($(arr[i]).attr("valId"));
		}
		if(liuYanIds.length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"至少选择一条留言",title:"提示"});
			return;
		}
		var data = {
				"liuYanIds" : liuYanIds,
			}
			var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/topOrUntopBtn.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				if(null != successDate.resultMap.result){
					$.util.topWindow().$.layerAlert.alert({msg:successDate.resultMap.result,title:"提示"});
					showAllTopMessages();
					creatTable();
				}else{
					$.util.topWindow().$.layerAlert.alert({msg:"置顶或取消失败",title:"提示"});
				}
			}	
		})
	}
	
	//我要留言
	$(document).on("click", "#addLiuYanBtn", function(){				
		$.util.topWindow().$.layerAlert.dialog({
			content : context
					+ '/zalyfk/showZaFeedbackUserLayerTextarea.action',
			pageLoading : true,
			title : "留言反馈",
			width : "800px",
			height : "630px",
			btn : [ "返回" ],
			callBacks : {
				btn1 : function(index, layero) {
					$.util.topWindow().$.layerAlert.closeWithLoading(index);
					location.reload();
				}
			},
			shadeClose : false,
			success : function(layero, index) {
			},
			initData : {
				"caseId" : caseId,
				"caseName" : caseName
			}
		})	
				
	})
	
	//删除留言
	$(document).on("click", "#deleteLiuYanBtn", function(){
		var liuYanIdAndPersonIdLst = [];
		var arr = $.icheck.getChecked("idCheck");
		for (var i = 0; i < arr.length; i++) {
			var liuYanIdAndPersonId = {
					"liuYanId" : $(arr[i]).attr("valId"),
					"personId" : $(arr[i]).attr("valPersonId")
			}
			liuYanIdAndPersonIdLst.push(liuYanIdAndPersonId);
		}
		if(liuYanIdAndPersonIdLst.length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"至少选择一条留言",title:"提示"});
			return;
		}
		var data = {
				"liuYanIdAndPersonIdLst" : liuYanIdAndPersonIdLst
			}
			var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/deleteLiuYan.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:successDate.resultMap.result,title:"提示"});
				showAllTopMessages();
				creatTable();
			}	
		})
	})
	
	//刷新
	$(document).on("click", "#refreshBtn", function(){
		location.reload();
	})
	
	//返回
	$(document).on("click", "#goBackBtn", function(){
		history.go(-1);
	})
	
	//tr的点击事件
	$(document).on("click", "tr", function(){
		$.each($("tr"), function(e,m){
			$(m).removeAttr("class");
		} ); 
		$(this).attr("class","info");
	})
	
	$(document).on("ifClicked", ".icheckbox", function(){
		var arr = $.icheck.getChecked("idCheck"); 
		if(!$(this).is(':checked')){
			arr.push(this);
		}else{
			var array = [];
			for(var i in arr){
			 	if(arr[i] != this){
			 		array.push(arr[i]);
			 	}
			}
			arr = array;
		}
		if(arr.length >= 1 ){
			var flag = true;
			for(var i in arr){
				if($(arr[i]).attr("valTop") != "0"){
					flag = false;
				};
			}
			if(flag){
				$("#topBtn").show();
			}else{
				$("#topBtn").hide();
			}
		}else{
			$("#topBtn").hide();
		}
		
		if(arr.length >= 1 ){
			var flag = true;
			for(var i in arr){
				if($(arr[i]).attr("valTop") != '1'){
					flag = false;
				};
			}
			if(flag){
				$("#untopBtn").show();
			}else{
				$("#untopBtn").hide();
			}
		}else{
			$("#untopBtn").hide();
		}
	})
	
	function init(){
		var data = {
				"caseId" : caseId,
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findDepartmentLstAndLiuYanPerson.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				initZaDepartment(successDate.resultMap.result.departmentLst);
				initZaPostAndPerson(successDate.resultMap.result.roleAndPersonsLst);
				caseName = successDate.resultMap.za.name;
				showTitleCaseName(caseName)
			}
		})
		showAllTopMessages();
	}
	function showTitleCaseName(caseName){
		$("#titleH2").empty();
		$("#titleH2").append(caseName)
	}
	
	function showAllTopMessages(){
		$("#tableIdDiv").hide();
		var data = {
				"caseId" : caseId,
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findAllTopMessages.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			async: false,
			success:function(successDate){
			//	caretTopAndPersonAndRoleTable(successDate.resultMap.result);
				tableInfoLst = successDate.resultMap.result;
				
			}
		})
	}
	
	
	function initZaDepartment(departmentLst){
		$("#zaDepartmentSel").empty();
		$.select2.addByList("#zaDepartmentSel", departmentLst,"id","name",true,true);
	}
	
	function initZaPostAndPerson(lyPostAndPerson){
		$("#postAndPersonDiv").empty();
		for(var key in lyPostAndPerson){
			var str = '<ul class="nav nav-pills name-group pull-left row-mar" style="margin-left: 40px;">'
				str +='<li><a href="#" varId='+lyPostAndPerson[key].role.id+' class="roleA" class="m-bold">'+lyPostAndPerson[key].role.name+'<span class="glyphicon glyphicon-arrow-right mar-left"></span></a></li>'
			var personLst = lyPostAndPerson[key].personLst;
			for(var i in personLst){
				str += '<li><a varId='+personLst[i].id+' class="personA" href="#">'+personLst[i].name+'</a></li>'
			}
			str += '</ul>'
			$("#postAndPersonDiv").append(str);
		}
	}
	
	//创建动态表格
	function creatTable(){
		$("#tableIdDiv").show();
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zalyfk/findLiuYanByCondition.action',
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "90px",
					"title" : "",
					"data" : "id",//置顶信息
					"render" : function(data, type, full, meta) {
						if(null == full.top || full.top == ""){
							return  '<input type="checkbox" valId="'+data+'" valTop="0" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"> &nbsp  ';
						}else if(full.top == "置顶"){
							return  '<input type="checkbox" valId="'+data+'" valTop="1" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+full.top+'</span>';
						}
						return  '<input type="checkbox" valId="'+data+'" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+full.top+'</span>';
					}
				},{
					"targets" : 1,
					"width" : "8%",
					"title" : "序号",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return  meta.row+1 +'&nbsp &nbsp' ;
					}
				},
				{
					"targets" : 2,
					"width" : "25%",
					"title" : '姓名',
					"data" : "personName",
					"render" : function(data, type, full, meta) {
						return '<div class="pull-left mar-right">'
						+'<a href="#" target="_blank"><img '
						+'src="../images/man/photo.png" width="50" height="50"></a></div>'
						+'<h2 class="row-mar font14">'+data+'</h2>'
						+'<p class="font11 color-gray">'+full.department+'</p>';
					}
				},
				{
					"targets" : 3,
					"title" : "留言内容",
					"data" : "context",
					"render" : function(data, type, full, meta) {
						return '<p>'+full.creatTime+'</p>'+data;
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
		tb.lengthMenu = [ 10 ];
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"caseId" : caseId,
				"department" : num == 1 ? null : $("#zaDepartmentSel").val(),
				"createTimeStart" : $.laydate.getTime("#dateRangeId", "start"),
				"createTimeEnd" : $.laydate.getTime("#dateRangeId", "end"),
				"start":d.start,
				"pageSize":d.length
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
			num++;
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.result.totalNum;
			json.recordsFiltered = json.resultMap.result.totalNum;
			json.data = json.resultMap.result.pageList;
		};
		tb.initComplete = function(){
			$("tbody").prepend(caretTopStr(tableInfoLst));
		}
		table = $("#tableId").DataTable(tb);
	}
	
	function caretTopStr(tableInfoLst){
		var str = "";
		if(null != tableInfoLst ){
			var j = 1;
			for(var i in tableInfoLst){
				str += "<tr><td>";
				if(null == tableInfoLst[i].top || tableInfoLst[i].top == ""){
					str += '<input type="checkbox" valId="'+tableInfoLst[i].id+'" valTop="0" valPersonId="'+tableInfoLst[i].personId+'" name="idCheck" class="icheckbox"> &nbsp  ';
				}else if(tableInfoLst[i].top == "置顶"){
					str += '<input type="checkbox" valId="'+tableInfoLst[i].id+'" valTop="1" valPersonId="'+tableInfoLst[i].personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+tableInfoLst[i].top+'</span>';
				}else{
					str += '<input type="checkbox" valId="'+tableInfoLst[i].id+'" valPersonId="'+tableInfoLst[i].personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+tableInfoLst[i].top+'</span>';
				}
				str += "</td><td>"+j+"</td><td>";
				str += '<div class="pull-left mar-right">'
					+'<a href="#" target="_blank"><img '
					+'src="../images/man/photo.png" width="50" height="50"></a></div>'
					+'<h2 class="row-mar font14">'+tableInfoLst[i].personName+'</h2>'
					+'<p class="font11 color-gray">'+tableInfoLst[i].department+'</p>';
				str += "</td><td>";
				str += '<p>'+tableInfoLst[i].creatTime+'</p>'+tableInfoLst[i].context;
				str += "</td></tr>";
				j++;
			}			
		}
		return str;
	}
	
	//创建静态表格
	function caretTopAndPersonAndRoleTable(tableInfoLst){
		$("#topAndPersonAndRoleTable").show();
		if(topAndPersonAndRoleTable != null) {
			topAndPersonAndRoleTable.destroy();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = tableInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "90px",
					"title" : "",
					"data" : "id",//置顶信息
					"render" : function(data, type, full, meta) {
						if(null == full.top || full.top == ""){
							return  '<input type="checkbox" valId="'+data+'" valTop="0" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"> &nbsp  ';
						}else if(full.top == "置顶"){
							return  '<input type="checkbox" valId="'+data+'" valTop="1" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+full.top+'</span>';
						}
						return  '<input type="checkbox" valId="'+data+'" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox"><span class="state state-blue2 font11 mar-left">'+full.top+'</span>';
					}
				},{
					"targets" : 1,
					"width" : "8%",
					"title" : "序号",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return  meta.row+1 +'&nbsp';
					}
				},
				{
					"targets" : 2,
					"width" : "25%",
					"title" : '姓名',
					"data" : "personName",
					"render" : function(data, type, full, meta) {
						return '<div class="pull-left mar-right">'
						+'<a href="#" target="_blank"><img '
						+'src="../images/man/photo.png" width="50" height="50"></a></div>'
						+'<h2 class="row-mar font14">'+data+'</h2>'
						+'<p class="font11 color-gray">'+full.department+'</p>';
					}
				},
				{
					"targets" : 3,
					"title" : "留言内容",
					"data" : "context",
					"render" : function(data, type, full, meta) {
						return '<p>'+full.creatTime+'</p>'+data;
					}
				}
		];
		tb.ordering = false;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
//		tb.paging = true;
//		tb.info = true;
//		tb.lengthMenu = [ 5 ];
		topAndPersonAndRoleTable = $("#topAndPersonAndRoleTable").DataTable(tb);
	}
	
	
})(jQuery);
