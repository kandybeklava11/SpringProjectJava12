package java12;

import jakarta.persistence.PersistenceContext;
import java12.entity.Course;
import java12.entity.Lesson;
import java12.entity.Student;
import java12.repo.impl.StudentRepoImpl;
import java12.service.GenericService;
import java12.service.impl.CourseServiceImpl;
import java12.service.impl.LessonServiceImpl;
import java12.service.impl.StudentServiceImpl;


import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        StudentServiceImpl studentService = new StudentServiceImpl();
        LessonServiceImpl lessonService=new LessonServiceImpl();
        CourseServiceImpl courseService=new CourseServiceImpl();

//         Student
//        Student newStudent = new Student("Иван", "ivan@example.com", 1990);
//        studentService.save(newStudent);
        //System.out.println(studentService.findById(1L));
        //System.out.println(studentService.getAll());
        //System.out.println(studentService.updateById(1L,new Student("Beka","Bekich@gmail.com",17)));
        //System.out.println(studentService.deleteById(2L));


//         Lesson
        //System.out.println(lessonService.save(new Lesson("Spring","Spring core","dddddd/dddd/ddddd",LocalDate.of(2024,2,8),true)));
        //System.out.println(lessonService.findById(2L));
        //System.out.println(lessonService.getAll());
        //System.out.println(lessonService.updateById(2L,new Lesson("Hibernate","Hibernate relationship","sssssss/sssssssss/sssssssss",LocalDate.of(2024,1,25),false)));
        //System.out.println(lessonService.deleteById(2L));
        //System.out.println(lessonService.assing(1L,1L));


        //Course
        //System.out.println(courseService.save(new Course("PeakSoft",14000,LocalDate.of(2021,6,14))));
        //System.out.println(courseService.getAll());
        //System.out.println(courseService.updateById(2L,new Course("Codewice",500,LocalDate.of(2020,3,12))));
        //System.out.println(courseService.deleteById(2L));
        //System.out.println(courseService.assing(1L,1L));
        //System.out.println(courseService.findById(1L));

    }
}
