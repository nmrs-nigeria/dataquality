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

public class PatientsFragmentController {
	
	DataqualityService dataQualityService = Context.getService(DataqualityService.class);
	
	public void controller(FragmentModel model, HttpServletRequest request) {
		
        int type = Integer.parseInt(request.getParameter("type"));
        DateTime endDateTime = new DateTime(new Date());
	DateTime startDateTime = endDateTime.minusMonths(6);
        String startDate = startDateTime.toString("yyyy'-'MM'-'dd' 'HH':'mm");
        String endDate = endDateTime.toString("yyyy'-'MM'-'dd' 'HH':'mm");
        CohortBuilder builder = new CohortBuilder();
        
         
        if(type == Constants.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT)
        {
           
           List<Object> data = dataQualityService.getActivePatientsWithoutDocumentedEducationalStatus();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of all active patients without a documented educational status ");
            //return totalActiveWithDocEducationalStatus+"";
        }
        else if(type == Constants.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT)
        {
            System.out.println("2");
             List<Object> data = dataQualityService.getActivePatientsWithoutDocumentedMaritalStatus();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of all active patients without a documented marital status ");
           
        }
        else if(type == Constants.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT)
        {
            System.out.println("3");
            List<Object> data = dataQualityService.getActivePatientsWithoutDocumentedOccupationalStatus();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of all active patients without a documented occupational status ");
           
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB)
        {
            System.out.println("4");
            List<Object> data = dataQualityService.getPatientsWithoutDocumentedDob(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               tempData.put("link", "/registrationapp/editSection.page?patientId="+patientId+"&sectionId=demographics&appId=referenceapplication.registrationapp.registerPatient");
               
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without documented age and/or Date of Birth ");
           
            
        }   
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX)
        {
            System.out.println("5");
            List<Object> data = dataQualityService.getPatientsWithoutDocumentedGender(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               tempData.put("link", "/registrationapp/editSection.page?patientId="+patientId+"&sectionId=demographics&appId=referenceapplication.registrationapp.registerPatient");
               
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without documented gender ");
           
            
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE)
        {
            System.out.println("6");
           List<Object> data = dataQualityService.getPatientsWithoutDocumentedPostiveDate(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without documented date of HIV diagnosis ");
            
        }
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT)
        {
            System.out.println("7");
            List<Object> data = dataQualityService.getPatientsWithoutDocumentedHIVEnrollment(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               //int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
              // tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
              tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without documented  HIV Enrollment ");
            
            
        }

        else if(type == Constants.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT)
        {
            System.out.println("8");
            Map<String, String>dataMap = new HashMap<>();
            
            int numerator = dataQualityService.getPatientsWhoPickARVCount(startDate, endDate);//CohortBuilder.getPatientsWhoPickARVCount(startDate, endDate);
            int denominator = dataQualityService.getPatientsOnARTCount(startDate, endDate);//CohortBuilder.getPatientsOnARTCount(startDate, endDate);
            
        }        
        
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT)
        {
            System.out.println("9");
             List<Object> data = dataQualityService.getPatientsWithoutDocCd4Cnt(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               //int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
              // tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
              tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               
               
               patientData.add(tempData);
           } 
           model.addAttribute("data", patientData); 
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without documented  CD4 Count");
            
           
        }  
        else if(type == Constants.NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA)
        {
            System.out.println("10");
            List<Object> data = dataQualityService.getPatientsWithoutDocumentedAddress(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               tempData.put("link", "/registrationapp/editSection.page?patientId="+patientId+"&sectionId=contactInfo&appId=referenceapplication.registrationapp.registerPatient");
               
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without registered address/LGA of residence ");
           
            
        }  
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH)
        {
            System.out.println("11");
            List<Object> data = dataQualityService.getPtsWithClinicalVisitNoDocWeight(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients with a clinic visit in the last 6 months that had no documented weight ");
           
            
            
        }  
        else if(type == Constants.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC)
        {
            System.out.println("12");
           System.out.println("11");
            List<Object> data = dataQualityService.getPtsWithClinicalVisitNoDocMUAC(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
              
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
        
           model.addAttribute("title", "Proportion of pediatric patients with a clinic visit in the last 6 months that had no documented MUAC  ");
            
        } 

        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO)
        {
            System.out.println("13");
            List<Object> data = dataQualityService.getPtsWithClinicalVisitNoDocWHO(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients with a clinic visit in the last 6 months that had no documented WHO clinical stage");
            
            
            
        } 
        
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS)
        {
            System.out.println("14");
            List<Object> data = dataQualityService.getPtsWithClinicalVisitNoDocTBStatus(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients with a clinic visit in the last 6 months that had no documented TB status");
            
            
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION)
        {
            System.out.println("15");
            List<Object> data = dataQualityService.getPtsWithDocLastARVPickupWithoutDurationCount();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients without a documented ART regimen duration in the last drug refill visit");
            
            
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY)
        {
            System.out.println("16");
            List<Object> data = dataQualityService.getPtsWithDocLastARVPickupWithoutQtyCount();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients without a documented ART regimen quantity in the last drug refill visit");
            
            
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN)
        {
            System.out.println("17");
            List<Object> data = dataQualityService.getPtsWithDocLastARVPickupWithoutDurationCount();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           model.addAttribute("title", "Proportion of patients without documented ART regimen in the last drug refill visit");
            
        } 
        else if(type == Constants.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS)
        {
            System.out.println("18");
            List<Object> data = dataQualityService.getPtsWithDocLastARVPickupWithDurationMoreThan180();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           model.addAttribute("title", "Proportion of patients with ART regimen duration  more than six(6) months in the last drug refill visit");
            
        } 
        
        else if(type == Constants.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT)
        {
            System.out.println("19");
            List<Object> data = dataQualityService.getPtsEligibleForVLWithoutResult();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = 0;//Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           model.addAttribute("title", "Proportion of eligible patients without documented Viral Load results done in the last one year");
            
            
        } 
        
        else if(type == Constants.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE)
        {
            System.out.println("20");
            List<Object> data = dataQualityService.getPtsEligibleForVLWithoutSampleCollection();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           model.addAttribute("title", "Proportion of patients with Viral Load result that had no documented specimen collection date ");
            
           
        } 
        else if(type == Constants.SAMPLE_SENT_WITH_SAMPLE_RECEIVED_AT_PCR)
        {
            System.out.println("21");
            List<Object> data = dataQualityService.getPtsEligibleForVLWithoutSampleReceived();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = 0;//Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[1].toString();
               String firstName = dataObject[2].toString();
               String lastName = dataObject[3].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           model.addAttribute("title", "Proportion of patients with Viral Load result that had no documented specimen collection date ");
            
           
        } 
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_WITH_FUNCTIONAL_STATUS)
        {
            System.out.println("22");
            List<Object> data = dataQualityService.getPtsWithClinicalVisitNoFunctionalStatus(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients with a clinic visit in the last 6 months that had no documented functional status");
            
           
        } 
        
        else if(type == Constants.STARTED_ART_LAST_6MONTHS_WITH_INITIAL_REGIMEN)
        {
            System.out.println("23");
             List<Object> data = dataQualityService.getPtsOnArtWithNoInitialRegimen(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of patients newly started on ART in the last 6 months without initial ART regimen");
            
           
            
        } 
        
        else if(type == Constants.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_NEXT_APPOINTMENT_DATE)
        {
            System.out.println("24");
           List<Object> data = dataQualityService.getPtsWithClinicalVisitNoNextAppDate(startDate, endDate);
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               if(encounterId == 0)
               {
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               }
               else{
                   tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               }
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of all patients with a clinic visit in the last 6 months that have no documented next scheduled appointment date");
            
           
            
        }
        
        else if(type == Constants.DOCUMENTED_EXIT_REASON_INACTIVE_COHORT)
        {
            System.out.println("25");
           
           List<Object> data = dataQualityService.getInactivePtsWithoutReasonCount();
           
           List<Map<String, String>> patientData = new ArrayList<>();
           
           for(int i=0; i<data.size(); i++)
           {
               //Map<String, Object> d = (HashMap<String, Object>)data.get(i);
               Object[] dataObject = (Object[]) data.get(i);
               String patientId = dataObject[0].toString();
               int encounterId = Integer.parseInt(dataObject[1].toString());
               String patientIdentifier = dataObject[2].toString();
               String firstName = dataObject[3].toString();
               String lastName = dataObject[4].toString();
               Map<String, String> tempData = new HashMap<>();
               tempData.put("patientId", patientId);
               tempData.put("encounterId", encounterId+"");
               tempData.put("patientIdentifier", patientIdentifier);
               tempData.put("firstName", firstName);
               tempData.put("lastName", lastName);
               //if(encounterId == 0)
               //{
                   tempData.put("link", "/coreapps/clinicianfacing/patient.page?patientId="+patientId);
               //}
               //else{
                 //  tempData.put("link", "/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?patientId="+patientId+"&encounterId="+encounterId+"");
               //}
               
               patientData.add(tempData);
           }
            
           model.addAttribute("data", patientData);
           
           model.addAttribute("title", "Proportion of all inactive patients without a documented exit reason");
            
           
            
        }
        
	model.addAttribute("constants", Constants.class);
		
    }
}
