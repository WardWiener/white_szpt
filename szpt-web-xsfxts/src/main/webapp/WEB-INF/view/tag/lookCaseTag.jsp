<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>


	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>
		<input id="paraId" type="hidden"
			value="<%=request.getParameter("id")%>">
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">案件分析打标</a></li>
				</ol>

				<div class="m-ui-title1">
					<h1>
						<button id="back" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						案件分析打标
					</h1>
				</div>
				<div class="main-block light-block-er" style="margin-top: 30px;">
					<div class="m-ui-title1 text-center" style="padding: 10px;">
						<h1>
							<span id="caseTitleUp"></span><span id="caseStateUp"
								class="state state-red2 mar-left"></span>
						</h1>
					</div>


					<div class="row">
						<!-- 案件基本信息 -->
						<%@include file="/WEB-INF/view/tag/lookCriminalBasicCase.jsp"%>
						<!-- 案件分析打标信息 -->
						<div id="caseTagInfo" class="col-xs-4" style="width: 350px; margin-left: -350px; float: left;">
							<div class="m-ui-title4" style="margin-bottom: 20px">
								<h2>
									<span class="glyphicon glyphicon-star  color-red2 mar-right"></span>案件分析打标
								</h2>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件级别：</label>
								</div>
								<div class="col-xs-7 m-label-right" name="levelName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件类别：</label>
								</div>
								<div class="col-xs-7 m-label-right" name="typeName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件性质：</label>
								</div>
								<div class="col-xs-7  m-label-right">
									<span name="firstSortName"></span>&nbsp;&nbsp;
									<span name="secondSortName"></span>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">发案地点：</label>
								</div>
								<div class="col-xs-7  m-label-right" name="address"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">发案社区：</label>
								</div>
								<div class="col-xs-7  m-label-right" name="communityName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">涉案场所：</label>
								</div>
								<div class="col-xs-7  m-label-right">
									<span name="placeTypeName"></span>&nbsp;&nbsp;
									<span name="placeName"></span>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">选择处所：</label>
								</div>
								<div class="col-xs-7  m-label-right" name="occurPlaceName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案进口：</label>
								</div>
								<div class="col-xs-7  m-label-right" name="entranceName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案出口：</label>
								</div>
								<div class="col-xs-7  m-label-right" name="exitName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案人数：</label>
								</div>
								<div class="col-xs-9  m-label-right" name="peopleNumName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案时间：</label>
								</div>
								<div class="col-xs-9  m-label-right" name="periodName"></div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案特点：</label>
								</div>
								<div class="col-xs-9  m-label-right" name="featureNames"></div>
							</div>
							<div class="row  m-ui-commitbox">
								<button id="modify" class="btn btn-success" style="width: 200px;display:none;">修改</button>
							</div>
						</div>
						<div class="row" style="padding-left:10px;">
							<div class="m-ui-title4 mar-top"><h2>操作历史</h2></div>
							<table id="operateRecordTable" class="table table-sg table-sg-sm" cellspacing="0" width="600"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/tag/lookCaseTag.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>