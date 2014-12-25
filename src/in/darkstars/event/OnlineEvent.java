package in.darkstars.event;

import in.darkstars.dto.User;

/**
 * @author dis-card
 *
 * Dec 25, 2014
 */

public class OnlineEvent extends Event {
	
	public OnlineEvent ( User user ) {
		super(user);
	}

}
