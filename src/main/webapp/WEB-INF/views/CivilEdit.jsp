<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/civil.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqEdit.js"></script>
<style>#prop input[type="text"] {border: 1px dotted #000000;margin-bottom: 5px;margin-left: 5px;}
   table tbody td{
     padding-top:5px;
   }
   
   input[type="reset"] {
    border: none;
    background-color: #56c759;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}

input[type="button"] {
    border: none;
    background-color: #56c759;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}

input[type="submit"] {
    border: none;
    background-color: #56c759;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}
</style>

<body class="appear-animate">
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources//assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>
                   <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-technician.html">Technician</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        
         <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Edit Civil Asset
                       
                    </h2>
                         <c:if test="${not empty success}">
                                 <div class="success">${success}</div>
						  </c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                         
                                         <c:if test="${not empty msg}">
                                         <div class="error">${msg}</div>
                                         </c:if>
                         
                        <c:url value="/civil/update" var="actionsubmit"></c:url>
                        
                            <form:form class="form contact-form" id="contact_form" action="${actionsubmit}" commandName="equipment" enctype="multipart/form-data">
                               
                               <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                               <input type="hidden" value="${equipment.id}" />
                               
                               <form:input type="hidden" path="id" />
                               
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group">
                                            <input type="text" readonly="readonly" value="Civil" id="asset_type" style="width:49%" class="input-md round form-control" placeholder="Asset Type"/>&nbsp;&nbsp;&nbsp;
                                                                       
                                            
                           		 <form:select data-bvalidator="required" path="assetSubtype" id="AssetSubtype" class="input-md round form-control" style="width:49%">
								
								<form:option value="-1">Select Asset SubType</form:option>
								<form:options items="${assetSubType}" itemLabel="assetSubTypeName" itemValue="id"/>
									
								</form:select> 
                                           <form:errors path="assetSubtype"/> 
                                        </div>
                        <div class="form-group">
                       <span>Equipment Code</span>&nbsp;<form:input path="equipmentCode" type="text"  readonly="true" value="${equipment.equipmentCode}" id="asset_type" style="width:49%" class="input-md round form-control" />&nbsp;&nbsp;&nbsp;
                                           
                        </div>
                        
                        
                        <div class="form-group">
  
                            <form:select data-bvalidator="required" path="civilLevel3" id="civil3" class="input-md round form-control">
								<form:option value="-1">Select Civil Level 3</form:option>
								<form:options items="${level3}" itemLabel="civilLevel3Name"
									itemValue="id" />
							</form:select>
							 
							 <form:errors path="civilLevel3" />        
                       </div>
                                        
                                     <div class="form-group">
                                        <form:select data-bvalidator="required" path="civilLevel4" id="civil4" class="input-md round form-control">
										<form:option value="-1">Select Civil Level 4</form:option>
										<form:options items="${level4}" itemLabel="civilLevel4Name"
										itemValue="id" />
										</form:select>
							 
							            <form:errors path="civilLevel4" /> 
							            
                                     </div>
                                      <div class="form-group">
                                      <span>Asset NO:</span>&nbsp;<form:input data-bvalidator="required" type="text" path="assetNo" id="asset_name" placeholder="Asset Name" style="width:49%" class="input-md round form-control"/>
                                       
                                      </div> 
                                        <div class="form-group">
                                      <span style="display:inline-block">Asset Name:</span>&nbsp; <form:input data-bvalidator="required" type="text" path="name" id="asset_name" placeholder="Asset Name" style="width:49%" class="input-md round form-control"/>&nbsp;&nbsp;&nbsp;
                                        <form:errors path="name" />
                                        </div>
                                       
                                        
                                        <div class="form-group">
                                           
                                            
                                             <form:select path="building" data-bvalidator="required" class="input-md round form-control" id="Building">
                                            
                                                <form:option value="-1">Select Building</form:option>
                                                <form:options items="${buildingList}" itemValue="id" itemLabel="name"/>
                                            
                                             </form:select>
                                            <form:errors path="building"/>
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                              
                                              <form:select id="Location" data-bvalidator="required"
								                path="location" items="${locations}" itemValue="id"
							                	itemLabel="name" >
							                	<form:option value="-1">Select Location</form:option>
							                	</form:select>
                                            <form:errors path="location"/>
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                        
                                     
                                       
                                        </div>
                                        
                                        <div class="form-group">
                                           
                            			<form:select path="frequency" items="${frequencies}" class="input-md round form-control">
                           				<form:option value="">Select Servicing Frequency</form:option>
										</form:select>&nbsp;&nbsp;
                                           <form:errors path="frequency"/> 
                                        </div>
                                        
                                        
                                       
                                        
                                        <div class="form-group">
                                            <form:input type="text" path="servingArea" id="servicingarea" style="width:49%" class="input-md round form-control" placeholder="Serving Area"></form:input>&nbsp;&nbsp;&nbsp;
                                            <form:errors path="servingArea"/>
                                            <form:input type="text" path="life" id="expectedlifeofyears" style="width:49%" class="input-md round form-control" placeholder="Expected Life Of Years"></form:input>
                                            <form:errors path="life"/>
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                            <form:input readonly="true" type="text" path="installationDate" id="installationdate" class="input-md round form-control" placeholder="Installation Date" style="width:90%" ></form:input>
                                           <form:errors path="installationDate"/>
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationdate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-gr	oup">
                                            <span>Condition of Asset</span><br />
                                            <form:radiobutton value="Excellent" path="assetCondition"/>&nbsp;<a class="a">Excellent</a><br />
                                            <form:radiobutton  value="Good" path="assetCondition"/>&nbsp;<a class="a">Good</a><br />
                                            <form:radiobutton  value="Average" path="assetCondition"/>&nbsp;<a class="a">Average</a><br />
                                            <form:radiobutton  value="Bad" path="assetCondition"/>&nbsp;<a class="a">Bad</a>
                                        </div>
                                        <div class="form-group">
                                            <span>Asset needs Replacement</span><br />
                                            <form:radiobutton path="assetReplace" value="Yes" name="yesno"/>&nbsp;<a class="a">Yes</a><br />
                                            <form:radiobutton path="assetReplace" value="No" name="yesno"/>&nbsp;<a class="a">No</a>
                                        </div>
                                        <div class="form-group">
                                            <form:input readonly="true" type="text" path="expreplacementDate" id="expecteddateofreplacement" class="input-md round form-control" placeholder="Expected date of Replacement" style="width:90%" ></form:input>
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('expecteddateofreplacement','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                            <form:input readonly="true" type="text" path="manufacturingDate" id="manufacturingdate" class="input-md round form-control" placeholder="Manufacturing Date" style="width:90%" ></form:input>
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('manufacturingdate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                        <form:hidden path="image" value="${equipment.image}" />
							              <br />
							             <label>&nbsp;</label>
							         <img src="${pageContext.servletContext.contextPath}/civil/getimage/${equipment.image}"
							            	style="height: 300px; width: 300px;" />
						                <br/>
                                           
                                        </div>
                                        
                                       
                                        
                                        <label class="grey" for="description">Asset:(Change	Image)</label>
							             <input type="file" name="temp_image" />
                                        
                                         <c:set var="equipmentCode"  value="${equipment.equipmentCode}"/>
											
											<c:if test="${not empty equipmentCode}">
												<img style="position: relative;top: 10px;" src="${pageContext.servletContext.contextPath}/civil/getimage/${equipment.equipmentCode}" style="height: 250px; width: 250px;" />
											</c:if>
											
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<i class="icon-print"  onClick="window.print()" style="font-size:25px;" title="Print QR"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%-- <a href="${pageContext.servletContext.contextPath}/civil/getQr/${equipment.equipmentCode}/?download=true" ><i class="icon-download"  style="font-size:25px;"  title="Download QR"></i></a> --%>
								
											
                                            &nbsp;&nbsp;&nbsp;
                                        
                                        <div class="form-group" style="position: relative;top: 19px;">
                                            <form:textarea path="remarks" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Remarks" required="required"></form:textarea>
                                        </div>
                                        <div class="form-group" style="position: relative;top: 16px;">
                                            <form:input type="text" path="drawingTitle" id="drawingtitle" class="input-md round form-control" placeholder="Drawing Title" ></form:input>
                                        </div>
                                        <div class="form-group" style="position: relative;top: 10px;">
                                            <form:input type="text" path="drawingNo" id="drawingname" class="input-md round form-control" placeholder="Drawing Number"></form:input>
                                        </div>
                                        
                                        <div class="form-group" style="position: relative;top: 10px;">
                                        <form:hidden path="drawingImage" value="${equipment.drawingImage}"/>
                                        
                                        <img src="${pageContext.servletContext.contextPath}/civil/getimage/${equipment.drawingImage}"
								        style="height: 250px; width: 250px;" />
                                          
                                        </div>
                                        
                                        <label class="grey" for="description">Asset:(Change	Drawing Image)</label>
							               <input type="file" name="temp_drawingImage"/>
                                         <input type="hidden" id="countProperties" value="${fn:length(equipment.baseline)}"/>
                                       <!-- <div class="form-group"> -->
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
                                          <c:forEach items="${equipment.baseline}" var="prop" varStatus="loop">
                                                   
                                                   
										<tr id="tr${loop.index}">
											<td><form:input type="hidden" value="${prop.id}"
													path='baseline[${loop.index}].id' /> <form:input
													type="hidden" value="${equipment.id}"
													path='baseline[${loop.index}].equipment' /> <form:input
													type='text' path='baseline[${loop.index}].name'
													data-bvalidator='required' value="${prop.name}" /></td>
											<td><form:input type='text'
													path='baseline[${loop.index}].min'
													data-bvalidator='required' value="${prop.min}" /></td>
											<td><form:input type='text'
													path='baseline[${loop.index}].max'
													data-bvalidator='required' value="${prop.max}" /></td>
											<td><form:input type='text'
													path='baseline[${loop.index}].unit'
													data-bvalidator='required' value="${prop.unit}" /></td>
											<c:if test="${loop.last}">
												<c:set var="totalRecords" value="${loop.count}" />
												<input id="countProperties" type="hidden"
													value="${totalRecords}">
											</c:if>


											<c:choose>
												<c:when test="${loop.index == '0'}">
													<td></td>
												</c:when>
												<c:otherwise>
													<td><a
														href='javascript:deleteParameter("${loop.index}")'><i
															class='icon-trash'></i></a></td>
												</c:otherwise>
											</c:choose>
											</td>
										</tr>
									</c:forEach>
                                                   
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="form-group">
                                            <input type="button" id="add_prop" name="addprop" value="Add Property" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" name="Update" value="Update" />
                                        <input type="reset" id="Cancel" name="cancel" value="Cancel" />
                                    </div>
                                    </form:form>
   </div>
   </div>
   </div>
   


        <!--End Form-->
    
</body>



