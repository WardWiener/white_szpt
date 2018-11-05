
$.zaMethod = $.zaMethod || {};
(function($){
	var flag = false;
	
	//筛选留言
	$(document).on("click", "#methodChooseBtn", function(){
		searchCreatTable();
	})
	
	
	//按部门分组选中事件
	$(document).on("ifChecked", "#methodIfGroupCheck", function(e){
		flag = true;
		searchCreatTable();
	})
	
	//按部门分组取消事件
	$(document).on("ifUnchecked", "#methodIfGroupCheck", function(e){
		flag = false;
		searchCreatTable();
	})
	
	
	//是否分组掉用此方法
	function searchCreatTable(){
		var departments = [];
		var arr = $.icheck.getChecked("methodDepartmentCheck") 
		for(var i=0;i<arr.length;i++){
			departments.push($(arr[i]).attr("varId"));
    	}
		
		if(departments.length < 1 && flag == true){
			$.each($(".methodDepartmentCheck"), function(e,m){
				departments.push($(m).attr("varId"));
			} ); 
		}
		
		var data = {
				"flag" : flag,
				"caseId" : $.zaxq.getCaseId(),
				"departments" : departments,
				"createTimeStart" : $.laydate.getTime("#dateRangeId", "start"),
				"createTimeEnd" : $.laydate.getTime("#dateRangeId", "end")
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/queryLiuYanByCondition.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				creatTable(successDate.resultMap.result);
			}
		})
	}
	
	//返回
	$(document).on("click", "#methodGoBackBtn", function(e){
		$.util.topWindow().$.layerAlert.closeWithLoading($.zaxq.pageIndex); 
	})
	
	//点击全部
		$(document).on("ifChecked", "#methodAllDepartmentCheck", function(e){		
			$.each( $("#methodDepartmentDiv .icheckbox"), function(e,m){
				$(m).iCheck('check');
			} ); 
		});
		$(document).on("ifUnchecked", "#methodAllDepartmentCheck", function(e){		
			$.each( $("#methodDepartmentDiv .icheckbox"), function(e,m){
					$(m).iCheck('uncheck');
			} ); 
		});
		
		$(document).on("ifUnchecked", "#methodDepartmentDiv .icheckbox", function(e){
			var obj = this;
			if($("#methodAllDepartmentCheck")[0].checked){
				$("#methodAllDepartmentCheck").iCheck('uncheck');
				$.each( $("#methodDepartmentDiv .icheckbox"), function(e,m){
					if(m != obj &&  m != $("#methodAllDepartmentCheck")[0]){
						
						$(m).iCheck('check');
					}
				});
			}
		})
	//导出excel
	$(document).on('click','#methodExcelBtn',function(){
		var departments = [];
		var arr = $.icheck.getChecked("methodDepartmentCheck") 
		for(var i=0;i<arr.length;i++){
			departments.push($(arr[i]).attr("varId"));
    	}
		if(departments.length < 1 && flag == true){
			$.each($(".methodDepartmentCheck"), function(e,m){
				departments.push($(m).attr("varId"));
			} ); 
		}
		var d= {
			"flag" : flag,
			"caseId" : $.zaxq.getCaseId(),
			"departments" : departments,
			"createTimeStart" : $.laydate.getTime("#dateRangeId", "start"),
			"createTimeEnd" : $.laydate.getTime("#dateRangeId", "end")		
		}
		var data = new Object();
		var queryStr = $.util.toJSONString(d);
		$.util.objToStrutsFormData(queryStr,"queryStr",data);
		var form = $.util.getHiddenForm(context +'/zalyfk/exportExcel.action',data);
		$.util.subForm(form);
	});	
	
	function init(){
		var data = {
				"caseId" : $.zaxq.getCaseId(),
			}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zalyfk/findDepartmentLst.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				initZaDepartment(successDate.resultMap.result);
			}
		})
		searchCreatTable();
	}
	
	//初始化办案部门
	function initZaDepartment(departmentLst){
		$("#methodDepartmentDiv").empty();
		$("#methodDepartmentDiv").append('<p class="col-xs-3"><input type="checkbox" name="aaa" id="methodAllDepartmentCheck" varId="'+null+'" class="icheckbox">全部</p>')
		if(null != departmentLst){
			for(var i in departmentLst){
				$("#methodDepartmentDiv").append('<p class="col-xs-3"><input varId="'+departmentLst[i].id+'" name="methodDepartmentCheck" type="checkbox" class="methodDepartmentCheck icheckbox">'+departmentLst[i].name+'</p>')
			}
		}
	}
	
	//创建表
	function creatTable(tableDataLst){
		$("#methodLiuYanTableDiv").empty();
		var str = "";
		str +='<table id="" class="table table-bordered table-hover m-ui-table-border" cellspacing="0">'
		+ '<thead>'
            +'<tr>'
               +'<th>序号</th>'
                +'<th width="35%">留言内容</th>'
                +'<th width="24%">留言时间</th>'
                +'<th width="15%">留言人</th>'
                +'<th>所在部门</th>'
            +'</tr>'
        +'</thead>';
        var sum = 0;
        for(var key in tableDataLst){
        	
        	var dataTable = tableDataLst[key];
        	var num = 1;
        	for(var i in dataTable){
        		str += ' <tr>'
                   +'<td>'+ num++ +'</td>'
                    +'<td>'+dataTable[i].context+'</td>'
                    +'<td>'+dataTable[i].creatTime+'</td>'
                    +'<td>'+dataTable[i].personName+'</td>'
                    +'<td>'+dataTable[i].unitName+'</td>'
                +'</tr> '
        	}
        	if(tableDataLst.length > 1){
        		str +=' <tr>'
                    +'<td>小计</td>'
                     +'<td colspan="4">'+dataTable.length+'</td>'
                 +'</tr> ';
        	}
            sum += dataTable.length;
        }
		str  +=' <tr>'
                +'<td>总计</td>'
                 +'<td colspan="4">'+sum+'</td>'
             +'</tr> '
             +'</table>';
		$("#methodLiuYanTableDiv").append(str)
		
	}
	
	
	jQuery.extend($.zaMethod , { 
		init:init
	});	
	
})(jQuery);
