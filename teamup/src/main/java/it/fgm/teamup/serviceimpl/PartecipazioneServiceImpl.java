package it.fgm.teamup.serviceimpl;

import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.repository.PartecipazioneRepository;
import it.fgm.teamup.services.IPartecipazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PartecipazioneServiceImpl implements IPartecipazioneService {

    @Autowired
    PartecipazioneRepository partecipazioneRepository;

    @Autowired
    IUtenteRepository utenteRepository;

    @Autowired
    IProgettoRepository progettoRepository;

    @Transactional        //da utilizzare durante le transazioni con il db
    public Partecipazione salva(Partecipazione partecipazione) {
        return partecipazioneRepository.save(partecipazione);
    }

    @Override
    public Partecipazione findByUtenteId(int id) {
        return partecipazioneRepository.findByUtenteId( id );
    }


    public Partecipazione impostaPartecipazione(Utente utente, Progetto progetto,  Partecipazione partecipazione){


        System.out.println(partecipazione.getRuolo());
        System.out.println(partecipazione.getPartecipazione_confermata());
        System.out.println(progetto.getId());


        Partecipazione part = new Partecipazione();
        System.out.println(utente.getPartecipazione());



        return part;

    }



}


