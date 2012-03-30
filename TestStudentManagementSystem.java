package studentmanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStudentManagementSystem {
    StudentManagementSystem sms;
    SimpleStudentRepository repo;
    Student student;

    @Before
    public void setUp(){
       repo=new SimpleStudentRepository();
       sms=new StudentManagementSystem(repo);
       student=new Student(12236, "John","Nash","Princetown,New Jersey", 3, 4.2);

       sms.registerStudent(student);
    }

    @Test
    public void testRegister(){
        Student temp=repo.findStudent(12236);
        Assert.assertEquals("Test register method",student,temp);
    }
}
