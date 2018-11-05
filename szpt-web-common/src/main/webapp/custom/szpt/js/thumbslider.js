
$.thumbslider = $.thumbslider || {} ;
(function($){
	
	var objs = {
			
	}
	
	var basicSettings = {
	   id:null,
	   tabDoms:[],
	   speed:"slow"
	}
	
	$(document).ready(function() {	
		$(document).on("click", ".thumbslider-circle-ul-li", function(){
			var id = $(this).parents(".thumbslider").attr("id") ;
			var index = $(this).attr("showIndex") ;
			var obj = $.thumbslider.getSilder(id) ;
			obj.toPage(index) ;
		});
		
	});

function initOrRefresh(settings){
	var _settings = copySeting(settings) ;
	var root = $("#" + _settings.id) ;
	
	var obj = objs[settings.id] ;
	if($.util.exist(obj)){
		$(".thumbslider-show-ul", root).html("") ; 
		$(".thumbslider-circle", root).html("") ; 
	}else{
		obj = {} ;
		objs[_settings.id] = obj ;
		obj.settings = _settings ;
	}

	var thumbsliderShow = $(".thumbslider-show", root) ;
	if(thumbsliderShow.length==0){
		thumbsliderShow = $("<div />", {
			"class":"thumbslider-show"
		}).appendTo(root);
	}
	
	var thumbsliderShowCount = $(".thumbslider-show-count", thumbsliderShow) ;
	if(thumbsliderShowCount.length==0){
		thumbsliderShowCount = $("<div />", {
			"class":"thumbslider-show-count thumbslider-show-container"
		}).appendTo(thumbsliderShow);
	}
	
	var thumbsliderShowCountUl = $(".thumbslider-show-ul", thumbsliderShowCount) ;
	if(thumbsliderShowCountUl.length==0){
		thumbsliderShowCountUl = $("<ul />", {
			"class":"thumbslider-show-ul"
		}).appendTo(thumbsliderShowCount);
	}
	
	var thumbsliderShowCopy = $(".thumbslider-show-copy", thumbsliderShow) ;
	if(thumbsliderShowCopy.length==0){
		thumbsliderShowCopy = $("<div />", {
			"class":"thumbslider-show-copy thumbslider-show-container",
			"style":"display:none"
		}).appendTo(thumbsliderShow);
	}
	var thumbsliderShowCopyUl = $(".thumbslider-show-ul", thumbsliderShowCopy) ;
	if(thumbsliderShowCopyUl.length==0){
		thumbsliderShowCopyUl = $("<ul />", {
			"class":"thumbslider-show-ul"
		}).appendTo(thumbsliderShowCopy);
	}


	
	var circle = $(".thumbslider-circle", root) ;
	if(circle.length==0){
		circle = $("<div />", {
			"class":"thumbslider-circle"
		}).appendTo(root);
	}
	
	var circleUl = $(".thumbslider-circle-ul", root) ;
	if(circleUl.length==0){
		circleUl = $("<div />", {
			"class":"thumbslider-circle-ul"
		}).appendTo(circle);
	}
	circleUl.css("left", 0) ;
	
	$.each(_settings.tabDoms, function(i, val){
		var li = $("<li />", {
			"showIndex": i,
			"class":"thumbslider-show-ul-li"
		}).appendTo(thumbsliderShowCountUl);
		li.append(val) ;
		
		var cli = $("<li />", {
			"showIndex": i,
			"class":"thumbslider-circle-ul-li"
		}).appendTo(circleUl);
		if(i==0){
			li.addClass("curr") ;
			cli.addClass("curr") ;
		}
		cli.append($(val).clone()) ;
	});
	
	obj.next = next ;
	obj.prev = prev ;
	obj.toPage = toPage ;
}

function toPage(index){
	var obj = this ;
	if(obj.isMoving){
		return ;
	}
	var settings = obj.settings ;
	var root = $("#"+obj.settings.id) ;
	var thumbsliderShowCount = $(".thumbslider-show-count", root) ;
	var thumbsliderShowCopy = $(".thumbslider-show-copy", root) ;
	var thumbsliderShowCopyUl = $(".thumbslider-show-ul", thumbsliderShowCopy) ;
	
	var currCountLi = $(".thumbslider-show-ul-li.curr", thumbsliderShowCount) ;
	var toCountLi = $(".thumbslider-show-ul-li[showIndex="+index+"]", thumbsliderShowCount) ;
	var countLis = $(".thumbslider-show-ul-li", thumbsliderShowCount) ;
	var total = countLis.length ;
	var width = currCountLi.outerWidth(true);
	
	var currIndex = currCountLi.attr("showIndex") ; 
	if(currIndex==index){
		return ;
	}

	var c_currCountLi = currCountLi.clone() ;
	c_currCountLi.show() ;
	var c_toCountLi = toCountLi.clone() ;
	c_toCountLi.show() ;
	
	thumbsliderShowCopyUl.html("") ;
	thumbsliderShowCopy.css("left", 0) ;
	
	var moveWidth = 0 ;
	if(currIndex<index){
		if(currIndex==0 && index==(total-1)){
			thumbsliderShowCopyUl.append(c_toCountLi) ;
			thumbsliderShowCopyUl.append(c_currCountLi) ;
			thumbsliderShowCopy.css("left", -width);
		}else{
			thumbsliderShowCopyUl.append(c_currCountLi) ;
			thumbsliderShowCopyUl.append(c_toCountLi) ;
			moveWidth = -width ;
		}
	}else{
		if(currIndex==(total-1) && index==0){
			thumbsliderShowCopyUl.append(c_currCountLi) ;
			thumbsliderShowCopyUl.append(c_toCountLi) ;
			moveWidth = -width ;
		}else{
			thumbsliderShowCopyUl.append(c_toCountLi) ;
			thumbsliderShowCopyUl.append(c_currCountLi) ;
			thumbsliderShowCopy.css("left", -width);
		}
	}
	
	thumbsliderShowCopy.show() ;
	thumbsliderShowCount.hide() ;
	obj.isMoving = true ;

	selectCircleByIndex(index, obj);
	thumbsliderShowCopy.animate({left:moveWidth}, settings.speed, function(){
		thumbsliderShowCopy.hide() ;
		thumbsliderShowCopyUl.html("") ;
		thumbsliderShowCopy.css("left", 0) ;
	
		countLis.removeClass("curr") ;
		countLis.each(function(i, val){
			if($(val).attr("showIndex")==index){
				$(val).show();
				$(val).addClass("curr") ; 
			}else{
				$(val).hide();
			}
		});
		thumbsliderShowCount.show() ;
		obj.isMoving = false ;
	});
	
	
}

function next(){
	nextShow(this) ;
}

function prev(){
	prevShow(this) ;
}

function prevShow(obj){
	var root = $("#"+obj.settings.id) ;
	var thumbsliderShowCount = $(".thumbslider-show-count", root) ;
	var currCountLi = $(".thumbslider-show-ul-li.curr", thumbsliderShowCount) ;
	var currIndex = currCountLi.attr("showIndex") ; 
	var countLis = $(".thumbslider-show-ul-li", thumbsliderShowCount) ;
	var total = countLis.length ;
	
	var toIndex = currIndex-1 ;
	
	if(toIndex<0){
		toIndex = total-1 ;
	}
	
	obj.toPage(toIndex) ;
}

function nextShow(obj){
	var root = $("#"+obj.settings.id) ;
	var thumbsliderShowCount = $(".thumbslider-show-count", root) ;
	var currCountLi = $(".thumbslider-show-ul-li.curr", thumbsliderShowCount) ;
	var currIndex = currCountLi.attr("showIndex") ; 
	var countLis = $(".thumbslider-show-ul-li", thumbsliderShowCount) ;
	var total = countLis.length ;
	
	var toIndex = parseInt(currIndex, 10)+1 ;
	
	if(toIndex > (total-1)){
		toIndex = 0 ;
	}
	
	obj.toPage(toIndex) ;
}

function nextCircle(obj){
	
}

function selectCircle(obj) {
	var root = $("#"+obj.settings.id) ;
	var thumbsliderShowCount = $(".thumbslider-show-count", root) ;
	var currCountLi = $(".thumbslider-show-ul-li.curr", thumbsliderShowCount) ;
	var currIndex = currCountLi.attr("showIndex") ; 
	selectCircleByIndex(currIndex, obj) ;
}

function selectCircleByIndex(index, obj) {
	
	var root = $("#"+obj.settings.id) ;
	var settings = obj.settings ;
	var circle = $(".thumbslider-circle-ul", root) ;
	var fromIndex = $(".thumbslider-circle-ul-li.curr", circle).attr("showIndex") ;
	var circleLis = $(".thumbslider-circle-ul-li", circle) ;
	circleLis.removeClass("curr");
	circleLis.eq(index).addClass("curr") ;
	
	var circleWdith = $(".thumbslider-circle", root).width() ;
	
	var total = circleLis.length ;
	var width = circleLis.outerWidth(true) ;
	var totalLength = total * width ;
	
	var left = circle.css("left") ;
	left = parseInt(left.replace("px", "")) ;
	
	if(circleWdith>totalLength){
		return ;
	}
	
	if(index==0){
		circle.animate({left:0}, settings.speed, function(){});
	}else if(index==(total-1)){
		circle.animate({left:(Math.ceil((circleWdith-totalLength)/width))*width}, settings.speed, function(){});
	}else if(fromIndex<index && circleWdith-((totalLength + left))<width && (totalLength + left - circleWdith)>width){
		circle.animate({left:-width + left}, settings.speed, function(){});
	}else if(fromIndex>index && left<0 && index>0){
		circle.animate({left:width + left}, settings.speed, function(){});
	}
	
}

jQuery.extend($.thumbslider, {	
	initOrRefresh:initOrRefresh,
	getSilder:function(id){
		return objs[id] ;
	}
});

function copySeting(opt){
	var tpsettings = $.util.cloneObj(basicSettings) ;
	$.util.mergeObject(tpsettings, opt) ;
	return tpsettings ;
}

})(jQuery);	


