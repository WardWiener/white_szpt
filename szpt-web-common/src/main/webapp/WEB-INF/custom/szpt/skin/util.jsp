<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.taiji.pubsec.szpt.common.model.SzptUnit,
com.taiji.pubsec.szpt.util.Constant"%> 

<title></title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" />  
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" />  
<link rel="bookmark" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" /> 	


<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/common/style/base.css" /> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/frame.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/branch.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/layer.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/util.js"></script>

<script>

$.common = $.common || {} ;
(function($){
	
	"use strict";
	
	jQuery.extend($.common, { 
		SZPT_UNIT_TYPE_CONSTANT:$.util.parseJsonByJSON('<%=SzptUnit.UNIT_TYPE.toJSONString()%>'),
		PCS_THRESHOLD_TYPE_CONSTANT:$.util.parseJsonByJSON('<%=Constant.PCS_THRESHOLD_TYPE.toJSONString()%>'),
		PCS_FENBU_THRESHOLD_TYPE_CONSTANT:$.util.parseJsonByJSON('<%=Constant.PCS_FENBU_THRESHOLD_TYPE.toJSONString()%>'),
		DICT:$.util.parseJsonByJSON('<%=Constant.DICT.toJSONString()%>'),
		STATISTICS_TYPE:$.util.parseJsonByJSON('<%=Constant.STATISTICS_TYPE.toJSONString()%>'),
		STATISTICS_TIME:$.util.parseJsonByJSON('<%=Constant.STATISTICS_TIME.toJSONString()%>'),
		STATISTICS_COLOR:$.util.parseJsonByJSON('<%=Constant.STATISTICS_COLOR.toJSONString()%>'),
		ROLE_TYPE:$.util.parseJsonByJSON('<%=Constant.ROLE_TYPE.toJSONString()%>'),
		XSFXTS_CONSTANT:$.util.parseJsonByJSON('<%=Constant.XSFXTS_CONSTANT.toJSONString()%>'),
		INFO_SNAPSHOT_MODULE:$.util.parseJsonByJSON('<%=Constant.INFO_SNAPSHOT_MODULE.toJSONString()%>'),
		UNIT_CODE:$.util.parseJsonByJSON('<%=Constant.UNIT_CODE.toJSONString()%>'),
        SHI_DUAN:$.util.parseJsonByJSON('<%=Constant.SHI_DUAN.toJSONString()%>'),
		TIME_VALUE_TYPE_CONSTANT:{
			"COSTUM":"COSTUM",
			"LAST_HOUR":"LAST_HOUR",
			"DAY":"DAY",
			"DAY3":"DAY3",
			"WEEK":"WEEK",
			"MONTH":"MONTH",
			"HALF_YEAR":"HALF_YEAR"
		},
		SZPT_HOST_PORT:'<%=Constant.SZPT_HOST_PORT()%>'
	});
	
	
})(jQuery);	
</script>

<%@include file="/common/library/comet/comet.jsp"%>
