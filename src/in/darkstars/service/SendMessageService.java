package in.darkstars.service;

import in.darkstars.dto.Message;
import in.darkstars.dto.User;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

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
	
	
	protected void init () {
		
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
