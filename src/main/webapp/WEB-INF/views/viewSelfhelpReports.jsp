	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-dateFormat/1.0/jquery.dateFormat.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$('#type').change(function(){
		$("#tblResult > tbody").html("");
		$.ajax({url: "${pageContext.servletContext.contextPath}/selfHelp/getreports/"+$(this).val(), success: function(result){
			$.each(result, function(item,data) {
			   $("#tblResult").find('tbody')
				        .append($('<tr>')
				         .append($('<td>')
				        	 .attr('data-id',data.id)
				        	 .attr('class','open-Dialog')
				        	 .attr('data-toggle','modal')
				        	 .attr('data-target','#myModal')
				        	.text(data.reportName)
				        )
				        .append($('<td>')
				        	  .text(data.requestorName)	
				        		
				        )
				        .append($('<td>')
				        	  .text($.format.date(data.createdDate, 'dd/MM/yyyy HH:mm a'))	
				        		
				        )
				        .append($('<td>')
				        	.append($('<a>')
				                 .attr('href', '${pageContext.servletContext.contextPath}/selfHelp/editadhocReport/'+data.id)
				                 .text('edit')
				            )
				           .append($('<a>')
				                 .attr('href', '${pageContext.servletContext.contextPath}/selfHelp/deleteadhocReport/'+data.id)
				                 .text('delete')
				           )
				        )
				        
				    );
		            });
		        },
			
		    });
		
	})
	
	
    
});

$(document).on("click", ".open-Dialog", function () {
    var  Id = $(this).data('id');
     $(".modal-body input[type='hidden']").val(Id);
    // As pointed out in comments, 
    // it is superfluous to have to manually call the modal.
    // $('#addBookDialog').modal('show');
    
     $(function () {
    	    $('#datetimepicker1').datepicker({});
    	    $('#datetimepicker2').datepicker({});
    	   });
});


    
    
</script>
</head>
<body class="appear-animate">

    <!-- Page Loader -->
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <!-- End Page Loader -->
    <!-- Page Wrap -->
    <div class="page" id="top">

       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Self Report</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            <form class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                        <div class="form-group input-group">
                                        	 <span class="input-group-addon label-left" id="basic-addon2">Report Type</span>
                                             <select id="type"  class="input-md round form-control">
                                                <c:forEach  items="${reporttype}" var="type">
                                                <option value="${type.id}">${type.formType}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-stripped ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Report Name</th>
                                                        <th>Created By</th>
                                                        <th>Creation/Modification Date : Time </th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${listofAdhocReports}" var="adhoc">
                                                    <tr>
                                                    <td><a data-id="${adhoc.id}" class="open-Dialog"  data-toggle="modal" data-target="#myModal">${adhoc.reportName}</a></td>
                                                    <td>${adhoc.requestorName}</td>
                                                    <td><fmt:formatDate value="${adhoc.createdDate}" type="both" 
                                                       dateStyle="short" timeStyle="short" /> </td>
                                                       <c:url value="/selfHelp/editadhocReport/${adhoc.id}" var="edit_url"/>
                                                        <c:url value="/selfHelp/deleteadhocReport/${adhoc.id}" var="delete_url"/>
                                                      
                                                     <td><a href="${edit_url}">edit</a>&nbsp;&nbsp;<a href="${delete_url}">delete</a></td>
                                                    </tr>
                                                   </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                     </div>
                                </div>
                                <c:if test="${not empty faultreplist}">
                                 <div class="form-group">
                                            <table class="ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <c:forEach items="${tableheaders}" var="headers">
                                                        <th>${headers}</th>
                                                        </c:forEach>
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                <c:forEach items="${faultreplist}" var="faultreport">
                                                <tr>
                                                <c:forEach items="${faultreport}" var="object" >
                                                    
                                                   <td>${object}</td>
                                                    
                                                    </c:forEach>
                                                    </tr>
                                                   </c:forEach>
                                                   </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                       
                                        </c:if>
                            </form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
                 <section class="page-section">
                     <c:url value="/selfHelp/runadhocReport" var="run_url"/>
                <form action="${run_url}">
              
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Please Enter Start date & End date for report?</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="hidden" id="rid" name="id">
                                    <input id="datetimepicker1" name="d1" type="text"  placeholder="From date" style="width:46%;padding:5px;border:none;height:20px;border-right:solid 1px" />
                                    <input id="datetimepicker2" name="d2" type="text"  placeholder="To date" style="width:46%;padding:5px;border:none;height:20px" />
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                <button type="submit" name="xls" class="btn">Generate Report</button>
                                <button type="submit" name="html" class="btn">Run</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
           
            <!-- Modal -->
            
        </section>
            
        </div>

        <!--End Form-->
        <!-- Foter -->
        <div class="clearfix"></div>
        <br /><br />
   
        <!-- End Footer -->

    </div>
  

</body>
</html>