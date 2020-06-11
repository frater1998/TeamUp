package it.fgm.teamup.repository;

import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

public interface IUtenteRepository extends JpaRepository<Utente,Integer> {


    public Utente findByEmailAndPassword(String username, String password);
}

