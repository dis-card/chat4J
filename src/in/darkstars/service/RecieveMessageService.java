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


import in.darkstars.dto.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	

	
	public void init () {
		
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
