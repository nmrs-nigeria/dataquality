/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dataquality.api.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.dataquality.Constants;

/**
 * @author lordmaul
 */
public class ClinicalDao {
	
	DbSessionFactory sessionFactory;
	
	public DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session getHibernateSession() {
		
		return sessionFactory.getHibernateSessionFactory().getCurrentSession();
	}
	
	public Integer getActivePatientsWithDocumentedEducationalStatus() {
		StringBuilder queryString = new StringBuilder(
		        "SELECT DISTINCT patient.patient_id, MAX(encounter.encounter_datetime) AS last_encounter  FROM patient ");
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString.append(" AND patient.patient_id  IN (SELECT person_id FROM obs "
		        + "JOIN encounter ON encounter.patient_id=obs.person_id AND encounter.form_id="
		        + Constants.HIV_ENROLLMENT_FORM
		        + " WHERE concept_id=1712 AND value_coded IS NOT NULL )  group by patient.patient_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		List<Object> data = query.list();
		//Object[] dataObject = (Object[]) data.get(0);
		
		//return Integer.parseInt(dataObject[0].toString());
		return data.size();
	}
	
	public List<Object> getActivePatientsWithoutDocumentedEducationalStatus() {
		StringBuilder queryString = new StringBuilder(
		        "SELECT person.person_id, IFNULL(hivE.encounter_id, 0) AS encounter_id,  patient_identifier.identifier, person_name.given_name, person_name.family_name, MAX(encounter.encounter_datetime) AS last_encounter  FROM person ");
		queryString
		        .append("  JOIN patient ON patient.patient_id=person.person_id "
		                + "JOIN patient_identifier ON patient_identifier.patient_id=person.person_id AND patient_identifier.preferred=1 ");
		queryString.append("  JOIN person_name ON person_name.person_id=person.person_id ");
		queryString.append("  LEFT JOIN encounter hivE ON hivE.patient_id=person.person_id AND hivE.form_id=?");
		
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString
		        .append(" AND (person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1712 AND value_coded IS NULL ) "
		                + " OR person.person_id NOT IN (SELECT person_id FROM obs WHERE concept_id=1712)  ) ");
		queryString.append(" GROUP BY person.person_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		System.out.println(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.HIV_ENROLLMENT_FORM);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		
		List<Object> data = query.list();
		return data;
	}
	
	public Integer getActivePatientsWithDocumentedMaritalStatus() {
		StringBuilder queryString = new StringBuilder(
		        "SELECT DISTINCT patient.patient_id, MAX(encounter.encounter_datetime) AS last_encounter FROM patient ");
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString
		        .append(" AND patient.patient_id  IN (SELECT person_id FROM obs "
		                + "JOIN encounter ON encounter.patient_id=obs.person_id AND encounter.form_id="
		                + Constants.HIV_ENROLLMENT_FORM
		                + " WHERE concept_id=1054 AND value_coded IS NOT NULL )   AND patient.voided=0 GROUP BY patient.patient_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		List<Object> data = query.list();
		//Object[] dataObject = (Object[]) data.get(0);
		
		//return Integer.parseInt(dataObject[0].toString());
		return data.size();
	}
	
	public List<Object> getActivePatientsWithoutDocumentedMaritalStatus() {
		
		StringBuilder queryString = new StringBuilder(
		        "SELECT person.person_id, IFNULL(hivE.encounter_id, 0) AS encounter_id,  patient_identifier.identifier, person_name.given_name, person_name.family_name, MAX(encounter.encounter_datetime) AS last_encounter  FROM person ");
		queryString
		        .append("  JOIN patient ON patient.patient_id=person.person_id "
		                + "JOIN patient_identifier ON patient_identifier.patient_id=person.person_id AND patient_identifier.preferred=1 ");
		queryString.append("  JOIN person_name ON person_name.person_id=person.person_id ");
		queryString.append("  LEFT JOIN encounter hivE ON hivE.patient_id=person.person_id AND hivE.form_id=?");
		
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString
		        .append(" AND (person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1054 AND value_coded IS NULL ) "
		                + " OR person.person_id NOT IN (SELECT person_id FROM obs WHERE concept_id=1054)  ) ");
		queryString.append(" GROUP BY person.person_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.HIV_ENROLLMENT_FORM);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		List<Object> data = query.list();
		return data;
	}
	
	public Integer getActivePatientsWithDocumentedOccupationalStatus() {
		
		StringBuilder queryString = new StringBuilder(
		        "SELECT DISTINCT patient.patient_id, MAX(encounter.encounter_datetime) AS last_encounter FROM patient ");
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString
		        .append(" AND patient.patient_id  IN (SELECT person_id FROM obs WHERE concept_id=1542 AND value_coded IS NOT NULL ) AND patient.voided=0 GROUP BY patient.patient_id ");
		
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		List<Object> data = query.list();
		//Object[] dataObject = (Object[]) data.get(0);
		
		//return Integer.parseInt(dataObject[0].toString());
		return data.size();
	}
	
	public List<Object> getActivePatientsWithoutDocumentedOccupationalStatus() {
		
		StringBuilder queryString = new StringBuilder(
		        "SELECT person.person_id, IFNULL(hivE.encounter_id, 0) AS encounter_id,  patient_identifier.identifier, person_name.given_name, person_name.family_name, MAX(encounter.encounter_datetime) AS last_encounter  FROM person ");
		queryString
		        .append("  JOIN patient ON patient.patient_id=person.person_id "
		                + "JOIN patient_identifier ON patient_identifier.patient_id=person.person_id AND patient_identifier.preferred=1 ");
		queryString.append("  JOIN person_name ON person_name.person_id=person.person_id ");
		queryString.append("  LEFT JOIN encounter hivE ON hivE.patient_id=person.person_id AND hivE.form_id=?");
		
		queryString
		        .append(" JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=?  "
		                + " AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.patient_id=encounter.patient_id AND e.form_id=?) "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL");
		queryString
		        .append(" AND (person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1542 AND value_coded IS NULL ) "
		                + " OR person.person_id NOT IN (SELECT person_id FROM obs WHERE concept_id=1542)  ) ");
		queryString.append(" GROUP BY person.person_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		DateTime now = new DateTime(new Date());
		//now = now.minusDays(28);
		int i = 0;
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		query.setInteger(i++, Constants.HIV_ENROLLMENT_FORM);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.PHARMACY_FORM_ID);
		query.setInteger(i++, Constants.ARV_GROUPING_CONCEPT);
		query.setInteger(i++, Constants.ARV_REGIMEN_DURATION);
		query.setString(i++, nowString);
		List<Object> data = query.list();
		return data;
	}
	
	public Integer getPatientsWithDocumentedDobCount(String startDate, String endDate) {
		StringBuilder queryString = new StringBuilder("SELECT DISTINCT patient.patient_id FROM patient  ");
		queryString.append(" JOIN person ON person.person_id=patient.patient_id ");
		queryString
		        .append("where patient.patient_id  IN (SELECT   patient_id FROM patient_program WHERE program_id=1 AND date_enrolled BETWEEN ? AND ?  ) AND patient.voided=0 ");
		queryString.append(" AND person.birthdate IS NOT NULL GROUP BY patient.patient_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		int i = 0;
		//query.setInteger(i++, Constants.ART_START_DATE_CONCEPT);
		query.setString(i++, startDate);
		query.setString(i++, endDate);
		//Object data = query.uniqueResult();
		
		//return Integer.parseInt(data.toString());
		List<Object> data = query.list();
		
		//return Integer.parseInt(data.toString());
		return data.size();
	}
	
	public List<Object> getPatientsWithDocumentedDob(String startDate, String endDate) {
		StringBuilder queryString = new StringBuilder(
		        "SELECT person.person_id,  patient_identifier.identifier, person_name.given_name, person_name.family_name FROM person  ");
		queryString
		        .append("  JOIN patient_identifier ON patient_identifier.patient_id=person.person_id AND patient_identifier.preferred=1 ");
		queryString.append("  JOIN person_name ON person_name.person_id=person.person_id ");
		queryString
		        .append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		queryString.append(" AND person.birthdate IS NULL ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		int i = 0;
		query.setInteger(i++, Constants.ART_START_DATE_CONCEPT);
		query.setString(i++, startDate);
		query.setString(i++, endDate);
		List<Object> data = query.list();
		return data;
	}
	
	public Integer getPatientsWithDocumentedGenderCount(String startDate, String endDate) {
		StringBuilder queryString = new StringBuilder("SELECT  DISTINCT person.person_id FROM person  ");
		queryString.append("JOIN patient ON patient.patient_id=person.person_id ");
		queryString
		        .append("where person.person_id  IN (SELECT patient_id FROM patient_program WHERE program_id=1 AND date_enrolled BETWEEN ? AND ?  ) AND person.voided=0 ");
		queryString.append(" AND (person.gender='M' OR person.gender='F') GROUP BY patient.patient_id  ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		int i = 0;
		//query.setInteger(i++, Constants.ART_START_DATE_CONCEPT);
		query.setString(i++, startDate);
		query.setString(i++, endDate);
		List<Object> data = query.list();
		
		//return Integer.parseInt(data.toString());
		return data.size();
	}
	
	public List<Object> getPatientsWithoutDocumentedGender(String startDate, String endDate) {
		StringBuilder queryString = new StringBuilder(
		        "SELECT person.person_id,  patient_identifier.identifier, person_name.given_name, person_name.family_name FROM person  ");
		queryString
		        .append("  JOIN patient_identifier ON patient_identifier.patient_id=person.person_id AND patient_identifier.preferred=1 ");
		queryString.append("  JOIN person_name ON person_name.person_id=person.person_id ");
		queryString
		        .append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		queryString
		        .append(" AND (person.gender !='M' AND person.gender !='F' AND person.gender !='Male' AND person.gender !='Female')  ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString());
		int i = 0;
		query.setInteger(i++, Constants.ART_START_DATE_CONCEPT);
		query.setString(i++, startDate);
		query.setString(i++, endDate);
		List<Object> data = query.list();
		return data;
	}
	
}
