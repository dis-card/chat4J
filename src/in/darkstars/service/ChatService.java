package in.darkstars.service;

import in.darkstars.dto.User;

import java.util.Properties;

public abstract class ChatService implements Service {

	private Properties config;
	private User user;
	protected static final String SRVR_PORT = "serverPort";
	protected static final String KEEP_ALIVE = "keepAlive";
	
	public void init ( Properties config, User user ) {
		this.config = config;
		this.user = user;
		init();
	}
	
	protected abstract void init ();
		
	

	protected Properties getConfig() {
		return config;
	}
	
	protected User getUser () {
		return user;
	}
	
}
