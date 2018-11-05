

(function($) {
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	$(document).ready(function() {

        var comet = $.comet.init("szpt-common-comet") ;

        comet.configure({
            configure:{
                url:COMET_OORT_URL,
                logLevel:"error"
            }
        }) ;

		var num = 0;
        comet.handshake({
			additional:{
				credentials: {
					clientid: currentUnitCodeForPush
				}
			},
			handshakeSuccess:function(handshakeReply){
				
				var subscribe = comet.subscribe({
					url:"/comet/broadcast",
					additional:{
			     	},
			     	msgCallBack:function(message){
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
						$.util.log(message) ;
						
						var data = message.data ;
						var info = $.util.parseJsonByEval(data.msgJSON) ;
						
						$.util.log(info) ;
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
					},
					repyCallBack:function(subscribeReply){
					  	$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_subscribeReply^^^^^^^^^^^^^^^^^^^^^^^^") ;
					  	$.util.log(subscribeReply) ;
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_subscribeReply^^^^^^^^^^^^^^^^^^^^^^^^") ;
					}
				}); 
			},
			handshakeError:function(handshakeReply){
				
			}
		}) ;

        var lsn = comet.addListener({
            url:'/service/receive/hint',
            msgCallBack:function(message){
                $.util.log("^^^^^^^^^^^^^^^^^^^^receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
                $.util.log(message) ;

                var data = message.data ;
                var info = $.util.parseJsonByEval(data.msgJSON) ;
                var msg = {
                    "context" : info.context,
                    "time" : info.time,
                    "unit" : info.unit,
                    "id" : info.id,
                    "existingTime" : info.existingTime,
                    "windowId" : "warningHint" + num
                };
                initAlertList("warningHint" + num);
                $.msghint.addMsg("warningHint" + num, msg);
                $.msghint.show("warningHint" + num);
                setTimeout(closeAlert("warningHint" + num), info.existingTime);
                num++;
                $.util.log(info) ;

                $.util.log("^^^^^^^^^^^^^^^^^^^^receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
            }
        }) ;
        
        /**
         * 初始化推给账户的comet
         */
        var cometAcc = $.comet.init("szpt-common-comet-account") ;

        cometAcc.configure({
            configure:{
                url:COMET_OORT_URL,
                logLevel:"error"
            }
        }) ;

		var numAcc = 0;
		cometAcc.handshake({
			additional:{
				credentials: {
					clientid: currentAccountCodeForPush
				}
			},
			handshakeSuccess:function(handshakeReply){
				
				var subscribe = cometAcc.subscribe({
					url:"/comet/broadcast",
					additional:{
			     	},
			     	msgCallBack:function(message){
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
						$.util.log(message) ;
						
						var data = message.data ;
						var info = $.util.parseJsonByEval(data.msgJSON) ;
						
						$.util.log(info) ;
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
					},
					repyCallBack:function(subscribeReply){
					  	$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_subscribeReply^^^^^^^^^^^^^^^^^^^^^^^^") ;
					  	$.util.log(subscribeReply) ;
						$.util.log("^^^^^^^^^^^^^^^^^^^^broadcast_subscribeReply^^^^^^^^^^^^^^^^^^^^^^^^") ;
					}
				}); 
			},
			handshakeError:function(handshakeReply){
				
			}
		}) ;

        var lsnAcc = cometAcc.addListener({
            url:'/service/receive/hint',
            msgCallBack:function(message){
                $.util.log("^^^^^^^^^^^^^^^^^^^^receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
                $.util.log(message) ;

                var data = message.data ;
                var info = $.util.parseJsonByEval(data.msgJSON) ;
                var msg = {
                    "bkdId" : info.bkdId,
                    "bkdh" : info.bkdh,
                    "id" : info.id,
                    "windowId" : "warningHintBk" + numAcc
                };
                initAlertListBkResult("warningHintBk" + numAcc);
                $.msghint.addMsg("warningHintBk" + numAcc, msg);
                $.msghint.show("warningHintBk" + numAcc);
                
                numAcc ++;
                $.util.log(info) ;

                $.util.log("^^^^^^^^^^^^^^^^^^^^receiveCallBack^^^^^^^^^^^^^^^^^^^^^^^^") ;
            }
        }) ;
	});

	function closeAlert(mhId, msgId){
		function temp(){
			$.msghint.close(mhId);
		}
		return temp;
	}
	
	/**
	 * 初始化布控结果推送弹窗
	 * 
	 * @param lstId
	 * @returns
	 */
	function initAlertListBkResult(lstId){
		var warningSettings = {
				id:lstId,
				dialog:{
					title:"人员布控结果",
					shift:2
				},
				callBacks:{
					formatMsgCallBack:function(msgs, id, obj, settings){
						var div = $("<div />") ;
						var val = msgs.pop();
						if(val == null){
							return false;
						}
						var obj = $("#msgTemplateDivBkResult").clone(true);
						obj.removeAttr("id");
						obj.css("display","block");
						var url = context + "/personExecuteControl/showPersonExecuteControlListPage.action?clickOrder=5&&clickListOrder=0&&bkdId=" + val.msg.bkdId + "&&bkdh=" + val.msg.bkdh;
						$(obj.find(".bkResultTitle")).text("布控单号：" + val.msg.bkdh + " 有新的反馈结果，请注意查收！");
						$(obj.find(".bkResultTitle")).attr("href", url).attr("windowId",val.msg.windowId).attr("msgId",val.id);
						obj.appendTo(div) ;
						return div ;
					}
				}
			}
			
			$.msghint.init(warningSettings) ;
	}
	
	function initAlertList(lstId){
		var warningSettings = {
				id:lstId,
				dialog:{
					title:"指令",
					shift:2
				},
				callBacks:{
					formatMsgCallBack:function(msgs, id, obj, settings){
						var div = $("<div />") ;
						var val = msgs.pop();
						if(val == null){
							return false;
						}
						var obj = $("#msgTemplateDiv").clone(true);
						obj.removeAttr("id");
						obj.css("display","block");
						$(obj.find(".context")).text(val.msg.context);
						$(obj.find(".unit")).text(val.msg.unit);
						$(obj.find(".time")).text(val.msg.time);
						$(obj.find(".receiveBtn")).attr("irsId", val.msg.id).attr("windowId",val.msg.windowId).attr("msgId",val.id);
						obj.appendTo(div) ;
						return div ;
					}
				}
			}
			
			$.msghint.init(warningSettings) ;
	}
	
	$(document).on("click" , ".receiveBtn", function(e){
		var windowId = $(this).attr("windowId");
		var msgId = $(this).attr("msgId");
		window.top.$.layerAlert.dialog({
			content : context +  '/instruct/showInstructionSign.action',
			pageLoading : true,
			title:"指令签收",
			width : "500px",
			height : "300px",
			btn:["签收","关闭"],
			callBacks:{
				btn1:function(index, layero){
					var cm = window.top.frames["layui-layer-iframe"+index].$.common ;
					cm.sign();
					$.msghint.removeMsg(windowId, msgId);
					$.msghint.close(windowId);
				},
				btn2:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); 
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				irsId:$(this).attr("irsId")
			},
			end:function(){
				
			}
		});
	});
	
	/**
	 * 关闭布控结果推送弹窗
	 */
	$(document).on("click",".bkResultTitle",function(){
		var windowId = $(this).attr("windowId");
		var msgId = $(this).attr("msgId");
		
		$.msghint.removeMsg(windowId, msgId);
		$.msghint.close(windowId);
	});
})(jQuery);


