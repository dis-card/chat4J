package in.darkstars.main;

import in.darkstars.dto.User;
import in.darkstars.exception.ConfigInitException;
import in.darkstars.helper.Messages;
import in.darkstars.presentation.UserInterfaceService;
import in.darkstars.service.KeepAliveService;
import in.darkstars.service.RecieveEventService;
import in.darkstars.service.RecieveMessageService;
import in.darkstars.service.SendMessageService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
public class Test {
	
	private static Logger LOGGER = Logger.getLogger(Test.class);
	public static String CONF_FILE="config/conf.properties";
	private static Properties confObject;
	
	public static void main(String args[]) throws ConfigInitException,
			IOException {
		try {

			LOGGER.info(Messages.CONFIG_LOAD);

			FileInputStream fis = new FileInputStream(CONF_FILE);
			confObject = new Properties();
			confObject.load(fis);

			LOGGER.info(Messages.CONFIG_LOAD_SUC);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			LOGGER.fatal(e);

		} catch (IOException e) {

			e.printStackTrace();
			LOGGER.fatal(e);
		}
		RecieveMessageService recieve = new RecieveMessageService();
		User me = new User ();
		me.setIpAddress("192.168.1.13");
		me.setNickName("Vikash");
		me.setStatus(User.Status.Available);
		recieve.init(confObject, me);
		Thread reciever = new Thread(recieve, "recieverThread");
		reciever.start();
		SendMessageService send = new SendMessageService();
		send.init(confObject, me);
		Thread sender = new Thread(send, "senderThread");
		sender.start();
		
		RecieveEventService event = new RecieveEventService();
		event.init(confObject, me);
		Thread eventReciever = new Thread (event,"recieveEventThread");
		eventReciever.start();
		
		
		
		UserInterfaceService ui = new UserInterfaceService();
		ui.init(confObject, me);
		Thread uit = new Thread(ui,"userInterface Thread");
		uit.start();
		/*KeepAliveService keepAlive = new KeepAliveService();
		keepAlive.init(confObject, user);
		Thread keepAliver = new Thread(send, "keepAliverThread");
		keepAliver.start();*/
		
	}

}
