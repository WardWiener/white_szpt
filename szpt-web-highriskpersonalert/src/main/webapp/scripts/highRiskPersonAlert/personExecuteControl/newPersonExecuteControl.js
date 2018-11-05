(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var idcode=null;
	$(document).ready(function() {
		//放大镜更改鼠标样式
		$("#searchIdcardNo").attr("style","cursor:pointer;");
		
		$(document).on("click" , "#searchIdcardNo", function(){
			var  zzTest=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if(!$.common.wifiCommon.idCodeValid($("#idcardNo").val())){				
				$.util.topWindow().$.layerAlert.alert({
					msg:"您输入的身份证号不正确!<br/>请重新输入",
					title:"提示",
					width:'300px',
					hight:'200px',
					icon:0,
				});
				return;
			}
			setPagePersonData();
		});
		if(!$.util.isBlank(id)){
			initPageField();
		}else{
			$.ajax({
				url:context +'/personExecuteControl/acquireNum.action',
				type:'post',
				dataType:'json',
				data:{},
				success:function(successData){
					if(successData.bkNum != null){
						$("#num").val(successData.bkNum);
					}
				}
			});
		}
//		$(document).on("blur" , "#idcardNo", function(){
//			
//			if(idcode==null){
//				return;
//			}
//			var  zzTest=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
//			if(!zzTest.test($("#idcardNo").val())){
//				return;
//			}
//			if(idcode==$("#idcardNo").val()){
//				return;
//			}
//			$.layerAlert.confirm({
//				msg:"身份证号已修改，是否更新人员信息?",
//				title:"提示",
//				yes:function(index, layero){
//					setPagePersonData();
//				}
//			});
//		});
		
		$(document).on("keyup" , "#idcardNo", function(){
			var  zzTest=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if(!$.common.wifiCommon.idCodeValid($("#idcardNo").val())){
				$("#personName").val("");
				$("#residenceAddress").val("");
				$("#sex").val("");
				$("#sexName").val("");
				setPhone([],true);
				setPic([],true);
			}
		})
	});
	function setPagePersonData(){
		$.ajax({
			url:context +'/personDetail/findPersonTypeAndMobile.action',
			type:'post',
			dataType:'json',
			data:{code:$("#idcardNo").val()},
			success:function(successData){
				if(successData.personBean!=null){
					var personBean = successData.personBean;
					idcode=personBean.idcode;
//					$.validform.setFormVals("#validform",personBean);
					$("#personName").val(personBean.name);
					$("#idcardNo").val(personBean.idcode);
//					$("#idcardNo").attr("readonly","readonly");
					$("#residenceAddress").val(personBean.registerAddress);
					$("#sex").val(personBean.sex);
					$("#sexName").val(personBean.sexName);
					var phones = successData.mobilePhoneInfoBeanList;
					setPhone(phones,true);
					var files = successData.fileBeanList;
					setPic(files,true);
				}else{
					$.layerAlert.alert({
						msg:"此人非高危人，不能进行布控，请重新输入身份证号。"
					});
				}
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(id)){//修改
			$.ajax({
				url:context +'/personExecuteControl/findPersonExecuteControlById.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var personExecuteControlBean = successData.personExecuteControlBean;
					if(personExecuteControlBean!=null){
						$.validform.setFormVals("#validform",personExecuteControlBean);
						var startTime=$.date.timeToStr(personExecuteControlBean.startTime,"yyyy-MM-dd HH:mm");
						var endTime=$.date.timeToStr(personExecuteControlBean.endTime,"yyyy-MM-dd HH:mm");
						$.laydate.setRange(startTime,endTime,"#dateRange","info@yyyy-MM-dd HH:mm"); 
						var phones = successData.personMobileInfoBeanList;
						setPhone(phones,false);
						var files = successData.fileBeanList;
						setPic(files,false);
						$("#lastModifyPerson").text(personExecuteControlBean.lastModifyPerson);
						$("#lastModifyTime").text($.date.timeToStr(personExecuteControlBean.lastModifyTime));
						$(document).off("click" , "#searchIdcardNo");  
//						$("input").attr("readonly","readonly");
//						$("textarea").attr("readonly","readonly");
						$("select").attr("disabled","disabled");
						$(".approveDiv").show();
						$("#approveContent").text(personExecuteControlBean.approveContent);
						$("#approvePeople").text(personExecuteControlBean.approvePeople);
						$("#approveTime").text($.date.timeToStr(personExecuteControlBean.approveTime, "yyyy-MM-dd HH:mm"));
					}
				},
				error:function(errorData){
					
				}
			});
		}
	}
	function setPhone(phones,ifCheck){
		var count=0;
		$(".phoneDiv").remove();
		$("#phoneDiv").html('<div class="col-xs-2"> <label class="control-label">手机：</label></div>');
		for(var i in phones){
			var a='';
			if(ifCheck){
				a+='<div class="col-xs-1"><input type="checkbox" class="icheckbox phoneCheck" name="phoneCheck" value="" number="'+phones[i].number+'" mac="'+phones[i].mac+'"></div>';
			}
			a+='<div class="col-xs-2"><input type="text" datatype="/(^\\d{11}$)|(^$)/" class="form-control input-sm valiform-keyup form-val" value="'+phones[i].number+'"></div>'+
				'<div class="col-xs-3 mar-left"><input type="text" datatype="/(^([0-9A-Fa-f]{2}-){5}[0-9A-Fa-f]{2}$)|(^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$)|(^$)/" class="form-control input-sm valiform-keyup form-val" value="'+phones[i].mac+'"></div>';
			if(count==0){
				$("#phoneDiv").append(a);
				count++;
			}else{
				a='<div class="row row-mar phoneDiv"><div class="col-xs-2"></div>'+a+'</div>';
				$("#phoneDiv").after(a);
			}
		}
	}
	function setPic(files,ifCheck){
		$("#picDiv").html('<div class="col-xs-2"> <label class="control-label">照片：</label></div>');
		for(var i in files){
			if(ifCheck){
				$("#picDiv").append('<div class="col-xs-2 mar-right"><p style="height:130px; overflow:hidden"><img src="'+context+'/personDetail/downloadFile.action?attachmentId='+files[i].id+'" width="100%"></p><p><input type="checkbox" class="icheckbox picCheck" name="picCheck"value="'+files[i].id+'"></p></div>');
			}else{
				$("#picDiv").append('<div class="col-xs-2 mar-right"><p style="height:130px; overflow:hidden"><img src="'+context+'/personDetail/downloadFile.action?attachmentId='+files[i].id+'" width="100%"></p></div>');
			}
		
		}
	}
	/**
	 * 获取页面所有字段
	 */
	function getAllField(){
		var formdata = $.validform.getFormVals("#validform");
		return formdata;
	}
	
	/**
	 * 保存
	 */
	function save(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		if($("#idcardNo").val() != idcode){
			$.util.topWindow().$.layerAlert.alert({
				msg:"身份证已更改!<br/>请重查基本信息",
				title:"提示",
				width:'300px',
				hight:'200px',
				icon:0,
			});
			return ;
		}
		if($.util.isBlank($.laydate.getTime("#dateRange","start"))){
			$.util.topWindow().$.layerAlert.alert({
				msg:"请选择布控开始时间",
				title:"提示",
				width:'300px',
				hight:'200px',
				icon:0,
			});
			return ;
		}
		if($.util.isBlank($.laydate.getTime("#dateRange","end"))){
			$.util.topWindow().$.layerAlert.alert({
				msg:"请选择布控结束时间",
				title:"提示",
				width:'300px',
				hight:'200px',
				icon:0,
			});
			return ;
		}
		if($.laydate.getTime("#dateRange","start")>$.laydate.getTime("#dateRange","end")){
			$.util.topWindow().$.layerAlert.alert({
				msg:"布控开始时间不能大于<br/>布控结束时间",
				title:"提示",
				width:'300px',
				hight:'200px',
				icon:0,
			});
			return ;
		}
		var personExecuteControlBean = getAllField();
		var dataMap = {};
		personExecuteControlBean.startTime=$.laydate.getTime("#dateRange","start");
		personExecuteControlBean.endTime=$.laydate.getTime("#dateRange","end");
		personExecuteControlBean.id=id;
		$.util.objToStrutsFormData(personExecuteControlBean, "personExecuteControlBean", dataMap);
		if($.icheck.getChecked("phoneCheck").length!=0){
			var phonechecked = $.icheck.getChecked("phoneCheck");
			for(var i in phonechecked){
				dataMap["personMobileInfoBeanList["+i+"].number"]=$(phonechecked[i]).attr("number");
				dataMap["personMobileInfoBeanList["+i+"].mac"]=$(phonechecked[i]).attr("mac");
				dataMap["personMobileInfoBeanList["+i+"].id"]=phonechecked[i].value!=""?phonechecked.value:null;
			}
		}
		if($.icheck.val("picCheck")!=""){
			var picchecked = $.icheck.getChecked("picCheck");
			for(var i in picchecked){
				dataMap["fileBeanList["+i+"].id"]=picchecked[i].value;
			}
		}
		$.ajax({
			url:context +'/personExecuteControl/savePersonExecuteControl.action',
			type:'post',
			dataType:'json',
			data:dataMap,
			success:function(successData){
				window.top.$.common.refresh();
				window.top.$.layerAlert.closeWithLoading(pageIndex); 
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, {
		save : save
	});	
})(jQuery);