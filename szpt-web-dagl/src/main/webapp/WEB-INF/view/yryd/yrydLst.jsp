<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/common/library/bootstrap/js/scrollspy.js"></script>
<script type="text/javascript" src="<%=context%>/custom/szpt/js/thumbslider.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/thumbslider.css" />
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>

<script>
	$(document).ready(function() {
		$('body').scrollspy({
			target : '#navbar-example'
		});
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-dagl.jsp"%>
		</div>
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">档案管理</a></li>
					<li><a href="#">一人一档</a></li>
				</ol>

				<div class="m-ui-title1">
					<h1>一人一档</h1>
				</div>
				<div class="main-block text-center show-hide-group">
					<span class="mar-right color-gray">姓名、身份证号:</span><input
						class="form-control" id="idcard" style="width: 400px; vertical-align: middle;">
					<button id="searchBtn" class="btn btn-info">查询</button>
					<button id="more" class="btn btn-bordered show-hide-btn">
						高级<span class="icon-angle-down" style="margin-left: 5px;"></span>
					</button>
					<button class="attention btn btn-danger">我的关注</button>
				</div>
					<div class="more row row-mar" style="margin-top: 15px; display:none">
							<div class="col-xs-2" style="width: 100px;">
								<label class="control-label">预警类型：</label>
							</div>
							<div class="col-xs-8" style="padding-top: 5px;">
								<div id="warning-type" class="warning-type" style="width: 700px;">
									<!-----------------------------------------开发注意此处样式修改 去掉<a>标签里的颜色名称------------------------------------------>
									 <a href="#" id="allYjlx" class="warnType btn sq-all" title="全部"></a>
								</div>
							</div>
						</div>
						<div id="rylbDiv" class="more row row-mar" style=" display:none">
							<div class="col-xs-2" style="width: 100px; padding-top: 10px">
								<label class="control-label">人员类型：</label>
							</div>
							<div class="col-xs-10">

								<div  class="row m-icheck-group mar-top">

									<p id="allRylbP" class="col-xs-2" style="width: 139px">
										<input id="allRylb"  type="checkbox" class="icheckbox">全部
									</p>
									<div id="xsqkType" class="col-xs-9"
										style="width: 750px; border: 1px dashed #6e97cb; padding: 5px 10px 8px 10px; margin-top: -8px">
										
									</div>
								</div>
								<div id="otherRylbType" class="row m-icheck-group mar-top">
								</div>
							</div>
						</div>
				<p class="row row-mar" id="personSum"  >
				<span class=" font16 color-orange1">为您找到相关结果<span id="tableTotal"></span>个</span>
				</p> 

				<!-----------------------内容开始-------------------------->
				<div id="personLst" class="m-ui-table">
					<table id="personTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
					</table>
				</div>	
					
				<div id="hiPersonalInfo" style="display: none;" >
					<div class="main-block"> 
					<div class="row person-block person-block-sm">

						<div class="col-xs-3 left">
								<!-------图片轮播-------->
								<div class="thumbslider">
										<div class="thumbslider-prev">
											<a href="javascript:void(0)"></a>
										</div>
										<div class="thumbslider-show" style="width:290px;height:230px"></div>
				
										<div class="thumbslider-next">
											<a href="javascript:void(0)"></a>
										</div>
				
										<div class="thumbslider-circle">
											<ul class="thumbslider-circle-ul">
				
											</ul>
										</div>
									</div>
								</div>
							<!-------图片轮播-------->

						<div class="col-xs-9 right">
							<div  class="col-info">
								
									<h2  valName="name" class="valCell"></h2>
									<span class="icon-xyren mar-left-sm xyr" title="嫌疑人" ></span>
								<span valName="sex" class="valCell" style="margin-right: 50px;"></span>
								<span class="focused  color-red3" style="display: none;"></span>
								<button val="btn" valId="" valType="" valxyr="" class="valCell showDetailsBtn btn btn-sm  btn-danger" >查看详情</button>
								&nbsp&nbsp&nbsp
								<span  class="concern color-red3" style="display: none;"><span class="icon-check micon"></span>已关注</span>
								<span  class="noConcern color-red3" style="display: none;">未关注</span>
								<div class="row color-gray2">
									<p class="warning-type">
										<!---sq-red、 sq-orange、sq-yellow、sq-blue、sq-white、---->
									</p>
									人员类别：<span valName="persontypeName" class="valCell"></span>
								</div>

								<p class="m-line"></p>
								<div class="row">
									<p class="col-xs-6">
										<span class="color-gray">身份证号：</span> <span id="yrydIdNum" valName="idcard" class="valCell"></span>
									</p>
									<p class="col-xs-6">
										<span class="color-gray">联系电话：</span><span valName="phone" class="valCell"></span>
									</p>
									<div class="col-xs-6">
										<p>
											<span class="color-gray">民族：</span><span valName="nation" class="valCell"></span>
										</p>
										<p>
											<span class="color-gray">文化程度：</span><span valName="culture" class="valCell"></span>
										</p>
									</div>
									<div class="col-xs-6">
										<p>
											<span class="color-gray">婚姻状况：</span><span valName="marry" class="valCell"></span>
										</p>
										<p>
											<span class="color-gray">职业：</span><span valName="occupation" class="valCell"></span>
										</p>

									</div>
								</div>
								<p class="m-line"></p>
								<p>
									<span class="color-gray">户籍地址：</span><span valName="address" class="valCell"></span>
								</p>
								<p>
									<span class="color-gray">现住地址：</span><span valName="localAddress" class="valCell"></span>
								</p>
								<p>
									<span class="color-gray">手机号：</span><span valName="phone" class="valCell"></span>
								</p>
							</div>
						</div>
					</div>
					</div>
				</div>
				
				<div id="commonPersonInfo" style="display: none;" >
					<div class="main-block">
					<div class="row  person-block-sm">

							<!-------图片轮播-------->

						<div class="col-xs-9 right">
							<div  class="col-info">
								
									<h2  valName="name" class="valCell"></h2>
								<span valName="sex" class="valCell" style="margin-right: 50px;"></span>
								<span class="focused  color-red3" style="display: none;"></span>
								<button val="btn" valId="" valType="" class="valCell showDetailsBtn btn btn-sm  btn-danger" >查看详情</button>
								&nbsp&nbsp&nbsp
								<span  class="concern color-red3" style="display: none;"><span class="icon-check micon"></span>已关注</span>
								<span  class="noConcern color-red3" style="display: none;">未关注</span>
								<div class="row color-gray2">
									<p class="warning-type">
										<!---sq-red、 sq-orange、sq-yellow、sq-blue、sq-white、---->
									</p>
									人员类别：<span valName="persontypeName" class="valCell"></span>
								</div>

								<p class="m-line"></p>
								<div class="row">
									<p class="col-xs-6">
										<span class="color-gray">身份证号：</span> <span id="yrydIdNum" valName="idcard" class="valCell"></span>
									</p>
									<p class="col-xs-6">
										<span class="color-gray">联系电话：</span><span valName="phone" class="valCell"></span>
									</p>
									<div class="col-xs-6">
										<p>
											<span class="color-gray">民族：</span><span valName="nation" class="valCell"></span>
										</p>
										<p>
											<span class="color-gray">文化程度：</span><span valName="culture" class="valCell"></span>
										</p>
									</div>
									<div class="col-xs-6">
										<p>
											<span class="color-gray">婚姻状况：</span><span valName="marry" class="valCell"></span>
										</p>
										<p>
											<span class="color-gray">职业：</span><span valName="occupation" class="valCell"></span>
										</p>
									</div>
								</div>
								<p class="m-line"></p>
								<p>
									<span class="color-gray">户籍地址：</span><span valName="address" class="valCell"></span>
								</p>
								<p>
									<span class="color-gray">现住地址：</span><span valName="occorAddress" class="valCell"></span>
								</p>
								<p>
									<span class="color-gray">手机号：</span><span valName="phone" class="valCell"></span>
								</p>
							</div>
						</div>
					</div>
				</div>
				</div>
				<div id="perspon" style="display:none"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/scripts/yryd/yrydLst.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
</html>