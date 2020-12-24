<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:forEach
		items="${sessionScope.modules}"
		var="access">
		<%-- <c:out value="${access.id}"/> --%>
		<c:choose>
		    <c:when test="${access.id == '24'}">
				<c:set var="flagcfr" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '25'}">
				<c:set var="flagefr" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '26'}">
				<c:set var="flagPms" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '28'}">
				<c:set var="flagPmsc" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '29'}">
				<c:set var="flagPmsm" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '16'}">
				<c:set var="flagcm" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '30'}">
				<c:set var="flagcl" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '27'}">
				<c:set var="flagpmtask" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '17'}">
				<c:set var="flagpm" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '18'}">
				<c:set var="flagrep" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '32'}">
				<c:set var="flagstrep" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '33'}">
				<c:set var="flagadrep" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '19'}">
				<c:set var="flagconfig" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '20'}">
				<c:set var="flagasset" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '21'}">
				<c:set var="flaginven" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '22'}">
				<c:set var="flagworksite" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '56'}">
				<c:set var="flagelect" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '53'}">
				<c:set var="flagmech" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '57'}">
				<c:set var="flaginstr" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '55'}">
				<c:set var="flaglift" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '58'}">
				<c:set var="flagcntrlaut" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '54'}">
				<c:set var="flagfire" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '39'}">
				<c:set var="flagbuild" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '40'}">
				<c:set var="flagloc" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '41'}">
				<c:set var="flagdep" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '42'}">
				<c:set var="flagmaint" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '43'}">
				<c:set var="flagpri" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '44'}">
				<c:set var="flagfault" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '45'}">
				<c:set var="flagunit" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '46'}">
				<c:set var="flagcost" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '47'}">
				<c:set var="flagequip" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '48'}">
				<c:set var="flagmanufac" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '49'}">
				<c:set var="flagwarehouse" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '50'}">
				<c:set var="flagpartype" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '51'}">
				<c:set var="flagvendor" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '87'}">
				<c:set var="flagsys" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '88'}">
				<c:set var="flagsubsys" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '89'}">
				<c:set var="flagtechnician" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '90'}">
				<c:set var="flagemail" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '92'}">
				<c:set var="flagexdate" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '91'}">
				<c:set var="flagmeter" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '93'}">
				<c:set var="flagutilityreadingheader" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '94'}">
				<c:set var="flagsearchutilityreading" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '95'}">
				<c:set var="flagutilityreading" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '97'}">
				<c:set var="flagacmv" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '98'}">
				<c:set var="flagcommsystem" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '100'}">
				<c:set var="flagfeedback" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '101'}">
				<c:set var="createworksite" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '102'}">
				<c:set var="createsor" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '103'}">
				<c:set var="laborrates" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '104'}">
				<c:set var="flagqotation" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '105'}">
				<c:set var="flagcreatechklist" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '106'}">
				<c:set var="flageditchklist" value="trrue" scope="page" />
			</c:when>
			
			
			<c:when test="${access.id == '112'}">
				<c:set var="flagrfa" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '107'}">
				<c:set var="flagrequestor" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '108'}">
				<c:set var="flagviewrequestor" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '109'}">
				<c:set var="flagapprover" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '110'}">
				<c:set var="flagfinance" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '111'}">
				<c:set var="flagadmin" value="trrue" scope="page" />
			</c:when>
			
			<c:when test="${access.id == '113'}">
				<c:set var="flagcivil" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '114'}">
				<c:set var="flagcc" value="trrue" scope="page" />
			</c:when>
			<c:when test="${access.id == '115'}">
				<c:set var="flagsa" value="trrue" scope="page" />
			</c:when>
			
			
		</c:choose>
	</c:forEach>
	

