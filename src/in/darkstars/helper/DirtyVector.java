package in.darkstars.helper;

import java.util.Vector;

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
 * Dec 27, 2014
 */

public class DirtyVector <T> extends Vector<T> {
	
	private boolean dirty;
	
	public boolean add ( Object obj ) {		
		
		dirty = true;
		return super.add((T) obj);	
		
	}
	
	public void add ( int index, Object obj ) {
		dirty = true;
		 super.add ( index, (T) obj );
	}

	
	public boolean isDirty() {
		return dirty;
	}	
	
	public void setDirty ( boolean dirty ) {
		this.dirty = dirty;
	}
	
	public T get( int index ) {
		dirty  = false;
		return super.get(index);
	}

	
}
