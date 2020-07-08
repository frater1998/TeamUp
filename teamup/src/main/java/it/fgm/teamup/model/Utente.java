package it.fgm.teamup.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Utente  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;


    @Column(unique=true, name = "email")
    private String email;
    private String password;

    private String descrizione;

    @OneToOne
    private Sessione session;

    public Sessione getSession() {
        return session;
    }

    public void setSession(Sessione session) {
        this.session = session;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String username) {
        this.descrizione = username;
    }

    public List<Partecipazione> getPartecipazione() {
        return partecipazione;
    }

    public void setPartecipazione(List<Partecipazione> partecipazione) {
        this.partecipazione = partecipazione;
    }

    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Partecipazione> partecipazione;


    public Utente getUtente() {

        return this;
    }


    public Utente(){};
    public Utente( String email, String password){

        this.email = email;
        this.password = password;
    };

    public Utente(String nome, String cognome, int id, String email, String descrizione,
                  String password, String username, List<Partecipazione> partecipazione)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.id = id;
        this.descrizione = descrizione;
        this.partecipazione = partecipazione;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
