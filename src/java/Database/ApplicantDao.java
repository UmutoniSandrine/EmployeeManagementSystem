/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Domain.Applicant;
import Domain.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author bitwayiki
 */
public class ApplicantDao {

    private static Session session;

    public static Applicant createApplicant(Applicant applicant) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(applicant);
        session.getTransaction().commit();
        session.close();
        return applicant;
    }

    public static Applicant deleteApplicant(Applicant applicant) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(applicant);
        session.getTransaction().commit();
        session.close();
        return applicant;
    }

    public static Applicant updateApplicant(Applicant applicant) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(applicant);
        session.getTransaction().commit();
        session.close();
        return applicant;
    }

    public static List<Applicant> findAll() {
        session = NewHibernateUtil.getSessionFactory().openSession();
        List<Applicant> applicants = session.createQuery("From Applicant").list();
        session.close();
        return applicants;
    }

    public Applicant findByNationalId(String nationalId) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
//        Applicant applicant = (Applicant) s.get(Applicant.class, nationalId);
        
        Query query = s.createQuery("FROM Applicant A WHERE A.nationalId = :nid");
        query.setParameter("nid", nationalId);
        Applicant applicant = (Applicant) query.uniqueResult();
        s.close();
        return applicant;
    }

}
