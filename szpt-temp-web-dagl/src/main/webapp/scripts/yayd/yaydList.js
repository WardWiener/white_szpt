(function($){
	"use strict";
	
	$(document).ready(function(){
		initOrRefreshTable("diyTable1") ;
		
		//如果地址里有caseCode那么默认就查询一次
		if(!$.util.isBlank(caseCode)){
			$("#searchText").val(caseCode);
			searchCase();
		}
	});
	
	function initOrRefreshTable(domId){
	}
	
	$(document).on("click", "#searchBtn", function(){
		searchCase();
	});
	$(document).on("click", "#mycase", function(){
		searchMyCase();
	});
	function searchCase(){
		var data = {
				"searchText" : $("#searchText").val(),
			}
			var dataMap = $.util.toJSONString(data);
			$.ajax({
				url:context +'/yayd/findYayd.action',
				type:"post",
				data : dataMap,
				contentType : "application/json; charset=utf-8",
				dataType:"json",
				success:function(successDate){
					initTable(successDate.resultMap.result);
					
					$("#tableTotal").text(successDate.resultMap.result.length);
				}
			})
	}	
	function searchMyCase(){
			$.ajax({
				url:context +'/yayd/findMycase.action',
				type:"post",
				dataType:"json",
				success:function(successDate){
                    if(successDate.resultMap.result==null || (successDate.resultMap.result!=null&&successDate.resultMap.result.length==null)){
                        $("#tableTotal").text(0) ;
                    }else{
                        $("#tableTotal").text(successDate.resultMap.result.length);
                    }

                    initTable(successDate.resultMap.result);
				}
			})
	}
	function initTable(data){
		$("#diyTable1-tbody").empty();
		var li = "<li id='model' style='display:none' ></li>";
		$("#diyTable1-tbody").append(li);
		for(var i in data){
			var objTableTemplate = $("#caseInfo");
			var objTable = objTableTemplate.clone(true);
			objTable.show();
			objTable.insertBefore($("#model"));
			$.each(objTable.find(".valCell"),function(){
				$(this).text(data[i][$(this).attr("valName")]);
			})
		}
	}
	
	$(document).on("click", "li", function(){
		var id = $(this).find(".caseCode").text();
		var url = window.location.href.split("?")[0];
		window.top.location.href = context + "/yayd/showYaydDetailPage.action?clickOrder=0&&clickListOrder=0&&id=" + id ;//+ "&&yaydListUrl=" + url + "?caseCode=" + id;
		sessionStorage.setItem("yaydDetailBackUrl", url + "?caseCode=" + id);
	});
})(jQuery);	