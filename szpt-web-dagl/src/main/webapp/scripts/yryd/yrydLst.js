$.dagl = $.dagl || {} ;
$.dagl.yryd = $.dagl.yryd || {} ;
(function($){
	"use strict";
	
	var fullSearchFlag = false;
	var table;
	
	$(document).ready(function(){
		
		//点击查询
		$(document).on("click", "#searchBtn", function(){
			creatTable();
		});
		
		$(document).on("click", "#concernBtn", function(){
			concernBtnClick();
		});
		
	});
	
	$(document).ready(function(){
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		$(document).on("click", "#topthumbslider .thumbslider-next", function(){
			var obj = $.thumbslider.getSilder("topthumbslider") ;
			obj.next() ;
		});
		
		$(document).on("click", "#topthumbslider .thumbslider-prev", function(){
			var obj = $.thumbslider.getSilder("topthumbslider") ;
			obj.prev() ;
		});
		
	});
	
	//点击高级
	$(document).on("click", "#more", function(e){
		if(fullSearchFlag){
			$(".more").hide();
			fullSearchFlag = false;
		}else{
			$(".more").show();
			fullSearchFlag = true;
		}
	});
	
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
					$("#warning-type").append('<a href="#" valName="0"  class="warnType btn sq-red"  title="红色"></a>')
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
	
	
	$(document).on("click", ".attention", function(e){
		var id = $(this).attr("caseCode");
		window.location.href = $.util.fmtUrl(context+"/yryd/showWdgzPage.action");
	});
	
	function creatTable(){
		var hiPersonTemplate = $("#hiPersonalInfo");
		var commonPersonTemplate = $("#commonPersonInfo");
		var i = 0;
		var sum;
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/yryd/findPersonInfo.action',
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "100%",
					"title" : "",
					"data" : "id",//置顶信息
					"render" : function(data, type, full, meta) {
						var alertlevel = "";
						if(full.alertlevel == "0"){
							alertlevel += '<span class="btn warningType sq-red" title="红色"></span>';
						}else if(full.alertlevel == "1"){
							alertlevel += '<span class="btn warningType sq-orange" title="橙色"></span>';
						}else if(full.alertlevel == "2"){
							alertlevel += '<span class="btn warningType sq-yellow" title="黄色"></span>';
						}else if(full.alertlevel == "3"){
							alertlevel += '<span class="btn warningType sq-white" title="白色"></span>';
						}else if(full.alertlevel == "4"){
							alertlevel += '<span class="btn warningType sq-blue" title="蓝色"></span>';
						}else if(full.alertlevel == "5"){
							alertlevel += '<span class="btn warningType sq-other" title="其他"></span>';
						}
						if (full.type == "是" || full.sfsXyy == "1") {
							var objTable = hiPersonTemplate.clone(true);
							objTable.show();
							$.each(objTable.find(".valCell"), function() {
								$(this).text(full[$(this).attr("valName")]);
								if ($(this).attr("val") == "btn") {
									$(this).attr("valId", full["idcard"]);
									$(this).attr("valType", full["type"]);
									$(this).attr("valxyr", full["sfsXyy"]);
								}
							})
							objTable.find(".thumbslider").attr("id",
									"topthumbslider" + i);
							objTable.find(".warning-type").html(alertlevel);
							imgCarousel(full.idcard, i);
							
							if (full.sfsXyy != "1") {
								objTable.find(".xyr").hide();
							}
							if(full.sfbGz){
								objTable.find(".concern").show();
								objTable.find(".noConcern").hide();
							}else{
								objTable.find(".concern").hide();
								objTable.find(".noConcern").show();
							}
						} else {
							var objTable = commonPersonTemplate.clone(true);
							objTable.show();
							$.each(objTable.find(".valCell"), function() {
								$(this).text(full[$(this).attr("valName")]);
								if ($(this).attr("val") == "btn") {
									$(this).attr("valId", full["idcard"]);
									$(this).attr("valType", full["type"]);
									$(this).attr("valxyr", full["sfsXyy"]);
								}
							})
//							objTable.find(".warning-type").html(alertlevel);
//							if (full.sfsXyy != "1") {
//								objTable.find(".xyr").hide();
//							}
							if(full.sfbGz){
								objTable.find(".concern").show();
								objTable.find(".noConcern").hide();
							}else{
								objTable.find(".concern").hide();
								objTable.find(".noConcern").show();
							}
						}
						i++;
						return objTable.html();
					}
				}
		];
		tb.ordering = false;
		tb.paging = true; //分页是否
		tb.hideHead = true; //是否隐藏表头
 		tb.bProcessing = true;//是否显示loading效果
		tb.dom = null; 
		tb.searching = false; //是否有查询输入框
		tb.lengthChange = false; //是否可以改变每页显示条数
		tb.info = true; //是否显示详细信息
		tb.lengthMenu = [ 10 ]; //每页条数
		tb.paramsReq = function(d, pagerReq){ //传入后台的请求参数
			var data;
			if(fullSearchFlag == true){
				data = {
					"persontypecode":allRylbSelectedType(),	
					"alertlevel":allYjlxSelectedType(),
					"criminaltypecode":allXsqkSelectedType(),
					"text":$("#idcard").val(),
					"start":d.start,
					"pageSize":d.length
				}
			}else{
				data = {
					"text":$("#idcard").val(),
					"start":d.start,
					"pageSize":d.length
				}
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.result.totalNumber;
			json.recordsFiltered = json.resultMap.result.totalNumber;
			json.data = json.resultMap.result.pageList;
			if (json.resultMap.result) {
				$("#tableTotal").text(json.resultMap.result.totalNumber)
			} else {
				$("#tableTotal").text(0)
			}
			
		};
		tb.initComplete = function(){ //表格加载完成后执行的函数
		}
		table = $("#personTable").DataTable(tb);//在哪个table标签中展示这个表格
	}
	

	//点击查看详情
	$(document).on("click", ".showDetailsBtn", function(e){
		var idcard = $(this).attr("valId");
		var type = $(this).attr("valType");
		var xyr = $(this).attr("valxyr");
		if(type=="是" || xyr=="1"){
			sessionStorage.setItem("yrydHighriskPersonDetailBackUrl", window.location.href);
			window.top.location.href = context + "/yryd/showYrydHighriskPersonAlertPage.action?clickOrder=1&&clickListOrder=0&&idcard="+idcard;
		}else{
			sessionStorage.setItem("yrydOrdinaryPersonDetailBackUrl", window.location.href);
			window.top.location.href = context + "/yryd/showYrydOrdinaryPersonPage.action?clickOrder=1&&clickListOrder=0&&idcard="+idcard;
		}
	});
	
	//图片轮播下一张
	$(document).on("click", ".thumbslider-next", function(){
		var id = $($(this).parents(".thumbslider")[0]).attr("id") ;
		var obj = $.thumbslider.getSilder(id) ;
		obj.next() ;
	});

	//图片轮播上一张
	$(document).on("click", ".thumbslider-prev", function(){
		var id = $($(this).parents(".thumbslider")[0]).attr("id") ;
		var obj = $.thumbslider.getSilder(id) ;
		obj.prev() ;
	});
	
	//圖片輪播
	function imgCarousel(idcard,str){
		var data = {
				"idcard": idcard,	
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yryd/findHiPersonAvatarById.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data  = successDate.resultMap.result
				var imgSrcs = [] ;
				for(var i in data){
					imgSrcs.push($("<img />", {
						"src":'data:image/jpg;base64,'+ data[i]
					}))
				}
				var settings = {
						"id":"topthumbslider" + str,
						"tabDoms":imgSrcs
				};
				$.thumbslider.initOrRefresh(settings) ;	
			}
		})
	}
	
	
	
})(jQuery);	