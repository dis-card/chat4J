package in.darkstars.dto;

import in.darkstars.dto.User.Status;

import java.io.Serializable;


/*Copyright (c) <2014> <dis-card>.
All rights reserved.

Redistribution and use in source and binary forms are permitted
provided that the above copyright notice and this paragraph are
duplicated in all such forms and that any documentation,
advertising materials, and other materials related to such
distribution and use acknowledge that the software was developed
by the <dis-card>. The name of the
<dis-card> may not be used to endorse or promote products derived
from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.*/


/**
 * @author dis-card
 * 
 *         Dec 25, 2014
 */

public class Event implements Serializable {

	private User user;
	private Status type;
	
	public Event(User user, Status type) {

		this.user = user;
		this.type = type;
	}
	
	public Event() {
		
	}

	

	public User getUser() {
		return user;
	}
	
	public Status getType () {
		return type;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setType(Status type) {
		this.type = type;
	}
	
	


}
