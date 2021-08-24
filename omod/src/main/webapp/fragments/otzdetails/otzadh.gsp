<h3>VL Charts</h3>


    <div class="row" style="width:110% !important">
        <div class="col-sm-12 col-md-6">
            <h3>Drug Pickup appointment</h3>
            <div id="chartDrugAppointment" style="height:500px;"></div>
        </div>
        <div class="col-sm-12 col-md-6">
            <h3>Medication Adherence Score</h3>
            <div id="chartAdherenceScore" style="height:500px;"></div>
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
        }

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
     var drugPickupData = new Array();
     drugPickupData.push(
                {
                    title:"Month-06",
                    withAppointment:10,
                    keptAppointment:8,
                    
                 },
                  {
                     title:"Month-12",
                    withAppointment:18,
                    keptAppointment:12,

                 },
                 {
                    title:"Month-18",
                    withAppointment:20,
                    keptAppointment:7,

                 },

             );
             /*var legendData = [{color:"#e35b17", title:"Patient with VL >=1000 cp/ml"}, {color:"#edc009", title:"Patient with VL between 200 - 999 cp/ml"},
                    {color:"#23ba58", title:"Patient with VL <200 cp/ml"},      
             ]*/
            var legendData = [];
            var graphArray = [
               {field: "withAppointment", color:"#589BD4", description:"title", title:"# of OTZ members with scheduled drug pickup appointment in the last 6 months", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "keptAppointment", color:"#A1a1a1", description:"title", title:"# of OTZ members who kept their appointment", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("# of members", drugPickupData, graphArray, "title", "chartDrugAppointment", "", "none", legendData, true, false);
          
          
          
          
          
     var adherenceData = new Array();
     adherenceData.push(
                {
                    title:"Month-06",
                    noPickedupDrug:16,
                    noWithGoodScore:12,
                    
                    //color:"#589BD4"
                 },
                  {
                     title:"Month-12",
                    noPickedupDrug:17,
                    noWithGoodScore:15,

                 },
                 {
                    title:"Month-18",
                    noPickedupDrug:10,
                    noWithGoodScore:4,

                 },

             );
             /*var legendData = [{color:"#e35b17", title:"Patient with VL >=1000 cp/ml"}, {color:"#edc009", title:"Patient with VL between 200 - 999 cp/ml"},
                    {color:"#23ba58", title:"Patient with VL <200 cp/ml"},      
             ]*/
            var legendData = [];
            var graphArray2 = [
               {field: "noPickedupDrug", color:"#589BD4", description:"title", title:"# of OTZ members who picked up drug", isOptimum:"totalInOtz", chartType:"otz"},
               {field: "noWithGoodScore", color:"#A1a1a1", description:"title", title:"# of OTZ members with good adherence Score", isOptimum:"totalInOtz", chartType:"otz"},
              ]
               //create chart
          buildBarCharts("# of members", adherenceData, graphArray2, "title", "chartAdherenceScore", "", "none", legendData, true, false);


          
          
        
          
}, 1000);

});
 </script>
    