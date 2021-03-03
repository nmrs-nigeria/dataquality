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
                <th>Action </th>
            </tr>

        </thead>
        <tbody>
            <tr>
                <td>I</td>
                <td>Proportion of all active patients with a documented educational status </td>
                <td id="activePtsWithDocEduStat" ><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="activePtsCount"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEduStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
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
                <td> Number of All patients Newly started on ART in the last 6months with a documented Drug pickup</td>
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
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)}" class="button" title="View records with issues">View Issues</a></td>
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
                <th>Action</th>
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
                <th>Action</th>
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
                <td class="totalWithResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleWithSampleCollected"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)}" class="button" title="View records with issues">View Issues</a></td>       
            </tr>
            
            <tr>
                <td>III</td> 
                <td>Proportion of patients with Viral load results with a documented date sample was received at the PCR lab</td>
                <td id="totalEligibleWithReceivedAtLab"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td class="totalWithResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleWithReceivedAtLab"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)}" class="button" title="View records with issues">View Issues</a></td>       
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
    var totalActivePatients = 0;
    var totalARTPatients = 0;
    var totalPatientsClinicVisit = 0;
    var totalPtsWithDocARVPickup = 0;
    var totalPtsEligibleForVl = 0;
    var totalPtsWithVl = 0;
   // getCohorts(cohortAjaxUrl);
   //educational status
   
   
     getCohorts2("${ui.format(constants.TOTAL_ACTIVE_PATIENTS)}", cohortAjaxUrl).then(function(response){
            var data = JSON.parse(response);
            totalActivePatients = data["totalActivePatients"];
            console.log("total active", totalActivePatients);
            getCohortRelatedToActivePatients();
            
            return getCohorts2("${ui.format(constants.STARTED_ART_LAST_6_MONTHS)}", cohortAjaxUrl); 
            
       })
       .then(function(response){
            var data = JSON.parse(response);
            totalARTPatients = data["totalPatientsStartedARTLast6Months"];
            
            getCohortRelatedToARTPatients();
            return getCohorts2("${ui.format(constants.HAD_CLINIC_VISIT_6_MONTHS)}", cohortAjaxUrl);
        
       })
       .then(function(response){
            var data = JSON.parse(response);
            totalPatientsClinicVisit = data["totalPatientsClinicVisit"];
            getCohortRelatedToClinicVisits();
            
            return  getCohorts2("${ui.format(constants.HAD_DOC_LAST_PICKUP)}", cohortAjaxUrl);
       })
       .then(function(response){
       
             console.log(response);
            var data = JSON.parse(response);
            totalPtsWithDocARVPickup = data["totalPtsWithDocARVPickup"];
            console.log(totalPtsWithDocARVPickup);
            getCohortRelatedToARVPickup();
            
            return getCohorts2("${ui.format(constants.ELIGIBLE_FOR_VIRAL_LOAD)}", cohortAjaxUrl);
       })
       .then(function(response){
            console.log(response);
            var data = JSON.parse(response);
            totalPtsEligibleForVl = data["totalPtsEligibleForVl"];
            console.log(totalPtsWithDocARVPickup);
            getCohortRelatedToVl();
       })
       .catch(function(error){
          console.log(error);
       })
       
      /*getCohorts("${ui.format(constants.STARTED_ART_LAST_6_MONTHS)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            totalARTPatients = data["totalPatientsStartedARTLast6Months"];
            
            getCohortRelatedToARTPatients();
       })
       
       getCohorts("${ui.format(constants.HAD_CLINIC_VISIT_6_MONTHS)}", cohortAjaxUrl, function(response){
            
            var data = JSON.parse(response);
            totalPatientsClinicVisit = data["totalPatientsClinicVisit"];
           
            getCohortRelatedToClinicVisits();
       })
       
     getCohorts("${ui.format(constants.HAD_DOC_LAST_PICKUP)}", cohortAjaxUrl, function(response){
            console.log(response);
            var data = JSON.parse(response);
            totalPtsWithDocARVPickup = data["totalPtsWithDocARVPickup"];
            console.log(totalPtsWithDocARVPickup);
            getCohortRelatedToARVPickup();
       })
      getCohorts("${ui.format(constants.ELIGIBLE_FOR_VIRAL_LOAD)}", cohortAjaxUrl, function(response){
            console.log(response);
            var data = JSON.parse(response);
            totalPtsEligibleForVl = data["totalPtsEligibleForVl"];
            console.log(totalPtsWithDocARVPickup);
            getCohortRelatedToVl();
       })
       getCohorts("${ui.format(constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#recentClinicPediatricVisitDocMuac").html(data["numerator"]);
            var denominator = data["denominator"];
            var percent = data["percent"];
            jq(".recentClinicPediatricVisit").html(denominator);
           // var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
            jq("#percentRecentClinicVisitDocMuac").html(percent+"%");   
       })*/
       
       
     function getCohortRelatedToActivePatients()
     {
     console.log("11");
        getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            jq("#activePtsWithDocEduStat").html(data["numerator"]);
            jq(".activePtsCount").html(totalActivePatients);
            var percent = ((data["numerator"] / totalActivePatients) * 100).toFixed(1);
            jq("#percentEduStatus").html(percent+"%");   
       });
       console.log("12");
       
       getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#activePtsWithDocMarStat").html(data["numerator"]);
            jq(".activePtsCount").html(totalActivePatients);
             var percent = ((data["numerator"] / totalActivePatients) * 100).toFixed(1);
            jq("#percentMarStatus").html(percent+"%");   
       });
       
       console.log("13");
       getCohorts("${ui.format(constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
           
            jq("#activePtsWithDocOccuStatus").html(data["numerator"]);
            jq(".activePtsCount").html(totalActivePatients);
            var percent = ((data["numerator"] / totalActivePatients) * 100).toFixed(1);
            jq("#percentOccuStatus").html(percent+"%");   
       })
       
       
       
     }
     
     
     function getCohortRelatedToARTPatients()
     {
        console.log("1");
        getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)}", cohortAjaxUrl, function(response){
            
        var data = JSON.parse(response);

            jq("#newlyArtWithDocDob").html(data["numerator"]);
            jq(".newlyStartedOnART").html(totalARTPatients);
             var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
            jq("#percentNewlyArtDocDob").html(percent+"%");  
       });
       console.log("2");
       getCohorts("${ui.format(constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            jq("#newlyArtWithDocAddress").html(data["numerator"]);
             var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
            jq(".newlyStartedOnART").html(totalARTPatients);
            jq("#percentNewlyArtDocAddress").html(percent+"%");   
       })
       console.log("3");
         getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)}", cohortAjaxUrl, function(response){
             var data = JSON.parse(response);
             jq("#newlyArtWithDocGender").html(data["numerator"]);
             jq(".newlyStartedOnART").html(totalARTPatients);
             var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
             jq("#percentNewlyArtDocGender").html(percent+"%");   
        })
        
        console.log("4");
        getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#newlyArtWithDocDiagnosis").html(data["numerator"]);
            jq(".newlyStartedOnART").html(totalARTPatients);
            var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
            jq("#percentNewlyArtDocDiagnosis").html(percent+"%");   
       })
       
       console.log("5");
       
         getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)}", cohortAjaxUrl, function(response){
             var data = JSON.parse(response);

             jq("#newlyArtWithDocEnrollmentDate").html(data["numerator"]);
             jq(".newlyStartedOnART").html(totalARTPatients);
             var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
             jq("#percentNewlyArtDocEnrollmentDate").html(percent+"%");   
        })
        
        console.log("6");
        getCohorts("${ui.format(constants.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#newlyArtWithDocDrugPickup").html(data["numerator"]);
            jq(".newlyStartedOnART").html(totalARTPatients);
            var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
            jq("#percentNewlyArtDocDrugPickup").html(percent+"%");   
       })
       
       console.log("7");
            getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                jq("#newlyArtWithDocCd4").html(data["numerator"]);
                jq(".newlyStartedOnART").html(totalARTPatients);
                var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
                jq("#percentNewlyArtDocCd4").html(percent+"%");   
          })
          console.log("8");
            getCohorts("${ui.format(constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                jq("#newlyArtInitialRegimen").html(data["numerator"]);
                jq(".newlyStartedOnART").html(totalARTPatients);
                var percent = ((data["numerator"] / totalARTPatients) * 100).toFixed(1);
                jq("#perccentNewlyArtInitialRegimen").html(percent+"%");   
           })
  
     }
     
     function getCohortRelatedToClinicVisits()
     {
        getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#recentClVisitDocWt").html(data["numerator"]);
            jq(".recentClinicVisit").html(totalPatientsClinicVisit);
            var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
            jq("#percentClVisitDocWt").html(percent+"%");   
       })
       
       
       
       getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#recentClVisitDocWHO").html(data["numerator"]);
            jq(".recentClinicVisit").html(totalPatientsClinicVisit);
            var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
            jq("#percentClVisitDocWHO").html(percent+"%");   
       })
            getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                jq("#recentClVisitDocTBStatus").html(data["numerator"]);
                jq(".recentClinicVisit").html(totalPatientsClinicVisit);
                var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
                jq("#percentClVisitDocTBStatus").html(percent+"%");   
           })
           getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                jq("#recentClVisitDocFunctionalStatus").html(data["numerator"]);
                jq(".recentClinicVisit").html(totalPatientsClinicVisit);
                var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
                jq("#percentClVisitDocFunctionalStatus").html(percent+"%");   
           })
           
           getCohorts("${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                jq("#recentClVisitWithNextAppDate").html(data["numerator"]);
                jq(".recentClinicVisit").html(totalPatientsClinicVisit);
                var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
                jq("#percentClVisitWithNextAppDate").html(percent+"%");   
           })

     }
     
     function getCohortRelatedToARVPickup()
     {
        getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#totalArvWithQty").html(data["numerator"]);
            var percent = ((data["numerator"] / totalPtsWithDocARVPickup) * 100).toFixed(1);
            jq(".lastArvPickup").html(totalPtsWithDocARVPickup);
            jq("#percentTotalArvWithQty").html(percent+"%");   
       })
       getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#totalArvWithDuration").html(data["numerator"]);
             var percent = ((data["numerator"] / totalPtsWithDocARVPickup) * 100).toFixed(1);
            jq(".lastArvPickup").html(totalPtsWithDocARVPickup);
            jq("#percentTotalArvWithDuration").html(percent+"%");   
       })
        getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            var percent = ((data["numerator"] / totalPtsWithDocARVPickup) * 100).toFixed(1);
            jq("#totalArvWithArtRegimen").html(data["numerator"]);
            jq(".lastArvPickup").html(totalPtsWithDocARVPickup);
            jq("#percentArvWithArtRegimen").html(percent+"%");   
       })
       getCohorts("${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);
            
            var percent = ((data["numerator"] / totalPtsWithDocARVPickup) * 100).toFixed(1);    
            jq("#totalPtsWithMoreThan180").html(data["numerator"]);
            jq(".lastArvPickupWithDuration").html(totalPtsWithDocARVPickup);
            jq("#percentPtsWithMoreThan180").html(percent+"%");   
       })
   
   
     }
     
     getCohorts("${ui.format(constants.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#totalInactiveDocReason").html(data["numerator"]);
            jq(".totalInactive").html(data["denominator"]);
            jq("#percentInactiveDocReason").html(data["percent"]+"%");   
       })
       getCohorts("${ui.format(constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)}", cohortAjaxUrl, function(response){
            var data = JSON.parse(response);

            jq("#recentClinicPediatricVisitDocMuac").html(data["numerator"]);
            var denominator = data["denominator"];
            var percent = data["percent"];
            jq(".recentClinicPediatricVisit").html(denominator);
           // var percent = ((data["numerator"] / totalPatientsClinicVisit) * 100).toFixed(1);
            jq("#percentRecentClinicVisitDocMuac").html(percent+"%");   
       })
  
  
       function getCohortRelatedToVl()
       {
           getCohorts("${ui.format(constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);

                var percent = ((data["numerator"] / totalPtsEligibleForVl) * 100).toFixed(1); 
                jq("#totalEligibleWithResult").html(data["numerator"]);
                jq(".totalEligible").html(totalPtsEligibleForVl);
                jq("#percentEligibleWithResult").html(percent+"%");  
                
                
                 
           })
           getCohorts("${ui.format(constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)}", cohortAjaxUrl, function(response){
                var data = JSON.parse(response);
                totalPtsWithVl = data["denominator"];
                var percent = ((data["numerator"] / data["denominator"]) * 100).toFixed(1); 
                jq("#totalResultWithCollection").html(data["numerator"]);
                jq(".totalWithResult").html(totalPtsWithVl);
                jq("#percentEligibleWithSampleCollected").html(percent+"%");   


                getCohorts("${ui.format(constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)}", cohortAjaxUrl, function(response){
                    var data = JSON.parse(response);
                     var percent = ((data["numerator"] / totalPtsWithVl) * 100).toFixed(1); 
                    jq("#totalEligibleWithReceivedAtLab").html(data["numerator"]);

                    jq("#percentEligibleWithReceivedAtLab").html(percent+"%");   
               })

           })

                
          
       }
   
</script>










