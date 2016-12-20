package OnlineMealOrder;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
@NamedQueries({@NamedQuery(
		name = "findCheckoutbyUser",
		query = "select c from Checkout c JOIN c.user u where u.id = :userId")})
@Entity
public class Checkout {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	private double totalCost;
	private Timestamp checkDate;
	@OneToMany(mappedBy="checkout", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Orders> orders;
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
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Timestamp getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Timestamp checkDate) {
		this.checkDate = checkDate;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public Checkout(int id, User user, double totalCost, Timestamp checkDate,
			List<Orders> orders) {
		super();
		this.id = id;
		this.user = user;
		this.totalCost = totalCost;
		this.checkDate = checkDate;
		this.orders = orders;
	}
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
