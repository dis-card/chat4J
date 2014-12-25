package in.darkstars.main;

import in.darkstars.dto.User;
import in.darkstars.exception.ConfigInitException;
import in.darkstars.helper.Messages;
import in.darkstars.presentation.UserInterface;
import in.darkstars.service.KeepAliveService;
import in.darkstars.service.RecieveMessageService;
import in.darkstars.service.SendMessageService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

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
		
		UserInterface ui = new UserInterface();
		ui.init(confObject);
		Thread uit = new Thread(ui,"userInterfaceThread");
		uit.start();
		/*KeepAliveService keepAlive = new KeepAliveService();
		keepAlive.init(confObject, user);
		Thread keepAliver = new Thread(send, "keepAliverThread");
		keepAliver.start();*/
		
	}

}
