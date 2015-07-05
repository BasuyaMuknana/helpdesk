<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="ui vertical inverted icon sidebar menu left" ><!-- <div class="ui large fixed inverted menu" style="margin-top: 14px;"> -->
	<div class="item">  PDIS HELPDESK</div> 
	<div class="item">
		<i class="large child icon"></i> 		
		<span style="font-size:1.2em"><sec:authentication property="principal.username" /></span>
		
	</div> 
	
	<a class="item" href="<c:url value='/admin/logsQuery'/>"> <i class="large history icon"></i> <p>檢視紀錄查詢</p></a>
	<a class="item" href="<c:url value='/admin/contentsManage'/>"> <i class="large announcement icon"></i> <p>告知內容管理</p></a>
	<a class="item" href="<c:url value='/admin/serviceCheck'/>"> <i class="large treatment icon"></i> <p>系統狀態測試</p></a>
	<a class="item" href="<c:url value='/admin/usersManage'/>" > <i class="large users icon"></i> <p>使用者管理</p></a>
	
	<div class="item"></div>
	<div class="item"></div>
	<%-- <div class="ui pointing dropdown link item">
		<i class="large dropdown icon"></i> 
		<i class="large users icon"></i> 
		使用者管理
		<div class="menu">
			<div class="item"><i class="large user icon"></i><span style="font-size:1.2em">使用者管理</span></div>
			<div class="divider"></div>
			<div class="item"><i class="large users icon"></i><span style="font-size:1.2em">群組管理</span></div>
		</div>	
	</div> --%>
	
	
	<!-- <div class="right menu"> -->
		
		<div class="item" >
			<a class=" ui primary button" href="<c:url value='/j_spring_security_logout'/>">
				<i class=" sign out icon"></i>
				<span style="font-size:1.2em">登出</span>
			</a>
		</div>
	<!-- </div> -->
</div>
<div class="ui black big right attached fixed icon button" style="margin-top:100px;"><i class="large  content icon"></i></div>
<script src='<c:url value="/js/application/apCommon.js" />'></script>
