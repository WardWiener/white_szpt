(function($){
	"use strict";
	$(document).ready(function() {
		initData();
	});
	
	function initData(){
		$.ajax({
			url:context +'/yayd/showCoDetail.action',
			type:'post',
			data:{dataId : $("#dataId").val()},
			dataType:'json',
			success:function(successData){
				$.each($(".valCell"),function(){
					$(this).text(successData.criminalObjectBean[$(this).attr("valName")]);
				})
			}
		});
	}
})(jQuery);