<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/canvasjs.min.js"></script>
     <script type="text/javascript">
		window.onload = function () {
			var chart = new CanvasJS.Chart("chartContainer", {
				title: {
					 
				},
				data: [{
					type: "column",
					dataPoints: [
						{ y: 45, label: "Jab" },
						{ y: 31, label: "Feb" },
						{ y: 52, label: "March" },
						{ y: 10, label: "April" },
						{ y: 46, label: "May" },
						{ y: 30, label: "June" },
						{ y: 50, label: "July" },
						{ y: 28, label: "Aug" },
						{ y: 45, label: "Sep" },
						{ y: 15, label: "Oct" },
						{ y: 48, label: "Nov" },
						{ y: 38, label: "Dec" },
					]
				}]
			});
			chart.render();
		    var pieChart = new CanvasJS.Chart("piechartContainer",
            {
                theme: "theme1",
                title: {
                   
                },
                data: [
                {
                    type: "pie",
                    showInLegend: true,
                    toolTipContent: "{y} - #percent %",
                    yValueFormatString: "#0.#,,. Million",
                    legendText: "No Fault reports found for the current month..!",
                    dataPoints: [
                        { y: 1256852 },
                        { y: 2256852 },
                        { y: 1056852 },
                    ]
                }
                ]
            });
		    pieChart.render();
		    var pieChart = new CanvasJS.Chart("lineContainer",
		            {
		                theme: "theme1",
		                title: {
		                   
		                },
		                data: [
		                {
		                    type: "line",
		                    showInLegend: true,
		                    toolTipContent: "{y} - #percent %",
		                    yValueFormatString: "#0.#,,. Million",
		                    legendText: "No Fault reports found for the current month..!",
		                    dataPoints: [
		                        { y: 1256852 },
		                        { y: 2256852 },
		                        { y: 1056852 },
		                    ]
		                }
		                ]
		            });
				    pieChart.render();
				    var pieChart = new CanvasJS.Chart("dotContainer",
				            {
				                theme: "theme2",
				                title: {
				                   
				                },
				                data: [
				                {
				                    type: "doughnut",
				                    showInLegend: true,
				                    toolTipContent: "{y} - #percent %",
				                    yValueFormatString: "#0.#,,. Million",
				                    legendText: "No Fault reports found for the current month..!",
				                    dataPoints: [
				                        { y: 1256852 },
				                        { y: 2256852 },
				                        { y: 1056852 },
				                    ]
				                }
				                ]
				            });
						    pieChart.render();
		}
    </script>
</head>
<body class="appear-animate">
	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Overview</li>
			  </ol>
			</nav>
			
   <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

		                <h5 class="section-title font-alt  mb-70 mb-sm-40">
		                    Fault Reports for the Current Year
		                </h5>
		                <div id="chartContainer" style="left:10px;height: 300px; width: 80%;"></div>
            		</div>
            		<div class="col-xs-4 col-sm-4 col-lg-4 col-md-4">		
     	               <h5 class="section-title font-alt  mb-70 mb-sm-40">
        	            Fault Reports for the Current Year
            		   </h5>
            			<div id="piechartContainer" style="left:10px;height: 300px; width: 80%;"></div>
        			</div>
        			
        		</div>
        		<div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		  
		           </div>
         			<div class="col-xs-4 col-sm-4 col-lg-4 col-md-4">
		                <h5 class="section-title font-alt  mb-70 mb-sm-40">
		                    Fault Reports for the Current Year
		                </h5>
		                <div id="lineContainer" style="left:10px;height: 300px; width: 80%;"></div>
            		</div>
            		<div class="col-xs-4 col-sm-4 col-lg-4 col-md-4">		
     	               <h5 class="section-title font-alt  mb-70 mb-sm-40">
        	            Fault Reports for the Current Year
            		   </h5>
            			<div id="dotContainer" style="left:10px;height: 300px; width: 80%;"></div>
        			</div>
        			
        		</div>
        		
        	</div>	
        	</div>	
</body>
       
</html>
