$.cascadedDicItem = $.cascadedDicItem || {};
(function($){
	
	"use strict";
	
	var objs = {} ;
	
	var dicItemSettings = {
		id : null,// 实例id
		key : "code",
		value : "name",
		isDefault : true,// 是否设置默认值
		isVerify : true, //是否验证
		dicTypeCode : null, //根级code
		selects : [] // 多个select配置
	}
	
	/**
	 * 刷新下一级字典项
	 * @param thisLevelSelector 当前层级selectId
	 */
	var refreshNextByLevel = function(thisLevelSelector){
		var thisObj = this;
		var settings = this.settings ;
		var dictionaryItemCode = $.select2.val(thisLevelSelector);
		
		thisObj.emptyNextAll(thisLevelSelector);
		//获取下级select id
		var index = 0;
		$.each(settings.selects,function(i,val){
			if(val.selector == thisLevelSelector){
				index = i;
				return false;
			}
		});
		if(index+1 < settings.selects.length){
			var nextLevelSelector = settings.selects[index+1].selector;
			$.ajax({
				url: context + '/findAllSubDictionaryItemsByParentItemCodeAndTypeCode.action',
				type:"POST",	
				data:{dictionaryType:settings.dicTypeCode,dictionaryItem:dictionaryItemCode},
				customizedOpt:{
					//设置是否loading
					ajaxLoading:true,
				},
				dataType:"json",
				success:function(data){
					var dictionaryItemLst = data.dictionaryItemLst;
					$.select2.addByList(nextLevelSelector,dictionaryItemLst,settings.key,settings.value,settings.isDefault,settings.isVerify);
				}
			});
		}
	}
	
	/**
	 * 刷新select中的默认值和选项
	 * @param selects [{selector : null, //select id
	 * 				selectedCode : null, //select已选code值
	 * 				eventBanding : null, //默认绑定事件，function
	 * 				selectEventCallback : null, //选中/改变事件回调
	 * 				unselectEventCallback : null //清除已选项事件回调
	 * 			}]
	 */
	var refreshBySelectedCodes = function(selects){
		var thisObj = this;
		var settings = this.settings ;
		settings.selects = selects
		var data = new Object();
		$.each(selects,function(i,val){
			if(i == 0){
				data[val.selector] = {dicTypeCode : settings.dicTypeCode, selectedCode : settings.dicTypeCode};
			}else{
				data[val.selector] = {dicTypeCode : settings.dicTypeCode, selectedCode : selects[i-1].selectedCode};
			}
		});
		
		var dataStr = $.util.toJSONString(data) ;
		
		$.ajax({
			url: context + '/findCascadedDicItem.action',
			contentType: "application/json; charset=utf-8",
			data:dataStr,
			type:"POST",
			customizedOpt:{
				//设置是否loading
				ajaxLoading:true,
			},
			dataType:"json",
			success:function(data){
				var dicItemCodeMap = data.dicItemCodeMap;
				$.each(selects,function(i,val){
					$.each(dicItemCodeMap,function(j,valJ){
						if(val.selector == j){
							$.select2.addByList(j, valJ, settings.key, settings.value, settings.isDefault, settings.isVerify);
							if(!$.util.isBlank(val.selectedCode)){
								$.select2.val(j, val.selectedCode);
							}
						}
					});
				});
				//绑定事件
				$.each(selects,function(i,val){
					val["eventBanding"] = function(){
						$(document).on("select2:select",val.selector,function(e){
							thisObj.refreshNextByLevel(val.selector);
							
							var select = e.currentTarget.outerHTML;
							if($.util.isFunction(val.selectEventCallback)){
								val.selectEventCallback(select);
							}
						});
						$(document).on("select2:unselect",val.selector,function(e){
							thisObj.emptyNextAll(val.selector);
							
							var select = e.currentTarget.outerHTML;
							if($.util.isFunction(val.unselectEventCallback)){
								val.unselectEventCallback(select);
							}
						});
					}
					val.eventBanding();
				});
			}
		});
		
	}
	
	/**
	 * 清空当前select所有下级的选项
	 * @param thisLevelSelector 当前层级selectId
	 */
	var emptyNextAll = function(thisLevelSelector){
		var settings = this.settings;
		var index = 0;
		$.each(settings.selects,function(i,val){
			if(thisLevelSelector == val.selector){
				index = i
			}
		});
		for(var i=index+1;i<settings.selects.length;i++){
			$.select2.empty(settings.selects[i].selector,true);
		}
	}
	
	/**
	 * 重置
	 */
	var reset = function (){
		var settings = this.settings;
		$.each(settings.selects,function(i,val){
			if(i == 0){
				$.select2.clear(val.selector,true);
			}else{
				$.select2.empty(val.selector,true);
			}
		});
	}
	
	function init(settings){
		if(!$.util.exist(settings)){
			return ;
		}
		var _settings = copySeting(settings,dicItemSettings);
		
		if($.util.isBlank(_settings.dicTypeCode)){
			return ;
		}
		
		var obj = {} ;
		obj.settings = _settings ;
		objs[_settings.id] = obj ;
		
		obj.refreshNextByLevel = refreshNextByLevel ;
		obj.refreshBySelectedCodes = refreshBySelectedCodes ;
		obj.emptyNextAll = emptyNextAll ;
		obj.reset = reset ;
		return obj ;
	}
	
	function copySeting(opt, basicSettings){
		var tpsettings = $.util.cloneObj(basicSettings) ;
		$.util.mergeObject(tpsettings, opt) ;
		return tpsettings ;
	}
	
	jQuery.extend($.cascadedDicItem, { 
//		destroy:destroy,
		init : init,
		getInstance:function(id){
			return objs[id] ;
		}
	});
})(jQuery);