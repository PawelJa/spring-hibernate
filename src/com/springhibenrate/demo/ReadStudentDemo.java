package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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

            // create student object
            System.out.println("Createing student object... ");
            Student student = new Student("Daffy", "Duck", "daffy@google.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving student... ");
            System.out.println(student);
            session.save(student);

            // commit the transaction
            session.getTransaction().commit();

            // find out the student's id: primary key
            System.out.println("Saved student, Generated id: " + student.getId());

            // now get a new session and start transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: priamry key
            System.out.println("\nGetting student with id: " + student.getId());
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + retrievedStudent);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}
