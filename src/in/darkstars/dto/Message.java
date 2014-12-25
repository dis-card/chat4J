package in.darkstars.dto;

import java.io.Serializable;
import java.net.InetAddress;

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
	private InetAddress source;
	private InetAddress destination;
	
	
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


	public InetAddress getSource() {
		return source;
	}


	public void setSource(InetAddress source) {
		this.source = source;
	}


	public InetAddress getDestination() {
		return destination;
	}


	public void setDestination(InetAddress destination) {
		this.destination = destination;
	}


	public String toString () {
		return message;
	}
	
}
