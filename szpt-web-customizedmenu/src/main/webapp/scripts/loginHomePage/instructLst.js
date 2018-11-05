(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var currentUserName = initData.currentUserName;

	var table;
	$(document).ready(function(){
		initInstructionTable();
	});
	
	function initInstructionTable(){

		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/instruction/queryInstructionReceiveList.action";
		tb.columnDefs = [
			{
				"targets": 0,
     	    	"width": "5%",
     	    	"title": "选择",
     	    	"className":"table-checkbox",
     	    	"data": "id" ,
     	    	"render": function ( data, type, full, meta ) {
 	    			var a = '<input type="checkbox" name="check" class="icheckbox" value="'+data+'" />' ;
 	    			if(full.isOverTime==1&&full.feedbackBeanList.length==0){
      	    			a+='<span class="glyphicon glyphicon-exclamation-sign font16 color-red2 pull-right"></span>';
      	    		}
 	    			return a;
     	    	}
			},
			{
				"targets" : 1,
				"width" : "10%",
				"title" : "发送单位",
				"data" : "instructionBean.createPeopleDepartmentName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "10%",
				"title" : "发送时间",
				"data" : "receiveTime",
				"render" : function(data, type, full, meta) {
					return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				}
			},
			{
				"targets" : 3,
				"width" : "15%",
				"title" : "指令内容",
				"data" : "instructionBean.content",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "10%",
				"title" : "关联内容",
				"data" : "instructionBean.relateObjectContent",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 5,
				"width" : "10%",
				"title" : "指令类型",
				"data" : "instructionBean.typeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 6,
				"width" : "10%",
				"title" : "指令状态",
				"data" : "statusName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 7,
				"width" : "10%",
				"title" : "签收时间",
				"data" : "signTime",
				"render" : function(data, type, full, meta) {
					return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				}
			},
			{
				"targets" : 8,
				"width" : "10%",
				"title" : "反馈时间",
				"data" : "feedbackTime",
				"render" : function(data, type, full, meta) {
					return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				}
			},
			{
				"targets" : 9,
				"width" : "10%",
				"title" : "操作",
				"data" : "",
				"render" : function(data, type, full, meta) {
					if(full.status==$.common.Constant.ZLZT_DQS()){
						return '<button class="btn btn-danger btn-sm sign">签收</button>';
					}else{
						return '<button class="btn btn-success btn-sm feedback">反馈</button>';
					}
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 10 ];
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
		//请求参数
		tb.paramsReq = function(d, pagerReq){
			var now = new Date();
			var start = new Date();
			start.setDate(now.getDate() - 3);
			d["instructionBean.createTimeStart"] = start.getTime();
			d["instructionBean.createTimeEnd"] = now.getTime() ;
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.instructionReceiveSubjectBeanPager.totalNum;
			json.recordsFiltered = json.instructionReceiveSubjectBeanPager.totalNum;
			json.data = json.instructionReceiveSubjectBeanPager.pageList;
		
		};
		tb.rowCallback = function(row,data, index) {
			
		};
		table = $("#instructionTable").DataTable(tb);
	}
	
	$(document).on("click",".sign",function(){
		var tr = $(this).parents("tr");
		var row = table.row(tr);
		var data = row.data();
		var id = data.id;
		$.ajax({
			url:context +'/instruction/signInstruction.action',
			type:'post',
			dataType:'json',
			data:{id:id},
			success:function(successData){
				table.draw(false);
			},
			error:function(errorData){
				
			}
		});
		return false;
	});
	
	$(document).on("click",".feedback",function(){
		var tr = $(this).parents("tr");
		var row = table.row(tr);
		var data = row.data();
		var id = data.id;
		feedback(id,false);
		return false;
	});
	
	function feedback(id,isDetail){
		var btn;
		if(!isDetail){
			btn=["提交","取消"]
		}else{
			btn=["确定"]
		}
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/instruction/showFeedbackInstructionPage.action',
			pageLoading : true,
			title:"指令反馈",
			width : "650px",
			height : "700px",
			btn:btn,
			callBacks:{
				btn1:function(index, layero){
					if(isDetail){
						$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
					}else{
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.common ;
						cm.save();
					}
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			isDetail:isDetail,
    			id : id,
    			currentUserName:currentUserName
    		},
    		end:function(){
    			table.draw(false);
    		}
		});
	}
	
})(jQuery);
