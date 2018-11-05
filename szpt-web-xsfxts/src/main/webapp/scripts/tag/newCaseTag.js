$.newCaseTag = $.newCaseTag || {} ;

(function($){
	
	"use strict";
	
	var id;
	var latitude;
	var longitude;
	
	var caseTagAjlbDicItem ;
	
	$(document).ready(function(){
		//获取案件编号
		id = $("#paraId").val();
		//初始化页面案件类别三级字典项
		initCaseTagAjlb();
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
		//设置案件相关信息
		$.lookCriminalBasicCase.caseTagQueryCaseRelatedInfoByCaseCode(id);
		
		/**
		 * 发案地点
		 */
		$(document).on("click" , ".showMap", function(e){
			alertMapPage();
		});
		
		/**
		 * 返回
		 */
		$(document).on("click" , "#back", function(e){
			history.back();
		});
		
		/**
		 * 保存
		 */
		$(document).on("click", "#save", function(e){
			saveOrUpdate();
		});
	});
	
	/**
	 * 初始化页面案件类别相关字典项
	 */
	function initCaseTagAjlb(){
		var dicItemSettings = {
			id:"caseTagAjlb",
			dicTypeCode : $.common.DICT.DICT_TYPE_AJXZ
		};
		caseTagAjlbDicItem = $.cascadedDicItem.init(dicItemSettings);
		
		var selects = [{
			selector : "#type",
			selectedCode : null
		},
		{
			selector : "#firstSort",
			selectedCode : null,
			selectEventCallback : function(select){
				
			},
			unselectEventCallback : function(select){
				
			}
		},
		{
			selector : "#secondSort",
			selectedCode : null
		}]
		
		caseTagAjlbDicItem.refreshBySelectedCodes(selects);
	}
	
	/**
	 * 初始化页面控件
	 * 通过ajax一次查询所有选项的数据列表，初始化页面控件
	 */
	function init(){
		$.szpt.util.businessData.initAjjbSelect("#level");
		$.szpt.util.businessData.initCountrySelect("#community");
		$.szpt.util.businessData.initFacsSelect("#occurPlace");
		$.szpt.util.businessData.initZajckSelect("#entrance");
		$.szpt.util.businessData.initZajckSelect("#exit");
		$.ajax({		   
			url:context +'/caseTag/initCaseTagDict.action',
			dataType:"json",
			type:"post",
			success:function(obj){
				$.select2.addByList("#placeType",obj.resultMap["sacs"],"code","name",true,true);
				var rs = obj.resultMap["zars"];
				var sj = obj.resultMap["time"];
				var td = obj.resultMap["ted"];
				for( var i in rs){
					$("#zars").append('<p class="col-xs-6"><input id="zars'+rs[i].code+'"type="radio" style="margin: 3px 3px 5px 5px;" name="peopleNum" class="form-val icheckbox" value="'+rs[i].code+'"><label for="zars'+rs[i].code+'">' +rs[i].name +'</label></p>')
				}
				
				for( var i in sj){
					$("#zasj").append('<p class="col-xs-6"><input id="zasj'+sj[i].code+'"type="radio" style="margin: 3px 3px 5px 5px;" name="period" class="form-val icheckbox" value="'+sj[i].code+'"><label for="zasj'+sj[i].code+'">' +sj[i].name+'</label></p>')
				}
				
				for( var i in td){
					$("#zatd").append('<p class="col-xs-6"><input id="zatd'+td[i].code+'"type="checkbox" style="margin: 3px 3px 5px 5px;" name="featureCodes" class="icheckbox" value="'+td[i].code+'"><label for="zatd'+td[i].code+'">' +td[i].name+'</label></p>')
				}
				
			}
		});
	}
	
	/**
	 * 弹出地图框，选择地址
	 */
	function alertMapPage(){
		//关闭地址验证
		$("#address").removeClass("valiform-keyup form-val").removeAttr("datatype");
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/caseTag/showCaseTagMapPage.action',
			pageLoading : true,
			title:"选择发案地址",
			width : "70%",
			height : "85%",
			btn:["确定","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.caseTagMap ;
					var obj = cm.getInfo();
					if($.util.exist(obj)){
						longitude = obj.longitude;
						latitude = obj.latitude;
						$("#address").val(obj.name);
						//查询地图数据当前点是那个村居，
						obj.map.queryCommunityCodeByLonlat({longitude : longitude, latitude : latitude}, function(communityCode){
							$.select2.val("#community",communityCode);
							$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
						});
					}
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				longitude : longitude ,
				latitude : latitude ,
				address : $("#address").val()
			},
			end:function(){
				//开启地址验证
				$("#address").addClass("valiform-keyup form-val").attr("datatype","*1-500");
			}
		});
	}
	
	/**
	 * 获取页面数据并保存数据
	 */
	function saveOrUpdate(){
		var demo = $.validform.getValidFormObjById("tagValidform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		//验证作案人数
		var peopleNumInputArray = $.icheck.getChecked("peopleNum");
		if(peopleNumInputArray.length < 1){
			$.util.topWindow().$.layerAlert.alert({icon:0,msg:"请选择作案人数。"});
			return ;
		}
		
		//验证作案特点
		var featureInputArray = $.icheck.getChecked("featureCodes");
		if(featureInputArray.length < 1){
			$.util.topWindow().$.layerAlert.alert({icon:0,msg:"请选择作案特点。"});
			return ;
		}
		var tagObj = $.validform.getFormVals("#tagValidform");
		tagObj.longitude = longitude;
		tagObj.latitude = latitude;
		tagObj.caseCode = $("#paraId").val();
		tagObj.featureCodes = null;
		var featureCodes = new Array();
		$.each(featureInputArray,function(i,input){
			featureCodes.push($(input).val());
		});
		var data = {
			"fcs" : featureCodes,
		    "ctb" : tagObj,
		}
		var dataStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/caseTag/saveOrUpdateCaseTag.action',
			dataType:"json",
			type:"post",
			data:dataStr,
			contentType: "application/json; charset=utf-8",
			customizedOpt:{
			     ajaxLoading:true,//设置是否loading
			},
			success:function(){
				window.location.href = $.util.fmtUrl(context+"/caseTag/showLookCaseTagPage.action?id="+id);
			}
		})
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.newCaseTag, { 
		
	});	
})(jQuery);
