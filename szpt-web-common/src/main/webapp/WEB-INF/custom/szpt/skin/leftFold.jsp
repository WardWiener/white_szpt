<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--展开折叠按钮-->
<div class="leftfold-box-out" style="display:none">
<div class="leftfold-box">
<a href="###"  class="btn-leftfold btn-leftfold-yes" ></a>
<a href="###" class="btn-leftfold btn-leftfold-no"></a>
</div>
</div>
<!-- 
当隐藏"#c-center-left"时，请将body加上class"m-ui-layer-body",
同时“。btn-leftfold-yes”隐藏,“。btn-leftfold-no”显示,
同时"#c-center-right"加上style"left:20px"
-->
<!-- 
当展开"#c-center-left"时，请将body移除class"m-ui-layer-body",
同时“。btn-leftfold-yes”显示,“。btn-leftfold-no”隐藏,
同时"#c-center-right"移除style"left:20px"
-->
<!--展开折叠按钮-->

<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/letFold.js"></script>


