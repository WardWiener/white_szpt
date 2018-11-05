<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body id="validformId" class="m-ui-layer-body validform">
	<div class="m-ui-layer-box" style="width: 75%; border: 0px;">
		<div class="m-ui-layer-searchbox">
		 <table class="table table-sg" cellspacing="0" width="100%" style="margin-bottom:0">
                <tbody>
              <tr>
                <td class="td-left"  >名称</td>
                <td class="word-break" width="60%" ><font color="#000000" id="itemName"></font></td>
              </tr>
             
               <tr>
                  <td class="td-left " >编码</td>
                  <td width="60%" ><font color="#000000" id="itemCode"></font></td>
              </tr>
               <tr>
                <td class="td-left ">状态</td><td ><font color="#000000" id="itemStatus"></font></td>
              </tr>
              <tr>
                <td class="td-left ">备注</td>
                  <td colspan="3" ><font color="#000000" id="itemdescription"></font></td>
              </tr>
            </tbody>
          </table>
		</div>
		<!--内容end-->
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript" src="<%=context%>/scripts/dictionary/lookItem.js"></script>
</body>
</html>





