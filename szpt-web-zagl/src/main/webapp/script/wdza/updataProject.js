$.specialCaseRoleAssignment = $.specialCaseRoleAssignment || {};
(function($){
	"use strict";
	var i=1;
	$(document).ready(function(){
		  onloadData();
		 /**
		 * 添加子案件btn
		 */
		$(document).on('click',"#addZAJBtn",function(){
			addZAJ();
		});
		 /**
		 * 删除子案件btn
		 */
		$(document).on('click',"#remZAJBtn",function(){
			remZAJ();
		});
		 /**
		 * 关闭页面返回上一级页面
		 */
		$(document).on('click',"#closeAdd",function(){
			window.history.back();
		});
		
		 /**
		 * 修改保存按钮
		 */
		$(document).on('click',"#saveBtn",function(){
			saveAdd();
		});
		 /**
		 * 新增重置按钮
		 */
		$(document).on('click',"#resBtn",function(){
			resetAdd();
		});
	  
	  
	})
	
	
	/**
	 * 重置
	 */
	function  resetAdd(){
		 onloadData();
	}
	
	/**
	 * 保存修改的信息
	 */
	function saveAdd(){
		var zamcName=$('#zamc').val(); //专案名称
		var jyaqName=$('#jyaq').val(); //简要案情
		if(zamcName==""||""==jyaqName){
			$.layerAlert.alert({title:"提示",msg:"带星号内容不可为空"});
		}else{

			var data={
					zamcName:zamcName,   //专案名称
					jyaqName:jyaqName,  //简要案情
					mqjzjName:$('#mqjz').val(),  //目前工作进展情况
					zczywtName:$('#zczywt').val(),  //侦查工作中的主要问题
					xybjhName:$('#xybjh').val(),  //下一步工作计划
					ajxzName:$('#ajxz option:selected').text(), //案件性质
					caseId:caseId
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
						creatTable();
						creatTable2();						
					}else{
						$.layerAlert.alert({title:"提示",msg:"修改失败"});
					}
				},
				error:function(data){
					$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
				}
			});	
		
		}
	
	}
	
	
	/**
	 * 移除子案件
	 */
	function remZAJ(){
    	var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==1){
    	$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){

	            var data={
	            		caseCode:$('input[type="checkbox"]:checked').attr("bb"),
						caseID:$('input[type="checkbox"]:checked').attr("caseID")
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/wdza/deleteSonCase.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						var a=successData.resultMap.result;
						if(a==true){
							$.layerAlert.alert({title:"提示",msg:"删除成功"});
						}else{
							$.layerAlert.alert({title:"提示",msg:"删除失败"});
						}
						onloadData();
					},
					error:function(data){
						$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
					}
				});	
			
			}
		});
		}else{
			$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
		}
    
	}
	
	
	
	
	/**
	 * 添加关联情报
	 */
//	function addInformation(){
//		
//		var objTableTemplate = $("#model");
//		var objTable = objTableTemplate.clone(false);
//		objTable.show();
//		objTable.insertBefore(objTableTemplate);
//		$.each(objTable.find(".valCell"),function(){
//			//$(this).text(recordInfoLst[i][$(this).attr("valName")]);
//			$(this).attr("id",$(this).attr("id")+i);
//		})
//		objTable.attr("id",objTable.attr("id")+i);  //被克隆对象id加一
//		$('#xh'+i).text(i);
//		
//		objTable.find(".toIcheckbox").each(function(i, val){
//			$(val).attr("id", Math.uuid()) ;
//			$(val).addClass("icheckbox") ;
//			$(val).iCheck('uncheck');
//		});
//		i+=1;
//	}
	  
	/**
	 * 维护案件基本信息--加载项
	 */
	function onloadData(){
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
 				$('#jyaq').text(specialCase.content); //简要案情
 				if(specialCase.progress!=null){
 					$('#mqjz').text(specialCase.progress);//目前工作进展情况
 				}
 				if(specialCase.problem!=null){
 					$('#zczywt').text(specialCase.problem);//侦查工作中的主要问题：
 				}
 				if(specialCase.plan!=null){
 					$('#xybjh').text(specialCase.plan);//下一步工作计划
 				}
 				var zaj=[];
 				zaj=specialCase.caseRelation;     //子案件
 				for(var i=0;i<zaj.length;i++){
 					var objTableTemplate = $("#model");
 					var objTable = objTableTemplate.clone(false);
 					objTable.show();
 					objTable.insertBefore(objTableTemplate);
 					$.each(objTable.find(".valCell"),function(){
 						$(this).text(zaj[i][$(this).attr("valName")]);
 					})
 					$.each(objTable.find(".toIcheckbox"),function(){
 						$(this).attr("bb",zaj[i].caseCode); //子案件ID
 						$(this).attr("caseID",specialCase.id);  //专案id;
 						$(this).attr("class","icheckbox");
 						$(this).iCheck('uncheck');
 					})
 					$.each(objTable.find("a"),function(){
 						$(this).attr("bb",zaj[i].caseCode); //子案件ID  //删除子案件所需的id
 					})
 					objTable.attr("id",objTable.attr("id")+1);
 				}
			},
			error:function(data){
				$.layerAlert.alert({title:"提示",msg:"系统繁忙。。。"});
			}
			
		
		});
	}
	
	
	
//	/**
//	 * 暴露本js方法，让其它js可调用
//	 */
//	jQuery.extend($.specialCaseRoleAssignment, { 
//		update : update
//	});	
	
})(jQuery);