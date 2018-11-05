$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;

$.szpt.util.searchTime = $.szpt.util.searchTime || {} ;
(function($){
	
	"use strict";
	$(document).ready(function() {	
		init() ;
	});
	
	var basicSettings = {
			callBacks:{
				initCallBack:function(queryTime){
					var end = new Date(queryTime.endTime) ;
					end.setDate(end.getDate() - 1) ;
					if($("#searchRange").length>0){
						$.laydate.setTimeRange(queryTime.startTime, end.getTime(), "#searchRange") ;
					}
				},
				clickCallBack:function(queryTime){
					var end = new Date(queryTime.endTime) ;
					end.setDate(end.getDate() - 1) ;
					if($("#searchRange").length>0){
						$.laydate.setTimeRange(queryTime.startTime, end.getTime(), "#searchRange") ;
					}
				}
			}	
	}
	
	function init(opt){
		
		var settings = copySeting(opt) ;
		
		$(document).on("click", ".searchTime-btn", function(e){
			
			$(".searchTime-btn").removeClass("btSelected") ;
			$(".searchTime-btn").removeClass("btn-danger") ;
			$(".searchTime-btn").removeClass("btn-primary") ;
			$(".searchTime-btn").addClass("btn-bordered") ;
			
			
			$(this).addClass("btSelected") ;
			$(this).addClass("btn-danger") ;
			
			if(!$.szpt.util.searchTime.getTimeValueType().toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.COSTUM.toLowerCase()){
				settings.callBacks.clickCallBack($.szpt.util.searchTime.getDates($.szpt.util.searchTime.getTimeValueType()));
			}

		});
		
		if(!$.szpt.util.searchTime.getTimeValueType().toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.COSTUM.toLowerCase()){
			settings.callBacks.clickCallBack($.szpt.util.searchTime.getDates($.szpt.util.searchTime.getTimeValueType()));
		}
	}
	
	function getDates(timeValueType){

		var dates = {} ;
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.LAST_HOUR.toLowerCase()){
			dates = fmtLastHour() ;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.DAY.toLowerCase()){
			dates = fmtDay() ;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.DAY3.toLowerCase()){
			dates = fmtDay3() ;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.WEEK.toLowerCase()){
			dates = fmtWeek() ;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.MONTH.toLowerCase()){
			dates = fmtMonth() ;
		}
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.HALF_YEAR.toLowerCase()){
			dates = fmtHalfYear() ;
		}

		var tbDates = getTbDates(dates.start, dates.end, timeValueType) ;
		var hbDates = getHbDates(dates.start, dates.end, timeValueType) ;
		
		return {
			startTime:dates.start.getTime(),
			endTime:dates.end.getTime(),
			startTimeTB:tbDates.startTB.getTime(),
			endTimeTB:tbDates.endTB.getTime(),
			startTimeHB:hbDates.startHB.getTime(),
			endTimeHB:hbDates.endHB.getTime()
		};
	}
	
	function fmtLastHour(){
		var start = new Date() ;
		var end = new Date() ;
		
		start.setHours(start.getHours() - 1) ;
		
		return {
			start:start,
			end:end
		};
	}
	
	function fmtDay(){
		
		var now = new Date() ;
		var start = new Date() ;
		var end = new Date() ;
		
		start.setHours(8) ;
		start.setMinutes(0) ;
		start.setSeconds(0) ;
		
		end.setHours(8) ;
		end.setMinutes(0) ;
		end.setSeconds(0) ;
		end.setDate(end.getDate() + 1) ;
		
		if(now<start){
			start.setDate(start.getDate() - 1) ;
		}
		
		return {
			start:start,
			end:end
		};
	}
	
	function fmtDay3(){
		var start = new Date() ;
		var end = new Date() ;
		
		start.setHours(8) ;
		start.setMinutes(0) ;
		start.setSeconds(0) ;
		
		end.setHours(8) ;
		end.setMinutes(0) ;
		end.setSeconds(0) ;
		end.setDate(end.getDate() + 1) ;
		
		start.setDate(start.getDate() - 2) ;
		
		return {
			start:start,
			end:end
		};
	}
	
	/**
	 * 上周日8点开始算
	 * @returns
	 */
	function fmtWeek(){
		
		var now = new Date(); 
		var nowTime = now.getTime() ; 
		var day = now.getDay();
		var oneDayTime = 24*60*60*1000 ; 
		//周一时间
		var mondayTime = nowTime - (day-1)*oneDayTime ; 
		//周日时间
		var sundayTime =  nowTime + (7-day)*oneDayTime ; 
		
		var start = new Date(mondayTime) ;
		var end = new Date(sundayTime) ;
		
		start.setHours(8) ;
		start.setMinutes(0) ;
		start.setSeconds(0) ;
		start.setDate(start.getDate() - 1) ;
		
		end.setHours(8) ;
		end.setMinutes(0) ;
		end.setSeconds(0) ;
		//end.setDate(end.getDate() + 1) ;
		
		
		
		return {
			start:start,
			end:end
		};
	}
	
	/**
	 * 当月一号开始算
	 * @returns
	 */
	function fmtMonth(){
		var start = new Date() ;
		var end = new Date() ;
		
		start.setDate(1) ;
		start.setHours(8) ;
		start.setMinutes(0) ;
		start.setSeconds(0) ;
		
		end.setDate(1) ;
		end.setMonth(end.getMonth() + 1) ;
		end.setHours(8) ;
		end.setMinutes(0) ;
		end.setSeconds(0) ;
		
		return {
			start:start,
			end:end
		};
	}
	
	function fmtHalfYear(){
		
		var start = new Date(); 
		var end = new Date() ;
		
		start.setHours(8) ;
		start.setMinutes(0) ;
		start.setSeconds(0) ;
		
		start.setMonth(start.getMonth() - 6) ;
		
		end.setHours(8) ;
		end.setMinutes(0) ;
		end.setSeconds(0) ;
		end.setDate(end.getDate() + 1) ;
		
		return {
			start:start,
			end:end
		};
	}
	
	function getTbDates(start, end, timeValueType){
		
		var st = new Date(start.getTime()) ;
		var ed = new Date(end.getTime()) ;
		
		st.setFullYear(st.getFullYear() - 1) ;
		ed.setFullYear(ed.getFullYear() - 1)
		
		return {
			startTB:st,
			endTB:ed
		}
	}
	
	function getHbDates(start, end, timeValueType){
		
		var st = new Date(start.getTime()) ;
		var ed = new Date(end.getTime()) ;

		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.DAY.toLowerCase()){
			st.setDate(st.getDate() - 1);
			ed.setHours(8);
			ed.setDate(ed.getDate() - 1);
		}
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.WEEK.toLowerCase()){
			ed=start;
			start.setHours(8);
			st.setDate(st.getDate() - 7) ;
			//ed.setDate(st.getDate()) ;
		}
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.MONTH.toLowerCase()){
			st.setMonth(st.getMonth() - 1) ;
			start.setHours(8);
			ed = start ;
		}
		
		return {
			startHB:st,
			endHB:ed
		}
	}
	
	function copySeting(opt){
		var tpsettings = $.util.cloneObj(basicSettings) ;
		$.util.mergeObject(tpsettings, opt) ;
		return tpsettings ;
	}
	
	function getCustomDates(){
		
		var startTime = $.laydate.getTime("#searchRange", "start") ;
		var endTime = $.laydate.getTime("#searchRange", "end") ;
		var end = null ;
		
		if(!startTime){
			throw "开始时间不能为空" ;
		}
		
		if(endTime){
			var end = new Date(endTime) ;
		}else{
			var end = new Date() ;
			end.setHours(8) ;
			end.setMinutes(0) ;
			end.setSeconds(0) ;
		}
		
		end.setDate(end.getDate() + 1) ;
		
		return {
			startTime:startTime,
			endTime:end.getTime()
		}
	}
	
	function getAllDays(timeValueType){
		
		if(timeValueType.toLowerCase()==$.common.TIME_VALUE_TYPE_CONSTANT.COSTUM.toLowerCase()){
			return getCustomDates() ;
		}
		
		return getDates(timeValueType) ;
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.searchTime, { 
		initBtns:init,
		getDates:getDates,
		getCustomDates:getCustomDates,
		getAllDays:getAllDays,
		getTimeValueType:function(){
			return $(".searchTime-btn.btSelected").attr("value") ;
		}
	});	
})(jQuery);