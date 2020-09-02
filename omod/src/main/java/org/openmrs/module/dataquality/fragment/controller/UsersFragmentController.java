/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataquality.fragment.controller;

import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataquality.api.dao.DbConnection;
import org.openmrs.module.dataquality.util.FactoryUtils;
import org.openmrs.module.dataquality.util.Model.PatientLineList;
import org.openmrs.module.dataquality.util.Model.SummaryDashboard;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.module.dataquality.CohortMaster;
import org.openmrs.module.dataquality.HITTCohort;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
//import liquibase.util.csv.opencsv.CSVWriter;
import com.opencsv.CSVWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.openmrs.Encounter;
import org.openmrs.api.EncounterService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.openmrs.module.dataquality.Constants;
import org.openmrs.module.dataquality.api.CohortBuilder;
import org.openmrs.module.dataquality.api.DataqualityService;

public class UsersFragmentController {
	
        Map <String, Integer> dataCache = new HashMap<>();
        Map <String, DateTime> lastUpdated = new HashMap<>();
        DataqualityService dataQualityService = Context.getService(DataqualityService.class);
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		
		//DbConnection connection = new DbConnection();
		//FactoryUtils factoryUtils = new FactoryUtils();
		/*Map<String, Integer> map = new HashMap<String, Integer>();
		//List<SummaryDashboard> summaryDashboardList = factoryUtils.getEncounters();
		CohortMaster cohortMaster = new CohortMaster();
		
		//List<PatientLineList> patientLineList = factoryUtils.getPatientsLineList();
		List<PatientLineList> patientLineList = new ArrayList<PatientLineList>();
		model.addAttribute("patientLineList", patientLineList);
		
		//map.put("totalPatients", Context.getPatientService().getAllPatients().size());
		map.put("totalPatients", 0);
		double numerator = 0.0, denominator = 0.0;
		String fileName = "";
		//Educational Status
		map.put("activepatientseducationalstatuscount",
		    cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT).size());
		map.put("activepatientcount", cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
		numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
		map.put("percentageeducationalstatus", (int) cohortMaster.getPercentage(numerator, denominator));
		//HITTCohort cohortObj = cohortMaster.getHITTCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT);
		//model.put("link", cohortObj.getLineListingFileName());
		//Marital Status
		map.put("activepatientsmaritalstatuscount",
		    cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT).size());
		//map.put("activepatientcount",cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
		numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
		map.put("percentagemaritalstatus", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Marital Status
		map.put("activepatientsoccupationalstatuscount",
		    cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT).size());
		//map.put("activepatientcount",cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
		numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
		map.put("percentageoccupationalstatus", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Started ART Last 6 Months documented DOB
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		map.put("startedartlast6monthscount", (int) denominator);
		map.put("startedartlast6monthscountdocumenteddob", (int) numerator);
		map.put("percentagestartedartlast6monthswithdocumenteddob", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Started ART Last 6 Months documented Gender
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthscountdocumentedsex", (int) numerator);
		map.put("percentagestartedartlast6monthswithdocumentedsex", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Started ART Last 6 Months with Documented Date Confirmed Positive
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthscountdocumenteddateconfirmedpositive", (int) numerator);
		map.put("percentagestartedartlast6monthswithdocumenteddateconfirmedpositive",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Started ART Last 6 Months with Documented Enrollement Date
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthscountdocumentedenrollmentdate", (int) numerator);
		map.put("percentagestartedartlast6monthswithdocumenteddateenrollmentdate",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients newly started on ART in the last 6 months with documented ART Start Date
		numerator = cohortMaster.countCohort(CohortMaster.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.PICKED_UP_ARV_DRUG_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthscountwithdrugpickup", (int) numerator);
		map.put("percentagestartedartlast6monthswithdrugpickup", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients newly started on ART in the last 6 months with documented CD4 result 
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthscountwithcd4count", (int) numerator);
		map.put("percentagestartedartlast6monthswithcd4count", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients newly started on ART in the last 6 months with registered address/LGA of residence
		/*
		  --Patient newly started on ART Last 6 Months
		  --Patient with registered Address/LGA
		  --Patient newly started on ART 6 Months with documented Address/LGA
		*/
		/*numerator = cohortMaster.countCohort(CohortMaster.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		//map.put("startedartlast6monthscount",(int)denominator);
		map.put("startedartlast6monthswithdocumentedlga", (int) numerator);
		map.put("percentagestartedartlast6monthswithdocumentedlga", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a clinic visit in the last 6 months that had documented weight
		numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH);
		denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);
		
		map.put("clinicvisitlast6monthscount", (int) denominator);
		map.put("clinicvisitlast6monthswithdocumentedweight", (int) numerator);
		map.put("percentagestclinicvisitlast6monthswithdocumentedweight",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC 
		numerator = cohortMaster.countCohort(CohortMaster.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC);
		denominator = cohortMaster.countCohort(CohortMaster.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS);
		
		map.put("pediatricclinicvisitlast6monthscount", (int) denominator);
		map.put("pediatricclinicvisitlast6monthswithdocumentedmuac", (int) numerator);
		map.put("percentagestclinicvisitlast6monthswithdocumentedmuac",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC 
		numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO);
		denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);
		
		//map.put("pediatricclinicvisitlast6monthscount",(int)denominator);
		map.put("clinicvisitlast6monthswithdocumentedwho", (int) numerator);
		map.put("percentagestclinicvisitlast6monthswithdocumentedwho",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a clinic visit in the last 6 months that had documented TB status 
		numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS);
		denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);
		
		//map.put("pediatricclinicvisitlast6monthscount",(int)denominator);
		map.put("clinicvisitlast6monthswithdocumentedtbstatus", (int) numerator);
		map.put("percentagestclinicvisitlast6monthswithdocumentedtbstatus",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a documented regimen duration in the last drug refill visit
		numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION);
		denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);
		
		map.put("lastarvpickupcohort", (int) denominator);
		map.put("lastarvpickupwithdurationcohort", (int) numerator);
		map.put("percentagelastarvpickupwithduration", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a documented regimen quantity in the last drug refill visit
		numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY);
		denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);
		
		//map.put("lastarvpickupcohort",(int)denominator);
		map.put("lastarvpickupwithquantitycohort", (int) numerator);
		map.put("percentagelastarvpickupwithquantity", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with documented ART regimen in the last drug refill visit
		numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN);
		denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);
		
		//map.put("lastarvpickupcohort",(int)denominator);
		map.put("lastarvpickupwithregimencohort", (int) numerator);
		map.put("percentagelastarvpickupwithregimen", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a regimen duration more than six(6) months  in the last drug refill visit
		numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS);
		denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);
		
		//map.put("lastarvpickupcohort",(int)denominator);
		map.put("lastarvpickupwithregimendurationmorethan6monthscohort", (int) numerator);
		map.put("percentagelastarvpickupwithregimendurationmorethan6months",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of eligible patients with documented Viral Load results done in the last one year
		numerator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT);
		denominator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_COHORT);
		
		map.put("viralloadeligible", (int) denominator);
		map.put("viralloadeligiblewithresultcohort", (int) numerator);
		map.put("percentageeligiblewithdocumentedresult", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of eligible patients with documented Viral Load results done in the last one year
		numerator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE);
		denominator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_WITH_SAMPLE_COLLECTION);
		
		map.put("viralloadeligiblewithsamplecollection", (int) denominator);
		map.put("viralloadresultwithsamplecollection", (int) numerator);
		map.put("percentageeligiblewithsamplecollected", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with Viral Load result that had documented specimen sent date 
		numerator = cohortMaster.countCohort(CohortMaster.SAMPLE_COLLECTED_WITH_SAMPLE_SENT_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_WITH_SAMPLE_COLLECTION);
		
		map.put("viralloadeligiblewithsamplecollection", (int) denominator);
		map.put("samplesollectedwithsamplesentcohort", (int) numerator);
		map.put("percentagesamplecollectedwithsamplesent", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with Viral Load result that had documented specimen received at PCR lab date 
		numerator = cohortMaster.countCohort(CohortMaster.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR);
		denominator = cohortMaster.countCohort(CohortMaster.SAMPLE_COLLECTED_WITH_SAMPLE_SENT_COHORT);
		
		//map.put("samp",(int)denominator);
		map.put("viralloadresultswithsamplesentandreceivedatpcrlab", (int) numerator);
		map.put("percentagesamplereceivedatpcrwithsamplesent", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients with a clinic visit in the last 6 months that had documented functional status
		numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS);
		denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);
		
		//map.put("samp",(int)denominator);
		map.put("clinicvisitdocumentedfunctionalstatus", (int) numerator);
		map.put("percentageclinicvisitdocumentedfunctionalstatus", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of patients newly started on ART in the last 6 months with initial ART regimen
		numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN);
		denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
		
		//map.put("samp",(int)denominator);
		map.put("newlystartedonartwithinitialartregimen", (int) numerator);
		map.put("percentagenewlystartedonartdocumentedinitialregimen",
		    (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of all patients with a clinic visit in the last 6 months that have documented next scheduled appointment date
		numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE);
		denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);
		
		//map.put("samp",(int)denominator);
		map.put("clinicvisitlast6monthswithnextappointmentdate", (int) numerator);
		map.put("percentageclinicvisitwithnextappointmentdate", (int) cohortMaster.getPercentage(numerator, denominator));
		
		//Proportion of all inactive patients with a documented exit reason
		numerator = cohortMaster.countCohort(CohortMaster.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT);
		denominator = cohortMaster.countCohort(CohortMaster.INACTIVE_PATIENT_COHORT);
		
		map.put("inactiveccohort", (int) denominator);
		map.put("documentedexitreasoninactive", (int) numerator);
		map.put("percentageinactivedocumentedexitreason", (int) cohortMaster.getPercentage(numerator, denominator));*/
		
