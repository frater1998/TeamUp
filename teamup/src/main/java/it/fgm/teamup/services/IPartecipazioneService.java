package it.fgm.teamup.services;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.Query;

public interface IPartecipazioneService {


    public Partecipazione salva(Partecipazione partecipazione);

    Partecipazione findByUtenteId(int id);

  // public void creaPartecipazioneL(Utente utente, Progetto progetto, Partecipazione partecipazione);

    //public Partecipazione impostaPartecipazione(Utente utente, Progetto progetto, Partecipazione partecipazione);

}
