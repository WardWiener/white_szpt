(function($){
	"use strict";
	
	var table = null;
	
	$(document).ready(function() {	
		/**
		 * 查询按钮事件
		 */
		$(document).on("click",".search",function(){
			table.draw(true);
		});
		/**
		 * 重置按钮事件
		 */
		$(document).on("click","#reset",function(){
			resetSearchCondition();
			table.draw(true);
		});
		$(document).on("click",".sign",function(){
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var data = row.data();
			var id = data.id;
			$.ajax({
				url:context +'/instructionReceive/signInstruction.action',
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
		
		$(document).on("click",".goYrydDetails",function(){
			sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
			window.open(context + "/yryd/showYrydHighriskPersonAlertPage.action?clickOrder=1&&clickListOrder=0&&idcard="+$(this).attr("valCode"));
			return false;
		});
		
		$(document).on("click","td",function(){
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var data = row.data();
			var id = data.id;
			feedback(id,true);
		});
		initDictionaryItem();
		initInstructionTable();
	});
	
	function initInstructionTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionReceive/queryInstructionReceiveList.action";
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
						var str = '';
						if(full.status==$.common.Constant.ZLZT_DQS()){
							str += '<button class="btn btn-danger btn-sm sign">签收</button>';
						}else{
							str += '<button class="btn btn-success btn-sm feedback">反馈</button>';
						}
						
						if(full.isGwr){
							str += '&nbsp;<button valCode="' + full.instructionBean.relatedObjectId + '" class="btn btn-success btn-sm goYrydDetails">详情</button>';
						}
						return str;
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
				if($.common.isFullConditionSearch()){
					d["instructionBean.content"] = $("#content").val();
					d["instructionBean.type"] = $.select2.val("#type");
					d["instructionBean.createTimeStart"] = $.laydate.getTime("#createTime", "start");
					d["instructionBean.createTimeEnd"] = $.laydate.getTime("#createTime", "end");
					d["status"] = $.select2.val("#status");
				}else{
					var content = $("#content2").val() == "内容模糊查询" ? "" : $("#content2").val();
					d["instructionBean.content"] = content;
				}
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
	
	/**
	 * 初始化状态字典项字段
	 * @returns
	 */
	function initDictionaryItem(){
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			data:{dictionaryType : $.common.Constant.ZLLX()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#type", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			data:{dictionaryType : $.common.Constant.ZLZT()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#status", successData.dictionaryItemLst,"id","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
	}
	function feedback(id,isDetail){
		var btn;
		if(!isDetail){
			btn=["提交","取消"]
		}else{
			btn=["确定"]
		}
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/instructionReceive/showFeedbackInstructionPage.action',
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
	/**
	 * 重置查询条件
	 */
	function resetSearchCondition(){
		$.laydate.reset("#createTime");
		$.select2.val("#status","");
		$.select2.val("#type","");
		$("#content").val("");
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
//		findAllAutoFlow:findAllAutoFlow
	});	
})(jQuery);