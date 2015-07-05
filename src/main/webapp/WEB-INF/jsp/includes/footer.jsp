<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<div id="footerTmpl" style="margin: 0px auto;">
  <footer id="footer">
  	<p><i class="ui large ambulance icon"></i>關貿網路技術研發課製作</p>
    <p><img src="<c:url value='/images/springsource-logo.png' />" /></p>
  </footer>
</div>

<style type="text/css">
/*讓footerTmpl內容置中*/
#footerTmpl { 
  text-align: center;
  margin-left: auto;
  margin-right: auto;
}
</style>
