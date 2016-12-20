package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import OnlineMealOrder.Orders;

public class OrderDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;
	
	public void createOrder(Orders order) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(order);

		em.getTransaction().commit();
		em.close();
	}
}
