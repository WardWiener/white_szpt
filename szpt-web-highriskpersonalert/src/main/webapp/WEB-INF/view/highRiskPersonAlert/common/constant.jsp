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
			XB : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.XB%>";
			},
			CZQK : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZQK%>";
			},
			YJLX : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX%>";
			},
			YJLX_HONG_SE : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_HONG_SE%>";
			},
			YJLX_CHENG_SE : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_CHENG_SE%>";
			},
			YJLX_HUANG_SE : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_HUANG_SE%>";
			},
			YJLX_BAI_SE : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_BAI_SE%>";
			},
			YJLX_LAN_SE : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_LAN_SE%>";
			},
			YJLX_QI_TA : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_QI_TA%>";
			},
			RYLX : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX%>";
			},
			YW : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YW%>";
			},
			NJJG : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.NJJG%>";
			},
			RYLBBQ : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLBBQ%>";
			},
			YJJLZT_WCL : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJJLZT_WCL%>";
			},
			YJJLZT_YCL : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJJLZT_YCL%>";
			},
			YJJLZT_HL : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJJLZT_HL%>";
			},
			RYLX_XSQK : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_XSQK%>";
			},
			RYLX_QJ : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QJ%>";
			},
			RYLX_QD : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QD%>";
			},
			RYLX_DQ : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_DQ%>";
			},
			RYLX_ZP : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZP%>";
			},
			RYLX_QT : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QT%>";
			},
			RYLX_SK : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SK%>";
			},
			RYLX_SW : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SW%>";
			},
			RYLX_SD : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SD%>";
			},
			RYLX_JSB : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_JSB%>";
			},
			RYLX_ZDSF : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZDSF%>";
			},
			RYLX_ZT : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZT%>";
			},
			RYLX_QSN : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QSN%>";
			},
			RYLX_AZBR : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_AZBR%>";
			},
			ZKLX : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.ZKLX%>";
			},
			HYQK : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.HYQK%>";
			},
			JYQK : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.JYQK%>";
			},
			ZY : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.ZY%>";
			},
			CZZT : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT%>";
			},
			CZZT_XZ : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_XZ%>";
			},
			CZZT_DSP : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_DSP%>";
			},
			CZZT_SPTG : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPTG%>";
			},
			CZZT_SPBH : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPBH%>";
			},
			AJLB : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.AJLB%>";
			},
			XL : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.XL%>";
			},
			MZ : function(){
				return "<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.MZ%>";
			}
		}
	});	
})(jQuery);
</script>
</body>
</html>