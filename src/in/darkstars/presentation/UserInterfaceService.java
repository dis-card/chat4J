package in.darkstars.presentation;

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
 * Dec 25, 2014
 */
import in.darkstars.dto.Event;
import in.darkstars.dto.User;
import in.darkstars.dto.User.Status;
import in.darkstars.helper.DirtyVector;
import in.darkstars.service.ChatService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import in.darkstars.helper.DirtyVector;

public class UserInterfaceService extends ChatService {
	
	private static final Logger LOGGER = Logger.getLogger(UserInterfaceService.class);
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu help;
	private JMenu settings;
	
	private JMenuItem exit;
	private JMenuItem about;
	private JMenuItem manual;
	private JMenuItem license;
	private JCheckBoxMenuItem secure;
	
	private JList<User> userList;
	
	private int height;
	private int width;
	public static final String TITLE="chat4J";
	private String FRAME_WIDTH = "frameWidth";
	private String FRAME_HEIGHT = "frameHeight";
	private JTextPane chatWindow;
	private JTextPane inputWindow;
	private Border lineBorder;
	private Border etchedBorder;
	
	public void init ( ) {
		
		lineBorder = BorderFactory.createLineBorder(Color.black);
		etchedBorder= BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);				
		height = Integer.parseInt(getConfig().getProperty(FRAME_HEIGHT) );
		width = Integer.parseInt(getConfig().getProperty(FRAME_WIDTH));
		frame = new JFrame(TITLE);
		frame.setPreferredSize(new Dimension(width, height) );
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.addWindowListener( new Chat4JWindowListener());
		
		frame.setJMenuBar( initMenuBar());
		frame.getContentPane().add(initChatWindow());
		frame.getContentPane().add(initInputWindow());
		frame.getContentPane().add(initUserList());
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private JList initUserList () {
		userList = new JList<User>();
		userList.setBounds(0, 0, 100, 400);
		userList.setModel(new DefaultListModel<User>()  );
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return userList;
	}
	
	private JTextPane initChatWindow () {
		
		chatWindow = new JTextPane();
		chatWindow.setEditable(false);
		chatWindow.setBounds(100, 0, 300, 300);		
		chatWindow.setBorder(etchedBorder);
		return chatWindow;
	}
	
	private JTextPane initInputWindow () {
		
		inputWindow = new JTextPane();		
		inputWindow.setBounds(100, 300, 300, 100);
		inputWindow.setBorder(lineBorder);
		return inputWindow;
		
	}
	
	private JMenuBar initMenuBar () {
		
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		settings = new JMenu("Settings");
		help = new JMenu("Help");
		
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		manual = new JMenuItem("Manual");
		license = new JMenuItem("Lincense");
		secure = new JCheckBoxMenuItem("Secure");
		secure.enable();
		
		
		file.add(exit);
		settings.add(secure);
		help.add(about);
		help.add(manual);
		help.add(license);
		
		menuBar.add(file);
		menuBar.add(settings);
		menuBar.add(help);
		
		return menuBar;
	}
	public void run () {
		
		while ( !isStop() ) {
			
			DirtyVector<User> chatUsersList = (DirtyVector<User>) getUserList();
			DefaultListModel<User> modelUserList = (DefaultListModel<User>) userList.getModel();
			if ( chatUsersList.isDirty() ) {
				
				modelUserList.removeAllElements();
				for ( User user : chatUsersList ) {
					
					modelUserList.addElement((user));
					
					
					
				}
				chatUsersList.setDirty(false);
				
				
			}
			
			try {
				Thread.sleep(Integer.parseInt(getConfig().getProperty(USER_RFRSH_RATE)) );
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
		frame.dispose();
	}
	
	public void initListeners () {
		
		frame.addWindowListener( new Chat4JWindowListener() );
		   
		
	}
	
	class Chat4JWindowListener extends WindowAdapter {
		
	    public void windowClosing(WindowEvent windowEvent) {
	    	User me = getUser();
    		me.setStatus(Status.Offline);
            super.windowClosing(windowEvent);
        }
    

	}
		
	
	

}
