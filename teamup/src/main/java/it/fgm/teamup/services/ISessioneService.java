package it.fgm.teamup.services;

import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import org.hibernate.Session;

public interface ISessioneService {

    public Sessione save (Sessione sessione);
}
