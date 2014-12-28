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
import in.darkstars.dto.User.Status;
import in.darkstars.event.StatusChangeEvent;
import in.darkstars.event.StatusChangeListener;
import in.darkstars.helper.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.log4j.Logger;

public class SendEventService extends ChatService implements StatusChangeListener {
	
	private final static Logger LOGGER = Logger.getLogger(SendEventService.class);
	private DatagramSocket udpSocket;
	private DatagramPacket packet;
	private ObjectOutputStream out;
	private ByteArrayOutputStream bos;
	private byte[] buffer;
	
	public void init() {
		
		try {
			
			udpSocket = new DatagramSocket();	
		
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			LOGGER.fatal(e);
		} catch (IOException e) {
		
			e.printStackTrace();
			LOGGER.fatal(e);
		}	
		
	}
	
	
	public void run () {
		
		int packetBurstRate = Integer.parseInt(getConfig().getProperty(PACKET_BURST_RATE));
		
		while ( !isStop() ) {
			
			try {
				for ( int i = 1; i <= packetBurstRate; i++ ) {
					
					User me = getUser();
					Event eventToSend = new Event();
					eventToSend.setUser(me);
					switch (me.getStatus()) {
					case Online:
						eventToSend.setType(Status.Online);
						break;
					case Offline:
						eventToSend.setType(Status.Offline);
						break;
					default:
						break;
					
					}
					bos = new ByteArrayOutputStream();
					out = new ObjectOutputStream(bos);
					out.writeObject( eventToSend );
					out.flush();
					buffer = bos.toByteArray();
					packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(getConfig().getProperty(BROADCAST_ADDR)), Integer.parseInt( getConfig().getProperty(EVT_SRVR_PORT)));
					udpSocket.send(packet);
					LOGGER.debug((eventToSend.getType()+" Sent"+i));
					
				}
					LOGGER.debug("Sleeping");
					Thread.sleep( Integer.parseInt ( getConfig().getProperty ( PACKET_BURST_INTERVAL ) ) );
				
				
				
			} catch (IOException e) {
			
				e.printStackTrace();
				LOGGER.fatal(e);
			} catch (NumberFormatException e) {

				e.printStackTrace();
				LOGGER.fatal(e);
			} catch (InterruptedException e) {

				e.printStackTrace();
				LOGGER.fatal(e);
			} finally {
				Utils.close(bos);
				Utils.close(out);
			}
			
		}
		
		
	}
	
	
	
	public void destroy () {
		
	}


	
	public void statusChangeEventOccurred(StatusChangeEvent evt) {
		
		int packetBurstRate = Integer.parseInt(getConfig().getProperty(PACKET_BURST_RATE));
		
		for ( int i = 1; i <= packetBurstRate; i++ ) {
			
			User me = (User) evt.getSource();
			Event eventToSend = new Event();
			eventToSend.setUser(me);
			switch (me.getStatus()) {
			case Online:
				eventToSend.setType(Status.Online);
				break;
			case Offline:
				eventToSend.setType(Status.Offline);
				break;
			default:
				break;
			
			}
			try {
				bos = new ByteArrayOutputStream();
				out = new ObjectOutputStream(bos);
				out.writeObject( eventToSend );
				out.flush();
				buffer = bos.toByteArray();
				packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(getConfig().getProperty(BROADCAST_ADDR)), Integer.parseInt( getConfig().getProperty(EVT_SRVR_PORT)));
				udpSocket.send(packet);
				System.out.println(eventToSend.getType()+" Sent"+i);
				LOGGER.debug(eventToSend.getType()+" Sent"+i);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.fatal(e);
			} finally {
				
				Utils.close(bos);
				Utils.close(out);
				
			}
			
			
		}


		
	}

	
	

}
