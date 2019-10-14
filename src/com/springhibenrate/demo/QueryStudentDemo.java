package com.springhibenrate.demo;

import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

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

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session
                    .createQuery("from Student").getResultList();
            displayStudents(students);

            // display students
            displayStudents(students);

            // query students: lastName='Doe'
            students = session
                    .createQuery("FROM Student s WHERE s.lastName='Doe'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe");
            displayStudents(students);

            // query students: lastName='Doe' or fistName='Daffy'
            students = session
                    .createQuery("FROM Student s WHERE s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(students);


            // query students: email like
            students = session
                    .createQuery("FROM Student s WHERE s.email LIKE '%yahoo.com'").getResultList();

            // display the studnets
            System.out.println("\n\nStudents who have email ends with 'yahoo.com'");
            displayStudents(students);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        // display the students
        for (Student student: students) {
            System.out.println(student);
        }
    }
}
