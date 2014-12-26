package in.darkstars.service;

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

import in.darkstars.dto.User;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public abstract class ChatService implements Service {

	private boolean stop = false;
	private Properties config;
	private User me;
	private Map<String, User > userMap;
	protected static final String MSG_SRVR_PORT = "msgServerPort";
	protected static final String EVT_SRVR_PORT = "evtServerPort";
	protected static final String KEEP_ALIVE = "keepAlive";	
	
	
	
	public void init ( Properties config, User me ) {
		this.config = config;
		this.me = me;
		this.userMap = new TreeMap<String, User> ();
		init();
	}
	
	public abstract void init ();
		
	

	public Map<String, User> getUserMap() {
		return userMap;
	}	

	protected Properties getConfig() {
		return config;
	}
	
	protected User getUser () {
		return me;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
}
