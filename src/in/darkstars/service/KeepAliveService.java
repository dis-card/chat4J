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

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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

	
	public void init() {
		
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
