/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dataquality.fragment.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.openmrs.module.dataquality.Misc;
import org.openmrs.module.dataquality.OTZPatient;
import org.openmrs.module.dataquality.api.dao.ARTDao;
import org.openmrs.module.dataquality.api.dao.HTSDao;
import org.openmrs.module.dataquality.api.dao.LabDao;
import org.openmrs.module.dataquality.api.dao.OTZDao;
import org.openmrs.module.dataquality.api.dao.PharmacyDao;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * @author lordmaul
 */
public class OtzdetailsFragmentController {
	
	public void controller(FragmentModel model, HttpServletRequest request) {
		int subSet = Integer.parseInt(request.getParameter("subset"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		DateTime startDateTime = new DateTime();
		DateTime endDateTime = new DateTime();
		
		if (request.getParameter("startDate") != null && !request.getParameter("startDate").equalsIgnoreCase("")) {
			startDateTime = new DateTime(request.getParameter("startDate"));
			endDateTime = new DateTime(request.getParameter("endDate"));
		} else {
			startDateTime = new DateTime("1990-01-01");
			endDateTime = new DateTime(new Date());
		}
		
		DateTime sixMonthsAgo = endDateTime.minusMonths(6);
		//Database.initConnection();
		
		String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");
		
		String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
		String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
		
		OTZDao otzDao = new OTZDao();
		
		model.addAttribute("subset", subSet);
		
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 2) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithScheduledPickup6MonthsBefore(startDate, endDate,
			    sixMonths);
			model.addAttribute("title",
			    "# of OTZ members with scheduled drug pick-up appointment in the last six months prior to enrollment on OTZ ( "
			            + startDate + " and " + endDate + ")");
			if (type == 2) {
				model.addAttribute("title",
				    "# of OTZ members with scheduled drug pick-up appointment in the last six months prior to enrollment on OTZ ( "
				            + startDate + " and " + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 3) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWhoKeptScheduledPickup6MonthsBefore(startDate, endDate,
			    sixMonths);
			model.addAttribute("title",
			    "# of OTZ members who kept their drug pick-up appointment in the last six months prior to enrolment on OTZ ( "
			            + startDate + " and " + endDate + ")");
			if (type == 2) {
				model.addAttribute("title",
				    "# of OTZ members who kept their drug pick-up appointment in the last six months prior to enrolment on OTZ ( "
				            + startDate + " and " + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 4) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithGoodAdhScore6MonthsBefore(startDate, endDate,
			    sixMonths);
			model.addAttribute("title",
			    "# of OTZ members with good drug adherence score in the last six months prior to enrolment on OTZ ( "
			            + startDate + " and " + endDate + ")");
			if (type == 2) {
				model.addAttribute("title",
				    "# of OTZ members with good drug adherence score in the last six months prior to enrolment on OTZ ( "
				            + startDate + " and " + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 5) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBefore(startDate, endDate, sixMonths);
			model.addAttribute("title",
			    "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ  ( "
			            + startDate + " and " + endDate + ")");
			if (type == 2) {
				model.addAttribute("title",
				    "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ  ( "
				            + startDate + " and " + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 6) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndBelow200(startDate, endDate,
			    sixMonths);
			model.addAttribute(
			    "title",
			    "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result less than 200 c/ml  ( "
			            + startDate + " and " + endDate + ")");
			if (type == 2) {
				model.addAttribute(
				    "title",
				    "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result less than 200 c/ml  ( "
				            + startDate + " and " + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 7) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndBtw200AND1000(startDate, endDate, sixMonths);
			model.addAttribute("title", "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result between 200 to less than 1000 c/ml  ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result between 200 to less than 1000 c/ml  ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 8) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndAboveOrEqual1000(startDate, endDate, sixMonths);
			model.addAttribute("title", "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result greater than or equal to 1000 c/ml th ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV in OTZ with baseline VL results (VL within the last 12 months) at enrolment into OTZ and VL result greater than or equal to 1000 c/ml  ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 9) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBefore(startDate, endDate, sixMonths);
			model.addAttribute("title", " # of AYPLHIV in OTZ with VL results at baseline within the last 6 months at enrollment into OTZ ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", " # of AYPLHIV in OTZ with VL results at baseline within the last 6 months at enrollment into OTZ ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 10) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBeforeAndBelow200(startDate, endDate, sixMonths);
			model.addAttribute("title", "# of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL less than 200 c/ml( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL less than 200 c/ml ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 11) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBeforeAndBtw200AND1000(startDate, endDate, sixMonths);
			model.addAttribute("title", "# of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL greater than or equal to 1000 c/ml ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV in OTZ with VL result at baseline within the last 6 months at enrollment into OTZ and VL greater than or equal to 1000 c/ml ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
			model.addAttribute("title", "# of AYPLHIV enrolled in OTZ in the cohort month ( " + startDate + " and "
			        + endDate + ")");
			if (type == 2) {
				model.addAttribute("title", "# of AYPLHIV not enrolled in OTZ in the cohort month ( " + startDate + " and "
				        + endDate + ")");
			}
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("Misc", Misc.class);
		}
		
	}
}
