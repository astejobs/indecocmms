<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>
<html>
<head>
<title>i-CMMS &#9867; (Indeco-Computerised Maintenance Management System)</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
  
    <meta name="author" content="Roman Kirichik">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    
    <!-- Favicons -->    
    <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/resources/assets/images/favicon/favicon.png">
    <script src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
    <style>.outerUl{width:auto;padding:5px;}.outerUl ul>li>a {text-decoration:none;padding: 10px;} .innerUl{width:auto;padding:5px}.innerUl ul >li> a{text-decoration:none;padding: 10px 20px;width:200px} .imageRotateHorizontal{ -moz-animation: spinHorizontal 4.8s infinite linear; -o-animation: spinHorizontal 4.8s infinite linear; -webkit-animation: spinHorizontal 4.8s infinite linear; animation: spinHorizontal 4.8s infinite linear; }@keyframes spinHorizontal { 0% { transform: rotateY(0deg); } 100% { transform: rotateY(360deg); } }</style>
  	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/aste/js/mustache.js"></script>
    <!-- CSS -->
    <link rel=stylesheet type="text/css" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/styleNew.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/mtree.css" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/custom.css" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/error.css" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/projectjs/fielderror.css" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/footable.bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/assets/css/red.css">
    
     <!-- New DatePicker css-->
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

	 <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.bvalidator.min.js"  > </script>
	 <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/default.min.js"  > </script>
	 <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/red.js"  > </script>
	 
		 
	 <!-- New DatePicker js-->
	 <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.12.4.js"></script>
     <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-ui.js"></script>
     <script type="text/javascript">
     
    	$(document).ready(function() {
     		$("#From_date").datepicker({ dateFormat: 'dd-mm-yy' });
            $("#To_date").datepicker({ dateFormat: 'dd-mm-yy' });
            $("#Schedule_Date").datepicker({ minDate: 0, dateFormat: 'dd-mm-yy' });
            
            $("#From_date").change(function() { console.log($("#From_date").val());
            	 var fromDt = $("#From_date").val();
            	 jQuery("#To_date").datepicker("destroy");
            	 $("#To_date").datepicker({ minDate: fromDt, dateFormat: 'dd-mm-yy' });
            });
            $("#To_date").change(function() { console.log($("#To_date").val());
		       	 var toDt = $("#To_date").val();
		       	 jQuery("#From_date").datepicker("destroy");
		       	 $("#From_date").datepicker({ maxDate: toDt, dateFormat: 'dd-mm-yy' });
		    });
     	});

    </script>
    
     
    <!-- Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

</head>
<body class="body-bg">
<header>
	<t:insertAttribute name="header" />
</header>
<t:insertAttribute name="body" />
<t:insertAttribute name="footer" />
</body>
</html>