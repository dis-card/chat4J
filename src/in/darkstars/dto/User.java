package in.darkstars.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import in.darkstars.event.StatusChangeEvent;
import in.darkstars.event.StatusChangeListener;

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
 */
public class User implements Serializable {
	
	private String nickName;
	private String ipAddress;
	public enum	Status {
		Online,
		Offline,
		Busy,
		Away
	}
	private Status status;
	private transient List <StatusChangeListener> stateChangeListenerList = new ArrayList<StatusChangeListener>();	
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
		this.fireStatusChangeEvent();
	}
	
	public String toString () {
		return this.nickName+status.toString();
	}
	
	public boolean equals ( Object obj ) {
		boolean equal = false;
		if ( obj instanceof User && ((User)obj).getNickName().equals(nickName) && ((User)obj).getIpAddress().equals(ipAddress) ) {
			equal = true;
		}
		return equal;
		
	}
	
	public void addStatusChangeListener ( StatusChangeListener listener ) {
		if ( !stateChangeListenerList.contains(listener)) {
			stateChangeListenerList.add(listener);
		}		
		
	}
	
	public void removeStatusChangeListener ( StatusChangeListener listener ) {
		if ( !stateChangeListenerList.contains(listener)) {
			stateChangeListenerList.add(listener);
		}
	}
	
	private void fireStatusChangeEvent () {
		StatusChangeEvent evt = new StatusChangeEvent(this);
		if ( stateChangeListenerList != null ) {
			for (StatusChangeListener listener : stateChangeListenerList ) {
				listener.statusChangeEventOccurred(evt);
			}
		}
		
	}
	
	

}
