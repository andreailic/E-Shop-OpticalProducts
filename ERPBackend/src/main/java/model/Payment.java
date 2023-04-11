package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Payment")
public class Payment {

	@Id
	@SequenceGenerator(name="PAYMENT_ID_GENERATOR", sequenceName="PAYMENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_ID_GENERATOR")
	private int payment_id;
	
	private String payment_method;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="order_id", referencedColumnName="order_id")	
	private Orders orders;
	
	public Payment() {
		
	}

	public Payment(int payment_id, String payment_method, Orders orders) {
		super();
		this.payment_id = payment_id;
		this.payment_method = payment_method;
		this.orders = orders;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
}

