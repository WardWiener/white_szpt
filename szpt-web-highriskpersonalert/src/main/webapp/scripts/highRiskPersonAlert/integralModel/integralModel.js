$.szpt = $.szpt || {} ;
$.szpt.integralModel = $.szpt.integralModel || {} ;

(function($){
	"use strict";
	
	var table=null;
	
	$(document).ready(function() {	
		initOrRefreshTable();
	});
	
	/**
	 * 新增
	 */
	$(document).on("click","#addModel",function(){
		window.top.$.layerAlert.dialog({
			content : context +  '/integralModel/showAddAndModifyPage.action',
			pageLoading : true,
			title:"新增人员积分模型",
			width : "80%",
			height : "100%",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.szpt.integralModel ;
					cm.getValue();
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			add : true
    		},
    		end:function(){
    			initOrRefreshTable();
    		}
		});
	});
	/**
	 * 修改
	 */
	$(document).on("click","#updateModel",function(){
		var data = null;
		var arr=$.icheck.getChecked("radio") ;
		if(arr.length!=1){
    		window.top.$.layerAlert.alert({msg:"请选择一条记录，进行修改！",title:"提示"}) ;
    		return false ;
    	}
		$.each(arr, function(i, val){
    		var tr = $(val).parents("tr") ;
    		var row = table.row(tr) ;
    		data = row.data() ;
    	});
		window.top.$.layerAlert.dialog({
			content : context +  '/integralModel/showAddAndModifyPage.action',
			pageLoading : true,
			title:"修改人员积分模型",
			width : "80%",
			height : "100%",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.szpt.integralModel ;
					cm.getValue();
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			add : false,
    			integralModelData:data
    		},
    		end:function(){
    			initOrRefreshTable();
    		}
		});
	});
	/**
	 * 删除
	 */
	$(document).on("click","#deleteModel",function(){
		var arr=$.icheck.getChecked("radio") ;
		if(arr.length!=1){
    		window.top.$.layerAlert.alert({msg:"请选择一条记录，进行删除！",title:"提示"}) ;
    		return false ;
    	}
		$.layerAlert.confirm({
    		msg:"删除后数据不可恢复，您确定要进行删除操作？",
    		title:"提示",
    		yes:function(index, layero){
    			var val=$.icheck.val("radio") ;
    			var data = {
    					"id":val,
    			}
    			var url = "/integralModel/delete.action";
    			operation(url,data,"删除");
			}
		});
	});
	/**
	 * 启用
	 */
	$(document).on("click","#enabled",function(){
		var arr=$.icheck.getChecked("radio") ;
		if(arr.length!=1){
    		window.top.$.layerAlert.alert({msg:"请选择一条记录，进行操作！",title:"提示"}) ;
    		return false ;
    	}
		$.layerAlert.confirm({
    		msg:"启用本模版后上一启用的模板将停用，您确定要进行启用操作？",
    		title:"提示",
    		yes:function(index, layero){
    			var val=$.icheck.val("radio") ;
    			var data = {
    					"id":val,
    					"status":$.common.DICT.DICT_ENABLED
    			}
    			var url = "/integralModel/updateState.action";
    			operation(url,data,"启用");
			}
		});
		
	});
	/**
	 * 停用
	 */
	$(document).on("click","#disable",function(){
		var arr=$.icheck.getChecked("radio") ;
		if(arr.length!=1){
    		window.top.$.layerAlert.alert({msg:"请选择一条记录，进行操作！",title:"提示"}) ;
    		return false ;
    	}
		var val=$.icheck.val("radio") ;
		var data = {
				"id":val,
				"status":$.common.DICT.DICT_DISENABLED
		}
		var url = "/integralModel/updateState.action";
		operation(url,data,"停用");
	});
	
	/**
	 * 向后台请求
	 * @param url 请求url
	 * @param data 传参数据
	 */
	function operation(url,data,msg){
		$.ajax({
			url:context +url,
			type:'post',
			dataType:'json',
			data:data,
			success:function(successData){
				var flag = successData.flag;
				if(flag){
					window.top.$.layerAlert.alert({msg:msg+"成功！",title:"提示",time:1500});
					initOrRefreshTable()
				}else{
					window.top.$.layerAlert.alert({msg:msg+"失败！",title:"提示"});
				}
			}
		});
	}

	
	function initOrRefreshTable(){
		if(table!=null){
			table.draw(false);
			return;
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/integralModel/findIntegralModel.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "10%",
				"title" : "选择",
				"data" : "id",
				"render" : function(data, type, full, meta) {
					 var a = '<input type="radio" name="radio" class="icheckradio" value="'+data+'" />' ;
	    			  return a;
				}
			},
			{
				"targets" : 1,
				"width" : "20%",
				"title" : "模型编号",
				"data" : "num",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "20%",
				"title" : "模型名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "15%",
				"title" : "状态",
				"data" : "statusName",
				"render" : function(data, type, full, meta) {
					var status = "";
					if(data=="启用"){
						status = "<span class='icon-dot icon-dot-qy mar-right'></span>"+data
					}else{
						status = "<span class='icon-dot icon-dot-ty mar-right'></span>"+data
					}
					return status;
				}
			},
			{
				"targets" : 4,
				"width" : "20%",
				"title" : "达到预警的最低分数值",
				"data" : "alertPoint",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.paging = [10];
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
			
		};
		tb.paramsResp = function(json) {
			var integarlModelBeanList = json.integarlModelBeanList;
			json.data = integarlModelBeanList;
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
		};
		tb.rowCallback = function(row,data, index) {
			//行双击事件
			$(row).on("dblclick",function(){
				 look(data);
			});
			
		};
		table = $("#integralModelTable").DataTable(tb);
	}
	
	/**
	 * 查看详情
	 */
	function look(data){
		window.top.$.layerAlert.dialog({
			content : context +  '/integralModel/showLookPage.action',
			pageLoading : true,
			title:"查看人员积分模型",
			width : "80%",
			height : "100%",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			integralModel : data
    		},
    		end:function(){
    			initOrRefreshTable();
    		}
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.integralModel, { 
		
	});	
})(jQuery);