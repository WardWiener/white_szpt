 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>       
 <!-------右侧内容begin--------> 
<div class="m-ui-title4 mar-top"> <h2>专案办案手段统计</h2></div> 
<div id="methodDepartmentDiv" class="row  row-mar m-icheck-group">
</div>
<div class="row row-mar mar-top">
      <div class="col-xs-1" style="width:90px;"> <label class="control-label">留言日期：</label></div>
					<div id="dateRangeId" class="col-xs-6 dateRange form-group"
					style="height: 30px;">
					<input type="hidden" id="dtfmt" class="dateFmt"
						value="info@yyyy-MM-dd" />
				
					<div class="col-xs-6">
						<div class="input-group" style="margin-right: 10px;">
							<input type="text" id="fixed_start"
								class="laydate-start form-control input-sm" readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
						
					</div>
					<div class="col-xs-6">
						<span style="float: left; margin-top: 4px; margin-left: -2px;">--</span>
						<div class="input-group" style="padding-left: 5px;">
							<input type="text" id="fixed_end"
								class="laydate-end form-control input-sm" readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>
				</div>
		  <div class="col-xs-2"><button id="methodChooseBtn" class="btn btn-primary btn-sm">留言筛选</button> </div>
</div>
<div class="row m-icheck-group">
<p class="col-xs-3"><input id="methodIfGroupCheck" type="checkbox" class="icheckbox">是否按部门分组</p>
</div>
<br>
<div id="methodLiuYanTableDiv">

	</div>
<div class="m-ui-commitbox">
<button id="methodExcelBtn" class="btn btn-primary btn-lg">导出 EXCEL</button> 
<button id="methodGoBackBtn" class="btn btn-default btn-lg">返回</button> 
</div>
