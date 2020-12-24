<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    

    
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

    <script>


        $(document).ready(function () {
            $('input.timepicker').timepicker({});
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
   

        <!-- Navigation panel -->
       
        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="../assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-technician.html">Technician</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <style>
            a {
                text-decoration: none;
            }

            select {
                width: 45%;
                height: 45%;
            }

            .sbThree {
                width: 30%;
            }

            .sbFour {
                width: 40%;
            }

            td {
                width: 250px;
            }
        </style>
        <!---MENU-->
        
        <script type="text/javascript">

       
        //Filter Tab
			$(function(){
            $('#addTaskButton').on('click', function () {
                var count = $("#tasksTable  tbody  tr").length;
                var taskId = $('select[id=processorTasksId]').val();
                if (taskId == 0) {
                    return;
                }
                var task = $("#processorTasksId option:selected").text();
                if (taskId == '1')
                {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input class="face" value="'+ taskId + '" name="criteria[' + count + '].tableColumn.id" type="hidden" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input type="text" class="other-1" name="criteria[' + count + '].valuee" style="width:200px"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '2') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input class="face" type="hidden" value="' + taskId + '" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input type="text" class="other-1" name="criteria[' + count + '].valuee" style="width:200px"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '3') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input type="hidden" class="face" value="' + taskId + '" name="criteria[' + count + '].tableColumn.id">' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Department 1</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '4') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id">' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '5') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input type="text" class="other-1" name="criteria[' + count + '].valuee" id="criteria[' + count + '].value" style="width:200px"  placeholder="Reported Date"/><img src="../assets/images/date/cal.png" onclick="javascript: NewCssCal(\'criteria[' + count + '].value\',\'ddMMyyyy\')" style="cursor:pointer;width:20px" /></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '6') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face"name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Location 1</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '7') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Building</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '8') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" />' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '9') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Fault Code</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '10') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Priority</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '11') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '12') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Main Grp</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '13') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '14') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '15') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '16') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '17') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '18') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Client</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '19') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '20') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }

                if (taskId == '21') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class=".timepicker" name="criteria[' + count + '].value" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }

                if (taskId == '22') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '23') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Amdad</option></select></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '24') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '25') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><select class="other-1" name="criteria[' + count + '].valuee" style="width:200px"><option>Amdad</option></select></td>'
                        + '<td><input type="button" value="X"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }
                if (taskId == '26') {
                    $('#tasksTable').append('<tr><td class="taskIdCol" style="display:none">' + taskId + '</td>'
                        + '<td class="taskDescriptionCol">' + task + '<input value="' + taskId + '" type="hidden" class="face" name="criteria[' + count + '].tableColumn.id" >' + '</td>'
                        + '<td><select class="other" style="width:100px" name="criteria[' + count + '].conditionn"><option value="Equal">Equal</option><option value="Not">Not</option><option value="Like">Like</option></select></td>'
                        + '<td><input class="other-1" name="criteria[' + count + '].valuee" style="width:200px" type="text"></td>'
                        + '<td><a class="btn-tr-remove" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>');
                }

                $("#processorTasksId option:selected").remove();
            });
            $(document).on('click', '.btn-tr-remove', function (e) {
                e.preventDefault();

                if ($('#tasksTable tr').length > 1) {
                    $(this).closest('tr').remove();
                    $('select[class="other"]').attr("name", function (i) {
                        var getResult = ('criteria[' + i + '].conditionn');
                        return (getResult);
                    });
                    $('input[class="other-1"]').attr("name", function (i) {

                        var getResult = ('criteria[' + i + '].value');
                        return (getResult);
                    });
                    $('input[class="face"]').attr("name", function (i) {

                        var getResult = ('criteria[' + i + '].tableColumn.id');
                        return (getResult);
                    });
               }
                return false;
            });
            $('#tasksTable').on('click', 'input[type="button"]', function () {

                var taskId = $(this).closest('tr').children('td.taskIdCol').text();
                var taskDescription = $(this).closest('tr').children('td.taskDescriptionCol').text();
                $(this).closest('tr').remove();
                console.log("id = " + taskId);
                console.log("desc = " + taskDescription);

                $('#processorTasksId')
                 .append($("<option></option>")
                            .attr("value", taskId)
                            .text(taskDescription));
            });
        });

        //End Filter Tab


        //Sorting
         $(document).ready(function () {
                $('#tasksTable01').on("change", "input[type=checkbox]", function () {
                    if (this.checked) {
                        $(this).attr('value', 'true');
                    } else {
                        $(this).attr('value', 'false');
                    }
                });
            })
        $(function () {
            $('#addTaskButton01').on('click', function () {
                var countr = $("#tasksTable01  tbody  tr").length;
                var taskId = $('select[id=processorTasksId01]').val();
                if (taskId == 0) {
                    return;
                }
                var task = $("#processorTasksId01 option:selected").text();
                if (taskId == '1')(
                    $('#tasksTable01').append('<tr id="sort[01]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                   )

               );
                if (taskId == '2') (
                    $('#tasksTable01').append('<tr id="sort[02]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '3') (
                    $('#tasksTable01').append('<tr id="sort[03]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '4') (
                    $('#tasksTable01').append('<tr id="sort[04]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '5') (
                    $('#tasksTable01').append('<tr id="sort[05]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '6') (
                    $('#tasksTable01').append('<tr id="sort[06]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '7') (
                   $('#tasksTable01').append('<tr id="sort[07]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                  + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                  + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                  + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                  + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
               )
              );
                if (taskId == '8') (
                    $('#tasksTable01').append('<tr id="sort[08]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '9') (
                    $('#tasksTable01').append('<tr id="sort[09]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '10') (
                    $('#tasksTable01').append('<tr id="sort[10]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '11') (
                    $('#tasksTable01').append('<tr id="sort[11]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '12') (
                    $('#tasksTable01').append('<tr id="sort[12]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '13') (
                    $('#tasksTable01').append('<tr id="sort[01]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '14') (
                    $('#tasksTable01').append('<tr id="sort[14]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '15') (
                    $('#tasksTable01').append('<tr id="sort[15]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '16') (
                    $('#tasksTable01').append('<tr id="sort[16]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '17') (
                    $('#tasksTable01').append('<tr id="sort[17]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '18') (
                    $('#tasksTable01').append('<tr id="sort[18]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '19') (
                   $('#tasksTable01').append('<tr id="sort[19]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                  + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                  + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                  + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                  + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
               )
              );
                if (taskId == '20') (
                    $('#tasksTable01').append('<tr id="sort[20]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '21') (
                    $('#tasksTable01').append('<tr id="sort[21]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '22') (
                    $('#tasksTable01').append('<tr id="sort[22]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '23') (
                    $('#tasksTable01').append('<tr id="sort[23]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '24') (
                    $('#tasksTable01').append('<tr id="sort[24]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '25') (
                   $('#tasksTable01').append('<tr id="sort[25]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                  + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                  + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                  + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                  + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
               )
              );
                if (taskId == '26') (
                    $('#tasksTable01').append('<tr id="sort[26]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '27') (
                    $('#tasksTable01').append('<tr id="sort[27]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                if (taskId == '28') (
                    $('#tasksTable01').append('<tr id="sort[28]"><td class="taskIdCol01" style="display:none">' + taskId + '</td>'
                   + '<td class="taskDescriptionCol01">' + task + '<input type="hidden" class="face-1" value="' + taskId + '" name="sort[' + countr + '].Column.id" />' + '</td>'
                   + '<td><input type="radio" name="sort[' + countr + '].sortOrder" class="Casc" value="ASC">Asc &nbsp;<input type="radio" name="sort[' + countr + '].sortOrder" class="descd" value="DESC">Desc</td>'
                   + '<td><input type="checkbox" class="ch-Class" name="sort['+countr+'].groupby"></td>'
                   + '<td><a class="btn-tr-remove-01" style="color:black;margin-left: 25px;cursor:pointer"><i style="cursor:pointer" class="fa fa-trash"> </i></a></td></tr>'
                )
               );
                $("#processorTasksId01 option:selected").remove();

            });
            $(document).on('click', '.btn-tr-remove-01', function (e) {
                e.preventDefault();

                if ($('#tasksTable01 tr').length > 1) {
                    $(this).closest('tr').remove();
                    $('input[type="radio"][class="Casc"]').attr("name", function (i) {
                        var getResult = ('sort[' + i+ '].sortOrder');
                        return (getResult);
                    });
                    $('input[type="radio"][class="descd"]').attr("name", function (i) {
                        var getResult = ('sort[' + i + '].sortOrder');
                        return (getResult);
                    });
                    $('input[type="checkbox"][class="ch-Class"]').attr("name", function (i) {
                        var getResult = ('sort[' + i + '].orderby');
                        return (getResult);
                    });
                    $('input[class="face-1"]').attr("name", function (i) {
                        var getResult = ('sort[' + i + '].Column.id');
                        return (getResult);
                    });
                }
                return false;
            });

            $('#tasksTable01').on('click', '.btn-tr-remove-01', function () {

                var taskId = $(this).closest('tr').children('td.taskIdCol01').text();
                var taskDescription = $(this).closest('tr').children('td.taskDescriptionCol01').text();
                $(this).closest('tr').remove();
                console.log("id = " + taskId);
                console.log("desc = " + taskDescription);

                $('#processorTasksId01')
                 .append($("<option></option>")
                            .attr("value", taskId)
                            .text(taskDescription));
            });
        //Emd Sorting
        });
        //End Sorting

        </script>
        <!--End Menu-->
        <!--Form-->
       <div  style="height:100px;position:relative"></div>
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        My Report Creation
                    </h2>

                    <!-- Contact Form -->
                    <div class="row">
                        <div class="col-md-8">
                             <c:url value="/selfHelp/BuildReport" var="buildreport"/>
                            <form:form method="post" action="${buildreport}" class="form contact-form" id="contact_form" commandName="adhocreport">
                            <form:hidden path="id"/>
                            <form:hidden path="adhocReportForm.id"/>
                            <form:hidden path="reportName"/>
                            <form:hidden path="description"/>
                            <form:hidden path="requestorName"/>
                            <form:hidden path="user.id"/>
                            <form:hidden path="workspace.id"/>
                                <div class="bs-example">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#columns">Columns</a></li>
                                        <li><a data-toggle="tab" href="#filter">Filter</a></li>
                                        <li><a data-toggle="tab" href="#sorting">Sorting & Grouping</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="columns" class="tab-pane fade in active">
                                            <h3>Move selected fields to right box.</h3>
                                            <script>
                                                $(document).ready(function () {
                                                    $("body").on('click', '#tOne tbody .stuList', function (e) {
                                                        var counter = $("#tbl2  tbody  tr").length - 1;
                                                        if ($(".ClsStuList td input:checked").length > 0) {
                                                            $(".ClsStuList td input:checked").each(function () {
                                                                tr = $(this).parent().parent();
                                                                tr.find("td:nth-child(1)").append(
                                                                    '<input type="hidden" class="other-1" value="' + $(this).closest('tr').attr('id') + '" name="columnList[' + counter + '].tableColumn.id">'
                                                                );
                                                                tr.append($("<td>", {
                                                                    class: "remStu",
                                                                    html: "<a><img src=\"../assets/images/trash..png\" style=\"height:20px;width:20px\"></a>",
                                                                    style: "text-decoration:underline;cursor:pointer",

                                                                }));
                                                                tr.find("input:checked").css("display", 'none');
                                                                $(".newClsStuList").append(tr.clone());
                                                                tr.find("td:nth-child(2)").remove();
                                                                tr.remove();
                                                            });
                                                            FadeIn();
                                                        }
                                                    })

                                                    $("body").on('click', '.remStu', function (e) {
                                                        tr = $(this).parent();
                                                        tr.find(".remStu").remove();
                                                        tr.find(".other-1").remove();
                                                        tr.find("input:checked").css("display", "block");
                                                        tr.find("input:checked").attr("checked", false);
                                                        $(".ClsStuList").append(tr.clone());
                                                        
                                                        tr.remove();
                                                        FadeIn();
                                                        $('input[class="other-1"]').attr("name", function (i) {

                                                            var getResult = ('columnList[' + (i) + '].tableColumn.id');
                                                            return (getResult);
                                                        });
                                                    })
                                                });

                                                function FadeIn() {
                                                    $(".ClsStuList, .newClsStuList").hide().fadeIn();
                                                };

                                            </script>
                                            <p>
                                                <style>
                                                    #tOne tr td{
                                                        padding:10px;
                                                        width:500px
                                                    }
                                                    #tbl2 tr td{
                                                        padding:10px;
                                                        width:500px
                                                    }
                                                </style>
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <table class="fntTen ClsStuList" id="tOne"  style="border:1px solid #79e15b">
                                                            <tbody>
                                                                <tr class="thClas">
                                                                    <th></th>
                                                                    <th></th>
                                                                </tr>
                                                                <c:forEach items="${listOfColumns}" var="col">
                                                                <tr id="${col.id}" style="display: table-row;">
                                                                    <td>${col.columnDesc}</td>
                                                                    <td align="center" width="40px">
                                                                        <input type="checkbox" data-value="FR.NO."
                                                                               id="stu_id[]" class="stuList" />
                                                                    </td>
                                                                </tr>
                                                                </c:forEach>
                                                                
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <table id="tbl2" class="fntTen newClsStuList" style="border:1px solid #79e15b">
                                                            <tr class="thClas">
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                            </p>
                                        </div>
                                        <div id="filter" class="tab-pane fade">
                                            <p>
                                                <select id="processorTasksId" multiple="multiple">
                                                  <c:forEach items="${listOfColumns}" var="col">
                                                    <option value="${col.id}">${col.columnDesc}</option>
                                                    </c:forEach>
                                                    
                                                </select>
                                                <table id="tasksTable">
                                                    <thead>
                                                        <tr>
                                                            <th>Columns</th>
                                                            <th>Filter</th>
                                                            <th>Value</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody></tbody>
                                                </table>
                                            </p>
                                            <p>
                                                <input type="button" id="addTaskButton" value="Add" />
                                            </p>
                                        </div>
                                        <div id="sorting" class="tab-pane fade">
                                            <p>
                                                <select id="processorTasksId01" multiple="multiple">
                                                     <c:forEach items="${listOfColumns}" var="col">
                                                    <option value="${col.id}">${col.columnDesc}</option>
                                                    </c:forEach>
                                                </select>
                                                <table id="tasksTable01">
                                                    <thead>
                                                        <tr>
                                                            <th>Columns</th>
                                                            <th>Sort (Asc&nbsp;<input type="radio" id="asc" name="asc">Dsc&nbsp;<input type="radio" id="dsc" name="asc">)</th>
                                                            <th>Group By</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody></tbody>
                                                </table>
                                            </p>
                                            <p>
                                                <input type="button" id="addTaskButton01" value="Add" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <input type="submit" value="Save">
                            </form:form>
                       
                </div>
           
        </div>

        <!--End Form-->
        <!-- Foter -->
       
        <!-- End Footer -->

    </div>
    <!-- End Page Wrap -->
    <!-- JS -->

</body>
</html>