package model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Payment {
	private int id;
	private String Email;
	private String Uname;
	private double PayAmount;
	private String TypeOfPayment;
	
	public Payment() {
		super();
	}

	public Payment(String Email,String Uname, double payAmount, String TypeOfPayment) {
		super();
		this.Email = Email;
		this.Uname = Uname;
		this.PayAmount = payAmount;
		this.TypeOfPayment = TypeOfPayment;
	}

	public Payment(int id,String Email, String Uname, double payAmount, String TypeOfPayment) {
		super();
		this.id = id;
		this.Email = Email;
		this.Uname = Uname;
		this.PayAmount = payAmount;
		this.TypeOfPayment = TypeOfPayment;
	}
	
	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getUname() {
	    return Uname;
	}

	public void setUname(String Uname) {
	    this.Uname = Uname;
	}


	public double getPayAmount() {
		return PayAmount;
	}

	public void setPayAmount(double PayAmount) {
		this.PayAmount = PayAmount;
	}

	public String getTypeOfPayment() {
		return TypeOfPayment;
	}

	public void setTypeOfPayment(String TypeOfPayment) {
		this.TypeOfPayment = TypeOfPayment;
	}

		
}
