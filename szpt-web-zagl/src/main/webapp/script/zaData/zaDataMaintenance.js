(function($){
	
	$(document).ready(function(){
		init();
		$("#stopUseZazl").hide();
		$("#startUseZazl").hide();
		addAndUpdateZazlDivHide();
	});
	
	//增加
	$(document).on("click" , "#addZazl", function(e){
		$("#newAddLi").show();
		$("#zazllbName").val("");
		$("#zazllbName").focus();
		addZazlDivShow();
	});
	
	document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
    
         if(e && e.keyCode==13){ // enter 键
        	 $.each( $(".comfigBtn"), function(e,m){
        		 if(!$(m).is(":hidden")){
        			m.click();
        		 } 
        	 })
        }
    };
	
	//增加确认
	$(document).on("click" , "#addCommit", function(e){
		addZazllb();
	})
	
	//增加函数
	function addZazllb(){
		var demo = $.validform.getValidFormObjById("contentDiv") ;
		//是否有没通过的验证
		var flag = $.validform.check(demo) ;
		//是给予提示  
		if(!flag){
			return;
		}
		var data = allZazllx();
		for(var i in data){
			if($("#zazllbName").val() == data[i].name){
				$.util.topWindow().$.layerAlert.alert({msg:"你输入的资料类型重复!",title:"提示"});
				return;
			}
		}
		addZazl();
		
	}
	
	//增加取消
	$(document).on("click" , "#addCancel", function(e){
		location.reload(false);
	})
	
	
	//修改
	$(document).on("click" , "#updateZazl", function(e){
		if(getAllIcheckedId().length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"请选择一种资料类型!",title:"提示"});
			return;
		}
		if(getAllIcheckedId().length >= 2){
			$.util.topWindow().$.layerAlert.alert({msg:"一次只能修改一种案件类型,请选择一个!",title:"提示"});
			return;
		}
		$.each( $(".icheckbox"), function(){
			if(this.checked){
				var id = $(this).attr("id");
				$("#span"+id).hide();
				$("#input"+id).show();
				
				$("#zazllbName").focus();
			}
		} )
		updateZazlDivShow();
	});
	
	
	//修改确认
	$(document).on("click" , "#updateCommit", function(e){
		updateZazl();
	})
	
	//修改取消
	$(document).on("click" , "#updateCancel", function(e){
		location.reload(false);
	})
	
	//删除
	$(document).on("click" , "#deleteZazl", function(e){
		
		if(getAllIcheckedId().length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"请选择一种资料类型!",title:"提示"});
			return;
		}
		$.util.topWindow().$.layerAlert.confirm({
			msg:"确认删除吗？",
			title:"提示",	  //弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			yes:function(index, layero){
				removeZazl();
			}
		});
	});
	
	//启用
	$(document).on("click" , "#startUseZazl", function(e){
		if(getAllIcheckedId().length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"至少选择一种资料类型!",title:"提示"});
			return;
		}	
		$.util.topWindow().$.layerAlert.confirm({
			msg:"确认启用吗？",
			title:"提示",	  //弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:3,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			yes:function(index, layero){
				startUseZazl();
			}
		});
	});
	
	//停用
	$(document).on("click" , "#stopUseZazl", function(e){
		if(getAllIcheckedId().length <= 0){
			$.util.topWindow().$.layerAlert.alert({msg:"至少选择一种资料类型!",title:"提示"});
			return;
		}
		$.util.topWindow().$.layerAlert.confirm({
			msg:"确认停用吗？",
			title:"提示",	  //弹出框标题
			width:'300px',
			hight:'200px',
			shade: [0.5,'black'],  //遮罩
			icon:3,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			yes:function(index, layero){
				stopUseZazl();
			}
		});
	});
	
	$(document).on("ifClicked", ".icheckbox", function(){
		var arr = $.icheck.getChecked("Checked"); 
		if(!$(this).is(':checked')){
			arr.push(this);
		}else{
			var array = [];
			for(var i in arr){
			 	if(arr[i] != this){
			 		array.push(arr[i]);
			 	}
			}
			arr = array;
		}
		if(arr.length >= 1 ){
			var flag = true;
			for(var i in arr){
				if($(arr[i]).attr("varState") != $.common.DICT.DICT_YES){
					flag = false;
				};
			}
			if(flag){
				$("#stopUseZazl").show();
			}else{
				$("#stopUseZazl").hide();
			}
		}else{
			$("#stopUseZazl").hide();
		}
		
		if(arr.length >= 1 ){
			var flag = true;
			for(var i in arr){
				if($(arr[i]).attr("varState") == $.common.DICT.DICT_YES){
					flag = false;
				};
			}
			if(flag){
				$("#startUseZazl").show();
			}else{
				$("#startUseZazl").hide();
			}
		}else{
			$("#startUseZazl").hide();
		}
	})
	
	//增加类别
	function addZazl(){
		var data = {
				"name":$("#zazllbName").val()
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zllxgl/addZaData.action',
			type:"post",
			data:queryStr,
			contentType: "application/json; charset=utf-8",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:"新增成功!",title:"提示",icon:1,end:function(){
					addOrUpdateAfter();
					init();
				}});
			}
		})
	}
	
	//更新
	function updateZazl(){
		var demo = $.validform.getValidFormObjById("contentDiv") ;
		//是否有没通过的验证
		var flag = $.validform.check(demo) ;
		//是给予提示  
		if(!flag){
			return;
		}
		var id = "";
		var name = "";
		$.each( $(".icheckbox"), function(){
			if(this.checked){
				id = $(this).attr("valId");
				name = $("#input"+$(this).attr("id")).val();
			}
		} )
		var allZllx = allZazllx();
		for(var i in allZllx){
			if(name == allZllx[i].name && id != allZllx[i].id){
				$.util.topWindow().$.layerAlert.alert({msg:"与已有类型重复!",title:"提示"});
				return;
			}
		}
		var data = {
				"id":id,
				"name":name
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zllxgl/updateZaData.action',
			type:"post",
			data:queryStr,
			contentType: "application/json; charset=utf-8",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:"修改成功!",title:"提示",icon:1,end:function(){
					addAndUpdateZazlDivHide();
					init();
				}});
			}
		})
	}
	
	
	//删除全部选中项
	function removeZazl(){
		var data = {
				"ids":getAllIcheckedId()
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zllxgl/removeZaData.action',
			type:"post",
			data:queryStr,
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:successDate.resultMap.result,title:"提示",end:function(){
					init();
				}});
			}
		})
		
	}
	
	//启用所有选中项
	function startUseZazl(){
		var data = {
				"ids":getAllIcheckedId()
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zllxgl/startUseZaData.action',
			type:"post",
			data:queryStr,
			contentType: "application/json; charset=utf-8",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:"启用成功!",title:"提示",icon:1,end:function(){
					init();
				}});
			}
		});
	}
	
	
	//停用所用选中项
	function stopUseZazl(){
		var data = {
				"ids":getAllIcheckedId()
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/zllxgl/stopUseZaData.action',
			type:"post",
			data:queryStr,
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				$.util.topWindow().$.layerAlert.alert({msg:"停用成功!",title:"提示",icon:1,end:function(){
					init();
				}});
			}
		});
	}
	
	//页面初始化
	function init(){
		$.ajax({
			url:context +'/zllxgl/findAllZaData.action',
			type:"post",
			dataType:"json",
			success:function(successDate){
				xsqkType(successDate.resultMap.result);
			}
		})
	}
	
	//初始化表格
	function xsqkType(allXsqkType){
		//清空
		$("#zazlType").empty();
		if(null != allXsqkType && allXsqkType.length > 0){
			for(var i in allXsqkType){
				var spanClass = "";
				if(allXsqkType[i].state == $.common.DICT.DICT_YES){
					spanClass = "icon-dot icon-dot-qy";
				}else{
					spanClass = "icon-dot icon-dot-ty";
				}
				if(allXsqkType[i].code == "01"){
					$("#zazlType").append('<li><span class="zl-icon zl-icon-basic"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'</li>');
				}else if(allXsqkType[i].code == "04"){
					$("#zazlType").append('<li><span class="zl-icon zl-icon-image"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'</li>');
				}else if(allXsqkType[i].code == "02"){
					$("#zazlType").append('<li><span class="zl-icon zl-icon-audio"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'</li>');
				}else if(allXsqkType[i].name.code == "03"){
					$("#zazlType").append('<li><span class="zl-icon zl-icon-video"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'</li>');
				}else if(allXsqkType[i].name.code == "05"){
					$("#zazlType").append('<li><span class="zl-icon zl-icon-other"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'</li>');
				}else{
					$("#zazlType").append('<li><span class="zl-icon zl-icon-other"></span>' 
							+'<span class="'+spanClass+'"></span>'
							+'<h2 class="name"><span id="span'+i+'" class="zazlSpan">'+allXsqkType[i].name +'</span><input  id="input'+i+'" class="zazlInput  form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" value="'+allXsqkType[i].name +'"></h2>'
							+'<p class="checkbox">'
							+'<input id="'+i+'" valName="'+allXsqkType[i].name +'" valId="'+allXsqkType[i].id +'" varState="'+allXsqkType[i].state+'" name="Checked" type="checkbox" class="icheckbox">'
							+'</p></li>');
				}
				
			}
			spanShowAndInputHide();
		}
	}
	
	//得到所有选中项
	function getAllIcheckedId(){
		var data = [];
		$.each( $(".icheckbox"), function(){
			if(this.checked){
				data.push($(this).attr("valId"));
			}
		} )
		return data;
	}
	
	//新增或修改取消或完成后
	function addOrUpdateAfter(){
		addAndUpdateZazlDivHide();
		$("#newAddLi").hide();
	}
	
	//添加修改确认取消按钮的隐藏
	function addAndUpdateZazlDivHide(){
		$("#crudDiv").show();
		$("#updateZazlDiv").hide();
		$("#addZazlDiv").hide();
		$("#newAddLi").hide();
	}
	
	//确认取消按钮的显示
	function addZazlDivShow(){
		$("#addZazlDiv").show();
		$("#crudDiv").hide();
		
	}
	
	//修改确认取消按钮的显示
	function updateZazlDivShow(){
		$("#updateZazlDiv").show();
		$("#crudDiv").hide();
	}
	
	//初始化时专案页面展示标签
	function spanShowAndInputHide(){
		$(".zazlSpan").show();
		$(".zazlInput").hide();
	}
	
	//取出所有的资料名称
	function allZazllx(){
		var data = [];
		$.each( $(".icheckbox"), function(){
			var map = {
					"id":$(this).attr("valId"),
					"name":$(this).attr("valName")
			}
			data.push(map);
		} )
		return data;
	}
	
})(jQuery);
