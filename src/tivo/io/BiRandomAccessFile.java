package tivo.io;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.RandomAccessFile;
import java.io.UTFDataFormatException;
import java.io.FileDescriptor;
import java.nio.channels.FileChannel;
import java.io.File;

import java.io.DataInputStream;
import java.io.Closeable;

public class BiRandomAccessFile
extends Object
implements DataOutput, DataInput, Closeable {
    private static Boolean reverseMode ;
	private RandomAccessFile raf;

    public static void setReverseMode(Boolean b) {
        reverseMode = b;
    }
   

public BiRandomAccessFile(String name, String mode) throws FileNotFoundException {
  raf = new RandomAccessFile (name, mode);
}

public BiRandomAccessFile(File file, String mode) throws FileNotFoundException {
  raf = new RandomAccessFile (file, mode);
}

public Boolean flipMode() {
	reverseMode = ! reverseMode;
	return reverseMode;
}


public final FileDescriptor getFD() throws IOException {
  return raf.getFD();
}
public final FileChannel getChannel() {
  return raf.getChannel();
}
public int read() throws IOException {
	return raf.read();
}
public int read(byte[] b, int off, int len) throws IOException {
	return raf.read(b,off,len);
}
public int read(byte[] b) throws IOException{
	return raf.read(b);
}
public final void readFully(byte[] b) throws IOException {
	 raf.readFully(b);
}
public final void readFully(byte[] b, int off, int len) throws IOException{
	 raf.readFully(b, off, len);
}
public int skipBytes(int n) throws IOException {
	return raf.skipBytes(n);
}
public void write(int b) throws IOException{  raf.write(b); }
public void write(byte[] b) throws IOException{  raf.write(b); }
public void write(byte[] b, int off, int len) throws IOException {  raf.write(b,off,len);}
public long getFilePointer() throws IOException {return raf.getFilePointer(); }
public void seek(long pos) throws IOException { raf.seek(pos);}
public long length() throws IOException {return raf.length();}
public void setLength(long newLength) throws IOException { raf.setLength(newLength);}
public void close() throws IOException { raf.close();}

public final boolean readBoolean() throws IOException {return raf.readBoolean();}
public final byte readByte() throws IOException {return raf.readByte(); }
public final int readUnsignedByte() throws IOException {return raf.readUnsignedByte(); }
public final char readChar() throws IOException {
	return raf.readChar();
}

       public final int readUnsignedShort() throws IOException {
                if(reverseMode) {
                 int byte1 = raf.read();
    int byte2 = raf.read();
    if (byte2 == -1  || byte2 == -1) throw new EOFException();
    return (byte2 << 8) + byte1;
  }

                else {
                        return raf.readUnsignedShort();
                }
         }

    public final short readShort() throws IOException {
                        if(reverseMode) {
                                return Short.reverseBytes(raf.readShort());
                        }
                        else {
                                return raf.readShort();
                        }
                 }

public final int readInt() throws IOException {
	if(reverseMode) {
		return Integer.reverseBytes(raf.readInt());
	}
	else {
		return raf.readInt();
	}
 }
public final long readLong() throws IOException {
	if(reverseMode) {
		return Long.reverseBytes(raf.readLong());
	}
	else {
		return raf.readLong();
	}
 }

public final float readFloat() throws IOException {		return raf.readFloat();}
public final double readDouble() throws IOException {			return raf.readDouble();}

public final String readLine() throws IOException {return raf.readLine(); }
public final String readUTF() throws IOException {return raf.readUTF();}

public final void writeBoolean(boolean v) throws IOException { raf.writeBoolean(v);}
public final void writeByte(int v) throws IOException	{ writeByte(v); }
public final void writeChar(int v) throws IOException { writeChar(v); }

public final void writeInt(int i) throws IOException{ if(reverseMode) {
  raf.write(i & 0xFF);
    raf.write((i >>> 8) & 0xFF);
    raf.write((i >>> 16) & 0xFF);
    raf.write((i >>> 24) & 0xFF);}
else 
 writeInt(i); }
public final void writeShort(int v) throws IOException { if(reverseMode) {
    raf.write(v & 0xFF);
    raf.write((v >>> 8) & 0xFF);} else
writeShort(v); 
}
public final void writeLong(long l) throws IOException{ if(reverseMode) {
 raf.write((int) l & 0xFF);
    raf.write((int) (l >>> 8) & 0xFF);
    raf.write((int) (l >>> 16) & 0xFF);
    raf.write((int) (l >>> 24) & 0xFF);
    raf.write((int) (l >>> 32) & 0xFF);
    raf.write((int) (l >>> 40) & 0xFF);
    raf.write((int) (l >>> 48) & 0xFF);
    raf.write((int) (l >>> 56) & 0xFF);} else
writeLong(l); }

public final void writeFloat(float v) throws IOException{ writeFloat(v); }
public final void writeDouble(double v) throws IOException{ writeDouble(v); }
public final void writeBytes(String s) throws IOException{ writeBytes(s); }
public final void writeChars(String s) throws IOException{ writeChars(s); }
public final void writeUTF(String str) throws IOException{ writeUTF(str); }
}
