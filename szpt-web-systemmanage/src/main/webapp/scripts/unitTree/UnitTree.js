(function($){
	var callBackFunction = null;
	var ztreeObjId;
	//单选树形setting
	var setting = {
		treeId : "zTree",
		data : {
			simpleData : {
				enable : true
			}
		},
		async: {
			enable: true,
			url : context + "/unitTree/searchInfoUnit.action",
			type : "post",
			autoParam: ["id"],
			dataFilter: function(treeId, parentNode, responseData) {
			    return eval(responseData.treeNodes);
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				callBackFunction(treeNode.id,treeNode.name);
				$("#"+ztreeObjId).dialog("close");
			}
		}
	}
	
	//多选树形setting
	var settingDou = {
			treeId : "zTree",
			data : {
				simpleData : {
					enable : true
				}
			},
			check: {
				enable: true //开启checkbox
			},
			async: {
				enable: true,
				url : context + "/unitTree/searchInfoUnit.action",
				type : "post",
				autoParam: ["id"],
				dataFilter: function(treeId, parentNode, responseData) {
				    return eval(responseData.treeNodes);
				}
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
	//				callBackFunction(treeNode.id,treeNode.name);
	//				$("#unitTree").dialog("close");
				}
			}
		};
	settingDou.check.chkboxType = { "Y" : "", "N" : "" };
	function showUnitTreeDialog(ztreeid,callBack,flag)
	{	
		callBackFunction = callBack;
		ztreeObjId=ztreeid;
		var ztreeSetting=flag=="1"?setting:settingDou;
		$.fn.zTree.init($("#treeDemo"), ztreeSetting);
		$("#"+ztreeid).dialog({
			resizable : false,
			modal : true,
			width : "520",
			height : "660",
			closeText : ""
		});
	}
	/**
	 * 向父页面暴露的方法
	 */
	jQuery.extend($.common, { 
		showUnitTreeDialog:function(ztreeid,callBack,flag){
			showUnitTreeDialog(ztreeid,callBack,flag);
		}
	});	
})(jQuery);