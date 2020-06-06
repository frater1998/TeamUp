package it.fgm.teamup.model;


import javax.persistence.*;

@Entity
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ruolo;
    private boolean partecipazione_confermata;

    @ManyToOne(optional = false)
    Utente utente;

    @ManyToOne(optional = false)
    Progetto progetto;
}
