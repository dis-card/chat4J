package in.darkstars.service;

import in.darkstars.dto.Message;
import in.darkstars.exception.ConfigInitException;
import in.darkstars.helper.Messages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author dis-card
 *
 * Dec 22, 2014
 *
 */
public class RecieveMessageService extends ChatService {
	
	private static Logger LOGGER = Logger.getLogger( RecieveMessageService.class );
	
	private ServerSocket server;
	private Socket sock;
	private int serverPort;
	

	
	protected void init () {
		
		try {
			
			server = new ServerSocket( Integer.parseInt( (String)getConfig().get(ChatService.MSG_SRVR_PORT) ));
			this.serverPort = server.getLocalPort();
		} catch (NumberFormatException e) {
		
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (IOException e) {

			e.printStackTrace();
			LOGGER.fatal(e);
		}
		
	}
	
	
	
	public void run ()  {
		
		try {
			
			sock = server.accept();
			ObjectInputStream os = new ObjectInputStream( sock.getInputStream() );
			Message message = (Message) os.readObject();
			System.out.println(message.toString());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			LOGGER.error(e);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			LOGGER.error(e);
		}
		
	}

	public void destroy ()  {
		try {
			server.close();
			
		} catch (IOException e) {		
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		try {
			sock.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
			LOGGER.error(e);
		}
		
	}
	

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}	

}
