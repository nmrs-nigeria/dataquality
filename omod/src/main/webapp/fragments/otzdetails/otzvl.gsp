<h3>VL Charts</h3>


    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6">
            <h3>Viral Load Access</h3>
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
            <h3>Viral Load suppression for VL done in the last 6 months</h3>
            <div id="chartVLSuppressionLast6Months" style="height:650px;"></div>
        </div>
        <div class="col-sm-12 col-md-6">
            <h3>Cascade for unsuppressed clients in OTZ</h3>
            <div id="chartUnsuppresses" style="height:650px;"></div>
        </div>
    </div>
    <br/>
    <br/>
    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6">
            <h3>Viral Load suppression post EAC</h3>
            <div id="chartVLSuppressionPostEAC" style="height:650px;"></div>
        </div>
        <div class="col-sm-12 col-md-6">
            <h3>Unsupressed post EAC vs Number switched to another regimen</h3>
            <div id="chartUnsuppressedVsSwitched" style="height:650px;"></div>
        </div>
    </div>
    
<script type="text/javascript">
 
var jq = jQuery;
jq(document).ready(function(){
    
setTimeout(function(){
/*myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("getLabGraphData") }').then(function(response){
            
        var vlChartData = new Array();

      
        var data = JSON.parse(response);
        vlData = data;
        for(var i=0; i<data.length; i++){
            if(parseFloat(data[i]["vlResult"]) >= 1000)
            {chartUnsuppressedVsSwitched
                aboveOr1000++;
            }else if(parseFloat(data[i]["vlResult"]) >= 200)
            {
                bet200_1000++;
            }
            else if(parseFloat(data[i]["vlResult"]) < 200)
            {
                less200++;
            }  
        }e0dd96

          vlChartData.push(
                {
                    vlRange:">=1000 cp/ml",
                    vlCount:aboveOr1000,
                    weightBand:"#60aaaa",
                    color:"#e35b17"
                 },
                  {
                    vlRange:"200 - 999 cp/ml",
                    vlCount:bet200_1000,
                    color:"#edc009"

                 },
                 {
                    vlRange:"<200 cp/ml",
                    vlCount:less200,
                    color:"#23ba58"

                 },

             );
             var legendData = [{color:"#e35b17", title:"Patient with VL >=1000 cp/ml"}, {color:"#edc009", title:"Patient with VL between 200 - 999 cp/ml"},
                    {color:"#23ba58", title:"Patient with VL <200 cp/ml"},      
             ]



                 var graphArray = [
                    {field: "vlCount", description:"Total Patients ", title:"Total Patients", isOptimum:"vl", chartType:"vlAnalysis"},
                   ]
               //create chart
               buildBarCharts("Total patients", vlChartData, graphArray, "vlRange", "chartTotalEnrolled", "", "none", legendData, false);

     });*/
     var vlAccessData = new Array();
     vlAccessData.push(
                {
                    title:"Month-06",
                    noEligible:10,
                    noTestDone:8,
                    
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noEligible:18,
                    noTestDone:12,

                 },
                 {
                    title:"Month-18",
                    noEligible:20,
                    noTestDone:7,

                 },

             );
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ whose samples were taken for 6-monthly vl test"},      
             ]
            //var legendData = [];
            var graphArray = [
               {field: "noEligible", color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noTestDone", color:"#A1a1a1", description:"title", title:"# of AYPLHIV enrolled in OTZ whose samples were taken for 6-monthly vl test", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("VL Access", vlAccessData, graphArray, "title", "chartVLAccess", "", "none", legendData, true, false);
          
          
          
          
          
     var vlCoverageData = new Array();
     vlCoverageData.push(
                {
                    title:"Month-06",
                    noEligible:16,
                    noWithResult:12,
                    
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noEligible:17,
                    noWithResult:15,

                 },
                 {
                    title:"Month-18",
                    noEligible:10,
                    noWithResult:4,

                 },

             );
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ with VL result in the past 6 months"},      
             ]
           // var legendData = [];
            var graphArray2 = [
               {field: "noEligible", color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ who were eligible for 6 monthly VL test", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResult", color:"#A1a1a1", description:"title", title:"# of AYPLHIV enrolled in OTZ with VL result in the past 6 months", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("VL Coverage", vlCoverageData, graphArray2, "title", "chartVlCoverage", "", "none", legendData, true, false);


     var vlSuppressionData = new Array();
     vlSuppressionData.push(
                {
                    title:"Month-06",
                    noEligible:16,
                    noWithResult:12,
                    
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noEligible:17,
                    noWithResult:15,

                 },
                 {
                    title:"Month-18",
                    noEligible:10,
                    noWithResult:4,

                 },

             );
            var legendData = [{color:"#589BD4", title:"# of AYPLHIV enrolled in OTZ with result in the past 6 months"},
                    {color:"#A1a1a1", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 6 months"},      
             ]
            var graphArray2 = [
               {field: "noEligible", color:"#589BD4", description:"title", title:"# of AYPLHIV enrolled in OTZ with result in the past 6 months", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResult", color:"#A1a1a1", description:"title", title:"# of AYPLHIV enrolled in OTZ with VL suppressed VL in the past 6 months", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("VL Coverage", vlCoverageData, graphArray2, "title", "chartVLSuppressionLast6Months", "", "none", legendData, true, false);
          
          
          
          var vlCascaceData = new Array();
          vlCascaceData.push(
                {
                    title:"Month-06",
                    noWithResultAbove1000:16,
                    noWithResultAbove1000EAC:12,
                    noWithResultAbove1000WithRepeat:12,
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noWithResultAbove1000:16,
                    noWithResultAbove1000EAC:12,
                    noWithResultAbove1000WithRepeat:12,

                 },
                 {
                    title:"Month-18",
                    noWithResultAbove1000:16,
                    noWithResultAbove1000EAC:12,
                    noWithResultAbove1000WithRepeat:12,

                 },

             );
            var legendData = [{color:"#589BD4", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m"},
                    {color:"#A1a1a1", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m and completed EAC"},      
                    {color:"#406dd6", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m with repeat VL"}, 
             ]
            var graphArray2 = [
               {field: "noWithResultAbove1000", color:"#589BD4", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResultAbove1000EAC", color:"#A1a1a1", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m and completed EAC", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithResultAbove1000WithRepeat", color:"#406dd6", description:"title", title:"# of AYPLHIV in OTZ with VL within the last 12 months result greater than or equal to 1000 c/m with repeat VL", isOptimum:"totalInOtz", chartType:"otz"}
              ]
               //create chart
          buildBarCharts("VL Coverage", vlCascaceData, graphArray2, "title", "chartUnsuppresses", "", "none", legendData, true, false);
          
          
          var vlSuppressionPostEACData = new Array();
          vlSuppressionPostEACData.push(
                {
                    title:"Month-06",
                    noSuppressed:16,
                    noWithResult:12,
                    
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noSuppressed:17,
                    noWithResult:15,

                 },
                 {
                    title:"Month-18",
                    noSuppressed:10,
                    noWithResult:4,

                 },

             );
             var legendData = [{color:"#589BD4", title:"# of AYPLHIV with VL result in the last 12 months >= 1000 c/m who have post EAC VL result"},
                    {color:"#A1a1a1", title:"# of AYPLHIV with suppressed post EAC VL result"},      
                    
             ]
            var graphArray3 = [
               {field: "noWithResult", color:"#589BD4", description:"title", title:"# of AYPLHIV with VL result in the last 12 months >= 1000 c/m who have post EAC VL result", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noSuppressed", color:"#A1a1a1", description:"title", title:"# of AYPLHIV with suppressed post EAC VL result", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("VL Coverage", vlSuppressionPostEACData, graphArray3, "title", "chartVLSuppressionPostEAC", "", "none", legendData, true, false);
          
          
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
          buildBarCharts("VL Coverage", vlSuppressionVsSwitchedData, graphArray3, "title", "chartUnsuppressedVsSwitched", "", "none", legendData, true, false);
          
          
          
        
          
}, 1000);

});
 </script>
    