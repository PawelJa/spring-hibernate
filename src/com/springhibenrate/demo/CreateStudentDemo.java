package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // use the session object to save Java object

            // create a student object
            Student student = new Student("Paul", "Wall", "paul@google.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving student... ");
            session.save(student);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}
