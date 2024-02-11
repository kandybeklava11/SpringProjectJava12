package java12.service.impl;

import jakarta.persistence.PersistenceContext;
import java12.entity.Course;
import java12.entity.Lesson;
import java12.repo.GenericRepository;
import java12.repo.impl.CourseRepoImpl;
import java12.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements GenericService<Course,Long> {
    @PersistenceContext
    private GenericRepository<Course, Long> genericRepository=new CourseRepoImpl();
    @Override
    public Course save(Course entity) {
        return genericRepository.save(entity);
    }

    @Override
    public Course findById(Long aLong) {
        return genericRepository.findById(aLong);
    }

    @Override
    public List<Course> getAll() {
        return genericRepository.getAll();
    }

    @Override
    public Course updateById(Long aLong, Course newEntity) {
        return genericRepository.updateById(aLong,newEntity);
    }

    @Override
    public String deleteById(Long aLong) {
    genericRepository.deleteById(aLong);
        return "successfully deleted !";
    }

    @Override
    public Course assing(Long aLong, Long IdEntity) {
        return genericRepository.assing(aLong,IdEntity);
    }
}
