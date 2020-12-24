<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqCr.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/fire.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.validate.js"></script>

<style>tr td {
      padding-top:10px;
 }
 
 input[type="button"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
  
    
}
input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
  
    
}
 </style>
<body class="appear-animate">

 <!-- Head Section -->
       <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-asset-subtype.html">Fire Create</a>
                        </div>
                    </div> 
                </div>
            </div>
        </section>
        
   <div  style="height:100px;position:relative;"></div>


      <div style="height:100px;position:relative"></div>

          <div class="container" style="width:100%;">
          		 <div class="row">
             			 <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3" style=" margin-top: -47px;">
          						 <jsp:include page="/WEB-INF/views/menu.jsp" />
             			</div>
          				   <div class="col-xs-8 col-sm-8 col-lg-8 col-md-8">
				                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
				                        Create Fire Equipment
				                    </h2>
               					<div class="form-group">
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                     <c:url value="/fireEquipment/add" var="create"/>
                       
						                            <form:form class="form contact-form" id="contact_form" action="${create}" method="POST"
													 commandName="fireequipment" enctype="multipart/form-data">
																<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
																<div class="form-group">
												                    <form:input type="text" path="equipmentType" name="equp_type" id="equp_type" style="width:49%" class="input-md round form-control" 
												                    placeholder="Equipment Type" value="${et}" readonly="true"/> 
												                    <form:select id="assetSubtype" path="assetSubtype" class="input-md round form-control" style="width:49%" required="true">
												                    <form:option value="">Select Equipment Sub Type</form:option>
												                    <form:options items="${subSystems}" itemLabel="assetSubTypeName" itemValue="id" />
												                    </form:select><form:errors path="assetSubtype" class="errors"/>
						                                        </div>
						                                        <div class="form-group">
						                                            <form:input path="name" type="text" name="equp_name" id="equp_name" class="input-md round form-control" placeholder="Equipment Name" required="true"/>
						                                            <form:errors path="name" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                          <form:select id="bId"  class="input-md round form-control" path="building" required="true">
																		<form:option value="">Select Building</form:option>
																				<c:forEach items="${buildingList}" var="building">
																						<form:option value="${building.id}">${building.name}</form:option>
																				</c:forEach>
													                 </form:select>				
						                                          <form:errors path="building" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:select  path="location" id="loc" class="input-md round form-control" placeholder="Location" required="true">
						                                            <form:option value="">Select any  Location</form:option>
						                                            <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
						                                            </form:select><form:errors path="location" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="assetNo" type="text" name="assetno" id="assetno" class="input-md round form-control" placeholder="Asset No." />
						                                            <form:errors path="assetNo" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="brand" type="text" name="brand" id="brand" class="input-md round form-control" placeholder="Brand" />
						                                            <form:errors path="brand" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="serialNumber" type="text" name="serialno" id="serialno" class="input-md round form-control" placeholder="Serial No." />
						                                            <form:errors path="serialNumber" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="quantity" type="text" name="quantity" id="quantity" class="input-md round form-control" placeholder="Quantity" />
						                                            <form:errors path="quantity" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                        	<form:select path="frequency"  items="${frequencies}" class="input-md round form-control" style="width:41%">
						                                            <form:option value="">Select Servicing Frequency</form:option>    
						                                        	</form:select>
						                                            <form:errors path="frequency" class="errors"/>
						                                        </div>
						                                        
						                                   		<div class="form-group">
						                                            <form:input path="servingArea" type="text" name="servicingarea" id="servicingarea" class="input-md round form-control" placeholder="Servicing Area"/>
						                                            <form:errors path="servingArea" class="errors"/>
						                                        </div>
						                                        
						                               			<div class="form-group">
						                                            <form:input type="text" path="installationDate" id="installationdate" class="input-md round form-control" placeholder="Installation Date" style="width:90%" readonly="true"/>
						                                            <form:errors path="installationDate" class="errors"/>
						                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationdate','ddMMyyyy')"  style="cursor:pointer;width:30px" />
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <span>Condition of Asset</span><br />
						                                            <form:radiobutton path="assetCondition" value="excellent"/><a class="a">Excellent</a><br />
						                                            <form:radiobutton path="assetCondition" value="good"/><a class="a">Good</a><br />
						                                            <form:radiobutton path="assetCondition" value="average"/><a class="a">Average</a><br />
						                                            <form:radiobutton path="assetCondition" value="bad"/><a class="a">Bad</a><br />
						                                            <form:errors path="assetCondition" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                          <span>Asset needs Replacement</span><br />
						                                          <form:radiobutton path="assetReplace" value="yes"/><a class="a">Yes</a><br />
						                                          <form:radiobutton path="assetReplace" value="no"/><a class="a">No</a><br />
						                                          <form:errors path="assetReplace" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input type="text" name="expecteddateofreplacement" path="expreplacementDate" id="expecteddateofreplacement" class="input-md round form-control" placeholder="Expected date of Replacement" style="width:90%" readonly="true" />
						                                             <form:errors path="expreplacementDate" class="errors"/>
						                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('expecteddateofreplacement','ddMMyyyy')" style="cursor:pointer;width:30px" />
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input type="text" name="manufacturingDate" path="manufacturingDate" id="manufacturingdate" class="input-md round form-control" placeholder="Manufacturing Date" style="width:90%" readonly="true" />
						                                            <form:errors path="manufacturingDate" class="errors"/>
						                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('manufacturingdate','ddMMyyyy')" style="cursor:pointer;width:30px"  />
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:textarea path="remarks" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Remarks"/>
						                                            <form:errors path="remarks" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="make" type="text" name="Make" id="Make" class="input-md round form-control" placeholder="Make" />
						                                            <form:errors path="make" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="capacity" type="text" name="Capacity" id="Capacity" class="input-md round form-control" placeholder="Capacity" />
						                                            <form:errors path="capacity" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="modelNo" type="text" name="ModelNo" id="ModelNo" class="input-md round form-control" placeholder="Model No." />
						                                            <form:errors path="modelNo" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="power" type="text" name="Power" id="Power" class="input-md round form-control" placeholder="Power" />
						                                            <form:errors path="power" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="ratedFlow" type="text" name="ratedFlow" id="RatedFlow" class="input-md round form-control" placeholder="Rated Flow" />
						                                            <form:errors path="ratedFlow" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="ratedHead" type="text" name="RatedHead" id="RatedHead" class="input-md round form-control" placeholder="Rated Head" />
						                                            <form:errors path="ratedHead" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="voltage" type="text" name="Voltage" id="Voltage" class="input-md round form-control" placeholder="Voltage" />
						                                            <form:errors path="voltage" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="ampere" type="text" name="Ampere" id="Ampere" class="input-md round form-control" placeholder="Ampere" />
						                                            <form:errors path="ampere" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="workPressure" type="text" name="WorkPressure" id="WorkPressure" class="input-md round form-control" placeholder="WorkPressure" />
						                                            <form:errors path="workPressure" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <form:input path="propellent_gas" type="text" name="PropellentGas" id="PropellentGas" class="input-md round form-control" placeholder="PropellentGas" />
						                                            <form:errors path="propellent_gas" class="errors"/>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                                            <input type="file" name="drawing_Image" id="drawingimage" class="input-md round form-control" placeholder="Drawing Image" />
						                                        </div>
						                                        
						                                        <input type="hidden" id="prm" value="1" />
						                                        
						                                        <div class="form-group">
						                                        	<span>Properties</span><br />
						                                        		<table id="prop" style="border:none !important">
						                                       				 
													                      		<thead>
								                                                    <tr>
								                                                        <th>Property Name</th>
								                                                        <th>Min Value</th>
								                                                        <th>Max Value</th>
								                                                        <th>Unit</th>
								                                                        <th></th>
								                                                    </tr>
								                                                </thead>
								                                                <tbody>
								                                                    <tr>
								                                                        <td><input type="text" name="baseline[0].name" value=""/>
								            			                                      <input type="hidden" name="baseline[0].equipmentbaseline" id="baselineId" value="">
								                                                        </td>
								                                                        <td><input type="text" name="baseline[0].min"/></td>
								                                                        <td><input type="text" name="baseline[0].max"/></td>
								                                                        <td><input type="text" name="baseline[0].unit"/></td>
								                                                    </tr>
								                                                </tbody>
						                                           		</table>
						                                        </div>
						                                        
						                                        <div class="form-group">
						                              				<input type="button" id="add_prop"  value="Add Property" />
						                                		</div> 
						                                		 
						                                        <div class="form-group">
						                           	    	         <input type="submit" id="Save" value="Save" />
						                            	             <input type="submit" id="Cancel" value="Cancel" />
						                                   		 </div>
						                         </form:form>		
						                        </div>
						                       
                        </div>
                  </div>
                 
             </div>
             
      </body>
                           