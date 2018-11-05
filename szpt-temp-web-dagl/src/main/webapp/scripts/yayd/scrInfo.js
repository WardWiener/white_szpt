(function($){
	"use strict";
	
	$(document).ready(function() {
		$(document).on("click",".btn",function(){
			$(".btn").removeClass("btn-danger");
			$(".btn").addClass("btn-primary");
			$(this).removeClass("btn-primary");
			$(this).addClass("btn-danger");
			$(".dataDiv").hide();
			$("#" + $(this).attr("divId")).show();
		});
		initData();
	});

	function initData(){
		$.ajax({
			url:context +'/yayd/showScrDetail.action',
			type:'post',
			data:{dataId : $("#dataId").val()},
			dataType:'json',
			success:function(successData){
				$.each($(".personInfo .valCell"),function(){
					$(this).text(successData.sufferCaseRelationBean.criminalPerson[$(this).attr("valName")]);
				})
				$.each($(".bjrTable .valCell"),function(){
					$(this).text(successData.sufferCaseRelationBean[$(this).attr("valName")]);
				})
			}
		});
	}
	
})(jQuery);