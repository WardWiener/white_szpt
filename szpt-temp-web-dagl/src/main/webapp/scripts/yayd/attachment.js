(function($){
		$(document).ready(function() {
			p$ = window.top.$ ;
			var frameData = p$.layerAlert.getFrameInitData(window) ;
			index = frameData.index ;
			var initData = frameData.initData ;
			var bName = navigator.userAgent.toLowerCase();
			var str = "";
			if(initData.type == "image"){
				str = "<img style='max-width:800px;max-height:474px;' src='" + initData.src + "'>";
			}else if(initData.type == "video"){
				var str = "<video controls autoplay loop name='media' style='width:800px;height:470px;'><source src='" + initData.src + "'></video>";
			}else if(initData.type == "audio"){
				var str = "<audio controls autoplay loop name='media' style='width:300px;height:30px;'><source src='" + initData.src + "'></audio>";
			}
			$("#content").html(str);
		});
	})(jQuery);

/**
 * 向父页面暴露的方法
 */
jQuery.extend($.common, {
	setIndex:function(diaIndex){
		index=diaIndex; 
	}
});