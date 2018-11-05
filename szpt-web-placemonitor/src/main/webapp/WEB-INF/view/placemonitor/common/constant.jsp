<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 实战平台重点人常量 -->
<script type="text/javascript">
(function($){
	"use strict";
	jQuery.extend($.common, { 
		Constant : {
			RYLX : function(){
				return "<%= com.taiji.pubsec.szpt.placemonitor.action.util.Constant.RYLX%>";
			}
		}
	});	
})(jQuery);
</script>
</body>
</html>