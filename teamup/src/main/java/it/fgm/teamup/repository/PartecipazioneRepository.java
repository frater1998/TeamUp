package it.fgm.teamup.repository;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Integer> {

    //@Query("select p from Progetto where progetto.")
    public Partecipazione findByUtenteId(int id);

    Partecipazione findById(long id);


    @Query("select p from Partecipazione p where p.partecipazione_confermata = false")
    List<Partecipazione> findByPartecipazione_confermataIsFalse(boolean pc);


}
