package OnlineMealOrder;

import java.lang.String; 
import java.lang.Integer; 
import java.lang.Float; 
import java.util.Hashtable; 
import java.util.Enumeration;

public class ShoppingCart {
	
	 protected Hashtable items = new Hashtable(); 
	 
	 public void addItem(Meal meal,  int quantity) {
		 
		 CartItem newItem = new CartItem(meal, quantity);
		 if (items.containsKey(meal.getId())) { 
			 CartItem tmpItem = (CartItem)items.get(meal.getId()); 
			 int tmpQuant = tmpItem.getQuantity();
			 quantity += tmpQuant; 
			 tmpItem.setQuantity(quantity);
		 }
		 else
		 {
			 items.put(meal.getId(), newItem);
		 }
	 }
	 
	 public void removeItem(Meal meal) { 
		 
		 if (items.containsKey(meal.getId())) { 
			 items.remove(meal.getId()); 
		 } 
	}
	 
	public void removeAll() { 
		 items.clear();
	}
	 
	 public void updateQuantity(Meal meal, int quantity) { 
		 
		 if (items.contains(meal.getId())) { 
			 CartItem tmpItem = (CartItem)items.get(meal.getId()); 
			 tmpItem.setQuantity(quantity);
		 } 
	 }
	 
	 public Enumeration getEnumeration() { 
		 
		 return items.elements(); 
	}
	
	 public double getCost() { 
		 
		 Enumeration<CartItem> allItems = items.elements();
		 CartItem tmpItem; 
		 double totalCost = 0.00; 
		 
		 while (allItems.hasMoreElements()) { 
			 tmpItem = (CartItem)allItems.nextElement();; 
			 totalCost += (tmpItem.getMeal().getPrice() * tmpItem.getQuantity()); 
		 } 
		 return totalCost; 
	 } 
	 
	 public int getNumOfItems() { 
		 
		 Enumeration<CartItem> allItems = items.elements();
		 CartItem tmpItem; 
		 int numOfItems = 0; 
		 
		 while (allItems.hasMoreElements()) { 
			 tmpItem = (CartItem)allItems.nextElement();; 
			 numOfItems += tmpItem.getQuantity(); 
		 }
		 return numOfItems;
	 }
}
