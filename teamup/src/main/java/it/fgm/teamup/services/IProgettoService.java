package it.fgm.teamup.services;

import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;

import java.util.List;

public interface IProgettoService {

    public Progetto salva(Progetto progetto);

    public Progetto findById(int id) throws IllegalArgumentException;

      public List<Progetto> getAll();

     public void deleteById(int id) ;


}
