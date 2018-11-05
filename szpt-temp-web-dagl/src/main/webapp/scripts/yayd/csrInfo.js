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
			url:context +'/yayd/showCsrDetail.action',
			type:'post',
			data:{dataId : $("#dataId").val()},
			dataType:'json',
			success:function(successData){
				$.each($(".personForXYR .valCell"),function(){
					$(this).text(successData.caseSupectRelationBean.criminalPerson[$(this).attr("valName")]);
				})
				$.each($(".xyrTable .valCell"),function(){
					$(this).text(successData.caseSupectRelationBean[$(this).attr("valName")]);
				})
				$.each(xyrTable.find(".valShow"),function(){
					if(successData.caseSupectRelationBean[$(this).attr("valName")] != "是"){
						$("tr[valTr=" + $(this).attr("valName") + "]" ).hide();
					}
				})
			}
		});
	}
	
})(jQuery);