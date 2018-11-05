(function($) {
    "use strict";
	var table;
	$(document).ready(function() {
		initTable();
		initStatus();
	});
	var number;		//当前弹出层
	jQuery.extend($.common, { 
		setTestObj2:function(index){
			number=index;	//从父页面获取当前弹出层   用于关闭
		},
	});	
	function initStatus(){
		$.ajax({
			url : context + "/role/findDictionaryItemsByType.action",
			type : "POST",
			data :{
				"dictionaryType":constantStauts
			},
			dataType : "json",
			success : function(data) {
				$.select2.addByList("#statu", data.dictionaryItemLst, "code", "name");
			},
		});
	}
	/*
	 * 生成表格
	 */
	function resetRole(){
		$.select2.val("#statu","all",true);
		$("#roleName").val("");
		table.draw(true);
	}
	function initTable(){
	var tb = $.uiSettings.getOTableSettings() ;
	tb.ajax.url = context+"/role/queryRole.action";
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
			"title": "角色名称",
			"data": "roleName" ,
			"render": function ( data, type, full, meta ) {								  
				      return data;
			}
		},
		{ 
			"targets": 2,
			"width": "20%",
			"title": "状态",
			"data": "status" ,
			"render": function ( data, type, full, meta ) {		        	
				      return data;
			}
		},
		{ 
			"targets": 3,
			"width": "40%",
			"title": "已配置权限",
			"data": "" ,
			"className":"table-edit",
			"render": function ( data, type, full, meta ) {	
				var d="";
				var count=0;
				   $(full.authorityBeanBeans).each(function(index,itm){
					   if(count==0){
						   d+=itm.authorityName;count++;
					   }else
						   { 
						      d+=","+itm.authorityName;
						      count++
						   }
				   })
				 var dataTips="<div class='t-over' style='width:70%;' tooltipPos='bottom' mouseTrack='true'" +
			 	 		" my='center bottom-20' at='center top'" +
			 	 		" title='"+d+"'>"+d+"</div>";
				return dataTips;
			}
		}
	                 
	] ;
	 tb.paging = true ; 
	 tb.lengthMenu = [ 10 ];
	 tb.lengthChange = false;
	 tb.searching = false ;
	 tb.ordering = false ;
	 tb.paramsReq = function(d, pagerReq){
		var roleBean = new Object();
		roleBean.roleName=$("#roleName").val();
		if($("#statu").val() == "all"){
			roleBean.status="";
		}else{
			roleBean.status=$("#statu").val();
		}
		//object转化成dataMap传到d里
		$.util.objToStrutsFormData(roleBean, "roleBean", d) ;
	 } ;	
	 tb.paramsResp=function(json){
	 json.data=json.roleBeanList;
	 json.recordsFiltered=json.totalNum;
	 json.recordsTotal=json.totalNum;
	 };
	 tb.rowCallback = function( row, data, index ) {
		$($(row).children("td")).each(function(i, val){
			if(i!=0){
				$(val).on("dblclick", function(){		//为每行tr绑定双击时间
					var tr = $(this).parents("tr");
					var row=table.row(tr);	//行对象
					var data=row.data();	//行所有数据
					var id=data.id;
					jQuery.extend($.common, { 
			    		getParent:function(){
			    			return id ;
			    		}
			    	});
					$.layerAlert.dialog({
						content :context+'/role/seeRole.action',
						pageLoading : true,
						width : "56%",
						height : "45%",
						title:"查看角色",
						shadeClose : false,
						btn:"关闭",
						success:function(layero, index){
							//打开弹出框后回调方法
							var win = $.layerAlert.frameWindow(index) ;		//获取弹出层的index
							win.$.common.setTestObj2(index,id);		//把index传入弹出层  用于关闭层
						},
						btn1:function(index, layero){
					 		layer.close(index);	//关闭层
					    },
					});
				})
			}
		});
     };
     table = $("#roleTabulation").DataTable(tb);
	}
	$(document).on('click','#resetRole',function(){
		resetRole();
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
 	
 	function modifyAndAdd(flag,ids){
 		jQuery.extend($.common, { 
    		getParentObj:function(){
    			return flag ;
    		},
    		getParentObj1:function(){
    			return ids ;
    		}
    	});
 		var title="修改角色";
 		if(ids==undefined){
 			title="新建角色";
 		}
 		$.layerAlert.dialog({
 			content :context+'/role/displayNewRole.action',
 			pageLoading : true,
 			width : "60%",
 			height : "95%",
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
 	 * 启用
 	 */
 	$(document).on('click','#startRole',function(){
 		var arr = $.icheck.getChecked("testTr") ;
    	if(arr.length==0){
    		$.layerAlert.alert({msg:"请至少选择一行！"}) ;
    		return false ;
    	}
    	var ids = new Array() ;
    	$.each(arr, function(i, val){
    		var tr = $(val).parents("tr") ;
    		var row = table.row(tr) ;
    		var data = row.data() ;
    		var id = data.id ;
    		ids.push(id) ;
    	});
    	var dataMap = new Object();
    	$(ids).each(function(i,ids){
    		dataMap["idList[" + i + "].id"]=ids;
    	});
    	dataMap["status"] = constantStauts_qy;
 		$.ajax({
    		url: context + '/role/modifyRoleStatus.action',
    		type:"POST",	
    		dataType:"json",
    		data : dataMap,
    		success:function(data){
    			alertHint("启用成功",6);
    			table.draw(true);
    		}
    	});
 		
 	});
 	/**
 	 * 停用
 	 */
 	$(document).on('click','#stopRole',function(){
 		var arr = $.icheck.getChecked("testTr") ;
	    	if(arr.length==0){
	    		$.layerAlert.alert({msg:"请至少选择一行！"}) ;
	    		return false ;
	    	}
	    	var ids = new Array() ;
	    	$.each(arr, function(i, val){
	    		var tr = $(val).parents("tr") ;
	    		var row = table.row(tr) ;
	    		var data = row.data() ;
	    		var id = data.id ;
	    		ids.push(id) ;
	    	});
	    	var dataMap = new Object();
	    	$(ids).each(function(i,ids){
	    		dataMap["idList[" + i + "].id"]=ids;
	    	});
	    	dataMap["status"] = constantStauts_ty;
	    	$.layerAlert.confirm({
 	    		msg:"停用角色后，使用该用户的角色将失效，您确定停用吗？",
 	    		title:"提示",
 	    		yes:function(index, layero){
 	    			$.ajax({
 	   	    		url: context + '/role/modifyRoleStatus.action',
 	   	    		type:"POST",	
 	   	    		dataType:"json",
 	   	    		data : dataMap,
 	   	    		success:function(data){
 	   	    			alertHint("停用成功",6);	    			
 	   	    			table.draw(true);
 	   	    		}
 	   	    	});
 	    		}
 	    	});
 		
 	});
 	/**
 	 * 删除
 	 */
 	$(document).on('click','#deleteRole',function(){
 	    	var arr = $.icheck.getChecked("testTr") ;
 	    	if(arr.length==0){
 	    		alertHint("请至少选择一行！") ; ;
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
 	    		msg:"删除后数据不可恢复，您确定要进行删除操作？",
 	    		title:"提示",
 	    		yes:function(index, layero){
 	    	    	$.ajax({
 	    	    		url: context + '/role/deleteRole.action',
 	    	    		type:"POST",	
 	    	    		dataType:"json",
 	    	    		data : dataMap,
 	    	    		success:function(data){
 	    	    			if(data.flag == "false"){
 	    	    				alertHint(data.msg,5);
 	    	    			}else{
 	    	    				alertHint(data.msg,6);
 	    	    				
 	    	    			}
 	    	    			table.draw(true) ;
 	    	    		}
 	    	    	});
 	    		}
 	    	});

 	});
 	
 	/**
	 * 查询
	 */
	$(document).on('click','#queryRole',function(){
		
				table.draw(true);
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
})(jQuery);