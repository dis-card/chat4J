package in.darkstars.event;

import java.io.Serializable;

import in.darkstars.dto.User;

/**
 * @author dis-card
 * 
 *         Dec 25, 2014
 */

public class Event implements Serializable {

	private User user;

	public enum Type {
		Online, Offline, KeepAlive
	}

	public Event(User user, Type eventType) {

		this.user = user;
		this.type = eventType;
	}

	private Type type;

	public User getUser() {
		return user;
	}
	
	public Type getType () {
		return type;
	}


}
