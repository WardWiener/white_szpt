$.specialCaseRoleAssignment = $.specialCaseRoleAssignment || {};
(function($){
	"use strict";
	var i=1;
	var table;
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var caseId = initData.lockerId;
	var updataBtncount=0;
	$(document).ready(function(){
		  initTable();
	}) 
		 /**
		 * 添加涉案人员
		 */
		$(document).on('click',"#addBtn",function(){
			addInvolvedPerson();
		});
		 /**
		 * 删除涉案人员
		 */
		$(document).on('click',"#remBtn",function(){
			remInvolvedPerson();
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
			save();
		});
		 /**
		 * 取消按钮
		 */
		$(document).on('click',"#resBtn",function(){
			$.validform.closeAllTips("involvedYZ") ;	
			table.draw(true);
		});
	   /**
	    * 双击修改按钮
	    */
	    $(document).on('dblclick',".odd,.even",function(){
	    	updataByDB($(this));	
	    });
	   
	    /**
	     * 双击修改
	     */
	    function updataByDB(data){
	    	data.attr("class","addTr");  //将该行定义为可保存的行
	    	data.find(".table-checkbox").html("");
	    	data.find(".name").html('<input datatype="*0-20" id="name" value="'+data.find(".name").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".nick").html('<input datatype="*0-20" id="nick" value="'+data.find(".nick").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".idcard").html('<input datatype="null/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/" value="'+data.find(".idcard").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".phone").html('<input datatype="null/^\\d{11}$/" value="'+data.find(".phone").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".householdRegister").html('<input datatype="*0-20" value="'+data.find(".householdRegister").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".householdAddress").html('<input datatype="*0-20" value="'+data.find(".householdAddress").text()+'" class="form-control input-sm tabelCell">')
	    	data.find(".createPerson").attr("bb",currentUserId);
	    	data.find(".createPerson").html(currentUserName);
	    	data.find(".createPerson").attr("class","tabelCell");
	    	data.find(".createdTime").html($.date.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    	data.find(".createdTime").attr("class","tabelCell");
	    	$("#name").focus();
	    }
	
		
		
		/**
		 * 添加涉案人员
		 */
		function addInvolvedPerson(){
			i++;
			if($('.addTr').length>0){    //有新增待保存内容
				if($('#name').val()==""&&$('#nick').val()==""){  //验证名称和绰号有没有二填一
					$.layerAlert.alert({title:"提示",msg:"姓名和绰号至少填写一项"});
				}else{
					$('#name').attr("id",$('#name').attr("id")+i);
					$('#nick').attr("id",$('#nick').attr("id")+i);
					var a='<tr class="addTr"><td></td><td></td>'+
					'<td><input datatype="*0-20" id="name" value="" class="form-control input-sm tabelCell"></td>'+
					'<td><input datatype="*0-20" id="nick" value="" class="form-control input-sm tabelCell"></td>'+
					'<td>'+
					'<span class="color-gray">身份证:</span>'+
					'<p><input datatype="null/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/" class="form-control input-sm tabelCell"></p>'+
					'<span class="color-gray">手机号:</span>'+
					'<p><input datatype="null/^\\d{11}$/" class="form-control input-sm tabelCell"></p></td>'+
					'<td>'+ 
					'<span class="color-gray">籍贯:</span>'+
					'<p><input datatype="*0-20" class="form-control input-sm tabelCell"></p>'+
					'<span class="color-gray">户籍:</span>'+
					'<p><input datatype="*0-20" class="form-control input-sm tabelCell"></p><br></td>'+
					'<td><p class="tabelCell" bb="'+currentUserId+'">'+currentUserName+'</p><br><p class="tabelCell">'+$.date.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss")+'</p><input class="tabelCell" type="text" style="display:none"></td>'+
					'</tr>';
					$('tbody').append(a);
					$("#name").focus();
				}
			}else{
				var a='<tr class="addTr"><td></td><td></td>'+
				'<td><input datatype="*0-20" id="name" value="" class="form-control input-sm tabelCell"></td>'+
				'<td><input datatype="*0-20" id="nick" value="" class="form-control input-sm tabelCell"></td>'+
				'<td>'+
				'<span class="color-gray">身份证:</span>'+
				'<p><input datatype="null/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/" class="form-control input-sm tabelCell"></p>'+
				'<span class="color-gray">手机号:</span>'+
				'<p><input datatype="null/^\\d{11}$/" class="form-control input-sm tabelCell"></p></td>'+
				'<td>'+ 
				'<span class="color-gray">籍贯:</span>'+
				'<p><input datatype="*0-20"  class="form-control input-sm tabelCell"></p>'+
				'<span class="color-gray">户籍:</span>'+
				'<p><input datatype="*0-20" class="form-control input-sm tabelCell"></p><br></td>'+
				'<td><p class="tabelCell" bb="'+currentUserId+'">'+currentUserName+'</p><br><p class="tabelCell">'+$.date.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss")+'</p><input class="tabelCell" type="text" style="display:none"></td>'+
				'</tr>';
				$('tbody').append(a);
				$("#name").focus();
			}
		}
		
	    /**
	     * 保存--涉案人员
	     */
		function save(){
			if($('.addTr').length>0){
				if($('#name').val()==""&&$('#nick').val()==""){  //验证名称和绰号有没有二填一
					$.layerAlert.alert({title:"提示",msg:"姓名和绰号至少填写一项"});
				}else{
					var demo = $.validform.getValidFormObjById("involvedYZ") ;
					//是否有没通过的验证
					var flag = $.validform.check(demo) ;
					//是给予提示  
					if(!flag){
						return;
					}else{
						if(updataBtncount==1){
							return;
						}
						updataBtncount++;
						var arr=$('.addTr .tabelCell');  //获取所有的节点
						   var tab=new Array();
						for(var i=0;i<arr.length/9;i++){
							var b=new Object();
							b.name=$(arr[i*9+0]).val();
							b.nick=$(arr[i*9+1]).val();
							b.idcard=$(arr[i*9+2]).val();
							b.phone=$(arr[i*9+3]).val();
							b.householdRegister=$(arr[i*9+4]).val();
							b.householdAddress=$(arr[i*9+5]).val();
							b.createPersonId=$(arr[i*9+6]).attr("bb");
							b.createdTime=$(arr[i*9+7]).text();
							b.id=$(arr[i*9+8]).val();
							tab.push(b);
						}
						var data={
								listTable:tab,
								caseId:caseId
						}
						var queryStr = $.util.toJSONString(data);
						$.ajax({
							url:context +'/wdza/saveInvolvedPerson.action',
							type:"post",
							contentType: "application/json; charset=utf-8",
							dataType:"json",
							data:queryStr,
							success:function(successData){
								var a=successData.resultMap.result;
								if(a==true){
									$.layerAlert.alert({title:"提示",msg:"保存成功"});
									table.draw(true);
									updataBtncount=0;
								}else{
									$.layerAlert.alert({title:"提示",msg:"保存失败"});
								}
							},
							error:function(data){
								updataBtncount=0;
							}
						});	
					}
					   
				
				}
			}else{
				table.draw(true);
			}
		}
		
	
	/**
	 * 移除涉案人员案件
	 */
	function remInvolvedPerson(){
    	var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length!=0){
    	$.util.topWindow().$.layerAlert.confirm({
			msg:"删除后不可恢复，确定要删除吗？",
			title:"删除",	  //弹出框标题
			width:'300px',
			hight:'200px',
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,  //弹出时的动画效果  有0-6种
			yes:function(index, layero){
				var arr=[];
				$('input[type="checkbox"]:checked').each(function(){
					arr.push($(this).attr("bb"));
				})
	            var data={
	            		InvolvedPersonID:arr,
						caseID:caseId
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/wdza/deleteInvolvedPerson.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						var a=successData.resultMap.result;
						if(a==true){
							$.layerAlert.alert({title:"提示",msg:"删除成功"});
							table.draw(true);
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
	 * 加载表格
	 */
	function initTable(){
		if(table!=null){
			table=null;
		}
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findInvolvedPersonTable.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"title": "",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return  '<input type="checkbox" name="askRoom" bb='+data+' class="icheckbox"/>';
         	    	}
				},{
					"targets": 1,
         	    	"title": "序号",
         	    	"width" : "8%",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :2,
					"width" : "15%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return '<p class="name">'+data+'</p>';
					}
				},
				{
					"targets" : 3,
					"width" : "10%",
					"title" : "绰号",
					"data" : "nick",
					"render" : function(data, type, full, meta) {
						return '<p class="nick">'+data+'</p>';
					}
				},
				{
					"targets" : 4,
					"width" : "27%",
					"title" : "身份证号、手机号",
					"data" : "phone",
					"render" : function(data, type, full, meta) {
						var  str='<span class="color-gray">身份证:</span><p class="idcard">'+full.idcard+'</p><span class="color-gray">手机号:</span><p class="phone">'+data+'<br></p></td>';
						return str;
					}
				},
				{
					"targets" : 5,
					"width" : "27%",
					"title" : "籍贯 、户籍",
					"data" : "householdRegister",
					"render" : function(data, type, full, meta) {
						var str='<span class="color-gray">籍贯:</span><p class="householdRegister">'+data+'</p><span class="color-gray">户籍:</span><p class="householdAddress">'+full.householdAddress+'</p>';
						return str;
					}
				},
				{
					"targets" : 6,
					"width" : "27%",
					"title" : "录入人员及时间",
					"data" : "createPerson",
					"render" : function(data, type, full, meta) {
						var str='<p class="createPerson">'+data.name+'</P><br><p class="createdTime">'+$.date.timeToStr(full.createdTime, "yyyy-MM-dd HH:mm:ss")+'</p><input value="'+full.id+'" class="tabelCell" type="text" style="display:none">';
						return str;
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
			//是否分页
			tb.paging=true;
			
			//请求参数
			tb.paramsReq = function(d, pagerReq){
					var data = {
						"caseId":caseId,
						"start":d.start,
						"length":d.length
					};
					var queryStr = $.util.toJSONString(data);
					$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.pageList;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	
	}
	  
	/**
	 * 维护案件基本信息--加载项
	 */
	function onloadData(){
		updataBtncount=0;
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
	
	
})(jQuery);