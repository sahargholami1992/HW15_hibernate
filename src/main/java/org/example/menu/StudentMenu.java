package org.example.menu;



import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.service.CourseService;
import org.example.service.StudentCourseService;
import org.example.service.StudentService;
import org.example.utill.ApplicationContext;
import org.example.utill.SecurityContext;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class StudentMenu {

    StudentService studentService = ApplicationContext.getStudentService();
    CourseService courseService = ApplicationContext.getCourseService();
    StudentCourseService studentCourseService = ApplicationContext.getStudentCourseService();
    Scanner scanner = new Scanner(System.in);

    MainMenu menu = new MainMenu();

    String userName;
    String passWord;


    public StudentMenu(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        login();
    }

    public void login() {
        if (studentService.existByUserNameAndPassword(userName, passWord)) {
            System.out.println("SUCCESSFULLY LOGGED IN");
            Student student = studentService.findByUserName(userName);
            SecurityContext.fillContext(student);
            studentMenu();
        } else {
            System.out.println("INVALID USERNAME AND PASSWORD");
            menu.start();
        }
    }


    public void studentMenu() {
        // boolean
        while (true) {
            String text = """
                    ************************
                    1- SHOW INFO
                    2- SHOW LIST OF COURSES
                    3- CHOOSE COURSES
                    4- SHOW CHOSEN COURSES
                    5- BACK
                    ************************
                    """;
            System.out.println(text);
            int select = input();
            switch (select) {
                case 1:
                    showInfo();
                    break;

                case 2:
                    showListOfCourses();
                    break;

                case 3:
                    chooseCourses();
                    break;

                case 4:
                    showChosenCourses();
                    break;

                case 5:
                    menu.start();
                    break;

                default:
                    System.out.println("INVALID INPUT !!");
                    studentMenu();
                    break;
            }
        }
    }


    private void showInfo() {
        Integer currentUserId = SecurityContext.getCurrentUserId();
        Optional<Student> byId = studentService.findById(currentUserId);
        System.out.println(byId.orElse(null));
    }

    private void showListOfCourses() {
        courseService.findAll().forEach(System.out::println);
    }

    private void chooseCourses() {
        showListOfCourses();
        Student student = (Student) SecurityContext.getCurrentUser();
        System.out.println("PLEASE ENTER COURSE ID : ");
        int id = input();
        if (courseService.existsById(id)) {
            Optional<Course> byId = courseService.findById(id);
            if (studentService.chooseCourses(student, byId.orElse(null))) {
                System.out.println("ADDED SUCCESSFULLY");
            } else System.out.println("INVALID REQUEST !");
        } else {
            System.out.println("ENTER CORRECT ID");
            studentMenu();
        }
    }

    private void showChosenCourses() {
        Student student = (Student) SecurityContext.getCurrentUser();
        System.out.println(studentCourseService.findCourseByStudent(student));
    }

    public Integer input() {
        int i;
        while (true) {
            try {
                i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } catch (InputMismatchException in) {
                scanner.nextLine();
                System.out.println("PLEASE ENTER VALID NUMBER !");
            }
        }
    }
}