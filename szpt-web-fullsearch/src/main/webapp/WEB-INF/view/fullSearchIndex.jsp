<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>全文检索 </title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/chosen.css" />
</head>
<body class="search-bg">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">
	<div class="search-result-box">
		 <div class="nb-home-box" id="indexTitle">
			<div class="search-home-box">
				<div class="search-box" style="margin-bottom:15px" >
				<input class="form-control" id="searchInput"><button class="btn btn-primary" id="search">搜索</button>
				</div>
				<a href="#" id="search-advanced-btn"  class="search-advanced-btn">高级搜索<span class="glyphicon glyphicon-menu-down mar-left"></span></a>
				<div class="clear"></div>
				<div id="search-advanced-query" class="search-advanced-query">
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">查询对象：</label></div>
<div class="col-xs-10">
      <div id="category" class="m-icheck-group">
      <p class="col-xs-12"><input id="allCategory" type="checkbox" class="icheckbox">全部</p>
       <p class="col-xs-4"><input type="checkbox" valName="jq" class="icheckbox category">警情</p> 
        <p class="col-xs-4"><input type="checkbox" valName="aj" class="icheckbox category">案件</p> 
         <p class="col-xs-4"><input type="checkbox" valName="gwr" class="icheckbox category">高危人</p> 
          <p class="col-xs-4"><input type="checkbox" valName="cs" class="icheckbox category">场所</p> 
           <p class="col-xs-4"><input type="checkbox" valName="qbxs" class="icheckbox category">情报线索</p> 
            <p class="col-xs-4"><input type="checkbox" valName="zl" class="icheckbox category">指令</p>
      </div>             
                      </div>                  
</div>
<div class="row row-mar" style="margin-top:20px;">
<div class="row row-mar">
						<div id="messageDate" class="col-xs-12 dateRange">
							<input type="hidden" id="dtfmt" class="dateFmt"
								value="info@yyyy-MM-dd HH:mm:ss" />
							<div class="col-xs-3">
								<label class="control-label">查询时间：</label>
							</div>
							<div class="col-xs-4 input-group">
								<input type="text" id="time_start" date-pos="bottom"
									class="laydate-start form-control input-sm valiform-keyup form-val"
									datatype="*1-50" nullmsg="请选择开始时间" readonly="readonly">
								<span class="laydate-start-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
							<div class="col-xs-1 to">—</div>
							<div class="col-xs-4 input-group">
								<input type="text" id="time_end"
									class="laydate-end form-control input-sm valiform-keyup form-val"
									datatype="*1-50" nullmsg="请选择开始时间" readonly="readonly">
								<span class="laydate-end-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
					</div>
</div>

<div class="row row-mar text-center" style="margin-top:20px;">

</div>
</div>
</div> 
</div>

