package java12.service.impl;

import jakarta.persistence.PersistenceContext;
import java12.entity.Student;
import java12.repo.GenericRepository;
import java12.repo.impl.StudentRepoImpl;
import java12.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements GenericService<Student,Long> {
    @PersistenceContext
    private GenericRepository<Student, Long> genericRepository=new StudentRepoImpl();
    @Override
    public Student save(Student entity) {
        return genericRepository.save(entity);
    }

    @Override
    public Student findById(Long aLong) {
        return genericRepository.findById(aLong);
    }

    @Override
    public List<Student> getAll() {
        return genericRepository.getAll();
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        return genericRepository.updateById(aLong,newEntity);
    }

    @Override
    public String deleteById(Long aLong) {
    genericRepository.deleteById(aLong);
        return "successfully deleted !";
    }

    @Override
    public Student assing(Long aLong, Long IdEntity) {
        return genericRepository.assing(aLong,IdEntity);
    }
}
