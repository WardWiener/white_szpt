$.zaList = $.zaList || {};
$.zaList.zaFindPerson = $.zaList.zaFindPerson || {} ;
(function($){
	var id = null;
	var name = null;
	
	var table = null;
	var zNodesT1 = [];
	var unitId = null;
	
	var sex = null;
	$(document).ready(function(){
		init();
	});
	
	//查询
	$(document).on("click", "#searchBtn", function(){
		zazllxCode = null;
		creatTable();
	})
	
	//初始化
	function init(){
		$.ajax({
			url:context +'/zazlwh/findAllUnitAndDepartment.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				var sexList=successDate.resultMap.sexList;
				var b="";
				for(var i=0;i<sexList.length;i++){
					if(sexList[i].name=='未知'){
						
					}else{
						b+='<option value="'+sexList[i].code+'" >'+sexList[i].name+'</option>';
					}
				}
				$('#sex').append(b);
				initDwLst(zNodesT1,successDate.resultMap.result);
				var setting = {
						callback: {
							onClick: zTreeOnClick
						},
						
						data: {
							simpleData: {
								enable: true
							}
						}
					};
				$.fn.zTree.init($("#treeDemo1"), setting, zNodesT1);
			}
		})
		creatTable();
	}

	function zTreeOnClick(event, treeId, treeNode) {
		unitId = treeNode.id;
		creatTable();
	}
	
	//初始化ztree
	function initDwLst(zNodesT1, data){
		if(null != data.dwLst && data.dwLst.length > 0){
			var cell = data.dwLst;
			for(var i in cell){
				if(null != cell[i].dwLst && null != cell[i].length >0){
					var zNodesT = [];
					var dataCell = {
							id:cell[i].id, pId:data.id, name:cell[i].name, open:false, children:zNodesT	
					}
					zNodesT1.push(dataCell);
					initDwLst(zNodesT,cell[i]);
				}else{
					var dataCell = {
							id:cell[i].id, pId:data.id, name:cell[i].name	
					}
					zNodesT1.push(dataCell);
				}
			}
		}
	}
	

	//初始化表格
	function creatTable(){
		if(table != null) {
			table.destroy();
		}
		var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context +'/zazlwh/findPersonLstByCondition.action',
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "7%",
					"title" : "",
					"data" : "id",
					"render" : function(data, type, full, meta) {
						return '<input type="checkbox" valId="'+data+'" valName="'+full.name+'" class="personIcheck icheckbox">';
					}
				},
				{
					"targets" : 1,
					"width" : "7%",
					"title" : '序号',
					"data" : "",
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "10%",
					"title" : "性别",
					"data" : "sex",
					"render" : function(data, type, full, meta) {
						
						return data;
					}
				},
				{
					"targets" : 4,
					"width" : "18%",
					"title" : "所属机构",
					"data" : "unit",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = true;
		tb.info = true;
		tb.lengthMenu = [ 5 ];
		
		tb.paramsReq = function(d, pagerReq){
			var data = {
				"unitId" : unitId,//单位id
				"name" : $("#personName").val(),
				"sex" : $('#sex option:selected') .val(),//性别
				"state" : $($.icheck.getChecked("DW")[0]).attr("valState"),
				"start":d.start,
				"length":d.length
			}
			var queryStr = $.util.toJSONString(data);
			$.util.objToStrutsFormData(queryStr,"queryStr",d);
		};
		tb.paramsResp = function(json) {
			json.recordsTotal = json.resultMap.result.totalNum;
			json.recordsFiltered = json.resultMap.result.totalNum;
			json.data = json.resultMap.result.pageList;
		};
		table = $("#tavleId").DataTable(tb);
	}
	/**
	 * 获取id&&name
	 */
	function getData(){
		var arrcheck=$('input[type="checkbox"]:checked');
		if(arrcheck.length==0){
			$.layerAlert.alert({title:"提示",msg:"至少勾选一项"});
		}else{
			var arrId=new Array();
			var arrName=new Array(); 
			$('input[type="checkbox"]:checked').each(function(){
				arrId.push($(this).attr("valId"));
				arrName.push($(this).attr("valName"));
			})
			var data={
					personId:arrId,  //人员id
					personName:arrName    //人员名称
			}
			table.draw(true);
			return data;			        
		}
	
	}
    
	
	//暴露接口让其他js可以调用
	jQuery.extend($.zaList.zaFindPerson , { 
		getData:getData
		
	});	
	
	
})(jQuery);
