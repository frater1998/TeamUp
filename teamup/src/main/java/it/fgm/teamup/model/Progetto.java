package it.fgm.teamup.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Progetto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titolo;
    private String categoria;
    private String descrizione;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "progetto", cascade = CascadeType.ALL)
    List<Partecipazione> partecipazione;

    public List<Partecipazione> getPartecipazione() {
        return partecipazione;
    }

    public void setPartecipazione(List<Partecipazione> partecipazione) {
        this.partecipazione = partecipazione;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitolo() { return titolo; }

    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescrizione() { return descrizione; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public Progetto(){};

    public Progetto(int id, String titolo, String categoria, String descrizione) {
        this.id = id;
        this.titolo = titolo;
        this.categoria = categoria;
        this.descrizione = descrizione;
    }

}
