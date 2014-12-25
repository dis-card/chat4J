package in.darkstars.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * @author dis-card
 *
 * Dec 25, 2014
 */

public class Utils {
	
	private static final Logger LOGGER = Logger.getLogger(Utils.class);
	
	public static void close ( InputStream is ) {
		if ( is != null ) {
			try {
				is.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);				
			}
		}
	}
	
	public static void close ( OutputStream os ) {
		if ( os != null ) {
			try {
				os.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);				
			}
		}
		
	}
	
	public static void close ( Socket sock ) {
		if ( sock != null ) {
			try {
				sock.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);
			}
		}
	}

}
