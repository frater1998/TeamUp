package it.fgm.teamup.serviceimpl;

import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IProgettoService;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ProgettoServiceImpl implements IProgettoService{


    @Autowired
    IProgettoRepository progettoRepository;


    @Transactional        //da utilizzare durante le transazioni con il db
    public Progetto salva(Progetto progetto) {
        return progettoRepository.save(progetto);
    }


    @Override
    public Progetto findById(int id){
        return progettoRepository.findById( id );
    }

    @Override
   public List<Progetto> getAll(){
        return  progettoRepository.findAll();
    }

    @Override
    public void deleteById(int id) {

        progettoRepository.deleteById( id );

    }


}
