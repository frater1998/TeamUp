package it.fgm.teamup.repository;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Integer> {

    //@Query("select p from Progetto where progetto.")
    public Partecipazione findByUtenteId(int id);


}
