
(function($){
	"use strict";
	var table;
	var personName="";
	var personId="";
	var caseName="";
	var caseCode="";
	var caseCodeArr=[];
	var personArr=[];  
	var handlingPeople="";
	$(document).ready(function(){
		//$.szpt.util.businessData.addToInitSuccessCallBack(init);
		initTable();
		onloadData();
		/**
		 * 一个框模糊查询按钮
		 */
		$(document).on("click", "#findBtn", function(e){
			table.draw(true);
		});
		/**
		 * 一高级查询按钮
		 */
		$(document).on("click", "#findjq", function(e){
			table.draw(true);
			
		});
		/**
		 * 一高级查询重置按钮
		 */
		$(document).on("click", "#res", function(e){
			res();
		});
		/**
		 * 刷新按钮
		 */
		$(document).on("click", "#fu", function(e){
			window.location.href = context+"/zawh/showProjectsMaintenance.action";
		});
		
		/**
		 * 新增按钮
		 */
		$(document).on("click", "#addBtn", function(e){
			 showAddProject();
		});
	
		/**
		 * 修改
		 */
		$(document).on("click", "#updateBtn", function(e){
			 showUpdataProject();
		});
		
		/**
		 * 删除
		 */
		$(document).on("click", "#delBtn", function(e){
			 delectProject();
			 
		});
		/**
		 * 分配用户按钮
		 */
		$(document).on("click", "#allotUserBtn", function(e){
			allotUser();
			 
		});
		/**
		 * 专案组成员 弹窗
		 */
		$(document).on("click", "#findPerson", function(e){
			findPerson();
			 
		});
		/**
		 * 维护专案--子案件--跳转页面按钮
		 */
		$(document).on("click", ".sonCase", function(e){
			//alert($(this).text())//子案件id;
			var id=$(this).text();
			window.location.href = $.util.fmtUrl(context+"/caseTag/showLookCaseTagPage.action?id="+id);
		});
		/**
		 * 维护专案--情报数--跳转页面按钮
		 */
		$(document).on("click", ".information", function(e){
			//alert($(this).attr("caseId"))//专案id;
		});
		/**
		 * 维护专案--搜索条件--涉及子案件--弹窗
		 */
		$(document).on("click", "#sonProject", function(e){
			findSonProject();
		});
		
	});
		/**
		 * 涉及子案件
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
						var insertArr=[];  //需要新增的数组
						for(var k=0;k<data.arrCaseCode.length;k++){ //将新增的子案件code集合和已经添加的比较,有的就不添加
							var m=0;
							for(var j=0;j<caseCodeArr.length;j++){
								if(data.arrCaseCode[k]==caseCodeArr[j]){
									m=1;
									break;
								}
							}
							if(m==0){
								caseName+=data.arrCaseName[k]+",";
								caseCode+=data.arrCaseCode[k]+",";
								insertArr.push(data.arrCaseCode[k]);
							}
						}
						
						for(var i=0;i<insertArr.length;i++){
							caseCodeArr.push(insertArr[i]);//记录添加的案件编号
						}
						$("#sjzaj").val(caseName.substring(0,caseName.length-1));
						$("#sjzaj").attr("sonCaseCode",caseCode);
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
					 caseName="";
					 caseCode="";
					caseCodeArr=[];
				}
			});
		}
		/**
		 * 重置
		 */
		function res(){
			$("#nrmhcx").val("");
			$("#zabh").val("");
			$("#ajmc").val("");
			$("#jyaq").val("");;
			$("#ajxz").get(0).selectedIndex = 0; 
			$("#sjzaj").val("");
			$("#zazcy").val("");
			$("#zazcy").attr("personid","");
			$("#sjzaj").attr("soncasecode","");
		}
		
		/**
		 * 搜索人员
		 */
		function findPerson(){
			$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/zazlwh/findPerson.action',
				pageLoading : true,
				title:"查询人员",
				width : "830px",
				height : "520px",
				btn:["添加","返回"],
				callBacks:{
					btn1:function(index, layero){
						var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.zaList.zaFindPerson;
						var data = cm.getData();
						var insertArr=[];  //需要新增的数组
						for(var k=0;k<data.personId.length;k++){ //将新增的子案件code集合和已经添加的比较,有的就不添加
							var m=0;
							for(var j=0;j<personArr.length;j++){
								if(data.personId[k]==personArr[j]){
									m=1;
									break;
								}
							}
							if(m==0){
								personName+=data.personName[k]+",";
								personId+=data.personId[k]+",";
								insertArr.push(data.personId[k]);
							}
						}
						
						for(var i=0;i<insertArr.length;i++){
							personArr.push(insertArr[i]);//记录添加的案件编号
						}
						
						$("#zazcy").val(personName.substring(0,personName.length-1));
						$("#zazcy").attr("personId",personId);
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
					//lockerTable.draw(true);
					personName="";
					personId="";
					personArr=[];
				}
			});
		
		}
		/**
		 *  分配用户按钮
		 */
	    function allotUser(){
	    	var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
				var id=arrcheck.attr("bb");
				$.util.topWindow().$.layerAlert.dialog({
					content : context +  '/zawh/showAllotUserProject.action',
					pageLoading : true,
					title:"分配用户",
					width : "70%",
					height : "80%",
//					btn:["分配","取消"],
//					callBacks:{
//						btn1:function(index, layero){							
//							var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.specialCaseRoleAssignment ;
//							cm.update();
//						},
//						btn2:function(index, layero){
//							$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
//						}
//					},
					shadeClose : false,
					success:function(layero, index){
						
					},
					initData:{
						lockerId : id
					},
					end:function(){
						table.draw(true);
					}
				});
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
	    }
		
		
		/**
		 * 删除按钮
		 */
	    function delectProject(){
	    	var arrcheck=$('input[type="radio"]:checked');
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
							id:$('input[type="radio"]:checked').attr("bb")
					}
					var queryStr = $.util.toJSONString(data);
					$.ajax({
						url:context +'/zawh/deleteCase.action',
						type:"post",
						contentType: "application/json; charset=utf-8",
						dataType:"json",
						data:queryStr,
						success:function(successData){
							$.layerAlert.alert({title:"提示",msg:"删除成功"});
							table.draw(true);
						}
					});	
				
				}
			});
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
	    }
	    
	    /**
		 * 修改弹出框
		 */
		function showUpdataProject(){		
			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().location.href = context +  '/zawh/showUpdataProject.action?queryStr='+id;
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		}
	    
	    
		
		/**
		 * 新增
		 */
		function showAddProject(){
			window.location.href= context +  '/zawh/showAddProject.action';
		}	
	
	/**
	 * 加载表格
	 */	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/zawh/findZAData.action";
			tb.columnDefs = [
			                 
				{
					"targets": 0,
         	    	"title": "",
         	    	"className":"table-f",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return  '<input type="radio" name="askRoom" bb='+full.id+' class="icheckradio"/>';
         	    	}
				},{
					"targets": 1,
         	    	"title": "序号",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :2,
					"width" : "30%",
					"title" : "专案名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						var a="<div class='pull-left mar-right'><p target='_blank'><span class='zl-icon zl-icon-basic'></span></p></div>" +
						"<h2 class='row-mar font14'><p varId="+full.id+" varName="+full.name+" href='javascript:void(0)' class='zaNameA' target='_blank'>"+full.name+"</p></h2>" +
						"<p><span class='state state-yellow1 mar-right'>"+full.nature+"</span>"+full.createdTime+"</p>" +
						" <p class='font11 color-gray2'>最新修改时间："+full.updatedTime+"</p>";
						return a;
					}
				},
				{
					"targets" : 3,
					"title" : "涉及子案件",
					"data" : "sonIDList",
					"render" : function(data, type, full, meta) {
						var str="";
						for(var i=0;i<data.length;i++ ){
							str+="<p><a href='javascript:void(0)' class='sonCase' target='_blank' bb='"+data[i]+"' >"+full.sonCaseCodeList[i]+"</a></p>";
						}
						return str;
					}
				},
				{
					"targets" : 4,
					"title" : "简要案情",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 5,
					"title" : "专案组成员",
					"data" : "zazcyList",
					"render" : function(data, type, full, meta) {
						var str='<div class="fi-ceng-out"> <span class="icon-man-group"></span><div class="fi-ceng list-ceng"><ul class="list-group">';
						for(var i=0;i<full.zazcyList.length;i++){
							//str+="<p>"+full.zazcyList[i]+"</p>";
							str+='<li class="list-group-item row"><span class="name">'+full.roleList[i]+'：</span><span class="con">'+full.zazcyList[i]+'</span></li>';
						}
						str+='</ul></div></div>';
						return str;
					}
				},
				{
					"targets" : 6,
					"title" : "关联情报线索",
					"data" : "qbSize",
					"render" : function(data, type, full, meta) {
						return '<p href="javascript:void(0)" target="_blank" caseId='+full.id+' class="btn btn-bordered btn-sm information">'+full.qbSize+'</p>';
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
				var str
				if($('#nrmhcx').val()=='内容模糊查询'){
					var str='';
				}else{
					str=$('#nrmhcx').val();
				}
				var s1
				if($("#ajxz").find("option:selected").text()=='全部'){
					var s1='';
				}else{
					s1=$("#ajxz").find("option:selected").text();
				}
					var data = {
						"nrmhcx":str,
						"zabh":$("#zabh").val(),
						"zamc":$("#ajmc").val(),
						"jyaq":$("#jyaq").val(),
						"ajxz":s1,
						"sjzaj":$("#sjzaj").attr("soncasecode"),
						"zazcy":$("#zazcy").attr("personid"),
						"start":d.start,
						"length":d.length
					};
					var queryStr = $.util.toJSONString(data);
					$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.resultMap;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	}
	
	/**
	 * 专案维护加载项
	 */
	function onloadData(){
		$.ajax({
			url:context +'/zawh/findCaseProject.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		//	data:queryStr,
			success:function(successData){
				var ajxzList=successData.resultMap.ajxz;
				$("#ajxz").empty();
				var b="";
					b+='<option value="" >全部</option>';
 				for(var i=0;i<ajxzList.length;i++){
 					b+='<option value='+ajxzList[i].id+' >'+ajxzList[i].name+'</option>';
				}
 				$('#ajxz').append(b);
			}		
		
		});
	}
	
})(jQuery);	