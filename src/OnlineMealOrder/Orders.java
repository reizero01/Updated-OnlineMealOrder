package OnlineMealOrder;

import javax.persistence.*;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="mealId")
	private Meal meal;
	private int quantity;
	@ManyToOne
	@JoinColumn(name="checkoutId")
	private Checkout checkout;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Checkout getCheckout() {
		return checkout;
	}
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}
	public Orders(int id, User user, Meal meal, int quantity, Checkout checkout) {
		super();
		this.id = id;
		this.user = user;
		this.meal = meal;
		this.quantity = quantity;
		this.checkout = checkout;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}