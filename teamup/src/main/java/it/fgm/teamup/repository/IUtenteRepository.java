package it.fgm.teamup.repository;

import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente,Integer> {


    public Utente findById(int id);


    public Utente findBySession(String session);


    public Utente findByEmailAndPassword(String email, String password);


    Utente findByEmail(String email);
}

