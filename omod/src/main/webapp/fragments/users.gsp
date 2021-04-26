<%
ui.includeJavascript("dataquality", "jquery-3.3.1.js")
ui.includeJavascript("dataquality", "jquery.dataTables.min.js")
ui.includeJavascript("dataquality", "datatables.min.js")
ui.includeJavascript("dataquality", "buttons.flash.min.js")
ui.includeJavascript("dataquality", "jszip.min.js")
ui.includeJavascript("dataquality", "pdfmake.min.js")
ui.includeJavascript("dataquality", "vfs_fonts.js")
ui.includeJavascript("dataquality", "buttons.html5.min.js")
ui.includeJavascript("dataquality", "jquery-ui.js")
ui.includeCss("dataquality", "jquery-ui.css")
ui.includeJavascript("dataquality", "buttons.print.min.js")
ui.includeCss("dataquality", "buttons.dataTables.min.css")
ui.includeCss("dataquality", "jquery.dataTables.min.css")

def id = config.id
%>
<%=ui.resourceLinks()%>
<div class="container">
    
    <fieldset>
        <legend>Filters</legend>
        <div class="row">
           <label class="col-sm-6 col-md-2 " ><strong>Start Date</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <input type="text" class="form-control date" id="startDate" name="startDate"/>
            </div>
            
            <label class="col-sm-6 col-md-2 "><strong>Start Date</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <input type="text" class="form-control date" id="endDate" name="endDate"/>
            </div>
            
            
            <div class="col-sm-6 col-md-2">
                <button class="button" id="filterCQI">Filter</button>
            </div>
        </div>
    </fieldset>
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
                <td id="totalActivePatientsEdu"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEduStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of all active patients with a documented marital status </td>
                <td id="activePtsWithDocMarStat"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalActivePatientsMar"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentMarStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>III</td>
                <td>Proportion of all active patients with a documented occupational status </td>
                <td id="activePtsWithDocOccuStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalActivePatientsOccu"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentOccuStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>IV</td>
                <td>
                   <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with documented age and/or Date of Birth</span>
                   <span class="withDate hidden">Proportion of patients started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> with documented age and/or Date of Birth</span>
                </td>
                <td id="startedOnArtWithDob"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtDob"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtDob"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>V</td>
                <td>
                    <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with documented Sex </span>
                    <span class="withDate hidden">Proportion of patients  started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> with documented Sex </span>
                </td>
                <td id="startedOnArtWithGender"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtGender"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtGender"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VI</td>
                <td>
                    <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with registered address/LGA of residence</span>
                    <span class="withDate hidden">Proportion of patients  started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> with registered address/LGA of residence</span>
                </td>
                <td id="startedOnArtWithAddress"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtAddress"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtAddress"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VII</td>
                <td>
                    <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with documented date of HIV diagnosis</span>
                    <span class="withDate hidden">Proportion of patients  started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> with documented date of HIV diagnosis</span>
                </td>
                <td id="startedOnArtWithDiagnosisDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtDiagnosisDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtDiagnosisDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>VIII</td>
                <td>
                    <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with documented HIV enrollment date</span>
                    <span class="withDate hidden">Proportion of patients  started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> months with documented HIV enrollment date</span>

                </td>
                <td id="startedOnArtWithEnrollmentDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtEnrollmentDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtEnrollmentDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>IX</td>
                <td> 
                    <span class="withNoDate">Number of All patients Newly started on ART in the last 6 months with a documented Drug pickup</span>
                    <span class="withDate hidden">Number of All patients started between <strong class="startDate"></strong> and <strong class="endDate"></strong> with a documented Drug pickup</span>
                </td>
                <td id="startedOnArtWithDrugPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtDrugPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtDrugPickup"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="#" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>X</td>
                <td>
                     <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with documented CD4 result </span>
                     <span class="withDate hidden">Proportion of patients started on ART between <strong class="startDate"></strong> and <strong class="endDate"></strong> with documented CD4 result </span>
                </td>
                <td id="startedOnArtWithCd4"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtCd4"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtCd4"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XI</td>
                <td>
                    <span class="withNoDate">Proportion of patients with a clinic visit in the last 6 months that had documented weight</span>
                    <span class="withDate hidden">Proportion of patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that had documented weight</span>
                
                </td>
                <td id="clinicVisitWithWeight"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitWeight"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitWeight"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
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
                <td>
                    <span class="withNoDate">Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC </span>
                    <span class="withDate hidden">Proportion of pediatric patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that had documented MUAC </span>
                </td>
                <td id="clinicVisitWithMuac"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitMuac"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitMuac"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XIII</td>
                <td>
                    <span class="withNoDate">Proportion of patients with a clinic visit in the last 6 months that had documented WHO clinical stage</span>
                    <span class="withDate hidden">Proportion of patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that had documented WHO clinical stage</span>
                    
                </td>
                <td id="clinicVisitWithWho"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitWho"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitWho"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XIV</td>
                <td>
                    <span class="withNoDate">Proportion of patients with a clinic visit in the last 6 months that had documented TB status</span>
                    <span class="withDate hidden">Proportion of patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that had documented TB status</span>
                </td>
                <td id="clinicVisitWithTBStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitTBStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitTBStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XV</td>
                <td>
                    <span class="withNoDate">Proportion of patients with a clinic visit in the last 6 months that had documented functional status</span>
                    <span class="withDate hidden">Proportion of patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that had documented functional status</span>
                </td>
                <td id="clinicVisitWithFunctionalStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitFunctionalStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitFunctionalStatus"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVI</td>
                <td >
                    <span class="withNoDate">Proportion of patients newly started on ART in the last 6 months with initial ART regimen</span>
                    <span class="withDate hidden">Proportion of patients  started on ART <strong class="startDate"></strong> and <strong class="endDate"></strong> with initial ART regimen</span>
                </td>
                <td id="startedOnArtWithInitialRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalStartedOnArtInitialRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentStartedArtInitialRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVII</td>
                <td>
                    <span class="withNoDate">Proportion of all patients with a clinic visit in the last 6 months that have documented next scheduled appointment date</span>
                    <span class="withDate hidden">Proportion of all patients with a clinic visit between <strong class="startDate"></strong> and <strong class="endDate"></strong> that have documented next scheduled appointment date</span>
                    
                </td>
                <td id="clinicVisitWithNextAppDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalClinicVisitNextAppDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentClinicVisitNextAppDate"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)}" class="button" title="View records with issues">View Issues</a></td>
            </tr>
            <tr>
                <td>XVIII</td>
                <td>
                    <span class="withNoDate">Proportion of all inactive patients with a documented exit reason</span>
                    <span class="withDate hidden">Proportion of all inactive patients as at <strong class="endDate"></strong> with a documented exit reason</span>
                </td>
                <td id="totalInactiveDocReason"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalInactive"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
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
                <td id="totalPickupWithQty"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalPickupQty"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentagePickupQty"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with a documented ART regimen duration in the last drug refill visit</td>
                <td id="totalPickupWithDuration"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalPickupDuration"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentagePickupDuration"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>III</td> 
                <td>Proportion of patients with documented ART regimen in the last drug refill visit</td>
                <td id="totalPickupWithRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalPickupRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentagePickupRegimen"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)}" class="button" title="View records with issues">View issues</a></td>    
            </tr>
            <tr>
                <td>IV</td> 
                <td>Proportion of patients with ART regimen duration not more than six(6) months  in the last drug refill visit</td>
                <td id="totalPickupWithQty180"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalPickupQty180"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentagePickupQty180"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
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
                <td id="totalEligibleResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentEligibleResult"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)}" class="button" title="View records with issues">View issues</a></td>       
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with Viral Load result that had documented specimen collection date </td>
                <td id="totalResultWithResultCollection"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalResultCollection"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentResultCollection"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)}" class="button" title="View records with issues">View Issues</a></td>       
            </tr>
            
            <tr>
                <td>III</td> 
                <td>Proportion of patients with Viral load results with a documented date sample was received at the PCR lab</td>
                <td id="totalResultWithResultSent"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="totalResultSent"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td id="percentResultSent"><img width="50" src="${ ui.resourceLink("dataquality", "images/loading.gif") }" /></td>
                <td><a href="dataqualityview.page?type=${ui.format(constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)}" class="button" title="View records with issues">View Issues</a></td>       
            </tr>



        </tbody>
    </table>
