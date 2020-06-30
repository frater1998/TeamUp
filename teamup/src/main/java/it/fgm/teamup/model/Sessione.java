package it.fgm.teamup.model;


import javax.persistence.*;

@Entity
public class Sessione {

    @Id
    private String id;



    @OneToOne
    private Utente utente;

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Sessione(String id, Utente utente) {
        this.id = id;
        this.utente = utente;
    }


    public Sessione() {
    }
}
