<h3>VL Charts</h3>


    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6">
            <h3>Viral Load Testing Uptake</h3>
            <div id="chartVLAccess" style="height:650px;"></div>
        </div>
        <div class="col-sm-12 col-md-6" style="width:103% !important">
            <h3>Viral Load Coverage</h3>
            <div id="chartVlCoverage" style="height:650px;"></div>
        </div>
    </div>
    <br/>
    <br/>
    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6" >
            <h3>Viral Load suppression  (VL done in the last 6 months)</h3>
            <div id="chartVLSuppressionLast6Months" style="height:650px;"></div>
        </div>
        <div class="col-sm-12 col-md-6" >
            <h3>Viral Load suppression  (VL done in the last 12 months)</h3>
            <div id="chartVLSuppressionLast12Months" style="height:650px;"></div>
        </div>
        
    </div>
    <br/>
    <br/>
    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6">
            <h3>Cascade for unsuppressed clients in OTZ</h3>
            <div id="chartUnsuppresses" style="height:650px;"></div>
        </div>
        <div class="col-sm-12 col-md-6">
            <h3>Viral Load suppression post EAC</h3>
            <div id="chartVLSuppressionPostEAC" style="height:650px;"></div>
        </div>
        <!--<div class="col-sm-12 col-md-6">
            <h3>Unsupressed post EAC vs Number switched to another regimen</h3>
            <div id="chartUnsuppressedVsSwitched" style="height:650px;"></div>
        </div>-->
    </div>
    
<script type="text/javascript">
 
var jq = jQuery;

  /*var patientsEligibleMonth6 = new Array();
    var patientsWithSampleMonth6 = new Array();
    var patientsWithResultMonth6 = new Array();
    var patientsSuppressedMonth6 = new Array();

    var patientsEligibleMonth12 = new Array();
    var patientsWithSampleMonth12 = new Array();
    var patientsWithResultMonth12 = new Array();
    var patientsSuppressedMonth12 = new Array();
    
    var patientsEligibleMonth18 = new Array();
    var patientsWithSampleMonth18 = new Array();
    var patientsWithResultMonth18 = new Array();
    var patientsSuppressedMonth18 = new Array();*/
    
    
    var patientsEligibleObj = {};
    var patientsWithSampleObj = {};
    var patientsWithResultObj= {};
    var patientsWithResultPast6MonthsObj = {};
    var patientsSuppressedPast6MonthsObj = {};
    
    var patientsWithResultPast12MonthsObj = {};
    var patientsWithResultPast12MonthsAbove1000Obj = {};
    
    var patientsWhoCompletedEACPast12MonthsObj = {};
    var patientsSuppressedPast12MonthsObj = {};
    var patientsWithRepeatVl12MonthsObj = {}
    var suppressedPatientsPostEACObj = {}
    var suppressedPatientsPostEACObj = {}
    var patientsWithResultPast12MonthObj = {};
    var patientsWithRepeatVl12MonthsObj = {};
    var allPatientsScheduledObj = {};
    var allPatientsKeptObj = {};
    var allPatientsGoodScoreObj = {};
    

