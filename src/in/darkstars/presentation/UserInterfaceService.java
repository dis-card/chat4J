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
import in.darkstars.dto.User;
import in.darkstars.helper.DirtyArrayList;
import in.darkstars.service.ChatService;

import java.awt.Dimension;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class UserInterfaceService extends ChatService {
	
	
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
	public static final String title="chat4J";
	private String WIDTH = "width";
	private String HEIGHT = "height";
	
	public void init ( ) {
		
		height = Integer.parseInt(getConfig().getProperty(HEIGHT) );
		width = Integer.parseInt(getConfig().getProperty(WIDTH));
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height) );
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setJMenuBar( initMenuBar());
		frame.add(initUserList());
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private JList initUserList () {
		userList = new JList<User>();
		userList.setModel(new DefaultListModel<User>()  );
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		return userList;
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
		List<User> users = getUserList();
		if ( ((DirtyArrayList<User>)users).isDirty() ) {
			
			((DefaultListModel<User>)userList.getModel()).removeAllElements();
			for ( User user : users ) {
				
				((DefaultListModel<User>)userList.getModel()).addElement((user));
			}
			
			
		}
		
	}
	
	public void destroy () {
		frame.dispose();
	}
	
	

}
