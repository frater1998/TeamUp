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

    @Query("select p from Progetto p where p.categoria ='sport'")
    List< Progetto> findAllByCategoriaIsSport(String sport);
    @Query("select p from Progetto p where p.categoria ='cinema'")
    List<Progetto> findAllByCategoriaIsCinema(String cinema);

    @Query("select p from Progetto p where p.categoria ='didattica'")
    List<Progetto> findAllByCategoriaIsDidattica(String didattica);

    @Query("select p from Progetto p where p.categoria ='altro'")
    List<Progetto> findAllByCategoriaIsAltro(String altro);

List<Progetto> findAll();



    @Query("select p from Progetto p ")
    List< Progetto> getprogetti();


    Progetto findByTitolo(String titolo);




}
