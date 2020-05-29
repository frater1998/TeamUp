package it.fgm.teamup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;



 @Entity
public class Attivita {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   private String obiettivi;

   private short percentualeCompletamento;

   Date dataInizio;
   Date dataFine;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getObiettivi() { return obiettivi; }

    public void setObiettivi(String obiettivi) { this.obiettivi = obiettivi; }

    public int getPercentualeCompletamento() { return percentualeCompletamento; }

    public void setPercentualeCompletamento(short percentualeCompletamento) { this.percentualeCompletamento = percentualeCompletamento; }

    public Date getDataInizio() { return dataInizio; }

    public void setDataInizio(Date dataInizio) { this.dataInizio = dataInizio; }

    public Date getDataFine() { return dataFine; }

    public void setDataFine(Date dataFine) { this.dataFine = dataFine; }

}
