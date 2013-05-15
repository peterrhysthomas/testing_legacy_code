package org.craftedsw.harddependencies.trip;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final TripDAO tripDao;
    private User loggedUser;

    public TripService() {
        loggedUser = UserSession.getInstance().getLoggedUser();
        tripDao = new TripDAO();
    }

    public TripService(User user, TripDAO tripDao) {
        this.loggedUser = user;
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
        boolean isFriend = false;
		if (loggedUser != null) {
			if (user.hasFriend(loggedUser)) {
				tripList = getTrips(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

    private List<Trip> getTrips(User user) {
        return tripDao.findTripsByUser(user);
    }

}
