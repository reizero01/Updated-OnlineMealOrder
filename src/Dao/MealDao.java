package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import OnlineMealOrder.Meal;

public class MealDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;
	
	public void createMeal(Meal ml) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.merge(ml);

		em.getTransaction().commit();
		em.close();
	}
}