package DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import bean.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {
	DataSource ds;
	Connection conn;

	public StudentDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		String query = String.format("select S.SID, S.GIVENNAME, S.SURNAME, S.CREDIT_TAKEN, S.CREDIT_GRADUATE, E.CREDIT_TAKING"
				+ " from STUDENTS as S, (select SID, SUM(CREDIT) as CREDIT_TAKING from ENROLLMENT group by SID) as E"
				+ " where S.SID = E.SID and S.SURNAME like '%%%s%%' and S.CREDIT_TAKEN >= %d", namePrefix, credit_taken);

		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con= this.ds.getConnection();
		PreparedStatement p= con.prepareStatement(query);
		ResultSet r= p.executeQuery();

		while(r.next()){
			String name= r.getString("GIVENNAME") + ", "+ r.getString("SURNAME");
			String sid = r.getString("SID");
			int creditTaken= r.getInt("CREDIT_TAKEN");
			int creditGraduate=r.getInt("CREDIT_GRADUATE");
			int creditTaking=r.getInt("CREDIT_TAKING");
					
			StudentBean sb = new StudentBean(sid, name, creditTaken, creditGraduate, creditTaking); 
			
			rv.put(sid, sb);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public Map<String, StudentBean> retrieveMin(int credit_taken) throws SQLException {
		String query = String.format("select S.SID, S.GIVENNAME, S.SURNAME, S.CREDIT_TAKEN, S.CREDIT_GRADUATE, E.CREDIT_TAKING"
				+ " from STUDENTS as S, (select SID, SUM(CREDIT) as CREDIT_TAKING from ENROLLMENT group by SID) as E"
				+ " where S.SID = E.SID and S.CREDIT_TAKEN < %d", credit_taken);

		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con= this.ds.getConnection();
		PreparedStatement p= con.prepareStatement(query);
		ResultSet r= p.executeQuery();

		while(r.next()){
			String name= r.getString("GIVENNAME") + ", "+ r.getString("SURNAME");
			String sid = r.getString("SID");
			int creditTaken= r.getInt("CREDIT_TAKEN");
			int creditGraduate=r.getInt("CREDIT_GRADUATE");
			int creditTaking=r.getInt("CREDIT_TAKING");
					
			StudentBean sb = new StudentBean(sid, name, creditTaken, creditGraduate, creditTaking); 
			
			rv.put(sid, sb);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
}

