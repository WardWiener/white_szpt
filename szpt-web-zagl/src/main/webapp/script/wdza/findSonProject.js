$.findMySonProject = $.findMySonProject || {};
(function($){
	"use strict";
	var table=null;
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index	
	var initData = frameData.initData ;
	var caseId = initData.caseId;
    var id; //子案件id
    var code; //子案件code
	$(document).ready(function(){
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		onload();
	}) 
	/**
	 * 保存选中的子案件
	 */
	$(document).on("click", "#saveSonProjectBtn", function(){
	   saveSonProject();
	})
		  
	/**
	 * 返回按钮
	 */
	$(document).on("click", "#returnBtn", function(){
		$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); 
	})		
			
	/**
	 * 重置按钮
	 */
	$(document).on("click", "#resBtn", function(){
		resetBetween();
	})	
	/**
	 * 查询按钮
	 */
	$(document).on("click", "#findBtn", function(){
		findSonCase();
	})	
	/**
	 * 保存按钮--点击事件
	 */
	$(document).on("click", ".layui-layer-btn0", function(){
		selectSonProject();
	})	
	
	/**
	 * 保存选中子案件
	 */
	function saveSonProject(){
		code=$('input[type="checkbox"]:checked').attr("code"); //可选多个子案件
			var data={
					sonCode:code,
					caseId:caseId
				}
			var queryStr = $.util.toJSONString(data);
			$.ajax({
				url:context +'/wdza/saveSonProject.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var a=successData.resultMap.result;
					if(a==true){
						$.layerAlert.alert({title:"提示",msg:"修改成功"});
						creatTable();
						creatTable2();						
					}else{
						$.layerAlert.alert({title:"提示",msg:"修改失败"});
					}
				},
				error:function(data){
					$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
				}
			});	
		
		}
	
	

	
	function  getId(){
		return id;
	}
	function getCode(){
		return code;
	}
	
	/**
	 * 查询子案件
	 */
	function findSonCase(){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zawh/findSonCase.action';
		tb.columnDefs = [
				{
					"targets" : 0,
					"title" : "",
					"data" : "caseCode",
					"render" : function(data, type, full, meta) {
						return '<input type="checkbox" code='+full.caseCode+' name="askRoom" class="icheckbox"/>';
					}
				},
				{
					"targets" : 1,
					"title" : '案件编号',
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				},
				{
					"targets" : 2,
					"title" : "案件名称",
					"data" : "caseName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"title" : "办案单位",
					"data" : "dqbldw",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"title" : "办案民警",
					"data" : "handlingPeople",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"title" : "发案时间",
					"data" : "caseTimeStart",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					"targets" : 6,
					"title" : "案由类别",
					"data" : "caseSort",
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
		var tobj = $.zTreeMenu.getTree("dwTree");
		var  unit;
		var a=tobj.tree.getSelectedNodes()[0];
		if(!a){
			unit="";
		}else{
			unit=tobj.tree.getSelectedNodes()[0].id;
		}
		var ajlb;
		if($("#aylb").val()=='全部'){
			ajlb='';
		}else{
			ajlb=$("#aylb").val();
		}
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"caseTimeStart" :$('#start').val(),//开始时间
				"caseTimeEnd" :$('#end').val(),  //结束时间
				"caseCode" :$('#ajbh').val(),  //案件编号
				"caseName" :$('#ajmc').val(),  //案件名称
				"caseSort":ajlb,   //案件类别
				"handingUnit":unit,   //办案单位
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
		table = $("#sonProjectTable").DataTable(tb);
	
	}

	/**
	 * 重置按钮
	 */
	function resetBetween(){
		$('#ajbh').val("");  //案件编号
		$('#ajmc').val("");  //案件名称
		$('#dwTree').val("");  //办案单位
		$("#aylb").val("全部");  //案件类型
		$('#bamj').val("");  //办案民警
		$('#start').val("");  //开始时间
		$('#end').val("");  //结束时间
	}
	
	/**
	 * 初始化加载案件类型
	 */
	function onload(){
		$.ajax({
			url:context+'/zawh/findAJLX.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				var lx=successData.resultMap.result;
				$('#aylb').empty();
				var b='<option>全部</option>';
				for(var i=0;i<lx.length;i++){
					b+='<option value="'+lx[i].code+'" >'+lx[i].name+'</option>';
				}
				$('#aylb').append(b);
			}		
		
		});
	
	}
	

	
	
	/**
	 * 初始化
	 */
	function init(){	
		$.szpt.util.businessData.iniOnetDwTree("dwTree");
	}
	


	
	
	

	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.findMySonProject, { 
		getId : getId,
		getCode:getCode
	});	
	
})(jQuery);