$.gradeTemplateList = $.gradeTemplateList || {};
(function($){
	"use strict"
	
	var cgtbTable = null;
	var addBtncount=0;
	var  updataBtncount=0;
	$(document).ready(function(){
		onloadData();
	})
		/**
		 * keydown 新增保存按钮
		 */
		$(document).on("keydown", "#resadd", function(event){
			event=document.all?window.event:event;
	  	    if((event.keyCode || event.which)==13){
	  	    	saveRole();
	  	    }
		})
		/**
		 * keydown 修改保存按钮
		 */
		$(document).on("keydown", "#resUpdate", function(event){
			event=document.all?window.event:event;
	  	    if((event.keyCode || event.which)==13){
	  	    	saveUpdateRole();
	  	    }
		})
		/**
		 * 新增的重置按钮
		 */
		$(document).on("click", "#resetBut", function(){
			saveRes();
		})
		/**
		 * 新增的保存按钮
		 */
		$(document).on("click", "#saveBut", function(){
			saveRole();
		})
		/**
		 * 新增
		 */
		$(document).on('click',"#newBut",function(){
			newRoleDiv();
		});
		/**
		 * 修改的重置按钮
		 */
		$(document).on("click", "#resetUpdateBut", function(){
			updateRes();
		})
		/**
		 * 修改的保存按钮
		 */
		$(document).on("click", "#saveUpdatBut", function(){
			saveUpdateRole();
		})
		/**
		 * 修改
		 */
		$(document).on('click',"#modifyBut",function(){
			updateRole();
		});
		/**
		 * 删除
		 */
		$(document).on('click',"#deleteBut",function(){
			deleteRole();
		});
		
		/**
		 * 启用
		 */
		$(document).on('click',"#enabledBut",function(){
			startUsingRole();
		});
		/**
		 * 停用
		 */
		$(document).on('click',"#disableBut",function(){
			stopUsingRole();
		});
		
		/**
		 * 领导角色分配用户
		 */
		$(document).on('click',"#assigningRolesBut",function(){
			assigningRoles();
		});
		/**
		 * 
		 */
		$(document).on('click',"#wordBtn",function(){
			window.location.href=context +'/iweboffice2000/createTemplateFileDD.action';
		});
			
	
	   
	
		
		function newRoleDiv(){
			$('#addBtn').show();
			$('#allBtn').hide();
			var bn="";
			bn+="<li><span class='icon-dot icon-dot-qy'></span>"
				+"<p class='checkbox'><input type='checkbox' class='icheckbox'></p>"
	            +"<h2 class='name'><input datatype='*1-20' class='form-control input-sm' value='' id='resadd'></h2>" +
	            "</li>";
			$('#perList').append(bn);
			$("#resadd").focus();
		}
	
	    /**
	     * 新增重置按钮
	     */
	    function saveRes(){
	    	onloadData();
			$('#addBtn').hide();
			$('#allBtn').show();
	    }
		
		 /**
	     * 修改重置按钮
	     */
	    function updateRes(){
	    	onloadData();
			$('#updateBtn').hide();
			$('#allBtn').show();
	    }
	    
	    
		/**
		 * 新增 保存
		 */
		function saveRole(){
			
			var name=$('#resadd').val();
			var demo = $.validform.getValidFormObjById("yanzheng") ;
			//是否有没通过的验证
			var flag = $.validform.check(demo) ;
			//是给予提示  
			if(!flag){
				return;
			}else{
				var arrname=new Array();
				$('.name').each(function(){
					arrname.push($(this).text());
				})
				var a=0;
				for(var i=0;i<arrname.length;i++){
					if(name==arrname[i]){
						a++;
					}
				}
				if(a==0){
					if(addBtncount==1){
						return;
					}
					addBtncount++;
					var data={
							name:name
					}
					var queryStr = $.util.toJSONString(data);
					$.ajax({
						url:context +'/zagl/saveSpecialCaseRole.action',
						type:"post",
						contentType: "application/json; charset=utf-8",
						dataType:"json",
						data:queryStr,
						success:function(successData){
							$.layerAlert.alert({title:"提示",msg:"保存成功",icon:1});
							onloadData();
							$('#addBtn').hide();
							$('#allBtn').show();
						},
						error:function(data){
							addBtncount=0;
						}
					});	
				}else{
					$.layerAlert.alert({title:"提示",msg:"角色名称重复,请重新填写"});
				}
			}
		
		}
		/**
		 * 修改
		 */
		function updateRole(){
			var arrcheck=$('input[type="checkbox"]:checked');
			if(arrcheck.length==1){
				if($.common.ROLE_TYPE.ROLE_TYPE_YZ==$('input[type="checkbox"]:checked').attr("lx")){
					$.layerAlert.alert({title:"提示",msg:"该角色不可修改"});
				}else{
					var b=$('input[type="checkbox"]:checked').attr("bb");
					var val=$('#chenge'+b).text();
					$('#chenge'+b).html("<input datatype='*1-20' class='form-control input-sm' id='resUpdate'  value='"+val+"'>");
					$('#updateBtn').show();
					$('#allBtn').hide();
					$("#resUpdate").focus();
				}
				
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		};
		
		/**
		 * 修改保存按钮
		 */
         function saveUpdateRole(){
	        	
				var name=$('#resUpdate').val();
				var demo = $.validform.getValidFormObjById("yanzheng") ;
				//是否有没通过的验证
				var flag = $.validform.check(demo) ;
				//是给予提示  
				if(!flag){
					return;
				}else{
					var arrname=new Array();
					$('.name').each(function(){
						arrname.push($(this).text());
					})
					var a=0;
					for(var i=0;i<arrname.length;i++){
						if(name==arrname[i]){
							a++;
						}
					}
					if(a==0){
						if(updataBtncount==1){
							return;
						}
						updataBtncount++;
						var data={
								code:$('input[type="checkbox"]:checked').attr("bb"),
								name:$('#resUpdate').val()
						}
						var queryStr = $.util.toJSONString(data);
						$.ajax({
							url:context +'/zagl/updateSpecialCaseRole.action',
							type:"post",
							contentType: "application/json; charset=utf-8",
							dataType:"json",
							data:queryStr,
							success:function(successData){
								onloadData();
								$('#updateBtn').hide();
								$('#allBtn').show();
							},
							error:function(data){
								updataBtncount=0;
							}
						})
					}else{
						$.layerAlert.alert({title:"提示",msg:"角色名称重复,请重新填写"});
					}
				}
			
         }
		
		/**
		 * 删除
		 */
		function  deleteRole(){
			var arrcheck=$('input[type="checkbox"]:checked');
			if(arrcheck.length==1){
				if($.common.ROLE_TYPE.ROLE_TYPE_YZ==$('input[type="checkbox"]:checked').attr("lx")){
					$.layerAlert.alert({title:"提示",msg:"该角色不可删除"});
				}else{
					$.util.topWindow().$.layerAlert.confirm({
						msg:"删除后不可恢复，确定要删除吗？",
						title:"删除",	  //弹出框标题
						width:'300px',
						hight:'200px',
						icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
						shift:1,  //弹出时的动画效果  有0-6种
						yes:function(index, layero){

				            var data={
									code:$('input[type="checkbox"]:checked').attr("bb")
							}
							var queryStr = $.util.toJSONString(data);
				            
				            $.ajax({    //验证该角色有没有分配人员
								url:context +'/zagl/findSpecialCaseRolePerson.action',
								type:"post",
								contentType: "application/json; charset=utf-8",
								dataType:"json",
								data:queryStr,
								success:function(successData){
									var bn=successData.resultMap.result;
									if(bn==true){
										$.layerAlert.alert({title:"提示",msg:"该角色已分配用户，不可删除。。"});
									}else{
										$.ajax({
											url:context +'/zagl/deleteSpecialCaseRole.action',
											type:"post",
											contentType: "application/json; charset=utf-8",
											dataType:"json",
											data:queryStr,
											success:function(successData){
												onloadData();
											}
										});
									}
								}
							});	
				            
						
						}
					});
				}
				
			}else{
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}
		
		}
		
		/**
		 * 启用
		 */
		function startUsingRole(){			
			var b=$('input[type="checkbox"]:checked');//获取选中的启用对象
			if(b.length==0){
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}else{
				var arr=new Array();
				$('input[type="checkbox"]:checked').each(function(){
					arr.push($(this).attr("bb"));
				})
				var data={
						code:arr
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/zagl/updateStartSpecialCaseRole.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						onloadData();
					}
				});	
	        }
		}
		
		/**
		 * 停用
		 */
		function stopUsingRole(){
			
			var b=$('input[type="checkbox"]:checked');//获取选中的对象
			if(b.length==0){
				$.layerAlert.alert({title:"提示",msg:"请勾选一项"});
			}else{
				var arr=new Array();
				$('input[type="checkbox"]:checked').each(function(){
					arr.push($(this).attr("bb"));
				})
				var data={
						code:arr
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/zagl/updateStopSpecialCaseRole.action',
					type:"post",
					contentType: "application/json; charset=utf-8",
					dataType:"json",
					data:queryStr,
					success:function(successData){
						onloadData();
					}
				});	
	        }
			}
		
			
		
		
		/**
		 * 暴露本js方法，让其它js可调用
		 */
		jQuery.extend($.gradeTemplateList, { 
			
		});	

			
	/**
	 * 加载页面时载入
	 */
	function onloadData(){
		$.validform.closeAllTips("yanzheng") ;		
		$('#perList').empty();
		$.ajax({
			url:context +'/zagl/findonloadData.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",			
			success:function(successData){
				var  map=successData["resultMap"];
				var dataList = [];
				dataList= map["dataList"];
				var bn="";
				for(var i=0;i<dataList.length;i++){
					if($.common.DICT.DICT_ENABLED==dataList[i].state){						
						bn+="<li><span id=zt"+dataList[i].code+" class='icon-dot icon-dot-qy'></span>";
//							+"<h2 class='name'id=chenge"+dataList[i].code+">"+dataList[i].name+"</h2>" +
//							"<p class='checkbox'><input type='checkbox' class='icheckbox' bb="+dataList[i].code+" lx="+dataList[i].type+"></p></li>";
					}else{
						bn+="<li><span id=zt"+dataList[i].code+" class='icon-dot icon-dot-ty'></span>";
//							+"<h2 class='name'id=chenge"+dataList[i].code+">"+dataList[i].name+"</h2>" +
//							"<p class='checkbox'><input type='checkbox' class='icheckbox' bb="+dataList[i].code+" lx="+dataList[i].type+"></p></li>";
					}
					if($.common.ROLE_TYPE.ROLE_TYPE_YZ==dataList[i].type){
						bn+="<h2 class='name'id=chenge"+dataList[i].code+">"+dataList[i].name+"</h2>"
						+"<span class='yuzhi'>系统预置</span>";
					}else{
						bn+="<h2 class='name'id=chenge"+dataList[i].code+">"+dataList[i].name+"</h2>" +
						"<p class='checkbox'><input type='checkbox' class='icheckbox' bb="+dataList[i].code+" lx="+dataList[i].type+"></p></li>";
					}
				}					
				$('#perList').append(bn)
				updataBtncount=0;
				addBtncount=0;			
			}
		});	
	}
	
	/**
	 * 领导角色分配
	 */
	function assigningRoles(){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/zagl/showSpecialCaseRoleAssignment.action',
			pageLoading : true,
			title:"领导角色分配用户",
			width : "70%",
			height : "80%",
//			btn:["保存","取消"],
//			callBacks:{
//				btn1:function(index, layero){
//					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.specialCaseRoleAssignment ;
//					cm.update();
//				},
//				btn2:function(index, layero){
//					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
//				}
//			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			end:function(){
				//cgtbTable.draw(true);
			}
		});
	}	
})(jQuery);