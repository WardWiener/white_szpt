$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;
$.szpt.util.noData = $.szpt.util.noData || {} ;
(function($){
	
	"use strict";
	
	function destroyTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
	}
	
	function initDictionaryTree(selectInfo, multiple, dicTypeCode){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		if(multiple){
			$.zTreeMenu.init(selectInfo, context + "/queryDictionaryTree.action", {
				async:{
					enable:true
				},
				check:{
			  		type:"onlySelectChildren"
				},
				callBacks:{
					formatNodeData:function(nodeData){
						nodeData.iconSkin = "dw" ;
					}
				}
			}) ;
		}else{
			$.zTreeMenu.init(selectInfo, context + "/queryDictionaryTree.action", {
				async:{
					enable:true,
					requestData:{"dicTypeCode":dicTypeCode}
				},
				callBacks:{
					formatNodeData:function(nodeData){
						nodeData.iconSkin = "dw" ;
			  		}
				}
			},{
				check: {
					enable: false
				},
				callback: {
					beforeClick:function(treeId, treeNode, clickFlag){
						var tobj = $.zTreeMenu.getTree(treeId);
						if(tobj.tree.getSelectedNodes().length == 1 && tobj.tree.getSelectedNodes()[0] == treeNode){
							$(tobj.inputDom).val("");
							tobj.tree.cancelSelectedNode(treeNode);
							return false;
						}
			  		},
					onClick:function(event, treeId, treeNode){
						var tobj = $.zTreeMenu.getTree(treeId);
						$(tobj.inputDom).val(treeNode.name);
			  		}
				}
			});
		}
		
	}
	
	function initDictionaryCheckTree(selectInfo, dicTypeCode){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		
		$.zTreeMenu.init(selectInfo, context + "/queryDictionaryTree.action", {
			async:{
				enable:true,
				requestData:{"dicTypeCode":dicTypeCode}
			},
			callBacks:{
				formatNodeData:function(nodeData){
					nodeData.iconSkin = "dw" ;
		  		}
			}
		},{
			check: {
				enable: true
			},
			callback: {
				beforeClick:function(treeId, treeNode, clickFlag){
					
		  		},
				onClick:function(event, treeId, treeNode){

		  		},
		  		onCheck:function(event, treeId, treeNode){
		  			var tobj = $.zTreeMenu.getTree(treeId);
		  			var names = "";
		  			if(tobj.tree.getCheckedNodes(true).length > 0){
		  				$.each(tobj.tree.getCheckedNodes(true),function(i,val){
		  					names += val.name;
		  					if(i < tobj.tree.getCheckedNodes(true).length-1){
		  						names += ",";
		  					}
		  				});
		  			}
					$(tobj.inputDom).val(names);
		  		}
			}
		});
	}
	
	function initJqlxTree(selectInfo,multiple){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		if(multiple){
			$.zTreeMenu.init(selectInfo, context + "/queryJqlx.action", {
				async:{
					enable:true
				},
				check:{
			  		type:"onlySelectChildren"
				},
				callBacks:{
					formatNodeData:function(nodeData){
						nodeData.iconSkin = "dw" ;
					}
				}
			}) ;
		}else{
			$.zTreeMenu.init(selectInfo, context + "/queryJqlx.action", {
				async:{
					enable:true,
				},
				callBacks:{
					formatNodeData:function(nodeData){
						nodeData.iconSkin = "dw" ;
			  		}
				}
			},{
				check: {
					enable: false
				},
				callback: {
					beforeClick:function(treeId, treeNode, clickFlag){
						var tobj = $.zTreeMenu.getTree(treeId);
						if(tobj.tree.getSelectedNodes().length == 1 && tobj.tree.getSelectedNodes()[0] == treeNode){
							$(tobj.inputDom).val("");
							tobj.tree.cancelSelectedNode(treeNode);
							return false;
						}
			  		},
					onClick:function(event, treeId, treeNode){
						var tobj = $.zTreeMenu.getTree(treeId);
						$(tobj.inputDom).val(treeNode.name);
			  		}
				}
			});
		}
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.noData, { 
		initDictionaryTree:function(selectInfo, multiple, dicTypeCode){
			//生成字典项树：selectInfo页面输入框id，multiple是否多选，dicTypeCode字典类型编码
			initDictionaryTree(selectInfo, multiple, dicTypeCode);
		},
		initDictionaryCheckTree:function(selectInfo, dicTypeCode){
			//生成字典项树：selectInfo页面输入框id，dicTypeCode字典类型编码
			initDictionaryCheckTree(selectInfo, dicTypeCode);
		},
		initJqlxTree:function(selectInfo, multiple){
			//生成警情类型项树：selectInfo页面输入框id，multiple是否多选
			initJqlxTree(selectInfo, multiple);
		},
		destroyTree:function(selectInfo){
			//摧毁树：selectInfo页面输入框id
			destroyTree(selectInfo);
		},
		getSelectByTree:function(selectInfo){
			//单选树取值：selectInfo页面输入框id
			var tobj = $.zTreeMenu.getTree(selectInfo);
			if(!$.util.exist(tobj) || !$.util.exist(tobj.tree)){
				return null;
			}
			if(tobj.tree.getSelectedNodes().length == 1){
				return tobj.tree.getSelectedNodes()[0].diyMap["code"];	
			}else{
				return null;
			}
		},
		getCheckedByTree:function(selectInfo){
			//多选树取值：selectInfo页面输入框id
			var list = [] ;
			$.each($.zTreeMenu.getCheckedValue(selectInfo).data, function(i, val){
				list.push(val.diyMap.code) ;
			});
			return list ;
		}
	});	
})(jQuery);