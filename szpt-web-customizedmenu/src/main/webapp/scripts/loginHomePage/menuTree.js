$.common =  $.common || {};
(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var zTreeObj ;
	  $(document).ready(function () {
		  init();
      });
	  
	 var setting = {
	            data: {
	                simpleData: {
	                    enable: true
	                }
	            },
	            check: {enable: true},
	            
	            callback: {
	        		beforeCheck: zTreeBeforeCheck
	        	}
	        };
	 
	 function zTreeBeforeCheck( treeId, treeNode){
		if(treeNode.isclick){
			return false;
		}else{
			return true;
		}
	 }
	   function init (){
	    	$.ajax({
	 			url:context +'/menu/findAllSystemMenu.action',
	 			type:"post",
	 			contentType: "application/json; charset=utf-8",
	 			success:function(successDate){
	 				zTreeObj = $.fn.zTree.init($("#zTree"), setting, successDate.resultMap.result);
	 			}
	 		})
	     }  
	   
	   function getseletTreeNodes(){
		   zTreeObj = $.fn.zTree.getZTreeObj("zTree");
		   var nodes = zTreeObj.getCheckedNodes();
		   console.log(nodes);
		   var ids = [];
		   for(var i in nodes){
			   if(!nodes[i].isclick){
				   ids.push(nodes[i].id);
			   }
		   }
		   console.log(ids);
		   return ids;
	   }
	     
	     function save(){
	    	var data = {
	    		"ids":getseletTreeNodes()
	 		}
	    	var dataMap = $.util.toJSONString(data);
	    	 $.ajax({
		 			url:context +'/menu/saveCustomizedMenu.action',
		 			type:"post",
		 			data :dataMap,
		 			contentType: "application/json; charset=utf-8",
		 			success:function(successDate){
		 				window.top.$.layerAlert.alert({msg:"保存成功!",
							title:"提示",
							icon:1,
							yes:function(index, layero){
								window.top.$.layerAlert.closeWithLoading(pageIndex); 
							}
						});
		 			}
		 		})
	     }
	     jQuery.extend($.common, { 
	    	 save:save
	 	});	
})(jQuery);