<!-- <div class="search-box" style="margin-bottom:15px"><input class="form-control"><button class="btn btn-primary">搜索</button></div>
<div class="clear"></div> -->


			
			<!-- <a href="#" class="advanced-btn search-advanced-btn">高级条件设置</a> -->

			<!------高级搜索表单----->
			<!-- <div class="advanced-query search-outline">
				<div class="search-advanced-query">
					<div class="row row-mar">
						<div id="messageDate" class="col-xs-12 dateRange">
							<input type="hidden" id="dtfmt" class="dateFmt"
								value="info@yyyy-MM-dd HH:mm:ss" />
							<div class="col-xs-3">
								<label class="control-label">查询时间：</label>
							</div>
							<div class="col-xs-4 input-group">
								<input type="text" id="time_start" date-pos="bottom"
									class="laydate-start form-control input-sm valiform-keyup form-val"
									datatype="*1-50" nullmsg="请选择开始时间" readonly="readonly">
								<span class="laydate-start-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
							<div class="col-xs-1 to">—</div>
							<div class="col-xs-4 input-group">
								<input type="text" id="time_end"
									class="laydate-end form-control input-sm valiform-keyup form-val"
									datatype="*1-50" nullmsg="请选择开始时间" readonly="readonly">
								<span class="laydate-end-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
					</div>

					<div class="row row-mar">
						<div class="col-xs-3">
							<label class="control-label">查询对象：</label>
						</div>
						<div class="col-xs-4">
							<select class="form-control input-sm" id="searchObject">
								<option value="all">全部</option>
								<option value="jq">警情</option>
								<option value="aj">案件</option>
								<option value="gwr">高危人</option>
								<option value="cs">场所</option>
								<option value="qbxs">情报线索</option>
								<option value="zl">指令</option>
							</select>
						</div>
					</div>

				</div>
				<div class="advanced-btn-box mar-top">
					<button class="advanced-btn-up">收起高级查询</button>
				</div>
			</div> -->
			<!------高级搜索表单----->
			
		
		<!------ 点击搜索后显示的内容开始  ----->
		<div id="infoTitle" style="display:none;">
			<div class="m-ui-searchbox" style="display:none;">
				<div  class="row row-mar" >
					<div class="col-xs-2"></div>
					<div class="col-xs-6">
					<div class="nb-search-box" style="margin-bottom: 15px">
						<input class="form-control" id="infoInput">
					</div>
					</div>
					<div class="col-xs-4">
							<button class="btn btn-info" id="query">
								<span class="glyphicon glyphicon-link mar-right"></span>搜索
							</button>
							<button id="back" class="advanced-btn-up" style="margin-top: 10px">返回高级查询</button>
					</div>
				</div>
			</div>
				<ol class="breadcrumb m-ui-breadcrumb">
					<li id="back"><a href="###" >搜索首页</a></li>
					<li><span class="color-red2" id="inputResult"></span>&nbsp;&nbsp;的搜索结果</li>
				</ol>

				<div class="row row-mar">
				<div class="col-xs-6">
				<div class="box light-block" style="margin-right:10px; height:400px;">
						<table>
							<tr>
								<td width="100px"><h4>警情</h4></td>
								<td id="eventTd" width="100px" style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="eventMore" class="btn btn-link">更多</button></td>
							</tr>
						</table>
						<table id="eventTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
				<div class="col-xs-6">
					<div class="box light-block" style="margin-right:10px; height:400px;">						<table>
							<tr>
								<td width="100px"><h4>案件</h4></td>
								<td id="casesTd" width="100px" style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="eventMore" class="btn btn-link">更多</button></td>
								
							</tr>
						</table>
						<table id="casesTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-6">
					<div class="box light-block" style="margin-right:10px; height:400px;">
						<table>
							<tr>
								<td width="100px"><h4>高危人</h4></td>
								<td id="highriskpersonTd" width="100px"
									style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="eventMore" class="btn btn-link">更多</button></td>
							</tr>
						</table>
						<table id="highriskpersonTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
				<div class="col-xs-6">
					<div class="box light-block" style="margin-right:10px; height:400px;">
						<table>
							<tr>
								<td width="100px"><h4>场所</h4></td>
								<td id="siteTd" width="100px" style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="eventMore" class="btn btn-link">更多</button></td>
							</tr>
						</table>
						<table id="siteTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-6">
					<div class="box light-block" style="margin-right:10px; height:400px;">
						<table>
							<tr>
								<td width="100px"><h4>指令</h4></td>
								<td id="commandTd" width="100px" style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="commandMore" class="btn btn-link">更多</button></td>
							</tr>
						</table>
						<table id="commandTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
				<div class="col-xs-6">
					<div class="box light-block" style="margin-right:10px; height:400px;">
						<table>
							<tr>
								<td width="100px"><h4>线索</h4></td>
								<td id="clueTd" width="100px" style="text-align: right;"></td>
								<td width="100px" style="text-align: left;">条</td>
								<td width="250px" style="text-align: right;"><button
										id="clueMore" class="btn btn-link">更多</button></td>
							</tr>
						</table>
						<table id="clueTable"
							class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

		<!------ 点击搜索后显示的内容结束  ----->
	<!--  
	<div class="coyright-index">
		<span class="glyphicon glyphicon-copyright-mark mar-right"></span>2016
		XXXX - 黔ICP备11010000号 - <span class="m-inline icon-foot-police"></span>黔公网安备11000004000000号
	</div>
	-->
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/fullSearchIndex.js"></script>
	
	<script type="text/javascript">
		var search = '<%=request.getParameter("search")%>';
	</script>
</html>