		//model.mergeAttributes(map);
		//Constants c = new Constants();
		
		model.addAttribute("constants", Constants.class);
		
	}
	
    public String getCohortCounts(HttpServletRequest request) {
        int type = Integer.parseInt(request.getParameter("type"));
        DateTime endDateTime = new DateTime(new Date());
	DateTime startDateTime = endDateTime.minusMonths(10006);
        String startDate = startDateTime.toString("yyyy'-'MM'-'dd' 'HH':'mm");
        String endDate = endDateTime.toString("yyyy'-'MM'-'dd' 'HH':'mm");
        CohortBuilder builder = new CohortBuilder();
        
         
        if(type == Constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)
        {
            System.out.println("1");
            Map<String, String>dataMap = new HashMap<>();
           
            int totalActiveWithDocEducationalStatus = dataQualityService.getActivePatientsWithDocumentedEducationalStatus();//CohortBuilder.getActivePatientsWithDocumentedEducationalStatus();
           // int totalActivePts = CohortBuilder.getActivePatientCount();
           int totalActivePts =dataQualityService.getActivePatientCount();//this.getTotalActivePts();
           
           System.out.println("done fetching");
            float percent = (totalActiveWithDocEducationalStatus * 100/ totalActivePts);
            dataMap.put("numerator", totalActiveWithDocEducationalStatus+"");
            dataMap.put("denominator", totalActivePts+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
            //return totalActiveWithDocEducationalStatus+"";
        }
        else if(type == Constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)
        {
            System.out.println("2");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getActivePatientsWithDocumentedMaritalStatus();//CohortBuilder.getActivePatientsWithDocumentedMaritalStatus();
            int denominator = dataQualityService.getActivePatientCount();//CohortBuilder.getActivePatientCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        else if(type == Constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)
        {
            System.out.println("3");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getActivePatientsWithDocumentedOccupationalStatus();//CohortBuilder.getActivePatientsWithDocumentedOccupationalStatus();
            int denominator = dataQualityService.getActivePatientCount();//CohortBuilder.getActivePatientCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)
        {
            System.out.println("4");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocumentedDobCount(startDate, endDate);//CohortBuilder.getPatientsWithDocumentedDobCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }   
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)
        {
            System.out.println("5");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocumentedGenderCount(startDate, endDate);//CohortBuilder.getPatientsWithDocumentedGenderCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)
        {
            System.out.println("6");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocumentedPostiveDateCount(startDate, endDate);//CohortBuilder.getPatientsWithDocumentedPostiveDateCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)
        {
            System.out.println("7");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocumentedHIVEnrollmentCount(startDate, endDate);//CohortBuilder.getPatientsWithDocumentedHIVEnrollmentCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }

        else if(type == Constants.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT)
        {
            System.out.println("8");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWhoPickARVCount(startDate, endDate);//CohortBuilder.getPatientsWhoPickARVCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }        
        
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)
        {
            System.out.println("9");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocCd4CntCount(startDate, endDate);//CohortBuilder.getPatientsWithDocCd4CntCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }  
        else if(type == Constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)
        {
            System.out.println("10");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWithDocumentedAddress(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }  
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH)
        {
            System.out.println("11");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithClinicalVisitDocWeightCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitDocWeightCount(startDate, endDate);
            int denominator = dataQualityService.getPtsWithClinicalVisitCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }  
        else if(type == Constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)
        {
            System.out.println("12");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithClinicalVisitDocMUACCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitDocMUACCount(startDate, endDate);
            int denominator = dataQualityService.getPedPtsWithClinicalVisitCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitCount(startDate, endDate);//check and confirm. But this should be for pediatrics alone and not total clinic visit
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 

        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)
        {
            System.out.println("13");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithClinicalVisitDocWHOCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitDocWHOCount(startDate, endDate);
            int denominator = dataQualityService.getPtsWithClinicalVisitCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)
        {
            System.out.println("14");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithClinicalVisitDocTBStatusCount(startDate, endDate);//CohortBuilder.getPtsWithClinicalVisitDocTBStatusCount(startDate, endDate);
            int denominator = dataQualityService.getPtsWithClinicalVisitCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)
        {
            System.out.println("15");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithDocLastARVPickupWithDurationCount();//CohortBuilder.getPtsWithDocLastARVPickupWithDurationCount();
            int denominator = dataQualityService.getPtsWithDocLastARVPickupCount();//CohortBuilder.getPtsWithDocLastARVPickupCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)
        {
            System.out.println("16");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithDocLastARVPickupWithQtyCount();//CohortBuilder.getPtsWithDocLastARVPickupWithQtyCount();
            int denominator = dataQualityService.getPtsWithDocLastARVPickupCount();//CohortBuilder.getPtsWithDocLastARVPickupCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN)
        {
            System.out.println("17");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithDocLastARVPickupWithRegiminCount();//CohortBuilder.getPtsWithDocLastARVPickupWithRegiminCount();
            int denominator = dataQualityService.getPtsWithDocLastARVPickupCount();//CohortBuilder.getPtsWithDocLastARVPickupCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS)
        {
            System.out.println("18");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPtsWithDocLastARVPickupWithDurationMoreThan180Count();//CohortBuilder.getPtsWithDocLastARVPickupWithDurationMoreThan180Count();
            int denominator = dataQualityService.getPtsWithDocLastARVPickupCount();//CohortBuilder.getPtsWithDocLastARVPickupCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        
        else if(type == Constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)
        {
            System.out.println("19");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsEligibleForVLWithResultCount();//CohortBuilder.getPtsEligibleForVLWithResultCount();
            int denominator = dataQualityService.getPtsEligibleForVLCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        
        else if(type == Constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)
        {
            System.out.println("20");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsEligibleForVLWithSampleCollectionCount();
            int denominator = dataQualityService.getPtsEligibleForVLCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)
        {
            System.out.println("21");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsEligibleForVLWithSampleReceivedCount();
            int denominator = dataQualityService.getPtsEligibleForVLCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)
        {
            System.out.println("22");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsWithClinicalVisitFunctionalStatusCount(startDate, endDate);
            int denominator = dataQualityService.getPtsWithClinicalVisitCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)
        {
            System.out.println("23");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsOnArtWithInitialRegimen(startDate, endDate);//CohortBuilder.getPtsOnArtWithInitialRegimen(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        } 
        
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)
        {
            System.out.println("24");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getPtsWithClinicalVisitNextAppDateCount(startDate, endDate);
            int denominator = dataQualityService.getPtsWithClinicalVisitCount(startDate, endDate);
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        
        else if(type == Constants.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT)
        {
            System.out.println("25");
            Map<String, String>dataMap = new HashMap<>();
            int numerator = dataQualityService.getInactivePtsWithReasonCount();
            int denominator = dataQualityService.getInactivePtsCount();
            float percent = (numerator * 100 / denominator);
            dataMap.put("numerator", numerator+"");
            dataMap.put("denominator", denominator+"");
            dataMap.put("percent", percent+"");
            return new JSONObject(dataMap).toString();
        }
        
        return "hi";
        
    }
        
    private int getTotalActivePts()
    {
        int totalActivePts = 0;
        DateTime now = new DateTime();
        System.out.println(dataCache.get("totalActivePts"));
        if(dataCache.containsKey("totalActivePts") && lastUpdated.containsKey("totalActivePts") && lastUpdated.get("totalActivePts").isAfter(now.minusMinutes(1)))
        {
           System.out.println("from cacheee");
           totalActivePts = dataCache.get("totalActivePts");//dataQualityService.getActivePatientCount();//builder.getActivePatientCount(); 
        }else
        {
            totalActivePts = dataQualityService.getActivePatientCount();//builder.getActivePatientCount(); 
            dataCache.put("totalActivePts", totalActivePts);
            lastUpdated.put("totalActivePts", now);
            System.out.println("from dss");
        }
        
        return totalActivePts;
    }
}