function getVlData()
{


    var today = new Date();
    var beginDate = new Date(Date.parse(startDate));
    var monthDifference = monthDiff(beginDate, today);//get months between 
    var vlCoverageData = new Array();
    var vlAccessData = new Array();
    var vlAccessMax = 0;
    var vlAccessMin = 0;
    
    var vlSuppressionData6Months = new Array();
    var vlSuppressionData12Months = new Array();
     var vlSuppressionPostEACData = new Array();
     var drugPickupData = new Array();
    var vlCascaceData = new Array();
    
    var adherenceData = new Array();
    myAjax({startDate:startDate, endDate:endDate}, "otz/getPatientsVLAccess.action").then(function(response){
        var data = JSON.parse(response);
         
        console.log(data);
        
        
         for(var i=0; i<monthDifference; i += 6){
            var title = (i == 0 ) ? "Baseline": "Month-"+i;
            var patientsEligible = data["patientsEligible"+i];
            var patientsWithSample= data["patientsWithSample"+i];
            var patientsWithResult = data["patientsWithResult"+i];
            var patientsWithResultPast6Months = data["patientsWithResultPast6Months"+i];
            var patientsSuppressedPast6Months = data["patientsSuppressedPast6Months"+i];
            
            var patientsWithResultPast12Months = data["patientsWithResultPast12Months"+i];
            var patientsWithResultPast12MonthsAbove1000 = data["patientsWithResultPast12MonthsAbove1000"+i];

            var patientsWhoCompletedEACPast12Months = data["patientsWhoCompletedEACPast12Months"+i];
            var patientsSuppressedPast12Months = data["patientsSuppressedPast12Months"+i];
            var patientsWithRepeatVl12Months = data["patientsWithRepeatVl12Months"+i];
            var suppressedPatientsPostEAC = data["suppressedPatientsPostEAC"+i];
            
            var allPatientsScheduled = data["allPatientsScheduled"+i];
            var allPatientsKept = data["allPatientsKept"+i];
            var allPatientsGoodScore = data["allPatientsGoodScore"+i];
            
            patientsEligibleObj[title] = patientsEligible;
            patientsWithSampleObj[title] = patientsWithSample;
            patientsWithResultObj[title] = patientsWithResult;
            patientsWithResultPast6MonthsObj[title] = patientsWithResultPast6Months;
            patientsSuppressedPast6MonthsObj[title] = patientsSuppressedPast6Months;
            patientsWithResultPast12MonthObj[title] = patientsWithResultPast12Months
            patientsSuppressedPast12MonthsObj[title] = patientsSuppressedPast12Months;
            patientsWithResultPast12MonthsAbove1000Obj[title] = patientsWithResultPast12MonthsAbove1000;
            patientsWhoCompletedEACPast12MonthsObj[title] = patientsWhoCompletedEACPast12Months;
            suppressedPatientsPostEACObj[title] = suppressedPatientsPostEAC;
            allPatientsScheduledObj[title] = allPatientsScheduled;
            allPatientsKeptObj[title] = allPatientsKept;
            allPatientsGoodScoreObj[title] = allPatientsGoodScore;
            
            
            drugPickupData.push(
                {
                    title:title,
                    withAppointment:allPatientsScheduled.length,
                    keptAppointment:allPatientsKept.length,
                   // percentage: ((allPatientsKept.length / allPatientsScheduled.length ) 100).toFixed(1)
                    
                 });
            
           adherenceData.push(
                {
                    title:title,
                    noPickedupDrug:allPatientsKept.length,
                    noWithGoodScore:allPatientsGoodScore.length,
                    //percentage:((allPatientsKept.length / allPatientsGoodScore.length ) 100).toFixed(1)
                    
                 }

             );
                vlAccessData.push(
                 {
                    title:title,
                    noEligible:patientsEligible.length,
                    noTestDone:patientsWithSample.length,
                    percentage: ((patientsWithSample.length / patientsEligible.length)  * 100).toFixed(1)
                    
                 });
                 
                 vlAccessMax = Math.max(Math.max(patientsEligible.length, patientsWithSample.length), vlAccessMax);
                 vlAccessMin = Math.min(Math.min(patientsEligible.length, patientsWithSample.length), vlAccessMin)
                 
                 
                vlCoverageData.push(
                {
                    title:title,
                    noEligible:patientsEligible.length,
                    noWithResult:patientsWithResult.length,
                    percentage: ((patientsWithResult.length / patientsEligible.length)  * 100).toFixed(1)
                    
                 });
                 
                 
                 
                vlSuppressionData6Months.push(
                {
                    title:title,
                    noEligible:patientsWithResultPast6Months.length,
                    noWithResult:patientsSuppressedPast6Months.length,
                    percentage: ((patientsSuppressedPast6Months.length / patientsWithResultPast6Months.length)  * 100).toFixed(1)

                 } );
                 
                vlSuppressionData12Months.push(
                {
                    title:title,
                    noEligible:patientsWithResultPast12Months.length,
                    noWithResult:patientsSuppressedPast12Months.length,
                    percentage: ((patientsSuppressedPast12Months.length / patientsWithResultPast12Months.length)  * 100).toFixed(1)

                 } );
                 
                 
                 vlCascaceData.push(
                {
                    title:title,
                    noWithResultAbove1000:patientsWithResultPast12MonthsAbove1000.length,
                    noWithResultAbove1000EAC:patientsWhoCompletedEACPast12Months.length,
                    noWithResultAbove1000WithRepeat:patientsWithRepeatVl12Months.length,
                   
                 }

             );
             
              vlSuppressionPostEACData.push(
                {
                    title:title,
                    noRepeat:patientsWithRepeatVl12Months.length,
                    noSuppressedPostEAC:suppressedPatientsPostEAC.length
                    
                 });
         }
        
        
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test"},
                    {color:"#12abb0", title:"# of AYPLHIV enrolled in OTZ whose samples were taken for 6-monthly vl test"},
                    {color:"#bf6c19", title:"6 Monthly Access"}      
             ]
            //var legendData = [];
            var graphArray = [
               {field: "noEligible", type:"column", valueAxis:1, color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test", isOptimum:"eligible6mts", chartType:"vlotz"},
               {field: "noTestDone", type:"column", valueAxis:1,  color:"#12abb0", description:"title", title:"# of AYPLHIV enrolled in OTZ whose samples were taken for 6-monthly vl test", isOptimum:"sampleTaken6t", chartType:"vlotz"},
               {field: "percentage", type:"line", valueAxis:2,  color:"#bf6c19", description:"title", title:"% of Eligible AYPLHIV  whose samples were taken for 6-monthly vl test", isOptimum:"percentage", chartType:"vlotz"},
              ]
              
              vlAccessMin = dataQuality_calculateMin(vlAccessMax, vlAccessMin);
              vlAccessMax = dataQuality_calculateMax(vlAccessMax, vlAccessMin)
               //create chart
          dataQuality_buildBarCharts("VL Access", vlAccessData, graphArray, "title", "chartVLAccess", "", "none", legendData, true, false, vlAccessMin, vlAccessMax);
          
          
          
          
         
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ with VL result in the past 6 months"},     
                    {color:"#bf6c19", title:"Viral Load Coverage"}     
             ]
       
            var graphArray2 = [
               {field: "noEligible", type:"column",  color:"#589BD4", valueAxis:1, description:"title", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test", isOptimum:"eligible6mts", chartType:"vlotz"},
               {field: "noWithResult", type:"column",  color:"#A1a1a1", valueAxis:1, description:"title", title:"# of AYPLHIV enrolled in OTZ with VL result in the past 6 months", isOptimum:"withResult", chartType:"vlotz"},
               {field: "percentage", type:"line", valueAxis:2,  color:"#bf6c19", description:"title", title:"% of Eligible AYPLHIV with result", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          dataQuality_buildBarCharts("VL Coverage", vlCoverageData, graphArray2, "title", "chartVlCoverage", "", "none", legendData, true, false);
          
          
          
          
     
            legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ with result in the past 6 months"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 6 months"}, 
                    {color:"#bf6c19", title:"Viral Load Coverage"}     
             ]
            graphArray2 = [
               {field: "noEligible", type:"column", valueAxis:1, color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ with result in the past 6 months", isOptimum:"withResult", chartType:"vlotz"},
               {field: "noWithResult", type:"column", valueAxis:1, color:"#A1a1a1", description:"title", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 6 months", isOptimum:"suppressed6Months", chartType:"vlotz"},
               {field: "percentage", type:"line", valueAxis:2,  color:"#bf6c19", description:"title", title:"% of AYPLHIV with suppressed result", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          dataQuality_buildBarCharts("VL Suppression", vlSuppressionData6Months, graphArray2, "title", "chartVLSuppressionLast6Months", "", "none", legendData, true, false);
          
          
          legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ with result in the past 12 months"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 12 months"}, 
                    {color:"#bf6c19", title:"Viral Load Coverage"}     
             ]
            graphArray2 = [
               {field: "noEligible", type:"column", valueAxis:1, color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ with result in the past 6 months", isOptimum:"withResult12Months", chartType:"vlotz"},
               {field: "noWithResult", type:"column", valueAxis:1, color:"#A1a1a1", description:"title", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 6 months", isOptimum:"suppressed12Months", chartType:"vlotz"},
               {field: "percentage", type:"line", valueAxis:2,  color:"#bf6c19", description:"title", title:"% of AYPLHIV with suppressed result", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
               
          
         
          dataQuality_buildBarCharts("VL Suppression", vlSuppressionData12Months, graphArray2, "title", "chartVLSuppressionLast12Months", "", "none", legendData, true, false);
          
          
          
            legendData = [{color:"#589BD4", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m"},
                    {color:"#A1a1a1", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m and completed EAC"},      
                    {color:"#406dd6", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m with repeat VL"}, 
             ]
             graphArray2 = [
               {field: "noWithResultAbove1000", color:"#589BD4", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResultAbove1000EAC", color:"#A1a1a1", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m and completed EAC", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResultAbove1000WithRepeat", color:"#406dd6", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m with repeat VL", isOptimum:"totalInOtz", chartType:"otz"}
              ]
               //create chart
          buildBarCharts("VL Coverage", vlCascaceData, graphArray2, "title", "chartUnsuppresses", "", "none", legendData, true, false);
          
          
          
         var legendData = [{color:"#589BD4", title:"# of AYPLHIV with VL result in the last 12 months >= 1000 c/m who have post EAC VL result"},
                    {color:"#A1a1a1", title:"# of AYPLHIV with suppressed post EAC VL result"},      
                    
             ]
            var graphArray3 = [
               {field: "noRepeat", color:"#589BD4", description:"title", title:"# of AYPLHIV with repeat VL  in the last 12 months", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noSuppressedPostEAC", color:"#A1a1a1", description:"title", title:"# of AYPLHIV with suppressed post EAC VL result ", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("VL Coverage", vlSuppressionPostEACData, graphArray3, "title", "chartVLSuppressionPostEAC", "", "none", legendData, true, false);
          
          
          
          buildAdhCharts(drugPickupData, adherenceData);
          
          
         return myAjax({startDate:startDate, endDate:endDate}, "otz/getPatientsVLCoverage.action") 
    }).then(function(){
        
        
    })

}

jq(document).ready(function(){
    
setTimeout(function(){


         
          
          
          var vlSuppressionVsSwitchedData = new Array();
          vlSuppressionVsSwitchedData.push(
                {
                    title:"Month-06",
                    noSuppressed:16,
                    noSwitched2:12,
                    noSwitched3:0,
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noSuppressed:17,
                    noSwitched2:15,
                    noSwitched3:1,

                 },
                 {
                    title:"Month-18",
                    noSuppressed:10,
                    noSwitche2:4,
                    noSwitched3:1,

                 },

             );
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled with post EAC result >=100c/m"},
                    {color:"#A1a1a1", title:"# switched to 2nd line"},  
                    {color:"#A1a1a1", title:"# switched to 3rd line"}

                    
             ]
            var graphArray3 = [
               {field: "noSuppressed", color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled with post EAC result >=100c/m", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noSwitched2", color:"#A1a1a1", description:"title", title:"# switched to 2nd line", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noSwitched3", color:"#406dd6", description:"title", title:"# switched to 3rd line", isOptimum:"totalInOtz", chartType:"otz"},
               ]
               //create chart
          //buildBarCharts("VL Coverage", vlSuppressionVsSwitchedData, graphArray3, "title", "chartUnsuppressedVsSwitched", "", "none", legendData, true, false);
          
          
          
         
          
        
          
}, 1000);

});
 </script>
    