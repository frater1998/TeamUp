package it.fgm.teamup.serviceimpl;


import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UtenteServiceImpl  implements IUtenteService {


    @Autowired            //code injection!
    private IUtenteRepository utenteRepository;
    private IUtenteService utenteService;

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional        //da utilizzare durante le transazioni con il db
    public Utente salva(Utente utente) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        if (utente != null){
            try {


                session.save( utente );
                tx.commit();
                session.close();
            } catch (Exception e){
                tx.rollback();
                session.close();
                e.printStackTrace();
            }
        }

        return utenteRepository.save(utente);
    }


    public void creaPartecipazioneL(Utente utente, Progetto progetto, Partecipazione partecipazione) {


        partecipazione.setUtente( utente );

        partecipazione.setProgetto( progetto );

        partecipazione.setRuolo( "LEADER" );
        partecipazione.setPartecipazione_confermata( true );

        System.out.println(partecipazione.getRuolo());
        System.out.println(partecipazione.getPartecipazione_confermata());
        System.out.println( partecipazione.getUtente());
        System.out.println( partecipazione.getProgetto().getTitolo());


    }


    @Override
    public Utente findByEmailAndPassword(String email, String password) {


        return utenteRepository.findByEmailAndPassword( email,password );
    }
/*
    @Override
    public Utente loginUtente(Utente utente) {
        return loginUtenteDAO.loginUtente( utente );
    }
*/

    public void aggPart( Partecipazione partecipazione, List<Partecipazione> part){

        part.add( partecipazione );

    }


}



/*
    @Override
    @Transactional
    public List<Utente> getAll() {
        return utenteRepository.findAll();
    }
    @Override

    @Transactional
    public Utente findById(int id){


        id = 1;
		Utente user = utenteRepository.getOne(id);
		if (user != null) {
			return user;
		} else {

		    return null;

		}


        // Metodo DA FIGHI usando le arrow function
      // return utenteRepository.findById( (int) id );
    }

    @Override
    public void deleteById(int id) {

    }
/*
    @Override
    @Transactional
    public void deleteById(int id)  {

        /*
         *  Non è corretto annidare tra loro funzioni transazionali, piuttosto conviene replicare il codice come in questo caso:
         *  Trovo l'utente con la arrow function usata in findById e rimuovo l'utente designato tramite userRepo
         *
        Utente user = IUtenteRepository.getOne( id );
        user = IUtenteRepository.findById( id );
        IUtenteRepository.delete(user);
    }
    */

