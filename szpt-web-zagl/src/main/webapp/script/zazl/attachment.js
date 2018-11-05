(function($){
		$(document).ready(function() {
			p$ = window.top.$ ;
			var frameData = p$.layerAlert.getFrameInitData(window) ;
			index = frameData.index ;
			var initData = frameData.initData ;
			console.log(initData);
			var str = "<video controls autoplay loop name='media' style='width:800px;height:470px;'><source src='" + initData.path + "'></video>";
			$("#content").html(str);
		});
		
})(jQuery);

