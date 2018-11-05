
$(document).ready(function() {	
	
	

	

});

/*******************************ajax 设置开始******************************/
$(document).ajaxStart(function() {
	if($.util.globalSettings.ajaxLoading){
		if($.util.globalSettings.layerAjaxLoadingFrameParam == "top"){
			window.top.$.layerAlert.loading({
				zIndex : 22222222
			}) ;
		}else{
			window.top.frames[$.util.globalSettings.layerAjaxLoadingFrameParam].$.layerAlert.loading() ;
		}
		
	}
}).ajaxSuccess(function() {
	if($.util.globalSettings.ajaxLoading){
		if($.util.globalSettings.layerAjaxLoadingFrameParam == "top"){
			window.top.$.layerAlert.closeAll('loading'); 
		}else{
			window.top.frames[$.util.globalSettings.layerAjaxLoadingFrameParam].$.layerAlert.closeAll('loading'); 
		}
		
	}
}).ajaxError(function() {
	if($.util.globalSettings.ajaxLoading){
		if($.util.globalSettings.layerAjaxLoadingFrameParam == "top"){
			window.top.$.layerAlert.closeAll('loading'); 
		}else{
			window.top.frames[$.util.globalSettings.layerAjaxLoadingFrameParam].$.layerAlert.closeAll('loading'); 
		}
	}
}).ajaxStop(function() {
	if($.util.globalSettings.ajaxLoading){
		if($.util.globalSettings.layerAjaxLoadingFrameParam == "top"){
			window.top.$.layerAlert.closeAll('loading'); 
		}else{
			window.top.frames[$.util.globalSettings.layerAjaxLoadingFrameParam].$.layerAlert.closeAll('loading'); 
		}
	}
});

//定义util类
$.util = $.util || {};
(function($){
	
	
})(jQuery);	
/*******************************ajax 设置结束******************************/

/********************************隐匿函数test开始************************************/
;(function( window, document, undefined ) {
	"use strict";
	(function($){
		var settings = {
			testSetting1:1,
			testSetting2:2
		} ;
		$.MyTest = function(){
			var obj = new Object() ;
			obj.testName = "myName" ;
			obj.mySetings = settings ;
			obj.testObj = function(){
				$.mylog("result:testValue1==" + this.mySetings.testSetting1 + " and testValue2=="+this.mySetings.testSetting2) ;
			} ;
			obj.updateSettings = function(testSetting1, testSetting2){
				this.mySetings.testSetting1 = testSetting1 ;
				this.mySetings.testSetting2 = testSetting2 ;
			} ;
			return obj ;
		};
	})(jQuery);	
}(window, document));

/********************************隐匿函数test结束************************************/

