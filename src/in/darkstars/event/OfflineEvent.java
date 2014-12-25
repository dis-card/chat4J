package in.darkstars.event;

import in.darkstars.dto.User;

/**
 * @author dis-card
 *
 * Dec 25, 2014
 */

public class OfflineEvent extends Event {
	
	public OfflineEvent ( User user ) {
		super(user);
	}

}
