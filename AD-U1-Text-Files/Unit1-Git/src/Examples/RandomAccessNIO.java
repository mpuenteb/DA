package Examples;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/* This file writes a sentence at the beginning and at the end of a file. 
 * The overwritten characters will be added at the end of the file */

public class RandomAccessNIO {

	public static void main(String[] args) {
		String s = "I was here!\n";
		byte data[] = s.getBytes();
		// Writes the string to a buffer
		ByteBuffer out = ByteBuffer.wrap(data);

		// Creates a buffer for reading
		ByteBuffer copy = ByteBuffer.allocate(12);
		Path file = Paths.get("input3.txt");

		// Opens the file for reading and writing
		try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {
			// Reads the first 12 bytes of the file
			int nread;
			 //java.io is a non-blocking IO, so a while sentence is needed
			// When nread is -1 and there is not any more bytes in the buffer, it is sure that all 12 bytes have been read
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

			//Moves to the beginning of the file
			fc.position(0);

			// Writes the string (overwriting the first 12 bytes of the file)
			while (out.hasRemaining())
				fc.write(out);
			out.rewind();

			//Moves to the last byte of the file
			long length = fc.size();
			fc.position(length - 1);
			
			//Flips the buffer from read to write. It will set the limit of the buffer to the current position and reset the position to zero
			copy.flip();
			
			// Writes the overwritten bytes
			while (copy.hasRemaining())
				fc.write(copy);
			// Writes the string again
			while (out.hasRemaining())
				fc.write(out);
			// Closes the file
			fc.close();
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}

	}

}
