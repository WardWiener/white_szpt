(function($) {
	"use strict";
	
	var table 
	var number;		//当前弹出层

	$(document).ready(function() {
		$("#powerTable_info").hide();
		if(!+"\v1"){
			var win = $.util.rootWindow() ;		//找父级页面
			var	id = win.$.common.getParent() ;//取父级页面数据
			$.ajax({
				url: context + '/authority/findAuthorityById.action',
				type:"POST",	
				dataType:"json",
				data : {
					"id":id
				},
				success:function(data){
					var d=data.authorityBean;
					intiUnitTree(d.authorityZTreeBean);
					$("#powerName").text(d.authorityName);
					$("#powerCategory").text(d.authorityCode);
					$("#powerId").val(d.id);
					queryPowerDrawTable();
				}
			});
		}else{
			jQuery.extend($.common, { 
				setTestObj2:function(index,id){
					number=index;	//从父页面获取当前弹出层   用于关闭
					$.ajax({
						url: context + '/authority/findAuthorityById.action',
						type:"POST",	
						dataType:"json",
						data : {
							"id":id
						},
						success:function(data){
							var d=data.authorityBean;
							$("#powerName").text(d.authorityName);
							$("#powerCategory").text(d.authorityCode==null?"":d.authorityCode);
							$("#powerId").val(d.id);
							queryPowerDrawTable();
						}
					});
				},
			});	
		}
		
		
	});
	var table 
	function queryPowerDrawTable(){
	var tb = $.uiSettings.getOTableSettings();
	tb.ajax.url = context+"/authority/findAuthorityById.action";
	tb.columnDefs = [
	     
	     { 
	    	 "targets": 0,
	    	 "width": "",
	    	 "title": "<font color='#000000'>权限属性类型</font>",
	    	 "data": "resourceName" ,
	    	 "render": function ( data, type, full, meta ) {								  
	    		 return "<font color='#000000'>"+data+"</font>";
	    	 }
	     },
	     { 
	    	 "targets": 1,
	    	 "width": "",
	    	 "title": "<font color='#000000'>资源链接</font>",
	    	 "data": "resourceUrl" ,
	    	 "render": function ( data, type, full, meta ) {
	    		 var d="<div class='t-over'  tooltipPos='bottom' mouseTrack='true'" +
	 	 		" my='center bottom-20' at='center top'" +
	 	 		" title='"+data+"'><font color='#000000'>"+data+"</font></div>";
	    		 return d;
	    	 }
	     }
	     ] ;
	tb.paging = false ; 
	tb.lengthChange = false;
	tb.searching = false ;
	tb.ordering = false ;
	tb.info=false;
	tb.paramsReq = function(d, pagerReq){
		var id=$("#powerId").val();
			$.util.objToStrutsFormData(id, "id", d);
	} ;
	tb.paramsResp=function(json){
		json.data=json.authorityBean.resourceLibBeans;
	};
	console.log(tb);
	 table = $("#powerTable").DataTable(tb);
	}
	
})(jQuery);