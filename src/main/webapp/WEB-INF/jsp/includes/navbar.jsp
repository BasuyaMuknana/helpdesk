<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<div id="navbarTmpl">
<img class="img-responsive" src="<c:url value="/images/banner-graphic.png" />" />	
  <nav class="navbar navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse"
        data-target="#navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span> <span
          class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse"
      id="navbar-collapse-1">
      <ul class="nav navbar-nav">
      	
        <li id="home">
          <a href="<s:url action='index'/>"><i class="large home icon"></i> Home</a>
        </li>
        
        
        <li id="find-owners" class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="large users icon"></i> Find Owners <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="find-owners-by-lastname">
              <a href="<s:url value='/workshop/owner_toFindByLastName'/>">Find Owners by Last Name</a>
            </li>
            <li id="find-owners-by-criteria">
              <a href="<s:url value='/workshop/owner_toFindByCriteria'/>">Find Owners by Criteria</a>
            </li>
          </ul>
        </li>
       
        
       
        <li id="veterinarians">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="large doctor icon"></i> Find Veterinarians <b class="caret"></b></a>
          <ul class="dropdown-menu">
            
            <li id="find-vets">
              <a href="<s:url value='/workshop/vet_findVetsResult'/>">Our Veterinarians</a>
            </li>
            <li id="find-vets-by-criteria">
              <a href="<s:url value='/workshop/vet_findVetsByName'/>">Find Veterinarians by Criteria</a>
            </li>
           
            
           
            <li id="edit-vet">
              <a href="<s:url value='#'/>">Edit My Vet Profile</a>
            </li>
           
            <li id="edit-all-vet">
              <a href="<s:url value='#'/>">Edit Vet(For Admin Only)</a>
            </li>
            
            
         
            <li id="add-vet">
              <a href="<s:url value='/workshop/vet_addVetPage'/>">Add a Vet</a>
            </li>
           
            <li id="delete-vet">
              <a href="<s:url value='#'/>">Delete Vet</a>
            </li>
            
          </ul>
        </li>
       
        <li id="error">
          <a href="#"><i class="large warning icon"></i> Error</a>
        </li>
        <li id="help">
          <a href="#"><i class="large help icon"></i> Help</a>
        </li>
       
        <li id="logout">
			<sec:authorize access="!isAuthenticated()">
		    <a href="<s:url value='/login_'/>">      <i class="sign in icon"></i> Sign In</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
		    <a href="<c:url value='/j_spring_security_logout'/>"><i class="sign out icon"></i> Sign Out</a>
			</sec:authorize>
        </li>
        
      </ul>
    </div> <!-- /.navbar-collapse -->
  </nav>
</div>  