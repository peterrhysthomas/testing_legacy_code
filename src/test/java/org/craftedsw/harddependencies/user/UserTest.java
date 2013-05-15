package org.craftedsw.harddependencies.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class UserTest {

    @Test
    public void shouldReturnTrueIfUserIsAFriend(){
        List<User> friends = new ArrayList<User>();
        User friend = new User();
        friends.add(friend);
        User user = new User(friends);

        assertTrue(user.hasFriend(friend));
    }

    @Test
    public void shouldReturnTrueIfUserIsNotFriend(){
        List<User> friends = new ArrayList<User>();
        User notFriend = new User();
        friends.add(new User());
        User user = new User(friends);

        assertFalse(user.hasFriend(notFriend));
    }

}
