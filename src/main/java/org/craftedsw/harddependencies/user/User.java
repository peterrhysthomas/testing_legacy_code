package org.craftedsw.harddependencies.user;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<User> friends = new ArrayList<User>();

	public List<User> getFriends() {
		return friends;
	}
}
