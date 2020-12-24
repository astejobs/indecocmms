 <%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div id="navbar" class="collapse navbar-collapse navbar-responsive-collapse outerUl">
            <ul class="nav navbar-nav">
            
            	<li class="dropdown innerUl">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Group Management <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/groupmanagement/addgroup">Create Group</a>
                        </li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/groupmanagement/modify">Modify Group</a>
                        </li>
                     </ul>
                </li> 
                 
                <li class="dropdown innerUl">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Management <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1"> 
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/usermanagement/adduser">Create User</a>
                        </li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/usermanagement/modify">Modify User</a>
                        </li>
                     </ul>
                </li> 
                 <li class="dropdown innerUl">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Access Control  <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1"> 
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/access/userpriviledges">User Priviledges</a>
                        </li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/access/siteaccess">Site Access</a>
                        </li>
                         <li>
                            <a href="${pageContext.servletContext.contextPath}/access/changepassword">Change  Password</a>
                        </li>
                     </ul>
                </li> 
                
            </ul>
		</div>
 <%-- <ul class="mtree transit col-md-12">
            <li>
                <a href="#" class="active">Group Management</a>
                <ul>
                    <li><a href="${pageContext.servletContext.contextPath}/groupmanagement/addgroup">Create Group</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/groupmanagement/modify">Modify Group</a></li>
                    
                </ul>
            </li>
            <li>
                <a href="#" class="active">User Management</a>
                <ul>
                    <li><a href="${pageContext.servletContext.contextPath}/usermanagement/adduser">Create User</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/usermanagement/modify">Modify User</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Access Control</a>
                <ul>
                    <li><a href="${pageContext.servletContext.contextPath}/access/userpriviledges">User Priviledges</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/access/siteaccess">Site Access</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/access/changepassword">Change Password</a></li>
                </ul>
            </li>
<!--             <li> -->
<!--                 <a href="#">Inventory</a> -->
<!--                 <ul> -->
<!--                     <li><a href="">Create Part User</a></li> -->
<!--                     <li><a href="">Modify User</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            <br />
       </ul> --%>
         <script>
            $(document).ready({
                    if (digiCam) {
                        var cXar = 25;
                        if (cXar === 26) {
                            $('txtResult').show();
                        } else {
                            $('txtResult').hide();
                        }
                    }
                })
        </script>