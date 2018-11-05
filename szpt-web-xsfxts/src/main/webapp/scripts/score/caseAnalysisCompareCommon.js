$.caseAnalysisCompareCommon = $.caseAnalysisCompareCommon || {};
(function($){
	
	"use strict";
	
	var cacTable = null;
	
	/**
	 * 初始化分析比对表
	 * 
	 * @param selector 选择器
	 * @param dataArray 数据集合
	 */
	function initAnalysisCompareTable(selector, dataArray){
		if(!$.util.isArray(dataArray) || dataArray.length < 1){
			return ;
		}
		if($.util.exist(cacTable)){
			cacTable.destroy();
			$(selector).empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "5%",
			"title" : "",
			"data" : "id",
			"render" : function(data, type, full, meta) {
				
				return "主案件以及其已串并案信息";
			}
		}, {
			"targets" : 1,
			"width" : "25%",
			"title" : "案件基本信息",
			"data" : "caseCode",
			"render" : function(data, type, full, meta) {
				var td = '<p><a href="javascript:void(0);" class="caseCode">' + data + '</a></p>'
				td += '<h4 class="row-mar">' + full.caseName + '</h4>';
				td += '<p><span class="color-gray">办案民警：</span>' + full.handlePolice + '</p>';
				td += '<p><span class="color-gray">发案时间：</span>' + $.date.timeToStr(full.caseTimeStart,"yyyy-MM-dd HH:mm") + '</p>';
				td += '<p><span class="color-gray">犯罪嫌疑人：</span>' + full.suspectName + '</p>';
				td += '<p><span class="color-gray">案件当前状态：</span><span class="color-green1">' + full.caseStateName + '</span></p>';
				if(meta.row == 0){
					var a = '';
					$.each(full.knownCaseCodes,function(k,kcc){
						a += '<a href="javascript:void(0);" class="caseCode">' + kcc + '</a>';
						if(k < full.knownCaseCodes.length - 1){
							a += "、" ;
						}
					});
					td += '<p><span class="color-gray">已串并案件：</span>' + a + '</p>';
				}
				return td;
			}
		}, {
			"targets" : 2,
			"width" : "5%",
			"title" : "串并案分值",
			"data" : "minScore",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "10%",
			"title" : "犯罪嫌疑人",
			"data" : "suspectName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 4,
			"width" : "10%",
			"title" : "案发地点",
			"data" : "address",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 5,
			"width" : "5%",
			"title" : "案发社区",
			"data" : "communityName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 6,
			"width" : "15%",
			"title" : "作案特点",
			"data" : "featureNames",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 7,
			"width" : "5%",
			"title" : "选择处所",
			"data" : "occurPlaceName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 8,
			"width" : "5%",
			"title" : "作案时段",
			"data" : "periodName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 9,
			"width" : "5%",
			"title" : "作案人数",
			"data" : "peopleNumName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 10,
			"width" : "5%",
			"title" : "作案进口",
			"data" : "entranceName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		},
		{
			"targets" : 11,
			"width" : "5%",
			"title" : "作案出口",
			"data" : "exitName",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}];
		
		st1.ordering = false;
		st1.paging = false; // 是否分页
        st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ dataArray.length ];
		st1.rowCallback = function(row,data, index) {
			$(row).data("data",data);
		};
		
		cacTable = $(selector).DataTable(st1);
		
		//设置表格样式
		$(selector + " tbody tr").each(function(t,tr){
			$(tr).attr("compareTr","true");
			if(t == 0){
				$(tr).find("td").css("background", "#0074b6");
//				$(selector + " thead").append(tr);
			}else{
				$(tr).find("td").eq(0).remove();
			}
		});
		//在主案件后加个只有一个td的tr做跨行合并
		$(selector + " tbody tr").eq(1).before('<tr><td rowspan="' + dataArray.length + '">可能的串并案信息</td></tr>');
		//设置滚动条样式
//		$(selector + " tbody").css("display","block").css("overflow","auto").css("height","350px");
//		$(selector + " thead").css("display","block");
//		//设置tbody的td宽度
//		$(selector + " thead tr").eq(0).find("th").each(function(t,th){
//			var thWidth = $(th).css("width").substring(0,$(th).css("width").indexOf("p"));
//			thWidth = (parseInt(thWidth) + 1) +"px";
//			$("#cacTable tbody tr").each(function(tb,tbtr){
//				if(t == 0 && tb == 0){
//					$(tbtr).find("td").eq(0).css("width",thWidth);
//				}
//				if(t > 0 && tb > 0){
//					$(tbtr).find("td").eq(t - 1).css("width",thWidth);
//				}
//			});
//		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.caseAnalysisCompareCommon, { 
		initAnalysisCompareTable : initAnalysisCompareTable
	});	
})(jQuery);