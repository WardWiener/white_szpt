$.scoreTemplateCommon = $.scoreTemplateCommon || {};
(function($){
	"use strict";
	
	/**
	 * 验证案件性质等select是否都填写值
	 */
	function verifySelect(secondSortIsNotNull){
		var type = $.select2.val("#type");
		var firstSort = $.select2.val("#firstSort");
		var secondSort = $.select2.val("#secondSort");
		
		var msg = "";
		if(secondSortIsNotNull){
			if($.util.isBlank(type)){
				msg += "案件类别未选择。";
			}else if($.util.isBlank(firstSort)){
				msg += "案件性质未选择。";
			}else if($.util.isBlank(secondSort)){
				msg += "二级案件性质未选择。";
			}
		}else{
			if($.util.isBlank(type)){
				msg += "案件类别未选择。";
			}else if($.util.isBlank(firstSort)){
				msg += "案件性质未选择。";
			}
		}
		return msg;
	}
	
	/**
	 * 验证编码是否唯一
	 * @param code 编码
	 */
	function verifyCodeOnly(code){
		var data = {
			"code" : code
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/scoreTemplate/verifyCodeOnly.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:false,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				if(resultMap.flag){
					$.layerAlert.tips({
						msg:'编码已存在。',
						selector:"input[name='code']",  //需要弹出层的元素选择器
						color:'#FF0000',
						position:2,
						closeBtn:2,
						time:2000,
						shift:1
					});
				}
			}
		});
	}
	
	/**
	 * 验证名称是否唯一
	 */
	function verifyNameOnly(name){
		var data = {
			"name" : name
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/scoreTemplate/verifyNameOnly.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    customizedOpt:{
			     ajaxLoading:false,//设置是否loading
			},
			success:function(successData){
				var resultMap = successData.resultMap;
				if(resultMap.flag){
					$.layerAlert.tips({
						msg:'名称已存在。',
						selector:"input[name='name']",  //需要弹出层的元素选择器
						color:'#FF0000',
						position:2,
						closeBtn:2,
						time:2000,
						shift:1
					});
				}
			}
		});
	}
	
	/**
	 * 验证权重总值是否大于10
	 */
	function verifyQuanZhong(){
		var flag = false;
		var quanZhongCount = 0;
		$("#gradeItemScoreTable tbody tr").each(function(t,tr){
			if(t == 6){
				return true;
			}
			quanZhongCount += parseFloat($(tr).find("input").eq(0).val());
		});
		if(Math.floor(quanZhongCount) == 10){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取评分项得分
	 */
	function getGradeItemScore(){
		var gisArray = new Array();
		$("#gradeItemScoreTable tbody tr").each(function(t,tr){
			if(t == 6){
				return true;
			}
			var item = getItemByTrNum(t);
//			switch(t){
//				case 0 :
//					item = "key_features";
//					break;
//				case 1 :
//					item = "key_occurPlace";
//					break;
//				case 2 :
//					item = "key_period";
//					break;
//				case 3 :
//					item = "key_peopleNum";
//					break;
//				case 4 :
//					item = "key_entrance";
//					break;
//				case 5 :
//					item = "key_exit";
//					break;
//				case 7 :
//					item = "key_communities";
//					break;
//			}
			
			var gisObj = new Object();
			gisObj["item"] = item;
			var rule = "";
			var inputValueArr = $(tr).find("input");
			$.each(inputValueArr,function(v,inputValue){
				if(v == 0){
					gisObj["weight"] = $(inputValue).val();
					return true;
				}
				rule += $(inputValue).val();
				if(v < inputValueArr.length - 1){
					rule += ",";
				}
			});
			gisObj["rule"] = rule;
			gisArray.push(gisObj);
		});
		return gisArray;
	}
	
	/**
	 * 设置评分项得分
	 * 
	 * @param objArray 评分项数组 
	 */
	function setGradeItemScore(objArray){
		if(!$.util.exist(objArray) || objArray.length < 1){
			return ;
		}
		$.each(objArray,function(i,val){
			$("tr[name='"+ val.item +"']").find("input").eq(0).val(val.weight);
			var inputValueArr = val.rule.split(",");
			$.each(inputValueArr,function(v,inputValue){
				$("tr[name='"+ val.item +"']").find("input").eq(v+1).val(inputValue);
			});
		});
	}
	
	/**
	 * 设置串并案评分项得分表tr和tr下的input的 name属性值
	 */
	function setGradeItemScoreTableTrAndInputName(){
		$("#gradeItemScoreTable tbody tr").each(function(t,tr){
			if(t == 6){
				return true;
			}
			var item = getItemByTrNum(t);
//			switch(t){
//				case 0 :
//					item = "key_features";
//					break;
//				case 1 :
//					item = "key_occurPlace";
//					break;
//				case 2 :
//					item = "key_period";
//					break;
//				case 3 :
//					item = "key_peopleNum";
//					break;
//				case 4 :
//					item = "key_entrance";
//					break;
//				case 5 :
//					item = "key_exit";
//					break;
//				case 7 :
//					item = "key_communities";
//					break;
//			}
			$(tr).attr("name",item);
		});
	}
	
	function getItemByTrNum(t){
		var item = "";
		switch(t){
			case 0 :
				item = KEY_CASEFEATURE;
				break;
			case 1 :
				item = KEY_CASEPLACE;
				break;
			case 2 :
				item = KEY_CASEPERIOD;
				break;
			case 3 :
				item = KEY_CASEPERSONCOUNT;
				break;
			case 4 :
				item = KEY_CASEENTRANCE;
				break;
			case 5 :
				item = KEY_CASEEXIT;
				break;
			case 7 :
				item = KEY_CASECOMMUNITY;
				break;
		}
		return item;
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.scoreTemplateCommon, { 
		verifyCodeOnly : verifyCodeOnly ,
		verifyNameOnly : verifyNameOnly ,
		verifyQuanZhong : verifyQuanZhong ,
		verifySelect : verifySelect ,
		getGradeItemScore : getGradeItemScore ,
		setGradeItemScore : setGradeItemScore ,
		setGradeItemScoreTableTrAndInputName : setGradeItemScoreTableTrAndInputName
	});	
})(jQuery);
