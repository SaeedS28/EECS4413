package bean;

public class EnrollmentBean {
	private int cid;
	private String students;
	private int credit;
	public EnrollmentBean(int cid, String students, int credit)	{
		this.cid = cid;
		this.students = students;
		this.credit = credit;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}
