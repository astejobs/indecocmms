<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<style>
        #fontSizeWrapper { font-size: 16px; }

#fontSize {
  width: 100px;
  font-size: 1em;
  }

/* ————————————————————–
  Tree core styles
*/
.tree { margin: 1em; }

.tree input {
   
  }

.tree input ~ ul { display: none; }

.tree input:checked ~ ul { display: block; }

/* ————————————————————–
  Tree rows
*/
.tree li {
  line-height: 1.2;
  position: relative;
  padding: 0 0 1em 1em; 
  list-style-type:none;  /* Added By Shahid to hide bullet symbols*/
  }

.tree ul li { padding: 1em 0 0 1em; }

.tree > li:last-child { padding-bottom: 0; }

/* ————————————————————–
  Tree labels
*/
.tree_label {
  position: relative;
  display: inline-block;
  background: #fff;
  }

label.tree_label { cursor: pointer; }

label.tree_label:hover { color: #666; }

/* ————————————————————–
  Tree expanded icon
*/
/*label.tree_label:before {
  background: #000;
  color: #fff;
  position: relative;
  z-index: 1;
  float: left;
  margin: 0 1em 0 -2em;
  width: 1em;
  height: 1em;
  border-radius: 1em;
  content: '+';
  text-align: center;
  line-height: .9em;
  }*/
 

/* ————————————————————–
  Tree branches
*/
.tree li:before {
  position: absolute;
  top: 0;
  bottom: 0;
  left: -.5em;
  display: block;
  width: 0;
  border-left: 1px solid #777;
  content: "";
  }

.tree_label:after {
  position: absolute;
  top: 0;
  left: -1.5em;
  display: block;
  height: 0.5em;
  width: 1em;
  border-bottom: 1px solid #777;
  border-left: 1px solid #777;
  border-radius: 0 0 0 .3em;
  content: '';
  }

label.tree_label:after { border-bottom: 0; }

/*:checked ~ label.tree_label:after {
  border-radius: 0 .3em 0 0;
  border-top: 1px solid #777;
  border-right: 1px solid #777;
  border-bottom: 0;
  border-left: 0;
  bottom: 0;
  top: 0.5em;
  height: auto;
  }*/

.tree li:last-child:before {
  height: 1em;
  bottom: auto;
  }

.tree > li:last-child:before { display: none; }

.tree_custom {
  display: block;
  background: #eee;
  padding: 1em;
  border-radius: 0.3em;
}
    </style>
    <script>

            $(document).ready(function () {
                $("#lsme").change(function () {
                    $('.lsme-c').prop('checked', $(this).prop('checked'));
                });
                $("#lsme-PM").change(function () {
                    $('.lsme-PM-c').prop('checked', $(this).prop('checked'));
                });
                $("#Repo").change(function () {
                    $('.Repo-c').prop('checked', $(this).prop('checked'));
                });
                $("#UserMgmt").change(function () {
                    $('.UserMgmt-c').prop('checked', $(this).prop('checked'));
                });
                $("#GrpMgmt").change(function () {
                    $('.GrpMgmt-c').prop('checked', $(this).prop('checked'));
                });
                $("#CtrlAccess").change(function () {
                    $('.CtrlAccess-c').prop('checked', $(this).prop('checked'));
                });
                
                
           	 $('#group').change(function()
        			 {
        		     
        		     $('input:checkbox').removeAttr('checked');
        		       $.ajax({
        			    url: $('#aurl').val()+$(this).val(),
        			    type: "GET",
        			    cache: false,
        			    
        			    success: function(data){
        			    	
        			    	$.each(data, function(index, element) {
        			    		
        			    		$('input[type=checkbox]').each(function () {
        			    			
        			    			if($(this).val() == element.id){
        				    			
        			    				$(this).prop("checked","true");
        				    		   
        				    		} 
        			    		   
        			    		});
        			    	
        			           });
        			      }
        			});
        		});
            });
        
        </script>
<body class="appear-animate">

  
       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Admin</a></li>
			    <li class="breadcrumb-item active" aria-current="page">User Priviledges</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                        <c:url value="/access/userpriviledges" var="url"/>
                            <form:form method="post"  action="${url}" commandName="grouppriviledges"   class="form contact-form" id="contact_form">
                                
                                 <c:url value="/access/getmodulesforgroup/" var="ajaxUrl"/>
                                        <input type="hidden" id="aurl"  value="${ajaxUrl}"/>
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            User Management 
                                        </h2>
                                        <c:if test="${not empty success}">
                                         <div class="success"> ${success}</div>
                                         </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Group</span>
	                                         <form:select  id="group"  path="userGroup"  class="input-md round form-control">
		                                         <form:option value="">Select Any Group</form:option>
		                                         <c:forEach items="${groupList}" var="groups">
		                                         <form:option value="${groups.id}">${groups.userGroupName}</form:option>
		                                         </c:forEach>
	                                          </form:select>
                                        </div>
                                    </div>
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Priviledges
                                        </h2>
                                         
                                        <ul class="tree">
                                            <li>
                                                <form:checkbox path="moduleDetail" value="1" id="lsme" />
                                                <label class="tree_label" for="c1">Computer	Maintenance Management System</label>
                                                <ul>
                                                    <li>
                                                        <form:checkbox  path="moduleDetail" class="lsme-c"  value="16"/><label for="c5">Corrective
														Maintenance</label>
														 <ul>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="24"/><label for="c5">Create Fault Report</label>
                                                                    </li>
                                                                      <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="25"/><label for="c5">Edit Fault Report</label>
                                                                    </li>
                                                                </ul>
                                                    </li>
                                                      <li>
                                                        <form:checkbox  path="moduleDetail" class="lsme-c"  value="30"/><label for="c5">Checklist Wizard
														</label>
														 <ul>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="105"/><label for="c5">Create Checklist</label>
                                                                    </li>
                                                                      <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="106"/><label for="c5">Search Checklist</label>
                                                                    </li>
                                                                </ul>
                                                    </li>
                                                    
                                                    
                                                    <li>
                                                        <form:checkbox  path="moduleDetail"  value="17"  class="lsme-c lsme-PM" />
                                                        <label for="c3" class="tree_label">Preventive Maintenance</label>
                                                        <ul>
                                                            <li>
                                                                <form:checkbox path="moduleDetail" value="26" class="lsme-c lsme-PM" id="c4" />
                                                                <label for="c4" class="tree_label ">PM Schedule</label>
                                                                <ul>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="28"/><label for="c5">Create PM Schedule</label>
                                                                    </li>
                                                                     <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="29"/><label for="c5">Search PM Schedule</label>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <form:checkbox   path="moduleDetail" value="27"  class="lsme-c lsme-PM" id="c6" />
                                                                <label for="c4" class="tree_label lsme-c">PM Task</label>
                                                                <ul>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c lsme-PM" value="31"/><label>Update Modify PM Task</label>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <form:checkbox   path="moduleDetail" value="18" id="c8" class="lsme-c Repo" />
                                                        <label for="c8" class="tree_label">Reports</label>
                                                        <ul>
                                                            <li>
                                                                 <form:checkbox  path="moduleDetail" value="32" class="lsme-c Repo-c" id="c9" />
                                                                <label for="c9" class="tree_label">Standard Report</label>
                                                                <ul>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" class="lsme-c Repo-c" value="34"/><label for="c9">FR Fault History</label>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                 <form:checkbox path="moduleDetail" value="33"  class="lsme-c Repo-c" id="c10" />
                                                                <label for="c10" class="tree_label">Adhoc Report</label>
                                                                <ul>
                                                                    <li>
                                                                         <form:checkbox   path="moduleDetail" class="lsme-c Repo-c" value="36" /><label>Create Adhoc Report</label>
                                                                    </li>
                                                                    <li>
                                                                         <form:checkbox   path="moduleDetail" class="lsme-c Repo-c" value="38" /> <label>View Adhoc Reports</label>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <form:checkbox   path="moduleDetail" value="19"  id="c11" class="lsme-c" />
                                                        <label for="c11" class="tree_label">lsme-configurator</label>
                                                        <ul>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c"  value="39" /><label>Building</label>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="40" /><label>Location</label>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="41" /><label>Department</label>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="42" /><label>Maintenance Group</label></li>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="43" /><label>Priority</label></li>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="44" /><label>Fault Category</label></li>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="45" /><label>Unit of Measure</label></li> --%>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="46" /><label>Cost Center </label></li>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="47" /><label>Equipment Type</label></li>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="48" /><label>Manufacturer</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="49" /><label>Warehouse</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="50" /><label>Part Type</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="51" /><label>Vendor</label></li> --%>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="87" /><label>System</label></li>
                                                            <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="88" /><label>Sub System</label></li>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="89"/><label>Technician</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="90" /><label>Email</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="91" /><label>Meter</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="92" /><label>Exception Date</label></li> --%>
                                                        </ul>
                                                    </li>
                                                    
                                                    
                                                    
                                                    
                                                    
<!--                                                     <li> -->
<%--                                                        <form:checkbox path="moduleDetail" value="93"  id="c12" class="lsme-c" /> --%>
<!--                                                         <label for="c12" class="tree_label">Utility Readings</label> -->
<!--                                                         <ul> -->
<!--                                                             <li> -->
<%--                                                                 <form:checkbox path="moduleDetail" class="lsme-c" value="94" /><label>Search Utility Reading</label> --%>
<!--                                                             </li> -->
<!--                                                             <li> -->
<%--                                                                 <form:checkbox path="moduleDetail" class="lsme-c" value="95" /><label>Utility Reading</label> --%>
<!--                                                             </li> -->
<!--                                                         </ul> -->
<!--                                                     </li> -->



 													<li> 
                                                        <form:checkbox   path="moduleDetail" value="20"  id="c13" class="lsme-c" /> 
                                                         <label for="c13" class="tree_label">Asset Management</label> 
                                                         <ul> 
                                                             <li> 
                                                                <form:checkbox   path="moduleDetail" value="97" class="lsme-c" id="c14" /> 
                                                                 <label for="c14" class="tree_label">Equipment</label> 
                                                                 <ul> 
                                                                     <li> 
                                                                        <form:checkbox path="moduleDetail" class="lsme-c" value="97" /><label>Create</label> 
                                                                     </li> 
                                                                     <li> 
                                                                         <form:checkbox path="moduleDetail" class="lsme-c" value="97" /><label>View/Edit</label> 
                                                                     </li> 
                                                                    
                                                                    
                                                                    
                                                                   
                                                                    
                                                                     <li> 
                                                                         <form:checkbox   path="moduleDetail" value="97" class="lsme-c" id="c20" /> 
                                                                         <label for="c20" class="tree_label">Equipment Template</label> 
                                                                         <ul> 
                                                                            <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="97" /><label>Create</label></li> 
                                                                          
                                                                         </ul> 
                                                                     </li> 
                                                                 </ul> 
                                                             </li> 
                                                         </ul> 
                                                     </li> 















<!--                                                     <li> -->
<%--                                                         <form:checkbox   path="moduleDetail" value="20"  id="c13" class="lsme-c" /> --%>
<!--                                                         <label for="c13" class="tree_label">Asset lsme-curation</label> -->
<!--                                                         <ul> -->
<!--                                                             <li> -->
<%--                                                                 <form:checkbox   path="moduleDetail" value="52" class="lsme-c" id="c14" /> --%>
<!--                                                                 <label for="c14" class="tree_label">Equipment</label> -->
<!--                                                                 <ul> -->
<!--                                                                     <li> -->
<%--                                                                         <form:checkbox path="moduleDetail" class="lsme-c" value="97" /><label>ACMV</label> --%>
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                          <form:checkbox path="moduleDetail" class="lsme-c" value="98" /><label>Communication System</label> --%>
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                         <form:checkbox  path="moduleDetail" value="53" class="lsme-c" id="c15" /> --%>
<!--                                                                         <label for="c15" class="tree_label">Mechanical</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="59" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="60" /><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                         <form:checkbox path="moduleDetail" value="54" class="lsme-c" id="c16" /> --%>
<!--                                                                         <label for="c16" class="tree_label">Fire</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox  class="lsme-c"  path="moduleDetail" value="63" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox  class="lsme-c"  path="moduleDetail" value="64" /><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                          <form:checkbox path="moduleDetail" value="55" class="lsme-c" id="c17" /> --%>
<!--                                                                         <label for="c17" class="tree_label">Lift</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox class="lsme-c"   path="moduleDetail" value="65" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="66" /><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                         <form:checkbox path="moduleDetail" value="56" class="lsme-c" id="c18" /> --%>
<!--                                                                         <label for="c18" class="tree_label">Electrical</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="67" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox class="lsme-c" path="moduleDetail" value="68" /><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                         <form:checkbox   path="moduleDetail" value="57" class="lsme-c" id="c19" /> --%>
<!--                                                                         <label for="c19" class="tree_label">Instrumentation</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox  class="lsme-c" path="moduleDetail" value="69" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="70" /><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                     <li> -->
<%--                                                                          <form:checkbox   path="moduleDetail" value="58" class="lsme-c" id="c20" /> --%>
<!--                                                                         <label for="c20" class="tree_label">Control & Automation</label> -->
<!--                                                                         <ul> -->
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="61" /><label>Create</label></li> --%>
<%--                                                                             <li> <form:checkbox class="lsme-c"  path="moduleDetail" value="62"/><label>Edit</label></li> --%>
<!--                                                                         </ul> -->
<!--                                                                     </li> -->
<!--                                                                 </ul> -->
<!--                                                             </li> -->
<!--                                                         </ul> -->
<!--                                                     </li> -->
<!--                                                     <li> -->
<%--                                                          <form:checkbox   path="moduleDetail" value="21"  id="c21" class="lsme-c" /> --%>
<!--                                                         <label for="c21" class="tree_label">Inventory</label> -->
<!--                                                         <ul> -->
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="71" /><label>Create Part</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="72" /><label>Issue Part</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="73" /><label>Receive Part</label></li> --%>
<%--                                                             <li> <form:checkbox   path="moduleDetail" class="lsme-c" value="96" /><label>Transfer Part</label></li> --%>
<!--                                                         </ul> -->
<!--                                                     </li> -->
<!--                                                     <li> -->
<%--                                                          <form:checkbox   path="moduleDetail" value="104" id="c21" class="lsme-c" /> --%>
<!--                                                         <label for="c21" class="tree_label">Quotation</label> -->
                                                       
<!--                                                     </li> -->
<!--                                                     <li> -->
<%--                                                          <form:checkbox   path="moduleDetail" value="100" id="c21" class="lsme-c" /> --%>
<!--                                                         <label for="c21" class="tree_label">FeedBack And Complaints</label> -->
                                                       
<!--                                                     </li> -->
                                                    <li>
                                                        <form:checkbox path="moduleDetail" value="22" id="c22" class="lsme-c" />
                                                        <label for="c23" class="tree_label">Contract Management</label>
                                                        <ul>
                                                            <li><form:checkbox path="moduleDetail" class="lsme-c" value="74" /><label>Create Contract Site</label></li>
                                                            <li><form:checkbox path="moduleDetail" class="lsme-c" value="101" /><label>Create Contract</label></li>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="102" /><label>Create SOR</label></li> --%>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="103" /><label>Labor rates</label></li> --%>
                                                        </ul>
                                                    </li>
<!--                                                     <li> -->
<%--                                                        <form:checkbox path="moduleDetail" value="105" id="c23" class="lsme-c" /> --%>
<!--                                                         <label for="c23" class="tree_label">Request For Approval</label> -->
<!--                                                         <ul> -->
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="106" /><label>Requestor</label></li> --%>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="107" /><label>View Requestor Requests</label></li> --%>
<%--                                                              <li><form:checkbox path="moduleDetail" class="lsme-c" value="115" /><label>Site Approver</label></li> --%>
<%--                                                              <li><form:checkbox path="moduleDetail" class="lsme-c" value="114" /><label>Cost Center</label></li> --%>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="108" /><label>Approver</label></li> --%>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="109" /><label>Finance Officer</label></li> --%>
<%--                                                             <li><form:checkbox path="moduleDetail" class="lsme-c" value="110" /><label>Administrator</label></li> --%>
<!--                                                         </ul> -->
<!--                                                     </li> -->
                                                </ul>
                                            </li>
                                            <li><form:checkbox path="moduleDetail" value="2" /><label>Document Management System</label></li>
                                            <li><form:checkbox path="moduleDetail" value="3" /><label>Equipment Maintenance System</label></li>
                                            <li><form:checkbox path="moduleDetail" value="4" /><label>Humman Resource Management System</label></li>
                                            <li><form:checkbox path="moduleDetail" value="5" /><label>Finance Management</label></li>
                                            <li><form:checkbox path="moduleDetail" value="6" /><label>Access Control</label></li>
                                            <li><form:checkbox path="moduleDetail" value="7"/><label>Asset & Property Management</label></li>
                                            <li><form:checkbox path="moduleDetail" value="8" /><label>e-Procurement</label></li>
                                            <li><form:checkbox path="moduleDetail" value="9" /><label>Contracts & Projects Management</label></li>
                                            <li><form:checkbox path="moduleDetail" value="10" /><label>Construction Management</label></li>
                                            <li><form:checkbox path="moduleDetail" value="11" /><label>Customer Relationship Management</label></li>
                                            <li><form:checkbox path="moduleDetail" value="12"/><label>Business Intelligence Analytics</label></li>
                                            <li><form:checkbox path="moduleDetail" value="13" /><label>Wrokflow Management System</label></li>
                                            <li><form:checkbox path="moduleDetail" value="14"/><label>Data Acquisition Engine</label></li>
                                            <li>
                                               <form:checkbox path="moduleDetail" value="15"  id="ctan" />
                                                <label class="tree_label" for="ctan" >Administration</label>
                                                     <li><input type="checkbox"  id="UserMgmt"/>
                                                               <label for="ccos" class="tree_label">User Management</label>
                                                                        <%-- <ul>
                                                                            <li> <form:checkbox   path="moduleDetail" class="UserMgmt-c" value="78" /><label>Create User</label></li>
                                                                            <li> <form:checkbox   path="moduleDetail" class="UserMgmt-c" value="79" /><label>Modify User</label></li>
                                                                        </ul> --%>
                                                                    </li>
                                                                    <li>
                                                                        <form:checkbox path="moduleDetail" value="76" id="GrpMgmt" />
                                                                        <label for="c18" class="tree_label">Group
													                     Management</label>
                                                                        <ul>
                                                                            <%-- <li> <form:checkbox   path="moduleDetail" class="GrpMgmt-c" value="80" /><label>Create Group</label></li>
                                                                            <li> <form:checkbox   path="moduleDetail" class="GrpMgmt-c" value="81" /><label>Modify Group</label></li> --%>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <form:checkbox   path="moduleDetail" value="77"  id="CtrlAccess" />
                                                                        <label for="cyang" class="tree_label">Access
												                                               	Control</label>
                                                                        <ul>
                                                                           <%--  <li> <form:checkbox   path="moduleDetail" class="CtrlAccess-c" value="82" /><label>User
															                                                                  Priviledges</label></li>
                                                                            <li> <form:checkbox   path="moduleDetail" class="CtrlAccess-c" value="83" /><label>Change
															                         Password</label></li> --%>
                                                                        </ul>
                                                                    </li>
                                                                 </li>
                                                          </ul>
                                    </div>
                                    <div class="form-group float-right">
                                        <input type="submit" id="submit" class="btn" value="Add" />
                                    </div>
     </form:form>
     </div>
     </div>
     </div>
     </div>

</body>
</html>
