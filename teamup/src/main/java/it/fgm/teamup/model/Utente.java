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

    private String username;

    @OneToOne
    private Sessione session;

    public Sessione getSession() {
        return session;
    }

    public void setSession(Sessione session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Utente(String nome, String cognome, int id, Date dataNascita, String email,
                  String password, String username, List<Partecipazione> partecipazione)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.id = id;
        this.username = username;
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
