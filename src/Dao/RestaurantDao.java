package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OnlineMealOrder.Restaurant;
import OnlineMealOrder.User;

public class RestaurantDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;
	
	public void createRestaurant(Restaurant rst) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.merge(rst);

		em.getTransaction().commit();
		em.close();
	}
	
	public List<Restaurant> FindRestaurant(User user) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findRestaurantbyUser");
		query.setParameter("userId", user.getId());
		restaurants = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return restaurants;
	}
}