$.addReport = $.addReport || {};
(function($){  //新增报告弹窗js
	"use strict";
	var i=1;
	var table;
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var caseId = initData.lockerId;

	$(document).ready(function(){
		//initRoportTable();
	})	
		
		 /**
		 * 上传附件
		 */
		$(document).on('click',"#fileUploadBtn",function(){
			//fileUpload();
			$('#upload').show();
		});
		 /**
		 * 删除报告
		 */
		$(document).on('click',"#deleteReport",function(){
			deleteReport();
		});
		 /**
		 * 保存报告
		 */
		$(document).on('click',"#saveReport",function(){
			saveReport();
		});
		 /**
		 * 取消报告
		 */
		$(document).on('click',"#saveReport",function(){
			saveReport();
		});
		function  saveReport(){
			
		}
		
		
		/**
		 * 上传附件
		 */
		function fileUpload(){
			if($('.fjClass').length>0){
				$.util.topWindow().$.layerAlert.confirm({
					msg:"点击上传会删除已有附件，确定要删除吗？",
					title:"删除",	  //弹出框标题
					width:'300px',
					hight:'200px',
					icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
					shift:1,  //弹出时的动画效果  有0-6种
					yes:function(index, layero){
						var data={
								fjId:$('.fjClass').attr("id")  //获取附件ID
						}
						var queryStr = $.util.toJSONString(data);
						$.ajax({
							url:context +'/wdza/deleteSpecialCaseMaterial.action',
							type:"post",
							contentType: "application/json; charset=utf-8",
							dataType:"json",
							data:queryStr,
							success:function(successData){ //删除附件
								var bn=successData.resultMap.result;
								if(bn==false){
									$.layerAlert.alert({title:"提示",msg:"附件删除失败"});
								}else{
									showUploading(); //上传弹窗
								}
							}
						});	
						
					}
				});
			}else{
				showUploading();
			}
			
				
		
		}
	  
		/**
		 *上传弹窗 
		 */
		function showUploading(){
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/wdza/updateZaData.action',
				pageLoading : true,
				title:"资料上传",
				width : "580px",
				height : "308px",
				btn:["完成","取消"],
				callBacks:{
					btn1:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.zaUpload
						var fj= cm.saveFJID();
						var str=""
						$('#fjList').empty();
						for(var i=0;i<fj.length;i++){
							str+='<li class="fjClass" id='+fj[i].id+'>'+fj[i].name+'</li>'
						}
						$('#fjList').append(str);
						$.util.topWindow().$.layerAlert.closeWithLoading(index);
					},
					btn2:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.zaUpload
						var fj= cm.saveFJID();
						var data={
								fjId:fj.id  //获取附件ID
						}
						var queryStr = $.util.toJSONString(data);
						$.ajax({
							url:context +'wdza/deleteSpecialCaseMaterial.action',
							type:"post",
							contentType: "application/json; charset=utf-8",
							dataType:"json",
							data:queryStr,
							success:function(successData){ //删除附件
								var bn=successData.resultMap.result;
								if(bn==false){
									$.layerAlert.alert({title:"提示",msg:"附件删除失败"});
									var fj= cm.saveFJID();
									var str=""
									$('#fjList').empty();
									for(var i=0;i<fj.length;i++){
										str+='<li class="fjClass" id='+fj[i].id+'>'+fj[i].name+'</li>'
									}
									$('#fjList').append(str);
								}
							}
						});	
					}
				},
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					//"zazllxCode" : zazllxCode,
					"zaId" : caseId
				},
				end:function(){
					//lockerTable.draw(true);
				}
			});
		}
	    /**
	     * 新增报告弹窗页面
	     */
	    function addReport(){
			var id=arrcheck.attr("bb");
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/zawh/showAddReport.action',
				pageLoading : true,
				title:"分配用户",
				width : "70%",
				height : "80%",
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					lockerId : id
				},
				end:function(){
					table.draw(true);
				}
			});
		
	    }
	
	/**
	 * 加载表格--专案进展报告
	 */	
	function initRoportTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findReportTable.action";
			tb.columnDefs = [
				{
					"targets": 0,
				 	"title": "",
				 	"className":"table-checkbox",
				 	"data": "id" ,
				 	"render": function ( data, type, full, meta ) {
				 			  return  '<input type="checkbox" name="askRoom" bb='+full.id+' caseID='+full.caseID+' class="icheckbox"/>';
				 	}
				},            
				{
					"targets": 1,
         	    	"title": "序号",
         	    	"width" : "8%",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :2,
					"width" : "25%",
					"title" : "报告名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return '<a href="#">'+data+'</a>';
					}
				},
				{
					"targets" : 3,
					"width" : "25%",
					"title" : "内容",
					"data" : "attachmentId",
					"render" : function(data, type, full, meta) {
						return '<a id="'+data+'"><span class="zl-icon zl-icon-basic"></span></a>';
					}
				},
				{
					"targets" : 4,
					"width" : "22%",
					"title" : "录入人及时间",
					"data" : "createPerson",
					"render" : function(data, type, full, meta) {
						return data+'<br>'+$.date.timeToStr(full.createdTime, "yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					"targets" : 5,
					"width" : "27%",
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
						"caseId":caseId,
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
			table = $("#table").DataTable(tb);
	  }
	
	
	
})(jQuery);