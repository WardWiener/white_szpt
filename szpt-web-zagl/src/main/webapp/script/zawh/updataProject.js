$.specialCaseRoleAssignment = $.specialCaseRoleAssignment || {};
(function($){
	"use strict";
	var i=1;
	var  updataBtncount=0;
	var arrOld=new Array();
	$(document).ready(function(){
		  onloadData();
		 /**
		 * 添加关联q情报btn
		 */
		$(document).on('click',"#addBtn",function(){
			addInformation();
		});
		 /**
		 * 关闭新增页面返回上一级页面
		 */
		$(document).on('click',"#closeAdd",function(){
			window.history.back();
			//window.location.href=context+'/zawh/showProjectsMaintenance.action';
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
		/**
		 * 返回按钮
		 */
		$(document).on('click',"#retBtn",function(){
			window.history.back();
			//window.location.href=context+'/zawh/showProjectsMaintenance.action';
		});
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
					$.layerAlert.alert({title:"提示",msg:"不继续添加的话请返回"});
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
		 onloadData();
	}
	
	
	/**
	 * 修改保存
	 */
	function saveAdd(){
			var zamcName=$('#zamc').val();
			var jyaqName=$('#jyaq').val();
			var demo = $.validform.getValidFormObjById("updataYZ") ;
			//是否有没通过的验证
			var flag = $.validform.check(demo) ;
			//是给予提示  
			if(!flag){
				return ;
			}else{
				if(updataBtncount==1){
					return;
				}
				updataBtncount++;
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
						zamcName:zamcName,
						jyaqName:jyaqName,
						gzjzName:$('#gzjz').val(),
						sjzajName:$('#sjzaj').val(),
						zabhName:$('#zabh').text(),
						ajxzName:$('#ajxz option:selected').text(),
						delArr:delArr,
						addArr:addArr,
						zaid:id
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/zawh/updataSpecialCase.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						window.location.href=context +'/zawh/showProjectsMaintenance.action';
					}
				});	
				updataBtncount=0;
			}

	}
	
	/**
	 * 添加关联情报
	 */
	function addInformation(){
		
		var objTableTemplate = $("#model");
		var objTable = objTableTemplate.clone(false);
		objTable.show();
		objTable.insertBefore(objTableTemplate);
		$.each(objTable.find(".valCell"),function(){
			//$(this).text(recordInfoLst[i][$(this).attr("valName")]);
			$(this).attr("id",$(this).attr("id")+i);
		})
		objTable.attr("id",objTable.attr("id")+i);  //被克隆对象id加一
		$('#xh'+i).text(i);
		
		objTable.find(".toIcheckbox").each(function(i, val){
			$(val).attr("id", Math.uuid()) ;
			$(val).addClass("icheckbox") ;
			$(val).iCheck('uncheck');
		});
		i+=1;
	}
	  
	/**
	 * 修改专案加载项
	 */
	function onloadData(){
		$("#zamc").focus();
		$.validform.closeAllTips("updataYZ") ;
		var childs=$('#zaglleftDIV a');
		for(var  i=0;i<childs.length;i++){
			if($(childs[i]).html()=="专案维护"){
				$(childs[i]).attr("class","clickActive");
			}else{
				$(childs[i]).attr("class","");
			}
		}
		var data={
		      id:id
             }
	var queryStr = $.util.toJSONString(data);
		
		$.ajax({
			url:context +'/zawh/findUpdataProject.action',
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
 				$('#ajxz').append(b);
 				$('#zabh').text(specialCase.code);
 				$('#zamc').val(specialCase.name);
 				$('#jyaq').val(specialCase.content);
 				$('#gzjz').val(specialCase.progress);
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
	
	//	/**
//	 * 暴露本js方法，让其它js可调用
//	 */
//	jQuery.extend($.specialCaseRoleAssignment, { 
//		update : update
//	});	
	
})(jQuery);