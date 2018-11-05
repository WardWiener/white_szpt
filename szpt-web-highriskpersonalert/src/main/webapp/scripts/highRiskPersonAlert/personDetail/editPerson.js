(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var personId = initData.personId;
	var editIdcode = initData.editIdcode;
	var count=0;
	var qjCode = $.common.Constant.RYLX_QJ();
	var dqCode = $.common.Constant.RYLX_DQ();
	var qdCode = $.common.Constant.RYLX_QD();
	var zpCode = $.common.Constant.RYLX_ZP();
	
	var xsqkCode = $.common.Constant.RYLX_XSQK();
	var swCode = $.common.Constant.RYLX_SW();
	var skCode = $.common.Constant.RYLX_SK();
	var sdCode = $.common.Constant.RYLX_SD();
	var jsbCode = $.common.Constant.RYLX_JSB();
	var zdsfCode = $.common.Constant.RYLX_ZDSF();
	var ztCode = $.common.Constant.RYLX_ZT();
	var qsnCode = $.common.Constant.RYLX_QSN();
	var azbrCode = $.common.Constant.RYLX_AZBR();
	
	var qjCount=0;
	var dqCount=0;
	var qdCount=0;
	var zpCount=0;
	
	var selectedType=[];
	var selectedTypeId=[];
	
	var personBean=null;
	var isNewPage = false;
	var data;
	$(document).ready(function() {
		if($.util.isBlank(editIdcode)){
			addPhoneGroup();
		}
		
		/**
		 * 增行手机号
		 */
		$(document).on("click","#addPhoneGroup",function(){
			if(!checkBlankLines()){
				return false;
			}
			addPhoneGroup();
		});
		
		/**
		 * 删行手机号
		 */
		$(document).on("click",'.deletePhone',function(){
			$(this).parent("div").remove();
			$.validform.closeAllTips("validform") ;
			$(".phoneDiv").each(function(d, div){
				$(div).find("label").text("手机号" + (d + 1) + "：");
				$(div).find("label").attr("id", "label" + (d + 1));
			});
		});
		
		initAllDictionaryItem();
//		initPageData();
		initPlupload();
		$(document).on("ifChanged",".type1",function(){
			var id = this.id;
			if(this.checked){
//				$(document).on("click" , "#"+id+"Tree", function(e){
//					var tree = $.zTreeMenu.getTree(id+"Tree") ;
//					tree.showMenu() ;
//				});
				$("#"+id+"Div").show();
				if(id==xsqkCode){
					$(".xsqkDiv").show();
					$("#xsqkDiv").show();
				}
			}else{
//				$(document).off("click" , "#"+id+"Tree");
				$("#"+id+"Div").hide();
				if(id==xsqkCode){
					$(".xsqkDiv").hide();
					$("#xsqkDiv").hide();
				}
			}
		});
		$(document).on("click" , "#"+qdCode+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(qdCode+"Tree") ;
			tree.showMenu() ;
		});
		$(document).on("click" , "#"+qjCode+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(qjCode+"Tree") ;
			tree.showMenu() ;
		});
		$(document).on("click" , "#"+dqCode+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(dqCode+"Tree") ;
			tree.showMenu() ;
		});
		$(document).on("click" , "#"+zpCode+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(zpCode+"Tree") ;
			tree.showMenu() ;
		});
    	$(document).on("click",".picDownload",function() {
			var id = $(this).attr("id");
			window.open( context + "/personDetail/downloadFile.action?attachmentId="+id);
		});
    	$(document).on("click",".picDelete",function() {
    		var t = $(this);
    		var id = $(this).attr("picId");
			$.layerAlert.confirm({
				msg : "确认删除吗？",
				yes : function(index,layero) {
    				$.ajax({
    					url : context+ "/personDetail/deleteAttachment.action?id="+id,
    					type : "POST",
    					dataType : "json",
    					success : function(data) {
    						$("#"+id).remove();
    						t.remove();
    					}
    				});
				}
			});
		});
    	$(document).on("blur","#idcode",function() {
    		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    		var card=$('#idcode').val();
    		   if(reg.test(card) === true)  
    		   {  
    			   findPersonByIdCode(this.value);
    		   }
		});
    	$(document).on("click","#newPage",function() {
    		$("input.form-control").val("");
    		$.select2.val("select","");
    		$.icheck.check("input",false);
    		$(".xsqkDiv").remove();
    		qjCount=0;
    		dqCount=0;
    		qdCount=0;
    		zpCount=0;
    		selectedType=[];
    		selectedTypeId=[];
    		$(this).remove();
    		initAllTree();
    		changeNewPage();
    		$("#picDiv").html("");
    		personId=null;
    		personBean=null;
    		$("#newPageWarnDiv").hide();
    		$("#approveDiv").hide();
		});
	});
	function initPageData(){
		if(personId==null){
			isNewPage=true;
			changeNewPage();
			return;
		}
		$.ajax({
			url:context +'/personDetail/findPersonTypeAndMobile.action',
			type:'post',
			dataType:'json',
			data:{personId:personId},
			async:false,
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				personBean = successData.personBean;
				$.validform.setFormVals("#validform",personBean);
				$("#name").text(personBean.name);
				$("#idcode").text(personBean.idcode);
//				$("#nickname").val(personBean.nickname);
//				$("#usedName").val(personBean.usedName);
				$("#peopleType").text(personBean.peopleTypeName);
//				$("#warnType"+personBean.warnType).show();
				$.select2.selectByText("#ethnicGroup",personBean.ethnicGroup);
				if(personBean.operateStatus==$.common.Constant.CZZT_SPBH()){
					$("#approveDiv").show();
					$("#approveContent").text(personBean.approveContent);
					$("#approvePeople").text(personBean.approvePeople);
					$("#approveTime").text(personBean.approveTime);
				}
//				$.select2.val("#education",personBean.education);
				if(personBean.birthday!=null){
					$.laydate.setTime(personBean.birthday,"#birthday"); 
				}
				var phones = successData.mobilePhoneInfoBeanList;
				if(phones.length < 1){
					addPhoneGroup();
				}
				$.each(phones,function(p, phone){
					addPhoneGroup(phone.id);
					$("#" + phone.id).find(".phone").val(phone.number);
					$("#" + phone.id).find(".imei").val(phone.imei);
					$("#" + phone.id).find(".mac").val(phone.mac);
				});
				
				var types = successData.highriskPeopleTypeBeanList;
				for(var i in types){
					selectedType.push(types[i].peopleType);
					selectedTypeId.push(types[i].id);
					if(ifXSQK(types[i].peopleType)!=false){
						var obj={
							"id":types[i].peopleType,
							"pId":ifXSQK(types[i].peopleType),
							"name":types[i].peopleTypeName
							}
						createTree(obj,types[i].highriskCriminalRecord);
					}
					if(types[i].peopleType==ztCode||types[i].peopleType==qsnCode||types[i].peopleType==azbrCode){
						$.icheck.check("#"+types[i].peopleType);
						$("#"+types[i].peopleType).parents(".checkDiv").attr("id",types[i].id);
					}
				}
				
				$("#registerAddress").text(personBean.registerAddress);
				$.icheck.check("#"+personBean.personInControlType,true);
				$.icheck.check("#sex"+personBean.sex,true);
				var files = successData.fileBeanList;
				for(var i in files){
					$("#picDiv").append('<div class="col-xs-2 mar-right"><p style="height:100px; overflow:hidden">'+
							'<img id="'+files[i].id+'" class="picDownload" width="100%" src="'+context+'/personDetail/downloadFile.action?attachmentId='+files[i].id+'">'+
							'<p class="pull-right"><button class="btn btn-x picDelete" picId="'+files[i].id+'">×</button></p></div>');
					
				}
			},
			error:function(errorData){
				
			}
		});
	}
	function changeNewPage(){
		$("#topDiv").html('<div class="col-xs-2"><label class="control-label">身份证号：<span class="red-star">*</span></label></div>'+
				'<div class="col-xs-6"><input id="idcode"class="form-control input-sm valiform-keyup form-val" datatype="/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/" name="idcode"></div>'+
				'<div class="col-xs-3"><button style="display:none"id="newPage"class="btn btn-default btn-sm">重置</button></div>');
		$("#topDiv").addClass("alert-info");
		$("#topDiv").css("padding","10px 0");
//		$("#topDiv").after('<div class="row row-mar"  id="warnTypeDiv"><div class="col-xs-2"><label class="control-label">预警类型：</label></div><div class="col-xs-6">'+
//				'<select id="warnType"class="form-control input-sm select2-noSearch allowClear form-val" name="warnType"><option value=""></option></select></div></div>');
//		$("#topDiv2").html('<div class="row row-mar">'+
//				'<div class="col-xs-2"> <label class="control-label">姓名：</label></div>'+
//				'<div class="col-xs-4"><input id="name"class="form-control input-sm valiform-keyup form-val"datatype="*1-10" name="name"></div>'+
//				'<div class="col-xs-2" style="width:50px;"> <label class="control-label">绰号：</label></div>'+
//				'<div class="col-xs-3"><input id="nickname"class="form-control input-sm valiform-keyup"datatype="*0-20"></div></div>');
		$("#topDiv2").show();
		$(".newPageDiv").show();
		$("#registerAddress").remove();
		$("#registerAddressDiv").append('<input id="registerAddress" class="form-control input-sm valiform-keyup form-val" datatype="*0-50" name="registerAddress">');
		$("input").attr("disabled","true");
		$("select").attr("disabled","true");
		$("#idcode").removeAttr("disabled");
		$(".phoneDiv").removeAttr("id");
		$("#bottomDiv").before($("#phoneDiv"));
		$("#bottomDiv").after($("#personInControlTypeDiv2"));
		if(editIdcode!=undefined&&editIdcode!=null){
			findPersonByIdCode(editIdcode);
		}
	}
	function findPersonByIdCode(idcode){
		$("input").removeAttr("disabled");
		$("select").removeAttr("disabled");
		$(".disabled").removeClass("disabled");
		$("#idcode").attr("disabled","true");
		$("#newPage").show();
		$.ajax({
			url:context +'/highriskPerson/findPersonByIdcode.action',
			type:'post',
			dataType:'json',
			data:{idcode:idcode},
			async:false,
			customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if(successData.highriskPersonBean!=null){
					personId = successData.highriskPersonBean.id;
					if(successData.highriskPersonBean.operateStatus==$.common.Constant.CZZT_SPTG()){
						window.top.$.layerAlert.alert({msg:"该高危人员已审批通过，请到人员打标处修改"}) ;
						return;
					}
					if(successData.highriskPersonBean.operateStatus==$.common.Constant.CZZT_DSP()){
						window.top.$.layerAlert.alert({msg:"该高危人员已提交审批，请到人员打标处修改"}) ;
						return;
					}
					initPageData();
					initAllTree();
					$("#newPageWarnDiv").show();
				}
			},
			error:function(errorData){
				
			}
		});
		
	}
	function initAllTree(){
		initTree(xsqkCode);
		initTree(skCode);
		initTree(swCode);
		initTree(sdCode);
		initTree(jsbCode);
		initTree(zdsfCode);
	}
	function initAllDictionaryItem(){
		initAllTree();
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.XB()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
//					$.select2.addByList("#sex", successData.dictionaryItemLst,"code","name");//设置下拉框选项
					var list = successData.dictionaryItemLst;
					for(var i in list){
						$("#sexDiv").append('<p class="col-xs-4"><input type="radio"  class="icheckradio" name="sex" id="sex'+list[i].code+'"value="'+list[i].code+'">'+list[i].name+'</p>');
					}
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.HYQK()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#marriageStatus", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.JYQK()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#employmentStatus", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.ZY()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#profession", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.YJLX()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#warnType", successData.dictionaryItemLst,"code","name");//设置下拉框选项
//					var list = successData.dictionaryItemLst;
//					for(var i in list){
//						$("#warnTypeDiv").append('<p class="col-xs-4"><input type="radio"  class="icheckradio" name="warnType" id="'+list[i].code+'"value="'+list[i].code+'">'+list[i].name+'</p>');
//					}
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.ZKLX()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					var list = successData.dictionaryItemLst;
					for(var i in list){
						$("#personInControlTypeDiv").append('<p class="col-xs-3"><input id="'+list[i].code+'" value="'+list[i].code+'"type="radio"  class="icheckradio"name="personInControlType">'+list[i].name+'</p>');
					}
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.XL()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#education", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		$.ajax({
			url:context +'/webUtil/findDictionaryItemByType.action',
			type:'post',
			dataType:'json',
			async:false,
			data:{dictionaryType : $.common.Constant.MZ()},
			success:function(successData){
				if(successData.dictionaryItemLst != null){
					$.select2.addByList("#ethnicGroup", successData.dictionaryItemLst,"code","name");//设置下拉框选项
				}
			},
			error:function(errorData){
				
			}
		});
		initPageData();
	}
	
	function initTree(code){
		$.zTreeMenu.init(code+"Tree", context + "/personDetail/findSubDics.action", {
			async:{
				enable:true
			},
			callBacks:{
				formatNodeData:function(nodeData){
					if(selectedType.indexOf(nodeData.id)!=-1){
						nodeData.checked=true;
						nodeData.diyMap.id=selectedTypeId[selectedType.indexOf(nodeData.id)];
						$.icheck.check("#"+code,true);
						var val = $("#"+code+"Tree").val();
						$("#"+code+"Tree").val(val==""?nodeData.name:val+"，"+nodeData.name);
					}
		  		}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "all",
				chkboxType: {"Y":"", "N":""}
			},
			callback : {
				onCheck:onCheck2
			}
		},null,{code:code}) ;
		$(document).on("click" , "#"+code+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(code+"Tree") ;
			tree.showMenu() ;
		});
	}
	function initXSQKTree(select,code,selected){
		$.zTreeMenu.init(select+"Tree", context + "/personDetail/findSubDics.action", {
			async:{
				enable:true
			},
			callBacks:{
				formatNodeData:function(nodeData){
					if(selected!=null&&selected.indexOf(nodeData.id)!=-1){
						nodeData.checked=true;
						var val = $("#"+select+"Tree").val();
						$("#"+select+"Tree").val(val==""?nodeData.name:val+"，"+nodeData.name);
					}
		  		}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "all",
				chkboxType: {"Y":"", "N":""}
			},
			callback : {
				onCheck:onCheck2
			}
		},null,{code:code}) ;
		$(document).on("click" , "#"+select+"Tree", function(e){
			var tree = $.zTreeMenu.getTree(select+"Tree") ;
			tree.showMenu() ;
		});
	}
	
	/**
	 * 验证手机号、IMEI号、WIFI MAC是否都未填
	 * @returns
	 */
	function checkBlankLines(){
		//循环电话组，找出手机号，imei号，wifi Mac号都未填的电话组
		var resultFlag = true;
		$(".phoneDiv").each(function(p, phoneDiv){
			var inputFlag = true;
			$(phoneDiv).find("input").each(function(i, input){
				var value = $(input).val();
				if(!$.util.isBlank(value)){
					inputFlag = false;
				}
			});
			//true:弹出提示框，提示“请填写手机号或IMEI号或WIFI MAC”
			if(inputFlag){
				$.layerAlert.tips({
					msg:'请填写手机号或IMEI号或WIFI MAC',
					selector:"#" + $(phoneDiv).find("label").attr("id"),
					color:'#FF0000',
					position:1,
					closeBtn:2,
					time:2000,
					shift:1
				});
				resultFlag = false;
				return false;
			}
		});
		return resultFlag;
	}
	
	/**
	 * 验证电话号码是否重复
	 * 
	 * @returns true：通过，false：不通过
	 */
	function cheackPhone(){
		//验证手机号是否有填写
		var allNullFlag = true;
		$(".phoneDiv").each(function(d, div){
			var phone = $(div).find(".phone").val();
			if(!$.util.isBlank(phone)){
				allNullFlag = false;
				return false;
			}
		});
		if(allNullFlag){//手机号都未填跳过验证，且验证通过
			return true;
		}else{//验证手机号是否有重复
			var phoneArray = [];
			var flag = true;
			$(".phoneDiv").each(function(d, div){
				var phone = $(div).find(".phone").val();
				if($.util.isBlank(phone)){
					return true;
				}
				if($.inArray(phone, phoneArray) != -1){
					flag = false;
					return false;
				}
				phoneArray.push(phone);
			});
			return flag;
		}
	}
	
	/**
	 * 验证MAC地址是否重复
	 * 
	 * @returns true：通过，false：不通过
	 */
	function cheackMac(){
		//验证Mac是否有填写
		var allNullFlag = true;
		$(".phoneDiv").each(function(d, div){
			var mac = $(div).find(".mac").val();
			if(!$.util.isBlank(mac)){
				allNullFlag = false;
				return false;
			}
		});
		if(allNullFlag){//mac都未填跳过验证，且验证通过
			return true;
		}else{//验证mac是否有重复
			var macArray = [];
			var flag = true;
			$(".phoneDiv").each(function(d, div){
				var mac = $(div).find(".mac").val();
				if($.util.isBlank(mac)){
					return true;
				}
				if($.inArray(mac, macArray) != -1){
					flag = false;
					return false;
				}
				macArray.push(mac);
			});
			return flag;
		}
	}
	
	/**
	 * 验证IMEI是否重复
	 * 
	 * @returns true：通过，false：不通过
	 */
	function cheackImei(){
		//验证Imei是否有填写
		var allNullFlag = true;
		$(".phoneDiv").each(function(d, div){
			var imei = $(div).find(".imei").val();
			if(!$.util.isBlank(imei)){
				allNullFlag = false;
				return false;
			}
		});
		if(allNullFlag){//imei都未填跳过验证，且验证通过
			return true;
		}else{//验证imei是否有重复
			var imeiArray = [];
			var flag = true;
			$(".phoneDiv").each(function(d, div){
				var imei = $(div).find(".imei").val();
				if($.util.isBlank(imei)){
					return true;
				}
				if($.inArray(imei, imeiArray) != -1){
					flag = false;
					return false;
				}
				imeiArray.push(imei);
			});
			return flag;
		}
	}
	function getData(operateStatus){
		if(isNewPage&&personBean!=null&&personBean.operateStatus==$.common.Constant.CZZT_SPTG()){
			window.top.$.layerAlert.alert({msg:"该高危人员已审批通过，请到人员打标处修改"}) ;
			return ;
		}
		if(isNewPage&&personBean!=null&&personBean.operateStatus==$.common.Constant.CZZT_DSP()){
			window.top.$.layerAlert.alert({msg:"该高危人员已提交审批，请到人员打标处修改"}) ;
			return ;
		}
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		if(isNewPage&&$.icheck.val("sex")==""){
			$.layerAlert.tips({
				msg:'请选择性别',
				selector:"#sexDiv",
				color:'#FF0000',
				position:1,
				closeBtn:2,
				time:1000,
				shift:1
			})
			flag=false;
			return;
		}
		if(isNewPage&&$.select2.val("#warnType")==""){
			$.layerAlert.tips({
				msg:'请选择预警类型',
				selector:"#warnType",
				color:'#FF0000',
				position:1,
				closeBtn:2,
				time:1000,
				shift:1
			})
			flag=false;
			return;
		}
		if(isNewPage&&$("#addReason").val()==""){
			$.layerAlert.tips({
				msg:'请填写新增原因',
				selector:"#addReason",
				color:'#FF0000',
				position:1,
				closeBtn:2,
				time:1000,
				shift:1
			})
			flag=false;
			return;
		}
		if($.icheck.getChecked("personInControlType")==0){
			$.layerAlert.tips({
				msg:'请选择在控类型',
				selector:"#personInControlTypeDiv",
				color:'#FF0000',
				position:1,
				closeBtn:2,
				time:1000,
				shift:1
			})
			flag=false;
			return;
		}
		data = new Object();
		var checked = $.icheck.getChecked("type1");
		var typeCount=0;
		for(var i in checked){
			if(checked[i].value==ztCode||checked[i].value==qsnCode||checked[i].value==azbrCode){
				data["highriskPeopleTypeBeanList["+typeCount+"].id"]=$(checked[i]).parents(".checkDiv").attr("id");
				data["highriskPeopleTypeBeanList["+typeCount+"].peopleType"]=checked[i].value;
				typeCount++;
				continue;
			}
			var treeNodes =  $.zTreeMenu.getCheckedNodes(checked[i].value+"Tree");
			for(var i in treeNodes){
				data["highriskPeopleTypeBeanList["+typeCount+"].id"]=treeNodes[i].diyMap.id;
				data["highriskPeopleTypeBeanList["+typeCount+"].peopleType"]=treeNodes[i].id;
				if(ifXSQK(treeNodes[i].id)!=false){
					var zp = $.zTreeMenu.getCheckedNodes(treeNodes[i].id+"Tree");
					if(zp.length==0){
						$.layerAlert.tips({
	        				msg:'请选择案件性质',
	        				selector:"#"+treeNodes[i].id+"Tree",
	        				color:'#FF0000',
	        				position:1,
	        				closeBtn:2,
	        				time:1000,
	        				shift:1
	        			})
	        			flag=false;
					}
					for(var j in zp){
						data["highriskPeopleTypeBeanList["+typeCount+"].highriskCriminalRecord["+j+"].criminalRecord"]=zp[j].id;
						data["highriskPeopleTypeBeanList["+typeCount+"].highriskCriminalRecord["+j+"].id"]=zp[j].diyMap.id;
					}
				}
				typeCount++;
			}
		}
		if(!flag){
			return ;
		}
		
		var phoneFlag = cheackPhone();
		if(!phoneFlag){
        	window.top.$.layerAlert.alert({msg:"填写的手机号，有重复，请重新填写"}) ;
			return;
        }
		
        var macFlag = cheackMac();
        if(!macFlag){
        	window.top.$.layerAlert.alert({msg:"填写的Mac号，有重复，请重新填写"}) ;
			return;
        }
        var macImei = cheackImei();
        if(!macImei){
        	window.top.$.layerAlert.alert({msg:"填写的IMEI号，有重复，请重新填写"}) ;
			return;
        }
		data["personBean.nickname"]=$("#nickname").val();
		data["personBean.id"]=personId;
		data["personBean.personInControlType"]=$.icheck.val("personInControlType");
		data["personBean.liveAddress"]=$("#liveAddress").val();
		data["personBean.marriageStatus"]=$.select2.val("#marriageStatus");
		data["personBean.employmentStatus"]=$.select2.val("#employmentStatus");
		data["personBean.workAddress"]=$("#workAddress").val();
		data["personBean.profession"]=$.select2.val("#profession");
		data["personBean.income"]=$("#income").val();
		if(isNewPage){
			data["personBean.idcode"]=$("#idcode").val();
			data["personBean.name"]=$("#name").val();
			data["personBean.usedName"]=$("#usedName").val();
			data["personBean.sex"]=$.icheck.val("sex");
			data["personBean.ethnicGroup"]=$.select2.text("#ethnicGroup");
			data["personBean.education"]=$.select2.val("#education");
			data["personBean.birthday"]=$.laydate.getTime("#birthday", "date")
			data["personBean.registerAddress"]=$("#registerAddress").val();
			data["personBean.operateStatus"]=operateStatus;
			data["personBean.addReason"]=$("#addReason").val();
			data["personBean.warnType"]=$.select2.val("#warnType");
//			data["personBean.warnType"]=$.icheck.val("warnType");
		}
		var j = 0;
		for(var i=0;i<$(".phoneDiv").length;i++){
			var item = $(".phoneDiv")[i];
			if($(item).find(".phone").val()!=""||$(item).find(".imei").val()!=""||$(item).find(".mac").val()!=""){
				data["mobilePhoneInfoBeanList["+j+"].number"]=$(item).find(".phone").val();
				data["mobilePhoneInfoBeanList["+j+"].imei"]=$(item).find(".imei").val();
				data["mobilePhoneInfoBeanList["+j+"].mac"]=$(item).find(".mac").val();
				data["mobilePhoneInfoBeanList["+j+"].id"]=$.util.isBlank($(item).attr("id")) ? null : $(item).attr("id");
				j ++;
			}
		}
		$.plupload.start("container");
	}
	function postData(finishedFiles){
		var fileBeanList = [];
		for (var key in finishedFiles) {
			fileBeanList.push(finishedFiles[key]);
        }
		$.util.objToStrutsFormData(fileBeanList, "fileBeanList", data);
		$.ajax({
			url:context +'/personDetail/updateHighriskPerson.action',
			type:'post',
			dataType:'json',
			data:data,
			success:function(successData){
				window.top.$.common.refresh();
				window.top.$.layerAlert.closeWithLoading(pageIndex); 
			},
			error:function(errorData){
				
			}
		});
	}
	function createTree(treeNode,selected){
		var a='';
		a+='<div id="'+treeNode.id+'Div"class="row row-mar xsqkDiv">';
		a+='<div class="col-xs-5">';
		a+='<input class="form-control input-sm" value="'+treeNode.name+'  →" readonly></div>';
		a+='<div class="col-xs-7"><p class="mar-left">';
		a+='<input id="'+treeNode.id+'Tree" class="form-control input-sm ui-readonly-select" readonly></p></div></div>';
		$("#xsqkDiv").append(a);
		var selectedList=[];
		var selectedIdList=[];
		if(selected!=null){
			for(var i in selected){
				selectedList.push(selected[i].criminalRecord);
				selectedIdList.push(selected[i].id);
			}
		}
		if(treeNode.id==qdCode||treeNode.pId==qdCode){
			initXSQKTree(treeNode.id,qdCode,selectedList,selectedIdList);
		}else if(treeNode.id==dqCode||treeNode.pId==dqCode){
			initXSQKTree(treeNode.id,dqCode,selectedList,selectedIdList);
		}else if(treeNode.id==zpCode||treeNode.pId==zpCode){
			initXSQKTree(treeNode.id,zpCode,selectedList,selectedIdList);
		}else if(treeNode.id==qjCode||treeNode.pId==qjCode){
			initXSQKTree(treeNode.id,qjCode,selectedList,selectedIdList);
		}
	}
	function ifXSQK(code){
		if(code.substring(0,9)==qjCode){
			return qjCode;
		}else if(code.substring(0,9)==qdCode){
			return qdCode;
		}else if(code.substring(0,9)==dqCode){
			return dqCode;
		}else if(code.substring(0,9)==zpCode){
			return zpCode;
		}else {
			return false;
		}
	}
	function onCheck2(e, treeId, treeNode) {

		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		
		var customSetting = treeObj.customSetting ;
		
		if(customSetting && customSetting.check && customSetting.check.type=="onlySelectChildren"){
			var tmp = treeNode ;
			while(tmp && tmp.getParentNode()){
				var pn = tmp.getParentNode() ;
				treeObj.checkNode(pn, false, true);
				tmp = pn ;
			}
			
			var tmpArr = treeObj.getNodesByParam("pId", treeNode.id, treeNode);
			
			
			while(tmpArr.length>0){
				
				var tmp = tmpArr ;
				tmpArr = [] ;
				$(tmp).each(function(i, val){
					treeObj.checkNode(val, false, true);
					
					var cdNodes = treeObj.getNodesByParam("pId", val.id, val) ;
					if(cdNodes && cdNodes.length>0){
						$.merge( tmpArr, cdNodes ) ;
					}
					
				});
				
			}
		}
		
		if(customSetting.inputDom.setNameAuto){
			var tobj = $.zTreeMenu.getTree(treeId);
			var data = $.zTreeMenu.getCheckedValue(treeId);
			$(tobj.inputDom).val(data.showNames) ;
		}
		if(treeNode.checked){
			if(treeNode.id==qdCode||treeNode.pId==qdCode||treeNode.id==dqCode||treeNode.pId==dqCode||treeNode.id==zpCode||treeNode.pId==zpCode||treeNode.id==qjCode||treeNode.pId==qjCode){
				var tree=$.zTreeMenu.getTree(treeNode.id+"Tree");
				if(tree==undefined){
					createTree(treeNode);
				}
			}
		}else{
			if(treeNode.id==qdCode||treeNode.pId==qdCode||treeNode.id==dqCode||treeNode.pId==dqCode||treeNode.id==zpCode||treeNode.pId==zpCode||treeNode.id==qjCode||treeNode.pId==qjCode){
				$.zTreeMenu.destroy(treeNode.id+"Tree");
				$("#"+treeNode.id+"Div").remove();
			}
		}
	}
	function initPlupload(){
		var setting = $.plupload.getBasicSettings() ;
		setting.container = "container" ; //容器id
		setting.url = context + "/personDetail/uploadFile.action";
		setting.controlBtn = {
			container:{
				className:"upload-btn"
			},
			selectBtn:{
				className:"btn btn-primary",
				html:'<span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-right:10px;"></span>选择'
			},
			uploadBtn:{
				init:false
			}
		} ;
		setting.finishlistDom = {
			className:"upload-text",
			downloadBtn:{
				init:false
			},
			deleteBtn:{
				init:false
			}
		};
		setting.filelistDom = {
			className:"upload-text"
		};
		setting.totalInfoDom = {
			className:"upload-text"
		};
		setting.customParams = {
		} ;
		setting.chunk_size = '0' ;
		setting.filters.max_file_size = '15mb';
		setting.filters.prevent_duplicates = true ;
		setting.multi_selection = false;
		setting.multi_file_num = 10 ;
		setting.filters.mime_types = [{title : "Image files", extensions : "jpg,gif,png"}];
		setting.multi_file_num_callback = function(max_file_size, totalSize){
		};
		setting.callBacks = {
				uploadAllFinish:function(up, finishedFiles){
					postData(finishedFiles);
				}
		}
		$.plupload.init(setting) ;
	}
