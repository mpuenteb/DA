package Exercises;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner input=new Scanner(System.in);
		int option;
		String reader="students22.txt";
		String writer="output.txt";
		
		Course c=new Course(2);
		Student s1=new Student("Peter", "Johnson", 1990, "Victoria Road", 3);
		Student s2=new Student("James", "Gordon", 1992, "London Road", 3);
		Student s3=new Student("Claire", "Griffin", 1992, "Oxford Street", 15);
		c.add(s1);
		c.add(s2);
		c.add(s3);

		c.readStudentsIS(writer);
		
		/*
		showMenu();
		
		option=input.nextInt();
		
		switch(option){
		case 1:{
			c.writeStudentsFW(writer);
			c.readStudentsRAL(reader);
		}
		break;
		case 2:{
			c.writeStudentsFW(writer);
			c.readStudentsRAB(reader);
		}
		break;
		case 3:{
			c.writeStudentsOS(writer);
			c.readStudentsIS(reader);
		}
		break;
		case 4:{
			c.writeStudentsBW(writer);
			c.readStudentsBR(reader);
		}
		break;
		case 5:{
			c.writeStudentsBWA(writer);
			c.readStudentsBR(reader);
		}
		break;
			default: System.out.println("You must choose an option");
			break;
		}
		
		input.close();
		*/
	}
	
	// Method to show menu
	public static void showMenu(){
		
		System.out.println("Choose an option");
		System.out.println("1- Save students to a file and read the file using the method ReadAllLines \n"
				+ "2- Save students to a file and read the file using the method ReadAllBytes: \n"
				+ "3- Save students to a file and read the file using streams \n"
				+ "4- Save students to a file and read the file using buffers \n"
				+ "5- Save students to a file and read the file using buffers");
	}

}