/*****************************通用扩展方法开始*********************************************/
(function($){
	"use strict";
		var rhorizontal = /left|center|right/ ;
	var rvertical = /top|center|bottom/ ;
	var roffset = /[\+\-]\d+(\.[\d]+)?%?/ ;
	var rposition = /^\w+/ ;
	var rpercent = /%$/ ;
	var supportsOffsetFractions ;
	
	var testElement, testElementParent, testElementStyle, offsetLeft, i,
	body = document.getElementsByTagName( "body" )[ 0 ],
	div = document.createElement( "div" );

	//Create a "fake body" for testing based on method used in jQuery.support
	testElement = document.createElement( body ? "div" : "body" );
	testElementStyle = {
		visibility: "hidden",
		width: 0,
		height: 0,
		border: 0,
		margin: 0,
		background: "none"
	};
	if ( body ) {
		$.extend( testElementStyle, {
			position: "absolute",
			left: "-1000px",
			top: "-1000px"
		});
	}
	for ( i in testElementStyle ) {
		testElement.style[ i ] = testElementStyle[ i ];
	}
	testElement.appendChild( div );
	testElementParent = body || document.documentElement;
	testElementParent.insertBefore( testElement, testElementParent.firstChild );
	
	div.style.cssText = "position: absolute; left: 10.7432222px;";
	
	offsetLeft = $( div ).offset().left;
	supportsOffsetFractions = offsetLeft > 10 && offsetLeft < 11;
	
	testElement.innerHTML = "";
	testElementParent.removeChild( testElement );
	//console.log
	jQuery.extend($.util, { 
		exist:function(val){
			/**
			 * 判断对象是否存在
			 */
			if(val!=undefined && val!=null){
				return true ;
			}else{
				return false ;
			}
		},
		isBlank:function(val){
			/**
			 * 判断某字符串是否为空或未定义或长度为0或由空白符(whitespace) 构成
			 */
			if(val!=undefined && val!=null&&!($.trim(val)==="")){
				return false ;
			}else{
				return true ;
			}
		},
		elExist:function(val, obj){
			/**
			 * 判断对象的元素是否存在
			 */
			if(obj==undefined || obj==null){
				return false ;
			}else{
				if(obj[val]==undefined || obj[val]==null){
					return false; 
				}else{
					return true ;
				}
			}
		},	
					jqUiPosition:function(targetSelector, wgSelector, my, at, mouseEvent){
	
				var options = {"my":my, "at":at, within:undefined, collision:"flipfit flip"} ;
				var atOffset, targetWidth, targetHeight, targetOffset, basePosition, dimensions,
					target = $( targetSelector ),
					within = $.position.getWithinInfo( options.within ),
					scrollInfo = $.position.getScrollInfo( within ),
					collision = ( options.collision || "flip" ).split( " " ),
					offsets = {};
				if($.util.exist(mouseEvent)){
					target = $( mouseEvent ) ;
				}

				dimensions = $.util.getDimensions( target );
				if ( target[0].preventDefault ) {
					// force left top to allow flipping
					options.at = "left top";
				}
				targetWidth = dimensions.width;
				targetHeight = dimensions.height;
				targetOffset = dimensions.offset;
				// clone to reuse original targetOffset later
				basePosition = $.extend( {}, targetOffset );

				// force my and at to have valid horizontal and vertical positions
				// if a value is missing or invalid, it will be converted to center
				$.each( [ "my", "at" ], function() {
					var pos = ( options[ this ] || "" ).split( " " ),
						horizontalOffset,
						verticalOffset;

					if ( pos.length === 1) {
						pos = rhorizontal.test( pos[ 0 ] ) ?
							pos.concat( [ "center" ] ) :
							rvertical.test( pos[ 0 ] ) ?
								[ "center" ].concat( pos ) :
								[ "center", "center" ];
					}
					pos[ 0 ] = rhorizontal.test( pos[ 0 ] ) ? pos[ 0 ] : "center";
					pos[ 1 ] = rvertical.test( pos[ 1 ] ) ? pos[ 1 ] : "center";
					
					// calculate offsets
					horizontalOffset = roffset.exec( pos[ 0 ] );
					verticalOffset = roffset.exec( pos[ 1 ] );
					offsets[ this ] = [
						horizontalOffset ? horizontalOffset[ 0 ] : 0,
						verticalOffset ? verticalOffset[ 0 ] : 0
					];

					// reduce to just the positions without the offsets
					options[ this ] = [
						rposition.exec( pos[ 0 ] )[ 0 ],
						rposition.exec( pos[ 1 ] )[ 0 ]
					];				

				});				
				
				// normalize collision option
				if ( collision.length === 1 ) {
					collision[ 1 ] = collision[ 0 ];
				}

				if ( options.at[ 0 ] === "right" ) {
					basePosition.left += targetWidth;
				} else if ( options.at[ 0 ] === "center" ) {
					basePosition.left += targetWidth / 2;
				}
				
				if ( options.at[ 1 ] === "bottom" ) {
					basePosition.top += targetHeight;
				} else if ( options.at[ 1 ] === "center" ) {
					basePosition.top += targetHeight / 2;
				}


				
				atOffset = $.util.getOffsets( offsets.at, targetWidth, targetHeight );
				basePosition.left += atOffset[ 0 ];
				basePosition.top += atOffset[ 1 ];
				

				var collisionPosition,
				elem = $( wgSelector ),
				elemWidth = elem.outerWidth(),
				elemHeight = elem.outerHeight(),
				marginLeft = $.util.parseCss( elem[0], "marginLeft" ),
				marginTop = $.util.parseCss( elem[0], "marginTop" ),
				collisionWidth = elemWidth + marginLeft + $.util.parseCss( elem[0], "marginRight" ) + scrollInfo.width,
				collisionHeight = elemHeight + marginTop + $.util.parseCss( elem[0], "marginBottom" ) + scrollInfo.height,
				position = $.extend( {}, basePosition ),
				myOffset = $.util.getOffsets( offsets.my, elem.outerWidth(), elem.outerHeight() );

				if ( options.my[ 0 ] === "right" ) {
					position.left -= elemWidth;
				} else if ( options.my[ 0 ] === "center" ) {
					position.left -= elemWidth / 2;
				}
	
				if ( options.my[ 1 ] === "bottom" ) {
					position.top -= elemHeight;
				} else if ( options.my[ 1 ] === "center" ) {
					position.top -= elemHeight / 2;
				}
	
				position.left += myOffset[ 0 ];
				position.top += myOffset[ 1 ];
	
				// if the browser doesn't support fractions, then round for consistent results
				if ( !supportsOffsetFractions ) {
					position.left = Math.round( position.left );
					position.top = Math.round( position.top );
				}
	
				collisionPosition = {
					marginLeft: marginLeft,
					marginTop: marginTop
				};
	
				$.each( [ "left", "top" ], function( i, dir ) {
					if ( $.ui.position[ collision[ i ] ] ) {
						$.ui.position[ collision[ i ] ][ dir ]( position, {
							targetWidth: targetWidth,
							targetHeight: targetHeight,
							elemWidth: elemWidth,
							elemHeight: elemHeight,
							collisionPosition: collisionPosition,
							collisionWidth: collisionWidth,
							collisionHeight: collisionHeight,
							offset: [ atOffset[ 0 ] + myOffset[ 0 ], atOffset [ 1 ] + myOffset[ 1 ] ],
							my: options.my,
							at: options.at,
							within: within,
							elem: elem
						});
					}
				});
	
				elem.offset(position);
				
				return position ;
	
		},

					getDimensions:function( elem ) {
			var raw = elem[0];
			if ( raw.nodeType === 9 ) {
				return {
					width: elem.width(),
					height: elem.height(),
					offset: { top: 0, left: 0 }
				};
			}
			if ( $.isWindow( raw ) ) {
				return {
					width: elem.width(),
					height: elem.height(),
					offset: { top: elem.scrollTop(), left: elem.scrollLeft() }
				};
			}
			if ( raw.preventDefault ) {
				return {
					width: 0,
					height: 0,
					offset: { top: raw.pageY, left: raw.pageX }
				};
			}
			return {
				width: elem.outerWidth(),
				height: elem.outerHeight(),
				offset: elem.offset()
			};
		},
		getOffsets:function( offsets, width, height ) {
			return [
				parseFloat( offsets[ 0 ] ) * ( rpercent.test( offsets[ 0 ] ) ? width / 100 : 1 ),
				parseFloat( offsets[ 1 ] ) * ( rpercent.test( offsets[ 1 ] ) ? height / 100 : 1 )
			];
		},
		parseCss:function( element, property ) {
			return parseInt( $.css( element, property ), 10 ) || 0;
		},
		cloneObj:function(obj){
			return $.extend( true,  {}, obj, {}) ;
		},
		deleteObjEls:function(els, obj){
			/**
			 * 批量删除Object的属性
			 * els如果是String单独删除一个属性，如果是数组就批量删除
			 */
			if(typeof els=="String" && els.constructor==String){
				delete obj[els] ;
			}else if(typeof els=="object" && els.constructor==Array){
				$.each(els, function(i, val){
					delete obj[val] ;
				}) ;
			}
		},			
		globalSettings : {
				isReSize:true,
				firstLoad:true,
				ajaxLoading:true,
				layerAjaxLoadingFrameParam:"top"
		} ,

		mylog:function(obj){
			/**
			 * 替代console.log方法，防止浏览没有console导致报错
			 * obj：打印的对象
			 */
			if ( window.console && console.log ) {
				console.log( obj );
			}
		},
		openMyWindow:function(url, title, width, height, top, left){
			/**
			 * 打开新的window对象
			 */
			var w = $(window.top).width() ;
			var h = $(window.top).height() ;	
			
			if(width==undefined){
				width = w ;
			}
			if(height==undefined){
				height = h ;
			}	
			if(top==undefined){
				top = 0 ;
			}	
			if(left==undefined){
				left = 0 ;
			}	

			var settings = "height="+height+",width=" + width + ",top="+top+",left="+left+",toolbar=no,menubar=no,resizable=yes,location=no,status=no" ; 
			window.top.open(url, title, settings) ;			
		},
		getHiddenForm:function(action, inputs){
			/**
			 * 得到隐藏的form表单，每次getform表单都会被清空
			 * action：form表单提交的url
			 * inputs:form表单内的input标签，数组类型，结构为：
			 * [
			 * 	{
			 *     name:"test1",
			 *     value:"testValue1"
			 *  },
			 *  {
			 *     name:"test2",
			 *     value:"testValue2"
			 *  }
			 * ]
			 */
			var form = $("#myHiddenForm") ;
			$(form).html("") ;		
			$(form).attr("action", action) ;
			$(form).attr("method", "post") ;
			var inputsStr = "" ;
			$(inputs).each(function(i, val){
				inputsStr += '<input type="hidden" name="'+val.name+'" value=\''+ val.value +'\' \/> ' ;
			});	
			$(form).append(inputsStr) ;
			return form ;				
		},
		subMyForm:function(form, delay, isLoading){
			/**
			 * 提交表单
			 * form：提交的form表单的dom对象
			 * delay：延迟提交时间
			 * isLoading：是否显示loading
			 */
			if(isLoading){
				layerLoading() ;
			}
			if(delay){
				setTimeout(function(){
				   $(form).submit() ;
				}, delay) ;
			}else{
				$(form).submit() ;
			}			
		},
	 //js object 转换为json字符串
	   toJSONString: function(object) { 
		     var type = typeof object; 
		     if (object != null && 'object' == type) { 
		       if (object != null && Array == object.constructor) type = 'array'; 
		       else if (object != null && RegExp == object.constructor) type = 'regexp'; 
		       else type = 'object'; 
		     } 
		     switch (type) { 
		     case 'undefined': 
		     case 'unknown': 
		       return; 
		       break; 
		     case 'function': 
		    	 return; 
			     break; 
		     case 'boolean': 
		     case 'regexp': 
		       return object.toString(); 
		       break; 
		     case 'number': 
		       return isFinite(object) ? object.toString() : 'null'; 
		       break; 
		     case 'string': 
		       return '"' + object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() { 
		         var a = arguments[0]; 
		         return (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': "" 
		       }) + '"'; 
		       break; 
		     case 'object': 
		       if (object === null) return 'null'; 
		       var results = []; 
		       //在此删除不需要的属性
		       
		       for (var property in object) { 
		         var value = jQuery.toJSONString(object[property]); 
		         if (value !== undefined) results.push(jQuery.toJSONString(property) + ':' + value); 
		       } 
		       return '{' + results.join(',') + '}'; 
		       break; 
		     case 'array': 
		       var results = []; 
		       for (var i = 0; i < object.length; i++) { 
		         var value = jQuery.toJSONString(object[i]); 
		         if (value !== undefined) results.push(value); 
		       } 
		       return '[' + results.join(',') + ']'; 
		       break; 
		     } 
		   } 
	});	
	
	$.fn.extend();		
})(jQuery);	
/*****************************通用扩展方法结束*********************************************/

