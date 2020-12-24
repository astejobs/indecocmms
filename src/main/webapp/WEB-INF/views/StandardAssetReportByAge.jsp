<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<body class="appear-animate"> 

        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Report By Age</li>
			  </ol>
			</nav>
			
 
        
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
        	
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Asset Reports
                    </h2>

                            <form class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Type</span>
                                            <select required class="input-md round form-control">
                                                <option>Select Equipment Type</option>
                                            </select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Sub Type</span>
                                            <select required class="input-md round form-control">
                                                <option>Select Equipment Sub Type</option>
                                            </select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building Name</span>
                                            <input type="text" name="building-name" id="building-name" class="input-md round form-control" placeholder="Building Name">
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                            <select required class="input-md round form-control">
                                                <option>Select Building</option>
                                            </select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <select required class="input-md round form-control">
                                                <option>Select Location</option>
                                            </select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Service Area</span>
                                            <input type="text" name="service-area" id="service-area" class="input-md round form-control" placeholder="Service Area" pattern=".{3,100}">
                                        </div>
                                        <div class="form-group">
                                            <span>Upto Age ( Yrs )</span>
                                            <input type="radio" name="choose_lifespan" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;By Age Range &nbsp;&nbsp;
                                            <input type="radio" name="choose_lifespan" />
                                        </div>
                                        <div>
                                            <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Life Span</span>
                                                <input type="number" name="life-span" id="life-span" class="input-md round form-control" placeholder="Life Span" pattern=".{3,100}">
                                            </div>
                                            <div class="row" style="margin:0px">
                                              <div class="form-group input-group" style="float:left">
	                                        		<span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
	                                                <input type="text" name="Start_date" id="Start_date" style="width:90%" class="input-md round form-control" placeholder="Start Date">&nbsp;&nbsp;&nbsp;
	                                                <img src="../assets/images/date/cal.png" onclick="javascript: NewCssCal('Start_date')" style="cursor:pointer;width:30px" />
                                               </div>
                                               <div class="form-group input-group">
	                                        		<span class="input-group-addon label-left" id="basic-addon2">End Date</span>
	                                                <input type="text" name="End_date" id="End_date" style="width:90%" class="input-md round form-control" placeholder="End Date">
	                                                <img src="../assets/images/date/cal.png" onclick="javascript: NewCssCal('End_date')" style="cursor:pointer;width:30px" />
	                                            </div>
                                             </div>
                                        <div class="clearfix"></div><br />
                                        <div class="form-group">
                                            <input type="submit" id="submit" class="btn" value="Submit" />
                                            <input type="button" id="btnexport" class="btn" value="Export to Excel" />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Search Result
                                        </h2>
                                        <div class="form-group">
                                            <table class="ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Equipment Type</th>
                                                        <th>Equipment SubType</th>
                                                        <th>Equipment Name</th>
                                                        <th>Building</th>
                                                        <th>Location</th>
                                                        <th>Model Number</th>
                                                        <th>Image</th>
                                                        <th>Age</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            
      </body>