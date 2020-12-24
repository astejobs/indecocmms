<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/electrical.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqEdit.js"></script>


<body>
<section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
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
		<div style="height:100px;position:relative"></div>
			 <div class="container" style="width:100%;">
			    <div class="row">
			        <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
				  		   <jsp:include page="/WEB-INF/views/menu.jsp" />
				     </div>
			   			 <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
					  				<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
					                       Edit Electrical Equipment
					      			</h2>                 
					 				<c:url value="/electrical/update" var="update"/>
							        <c:if test="${not empty success}">
					                       <div class="success">${success}</div>
					              	</c:if>
									<c:if test="${not empty fail}">
					                       <div class="error">${fail}</div>
					             	</c:if>
						         	<form:form action="${update}" class="form contact-form" commandName="electrical" method="POST"  enctype="multipart/form-data">
						 	            <input type="hidden" name="id" value="${electrical.id}"/> 
						        		<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
						        		<div class="form-group">
						                <form:input type="text" path="equipmentType" value="Electrical" readonly="true" class="input-md round form-control" placeholder="Equipment Type"/>
						                </div>
						               <div class="form-group"> 
							                <form:select class="input-md round form-control" path="assetSubtype">
							                <form:option value="">Select Electrical Sub Type</form:option>
							                <c:forEach items="${subTypeList}" var="subTypeList">
							                	<form:option value="${subTypeList.id}">${subTypeList.assetSubTypeName}</form:option>
											</c:forEach>
							                </form:select>
							                <form:errors path="assetSubtype" class="errors"/> 
						                </div>
						                <div class="form-group">
						                   <form:input  type="text"  path="name"  class="input-md round form-control" placeholder="Equipment Name" />
						                </div>
						                <div class="form-group">
						                   <form:select class="input-md round form-control" path="building" id="bId" >
						                   <form:option value="">Select Building</form:option>
						                   <c:forEach items="${buildingList}" var="building">
						                   <form:option value="${building.id}">${building.name}</form:option>
										   </c:forEach>
						                   </form:select>
						                   <form:errors path="building" class="errors"/>       
						               </div>
						               <div class="form-group">
						                   <form:select class="input-md round form-control" path="location" id="loc">
						                   <form:option value="">Select Location</form:option>
						                   <c:forEach items="${locationList}" var="location">
										   <form:option value="${location.id}">${location.name}</form:option>
										   </c:forEach>
						                   </form:select>
						                   <form:errors path="location"  class="errors"/>           
						              </div>
						              <div class="form-group">
						                   <form:input type="text" path="assetNo" class="input-md round form-control" placeholder="Asset No." /> 
						              </div>
						              <div class="form-group">
						                   <form:input type="text" path ="model" class="input-md round form-control" placeholder="Model" /> 
						              </div>
                                      <div class="form-group">
                                            <form:input type="text"  path="serialNo" class="input-md round form-control"
                                             placeholder="Serial No." /> 
                                     </div>
                                     <div class="form-group">
                                            <form:input type="text" path="capacity"
                                             class="input-md round form-control" placeholder="Capacity" /> 
                                      </div>
                                      <div class="form-group">
                                            <form:input type="text" path="quantity"
                                             class="input-md round form-control" placeholder="Quantity" /> 
                                        </div>
                                        <div class="form-group">
                                            <form:select class="input-md round form-control" path="frequency" items="${frequencies}">
                                                <option>Select Servicing Frequency</option>
                                            </form:select>&nbsp;&nbsp;
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text"  path="servingArea"
                                            class="input-md round form-control" placeholder="Servicing Area"/> 
                                        </div>
                                        
                                         <div class="form-group">
                                            <form:input type="text"  path="installationDate" id="installationDate"
                                            class="input-md round form-control" placeholder="Installation Date" style="width:90%"/> 
                                            <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                             <form:errors path="installationDate" class="errors"/> 
                                        </div>
                                        
                                    <div class="form-group">
                                            <span>Condition of Asset</span><br />
                                            <form:checkbox value="exelent" path="assetCondition" />&nbsp;<a class="a">Excellent</a><br />
                                            <form:checkbox value="good" path="assetCondition" />&nbsp;<a class="a">Good</a><br />
                                            <form:checkbox  value="average" path="assetCondition" />&nbsp;<a class="a">Average</a><br />
                                            <form:checkbox   value="bad"  path="assetCondition"/>&nbsp;<a class="a">Bad</a><br />
                                            
                                            
                                              
                                         
                                         
                                              <c:set var="eqpcode"  value="${electrical.equipmentCode}"/>
                                              <form:hidden path="equipmentCode"/>
                                        <c:if test="${not empty eqpcode}">
                                            <img
											src="${pageContext.servletContext.contextPath}/electrical/getimage/${electrical.equipmentCode}"
											style="height: 250px; width: 250px;" /></c:if>                      
                                           
                                        
                                     
                                            
                                            
                                    </div>
                                     <div class="form-group">
                                            <span>Asset needs Replacement</span><br />
                                            <form:checkbox value="yes"  path="assetReplace"/>&nbsp;<a class="a">Yes</a><br />
                                            <form:checkbox value="no" path="assetReplace"/>&nbsp;<a class="a">No</a>
                                        </div>
                                        
          
                              <div class="form-group">
                                            <form:input type="text"  path = "expreplacementDate"  id="expreplacementDate"
                                            class="input-md round form-control"  placeholder="Expected date of Replacement" style="width:90%"  /> 
                                         <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick = "javascript: NewCssCal('expreplacementDate','ddMMyyyy')" style="cursor:pointer;width:30px" /> 
                                          	<form:errors path="expreplacementDate" class="errors"/>  
                                </div> 
                                        
                                        
                                        <div class="form-group">
                                            <form:input type="text"  path="manufacturingDate" class="input-md round form-control" id="manufacturingdate"
                                             placeholder="Manufacturing Date" style="width:90%" />
                                            <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('manufacturingdate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                            <form:errors path="manufacturingDate" class="errors"/> 
                                        </div>
                                        
                                        
                                      <div class="form-group">
                                       <span>Equipment Image</span>&nbsp;&nbsp;
                                         <form:hidden path="image"/>
                                          <input type="file" name="equipmentimage" id="equipmentimage" class="input-md round form-control" placeholder="Equipment Image" />
                                         <c:set var="eqpimage"  value="${electrical.image}"/>
                                       	 <c:if test="${not empty eqpimage}">
                                            <img
											src="${pageContext.servletContext.contextPath}/electrical/getimage/${electrical.image}"
											style="height: 250px; width: 250px;" /></c:if>                      
                                 		 </div>
                                        <div class="form-group">
                                            <form:textarea  path="remarks" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Remarks"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" name="drawingtitle" id="drawingtitle"  path="drawingTitle"
                                            class="input-md round form-control" placeholder="Drawing Title" /> 
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text"  path="drawingNo"
                                            class="input-md round form-control" placeholder="Drawing No" /> 
                                        </div>
                                        <div class="form-group">
                                         <span>Drawing Image</span>&nbsp;&nbsp;
                                          <form:hidden path="drawingImage"/>
                                            <input type="file" name="drawingimage" id="drawingimage" class="input-md round form-control" placeholder="Drawing Image" />
                                        <c:set var="draw"  value="${electrical.drawingImage}"/>
                                        <c:if test="${not empty draw}">
                                            <img
											src="${pageContext.servletContext.contextPath}/electrical/getimage/${electrical.drawingImage}"
											style="height: 250px; width: 250px;" /></c:if>                      
                                        </div>
                                        <div class="form-group">
                                            <span>Properties</span><br />
                                            <table id="prop" style="border:none !important" >
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
                                                <input type="hidden" value="${fn:length(electrical.baseline)}"  name="prm" />
                               					<c:forEach items="${electrical.baseline}" var="prop" varStatus="loop">
                                                   <tr id="tr${loop.index}">
                                                      <td><form:input type="hidden" value="${prop.id}"  path='baseline[${loop.index}].id' />
		                                              <form:input type='text' path='baseline[${loop.index}].name' data-bvalidator='required' value="${prop.name}" />
		                                              </td>
                          		                      <td><form:input type='text' path='baseline[${loop.index}].min' data-bvalidator='required' value="${prop.min}" /></td>
                      								  <td><form:input type='text' path='baseline[${loop.index}].max' data-bvalidator='required' value="${prop.max}" /></td>
                      								  <td><form:input type='text' path='baseline[${loop.index}].unit' data-bvalidator='required' value="${prop.unit}" /></td>
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
                                      		<input type="submit" id="submit" value="update" />
                                        </div>
      						</form:form>
      					</div>
      				</div>		
              </div>      
       </body>
</html>
     

                                    