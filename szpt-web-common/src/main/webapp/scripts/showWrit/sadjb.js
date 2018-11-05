(function($){
	"use strict";
	var index = null;
	var riqiList=["a4","a20","a47"];
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
			url:context +'/showWrit/sadjb.action',
			type:'post',
			data:{writId : $("#writId").val()},
			dataType:'json',
			success:function(successData){
				var bean = successData.sadjBean;
				$.each($(".valCell"),function(){
					if(isRIQI($(this).attr("valName")) && bean[$(this).attr("valName")]!=null && bean[$(this).attr("valName")].length>10 ){
						$(this).text(bean[$(this).attr("valName")].substring(0,10));
					}else{
						$(this).text(bean[$(this).attr("valName")]);
					}
				})
				switch(bean["a9"]){
					case "A":
						$(".a9Check.check1").addClass("checked");
						break;
					case "B":
						$(".a9Check.check2").addClass("checked");
						break;
					case "C":
						$(".a9Check.check3").addClass("checked");
						break;
					case "D":
						$(".a9Check.check4").addClass("checked");
						break;
					case "E":
						$(".a9Check.check5").addClass("checked");
						break;
					case "F":
						$(".a9Check.check6").addClass("checked");
						break;
					case "G":
						$(".a9Check.check7").addClass("checked");
						break;
				}
				switch(bean["a18"]){
					case "1":
						$(".a18Check.check1").addClass("checked");
						break;
					case "2":
						$(".a18Check.check2").addClass("checked");
						break;
					case "3":
						$(".a18Check.check3").addClass("checked");
						break;
					case "4":
						$(".a18Check.check4").addClass("checked");
						break;
					case "5":
						$(".a18Check.check5").addClass("checked");
						break;
				}
			}
		});
	}
	function isRIQI(data){
		return riqiList.indexOf(data)!=-1
	}
})(jQuery);