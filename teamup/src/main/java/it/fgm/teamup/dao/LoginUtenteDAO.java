package it.fgm.teamup.dao;
/*

import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginUtenteDAO {

    SessionFactory sessionFactory;

    @Autowired
    IUtenteRepository utenteRepository;
    @Autowired
    IUtenteService utenteService;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Utente loginUtente(Utente utente){

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "SELECT u FROM Utente u WHERE u.email = ? AND u.password = ?";

        try {
            Query query = session.createQuery( sql );
            query.setParameter( 0, utente.getEmail() );
            query.setParameter( 1, utente.getPassword() );
            utente  = (Utente) query.uniqueResult();
            tx.commit();
            session.close();

        } catch (Exception e){
            tx.rollback();session.close();
            e.printStackTrace();
        }

        return utente;




    }





 */


//}
