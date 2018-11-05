(function($){
	"use strict";
	var index = null;
	var riqiList=["a11","a15","a20","b13","b16","c13","c16"];
	$(document).ready(function() {
		$(document).on("click",".btn",function(){
			$(".btn").removeClass("btn-danger");
			$(".btn").addClass("btn-primary");
			$(this).removeClass("btn-primary");
			$(this).addClass("btn-danger");
			$(".writDiv").hide();
			$("#" + $(this).attr("divId")).show();
		});
		initWrit();
	});

	function initWrit(){
		$.ajax({
			url:context +'/showWrit/dbz.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				$.each($(".valCell"),function(){
					if(isRIQI($(this).attr("valName")) && successData.dbzBean[$(this).attr("valName")]!=null && successData.dbzBean[$(this).attr("valName")].length>10 ){
						$(this).text(successData.dbzBean[$(this).attr("valName")].substring(0,10));
					}else{
						$(this).text(successData.dbzBean[$(this).attr("valName")]);
					}
				})
			}
		});
	}
	function isRIQI(data){
		return riqiList.indexOf(data)!=-1
	}
})(jQuery);