<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>

</head>

<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">

<div class="m-ui-layer-content">
<div class="clear"></div>

<div id="tabs" class="m-ui-tabs3">
        <ul class="nav">
        <!-- <li><a href="#tabs-1">可分配用户</a></li>
         <li><a href="#tabs-2">已分配用户</a></li> -->
        </ul>
<div id="tabs-1">
<div class="row" style="background:#fcfcfc;">
<div class="col-xs-3" >
<div style="padding:10px; border-right:1px solid #ddd;">
        
        
<ul id="treeDemo1" class="ztree"></ul>

        </div>     
        </div>
              
        <div class="col-xs-9">
         <div style="padding:20px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
                       
<div class="row row-mar">
<div class="col-xs-1"> <label class="control-label">姓名：</label></div>
<div class="col-xs-2"> <input id="personName" type="text" class="form-control input-sm"></div>
<div class="col-xs-1"> <label class="control-label">性别：</label></div>
<div class="col-xs-2"> 
    <select class="form-control input-sm" id="sex">
       <option value="" >全部</option>
    </select>
</div>
<div class="col-xs-4"><div class="m-icheck-group" style="margin-left:10px;">
            <p class="col-xs-5"><input type="radio" class="radioState icheckradio" valState="0"  name="DW" id="DW_1" checked>本单位</p>
            <p class="col-xs-7"><input type="radio" class="radioState icheckradio" valState="1" name="DW" id="DW_2">含下级单位</p>
            
            </div></div>
<div class="col-xs-1"><button id="searchBtn" class="btn btn-primary btn-lg">查询</button></div>         
</div>

							<table id="tavleId"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
							</table>       
		 <!-- <thead>
            <tr>
                <th width="7%"></th>
                <th width="7%">序号</th>
                <th width="15%">姓名</th>
                <th width="10%">性别</th>
                <th width="15%">人员类型</th>
                <th width="18%">所属机构</th>
                <th width="14%">状态</th>
                <th>人员编号</th>
            </tr>
        </thead>
        <tbody>
            <tr>
              <td><input type="checkbox" class="icheckbox"></td>
              <td>1</td>
              <td>市局</td>
              <td>男</td>
              <td>test</td>
              <td>XXX</td>
              <td></td>
              <td></td>
          </tr> 
           <tr>
              <td><input type="checkbox" class="icheckbox"></td>
              <td>2</td>
              <td>李甜甜</td>
              <td>女</td>
              <td>test</td>
              <td>XXX</td>
              <td></td>
              <td></td>
          </tr> 
        </tbody>
    </table> -->
</div>
</div>
<!--right-->
</div>
<div class="m-ui-commitbox" style="margin-top:0">
</div>
</div>        
</div>
<!-----tabs----->



</div>
<!--内容end-->


</div>
	<script type="text/javascript"
		src="<%=context%>/script/zazl/zaFindPerson.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

</body>
<style>
.diyTable-page {
	text-align: right;
}

.laypage_main a, .laypage_main span {
	color: #fff;
}

.laypageskin_default a {
	border: 0px;
	background-color: transparent;
}
</style>
</html>