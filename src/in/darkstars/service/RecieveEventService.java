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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.List;

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
					if ( !user.equals(getUser()) ) {
						
						String nickName = user.getNickName();
						List<User> userList = getUserList();						
						LOGGER.debug(user.getStatus()+" recieved from "+ user.getNickName());
						
						// If the user is not in the list or his status changed then only we process the event. 
					
						if ( !userList.contains(user) ) {
							userList.add( user );
							System.out.println("Added "+user.getNickName()+" as "+user.getStatus());
						} else if ( isStatusChanged(user) ) {
							
							int index = userList.indexOf( user );
							switch ( evt.getType() ) {
							case Offline:
								userList.remove( user );
								System.out.println("Removed "+user.getNickName());
								break;
							case Online:
							case Busy:
							case Away:
								userList.set(index, user);
								break;
							default:
								LOGGER.error(evt.getType());
								break;
							
							}
												
							
						}
					/*	if ( !userList.contains(user) || ( userList.contains(user) && isStatusChanged(user) ) ) {
							
							switch ( evt.getType() ) {						
							case Online:
								// if the user is not in the list, or if he is in the list but it's status changed.
								if ( userList.contains(user) ) {
									int index = userList.indexOf( user );
									userList.set(index, user);									
								} else {
									userList.add( user );
									System.out.println("Added "+user.getNickName());
								}
								
								break;
							case Offline:								
								if ( userList.contains(user) ) {
										userList.remove( user );
										System.out.println("Removed "+user.getNickName());
								}							
								break;						
							case Busy :
								if ( userList.contains(user) ) {
									int index = userList.indexOf(user);
									User chatUser = (User)userList.get(index);
									chatUser.setStatus(user.getStatus());
									userList.set(index, chatUser);
									System.out.println("Status changed for "+chatUser.getNickName()+" to "+chatUser.getStatus());
							} else {
								userList.add( user );
								System.out.println("Added from busy "+user.getNickName());
							}
								break;
							case Away :								
								if ( userList.contains(user) ) {
										int index = userList.indexOf(user);
										User chatUser = (User)userList.get(index);
										chatUser.setStatus(user.getStatus());
										userList.set(index, chatUser);
										System.out.println("Status changed for "+chatUser.getNickName()+" to "+chatUser.getStatus());
								} else {
									userList.add( user );
									System.out.println("Added from away "+user.getNickName());
								}
								break;
							default:
								LOGGER.error(evt);
								break;
							}							
						}*/
												
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
	
	private boolean isStatusChanged(User user) {
		
		boolean statusChanged = false;
		
		if ( user.getStatus() != getUserFromList(user).getStatus() ) {
			statusChanged = true;
		}
		return statusChanged;
	}
	
	private User getUserFromList ( User user ) {
		
		int index = getUserList().indexOf(user);
		User chatUser = (User)getUserList().get(index);
		return chatUser;
	}

	public void destroy () {
		
		
		sock.close();
		
	}

}
