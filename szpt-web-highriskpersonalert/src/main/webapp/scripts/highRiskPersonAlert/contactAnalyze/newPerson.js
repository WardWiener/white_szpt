(function($){
	"use strict";
//	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
//	var pageIndex = frameData.index ;//当前弹窗index
//	var initData = frameData.initData ;
//	var table;
	var count=1;
	var count2=1;
	$(document).ready(function() {	
		$(document).on("click","#addPhone",function(){
			count++
			if(count%2==0){
				var a='<div class="col-xs-2"> <label class="control-label">手机号'+count+'：</label></div>'
				a+='<div class="col-xs-3"><input class="form-control input-sm valiform-keyup phone" value=""datatype="n11-11"errormsg="请填写正确手机号"></div>';
				$(".phoneDiv:last").append(a);
			}else{
				var a='<div class="row row-mar phoneDiv"><div class="col-xs-2"> <label class="control-label">手机号'+count+'：</label></div>'
				a+='<div class="col-xs-3"><input class="form-control input-sm valiform-keyup phone" value=""datatype="n11-11"errormsg="请填写正确手机号"></div></div>';
				$(".phoneDiv:last").after(a);
			}
		});
		$(document).on("click","#add",function(){
			var a='<tr>';
            a+='<td><input class="form-control input-sm valiform-keyup"></td>';
            a+='<td><input class="form-control input-sm valiform-keyup"></td>';
            a+='<td>2016-5-30 12:01:03</td>';
            a+='<td><input class="form-control input-sm valiform-keyup"></td>';
            a+='<td><button class="btn btn-x">×</button> </td>';
            a+='</tr>';
			$("tbody").append(a);
		});
	});
	function getData(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
	});	
})(jQuery);