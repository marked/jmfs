package tivo.io;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;

import java.io.DataInputStream;
import java.io.Closeable;

public class BiDataInputStream implements Closeable, DataInput {
    private static Boolean reverseMode;
    private DataInputStream dis;

    public static void setReverseMode(Boolean b) {
	reverseMode = b;
    }
    
    public BiDataInputStream(InputStream in) {
	dis = new DataInputStream ( in );
    }
    
    public void close()  throws IOException {
	dis.close();
    }    
    public int	read(byte[] b)  throws IOException {
	return dis.read(b);
    }
    public int	read(byte[] b, int off, int len) throws IOException {
	return dis.read(b,off,len);
    }
    public boolean readBoolean()  throws IOException {
	return dis.readBoolean();
    }
    public byte readByte()  throws IOException {
	return dis.readByte();
    }
    public char readChar()  throws IOException {
	return dis.readChar();
    }
    public double readDouble() throws IOException {
	return dis.readDouble();
    }
    public float readFloat()  throws IOException {
	return dis.readFloat();
    }
    public void	readFully(byte[] b)  throws IOException {
	dis.readFully(b);
    }
    public void	readFully(byte[] b, int off, int len)  throws IOException {
	dis.readFully(b,off,len);
    }

		public final short readShort() throws IOException {
			if(reverseMode) {
				return Short.reverseBytes(dis.readShort());
			}
			else {
				return dis.readShort();
			}
		 }
	public final int readUnsignedShort() throws IOException {
    if(reverseMode) {
                 int byte1 = dis.read();
    int byte2 = dis.read();
    if (byte2 == -1  || byte2 == -1) throw new EOFException();
    return (byte2 << 8) + byte1;
  }


		else {
			return dis.readUnsignedShort();
		}
	 }

	public final int readInt() throws IOException {
		if(reverseMode) {
			return Integer.reverseBytes(dis.readInt());
		}
		else {
			return dis.readInt();
		}
	 }
	public final long readLong() throws IOException {
		if(reverseMode) {
			return Long.reverseBytes(dis.readLong());
		}
		else {
			return dis.readLong();
		}
	 }

   public int readUnsignedByte()  throws IOException {
			return dis.readUnsignedByte();
    }

     public String readLine() throws IOException {
	return dis.readLine();
    }


    public String readUTF()  throws IOException {
	return dis.readUTF();
    }
    public static String readUTF(DataInput in)  throws IOException {
	return readUTF(in);
    }
    
    public int skipBytes(int n) throws IOException {
	return dis.skipBytes(n);
    }


}
