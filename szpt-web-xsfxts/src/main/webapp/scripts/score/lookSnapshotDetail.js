$.lookSnapshotDetail = $.lookSnapshotDetail || {};
(function($){
	"use strict";
	
	$(document).ready(function() {	
		querySnapshotById();
	});
	
	/**
	 * 根据id查询快照详情
	 * 
	 */
	function querySnapshotById(){
		var data = {
			"sanpshotId" : sanpshotId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/snapshot/querySnapshotById.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var csrsb = successData.resultMap.csrsb;
				var snapshot = successData.resultMap.snapshot;
				csrsb.snapshot = snapshot;
				
				//设置快照简略信息
				csrsb.createdDate = $.date.timeToStr(csrsb.createdDate,"yyyy-MM-dd HH:mm");
				$.validform.setFormTexts("#snapshotInfo", csrsb);
				
				//主案件简略信息
				var mainCase = csrsb.snapshot[0];
				$.validform.setFormTexts("#mainCaseInfo", mainCase);
				
				$.caseAnalysisCompareCommon.initAnalysisCompareTable("#cacTable", snapshot);
			}
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookSnapshotDetail, { 
		
	});	
})(jQuery);



