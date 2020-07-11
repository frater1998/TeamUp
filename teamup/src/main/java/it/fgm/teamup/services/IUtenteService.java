package it.fgm.teamup.services;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

public interface IUtenteService {
    public Utente salva(String descrizione , int id);

    Progetto saveProg(String descr, int id);

    public void creaPartecipazioneL(Utente utente, Progetto progetto, Partecipazione partecipazione);
    public Utente findByEmailAndPassword(String email, String password);

    //public Utente loginUtente(Utente utente);




    public void aggPart(Partecipazione partecipazione, List<Partecipazione> part);
    //  public List<Utente> getAll();
//    public Utente findById(int id) ;
  //  public void deleteById(int id) ;
}
