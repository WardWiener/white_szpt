$.xsfxts = $.xsfxts || {} ;
$.xsfxts.xsList = $.xsfxts.xsList || {} ;
$.xsfxts.xsList.util = $.xsfxts.xsList.util || {} ;
(function($){
	"use strict";
	var table;
	var personName="";
	var personId="";
	var caseName="";
	var caseCode="";
	var handlingPeople="";
	var caseCodeArr=[];
	var personArr=[];
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
		 * 专案组成员
		 */
		$(document).on("click", "#findPerson", function(e){
			findPerson();
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
		 * 维护专案基本信息
		 */
		$(document).on("click", "#whMessage", function(e){
			showUpdataProject();
		});
		
		/**
		 * 维护专案--涉案人员
		 */
		$(document).on("click", "#involvedBtn", function(e){
			involvedPerson();
		});
		/**
		 * 维护专案--涉案人员关系
		 */
		$(document).on("click", "#relationBtn", function(e){
			involvedPersonRelation();
		});
		
		/**
		 * 维护专案--专案报告关系
		 *//*
		$(document).on("click", "#relationBtn", function(e){
			projectReport();
		});*/
		/**
		 * 维护专案--涉及子案件--跳转页面按钮
		 */
		$(document).on("click", ".sonCase", function(e){
			//alert($(this).attr("bb"))//子案件id;
			var id=$(this).text();
			window.location.href = $.util.fmtUrl(context+"/caseTag/showLookCaseTagPage.action?id="+id);
		});
		/**
		 * 维护专案--搜索条件--涉及子案件--弹窗
		 */
		$(document).on("click", "#sonProject", function(e){
			findSonProject();
		});
		/**
		 * 置顶和取消自定的div显隐
		 */
		$(document).on('ifChecked','input[name="askRoom"]', function(event){
			   var a=$('input[type="radio"]:checked').attr("ifzd");
			   if(a==0||a=='null'){
				   $('#stick').show();
				   $('#nostick').hide();
			   }else{
				   $('#nostick').show();
				   $('#stick').hide();
			   }
			
		});
		/**
		 * 置顶按钮
		 */
		$(document).on("click", "#stick", function(e){
			if($('.state-red1').length<5){
				   saveZD();
			   }else{
				   $.layerAlert.alert({title:"提示",msg:"已经超过置顶上限"});
			   }
		});
		/**
		 * 取消置顶
		 */
		$(document).on("click", "#nostick", function(e){
			saveQXZD();
		});
		/**
	    * 双击修改按钮
	    */
	    $(document).on('dblclick',".odd,.even",function(){
	    	var caseId=$(this).find('input.icheckradio').attr("bb");	
	    	showUpdataProjectByDB(caseId);
	    });
		
		
		/**
		 * 双击查看案件详情
		 */
	    function showUpdataProjectByDB(data){
	    	$.util.topWindow().$.layerAlert.dialog({
				content : context +  '/wdza/showUpdataProject.action',
				pageLoading : true,
				title:"我的专案",
				width : "60%",
				height : "80%",
				shadeClose : false,
				success:function(layero, index){
					
				},
				initData:{
					lockerId : data,  //专案id
					condition:'基本信息'
				},
				end:function(){
					table.draw(true);
				}
			});
	    }
	    
		
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
					var caseName="";
					var caseCode="";
					caseCodeArr=[];
				}
			});
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
		 * 维护专案--专案报告关系
		 */
		function projectReport(){

			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().location.href = context +  '/wdza/showProjectReport.action?queryStr='+id;
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		
		}
		
		/**
		 * 涉案人员关系弹窗
		 */
		function involvedPersonRelation(){
			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().$.layerAlert.dialog({
						content : context +  '/wdza/showInvolvedPerson.action',
						pageLoading : true,
						title:"我的专案",
						width : "60%",
						height : "80%",
						shadeClose : false,
						success:function(layero, index){
							
						},
						initData:{
							lockerId : id,  //专案id
							condition:'涉案人员关系'
						},
						end:function(){
							table.draw(true);
						}
					});
				//	$.util.topWindow().location.href = context +  '/wdza/showInvolvedPerson.action?queryStr='+id;
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		
		}
		
		/**
		 * 涉案人员
		 */
		function involvedPerson(){
			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().$.layerAlert.dialog({
						content : context +  '/wdza/showInvolvedPerson.action',
						pageLoading : true,
						title:"我的专案",
						width : "60%",
						height : "80%",
						shadeClose : false,
						success:function(layero, index){
							
						},
						initData:{
							lockerId : id,  //专案id
							condition:'涉案人员'
						},
						end:function(){
							table.draw(true);
						}
					});
				//	$.util.topWindow().location.href = context +  '/wdza/showInvolvedPerson.action?queryStr='+id;
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		}
		
		/**
		 * 维护专案基本信息页面--弹窗
		 */
		function showUpdataProject(){
			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().$.layerAlert.dialog({
						content : context +  '/wdza/showUpdataProject.action',
						pageLoading : true,
						title:"我的专案",
						width : "60%",
						height : "80%",
						shadeClose : false,
						success:function(layero, index){
							
						},
						initData:{
							lockerId : id,  //专案id
							condition:'基本信息'
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
		 * 置顶的方法
		 */
		function saveZD(){
            var data={
            		caseId:$('input[type="radio"]:checked').attr("bb")
			}
			var queryStr = $.util.toJSONString(data);
			$.ajax({
				url:context +'/wdza/saveZD.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var a=successData.resultMap.result;
					if(a==true){
						$.layerAlert.alert({title:"提示",msg:"置顶成功"});
						table.draw(true);
					}else{
						$.layerAlert.alert({title:"提示",msg:"置顶失败"});
					}
				}
			});	
		
		
		};
		
		/**
		 * 取消置顶的方法
		 */
		function saveQXZD(){
            var data={
            		caseId:$('input[type="radio"]:checked').attr("bb")
			}
			var queryStr = $.util.toJSONString(data);
			$.ajax({
				url:context +'/wdza/saveQXZD.action',
				type:"post",
				contentType: "application/json; charset=utf-8",
				dataType:"json",
				data:queryStr,
				success:function(successData){
					var a=successData.resultMap.result;
					if(a==true){
						$.layerAlert.alert({title:"提示",msg:"取消置顶成功"});
						table.draw(true);
					}else{
						$.layerAlert.alert({title:"提示",msg:"取消置顶失败"});
					}
					
				}
			});	
		
		
		};
		/**
		 * 重置按钮
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
		 * 修改弹出框
		 */
		/*function showUpdataProject(){		
			var arrcheck=$('input[type="radio"]:checked');
			if(arrcheck.length==1){
					var	id=$('input[type="radio"]:checked').attr("bb");
					$.util.topWindow().location.href = context +  '/wdza/showUpdataProject.action?queryStr='+id;
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		}*/
		
		
		
	
	/**
	 * 加载表格
	 */	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findMyProjectTable.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"title": "",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return  '<input type="radio" name="askRoom" bb='+full.id+' ifzd='+full.isStick+' class="icheckradio"/>';
         	    	}
				},{
					"targets": 1,
         	    	"title": "是否置顶",
         	    	"className":"table-checkbox",
         	    	"data": "isStick" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		if(data==1){
         	    			return  '<span class="state  state-red1">置顶</span>';
         	    		}else{
         	    			return "";
         	    		}
         	    		 
         	    	}
				},{
					"targets": 2,
         	    	"title": "序号",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :3,
					"width" : "30%",
					"title" : "专案名称",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						var a="<div class='pull-left mar-right'><p target='_blank'><span class='zl-icon zl-icon-basic'></span></p></div>" +
						"<h2 class='row-mar font14'><p  target='_blank'>"+full.name+"</p></h2>" +
						"<p><span class='state state-yellow1 mar-right'>"+full.nature+"</span>"+full.createdTime+"</p>" +
						" <p class='font11 color-gray2'>最新修改时间："+full.updatedTime+"</p>";
						return a;
					}
				},
				{
					"targets" : 4,
					"title" : "涉及子案件",
					"data" : "sonIDList",
					"render" : function(data, type, full, meta) {
						var str="";
						for(var i=0;i<data.length;i++ ){
							str+="<p><a href='javascript:void(0)' target='_blank' class='sonCase' bb='"+data[i]+"' >"+full.sonCaseCodeList[i]+"</a></p>";
						}
						return str;
					}
				},
				{
					"targets" : 5,
					"title" : "简要案情",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 6,
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
						"sjzaj":$("#sjzaj").val(),
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
	 * 我的专案加载项
	 */
	function onloadData(){
		$.ajax({
			url:context +'/wdza/findMyProject.action',
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
	});
})(jQuery);	