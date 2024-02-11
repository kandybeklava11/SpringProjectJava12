package java12.service.impl;

import jakarta.persistence.PersistenceContext;
import java12.entity.Lesson;
import java12.repo.GenericRepository;
import java12.repo.impl.LessonRepoImpl;
import java12.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonServiceImpl implements GenericService<Lesson,Long> {
    @PersistenceContext
    private GenericRepository<Lesson, Long> genericRepository=new LessonRepoImpl();
    @Override
    public Lesson save(Lesson entity) {
        return genericRepository.save(entity);
    }

    @Override
    public Lesson findById(Long aLong) {
        return genericRepository.findById(aLong) ;
    }

    @Override
    public List<Lesson> getAll() {
        return genericRepository.getAll();
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        return genericRepository.updateById(aLong,newEntity);
    }

    @Override
    public String deleteById(Long aLong) {
        genericRepository.deleteById(aLong);
        return "successfully deleted !";
    }

    @Override
    public Lesson assing(Long aLong, Long IdEntity) {
        return genericRepository.assing(aLong,IdEntity);
    }
}
