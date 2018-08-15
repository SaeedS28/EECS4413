package bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	private String namePrefix;
	private String credit_taken;
	private List<StudentBean> list;
	
	//Default ctor
	public ListWrapper() {
		this("","",new ArrayList<StudentBean>());
	}
	
	//overloaded ctor
	public ListWrapper(String namePrefix, String credit_taken, List<StudentBean> list) {
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}

	public ListWrapper(String credit_taken, ArrayList<StudentBean> arrayList) {
		this.namePrefix = "";
		this.credit_taken = credit_taken;
		this.list = arrayList;
	}

	@XmlAttribute
	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	@XmlAttribute(name="creditTaken")
	public String getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(String credit_taken) {
		this.credit_taken = credit_taken;
	}

	@XmlElement(name="studentList")
	public List<StudentBean> getList() {
		return list;
	}

	public void setList(List<StudentBean> list) {
		this.list = list;
	}

}
