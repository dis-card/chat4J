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
import in.darkstars.dto.User.Status;
import in.darkstars.helper.DirtyVector;
import in.darkstars.service.ChatService;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

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
	
	private JScrollPane chatWindowScrollPane;
	
	private int height;
	private int width;
	public static final String TITLE="chat4J";
	private String FRAME_WIDTH = "frameWidth";
	private String FRAME_HEIGHT = "frameHeight";
	private JTextPane chatWindow;
	private JTextPane inputWindow;
	private Border lineBorder;
	private Border etchedBorder;
	private JMenu status;
	private JRadioButtonMenuItem online;
	private JRadioButtonMenuItem busy;
	private JRadioButtonMenuItem away;
	private ButtonGroup statusBtnGrp;
	
	private ImageIcon availableIcon = new ImageIcon("icon/available.png");
	private ImageIcon busyIcon = new ImageIcon("icon/busy.png");
	private ImageIcon awayIcon = new ImageIcon("icon/away.png");
	
	private Font serifFont = new Font("Serif", Font.ITALIC, 15);
	private Font monoTypeCrosive = new Font("Monotype Corsiva",Font.PLAIN, 10);
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
		userList.setCellRenderer(new ListEntryCellRenderer());
		userList.addMouseListener(new ListAction());
		return userList;
	}
	
	private JScrollPane initChatWindow () {
		
		JTextPane chatWindow = createChatWindow();		
		chatWindow.setText(getCopyRight());		
		return addScrollBar(chatWindow);
	}
	
	private JScrollPane addScrollBar ( Component comp ) {
		chatWindowScrollPane = new JScrollPane(chatWindow);
		chatWindowScrollPane.setBounds(100, 0, 300, 300);
		return chatWindowScrollPane;
	}
	
	private JTextPane createChatWindow() {
		chatWindow = new JTextPane();
		chatWindow.setContentType("text/html");
		chatWindow.setEditable(false);
		chatWindow.setBounds(100, 0, 300, 300);		
		chatWindow.setBorder(etchedBorder);			
		return chatWindow;
	}
	
	private String getCopyRight() {
		return "Copyright (c) 2014 dis-card. All rights reserved."
		+ "Redistribution and use in source and binary forms are permitted"
		+ "provided that the above copyright notice and this paragraph are"
		+ "duplicated in all such forms and that any documentation,"
		+ "advertising materials, and other materials related to such"
		+ "distribution and use acknowledge that the software was developed"
		+ "by the <dis-card>. The name of the"
		+ "<dis-card> may not be used to endorse or promote products derived"
		+ "from this software without specific prior written permission."
		+ "THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR"
		+ "IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED"
		+ "WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE"; 
		
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
		status = new JMenu ("Status");
		settings = new JMenu("Settings");
		help = new JMenu("Help");
		
		exit = new JMenuItem("Exit");
		statusBtnGrp = new ButtonGroup();
		online = new JRadioButtonMenuItem("Online", true);
		busy = new JRadioButtonMenuItem("Busy");
		away = new JRadioButtonMenuItem("Away");
		online.addActionListener(new OnlineRadioMenuItemActionListener ());
		busy.addActionListener(new BusyRadioMenuItemActionListener ());
		away.addActionListener(new AwayRadioMenuItemActionListener ());
		statusBtnGrp.add(online);
		statusBtnGrp.add(busy);
		statusBtnGrp.add(away);

		
		about = new JMenuItem("About");
		manual = new JMenuItem("Manual");
		license = new JMenuItem("Lincense");
		secure = new JCheckBoxMenuItem("Secure");
		secure.enable();
		
		
		file.add(exit);
		status.add(online);
		status.add(busy);
		status.add(away);
		settings.add(secure);
		help.add(about);
		help.add(manual);
		help.add(license);
		
		menuBar.add(file);
		menuBar.add(status);
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
	
	class OnlineRadioMenuItemActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			getUser().setStatus(User.Status.Online);
			LOGGER.info("Status changed to Online");
			
		}
		
	}
		
	class BusyRadioMenuItemActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			getUser().setStatus(User.Status.Busy);			
			LOGGER.info("Status changed to Busy");
		}
		
	}
	
	class AwayRadioMenuItemActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			getUser().setStatus(User.Status.Away);
			LOGGER.info("Status changed to Away");
			
		}
		
	}


	class ListEntryCellRenderer	 extends JLabel implements ListCellRenderer
	{
	   private JLabel label;
	  
	   public Component getListCellRendererComponent(JList list, Object value,
	                                                 int index, boolean isSelected,
	                                                 boolean cellHasFocus) {
	      User user = (User) value;	  
	      setText(user.toString());
	      switch ( user.getStatus() ) {
	      case Online:
	    	  setIcon(availableIcon);
	    	  break;
	      case Busy:
	    	  setIcon(busyIcon);
	    	  break;
	      case Away:
	    	  setIcon(awayIcon);
	    	  break;
	      default:
	    	  LOGGER.error("Unkown "+user.getStatus());
	    	   break;
	      }	      
	   
	      if (isSelected) {
	         setBackground(list.getSelectionBackground());
	         setForeground(list.getSelectionForeground());
	      }
	      else {
	         setBackground(list.getBackground());
	         setForeground(list.getForeground());
	      }
	      /*
	  
	      setEnabled(list.isEnabled());
	      setFont(list.getFont()); */
	      setOpaque(true);
	  
	      return this;
	   }
	}
	
	class ListAction extends MouseAdapter {
		@Override
		public void mouseClicked ( MouseEvent e ) {
			 if(e.getClickCount() == 2){
			     int index = userList.locationToIndex(e.getPoint());
			     DefaultListModel<User> dlm = (DefaultListModel<User>) userList.getModel();
			     User user = dlm.getElementAt(index);			     
			     System.out.println("Double clicked on " + user.getNickName());
			     chatWindow.setText(user.getChatHistory().toString());
				
				
			}
			
		}
	}
	
	

}
