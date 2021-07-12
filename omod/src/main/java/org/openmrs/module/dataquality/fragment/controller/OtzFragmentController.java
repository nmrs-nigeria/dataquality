/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dataquality.fragment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.openmrs.api.UserService;
import org.openmrs.module.dataquality.OTZPatient;
import org.openmrs.module.dataquality.api.dao.Database;
import org.openmrs.module.dataquality.api.dao.OTZDao;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * @author lordmaul
 */
public class OtzFragmentController {
	
	OTZDao otzDao = new OTZDao();
	
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		model.addAttribute("testing", "test");
		model.addAttribute("title", "OTZ");
		Database.initConnection();
	}
	
	public String getAllEnrolledInOTZ(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            //Database.initConnection();

            System.out.println("start date time"+startDateTime);
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            
            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;
            
            List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZ(startDate, endDate);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }
            
            
            Map<String, String> dataMap = new HashMap<>();
           
            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithScheduledPickup6MonthsBefore(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithScheduledPickup6MonthsBefore(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWhoKeptScheduledPickup6MonthsBefore(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWhoKeptScheduledPickup6MonthsBefore(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithGoodAdhScore6MonthsBefore(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithGoodAdhScore6MonthsBefore(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL12MonthsBefore(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBefore(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL12MonthsBeforeAndBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL12MonthsBeforeAndBtw200AND1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndBtw200AND1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL12MonthsBeforeAndAboveOrEqual1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL12MonthsBeforeAndAboveOrEqual1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL6MonthsBefore(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBefore(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL6MonthsBeforeAndBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBeforeAndBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL6MonthsBeforeAndBtw200AND1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBeforeAndBtw200AND1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVL6MonthsBeforeAndAboveOrEqual1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVL6MonthsBeforeAndAboveOrEqual1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEligibleForMonthZeroVL(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEligibleForMonthZeroVL(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEligibleForMonthZeroVLWithSampleCollectedAtEnrollment(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEligibleForMonthZeroVLWithSampleCollectedAtEnrollment(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalWithBaseLineVLBelow1000AndMonthZeroVlBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalWithBaseLineVLBelow1000AndMonthZeroVlBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalWithBaseLineVLBelow1000AndMonthZeroVlAbove200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalWithBaseLineVLBelow1000AndMonthZeroVlAbove200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalWithBaseLineVLBelow1000AndMonthZeroVlAbove1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalWithBaseLineVLBelow1000AndMonthZeroVlAbove1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithScheduledPickupAfter(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithScheduledPickupAfter(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWhoKeptScheduledPickupAfter(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWhoKeptScheduledPickupAfter(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithGoodAdhScoreAfter(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithGoodAdhScoreAfter(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVL(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVL(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVLWithSampleTaken(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVLWithSampleTaken(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVLWithSampleTakenAndResult(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVLWithSampleTakenAndResult(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVLWithSampleTakenAndResultBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVLWithSampleTakenAndResultBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVLWithSampleTakenAndResultAbove200Below1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVLWithSampleTakenAndResultAbove200Below1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledEligibleForVLWithSampleTakenAndResultAbove1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledEligibleForVLWithSampleTakenAndResultAbove1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12Months(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12Months(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove200Below1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove200Below1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000CompletedEAC(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000CompletedEAC(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVl(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVl(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlBelow200(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlBelow200(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlAbove200Below1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlAbove200Below1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlAbove1000(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithVLPast12MonthsResultAbove1000WithRepeatVlAbove1000(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithSwitchTo2ndLine(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithSwitchTo2ndLine(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledWithSwitchTo3rdLine(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalEnrolledWithSwitchTo3rdLine(startDate, endDate, sixMonths);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalAYPLHIVEnrolledInOTZWhoComplete7(HttpServletRequest request) {
            DateTime startDateTime = new DateTime(request.getParameter("startDate"));
            DateTime endDateTime = new DateTime(request.getParameter("endDate"));
            DateTime sixMonthsAgo = endDateTime.minusMonths(6);
            //Database.initConnection();

            
            String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
            String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
            String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

            int male10To14=0;
            int male15To19=0;
            int male20To24=0;
            int female10To14=0;
            int female15To19=0;
            int female20To24=0;

            List<OTZPatient> allPatients = otzDao.getTotalAYPLHIVEnrolledInOTZWhoComplete7(startDate, endDate);
            for(int i=0; i<allPatients.size(); i++)
            {
                System.out.println(allPatients.get(i).getGender());
                if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        male10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        male15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        male20To24++;
                    }
                }
                else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
                {
                    if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
                    {
                        female10To14++;
                    }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
                    {
                        female15To19++;
                    }
                    else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
                    {
                        female20To24++;
                    }
                }
            }


            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("male10To14",  male10To14+"");
            dataMap.put("male15To19",  male15To19+"");
            dataMap.put("male20To24",  male20To24+"");
            dataMap.put("female10To14",  female10To14+"");
            dataMap.put("female15To19",  female15To19+"");
            dataMap.put("female20To24",  female20To24+"");
            //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
            return new JSONObject(dataMap).toString();

    }
	
	public String getTotalEnrolledAndTransferredOutAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndTransferredOutAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
	
	public String getTotalEnrolledAndLTFUAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndLTFUAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
	
	public String getTotalEnrolledAndDiedAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndDiedAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
	
	public String getTotalEnrolledAndOptedOutAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndOptedOutAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
	
	public String getTotalEnrolledAndTransitionedAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndTransitionedAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
	
	public String getTotalEnrolledAndExitedAfter(HttpServletRequest request) {
	        DateTime startDateTime = new DateTime(request.getParameter("startDate"));
	        DateTime endDateTime = new DateTime(request.getParameter("endDate"));
	        DateTime sixMonthsAgo = endDateTime.minusMonths(6);
	        //Database.initConnection();

	        
	        String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
	        String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
	        String sixMonths = sixMonthsAgo.toString("yyyy'-'MM'-'dd");

	        int male10To14=0;
	        int male15To19=0;
	        int male20To24=0;
	        int female10To14=0;
	        int female15To19=0;
	        int female20To24=0;

	        List<OTZPatient> allPatients = otzDao.getTotalEnrolledAndExitedAfter(startDate, endDate);
	        for(int i=0; i<allPatients.size(); i++)
	        {
	            System.out.println(allPatients.get(i).getGender());
	            if(allPatients.get(i).getGender().equalsIgnoreCase("M") || allPatients.get(i).getGender().equalsIgnoreCase("Male"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    male10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    male15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    male20To24++;
	                }
	            }
	            else if(allPatients.get(i).getGender().equalsIgnoreCase("F") || allPatients.get(i).getGender().equalsIgnoreCase("Female"))
	            {
	                if(allPatients.get(i).getAge() >=10 && allPatients.get(i).getAge() <=14)
	                {
	                    female10To14++;
	                }else if(allPatients.get(i).getAge() >=15 && allPatients.get(i).getAge() <=19)
	                {
	                    female15To19++;
	                }
	                else if(allPatients.get(i).getAge() >=20 && allPatients.get(i).getAge() <=24)
	                {
	                    female20To24++;
	                }
	            }
	        }


	        Map<String, String> dataMap = new HashMap<>();

	        dataMap.put("male10To14",  male10To14+"");
	        dataMap.put("male15To19",  male15To19+"");
	        dataMap.put("male20To24",  male20To24+"");
	        dataMap.put("female10To14",  female10To14+"");
	        dataMap.put("female15To19",  female15To19+"");
	        dataMap.put("female20To24",  female20To24+"");
	        //dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
	        return new JSONObject(dataMap).toString();

	}
}
