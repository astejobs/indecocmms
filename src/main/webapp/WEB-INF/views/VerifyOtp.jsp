<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<!--[if IE 8]> <html class="no-js lt-ie9 ie8" lang="en"> <![endif]-->
<!--[if IE 9]> <html class="ie9" lang="en"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<head>

    <!-- Metas ================================================== -->
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, user-scalable=no, target-densitydpi=device-dpi" />
    <meta name="description" content="LSME Login" />
    <meta name="keywords" content=" LSME Login" />
    <meta name="author" content="Emeer Meer" />
    
    <!-- Page Title
    ================================================== -->
    <title>i-CMMS &#9867; (Indeco-Computerised Maintenance Management System)</title>

    <!-- Favicons -->
     <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/favicon.png">

    <!-- Google fonts
    ================================================== -->
    <link href='http://fonts.googleapis.com/css?family=Oswald:300,400,500,700' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,600,700,800,300' rel='stylesheet' type='text/css' />
    <link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Russo+One&display=swap" rel="stylesheet">
	
    <script src="${pageContext.request.contextPath}/resources/aste/js/jquery-1.10.2.min.js"></script>
   
    <!-- bootstrap v3.0.2
    ================================================== -->
    <link href="${pageContext.request.contextPath}/resources/aste/css/bootstrap.css" rel="stylesheet" />

    <!--font-awesome
    ================================================== -->
    <link href="${pageContext.request.contextPath}/resources/aste/css/font-awesome.css" rel="stylesheet" />

    <!-- CSS Custom
    ================================================== -->
    <link href="${pageContext.request.contextPath}/resources/aste/css/style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/aste/css/perfect-scrollbar.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/aste/css/magnific-popup.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/aste/css/color-default.css" rel="stylesheet" />
    <link href="${pageContext.servletContext.contextPath}/resources/assets/css/error.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/aste/js/bootstrap.js"></script> 
    
    
    <style>
		#partitioned {
		  padding-left: 15px;
		  letter-spacing: 35px;
		  border: 0;
		  background-image: linear-gradient(to left, black 70%, rgba(255, 255, 255, 0) 0%);
		  background-position: bottom;
		  background-size: 50px 1px;
		  background-repeat: repeat-x;
		  background-position-x: 35px;
		  width: 220px;
		  min-width: 220px;
		  font-weight:300;	  
		  font-family: 'Russo One', sans-serif;	
		}
		
		#divInner{
		  left: 0; 
		  position: sticky;
		}
		
		#divOuter{
		  width: 200px; 
		  overflow: hidden;
		}
		.countdown {
			font-size:12px;
			font-family: 'Russo One', sans-serif;
			margin-top:-6px;
		}
    </style>
