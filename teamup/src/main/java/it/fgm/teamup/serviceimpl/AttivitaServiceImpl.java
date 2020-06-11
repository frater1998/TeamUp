package it.fgm.teamup.serviceimpl;



import it.fgm.teamup.model.Attivita;
import it.fgm.teamup.repository.IAttivitàRepository;
import it.fgm.teamup.services.IAttivitàService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttivitaServiceImpl  implements IAttivitàService {



    @Autowired            //code injection!
    private IAttivitàRepository attivitaRepository;
    private IAttivitàService attivitaService;

    @Override
    @Transactional        //da utilizzare durante le transazioni con il db
    public Attivita salva(Attivita attivita) {
        return attivitaRepository.save(attivita);
    }


    @Override
    public Attivita findById(int id){

        return attivitaService.findById(id);
    }


}



