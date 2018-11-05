$.dagl = $.dagl || {} ;
$.dagl.wdgz = $.dagl.wdgz || {} ;
(function($){
	"use strict";
	
	var fullSearchFlag = false;
	var table = null;
	$(document).ready(function(){
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		initLoadTable();
	});
	
	//查询
	$(document).on("click", "#search", function(e){
		searchClick();
	});
	
	function searchClick(){
		var data = {};
		if(fullSearchFlag){
			data={
					"name":$("#name").val(),
					"idNum":$("#idNum").val(),
					"warnType":allYjlxSelectedType(),
					"rylb":allRylbSelectedType(),
					"xsqk":allXsqkSelectedType(),
					"caseName":$("#caseName").val(),
					"caseCode":$("#caseCode").val()
			}
		}else{
			data={
					"name":$("#name").val(),
					"idNum":$("#idNum").val(),
			}
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/wdgz/findInterestedPersonList.action',
			type:"post",
			data:dataMap,
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				initTable(successDate.resultMap.result);
			}
		})
	}
	
	//重置
	$(document).on("click", "#reset", function(e){
		location.reload(); 
	});
	
	//点击加载更多
	$(document).on("click", "#more", function(e){
		if(fullSearchFlag){
			$(".more").hide();
			fullSearchFlag = false;
		}else{
			$(".more").show();
			fullSearchFlag = true;
		}
	});
	
	//置顶
	$(document).on("click", ".inTop", function(e){
		var data = {
				"id":$(this).attr("valName")
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/wdgz/stickInterestedPerson.action',
			type:"post",
			data:dataMap,
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				if(successDate.resultMap.result == $.common.DICT.DICT_NO){
					window.top.$.layerAlert.alert({msg:"置顶个数不能大于3个,置顶失败!",title:"提示"});
				}else{
					window.top.$.layerAlert.alert({msg:"置顶成功!",title:"提示",end:function(){
						searchClick();
					}});
				}
			}
		});
	})
	//取消置顶
	$(document).on("click", ".unTop", function(e){
		var data = {
				"id":$(this).attr("valName")
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/wdgz/unstickInterestedPerson.action',
			type:"post",
			data:dataMap,
			contentType: "application/json; charset=utf-8",
			success:function(successDate){
					window.top.$.layerAlert.alert({msg:"取消成功!",title:"提示",end:function(){
						searchClick();
					}});
			}
		});
	});
	
	//取消关注
	$(document).on("click", ".unAttention", function(e){
		$.ajax({
			url:context +'/wdgz/cancelInterestedPerson.action',
			type:"post",
			data:{
				id:$(this).attr("valName")
			},
			dataType:"json",
			success:function(){
				window.top.$.layerAlert.alert({msg:"取消成功!",title:"提示",end:function(){
					searchClick();
				}});
			}
		})
	});
	
	//查看详情
	$(document).on("click" , ".showDetails", function(){
		var idcard = $(this).attr("valName");
		var type = $(this).attr("valGwr");
		if(type){
			sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
			window.top.location.href = context + "/yryd/showYrydHighriskPersonAlertPage.action?clickOrder=1&&clickListOrder=0&&idcard="+idcard;
		}else{
			sessionStorage.setItem("yrydOrdinaryPersonDetailBackUrl", window.location.href);
			window.top.location.href = context + "/yryd/showYrydOrdinaryPersonPage.action?clickOrder=1&&clickListOrder=0&&idcard="+idcard;
		}
	});
	function initLoadTable(){
		var data = {};
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/wdgz/findInterestedPersonList.action',
			type:"post",
			data:dataMap,
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				initTable(successDate.resultMap.result);
			}
		})
	}
	
	//页面初始化
	function init(){
		yjlxType($.szpt.util.businessData.getDic_yjlxLst());//预警类型
		rylbType($.szpt.util.businessData.getDic_rylxLst());//人员类别
		xsqkType($.szpt.util.businessData.getDic_qklxLst());//刑事前科
	}
	//加载所有刑事前科
	function xsqkType(allXsqkType){
		if(null != allXsqkType && allXsqkType.length > 0){
			for(var i in allXsqkType){
				$("#xsqkType").append('<p class="col-xs-1" style="width: 100px"><input type="checkbox" name="xsqkTypeChe" valName="'+allXsqkType[i].code+'" valLxName="'+allXsqkType[i].name+'" class="xsqkTypeChe icheckbox">'+allXsqkType[i].name+' </p>');
			}
		}
		$.icheck.able("#xsqkType .xsqkTypeChe",false);
	}
	
	//刑事前科(旧)选中事件
	$(document).on("ifChecked", "#xsqkCheckbox", function(){	
		$.icheck.able("#xsqkType .xsqkTypeChe",true);
		$.each( $("#xsqkType .xsqkTypeChe"), function(e,m){
			if($(m).attr("valLxName") == "抢劫案" || $(m).attr("valLxName") == "抢夺案" || $(m).attr("valLxName") == "盗窃案"){
				$(m).iCheck('check');
			}
		} ); 
	})
	
	//查询所有选中刑事前科
	function allXsqkSelectedType(){
		var data = [];
		$.each( $("#xsqkType .icheckbox"), function(){
			if(this.checked && $(this).attr("id") != "xsqkCheckbox"){
				data.push($(this).attr("valName"));
			}
		} );
		return data;
	}
	
	//加载所有的预警类型
	function yjlxType(allYjlxType){
		if(null != allYjlxType && allYjlxType.length > 0){
			for(var i in allYjlxType){
				if(allYjlxType[i].code == "0"){
					$("#warning-type").append('<a href="#" valName="0" class="warnType btn sq-red"  title="红色"></a>')
				}else if(allYjlxType[i].code == "1"){
					$("#warning-type").append('<a href="#" valName="1"  class="warnType btn sq-orange" title="橙色"></a>')
				}else if(allYjlxType[i].code == "2"){
					$("#warning-type").append('<a href="#"  valName="2" class="warnType btn sq-yellow" title="黄色"></a>')
				}else if(allYjlxType[i].code == "3"){
					$("#warning-type").append('<a href="#"  valName="3" class="warnType btn sq-white" title="白色"></a>')
				}else if(allYjlxType[i].code == "4"){
					$("#warning-type").append('<a href="#"  valName="4" class="warnType btn sq-blue" title="蓝色"></a>')
				}else if(allYjlxType[i].code == "5"){
					$("#warning-type").append('<a href="#"  valName="5" class="warnType btn sq-other" title="其它"></a>')
				}
			}
		}
	}
	

	//点击预警类型所有的选中
	$(document).on("click" , "#allYjlx", function(){
		if($(this).attr("class").indexOf("selected") == -1){
			$.each( $("#warning-type .warnType"), function(e,m){
				if($(m).attr("id") != "allYjlx"){
					if($(m).attr("class").indexOf("selected") == -1){
						$(m).attr("class" , $(m).attr("class")+ " selected");
					}
				}
			} ); 
		}else{
			$.each( $("#warning-type .warnType"), function(e,m){
				if($(m).attr("id") != "allYjlx"){
					if($(m).attr("class").indexOf("selected") != -1){
						var subStr = $(m).attr("class").substring(0 , $(m).attr("class").indexOf("selected")-1);
						$(m).attr("class",subStr);
					}
				}
			} ); 
		}
	})
	
	//预警按钮点击事件
	$(document).on("click" , ".warnType", function(){
		if($(this).attr("class").indexOf("selected") == -1){
			$(this).attr("class" , $(this).attr("class")+ " selected");
		}else{
			var subStr = $(this).attr("class").substring(0 , $(this).attr("class").indexOf("selected")-1);
			$(this).attr("class",subStr);
		}
		if($(this).attr("id") != "allYjlx"){
			if($("#allYjlx").attr("class").indexOf("selected") != -1){
				var subStr = $("#allYjlx").attr("class").substring(0 , $("#allYjlx").attr("class").indexOf("selected")-1);
				$("#allYjlx").attr("class",subStr);
			}
		}
	});
	
	//预警类型选中事件
	function allYjlxSelectedType(){
		var data = [];
		$.each( $("#warning-type a"), function(e,m){
			if($(m).attr("class").indexOf("selected") != -1){
				data.push($(m).attr("valName"));
			}
		} ) 
		return data;
	}

	//加载所有的人员类别
	function rylbType(allRylbType){
		if(null != allRylbType && allRylbType.length > 0){
			for(var i in allRylbType){
				if(allRylbType[i].code == "014"){
					//刑事前科
					var rylbName = allRylbType[i].name.substring(0,4); 
					$("#xsqkType").append('<p class="col-xs-1" style="width: 150px"><input type="checkbox" valName="'+allRylbType[i].code+'" class="icheckbox"  id="xsqkCheckbox">'+rylbName+'</p>')
				}else{
					$("#otherRylbType").append('<p class="col-xs-1" style="width: 150px"><input type="checkbox" valName="'+allRylbType[i].code+'"  class="icheckbox">'+allRylbType[i].name+' </p>')
				}
			}
		}
	}
	
	//点击人员类别中的全部选型时
	$(document).on("ifChecked", "#allRylb", function(e){		
		$.each( $("#rylbDiv .icheckbox"), function(e,m){
			if($(m).attr("id") != "allRylb"){
				$(m).iCheck('check');
			}
		} ); 
	});
	$(document).on("ifUnchecked", "#allRylb", function(e){		
		$.each( $("#rylbDiv .icheckbox"), function(e,m){
				$(m).iCheck('uncheck');
		} ); 
	});
	
	$(document).on("ifUnchecked", "#rylbDiv .icheckbox", function(e){
		var obj = this;
		if($("#allRylb")[0].checked && $(this) != $("#allRylb")){
			$("#allRylb").iCheck('uncheck');
			$.each( $("#rylbDiv .icheckbox"), function(e,m){
				if(m !=obj &&  m != $("#allRylb")[0]){
					$(m).iCheck('check');
				}
			});
			if($(obj).hasClass("xsqkTypeChe")){
				$(this).iCheck('uncheck');
			}
		}
		if(this == $("#xsqkCheckbox")[0]){
			$("#xsqkType .xsqkTypeChe").iCheck('uncheck');
			$.icheck.able("#xsqkType .xsqkTypeChe",false);
		}
	})
	
	
	//查询所有的人员类别选中项
	function allRylbSelectedType(){
		var data = [];
		$.each( $("#otherRylbType .icheckbox"), function(){
			if(this.checked){
				data.push($(this).attr("valName"))
			}
		} ) 
		return data;
	}
	//表格
	function initTable(tableInfoLst){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = tableInfoLst;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "10%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						var str = "";
						if(full.stick){
							str = str + '<span class="glyphicon glyphicon-flag color-red1 mar-left"></span>';
						}
						if(full.xyr){
							str = str + '<span class="icon-xyren mar-left-sm" title="嫌疑人"></span>';
						}
						return data + str;
					}
				},
				{
					"targets" : 1,
					"width" : "20%",
					"title" : '身份证号',
					"data" : "idCard",
					"render" : function(data, type, full, meta) {
						return "<span class='idCard'>" +data+"</span>";
					}
				},
				{
					"targets" : 2,
					"width" : "10%",
					"title" : '预警类型',
					"data" : "yjlx",
					"render" : function(data, type, full, meta) {
						if(data == "0"){
							return '<div class="warning-type"><span class="btn sq-red" title="红色"></span></div>';
						}else if(data == "1"){
							return '<div class="warning-type"><span class="btn sq-orange" title="橙色"></span></div>';
						}else if(data == "2"){
							return '<div class="warning-type"><span class="btn sq-yellow" title="黄色"></span></div>';
						}else if(data == "3"){
							return '<div class="warning-type"><span class="btn sq-white" title="白色"></span></div>';
						}else if(data == "4"){
							return '<div class="warning-type"><span class="btn sq-blue" title="蓝色"></span></div>';
						}else if(data == "5"){
							return '<div class="warning-type"><span class="btn sq-other" title="其他"></span></div>';
						}else{
							return "";
						}
					}
				},
				{
					"targets" : 3,
					"width" : "20%",
					"title" : '人员类别',
					"data" : "rylb",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "10%",
					"title" : '积分',
					"data" : "jf",
					"render" : function(data, type, full, meta) {
						if(!data || data == "" ){
							return "";
						}
						return '<a href="#" target="_blank" class="font16 color-orange1">'+data+'</a>';
					}
				},
				{
					"targets" : 5,
					"title" : '操作',
					"data" : "stick",
					"render" : function(data, type, full, meta) {
						var topClass = "";
						var top = "";
						if(data == true){
							topClass = "unTop";
							top = "取消置顶";	
						}else{
							topClass = "inTop";
							top = "置顶";
						}
						return '<button valName="'+full.idCard+'" valGwr="'+full.gwr+'" class="showDetails btn btn-bordered btn-xs">查看详情</button><button valName="'+full.id+'" class="unAttention btn btn-bordered btn-xs">取消关注</button><button valName="' + full.id + '" class="' + topClass + ' btn btn-bordered btn-xs">'+top+'</button>';
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 10 ];
		table = $("#concernInfo").DataTable(tb);
	}
	
	
})(jQuery);



