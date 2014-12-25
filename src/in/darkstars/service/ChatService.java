package in.darkstars.service;

import java.util.Properties;

public abstract class ChatService implements Service {

	private Properties config;
	protected static final String SRVR_PORT = "serverPort";
	
	public void init ( Properties config ) throws Exception {
		this.config = config;
		init();
	}
	
	protected abstract void init ();
		
	

	protected Properties getConfig() {
		return config;
	}
	
}
