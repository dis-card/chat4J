package in.darkstars.presentation;
/**
 * @author dis-card
 *
 * Dec 25, 2014
 */
import java.awt.Dimension;
import java.util.Properties;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UserInterface implements Runnable {
	
	private Properties config;
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
	
	private int height;
	private int width;
	public static final String title="chat4J";
	private String WIDTH = "width";
	private String HEIGHT = "height";
	
	public void init ( Properties config ) {
		this.config = config;
		height = Integer.parseInt(config.getProperty(HEIGHT) );
		width = Integer.parseInt(config.getProperty(WIDTH));
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height) );
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setJMenuBar( initMenuBar());
		frame.pack();
		frame.setVisible(true);
		
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
		//init();
	}
	
	public void destroy () {
		frame.dispose();
	}

}
