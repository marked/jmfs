/* 
	Java MFS (jmfs) - Copyright (C) 2010 Artem Erchov
	Contact: comer0@gmail.com 

	This file is part of jmfs.

	jmfs is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	jmfs is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package tivo.disk;
import tivo.io.JavaLog;
//import java.io.RandomAccessFile;
import tivo.io.BiRandomAccessFile;

public class Storage {
        private static final JavaLog log = JavaLog.getLog( TivoDisk.class );

	private String				name;
	private BiRandomAccessFile	img;
	
	public Storage( String name ) throws Exception {
		this( name, false );
	}
	
	public Storage( String name, boolean writable ) throws Exception {
		this.name	= name;
		this.img	= new BiRandomAccessFile( name, (writable ? "rw" : "r") );
	}	

	public String getName() {
		return name;
	}

	public BiRandomAccessFile getImg() {
		return img;
	}

	public long getSize() throws Exception {
		return (img == null) ? 0 : img.length();
	}


	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof Storage) )
			return false;
		Storage that = (Storage)obj;
		return	(this == that)
			||	(this.name == that.name)
			||	((this.name != null) && this.name.equals(that.name));
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			if( img != null ) {
				img.close();
				img = null;
			}
		}
		catch( Throwable t ) {}
		super.finalize();
	}
}
