;(function($){
	"use strict";
	var table;
	var modifyIndex = null;
	var zTree; 
	var searchingTypeId ;
	var flag;
	var treeNodes;
	var item_stop="停用";
	var item_start="启用";
//	var searchingTypeCode;
	$(document).ready(function() {
      loadsearchKey();
//数据字典项树
 var setting = {   
	    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
	    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
	    treeNodeParentKey : "searchingTypeId",        //在isSimpleData格式下，当前节点的父节点id属性  
	    showLine : true,                  //是否显示节点间的连线  
	    checkable : true  ,
	    global:false,
	    async: {
			enable: true,
			global: false,
			url : context + "/dictionaryType/findAllSubDictItemsByParId.action",
			type : "post",
			autoParam: ["id"],
				dataFilter: function(treeId, parentNode, responseData) {
				    return eval(responseData.ztreeBeanList);
				}
			},
		callback : { //回调函数  
            onRightClick : zTreeOnRightClick   //右键事件  
        }  
		}; 
//显示右键菜单  
 function showRMenu(type, x, y,id) {  
     $("#rMenu div").show();  
     if(id!=null){
    	 $.ajax({
        	    url:context+'/dictionaryType/lookOverItem.action',
    			type:"POST",
    			data:{"itemId":id},
    			dataType:"json",
    			global: false,
    			success:function(data){
    				var item = data.dicItemBean;
    				  $("#m_del").hide(); 
    			    if(item.state == item_start){
    			    	$("#m_stop").show();
    			    	 $("#m_start").hide();
    			    }else if(item.state == item_stop){
    			    	$("#m_stop").hide();
    			    	$("#m_start").show();
    			    }
    			}
         });
     }
     if (type=="root") {  
         $("#m_del").hide();  
         $("#m_edit").hide();  
         $("#m_query").hide(); 
         $("#m_up").hide();
         $("#m_down").hide();
         $("#m_start").hide();
         $("#m_stop").hide();
     }else{
    	 $("#m_del").hide();  
         $("#m_edit").show();  
         $("#m_query").show(); 
         $("#m_up").show();
         $("#m_down").show();
     }
     $("#rMenu").css({"top":y+"px", "left":x+"px", "display":"block"});  
 }  
 //隐藏右键菜单  
 function hideRMenu() {  
     $("#rMenu").hide();  
     
 }  
 //鼠标右键事件-创建右键菜单  
 function zTreeOnRightClick(event, treeId, treeNode) {
	 var zTree = $.fn.zTree.getZTreeObj(treeId);
	 if (!treeNode) {  
         zTree.cancelSelectedNode();  
         showRMenu("root", event.clientX+$(window).scrollLeft()+30, event.clientY+ $(window).scrollTop(),null);  
     }else {
    	 zTree.selectNode(treeNode);  
    	 showRMenu("node", event.clientX+$(window).scrollLeft()+30, event.clientY + $(window).scrollTop(),treeNode.id);  
     }  
 }  
 //右键菜单的功能实现
 
 //获得选中的节点Id函数
 	function getSelectedNodesId(){
 		 hideRMenu() ;
 		var zTree = $.fn.zTree.getZTreeObj("ztree-demo");
		var nodes = zTree.getSelectedNodes();
		var treeNode = nodes[0] ;
		var itemId =null;
		 if(nodes.length==0){
				return itemId;
		 }
		 else{
			 var treeNode = nodes[0] ;
			 var itemId = treeNode.id ;
			 return itemId;
		 }
 	}
 	//获得选中节点的下一个节点id
 	function getSelectedNextNodesId(){
		 hideRMenu() ;
		var zTree = $.fn.zTree.getZTreeObj("ztree-demo");
		var nodes = zTree.getSelectedNodes();
		var treeNode =  nodes[0];
		var itemId =null;
		 if(nodes.length==0){
				return itemId;
		 }
		 else{
			 var treeNode = nodes[0].getNextNode();
			 if(treeNode!=null){
				itemId = treeNode.id ;
			 }
			 return itemId;
		 }
	}
 	//获取选中节点的上一个节点id
	function getSelectedpreNodesId(){
		 hideRMenu() ;
		var zTree = $.fn.zTree.getZTreeObj("ztree-demo");
		var nodes = zTree.getSelectedNodes();
		var treeNode =nodes[0];
		var itemId =null;
		 if(nodes.length==0){
				return itemId;
		 }
		 else{
			 var treeNode =nodes[0].getPreNode(); 
			 if(treeNode!=null){
			   itemId = treeNode.id ;
			 }
			 return itemId;
		 }
	}
 //新增字典项
 // XIEHF 20160225 修改。 处理数据字典项新增后页面一直处于数据加载状态的问题，并实现新增后数据字典项树实时刷新。
	 $(document).on('click','#m_add',function(){
		var itemId = getSelectedNodesId() ;
		addItem(itemId) ;
	 })
	 function addItem(itemId){
		 var initData ={
				 searchingTypeId:searchingTypeId,
				 itemId:itemId
		 };
			$.util.topWindow().$.layerAlert.dialog({
				content:context+"/dictionaryType/toAddictionaryType.action",
				title:"新增字典项信息",
				btn:["保存", "取消"],
				width:"500px",
				height:"320px",
				initData:function(){
					return $.util.exist(initData)?initData:{} ;
				},
				callBacks:{
					btn1:function(index, layero){
				 		 var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.common ;
				 		 cm.save();
				    },
				    btn2:function(index, layero){				    	
				 		//这样实现btn1的回调函数默认不关闭层，需要手动调用close函数关闭
				    	layer.close(index);
				    }
				},				
				end:function(index, layero){
					reloadTree(); //刷新数据字典项树。  
				}
			})
	 }
	 
	 
//修改字典项    
	 // XIEHF 20160225 修改。 处理数据字典项修改后页面一直处于数据加载状态的问题，并实现修改后数据字典项树实时刷新。
	 $(document).on('click','#m_edit',function(){
		 var itemId = getSelectedNodesId();
		 modifyItem(itemId);
	 })
	 function modifyItem(itemId){
			var initData = {
					itemId: itemId,
					searchingTypeId:searchingTypeId,
					type:"modify"
			}
			$.util.topWindow().$.layerAlert.dialog({
				content:context+"/dictionaryType/toAddictionaryType.action",
				btn:["保存", "取消"],
				title:"编辑字典项信息",
				width:"500px",
				height:"320px",
				initData:function(){
					return $.util.exist(initData)?initData:{} ;
				},
				callBacks:{
					btn1:function(index, layero){
				 		 var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.common ;
				 		 cm.update();
				    },
				    btn2:function(index, layero){
				    	layer.close(index);
				    }
				},	
				end:function(){
					reloadTree(); //刷新数据字典项树。  
				}
			})
	 }
	 
 $(document).on('click','#m_start',function(){
		 var itemId = getSelectedNodesId();
		 startItem(itemId);
	 })
	function  startItem(itemId){
	 var dataMap = new Object();
		dataMap["dicItemBean.dicTypeId"] = searchingTypeId;
		dataMap["dicItemBean.id"] = itemId;
		dataMap["dicItemBean.state"] = constantStauts_qy;
		 $.ajax({
				url : context+"/dictionaryType/updateDictionaryItemStatus.action",
				type : "post",
				data : dataMap,
				success : function(data) {
					alertHint("启用成功",6);
					reloadTree();
					
				}
			});
	 
	 }
 $(document).on('click','#m_stop',function(){
	 var itemId = getSelectedNodesId();
	 stopItem(itemId);
 })
 function  stopItem(itemId){
	 var dataMap = new Object();
		dataMap["dicItemBean.dicTypeId"] = searchingTypeId;
		dataMap["dicItemBean.id"] = itemId;
		dataMap["dicItemBean.state"] = constantStauts_ty;
	 $.ajax({
			url : context+"/dictionaryType/updateDictionaryItemStatus.action",
			type : "post",
			data : dataMap,
			success : function(data) {
				alertHint("停用成功",6);
				reloadTree();
				
			}
		});
 
 }
//查看字典项
	 $(document).on('click','#m_query',function(){
		 var itemId = getSelectedNodesId();
		 detailItem(itemId);
	 })
	 function detailItem(itemId){
			var initData = {
					itemId:itemId,
					type:"detail"
			}
			$.util.topWindow().$.layerAlert.dialog({
				content:context+"/dictionaryType/toLookictionaryType.action",
				title:"查看字典项信息",
				btn:["确认"],
				width:"500px",
				height:"320px",
				initData:function(){
					return $.util.exist(initData)?initData:{} ;
				},
				yes:function(index, layero){
					layer.close(index);
				}
			})
	 }

	 
 //删除字典项
	 $(document).on('click','#m_del',function(){
		 var itemId = getSelectedNodesId();
		 removeItem(itemId);
	 })
	 function removeItem(itemId){
			$.layerAlert.confirm({
				msg:"确认删除字典项吗？",
				title:"提示",
				yes:function(index, layero){
					$.ajax({
						url: context + '/dictionaryType/removeItem.action',
						type:"POST",
						data:{itemId:itemId},
						dataType:"json",
						success:function(){   
							 alertHint("删除成功",6);// XIEHF 20160225 删除数据字典项后，刷新字典项树。
							reloadTree();
						}
					});
				}
			});
	 }

//字典项上移
	 $(document).on('click','#m_up',function(){
		 var itemId = getSelectedNodesId();
		 var itemPreId = getSelectedpreNodesId();
		 if(itemPreId == null){
			 $.layerAlert.alert({msg:"无法上移!",title:"提示"});
		 }else{
			 moveupDictionaryItem(itemId,itemPreId);

		 }
	 })
	 function moveupDictionaryItem(itemId,itemPreId){
		 $.ajax({
				url : context + "/dictionaryType/moveUpDictionaryItem.action",
				type : "post",
				data : {
					itemId : itemId,
					itemPreId:itemPreId,
					searchingTypeId:searchingTypeId
				},
				success : function(data) {
					reloadTree();
				}
			});
	 }
//字典项下移
	$(document).on('click','#m_down',function(){
		 var itemId = getSelectedNodesId();
		 var itemNexId = getSelectedNextNodesId();
		 if(itemNexId == null){
				$.layerAlert.alert({msg:"无法下移!",title:"提示"});
		 }else{
			 movedownDictionaryItem(itemId,itemNexId);
 
		 }
	})
	function movedownDictionaryItem(itemId,itemNexId){
		 $.ajax({
				url : context + "/dictionaryType/moveDownDictionaryItem.action",
				type : "post",
				data : {
					itemId : itemId,
					itemNexId:itemNexId,
					searchingTypeId:searchingTypeId
				},
				success : function(data) {
					reloadTree(); 
				}
			});
	}
	
	
	function reloadTree() {  
		//加载树时隐藏右键菜单功能
		 hideRMenu(); 
		$(function(){  
		    $.ajax({
		        data:{"searchingTypeId":searchingTypeId},
		        cache:false,  
		        type: 'POST', 
		        global:false,
		        dataType : "json",  
		        url: context+"/dictionaryType/initDictionaryItemTree.action",//请求的action路径  
		        error: function () {
		        	$.layerAlert.alert({msg:"请求失败!",title:"提示"});
		        },  
		        success:function(data){ 
		            treeNodes = data.ztreeBeanList;     
		            zTree = $.fn.zTree.init($("#ztree-demo"), setting, treeNodes);
		        }  
		    });  
		});  
    } 
	var count=1;
//数据字典类型表格
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context+ "/dictionaryType/findDictionaryType.action";
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "50px",
					"title" : "序号",
					"data" : "id",
					"render" : function(
							data, type,
							full, meta) {
						return count++;
					}
				},
				{
					"targets" : 1,
					"width" : "",
					"title" : "类型名称",
					"data" : "name",
					"render" : function(
							data, type,
							full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "",
					"title" : "类型编码",
					"data" : "code",
					"render" : function(
							data, type,
							full, meta) {
						return data==null?"":data;
					}
				},
				{
					"targets" : 3,
					"width" : "",
					"title" : "类型划分",
					"data" : "classifier",
					"render" : function(
							data, type,
							full, meta) {
						return data=="null"?"":data;
					}
				},
				{
					"targets" : 4,
					"width" : "",
					"title" : "描述",
					"data" : "description",
					"render" : function(
							data, type,
							full, meta) {
						return data=="null"?"":data;
					}
				},
				{
					"targets" : 5,
					"width" : "",
					"title" : "操作",
					"data": "control" ,
					"className" : "table-edit",
					"render" : function(
							data, type,
							full, meta) 
							 {
						return "<button class='modifyAttr btn btn-primary btn-xs' >修改</button>" ;
				}
				} ];
		tb.ordering = false;
		tb.lengthMenu = [ 10 ],
		tb.searching = false;
		tb.lengthChange = false;
		tb.autoFooter = false;
		tb.paramsReq = function(d, pagerReq) {
			var classifer = $("#searchKey").val() ;
			var dictionaryTypeName = $("#searchVal").val() ;
			$.util.objToStrutsFormData(
					classifer, "classifer", d);
			$.util.objToStrutsFormData(
					dictionaryTypeName, "dictionaryTypeName", d);
			count=d.start+1;
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.totalNum;
			json.recordsFiltered = json.totalNum;
			json.data = json.dictionaryTypeBeanPager.pageList;
	
		};
		tb.detailFmt = function(d) {
			var div = "<div>";
			div += "额外的信息：" + d.extral;
			div += "</div>";
			return div;
		};
		
		
//加载数据字典项树
		tb.rowCallback = function(row,data, index){
			
			$($(row).children("td")).each(function(i, val){
				$(val).on("click", function(){
					$.each($("#datatable tbody tr"), function(i,item){
						$(item).removeAttr("style");
					 }); 
					$(row).attr("style","background:#013;")
					searchingTypeId = data.id;
					reloadTree();
					//页面加载时生成树并且监听鼠标点击事件，及时隐藏右键菜单
					$("body").bind(//鼠标点击事件不在节点上时隐藏右键菜单  
			                "mousedown",  
			                function(event) {  
			                    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {  
			                        $("#rMenu").hide();
			                    }  
			                });  
				})
			});
		}
		table = $("#datatable").DataTable(tb);
		
		//查询
		$(document).on('click','#search',function() {
				table.draw(true,"#search");
		});
		
		
		//新增字典类型
		$(document).on('click','#add',function() {
			$("#add").attr({"disabled":"disabled"});
			$(".modifyAttr").attr({"disabled":"disabled"});
			$.dataTable.addRow("#datatable","before",tb,
				[
					"",
					'<input type="text" class="tb-name form-control input-sm valiform-keyup form-val" style="width:90px" datatype="*1-20"/><span class="red-star red_star_canHide" style="display: inline;">*</span>',
					'<input type="text" class="tb-code form-control input-sm valiform-keyup form-val" style="width:90px"  datatype="*1-20"/><span class="red-star red_star_canHide" style="display: inline;">*</span>',
					'<input type="text" class="tb-classifier form-control" style="width:90px" datatype="*0-20"/>',
					'<input type="text" class="tb-description form-control" style="width:90px" datatype="*0-500" />',
					'<button class="table-row-save btn btn-primary btn-xs m-ui-btnxs">保存</button>' 
					+'<button class="btn btn-success btn-xs cancelType">取消</button>']);
		});
	
		$(document).on('click','.table-row-save',function() {
			//r行对应的JQ对象
			var r = $(this).parents("tr") ;
			//row行对应的table对象
			var row = table.row(r) ;
			//行数据
			var full = row.data() ;
			
			//获取新输入框的数据（通过class获取） 
			var dictionaryTypeBean = new Object();
			//行Id
			if($.util.exist(full)){
				dictionaryTypeBean.testId = full.testId;
			}
			dictionaryTypeBean.name = r.find(".tb-name").val() ;
			dictionaryTypeBean.code = r.find(".tb-code").val() ;
			dictionaryTypeBean.classifier = r.find(".tb-classifier").val() ;
			dictionaryTypeBean.description= r.find(".tb-description").val() ;
			var demo = $.validform.getValidFormObjById("validformId") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return;
			}
			if($.util.isBlank(dictionaryTypeBean.name)){
				$.util.topWindow().$.layerAlert.alert({
					msg:"字典类型名称不能为空！",
				})
			}
			else if($.util.isBlank(dictionaryTypeBean.code)){
				$.util.topWindow().$.layerAlert.alert({
					msg:"字典类型编码不能为空！",
				})
			}
			else{
				var dataMap = new Object();
				$.util.objToStrutsFormData(dictionaryTypeBean, "dictionaryTypeBean", dataMap);
				$.ajax({
					url : context+ '/dictionaryType/saveDictionaryType.action',
					data : dataMap,
					type : "POST",
					btn : '#delete',
					dataType : "json",
					success : function(data) {
						$("#add").removeAttr("disabled");
						$(".modifyAttr").removeAttr("disabled");
						if(data.flag=="true"){
						   table.draw(false);
						   alertHint("保存成功",6);
						}else{
							$.util.topWindow().$.layerAlert.alert({
								msg:data.msg,title:"提示"
							})
						}
						
					}
				});
			}
			//重绘表格                  
	//		table.draw(false);   用户体检不好，数据校验不通过时不重绘表格。  20160226 XIEHF
		});
		
		//新增时取消
		$(document).on("click", ".cancelType", function(e) {
			$("#add").removeAttr("disabled");
			$(".modifyAttr").removeAttr("disabled");
			$.validform.closeAllTips("validformId");
			cancelType();
		});
		function cancelType(){
			
			table.draw(false);
		}
		
		
		
		//数据字典类型修改
		$(document).on("click", ".modifyAttr", function(e) {
			$(".modifyAttr").attr({"disabled":"disabled"});
			$("#add").attr({"disabled":"disabled"});
			var tr = $(this).parents("tr");
			modifyAttr(tr);
		});
		function modifyAttr(tr){
			var row = table.row(tr);
			modifyIndex = row.index();
			var full = row.data();
			var arrParams=new Array();
			arrParams.push("");
			arrParams.push('<input type="text" class="tb-name form-control input-sm valiform-keyup form-val" value='+full.name+'  style="width:90px" datatype="*1-20"/><span class="red-star red_star_canHide" style="display: inline;">*</span>');
			arrParams.push('<input type="text" class="tb-code form-control input-sm valiform-keyup form-val" value='+full.code+'  style="width:90px" datatype="*1-20"><span class="red-star red_star_canHide" style="display: inline;">*</span><span style="color:red;font-size:12px"><br>请谨慎修改</span></input >');
			if(full.classifier!="null"&&full.classifier!=""&&full.classifier!=null)
			   {
				arrParams.push('<input type="text" class="tb-classifier form-control" value='+full.classifier+'  style="width:90px"datatype="*0-20" />');
			   }else{
				   arrParams.push('<input type="text" class="tb-classifier form-control"   datatype="*0-20" />')
			   }
			if(full.description!="null"&&full.description!=""&&full.description!=null)
			   {
				arrParams.push('<input type="text" class="tb-description form-control" value='+full.description+'  style="width:90px" datatype="*0-500" />');
			   }else{
				   arrParams.push('<input type="text" class="tb-description form-control"   datatype="*0-500"/>')
			   }
			arrParams.push('<button class=" table-row-update btn btn-primary btn-xs m-ui-btnxs" >保存</button>' 
					+'<button class="btn btn-success btn-xs cancelType">取消</button>');
			$.dataTable.editRow(tr,arrParams
			);
		}
		
		$(document).on('click','.table-row-update',function() {
			//r行对应的JQ对象
			var r = $(this).parents("tr") ;
			//row行对应的table对象
			var row = table.row(r) ;
			//行数据
			var full = row.data() ;
			
			//获取新输入框的数据（通过class获取） 
			var dictionaryTypeBean = new Object();
			//行Id
			if($.util.exist(full)){
				dictionaryTypeBean.testId = full.testId;
			}
			var demo = $.validform.getValidFormObjById("validformId") ;
			var flag = $.validform.check(demo) ;
			if(!flag){
				return;
			}
			dictionaryTypeBean.id=full.id;
			dictionaryTypeBean.name = r.find(".tb-name").val() ;
			dictionaryTypeBean.code = r.find(".tb-code").val() ;
			dictionaryTypeBean.classifier = r.find(".tb-classifier").val() ;
			dictionaryTypeBean.description= r.find(".tb-description").val() ;
			var dataMap = new Object();
			$.util.objToStrutsFormData(dictionaryTypeBean, "dictionaryTypeBean", dataMap);
			$.ajax({
				url : context+ '/dictionaryType/updateDictionaryType.action',
				data : dataMap,
				type : "POST",
				btn : '#delete',
				dataType : "json",
				success : function(data) {
					$(".modifyAttr").removeAttr("disabled");
					$("#add").removeAttr("disabled");
					if(data.flag == "false"){
						$.util.topWindow().$.layerAlert.alert({
							msg:data.msg,title:"提示"
						});
					}else{
						$.each(row,function(i,val) {
							table.row(val).remove();
							table.draw(false);
						});
						 alertHint("修改成功",6);
					}
				}
			});
			
		});
		
//数据字典类型停用
		$(document).on("click", ".disableType", function(e) {
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var full = row.data();
			disableType(full.id);
		});
		function disableType(id){
			$.layerAlert.confirm({
				msg:"确认停用吗？",
				title:"提示",
				yes:function(index, layero){
					$.ajax({
						url: context + '/dictionaryType/disableType.action',
						data:{
							"id":id
						},
						type:"POST",
						dataType:"json",
						success:function(data){
							alertHint("停用成功");
							table.draw(false);
						}
					});
				}
			});
		}
//数据字典类型启用
		$(document).on("click", ".enableType", function(e) {
			var tr = $(this).parents("tr");
			var row = table.row(tr);
			var full = row.data();
			enableType(full.id);
		});
		function enableType(id){
			$.layerAlert.confirm({
				msg:"确认启用吗？",
				titie:"提示",
				yes:function(index, layero){
					$.ajax({
						url: context + '/dictionaryType/enableType.action',
						data:{
							"id":id
						},
						type:"POST",
						dataType:"json",
						success:function(data){
							alertHint("启用成功");
							table.draw(false) ;
						}
					});
				}
			});
		}
		function alertHint(msg,icon){
	 		$.layerAlert.alert({
	 			msg:msg,
	 			title:"提示",		//弹出框标题
	 			width:'300px',
	 			hight:'200px',
	 			shade: [0.5,'black'],  //遮罩
	 			icon:icon,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
	 			shift:1,			//弹出时的动画效果  有0-6种
	 		});
	 	}
		
//重置
	$(document).on('click','#reset',function() {
		$.select2.selectByOrder("#searchKey", "0", true);
		$("#add").removeAttr("disabled");
		$("#searchVal").val("");
		loadsearchKey();
		table.draw(false);
	});
})
	//查找按钮中的数据字典类型划分
	function loadsearchKey(){
				$.ajax({
					type:'POST',
					dataType:"json",
					url:context+"/dictionaryType/initDicTypeClassifer.action",
					success:function(data){
						var dicTypeList = data.dicTypeClassifierList;
						var count=dicTypeList.length;
						for(var i=0;i<count;i++){
							var iscontaintest=false;
							if(dicTypeList[i]!=null){
								iscontaintest= dicTypeList[i].indexOf("null")==-1?false:true;
							}
							if(dicTypeList[i]==null||dicTypeList[i]==""||iscontaintest){
								dicTypeList.splice(i,1);
								count=count-1;
							}
						}
						dicTypeList.unshift("全部");
						$.select2.addByStrList("#searchKey", dicTypeList, false, false)
					}
				});
			}
})(jQuery);
