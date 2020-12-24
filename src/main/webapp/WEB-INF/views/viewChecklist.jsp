<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Head Section -->
   		<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">View</li>
			  </ol>
			</nav>
			
    <!-- End Head Section -->
    <!--Form-->
  <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                    View Checklist
                </h2>

                <form class="form contact-form anchor">
                    <div class="form-group">
                        <a>Checklist Code</a>${checklistheader.checklistName }
                    </div>
                    <div class="form-group">
                        <a>Equipment SubType</a>
                        <c:forEach items="${checklistheader.equipment}" var="equ">
                        ${equ.assetSubtype}
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <a>Equipment Brand</a>
                        <c:forEach items="${checklistheader.equipment}" var="equ">
                        ${equ.make}
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <a>Equipment Model</a>
                         <c:forEach items="${checklistheader.equipment}" var="equ">
                          ${equ.modelNo}
                        </c:forEach>
                        
                    </div>
                    <div class="form-group">
                        <a>Building</a>
                         <c:forEach items="${checklistheader.equipment}" var="equ">
                          ${equ.building.name}
                        </c:forEach>
                        
                    </div>
                    <div class="form-group">
                        <a>Location</a>
                          <c:forEach items="${checklistheader.equipment}" var="equ">
                          ${equ.location.name}
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <table class="ssh-table" id="tblResult">
                            <thead>
                                <tr>
                                    <th>S.No</th>
                                    <th>Description</th>
                                    <th>Condition</th>
                                    <th>Remarks</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${checklistheader.propertyTitles}" var="checklistproptitles">
                            <tr>
                              <td>TITLE:</td>
                              <td>${checklistproptitles.title}</td>
                            </tr>
                            <c:forEach items="${checklistproptitles.properties }" var="properties" varStatus="propertycount">
                            <tr>
                            <td>${propertycount.count}</td>
                            <c:set var="string1" value="${propertycount.description}"/>
                            <c:set var="string2" value="${fn:replace(string1, 
                                '__', '<input")}" />
                            <td>${propertycount.description}</td>
                            <td>
                            <select>
                            <option value="YES">YES</option>
                            <option value="NO">NO</option>
                            <option value="MAYBE">MAYBE</option>
                            </select>
                            </td>
                            <td>
                            <input type="text"/>
                            </td>
                            </tr>
                            
                            </c:forEach>
                             
                            
                            </c:forEach>
                            
                            </tbody>
                        </table>
                    </div>
              </form>

            </div>
        </div>
    </div>
 </div>
   