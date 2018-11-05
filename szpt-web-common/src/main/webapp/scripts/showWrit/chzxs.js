(function($){
	"use strict";
	var index = null;
	var riqiList=["a11","a15","a18","a22","b10","b12","b14","c10","c12","c14"];
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
			url:context +'/showWrit/chzxs.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				var bean = successData.chzxsBean;
				$.each($(".valCell"),function(){
					if(isRIQI($(this).attr("valName")) && bean[$(this).attr("valName")]!=null && bean[$(this).attr("valName")].length>10 ){
						$(this).text(bean[$(this).attr("valName")].substring(0,10));
					}else{
						$(this).text(bean[$(this).attr("valName")]);
					}
				})
			}
		});
	}
	function isRIQI(data){
		return riqiList.indexOf(data)!=-1
	}
})(jQuery);