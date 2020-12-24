
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


 <!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<link rel="stylesheet" href="/resources/demos/style.css"> 
   

        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item"><a href="#">FaultReport</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Create</li>
			  </ol>
			</nav>
		
		<c:url value="/schedule/save" var="actionsubmit"></c:url>
<!--Form-->
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
        
                    <h4 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        Create PM Schedule
                    </h4>
                    
                    <form:form action="${actionsubmit}" method="post" modelAttribute="schedule">
                    					
                    					
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">PM Schedule No</span>
                                            <form:input readonly="true" type="text" path="scheduleNumber" id="PM_Schedule_No" class="input-md round form-control" placeholder="PM Schedule No." pattern=".{3,100}" />
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Brief Description</span>
                                            <form:input type="text" path="briefDescription" id="Brief_Description" class="input-md round form-control" placeholder="Brief Description"/>                     
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Detailed Description</span>
                                            <form:textarea id="Detailed_description" path="detailedDescription" class="input-md round form-control" placeholder="Detailed Description"/>
                                        </div>
                                      
                                        <%-- div class="form-group">
                                            <form:input type="text" path="userRefNumber" id="User_Reference_No" class="input-md round form-control" placeholder="User Reference No*"  required="required"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:select required="required" class="input-md round form-control" path="taskPerformed">
                                                <form:option value="inservice">In Service</form:option>
												<form:option value="outofservice">Out of Service</form:option>
                                            </form:select>
                                        </div> --%>
                                        
                                        <h4 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Equipment to be Maintained
                                        </h4>
                                        
                                        
	                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Checklist</span>
	                                        
	                                     <form:select path="checklistHeader"  id="bd1" class="input-md round form-control" >
											<form:option  value=""> Select Checklist:</form:option>
										
											<form:options readonly="readonly" items="${listCheck}" itemLabel="checklistName"  itemValue="id" ></form:options>
										
										</form:select> 
									
										
	                                           <!--  <textarea id="Select_Equipment"  name="Select_Equipment" class="input-md round form-control col-lg-3" placeholder="Select Equipment "></textarea>
	                         -->            </div>
	                                        
	                                            <div class="form-group">
	                                            
	                                            <form:select  class="input-md round form-control" id="contentfromchild" path="equipment" size="3" required="required">
													<form:option value="">Select Equipment</form:option>
												</form:select>
												<input type="button" class="submit_btn btn btn-mod btn-medium btn-round" id="removeEq" value="X">
	                                            
	                                               <!--  <input type="button" value="Select Equipment" onclick="window.open('call-forms/create-checklist-select-equipments.html', 'name', 'width=600,height=600');" class="submit_btn btn btn-mod btn-medium btn-round" /> -->
	                                            </div>
                                        
                                        
                                       <!--  <div class="form-group">
                                        <input type="button" class="submit_btn btn btn-mod btn-medium btn-round" id="removeEq" value="Remove">
                                        </div> -->
                                        
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Daily</span>
                                          <form:select required="required" class="input-md round form-control" path="frequency" items="${frequencies}"/>
                                        </div>
                                        
                                        
                         <%--     <div class="form-group">
									<label >Team </label>
									
									<form:select path="teams" required="required" class="input-md round form-control" >
							 			<form:options items="${teamList}" itemLabel="name" itemValue="id"/>									
									</form:select>
									
									<form:errors path="teams"/>
							</div> --%>
                             <div class="clearfix"> </div>           
                                     
                                  <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Reviewer</span>
                                        
                                     <form:select path="" class="input-md round form-control" >
                                     
										<form:option  value="">Reviewer</form:option>
										
									</form:select> 
                                          
                                  </div>
                                     
                                      <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Approver</span>
                                        
                                     <form:select path=""  class="input-md round form-control">
                                     
										<form:option  value="">Approver</form:option>
										
									</form:select> 
                                          
                                  </div>
                                      
                                        <h4 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Schedule Duration
                                        </h4>
                                        <!-- <div class="row" style="margin:0px"> -->
		                                        <div class="form-group input-group" >
	                                        		<span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
		                                            <form:input type="text" path="startDate" required="required" id="From_date" class="input-md round form-control col-md-6" placeholder="Start Date" autocomplete="off"/>
		                                        </div>
		                                         <div class="form-group input-group">
	                                        		<span class="input-group-addon label-left" id="basic-addon2">End Date</span>
		                                            <form:input type="text" path="endDate" required="required" id="To_date" class="input-md round form-control" placeholder="End Date" autocomplete="off"/>
		                                         </div>
                                         <!-- </div> -->
                                         
                                        <h4 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Schedule Criteria
                                        </h4>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Schedule By Date</span>
                                        	 <form:input type="text" path="scheduleDate" required="required" id="Schedule_Date" class="input-md round form-control" placeholder="Schedule Date" autocomplete="off"/>
                                            
