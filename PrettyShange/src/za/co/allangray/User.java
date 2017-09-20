package za.co.allangray;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User implements Comparable<User>{
	
	String user_name;
	List<String> messages = new ArrayList<String>();
	Set<User> follows = new TreeSet<User>();
	
	 @Override
	public String toString() {
		return user_name ;
			
		
	}

	
	public List<String> getMessages() {
		return messages;
	}


	public void setMessages(List<String> messages) {
		this.messages = messages;
	}


	public Set<User> getFollows() {
		return follows;
	}


	public void setFollows(Set<User> follows) {
		this.follows = follows;
	}


	@Override
	public int compareTo(User user) {
		// TODO Auto-generated method stub
		 return this.user_name.compareTo(user.getUser_name());
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	

	

}
