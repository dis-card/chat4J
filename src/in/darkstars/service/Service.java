package in.darkstars.service;

import java.util.Properties;

public interface Service extends Runnable {
	
	void init ( Properties config ) throws Exception;
	void destroy () ;

}
