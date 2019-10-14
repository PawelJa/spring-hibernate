package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();


        try {
            int studentId = 1;

            // now get a new session and start transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: priamry key
            System.out.println("\nGetting student with id: " + studentId);
            Student retrievedStudent = session.get(Student.class, studentId);

            // delete the student
//            System.out.println("Deleting student: " + retrievedStudent);
//            session.delete(retrievedStudent);

            // delete student id=2
            System.out.println("Delete student where id=2");
            session.createQuery("DELETE FROM Student WHERE id=2").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}
