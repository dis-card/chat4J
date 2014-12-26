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

import in.darkstars.dto.Event;
import in.darkstars.dto.User;
import in.darkstars.helper.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Map;

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
	private MulticastSocket sock;
	private ByteArrayInputStream bis;
	private ObjectInputStream ois;
	private InetAddress multicastAddress;
	
	public void init () {
		 buffer = new byte[65508];
		 packet = new DatagramPacket(buffer, buffer.length );
		 try {
			sock = new MulticastSocket( Integer.parseInt(getConfig().getProperty(EVT_SRVR_PORT)) );
			sock.setBroadcast(true);
			//multicastAddress = InetAddress.getByName(getConfig().getProperty(BROADCAST_ADDR));
			//sock.joinGroup(multicastAddress);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (SocketException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void run () {
		while ( !isStop() ) {
			
			try {
				sock.receive(packet);
				bis = new ByteArrayInputStream(packet.getData());
				ois = new ObjectInputStream(bis);
				Object obj = ois.readObject();
				if ( obj instanceof Event ) {
					Event evt = (Event) obj;
					User user = evt.getUser();
					String nickName = user.getNickName();
					Map<String, User> userMap = getUserMap();						
						switch ( evt.getType() ) {						
						case Online:
							System.out.println("Online Recieved");
							synchronized (userMap) {
								userMap.put(nickName, user );
							}
							break;
						case Offline:
							userMap.remove(nickName);
							break;						
						default:
							LOGGER.error(evt);
							break;
						}
						
					
					
				}			
				ois.close();
				bis.close();
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.fatal(e);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void destroy () {
		
		
		sock.close();
		
	}

}
