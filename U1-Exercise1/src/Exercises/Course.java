package Exercises;

import java.awt.image.ByteLookupTable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Course {
    private List<Student> students;
    private int nrStudents; //Number of students in the course
    public List<String>studentList;	// List <String> of students in course

    public Course(int size){
        students=new ArrayList<Student>(size);
        nrStudents=size;
        
    }
    /**
     * Checks if the course has no students
     * @return true if empty
     */
    public boolean emptyCourse(){
        return students.isEmpty();
    }

    /**
     * Checks if the course is full
     * @return true if full
     */
    public boolean fullCourse(){
        return nrStudents==students.size();
    }
    /**
     * Adds a new student to the course (if not full)
     */
    public void add(Student student){
        if (!this.fullCourse()){
            students.add(student);
            nrStudents++;
        }
    }
    /**
     * Deletes a student from the course
     * @return true if student deleted, false if not
     */

    public boolean delete(Student student){
    	return students.remove(student);
        
    }
   
   
    /**
     * Prints all the students registered in the course
     */
    public void infoStudents(){
        System.out.println("The course has the following students:");
        for (int j=0;j<students.size();j++){
             System.out.println(students.get(j).toString()+" ");
         }
    }
    
    /**
     * Method to convert List<Students> to List<String>
     *
     */
    public List<String> createList(){
    	studentList=new ArrayList<String>(students.size());
    	for(int i=0; i<students.size() ;i++){
    		studentList.add(students.get(i).toString());
		}
    	return studentList;
    }
    
    /**
	 * Writes list of students of a given file to an output.txt
	 * using File.write()
	 * @param name
	 */
	public void writeStudentsFW(String name){
		
		Path output=Paths.get(name);
		studentList=createList();	// Convert students list to a string list
		
		try{
			// Write the list of students in output file
			Files.write(output,studentList);
			
		}

		// file doesn't exist
		catch (NoSuchFileException e){
			System.err.println(e);
		}
		// general IO exception
		catch (IOException e){
			System.err.println(e);
		}
	}
	
	/**
	 * Writes list of students of a given file to an output.txt
	 * using OutputStream.write()
	 * @param name
	 */
	// MAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void writeStudentsOS (String name){
				
		Path output=Paths.get(name);	
		OutputStream foutput=null;
		
		// List <String> 
		studentList=createList();
		byte[]listByte=new byte[studentList.size()];
		listByte=studentList.toString().getBytes(Charset.forName("UTF-8"));
		
		// --- PRUEBAS -----
		
		
//		List<Byte>listaByte=new ArrayList<Byte>(studentList.size());
//		
//		for (int i=0; i<studentList.size();i++){
//			listByte[i]=studentList.get(i).toString().getBytes();
//		}
		
//		byte[]arrayByte=new byte[studentList.size()];
//		for (int i=0; i<studentList.size();i++){
//			arrayByte[i]=studentList.get(i).getBytes(Charset.forName("UTF-8"));
//		}
//		
//		char []charArray=new char[studentList.size()];
//		for (int i=0; i<studentList.size();i++){
//			charArray[i]=studentList.toString().charAt(i);
//			arrayByte[i]=(byte) charArray[i];
//		}
	
		
		try{
					
			foutput=Files.newOutputStream(output);
//			
//			for (int i=0; i<studentList.size();i++){
//				byte[]byteLine=new byte[studentList.get(i).length()];
//				byteLine=studentList.get(i).getBytes();
//				foutput.write(byteLine);
//			}
			foutput.write(listByte);	// ESCRIBE EN UNA LÍNEA
			foutput.close();
			
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println(e);
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Writes  a given file to an output.txt
	 * using BufferedWriter.write()
	 * @param name
	 */
	
	public void writeStudentsBW (String name){
		
		Path output=Paths.get(name);	
		BufferedWriter bwriter=null;
		Charset cs;
		
		// Get list of strings
		studentList=createList();
		// Convert List<String> to String[]
		String[]listArray=new String[studentList.size()];
		for (int i=0; i<studentList.size();i++){
			listArray[i]=studentList.get(i);
		}
	
		try{
			cs = Charset.forName("UTF-8");
						
			bwriter=Files.newBufferedWriter(output, cs);
			
			for (int i = 0; i < listArray.length; i++) {
				bwriter.write(listArray[i], 0, listArray[i].length());
				// newline
				bwriter.newLine();
				
			}
		    bwriter.close();
			
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println(e);
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Writes  a given file to an output.txt
	 * using BufferedWriter.write() and append option
	 * @param name
	 */
	
	public void writeStudentsBWA (String name){
		
		Path output=Paths.get(name);	
		BufferedWriter bwriter=null;
		Charset cs;
		
		// Get list of strings
		studentList=createList();
		// Convert List<String> to String[]
		String[]listArray=new String[studentList.size()];
		for (int i=0; i<studentList.size();i++){
			listArray[i]=studentList.get(i);
		}
	
		try{
			cs = Charset.forName("UTF-8");
						
			bwriter=Files.newBufferedWriter(output, cs, StandardOpenOption.APPEND);
			
			for (int i = 0; i < listArray.length; i++) {
				bwriter.write(listArray[i], 0, listArray[i].length());
				// newline
				bwriter.newLine();
			}
		    bwriter.close();
				
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println(e);
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
	}
	
	
	/**
	 * Reads all lines from file and shows on screen,
	 * using realAllLines()
	 * @param name 
	 */
	public void readStudentsRAL(String name){
		
		Path input=Paths.get(name);
		
		try{
			studentList=Files.readAllLines(input);
			// go over the list and show
			for (String student : studentList) {
				System.out.println(student);
			}
			
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println("The file doesn't exist!");
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	/**
	 * Reads all bytes from file and shows on screen,
	 * using realAllBytes()
	 * @param name 
	 */
	public void readStudentsRAB(String name){
		
		Path input=Paths.get(name);
		byte[]studentList;
		String list;
		
		try{
			// save students in byte array
			studentList=Files.readAllBytes(input);
			// convert array to string and show
			list=new String(studentList);
			System.out.println(list);
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println("The file doesn't exist!");
		}
		catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Reads file and shows using inputStream
	 * @parm name
	 */
	public void readStudentsIS(String name){
		
		Path input=Paths.get(name);
		InputStream finput=null;
		int c;
		
		try{
			finput=Files.newInputStream(input);
			while((c=finput.read())!=-1){
				System.out.print((char)c);
			}
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println("The file doesn't exist!");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	/**
	 * Reads file with buffered reader and shows
	 * @param name
	 */
	public void readStudentsBR(String name){
		
		Path input=Paths.get(name);
		BufferedReader breader=null;
		String line;
		
		try{
			breader=Files.newBufferedReader(input);
			while((line=breader.readLine())!=null){
				System.out.println(line);
			}
		}
		// file doesn't exist
		catch (NoSuchFileException e) {
			System.err.println("The file doesn't exist!");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		}
		// general IO exception
		catch (IOException e) {
			System.err.println(e);
		}
	}
	
}
