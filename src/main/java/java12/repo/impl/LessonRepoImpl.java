package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java12.config.HibernateConfig;
import java12.entity.Course;
import java12.entity.Lesson;
import java12.entity.Student;
import java12.repo.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class LessonRepoImpl implements GenericRepository <Lesson,Long>{
    @PersistenceContext
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();
    @Override
    public Lesson save(Lesson entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public Lesson findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class, aLong);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return lesson;
    }

    @Override
    public List<Lesson> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Lesson> lessons ;
        try {
            entityManager.getTransaction().begin();
            lessons = entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return Collections.emptyList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return lessons;
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class, aLong);
            lesson.setDescription(newEntity.getDescription());
            lesson.setTitle(newEntity.getTitle());
            lesson.setVideoLink(newEntity.getVideoLink());
            lesson.setPublishedDate(newEntity.getPublishedDate());
            lesson.setPresentation(newEntity.isPresentation());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return  lesson;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson ;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class, aLong);
            entityManager.remove(lesson);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Lesson assing(Long aLong, Long IdEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson = null;

        try {
            entityManager.getTransaction().begin();

             lesson = entityManager.find(Lesson.class, IdEntity);
            Course course = entityManager.find(Course.class, aLong);
            course.getLessons().add(lesson);
            entityManager.persist(lesson);
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error assigning student to course: " + e.getMessage());
        } finally {
            entityManager.close();
        }

        return lesson;
    }
}
