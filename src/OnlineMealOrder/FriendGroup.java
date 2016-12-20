package OnlineMealOrder;

import java.util.List;

import javax.persistence.*;
@NamedQueries({@NamedQuery(
		name = "findGroupbyCreator",
		query = "select f from FriendGroup f where f.creator.id = :creatorId"),
		@NamedQuery(
		name = "findGroupbyNameandCreator",
		query = "select f from FriendGroup f where f.creator.id = :creatorId and f.name = :name"),
		@NamedQuery(
		name = "findGroupMember",
		query = "select u from User u JOIN u.memberGroups g where g.id = :groupId and u.username = :membername "),
		@NamedQuery(
		name = "findGroupMembersbyId",
		query = "select f.members from FriendGroup f where f.id = :groupId"),
		@NamedQuery(
		name = "findGroupbyId",
		query = "select f from FriendGroup f where f.id = :groupId")
		})
@Entity
public class FriendGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name="creatorId")
	private User creator;
	@ManyToMany
	private List<User> members;
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
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public FriendGroup(int id, String name, User creator, List<User> members) {
		super();
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.members = members;
	}
	public FriendGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
}