package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java12.config.HibernateConfig;
import java12.entity.Course;
import java12.entity.Student;
import java12.repo.GenericRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CourseRepoImpl implements GenericRepository<Course, Long> {
    @PersistenceContext
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();

    @Override
    public Course save(Course entity) {
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
    public Course findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class, aLong);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return course;
    }

    @Override
    public List<Course> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Course> courses;
        try {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
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
        return courses;
    }


    @Override
    public Course updateById(Long aLong, Course newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class, aLong);
            course.setName(newEntity.getName());
            course.setPrice(newEntity.getPrice());
            course.setDateOfStart(newEntity.getDateOfStart());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return course;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class, aLong);
            entityManager.remove(course);
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
    public Course assing(Long aLong, Long IdEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;

        try {
            entityManager.getTransaction().begin();

            Student student = entityManager.find(Student.class, IdEntity);
            course = entityManager.find(Course.class, aLong);
            course.getStudents().add(student);
            student.setCourse(course);
            entityManager.persist(student);
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

        return course;
    }
}
