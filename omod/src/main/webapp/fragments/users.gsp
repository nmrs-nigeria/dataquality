<%
ui.includeJavascript("dataquality", "jquery-3.3.1.js")
ui.includeJavascript("dataquality", "jquery.dataTables.min.js")
ui.includeJavascript("dataquality", "datatables.min.js")
ui.includeJavascript("dataquality", "buttons.flash.min.js")
ui.includeJavascript("dataquality", "jszip.min.js")
ui.includeJavascript("dataquality", "pdfmake.min.js")
ui.includeJavascript("dataquality", "vfs_fonts.js")
ui.includeJavascript("dataquality", "buttons.html5.min.js")
ui.includeJavascript("dataquality", "buttons.print.min.js")
ui.includeCss("dataquality", "buttons.dataTables.min.css")
ui.includeCss("dataquality", "jquery.dataTables.min.css")

def id = config.id
%>
<%=ui.resourceLinks()%>
<div class="container">
    <table id="myTable" class="display">
        <thead>
            <tr><th colspan='6' style='text-align: left; font-size: 16px;'>Clinical</th></tr>
        </thead>
        <thead style="font-size: 13px;">
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export </th>
            </tr>

        </thead>
        <tbody>
            <tr>
                <td>I</td>
                <td>Proportion of all active patients with a documented educational status </td>
                <td id="activePtsWithDocEduStat" ><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="activePtsCount"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEduStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)}" class="button" title="Export records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of all active patients with a documented marital status </td>
                <td id="activePtsWithDocMarStat"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="activePtsCount"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentMarStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>III</td>
                <td>Proportion of all active patients with a documented occupational status </td>
                <td id="activePtsWithDocOccuStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="activePtsCount"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentOccuStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>IV</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented age and/or Date of Birth</td>
                <td id="newlyArtWithDocDob"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocDob"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>V</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented Sex</td>
                <td id="newlyArtWithDocGender"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocGender"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VI</td>
                <td>Proportion of patients newly started on ART in the last 6 months with registered address/LGA of residence </td>
                <td id="newlyArtWithDocAddress"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocAddress"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VII</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented date of HIV diagnosis</td>
                <td id="newlyArtWithDocDiagnosis"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocDiagnosis"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VIII</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented HIV enrollment date</td>
                <td id="newlyArtWithDocEnrollmentDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocEnrollmentDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>IX</td>
                <td> Number of All patients Newly started on ART in the last 6months with a documented HIV ART Start Date</td>
                <td id="newlyArtWithDocDrugPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocDrugPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="#" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>X</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented CD4 result </td>
                <td id="newlyArtWithDocCd4"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentNewlyArtDocCd4"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)}" class="button" title="Export records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XI</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented weight</td>
                <td id="recentClVisitDocWt"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClVisitDocWt"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <!--
                <td></td>
                <td>${ui.format()}</td>
                <td>${ui.format()}</td>
                <td>${ui.format()}</td>
                <td><a href=''>Get List</a></td>
            -->
            <tr>
                <td>XII</td>
                <td>Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC </td>
                <td id="recentClinicPediatricVisitDocMuac"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicPediatricVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentRecentClinicVisitDocMuac"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XIII</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented WHO clinical stage</td>
                <td id="recentClVisitDocWHO"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClVisitDocWHO"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XIV</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented TB status</td>
                <td id="recentClVisitDocTBStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClVisitDocTBStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XV</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented functional status</td>
                <td id="recentClVisitDocFunctionalStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClVisitDocFunctionalStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVI</td>
                <td >Proportion of patients newly started on ART in the last 6 months with initial ART regimen</td>
                <td id="newlyArtInitialRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="newlyStartedOnART"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="perccentNewlyArtInitialRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVII</td>
                <td>Proportion of all patients with a clinic visit in the last 6 months that have documented next scheduled appointment date</td>
                <td id="recentClVisitWithNextAppDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="recentClinicVisit"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClVisitWithNextAppDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVIII</td>
                <td>Proportion of all inactive patients with a documented exit reason</td>
                <td id="totalInactiveDocReason"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="totalInactive"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentInactiveDocReason"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            
            <tr>
                <th colspan='6'  style='text-align: left; font-size: 16px;'>Pharmacy</th>
            </tr> 
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export</th>
            </tr>
            <tr>
                <td>I</td> 
                <td>Proportion of patients with a documented ART regimen quantity in the last drug refill visit</td>
                <td id="totalArvWithQty"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="lastArvPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentTotalArvWithQty"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with a documented ART regimen duration in the last drug refill visit</td>
                <td id="totalArvWithDuration"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="lastArvPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentTotalArvWithDuration"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>III</td> 
                <td>Proportion of patients with documented ART regimen in the last drug refill visit</td>
                <td id="totalArvWithArtRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="lastArvPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentArvWithArtRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>IV</td> 
                <td>Proportion of patients with ART regimen duration not more than six(6) months  in the last drug refill visit</td>
                <td id="totalPtsWithMoreThan180"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="lastArvPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentPtsWithMoreThan180"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>

            <tr>
                <th colspan='6'  style='text-align: left; font-size: 16px;'>Laboratory</th>
            </tr> 
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export</th>
            </tr>
            <tr>
                <td>I</td>
                <td>Proportion of eligible patients with documented Viral Load results done in the last one year</td>
                <td id="totalEligibleWithResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="totalEligible"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleWithResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)}" class="button" title="View records with issues">View issues</a></td>       
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with Viral Load result that had documented specimen collection date </td>
                <td id="totalResultWithCollection"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="totalEligible"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleWithSampleCollected"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)}" class="button" title="Export records with issues">Export</a></td>       
            </tr>
            
            <tr>
                <td>III</td> 
                <td>Proportion of patients with Viral load results with a documented date sample was received at the PCR lab</td>
                <td id="totalEligibleWithReceivedAtLab"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="totalEligible"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleWithReceivedAtLab"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)}" class="button" title="Export records with issues">Export</a></td>       
            </tr>



        </tbody>
    </table>
