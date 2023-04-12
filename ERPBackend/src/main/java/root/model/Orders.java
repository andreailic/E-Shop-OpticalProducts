package root.model;

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
public class Orders {

	@Id
	@SequenceGenerator(name="ORDER_ID_GENERATOR", sequenceName="ORDER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ID_GENERATOR")
	private int order_id;
	
	@Temporal(TemporalType.DATE)
	private Date order_date;
	private String order_status;
	private float order_price;
	
	// bi-directional many-to-one association to Brand
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name="User")
			private User user;
			
		public Orders() {
						
		}

		public Orders(int order_id, Date order_date, String order_status, float order_price, User user) {
			super();
			this.order_id = order_id;
			this.order_date = order_date;
			this.order_status = order_status;
			this.order_price = order_price;
			this.user = user;
		}

		public int getOrder_id() {
			return order_id;
		}

		public void setOrder_id(int order_id) {
			this.order_id = order_id;
		}

		public Date getOrder_date() {
			return order_date;
		}

		public void setOrder_date(Date order_date) {
			this.order_date = order_date;
		}

		public String getOrder_status() {
			return order_status;
		}

		public void setOrder_status(String order_status) {
			this.order_status = order_status;
		}

		public float getOrder_price() {
			return order_price;
		}

		public void setOrder_price(float order_price) {
			this.order_price = order_price;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		
		

}
