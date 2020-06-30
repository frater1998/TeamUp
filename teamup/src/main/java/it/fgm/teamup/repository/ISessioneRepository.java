package it.fgm.teamup.repository;

import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessioneRepository extends JpaRepository<Sessione, Integer> {


    Sessione findById(String id);


}
