(function($) {
	"use strict";
	$(document).ready(function(){
	});
	var table;
	$("#type").val("table");
	var tb = $.uiSettings.getOTableSettings() ;
	tb.ajax.url = context+"/authority/queryAllAuthority.action";
	tb.columnDefs = [
	     { 
	    	 "targets": 0,
	    	 "width": "50px",
	    	 "title": "选择",
	    	 "className":"table-checkbox",
	    	 "data": "" ,
	    	 "render": function ( data, type, full, meta ) {		
	    		 var a = '<input type="checkbox" name="testTr" class="icheckbox" />' ;
	    		 return a;
	    	 }
	     },
	     { 
	    	 "targets": 1,
	    	 "width": "",
	    	 "title": "权限名称",
	    	 "data": "authorityName" ,
	    	 "render": function ( data, type, full, meta ) {								  
	    		 return data;
	    	 }
	     },
	     { 
	    	 "targets": 2,
	    	 "width": "",
	    	 "title": "权限编码",
	    	 "data": "authorityCode" ,
	    	 "render": function ( data, type, full, meta ) {		        	
	    		 return data;
	    	 }
	     }
	     
	     ] ;
	tb.paging = true ; 
    tb.lengthMenu = [ 10 ];
	tb.lengthChange = false;
	tb.searching = false ;
	tb.ordering = false ;
	tb.paramsReq = function(d, pagerReq){
		var authorityBean = new Object();
		authorityBean.authorityName=$("#powerName").val();
		authorityBean.id=$("#powerId").val();
		authorityBean.authorityCode=$("#authorityCode").val();
		//object转化成dataMap传到d里
		$.util.objToStrutsFormData(authorityBean, "authorityBean", d);
		var type;
		type=$("#type").val();
		$.util.objToStrutsFormData(type, "type", d);
	} ;
	
	tb.paramsResp=function(json){
	 json.data=json.authorityBeanList;
		$('#queryP').removeAttr("disabled");
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
			    		},
			    		
			    	});
					$.layerAlert.dialog({
						content :context+'/authority/displayAuthority.action',
						pageLoading : true,
						width : "60%",
						height : "50%",
						title:"查看权限",
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
     table = $("#powerTable").DataTable(tb);
	
	/**查询*/
	$(document).on('click','#queryP',function(){
		$("#type").val("table");
		table.draw(true);
		$("#queryP").attr("disabled","true");
	});
	/**重置*/
	$(document).on('click','#resetting',function(){
		$("#type").val("table");
		$("#powerName").val("");
		$("#authorityCode").val("");
		table.draw(true);
	});
	
	/**新建权限*/
	$(document).on('click','#addPower',function(){
		var add="add"
		newAndModify(add);
			
	});
	/**修改权限*/
	$(document).on('click','#modifyPower',function(){
		var modify="modify";
		var arr = $.icheck.getChecked("testTr") ;
    	if(arr.length>1||arr.length==0){
    		$.layerAlert.alert({msg:"请选择一行，进行修改!"}) 
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
    	
		newAndModify(modify,ids);
	});
	
	function newAndModify(flag,ids){
		jQuery.extend($.common, { 
    		getParentObj:function(){
    			return flag ;
    		},
    		getParentObj1:function(){
    			return ids ;
    		}
    	});
		var title="修改权限";
 		if(ids==undefined){
 			title="新建权限";
 		}
		$.layerAlert.dialog({
			content : context +'/authority/showNewAuthority.action',
			pageLoading : true,
			width : "60%",
			height : "85%",
			title:title,
			shadeClose : false,
			success:function(layero, index){
				//打开弹出框后回调方法
				var win = $.layerAlert.frameWindow(index) ;		//获取弹出层的index
				win.$.common.setTestObj2(index) ;		//把index传入弹出层  用于关闭层
				win.$.common.addAndModify(flag,ids) ;
				
			},
			callBacks : {		// 按钮的回调函数:yes默认代表btn数组中第一个名称的按钮并且点击会默认关闭层
				btn1:function(index, layero){
    				layer.close(index);	
			    }
			 },
			end:function(){
				table.draw(true);
			}
		});
	}
	/**删除权限*/
	$(document).on('click','#deletePower',function(){
		var arr = $.icheck.getChecked("testTr") ;
    	if(arr.length==0){
    		$.layerAlert.alert({msg:"请至少选择一行，进行删除"}) 
    		$('#deletePower').removeAttr("disabled") ;
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
	    	    		url: context + '/authority/deleteAuthority.action',
	    	    		type:"POST",	
	    	    		dataType:"json",
	    	    		data : dataMap,
	    	    		success:function(data){
	    	    			if(data.flag == "false"){
	    	    				alertHint(data.msg,5);
	    	    				return;
	    	    			}else{
	    	    				alertHint("删除成功",6);
	    	    				table.draw(true);
	    	    				$("#type").val("table");
	    	    			}
	    	    		}
	    	    	});
	    		}
	    	});
	});
	
 	/**
	 * 查询
	 */
	$(document).on('click','#queryRole',function(){
		table.draw(true,'#queryRole');
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