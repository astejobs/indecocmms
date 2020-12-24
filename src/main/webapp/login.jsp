<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="no-js lt-ie9 ie8" lang="en"> <![endif]-->
<!--[if IE 9]> <html class="ie9" lang="en"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<head>
    <!-- Metas
    ================================================== -->
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
   <%--  <link rel="apple-touch-icon" sizes="57x57" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/favicon-16x16.png">
	<link rel="manifest" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff"> --%>

    <!-- Google fonts
    ================================================== -->
    <link href='http://fonts.googleapis.com/css?family=Oswald:300,400,500,700' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,600,700,800,300' rel='stylesheet' type='text/css' />
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
    
    <script src="${pageContext.request.contextPath}/resources/aste/js/bootstrap.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/modernizr.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.funnyText.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/masonry.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.easypiechart.min.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.appear.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.cslider.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.mixitup.min.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/classie.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/imagesloaded.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.parallax-1.1.3.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/parallaxInit.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.flexisel.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.mousewheel.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/perfect-scrollbar.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/carousel-slider.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.magnific-popup.min.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.flexslider-min.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.easing.1.3.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/tweetable.jquery.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.timeago.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/jquery.placeholder.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/scripts.js"></script> <script src="${pageContext.request.contextPath}/resources/aste/js/switcher.js"></script> <script> $(document).ready(function () { $('#loginForm').click(function () { $('#innerForm p').toggle(); }); }); </script> <script> jQuery(document).ready(function() { $('#innerForm p').hide(); // Pie Charts 'use strict'; var pieChartClass = 'pieChart', pieChartLoadedClass = 'pie-chart-loaded'; function initPieCharts() { var chart = $('.' + pieChartClass); chart.each(function() { $(this).appear(function() { var $this = $(this), chartBarColor = ($this.data('bar-color')) ? $this.data('bar-color') : "#0754a4", chartBarWidth = ($this.data('bar-width')) ? ($this.data('bar-width')) : 150 if( !$this.hasClass(pieChartLoadedClass) ) { $this.easyPieChart({ animate: 2000, size: chartBarWidth, lineWidth: 6, scaleColor: false, trackColor: "#eeeeee", barColor: chartBarColor, }).addClass(pieChartLoadedClass); } }); }); } initPieCharts(); }); </script> <script type="text/javascript"> $(document).ready(function() { 'use strict'; $('.funny-text').funnyText({ speed: 700, activeColor: '#0754a4', color: '#fff', fontSize: '1em', borderColor: 'none' });}); </script>
</head>
<body data-twttr-rendered="true" data-spy="scroll" data-target="#my-nav">
    
    <!-- pre loader -->
    <div id="preloader">
        <div id="status">&nbsp;</div><!-- /status -->
    </div>
    
    
    
    <!-- Banner section-->
   <section class="banner" id="header">
        <div class="banner-pattern">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <!--logo-->
                        <div class="main-photo" id="header-photo">
                            <img class="pulsate-opacity" src="${pageContext.request.contextPath}/resources/assets/images/cmms-logo-blue.png" alt="" style="width:200px;height:50px;">
                        </div>
                     </div>
                      <div class="row">
                      	<!--Login Form-->
					    <div class="col-sm-12">
					        <div class="col-md-4 col-sm-12 offset-md-center jumbotron mx-sm-1" style="margin-top:40px;">
<!-- 					           <a id="loginForm" href="javascript:void(0)"  style="background-color:#ff0000;padding:5px 10px;text-decoration:none;color:#fff"> Login</a> -->
					            <div id="showForm" style="padding-top:30px;padding-bottom:30px;">
					                
					                    <div id="innerForm">
					  			           <c:url value="/login" var="loginurl"/>
					            		       <form  method="post" action="${loginurl}" class="form contact-form">    
							                        <div class="form-group">
							                        	<input type="text" name="username" class="form-control" required placeholder="Username" />
							                        </div>
							                        <div class="form-group">
							                        	<input type="password" name="password" class="form-control" pattern=".{3,100}" required placeholder="Password" />
							                        </div>
							                        <p>
								                    	<c:if test="${not empty  param.error}">
											                <label id="err" style="color:red">Incorrect Username or Password</label>
									              		</c:if>
								                    </p>
							                        <div class="form-group">
							                        	<button type="submit" class="form-control" style="background-color:#0754a4;color:#fff" value="Login" id="login_btn">Login</button>
							                        </div>
								                    
					                    	   </form>
					                     </div>
					                
					            </div>
					        </div>
					     </div>
					    <!--End Login Form-->
                      </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End of Banner -->
   
	<script>
		$(document).ready(function(){
			$('#LoginForm').click(function(){
				$('#innerForm').toggle();
			})
		})
		setTimeout(function() {
			  $('#err').fadeOut('slow');
			},5000);
	</script>  
   </body>
</html>
