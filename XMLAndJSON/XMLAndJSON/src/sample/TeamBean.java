package sample;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamBean {
	//@XmlElement
   	private List <EmployeeBean> member;
   	public TeamBean(ArrayList<EmployeeBean> members) {
		super();
		this.member = members;
	}

	public TeamBean() {
		// TODO Auto-generated constructor stub
		this.member = new ArrayList<EmployeeBean>();
	}
	public List<EmployeeBean> getMembers() {
		return member;
	}
	public void setMembers(ArrayList<EmployeeBean> members) {
		this.member = members;
	}

}
