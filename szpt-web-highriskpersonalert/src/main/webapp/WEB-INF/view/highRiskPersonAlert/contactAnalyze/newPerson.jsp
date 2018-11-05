<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<body id="validform" class="validform m-ui-layer-body">

<div class="m-ui-layer-box" style="width:550px;">
<div class="m-ui-title2"><h2>人员及话单增加</h2></div>
<div class="row row-mar mar-top">
<div class="col-xs-2"> <label class="control-label">姓名：</label></div>
<div class="col-xs-3"><input id="name" class="form-control input-sm valiform-keyup"datatype="*1-10"errormsg="请填写正确姓名"></div>
<div class="col-xs-2"> <label class="control-label">身份证：</label></div>
<div class="col-xs-3"><input id="idcode"class="form-control input-sm valiform-keyup"datatype="null|*18-18"errormsg="请填写正确身份证"></div>
<button id="addPhone"class="btn btn-info btn-sm" title="增加手机号"><span class="glyphicon glyphicon-plus"></span></button>
</div>
<div class="row row-mar phoneDiv">
<div class="col-xs-2"> <label class="control-label">手机号1：</label></div>
<div class="col-xs-3"><input class="form-control input-sm valiform-keyup phone" value=""datatype="n11-11"errormsg="请填写正确手机号"></div>
</div>
<table id="table"class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
   <thead>
            <tr>
                <th width="21%">拨出电话</th>
                <th width="21%">接入电话</th>
                <th width="33%">接通时间</th>
                <th>通话时长</th>
                <th width="10%">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr class="phoneTr">
              <td><input id="out1"class="form-control input-sm valiform-keyup out"datatype="n11-11"errormsg="请填写正确手机号"></td>
              <td><input id="in1"class="form-control input-sm valiform-keyup in"datatype="n11-11"errormsg="请填写正确手机号"></td>
              <td><div id="time1" class="input-group laydate">
											<input type="hidden" class="laydate-fmt dateFmt"
												value="yyyy-MM-dd HH:mm:ss" /> <input
												id="gatherTime_input" 
												class="form-control laydate-value"
												readonly="readonly"> <span
												class="input-group-addon m-ui-addon laydate-value-span"> <span 
												class="glyphicon glyphicon-calendar" aria-hidden="true"
												style="font-size: 16px;"></span>
											</span>
										</div></td>
              <td><input id="long1"class="form-control input-sm valiform-keyup"></td>
              <td><button class="btn btn-x">×</button> </td>
          </tr> 
        </tbody>
   
    </table>
<p class="text-right" style="padding-right:20px; padding-top:10px;"><button id="add"class="btn btn-info btn-sm"  title="增加一条"><span class="glyphicon glyphicon-plus"></span></button> </p>
</div>
<div class="m-ui-commitbox">
<button id="save"class="btn btn-primary">确定</button>
</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/contactAnalyze/newPerson.js"></script>

</html>