

$(document).ready(function() {	

	initIcheck() ;
	initSelect2() ;
	  
});

function initIcheck(){
  //icheck初始化
  $('input.icheckbox').iCheck({
	  checkboxClass: 'icheckbox_square-green',
	  radioClass: 'iradio_square-green',
	  increaseArea: '20%' // optional
  });    
  $('input.icheckradio').iCheck({
	  checkboxClass: 'icheckbox_square-green',
	  radioClass: 'iradio_square-green',
	  increaseArea: '20%' // optional
  });  
}

function initSelect2(){
	$(".select2").select2() ;
	$(".select2-noSearch").select2({
		minimumResultsForSearch: Infinity
	}) ;
	$(".select2-multiple").select2() ;	
}


/**************** 让el容器中的的所有元素都能兼容IE6***********
$.bootstrapIE6(el)
************** 让el容器中的的所有元素都能兼容IE6***********/


$(document).ready(function() {
	$(".nav-ceng-out").hover(function(){
		 $(this).find(".nav-ceng").slideDown();
		 $(this).children("a").addClass("current");
	   },function(){
		   $(this).find(".nav-ceng").hide();
		   $(this).children("a").removeClass("current");
	   });
    
});//顶部下拉导航 	    


$(document).ready(function() {
	$(".advanced-btn").click(function() {
    $(".advanced-query").slideDown(500);
	$(".basic-query").hide();
	$(".advanced-btn-box").show();
	});
	
	$(".advanced-btn-up").click(function(){
	$(".advanced-query").slideUp(500);
	$(".basic-query").show();
	$(".advanced-btn-box").hide();
	});   
})//右侧高级查询

$(document).ready(function() {
$('.m-move').draggable();
});
//可拖拽


$(document).ready(function() {
	function gotoTop(min_height){
    var gotoTop_html = '<div id="gotoTop" title="回顶部"></div>';
    $("#gotoTop").click(
        function(){$('html,body').animate({scrollTop:0},300);
    }).hover(
        function(){$(this).addClass("hover");},
        function(){$(this).removeClass("hover");
    });
    //获取页面的最小高度，无传入值则默认为600像素
    min_height ? min_height = min_height : min_height = 600;
    $(window).scroll(function(){
        //获取窗口的滚动条的垂直位置
        var s = $(window).scrollTop();
        //当窗口的滚动条的垂直位置大于页面的最小高度时，让返回顶部元素渐现，否则渐隐
        if( s > min_height){
            $("#gotoTop").fadeIn(100);
        }else{
            $("#gotoTop").fadeOut(200);
        };
    });
};
gotoTop();
});

//回顶部

/************************************
$(document).ready(function(){
var navH = $(".fixed-a").offset().top;
$(window).scroll(function(){
var scroH = $(this).scrollTop();
if(scroH>=navH){
$(".fixed-a").addClass('fixed-b');
}
else if(scroH < navH){
$(".fixed-a").removeClass('fixed-b');
}
});
});
*************************************/
//滚动到浏览器顶部时固定
$(document).ready(function() {	
	var tbSt = $.uiSettings.getOTableSettings ;
	$.util.mylog(tbSt) ;
	tbSt.serverSide = false ;
	$('#example1').DataTable(tbSt) ;	
});

$(document).ready(function(e) {
      $(".fi-ceng-out").hover(function(){
	  $(this).find(".fi-ceng").show();
	   },function(){
		    $(this).find(".fi-ceng").hide();	 
       });
	   	   
});
$(document).ready(function() {
	$( "#tabs" ).tabs();
});


/*****************小图点击弹出放大********************/
/*$('a.customGal').zoomimage({
	controlsTrigger: 'mouseover',
	className: 'custom',
	shadow: 40,
	controls: false,
	opacity: 1,
	beforeZoomIn: function(boxID) {
		$('#' + boxID)
			.find('img')
			.css('opacity', 0)
			.animate(
				{'opacity':1},
				{ duration: 500, queue: false }
			);
	},
	beforeZoomOut: function(boxID) {
		$('#' + boxID)
			.find('img')
			.css('opacity', 1)
			.animate(
				{'opacity':0},
				{ duration: 500, queue: false }
			);
	}
});*/
/*****************小图点击弹出放大********************/