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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.log4j.Logger;

public class KeepOnlineService extends ChatService {
	
	private final static Logger LOGGER = Logger.getLogger(KeepOnlineService.class);
	private DatagramSocket udpSocket;
	private DatagramPacket packet;
	private ObjectOutputStream out;
	private ByteArrayOutputStream bos;
	private byte[] buffer;
	
	public void init() {
		
		try {
			
			udpSocket = new DatagramSocket();
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			
		
			
		} catch (NumberFormatException e) {
			
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
				out.writeObject(new Event( getUser(), Event.Type.Online ));
				out.flush();
				buffer = bos.toByteArray();
				packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(getConfig().getProperty(BROADCAST_ADDR)), Integer.parseInt( getConfig().getProperty(EVT_SRVR_PORT)));
				udpSocket.send(packet);
				System.out.println("Online Sent");
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
		
			
	}
	
	public void destroy () {
		
	}

	
	

}
