package it.fgm.teamup.services;

import it.fgm.teamup.exception.UtenteNonTrovato;
import it.fgm.teamup.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUtenteService {
    public Utente salva(Utente utente);

    public Utente findByEmailAndPassword(String email, String password);
    //  public List<Utente> getAll();
//    public Utente findById(int id) ;
  //  public void deleteById(int id) ;
}
