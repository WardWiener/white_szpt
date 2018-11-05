<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
	<div id="pa_lookPage" class="m-ui-layer-box" style="width: 600px;">
	
		<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
			<!--基本信息-->
			<div class="row row-mar">
				  <table class="table table-sg" cellspacing="0" width="100%" style="margin-bottom:0">
                <tbody>
              <tr>
                <td class="td-left"  width="20%">姓名</td><td class="word-break" width="20%" style="color:#333" id="pa_lookPage_name"></td>
                <td class="td-left" width="20%">性别</td><td width="20%" id="pa_lookPage_sex" style="color:#333"></td>
              </tr>
              <tr>
                <td class="td-left">职务</td> <td id="pa_lookPage_job" style="color:#333"></td>
                <td class="td-left">警号</td> <td  id="pa_lookPage_policeNo" style="color:#333"></td>
              </tr>
               <tr>
                <td class="td-left">身份证号</td> <td id="pa_lookPage_statusCardNo" style="color:#333"></td>
                <td class="td-left">民族</td> <td  id="pa_lookPage_nationality" style="color:#333"></td>
              </tr>
               <tr>
                <td class="td-left">政治面貌</td><td id="pa_lookPage_politicsType" style="color:#333"></td>
                  <td class="td-left">学历</td> <td  id="pa_lookPage_diploma" style="color:#333"></td>
              </tr>
              <tr>
                <td class="td-left">所属单位</td><td id="pa_lookPage_unit" style="color:#333"></td>
                  <td class="td-left">移动电话</td> <td  id="pa_lookPage_movePhone" style="color:#333"></td>
              </tr>
               <tr>
                <td class="td-left">办公电话</td><td id="pa_lookPage_officePhone" style="color:#333"></td>
                  <td class="td-left">状态</td> <td  id="pa_lookPage_status" style="color:#333"></td>
              </tr>
            </tbody>
          </table>
		<div class="m-ui-commitbox">
			<button class="btn btn-success m-ui-btn2 pa_lookPage_close">关闭</button>
		</div>
	</div>
	<!-- 自己写js -->
	<script type="text/javascript" src="<%=context%>/scripts/xtgl/person/lookPerson.js"></script>


</body>
</html>