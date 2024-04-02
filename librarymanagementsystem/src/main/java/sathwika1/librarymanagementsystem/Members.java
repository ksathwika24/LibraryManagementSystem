package sathwika1.librarymanagementsystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="memeber_id",length=10)
	private int id;
	
	@Column
	private String name;
	public Members(int id, String name, String emailid, String address, int mobileno) {
		super();
		this.id = id;
		this.name = name;
		this.emailid = emailid;
		this.address = address;
		this.mobileno = mobileno;
	}
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "memeber [id=" + id + ", name=" + name + ", emailid=" + emailid + ", address=" + address + ", mobileno="
				+ mobileno + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMobileno() {
		return mobileno;
	}
	public void setMobileno(int mobileno) {
		this.mobileno = mobileno;
	}
	private String emailid;
	private String address;
	private int mobileno;
}
