
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openmrs.module.dataquality.api.dao.Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lordmaul
 */
public class ClinicalDaoHelper {
    public List<Map<String,String>> getActivePtsWithoutWithEducationalStatus(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
                List<Map<String, String>> allPatients = new ArrayList<>();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			StringBuilder queryString = new StringBuilder(
			        " select IFNULL(encounter.encounter_id, 0) AS encounter_id, dqr_meta.patient_id, person_name.given_name, person_name.family_name, patient_identifier.identifier FROM dqr_meta "
                                        + " LEFT JOIN encounter hivE ON hivE.patient_id=person.person_id AND hivE.form_id=23 "
                                        +"  JOIN person_name ON person_name.person_id=dqr_meta.patient_id "
			                + " JOIN patient_identifier ON patient_identifier.patient_id=patient.patient_id AND patient_identifier.preferred=1 "
                                        + " JOIN dqr_pharmacy ON dqr_pharmacy.patient_id=dqr_meta.patient_id "
			                + "	 WHERE art_start_date >=? AND  "
			                + "	 DATE_ADD(dqr_pharmacy.pickupdate,  INTERVAL (dqr_pharmacy.days_refill+28) DAY) >= ?  "
			                + "     AND dqr_pharmacy.pickupdate= ( "
			                + "		SELECT MAX(pickupdate) FROM dqr_pharmacy lastpickup "
			                + "        WHERE lastpickup.patient_id=dqr_pharmacy.patient_id "
			                + "	 HAVING MAX(pickupdate) <=? )   "
			                + " AND (dqr_meta.termination_status IS NULL OR dqr_meta.termination_status!=1066 )  ");
			
			queryString.append(" AND dqr_meta.education_status!='' ");
			
			int i = 1;
			stmt = con.prepareStatement(queryString.toString());
			
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
                            String patientId = dataObject[0].toString();
                            int encounterId = Integer.parseInt(rs.getInt[1].toString());
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
}
