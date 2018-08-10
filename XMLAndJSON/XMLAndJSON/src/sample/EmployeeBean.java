package sample;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "member")
@XmlAccessorType(XmlAccessType.FIELD)

public class EmployeeBean extends PersonBean {

	@XmlElement
	int salary;
	@XmlAttribute
	String emplType = "regular";

	public EmployeeBean() {
	}

	public EmployeeBean(String sin, String name, int age, int salary, String emplType) {
		super(sin, name, age);
		this.salary = salary;
		this.emplType = emplType;
	}

	public String getEmplType() {
		return emplType;
	}

	public void setEmplType(String emplType) {
		this.emplType = emplType;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
