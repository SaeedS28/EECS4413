package sample;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonBean {
	@XmlElement
	private String sin;
	@XmlElement
	private String name;
	@XmlElement
	private int age;

	public PersonBean(String sin, String name, int age) {
		super();
		this.sin = sin;
		this.name = name;
		this.age = age;
	}

	public PersonBean() {

	}

	public String getSin() {
		return sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
