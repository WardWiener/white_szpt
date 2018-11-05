(function($){
	"use strict";
	var index = null;
	var riqiList=["b14","b22"];
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
			url:context +'/showWrit/chz.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				var bean = successData.chzBean;
				$.each($(".valCell"),function(){
					if(isRIQI($(this).attr("valName")) && bean[$(this).attr("valName")]!=null && bean[$(this).attr("valName")].length>10 ){
						$(this).text(bean[$(this).attr("valName")].substring(0,10));
					}else{
						$(this).text(bean[$(this).attr("valName")]);
					}
				})
				$.each($(".checkDiv"),function(){
					if(bean[$(this).attr("valName")] != ""){
						$(this).addClass("checked");
					}
				})
			}
		});
	}
	function isRIQI(data){
		return riqiList.indexOf(data)!=-1
	}
})(jQuery);