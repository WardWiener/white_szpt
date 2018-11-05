$.findSonProject = $.findSonProject || {};
(function($){
	"use strict";
	var table=null;
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index	
    var id; //子案件id
    var code; //子案件code
	$(document).ready(function(){
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		onload();
		findSonCaseTable();
	}) 
	/**
	 * 确认选中的子案件
	 */
//	$(document).on("click", "#saveSonProjectBtn", function(){
//	   selectSonProject();
//	})
//		  
	/**
	 * 返回按钮
	 */
//	$(document).on("click", "#returnBtn", function(){
//		$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); 
//	})		
			
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
		table.draw(true);
	})	
	/**
	 * 保存按钮--点击事件
	 */
	$(document).on("click", ".layui-layer-btn0", function(){
		selectSonProject();
	})	
	
	/**
	 * 确认选中子案件
	 */
	function selectSonProject(){
	 //	id=$('input[type="checkbox"]:checked').attr("bb")
		code=$('input[type="checkbox"]:checked').attr("code")
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
	function findSonCaseTable(){
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
						return '<input type="checkbox" handlingPeople='+full.handlingPeople+' caseName='+full.caseName+' caseCode='+data+' name="askRoom" class="icheckbox"/>';
					}
				},
				{
					"targets" : 1,
					"title" : '案件编号',
					"data" : "caseCode",
					"render" : function(data, type, full, meta) {
						return data;
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
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 5 ];
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
		//是否分页
		tb.paging=true;
		tb.paramsReq = function(d, pagerReq){
			var tobj = $.zTreeMenu.getTree("dwTree");
			var  unit;
			if(typeof(tobj)=="undefined"){
				unit="";
			}else{
				var bss=tobj.tree.getSelectedNodes()[0];
				if(typeof(bss)=="undefined"){
					unit="";
				}else{
					unit=tobj.tree.getSelectedNodes()[0].id;
				}
			}
			var ajlb;
			if($("#aylb").val()=='全部'){
				ajlb='';
			}else{
				ajlb=$("#aylb").val();
			}
			var data = {
				"discoverTimeStart" :$('#start').val(),//开始时间
				"discoverTimeEnd" :$('#end').val(),  //结束时间
				"caseCode" :$('#ajbh').val(),  //案件编号
				"caseName" :$('#ajmc').val(),  //案件名称
				"caseSort":ajlb,   //案件类别
				"handlingUnit":unit,   //办案单位
				"polices":$('#bamj').val(),//办案民警
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
	 * 获取对象
	 */
	function getData(){
		var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==0){
			$.layerAlert.alert({title:"提示",msg:"至少勾选一项"});
		}else{
			var arrCaseCode=new Array();
			var arrCaseName=new Array();
			var arrHandlingPeople=new Array();
			$('input[type="checkbox"]:checked').each(function(){
				arrCaseName.push($(this).attr("caseName"));
				arrCaseCode.push($(this).attr("caseCode"));
				arrHandlingPeople.push($(this).attr("handlingPeople"));
			})
			var data={
				arrCaseName:arrCaseName,  //案件名称
				arrCaseCode:arrCaseCode,   //案件编号
				arrHandlingPeople:arrHandlingPeople  //办案民警
			}
			table.draw(true);
			return data;		        
		}
	
	}
    
	
	//暴露接口让其他js可以调用
	jQuery.extend($.findSonProject , { 
		getData:getData
		
	});	
	
})(jQuery);