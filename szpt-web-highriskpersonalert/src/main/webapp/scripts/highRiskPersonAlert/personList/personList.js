(function($){
	"use strict";
	var table = null;
	var isApprove = false;
	
	var hongSeKey = $.common.Constant.YJLX_HONG_SE();
	var chengSeKey = $.common.Constant.YJLX_CHENG_SE();
	var huangSeKey = $.common.Constant.YJLX_HUANG_SE();
	var baiSeKey = $.common.Constant.YJLX_BAI_SE();
	var lanSeKey = $.common.Constant.YJLX_LAN_SE();
	var qitaKey = $.common.Constant.YJLX_QI_TA();
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		
		if($("#isApprove").val()==1){
			isApprove=true;
			$.icheck.check("#"+$.common.Constant.CZZT_DSP(),true);
		}
		initTable();
		findCreatedPersonLately();
	
		/**
		 * 搜索按钮事件
		 */
		$(document).on("click","#seach",function(){
			table.draw(true);
		});
		/**
		 * 重置按钮事件
		 */
		$(document).on("click","#reset",function(){
			resetSeachCondition();
			table.draw(true);
		});

		$(document).on("click",".detail",function(){
			var idcode = $(this).attr("idcode");
			window.location.href = context +"/personDetail/showPersonDetailPage.action?clickOrder=" + clickOrder + "&&clickListOrder=0&&idcode=" + idcode 
									+ "&&url=" + window.location.href;
		});
		$(document).on("click",".track",function(){
			var idcode = $(this).attr("idcode");
			window.location.href = context +"/trackAnalyze/showTrackAnalyzePage.action?clickOrder=" + clickOrder + "&&clickListOrder=0&&idcode=" + idcode
									+ "&&url=" + window.location.href;
		});
		$(document).on("click",".heatAnalyze",function(){
			var idCode = $(this).attr("idcode");
			var peopleTypeName = $(this).attr("valRylx");
			var map = {
				"idCode":idCode,
				"propleTypeName":peopleTypeName
			}
			var data = new Object();
			var queryStr = $.util.toJSONString(map);
			$.util.objToStrutsFormData(queryStr,"queryStr",data);
			var form = $.util.getHiddenForm(context +'/heatAnalyze/jumpHeatAnalyzePage.action?clickOrder=' + clickOrder + '&&clickListOrder=0&&url=' + window.location.href,data);
			$.util.subForm(form);
			//window.location.href = context +"/heatAnalyze/showHeatAnalyzePage.action?clickOrder=5&&clickListOrder=0&&rylx=" + rylx;
		});
		$(document).on("click",".apply",function(){
			var idcode = $(this).attr("idcode");
			applyApprove(idcode);
		});
		$(document).on("click",".approve",function(){
			var idcode = $(this).attr("idcode");
			approve(idcode);
		});
		$(document).on("click",".edit",function(){
			var idcode = $(this).attr("idcode");
			addPerson(idcode);
		});
		$(document).on("ifClicked","#ableCheck",function(){
			if(this.checked){
				$.icheck.able(".disCheck",false);
				$.icheck.check(".disCheck",false);
			}else{
				$.icheck.able(".disCheck",true);
				$.icheck.check(".xsqkCheck",true);
			}
		});
		
		$(document).on("ifClicked","#all",function(){
			if(this.checked){
				$.icheck.able(".disCheck",false);
				$.icheck.check(".typeCheck",false);
			}else{
				$.icheck.able(".disCheck",true);
				$.icheck.check(".typeCheck",true);
			}
		});
		$(document).on("ifClicked","#allCzzt",function(){
			if(this.checked){
				$.icheck.check(".czztCheck",false);
			}else{
				$.icheck.check(".czztCheck",true);
			}
		});
		$(document).on("click","#add",function(){
			addPerson(null);
		});
		
		//刑事前科(旧)选中事件
		$(document).on("ifChecked", "#xsqkCheckbox", function(){	
			$.icheck.able("#xsqkType .xsqkTypeChe",true);
			$.each( $("#xsqkType .xsqkTypeChe"), function(e,m){
				if($(m).attr("valLxName") == "抢劫案" || $(m).attr("valLxName") == "抢夺案" || $(m).attr("valLxName") == "盗窃案"){
					$(m).iCheck('check');
				}
			} ); 
		})
		
		
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
		//审批状态取消选中事件
		$(document).on("ifUnchecked", ".czztCheck", function(e){
			$("#allCzzt").iCheck('uncheck');
		})
	});
	function init(){
		yjlxType($.szpt.util.businessData.getDic_yjlxLst());//预警类型
		initRyqk($.szpt.util.businessData.getDic_yjlxLst());
		rylbType($.szpt.util.businessData.getDic_rylxLst());//人员类别
		xsqkType($.szpt.util.businessData.getDic_qklxLst());//刑事前科
		if(currentUnitCode != "520199310000"){
			$("#examineStateDiv").hide();
		}
	}
	/**
	 * 初始化五色预警人员列表table
	 */
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context + "/highriskPerson/queryPersonList.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "10%",
				"title" : "姓名",
				"data" : "name",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 1,
				"width" : "15%",
				"title" : "身份证号",
				"data" : "idcode",
				"render" : function(data, type, full, meta) {
					return data
				}
			},
			{
				"targets" : 2,
				"width" : "10%",
				"title" : "预警类型",
				"data" : "warnTypeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "30%",
				"title" : "人员类型",
				"data" : "peopleTypeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 4,
				"width" : "5%",
				"title" : "积分",
				"data" : "accumulatePoints",
				"render" : function(data, type, full, meta) {
					var d = '<a href="javascript:void(0);" target="_blank" class="font16 color-orange1">'+data+'</a>';
					return d;
				}
			},
			{
				"targets" : 5,
				"width" : "7%",
				"title" : "状态",
				"data" : "operateStatusName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 6,
				"width" : "20%",
				"title" : "操作",
				"data" : "idcode",
				"render" : function(data, type, full, meta) {
					var a='';
					if(full.operateStatusName==""||full.operateStatus==$.common.Constant.CZZT_SPTG()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
						a+='<button class="btn btn-bordered btn-xs heatAnalyze" valRylx="'+full.peopleTypeName+'" idcode="'+data+'">人案时空分析</button>';
						a+='<button class="btn btn-bordered btn-xs track" idcode="'+data+'">轨迹分析</button>';
					}else if(!isApprove&&full.operateStatus==$.common.Constant.CZZT_XZ()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
						a+='<button class="btn btn-bordered btn-xs edit" idcode="'+data+'">修改</button>';
						a+='<button class="btn btn-bordered btn-xs apply" idcode="'+data+'">提交审批</button>';
					}else if(isApprove&&full.operateStatus==$.common.Constant.CZZT_XZ()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
					}else if(!isApprove&&full.operateStatus==$.common.Constant.CZZT_DSP()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
					}else if(!isApprove&&full.operateStatus==$.common.Constant.CZZT_SPBH()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
						a+='<button class="btn btn-bordered btn-xs edit" idcode="'+data+'">修改</button>';
					}else if(isApprove&&full.operateStatus==$.common.Constant.CZZT_SPBH()){
						a+='<button class="btn btn-bordered btn-xs detail" idcode="'+data+'">查看详情</button>';
					}
					return a;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 10 ];
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
		//请求参数
		tb.paramsReq = function(d, pagerReq){
            d["highriskPersonBean.name"] = $("#name").val();
            d["highriskPersonBean.idcode"] = $("#idCode").val();
            var warnType = "";
			$(".warnType").each(function(i,val){
				if($(val).hasClass("selected")){
					warnType += $(val).attr("valName")+",";
				}
			});
            var peopleType="";
			var types=$.icheck.getChecked("type");
			if(types!=""){
				$.each(types,function(i,val){
					peopleType+=$(val).attr("valName")+',';
				});
			}
			var czzt="";
			var czzts=$.icheck.getChecked("czzt");
			if(czzts!=""){
				$.each(czzts,function(i,val){
					czzt+=val.value+',';
				});
			}
//			if(currentUnitCode != "520199310000"){
//				czzt= $.common.Constant.CZZT_SPTG();
//			}else {
//				if(czzt == ""){
//					$.each($(".czztCheck"),function(i,val){
//						czzt+=val.value+',';
//					});
//				}
//			}
            d["highriskPersonBean.warnType"] = warnType.substring(0,warnType.length-1);
            d["highriskPersonBean.peopleType"] = peopleType;
            d["highriskPersonBean.operateStatus"] = czzt;
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.highriskPersonBeanPager.totalNum;
			json.recordsFiltered = json.highriskPersonBeanPager.totalNum;
			json.data = json.highriskPersonBeanPager.pageList;
		};
		tb.rowCallback = function(row,data, index) {
			$($(row).children("td")).each(function(i, val){
			if(i==4){
				$(val).on("click", function(){		
					var tr = $(this).parents("tr");
					var row=table.row(tr);	//行对象
					var data=row.data();	//行所有数据
					var id=data.id;
					$.layerAlert.dialog({
						content :context+'/highriskPerson/showIntegralInfo.action',
						pageLoading : true,
						width : "80%",
						height : "100%",
						title:"积分详情",
						shadeClose : false,
						btn:"关闭",
						success:function(layero, index){
							
						},
						btn1:function(index, layero){
					 		layer.close(index);	//关闭层
					    },
					    initData:{
			    			infoData : data
			    		},
					});
				})
			}
		});
		};
		table = $("#table").DataTable(tb);
	}
	
	/**
	 * 重置查询条件
	 */
	function resetSeachCondition(){
		$("#name").val("");
		$("#idCode").val("");
		$(".warnType").removeClass("selected");
		$(".sq-all").removeClass("selected");
		$.icheck.able(".disCheck",false);
		$.icheck.check(".icheckbox",false);
	}
	
	/**
	 * 查找最新创建的5个重点人信息
	 */
	function findCreatedPersonLately(){
		$.ajax({
			url:context +'/highriskPerson/findCreatedPersonLately.action',
			type:'post',
			dataType:'json',
			data:{},
			success:function(successData){
				$("#newAddPersonUl").html("");
				$.each(successData.highriskPersonBeanList,function(i,val){
					if(val.name != null){
						var a = '<a idcode="'+val.idcode+'" href="#" class="list-group-item newAddPersonA detail"><span class="badge m-badge">'+$.date.timeToStr(val.createdTime, "yyyy/MM/dd")+'</span>'+val.name+'</a>';
						$("#newAddPersonUl").append(a);
					}
				});
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 按预警类型统计重点人员数量
	 */
	function sumupByWarnType(){
		$.ajax({
			url:context +'/highriskPerson/sumupByWarnType.action',
			type:'post',
			dataType:'json',
			data:{},
			success:function(successData){
				var statisticsInfoList = successData.statisticsInfoList;
			
				var sum = 0;
				$("#"+hongSeKey).text(statisticsInfoList[0]==null?"0人":statisticsInfoList[0].value + "人");
				$("#"+chengSeKey).text(statisticsInfoList[1]==null?"0人":statisticsInfoList[1].value + "人");
				$("#"+huangSeKey).text(statisticsInfoList[2]==null?"0人":statisticsInfoList[2].value + "人");
				$("#"+baiSeKey).text(statisticsInfoList[3]==null?"0人":statisticsInfoList[3].value + "人");
				$("#"+lanSeKey).text(statisticsInfoList[4]==null?"0人":statisticsInfoList[4].value + "人");
				$("#"+qitaKey).text(statisticsInfoList[5]==null?"0人":statisticsInfoList[5].value + "人");
				sum+=statisticsInfoList[0]==null?0:parseInt(statisticsInfoList[0].value);
				sum+=statisticsInfoList[1]==null?0:parseInt(statisticsInfoList[1].value);
				sum+=statisticsInfoList[2]==null?0:parseInt(statisticsInfoList[2].value);
				sum+=statisticsInfoList[3]==null?0:parseInt(statisticsInfoList[3].value);
				sum+=statisticsInfoList[4]==null?0:parseInt(statisticsInfoList[4].value);
				sum+=statisticsInfoList[5]==null?0:parseInt(statisticsInfoList[5].value);
				$("#sum").text(sum);
			},
			error:function(errorData){
				
			}
		});
	}
	function addPerson(idcode){
		window.top.$.layerAlert.dialog({
			content : context +  '/personDetail/showPersonEditPage.action',
			pageLoading : true,
			title:"新增高危人",
			width : "650px",
			height : "95%",
			btn:["保存","提交审批","返回"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.save();
				},
				btn2:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.apply();
				},
				btn3:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    		},
    		initData:{
    			personId : null,
    			editIdcode:idcode
    		},
    		end:function(){
    		}
		});
	}
	function applyApprove(idcode){
		$.ajax({
			url:context +'/highriskPerson/applyApprove.action',
			type:'post',
			dataType:'json',
			data:{idcode:idcode},
			success:function(successData){
				table.draw(false);
			},
			error:function(errorData){
				
			}
		});
	}
	function approve(idcode){
		window.top.$.layerAlert.dialog({
			content : context +  '/highriskPerson/showApprovePersonPage.action',
			pageLoading : true,
			title:"新增高危人审批",
			width : "650px",
			height : "95%",
			btn:["通过","驳回"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.pass();
				},
				btn2:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.noPass();
				}
			},
			shadeClose : false,
    		success:function(layero, index){
    		},
    		initData:{
    			idcode:idcode
    		},
    		end:function(){
    		}
		});
	}
	//加载所有的预警类型
	function yjlxType(allYjlxType){
		if(null != allYjlxType && allYjlxType.length > 0){
			for(var i in allYjlxType){
				if(allYjlxType[i].code == hongSeKey){
					$("#warning-type").append('<a href="#" valName="'+hongSeKey+'" class="warnType btn sq-red"  title="红色"></a>')
				}else if(allYjlxType[i].code == chengSeKey){
					$("#warning-type").append('<a href="#" valName="'+chengSeKey+'"  class="warnType btn sq-orange" title="橙色"></a>')
				}else if(allYjlxType[i].code == huangSeKey){
					$("#warning-type").append('<a href="#"  valName="'+huangSeKey+'" class="warnType btn sq-yellow" title="黄色"></a>')
				}else if(allYjlxType[i].code == baiSeKey){
					$("#warning-type").append('<a href="#"  valName="'+baiSeKey+'" class="warnType btn sq-white" title="白色"></a>')
				}else if(allYjlxType[i].code == lanSeKey){
					$("#warning-type").append('<a href="#"  valName="'+lanSeKey+'" class="warnType btn sq-blue" title="蓝色"></a>')
				}else if(allYjlxType[i].code == qitaKey){
					$("#warning-type").append('<a href="#"  valName="'+qitaKey+'" class="warnType btn sq-other" title="其它"></a>')
				}
			}
		}
	}
	
	function initRyqk(allYjlxType){
		if(null != allYjlxType && allYjlxType.length > 0){
			for(var i in allYjlxType){
				if(allYjlxType[i].code == hongSeKey){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-danger">'
							+'<span id="'+hongSeKey+'" class="badge m-badge">0人</span>红色</li>')
				}else if(allYjlxType[i].code == chengSeKey ){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-warning" style="background-color: #ffe4ce; color: #e26500;">'
							+'<span id="'+chengSeKey+'" class="badge m-badge">0人</span>橙色</li>')
				}else if(allYjlxType[i].code == huangSeKey){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-warning">'
							+'<span id="'+huangSeKey+'" class="badge m-badge">0人</span>黄色</li>')
				}else if(allYjlxType[i].code == baiSeKey){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-warning" style="background: #fff; color: #666">'
							+'<span id="'+baiSeKey+'" class="badge m-badge">0人</span>白色</li>')
				}else if(allYjlxType[i].code == lanSeKey){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-info">'
							+'<span id="'+lanSeKey+'" class="badge m-badge">0人</span>蓝色</li>')
				}else if(allYjlxType[i].code == qitaKey){
					$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-info" style="background-color: #b7b6e9;  color:#7673EF;">'
							+'<span id="'+qitaKey+'" class="badge m-badge">0人</span>其他</li>')
				}
			}
			$("#yjlxRyqkUl").append('<li class="list-group-item list-group-item-success"><span class="badge m-badge">'
					+'<span id="sum" style="font-size: 16px; font-weight: bold; margin-right: 3px;">0</span>人</span>合计人</li>')
		}
		sumupByWarnType();
	}
	//加载所有刑事前科
	function xsqkType(allXsqkType){
		if(null != allXsqkType && allXsqkType.length > 0){
			for(var i in allXsqkType){
				$("#xsqkType").append('<p class="col-xs-1" style="width: 100px"><input type="checkbox" name="xsqkTypeChe" valName="'+allXsqkType[i].code+'" valLxName="'+allXsqkType[i].name+'" name="type" class="xsqkTypeChe icheckbox">'+allXsqkType[i].name+' </p>');
			}
		}
		$.icheck.able("#xsqkType .xsqkTypeChe",false);
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
					$("#otherRylbType").append('<p class="col-xs-1" style="width: 150px"><input type="checkbox" valName="'+allRylbType[i].code+'" name="type" class="icheckbox">'+allRylbType[i].name+' </p>')
				}
			}
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		refresh : function(){
			table.draw();
			findCreatedPersonLately();
		}
	});	
})(jQuery);