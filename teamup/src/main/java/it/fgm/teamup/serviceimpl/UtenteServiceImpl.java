package it.fgm.teamup.serviceimpl;



import it.fgm.teamup.exception.UtenteNonTrovato;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteServiceImpl  implements IUtenteService {



    @Autowired            //code injection!
    private IUtenteRepository utenteRepository;
    private IUtenteService utenteService;

    @Override
    @Transactional        //da utilizzare durante le transazioni con il db
    public Utente salva(Utente utente) {
        return utenteRepository.save(utente);
    }


    @Override
    public Utente findByEmail(String email) throws UtenteNonTrovato {

        return utenteService.findByEmail(email);
    }

    @Override

    public Utente findByPassword(String password) throws UtenteNonTrovato {
        return utenteService.findByPassword( password );

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

