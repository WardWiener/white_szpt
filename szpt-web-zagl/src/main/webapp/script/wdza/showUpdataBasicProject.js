$.basicMessage = $.basicMessage || {};
(function($){
	"use strict";
	var i=1; 
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var initData = frameData.initData ;
	var caseId = initData.lockerId;
	var  updataBtncount=0;
	var arrOld=new Array();
	$(document).ready(function(){
		  onloadBasicData();
		 /**
		 * 添加子案件btn
		 */
		$(document).on('click',"#basicAddZAJBtn",function(){
			findSonProject();
		});
		 /**
		 * 删除子案件btn
		 */
		$(document).on('click',"#basicRemZAJBtn",function(){
			remZAJ();
		});
		
		 /**
		 * 修改保存按钮
		 */
		$(document).on('click',"#basicSaveBtn",function(){
			saveAdd();
		});
		 /**
		 * 新增重置按钮
		 */
		$(document).on('click',"#basicResBtn",function(){
			resetAdd();
		});
	  
	  
	})
	
	/**
	 * 移除子案件
	 */
	function remZAJ(){
    	var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length>0){
    	$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){
				for(var i=0;i<arrcheck.length;i++){
					$(arrcheck[i]).parent().closest('tr').remove();
				}
			}
		});
		}else{
			$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
		}
    
	}
	
	/**
	 * 涉及子案件--添加
	 */
	function findSonProject(){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/zawh/showFindSonProject.action',
			pageLoading : true,
			title:"查询人员",
			width : "930px",
			height : "520px",
			btn:["添加","返回"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.findSonProject;
					var data = cm.getData();
					var str="";
					for(var i=0;i<data.arrCaseName.length;i++){
						var m=0;
						var b=$('#basicModel .caseCode');
						for(var j=0;j<b.length;j++){
							if($(b[j]).text()==data.arrCaseCode[i]){
								m=1;
								break;
							}
						}
						if(m==0){
							//caseName+=data.arrCaseName[i]+",";
							str+='<tr>'
								+ '<td> <input type="checkbox" class="icheckbox"/></td>'
								+'<td><a href="javascript:void(0)" class="valCell caseCode" valName="caseCode" bb="" >'+data.arrCaseCode[i]+'</a></td>'
								+'<td class="valCell" >'+data.arrCaseName[i]+'</td>'
								+'<td class="valCell">'+data.arrHandlingPeople[i]+'</td></tr>';
						}
					}
					$('#basicModel').append(str);
					$.util.topWindow().$.layerAlert.alert({title:"提示",msg:"不继续添加的话请返回"});
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index);
					 //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				//"zazllxCode" : zazllxCode
			},
			end:function(){
				
			}
		});
	}
	
	/**
	 * 重置
	 */
	function  resetAdd(){
		 onloadBasicData();
	}
	
	/**
	 * 保存修改的信息
	 */
	function saveAdd(){
		var zamcName=$('#zamc').val(); //专案名称
		var jyaqName=$('#jyaq').val(); //简要案情
		
			var demo = $.validform.getValidFormObjById("basicYZ") ;
			//是否有没通过的验证
			var flag = $.validform.check(demo) ;
			//是给予提示  
			if(!flag){
				return;
			}else{
				
				var b=$('#basicModel .caseCode');
				var insertArr=new Array();
				var delArr=new Array(); //需要删除的arr
				for(var i=0;i<arrOld.length;i++){
					var m=0;
					for(var j=0;j<b.length;j++){
						if($(b[j]).text()==arrOld[i]){
							insertArr.push(arrOld[i]);  //检出重复内容
							m=1;
							break;
						}
					}	
					if(m==0){
						delArr.push(arrOld[i]);
					}
				}
				
				var addArr=new Array(); //需要添加的arr
				for(var i=0;i<b.length;i++){
					var m=0;
					for(var j=0;j<insertArr.length;j++){
						if($(b[i]).text()==insertArr[j]){
							m=1;
							break;
						}
					}
					if(m==0){   //在页面上的没有和原来相同的内容及为新增的内容
						  var sonCode=new Object();
						  var arr=$(b[i]).closest('tr').find('.valCell');						  
						  sonCode.caseCode=$(arr[0]).text();
						  sonCode.caseName=$(arr[1]).text();
						  sonCode.workers=$(arr[2]).text();
						  addArr.push(sonCode);
					}
				}
				
				var data={
						zamcName:zamcName,   //专案名称
						jyaqName:jyaqName,  //简要案情
						mqjzjName:$('#mqjz').val(),  //目前工作进展情况
						zczywtName:$('#zczywt').val(),  //侦查工作中的主要问题
						xybjhName:$('#xybjh').val(),  //下一步工作计划
						ajxzName:$('#ajxz option:selected').text(), //案件性质
						zabh:$('#zabh').text(),
						caseId:caseId,
						delArr:delArr,
						addArr:addArr
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/wdza/updataSpecialCase.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						var a=successData.resultMap.result;
						if(a==true){
							$.layerAlert.alert({title:"提示",msg:"修改成功"});
						}else{
							$.layerAlert.alert({title:"提示",msg:"修改失败"});
						}
					}
				});	
			}
			
			
	}
	
	
	
	
	/**
	 * 维护案件基本信息--加载项
	 */
	function onloadBasicData(){
		$("#zamc").focus();
		var data={
				caseId:caseId
             }
	var queryStr = $.util.toJSONString(data);
		
		$.ajax({
			url:context +'/wdza/findMyUpdataProject.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			data:queryStr,
			success:function(successData){
				var ajxzList=successData.resultMap.ajxz;
				var specialCase=successData.resultMap.specialCase;
				var ajxz=specialCase.nature; //案件性质
				var b="";
				$("#ajxz").empty();
 				for(var i=0;i<ajxzList.length;i++){
 					if(ajxz==ajxzList[i].name){
 						b+='<option select="selected" value='+ajxzList[i].id+' >'+ajxzList[i].name+'</option>';
 					}else{
 						b+='<option value='+ajxzList[i].id+' >'+ajxzList[i].name+'</option>';
 					}
				}
 				$('#ajxz').append(b); //案件性质
 				$('#zabh').text(specialCase.code);//专案编号
 				$('#zamc').val(specialCase.name); //专案名称
 				$('#jyaq').val(specialCase.content); //简要案情
 				if(specialCase.progress!=null){
 					$('#mqjz').val(specialCase.progress);//目前工作进展情况
 				}
 				if(specialCase.problem!=null){
 					$('#zczywt').val(specialCase.problem);//侦查工作中的主要问题：
 				}
 				if(specialCase.plan!=null){
 					$('#xybjh').val(specialCase.plan);//下一步工作计划
 				}
 				var zaj=[];
 				zaj=specialCase.sonList;     //子案件
 				arrOld=[];
 				$('#basicModel').empty();
				var str="";
				for(var i=0;i<zaj.length;i++){
					arrOld.push(zaj[i].caseCode);
						str+='<tr>'
							+ '<td> <input type="checkbox" class="icheckbox"/></td>'
							+'<td><a href="javascript:void(0)" class="valCell caseCode" valName="caseCode" bb="" >'+zaj[i].caseCode+'</a></td>'
							+'<td class="valCell" >'+zaj[i].caseName+'</td>'
							+'<td class="valCell">'+zaj[i].workers+'</td></tr>';
				}
				$('#basicModel').append(str); 	
			}		
		
		});
	}
	
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.basicMessage, { 
		onloadBasicData : onloadBasicData
	});	
	
})(jQuery);