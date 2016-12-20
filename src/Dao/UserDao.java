package Dao;


import java.util.List;

import javax.persistence.*;

import OnlineMealOrder.User;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;

	public void createUser(User user) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public User findUserbyUsername(String username) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findUserbyUsername");
		query.setParameter("username", username);
		User user = null;
		List<User> users = query.getResultList();
		if(!users.isEmpty())
			user = users.get(0);
		em.getTransaction().commit();
		em.close();
		return user;
	}
	
	public User findUserbyPassword(String username, String password) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findUserbyPassword");
		query.setParameter("username", username);
		query.setParameter("password", password);
		User user = null;
		List<User> users = query.getResultList();
		if(!users.isEmpty())
			user = users.get(0);
		em.getTransaction().commit();
		em.close();
		return user;
	}
}
