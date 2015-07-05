<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html lang="zh-TW">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">


<%-- Font Awesome --%>
<%-- <link href="<c:url value="/font-awesome/css/font-awesome.min.css" />"
	rel="stylesheet"> --%>

<%-- application --%>
<link href="<c:url value="/css/application.css"/>" rel="stylesheet">
<script src="<c:url value="/js/application.js"/>"></script>


<!--[if lt IE 9]>
  <script src="<c:url value="/js/html5shiv.js"/>"></script>
  <script src="<c:url value="/js/respond.min.js"/>"></script>
<![endif]-->
<%--  
<script src="<c:url value="/js/kendo.web.min.js"/>"></script>
<script src="<c:url value="/js/cultures/kendo.culture.zh-TW.min.js"/>"></script>
--%>

<%-- jquery v1.11.1 --%>
<script src='<c:url value="/js/jquery.min.js"            />'></script>

<%-- semantic v1.4.1 --%>
<script src='<c:url value="/semantic_ui/semantic.min.js" />'></script>
<link href='<c:url value="/semantic_ui/semantic.min.css" />' rel="stylesheet">

<title>PDIS Help Desk</title>
</head>
<body>
	<div class="ui page grid">
		<div class="row">
			<div class="center aligned sixteen wide column">
				<h1 class="ui header">個資使用告知共用服務後臺管理系統</h1>
				<h2 class="ui header">PDIS HELPDESK</h2>
			</div>
		</div>
		<div class="row">
			<div class="three wide column">
			</div>
			<div class="ten wide column">
				<s:if test="%{#parameters.error != null}">
					<div id="errMsg">
						<!-- insert error msg here -->
						<div class="ui icon error message">
							<i class="warning icon"></i>
							<div class="content">
								<h3>您輸入的帳號密碼有誤或帳號未啟用，請再嘗試一次</h3>
							</div>
						</div>
					</div>
				</s:if>
				<!-- form 不要使用struts form tag宣告，不然struts會在後面加上.action，會讓SS認不得 -->
				<form action="<c:url value='/j_spring_security_check'/>"
					method="post" id="homeLogin">

					<div id="signInForm" class="ui form segment">
						<div class="field">
							<label>Username</label>
							<div class="ui left icon corner labeled input">
								<s:textfield id="username" name="j_username" value=""
									theme="simple" placeholder="Username" autofocus="true"/>

								<i class="user icon"></i>
								<div class="ui corner orange label">
									<i class="asterisk icon"></i>
								</div>
							</div>
						</div>
						<div class="field">
							<label>Password</label>
							<div class="ui left icon corner labeled input">
								
								<s:password id="password" name="j_password" value=""
									theme="simple" placeholder="Password" />
								<i class="lock icon"></i>
								<div class="ui corner orange label">
									<i class="asterisk icon"></i>
								</div>
							</div>
						</div>
      					
					<input id="signInBtn" type="submit" value="Login" class="ui large blue button" />
					<a class="ui large green tag label ap_help"
							data-variation="wide inverted"
							data-html='<h4>請聯絡系統開發部技術研發課同仁</h4>'
						>
							<i class="large help circle icon" ></i>
							沒有帳號或忘記密碼?
						</a>
					</div>
				</form>
				<!-- <div class="ui icon info message">
				  <i class="big help icon"></i>
				  <div class="content">
					  <h2 class="ui header">
					    	沒有帳號或忘記密碼?
					  </h2>
					  <h3>請聯絡系統開發部技術研發課同仁</h3>
				  </div>
				</div> -->
			</div>
			<div class="three wide column">
			</div>
		
		</div>
	
	</div>
	
	<script src='<c:url value="/js/application/apCommon.js" />'></script>


</body>
</html>