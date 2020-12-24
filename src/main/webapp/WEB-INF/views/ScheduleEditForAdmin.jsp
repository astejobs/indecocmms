<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


  <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Building</li>
			  </ol>
			</nav>
        
        
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
                        Approve PM Schedule
                    </h2>
                    
                    <c:url value="/schedule/updateForAdmin" var="actionsubmit"></c:url>
                    <form:form action="${actionsubmit}" method="post" modelAttribute="schedule">
                    
                  		   <form:hidden path="id" />
                  		   <form:hidden path="checklistHeader"/>
							
							<c:forEach items="${schedule.equipment}" var="scheq" varStatus="i">
							<form:hidden path="equipment[${i.index}]" />
							</c:forEach>
							
							<input type="hidden" id="today" value="${newdate}">
							
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">PM Schedule No.</span>
                                            <form:input readonly="true" type="text" path="scheduleNumber" id="PM_Schedule_No" class="input-md round form-control" placeholder="PM Schedule No." />
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Brief Description</span>
                                            <form:input type="text" path="briefDescription" id="Brief_Description" class="input-md round form-control" placeholder="Brief Description" />
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Detailed Description</span>
                                            <form:textarea id="Detailed_description" path="detailedDescription" class="input-md round form-control col-lg-3" placeholder="Detailed Description"/>
                                        </div>
                                        
                                  <%--       <div class="form-group">
                                            <form:input type="text" path="userRefNumber" id="User_Reference_No" class="input-md round form-control" placeholder="User Reference No*"  required="required"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:select required="required" class="input-md round form-control" path="taskPerformed">
                                                <form:option value="inservice">In Service</form:option>
												<form:option value="outofservice">Out of Service</form:option>
                                            </form:select>
                                        </div> --%>
                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Equipment to be Maintained
                                        </h2>
                                       
                                    
                                        <%-- <div class="form-group">  
                                        
                                       <label>Select Equipment:</label>
                                  		<label>
										<c:forEach items="${schedule.equipment}" var="equipment">
								
											${equipment.name}-${equipment.equipmentCode}<br>
								
										</c:forEach>
										</label>
                                        </div> --%>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Frequency</span>
                                          <form:select required="required" class="input-md round form-control" path="frequency" items="${frequencies}"/>
                                        </div>
                                        
                                        
                  <%--            <div class="cf-right-col">
									<label >Team </label>
									
									<form:select path="teams" required="required" >
							 			<form:options items="${teamList}" itemLabel="name" itemValue="id"/>									
									</form:select>
									
									<form:errors path="teams"/>
							</div>
                                    --%>     
                                     
                                  <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Reviewer</span>
                                        
                                     <form:select path="" class="input-md form-control round">
                                     
										<form:option  value="">Reviewer</form:option>
										
									</form:select> 
                                          
                                  </div>
                                     
                                      <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Approver</span>
                                        
                                     <form:select path="" class="input-md form-control round">
                                     
										<form:option  value="">Approver</form:option>
										
									</form:select> 
                                          
                                  </div>
                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Schedule Duration
                                        </h2>
                                        
          <!-- <div class="row" style="margin:0;">  -->
			<div class="form-group input-group">
	             <span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
				<form:input type="text" path="startDate" id="From_date"
					class="input-md round form-control" placeholder="Start Date" />
				<%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
					onclick="javascript: NewCssCal('From_date','ddMMyyyy')"
					style="cursor: pointer; width: 30px" /> --%>
			</div>
			<div class="form-group input-group">
	             <span class="input-group-addon label-left" id="basic-addon2">End Date</span>
				<form:input type="text" path="endDate" id="To_date"
					class="input-md round form-control" placeholder="End Date" />
				<%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
					onclick="javascript: NewCssCal('To_date','ddMMyyyy')"
					style="cursor: pointer; width: 30px" /> --%>
			</div>
		 <!--  </div>  -->

                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Schedule Criteria
                                        </h2>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Schedule Date</span>
                                           <%--  Schedule by Date:&nbsp;&nbsp;&nbsp;&nbsp;
                                            <form:checkbox path="scheduleByDate" id="Schedule_by_Date"  class="input-md round"/>&nbsp;&nbsp;&nbsp;
                                             --%>
                                               <form:input type="text" path="scheduleDate" required="required" id="Schedule_Date"  class="input-md round form-control" placeholder="Schedule Date" onclick="javascript: NewCssCal('Schedule_Date','ddMMyyyy')"/>
                                        	<%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('Schedule_Date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
                                        
                                        </div>
                                        
                                      
                                 <%--        <div class="form-group">
                                            Schedule by Meter: 
                                            <form:checkbox path="scheduleByMeter" id="Schedule_by_Date" style="width:3%" class="input-md round" placeholder="Start Date" />&nbsp;&nbsp;&nbsp;
                                           <form:select path="meter" required="required">
												<form:option value="">Select Meter</form:option/>
												<form:options items="${meters}" itemLabel="meterno"	itemValue="id"/>	
										  </form:select>
                                            <form:radiobutton path="above" id="AboveBelow" style="width:8%" class="input-md round "/>Above&nbsp;
                                            <form:radiobutton path="above" id="AboveBelow" style="width:8%" class="input-md round "/>Below&nbsp;
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:input type="text" path="triggerReading" id="Trigger_Reading" class="input-md round form-control" placeholder="Trigger Reading"  />
                                        </div>
                                         --%>
	                                     <div class="form-group float-right">
                                            <input type="submit" name="accept"  value="Accept" class="submit_btn btn btn-mod btn-medium btn-round" />
                                            <input type="submit" name="reject" value="Reject" class="submit_btn btn btn-mod btn-medium btn-round" />
                                        </div>
                                        
                                         <br />
                                         
                               </form:form>
                                         
 </div>
 </div>
 </div>
 </div>
        
<script type="text/javascript">
$(document).ready(function() 
		{
$( "#bd1" ).change(function() {
	
	     var id = $(this).val();
	   
	     $('#contentfromchild').empty();
	   
	  var url = "${pageContext.request.contextPath}/schedule/equipmentlist/"+id;
	  
	 if(id!='')
		 {
      $.get(url, function(data){
         
    	 
         $('#contentfromchild').empty();
         
              $.each(data,function(k,v){
              var option = $('<option/>');
              option.attr('selected','seleted');
              option.attr({ 'value': v.id }).text(v.name);
              $('#contentfromchild').append(option);
              
              }); 
	  
     
	  
      
});
		 }
});        
});
    
</script>

                                    


       