<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/foot.js"></script>
<div id="msgTemplateDiv" style="display:none">
	<p>指令内容：<span class="context"></span></p>
	<p style="text-align: right;">指令发送单位：<span class="unit"></span></p>
	<p style="text-align: right;"><span class="time"></span></p><br/>
	<p style="text-align: right;"><button class="btn btn-sm btn-primary receiveBtn">签收</button></p>
</div>

<div id="msgTemplateDivBkResult" style="display:none">
	<p><a href="javascript:void(0);" class="bkResultTitle"></a></p>
</div>