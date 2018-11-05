<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="d" uri="/WEB-INF/tag/securityresource.tld"%> 
<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>


<div class="user-box">
	<div class="user-icon">
		<img src="<%=request.getContextPath()%>/images/police.png">
	</div>
	<div class="user-name"></div>
</div>
<script type="text/javascript">
	$(".user-name").html(currentUserName + "<br>" + currentUnitName);
</script>
<script type="text/javascript">
	$(document).ready(function() {	
		setH3Display();
	});
	/**
	 * 隐藏无下级项的一级标签
	 * @returns
	 */
	function setH3Display () {
		$("#accordion h3").each(function(){
			if($.util.isBlank($(this).attr("myhref"))){
				//myhref为空说明是不可跳转的一级菜单项
				var liLst = $($(this).next()).find("ul li");
				//获取子菜单列表
				var aCount = 0;//跳转连接数
				for(var i = 0; i<liLst.length; i++){
					aCount += $(liLst[i]).find("a").length;
				}
				if(aCount == 0){
					$(this).css("display", "none");
					$($(this).next()).css("display", "none");
				}
			}
		});
	}
	</script>
<h1 class="nav-father">系统管理</h1>

<div id="accordion" class="m-ui-accordion" style="display: none">
   <li>
				<d:ss resource="/dictionaryType/*"
							type="h3" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/dictionaryType/toDictionaryType.action"' value='<span class="leftmenu-icon icon-3"></span><span class="moveSpan"><span class="leftH3Span">数据字典</span></span>' />	
  </li>
	
	<h3><span class="leftmenu-icon icon-2"></span><span class="moveSpan"><span class="leftH3Span">组织机构管理</span></span></h3>
	<div>
		<ul>
			<li>
				<d:ss resource="/unit/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/unit/toUnit.action"' value="单位管理" />	
			</li>
			<li>
				<d:ss resource="/department/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/department/toDepartment.action"' value="部门管理" />	
			</li>
			<li>
				<d:ss resource="/personManage/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/personManage/toPerson.action"' value="人员管理" />	
			</li>
			<li><d:ss resource="/community/showCommunityJsp.action" type="a"
					request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/community/showCommunityJsp.action"'
					value="社区信息维护" /></li>
		</ul>
	</div>
	<h3><span class="leftmenu-icon icon-1"></span><span class="moveSpan"><span class="leftH3Span">角色权限管理</span></span></h3>
	<div>
		<ul>
			<li>
				<d:ss resource="/resource/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/resource/toResource.action"' value="资源管理" />	
			</li>
			<li>
				<d:ss resource="/authority/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/authority/toAuthority.action"' value="权限管理" />	
			</li>
			<li>
				<d:ss resource="/role/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/role/toRole.action"' value="角色管理" />	
			
			</li>
			<li>
				<d:ss resource="/userManage/*"
							type="a" request="${pageContext.request}"
							otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/userManage/toUser.action"' value="用户管理" />	
			</li>
		</ul>
	</div>
</div>
