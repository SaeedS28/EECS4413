package bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	@XmlAttribute(name="namePrefix")
	private String namePrefix;
	@XmlAttribute(name = "creditTaken")
	private int credit_taken;
	@XmlElement(name="studentList")
	private List<StudentBean> list;
	
	//Default ctor
	public ListWrapper() {
		
	}
	
	//overloaded ctor
	public ListWrapper(String namePrefix, int credit_taken, List<StudentBean> list) {
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}
	
	public String getNamePrefix() {
		return namePrefix;
	}
	
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	
	public int getCredit_taken() {
		return credit_taken;
	}
	
	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}
	
	public List<StudentBean> getList() {
		return list;
	}
	
	public void setList(List<StudentBean> list) {
		this.list = list;
	}

}
