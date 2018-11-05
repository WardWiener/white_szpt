(function($){
	//拿到请求参数
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var caseId = initData.caseId;
	var caseName = initData.caseName;
	
	var table = null;
	$(document).ready(function(){
		init();
	});
	
	//发表留言
	$(document).on("click", "#publishBtn", function(){
		
		var demo = $.validform.getValidFormObjById("contentDiv") ;
		//是否有没通过的验证
		var flag = $.validform.check(demo) ;
		//是给予提示  
		if(!flag){
			return;
		}
		var content = $("#contentText").val();
		
		var data = {
				"caseId" : caseId,
				"content" : content
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/addLiuYan.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				if(successDate.resultMap.result == true){
					$.util.topWindow().$.layerAlert.alert({msg:"发表成功",title:"提示"});
					$("#contentText").val("");
					loadTableData();
				}else{
					$.util.topWindow().$.layerAlert.alert({msg:"发表失败",title:"提示"});
				}
			}
		})
	})

	//取消
	$(document).on("click", "#cancelBtn", function(){
		$("#contentText").val("");
		
	})
	
	//删除
	$(document).on("click", "#deleteLiuYanBtn", function(){
		var liuYanIdAndPersonIdLst = [];
		var arr = $.icheck.getChecked("idCheck");
		for (var i = 0; i < arr.length; i++) {
			var liuYanIdAndPersonId = {
					"liuYanId" : $(arr[i]).attr("valId"),
					"personId" : $(arr[i]).attr("valPersonId")
			}
			liuYanIdAndPersonIdLst.push(liuYanIdAndPersonId);
		}
		if(liuYanIdAndPersonIdLst.length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"至少选择一条留言",title:"提示"});
			return;
		}
		var data = {
				"liuYanIdAndPersonIdLst" : liuYanIdAndPersonIdLst
			}
			var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/deleteLiuYan.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:successDate.resultMap.result,title:"提示"});
				loadTableData();
			}	
		})
	})
	//刷新
	$(document).on("click", "#refreshBtn", function(){
		location.reload();
	})
	
	function init(){
		showTitleCaseName(caseName)
		loadTableData();
	}
	function showTitleCaseName(caseName){
		$("#titleH2").empty();
		$("#titleH2").text(caseName)
	}
	
	//加载我的留言数据
	function loadTableData(){
		var data = {
				"caseId" : caseId
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findLiuYanByPersonIdAndCaseId.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				creatTable(successDate.resultMap.result);
			}
		})
	}
	
	
	
	//创建我的留言表格
	function creatTable(tableInfoLst){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = tableInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "8%",
					"title" : "",
					"data" : "id",//置顶信息
					"render" : function(data, type, full, meta) {
						return  '<input type="checkbox" valId="'+data+'" valPersonId="'+full.personId+'" name="idCheck" class="icheckbox">';
					}
				},{
					"targets" : 1,
					"width" : "8%",
					"title" : "序号",
					"data" : "",
					"render" : function(data, type, full, meta) {
						return  meta.row+1;
					}
				},
				{
					"targets" : 2,
					"width" : "25%",
					"title" : '姓名',
					"data" : "personName",
					"render" : function(data, type, full, meta) {
						return '<div class="pull-left mar-right">'
						+'<a href="#" target="_blank"><img '
						+'src="../images/man/photo.png" width="50" height="50"></a></div>'
						+'<h2 class="row-mar font14">'+data+'</h2>'
						+'<p class="font11 color-gray">'+full.department+'</p>';
					}
				},
				{
					"targets" : 3,
					"title" : "留言内容",
					"data" : "context",
					"render" : function(data, type, full, meta) {
						return '<p>'+full.creatTime+'</p>'+data;
					}
				}
		];
		tb.ordering = false;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 2 ];
		table = $("#tavleId").DataTable(tb);
	}
	

})(jQuery);
