<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>

<title>实战平台 – 系统管理</title>
<%@include file="/WEB-INF/base/basePart.jsp"%> 
<%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS; 
      String constant_unitInit = com.taiji.pubsec.szpt.xtgl.Constant.ORGANIZATION_INIT;
%>
<script>
var serverName = '<%=request.getServerName()%>' ;
$(document).ready(function() {	
});
document.oncontextmenu = function(){return false;};  

var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
var constantInit = '<%=constant_unitInit%>'
</script>

</head>
<body id="validformId" class="validform">
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

<div id="c-center">
<%@include file="/WEB-INF/base/skin/leftPart-xtgl.jsp"%>


<div id="c-center-right">

<div id="c-center-right-content">

<ol class="breadcrumb m-ui-breadcrumb">
  <li>系统管理</li>
  <li>数据字典</li>
</ol>


<div class="m-ui-title1"><h1>数据字典</h1></div>
		<div class="m-ui-searchbox">
						<div class="m-ui-searchbox-con">
						 	<div class="row row-mar mar-top">
							  <div class="col-xs-4">
											<div class="col-xs-4 ">
												<label class="control-label">字典类型划分：</label>
											</div>
											<div class="col-xs-8">
												<select id="searchKey" class="select2 form-control input-sm">
												</select>
											</div>
								</div>
								
								<div class="col-xs-4">	
											<div class="col-xs-4">
												<label class="control-label">字典类型名称</label>
											</div>
											<div class="col-xs-8">
												<input id="searchVal" type="text" class="default form-control input-sm"  /> 
											</div>
								</div>
								
								<div class="col-xs-4">
									<div class="m-ui-btn-box text-right">
										<button class="btn btn-primary disableControl" id="search">
											<span class="glyphicon glyphicon-search" aria-hidden="true"
												style="margin-right: 10px;"></span>查询
										</button>
										<button class="btn btn-default m-ui-btn3" id="add">新增</button>	
										<button id="reset" class="btn btn-default m-ui-btn3"  style="margin-left: 5px;">重置</button>
									</div>
								</div>
								
							</div>	
						</div>
		</div>  

	
	<div class="row" style="margin-top:15px;">
			<div class="col-xs-8">
				<div class="m-ui-title3"><h2>字典类型</h2></div>
						<div id="drawtable" class="m-ui-table ">
							<table id="datatable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>
						</div>
			</div> 
			
			<div class="col-xs-4">
			     <div style="margin-left:30px">
				     <div class="m-ui-title3"><h2>数据字典项</h2></div>
				      <div  style="border:1px solid #ddd;padding:15px">
					<ul id="ztree-demo" class="ztree" style="width:300px;height:450px;overflow:auto">
					</div>
				    </ul>
				</div>
			</div>
	</div>
</div>
</div>
</div>
<p>
						<span>
							<!-- 右键菜单div -->
							<div id="rMenu" style="position:absolute;display:none;">
							<li style="list-style-type:none">
							 <div class="alert alert-info" style="padding:15px">
							     <button  id="m_add"  class="btn btn-xs btn-success"><span>新增</span></button>
							     <button id="m_edit" class="btn btn-xs btn-success"><span>编辑</span></button>
							     <button  id="m_del" class="btn btn-xs btn-success"><span>删除</span></button>
							     <button id="m_start" class="btn btn-xs btn-primary"><span>启用</span></button>
							     <button  id="m_stop"class="btn btn-xs btn-primary"><span>停用</span></button>
							     <button id="m_query" class="btn btn-xs btn-primary"><span>查看</span></button>
							     <button  id="m_up" class="btn  btn-xs btn-info "><span>上移</span></button>
							     <button id="m_down" class="btn btn-xs btn-info "><span>下移</span></button>
						  </div>
							</div>
						</span>
					</p>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript" src="<%=context%>/custom/szpt/js/left.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/dictionary/dictionarytype.js"></script>

</html>