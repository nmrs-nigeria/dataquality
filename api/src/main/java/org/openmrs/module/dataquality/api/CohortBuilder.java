package org.openmrs.module.dataquality.api;

import org.openmrs.module.dataquality.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.joda.time.DateTime;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.dataquality.api.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author lordmaul
 */
public class CohortBuilder {
	
	//@Autowired
	DbSessionFactory sessionFactory;
	
	///private static final String activeQueryJoinString = " JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id=encounter.encounter_id ) ";
	private static final String activeQueryJoinString = " JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? "
	        + " JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
	        + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
	        + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL durationobs.value_numeric DAY) > ? AND person.voided=0 AND durationobs.value_numeric IS NOT NULL "
	        + "  ";
	
	//where the duration for the last arv refill + 28 > now
	
	public static int getActivePatientDocumentedEducationStatusCount() {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		StringBuilder query = new StringBuilder(
		        "SELECT COUNT(person.person_id) AS count, MAX(encounter.encounter_datetime) AS last_encounter FROM person ");
		query.append(activeQueryJoinString);
		//System.out.println(query.toString());
		//query.append(" changed_by, date_changed, voided_by, voided, date_voided,  voided_reason, deathdate_estimated, datim_id, message_uuid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?)");
		try {
			con = Database.connectionPool.getConnection();
			
			stmt = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			DateTime now = new DateTime(new Date());
			int i = 1;
			// stmt.setString(i++, patientDemo.getPatientUuid());
			now = now.minusDays(28);
			String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			stmt.setString(i++, nowString);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			//screen.updateStatus(ex.getMessage());
			ex.printStackTrace();
			return 0;
		}
		finally {
			try {
				if (rs != null && stmt != null) {
					//rs.close();
					stmt.close();
					Database.connectionPool.free(con);
					// con.close();
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				//Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public int getActivePatientCount() {
		
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		StringBuilder queryString = new StringBuilder(
		        " SELECT DISTINCT patient.patient_id, MAX(encounter.encounter_datetime) AS last_encounter FROM patient ");
		queryString
		        .append("JOIN encounter ON encounter.patient_id=patient.patient_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter e WHERE e.form_id="
		                + Constants.PHARMACY_FORM_ID
		                + " AND e.patient_id=encounter.patient_id)  "
		                + " JOIN obs obsgroup ON obsgroup.person_id=patient.patient_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		                + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		                + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL (durationobs.value_numeric+28) DAY) > ? AND patient.voided=0 "
		                + " AND durationobs.value_numeric IS NOT NULL group by patient.patient_id");
		
		//query.append(" changed_by, date_changed, voided_by, voided, date_voided,  voided_reason, deathdate_estimated, datim_id, message_uuid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?)");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(queryString.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			DateTime now = new DateTime(new Date());
			//now = now.minusDays(28);
			//now = now.minusDays(28);
			String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			stmt.setString(i++, nowString);
			rs = stmt.executeQuery();
			rs.last();
			int totalRows = rs.getRow();
			System.out.println(" Total active: " + totalRows);
			
			return totalRows;
			//rs.next();
			
			//return rs.getInt("count");
		}
		catch (SQLException ex) {
			//screen.updateStatus(ex.getMessage());
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getActivePatientsWithDocumentedEducationalStatus() {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String activeQueryJoinString = " JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=?  "
		        + " JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id "
		        + " JOIN obs durationobs ON durationobs.encounter_id=encounter.encounter_id AND durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=?  "
		        + " WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL durationobs.value_numeric DAY) > ? AND person.voided=0 "
		        + " AND durationobs.value_numeric IS NOT NULL";
		
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person ");
		query.append(activeQueryJoinString);
		query.append(" AND person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1542 AND value_coded IS NOT NULL ) AND person.voided=0 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			DateTime now = new DateTime(new Date());
			now = now.minusDays(28);
			String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			stmt.setString(i++, nowString);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getActivePatientsWithDocumentedMaritalStatus() {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1054 AND value_coded IS NOT NULL ) AND person.voided=0 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getActivePatientsWithDocumentedOccupationalStatus() {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=1542 AND value_coded IS NOT NULL ) AND person.voided=0 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsOnARTCount(String startDate, String endDate) {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsOnArtWithInitialRegimen(String startDate, String endDate) {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs  ON obs.person_id=person.person_id AND obs.concept_id IN (164506, 164513, 165702, 164507, 164514, 165703) AND obs.encounter_id=encounter.encounter_id ");
		
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		//query.append(" JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWithDocumentedDobCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		query.append(" AND person.birthdate IS NOT NULL ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWithDocumentedGenderCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		query.append(" AND (person.gender='M' OR person.gender='F')  ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWithDocumentedPostiveDateCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		query.append(" AND person.person_id IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?)");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.DATE_CONFIRMED_POSITIVE);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWithDocumentedHIVEnrollmentCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append(" JOIN  patient_program ON patient_program.patient_id=person.person_id AND patient_program.program_id=? ");
		query.append(" where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		//query.append(" AND person.person_id IN (SELECT person_id FROM patient_program WHERE program_id=? )");
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.HIV_PROGRAM_ID);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWhoPickARVCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		query.append(" AND person.person_id IN (SELECT person_id FROM obs WHERE concept_id=? AND value_coded IS NOT NULL)");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.REGIMEN_LINE_CONCEPT);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPatientsWithDocCd4CntCount(String startDate, String endDate)//where art start date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		query.append(" AND person.person_id IN (SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL)");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.CD4_COUNT_CONCEPT);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("  WHERE  person.voided=0   ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			//stmt.setInt(i++, Constants.CD4_COUNT_CONCEPT);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitDocWeightCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.encounter_id=encounter.encounter_id AND wobs.concept_id=? AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_numeric IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.WEIGHT_CONCEPT);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitFunctionalStatusCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.encounter_id=encounter.encounter_id AND wobs.concept_id=?  AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_coded IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.FUNCTIONAL_STATUS_CONCEPT);
			//stmt.setString(i++, startDate);
			//stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitNextAppDateCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.encounter_id=encounter.encounter_id AND wobs.concept_id=?  AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_datetime IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.FUNCTIONAL_STATUS_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getInactivePtsCount()//as at now
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		DateTime now = new DateTime(new Date());
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id=encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		query.append(" JOIN obs durationobs ON durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=? AND durationobs.value_numeric IS NOT NULL ");
		query.append(" WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL durationobs.value_numeric DAY) < ? ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			stmt.setString(i++, nowString);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getInactivePtsWithReasonCount()//as at now
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		DateTime now = new DateTime(new Date());
		String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id=encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		query.append(" JOIN obs durationobs ON durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=? AND durationobs.value_numeric IS NOT NULL ");
		query.append(" JOIN obs inactiveobs ON inactiveobs.person_id=person.person_id AND inactiveobs.concept_id=?");
		query.append(" WHERE DATE_ADD(encounter.encounter_datetime,  INTERVAL durationobs.value_numeric DAY) < ? ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			stmt.setInt(i++, Constants.EXIT_REASON_CONCEPT);
			stmt.setString(i++, nowString);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitDocMUACCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ? AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_numeric IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.MUAC_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithClinicalVisitDocWHOCount(String startDate, String endDate)//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ? AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_coded IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.WHO_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	//get all pts with a documented TB status within the supplied dates
	public static int getPtsWithClinicalVisitDocTBStatusCount(String startDate, String endDate) {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append("JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id IN (22, 14, 20) AND encounter_datetime BETWEEN ? AND ? ");
		query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ? AND wobs.obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.obs_id =wobs.obs_id )  AND wobs.value_coded IS NOT NULL ");
		//query.append("JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? AND wobs.obs_datetime BETWEEN ? AND ?  ");
		//query.append(" JOIN (SELECT MAX(obs_datetime) AS maxdatetime, person_id FROM obs ) maxObs ON maxObs.maxdatetime=wobs.obs_datetime AND maxObs.person_id=wobs.person_id ");
		//query.append(" AND person.person_id IN ");
		//query.append("(SELECT person_id FROM obs WHERE concept_id=? AND value_numeric IS NOT NULL AND obs_datetime BETWEEN ? AND ? AND obs_datetime=(SELECT MAX(obs_datetime) FROM obs c WHERE c.person_id=obs.person_id AND c.concept_id=obs.concept_id) )");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			stmt.setInt(i++, Constants.TB_STATUS_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithDocLastARVPickupCount()//get all pts with at least one pickup
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		//query.append(" JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithDocLastARVPickupWithRegiminCount()//get all pts with a last drug pickup has a regimen
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs  ON obs.person_id=person.person_id AND obs.concept_id IN (164506, 164513, 165702, 164507, 164514, 165703) AND obs.encounter_id=encounter.encounter_id ");
		//query.append(" JOIN obs wobs ON wobs.person_id=person.person_id AND wobs.concept_id=? ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithDocLastARVPickupWithDurationCount()//get all pts whose last arv pick has a duration
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		query.append(" JOIN obs durationobs ON durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=? AND durationobs.value_numeric IS NOT NULL ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithDocLastARVPickupWithDurationMoreThan180Count()//get all pts whose days of arv refill is more than 180 days
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		query.append(" JOIN obs durationobs ON durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=? AND durationobs.value_numeric > 180 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_DURATION);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsEligibleForVLCount() {
		return getPtsEligibleForVLWithResultCount() + getPtsEligibleForVLWithoutResultCount();
	}
	
	public static int getPtsEligibleForVLWithoutResultCount()//get a count of pts eligible for vl who haven't done vl
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN obs artobs ON artobs.person_id=person.person_id AND artobs.concept_id=? AND artobs.value_datetime <=? ");//less than or eq to 6 months ago
		query.append(" WHERE person.person_id NOT IN ");
		query.append(" ( SELECT obs.person_id FROM obs JOIN obs resultdateobs ON resultdateobs.encounter_id=obs.encounter_id WHERE obs.concept_id=? AND resultdateobs.concept_id=? AND resultdateobs.value_datetime>=? )");
		//query.append(" LEFT JOIN obs vlresultobs");
		
		DateTime now = new DateTime(new Date());
		DateTime sixMonthsAgo = now.minusMonths(6);
		DateTime aYearAgo = now.minusMonths(12);
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, sixMonthsAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			stmt.setInt(i++, Constants.VIRAL_LOAD_CONCEPT);
			stmt.setInt(i++, Constants.VIRAL_LOAD_RESULT_DATE_CONCEPT);
			stmt.setString(i++, aYearAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsEligibleForVLWithResultCount()//get a count of pts eligible for vl who have actually done and have result
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN obs artobs ON artobs.person_id=person.person_id AND artobs.concept_id=? AND artobs.value_datetime <=? ");//less than or eq to 6 months ago
		query.append(" WHERE person.person_id IN ");
		query.append(" ( SELECT obs.person_id FROM obs JOIN obs resultdateobs ON resultdateobs.encounter_id=obs.encounter_id WHERE obs.concept_id=? AND resultdateobs.concept_id=? AND resultdateobs.value_datetime>=? )");
		//query.append(" LEFT JOIN obs vlresultobs");
		
		DateTime now = new DateTime(new Date());
		DateTime sixMonthsAgo = now.minusMonths(6);
		DateTime aYearAgo = now.minusMonths(12);
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, sixMonthsAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			stmt.setInt(i++, Constants.VIRAL_LOAD_CONCEPT);
			stmt.setInt(i++, Constants.VIRAL_LOAD_RESULT_DATE_CONCEPT);
			stmt.setString(i++, aYearAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsEligibleForVLWithSampleCollectionCount()//get a count of pts eligible for vl who have actually done and have result
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN obs artobs ON artobs.person_id=person.person_id AND artobs.concept_id=? AND artobs.value_datetime <=? ");//less than or eq to 6 months ago
		query.append(" WHERE person.person_id IN ");
		query.append(" ( SELECT obs.person_id FROM obs JOIN obs samplecollectionobs ON samplecollectionobs.encounter_id=obs.encounter_id WHERE obs.concept_id=? AND samplecollectionobs.value_datetime>=? )");
		//query.append(" LEFT JOIN obs vlresultobs");
		
		DateTime now = new DateTime(new Date());
		DateTime sixMonthsAgo = now.minusMonths(6);
		DateTime aYearAgo = now.minusMonths(12);
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, sixMonthsAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			stmt.setInt(i++, Constants.VIRAL_LOAD_CONCEPT);
			stmt.setInt(i++, Constants.DATE_SAMPLE_COLLECTED_CONCEPT);
			stmt.setString(i++, aYearAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsEligibleForVLWithSampleSentCount()//get a count of pts eligible for vl who have actually done and have result
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN obs artobs ON artobs.person_id=person.person_id AND artobs.concept_id=? AND artobs.value_datetime <=? ");//less than or eq to 6 months ago
		query.append(" WHERE person.person_id IN ");
		query.append(" ( SELECT obs.person_id FROM obs JOIN obs samplesentobs ON samplesentobs.encounter_id=obs.encounter_id WHERE obs.concept_id=? AND samplesentobs.value_datetime>=? )");
		//query.append(" LEFT JOIN obs vlresultobs");
		
		DateTime now = new DateTime(new Date());
		DateTime sixMonthsAgo = now.minusMonths(6);
		DateTime aYearAgo = now.minusMonths(12);
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, sixMonthsAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			stmt.setInt(i++, Constants.VIRAL_LOAD_CONCEPT);
			stmt.setInt(i++, Constants.DATE_SAMPLE_SENT_CONCEPT);
			stmt.setString(i++, aYearAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsEligibleForVLWithSampleReceivedCount()//
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN obs artobs ON artobs.person_id=person.person_id AND artobs.concept_id=? AND artobs.value_datetime <=? ");//less than or eq to 6 months ago
		query.append(" WHERE person.person_id IN ");
		query.append(" ( SELECT obs.person_id FROM obs JOIN obs samplereceivedobs ON samplereceivedobs.encounter_id=obs.encounter_id WHERE obs.concept_id=? AND samplereceivedobs.value_datetime>=? )");
		//query.append(" LEFT JOIN obs vlresultobs");
		
		DateTime now = new DateTime(new Date());
		DateTime sixMonthsAgo = now.minusMonths(6);
		DateTime aYearAgo = now.minusMonths(12);
		
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, sixMonthsAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			stmt.setInt(i++, Constants.VIRAL_LOAD_CONCEPT);
			stmt.setInt(i++, Constants.DATE_RECEIVED_AT_PCR_LAB);
			stmt.setString(i++, aYearAgo.toString("yyyy'-'MM'-'dd' 'HH':'mm"));
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getPtsWithDocLastARVPickupWithQtyCount()//where the encounter date is between the date range supplied
	{
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT person.person_id) as count FROM person  ");
		query.append(" JOIN encounter ON encounter.patient_id=person.person_id AND encounter.form_id=? AND encounter.encounter_datetime=(SELECT MAX(encounter_datetime) FROM encounter enc WHERE enc.encounter_id =encounter.encounter_id ) ");
		query.append(" JOIN obs obsgroup ON obsgroup.person_id=person.person_id AND obsgroup.concept_id=? AND obsgroup.encounter_id=encounter.encounter_id ");
		query.append(" JOIN obs durationobs ON durationobs.obs_group_id=obsgroup.obs_id AND durationobs.concept_id=? AND durationobs.value_numeric IS NOT NULL ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			//stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setInt(i++, Constants.PHARMACY_FORM_ID);
			stmt.setInt(i++, Constants.ARV_GROUPING_CONCEPT);
			stmt.setInt(i++, Constants.ARV_REGIMEN_QUANTITY);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
	public static int getCohortCount(int conceptId, String startDate, String endDate) {
		Database.initConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(person.person_id) as count FROM person  ");
		query.append("where person.person_id  IN (SELECT person_id FROM obs WHERE concept_id=? AND value_datetime BETWEEN ? AND ?  ) AND person.voided=0 ");
		try {
			con = Database.connectionPool.getConnection();
			stmt = con.prepareStatement(query.toString());
			
			int i = 1;
			stmt.setInt(i++, Constants.ART_START_DATE_CONCEPT);
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt("count");
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			Database.finallyBlock(rs, stmt, con);
		}
	}
	
}
