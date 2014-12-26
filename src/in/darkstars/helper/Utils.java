package in.darkstars.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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
 * Dec 25, 2014
 */

public class Utils {
	
	private static final Logger LOGGER = Logger.getLogger(Utils.class);
	
	public static void close ( InputStream is ) {
		if ( is != null ) {
			try {
				is.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);				
			}
		}
	}
	
	public static void close ( OutputStream os ) {
		if ( os != null ) {
			try {
				os.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);				
			}
		}
		
	}
	
	public static void close ( Socket sock ) {
		if ( sock != null ) {
			try {
				sock.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				LOGGER.error(e);
			}
		}
	}

}
