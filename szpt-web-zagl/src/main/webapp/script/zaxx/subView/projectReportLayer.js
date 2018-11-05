$.projectReport = $.projectReport || {};
(function($){
	"use strict";
	var i=1;
	var table;
	$(document).ready(function(){
		//initRoportTable();
	})	
		 /**
		 * 关闭页面返回上一级页面
		 */
		$(document).on('click',".attachment",function(){
		 var a=	$(this).attr("id");
		 alert(a);
		});
		
		 /**
		 * 新增报告
		 */
		$(document).on('click',"#addReport",function(){
			addReport();
		});
		 /**
		 * 删除报告
		 */
		$(document).on('click',"#deleteReport",function(){
			deleteReport();
		});
		 /**
		 * 查看报告详情
		 */
		$(document).on('click',".jumpBG",function(){
			baxq($(this).attr("id"),$(this).attr("fjid"));
		});
	  
		/**
		 * 报告详情--弹窗
		 */
		function baxq(data,fjid){

			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/wdza/showReportDetails.action',
				pageLoading : true,
				title:"报告详情",
				width : "70%",
				height : "80%",
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					lockerId :data,  //报告id
					fjid:fjid
				},
				end:function(){
					table.draw(true);
				}
			});
		
	    
		}
		
	    /**
	     * 新增报告弹窗页面
	     */
	    function addReport(){
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/wdza/showAddReport.action',
				pageLoading : true,
				title:"分配用户",
				width : "70%",
				height : "80%",
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					lockerId : $.zaxq.getCaseId()
				},
				end:function(){
					table.draw(true);
				}
			});
		
	    }
	
	
	/**
	 * 删除报告
	 */
	function deleteReport(){
    	var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==1){
    	$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){

	            var data={
						id:$('input[type="checkbox"]:checked').attr("bb"),
						fjId:$('input[type="checkbox"]:checked').attr("fjId")
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/wdza/deleteReport.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						var b=successData.resultMap.result;
						if(b==true){
							$.layerAlert.alert({title:"提示",msg:"删除成功"});
							table.draw(true);
						}else{
							$.layerAlert.alert({title:"提示",msg:"删除失败"});
						}
					},
					error:function(data){
						$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
					}
				});	
			
			}
		});
		}else{
			$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
		}
    
	}
	
	/**
	 * 加载表格--专案进展报告
	 */	
	function initRoportTable(){
		if (table!=null){
			table.draw(true);
		}else{
			var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findReportTable.action";
			tb.columnDefs = [
			                 {
			                	 "targets": 0,
			                	 "title": "",
			                	 "className":"table-checkbox",
			                	 "data": "id" ,
			                	 "render": function ( data, type, full, meta ) {
			                		 return  '<input type="checkbox" name="askRoom" fjId="'+full.attachmentId+'" bb="'+full.id+'" class="icheckbox"/>';
			                	 }
			                 },            
			                 {
			                	 "targets": 1,
			                	 "title": "序号",
			                	 "width" : "7%",
			                	 "className":"table-checkbox",
			                	 "data": "id" ,
			                	 "render": function ( data, type, full, meta ) {
			                		 return    (meta.row+1);
			                	 }
			                 },
			                 {
			                	 "targets" :2,
			                	 "width" : "29%",
			                	 "title" : "报告名称",
			                	 "data" : "name",
			                	 "render" : function(data, type, full, meta) {
			                		 return'<div class="pull-left mar-right"><a href="javascript:void(0);" class="jumpBG" fjid="'+full.attachmentId+'" id="'+full.id+'" target="_blank"><span class="zl-icon zl-icon-basic"></span></a></div>'
			                         +'<br/><h2 class="row-mar font14"><a class="jumpBG" fjid="'+full.attachmentId+'" id="'+full.id+'" href="javascript:void(0)">'+data+'</a></h2>';
			                	 }
			                 },
//			                 {
//			                	 "targets" : 3,
//			                	 "width" : "25%",
//			                	 "title" : "内容",
//			                	 "data" : "attachmentId",
//			                	 "render" : function(data, type, full, meta) {
//			                		 return '<a class="attachment" id="'+data+'"><span class="zl-icon zl-icon-basic"></span></a>';
//			                	 }
//			                 },
			                 {
			                	 "targets" : 3,
			                	 "width" : "29%",
			                	 "title" : "录入人及时间",
			                	 "data" : "createPerson",
			                	 "render" : function(data, type, full, meta) {
			                		 return data+'<br>'+$.date.timeToStr(full.createdTime, "yyyy-MM-dd HH:mm:ss");
			                	 }
			                 },
			                 {
			                	 "targets" : 4,
			                	 "width" : "29%",
			                	 "title" : "录入机构",
			                	 "data" : "unitName",
			                	 "render" : function(data, type, full, meta) {
			                		 return data;
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
			//是否分页
			tb.paging=true;
			
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				var data = {
						"caseId":$.zaxq.getCaseId(),
						"start":d.start,
						"length":d.length
				};
				var queryStr = $.util.toJSONString(data);
				$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.pageList;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#reportTable").DataTable(tb);
		}
	  }
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.projectReport, { 
		initRoportTable : initRoportTable
	});	
	
})(jQuery);