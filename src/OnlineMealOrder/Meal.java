package OnlineMealOrder;

import java.util.List;

import javax.persistence.*;

@Entity
public class Meal {
	@Id
	private int id;
	private String name;
	private String discription;
	private double price;
	@ManyToOne
	@JoinColumn(name="restaurantId")
	private Restaurant restaurant;
	@OneToMany(mappedBy="meal", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Orders> orders;
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
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public Meal(int id, String name, String discription, double price) {
		super();
		this.id = id;
		this.name = name;
		this.discription = discription;
		this.price = price;
	}
	public Meal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
