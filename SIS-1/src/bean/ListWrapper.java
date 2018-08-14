package bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	@XmlAttribute
	private String namePrefix;
	@XmlAttribute(name = "creditTaken")
	private String credit_taken;
	@XmlElement(name="studentList")
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

}
