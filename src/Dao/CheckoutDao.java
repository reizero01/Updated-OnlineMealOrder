package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OnlineMealOrder.Checkout;
import OnlineMealOrder.User;

public class CheckoutDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;
	
	public void createCheckout(Checkout ck) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(ck);

		em.getTransaction().commit();
		em.close();
	}
	
	public List<Checkout> getCheckoutbyUser(User user) {
		List<Checkout> checkouts = new ArrayList<Checkout>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findCheckoutbyUser");
		query.setParameter("userId", user.getId());
		checkouts = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return checkouts;
	}
	
}
