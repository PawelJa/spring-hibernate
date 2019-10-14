package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

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

            // update student
            System.out.println("Updating student...");
            retrievedStudent.setFirstName("Scooby");

            // commit the transaction
            session.getTransaction().commit();



            //

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");

            session.createQuery("UPDATE Student set email='foo@google.com'")
                    .executeUpdate();

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}
