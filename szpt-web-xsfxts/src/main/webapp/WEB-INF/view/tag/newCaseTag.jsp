<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>
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
							<span id="caseTitleUp"></span>
							<span id="caseStateUp" class="state state-red2 mar-left"></span>
						</h1>
					</div>


					<div class="row">
						<!-- 案件基本信息 -->
						<%@include file="/WEB-INF/view/tag/lookCriminalBasicCase.jsp"%>
						<!-- 案件分析打标信息 -->
						<div id="tagValidform" class="validform col-xs-4" style="width: 350px; margin-left: -350px; float: left;">
							<input name="id" type="hidden" class="form-val"/>
							<div class="m-ui-title4" style="margin-bottom: 20px">
								<h2>
									<span class="glyphicon glyphicon-star  color-red2 mar-right"></span>案件分析打标
								</h2>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件级别：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-7">
									<select id="level" name="level"
										class="form-control input-sm select2-noSearch valiform-keyup form-val"
										datatype="*"></select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件类别：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-7">
									<select id="type" name="type"
										class="form-control input-sm select2-noSearch valiform-keyup form-val"
										datatype="*">

									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">案件性质：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-4 ajxzSelect">
									<select id="firstSort" name="firstSort" datatype="*"
										class="form-control select2-noSearch input-sm valiform-keyup form-val">

									</select>
								</div>
								<div class="col-xs-4 ajxzSelect">
									<select id="secondSort" name="secondSort"
										class="form-control select2-noSearch input-sm form-val">

									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">发案地点：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-7 input-group">
									<input type="text" id="address" name="address" class="form-control input-sm valiform-keyup form-val showMap" readonly datatype="*1-500" errormsg="地址不可大于500个字符" nullmsg="请选择地址"/> 
									<span class="input-group-addon showMap">
										<span class="glyphicon glyphicon-map-marker"></span>
									</span>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">发案社区：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-7">
									<select id="community" name="community"
										class="form-control input-sm select2 valiform-keyup form-val"
										datatype="*">
									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">涉案场所：</label>
								</div>
								<div class="col-xs-4">
									<select id="placeType" name="placeType" class="form-control input-sm form-val select2-noSearch allowClear">
										
									</select>
								</div>
								<div class="col-xs-4  input-group">
									<input id="placeName" name="placeName" class="form-control input-sm form-val">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-search"></span>
									</span>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">选择处所：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-7">
									<select id="occurPlace" name="occurPlace" class="form-control input-sm select2-noSearch valiform-keyup form-val" datatype="*">
									
									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案进口：<span class="">&nbsp;</span></label>
								</div>
								<div class="col-xs-7">
									<select id="entrance" name="entrance" class="form-control input-sm select2-noSearch form-val allowClear">
									
									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案出口：<span class="">&nbsp;</span></label>
								</div>
								<div class="col-xs-7">
									<select id="exit" name="exit" class="form-control input-sm select2-noSearch form-val allowClear">

									</select>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案人数：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-9">
									<div id="zars" class="row m-icheck-group"></div>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案时间：<span class="">&nbsp;</span></label>
								</div>
								<div class="col-xs-9">
									<div id="zasj" class="row m-icheck-group"></div>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-3">
									<label class="control-label">作案特点：<span
										class="red-star">*</span></label>
								</div>
								<div class="col-xs-9">
									<div id="zatd" class="row m-icheck-group"></div>
								</div>
							</div>

							<div class="row  m-ui-commitbox">
								<button id="save" class="btn btn-primary" style="width: 200px">保存</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript"
		src="<%=context%>/scripts/tag/newCaseTag.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>