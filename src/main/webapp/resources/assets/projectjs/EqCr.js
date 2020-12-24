$(document).ready(function () {
                                             
                                       /*     $("#prop").append('<tr><td><input type="text" class="baselineName" name="baseline[0].name"></td><td><input class="baselineMin" name="baseline[0].min" type="text"></td><td style="width:20%"><input class="baselineMin" name="baseline[0].max" type="text"></td><td><input type="text" class="baselineUnit" name="baseline[0].unit" /></td></tr>');
                                      */    
                                            $("#add_prop").click(function () {
                                                var counter = $("#prop  tbody  tr").length ;
     
                                                $("#prop").append('<tr><td><input type="text" class="baselineName" name="baseline['+counter+'].name"></td><td><input class="baselineMin" name="baseline['+counter+'].min" type="text"></td><td style="width:20%"><input class="baselineMin" name="baseline['+counter+'].max" type="text"></td><td><input type="text" class="baselineUnit" name="baseline['+counter+'].unit" /></td><td><a class="btn-remove-tr" style="color:black"><img style="width:20px;height:20px;" src="../assets/images/trash..png" /></a></td></tr>');

                                            });

                                            $('#prop').on('click', '.btn-remove-tr', function (e) {
                                                e.preventDefault();
                                                if ($('#prop tr').length > 1) {
                                                    $(this).closest('tr').remove();
                                                    $('input[class="baselineName"]').attr("name", function (i) {
                                                       var getResult = (i+1);
                                                        return ("baseline["+getResult+"].name");
                                                    });
                                                    $('input[class="baselineMin"]').attr("name", function (i) {
                                                       var getResult = (i+1);
                                                        return ("baseline["+getResult+"].min");
                                                    });
                                                    $('input[class="baselineMin"]').attr("name", function (i) {
                                                        var getResult = (i+1);
                                                         return ("baseline["+getResult+"].min");
                                                     });
                                                    $('input[class="baselineUnit"]').attr("name", function (i) {
                                                        var getResult = (i+1);
                                                         return ("baseline["+getResult+"].min");
                                                     });
                                                }
                                                return false;
                                             });
                                        })