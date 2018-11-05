(function($){
	
	"use strict";
	
	$(document).ready(function() {	
		
		invalideInfo() ;

		$(document).on("click", "#login-button", function(){
			//$.util.objToStrutsFormData(obj, "personBean", dataMap);					
			var obj = $.validform.getFormVals("#myvf") ;
			var flag = true ;
			if($.util.isBlank(obj.userName)){
				$($("#userName").next()).css("display", "block") ;
				flag = false ;
			}
			if($.util.isBlank(obj.password)){
				$($("#password").next()).css("display", "block") ;
				flag = false ;
			}
/*			if($.util.isBlank(obj.validate)){
				$($("#validate").next()).css("display", "block") ;
				flag = false ;
			}*/
			
			if(!flag){
				return false ;
			}
			//obj.password = $.util.encryptMd5(obj.password + sessionId) ;
			obj.passEncrypt = "no" ;
			var url = context + "/login" ;
			var form = $.util.getHiddenForm(url, obj) ;
			$.util.subForm(form) ;
/**
 * 
 *  //var serverName = '<%=request.getServerName()%>' ;
	//var httpsPort = '<%=ConstantsFmtUtil.globalMap.get("httpsPort")%>' ;
	//var sessionId = '<%=session.getId()%>' ;
 * 
 * 
 */			
//			var url = 'https://'+serverName+':'+httpsPort+ context + '/login;jsessionid=' + sessionId ;
//			var form = $.util.getHiddenForm(url, obj) ;
//
//			$.util.subForm(form) ;
			
//		 	$.ajax({
//		         url:'https://localhost:8444/system-manager-web/login',
//		         data:obj,
//		         dataType:"jsonp",
//		         jsonp:"jsonpCallback",
//		         success:function(data){
//		        	 alert() ;
//		        	 console.log(data.toString()) ;
//		         },
//		         error:function(event,xhr,options,exc){
//		        	 console.log(xhr) ;
//		         }
//		    });
			
//	    	$.ajax({
//	    		url: context + '/login',
//	    		data:obj,
//	    		type:"POST",	
//	    		dataType:"json",
//	    		success:function(data){
//	    			console.log(data) ;
//	    		}
//	    	});
			
//			event.preventDefault();
//			$('.logform').fadeOut(500);
//			$('.login-box').addClass('login-box-success');
		});
		
		$(document).on("click", "#randomImg", function(){
			reloadImage(this) ;
		});
		
		$(document).on("keydown", function(e){
			keyLogin(e) ;
		});
		
		$(".form-val").on('keyup change', function(){
			var val = $(this).val() ;
			if($.util.isBlank(val)){
				$($(this).next()).css("display", "block") ;
			}else{
				$($(this).next()).css("display", "none") ;
			}
			
			if( !$.util.hasJQueryEvent(this, "blur") ){
				$(this).on('blur', function(){
					var val = $(this).val() ;
					if($.util.isBlank(val)){
						$($(this).next()).css("display", "block") ;
					}else{
						$($(this).next()).css("display", "none") ;
					}
				});
			}
		});
		
//    	$.ajax({
//    		url: context + '/person/initEditPersonInfo.action',
//    		data:{personId:initData.id},
//    		type:"POST",	
//    		dataType:"json",
//    		success:init
//    	});
		
		function reloadImage(selector){
			var img = $(selector) ;
			var src = img.attr("src") ;
			if(src.indexOf("?")>0){
				src = src.substring(0, src.indexOf("?")) ;
			}
			src = src + "?d=" + Math.random() ;
			img.attr("src", src) ;
		}
		
	    function keyLogin(e){  
	        var ev = document.all ? window.event : e;
	   	    if(event.keyCode==13){
	   	    	$("#login-button").trigger("click") ;
	   	        //document.getElementById("loginId").click(); //调用登录按钮的登录事件  
	   	    }
	   	} 

	    function invalideInfo(){
	    	var url = context + "/index.jsp" ;
	    	if(sign.sign=="0"){
	    		invalideAlert("验证失败！", url) ;
	    	}

	    	if(sign.sign=="1"){
	    		invalideAlert("验证失败！", url) ;
	    	}

	    	if(sign.sign=="2"){
	    		invalideAlert("用户被锁定！", url) ;
	    	}

	    	if(sign.sign=="3"){
	    		invalideAlert("用户被禁用！", url) ;
	    	}

	    	if(sign.sign=="4"){
	    		invalideAlert("用户已过有效期！", url) ;
	    	}

	    	if(sign.sign=="5"){
	    		invalideAlert("用户已过有效期！", url) ;
	    	}

	    	if(sign.sign=="6"){
	    		invalideAlert("验证失败！", url) ;
	    	}

	    	if(sign.sign=="7"){
	    		invalideAlert("验证失败！", url) ;
	    	}

	    	if(sign.sign=="8"){
	    		invalideAlert("验证码错误！", url) ;
	    	}	  
	    	
	    	if(sign.sign=="9"){
	    		invalideAlert("用户登陆数已超出！", url) ;
	    	}	  
	    	
	    }
	    
	    function invalideAlert(msg, url){
			$.layerAlert.alert({
				msg:msg,
				end:function(){
					window.top.location.href = url ;
				}
			});
	    }
    });
	
})(jQuery);	