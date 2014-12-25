package in.darkstars.service;

import java.util.Properties;
import in.darkstars.dto.User;

public interface Service extends Runnable {
	
	void init ( Properties config, User user ) throws Exception;
	void destroy () ;

}
