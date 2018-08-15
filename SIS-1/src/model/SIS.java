package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.sun.xml.internal.ws.util.Pool.Marshaller;

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
			return sd.retrieve(namePrefix, creds);
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, EnrollmentBean> retriveEnrollment(String namePrefix, String credit_taken) throws Exception {
		int creds=0;
		try{
			creds=Integer.parseInt(credit_taken);
			return ed.retrieve(namePrefix, creds);
		} catch(Exception e){
			System.out.println("you fucked up fam");
		}
		return null;
	}

	public void export(String namePrefix, String credit_taken, String filename) throws Exception{
		int credit=0;
		
		try {
			credit=Integer.parseInt(credit_taken);
			Map<String,StudentBean> sb=sd.retrieve(namePrefix, credit);
			ListWrapper lw=new ListWrapper(namePrefix, credit_taken, new ArrayList<StudentBean>(sb.values()));
			
			JAXBContext jc = JAXBContext.newInstance(lw.getClass());
			javax.xml.bind.Marshaller marshaller=jc.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			sw.write("\n");
			
			//Code for SIS.xsd
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//			Schema schema = sf.newSchema(new File("C:\\Users\\Saad\\Desktop\\EECS4413\\SIS-1\\WebContent\\export\\SIS.xsd"));
//			marshaller.setSchema(schema);
			marshaller.marshal(lw, new StreamResult(sw));
			
			System.out.println(sw.toString());
			FileWriter fw = new FileWriter(filename);
			fw.write(sw.toString());
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
}
