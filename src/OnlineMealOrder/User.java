package OnlineMealOrder;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries(value = { @NamedQuery(
		name = "findUserbyPassword",
		query = "select u from User u where u.username = :username and u.password = :password"),
		@NamedQuery(
		name = "findUserbyUsername",
		query = "select u from User u where u.username = :username")})
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Orders> orders;
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Checkout> checkouts;
	@OneToMany(mappedBy="creator", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<FriendGroup> createGroups;
	@ManyToMany(mappedBy="members")
	private List<FriendGroup> memberGroups;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<FriendGroup> getCreateGroups() {
		return createGroups;
	}
	public void setCreateGroups(List<FriendGroup> createGroups) {
		this.createGroups = createGroups;
	}
	public List<FriendGroup> getMemberGroups() {
		return memberGroups;
	}
	public void setMemberGroups(List<FriendGroup> memberGroups) {
		this.memberGroups = memberGroups;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public List<Checkout> getCheckouts() {
		return checkouts;
	}
	public void setCheckouts(List<Checkout> checkouts) {
		this.checkouts = checkouts;
	}
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
