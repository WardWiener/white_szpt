$.zaxq = $.zaxq || {};
(function($){
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var pageIndex = frameData.index ;
	var caseId = initData.lockerId;
	var zaCondition=initData.condition;
	
	var zazllxCode
	$(document).ready(function(){
		init();
		//showClickModule(null);
	});
	
	function getCaseId(){
		return caseId;
	}
	
	//点击案件基本资料
	$(document).on("click", "#basicA", function(){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$(this).addClass("list-group-item-info");
		$.basicMessage.onloadBasicData();
		showClickModule("basicDiv");
		//加载案件基本资料
		$('.zlsc').hide();
	
	})
	//点击专案涉案人员
	$(document).on("click", "#personA", function(){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$(this).addClass("list-group-item-info");
		$.involvedPerson.initInvolvedPersonTable();
		showClickModule("personDiv");
		
	})
	//点击涉案人员关系
	$(document).on("click", "#relationshipA", function(){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$(this).addClass("list-group-item-info");
		$.involvedPersonConcern.initInvolvedPersonConcern();
		showClickModule("relationshipDiv");
	
	})
	//点击专案进展报告
	$(document).on("click", "#reportA", function(){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$(this).addClass("list-group-item-info");
		$.projectReport.initRoportTable();
		showClickModule("reportDiv");
		//加载专案进展报告
	})
	//点击办案手段统计
	$(document).on("click", "#methodA", function(){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$('.dataA').attr("class","dataA list-group-item");
		$(this).addClass("list-group-item-info");
		showClickModule("methodDiv");
		$("#bottomDiv").hide();
		$.zaMethod.init()//加载办案手段统计
	})
	
	//点击专案资料
	//点击专案资料
	$(document).on("click", ".dataA", function(){
		clickDataA(this);
	})
	function clickDataA(dataA){
		$('#zaInfo .list-group-item').attr("class","list-group-item");
		$('.dataA').attr("class","dataA list-group-item");
		$(dataA).addClass("list-group-item-info");
		showClickModule("dataDiv");
		$.zaData.showTitle($(dataA).text());
		zazllxCode = $(dataA).attr("valCode");
		$.zaData.datacCreatTable();
	}
	
	function showClickModule(moduleDiv){
		$.each( $(".subViewDiv"), function(e,m){
			$(m).hide();
		} ); 
		$("#"+moduleDiv).show();
		$('.zlsc').show();
	}
	
	function showDataModule(){
		$("#bottomDiv").show();
		$.each( $(".subViewDiv"), function(e,m){
			$(m).hide();
		} ); 
		$("#dataDiv").show();
	}
	//初始化方法
	function  init(){
		$.ajax({
			url:context +'/zazlwh/findAllZazllx.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				initZazllx(successDate.resultMap.result);
			}
		})
		if(zaCondition=='基本信息'){
			$('.list-group-item').attr("class","list-group-item");
			$('#basicA').addClass("list-group-item-info");
			$.basicMessage.onloadBasicData();
			showClickModule("basicDiv");
			//$('.zlsc').hide();
		}else if(zaCondition=='涉案人员'){
			$('.list-group-item').attr("class","list-group-item");
			$('#personA').addClass("list-group-item-info");
			$.involvedPerson.initInvolvedPersonTable();
			showClickModule("personDiv");
		}else if(zaCondition=='涉案人员关系'){
			$('.list-group-item').attr("class","list-group-item");
			$('#relationshipA').addClass("list-group-item-info");
			$.involvedPersonConcern.initInvolvedPersonConcern();
			showClickModule("relationshipDiv");
		}
	}
	
	//查询所有的专案资料类型
	function initZazllx(zazllx){
		$("#zazllxDiv").empty();
		for(var i in zazllx){
			$("#zazllxDiv").append('<a href="#"  valCode="'+zazllx[i].code+'" class="dataA list-group-item">'+zazllx[i].name+'</a>')
		}
	}
	
	/**
	 * 资料上传
	 */
	$(document).on("click", "#fileUploadBtn", function(){
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/zazlwh/updateZaData.action',
			pageLoading : true,
			title:"资料上传",
			width : "580px",
			height : "308px",
			btn:["返回"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index);
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				"zazllxCode" : zazllxCode,
				"zaId" : caseId
			},
			end:function(){
				if(zazllxCode){
					$.each($(".dataA"), function(e,m){
						if($(this).attr("valCode") == zazllxCode){
							clickDataA(this);
						}
					} );
				}
			}
		});
			
	})
	
	
	/**
	 * 留言反馈
	 */
	$(document).on("click", "#feedbackBtn", function(){
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/zalyfk/showZaFeedbackUserLayer.action',
			pageLoading : true,
			title:"留言反馈",
			width : "900px",
			height : "630px",
			btn:["返回"],
			callBacks:{
				btn1:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index);
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				"caseId" : caseId
			}
			
		});
			
	})
	
	//返回
	$(document).on("click", "#goBackBtn", function(){
		$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); 
	})
	function getZazllxCode(){
		return zazllxCode;
	}
	
	jQuery.extend($.zaxq , { 
		showDataModule:showDataModule,
		getCaseId:getCaseId,
		getZazllxCode:getZazllxCode,
		pageIndex:pageIndex
	});	

})(jQuery);
