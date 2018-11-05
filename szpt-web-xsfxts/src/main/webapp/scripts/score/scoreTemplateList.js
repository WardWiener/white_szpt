$.RobberyTheftCaseScoreTemplateList = $.RobberyTheftCaseScoreTemplateList || {};
(function($){
	"use strict"
	
	var cgtbTable = null;
	
	$(document).ready(function(){
		findCgtgList();
		
		/**
		 * 新增
		 */
		$(document).on('click',"#newBut",function(){
			alertNewPage();
		});
		
		/**
		 * 复制新增
		 */
		$(document).on('click',"#copyBut",function(){
			var cgtbIds = getSelectedCgtbIds();
			if(cgtbIds.length != 1){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择一条数据。"});
				return ;
			}
			
			var cgtbId = cgtbIds[0];
			alertNewPage(cgtbId);
		});
		
		/**
		 * 修改
		 */
		$(document).on("click","#modifyBut",function(){
			alertModifyPage();
		});
		
		/**
		 * 删除
		 */
		$(document).on("click","#deleteBut",function(){
			deleteCgtb();
		});
		
		/**
		 * 启用
		 */
		$(document).on("click","#enabledBut",function(){
			var cgtbIds = getSelectedCgtbIds();
			
			if(cgtbIds.length != 1){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择一条数据。"});
				return ;
			}
			
			var state = $("input[value='"+cgtbIds[0]+"']").attr("state");
			if(state == $.common.DICT.DICT_ENABLED){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"已启用的数据不需重复启用。"});
				return ;
			}
			modifyCgtbStatus(cgtbIds, $.common.DICT.DICT_ENABLED);
		});
		
		/**
		 * 停用
		 */
		$(document).on("click","#disableBut",function(){
			var cgtbIds = getSelectedCgtbIds();
			if(cgtbIds.length < 1){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择一条数据。"});
				return ;
			}
			
			var msg = "";
			$.each(cgtbIds,function(i,val){
				var state = $("input[value='"+val+"']").attr("state");
				if(state == $.common.DICT.DICT_DISENABLED){
					msg = "已停用的数据不需重复停用。"
					return false;
				}
			});
			if(!$.util.isBlank(msg)){
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:msg});
				return ;
			}
			
			modifyCgtbStatus(cgtbIds, $.common.DICT.DICT_DISENABLED);
		});
		
		/**
		 * table 行点击事件
		 */
		$(document).on("click","#cgtbTable tr",function(){
			var cgtbId = $(this).find("input").val();
			alertLookPage(cgtbId);
		});
		
	});
	
	/**
	 * 新建串并案评分模版
	 */
	function alertNewPage(cgtbId){
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/scoreTemplate/showNewScoreTemplate.action',
			pageLoading : true,
			title:"新增串并案评分模版",
			width : "850px",
			height : "80%",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.newScoreTemplate ;
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
				cgtbId : cgtbId
			},
			end:function(){
				findCgtgList();
			}
		});
	}
	
	/**
	 * 修改串并案评分模版
	 */
	function alertModifyPage(){
		var cgtbIds = getSelectedCgtbIds();
		if(cgtbIds.length != 1){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择一条数据。"});
			return ;
		}
		
		var cgtbId = cgtbIds[0];
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/scoreTemplate/showModifyScoreTemplate.action',
			pageLoading : true,
			title:"修改串并案评分模版",
			width : "850px",
			height : "80%",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.modifyScoreTemplate ;
					cm.update();
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				cgtbId : cgtbId
			},
			end:function(){
				findCgtgList();
			}
		});
	}
	
	/**
	 * 查看串并案评分模版
	 */
	function alertLookPage(cgtbId){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/scoreTemplate/showLookScoreTemplate.action',
			pageLoading : true,
			title:"查看串并案评分模版",
			width : "850px",
			height : "80%",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				cgtbId : cgtbId
			},
			end:function(){
				
			}
		});
	}
	
	/**
	 * 修改串并案分析评分模版状态
	 * @param cgtbIds 模版id数组
	 * @param state 状态code
	 */
	function modifyCgtbStatus(cgtbIds, state){
		var data = {
			"rtcstbIds" : cgtbIds ,
			"state" : state
		}
		var dataStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/scoreTemplate/modifyStatusByIds.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			customizedOpt:{
				ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if(successData.resultMap.flag){
					var msg = "停用成功。";
					if(state == $.common.DICT.DICT_ENABLED){
						msg = "启用成功。";
					}
					$.util.topWindow().$.layerAlert.alert({icon:6, msg:msg});
					findCgtgList();
				}
			}
		});	
	}
	
	/**
	 * 删除串并案分析评分模版
	 */
	function deleteCgtb(){
		var cgtbIds = getSelectedCgtbIds();
		
		if(cgtbIds.length < 1){
			$.util.topWindow().$.layerAlert.alert({icon:0, msg:"请选择一条数据。"});
			return ;
		}
		
		$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){
				var data = {
					"rtcstbIds" : cgtbIds
				}
				var dataStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/scoreTemplate/deleteByIds.action',
					data:dataStr,
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					customizedOpt:{
						ajaxLoading:true,//设置是否loading
					},
					success:function(successData){
						var resultMap = successData.resultMap;
						if(resultMap.flag){
							if($.util.exist(resultMap.rtcstbNames)){
								var names = "";
								$.each(resultMap.rtcstbNames,function(n,name){
									names += name;
									if(n < resultMap.rtcstbNames.length - 1){
										names += "、";
									}
								});
								$.util.topWindow().$.layerAlert.alert({icon:0, msg:names + "，已被使用，不可删除。"});
							}else{
								$.util.topWindow().$.layerAlert.alert({icon:6, msg:"删除成功。"});
							}
						}else{
							$.util.topWindow().$.layerAlert.alert({icon:5, msg:"删除失败。"});
						}
						findCgtgList();
					}
				});
			}
		});
	}
	
	/**
	 * 获取选中的串并案分析评分模版id集合
	 */
	function getSelectedCgtbIds(){
		var arr = $.icheck.getChecked("cgtb");
		var cgtbIds = new Array();
		$.each(arr,function(i,val){
			var cgtbId = $(val).val();
			if($.util.isBlank(cgtbId)){
				return true;
			}
			cgtbIds.push(cgtbId);
		});
		return cgtbIds;
	}
	
	/**
	 * 查询所有模版
	 */
	function findCgtgList(){
		$.ajax({
			url:context +'/scoreTemplate/queryList.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				initCgtbTable(successData.resultMap.rtcstbs);
			}
		});
	}
	
	/**
	 * 初始化串并案分析评分模版列表
	 */
	function initCgtbTable(formData){
		if($.util.exist(cgtbTable)){
			cgtbTable.destroy();
			$("#cgtbTable").empty();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = formData;
		tb.columnDefs = [
			{
				"targets": 0,
     	    	"width": "50px",
     	    	"title": "选择",
     	    	"className":"table-checkbox",
     	    	"data": "id" ,
     	    	"render": function ( data, type, full, meta ) {
     	    			  var a = '<input type="checkbox" name="cgtb" class="icheckbox" value="'+data+'" state="'+full.state+'"/>' ;
     	    			  return a;
     	    	}
			},
			{
				"targets" : 1,
				"width" : "",
				"title" : "模版编码",
				"data" : "code",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "",
				"title" : "模版名称",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "",
				"title" : "案件类型",
				"data" : "typeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "",
				"title" : "案件性质",
				"data" : "firstSortName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 5,
				"width" : "",
				"title" : "二级案件性质",
				"data" : "secondSortName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 6,
				"width" : "",
				"title" : "状态",
				"data" : "stateName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 7,
				"width" : "",
				"title" : "串并案最低分数值",
				"data" : "minScore",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 8,
				"width" : "",
				"title" : "备注",
				"data" : "remarks",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}
		];
		tb.ordering = false;
		tb.paging = false; // 是否分页
		tb.info = true; // 是否显示表格左下角分页信息
		tb.autoFoot = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.lengthMenu = [ formData.length ];
		cgtbTable = $("#cgtbTable").DataTable(tb);
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.RobberyTheftCaseScoreTemplateList, { 
		
	});	
})(jQuery);