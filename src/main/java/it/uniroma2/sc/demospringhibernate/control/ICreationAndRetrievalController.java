package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.entity.Cane;

import java.util.List;

public interface ICreationAndRetrievalController {
    Cane createDog(Cane c);

    Cane loadDogById(Long idCane);

    List<Cane> loadDogs();

    List<Cane> searchDogsByName(String nome);

    List<Cane> searchDogsByOwner(Long idPadrone) throws Exception;

    void createSampleData();
}
