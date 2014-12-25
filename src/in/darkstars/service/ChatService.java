package in.darkstars.service;

import in.darkstars.dto.User;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class ChatService implements Service {

	private boolean stop = false;
	private Properties config;
	private User me;
	private HashMap<String, User > userMap;
	protected static final String MSG_SRVR_PORT = "msgServerPort";
	protected static final String EVT_SRVR_PORT = "evtServerPort";
	protected static final String KEEP_ALIVE = "keepAlive";	
	
	
	
	public void init ( Properties config, User me ) {
		this.config = config;
		this.me = me;
		this.userMap = new HashMap<String, User> ();
		init();
	}
	
	protected abstract void init ();
		
	

	public HashMap<String, User> getUserMap() {
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
