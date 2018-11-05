(function($){
	"use strict";
	$(document).ready(function(){
		init();
	});
	
	
	$(document).on("click" , "#search", function(e){
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
	
	//测试指令推送
//	$(document).on("click" , "#search", function(e){
//		$.ajax({
//			url:context +'/loginHomePage/createNewIn.action',
//			type:"post",
//			data:{},
//			dataType:"json",
//			success:function(successDate){
//			}
//		})
//	});
	
	//点击更多菜单
	$(document).on("click" , "#addOtherMenu", function(e){
		
		window.top.$.layerAlert.dialog({
			content : context +  '/menu/showmenuTree.action',
			pageLoading : true,
			title:"常用功能设置",
			width : "600px",
			height : "700px",
			btn:["保存","关闭"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.save();
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); 
				}
			},
			shadeClose : false,
			success:function(layero, index){
			},
			initData:{
			},
			end:function(){
				showMenu();
			}
		});
	})
	
	//点击更多指令
	$(document).on("click" , "#moreInstruct", function(e){
		showInstructLst();
	})
	
	//加载指令列表
	function showInstructLst(){
		window.top.$.layerAlert.dialog({
			content : context +  '/instruct/showInstructLst.action',
			pageLoading : true,
			title:"查看指令列表",
			width : "1000px",
			height : "650px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); 
				}
			},
			shadeClose : false,
			success:function(layero, index){
			},
			initData:{
				currentUserName:currentUserName
			},
			end:function(){
			}
		});
	}
	
	//页面初始化
	function init(){
		initInstruct();
		showMenu();
	}
	
	//初始化指令列表
	function initInstruct(){
		$.ajax({
			url:context +'/instruct/findInstruction.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				showInstruct(successDate.resultMap.result);
			}
		})
	}
	
	
	function showInstruct(data){
		$("#instructLst").empty();
		
		if(data.length == 0){
			$("#instructLst").append('无待签收指令。')
			return;
		}
		if(null != data && data.length > 0){
			var instructMedol = $("#oneInstruct");
			for(var i in data){
				var instruct = instructMedol.clone(true);
				instruct.show();
				instruct.insertBefore($("#instructLst"));
				$(instruct).attr("valId",data[i].id);
				$(instruct).find(".content").text(data[i].instructionBean.content);
				$(instruct).find(".time").text($.date.timeToStr(data[i].instructionBean.createTime,"yyyy-MM-dd HH:mm"));
			}
		}
	}
	
	//点击每条指令详情事件
	$(document).on("click" , ".showInstructDetails", function(e){
		showInstructDetils($(this).attr("valId"));
	})
	
	//显示指令详情
	function showInstructDetils(id){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/instruction/showFeedbackInstructionPage.action',
			pageLoading : true,
			title:"指令反馈",
			width : "650px",
			height : "700px",
			btn:["确定"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); 
				}
			},
    		success:function(layero, index){
    			
    		},
    		initData:{
    			isDetail:true,
    			id : id,
    			currentUserName:currentUserName
    		},
    		end:function(){
    		}
		});
	}
	
	function showMenu(){
		$.ajax({
			url:context +'/loginHomePage/findAllUserUsedModuleUrl.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				userCustomizedMenu(successDate.resultMap.result);
			}
		})
	}
	
	function userCustomizedMenu(data){
		$("#templateMenuLst").empty();
		if(null != data && data.length > 0){
			var templateMenuMedol = $("#templateMenuUl #templateMenu");
			for(var i in data){
				var templateMenu = templateMenuMedol.clone(true);
				templateMenu.removeAttr("id");
				templateMenu.prependTo($("#templateMenuLst"));
				$(templateMenu).attr("valUrl",data[i].url);
				$(templateMenu).find(".name").text(data[i].name);
			}
		}
	}
	
	$(document).on("click" , ".modul", function(e){
		window.top.location.href = context + $(this).attr("valUrl");
	});
	
	
})(jQuery);
