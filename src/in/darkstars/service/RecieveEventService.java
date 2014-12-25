package in.darkstars.service;

import in.darkstars.dto.User;
import in.darkstars.event.Event;
import in.darkstars.helper.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * @author dis-card
 *
 * Dec 25, 2014
 */

public class RecieveEventService extends ChatService {
	
	private static final Logger LOGGER = Logger.getLogger(RecieveEventService.class);
	private byte [] buffer;
	private DatagramPacket packet;
	private DatagramSocket sock;
	private ByteArrayInputStream bis;
	private ObjectInputStream ois;
	
	protected void init () {
		 buffer = new byte[65508];
		 packet = new DatagramPacket(buffer, buffer.length );
		 try {
			sock = new DatagramSocket( Integer.parseInt(EVT_SRVR_PORT) );
			sock.receive(packet);
			bis = new ByteArrayInputStream(packet.getData());
			ois = new ObjectInputStream(bis);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (SocketException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (IOException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		}
	}
	
	public void run () {
		while ( !isStop() ) {
			
			try {
				
				Object obj = ois.readObject();
				if ( obj instanceof Event ) {
					Event evt = (Event) obj;
					User user = evt.getUser();
					String nickName = user.getNickName();
					HashMap<String, User> userMap = getUserMap();
					synchronized ( userMap ) {
						
						switch ( evt.getType() ) {						
						case Online:						
							getUserMap().put(nickName, user );
							break;
						case Offline:
							getUserMap().remove(nickName);
							break;						
						default:
							LOGGER.error(evt);						
						}
						
					}
					
				}				
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.fatal(e);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void destroy () {
		
		Utils.close(ois);
		Utils.close(bis);
		sock.close();
		
	}

}
