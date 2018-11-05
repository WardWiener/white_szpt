<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>

</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:100%,height: 100%;">
<div class="m-ui-layer-content">
<div  class="person-list person-list-sm">
	<ul id="role">
		<li class="" id='model' style="display: none" ><a href="javascript:void(0)" bb="" class="valCell" valName="name" ></a></li>		
	</ul>
	<ul id="role2" style="display: none">
		<li class="" bb="" id='model2' style="display: none" ><a href="javascript:void(0)" bb="" class="valCell" valName="name" ></a></li>		
	</ul>
</div>
<div class="clear"></div>

<div id="tabs" class="m-ui-tabs3">
        <ul class="nav">
        <li><a href="#tabs-1" id="kfpBtn">可分配用户</a></li>
         <li><a href="#tabs-2" id="yfpBtn" >已分配用户</a></li>
        </ul>
<div id="tabs-1">
<div class="row" style="background:#fcfcfc;">
<div class="col-xs-3" >
        <div style="padding:10px; border-right:1px solid #ddd;">
        
        
<!--树形单位-->	<ul id="treeDemo1" class="ztree"></ul>

         
        </div>     
        </div>
        
<!--left-->        
        <div class="col-xs-9">
         <div style="padding:20px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
                       
<div class="row row-mar">
<div class="col-xs-1"> <label class="control-label">姓名：</label></div>
<div class="col-xs-2"> <input type="text" id="personName" class="form-control input-sm"></div>
<div class="col-xs-1"> <label class="control-label">性别：</label></div>
<div class="col-xs-2"> 
    <select class="form-control input-sm" id="sex">
       <option value="" >全部</option>
    </select>
</div>
<div class="col-xs-4"><div class="m-icheck-group" style="margin-left:10px;" id="radio1">
            <p class="col-xs-5"><input type="radio" class="icheckradio" name="DW" id="DW_1" checked="checked" value="0" >本单位</p>
            <p class="col-xs-7"><input type="radio" class="icheckradio" name="DW" id="DW_2" value="1">含下级单位</p>
            </div></div>
            
 <div class="col-xs-2"> 
	            <button class="btn btn-primary" id="searchBtn">查询</button>
            </div>
</div>

<table class="table table-bordered table-hover m-ui-table-whole" id="tavleId" cellspacing="0">
       
    </table>
</div>
</div>
<!--right-->
</div>
         <div class="m-ui-commitbox" style="margin-top:0">
					<button class="btn btn-primary btn-lg" id="save" >分配</button>
					<button class="btn btn-default btn-lg" id="res">返回</button> 
			</div>

</div> 


<div id="tabs-2">
<div class="row" style="background:#fcfcfc;">
<div class="col-xs-3" >
        <div style="padding:10px; border-right:1px solid #ddd;">
        
        
<!--树形单位-->	<ul id="treeDemo2" class="ztree"></ul>

         
        </div>     
        </div>
        
<!--left-->        
        <div class="col-xs-9">
         <div style="padding:20px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
                       

<table class="table table-bordered table-hover m-ui-table-whole" id="tavleId2" cellspacing="0">
       
    </table>
</div>
</div>
<!--right-->
</div>
                  <div class="m-ui-commitbox" style="margin-top:0">
					<button class="btn btn-primary btn-lg" id="save2" >取消分配</button>
					<button class="btn btn-default btn-lg" id="res2">返回</button> 
				  </div>

</div> 
       
</div>
<!-----tabs----->



</div>
<!--内容end-->


</div>

<script>

var clickOrder = 'null'; 
var clickListOrder = 'null'; 

if(clickOrder=="null"){
	clickOrder = "0" ;
} ;
if(clickListOrder=="null"){
	clickListOrder = "0" ;
} ;

var isReSize = true ;
var firstLoad = true ;
</script>
<script type="text/javascript">
		/* //
		var setting = {
				callback: {
					onClick: zTreeOnClick
				},

			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodesT1 =[
			{ id:1, pId:0, name:"贵阳市经开分局", open:true, iconOpen:"../images/ztree/ztree-icon_dw.png", iconClose:"../images/ztree/ztree-icon_dw.png"},
			{ id:11, pId:1, name:"法制室", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:12, pId:1, name:"刑侦大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:13, pId:1, name:"网安大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:14, pId:1, name:"......", icon:"../images/ztree/ztree-icon_dot.png"},
			
			{ id:2, pId:0, name:"xxxxx局", open:true, iconOpen:"../images/ztree/ztree-icon_dw.png", iconClose:"../images/ztree/ztree-icon_dw.png"},
			{ id:21, pId:2, name:"法制室", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:22, pId:2, name:"刑侦大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:23, pId:2, name:"网安大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:24, pId:2, name:"......", icon:"../images/ztree/ztree-icon_dot.png"},

		];
		var zNodesT2 =[
			{ id:1, pId:0, name:"贵阳市经开分局", open:true, iconOpen:"../images/ztree/ztree-icon_dw.png", iconClose:"../images/ztree/ztree-icon_dw.png"},
			{ id:11, pId:1, name:"法制室", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:12, pId:1, name:"刑侦大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:13, pId:1, name:"网安大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:14, pId:1, name:"......", icon:"../images/ztree/ztree-icon_dot.png"},
			
			{ id:2, pId:0, name:"xxxxx局", open:true, iconOpen:"../images/ztree/ztree-icon_dw.png", iconClose:"../images/ztree/ztree-icon_dw.png"},
			{ id:21, pId:2, name:"法制室", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:22, pId:2, name:"刑侦大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:23, pId:2, name:"网安大队", icon:"../images/ztree/ztree-icon_dot.png"},
			{ id:24, pId:2, name:"......", icon:"../images/ztree/ztree-icon_dot.png"},

		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo1"), setting, zNodesT1);
		});
		$(document).ready(function(){
	        $.fn.zTree.init($("#treeDemo2"), setting,zNodesT2);	
	    });
		
		function zTreeOnClick(event, treeId, treeNode) {
			unitId = treeNode.id;
			creatTable();
		}

		//--> */
	</script>
   <script type="text/javascript" src="<%=context%>/script/zawh/allotUser.js"></script>
</body>
</html>