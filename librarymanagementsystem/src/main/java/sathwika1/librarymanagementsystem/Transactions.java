package sathwika1.librarymanagementsystem;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int transaction_id ;
	
	private int member_id;
	private long checkout_date;
	private long return_date ;
@ManyToOne
@JoinColumn(name="Members")
private Members Members;

public Members getMembers() {
	return Members;
}
public void setMembers(Members members) {
	Members = members;
}
@ManyToOne
@JoinColumn(name="book_id")
private books book;
	public books getBook() {
	return book;
}
public void setBook(books book) {
	this.book = book;
}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transactions(int transaction_id, int member_id, long checkout_date, long return_date) {
		super();
		
		this.transaction_id = transaction_id;
		
		this.member_id = member_id;
		this.checkout_date = checkout_date;
		this.return_date = return_date;
	}
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ",  member_id=" + member_id
				+ ", checkout_date=" + checkout_date + ", return_date=" + return_date + "]";
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
		
	
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public long getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(long checkout_date) {
		this.checkout_date = checkout_date;
	}
	public long getReturn_date() {
		return return_date;
	}
	public void setReturn_date(long return_date) {
		this.return_date = return_date;
	}
	
}


