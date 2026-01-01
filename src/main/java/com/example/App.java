package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        createStudent(factory);
        readStudent(factory);
        updateStudent(factory);
        deleteStudent(factory);

        factory.close();
    }

    // CREATE
    public static void createStudent(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();

        Student s = new Student(13, "sudheer", 75);
        session.persist(s);

        session.getTransaction().commit();
        session.close();

        System.out.println("Student Created");
    }

    // READ
    public static void readStudent(SessionFactory factory) {
        Session session = factory.openSession();

        Student s = session.get(Student.class, 11);
        if (s != null) {
            System.out.println("Student Found:");
            System.out.println(
                    s.getId() + " " +
                    s.getName() + " " +
                    s.getMarks()
            );
        } else {
            System.out.println("Student Not Found");
        }

        session.close();
    }

    // UPDATE
    public static void updateStudent(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();

        Student s = session.get(Student.class, 11);
        if (s != null) {
            s.setMarks(98);
            s.setName("Sai Prasad Updated");
            session.merge(s);
        }

        session.getTransaction().commit();
        session.close();

        System.out.println("Student Updated");
    }

    // DELETE
    public static void deleteStudent(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();

        Student s = session.get(Student.class, 11);
        if (s != null) {
            session.remove(s);
        }

        session.getTransaction().commit();
        session.close();

        System.out.println("Student Deleted");
    }
}
