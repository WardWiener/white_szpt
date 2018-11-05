(function($) {
var table;
var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
var number = frameData.index ;//当前弹窗index
var initData = frameData.initData ;
var unitId=initData.unitId;
$(document).ready(function(){
	var tb = $.uiSettings.getOTableSettings();
		tb.ajax.url = context+ "/userManage/queryrenyuan.action";
		tb.columnDefs = [
			{
				"targets" : 0,
				"width" : "",
				"title" : '',
				"data" : "personNo",
				"render" : function(
						data, type,
						full, meta) {
					var a = '<input type="radio" name="personNo" value="'+data+'" class="icheckradio"/>';
					return a;
				}
			},{
				"targets" : 1,
				"width" : "",
				"title" : '姓名',
				"data" : "name",
				"render" : function(data, type,full, meta) {
					return data;
				}
			},{
				"targets" : 2,
				"width" : "",
				"title" : '所属单位',
				"data" : "unitFullName",
				"render" : function(data, type,full, meta) {
					return data;
				}
			}
			];
		 tb.paging = false ; //取消分页显示
		 tb.searching = false ;
		 tb.ordering = false ;
		 tb.lengthMenu = [ 2 ],
		 tb.lengthChange = false;	
	     tb.info=false;
		tb.paramsReq = function(d, pagerReq) {
			var obj=new Object();
			var perName=$("#perName").val();
			if(perName=="请输入"){
				perName="";
			}
			obj.unitId=unitId;
			var person=new Object();
			person.name=perName;
			obj.personBean=person;
			//object转化成dataMap传到d里
			$.util.objToStrutsFormData(obj, "accountBean", d) ;
		};
		tb.paramsResp = function(json) {
		//json.data = json.userList;
			json.data = json.personBeans;
		};
		table = $("#datatable").DataTable(tb);
		$(document).on('click','#searchPerson',function() {table.draw(true,"#searchPerson");});
});
jQuery.extend($.common, { 
	getrenyuan:function(){
		var per=new Object();
		var arr = $.icheck.getChecked("personNo");
		if(arr.length !=1){
    		$.layerAlert.alert({msg:"只能选择一行数据！",title:"提示"});
    		return per;
    	}
		$.each(arr, function(i, val){
    		var tr = $(val).parents("tr") ;
    		var row = table.row(tr) ;
    		var full = row.data() ;
    		per.personId=full.personId;//人员编码
    		per.name=full.name;//人员名称
    		per.unitId=full.unitId;//人员所属单位编码
    		per.unitName=full.unitFullName;//人员所属单位名称
    	});
		return per;
	}
});	
})(jQuery);