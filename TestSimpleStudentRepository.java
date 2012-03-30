package studentmanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleStudentRepository {
    SimpleStudentRepository repo;
    Student student;

    @Before
    public void setUp(){
        repo=new SimpleStudentRepository();
        student=new Student(12236, "John","Nash","Princetown,New Jersey", 3, 4.2);
        repo.saveStudent(student);
    }

    @Test
    public void testSaveAndFind(){
        Student temp=repo.findStudent(12236);
        Assert.assertEquals("Test save and find methods",student,temp);
    }

    @Test
    public void testDelete(){
       repo.deleteStudent(12236);
       Student temp=repo.findStudent(12236);
       Assert.assertEquals("Test delete method",null,temp);
    }

    @Test
    public void testUpdate(){
       Student stud=new Student(12236, "John","Nash","New York", 3, 4.2);
       repo.upadateStudent(stud);       

       Student temp=repo.findStudent(12236);
       Assert.assertEquals("Test update method","New York",temp.getAddress());
    }
}
