package org.craftedsw.harddependencies.trip;


import org.craftedsw.harddependencies.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    @Test(expected = DependendClassCallDuringUnitTestException.class)
    public void cannotCallUserSessionInTest() throws UserNotLoggedInException {
        TripService tripService = new TripService();
        tripService.getTripsByUser(new User());
    }

    @Test(expected = DependendClassCallDuringUnitTestException.class)
    public void cannotCallDaoInTest() throws UserNotLoggedInException {
        final User loggedUser = new User();
        List<User> friendList = new ArrayList<User>();
        friendList.add(loggedUser);
        User friend =  new User(friendList);

        TripService tripService = new TripService(loggedUser, new TripDAO());

        tripService.getTripsByUser(friend);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void notLoggedInUserThrowsException() throws UserNotLoggedInException {
        TripService tripService = new TripService(null, new TripDAO());
        tripService.getTripsByUser(new User());
    }

    @Test
    public void userWhoIsNotAFriendReturnsNoResults() throws UserNotLoggedInException {
        TripService tripService = new TripService(new User(), new TripDAO());
        assertThat(tripService.getTripsByUser(new User()), is((empty())));
    }


    @Test
    public void userWhoIsAFriendReturnsResults() throws UserNotLoggedInException {
        final User loggedUser = new User();
        List<User> friendList = new ArrayList<User>();
        friendList.add(loggedUser);
        User friend =  new User(friendList);

        TripDAO dao = mock(TripDAO.class);
        List<Trip> tripList = new ArrayList<Trip>();
        tripList.add(new Trip());
        when(dao.findTripsByUser(friend)).thenReturn(tripList);

        TripService tripService = new TripService(loggedUser, dao);

        assertThat(tripService.getTripsByUser(friend), hasSize(1));
    }


}
