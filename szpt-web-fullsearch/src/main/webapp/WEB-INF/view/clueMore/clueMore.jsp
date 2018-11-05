<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>全文检索 – 线索</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="search-bg">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div class="search-result-box" style="margin-top:65px;">
			<ol class="breadcrumb m-ui-breadcrumb">
  				<li id="back"><a href="###" >搜索首页</a></li>
  				<li>警情&nbsp;&nbsp;<span class="color-red2" id="inputResult"></span>&nbsp;&nbsp;的搜索结果</li>
			</ol>

				<div class="m-ui-searchbox">
					<div class="m-ui-searchbox-con">

						<div class="row row-mar">
							<div class="col-xs-3">
								<div class="col-xs-6">
									<label class="control-label" style="line-height:0px">关键字：</label>
								</div>
								<div class="col-xs-6" >
									<input type="text" class="form-control input-sm" id="key">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label" style="line-height:0px">线索来源：</label>
								</div>
								<div class="col-xs-6">
									<select class="form-control input-sm" id="clueSource">
										<option value="">全部</option>
										<option selected>打击指令</option>
										<option>防控指令</option>
										<option>管控指令</option>
										<option>研判指令</option>
										<option>落地指令</option>
										<option>情报核实指令</option>
										<option>研判结果推送指令</option>
									</select>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="col-xs-6">
									<label class="control-label" style="line-height:0px">关联案事件/高危人/重点场所：</label>
								</div>
								<div class="col-xs-4">
									<input type="text" class="form-control input-sm" id="values">
								</div>
							</div>
								<button class="btn btn-primary btn-sm" id="query">查询</button>
								<button class="btn btn-default btn-sm" id="reset">重置</button>
						</div>
					</div>
				</div>
						<div class="row row-mar" style="margin-top: 30px">
							<table id="clueTable"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
								 
							</table>
						</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var title = "${param.title}";
	var startTime = "${param.startTime}";
	var endTime = "${param.endTime}";
</script>
<script type="text/javascript"
	src="<%=context%>/scripts/clueMore/clueMore.js"></script>
</html>
