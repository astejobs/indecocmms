<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
    
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/mechanical.js"></script>
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
 </head>       
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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-technician.html">Mechanical Create</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <!---MENU-->
         <div  style="height:100px;position:relative"></div>
			 <div class="container" style="width:100%;">
		  		<div class="row">
		    		<div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    			<jsp:include page="/WEB-INF/views/menu.jsp" />
		    		</div>
         			<div class="col-xs-8 col-sm-8 col-lg-8 col-md-8">            
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                       Create Mechanical Equipment
                        <c:url value="/mechanical" var="submit"/>
                         <c:set var="submitForm" value="ADD"></c:set>
                    </h2>
                     <div class="form-group">

                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   
                    <!-- Contact Form -->
                    <div class="row">
                        <div >
                          
                            <form:form action="${submit}" method="post" class="form contact-form" id="contact_form"  modelAttribute="mechanicalEquipment" enctype="multipart/form-data" >
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group">
                                        
                                        <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                                             <form:input type="text" path="equipmentType" value="${EqType}" id="equp_type" style="width:49%" class="input-md round form-control" placeholder="Equipment Type" disabled="true" />
                                           
                                            <form:select style="width:49%" class="input-md round form-control" path="assetSubtype">
                                            
                                            <form:option value="" >Select Equipment Sub Type</form:option>
								<form:options items="${subSystems}" itemLabel="assetSubTypeName"
									itemValue="id" />
								</form:select>
								<form:errors path="assetSubtype"></form:errors>
                                            
                                 </div>
                                        <div class="form-group">
                                            <form:input type="text" path="name" id="equp_name" class="input-md round form-control" placeholder="Equipment Name" />
                                        </div>
                                        <div class="form-group">
                                      
                                        <form:select id="building"  class="input-md round form-control"
								path="building">
								<form:option value="">Select Building</form:option>
								<c:forEach items="${buildingList}" var="building">
									<form:option value="${building.id}">${building.name}</form:option>
								</c:forEach>
							</form:select>								<form:errors path="building"></form:errors>
							
							
                                        
                                        
                                            
                                        </div>
                                        <div class="form-group">
                                        
                                       <!--  <form:select required="required" class="input-md round form-control"  id="location" path="location">
                                                   <form:option value="">Select any Location</form:option>
                                        </form:select>-->
                                        
                                        
                                    <form:select  path="location" id="location" class="input-md round form-control" placeholder="Location">
                                              <form:option value="">Select any  Location</form:option>
                                               <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
							<form:errors path="location"></form:errors>
							
                                           
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
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationdate','ddMMyyyy')"  style="cursor:pointer;width:30px" />
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
                                            <form:input type="text" readonly="true"  name="expecteddateofreplacement" path="expreplacementDate" id="expecteddateofreplacement" class="input-md round form-control" placeholder="Expected date of Replacement" style="width:90%" />
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
                                            <form:textarea class="input-md round form-control col-lg-3" required="required" style="margin-bottom:10px" path="remarks" placeholder="Remarks" />
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
                                        
                                        
                                        <div class="form-group">
                                            <form:input type="text" path="drawingTitle" name="drawingtitle" id="drawingtitle" class="input-md round form-control" placeholder="Drawing Title" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="drawingNo" name="drawingname" id="drawingname" class="input-md round form-control" placeholder="Drawing Name" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <input type="file"  name="drawingimage" id="drawingimage" class="input-md round form-control" placeholder="Drawing Image" />
                                        
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
                                                        <td><input type="text" name="baseline[0].name" />
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
                                                        <input type="button" id="add_prop" name="add_prop" value="Add Property" />
                                               </div>
                                              
                                                 <div class="form-group">
                                        <input type="submit" id="Save" value="${submitForm }" />
                                        <input type="submit" id="Cancel" value="Cancel" />
                                    </div>
                                    <script>
                                    $(document).ready(function () {
                                       // $("#addprop").click(function () {
                                      //      $("#prop").append('<tr><th><input type="text" /></th><th><input type="text" class="code" /></th><th><input type="text" class="code"/></th><th><input type="text" class="code"/></th></td> <td style="padding-left:10px;position:relative"><a href="javascript:void(0);" id="remCF" style="color:black"><img style="width:20px;height:20px;" src="../assets/images/trash..png" /></a></td></tr>');
                                      //  });

                                     //   $("#prop").on('click', '#remCF', function () {
                                      //      $(this).parent().parent().remove();
                                      //  });

                                        $(function () {
                                            $("#prop").pagination({
                                                items: 100,
                                                itemsOnPage: 10,
                                                cssStyle: 'light-theme'
                                            });
                                        });

                                    });
                                    </script>
                                    <style>
                                        #prop input[type="text"] {
                                            border: 1px dotted #000000;
                                            margin-bottom: 5px;
                                            margin-left: 5px;
                                        }
                                    </style>
                                </div>
                            </form:form>
                        </div>
                    </div>
                  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqCr.js"></script>
                 
                            
                    <!-- End Contact Form -->
                </div>
           
            </div>
            </div>
            
        </div>

        <!--End Form-->
            
</body>
</html>
