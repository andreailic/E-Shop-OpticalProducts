package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Orders")
public class Order {

	@Id
	@SequenceGenerator(name="ORDER_ID_GENERATOR", sequenceName="ORDER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ID_GENERATOR")
	private int orderId;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	private String orderStatus;
	private float orderPrice;
	
	// bi-directional many-to-one association to Brand
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name="User")
			private User user;
			
		public Order() {
						
		}

		public Order(int order_id, Date order_date, String order_status, float order_price, User user) {
			super();
			this.orderId = order_id;
			this.orderDate = order_date;
			this.orderStatus = order_status;
			this.orderPrice = order_price;
			this.user = user;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public float getOrderPrice() {
			return orderPrice;
		}

		public void setOrderPrice(float orderPrice) {
			this.orderPrice = orderPrice;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		
		

}
