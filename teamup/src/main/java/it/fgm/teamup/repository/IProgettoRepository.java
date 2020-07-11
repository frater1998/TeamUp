package it.fgm.teamup.repository;


import it.fgm.teamup.model.Progetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProgettoRepository extends JpaRepository<Progetto, Integer> {


    Progetto findById(int id);

    @Query("select p from Progetto p where p.categoria ='musica'")
    List< Progetto> findAllByCategoriaIsMusica(String categoria);


    @Query("select p from Progetto p ")
    List< Progetto> getprogetti();


    Progetto findByTitolo(String titolo);




}
