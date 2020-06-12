package it.fgm.teamup.repository;

import it.fgm.teamup.model.Attivita;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAttivitàRepository extends JpaRepository<Attivita,Integer> {

    Attivita findById(int id);
}