</div>




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
   
   jq('.date').datepicker({
            dateFormat: 'yy-mm-dd',
            changeYear: true,
            changeMonth:true,
            yearRange: "-30:+0",
            autoclose: true
        });
        
   jq("#filterCQI").click(function(){
        
        var startDate = jq("#startDate").val();
        var endDate = jq("#endDate").val();
        if(startDate == "" || endDate == "")
        {
            alert("Please select start and end date");
        }else{
             jq(".startDate").html(startDate)
             jq(".endDate").html(endDate);
             jq(".withDate").removeClass("hidden");
             jq(".withNoDate").addClass("hidden");
             filterRecords();
             
        }
        
        
        
        
   });
        
        
  filterRecords();
        
   function filterRecords()
   {    
   
          var startDate = jq("#startDate").val();
            var endDate = jq("#endDate").val();
   
        myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getActivePatientsWithDocumentEducationalStaus") }').then(function(response){

            
             var data = JSON.parse(response);
             var totalPtsWithEducationalStatus = data["totalPtsWithEducationalStatus"];
             var totalActivePatients = data["totalActivePatients"];
             var percent = ( totalPtsWithEducationalStatus/totalActivePatients * 100).toFixed(1);

             jq("#activePtsWithDocEduStat").html(totalPtsWithEducationalStatus);
             jq("#totalActivePatientsEdu").html(totalActivePatients);
             jq("#percentEduStatus").html(percent+"%");
            

             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getActivePatientsWithDocumentMaritalStaus") }');
         }).then(function(response){

           
             var data = JSON.parse(response);
             var totalPtsWithMaritalStatus = data["totalPtsWithMaritalStatus"];
             var totalActivePatients = data["totalActivePatients"];
             var percent = ( totalPtsWithMaritalStatus/totalActivePatients * 100).toFixed(1);

             jq("#activePtsWithDocMarStat").html(totalPtsWithMaritalStatus);
             jq("#totalActivePatientsMar").html(totalActivePatients);
             jq("#percentMarStatus").html(percent+"%");
            

             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getActivePatientsWithDocumentOccupationalStaus") }');
         })
         
         .then(function(response){

             var data = JSON.parse(response);
             var totalPtsWithOccupationalStatus = data["totalPtsWithOccupationalStatus"];
             var totalActivePatients = data["totalActivePatients"];
             var percent = ( totalPtsWithOccupationalStatus/totalActivePatients * 100).toFixed(1);

             jq("#activePtsWithDocOccuStatus").html(totalPtsWithOccupationalStatus);
             jq("#totalActivePatientsOccu").html(totalActivePatients);
             jq("#percentOccuStatus").html(percent+"%");

             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocDob") }');
         })
         .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithDob = data["totalPtsStartedOnArtWithDob"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithDob/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithDob").html(totalPtsStartedOnArtWithDob);
             jq("#totalStartedOnArtDob").html(totalPtsStartedOnArt);
             jq("#percentStartedArtDob").html(percent+"%");
            

             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocGender") }');
         })
         
          .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithGender = data["totalPtsStartedOnArtWithGender"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithGender/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithGender").html(totalPtsStartedOnArtWithGender);
             jq("#totalStartedOnArtGender").html(totalPtsStartedOnArt);
             jq("#percentStartedArtGender").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocAddress") }');
         })
         .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithAddress = data["totalPtsStartedOnArtWithAddress"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithAddress/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithAddress").html(totalPtsStartedOnArtWithAddress);
             jq("#totalStartedOnArtAddress").html(totalPtsStartedOnArt);
             jq("#percentStartedArtAddress").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocHivDiagnosisDate") }');
         })
         
         .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithHivDiagnosisDate = data["totalPtsStartedOnArtWithHivDiagnosisDate"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithHivDiagnosisDate/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithDiagnosisDate").html(totalPtsStartedOnArtWithHivDiagnosisDate);
             jq("#totalStartedOnArtDiagnosisDate").html(totalPtsStartedOnArt);
             jq("#percentStartedArtDiagnosisDate").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocHivEnrollmentDate") }');
         })
         .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithHivEnrollmentDate = data["totalPtsStartedOnArtWithHivEnrollmentDate"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithHivEnrollmentDate/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithEnrollmentDate").html(totalPtsStartedOnArtWithHivEnrollmentDate);
             jq("#totalStartedOnArtEnrollmentDate").html(totalPtsStartedOnArt);
             jq("#percentStartedArtEnrollmentDate").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocDrugPickup") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithDrugPickup = data["totalPtsStartedOnArtWithDrugPickup"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithDrugPickup/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithDrugPickup").html(totalPtsStartedOnArtWithDrugPickup);
             jq("#totalStartedOnArtDrugPickup").html(totalPtsStartedOnArt);
             jq("#percentStartedArtDrugPickup").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithDocCd4") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithCd4 = data["totalPtsStartedOnArtWithCd4"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithCd4/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithCd4").html(totalPtsStartedOnArtWithCd4);
             jq("#totalStartedOnArtCd4").html(totalPtsStartedOnArt);
             jq("#percentStartedArtCd4").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocWeight") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocWeight = data["totalPtsClinicVisitDocWeight"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocWeight/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithWeight").html(totalPtsClinicVisitDocWeight);
             jq("#totalClinicVisitWeight").html(totalPtsClinicVisit);
             jq("#percentClinicVisitWeight").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocMuac") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocMuac = data["totalPtsClinicVisitDocMuac"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocMuac/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithMuac").html(totalPtsClinicVisitDocMuac);
             jq("#totalClinicVisitMuac").html(totalPtsClinicVisit);
             jq("#percentClinicVisitMuac").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocWhoStage") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocWhoStage = data["totalPtsClinicVisitDocWhoStage"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocWhoStage/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithWho").html(totalPtsClinicVisitDocWhoStage);
             jq("#totalClinicVisitWho").html(totalPtsClinicVisit);
             jq("#percentClinicVisitWho").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocTBStatus") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocTBStatus = data["totalPtsClinicVisitDocTBStatus"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocTBStatus/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithTBStatus").html(totalPtsClinicVisitDocTBStatus);
             jq("#totalClinicVisitTBStatus").html(totalPtsClinicVisit);
             jq("#percentClinicVisitTBStatus").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocFunctionalStatus") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocFunctionalStatus = data["totalPtsClinicVisitDocFunctionalStatus"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocFunctionalStatus/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithFunctionalStatus").html(totalPtsClinicVisitDocFunctionalStatus);
             jq("#totalClinicVisitFunctionalStatus").html(totalPtsClinicVisit);
             jq("#percentClinicVisitFunctionalStatus").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithInitialRegimen") }');
         })
         .then(function(response){

             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsStartedOnArtWithInitialRegimen = data["totalPtsStartedOnArtWithInitialRegimen"];
             var totalPtsStartedOnArt = data["totalPtsStartedOnArt"];
             var percent = ( totalPtsStartedOnArtWithInitialRegimen/totalPtsStartedOnArt * 100).toFixed(1);

             jq("#startedOnArtWithInitialRegimen").html(totalPtsStartedOnArtWithInitialRegimen);
             jq("#totalStartedOnArtInitialRegimen").html(totalPtsStartedOnArt);
             jq("#percentStartedArtInitialRegimen").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsClinicVisitDocNextAppDate") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsClinicVisitDocNextAppDate = data["totalPtsClinicVisitDocNextAppDate"];
             var totalPtsClinicVisit = data["totalPtsClinicVisit"];
             var percent = ( totalPtsClinicVisitDocNextAppDate/totalPtsClinicVisit * 100).toFixed(1);

             jq("#clinicVisitWithNextAppDate").html(totalPtsClinicVisitDocNextAppDate);
             jq("#totalClinicVisitNextAppDate").html(totalPtsClinicVisit);
             jq("#percentClinicVisitNextAppDate").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getInactivePtsWithDocReason") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalInactivePtsWithReason = data["totalInactivePtsWithReason"];
             var totalInactivePts = data["totalInactivePts"];
             var percent = ( totalInactivePtsWithReason/totalInactivePts * 100).toFixed(1);

             jq("#totalInactiveDocReason").html(totalInactivePtsWithReason);
             jq("#totalInactive").html(totalInactivePts);
             jq("#percentInactiveDocReason").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithDrugQuantity") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPickupWithQuantity = data["totalPickupWithQuantity"];
             var totalPickup = data["totalPickup"];
             var percent = ( totalPickupWithQuantity/totalPickup * 100).toFixed(1);
             jq("#totalPickupWithQty").html(totalPickupWithQuantity);
             jq("#totalPickupQty").html(totalPickup);
             jq("#percentagePickupQty").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithDrugDuration") }');
         }).then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPickupWithDuration = data["totalPickupWithDuration"];
             var totalPickup = data["totalPickup"];
             var percent = ( totalPickupWithDuration/totalPickup * 100).toFixed(1);
             jq("#totalPickupWithDuration").html(totalPickupWithDuration);
             jq("#totalPickupDuration").html(totalPickup);
             jq("#percentagePickupDuration").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithDrugRegimen") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPickupWithRegimen = data["totalPickupWithRegimen"];
             var totalPickup = data["totalPickup"];
             var percent = ( totalPickupWithRegimen/totalPickup * 100).toFixed(1);
             jq("#totalPickupWithRegimen").html(totalPickupWithRegimen);
             jq("#totalPickupRegimen").html(totalPickup);
             jq("#percentagePickupRegimen").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithDrugPickupQtyLessThan180") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPickupWithQtyLessThan180 = data["totalPickupWithQtyLessThan180"];
             var totalPickup = data["totalPickup"];
             var percent = ( totalPickupWithQtyLessThan180/totalPickup * 100).toFixed(1);
             jq("#totalPickupWithQty180").html(totalPickupWithQtyLessThan180);
             jq("#totalPickupQty180").html(totalPickup);
             jq("#percentagePickupQty180").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsEligibleForVLWithResult") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsEligibleForVlWithResult = data["totalPtsEligibleForVlWithResult"];
             var totalPtsEligibleForVl = data["totalPtsEligibleForVl"];
             var percent = ( totalPtsEligibleForVlWithResult/totalPtsEligibleForVl * 100).toFixed(1);
             jq("#totalEligibleWithResult").html(totalPtsEligibleForVlWithResult);
             jq("#totalEligibleResult").html(totalPtsEligibleForVl);
             jq("#percentEligibleResult").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithResultAndSampleCollectionDate") }');
         })
         
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsWithVlResultAndSampleCollectionDate = data["totalPtsWithVlResultAndSampleCollectionDate"];
             var totalPtsWithResult = data["totalPtsWithResult"];
             var percent = ( totalPtsWithVlResultAndSampleCollectionDate/totalPtsWithResult * 100).toFixed(1);
             jq("#totalResultWithResultCollection").html(totalPtsWithVlResultAndSampleCollectionDate);
             jq("#totalResultCollection").html(totalPtsWithResult);
             jq("#percentResultCollection").html(percent+"%");
             
             return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsWithResultAndSampleReceivedDate") }');
         })
         .then(function(response){
             console.log("response", response);
             var data = JSON.parse(response);
             var totalPtsWithVlResultAndReceivedDate = data["totalPtsWithVlResultAndReceivedDate"];
             var totalPtsWithResult = data["totalPtsWithResult"];
             var percent = ( totalPtsWithVlResultAndReceivedDate/totalPtsWithResult * 100).toFixed(1);
             jq("#totalResultWithResultSent").html(totalPtsWithVlResultAndReceivedDate);
             jq("#totalResultSent").html(totalPtsWithResult);
             jq("#percentResultSent").html(percent+"%");
             
             //return  myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getPtsStartedOnARTWithInitialRegimen") }');
         })
         
      }
   
       
    
       
       
     
     
  
     
    
    
     
    
  
       
   
</script>



