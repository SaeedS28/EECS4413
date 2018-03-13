package model;

import java.util.Map;

import DAO.*;
import bean.*;

public class SIS {

	private StudentDAO sd;
	private EnrollmentDAO ed;

	public SIS() throws ClassNotFoundException {
		ed = new EnrollmentDAO();
		sd = new StudentDAO();
	}

	public Map<String, StudentBean> retriveStudent(String namePrefix, String credit_taken) throws Exception {
		int creds=0;
		try{
			creds=Integer.parseInt(credit_taken);
		} catch(Exception e){
			System.out.println("you fucked up fam");
		}
		return sd.retrieve(namePrefix, creds);
	}

	public Map<String, EnrollmentBean> retriveEnrollment(String namePrefix, String credit_taken) throws Exception {
		int creds=0;
		try{
			creds=Integer.parseInt(credit_taken);
		} catch(Exception e){
			System.out.println("you fucked up fam");
		}
		return ed.retrieve(namePrefix, creds);

	}

}
