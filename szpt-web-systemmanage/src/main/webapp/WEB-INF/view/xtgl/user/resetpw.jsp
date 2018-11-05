<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

</head>
<style>
h2, .h2 {
  font-size: 30px;
}
h1, .h1, h2, .h2, h3, .h3 {
  margin-top: 20px;
  margin-bottom: 10px;
}

.str, .atv {
  color: #D14;
}

.typ, .atn, .dec, .var {
  color: teal;
}

</style>
<body class="m-ui-layer-body" style="overflow-x:hidden">

<div class="m-ui-searchbox" style="background:white;color:#000000">
<div class="m-ui-searchbox-con" style="margin-left:120px;overflow-x:hidden;overflow-y:hidden;">
<div class="row">
<div class="col-xs-6" >
密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" class="valiform-keyup form-val"  datatype="str6-40" 
            		errormsg="密码需大于6位"  id="mima1" value="" style="color:#000000;" /><br><br>
确认密码：<input type="password" id="mima2"  class="valiform-keyup form-val" datatype="str6-40" 
            		errormsg="密码需大于6位" value="" style="color:#000000;" />
</div>
</div>
</div>
</div>

</body>

<script type="text/javascript" src="<%=context%>/scripts/xtgl/user/resetpw.js"></script>

</html>
