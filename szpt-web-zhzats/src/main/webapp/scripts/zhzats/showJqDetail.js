$.showJqDetail = $.showJqDetail || {};
(function($){
	
	"use strict"
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var jqId = initData.jqId;
	
	$(document).ready(function(){
		queryJqDetailByCaseCode();
		jqDispose();
		
	});
	
	/**
	 * 查看警情详情
	 * @param ajCode 案件编号
	 */
	function queryJqDetailByCaseCode(){
		var obj = new Object();
		obj.queryStr = jqId;
		$.ajax({
			url:context +'/yanpan/jqBasicMessageQuery.action',
			data:obj,
			type:"post",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(data){
				$.validform.setFormTexts("#showJqDetail",data.resultMap.factJq);
				$.validform.setFormTexts("#showJqDetail",data.resultMap.jqAnalyze);
			}
		});
	}
	
	//警情处置记录
	function jqDispose(){
		var obj = new Object();
		obj.queryStr=jqId;
		$.ajax({
			url:context +'/yanpan/jqDisposeRecordQuery.action',
			data:obj,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(data){
				jqRecord(data.resultMap.list);
			}
		});
	}
	
	function jqRecord(list){
		for(var i=0;i<list.size();i++){
			if(i%2==0){
				$("#recordAppend").append("<li class='odd'>");
			}else{
				$("#recordAppend").append("<li class='even'>");	
			}
			$("#recordAppend").append("<span class='icon-red-dot'></span>"
					 +"<div class='time-box'><p>"+data+"</p></div>"
					+"<div class='con-box'><span class='arrow'></span>"+data+"</div></li>");
		}
		
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.showJqDetail, { 
		
	});	
})(jQuery);