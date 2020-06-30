package it.fgm.teamup.repository;


import it.fgm.teamup.model.Progetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgettoRepository extends JpaRepository<Progetto, Integer> {


    Progetto findById(int id);





}
