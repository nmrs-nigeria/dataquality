/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dataquality.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.openmrs.module.dataquality.Constants;
import org.openmrs.module.dataquality.Misc;

/**
 * @author lordmaul
 */
public class PatientDao {
	
	public int getTotalPatients() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT COUNT(patient.patient_id) AS count  FROM patient WHERE voided=0 ";
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("count");
			return totalCount;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return 0;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Map<String, String>> getAllPatients(int limit, int offset, String lastUpdateDate) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Map<String, String>> allPatients = new ArrayList<>();
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = " SELECT patient_id FROM patient  WHERE patient.voided=0 AND (patient.date_created >=? OR patient.date_changed >=?)";
                        query += " AND " +
                                "( " +
                                "   patient_id  in (SELECT person_id FROM obs WHERE date_created >= ? ) OR " +
                                "   patient_id IN (SELECT patient_id FROM encounter WHERE date_created >=? OR date_changed >=?) " +
                                ")";
                        query += " ORDER BY patient_id LIMIT " +offset + ", " + limit;
			        
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmt.setFetchSize(200);
                        stmt.setString(i++, lastUpdateDate);
                        stmt.setString(i++, lastUpdateDate);
                        stmt.setString(i++, lastUpdateDate);
                        stmt.setString(i++, lastUpdateDate);
                        stmt.setString(i++, lastUpdateDate);
                        
			rs = stmt.executeQuery();
			rs.setFetchSize(limit);
			while (rs.next()) {
                            Map<String,String> tempMap = new HashMap<>();
                            tempMap.put("patient_id", rs.getString("patient_id"));
                            allPatients.add(tempMap);
			}
			return allPatients;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return null;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Map<String, String>> getPatientMeta(int patientId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Map<String, String>> allPatients = new ArrayList<>();
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT patient.patient_id, artobs.value_datetime AS artstartdate, person.birthdate, eduobs.value_coded AS educational_staus,\n"
			        + "marobs.value_coded AS marital_status, occuobs.value_coded AS occupation_status, person.gender, encounter.encounter_datetime AS hivenrollment_date, "
			        + " hivdiagnosis.obs_datetime AS hivdiagnosis_date, terminationobs.value_coded AS termination_status, "
                                + " CONCAT(IFNULL(person_address.address1, ''), ' ', IFNULL(person_address.address2, ''), ' ', IFNULL(person_address.city_village, ''), ' ',  IFNULL(person_address.state_province, '') ) AS address "
			        + " FROM person "
			        + " JOIN patient ON patient.patient_id=person.person_id "
			        + " LEFT JOIN encounter ON encounter.patient_id=person.person_id AND encounter.encounter_type=14 "
			        + " LEFT JOIN obs artobs ON artobs.person_id=patient.patient_id AND artobs.concept_id=159599 AND artobs.voided=0 "
			        + " LEFT JOIN obs eduobs ON eduobs.person_id=patient.patient_id AND eduobs.concept_id=1712 AND eduobs.voided=0 "
			        + " LEFT JOIN obs marobs ON marobs.person_id=patient.patient_id AND marobs.concept_id=1054 AND marobs.voided=0 "
			        + " LEFT JOIN obs occuobs ON occuobs.person_id=patient.patient_id AND occuobs.concept_id=1542 AND occuobs.voided=0  "
			        + " LEFT JOIN obs hivdiagnosis ON hivdiagnosis.person_id=patient.patient_id AND hivdiagnosis.concept_id=160554 AND hivdiagnosis.voided=0  "
                                + " LEFT JOIN obs terminationobs ON terminationobs.person_id=patient.patient_id AND terminationobs.concept_id=165586 AND terminationobs.voided=0 "
                                + " LEFT JOIN person_address ON person_address.person_id=person.person_id ";
                        
                        query += " WHERE person.person_id=? AND person.voided=0 GROUP BY person.person_id ";
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        stmt.setInt(i++, patientId);
			//stmt.setFetchSize(200);
			rs = stmt.executeQuery();
			while (rs.next()) {
                            Map<String,String> tempMap = new HashMap<>();
                            tempMap.put("patient_id", rs.getString("patient_id"));
                            tempMap.put("art_start_date", rs.getString("artstartdate"));
                            tempMap.put("birthdate", rs.getString("birthdate"));
                            tempMap.put("educational_staus", Misc.getEducationalStatus(rs.getInt("educational_staus")));
                            tempMap.put("occupation_status", Misc.getOccupationalStatus(rs.getInt("occupation_status")));
                            tempMap.put("marital_status", Misc.getCivilStatus(rs.getInt("marital_status")));
                            tempMap.put("gender", rs.getString("gender"));
                            tempMap.put("hiv_diagnosis_date", rs.getString("hivdiagnosis_date"));
                            tempMap.put("hiv_enrollment_date", rs.getString("hivenrollment_date"));
                            tempMap.put("address", rs.getString("address"));
                            tempMap.put("termination_status", rs.getString("termination_status"));
                            
                            allPatients.add(tempMap);
			}
			return allPatients;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return null;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
    }
	
	public int savePatientMeta(List<Map<String, String>> allPatientMetas) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "INSERT INTO dqr_meta (patient_id, art_start_date, dob, education_status, marital_status, occupation, gender, address, hiv_diagnosis_date, hiv_enrollment_date, termination_status)VALUES";
			for (int i = 0; i < allPatientMetas.size(); i++) {
				query += "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),";
			}
			
			query = query.substring(0, query.length() - 1);
			query += " ON DUPLICATE KEY UPDATE art_start_date=VALUES(art_start_date), dob=VALUES(dob), education_status=VALUES(education_status), ";
			query += " marital_status=VALUES(marital_status), occupation=VALUES(occupation), address=VALUES(occupation), hiv_diagnosis_date=VALUES(hiv_diagnosis_date), hiv_enrollment_date=VALUES(hiv_enrollment_date),  ";
			query += " termination_status=VALUES(termination_status), address=VALUES(address) ";
			
			int i = 1;
			stmt = con.prepareStatement(query);
			for (int j = 0; j < allPatientMetas.size(); j++) {
				stmt.setInt(i++, Integer.parseInt(allPatientMetas.get(j).get("patient_id")));
				stmt.setString(i++, allPatientMetas.get(j).get("art_start_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("birthdate"));
				stmt.setString(i++, allPatientMetas.get(j).get("educational_staus"));
				stmt.setString(i++, allPatientMetas.get(j).get("marital_status"));
				stmt.setString(i++, allPatientMetas.get(j).get("occupation_status"));
				stmt.setString(i++, allPatientMetas.get(j).get("gender"));
				stmt.setString(i++, allPatientMetas.get(j).get("address"));
				stmt.setString(i++, allPatientMetas.get(j).get("hiv_diagnosis_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("hiv_enrollment_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("termination_status"));
			}
			//stmt.setFetchSize(200);
			stmt.executeUpdate();
			
			return 1;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return 0;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
}
