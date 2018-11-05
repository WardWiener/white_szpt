<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<style type="text/css">
  .td-left1{
  
  }

</style>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
</script>
</head>
<body class="m-ui-layer-body" style="color:#333">
<div class="m-ui-layer-box" >
		<div class=" m-ui-layer-searchbox" style="padding-bottom: 0;">
		<div class="row row-mar mar-left mar-right">
           <div class="col-xs-12">
               <table class="table table-sg" cellspacing="0" width="100%" style="margin-bottom:0">
                <tbody>
              <tr>
                <td class="td-left"  width="20%">角色名称</td><td class="word-break" width="20%" id="roleName" style="color:#333"></td>
                <td class="td-left" width="20%">状    态</td><td width="20%"  id="statusReadonly" style="color:#333"></td>
              </tr>
              <tr>
                <td class="td-left">角色编码</td><td colspan="3" id="codeReadonly" style="color:#333"></td>
              </tr>
               <tr>
                   <td rowspan="5"  class="td-left">权限信息</td>
                   <td colspan="3" ><ul id="ztree-inUnitTree" class="ztree" style="height:150px"></ul></td>
              </tr>
            </tbody>
          </table>
        </div>
     </div>
	</div>
	</div>
</body>
<script type="text/javascript"
			src="<%=context%>/scripts/xtgl/role/seeRole.js"></script>

</html>