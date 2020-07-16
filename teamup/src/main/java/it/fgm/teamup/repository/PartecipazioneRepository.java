package it.fgm.teamup.repository;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Integer> {

    @Query("select p from Partecipazione p where p.utente.id =  :id group by p.utente.id")
    public Partecipazione findByUtenteId(int id);

    Partecipazione findById(long id);


    @Query("select p from Partecipazione p where p.utente.id = :id and p.ruolo = 'LEADER' group by p.utente.id")
    Partecipazione findByUtenteIdAndRuoloIsLeader(int id);

    @Query("select p from Partecipazione p where p.ruolo = 'LEADER' group by p.utente.id")
    Partecipazione findByRuoloIsLeader();

    @Query("select p from Partecipazione p where p.partecipazione_confermata = false")
    List<Partecipazione> findByPartecipazione_confermataIsFalse(boolean pc);

    @Query("select p from Partecipazione p where p.partecipazione_confermata = true and " +
            "  p.utente = :utente")
    List<Partecipazione> findByPartecipazione_confermataIsTrue(Utente utente);

    @Query("SELECT p from Partecipazione p " +
            "JOIN Partecipazione p1 on p.progetto.id = p1.progetto.id " +
            "WHERE p.partecipazione_confermata = false AND " +
            "  p1.ruolo = 'LEADER'")
    List<Partecipazione> findByPartecipazione_confermataIsFalseAndRuoloIsLeader();

    @Query("select p from Partecipazione p where p.progetto.id = :id")
    List<Partecipazione> findByProgettoId(int id);

  //  Partecipazione findByProgettoIdAndUtenteId(int id);


    @Query("SELECT p FROM Partecipazione p join Partecipazione p1 on p.progetto.id = p1.progetto.id where p.partecipazione_confermata = true " +
            "and p.ruolo = 'TEAM-MATE' and p1.ruolo = 'LEADER' group by p.progetto.id")
    List<Partecipazione> findAllByPartecipazione_confermataIsTrueAndAndRuolo();

}