/********************************调整界面宽高开始**********************************/

(function($){
	var settings = {
		funcs : {
			windowReSizeFunc:function(val){
				if($.util.globalSettings.isReSize){
					$.util.mylog(val) ;
				}
			}
		}
	}
	jQuery.extend($.util, { 
		windowReSize:function(){
			$.each(settings.funcs, function(key, val){
				val(123) ;
//				var evalStr = val.funcName + "(" + val.funcVals + ")" ;
//				eval(evalStr);
			});
		},
		setRizeFunc:function(funcName, func){
			settings.funcs[funcName] = func ;
		},
		deleteRizeFunc:function(funcName){
			delete settings.funcs[funcName] ;
			$.util.mylog(settings.funcs) ;
		}
	});	
	
})(jQuery);	

/********************************调整界面宽高结束**********************************/

/**控件通用设置**/
$.uiSettings = $.uiSettings || {};
(function($){
	
	/********************************datatable Settings开始**********************************/
	var oTableSettings = {
			language: {
		        processing:     "<img src='" + ContextPath + "/common/library/dataTables/images/xubox_loading2.gif' />",
		        search:         "搜索：",	        
		        lengthMenu:    "每页显示&nbsp;_MENU_&nbsp;条",
		        info: "当前第 _START_ - _END_ 条　共计 _TOTAL_&nbsp;条",
		        infoEmpty:      "没有记录",
		        infoFiltered:   "(从 _MAX_ 条记录中过滤)",
		        infoPostFix:    "",
		        loadingRecords: "正在加载...",
		        zeroRecords:    "没有找到符合条件的数据",
		        emptyTable:     "无数据",
		        paginate: {
		            first:      "首页",
		            previous:   "前一页",
		            next:       "后一页",
		            last:       "尾页"
		        },
		        aria: {
		            sortAscending:  ": 升序排列",
		            sortDescending: ": 降序排列"
		        }
		    },
		    dom: '<"dtTop"lp<"clear">>rt<"dtBottom"lp<"clear">>',
		    autoWidth: false,
		    lengthChange: true,
		    ordering: false, 
		    paging: true,
		    processing: false,
		    searching: false,
		    stateSave: false,
		    pagingType: "full_numbers",
		    serverSide: true,
		    lengthMenu: [ 10, 25, 50, 75, 100 ],
//		    ajaxSource: ContextPath + "/fxyp/findDetail.action",
		    columnDefs:[
//		        { 
//			          "targets": 0,
//			          "width": "",
//			          "title": "报警时间",
//			          "data": "BJSJ" ,
//			          "render": function ( data, type, full, meta ) {		        	
//			        	      return data;
//			          }
//		        }
		    ],
		    serverParams: function ( aoData ) { 	    	
//		    	aoData.push( { "name": "page_jqlxComment", "value": page_jqlxComment } );
//		    	aoData.push( { "name": "startTime", "value": startTime } );
//		    	aoData.push( { "name": "endTime", "value": endTime } );
//		    	aoData.push( { "name": "page_unitName", "value": page_unitName } );
//		    	aoData.push( { "name": "isAllLxChildren", "value": isAllLxChildren } );
//		    	aoData.push( { "name": "isAllDwChildren", "value": isAllDwChildren } );
		    },
	  	   	"fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
//		         oSettings.jqXHR = $.ajax({
//		           "dataType": 'json',
//		           "type": "POST",
//		           "url": sSource,
//		           "data": aoData,
//		           "success": function(data){
//		        	   var odata = {"aaData":data.jqDetailPager.pageList, "iTotalRecords":data.jqDetailPager.totalNum, "iTotalDisplayRecords":data.dataTotalDisplayRecords} ;
//		        	   mylog(data.jqDetailPager.totalNum);
//		        	   mylog(data.dataTotalDisplayRecords);
//		        	   fnCallback(odata) ;
//		        	},
//		           "error":function(data){
//		           }
//		         } );
	  	    },
		    createdRow: function( row, data, dataIndex ) {
		    	
		    },
		    drawCallback: function( settings ) {	
		    	
		    },
		    footerCallback: function( thead, data, start, end, display ) {
		    },
		    formatNumber: function ( toFormat ) {
		    },
		    infoCallback: function( settings, start, end, max, total, pre ) {
		    },
		    initComplete: function(settings, json) {
		    },
		    preDrawCallback: function( settings ) {
		    },
		    rowCallback: function( row, data, index ) {
		    },
		    stateLoadCallback: function (settings) {
		    },
		    stateLoaded: function (settings, data) {
		    },
		    stateLoadParams: function (settings, data) {
		    },
		    stateSaveCallback: function (settings, data) {
		    },
		    stateSaveParams: function (settings, data) {
		    }
			
	} ;
	/********************************datatable Settings结束**********************************/
	
	/********************************highchart设置开始**********************************/
	var hightChartBarSettings = {
	    	colors: ['#9d72e7','#529aff','#ff426a'], //线条颜色
	        chart: {
	            type: 'column',
	            backgroundColor: 'rgba(0,0,0,0)'
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            text: ''
	        },
			credits : {
				enabled : false
			},  	
	        xAxis: {
	            lineColor :  '#485d7c',	//基线颜色
			    labels : {
			    	rotation: -50,
					style: {
						color: '#fff',//颜色
						fontSize:'14px'  //字体
				}
					},
				categories : []
	        },
	        yAxis: {
	            min: 0,
	            lineColor :  '#485d7c',	//基线颜色
			    labels : {
						style: {
						color: '#8194af',//颜色
						fontSize:'14px'  //字体
				}
					},
	            title: {
	                text: ''
	            }
	        },
	        legend: {
//	            layout: 'vertical',
//	            align: 'right',
//	            verticalAlign: 'middle',
	            borderWidth: 1,
	            backgroundColor: '#FCFFC5',
	            borderColor: '#C98657'
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}：</td>' +
	                '<td style="padding:0"><b>{point.y}</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            },
	            series:{
	            	events:{
	            		
	            	}
	            }
	        },
			series : [
			          
			]
	    } ;	

	
	var hightChartPieSettings = {
			colors: ['#fe0000','#ff0078','#fe00d2', '#9c01ff', '#b56dff', '#0c00ff', '#00a2ff', '#5ec4ff', '#7cfbbb', '#09c701', '#77f036', '#cbff7a', '#e7b200', '#fffa74'], //线条颜色
	        chart: {
	        	backgroundColor: 'rgba(0,0,0,0)',
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
			credits : {
				enabled : false
			},          
	        title: {
	            text: ''
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#fff',
	                    connectorColor: '#fff',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                }
	            },
	            series:{
	            	events:{
	            		
	            	}
	            }
	        },
	        series: []
	    };	
	
	/********************************highchart设置结束**********************************/
	jQuery.extend($.uiSettings, { 
		getOTableSettings : function(){
			return oTableSettings ;
		},
		getHightChartBarSettings : function(){
			return hightChartBarSettings ;
		},	
		getHightChartPieSettings : function(){
			return hightChartPieSettings ;
		}
	});	
	

	
})(jQuery);	

