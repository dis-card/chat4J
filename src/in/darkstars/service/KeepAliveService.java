package in.darkstars.service;



import in.darkstars.event.Event;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class KeepAliveService extends ChatService {
	
	private final static Logger LOGGER = Logger.getLogger(KeepAliveService.class);
	public final String broadCastAddress = "255.255.255.255";
	private Socket sock;
	private ObjectOutputStream out;
	
	
	
	public void run () {
		
		try {
			out.writeObject(new Event( getUser(), Event.Type.KeepAlive ));
			out.flush();
			System.out.println("Online");
			Thread.sleep( Integer.parseInt(getConfig().getProperty(KEEP_ALIVE) ));
			
		} catch (IOException e) {
		
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (NumberFormatException e) {

			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (InterruptedException e) {

			e.printStackTrace();
			LOGGER.fatal(e);
		}
		
				
		
	}
	
	public void destroy () {
		
	}

	
	protected void init() {
		
		try {
			
		//	sock = new Socket(broadCastAddress,Integer.parseInt(getConfig().getProperty(ChatService.SRVR_PORT)));
			out = new ObjectOutputStream( sock.getOutputStream() );
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (IOException e) {
		
			e.printStackTrace();
			LOGGER.fatal(e);
		}	
		
	}

}
