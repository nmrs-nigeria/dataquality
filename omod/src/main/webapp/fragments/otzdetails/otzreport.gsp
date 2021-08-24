<% 
int i=1;


def title = config.title

%>
<div class="container">
    
    <h2>OTZ Report</h2>
   
    <br/>
    
      <br/>
    <table class="table dataTable">
        <thead>
            <!--<tr>
               
                <th rowspan="2" valign="center">S/N</th>
                <th  rowspan="2" style="text-align:center" align="middle">Indicator</th>
                <th colspan="3"  style="text-align:center">Male</th>
                <th colspan="3"  style="text-align:center">Female</th>
                <th rowspan="2"  style="text-align:center">Action</th>
            </tr>
            -->
             <tr>
                <th valign="center">S/N</th>
                <th  style="text-align:center" align="middle">Indicator</th>
                <th>Male 10-14</th>
                <th>Male 15-19</th>
                <th>Male 20-24</th>
                <th>Female 10-14</th>
                <th>Female 15-19</th>
                <th>Female 20-24</th>
                <th  style="text-align:center">Action</th>

             </tr>
            
        </thead>
        <tbody>
            <tr>
                <td><%= i++%></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month</td>
                <td class="loadingView num4"  id="totalEnrolledM10To14">-</td>
                <td class="loadingView num4"  id="totalEnrolledM15To19">-</td>
                <td class="loadingView num4"  id="totalEnrolledM20To24">-</td>
                <td class="loadingView num4"  id="totalEnrolledF10To14">-</td>
                <td class="loadingView num4"  id="totalEnrolledF15To19">-</td>
                <td class="loadingView num4"  id="totalEnrolledF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="1" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++%></td>
                <td># of OTZ members with scheduled drug pick-up appointment in the last six months prior to enrollment on OTZ</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorM10To14">-</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorM15To19">-</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorM20To24">-</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorF10To14">-</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorF15To19">-</td>
                <td class="loadingView num4" id="totalEnrolledWithApp6MtPriorF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="2" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members who kept their drug pick-up appointment in the last six months prior to enrolment on OTZ</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorM10To14">-</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorM15To19">-</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorM20To24">-</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorF10To14">-</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorF15To19">-</td>
                <td class="loadingView num4" id="totalEnrolledKeptAppPriorF20To24">-</td>
                
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="3" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members with good drug adherence score in the last six months prior to enrolment on OTZ</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorM10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorM15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorM20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorF10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorF15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledGoodAdhPriorF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="4" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td> # of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ </td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultM10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultM15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultM20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultF10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultF15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="5" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result less than 200 c/ml </td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultBelow200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="6" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td> # of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result between 200 to less than 1000 c/ml </td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult200To1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="7" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr><tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result greater than or equal to 1000 c/ml </td>
                 <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResultAbove1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="8" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with VL results at baseline within the last 6 months at enrollment into OTZ</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtM10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtM15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtM20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtF10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtF15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="9" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL less than 200 c/ml</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="10" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrolment into OTZ and VL result is between 200 to less than 1000 c/ml</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBt200To1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="11" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL greater than or equal to 1000 c/ml</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledBaselineResult6MtBelow1000Gt1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="12" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ without baseline VL results or with baseline VL result less than 1000 c/ml and eligible for month zero VL sample collection </td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZM10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZM15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZM20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZF10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZF15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="13" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ enrolled in the cohort month and eligible for month zero VL sample collection whose VL samples were taken (at month zero) </td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleM10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleM15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleM20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleF10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleF15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledEligibleMtZSampleF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="14" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with baseline VL results <1000 c/ml whose VL result for sample collected at month zero is less than 200 c/ml </td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="15" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with baseline VL results <1000 c/ml whose VL result for sample collected at month zero is between 200 to less than 1000 c/ml </td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ200To1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="16" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with baseline VL results <1000 c/ml  whose VL result for sample collected at month zero is greater than or equal to 1000 c/ml </td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000M10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000M15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000M20To24">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000F10To14">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000F15To19">-</td>
                <td class="loadingView num3"  id="totalEnrolledB1000MZ1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="17" data-type="1" href="javascript:void(0);" >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members with scheduled drug pick-up appointment in the follow up period</td>
                <td class="loadingView num3"  id="scheduledPickupFUM10To14">-</td>
                <td class="loadingView num3"  id="scheduledPickupFUM15To19">-</td>
                <td class="loadingView num3"  id="scheduledPickupFUM20To24">-</td>
                <td class="loadingView num3"  id="scheduledPickupFUF10To14">-</td>
                <td class="loadingView num3"  id="scheduledPickupFUF15To19">-</td>
                <td class="loadingView num3"  id="scheduledPickupFUF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="18" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
           
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members who kept their drug pick-up appointment in the follow up period</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUM10To14">-</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUM15To19">-</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUM20To24">-</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUF10To14">-</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUF15To19">-</td>
                <td class="loadingView num3"  id="scheduledKeptPickupFUF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="19" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members with good drug adherence score in the follow up period</td>
                <td class="loadingView num3"  id="goodAdhFUM10To14">-</td>
                <td class="loadingView num3"  id="goodAdhFUM15To19">-</td>
                <td class="loadingView num3"  id="goodAdhFUM20To24">-</td>
                <td class="loadingView num3"  id="goodAdhFUF10To14">-</td>
                <td class="loadingView num3"  id="goodAdhFUF15To19">-</td>
                <td class="loadingView num3"  id="goodAdhFUF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="20" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ who were eligible for routine VL test during the follow up period i.e. No VL result for the 6-month period prior to the beginning of the reporting period</td>
                <td class="loadingView num3"  id="eligibleFUM10To14">-</td>
                <td class="loadingView num3"  id="eligibleFUM15To19">-</td>
                <td class="loadingView num3"  id="eligibleFUM20To24">-</td>
                <td class="loadingView num3"  id="eligibleFUF10To14">-</td>
                <td class="loadingView num3"  id="eligibleFUF15To19">-</td>
                <td class="loadingView num3"  id="eligibleFUF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="21" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ whose samples were taken for routine VL test</td>
                <td class="loadingView num3"  id="samplesTakenM10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenM15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenM20To24">-</td>
                <td class="loadingView num3"  id="samplesTakenF10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenF15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="22" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with result for sample taken for routine VL test during the follow up period</td>
                <td class="loadingView num3"  id="samplesTakenResultM10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResultM15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResultM20To24">-</td>
                <td class="loadingView num3"  id="samplesTakenResultF10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResultF15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResultF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="23" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with result for sample taken for routine VL test during the follow up period less than 200 copies/ml</td>
                <td class="loadingView num3"  id="samplesTakenResult200M10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200M15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200M20To24">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200F10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200F15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="24" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with result for sample taken for routine VL test during the follow up period between 200 to less than 1000 copies/ml</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000M10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000M15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000M20To24">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000F10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000F15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult200To1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="25" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV in OTZ with result for sample taken for routine VL test during the follow up period greater than or equal to 1000 copies/ml
