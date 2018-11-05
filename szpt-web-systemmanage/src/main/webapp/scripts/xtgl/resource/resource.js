(function($) {
	"use strict";
	var table;
	$(document).ready(function() {
		initTable();
		initSelect2();
	});
	
	var number;		//当前弹出层
	jQuery.extend($.common, { 
		setTestObj2:function(index){
			number=index;	//从父页面获取当前弹出层   用于关闭
		},
	});	
	
	/*
	 * 生成表格
	 */
	//function queryRole (){
	function initTable(){
	var tb = $.uiSettings.getOTableSettings() ;
	tb.ajax.url = context+"/resource/queryResource.action";
	tb.columnDefs = [
		{ 
			"targets": 0,
			"width": "5%",
			"title": "选择",
			"className":"table-checkbox",
			"data": "id" ,
			"render": function ( data, type, full, meta ) {		
					  var a = '<input type="checkbox" name="testTr" class="icheckbox" />' ;
				      return a;
			}
		},
		{ 
			"targets": 1,
			"width": "35%",
			"title": "资源名称",
			"data": "resourceName" ,
			"render": function ( data, type, full, meta ) {								  
				      return data;
			}
		},
		{ 
			"targets": 2,
			"width": "20%",
			"title": "资源类型",
			"data": "type" ,
			"render": function ( data, type, full, meta ) {		        	
				      return data;
			}
		},
		{ 
			"targets": 3,
			"width": "20%",
			"title": "资源链接",
			"data": "resourceUrl" ,
			"render": function ( data, type, full, meta ) {		        	
				      return data;
			}
		},
	] ;
	 tb.paging = true ; 
	 tb.lengthMenu = [ 10 ];
	 tb.lengthChange = false;
	 tb.searching = false ;
	 tb.ordering = false ;
	 tb.paramsReq = function(d, pagerReq){
		var resourceBean = new Object();
		resourceBean.resourceName=$("#roleName").val();
		if($("#pa_newPage_type").val() == "all"){
		resourceBean.type = "";
		}else{
			resourceBean.type = $("#pa_newPage_type").val()
		}
		//object转化成dataMap传到d里
		$.util.objToStrutsFormData(resourceBean, "resourceBean", d) ;
	 } ;
		
	 tb.paramsResp=function(json){
	 json.data=json.resourceBeanList;
	 json.recordsFiltered=json.totalNum;
	 json.recordsTotal=json.totalNum;
	 };
	
     table = $("#roleTabulation").DataTable(tb);
	}
    /**
     * 
     */
	$(document).on('click','#resetRole',function(){
		$("#roleName").val("");
		$.select2.val("#pa_newPage_type","all",true);
		 table.draw(false);
	});
	
 	/**
	 * 新建
	 */
	$(document).on('click','#addRole',function(){
		var add=true;
		modifyAndAdd(add);
	});
 	/**
 	 * 修改
 	 */
 	$(document).on('click','#modifyRole',function(){
 		var arr = $.icheck.getChecked("testTr") ;
    	if(arr.length>1||arr.length==0){
    		$.layerAlert.alert({msg:"请选择一行，进行修改！"}) ;
    		//$('#delete').removeAttr("disabled") ;
    		return false ;
    	}
    	var ids;
    	$.each(arr, function(i, val){
    		var tr = $(val).parents("tr") ;
    		var row = table.row(tr) ;
    		var data = row.data() ;
    		var id = data.id ;
    		ids=id;
    	});
 		var modify=true;
 		modifyAndAdd(modify,ids);
 	});
 	function initSelect2(){
    	$.ajax({
    		    url: context + '/resource/queryResourceType.action',
	    		type:"POST",	
	    		data:{
	    		"dictionaryType":constantResourceType//字典项编码
	    		},
	    		dataType:"json",
	    		async:false,
	    		success:function(data){
	    			$.select2.addByList("#pa_newPage_type", data.dictionaryItemLst,"code","name");

	    		}
    	});
    }

 	function modifyAndAdd(flag,ids){
 		jQuery.extend($.common, { 
    		getParentObj:function(){
    			return flag ;
    		},
    		getParentObj1:function(){
    			return ids ;
    		}
    	});
 		var title="修改资源";
 		if(ids==undefined){
 			title="新建资源";
 		}
 		$.layerAlert.dialog({
 			content :context+'/resource/displayNewResource.action',
 			pageLoading : true,
 			width : "40%",
 			height : "44%",
 			title:title,
 			shadeClose : false,
 			success:function(layero, index){
 				//打开弹出框后回调方法
 				var win = $.layerAlert.frameWindow(index) ;		//获取弹出层的index
 				win.$.common.setTestObj2(index) ;		//把index传入弹出层  用于关闭层
 				win.$.common.setTestObj1(flag,ids);
 			},
 			end:function(){
 				
    			table.draw(true);
    		}
 		});
 	}
 
 	/**
 	 * 删除
 	 */
 	$(document).on('click','#deleteRole',function(){
 	    	var arr = $.icheck.getChecked("testTr") ;
 	    	if(arr.length==0){
 	    		$.layerAlert.alert({msg:"请至少选择一行！"}) ;
 	    		return false ;
 	    	}
 	    	
 	    	var ids = new Array() ;
 	    	$.each(arr, function(i, val){
 	    		var tr = $(val).parents("tr");
 	    		var row = table.row(tr) ;
 	    		var data = row.data() ;
 	    		var id = data.id ;
 	    		ids.push(id) ;
 	    	});
 	    	var dataMap = new Object();
	    	$(ids).each(function(i,ids){
	    		dataMap["idList[" + i + "].id"]=ids;
	    	});
 	    	$.layerAlert.confirm({
 	    		title:"提示",
 	    		msg:"删除后数据不可恢复，您确定要进行删除操作？",
 	    		yes:function(index, layero){
 	    	    	$.ajax({
 	    	    		url: context + '/resource/deleteResource.action',
 	    	    		type:"POST",	
 	    	    		dataType:"json",
 	    	    		data : dataMap,
 	    	    		success:function(data){
 	    	    			if(data.flag == "false"){
 	    	    				alertHint(data.msg,2)
 	    	    			}else{
 	    	    				alertHint(data.msg,1)
 	    	    				table.draw(true) ;
 	    	    			}
 	    	    		}
 	    	    	});
 	    		}
 	    	});
 	});
 	function alertHint(msg,icon){
 		$.layerAlert.alert({
 			msg:msg,
 			title:"提示",		//弹出框标题
 			width:'300px',
 			hight:'200px',
 			shade: [0.5,'black'],  //遮罩
 			icon:icon,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
 			shift:1,			//弹出时的动画效果  有0-6种
 		});
 	}
 	$(document).on('click','#queryRole',function(){
		
		table.draw(true);
    });
})(jQuery);