<%--        <form:checkbox path="scheduleByDate" id="Schedule_by_Date"   class="input-md round"/>&nbsp;&nbsp;&nbsp; --%>
                                                                               
                        <%--  <form:input type="text" required="required" path="scheduleDate" id="Schedule_Date" class="input-md round form-control" placeholder="Schedule Date" onclick="javascript: NewCssCal('Schedule_Date','ddMMyyyy')" /> --%>
 
                                        </div>
                                        
                                       
                                       <%--  <div class="form-group">
                                            Schedule by Meter: 
                                            <form:checkbox path="scheduleByMeter" id="Schedule_by_Date_tig"  style="width:3%" class="input-md round" placeholder="Start Date" pattern=".{5,100}"/>&nbsp;&nbsp;&nbsp;
                                           <form:select path="meter" required="required">
												<form:option value="">Select Meter</form:option/>
												<form:options items="${meters}" itemLabel="meterno"	itemValue="id"/>	
										  </form:select>
                                            <form:radiobutton path="above" id="AboveBelow" style="width:8%" class="input-md round "/>Above&nbsp;
                                            <form:radiobutton path="above" id="AboveBelow" style="width:8%" class="input-md round "/>Below&nbsp;
                                        </div> --%>
                                     <!--    <div class="form-control">
                                                <input type="hidden" id="sDate" name="sDate" />
                                                <input type="hidden" id="eDate" name="eDate" />
                                            </div> -->
                                        <%-- <div class="form-group">
                                            <form:input type="text" path="triggerReading" disabled="disabled" id="Trigger_Reading" class="input-md round form-control" placeholder="Trigger Reading" pattern=".{5,100}" required="required"/>
                                        </div> --%>
                                        
	                                     <div class="form-group float-right">
                                            <input type="submit" value="Save" class="submit_btn btn btn-mod btn-medium btn-round" />
                                            <input type="submit" value="Cancel" class="submit_btn btn btn-mod btn-medium btn-round" />
                                        </div>
                                        
                                         <br />
                                         
                               </form:form>
                                         
 </div>
 <!-- <div class="col-sm-2">
	 <div class="jumbotron" class="margin-left:10px;height:100%">
	 		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	 </div>
 </div> -->
 </div>
 </div>
 </div>
                                    
    <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  -->
    <script>
        $(function () {
            //$("#From_date").datepicker({ dateFormat: 'dd-mm-yy' });
            //$("#To_date").datepicker({ dateFormat: 'dd-mm-yy' });
            $("#Schedule_Date").datepicker({ minDate: 0, dateFormat: 'dd-mm-yy' });
            
            /* $("#From_date").change(function() { console.log($("#From_date").val());
            	 var fromDt = $("#From_date").val();
            	 jQuery("#To_date").datepicker("destroy");
            	 $("#To_date").datepicker({ minDate: fromDt, dateFormat: 'dd-mm-yy' });
            });
            $("#To_date").change(function() { console.log($("#To_date").val());
		       	 var toDt = $("#To_date").val();
		       	 jQuery("#From_date").datepicker("destroy");
		       	 $("#From_date").datepicker({ maxDate: toDt, dateFormat: 'dd-mm-yy' });
		    }); 
             */
             /*  $("#Schedule_Date").attr("disabled", "disabled");
             $("#Trigger_Reading").attr("disabled", "disabled");  */

            $("#From_date").on('change', function () {
                var dateString = "";
                dateString = $.datepicker.parseDate('dd-mm-yy', $("#From_date").val());
                var _getStart = convert(dateString.toString());
                $("#sDate").val(_getStart);
               /*   $("#Schedule_Date").attr("disabled", "disabled"); 
                 $('#Schedule_by_Date').attr('checked', false); */
                //jQuery("#Schedule_Date").datepicker("destroy");
                $("#Schedule_Date").datepicker("refresh");
            }); 

            $("#To_date").on('change', function () {
                var dateString = "";
                dateString = $.datepicker.parseDate('dd-mm-yy', $("#To_date").val());
                var _getEnd = convert(dateString.toString());
                 $("#eDate").val(_getEnd);
              /*    $("#Schedule_Date").attr("disabled", "disabled");
                 $('#Schedule_by_Date').attr('checked', false); */
                //jQuery("#Schedule_Date").datepicker("destroy");
                $("#Sc21hedule_Date").datepicker("refresh"); 
            });
       

            function convert(str) {
                var mnths = {
                    Jan: "01", Feb: "02", Mar: "03", Apr: "04", May: "05", Jun: "06",
                    Jul: "07", Aug: "08", Sep: "09", Oct: "10", Nov: "11", Dec: "12"
                },
                date = str.split(" ");
                    return [mnths[date[1]], date[2], date[3]].join("/");
                }

                  $("#Schedule_by_Date").on('change', function () {
                    if ($(this).is(":checked")) {
                        $("#Schedule_Date").removeAttr("disabled");
                        $('#Schedule_by_Date').focusin();

                        var startDateRange = $("#sDate").val();
                        var endDateRange = $("#eDate").val();
                      
                        $("#Schedule_Date").datepicker({
                            dateFormat: 'dd-mm-yy', minDate: new Date(startDateRange), maxDate: new Date(endDateRange),
                        });
                    } else {
                      /*   $("#Schedule_Date").attr("disabled", "disabled"); */
                        jQuery("#Schedule_Date").datepicker("destroy");
                        $("#Schedule_Date").datepicker("refresh");
                    }
                }); 

                $("#Schedule_by_Date_tig").on('change', function () {
                    if ($(this).is(":checked")) {
                        $("#Trigger_Reading").removeAttr("disabled");
                        $("#Trigger_Reading").focus();
                    } else {
                        $("#Trigger_Reading").attr("disabled", "disabled");
                    }
                });
        });
     </script>




<script type="text/javascript">

var stringEq = '__'; 
var EqList = new Array();
EqList[0] = '_';
var i=0;
var flg=0;

var j=0;

$(document).ready(function(){
	//contentfromchild remove
	$("#removeEq").click(function(){
		 
		 

		$("#contentfromchild > option").each(function() {
		 if($(this).val()==$("#contentfromchild").val()){

			 for(j=0; j < EqList.length; j++){
		
				 try{
					
					 if(EqList[j].includes($(this).val()))
						{
					
						EqList.splice(j, 1);
						}

				 }
				 catch(e){}
					}

			 
			$(this).remove();
		}
		});	
			
		 $("#contentfromchild > option").each(function() {
			 $(this).prop('selected', true);
			});	

		
		});


	$("#cr").click(function(){
		$("#contentfromchild > option").each(function() {
			 $(this).prop('selected', true);
			});	
		});
	
});
</script>

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


 
