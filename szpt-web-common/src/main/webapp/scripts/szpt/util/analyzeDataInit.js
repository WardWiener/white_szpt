$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;

$.szpt.util.analyzeData = $.szpt.util.analyzeData || {} ;
(function($){
	
	"use strict";
	
	var unit_pcs = [] ;
	
	var pcs_threshold = {} ;
	var pcs_fenbu_threshold = {} ;
	
	var initSuccessCallBacks = [] ;
	
	$(document).ready(function() {	
		init() ;
	});
	
	function init(){
		initDatas() ;
	}
	
	function initDatas(){
		var data = {
				"unitType":$.common.DICT.DICT_DWLX_PCS//$.common.SZPT_UNIT_TYPE_CONSTANT.TYPE_PCS	
			};
			
		var dataStr = $.util.toJSONString(data) ;
			
		$.ajax({
			url:context +'/analyzeInitInfo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				unit_pcs = successData.resultMap.pcs ;
				$.each($.common.PCS_THRESHOLD_TYPE_CONSTANT, function(key, val){
					pcs_threshold[val] = successData.resultMap[val] ;
				});
				
				$.each($.common.PCS_FENBU_THRESHOLD_TYPE_CONSTANT, function(key, val){
					pcs_fenbu_threshold[val] = successData.resultMap[val] ;
				});
				
				$.each(initSuccessCallBacks, function(i, val){
					val() ;
				});
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.analyzeData, { 
		initBtns:init,
		getPcs:function(){
			return unit_pcs ;
		},
		getPcsCodes:function(){
			var list = [] ;
			$.each(unit_pcs, function(i, val){
				list.push(val.code) ;
			});
			return list;
		},
		getPcsIds:function(){
			var list = [] ;
			$.each(unit_pcs, function(i, val){
				list.push(val.id) ;
			});
			return list ;
		},
		getPcsNames:function(){
			var list = [] ;
			$.each(unit_pcs, function(i, val){
				list.push(val.name) ;
			});
			return list ;
		},
		getPcsThreshold:function(pcsName, THRESHOLD_TYPE){
			var obj ;
			$.each(pcs_threshold, function(key, val){
				var flag = true ;
				if(THRESHOLD_TYPE==key){
					$.each(val, function(i, val1){
						if(val1.name==pcsName){
							obj = val1 ;
							flag = false ;
							return false ;
						}
					});
				}
				return flag ;
			});
			return obj ;
		},
		get_pcs_fenbu_threshold:function(pcsName, startTime, endTime){
			var startDate = new Date(startTime) ;
			var endDate = new Date(endTime) ;
			var minus = endDate.getDate() - startDate.getDate() ;
			
			var type = "" ;
			
			if(minus<2){
				type = $.common.PCS_FENBU_THRESHOLD_TYPE_CONSTANT.FENBU_DAY ;
			}
			
			if(minus>=2 && minus<7){
				type = $.common.PCS_FENBU_THRESHOLD_TYPE_CONSTANT.FENBU_WEEK ;
			}
			
			if(minus>=7 && minus<31){
				type = $.common.PCS_FENBU_THRESHOLD_TYPE_CONSTANT.FENBU_MONTH ;
			}
			
			if(minus>=31 && minus<366){
				type = $.common.PCS_FENBU_THRESHOLD_TYPE_CONSTANT.FENBU_YEAR ;
			}
			
			var obj ;
			$.each(pcs_fenbu_threshold, function(key, val){
				var flag = true ;
				if(type==key){
					$.each(val, function(i, val1){
						if(val1.name==pcsName){
							obj = val1 ;
							flag = false ;
							return false ;
						}
					});
				}
				return flag ;
			});
			return obj ;
		},
		initPcsSelect:function(selector){
			$.select2.addByObjects(selector, [{"all":"全部"}]) ;
			var pcs = $.szpt.util.analyzeData.getPcs() ;
			$.select2.addByList(selector, pcs, "code", "name") ;
		},
		getSeletedByPcsSelect:function(selector){
			var val = $.select2.val(selector) ;
			if(val=="all"){
				return $.szpt.util.analyzeData.getPcsCodes() ;
			}
			return [val] ;
		},
		addToInitSuccessCallBack:function(func){
			initSuccessCallBacks.push(func) ;
		}
	});	
})(jQuery);