</head>
<body data-twttr-rendered="true" data-spy="scroll" data-target="#my-nav">
    
    <!-- pre loader -->
    <!-- <div id="preloader">
        <div id="status">&nbsp;</div>/status
    </div> -->
    
    
    
    <!-- Banner section-->
   <section class="banner w-100" id="header" style="position:fixed;top:0;right:0;left:0">
        <div class="banner-pattern">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <!--logo  pulsate-opacity -->
                        <div class="main-photo" id="header-photo">
                            <img class="pulsate-opacity" src="${pageContext.request.contextPath}/resources/assets/images/cmms-logo-blue.png" alt="" style="width:200px;height:50px;">
                        </div>
                     
                      <div class="row">
                      	<!--Login Form offset-md-center-->
					    <div class="col-sm-12">
					        <div class="col-md-6 col-sm-12 jumbotron mx-sm-1" style="margin-top:30px;margin-left:25.03%;">
					           
					            <div id="showForm" >
					                
					                 <p style="font-size:11px">OTP has been sent to your email address, please enter 4 digit OTP to continue.</p>
					                 
						               <label id="success" style="color:green; font-size:14px;display:none;">OTP Sent Successfully</label>
				              		<c:if test="${not empty error}">
						               <label id="otp_resend" style="color:red; font-size:14px;">This code is not valid.</label>
				              		</c:if>
					                    <div id="innerForm">

					  			           <c:url value="/verify" var="url"/>
					            		       <form:form  method="post" action="${url}" commandName="verifyCode" class="form contact-form" id="verifyForm"> 
					            		       <p class="lead">Enter Your OTP Here</p>
							                        <div style="align-items:center;width:100%;margin-left:50px">							                        
								                        <div id="divOuter">
															<div id="divInner">
																 <div class="form-group" style="margin-left: 7%;">
															<input type="hidden" id="contextPath"  value="${pageContext.request.contextPath}"/>
																 
								                        			<form:input type="text" path="code" maxlength="4" id="partitioned" autocomplete="off" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required="required" />
								                        			<form:input type="hidden" id="username"  path="username"/>
								                        		</div>
															</div>
														</div>
							                       </div>
							                        <div class="form-group" style="margin-top:20px;">
							                        	<button type="submit" class="form-control" style="background-color:#0754a4;color:#fff" value="Verify" id="verify_btn">Verify</button>
							                        </div>
					                        <p style="font-size:12px">
					                        <a href="#" id="resend" >Re-send OTP</a>
					                        
					                    	 <span class="countdown"></span>
					                        </p>
					                        <p class="loading-icon-otp" style="font-size:14px;font-weight:bold;"> Sending...</p>
					                    	  </form:form>
					                     </div>
					                
					            </div>
					        </div>
					     </div>
					    <!--End Login Form-->
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End of Banner -->
    <script type="text/javascript">
   $(document).ready(function() {
	   //Hide Timer
	   $('.countdown').css({'display':'none'});
	   //Hide Loading Icon
	   $('.loading-icon-otp').css({'display':'none'});
	   
		 $("#resend").click(function(e) { 
			 console.log('clicked');
			 //Show Loading Icon
			 $('.loading-icon-otp').css({'display':'block'});
			//Hide Resend button
	         $('#resend').css({'display':'none'});
			 
			e.preventDefault();
			var username=$('#username').val();
			 var data = {"username": username};
			 console.log(data);
		    $.ajax({
		        url: "/api/resendCode",
		        type: "POST",
		        data:JSON.stringify(data),
		        contentType: "application/json",
		        dataType: "json",
		        complete: function(xhr, statusText){
		            console.log(xhr.status); 
		            if(xhr.status ==200){
		            	$('.loading-icon-otp').css({'display':'none'});
		            	$('#success').css({'display':''});
		            	$('#resend').attr({'pointer-events':'none'});//pointer-events:none;
			            setTimeout(function(){
			            	$('#success').css({'display':'none'});
			            },60000*2);			            
			            
			            //Show Timer
			            $('.countdown').css({'display':'block'});
			            showTimer();
			            //Hide timer After 2 Minutes
			            setTimeout(function(){			            	
			            	$('.countdown').css({'display':'none'});
			            	$('#resend').css({'display':'block'});
			            },60000*2);
			            
			            setTimeout(function(){
			            	$('#resend').removeAttr('pointer-events');
			            },120000*2);
		            }
		        }
		    }); 
		});
		 
		 
		 function showTimer() {
			 //var timer2 = "5:01";
			 var timer2 = "2:01";
			 var interval = setInterval(function() {
	
	
			   var timer = timer2.split(':');
			   //by parsing integer, I avoid all extra string processing
			   var minutes = parseInt(timer[0], 10);
			   var seconds = parseInt(timer[1], 10);
			   --seconds;
			   minutes = (seconds < 0) ? --minutes : minutes;
			   if (minutes < 0) clearInterval(interval);
			   seconds = (seconds < 0) ? 59 : seconds;
			   seconds = (seconds < 10) ? '0' + seconds : seconds;
			   //minutes = (minutes < 10) ?  minutes : minutes;
			   $('.countdown').html(minutes + ':' + seconds);
			   timer2 = minutes + ':' + seconds;
			 }, 1000);
		 }
	});
   </script>
	<script>	
	
	
		setTimeout(function() {
			  $('#err').fadeOut('slow');
			},5000);
		 
		/* 4 Digit OTP */
		/* var obj = document.getElementById('partitioned');
		obj.addEventListener('keydown', stopCarret); 
		obj.addEventListener('keyup', stopCarret); 

		function stopCarret() {
			if (obj.value.length > 3){
				setCaretPosition(obj, 3);
			}
		} */

		function setCaretPosition(elem, caretPos) {
		    if(elem != null) {
		        if(elem.createTextRange) {
		            var range = elem.createTextRange();
		            range.move('character', caretPos);
		            range.select();
		        }
		        else {
		            if(elem.selectionStart) {
		                elem.focus();
		                elem.setSelectionRange(caretPos, caretPos);
		            }
		            else
		                elem.focus();
		        }
		    }
		}
		
	</script>  
   </body>
</html>
