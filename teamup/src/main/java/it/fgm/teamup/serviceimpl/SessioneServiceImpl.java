package it.fgm.teamup.serviceimpl;

import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.ISessioneRepository;
import it.fgm.teamup.services.ISessioneService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class SessioneServiceImpl  implements ISessioneService {

    @Autowired
    ISessioneRepository sessioneRepository;


    @Override
    public Sessione save( Sessione sessione) {
        return sessioneRepository.save( sessione );
    }
}
