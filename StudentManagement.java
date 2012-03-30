package studentmanagement;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentManagement {
    public static void main(String[] args) {
        
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Spring-context.xml");
        context.start();
        StudentManagementSystem sms=(StudentManagementSystem)context.getBean("sms");
        sms.registerStudent((Student)context.getBean("student1"));
        sms.listAllStudents();        
        
        sms.registerStudent((Student)context.getBean("student2"));
        sms.registerStudent((Student)context.getBean("student3"));
        sms.listAllStudents();
    }

}

class Student {
    private String firstName,lastName,address;
    private int index,level;
    private double gpa;
    
    public Student(){        
    }
    
    public Student(int index,String first,String last,String addr,int level,double gpa){
        this.index=index;
        this.firstName=first;
        this.lastName=last;
        this.address=addr;
        this.level=level;
        this.gpa=gpa;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}

interface StudentRepository {

    public void saveStudent(Student st);

    public Student findStudent(int index);

    public void upadateStudent(Student st);

    public void deleteStudent(int index);

    public List<Student> findAll();

}

class SimpleStudentRepository implements StudentRepository{

    private HashMap<Integer,Student> students;

    public SimpleStudentRepository(){
        students=new HashMap<Integer, Student>();
    }

    public void saveStudent(Student st) {
        students.put(st.getIndex(), st);
    }

    public Student findStudent(int index) {
        return  students.get(index);
    }

    public void upadateStudent(Student st) {
        deleteStudent(st.getIndex());
        saveStudent(st);
    }

    public void deleteStudent(int index) {
        if(findStudent(index)!=null){
            students.remove(index);
        }
        else{
            System.out.println("Student not found!");
        }
    }

    public List<Student> findAll() {        
        List<Student> temp=new ArrayList<Student>(students.values()); 
        return temp;
    }

}

class StudentManagementSystem {

    private SimpleStudentRepository repo;
    
    public StudentManagementSystem(){
        repo=new SimpleStudentRepository();
    }

    public StudentManagementSystem(SimpleStudentRepository r){
        repo=r;
    }

    public void listAllStudents(){
        List<Student> temp=repo.findAll();

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Student Details");
        System.out.println("---------------------------------------------------------------------------------");
        
        for(int i=0;i<temp.size();i++){
            System.out.print(temp.get(i).getIndex());
            System.out.print("\t"+temp.get(i).getFirstName());
            System.out.print(" "+temp.get(i).getLastName());
            System.out.print("\t"+temp.get(i).getAddress());
            System.out.print("\t\tLevel:"+temp.get(i).getLevel());
            System.out.println("\t\tGPA:"+temp.get(i).getGpa());
        }
        
        System.out.println("---------------------------------------------------------------------------------");
    }
    public void registerStudent(Student st){
	repo.saveStudent(st);
    }
}


