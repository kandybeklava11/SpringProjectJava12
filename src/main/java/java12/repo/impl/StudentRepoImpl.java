package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java12.config.HibernateConfig;
import java12.entity.Course;
import java12.entity.Student;
import java12.repo.GenericRepository;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;
@Repository
public class StudentRepoImpl implements GenericRepository<Student,Long>  {

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();
    @Override
    public Student save(Student entity) {
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
    public Student findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, aLong);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Student> students ;
        try {
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("select s from Student s", Student.class).getResultList();
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
        return students;
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, aLong);
            student.setName(newEntity.getName());
            student.setEmail(newEntity.getEmail());
            student.setYearOfBirth(newEntity.getYearOfBirth());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return  student;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student ;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, aLong);
            entityManager.remove(student);
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
    public Student assing(Long aLong, Long IdEntity) {
     return null;
    }
}
