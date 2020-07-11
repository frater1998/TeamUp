package it.fgm.teamup.services;


import it.fgm.teamup.model.Attivita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAttivitàService {


    Attivita save(String percentuale, int id);

}
