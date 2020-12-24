<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/mechanical.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqEdit.js"></script>


<script>
       $(document).ready(function () {
           $(function () {
                $("#prop").pagination({
                     items: 100,
                     itemsOnPage: 10,
                     cssStyle: 'light-theme'
                });
           });
       });-
</script>
 <style> #prop input[type="text"] { border: 1px dotted #000000;margin-bottom: 5px;margin-left: 5px;}</style> 
<body class="appear-animate">

       <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="../assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>
-
                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/search-fault-report.html">Search Fault Report</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        
      <div  style="height:100px;position:relative;"></div>


      <div style="height:100px;position:relative"></div>

          <div class="container" style="width:100%;">
           <div class="row">
              <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3" style="
    margin-top: -47px;
">
           <jsp:include page="/WEB-INF/views/menu.jsp" />
             </div>
             <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Edit Mechanical Equipment
                   </h2>
                       <c:url value="/mechanical/update" var="submit"/>
                        <c:set var="caption" value="Update"></c:set>
                          <div class="form-group">
                            <c:if test="${not empty success}">
                               <div class="success">${success}</div>
            				</c:if>
			        		<c:if test="${not empty fail}">
                                <div class="error">${fail}</div>
                            </c:if>
                      <form:form action="${submit}" method="post" class="form contact-form" id="contact_form"  modelAttribute="mechanicalEquipment" enctype="multipart/form-data" >
                                 <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                                        <div class="form-group">
	                                        <form:input path="id" type="hidden"/>
	                                        <form:input path="image" type="hidden"/>
	                                        <form:input path="drawingImage" type="hidden"/>
	                                        <form:input type="text" path="equipmentType"  id="equp_type" style="width:49%" class="input-md round form-control" placeholder="Equipment Type" disabled="true" />&nbsp;&nbsp;&nbsp;
	                                        <form:input type="text" path="equipmentCode"  id="equp_code" style="width:49%" class="input-md round form-control" readonly="true1"	/>&nbsp;&nbsp;&nbsp;
                                        <div>
                                        <div class="form-group">
	                                        <c:set var="codeImage"  value="${mechanicalEquipment.equipmentCode}"/>
	                                        <c:if test="${not empty codeImage}">
	                                            <img
												src="${pageContext.servletContext.contextPath}/mechanical/getimage/${codeImage}"
												style="height: 250px; width: 250px;" />
										   </c:if>
						                   </div>
	                                       <form:select  class="input-md round form-control" path="assetSubtype">
	                                      	<form:option value="" >Select Equipment Sub Type</form:option>
							          		<form:options items="${subSystems}" itemLabel="assetSubTypeName" itemValue="id" />
									
											</form:select>	
											<form:errors path="assetSubtype"></form:errors>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="name" id="equp_name" class="input-md round form-control" placeholder="Equipment Name" />
                                        </div>
                                        <div class="form-group">
                                            <form:select id="building"  class="input-md round form-control" path="building">
											<form:option value="">Select Building</form:option>
											<c:forEach items="${buildingList}" var="building">
												<form:option value="${building.id}">${building.name}</form:option>
											</c:forEach>
											</form:select>
											<form:errors path="building"></form:errors>
							            </div>
                                        <div class="form-group">
                                           <form:select  path="location" id="location" class="input-md round form-control" placeholder="Location">
                                              <form:option value="">Select any  Location</form:option>
                                               <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
							           </div>
                                       <div class="form-group">
                                            <form:input type="text" path="assetNo" id="assetno" class="input-md round form-control" placeholder="Asset No." />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="brand" id="brand" class="input-md round form-control" placeholder="Brand" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="serialNo" id="serialno" class="input-md round form-control" placeholder="Serial No." />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="quantity" id="quantity" class="input-md round form-control" placeholder="Quantity" />
                                        </div>
                                        <div class="form-group">
                                       
                                         <form:select class="input-md round form-control" path="frequency"
										items="${frequencies}">
									</form:select>
                                        
                                           
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="servingArea" id="servicingarea" class="input-md round form-control" placeholder="Servicing Area"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" readonly="true" path="installationDate" id="installationdate" class="input-md round form-control" placeholder="Installation Date" style="width:90%" />
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationdate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                            <span>Condition of Asset</span><br />
                                            
                                             
                                           
                                           
                                           
                                            <form:radiobutton path="assetCondition" value="Excellent" />&nbsp;<a class="a">Excellent</a><br />
                                            <form:radiobutton  path="assetCondition" value="good" />&nbsp;<a class="a">Good</a><br />
                                            <form:radiobutton  path="assetCondition" value="average" />&nbsp;<a class="a">Average</a><br />
                                            <form:radiobutton path="assetCondition" value="bad" />&nbsp;<a class="a">Bad</a>
                                        </div>
                                        <div class="form-group">
                                            <span>Asset needs Replacement</span><br />
                                            <form:radiobutton path="assetReplace" value="yes" />&nbsp;<a class="a">Yes</a><br />
                                            <form:radiobutton  path="assetReplace" value="no" />&nbsp;<a class="a">No</a>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" readonly="true" name="expecteddateofreplacement" path="expreplacementDate" id="expecteddateofreplacement" class="input-md round form-control" placeholder="Expected date of Replacement" style="width:90%" />
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('expecteddateofreplacement','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" readonly="true" name="manufacturingdate" path="manufacturingDate" id="manufacturingdate" class="input-md round form-control" placeholder="Manufacturing Date" style="width:90%" />
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('manufacturingdate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" name="expectedlifeofyears" path="life" id="expectedlifeofyears" class="input-md round form-control" placeholder="Expected life of years" />
                                        </div>
                                        <div class="form-group">
                                            <form:textarea required="required" class="input-md round form-control col-lg-3" style="margin-bottom:10px" path="remarks" placeholder="Remarks" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="modelNo" name="ModelNo" id="ModelNo" class="input-md round form-control" placeholder="Model No." />
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:input type="text" path="ratedHead" name="RatedHead" id="RatedHead" class="input-md round form-control" placeholder="Rated Head" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" name="Voltage" path="voltage" id="Voltage" class="input-md round form-control" placeholder="Voltage" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="power" name="Power" id="Power" class="input-md round form-control" placeholder="Power" />
                                        </div>

                                        <div class="form-group">
                                             <input type="file" name="equipimage" id="equipimage" class="input-md round form-control" placeholder="Equipment Image" />
                                     
                                        </div>
                                        
                                         <div>
                                        <c:set var="eqpimage"  value="${mechanicalEquipment.image}"/>
                                        <c:if test="${not empty eqpimage}">
                                            <img
											src="${pageContext.servletContext.contextPath}/mechanical/getimage/${mechanicalEquipment.image}"
											style="height: 250px; width: 250px;" /></c:if>
										
                                        
                                        
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="drawingTitle" name="drawingtitle" id="drawingtitle" class="input-md round form-control" placeholder="Drawing Title" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="drawingNo" name="drawingname" id="drawingname" class="input-md round form-control" placeholder="Drawing Name" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <input type="file"  name="drawingimage" id="drawingimage" class="input-md round form-control" placeholder="Drawing Image" />
                                        
                                        </div> 
                                        <div>
                                        <c:set var="drawimage"  value="${mechanicalEquipment.drawingImage}"/>
                                        <c:if test="${not empty drawimage}">
                                            <img
											src="${pageContext.servletContext.contextPath}/mechanical/getimage/${mechanicalEquipment.drawingImage}"
											style="height: 250px; width: 250px;" /></c:if>
										
                                        
                                        
                                        </div>
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
                                                <input type="hidden" value="${fn:length(mechanicalEquipment.baseline)}"  name="prm"/>
                                                
                                                 <c:forEach items="${mechanicalEquipment.baseline}" var="prop" varStatus="loop">
                                                   
                                                
                                                      <tr id="tr${loop.index}">
                                                      
                                                      <td><form:input type="hidden" value="${prop.id}"
								                          path='baseline[${loop.index}].id' />
								                          <form:input
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
								                      
								                          <td><a href='javascript:deleteParameter("${loop.index}")'><i class='icon-trash'></i></a></td>
								                      </tr>
                                                    
                                            	</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="form-group">
                                              <input type="button" id="add_prop"  value="Add Property" />
                                        </div>  
                                        <div class="form-group">
                                        <input type="submit" id="Save" value="${caption }" />
                                        <input type="submit" id="Cancel" value="Cancel" />
        						</div>
        					</div>
        				</form:form>
        			</div>
        		</div>
        	</div>
        </div>
 </body>
</html>