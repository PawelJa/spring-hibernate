package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

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

            // create 3 student objects
            Student student1 = new Student("John", "Doe", "john@google.com");
            Student student2 = new Student("Marry", "Public", "marry@google.com");
            Student student3 = new Student("Bonita", "Applebum", "bonita@google.com");

            // start a transaction
            session.beginTransaction();

            // save the student objects
            System.out.println("Saving student... ");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}
