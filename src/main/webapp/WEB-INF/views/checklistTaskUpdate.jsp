<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    <style>
        label {
            font-family: Dosis, arial, sans-serif;
            /* font-weight: normal;*/
            font-size: 16px;
        }
        
        table {
            table-layout: auto;
        }
    </style>

<body class="appear-animate">

    <!-- Page Loader -->
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <!-- End Page Loader -->
    <!-- Page Wrap -->
    <div class="page" id="top">

        <!-- Navigation panel -->
     
        <!-- End Navigation panel -->
        <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Checklist Update</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
        
                  <c:if test="${not empty success}">
					     <div class="success">${success}</div>
			      </c:if>
			      
			      
				<c:if test="${not empty fail}">
					     <div class="error">${fail}</div>
                </c:if>
 
            <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                      Checklist
                    </h2>
                  
 <c:url value="/task/checklistUpdatee" var="taskupdate"></c:url>

         <form:form action="${taskupdate}" class="form contact-form" commandName="task" id="validate"
                    method="POST" enctype="multipart/form-data">
                    
          <form:input type="hidden" path="id"/>
          <form:input type="hidden" path="schedule.id"/>
          <form:input type="hidden" path="equipment.id"/>
          <form:input type="hidden" path="actualScheduleDate"/>
          <form:input type="hidden" path="scheduleDate"/>
          <form:input type="hidden" path="task_number"/>
          <form:input type="hidden" path="userDetail.id"/>
          <form:input type="hidden" path="completedDate" />
           <form:input type="hidden" path="endDate" />
          <form:input type="hidden" path="remarks"/>
          <form:input type="hidden" path="status" />
        
           <form:input type="hidden" path="completedBy" />
   
       
         <!--   <div class="form-group">
                <label class="col-md-3">Checklist Code:</label>
            </div>-->
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Reference No.</span>
                <form:input type="text" path="schedule.userRefNumber" disabled="true" class="input-md round form-control"/>                
                
              
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Date of Service</span>
                <form:input type="text" path="scheduleDate" disabled="true" class="input-md round form-control"/>                
                
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Equipment Sub Type</span>
                              <form:input type="text" path="equipment.assetSubtype.assetSubTypeName" disabled="true" class="input-md round form-control"/>                
              
           
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Equipment Brand</span>
                <label> <form:input type="text" path="equipment.brand" disabled="true" class="input-md round form-control"/>                
                
                </label>
            </div>
  
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Building</span>
                <form:input type="text" path="equipment.building.description" disabled="true" class="input-md round form-control"/>                
                
               
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Location</span>
                  <form:input type="text" path="equipment.location.description" disabled="true" class="input-md round form-control"/>     
             
            </div>
            <div class="form-group">
                <label class="col-md-3">Frequency of Service:</label>
                
               
                <c:choose>
										<c:when test="${task.schedule.frequency == 1}">Daily</c:when>
										<c:when test="${task.schedule.frequency == 2}">Weekly</c:when>
										<c:when test="${task.schedule.frequency == 3}">Fortnightly</c:when>
										<c:when test="${task.schedule.frequency == 4}">Monthly</c:when>
										<c:when test="${task.schedule.frequency == 5}">Quarterly</c:when>
										<c:when test="${task.schedule.frequency == 6}">HalfYearly</c:when>
										<c:when test="${task.schedule.frequency == 7}">Yearly</c:when>
									</c:choose>	
								
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Actual Schedule Date</span>
            
										  <form:input type="text" path="actualScheduleDate" disabled="true" class="input-md round form-control"/>      
            </div>

          
           
            <div class="form-group">
            
                <label class="col-md-3">Before Image:</label>
                <form:hidden path="beforeImage"/>
                <c:set var="before" value="${task.beforeImage}"></c:set>
          							<c:if test="${not empty before}">
          								<img src="${pageContext.servletContext.contextPath}/task/getimage/${task.beforeImage}" style="height: 250px; width: 250px;" />
          							</c:if>	
          							
          <input class="col-md-9" type="file" name="before_image" id="before_image" class="input-md round form-control" placeholder="Project image (jpg/png)">
          							
            </div>
            <div style="clear: left"> </div>
            <div class="form-group">
                <label class="col-md-3">After Image:</label>
               
                 <form:hidden path="afterImage"/>
                 <c:set var="after" value="${task.afterImage}"></c:set>
									<c:if test="${not empty after}">
								         <img src="${pageContext.servletContext.contextPath}/task/getimage/${task.afterImage}" style="height: 250px; width: 250px;" />
									</c:if>
								
                <input class="col-md-9" type="file" name="after_image" id="after_image" class="input-md round form-control" placeholder="Project image (jpg/png)">
            </div>
            <div style="clear: left"> </div>

            <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                      Work Description
                    </h2>



            <div class="table-responsive">
                <table class="table table-stripped" id="tblResult">
                    <thead>
                        <tr>
                            <th>S No.</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Remarks</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                         
                           <c:forEach items="${task.checkLists}" var="check" varStatus="loop"> 
                          
                            
                              
							<tr>
                              <td>${loop.index+1}  </td>
                              <td>
                                  <form:hidden path="checkLists[${loop.index}].id" value="${check.id}" /> 
                                  <form:hidden path="checkLists[${loop.index}].task" value="${check.task.id}" /> 
                                   <form:input path="checkLists[${loop.index}].description" value="${check.description}" /> 
                                 </td>
                                <td>
                                  
                                  
                                       <form:select path="checkLists[${loop.index}].status"> 
                                       <form:option value="YES">YES</form:option>
                                       <form:option value="NO">NO</form:option>
                                       <form:option value="NA">NA</form:option>
                                       
                                       </form:select>
                                
                                         
                                        </td>
                                          <td>
                                  
                           						<form:textarea path="checkLists[${loop.index}].remarks"/>
                        			
                                  	</td>
                  </tr>
									
							 </c:forEach> 
                         
								

                       
                    </tbody>
                </table>
            </div>

           <div class="form-group">
                                                <label style="display:inline-block " class="col-md-3">Comments:</label>
                                                <form:textarea path="comments"></form:textarea>
                                            </div>
            
            <div style="clear: left"> </div>
            <br>
            
        <input type="hidden" id="leftmg" value="" name="leftmg">
        <input type="hidden" id="middlemg" value="" name="middlemg">
        <input type="hidden" id="rightmg" value="" name="rightmg">
            
            
          <div class="form-group">
                         <label style="display:inline-block " class="col-md-3">Attended By:</label>
                          
                           
                          <c:if test="${ empty  task.srvc_verify_sign1 }">
                           <input type="hidden" name="leftImageView" id="leftImageView" class="leftImageView" /> 
                                 <canvas id="signature-pad" class="signature-pad" width=200 height=100 style="border:1px solid #000000;"></canvas>
                           </c:if>
   
              <c:if test="${not empty task.srvc_verify_sign1 }"> 
              <form:hidden path="srvc_verify_sign1"/>
               <input type="hidden" name="leftImageView" id="leftImageView" value="empty" class="leftImageView" /> 
            
                       <img id ="signature-pad-img" src="${pageContext.servletContext.contextPath}/task/getimage/${task.srvc_verify_sign1}"
                        style="width: 200px; height: 100px; border: 1px solid #000;">
                    </c:if>
                           </div>
           
             
            <div style="clear: left"> </div>
                                      <br>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                  <div class="form-group">
                                  
                               
               <label style="display:inline-block " class="col-md-3">Checked By:</label>
                  
                   
                   <c:if test="${empty task.srvc_chkd_sign1}">
                    <input type="hidden" name="middleImageView" id="middleImageView" class="middleImageView">
                    <canvas id="signature-pad1" class="signature-pad1" width=200 height=100 style="border:1px solid #000000;"></canvas>
                  </c:if>
                  
                  
                  <c:if test="${not empty task.srvc_chkd_sign1 }">
                 <form:hidden path="srvc_chkd_sign1"/>
                   <input type="hidden" name="middleImageView" id="middleImageView" value="empty" class="middleImageView">
                     <img id="signature-pad1-img" src="${pageContext.servletContext.contextPath}/task/getimage/${task.srvc_chkd_sign1}"
           style="width: 200px; height: 100px; border: 1px solid #000;">
                  
                  </c:if>
                                            </div>
           `<div style="clear: left"> </div>
                                            <br>
                                            <div class="form-group">
                                                <label style="display:inline-block " class="col-md-3">Acknowledged By:</label>
                                                
                                                
                                                
                                     
                                         
                                       <c:if test="${empty task.srvc_acknwldg_sign}">
                                         <input type="hidden" name="rightImageView" id="rightImageView" class="rightImageView">
                                                <canvas id="signature-pad2" class="signature-pad2"  width=200 height=100 style="border:1px solid #000000;"></canvas>
                                        </c:if> 
                                        
                                        <c:if test="${not empty task.srvc_acknwldg_sign }">
                                        <form:hidden path="srvc_acknwldg_sign"/>
                                        
                               
                                           <input type="hidden" name="rightImageView" id="rightImageView" class="rightImageView" value="empty">
          <img  id ="signature-pad2-img" src="${pageContext.servletContext.contextPath}/task/getimage/${task.srvc_acknwldg_sign}"
           style="width: 200px; height: 100px; border: 1px solid #000;">  
                                        </c:if>
                                           
                                               


                                            </div>
                                            <br>
                                            <br>
           
           
            
                                        <div class="form-group">
						               <input type="submit" id="Save" class="btn" value="Submit" />
						               <input type="button" value="back" class="btn" onClick="javascript:location.href = '${pageContext.servletContext.contextPath}/task/${task.id}';" />
						               <input type ="button" class="btn" onclick="javascript:window.print();"value="Print this page">
						               
						               
						                                   		 </div>
       
            


</form:form>

            <!-- End Contact Form -->
        </div>

    </div>

    <!--End Form-->
    <!-- Foter -->
    <div class="clearfix"></div>
    <br />
    <br />
   
    <!-- End Footer -->

    </div>
    </div>
    <!-- End Page Wrap -->
    <!-- JS -->
    
    <script>
    
    
    
    </script>
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/signature_pad.js"></script>
                                

                                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
                                <script type="text/javascript" src="../assets/js/iCloud.js"></script>
                                <script type="text/javascript" src="../assets/js/accord.js"></script>
                                <script type="text/javascript" src="../assets/js/mtree.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery-1.11.2.min.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.easing.1.3.js"></script>
                                <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
                                <script type="text/javascript" src="../assets/js/SmoothScroll.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.scrollTo.min.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.localScroll.min.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.viewport.mini.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.countTo.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.appear.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.sticky.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.parallax-1.1.3.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.fitvids.js"></script>
                                <script type="text/javascript" src="../assets/js/owl.carousel.min.js"></script>
                                <script type="text/javascript" src="../assets/js/isotope.pkgd.min.js"></script>
                                <script type="text/javascript" src="../assets/js/imagesloaded.pkgd.min.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.magnific-popup.min.js"></script>
                                <script type="text/javascript" src="../assets/js/gmap3.min.js"></script>
                                <script type="text/javascript" src="../assets/js/wow.min.js"></script>
                                <script type="text/javascript" src="../assets/js/masonry.pkgd.min.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.simple-text-rotator.min.js"></script>
                                <script type="text/javascript" src="../assets/js/all.js"></script>
                                <script type="text/javascript" src="../assets/js/contact-form.js"></script>
                                <script type="text/javascript" src="../assets/js/jquery.ajaxchimp.min.js"></script>
                                
                                
                                <script>
                                
                                   
                                $('document').ready(function(){
                                  
                                
                                  
                                
                                  try{
                                    var signaturePad = new SignaturePad(document.getElementById('signature-pad'), {
                                          backgroundColor: 'rgba(255, 255, 255, 0)',
                                          penColor: 'rgb(0, 0, 0)'
                                          
                                   });
                                      
                                  
                                    
                                        var dtleftmg=signaturePad.toDataURL();
                                        dtleftmg =dtleftmg.replace("data:image/png;base64,","");
                                        $("#leftmg").val(dtleftmg)
                                      
                                  }catch(e){}
                                      
                                   
                                try{
                                    var signaturePad1 = new SignaturePad(document.getElementById('signature-pad1'), {
                                        backgroundColor: 'rgba(255, 255, 255, 0)',
                                        penColor: 'rgb(0, 0, 0)'
                                    });
                                    
                              
                                     var dtmiddlemg=signaturePad1.toDataURL();
                                     dtmiddlemg =dtmiddlemg.replace("data:image/png;base64,","");
                                     $("#middlemg").val(dtmiddlemg)
                                   
                                }catch(e){}  

                              
                                try{
                                  var signaturePad2 = new SignaturePad(document.getElementById('signature-pad2'), {
                                      backgroundColor: 'rgba(255, 255, 255, 0)',
                                      penColor: 'rgb(0, 0, 0)'
                                  });
                                  
                                  var dtrightmg=signaturePad2.toDataURL();
                                  dtrightmg =dtrightmg.replace("data:image/png;base64,","");
                                   $("#rightmg").val(dtrightmg)
                            }catch(e){} 
                                 
                              
 $( "#validate" ).submit(function( event ) {
                              
   
   
   try{
                               var atb=signaturePad.toDataURL();
                               var checkleftimg = $("#leftmg").val();
                                 atb =atb.replace("data:image/png;base64,","");
                                 if(atb==checkleftimg)
                                     $("#leftmg").val("true");
                                else
                                  
                                  $("#leftImageView").val(atb)
                                  
   }catch(e){}
                            
   
   
   try{
                          var chk =signaturePad1.toDataURL();
                                var chkmiddleimg=$("#middlemg").val();
                chk =chk.replace("data:image/png;base64,","");
                                  if(chk==chkmiddleimg){
                             $("#middlemg").val("true");
                          }else{
                                 $("#middleImageView").val(chk)
                               }
                                  
   }catch(e){}
         
   try{
                              var ack =signaturePad2.toDataURL();
                             var chkrightimg=$("#rightmg").val();
                             ack=ack.replace("data:image/png;base64,","")
                              if(ack==chkrightimg){
                              $("#rightmg").val("true");
                             }else{
                                      $("#rightImageView").val(ack)
                                              }
   }catch(e){}      
  
                            
                             });        
                    })
                    
                    
                    
            
                            
              </script>
                                
                                
                                
                                
                                
                                
                                
                                

</body>

</html>