</div>




<script>
    jq = jQuery;

    jq(document).ready(function() {
    jq('#myTable').DataTable( {
    dom: 'Bfrtip',
    buttons: [
    'copy', 'csv', 'excel', 'pdf', 'print'
    ]
    } );
    } );
</script>
<style>
    .dt-buttons{
    float: right;
    }
    #apps{
    margin-bottom: 60px;
    }
    #myTable {
    width: 90%;
    margin-left: 5%;
    }
    .buttons-html5{
    text-decoration: none;
    margin-left: 5px;
    margin-bottom: 10px;
    text-align: center;
    border-radius: 3px;
    background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #5b57a6), color-stop(100%, #5b57a6));
    background: -webkit-linear-gradient(top, #5b57a6, #5b57a6);
    background: -moz-linear-gradient(top, #5b57a6, #5b57a6);
    background: -o-linear-gradient(top, #5b57a6, #5b57a6);
    background: -ms-linear-gradient(top, #5b57a6, #5b57a6);
    background: linear-gradient(top, #5b57a6, #5b57a6);
    background-color: #5b57a6;
    border: #5b57a6 1px solid;
    padding: 8px 20px;
    display: inline-block;
    line-height: 1.2em;
    color: white;
    cursor: pointer;
    min-width: 0;
    max-width: 300px;
    text-decoration: none;
    padding: 5px 6px;
    min-width: 70px;
    font-size: 0.9em;
    }

    #myTable_paginate{
    display: flex-inline;
    }
    #myTable_paginate li {
    margin:2px;
    padding:3px;
    text-decoration: none;
    text-align: center;
    border-radius: 3px;
    background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #5b57a6), color-stop(100%, #5b57a6));
    background: -webkit-linear-gradient(top, #5b57a6, #5b57a6);
    background: -moz-linear-gradient(top, #5b57a6, #5b57a6);
    background: -o-linear-gradient(top, #5b57a6, #5b57a6);
    background: -ms-linear-gradient(top, #5b57a6, #5b57a6);
    background: linear-gradient(top, #5b57a6, #5b57a6);
    background-color: #5b57a6;
    border: #5b57a6 1px solid;
    display: inline-block;
    color: white;
    cursor: pointer;
    width:auto;
    }

    #myTable_paginate li a{
    color:white;
    } 
</style>
<script type="text/javascript">
    jq = jQuery;
    var cohortAjaxUrl = '${ ui.actionLink("getCohortCounts") }';
   // getCohorts(cohortAjaxUrl);
   //educational status
   //setTimeout(function(){
        getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            jq("#activePtsWithDocEduStat").html(data["numerator"]);
            jq(".activePtsCount").html(data["denominator"]);
            jq("#percentEduStatus").html(data["percent"]+"%");   
       })
   //}, 1);
   
   
   //marital status status
  
   //setTimeout(function(){
        getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#activePtsWithDocMarStat").html(data["numerator"]);
            jq(".activePtsCount").html(data["denominator"]);
            jq("#percentMarStatus").html(data["percent"]+"%");   
       })
   //}, 1);
   
   
   //setTimeout(function(){
         //active patients with documented occupational status
        getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            console.log(data);
            jq("#activePtsWithDocOccuStatus").html(data["numerator"]);
            jq(".activePtsCount").html(data["denominator"]);
            jq("#percentOccuStatus").html(data["percent"]+"%");   
       })
   //}, 1);
   
   
  
   

   //started art in the last 6 months with documented dob
   getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocDob").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocDob").html(data["percent"]+"%");   
   })
   
    //started art in the last 6 months with documented dob
   getCohorts("${ui.format(constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocAddress").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocAddress").html(data["percent"]+"%");   
   })
   
   
    //started art in the last 6 months with documented gender
   getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocGender").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocGender").html(data["percent"]+"%");   
   })
   //started art in the last 6 months with documented date confirmed positive
   getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocDiagnosis").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocDiagnosis").html(data["percent"]+"%");   
   })

   //started art in the last 6 months with documented date hiv enrollment date
   getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocEnrollmentDate").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocEnrollmentDate").html(data["percent"]+"%");   
   })
   
   //started art in the last 6 months with arv pickup
   getCohorts("${ui.format(constants.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocDrugPickup").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocDrugPickup").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtWithDocCd4").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#percentNewlyArtDocCd4").html(data["percent"]+"%");   
   })
  
   
   getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClVisitDocWt").html(data["numerator"]);
        jq(".recentClinicVisit").html(data["denominator"]);
        jq("#percentClVisitDocWt").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClinicPediatricVisitDocMuac").html(data["numerator"]);
        jq(".recentClinicPediatricVisit").html(data["denominator"]);
        jq("#percentRecentClinicVisitDocMuac").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClVisitDocWHO").html(data["numerator"]);
        jq(".recentClinicVisit").html(data["denominator"]);
        jq("#percentClVisitDocWHO").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClVisitDocTBStatus").html(data["numerator"]);
        jq(".recentClinicVisit").html(data["denominator"]);
        jq("#percentClVisitDocTBStatus").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClVisitDocFunctionalStatus").html(data["numerator"]);
        jq(".recentClinicVisit").html(data["denominator"]);
        jq("#percentClVisitDocFunctionalStatus").html(data["percent"]+"%");   
   })
   
  getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#newlyArtInitialRegimen").html(data["numerator"]);
        jq(".newlyStartedOnART").html(data["denominator"]);
        jq("#perccentNewlyArtInitialRegimen").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#recentClVisitWithNextAppDate").html(data["numerator"]);
        jq(".recentClinicVisit").html(data["denominator"]);
        jq("#percentClVisitWithNextAppDate").html(data["percent"]+"%");   
   })

   getCohorts("${ui.format(constants.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalInactiveDocReason").html(data["numerator"]);
        jq(".totalInactive").html(data["denominator"]);
        jq("#percentInactiveDocReason").html(data["percent"]+"%");   
   })
   
   
   getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalArvWithQty").html(data["numerator"]);
        jq(".lastArvPickup").html(data["denominator"]);
        jq("#percentTotalArvWithQty").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalArvWithDuration").html(data["numerator"]);
        jq(".lastArvPickup").html(data["denominator"]);
        jq("#percentTotalArvWithDuration").html(data["percent"]+"%");   
   })
   getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalArvWithArtRegimen").html(data["numerator"]);
        jq(".lastArvPickup").html(data["denominator"]);
        jq("#percentArvWithArtRegimen").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalPtsWithMoreThan180").html(data["numerator"]);
        jq(".lastArvPickup").html(data["denominator"]);
        jq("#percentPtsWithMoreThan180").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalEligibleWithResult").html(data["numerator"]);
        jq(".totalEligible").html(data["denominator"]);
        jq("#percentEligibleWithResult").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalResultWithCollection").html(data["numerator"]);
        jq(".totalEligible").html(data["denominator"]);
        jq("#percentEligibleWithSampleCollected").html(data["percent"]+"%");   
   })
   
   getCohorts("${ui.format(constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)}", cohortAjaxUrl, function(response){
        var data = JSON.parse(response);
       
        jq("#totalEligibleWithReceivedAtLab").html(data["numerator"]);
        jq(".totalEligible").html(data["denominator"]);
        jq("#percentEligibleWithReceivedAtLab").html(data["percent"]+"%");   
   })
</script>










