
$(document).ready(function() {	
	$(".c-top-right ul li").each(function(){
		if($.util.isBlank($(this).html())){
			$(this).remove();
		}
	})

	$(document).on("click" , "#solrSearch", function(e){
		search()
	});
	
	$(document).on("keydown" , "#indexInput", function(e){
		if(e.keyCode==13){
			search()
		}
	})
	
	function search(){
		if(!$("#indexInput").val() || $("#indexInput").val()=="警情、事件、案件、高危人、场所……"){
			return;
		}else{
			window.top.location.href = context + "/fullsearch/showFullSearchIndexPage.action?search="+$("#indexInput").val();
		}
	}
	
	//设置指令菜单
	if($(".instructionMenu").parent("a").length > 1){
		$(".instructionMenu").parent("a").eq(1).hide();
	}
});
