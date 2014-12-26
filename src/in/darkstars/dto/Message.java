package in.darkstars.dto;

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
 * Dec 22, 2014
 *
 */
public class Message implements Serializable {

	private String message;
	private User from;
	private User to;
	
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public User getFrom() {
		return from;
	}


	public void setFrom(User from) {
		this.from = from;
	}


	public User getTo() {
		return to;
	}


	public void setTo(User to) {
		this.to = to;
	}


	public String toString () {
		return message;
	}
	
}
