<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<script type="text/javascript">
	var bkdId = "${param.bkdId}";
	var bkdh = "${param.bkdh}";
</script>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">高危人群分析</a></li>
					<li class="active"><a href="#">人员布控</a></li>
				</ol>

<input id="isApprove" style="display:none" value="1">
<!--整体查询板块--begin-->
<div class="basic-query-out">
<div class="basic-query"><input id="content2"type="text" class="form-control input-sm" value="内容模糊查询" onBlur="if(!value){value=defaultValue;this.style.color='#b1b8c2'}"  onFocus="if(value==defaultValue){value='';this.style.color='#fff'}" style="color:#b1b8c2;"><button class="btn btn-primary btn-sm search">查询</button><button class="advanced-btn">展开高级查询</button></div>
</div>


<div class="m-ui-searchbox  advanced-query">
<div class="m-ui-searchbox-con">

<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">布控单编号：</label></div>
    <div class="col-xs-6"><input id="num" type="text" class="form-control input-sm"></div>
</div>

<div id="dateRange" class="col-xs-6 dateRange">
									<input type="hidden" id="dtfmt" class="dateFmt"
										value="yyyy-MM-dd HH:ss" />
									<div class="col-xs-3">
										<label class="control-label">布控时间范围：</label>
									</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_start" date-pos="bottom"
												class="laydate-start form-control input-sm"
												readonly="readonly"> <span
												class="laydate-start-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
									<div class="col-xs-3 to">――</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_end"
												class="laydate-end form-control input-sm"
												readonly="readonly"> <span
												class="laydate-end-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
								</div>

<div class="col-xs-3">
<div class="col-xs-6"> <label class="control-label">布控人姓名：</label></div>
    <div class="col-xs-6"><input id="personName" type="text" class="form-control input-sm"></div>
   
</div>
</div>
<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">布控人员身份证号：</label></div>
    <div class="col-xs-6"><input id="idcardNo" type="text" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">备注：</label></div>
    <div class="col-xs-6"><input id="note" type="text" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">审批状态：</label></div>
    <div class="col-xs-6"><select id="operateStatus"class="form-control input-sm select2-noSearch allowClear">
    <option value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_DSP%>">待审批</option>
    <option value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPTG%>">审批通过</option>
    <option value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPBH%>">审批驳回</option>
    </select></div>
</div>
<div class="col-xs-3 text-right ">  
<input type="checkbox" class="icheckbox" id="ifDisabled"name="ifDisabled">&nbsp;&nbsp;是否显示已失效布控单
</div>
<div class="col-xs-3 text-right">  
<button class="btn btn-primary btn-sm search">查询</button>
<button class="btn btn-default btn-sm" id="reset">重置</button>   
</div>
</div>

</div>
</div>
<!--查询结束-->


<div class="advanced-btn-box"><button class="advanced-btn-up">收起高级查询</button></div>

<!--整体查询板块--end-->

<!--悬浮操作层-->
<div class="m-ui-title1"><h1>人员布控</h1><div class="m-ui-caozuobox">
<button id="add"class="btn btn-primary" >新增布控</button>
<button id="edit"class="btn btn-success" >维护布控</button>
<button id="stop"class="btn btn-danger" >停止布控</button>
<button id="result"class="btn btn-primary">查看布控结果</button>
<button id="approve"class="btn btn-primary">审批</button>
<button id="refresh"class="btn btn-primary"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
</div>
</div>

<!--悬浮操作层-->

<div class="m-ui-table" style="margin-top:40px;">
                  <table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                  </table>
                  
                  
              </div>
<!--结束-->
</div>
</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<input type="text" id="keySelect" style="margin-bottom:5px;display:none;" class="form-control input-sm" />
		<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personExecuteControl/personExecuteControlList.js"></script>
</html>