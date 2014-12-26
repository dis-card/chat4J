package in.darkstars.service;

import in.darkstars.dto.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.Logger;

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

/**
 * @author dis-card
 * 
 *         Dec 23, 2014
 * 
 */
public class SendMessageService extends ChatService {

	private static Logger LOGGER = Logger.getLogger(SendMessageService.class);
	private Scanner scanner;
	private StringBuilder message;
	private Socket sock;
	private ObjectOutputStream out;
	
	
	public void init () {
		
		scanner = new Scanner(System.in);
		try {
			sock = new Socket(getUser().getIpAddress(), Integer.parseInt(getConfig().getProperty(ChatService.MSG_SRVR_PORT)));
			
		} catch (UnknownHostException e) {

			e.printStackTrace();
			LOGGER.error(e);
		} catch (IOException e) {

			e.printStackTrace();
			LOGGER.error(e);
		}		
	}
	

	public void run() {

		message = new StringBuilder(scanner.nextLine());
		if (sock != null) {
			if (out == null) {

				try {
					out = new ObjectOutputStream(sock.getOutputStream());
				} catch (IOException e) {
					
					e.printStackTrace();
					LOGGER.error(e);
				}
			}

				Message messageObj = new Message();
				messageObj.setMessage(String.valueOf(message));
				try {
					out.writeObject(messageObj);
				} catch (IOException e) {
				
					e.printStackTrace();
					LOGGER.error(e);
				}

			
		}

	}
	
	public void destroy ()  {
		try {
			sock.close();
			
		} catch (IOException e) {		
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		try {
			out.close();
		} catch (IOException e) {		
			e.printStackTrace();
			LOGGER.error(e);
		}
	}


	

}
