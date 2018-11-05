(function($){
	"use strict";
	
	var table ;
	var isApprove = false;
	$(document).ready(function() {
		//地址栏有布控单号和布控id就直接弹出查看布控结果页面
		if(!$.util.isBlank(bkdId) && !$.util.isBlank(bkdh)){
			showResult(bkdh, bkdId);
		}
		
		if($("#isApprove").val()==1){
			isApprove=true;
		}
		$(document).on("click","#add",function(){
			addControl();
		});
		$(document).on("click","#edit",function(){
			var check = $.icheck.getChecked("check");
			if(check.length!=1){
				$.layerAlert.tips({
					msg:'请选择一条布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			if($(check[0]).attr("status")=="0"){
				$.layerAlert.tips({
					msg:'该布控单已失效',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			if($(check[0]).attr("operateStatus")!=$.common.Constant.CZZT_SPBH()){
				$.layerAlert.tips({
					msg:'请选择审批驳回的布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			addControl(check[0].value);
		});
		$(document).on("click","#stop",function(){
			var check = $.icheck.getChecked("check");
			if(check.length!=1){
				$.layerAlert.tips({
					msg:'请选择一条布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			if($(check[0]).attr("status")=="0"){
				$.layerAlert.tips({
					msg:'该布控单已失效',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			$.layerAlert.confirm({
				msg:"确认停止布控吗？",
				title:"提示",
				yes:function(index, layero){
					stopControl(check[0].value);
				}
			});
			
		});
		$(document).on("click","#refresh",function(){
			history.go(0);
		});
		$(document).on("click",".detail",function(){
			var id = this.id;
			showDetail(id);
		});
		$(document).on("click","#result",function(){
			
			var check = $.icheck.getChecked("check");
			if(check.length!=1){
				$.layerAlert.tips({
					msg:'请选择一条布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			showResult($('#'+check[0].value).text(),check[0].value);
		});
		$(document).on("click","#approve",function(){
			var check = $.icheck.getChecked("check");
			if(check.length!=1){
				$.layerAlert.tips({
					msg:'请选择一条布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			if($(check[0]).attr("status")=="0"){
				$.layerAlert.tips({
					msg:'该布控单已失效',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			if($(check[0]).attr("operateStatus")!=$.common.Constant.CZZT_DSP()){
				$.layerAlert.tips({
					msg:'请选择待审批的布控单',
					selector:this,
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
			showApprove(check[0].value);
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
		if(isApprove){
			initApproveTable();
		}else{
			initTable();
		}
	});
	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/personExecuteControl/queryPersonExecuteControlList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "4%",
         	    	"title": "选择",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  var a = '<input type="checkbox" name="check" class="icheckbox" value="'+data+'"status="'+full.status+'"operateStatus="'+full.operateStatus+'" />' ;
         	    			  return a;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "10%",
					"title" : "布控单编号",
					"data" : "num",
					"render" : function(data, type, full, meta) {
						return '<a class="detail"href="#" id="'+full.id+'">'+data+'</a>';
					}
				},
				{
					"targets" : 2,
					"width" : "5%",
					"title" : "审批状态",
					"data" : "operateStatusName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "5%",
					"title" : "姓名",
					"data" : "personName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "12%",
					"title" : "身份证号",
					"data" : "idCardNo",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "10%",
					"title" : "布控累计结果",
					"data" : "idCardNo",
					"render" : function(data, type, full, meta) {
						var str = "";
						if(full.status=="1"){
							str = '<a href="#" class="tishikuang" data-toggle="tooltip"  data-placement="bottom" title="" data-original-title="Tooltip on bottom"></a>';
						}else{
							str = '<a href="#" class="tishikuang" data-toggle="tooltip"  data-placement="bottom" title="该布控单已失效" data-original-title="Tooltip on bottom"></a>';
						}
						return str;
					}
				},
				{
					"targets" : 6,
					"width" : "10%",
					"title" : "起始时间",
					"data" : "startTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 7,
					"width" : "10%",
					"title" : "结束时间",
					"data" : "endTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 8,
					"width" : "10%",
					"title" : "最新修改时间",
					"data" : "lastModifyTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 9,
					"width" : "7%",
					"title" : "最新修改人",
					"data" : "lastModifyPerson",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 10,
					"width" : "15%",
					"title" : "布控原因",
					"data" : "note",
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
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				if($.common.isFullConditionSearch()){
					d["personExecuteControlBean.num"] = $("#num").val();
					d["personExecuteControlBean.personName"] = $("#personName").val();
					d["personExecuteControlBean.idCardNo"] = $("#idcardNo").val();
					d["personExecuteControlBean.note"] = $("#note").val();
					d["personExecuteControlBean.startTime"] = $.laydate.getTime("#dateRange", "start");
					d["personExecuteControlBean.endTime"] = $.laydate.getTime("#dateRange", "end");
					d["isShowInvalid"] = $.icheck.getChecked("ifDisabled").length!=0;
					
					d["personExecuteControlBean.operateStatus"]=$.select2.val("#operateStatus");
				}else{
                    var note = $("#content2").val().indexOf("模糊查询")>=0 ? "" : $("#content2").val();
					d["personExecuteControlBean.personName"] = note;
				}
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.personExecuteControlBeanPager.totalNum;
				json.recordsFiltered = json.personExecuteControlBeanPager.totalNum;
				json.data = json.personExecuteControlBeanPager.pageList;
			
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	}
	
	//鼠标移进时间
	$(document).on("mouseover","tr",function(){
		$(this).find(".tishikuang").trigger("hover");
		$(this).find(".tishikuang").trigger("focus");
	});
	$(document).on("mouseout","tr",function(){
		$(this).find(".tishikuang").trigger("blur");
	});
	
	function initApproveTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/personExecuteControl/queryPersonExecuteControlList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "4%",
         	    	"title": "选择",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  var a = '<input type="checkbox" name="check" class="icheckbox" value="'+data+'"status="'+full.status+'"operateStatus="'+full.operateStatus+'" />' ;
         	    			  return a;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "10%",
					"title" : "布控单编号",
					"data" : "num",
					"render" : function(data, type, full, meta) {
						return '<a class="detail"href="#" id="'+full.id+'">'+data+'</a>';
					}
				},
				{
					"targets" : 2,
					"width" : "5%",
					"title" : "审批状态",
					"data" : "operateStatusName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "5%",
					"title" : "姓名",
					"data" : "personName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "12%",
					"title" : "身份证号",
					"data" : "idCardNo",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"width" : "10%",
					"title" : "起始时间",
					"data" : "startTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 6,
					"width" : "10%",
					"title" : "结束时间",
					"data" : "endTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 7,
					"width" : "15%",
					"title" : "布控原因",
					"data" : "note",
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
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				if($.common.isFullConditionSearch()){
					d["personExecuteControlBean.num"] = $("#num").val();
					d["personExecuteControlBean.personName"] = $("#personName").val();
					d["personExecuteControlBean.idCardNo"] = $("#idcardNo").val();
					d["personExecuteControlBean.note"] = $("#note").val();
					d["personExecuteControlBean.startTime"] = $.laydate.getTime("#dateRange", "start");
					d["personExecuteControlBean.endTime"] = $.laydate.getTime("#dateRange", "end");
					d["isShowInvalid"] = $.icheck.getChecked("ifDisabled").length!=0;
					d["personExecuteControlBean.operateStatus"]=$.select2.val("#operateStatus");
				}else{
					var note = $("#content2").val().indexOf("模糊查询")>=0 ? "" : $("#content2").val();
					d["personExecuteControlBean.personName"] = note;
					d["personExecuteControlBean.operateStatus"]=$.common.Constant.CZZT_DSP();
				}
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.personExecuteControlBeanPager.totalNum;
				json.recordsFiltered = json.personExecuteControlBeanPager.totalNum;
				json.data = json.personExecuteControlBeanPager.pageList;
			
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	}
	
	function addControl(id){
		var title = "新增人员布控";
		if(!$.util.isBlank(id)){
			title = "维护人员布控";
		}
		window.top.$.layerAlert.dialog({
			content : context +  '/personExecuteControl/showNewPersonExecuteControlPage.action',
			pageLoading : true,
			title:title,
			width : "820px",
			height : "550px",
			btn:["提交审批","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.save();
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
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
	}
	function stopControl(id){
		$.ajax({
			url:context +'/personExecuteControl/stopPersonExecuteControl.action',
			type:'post',
			dataType:'json',
			data:{
				id:id
			},
			success:function(successData){
				$.util.topWindow().$.layerAlert.alert({
					msg:"该布控单已失效",
					title:"提示",
					icon:1,
					yes:function(index, layero){
						table.draw(false);
					}
				});
			},
			error:function(errorData){
				
			}
		});
	}
	function showDetail(id){
		window.top.$.layerAlert.dialog({
			content : context +  '/personExecuteControl/showPersonExecuteControlDetailPage.action',
			pageLoading : true,
			title:"人员布控详情",
			width : "820px",
			height : "700px",
			btn:["确定"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			id : id,
    			isApprove : false
    		},
    		end:function(){
    		}
		});
	}
	function showApprove(id){
		window.top.$.layerAlert.dialog({
			content : context +  '/personExecuteControl/showPersonExecuteControlDetailPage.action',
			pageLoading : true,
			title:"人员布控审批",
			width : "820px",
			height : "700px",
			btn:["通过","驳回"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.pass();
				},
				btn2:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.noPass();
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    		},
    		initData:{
    			id : id,
    			isApprove : true
    		},
    		end:function(){
    		}
		});
	}
	function showResult(code,id){
		window.top.$.layerAlert.dialog({
			content : context +  '/personExecuteControl/showPersonExecuteControlResultPage.action',
			pageLoading : true,
			title:"人员布控结果",
			width : "850px",
			height : "700px",
			btn:["返回","生成布控快照"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				},btn2:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.createExecuteComtrolSnapshotClick();
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    			
    		},
    		initData:{
    			id : id,
    			code:code,
    			isApprove : true
    		},
    		end:function(){
    		}
		});
	}
	/**
	 * 重置查询条件
	 */
	function resetSearchCondition(){
		$.laydate.reset("#dateRange");
		$("#note").val("");
		$("#num").val("");
		$("#personName").val("");
		$("#idcardNo").val("");
		$.icheck.check("#ifDisabled",false);
		if(isApprove){
			$.select2.val("#operateStatus",$.common.Constant.CZZT_DSP());
		}else{
			$.select2.val("#operateStatus","");
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		refresh:function(){
			table.draw(true);
		}
	});	
})(jQuery);