//	function save(){
//		$.plupload.start("container");
//	}
	
	/**
	 * 添加电话组
	 * 
	 * @param phoneGroupId 电话组id
	 * @returns
	 */
	function addPhoneGroup(phoneGroupId){
		var phoneDivLength = $(".phoneDiv:visible").length;
		var phoneTemplateDiv = $("#phoneGroupDiv");
		var phoneCloneTemplateDiv = phoneTemplateDiv.clone(true);
		if($.util.isBlank(phoneGroupId)){
			phoneCloneTemplateDiv.attr("id", "");
		}else{
			phoneCloneTemplateDiv.attr("id", phoneGroupId);
		}
		phoneCloneTemplateDiv.addClass("phoneDiv");
		$(phoneCloneTemplateDiv).find("label").attr("id", "label" + (phoneDivLength + 1));
		$(phoneCloneTemplateDiv).find("label").text("手机号" + (phoneDivLength + 1) + "：");
		if(phoneDivLength < 1){
			$(phoneCloneTemplateDiv).append($("#addPhoneGroup"));//添加按钮
		}else{
			var deletePhoneGroupBut = $("#deletePhoneGroup");
			var deleteClonePhoneGroupBut = deletePhoneGroupBut.clone(true);
			$(deleteClonePhoneGroupBut).removeAttr("id");
			$(phoneCloneTemplateDiv).append(deleteClonePhoneGroupBut);//删除按钮
		}
		$("#phoneDiv").append(phoneCloneTemplateDiv);
	}
	function checkPersonIsExist(){
		$.ajax({
			url:context +'/personDetail/findPersonTypeAndMobile.action',
			type:'post',
			dataType:'json',
			data:{code:$("#idcode").val()},
			success:function(successData){
				if(successData.personBean!=null){
					$.util.topWindow().$.layerAlert.alert({
						msg:"此高危人已存在,请重新输入!",
						title:"提示",
						icon:0,
						yes:function(index, layero){
							
						}
					});
				}else{
					getData($.common.Constant.CZZT_XZ())
				}
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		save : function(){
			getData($.common.Constant.CZZT_XZ());
		},
		apply : function(){
			getData($.common.Constant.CZZT_DSP())
		}
//		save : save
	});	
})(jQuery);