package it.fgm.teamup.services;

import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;

import java.util.List;

public interface IProgettoService {

    public Progetto salva(String descr, int id);

    public Progetto findById(int id) ;

   List< Progetto> findAllByCategoriaIsMusica(String categoria);


      public List<Progetto> getAll();

     public void deleteById(int id) ;


}
