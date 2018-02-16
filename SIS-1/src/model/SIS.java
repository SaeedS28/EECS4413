package model;

import java.util.Map;

import DAO.*;
import bean.*;

public class SIS {

	StudentDAO sd;
	EnrollmentDAO ed;

	public SIS() throws ClassNotFoundException {
		ed = new EnrollmentDAO();
		sd = new StudentDAO();
	}

	public Map<String, StudentBean> retriveStudent(String namePrefix, String credit_taken) throws Exception {
		return null;

	}

	public Map<String, EnrollmentBean> retriveEnrollment() throws Exception {
		return null;

	}

}
