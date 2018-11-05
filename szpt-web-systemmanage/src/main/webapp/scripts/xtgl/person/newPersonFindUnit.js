(function($){
	"use strict";
	var table = null;//dataTable
	//拿到初始化的值
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	$(document).ready(function() {	
		initNewPersonSelectUnitPage();
		
		$("#pa_findUnitPage_close").on("click",function(){//关闭
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
		});
		$("#pa_findUnitPage_search").on("click",function(){//搜索
			table.draw(false);//重绘表格
		});
		$("#pa_findUnitPage_save").on("click",function(){//确定
			
			var arr = $.icheck.getChecked("bdca_dataTable_trCheckbox");
			
			if(arr.length > 0){
				var win = $.util.rootWindow();
				win.$.rowData = $(arr[0]).parents("tr").data("rowData");
				$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
			}else{
				
			}
//			parent.$.common.testA("雨中分局");
		});
		$("#pa_findUnitPage_cancel").on("click",function(){//取消
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
		});
		
		
		
	});

	
	
	
/**
 * 初始化单位查询页面
 */	
function initNewPersonSelectUnitPage(){
	
	var tb = $.uiSettings.getOTableSettings();
	tb.ajax.url = context+ "/personManage/searchUnitList.action";
	tb.columnDefs = [
	    {
			"targets" : 0,
			"width" : "10%",
			"title" : "",
			"data" : "",
			"render" : function(data, type,full, meta) {
				var a = '<input type="radio" name="bdca_dataTable_trCheckbox" class="icheckbox" />';
					return a;
			}
	    },
	    {
			"targets" : 1,
			"width" : "50%",
			"title" : "单位名称",
			"data" : "unitName",
			"render" : function(data, type,full, meta) {
					return data;
			}
	    },
	    {
		"targets" : 2,
		"width" : "40%",
		"title" : "单位简称",
		"data" : "shortName",
		"render" : function(data, type,full, meta) {
				return data;
			}
	    }];
	tb.ordering = false;
	tb.paging = false;
	tb.lengthMenu = [],
	tb.searching = false;
	tb.lengthChange = false;//不分页
	tb.autoFooter = false;//是否显示底边字段(底边表头)
	tb.paramsReq = function(d, pagerReq) {//查询条件回调，初始化和重绘时候都会先执行该回调
		var unitBean = {
			"unitName":$("#pa_findUnitPage_searchText").val() == "请输入"?"":$("#pa_findUnitPage_searchText").val(),
		};
		$.util.objToStrutsFormData(unitBean, "unitBean", d);
	};
	
	tb.paramsResp = function(json) {//处理返回的数据的回调
		json.data = json.unitBeanList;
		json.recordsTotal = json.length;
		json.recordsFiltered = json.length;
	};
	
	tb.rowCallback = function(row, data, index) {//行绘制完毕之后回调
		$(row).data("rowData",data);//绑定tr元素上数据
	};
	
	tb.myDrawCallback = function(settings){//表格重绘完成之后回调方法
		//checkBox选中事件
		$("input[name='bdca_dataTable_trCheckbox']").on("ifChanged", function(event){
			$("#pa_findUnitPage_unitTable tr").css("background-color","");
			
			var arr = $.icheck.getChecked("bdca_dataTable_trCheckbox");
			$(arr).each(function(i,val){
				$(val).parents("tr").css("background-color","#EEE");
			});
		});
    };
	table = $("#pa_findUnitPage_unitTable").DataTable(tb);
	
}





	
})(jQuery);	