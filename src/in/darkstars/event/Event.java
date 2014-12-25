package in.darkstars.event;

import java.io.Serializable;

import in.darkstars.dto.User;

/**
 * @author dis-card
 *
 * Dec 25, 2014
 */

public abstract class Event implements Serializable {
	
	private User user;
	
	public Event ( User user ) {
		
		this.user = user;
	}
}
