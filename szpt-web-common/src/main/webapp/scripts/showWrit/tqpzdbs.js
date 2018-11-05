(function($){
	"use strict";
	var index = null;
	var riqiList=[];
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
			url:context +'/showWrit/tqpzdbs.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				var bean = successData.tqpzdbsBean;
				$.each($(".valCell"),function(){
					if(isRIQI($(this).attr("valName")) && bean[$(this).attr("valName")]!=null && bean[$(this).attr("valName")].length>10 ){
						$(this).text(bean[$(this).attr("valName")].substring(0,10));
					}else{
						if($(this).attr("valName") == "a7"){
							var text = bean[$(this).attr("valName")];
							var arr = text.split("  ");
							var str = "";
							for(var i in arr){
								if(arr[i] != ""){
									str += "<span>  " + arr[i] + "</span><br/>";
								}
							}
							$(this).html(str);
						}else{
							$(this).text(bean[$(this).attr("valName")]);
						}
					}
				})
			}
		});
	}
	function isRIQI(data){
		return riqiList.indexOf(data)!=-1
	}
})(jQuery);