    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
    <!-- Page Loader -->
    <div class="page-loader" >
        <div class="loader" style="position:absolute;">Loading${pageContext.servletContext.contextPath}/resources.</div>
    </div>
    <!-- End Page Loader -->
    <!-- Page Wrap style="background-color:#0754a4; color:#8B78E6 !important;"-->
    
 	<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="ml-2">
    	
	    <div class="navbar menu-first" style="margin-bottom:0px">
	    
	    <a class="navbar-brand">
	    	<img src="${pageContext.servletContext.contextPath}/resources/assets/images/cmms-logo.png" style="width:110px;height:35px;margin-left:10px;">
	    </a>
	        <div id="" class="outerUl right">
	            <ul class="nav navbar-nav">
	            
	                <li>
	                     <a href="${pageContext.servletContext.contextPath}/logout" class="mn-has-sub">Logout <i class="fa owl-fadeUp-out"></i></a>
	                </li>
	                <li>
	                <a>
	                	&nbsp;&nbsp; Welcome 
	                	<sec:authentication property="principal.username" />
	                	<img src="${pageContext.servletContext.contextPath}/resources/assets/images/user-avatar.png" style="height:25px;border:1px dashed #000000" />
	                </a>
	                </li>
	            </ul>
			</div>
		</div>

        <div class="navbar-header menu-toggler">
            <button button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>	
        </div>
        
       <%--  <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>
        <%-- <jsp:include page="/WEB-INF/views/admin/managementmenu.jsp" />  --%>
        
</div>
</nav>

 <div class="clearfix"></div><br /><br />

     