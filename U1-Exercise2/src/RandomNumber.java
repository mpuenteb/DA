import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class RandomNumber {

	public static void main(String[] args) {
		
		Scanner input=new Scanner(System.in);
		int number;
		
		try{
			
			RandomAccessFile raf=new RandomAccessFile("numbers.txt", "rw");
			
			System.out.print("Introduce a number: ");
			number=input.nextInt();
			
			// write the number converted to bytes
			raf.seek(0);
			raf.write(number);
			raf.seek(raf.length()-1);
			raf.write(number);
			
			// Call method that shows file content
			readFile();
		}
		
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}

	}
	
	public static void readFile() throws IOException{
		int num;
		
		try {
			RandomAccessFile raf2=new RandomAccessFile("numbers.txt", "rw");
			num=raf2.readInt();
			while(num!=-1){
				System.out.print(num);
				num=raf2.read();
				raf2.readInt();
			}

			raf2.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
