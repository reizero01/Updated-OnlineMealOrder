package OnlineMealOrder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@NamedQueries({@NamedQuery(
		name = "findRestaurantbyCuisine",
		query = "select r from Restaurant r where :cuisine MEMBER OF r.cuisine"),
		@NamedQuery(
		name = "findRestaurantbyUser",
		query = "select DISTINCT r from Restaurant r JOIN r.meals m JOIN m.orders o JOIN o.user u where u.id = :userId")
		})

@Entity
public class Restaurant {
	@Id
	private int id;
	private String name;
	private String address;
	private String city;
	@ElementCollection
	@CollectionTable(name ="cuisines")
	private List<String> cuisine = new ArrayList<String>();;
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Meal> meals;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getCuisine() {
		return cuisine;
	}
	public void setCuisine(List<String> cuisine) {
		this.cuisine = cuisine;
	}
	public List<Meal> getMeals() {
		return meals;
	}
	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	public Restaurant(int id, String name, String address, String city,
			List<String> cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.cuisine = cuisine;
	}
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
