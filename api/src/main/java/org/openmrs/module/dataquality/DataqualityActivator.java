/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataquality;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import java.util.UUID;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.UserContext;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.dataquality.api.dao.ClinicalDao;
import org.openmrs.module.dataquality.api.dao.Database;
import org.openmrs.module.dataquality.api.dao.HTSDao;
import org.openmrs.module.dataquality.api.dao.LabDao;
import org.openmrs.module.dataquality.api.dao.PatientDao;
import org.openmrs.module.dataquality.api.dao.PharmacyDao;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */

public class DataqualityActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
        private List<Map<String, String>> allPatientMetas = new ArrayList<>();
        private List<Map<String, String>> allClinicalEncounters = new ArrayList<>();
        private List<Map<String, String>> allLabEncounters = new ArrayList<>();
        private List<Map<String, String>> allPharmacyEncounters = new ArrayList<>();
        private List<Map<String, String>> allClientIntakeEncounters = new ArrayList<>();
        private List<Map<String, String>> allIPTEncounters = new ArrayList<>();
        
        
	PatientDao dao = new PatientDao();
        ClinicalDao clinicalDao = new ClinicalDao();
	LabDao labDao = new LabDao();
        PharmacyDao pharmacyDao = new PharmacyDao();
        HTSDao htsDao = new HTSDao();
	private String lastAnalysisDate = "";
	
	/**
	 * @see #started()
	 */
	
	public void started() {
		Database.initConnection();
		System.out.println("started data quality module");
		
		log.info("Started Dataquality");
		this.startAnalyticsTask();
		
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown Dataquality");
	}
	
	private void startAnalyticsTask() {
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
                    @Override
                    public void run() {
                        PatientDao patientDao = new PatientDao();
                        Context.openSession();
                          UserContext userContext =  Context.getUserContext();
                            
                            
                            /*if (userContext != null) {
                                    Context.setUserContext(userContext);
                            } else {
                                    Context.setUserContext(Context.getUserContext());
                            }*/
                            //get the last date that the analysis was done. The very first analysis will take some time, but subsequent onces should not take long
                            lastAnalysisDate = dao.getGlobalProperty("dqr_last_analysis_date");//Context.getAdministrationService().getGlobalProperty("dqr_last_analysis_date");
                            System.out.println("Last analysis Date" + lastAnalysisDate);
                            if(lastAnalysisDate == null || lastAnalysisDate.equalsIgnoreCase(""))
                            {
                                lastAnalysisDate = "1990-01-01";
                                UUID uuid = UUID.randomUUID();
                                String uuidAsString = uuid.toString();
                                patientDao.saveGlobalProperty("dqr_last_analysis_date", lastAnalysisDate, "Last time DQR Analysis was run", uuidAsString);
                                
                            }
                           // lastAnalysisDate = "1990-01-01";
                            System.out.println("Last analysis Date" + lastAnalysisDate);
                            System.out.println("Task Timer on Fixed Rate");
                            //get patient count
                            int totalPatients = dao.getTotalPatients();
                            System.out.println("total patient count" + totalPatients);
                            int limit = 1000;
                            int totalPages = totalPatients / limit;

                            //get patients in fragments of 1000.
                            for (int i = 0; i < totalPages; i++) {
                                    //for each fragment, perform analysis
                                    int offset = i * limit;
                                    List<Map<String,String>> allPatients = dao.getAllPatients(limit, offset, lastAnalysisDate);

                                    //loop through the patients and save encounters in flat tables
                                    for(int j=0; j<allPatients.size(); j++)
                                    {
                                        int patientId = Integer.parseInt(allPatients.get(j).get("patient_id"));
                                        
                                        allPatientMetas.addAll(dao.getPatientMeta(patientId));
                                        allClinicalEncounters.addAll(clinicalDao.getClinicalEncounters(patientId));
                                        allLabEncounters.addAll(labDao.getLabEncounters(patientId));
                                        allPharmacyEncounters.addAll(pharmacyDao.getPharmacyEncounters(patientId));
                                        allClientIntakeEncounters.addAll(htsDao.getClientIntakeEncounters(patientId));
                                        allIPTEncounters.addAll(labDao.getIPTEncounters(patientId));
                                    }
                                    //save the encounters, clear the data structures and start again
                                    dao.savePatientMeta(allPatientMetas);
                                    clinicalDao.saveClinicalEncounters(allClinicalEncounters);
                                    labDao.saveLabEncounters(allLabEncounters);
                                    pharmacyDao.savePharmacyEncounters(allPharmacyEncounters);
                                    htsDao.saveClientEncounters(allClientIntakeEncounters);
                                    labDao.saveIPTEncounters(allIPTEncounters);

                                    //clear all datastructures
                                    allPatientMetas.clear();
                                    allClinicalEncounters.clear();
                                    allLabEncounters.clear();
                                    allPharmacyEncounters.clear();
                                    allClientIntakeEncounters.clear();
                                    allIPTEncounters.clear();
                                    System.out.println("completed cycle " + i + "out of" + (totalPages - 1));
                            }
                            
                            
                            
                            ///once complete, lets save last run date
                            DateTime today = new DateTime(new Date());
                            String now = today.toString("yyyy'-'MM'-'dd HH:mm");
                            Context.getAdministrationService().updateGlobalProperty("dqr_last_analysis_date", now);
                            Context.closeSession();
                            System.out.println("completed");
                    };
		};
		t.scheduleAtFixedRate(tt, 5000, 10000);
		
	}
	
}
