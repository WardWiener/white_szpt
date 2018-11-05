(function($) {
	"use strict";

	var number;		//当前弹出层
	var roleId;
	$(document).ready(function() {
//		$("#status").attr("disabled","true");
		
		if(!+"\v1"){
			var win = $.util.rootWindow() ;		//找父级页面
			var	id = win.$.common.getParent() ;//取父级页面数据   
			roleId=id;
			$.ajax({url : context + '/role/queryRoleById.action',
				type : "POST",
				async: true,
				dataType : "json",
				data : {
					"id" :roleId,
				},
				success : function(data) {
					$("#roleName").text(data.roleBean.roleName);
					$("#statusReadonly").text(data.roleBean.status);
				    $("#codeReadonly").text(data.roleBean.roleCode);
					intiUnitTree(data.authorityZTreeBean);
				}
			});
		}else{
			jQuery.extend($.common, { 
				setTestObj2:function(index,id){
					number=index;	//从父页面获取当前弹出层   用于关闭
					roleId=id;
					$.ajax({url : context + '/role/queryRoleById.action',
						type : "POST",
						async: true,
						dataType : "json",
						data : {
							"id" :roleId,
						},
						success : function(data) {
							$("#roleName").text(data.roleBean.roleName);
							$("#statusReadonly").text(data.roleBean.status);
							 $("#codeReadonly").text(data.roleBean.roleCode);
							intiUnitTree(data.authorityZTreeBean);
						}
					});
				},
				
			});	
		}
	});
	function intiUnitTree(authorityList) {
		var settingIn = {
				async: {
					enable: true,
					url:context+"/role/queryRoleById.action",
					autoParam:["id=id"],		
					dataFilter: function(treeId, parentNode, childNodes) {
						return childNodes.authorityZTreeBean;	//子节点集合
					}
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					}
				},
			};
									//第一个参数  树的id  第二个参数  树初始设置   第三个参数  初始值
 		var treeIn = $.fn.zTree.init($("#ztree-inUnitTree"), settingIn,authorityList);

}
	
})(jQuery);