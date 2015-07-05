<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="zh-TW">
<head>
<title><decorator:title default="PDIS HELPDESK" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link href="<c:url value="/css/application.css"/>" rel="stylesheet">
<!-- Bootstrap -->
<%-- <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"> --%>
<%-- <script src="<c:url value="/js/bootstrap.min.js"/>"></script> --%>
<!-- Font Awesome -->
<%-- <link href="<c:url value="/font-awesome/css/font-awesome.min.css" />" rel="stylesheet"> --%>



<%-- jquery v1.11.1 --%>
<script src="<c:url value="/js/jquery-1.10.1.min.js"/>"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="<c:url value="/js/html5shiv.js"/>"></script>
  <script src="<c:url value="/js/respond.min.js"/>"></script>
<![endif]-->


<!-- Kendo UI -->
<link href="<c:url value="/css/kendo.common-bootstrap.min.css"/>" rel="stylesheet" />
<link href="<c:url value="/css/kendo.bootstrap.min.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/kendo.web.min.js"/>"></script>
<script src="<c:url value="/js/cultures/kendo.culture.zh-TW.min.js"/>"></script>

<%-- semantic v1.4.1 --%>
<script src='<c:url value="/semantic_ui/semantic.min.js" />'></script>
<link href='<c:url value="/semantic_ui/semantic.min.css" />' rel="stylesheet">

<!-- application -->
<script src="<c:url value="/js/application.js"/>"></script>


<decorator:head />
</head>
<body class="pushable">

  
  
	<jsp:include page="../includes/admin_top.jsp" />

  	<decorator:body />

  <%-- <div id="main" class="container">
  	
    <decorator:body />
  </div> --%>

  <%-- <jsp:include page="../includes/admin_bottom.jsp" /> --%>

</body>
</html>