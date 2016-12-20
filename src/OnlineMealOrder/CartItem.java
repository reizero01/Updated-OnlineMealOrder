package OnlineMealOrder;

public class CartItem {
	private Meal meal;
	private int quantity;
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
	public CartItem(Meal meal, int quantity) {
		super();
		this.meal = meal;
		this.quantity = quantity;
	}
	
}
