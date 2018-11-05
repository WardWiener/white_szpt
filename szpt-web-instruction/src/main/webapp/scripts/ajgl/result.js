$.result = $.result || {};
(function($) {
	"use strict";
    $(document).ready(function() {  
    	/**
    	 * 关闭按钮旋转
    	 */
    	var value = 0
    	$("#btn-close-window").rotate({ 
    	   bind: 
    	     { 
    	        mouseover: function(){
    	            value +=90;
    	            $(this).rotate({ animateTo:value, duration: 500})
    	        },
    	        mouseleave: function(){
    	            value +=-90;
    	            $(this).rotate({ animateTo:value})
    	      }
    	     } 
    	});
    	
    	var data = window.parent.jsonData ;
    	onloadTable();
    	$(document).on("click",".openAtt",function(){
    		var id= $(this).attr("shotId");
    		var code= $(this).attr("shotCode");
    		openWebForm("快照页面","快照页面",$.common.SZPT_HOST_PORT + context +'/enterbaq/snapshotNavi.action?snapshotId='+id+'&&snapshotCode='+code,803,653);
    	})
    	/**
		 * 小程序关闭按钮
		 */
		$(document).on("click", "#btn-close-window", function(){
			exitForm();//关闭方法 
		});
    });
    
    /**
     * 加载研判结果
     * @returns
     */
    function onloadTable(){

		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/enterbaq/findResearchMessage.action";
			tb.columnDefs = [ {
				"targets" : 0,
				"width" : "",
				"title" : "序号",
				"data" : "",
				"render" : function(data, type, full, meta) {
					return meta.row + 1;
				}
			}, {
				"targets" : 1,
				"width" : "",
				"title" : "研判类型",
				"data" : "instructionType",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}, {
				"targets" : 2,
				"width" : "",
				"title" : "推送原因",
				"data" : "instructionContent",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}, {
				"targets" : 3,
				"width" : "",
				"title" : "推送时间",
				"data" : "instructionTime",
				"render" : function(data, type, full, meta) {
					return data;
				}
			}, {
				"targets" : 4,
				"width" : "",
				"title" : "快照",
				"data" : "snapshotId",
				"render" : function(data, type, full, meta) {
					if(data!=null){
						return '<button class="openAtt" shotId="'+data+'" shotCode="'+full.snapshotCode+'">快照附件</button>'
					}
					return data;
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
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				var data = {
						"identy" : identy,//人员id
						"start":d.start,
						"length":d.length
					}
				var queryStr = $.util.toJSONString(data);
				$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.list;
				if(!$.util.exist(json.resultMap.list) || json.resultMap.list.length < 1){
					$("#resConTable").css("text-align", "center");
				}
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			 $("#resConTable").DataTable(tb);
	
    	
//    	var st1 = $.uiSettings.getLocalOTableSettings();
//    	st1.data = dataArr;
//    	st1.columnDefs = [ {
//    					"targets" : 0,
//    					"width" : "",
//    					"title" : "序号",
//    					"data" : "",
//    					"render" : function(data, type, full, meta) {
//    						return meta.row + 1;
//    					}
//    				}, {
//    					"targets" : 1,
//    					"width" : "",
//    					"title" : "研判类型",
//    					"data" : "instructionType",
//    					"render" : function(data, type, full, meta) {
//    						return data;
//    					}
//    				}, {
//    					"targets" : 2,
//    					"width" : "",
//    					"title" : "推送原因",
//    					"data" : "instructionContent",
//    					"render" : function(data, type, full, meta) {
//    						return data;
//    					}
//    				}, {
//    					"targets" : 3,
//    					"width" : "",
//    					"title" : "推送时间",
//    					"data" : "instructionTime",
//    					"render" : function(data, type, full, meta) {
//    						return data;
//    					}
//    				}
//
//    				];
//
//    				st1.ordering = false;
//    				st1.paging = true;
//    				st1.hideHead = false;
//    				st1.dom = null;
//    				st1.searching = false;
//    				st1.lengthChange = false;
//    				st1.lengthMenu = [ 5, 10 ];
//    				$("#resConTable").DataTable(st1);
    }
    
    /*****************************小程序加载部分************************************/
	  //拖动处理
	var oldX = 0;
	var oldY = 0;
	var addPressEvent = 0;
	document.getElementById("divMouseMove").onmousedown = function() {
		oldX = event.screenX;
		oldY = event.screenY;
		if (addPressEvent == 0) {
			addPressEvent = 1;
			if (document.attachEvent) {
				document.detachEvent('onmousemove', moveOnMousePress);
				document.detachEvent('onmouseup', moveOnMouseUP);
				document.attachEvent('onmousemove', moveOnMousePress, false);
				document.attachEvent('onmouseup', moveOnMouseUP, false);
			} else {
				document.removeEventListener('mousemove', moveOnMousePress);
				document.removeEventListener('mouseup', moveOnMouseUP);
				document.addEventListener('mousemove', moveOnMousePress, false);
				document.addEventListener('mouseup', moveOnMouseUP, false);
			}
		}
	}
	var rushCount = 0;
	function moveOnMousePress() {
		rushCount++;//降低频率
		if (rushCount > 7) {
			rushCount = 0;
			moveForm(event.screenX - oldX, event.screenY - oldY);
			oldX = event.screenX;
			oldY = event.screenY;
		}
	}
	function moveOnMouseUP() {
		addPressEvent = 0;
		if (document.attachEvent) {
			document.detachEvent('onmousemove', moveOnMousePress);
			document.detachEvent('onmouseup', moveOnMouseUP);
		} else {
			document.removeEventListener('mousemove', moveOnMousePress);
			document.removeEventListener('mouseup', moveOnMouseUP);
		}
		//松开最后执行一次
		moveForm(event.screenX - oldX, event.screenY - oldY);
		oldX = event.screenX;
		oldY = event.screenY;
	}
	//--拖动处理完

	  function getRoomNum() {
  	    roomNum = getConfig("Basic", "RoomNumber");
  	  return roomNum;
  	} 
    
  //读取配置文件
    function getConfig(node,key) {
        return window.external.getConfig(node,key); 
    }
    //读取配置文件
    function getConfigAll(node) {
        return window.external.getConfigAllString(node); 
    }
  //设置小图标右键菜单大小
    function setMenuWindowSize(x, y) {
    	window.external.setWindowSize(x, y);
    }

     //打开menu
    function openMenuForm(Url, formWidth, formHeight)
    {

        window.external.openUrlByMenuForm(Url, formWidth, formHeight);
    }
    //windows方法
    //IE打开url
    function openUrlExplorer(Url) {
        window.external.openUrlExplorer(Url);
    }
    function moveForm(addX,addY) {
        if(addX==0&&addY==0)return;
        window.external.moveForm(addX,addY);
    }
  //结束程序
    function exitProgram() {
        window.external.exitProgram();
    }

    function hideForm() {
        window.external.hideForm();
    }
    
  //关闭窗口
    function exitForm() {
    	window.external.exitForm();
    }
	
	/**
	 * 开启窗口
	 */
	function openWebForm(formId,formName,Url, formWidth, formHeight)
	{
	    window.external.openUrlByWinForm(formId,formName,Url, formWidth, formHeight);
	}
	
	/*************************小程序end******************************************/
    
})(jQuery);