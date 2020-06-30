package it.fgm.teamup.model;


import it.fgm.teamup.repository.PartecipazioneRepository;
import it.fgm.teamup.services.IProgettoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ruolo;
    private boolean partecipazione_confermata;


    @JoinColumn(name = "utente_id",
            referencedColumnName = "id")
    @ManyToOne
    Utente utente;

    @JoinColumn(name = "progetto_id",
            referencedColumnName = "id")
    @ManyToOne
    Progetto progetto;

    public Partecipazione(long id, String ruolo, boolean partecipazione_confermata, Utente utente, Progetto progetto) {
        this.id = id;
        this.ruolo = ruolo;
        this.partecipazione_confermata = partecipazione_confermata;
        this.utente = utente;
        this.progetto = progetto;
    }

    public Partecipazione() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public boolean getPartecipazione_confermata() {
        return partecipazione_confermata;
    }

    public void setPartecipazione_confermata(boolean partecipazione_confermata) {
        this.partecipazione_confermata = partecipazione_confermata;
    }

    public Utente getUtente() {
        return (Utente) utente;
    }

    public void setUtente(Utente utente) {
        this.utente =  utente;
    }

    public Progetto getProgetto() {
        return  progetto;
    }

    public void setProgetto(Progetto progetto) {
        this.progetto =  progetto;
    }


}
