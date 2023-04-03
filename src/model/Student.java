package JDBC.src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private Float score;
    Boolean passed;

    //load connection


    //insert student to students table
    public void insert(){
        
        String sql = "INSERT INTO STUDENTS(id, name, gender, age, score, passed) VALUES(?,?,?,?,?,?);";
        
        try(
            Connection connection = dataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, getId());
            preparedStatement.setString(2, getName());
            preparedStatement.setString(3, getGender());
            preparedStatement.setInt(4, getAge());
            preparedStatement.setFloat(5, getScore());
            preparedStatement.setBoolean(6, getPassed());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    private void dataSource() {
        return ;
    }
    private void Connection(){
        return;
    }

    //delete student by id in students table
    public void delete(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Id To Delete : ");
        int id = cin.nextInt();

        List<Student> sts = getAllStudents();
        long n = sts.stream().filter(s -> s.getId().equals(id)).count();

        if (n>=1){
            String sql = "DELETE FROM STUDENTS WHERE id = ?";
            try (
                Connection connection = dataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Success Delete");
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }else{
            System.out.println("Not Found This Id!!!");
        }
        
    }

    //update student in students table
    public void update(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Id To Update : ");
        int id = cin.nextInt();

        List<Student> sts = getAllStudents();
        long n = sts.stream().filter(s -> s.getId().equals(id)).count();

        if (n>=1){
            inputDataUpdate();
            String sql = "UPDATE STUDENTS SET name=?, gender=?, age=?, score=?, passed=? WHERE id = ?";
            try (
                Connection connection = dataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {

                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getGender());
                preparedStatement.setInt(3, getAge());
                preparedStatement.setFloat(4, getScore());
                preparedStatement.setBoolean(5, getPassed());
                preparedStatement.setInt(6, id);

                preparedStatement.executeUpdate();
                System.out.println("Success Updated");

            } catch (SQLException e) {
                e.getStackTrace();
            }
        }else{
            System.out.println("Not Found This Id!!!");
        }
    }

    //get all students in students table
    public List<Student> getAllStudents(){

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS ORDER BY id;";

        try(
            Connection connection = dataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while(resultSet.next()){
                setId(resultSet.getInt(1));
                setName(resultSet.getString(2));
                setGender(resultSet.getString(3));
                setAge(resultSet.getInt(4));
                setScore(resultSet.getFloat(5));
                setPassed(resultSet.getBoolean(6));
                students.add(new Student(getId(), getName(), getGender(), getAge(), getScore(), getPassed()));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return students;
    }

    //select student by name
    public void selectByName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Student Name : ");
        String name = input.nextLine();
        List<Student> students = getAllStudents();
        long n = students.stream().filter(s -> s.getName().contains(name)).count();
        if (n >= 1){
            System.out.println("------------------------");
            students.stream().filter(s -> s.getName().contains(name)).forEach(s -> System.out.println(s));
            System.out.println("------------------------");
        }else{
            System.out.println("Not Found This Name!!!");
        }
    }

    //select student by id
    public void selectById(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Student Id : ");
        int id = input.nextInt();
        List<Student> students = getAllStudents();
        long n = students.stream().filter(s -> s.getId().equals(id)).count();
        if (n >= 1){
            System.out.println("------------------------");
            students.stream().filter(s -> s.getId().equals(id)).forEach(s -> System.out.println(s));
            System.out.println("------------------------");
        }else{
            System.out.println("Not Found This Id!!!");
        }
    }
    
    //display
    public void display(){
        System.out.println("------------------------");
        List<Student> st = getAllStudents();
        st.stream().forEach(s -> System.out.println(s));
        System.out.println("------------------------");
    }

    //input student information
    public void inputData(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter ID : ");
        setId(input.nextInt());
        System.out.print("Enter Name : ");
        input.nextLine();
        setName(input.nextLine());
        System.out.print("Enter Gender : ");
        setGender(input.nextLine());
        System.out.print("Enter Age  : ");
        setAge(input.nextInt());
        System.out.print("Enter Score : ");
        setScore(input.nextFloat());
        setPassed(getPassed());
    }
    
    //input student update information
    public void inputDataUpdate(){
        Scanner input = new Scanner(System.in);
        setId(getId());
        System.out.print("Enter Name : ");
        setName(input.nextLine());
        System.out.print("Enter Gender : ");
        setGender(input.nextLine());
        int age = (int) validate("Enter Age  : ");setAge(age);
        float sc = validate("Enter Score : ");setScore(sc);
        setPassed(getPassed());
    }

    //validate input
    public static float validate(String txt){
        Scanner cin = new Scanner(System.in);
        boolean isTrue = false;
        float number =0;
        String num;

        do{
            try {
                System.out.print(txt);
                num = cin.nextLine();
                number = Float.parseFloat(num);
                isTrue = true;
            } catch (Exception e) {
                System.out.println("Please Input Number!!!");
                isTrue = false;
            }
        }while(!isTrue);

        return number;
    }

    public Student() {
    }

    public Student(Integer id, String name, String gender, Integer age, Float score, Boolean passed) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.score = score;
        this.passed = passed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Boolean getPassed() {
        Boolean isPassed = false;
        if (getScore() >= 50){
            isPassed = true;
        }else{
            isPassed = false;
        }
        return isPassed;
    }

    public void setPassed(Boolean passed) {
       this.passed = passed;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", score=" + score
                + ", passed=" + passed + "]";
    }
    
}
