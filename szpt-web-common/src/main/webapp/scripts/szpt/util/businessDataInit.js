$.szpt = $.szpt || {} ;
$.szpt.util = $.szpt.util || {} ;

$.szpt.util.businessData = $.szpt.util.businessData || {} ;
(function($){
	
	"use strict";
	
	var unit_pcs = [] ;
	var country = [] ;
	var unit_zhuge =[];
	var dic_zt = [] ;
	var dic_yjlx = [] ;
	var dic_rylx = [] ;
	var dic_qklx = [] ;
	var dic_ajlb = [] ;
	var dic_ajjb = [] ;
	var dic_zatd = [] ;
	var dic_zajck = [] ;
	var dic_zars = [] ;
	var dic_zasd = [] ;
	var dic_facs = [] ;
	var dic_dbzt = [] ;
	
	var initSuccessCallBacks = [] ;
	
	$(document).ready(function() {	
		init() ;
	});
	
	function init(){
		var data = {
				"ogUnitType" : $.common.DICT.DICT_DWLX_PCS
			};
			
		var dataStr = $.util.toJSONString(data) ;
			
		$.ajax({
			url:context +'/businessInitInfo.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successData){
				unit_zhuge = successData.resultMap.zhuge;
				unit_pcs = successData.resultMap.ogUnitPcs;
				country = successData.resultMap.ogAllCj;
				dic_zt = successData.resultMap.zt;
				dic_yjlx = successData.resultMap.yjlx;
				dic_rylx = successData.resultMap.rylx;
				dic_qklx = successData.resultMap.qklx;
				dic_ajlb = successData.resultMap.ajlb;
				dic_ajjb = successData.resultMap.ajjb;
				dic_zatd = successData.resultMap.zatd;
				dic_zajck = successData.resultMap.zajck;
				dic_zars = successData.resultMap.zars;
				dic_zasd = successData.resultMap.zasd;
				dic_facs = successData.resultMap.facs;
				dic_dbzt = successData.resultMap.dbzt;
				$.each(initSuccessCallBacks, function(i, val){
					val() ;
				});
			}
		});
	}
	
	function destroyTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
	}
	
	function initAjxzTree(selectInfo,ajlbSelector){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryAjxz.action", {
			async:{
				enable:true,
				requestData:{"topCode":$.szpt.util.businessData.getSeletedBySelector(ajlbSelector)}
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
	
	/**
	 * 查询警情类型树-选择多个
	 * 
	 * @param hideJqlxIdArray 需要隐藏不显示checkbox的单位id数组
	 */
	function initJqlxsTree(selectInfo, hideJqlxIdArray){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryJqlx.action", {
			async:{
				enable:true,
			},
			
			callBacks:{
				formatNodeData:function(nodeData){

				}
			}
		},{
			check: {
				enable: true,
				chkboxType: {
					"Y": "ps",
					"N": "ps"
				}

			},
			callback: {
				beforeClick:function(treeId, treeNode, clickFlag){
					
				},
				onClick:function(event, treeId, treeNode){
					console.log(treeNode);
					
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
	//查询警情类型树-选择单个
	function initJqlxTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
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
	//查询刑事类警情树-选择单个
	function initXsjqTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryXsjq.action", {
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
	
	function initCjTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryCunJu.action", {
			async:{
				enable:true,
			},
			callBacks:{
				formatNodeData:function(nodeData){
					if(nodeData.diyMap.type == $.common.DICT.DICT_DWLX_PCS){
						nodeData.nocheck = true;
					}else{
						nodeData.nocheck = false;
					}
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
	/**
	 * 查询单位树-选择多个
	 * 
	 * @param hideUnitIdArray 需要隐藏不显示checkbox的单位id数组
	 * @param selectUnitCode 需要默认被选择的单位code
	 * 
	 */
	function initDwTree(selectInfo, hideUnitIdArray, selectUnitCode){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryUnit.action", {
			async:{
				enable:true,
			},
			callBacks:{
				formatNodeData:function(nodeData){
					nodeData.nocheck = nodeData.isParent;
					//不想被选的单位不显示checkbox
					if($.util.exist(hideUnitIdArray) && $.inArray(nodeData.diyMap.id, hideUnitIdArray) != -1){
						nodeData.nocheck = true;
					}
					//默认被选中
					if(!$.util.isBlank(selectUnitCode) && nodeData.diyMap.code == selectUnitCode){
						console.log(nodeData);
						nodeData.checked = true;
					}
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
					console.log(treeNode);
					
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
	/**
	 * 查询单位树-选择单个
	 */
	function iniOnetDwTree(selectInfo){
		$.zTreeMenu.destroy(selectInfo);
		$("#" + selectInfo).val("");
		$(document).off("click" , "#" + selectInfo);
		$(document).on("click" , "#" + selectInfo, function(e){
			var tree = $.zTreeMenu.getTree(selectInfo) ;
			tree.showMenu() ;
		});
		$.zTreeMenu.init(selectInfo, context + "/queryUnit.action", {
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
	
	/**
	 * 根据树id显示树下拉框
	 * 
	 * @param selectInfo
	 * @returns
	 */
	function showMenuByzTreeSelectInfo(selectInfo){
		var tree = $.zTreeMenu.getTree(selectInfo) ;
		if($.util.exist(tree)){
			tree.showMenu() ;
		}
	}
	
	/**
	 * 根据树id获取ztree对象
	 * 
	 * @param selectInfo
	 * @returns
	 */
	function getzTreeBySelectInfo(selectInfo){
		var tree = $.zTreeMenu.getTree(selectInfo) ;
		return tree;
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.szpt.util.businessData, { 
		getSeletedBySelector:function(selector){
			var val = $.select2.val(selector) ;
			return val;
		},
		initZhugeSelect:function(selector){
			$.select2.addByList(selector, unit_zhuge, "code", "name", true, true) ;
		},
		initPcsSelect:function(selector){
			$.select2.addByList(selector, unit_pcs, "code", "name", true, true) ;
		},
		initCountrySelect:function(selector){
			$.select2.addByList(selector, country, "code", "name", true, true) ;
		},
		initZtSelect:function(selector){
			$.select2.addByList(selector, dic_zt, "code", "name", true, true) ;
		},
		initAjlbSelect:function(selector){
			$.select2.addByList(selector, dic_ajlb, "code", "name", true, true) ;
		},
		initAjjbSelect:function(selector){
			$.select2.addByList(selector, dic_ajjb, "code", "name", true, true) ;
		},
		initZatdSelect:function(selector){
			$.select2.addByList(selector, dic_zatd, "code", "name", true, true) ;
		},
		initZajckSelect:function(selector){
			$.select2.addByList(selector, dic_zajck, "code", "name", true, true) ;
		},
		initZarsSelect:function(selector){
			$.select2.addByList(selector, dic_zars, "code", "name", true, true) ;
		},
		initZasdSelect:function(selector){
			$.select2.addByList(selector, dic_zasd, "code", "name", true, true) ;
		},
		initFacsSelect:function(selector){
			$.select2.addByList(selector, dic_facs, "code", "name", true, true) ;
		},
		initDbztSelect:function(selector){
			$.select2.addByList(selector, dic_dbzt, "code", "name", true, true) ;
		},
		initAjxzTree:function(selectInfo,ajlbSelector){
			initAjxzTree(selectInfo,ajlbSelector);
		},
		initJqlxTree:function(selectInfo){
			initJqlxTree(selectInfo);
		},
		initXsjqTree:function(selectInfo){
			initXsjqTree(selectInfo);
		},
		initJqlxsTree:function(selectInfo, hideJqlxIdArray){
			initJqlxsTree(selectInfo, hideJqlxIdArray)
		},
		initCjTree:function(selectInfo){
			initCjTree(selectInfo);
		},
		initDwTree:function(selectInfo, hideUnitIdArray, selectUnitCode){
			initDwTree(selectInfo, hideUnitIdArray, selectUnitCode);
		},
		iniOnetDwTree:function(selectInfo){
			iniOnetDwTree(selectInfo);
		},
		destroyTree:function(selectInfo){
			destroyTree(selectInfo);
		},
		getSelectByTree:function(selectInfo){
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
			var tobj = $.zTreeMenu.getTree(selectInfo);
			if(!$.util.exist(tobj) || !$.util.exist(tobj.tree)){
				return [];
			}
			if(tobj.tree.getCheckedNodes().length > 0){
				var codeArr = [];
				$.each(tobj.tree.getCheckedNodes(),function(i,val){
					codeArr.push(val.diyMap["code"]);
				});
				return codeArr;	
			}else{
				return [];
			}
		},
		addToInitSuccessCallBack:function(func){
			initSuccessCallBacks.push(func) ;
		},
		getDic_yjlxLst:function(){
			return dic_yjlx;
		},
		getDic_rylxLst:function(){
			return dic_rylx;
		},
		getRylxCodes:function(){
			var list = [] ;
			$.each(dic_rylx, function(i, val){
				list.push(val.code) ;
			});
			return list;
		},
		getDic_qklxLst:function(){
			return dic_qklx;
		},
		showMenuByzTreeSelectInfo : showMenuByzTreeSelectInfo,
		getzTreeBySelectInfo : getzTreeBySelectInfo
	});	
})(jQuery);