package root.model;

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

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Review")
public class Review {

	@Id
	@SequenceGenerator(name="REVIEW_ID_GENERATOR", sequenceName="REVIEW_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEW_ID_GENERATOR")
	private int review_id;
	
	private float rating;
	private String comment;
	
	// bi-directional many-to-one association to Brand
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name="User")
			private User user;
			
	// bi-directional many-to-one association to Brand
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name="Product")
			private Product product;
					
			
	  public Review() {
				
			}


	public Review(int review_id, float rating, String comment, User user, Product product) {
		super();
		this.review_id = review_id;
		this.rating = rating;
		this.comment = comment;
		this.user = user;
		this.product = product;
	}


	public int getReview_id() {
		return review_id;
	}


	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
			
	  
}