<div id="navbar" class="collapse navbar-collapse navbar-responsive-collapse outerUl">
            <ul class="nav navbar-nav">
            
              <c:if test="${flagcm == 'trrue'}">
            
            <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Corrective Maintenance <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                     <c:if test="${flagcfr == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/faultReport/add">Create Fault Report</a>
                        </li>
                        </c:if>
                          <c:if test="${flagefr == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/faultReport/search">Edit / Search Fault Report</a>
                        </li>
                        </c:if>
                     </ul>
                </li> 
                 </c:if>
                 
                   <c:if test="${flagcl == 'trrue'}">  
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Checklist Wizard <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                     <c:if test="${flagcreatechklist == 'trrue'}">  
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/checklist/addchecklist">Create Checklist</a>
                        </li>
                        </c:if>
                            <c:if test="${flageditchklist == 'trrue'}">  
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/checklist/search">Search Checklist</a>
                        </li>
                        </c:if>
                     </ul>
                </li> 
                </c:if>
                
                     <c:if test="${flagpm == 'trrue'}">
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Preventive Maintenance <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                      <c:if test="${flagPms == 'trrue'}">
                        <li class="dropdown-submenu">
                            <a href="#" class="dropdown-toggle level2-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">PM Schedule<i class="fa fa-angle-right"></i></a>
		                    <ul class="dropdown-menu level2">
		                    <c:if test="${flagPmsc == 'trrue'}">
		                        <li><a href="${pageContext.servletContext.contextPath}/schedule/create">Create PM Schedule</a></li>
		                        </c:if>
		                         <c:if test="${flagPmsc == 'trrue'}">
		                        <li><a href="${pageContext.servletContext.contextPath}/schedule/review">Review PM Schedule</a></li>
		                        </c:if>
		                          <c:if test="${flagPmsc == 'trrue'}">
		                        <li><a href="${pageContext.servletContext.contextPath}/schedule/admin">Approve PM Schedule</a></li>
		                        </c:if>
		                          <c:if test="${flagPmsm == 'trrue'}">
		                        <li><a href="${pageContext.servletContext.contextPath}/schedule/search">Search PM Schedule</a></li>
		                        </c:if>
		                    </ul>
                        </li>
                        </c:if>
                           <c:if test="${flagpmtask == 'trrue'}">
                        <li class="dropdown-submenu">
		                    <a href="#" class="dropdown-toggle level2-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">PM Task<i class="fa fa-angle-right"></i></a>
		                    <ul class="dropdown-menu level2">
		                        <li><a href="${pageContext.servletContext.contextPath}/task/search">Update/Modify/PM Task</a></li>
		                    </ul>
		                </li>
		                </c:if>
                     </ul>
                </li> 
                </c:if>
                    <c:if test="${flagrep == 'trrue'}">
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reports <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                       <c:if test="${flagstrep == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/standardReport"><i class="fa fa-link fa-sm"></i> Standard Report</a>
                        </li>
                        </c:if>
                          <c:if test="${flagadrep == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/selfHelp/AdhocReports"><i class="fa fa-link fa-sm"></i> Self Service Report</a>
                        </li>
                        </c:if>
                     </ul>
                </li> 
                </c:if>
                   <c:if test="${flagconfig == 'trrue'}">
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Configurator <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                       <c:if test="${flagbuild == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/building"><i class="fa fa-link fa-sm"></i> Building</a>
                        </li>
                        </c:if>
                         <c:if test="${flagloc == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/location"><i class="fa fa-link fa-sm"></i> Location</a>
                        </li>
                        
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/division"><i class="fa fa-link fa-sm"></i> Division</a>
                        </li>
                        </c:if>
                            <c:if test="${flagdep == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/department"><i class="fa fa-link fa-sm"></i> Department</a>
                        </li>
                        </c:if>
                         <c:if test="${flagmaint == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/maintainenceGrp"><i class="fa fa-link fa-sm"></i> Maintenance Group</a>
                        </li>
                        </c:if>
                        
                            <c:if test="${flagpri == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/priorty"><i class="fa fa-link fa-sm"></i> Priority</a>
                        </li>
                        </c:if>
                         <c:if test="${flagfault == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/faultCategory"><i class="fa fa-link fa-sm"></i> Fault Category</a>
                        </li>
                        </c:if>
                           <c:if test="${flagcost == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/costCenter"><i class="fa fa-link fa-sm"></i> Cost Center</a>
                        </li>
                        </c:if>
                         <c:if test="${flagsys == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/assetType"><i class="fa fa-link fa-sm"></i> Asset Type</a>
                        </li>
                        </c:if>
                          <c:if test="${flagsubsys == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/assetSubtype"><i class="fa fa-link fa-sm"></i> Asset Subtype</a>
                        </li>
                        </c:if>
                     </ul>
                </li> 
                </c:if>
                 <c:if test="${flagasset == 'trrue'}">
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Asset Management <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                        <c:if test="${ flagacmv == 'trrue'}">
                        <li class="dropdown-submenu">
		                    <a href="#" class="dropdown-toggle level2-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Equipment<i class="fa fa-angle-right"></i></a>
		                    <ul class="dropdown-menu level2">
		                        <li><a href="${pageContext.servletContext.contextPath}/equipment/add">Create</a></li>
		                        <li><a href="${pageContext.servletContext.contextPath}/equipment/search">View / Edit</a></li>
		                    </ul>
		                </li>
		                </c:if>
		                   <c:if test="${ flagacmv == 'trrue'}">
		                <li class="dropdown-submenu">
		                    <a href="#" class="dropdown-toggle level2-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Equipment Template<i class="fa fa-angle-right"></i></a>
		                    <ul class="dropdown-menu level2">
		                        <li><a href="${pageContext.servletContext.contextPath}/addproperty/add">Create</a></li>
		                    </ul>
		                </li>
		                </c:if>
                     </ul>
                </li> 
                </c:if>
                     <c:if test="${flagworksite == 'trrue'}">
                <li class="dropdown innerUl">
                    <a href="javascript:void(0)" style="position:relative;top:-5px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contracts Management <i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu level1">
                     <c:if test="${createworksite == 'trrue'}">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/contract/add"><i class="fa fa-link fa-sm"></i> Create Contract</a>
                        </li>
                        </c:if>
                     </ul>
                </li> 
                </c:if>
            </ul>
		</div>
        