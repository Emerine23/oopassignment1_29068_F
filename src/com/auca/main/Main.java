package com.auca.main;

import com.auca.abstracts.Person;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;

import com.auca.department.Department;
import com.auca.student.Student;
import com.auca.teacher.Teacher;
import com.auca.administrator.Administrator;
import com.auca.course.Course;
import com.auca.classroom.Classroom;
import com.auca.enrollment.Enrollment;
import com.auca.assessment.Assessment;
import com.auca.feepayment.FeePayment;
import com.auca.librarybook.LibraryBook;
import com.auca.bookloan.BookLoan;

import com.auca.exceptions.DuplicateRecordException;
import com.auca.exceptions.RecordNotFoundException;
import com.auca.exceptions.PrerequisiteMissingException;
import com.auca.exceptions.BookUnavailableException;
import com.auca.exceptions.ValidationException;

import java.io.FileOutputStream;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.math.BigDecimal;
import java.util.concurrent.Callable;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Department>departments = new ArrayList<>();
    private static final ArrayList<Student> students= new ArrayList<>();
    private static final ArrayList<Teacher> teachers= new ArrayList<>();
    private static final ArrayList<Administrator> administrators =new ArrayList<>();
    private static final ArrayList<Classroom>classrooms= new ArrayList<>();
    private static final ArrayList<Course>courses= new ArrayList<>();
    private static final ArrayList<Enrollment>enrollments= new ArrayList<>();
    private static final ArrayList<Assessment>assessments= new ArrayList<>();
    private static final ArrayList<FeePayment>payments= new ArrayList<>();
    private static final ArrayList<LibraryBook>books= new ArrayList<>();
    private static final ArrayList<BookLoan>bookLoans= new ArrayList<>();


    public static void main(String[] args){

        System.out.println("AUCA SCHOOL MANAGEMENT SYSTEM");

        showMenu();
    }

    public static void showMenu(){
        int choice;

        do{
            System.out.println("MAIN MENU");
            System.out.println("1.Register department");
            System.out.println("2.Register student");
            System.out.println("3.Register teacher");
            System.out.println("4.Register administrator");
            System.out.println("5.Register course");
            System.out.println("6.Registe classroom");
            System.out.println("7.Enroll student");
            System.out.println("8.Record assessment");
            System.out.println("9.Record Fee payment");
            System.out.println("10.Register library book");
            System.out.println("11.Borrow Library book");
            System.out.println("12.Return library book");
            System.out.println("13.View all Records");
            System.out.println("14.Search record by ID");
            System.out.println("15.Update Existing record");
            System.out.println("16.Student Academic summary");
            System.out.println("17.student payment history");
            System.out.println("18.View available books");
            System.out.println("19.Demostrate polymorphism");
            System.out.println("0.Exit");

            System.out.println("\nEnter your choice:");

            try {
                choice= Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:
                        registerDepartment();
                        break;
                    case 2:
                        registerStudent();
                        break;
                    case 3:
                        registerTeacher();
                        break;
                    case 4:
                        registerAdministrator();
                        break;
                    case 5:
                        registerCourse();
                        break;
                    case 6:
                        registerClassroom();
                        break;
                    case 7:
                        System.out.println("Enroll student");
                        break;
                    case 8:
                        recordAssessment();
                        break;
                    case 9:
                        recordFeePayment();
                        break;
                    case 10:
                        System.out.println("Register library book selected");
                        break;
                    case 11:
                        System.out.println("Borrow library book selected");
                        break;
                    case 12:
                        System.out.println("Return library book selected");
                        break;
                    case 13:
                        System.out.println("View all records selected");
                        break;
                    case 14:
                        System.out.println("Search record by ID selected");
                        break;
                    case 15:
                        System.out.println("Update existing records selected");
                        break;
                    case 16:
                        System.out.println("Student Academic summary selected");
                        break;
                    case 17:
                        System.out.println("Student payment History selected");
                        break;
                    case 18:
                        System.out.println("View available books selecetd");
                        break;
                    case 19:
                        demostratePolymorphism();
                        demonstrateInterfacePolymorphism();
                        break;
                    case 0:
                        System.out.println("Thank you for using AUCA school managemnet System");
                        break;
                    default:
                        System.out.println("Invalid choice. Please selected from 0 to 19");
                }
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
                choice= -1;
            }
        }while (choice != 0);
    }

    private static String readText(String message){
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static LocalDate readDate(String message){

        while (true){
            try {
                String date = readText(message);
                return LocalDate.parse(date);
            } catch (DateTimeParseException e){
                System.out.println("Invalid date. Use yyyy-MM-dd");
            }
        }
    }
    private static int readInt(String message){

        while (true){
            try {
                return Integer.parseInt(readText(message));
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid whole number");
            }
        }
    }
    private static double readDouble(String message){

        while (true){
            try {
                return Double.parseDouble(readText(message));
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean readYesNo(String message){

        while (true){
            String answer = readText(message);

            if (answer.equalsIgnoreCase("yes")){
                return true;
            }
            if (answer.equalsIgnoreCase("no")){
                return false;
            }
            System.out.println("Please enter yes or no");
        }
    }

    private static Department selectDepartment(){

        if (departments.isEmpty()){
            System.out.println("No department are registered yet");
            System.out.println("Please register a department");
            return null;
        }

        System.out.println("Available Departments");

        for(int i=0; i<departments.size(); i++){
            System.out.println((i+1) + "." + departments.get(i).getDepartmentName());
        }

        int choice = readInt("Select department number:");

        if (choice < 1 || choice> departments.size()){
            System.out.println("Invalid department selection");
            return null;
        }
        return departments.get(choice - 1);
    }

    private static void registerDepartment() {

        System.out.println("REGISTER DEPARTMENT");

        String id= readText("Enter deaprtment ID:");

        for (Department department : departments){

            if (department.getDepartmentId().equalsIgnoreCase(id)){
                System.out.println("This department ID arleady exists");
                return;
            }
        }

        String name= readText("Enter department name:");
        String code= readText("Enter department code:");
        String location= readText("Enter office location:");
        String email= readText("Enter contact email");

        Department department = new Department(id, name,code,location,email,null);

        try{
            department.validate();
            departments.add(department);

            System.out.println("Department registered successfully");
        } catch (ValidationException e){
            System.out.println("Registration failed:" + e.getMessage());
        }
    }

    private static void registerStudent(){

        System.out.println("REGISTER STUDENT");

        Department department= selectDepartment();

        if (department ==null){
            return;
        }

        String id= readText("Enter person ID:");

        for (Student student : students){
            if (student.getId().equalsIgnoreCase(id)){
                System.out.println("This student ID already exists.");
                return;
            }
        }

        String firstName= readText("Enter first name:");
        String lastName= readText("Enter last name:");
        String email= readText("Enter personal email:");
        String phone= readText("Enter phone number:" );
        LocalDate dob= readDate("Enter date of birth:");

        String memberNumber= readText("Enter member number:");
        LocalDate joinDate= readDate("Enter join date:");
        String academicStatus= readText("Enter academic status:");
        String institutionalEmail= readText("Enter institutional email:");
        String advisorName= readText("Enter advisor name:");

        String studentMember= readText("Enter student number:");
        String programNsme= readText("Enter program name:");
        LocalDate admissionDate= readDate("Enter admission date:");
        int studyYear= readInt("Enter study year(1-6)");
        String guardianPhone= readText("Enter guardian phone:");
        boolean schoraship= readYesNo("Does the student have a scholarship(yes/no):");

        Student student= new Student(id,firstName,lastName,email,phone,dob,memberNumber,department,joinDate,academicStatus,institutionalEmail,advisorName,studentMember,programNsme,admissionDate,studyYear,guardianPhone,schoraship);

        try {
            student.validate();
            students.add(student);

            System.out.println("Student registered successfully");
        } catch (ValidationException e){
            System.out.println("Registration failed:" + e.getMessage());
        }
    }
    private static void registerTeacher() {
        System.out.println(" REGISTER TEACHER");

        Department department = selectDepartment();

        if (department == null) {
            return;
        }

        String id = readText("Enter person ID:");

        for (Teacher teacher : teachers) {
            if (teacher.getId().equalsIgnoreCase(id)) {
                System.out.println("This teacher ID already exists");
                return;
            }
        }

        String firstName = readText("Enter first name: ");
        String lastName = readText("Enter last name: ");
        String email = readText("Enter personal email: ");
        String phone = readText("Enter phone number: ");
        LocalDate dob = readDate("Enter date of birth : ");

        String memberNumber = readText("Enter member number: ");
        LocalDate joinDate = readDate("Enter join date: ");
        String academicStatus = readText("Enter academic status (ACTIVE/INACTIVE): ");
        String institutionalEmail = readText("Enter institutional email: ");
        String advisorName = readText("Enter advisor name: ");

        String employeeNumber = readText("Enter employee number: ");
        String specialization = readText("Enter specialization: ");
        LocalDate hireDate = readDate("Enter hire date: ");

        BigDecimal salary;

        while (true) {

            try {
                salary = new BigDecimal(readText("Enter salary:"));
                break;
            } catch (NumberFormatException e){
                System.out.println("please enter a valid salary.");
            }

        }

        String officeNumber= readText("Enter office number");
        String employmentType= readText(" Enter employment type(FULL-TIME/PART-TIME/CONTRACT):");

        Teacher teacher= new Teacher(id,firstName,lastName,email,phone,dob,memberNumber,department,joinDate,academicStatus,institutionalEmail,advisorName,employeeNumber,specialization,hireDate,salary,officeNumber,employmentType);

        try {
            teacher.validate();
            teachers.add(teacher);

            System.out.println("Teacher registered successfully");
        } catch (ValidationException e){
            System.out.println("Registration failed:" +e.getMessage());
        }

    }

    private static void registerAdministrator(){

        System.out.println("REGISTER ADMINISTRATOR");

        String id= readText("Enter person ID:");

        for(Administrator administrator : administrators){
            if(administrator.getId().equalsIgnoreCase(id)){
                System.out.println("This administrator ID already exists.");
                return;
            }
        }


        String firstName = readText("Enter first name: ");
        String lastName = readText("Enter last name: ");
        String email = readText("Enter personal email: ");
        String phone = readText("Enter phone number: ");
        LocalDate dob = readDate("Enter date of birth (yyyy-MM-dd): ");

        String staffNumber = readText("Enter staff number: ");
        String jobTitle = readText("Enter job title: ");
        String assignedOffice = readText("Enter assigned office: ");
        LocalDate employmentDate = readDate("Enter employment date (yyyy-MM-dd): ");
        int accessLevel = readInt("Enter access level: ");
        boolean active = readYesNo("Is this administrator active? (yes/no): ");

        Administrator administrator= new Administrator(id,firstName,lastName,email,phone,dob,staffNumber,jobTitle,assignedOffice,employmentDate,accessLevel,active);

        try {
            administrator.validate();
            administrators.add(administrator);

            System.out.println("Administrator registered successfully");
        } catch (ValidationException e){
            System.out.println("Registration failed:" + e.getMessage());
        }
    }

    private static Teacher selectTeacher(){
        if (teachers.isEmpty()){
            System.out.println("No teachrs are registered yet.");
            System.out.println("Please register a teacher first");
            return null;
        }

        System.out.println("Available Teachers:");

        for (int i=0; i<teachers.size(); i++){
            System.out.println((i+1) + "." + teachers.get(i).getDisplayName());
        }
        int choice= readInt("Select teacher number:");

        if (choice <1 || choice>teachers.size()){
            System.out.println("Invalid teacher selection.");
            return null;
        }
        return teachers.get(choice - 1);
    }


    private static void registerCourse() {

        System.out.println(" REGISTER COURSE ");

        Department department = selectDepartment();

        if (department == null) {
            return;
        }

        Teacher teacher = selectTeacher();

        if (teacher == null) {
            return;
        }

        String id = readText("Enter course ID: ");

        for (Course course : courses) {
            if (course.getId().equalsIgnoreCase(id)) {
                System.out.println("This course ID already exists.");
                return;
            }
        }

        String code = readText("Enter course code: ");
        String title = readText("Enter course title: ");
        int creditHours = readInt("Enter credit hours (1-6): ");

        Course course = new Course(
                id, code, title, creditHours, department, teacher
        );

        try {
            course.validate();
            courses.add(course);

            System.out.println("Course registered successfully!");

        } catch (ValidationException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void registerClassroom(){
        System.out.println("REGISTER CLASSROOM");

        String id = readText("Enter classroom ID:");

        for (Classroom classroom: classrooms){
            if (classroom.getId().equalsIgnoreCase(id)){
                System.out.println("This classroom ID already exists");
                return;
            }
        }
        String building= readText("Enter building name:");
        String roomNumber= readText("Enter room number:");
        int capacity = readInt("Enter classroom capacity:");
        int floor= readInt("Enter floor number:");

        boolean avalaible= readYesNo("Is the classroom avalaible? (yes/no)");

        Classroom classroom= new Classroom(id, building,roomNumber,capacity,floor,avalaible);

        try {
            classroom.validate();
            classrooms.add(classroom);

            System.out.println("Classroom registered successfully");
        } catch (ValidationException e){
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static Student selectStudent(){
        if (students.isEmpty()){
            System.out.println("No students are registered yet.");
            return null;
        }
        System.out.println("Available Students:");

        for (int i=0; i< students.size(); i++){
            System.out.println((i+1)+ "."+students.get(i).getDisplayName());
        }
        int choice= readInt("Select student number:");

        if(choice<1 || choice> students.size()){
            System.out.println("Invalid student selection");
            return null;
        }
        return students.get(choice - 1);
    }
    private static Course selectCourse(){
        if(courses.isEmpty()){
            System.out.println("No courses are registered yet");
            return null;
        }
        System.out.println("Available Courses:");

        for (int i=0; i< courses.size();i++){
            System.out.println((i+1)+"."+ courses.get(i).getDisplayName());
        }

        int choice= readInt("selected course number:");

        if(choice<1 || choice>courses.size()){
            System.out.println("Invalid course selection.");
            return null;
        }

        return courses.get(choice-1);
    }


    private static Enrollment selectEnrollment() {

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments are available.");
            return null;
        }

        System.out.println("\nAvailable Enrollments:");

        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ". " +
                    enrollments.get(i).getRegistrationSummary());
        }

        int choice = readInt("Select enrollment number: ");

        if (choice < 1 || choice > enrollments.size()) {
            System.out.println("Invalid enrollment selection.");
            return null;
        }

        return enrollments.get(choice - 1);
    }


    private static void recordAssessment() {

        System.out.println("RECORD ASSESSMENT ");

        Enrollment enrollment = selectEnrollment();

        if (enrollment == null) {
            return;
        }

        if (!enrollment.isActive()) {
            System.out.println("Cannot record an assessment for an inactive enrollment.");
            return;
        }

        String id = readText("Enter assessment ID: ");

        for (Assessment assessment : assessments) {
            if (assessment.getId().equalsIgnoreCase(id)) {
                System.out.println("This assessment ID already exists.");
                return;
            }
        }

        String type = readText("Enter assessment type: ");
        double maximumScore = readDouble("Enter maximum score: ");
        double obtainedScore = readDouble("Enter obtained score: ");
        LocalDate date = readDate("Enter assessment date (yyyy-MM-dd): ");

        Assessment assessment = new Assessment(
                id, enrollment, type, maximumScore, obtainedScore, date
        );

        try {
            assessment.validate();
            assessments.add(assessment);

            System.out.println("Assessment recorded successfully!");

        } catch (ValidationException e) {
            System.out.println("Assessment failed: " + e.getMessage());
        }
    }


    private static void recordFeePayment() {

        System.out.println("RECORD FEE PAYMENT");

        Student student = selectStudent();

        if (student == null) {
            return;
        }

        String id = readText("Enter payment ID: ");

        for (FeePayment payment : payments) {
            if (payment.getId().equalsIgnoreCase(id)) {
                System.out.println("This payment ID already exists.");
                return;
            }
        }

        BigDecimal amount;

        while (true) {
            try {
                amount = new BigDecimal(readText("Enter payment amount: "));
                break;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }

        LocalDate date = readDate("Enter payment date (yyyy-MM-dd): ");
        String method = readText("Enter payment method: ");
        String receipt = readText("Enter receipt number: ");

        FeePayment payment = new FeePayment(
                id, student, amount, date, method, receipt
        );

        try {
            payment.validate();
            payments.add(payment);

            System.out.println("Fee payment recorded successfully!");

        } catch (ValidationException e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }


    public static void demostratePolymorphism(){

        ArrayList<Person>people= new ArrayList<>();

        people.addAll(students);
        people.addAll(teachers);
        people.addAll(administrators);

        System.out.println("POLYMORPHISM DEMOSTRATION");

        if(people.isEmpty()){
            System.out.println("No people have been registered yet");
            return;
        }

        for (Person person : people){
            System.out.println("Role:" + person.getRoleDescription());
            System.out.println("Name:" + person.getDisplayName());
            System.out.println("ID:" + person.getId());

            person.printDetails();
        }
    }

    public static void demonstrateInterfacePolymorphism() {

        ArrayList<Registrable> records = new ArrayList<>();

        // Add all registered records
        records.addAll(departments);
        records.addAll(students);
        records.addAll(teachers);
        records.addAll(administrators);
        records.addAll(courses);
        records.addAll(classrooms);
        records.addAll(enrollments);
        records.addAll(assessments);
        records.addAll(payments);
        records.addAll(books);
        records.addAll(bookLoans);

        System.out.println(" INTERFACE POLYMORPHISM DEMONSTRATION ");

        if (records.isEmpty()) {
            System.out.println("No records have been registered yet.");
            return;
        }

        for (Registrable record : records) {

            System.out.println(record.getRegistrationSummary());
        }
    }

}
