<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>

	
(function($){
	
	"use strict";
	
	var testObj = {a:123} ;
	var testObj1 = {} ;
	
	
	$(document).ready(function() {	
	
	    $(document).on( 'click', "#chuanzhi", function () {
	    	var win = $.util.rootWindow() ;
	   		var	obj = win.$.common.getParentObj() ;
	   		$.util.log(obj) ;
	    });
	    
    });
	
	
	
	jQuery.extend($.common, { 
		getTestObj:function(){
			return testObj ;
		},
		setTestObj1:function(data){
			testObj1 = data ;
			$.util.log(testObj1) ;
		}
	});	
	
})(jQuery);	
	

</script>
<style type="text/css">
 p{
 word-wrap:break-word;
 }
</style>
</head>

<body class="m-ui-layer-body">
<div class="m-ui-layer-box" style="width:600px;">
<div class="m-ui-layer-searchbox">
<!--基本信息-->
<div class="row row-mar">
           <div class="col-xs-12">
               <table class="table table-sg" cellspacing="0" width="100%" style="margin-bottom:0">
                <tbody>
              <tr>
                <td class="td-left"  width="20%">用户名</td><td class="word-break" width="20%" id="username" style="color:#333"></td>
                <td class="td-left" width="20%">密码</td><td width="20%" id="password" style="color:#333"></td>
              </tr>
              <tr>
                <td class="td-left">人员</td> <td id="name" style="color:#333"></td>
                <td class="td-left">状态</td> <td  id="zhuangtai" style="color:#333"></td>
              </tr>
               <tr>
                <td class="td-left">人员所属单位</td>
                <td colspan="3" id="danwei" style="color:#333"></td>
              </tr>
              <tr>
                <td class="td-left">有效期限</td>
                <td colspan="3" id="qixian"" style="color:#333">2015-01-21 17:00&nbsp;&nbsp;至&nbsp;&nbsp;2015-01-21 17:00</td>
              </tr>
               <tr>
                <td class="td-left">备注</td>
                <td colspan="3" id="beizhu" style="color:#333"></td>
              </tr>
               <tr>
                <td rowspan="5" class="td-left">已选角色</td>
                <td colspan="3" > <ul id="ztree-roleTree" class="ztree" style="height:300px"></ul>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
</div>
<!--基本信息end-->
</div>
</div>
</body>

<script type="text/javascript" src="<%=context%>/scripts/xtgl/user/detail.js"></script>

</html>