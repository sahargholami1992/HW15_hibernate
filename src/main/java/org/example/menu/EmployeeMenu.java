package org.example.menu;


import org.example.entity.*;
import org.example.entity.enumeration.MidTerm;
import org.example.entity.enumeration.TeacherType;
import org.example.service.*;
import org.example.utill.ApplicationContext;
import org.example.utill.SecurityContext;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeMenu {
    Scanner scanner = new Scanner(System.in);

    EmployeeService employeeService = ApplicationContext.getEmployeeService();
    StudentService studentService = ApplicationContext.getStudentService();
    TeacherService teacherService = ApplicationContext.getTeacherService();
    CourseService courseService = ApplicationContext.getCourseService();
    LessonService lessonService = ApplicationContext.getLessonService();

    MainMenu menu = new MainMenu();

    String userName;
    String password;

    public EmployeeMenu(String userName, String password) {
        this.userName = userName;
        this.password = password;
        login();
    }

    public void login() {
        if (employeeService.existByUserNameAndPassword(userName, password)) {
            System.out.println("successfully logged in");
            Employee employee = employeeService.findByUserName(userName);
            SecurityContext.fillContext(employee);
            employeeServices();
        } else {
            System.out.println("invalid username and password");
            menu.start();
        }
    }

    public void employeeServices() {
        while (true) {
            String text = """
                    1- student crud
                    2- teacher crud
                    3- employee crud
                    4- course crud
                    5- show payslip
                    6- logout
                    """;
            System.out.println(" enter your select");
            System.out.println(text);
            int select = input();
            switch (select) {
                case 1:
                    studentCrud();
                    break;
                case 2:
                    teacherCrud();
                    break;
                case 3:
                    employeeCrud();
                    break;
                case 4:
                    courseCrud();
                    break;
                case 5:
                    showPaySlip();
                    break;
                case 6:
                    menu.start();
                    break;
                default:
                    System.out.println("invalid input");
                    employeeServices();
                    break;
            }
        }
    }


    private void studentCrud() {
        while (true) {
            switch (selectCrud()) {
                case 1: {
                    Student student = fillStudent();
                    studentService.saveOrUpdate(student);
                    System.out.println("student successfully registered");
                    break;

                }
                case 2: {
                    System.out.println("enter student id");
                    Integer id = input();
                    if (studentService.existsById(id)) {
                        Student student = fillStudent();
                        student.setId(id);
                        studentService.saveOrUpdate(student);
                        System.out.println("student successfully updated");
                    } else System.out.println("this id does not exist");
                    break;
                }
                case 3: {
                    System.out.println("enter student id");
                    Integer id = input();
                    if (studentService.existsById(id)) {
                        studentService.deleteById(id);
                        System.out.println("student successfully deleted");
                    } else System.out.println("this id does not exist !");
                    break;
                }
                case 4:
                    employeeServices();
                    break;
                default:
                    System.out.println("invalid input ! ");
                    studentCrud();
            }
        }
    }

    private Student fillStudent() {
        System.out.println(" enter first name : ");
        String firstName = scanner.next();
        System.out.println(" enter last name : ");
        String lastName = scanner.next();
        System.out.println(" enter username : ");
        String userName = scanner.next();
        System.out.println(" enter password : ");
        String password = scanner.next();
        System.out.println(" enter student number : ");
        String studentNumber = scanner.next();
        return new Student(firstName, lastName, userName, password, studentNumber);
    }

    private void teacherCrud() {
        while (true) {
            switch (selectCrud()) {
                case 1: {
                    Teacher teacher = fillTeacher();
                    teacherService.saveOrUpdate(teacher);
                    System.out.println("teacher successfully registered");
                    break;

                }
                case 2: {
                    System.out.println("enter teacher id");
                    Integer id = input();
                    if (teacherService.existsById(id)) {
                        Teacher teacher = fillTeacher();
                        teacher.setId(id);
                        teacherService.saveOrUpdate(teacher);
                        System.out.println("teacher successfully updated");
                    } else System.out.println("this id does not exist !");
                    break;
                }
                case 3: {
                    System.out.println("enter teacher id");
                    Integer id = input();
                    if (teacherService.existsById(id)) {
                        teacherService.deleteById(id);
                        System.out.println("teacher successfully deleted");
                    } else System.out.println("this id does not exist !");
                    break;
                }
                case 4:
                    employeeServices();
                    break;
                default:
                    System.out.println("invalid input ! ");
                    teacherCrud();
            }
        }
    }

    private Teacher fillTeacher() {
        System.out.println(" enter first name : ");
        String firstName = scanner.next();
        System.out.println(" enter last name : ");
        String lastName = scanner.next();
        System.out.println(" enter username : ");
        String userName = scanner.next();
        System.out.println(" enter password : ");
        String passWord = scanner.next();
        System.out.println(" enter teacher number : ");
        String teacherNumber = scanner.next();
        System.out.println(" choose teacher type");
        String text = """
                1- ACADEMIC_STAFF
                2- HOURLY_TEACHING
                """;
        System.out.println(text);
        int teacherType = input();
        TeacherType type = null;
        switch (teacherType) {
            case 1:
                type = TeacherType.ACADEMIC_STAFF;
                break;
            case 2:
                type = TeacherType.HOURLY_TEACHING;
                break;
            default:
                System.out.println("invalid input ! ");
                fillTeacher();
        }
        return new Teacher(firstName, lastName, userName, passWord, teacherNumber, type);
    }


    private void employeeCrud() {
        while (true) {
            switch (selectCrud()) {
                case 1: {
                    Employee employee = fillEmployee();
                    employeeService.saveOrUpdate(employee);
                    System.out.println("employee successfully registered");
                    break;

                }
                case 2: {
                    System.out.println("enter employee id");
                    Integer id = input();
                    if (employeeService.existsById(id)) {
                        Employee employee = fillEmployee();
                        employee.setId(id);
                        employeeService.saveOrUpdate(employee);
                        System.out.println("employee successfully updated");
                    } else System.out.println("this id does not exist !");
                    break;
                }
                case 3: {
                    System.out.println("enter  employee id");
                    Integer id = input();
                    if (employeeService.existsById(id)) {
                        employeeService.deleteById(id);
                        System.out.println("employee successfully deleted");
                    } else System.out.println("this id does not exist !");
                    break;
                }
                case 4:
                    employeeServices();
                    break;
                default:
                    System.out.println("invalid input ! ");
                    teacherCrud();
            }
        }
    }

    private Employee fillEmployee() {
        System.out.println(" enter first name : ");
        String firstName = scanner.next();
        System.out.println(" enter last name : ");
        String lastName = scanner.next();
        System.out.println(" enter username : ");
        String userName = scanner.next();
        System.out.println(" enter password : ");
        String passWord = scanner.next();
        System.out.println(" enter employee number : ");
        String employeeNumber = scanner.next();
        return new Employee(firstName, lastName, userName, passWord, employeeNumber);
    }

    private void courseCrud() {
        switch (selectCrud()) {
            case 1: {
                Course course = fillCourse();
                courseService.saveOrUpdate(course);
                System.out.println("course successfully registered");
                break;

            }
            case 2: {
                System.out.println(" enter course id");
                Integer id = input();
                if (courseService.existsById(id)) {
                    Course course = fillCourse();
                    course.setId(id);
                    courseService.saveOrUpdate(course);
                    System.out.println("course successfully updated");
                } else System.out.println("this id does not exist !");
                break;
            }
            case 3: {
                System.out.println("enter course id");
                Integer id = input();
                if (courseService.existsById(id)) {
                    courseService.deleteById(id);
                    System.out.println("course successfully deleted");
                } else System.out.println("This id does not exist !");
                break;
            }
            case 4:
                employeeServices();
                break;
            default:
                System.out.println("invalid input ! ");
                teacherCrud();
        }


    }

    private Course fillCourse() {

        System.out.println("select lesson :");

        Collection<Lesson> all = lessonService.findAll();

        all.forEach(System.out::println);
        Integer id = input();
        Optional<Lesson> lessonById = lessonService.findById(id);

        System.out.println("choose teacher :");
        Collection<Teacher> allTeacher = teacherService.findAll();
        allTeacher.forEach(System.out::println);
        Integer idTeacher = input();
        Optional<Teacher> teacherById = teacherService.findById(idTeacher);

        System.out.println(" enter term year :");
        Integer year = input();
        System.out.println(" select MIDTERM :");
        String text = """
                1- FIRST
                2- SECOND
                """;
        System.out.println(text);
        int midTerm = input();
        MidTerm type = null;
        switch (midTerm) {
            case 1:
                type = MidTerm.FIRST;
                break;
            case 2:
                type = MidTerm.SECOND;
                break;
            default:
                System.out.println("invalid input ! ");
                courseCrud();
        }
        return new Course(lessonById.orElse(null), teacherById.orElse(null), new Term(year, type));
    }

    private void showPaySlip() {
        System.out.println(employeeService.showPaySlip());
    }

    public int selectCrud() {
        String text = """
                
                1- add
                2- update
                3- delete
                4- back
                
                """;
        System.out.println(text);
        return input();
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
                System.out.println(" enter valid number !");
            }
        }
    }

}
