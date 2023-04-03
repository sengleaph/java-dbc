import java.util.Scanner;

import model.Student;

public class Information {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Student student = new Student();

        int ch = 0;
        do{
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student By Id");
            System.out.println("3. Delete Student By Id");
            System.out.println("4. Select Student By Id");
            System.out.println("5. Select Student By Name");
            System.out.println("6. Display");
            System.out.println("7. Exit");
            System.out.print("Enter Menu : ");
            ch = input.nextInt();

            switch(ch){
                case 1 -> {
                    System.out.println("Insert Student");
                    System.out.println("--------------");
                    student.inputData();
                    student.insert();
                }
                case 2 -> {
                    System.out.println("Update Student By Id");
                    System.out.println("--------------");
                    student.update();
                }
                case 3 -> {
                    System.out.println("Delete Student By Id");
                    System.out.println("--------------");
                    student.delete();
                }
                case 4 -> {
                    System.out.println("Select Student By Id");
                    System.out.println("--------------");
                    student.selectById();
                }
                case 5 -> {
                    System.out.println("Select Student By Name");
                    System.out.println("--------------");
                    student.selectByName();
                }
                case 6 -> {
                    System.out.println("Display Student");
                    System.out.println("--------------");
                    student.display();
                }
                case 7 -> {}
            }
        }while(ch!=7);
        input.close();

    }
}
