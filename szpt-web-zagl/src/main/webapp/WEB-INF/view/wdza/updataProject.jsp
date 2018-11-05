<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

<script type="text/javascript">
	var caseId = "${param.queryStr}";
</script>
</head>

<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">
<div class="m-ui-title2"><h2>专案详情</h2><div class="m-ui-closebox"><button id="closeAdd" class="btn m-ui-close"></button></div></div>
<div class="row" style=" margin:0;background:#f8f8f8;">

<!-------左侧-------->
<div class="col-xs-2" style="width:18%">
<div style="padding:10px; border-right:1px solid #ddd;">
<p class="row-mar"> 专案信息：</p>
 <div class="list-group">
          <a href="#" class="list-group-item  list-group-item-info">案件基本资料</a>
          <a href="#" class="list-group-item">专案涉案人员</a>
          <a href="#" class="list-group-item">涉案人员关系</a>
          <a href="#" class="list-group-item">专案进展报告</a>
          <a href="#" class="list-group-item">办案手段统计</a>
        </div> 
<p class="row-mar"> 专案资料：</p>  
<div class="list-group">
          <a href="#" class="list-group-item">音频资料</a>
          <a href="#" class="list-group-item">视频资料</a>
          <a href="#" class="list-group-item">图片资料</a>
          <a href="#" class="list-group-item">其他资料</a>
        </div>
              
</div>     
</div>
<!-------左侧-------->   
     
 <!-------右侧-------->       
<div class="col-xs-10" style="width:82%">
         <div style="padding:10px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
         
 <!-------右侧内容begin--------> 
<div class="m-ui-title4 mar-top"><h2>维护案件基本信息</h2></div> 
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">专案编号：</label></div>
<div class="col-xs-2 m-label-right"id="zabh" ></div>
<div class="col-xs-2"> <label class="control-label">专案名称：<span class="red-star">*</span></label></div>
<div class="col-xs-5"> <input type="text" id="zamc" class="form-control input-sm" value=""></div>
<div class="col-xs-2"> <label class="control-label">专案性质：<span class="red-star">*</span></label></div>
<div class="col-xs-3"> <select class="form-control input-sm" id="ajxz">
                      </select>
</div>
</div>


<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">涉及子案件：</label></div>
<div class="col-xs-10 m-label-right text-right">
<button class="btn btn-xs btn-primary" id="addZAJBtn" >添加子案件</button>
<button class="btn btn-xs btn-danger" id="remZAJBtn" >删除子案件</button>
</div>
</div>

<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" style="margin-bottom:20px;">
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
        

<h5 class="row-mar"> 简要案情：<span class="red-star">*</span></h5>
<div class="row"><textarea class="form-control input-sm" rows="5" id="jyaq"></textarea></div>
 


<h5 class="row-mar mar-top"> 目前工作进展情况：</h5>
<div class="row"><textarea class="form-control input-sm" rows="5" id="mqjz"></textarea></div>

<h5 class="row-mar mar-top"> 侦查工作中的主要问题：</h5>
<div class="row"><textarea class="form-control input-sm" rows="5" id="zczywt"></textarea></div>

<h5 class="row-mar mar-top"> 下一步工作计划：</h5>
<div class="row"><textarea class="form-control input-sm" rows="5" id="xybjh" ></textarea></div>
<div style="display: none" >
<h5 class="row-mar mar-top"> 关联情报线索：</h5>
<div class="text-right">
<button class="btn btn-xs btn-primary">添加关联线索</button>
<button class="btn btn-xs btn-danger">删除关联线索</button>
</div>
<table class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
        <thead>
            <tr>
                <th width="8%">序号</th>
                <th width="18%">情报编码</th>
                <th width="20%">情报名称</th>
                <th width="23%">采集时间</th>
                <th>内容描述</th>
            </tr>
        </thead>
        <tbody>
            <tr>
              <td>1</td>
              <td>********</td>
              <td>XX要上访</td>
              <td>2016-07-06 14:30</td>
              <td>东城区MAMO现代城有人吸毒…</td>
          </tr> 
           <tr>
              <td>2</td>
              <td></td>
              <td></td>
              <td>2016-07-05 11:30</td>
              <td>少林方丈贪污且…</td>
          </tr> 
        </tbody>
    </table>
    </div>
 <!-------右侧内容end--------> 
 
<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg" id="saveBtn">保存</button>
<button class="btn btn-default btn-lg" id="resBtn" >取消</button> 
</div>
 
</div>
</div>
</div>  
       
<!--row-end-->        
<!--内容end-->


</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript" src="<%=context%>/script/wdza/updataProject.js"></script>
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