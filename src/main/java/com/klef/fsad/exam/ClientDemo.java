package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        // Insert
        Ticket t = new Ticket();
        t.setName("Issue 1");
        t.setDate(new Date());
        t.setStatus("Open");

        session.save(t);

        // Update using HQL (positional parameters)
        String hql = "update Ticket set name=?1, status=?2 where id=?3";

        Query<?> query = session.createQuery(hql);
        query.setParameter(1, "Updated Issue");
        query.setParameter(2, "Closed");
        query.setParameter(3, t.getId());

        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
        sf.close();

        System.out.println("Done!");
    }
}