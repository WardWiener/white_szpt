




(function($){
	"use strict";
	var fullSearch = false;
	$(document).ready(function() {	
	
		$.jcade.initWidget() ;
		$.util.initWidget() ;
		initDisableControl() ;
		initBtUrl() ;
		initTooltip() ;
		
		//浮动窗口
		$(document).on("mouseover mouseout",".fi-ceng-out",function(event){
			if(event.type == "mouseover"){
				//鼠标悬浮
				$(this).find(".fi-ceng").show();
			}else if(event.type == "mouseout"){
				//鼠标离开
				$(this).find(".fi-ceng").hide();
			}
		 });
		
		$(".nav-ceng-out").hover(function(){
			$(this).find(".nav-ceng").show();
		},function(){
			$(this).find(".nav-ceng").hide();	 
	    });
		
		//页签初始化
		$("#tabs").tabs();
		//右侧高级查询开始
		$(".advanced-btn").click(function() {
		    $(".advanced-query").slideDown(500);
			$(".basic-query").hide();
			$(".advanced-btn-box").show();
			fullSearch = true;
		});
		$(".advanced-btn-up").click(function(){
			$(".advanced-query").slideUp(500);
			$(".basic-query").show();
			$(".advanced-btn-box").hide();
			fullSearch = false;
		});
		//右侧高级查询结束
		
		/**
		 * 单位树搜索按钮点击事件
		 */
		$(document).on("click",".selectTreeUnit",function(){
			showMenu();
		});
		
		$(".show-hide-btn").click(function() {
		    $(this).parents(".show-hide-group").find(".show-hide-content").fadeToggle(500);
		});
		
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
		$(".select2").select2({
			width:"100%",
			placeholder:"请选择"
		}) ;
		$(".select2-noSearch").select2({
			minimumResultsForSearch: Infinity,
			width:"100%",
			placeholder:"请选择"
		}) ;
		$(".select2-multiple").select2({
			width:"100%",
			placeholder:"请选择"
		}) ;	
	}
	
	function initInputLength(){
		var setting = $.globalSettings.inputDefaultLength ;
		$("input[type=text].default").each(function(i, val){
			var st = $(val).attr("maxlength") ;
			if(!(st!=null&&st!=undefined&&st.length>0)){
				$(val).attr("maxlength", setting) ;
			}			
		});
	}	
		
	function initDateRange(){
		$("div.dateRange").each(function(i, val){
			var div = $(this) ;
			var fmt = $.laydate.getFmt(div) ;
			
			var start = div.find(".laydate-start") ;
			var end = div.find(".laydate-end") ;
			
			var startId = start.attr("id") ;
			var endId = end.attr("id") ;
			
			$.laydate.setDateObj(startId, {
				format: fmt, //日期格式
		        istime: true, //是否开启时间选择
		        isclear: true, //是否显示清空
		        istoday: true, //是否显示今天
		        issure: true, //是否显示确认
		        festival: true, //是否显示节日
		        fixed: true, //是否固定在可视区域
		        zIndex: 99999999, //css z-index
		        choose: function(dates){ //选择好日期的回调
		        	var endObj = $.laydate.getDateObj(endId) ;
		        	endObj.min = dates ; 
		        	endObj.start = dates ; 
		        }					
			}) ; 
			
			$.laydate.setDateObj(endId, {
				format: fmt, //日期格式
		        istime: true, //是否开启时间选择
		        isclear: true, //是否显示清空
		        istoday: true, //是否显示今天
		        issure: true, //是否显示确认
		        festival: true, //是否显示节日
		        fixed: true, //是否固定在可视区域
		        zIndex: 99999999, //css z-index
		        choose: function(dates){ //选择好日期的回调
		        	var endObj = $.laydate.getDateObj(startId) ;
		        	endObj.max = dates ; 
		        }						
			}) ; 
			

			
			$(document).on('click', "#"+startId, function () {			
				var div = $(this).parents(".dateRange") ;
				var fmt = $.laydate.getFmt(div) ;
				var dateObj = $.laydate.getDateObj(startId) ;
				dateObj.format = fmt ;
				
				laydate(dateObj);
			});
			
			$(document).on('click', "#"+endId, function () {	
				var div = $(this).parents(".dateRange") ;
				var fmt = $.laydate.getFmt(div) ;
				var dateObj = $.laydate.getDateObj(endId) ;
				dateObj.format = fmt ;
				
				laydate(dateObj);
			});
			
		});
		
		
		$(document).on('change', ".laydate-range", function () {		
			var div = $(this).parents(".dateRange") ;
			div.find(".laydate-start").val("") ;
			div.find(".laydate-end").val("") ;
		});
	
	}
	
	function initLaydate(){
		$("div.laydate").each(function(i, val){
			var div = $(this) ;
			var fmt = $.laydate.getFmt(div) ;
			
			var ldv = div.find(".laydate-value") ;
			
			var ldvId = ldv.attr("id") ;
			
			$.laydate.setDateObj(ldvId, {
				format: fmt, //日期格式
		        istime: true, //是否开启时间选择
		        isclear: true, //是否显示清空
		        istoday: true, //是否显示今天
		        issure: true, //是否显示确认
		        festival: true, //是否显示节日
		        fixed: true, //是否固定在可视区域
		        zIndex: 99999999, //css z-index
		        choose: function(dates){ //选择好日期的回调
		        	
		        }				
				
				
			}) ;
			
			$(document).on('click', "#"+ldvId, function () {			
				var div = $(this).parents(".laydate") ;
				var fmt = $.laydate.getFmt(div) ;
				
				var dateObj = $.laydate.getDateObj(ldvId) ;
				dateObj.format = fmt ;
				
				laydate(dateObj);
			});
			
		});
		
		
		$(document).on('change', ".laydate-fmt", function () {		
			var div = $(this).parents(".laydate") ;
			div.find(".laydate-value").val("") ;
		});
	
	}
	
	function initTabs(){
		$(".uiTabs").tabs() ;
	}
	
	function initDisableControl(){
		$(document).on('click', '.disableControl', function () {	
			$(this).attr("disabled",true); 
		});
	}
	
	function initBtUrl(){
		$(document).on('click', '.btUrl', function () {	
			var href = $(this).attr("myHref") ;
			href = $.util.fmtUrl(href) ;
			location.href = href ;
		});
	}
	
	function initTooltip(){
		$.tooltips.initTooltips(document) ;
		$.tooltips.initSelectableToolTips() ;
	}
	
	var settingSelect = {
			view: {
				fontCss: getFontCss
			},
			async: {
				enable: true,
				global: false,
				url:context+"/unitTree/initUnitTree.action",
				autoParam:["id=code"],
				dataFilter: function(treeId, parentNode, childNodes) {
					childNodes.ztreeList[0].open = true;
					return childNodes.ztreeList;
				}
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId"
				}
			},
			callback:{
				onClick : function(event, treeId, treeNode) {
					$("#unitName").val(treeNode.name);
					$("#unit").val(treeNode.id);
					hideMenu();
				}
			}
		};
		
		/**
		 * 初始化单位树
		 */
		function intiSelectUnitTree() {
			var treeSelect = $.fn.zTree.init($("#ztree-unitSelect"), settingSelect);
			treeSelect.lastSearchValue = "" ;
			treeSelect.nodeSearchList = [] ;
			treeSelect.fontSearchCss = {} ;
			
			$(document).on('focus', '#keySelect', function () {
				var key = $(this) ;
				 focusKey(key) ;
				
			});
			
			$(document).on('blur', '#keySelect', function () {
				var key = $(this) ;
				blurKey(key) ;
			});		
			
			$(document).on('keyup change', '#keySelect', function () {
				var key = $(this) ;
				searchNode(key, "ztree-unitSelect") ;
			});
		}
		
		/**
		 * 失去焦点事件
		 */
		function focusKey(key) {
			if (key.hasClass("empty")) {
				key.removeClass("empty");
			}
		}
		
		/**
		 * 获得焦点事件
		 */
		function blurKey(key) {
			if (key.get(0).value == "") {
				key.addClass("empty");
			}
		}
		
		/**
		 * 搜索节点
		 */
		function searchNode(key, treeId){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			var value = $.trim(key.get(0).value);
			if (value === "") {
				var nodes = zTree.getNodesByParam("isHidden", true);
				zTree.showNodes(nodes);
				zTree.expandAll(false);
				return;
			}
			var keyType = "name";
			if (key.hasClass("empty")) {
				value = "";
			}
			if (zTree.lastSearchValue === value) return;
			zTree.lastSearchValue = value ;
			updateNodes(false, treeId); 
			
			
			zTree.nodeSearchList = zTree.getNodesByParamFuzzy(keyType, value);
			//将符合条件的  节点  更新
			updateNodes(true, treeId);
		}
		
		/**
		 * 更新节点方法
		 */
		function updateNodes(highlight, treeId) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			var nodeList = zTree.nodeSearchList ;
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				zTree.expandNode(nodeList[i], true, true, true);
				zTree.selectNode(nodeList[i]);
				zTree.updateNode(nodeList[i]);
			}
		}
		
		/**
		 * 节点样式
		 */
		function getFontCss(treeId, treeNode){
			return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		}
		
		/**
		 * 显示单位树
		 */
		function showMenu() {
			var obj = $("#unitName");
			var oOffset = $("#unitName").offset();
			$("#menuContent").css({left:oOffset.left + "px", top:oOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
			$("body").on("mousedown", onBodyDown);
		}
		/**
		 * 隐藏单位树
		 */
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").off("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		intiSelectUnitTree : intiSelectUnitTree,
		isFullConditionSearch:function(){
			return fullSearch;
		}
	});	
	
})(jQuery);	