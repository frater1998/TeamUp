package it.fgm.teamup.model;

import javax.persistence.*;
import java.util.Date;



 @Entity
public class Attivita {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


   private String obiettivi;

   private String percentualeCompletamento;

   Date dataInizio;
   Date dataFine;

     @JoinColumn(name = "progetto_id",
             referencedColumnName = "id")
   @ManyToOne
   private Progetto progetto;

     public Progetto getProgetto() {
         return progetto;
     }

     public void setProgetto(Progetto progetto) {
         this.progetto = progetto;
     }

     public Attivita(){};
     public Attivita( long id, String obiettivi, String percentualeCompletamento){

         this.id = id;
         this.obiettivi = obiettivi;
         this.percentualeCompletamento = percentualeCompletamento;
     };

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getObiettivi() { return obiettivi; }

    public void setObiettivi(String obiettivi) { this.obiettivi = obiettivi; }

    public String getPercentualeCompletamento() { return percentualeCompletamento; }

    public void setPercentualeCompletamento(String percentualeCompletamento) {
        this.percentualeCompletamento = percentualeCompletamento; }



}
