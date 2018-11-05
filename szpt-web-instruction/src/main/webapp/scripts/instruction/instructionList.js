$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;
$.szpt.util.instruction = $.szpt.util.instruction || {} ;
(function($){
	"use strict";
	
	var table = null;
	
	$(document).ready(function() {	
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$(document).on("click","#add",function(){
			addInstruction();
		});
		$(document).on("click",".send",function(){
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var data = row.data();
			var now = new Date().getTime();
			if(data.requireFeedbackTime<now){
				$.layerAlert.tips({
					msg:'已过要求反馈时间，无法再次下发',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			var id = data.id;
			addInstruction(id);
			return false;
		});
		$(document).on("click","#instructionTable tbody td",function(){
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var data = row.data();
			var id = data.id;
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/instructionManage/showFindInstructionPage.action',
				pageLoading : true,
				title:"查看指令详情",
				width : "870px",
				height : "500px",
				btn:["返回"],
				callBacks:{
					btn1:function(index, layero){
						$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
					}
				},
				shadeClose : false,
	    		success:function(layero, index){
	    			
	    		},
	    		initData:{
	    			id : id
	    		},
	    		end:function(){
	    		}
			});
		});
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
		
		//$.common.intiSelectUnitTree();//初始化单位树 ajglUtil.js
		initDictionaryItem();
		initInstructionTable();
	});
	
	function initInstructionTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionManage/queryInstructionList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "5%",
         	    	"title": "选择",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  var a = '<input type="checkbox" name="check" class="icheckbox" value="'+data+'" />' ;
         	    			  return a;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "13%",
					"title" : "创建时间",
					"data" : "createTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 2,
					"width" : "18%",
					"title" : "指令内容",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : "关联内容",
					"data" : "relateObjectContent",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "10%",
					"title" : "指令类型",
					"data" : "typeName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "13%",
					"title" : "要求反馈时间",
					"data" : "requireFeedbackTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 6,
					"width" : "10%",
					"title" : "下发单位",
					"data" : "subs",
					"render" : function(data, type, full, meta) {
						if(data.length==0){
							return '<div class="fi-ceng-out"><span class="color-red2 font16 mar-right">'+data.length+'</span>个';
						}
						var a='';
						a+='<div class="fi-ceng-out"><span class="color-red2 font16 mar-right">'+data.length+'</span>个';
						a+='<div class="fi-ceng row-ceng">   ';
						a+='<h5><span class="pull-right">任务状态</span>下发单位</h5>';
						$.each(data, function(i,item){
							if(item.status==$.common.Constant.ZLZT_DQS()){
								 a+='<p><span class="pull-right color-red2">'+item.statusName+'</span>'+item.receiveSubjectName+'</p>';
							}else{
								a+='<p><span class="pull-right color-green1">'+item.statusName+'</span>'+item.receiveSubjectName+'</p>';
							}
						 }); 
						a+='</div></div>';
						return a;
					}
				},
				{
					"targets" : 7,
					"width" : "10%",
					"title" : "操作",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return '<button class="btn btn-primary btn-sm send">下发</button>';
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
					var tobj = $.zTreeMenu.getTree("unitTree");
					var list = tobj.tree.getSelectedNodes();
					var unitId="";
						if($.util.exist(list) &&list.length>0 ){
							for(var i=0;i<list.length;i++){
								unitId=list[i].id;
							}	
						}
					d["instructionBean.id"] = unitId;
					d["instructionBean.type"] = $.select2.val("#type");
					d["instructionBean.createTimeStart"] = $.laydate.getTime("#createTime", "start");
					d["instructionBean.createTimeEnd"] = $.laydate.getTime("#createTime", "end");
					d["instructionBean.requireFeedbackTimeStart"] = $.laydate.getTime("#backTime", "start");
					d["instructionBean.requireFeedbackTimeEnd"] = $.laydate.getTime("#backTime", "end");
				}else{
					var content = $("#content2").val() == "内容模糊查询" ? "" : $("#content2").val();
					d["instructionBean.content"] = content;
				}
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.instructionBeanPager.totalNum;
				json.recordsFiltered = json.instructionBeanPager.totalNum;
				json.data = json.instructionBeanPager.pageList;
			
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
	}
	function addInstruction(id,type,typeSetContent){
		var title = "新增指令";
		if(!$.util.isBlank(id)){
			title = "下发指令";
		}
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/instructionManage/showNewInstructionPage.action',
			pageLoading : true,
			title:title,
			width : "450px",
			height : "565px",
			btn:["下发","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.szpt.util.newnIstruction ;
					cm.save();
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			id : id,
    			type : type,
    			typeSetContent : typeSetContent
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
		$.laydate.reset("#backTime");
		$.select2.val("#type","");
		$.szpt.util.businessData.destroyTree("unitTree");
		$.szpt.util.businessData.initDwTree("unitTree");
		//$("#unit").val("");
		$("#unitName").val("");
		$("#content").val("");
	}
	/**
	 * 跳转高危人员档案
	 */
	function toPersonDetail(personId){
		window.location.href = context +"/personDetail/showPersonDetailPage.action?clickOrder=2&&clickListOrder=0&&personId=" + personId;
	}
	function init() {
		$.szpt.util.businessData.iniOnetDwTree("unitTree");
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.instruction, { 
		addInstruction:addInstruction,
		toPersonDetail : toPersonDetail
	});	
})(jQuery);