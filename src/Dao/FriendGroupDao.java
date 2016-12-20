package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OnlineMealOrder.FriendGroup;
import OnlineMealOrder.User;

public class FriendGroupDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;
	
	public void createGroup(User creator, String groupName) {

		List<User> members = new ArrayList<User>();
		members.add(creator);
		FriendGroup fg = new FriendGroup();
		fg.setCreator(creator);
		fg.setName(groupName);
		fg.setMembers(members);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(fg);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeGroup(int groupId) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findGroupbyId");
		query.setParameter("groupId", groupId);
		FriendGroup group = null;
		List<FriendGroup> groups = query.getResultList();
		if(!groups.isEmpty())
		{
			group = groups.get(0);
			em.remove(group);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public void addGroupMember(FriendGroup group, User member) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		group.getMembers().add(member);
		em.merge(group);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<FriendGroup> findGroupbyCreator(User creator) {
		List<FriendGroup> friendGroups = new ArrayList<FriendGroup>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findGroupbyCreator");
		query.setParameter("creatorId", creator.getId());
		friendGroups = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return friendGroups;
	}

	public FriendGroup findGroupbyNameandCreator(User creator, String groupname){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findGroupbyNameandCreator");
		query.setParameter("creatorId", creator.getId());
		query.setParameter("name", groupname);
		FriendGroup group = null;
		List<FriendGroup> groups = query.getResultList();
		if(!groups.isEmpty())
			group = groups.get(0);
		em.getTransaction().commit();
		em.close();
		return group;
	}
	
	public User findGroupMember(FriendGroup friendgroup, String membername){
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findGroupMember");
		query.setParameter("groupId", friendgroup.getId());
		query.setParameter("membername", membername);
		User member = null;
		List<User> members = query.getResultList();
		if(!members.isEmpty())
			member = members.get(0);
		em.getTransaction().commit();
		em.close();
		return member;
	}
	
	public List<User> findGroupMembersbyId(int groupId){
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findGroupMembersbyId");
		query.setParameter("groupId", groupId);
		List<User> members = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return members;
	}

}
