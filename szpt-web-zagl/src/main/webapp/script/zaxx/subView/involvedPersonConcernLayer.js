$.involvedPersonConcern = $.involvedPersonConcern || {};
(function($){
	"use strict";
	var i=1;
	var table;
	$(document).ready(function(){		
		//initInvolvedPersonTable();
		
	})	
		 
		 /**
		 * 修改涉案人员信息--弹窗按钮
		 */
		$(document).on('click',"#involvedPersonWHBtn",function(){
			showUpdataInvolvedPerson();
		});
	
	
	/**
	 * 我的专案--人员修改页面弹窗
	 */
	function showUpdataInvolvedPerson(){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/wdza/showUpdataInvolvedPerson.action',
			pageLoading : true,
			title:"我的专案",
			width : "60%",
			height : "80%",
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				lockerId : $.zaxq.getCaseId()  //专案id
				
			},
			end:function(){
				table.draw(true);
			}
		});
		//$.util.topWindow().location.href = context +  '/wdza/showUpdataInvolvedPerson.action?queryStr='+caseId;
	}
	
	
	/**
	 * 加载涉案人员关系
	 */	
	function initInvolvedPersonConcern(){
//		 var data={
//	          		caseId:$.zaxq.getCaseId()
//				}
//		var queryStr = $.util.toJSONString(data);
//		$.ajax({
//			url:context +'/zawh/findPersonConcern.action',
//			type:"post",
//			contentType: "application/json; charset=utf-8",
//			dataType:"json",
//			data:queryStr,
//			success:function(successData){
//				var personList=successData.resultMap.result;
//				$("#personConcernBtn").empty();
//				var b="";
// 				for(var i=0;i<personList.length;i++){
// 					b+='<li><a href="#">'+personList[i].name+'</a><p class="color-gray text-center">'+personList[i].nick+'</p></li>';
//				}
// 				$('#personConcernBtn').append(b);
//			}		
//		
//		});
	
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.involvedPersonConcern, { 
		initInvolvedPersonConcern : initInvolvedPersonConcern
	});	
	
})(jQuery);