(function($){
	"use strict";
	var index = null;
	var riqiList=["a9","a13","a14","a16","a17","a22","a24",'b11','b16','b17','b18','c11','c16','c17','c18','d11','d16','d17','d18'];
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
			url:context +'/showWrit/bgjyqx.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				var bean = successData.bgjyqxBean;
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