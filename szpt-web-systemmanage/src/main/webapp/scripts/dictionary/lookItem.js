;(function($){
var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
var pageIndex = frameData.index ;//当前弹窗index	

$(document).ready(function() {	
	var itemId ;
	"use strict";
	var searchingTypeId ;
	var itemName;
	var itemCode;
	var itemDescription;
	var flag;
	$(document).ready(function() {
		var p$ = $.util.topWindow().$ ;
		var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
		var index = frameData.index ;
		var initData = frameData.initData ;
		itemId = initData.itemId;
		//编辑
		$.ajax({
			url:context+'/dictionaryType/lookOverItem.action',
			type:"POST",
			data:{"itemId":itemId},
			dataType:"json",
			success:function(data){
				var item = data.dicItemBean;
				if(item != null){
					$("#itemName").html(item.name);
					$("#itemCode").html(item.code);
					$("#itemdescription").html(item.description);
					$("#itemStatus").html(item.state)
				}
			}
		});
		jQuery.extend($.common, { 
			save:function(){
				saveObj();
			},
			update:function(){
				updateObj();
			}
		});	
		
    });
	
})
})(jQuery);	