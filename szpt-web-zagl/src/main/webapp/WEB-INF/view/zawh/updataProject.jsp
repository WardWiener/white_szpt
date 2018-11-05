<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript">
var id = "${param.queryStr}";
</script>

</head>
<body>
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-zagl.jsp"%>
		</div>
		<div id="c-center-right-content">
		  <ol class="breadcrumb m-ui-breadcrumb">
			  <li><a href="#">首页</a></li>
			  <li><a href="#">专案资料管理</a></li>
			  <li><a href="#">专案维护</a></li>
		  </ol>
<div class="m-ui-title2"><h2>修改专案</h2><div class="m-ui-closebox"><button class="btn m-ui-close" id="closeAdd" ></button></div></div>
<div class="m-ui-layer-content validform" id="updataYZ">
  
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">专案编号：</label></div>
<div class="col-xs-2 m-label-right" id="zabh"></div>
<div class="col-xs-2"> <label class="control-label">专案名称：<span class="red-star">*</span></label></div>
<div class="col-xs-5"> <input type="text" id="zamc" datatype="*1-50" class="form-control input-sm" value=""></div>
<div class="col-xs-2"> <label class="control-label">专案性质：<span class="red-star">*</span></label></div>
<div class="col-xs-3"> <select class="form-control input-sm" id="ajxz">
                      </select>
</div>
</div>
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">涉及子案件：</label></div>
<div class="col-xs-10 m-label-right text-right">
<button class="btn btn-xs btn-primary" id="basicAddZAJBtn">添加子案件</button>
<button class="btn btn-xs btn-danger" id="basicRemZAJBtn">删除子案件</button>
</div>
</div>
<table id="basicTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" style="margin-bottom:20px;">
        <thead>
            <tr>
                <th></th>
                <th>子案件编号</th>
                <th>子案件名称</th>
                <th>子案件办案民警</th>

            </tr>
        </thead>
        <tbody id="basicModel">
                       
        </tbody>
    </table>
<div class="row row-mar">
<p> 简要案情：<span class="red-star">*</span></p>
<div class="col-xs-12"><textarea class="form-control input-sm"  datatype="*1-80" rows="5" id="jyaq" ></textarea></div>
</div> 
<div class="row row-mar">
<p> 目前工作进展情况：</p>
<div class="col-xs-12"><textarea class="form-control input-sm" rows="5" datatype="*0-80" id="gzjz"></textarea></div>
</div>

 <div class="m-ui-commitbox">
		<button class="btn btn-primary btn-lg" id="saveBtn">保存</button>
		<button class="btn btn-default btn-lg" id="resBtn">重置</button> 
		<button class="btn btn-default btn-lg" id="retBtn">取消</button> 
    </div>


<!-- 情报模块开始 -->
<div  style="display: none">
<div class="m-ui-title4"><h2>关联情报线索</h2></div>
<button class="btn btn-xs btn-primary" id="addBtn">添加关联线索</button>
<button class="btn btn-xs btn-danger">删除关联线索</button>
<table class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" >
        <thead>
            <tr>
                <th width="4%" > </th>
                <th width="4%">序号</th>
                <th width="18%">情报编码</th>
                <th width="20%">情报名称</th>
                <th width="23%">采集时间</th>
                <th>内容描述</th>
            </tr>
        </thead>
        <tbody id="glqbxsBtn">
        
            <tr id="model"  class="valCell">
              <td class="xbClass"><input type="checkbox" class="toIcheckbox" name="emphasis"/></td>
              <td class="xbClass valCell" id="xh">0</td>
              <td class="xbClass"><input type="text" id="qbbm" class="valCell" ></td>
              <td class="xbClass"><input type="text" id="qbmc" class="valCell"></td>
              <td class="xbClass">
	                 <div id="time" class="laydate input-group valCell" style="margin-right:10px;width:200px;margin-bottom:10px;">
						<input type="hidden" class="laydate-fmt dateFmt" value="info@yyyy-MM-dd" />
						<input type="text" id="cjsj"  class="laydate-value form-control input-sm  valCell" readonly="readonly">
						<span class="laydate-value-span input-group-addon m-ui-addon">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
	                  </div>
                </td>
              <td class="xbClass"><input type="text" id="nrms" class="valCell" value=""></td>
            </tr>  
        </tbody>
        
    </table>
    </div><!-- 情报模块后期待确认 -->

</div>
<!--内容end-->	
</div>


<script type="text/javascript" src="<%=context%>/script/zawh/updataProject.js"></script>


	</div>
		

	
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<style>
		.diyTable-page{
			text-align: right;
		}
		.laypage_main a, .laypage_main span {
			color:#fff;
		}
		.laypageskin_default a{
			border: 0px;
		    background-color: transparent;
		}
</style>
</html>