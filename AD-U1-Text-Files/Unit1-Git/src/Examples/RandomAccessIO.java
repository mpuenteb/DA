package Examples;

import java.io.IOException;
import java.io.RandomAccessFile;

/* This file writes a sentence at the beginning and at the end of a file. 
 * The overwritten characters will be added at the end of the file */

public class RandomAccessIO {

	public static void main(String[] args) {
		try {
			String s = "I was here!\n";
			byte data[]=new byte[12];
            RandomAccessFile fileStore = new RandomAccessFile("input2.txt", "rw");
            //Reads the first 12 bytes from the file. 
            fileStore.read(data);
            //java.io is a blocking IO, so at this point all 12 chars are already read
            //Moves to the beginning of the file
            fileStore.seek(0);

            // Writes the string (overwriting the first 12 bytes of the file)
            fileStore.write(s.getBytes());
            long length = fileStore.length();
            //Moves to the last byte of the file
			fileStore.seek(length-1);
			// Writes the overwritten bytes
			fileStore.write(data);
			// Writes the string again
			fileStore.write(s.getBytes());
			//Closes the file
            fileStore.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