</td>
                <td class="loadingView num3"  id="samplesTakenResult1000M10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult1000M15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult1000M20To24">-</td>
                <td class="loadingView num3"  id="samplesTakenResult1000F10To14">-</td>
                <td class="loadingView num3"  id="samplesTakenResult1000F15To19">-</td>
                <td class="loadingView num3"  id="samplesTakenResult1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="26" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months</td>
                <td class="loadingView num3"  id="resultPast12MtM10To14">-</td>
                <td class="loadingView num3"  id="resultPast12MtM15To19">-</td>
                <td class="loadingView num3"  id="resultPast12MtM20To24">-</td>
                <td class="loadingView num3"  id="resultPast12MtF10To14">-</td>
                <td class="loadingView num3"  id="resultPast12MtF15To19">-</td>
                <td class="loadingView num3"  id="resultPast12MtF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="27" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ  in the cohort month with VL result within the last 12 months less than 200 copies/ml</td>
                <td class="loadingView num3"  id="resultPast12Mt200M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="28" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months is between 200 to less than 1000 copies/ml</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt200To100F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="29" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ  in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml</td>
                <td class="loadingView num3"  id="resultPast12Mt1000M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="30" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml and completed EAC</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACM10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACM15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACM20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACF10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACF15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000EACF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="31" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml who have repeat VL result</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatM10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatM15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatM20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatF10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatF15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000RepeatF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="32" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml whose repeat VL result is less than 200 copies/ml</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="33" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml whose repeat VL result is between 200 to less than 1000 copies/ml</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat200To1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="34" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of AYPLHIV enrolled in OTZ in the cohort month with VL result within the last 12 months greater than or equal to 1000 copies/ml whose repeat VL result is greater than or equal to 1000 copies/ml</td>
                 <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000M10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000M15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000M20To24">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000F10To14">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000F15To19">-</td>
                <td class="loadingView num3"  id="resultPast12Mt1000Repeat1000F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="35" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># switched to second line ART</td>
                <td class="loadingView num3"  id="switchTo2ndM10To14">-</td>
                <td class="loadingView num3"  id="switchTo2ndM15To19">-</td>
                <td class="loadingView num3"  id="switchTo2ndM20To24">-</td>
                <td class="loadingView num3"  id="switchTo2ndF10To14">-</td>
                <td class="loadingView num3"  id="switchTo2ndF15To19">-</td>
                <td class="loadingView num3"  id="switchTo2ndF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="36" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># switched to third line ART</td>
                <td class="loadingView num3"  id="switchTo3rdM10To14">-</td>
                <td class="loadingView num3"  id="switchTo3rdM15To19">-</td>
                <td class="loadingView num3"  id="switchTo3rdM20To24">-</td>
                <td class="loadingView num3"  id="switchTo3rdF10To14">-</td>
                <td class="loadingView num3"  id="switchTo3rdF15To19">-</td>
                <td class="loadingView num3"  id="switchTo3rdF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="37" data-type="1" href="javascript:void(0);" >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># of OTZ members who have completed the 7 AYP modules</td>
                <td class="loadingView num3"  id="completed7M10To14">-</td>
                <td class="loadingView num3"  id="completed7M15To19">-</td>
                <td class="loadingView num3"  id="completed7M20To24">-</td>
                <td class="loadingView num3"  id="completed7F10To14">-</td>
                <td class="loadingView num3"  id="completed7F15To19">-</td>
                <td class="loadingView num3"  id="completed7F20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="38" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># transferred out during the follow up period</td>
                <td class="loadingView num3"  id="transferredOutM10To14">-</td>
                <td class="loadingView num3"  id="transferredOutM15To19">-</td>
                <td class="loadingView num3"  id="transferredOutM20To24">-</td>
                <td class="loadingView num3"  id="transferredOutF10To14">-</td>
                <td class="loadingView num3"  id="transferredOutF15To19">-</td>
                <td class="loadingView num3"  id="transferredOutF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="39" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr><tr>
                <td><%= i++ %></td>
                <td># Lost to follow up during the follow up period</td>
                <td class="loadingView num3"  id="ltfuM10To14">-</td>
                <td class="loadingView num3"  id="ltfuM15To19">-</td>
                <td class="loadingView num3"  id="ltfuM20To24">-</td>
                <td class="loadingView num3"  id="ltfuF10To14">-</td>
                <td class="loadingView num3"  id="ltfuF15To19">-</td>
                <td class="loadingView num3"  id="ltfuF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="40" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># reported dead during the follow up period</td>
                <td class="loadingView num3"  id="deadM10To14">-</td>
                <td class="loadingView num3"  id="deadM15To19">-</td>
                <td class="loadingView num3"  id="deadM20To24">-</td>
                <td class="loadingView num3"  id="deadF10To14">-</td>
                <td class="loadingView num3"  id="deadF15To19">-</td>
                <td class="loadingView num3"  id="deadF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="41" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># that opted out of OTZ during the follow up period</td>
                <td class="loadingView num3"  id="optedOutM10To14">-</td>
                <td class="loadingView num3"  id="optedOutM15To19">-</td>
                <td class="loadingView num3"  id="optedOutM20To24">-</td>
                <td class="loadingView num3"  id="optedOutF10To14">-</td>
                <td class="loadingView num3"  id="optedOutF15To19">-</td>
                <td class="loadingView num3"  id="optedOutF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="42" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># aged 20-24 years and transitioned to adult care during the follow up  period</td>
                <td class="loadingView num3"  id="transitionedM10To14">-</td>
                <td class="loadingView num3"  id="transitionedM15To19">-</td>
                <td class="loadingView num3"  id="transitionedM20To24">-</td>
                <td class="loadingView num3"  id="transitionedF10To14">-</td>
                <td class="loadingView num3"  id="transitionedF15To19">-</td>
                <td class="loadingView num3"  id="transitionedF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="43" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><%= i++ %></td>
                <td># that exited OTZ during the follow up period</td>
                <td class="loadingView num3"  id="exitedM10To14">-</td>
                <td class="loadingView num3"  id="exitedM15To19">-</td>
                <td class="loadingView num3"  id="exitedM20To24">-</td>
                <td class="loadingView num3"  id="exitedF10To14">-</td>
                <td class="loadingView num3"  id="exitedF15To19">-</td>
                <td class="loadingView num3"  id="exitedF20To24">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="44" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            
            
        </tbody>
        
    </table>
</div>



<!-- Modal -->
<div class="modal fade" id="descriptionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle">Description</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Analytic Description</strong></div>
              <div class="col-sm-12 col-md-8"><p id="description"></p></div>
              <hr class="niceDarkHr"/>
          </div>
          
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Data Elements</strong></div>
              <div class="col-sm-12 col-md-8"><p id="dataElements"></p></div>
              <hr class="niceDarkHr"/>
          </div>
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Numerator</strong></div>
              <div class="col-sm-12 col-md-8"><p id="numerator"></p></div>
              <hr class="niceDarkHr"/>
          </div>
           <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Denominator</strong></div>
              <div class="col-sm-12 col-md-8"><p id="denominator"></p></div>
              <hr class="niceDarkHr"/>
          </div>
           <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Minimum Expectation</strong></div>
              <div class="col-sm-12 col-md-8"><p id="minimumExpectation"></p></div>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<style>


    
</style>
