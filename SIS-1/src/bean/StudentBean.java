package bean;

public class StudentBean {
	private int sid;
	private String name;
	private int credit_taken;
	private int credit_graduate;

	public StudentBean(int sid, String name, int credits_taken, int credit_graduate){
		this.sid=sid;
		this.name=name;
		this.credit_taken=credits_taken;
		this.credit_graduate=credit_graduate;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}

	public int getCredit_graduate() {
		return credit_graduate;
	}

	public void setCredit_graduate(int credit_graduate) {
		this.credit_graduate = credit_graduate;
	}

}
