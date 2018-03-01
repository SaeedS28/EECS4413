package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;
import bean.StudentBean;

public class EnrollmentDAO {
	DataSource ds;
	
	public EnrollmentDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieve(String namePrefix, int credits) throws SQLException {
		String query= "select * from enrollment where sid in (select sid from students where surname like '%"+ namePrefix + "%' and credit_taken <= " + credits+")"; 
				//+ "and credit_taken >="+ credit_taken;
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con= this.ds.getConnection();
		PreparedStatement p= con.prepareStatement(query);
		ResultSet r= p.executeQuery();

		while(r.next()){
			if (rv.get(r.getString("CID")) != null){
				String sID= r.getString("SID");
				rv.get(r.getString("CID")).getStudents().add(sID);
			} else {
				String cID= r.getString("CID");
				String sID= r.getString("SID");
				int creds = r.getInt("CREDIT");			
				ArrayList <String> sIds = new ArrayList<String>();
				sIds.add(sID);
				EnrollmentBean sb = new EnrollmentBean(cID,sIds,creds); 
				
				rv.put(cID, sb);
			}		
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
}
