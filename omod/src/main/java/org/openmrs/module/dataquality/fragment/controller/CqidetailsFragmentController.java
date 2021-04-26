/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dataquality.fragment.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.openmrs.module.dataquality.api.dao.ARTDao;
import org.openmrs.module.dataquality.api.dao.HTSDao;
import org.openmrs.module.dataquality.api.dao.LabDao;
import org.openmrs.module.dataquality.api.dao.PharmacyDao;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * @author lordmaul
 */
public class CqidetailsFragmentController {
	
	public void controller(FragmentModel model, HttpServletRequest request) {
		int subSet = Integer.parseInt(request.getParameter("subset"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		HTSDao htsDao = new HTSDao();
		ARTDao artDao = new ARTDao();
		LabDao labDao = new LabDao();
		PharmacyDao pharmacyDao = new PharmacyDao();
		
		model.addAttribute("subset", subSet);
		
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Map<String, String>> allPatients = htsDao.getAllAdultsOfferedIndexTesting(startDate, endDate);
			model.addAttribute("title", "Adult PLHIV offered index testing between " + startDate + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 2) {//Percentage of new ped PLHIV offered index testing
			//lets get the data
			List<Map<String, String>> allPatients = htsDao.getAllPedsOfferedIndexTesting(startDate, endDate);
			model.addAttribute("title", "Pediatric PLHIV offered index testing between " + startDate + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 3) {//adult clients tested hiv positive
			//lets get the data
			List<Map<String, String>> allPatients = htsDao
			        .getAllAdultClientsPatientsTestedPositiveForHIV(startDate, endDate);
			model.addAttribute("title", "Adult clients tested HIV positive between " + startDate + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 4) {//adult clients tested hiv positive
			//lets get the data
			List<Map<String, String>> allPatients = htsDao.getAllPedClientsPatientsTestedPositiveForHIV(startDate, endDate);
			model.addAttribute("title", "Pediatric clients tested HIV positive between " + startDate + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 5) {//adults started on art
			//lets get the data
			List<Map<String, String>> allPatients = artDao.getAllAdultsStartedOnArt(startDate, endDate);
			model.addAttribute("title", "Adult patients started on Antiretroviral therapy (ART) between " + startDate
			        + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 6) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = artDao.getAllPedsStartedOnArt(startDate, endDate);
			model.addAttribute("title", "Pediatric patients started on Antiretroviral therapy (ART) between " + startDate
			        + " and " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 7) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = artDao.getAllPatientsReceivingARV(startDate, endDate);
			model.addAttribute("title", "Patients with " + startDate + " and is currently receiving ART as at " + endDate);
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		
		if (subSet == 8) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = labDao.getAllARVPtsWithVLRequest6Months(startDate, endDate);
			model.addAttribute("title",
			    "List of  ART patients with a viral load (VL) request at six (6) months after commencing ART. ");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 9) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = labDao.getAllARVPtsWithVLRequest7Months(startDate, endDate);
			model.addAttribute("title",
			    "List of  ART patients with a viral load (VL) request at seven (7) months after commencing ART. ");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		
		if (subSet == 10) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = labDao.getAllPtsWithSuppressedFirstVl(startDate, endDate);
			model.addAttribute("title", "List of  ART patients with a suppressed first viral load  ");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 11) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = labDao.getAllPedPtsWithSuppressedFirstVl(startDate, endDate);
			model.addAttribute("title", "List of  Pediatric ART patients with a suppressed first viral load  ");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 12) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = pharmacyDao.getAllPtsWithMissedAppointment(startDate, endDate);
			model.addAttribute("title", "List of  Patients with missed appoitment between " + startDate + " and " + endDate
			        + " who did not return to care");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (subSet == 13) {//peds started on art 
			//lets get the data
			List<Map<String, String>> allPatients = pharmacyDao.getAllPedPtsWithMissedAppointment(startDate, endDate);
			model.addAttribute("title", "List of Ped  Patients with missed appoitment between " + startDate + " and "
			        + endDate + " who did not return to care");
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		
	}
}
