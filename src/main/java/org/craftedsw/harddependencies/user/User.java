package org.craftedsw.harddependencies.user;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<User> friends = new ArrayList<User>();

    public User(List<User> friends) {
        this.friends = friends;
    }

    public User(){
    }

    public List<User> getFriends() {
		return friends;
	}

    public boolean hasFriend(User friend) {
        for (User myFriend : this.getFriends()) {
            if (myFriend.equals(friend)) {
                return true;
            }
        }
        return false;
    }
}
