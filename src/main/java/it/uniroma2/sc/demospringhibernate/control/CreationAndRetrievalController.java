package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.DogDao;
import it.uniroma2.sc.demospringhibernate.dao.DiplomaDao;
import it.uniroma2.sc.demospringhibernate.dao.DegreeDao;
import it.uniroma2.sc.demospringhibernate.dao.PersonDao;
import it.uniroma2.sc.demospringhibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Service
public class CreationAndRetrievalController implements ICreationAndRetrievalController {

    @Autowired
    private DogDao dogDao;

    @Autowired
    private DiplomaDao diplomaDao;

    @Autowired
    private DegreeDao degreeDao;

    @Autowired
    private PersonDao personDao;

    @Transactional
    public void createSampleData() {
        Indirizzo indirizzo = new Indirizzo("Via Roma 20", "00100");
        Persona p = new Persona("Manuel", "Mastrofini", indirizzo);

        Laurea l = new Laurea("Degree", 2010, false);
        Diploma d = new Diploma("High school diploma", 2010, "Tourism");

        p.addQualification(l);
        p.addQualification(d);

        p = personDao.save(p);

        //caneDao = new CaneDao();

        Cane c0 = new Cane("Rosy", p);
        dogDao.save(c0);

        Cane c1 = new Cane("Brian", p);
        dogDao.save(c1);

        List<Cane> allSavedDogs = dogDao.findAll();
        for (Cane c : allSavedDogs) {
            System.out.println(c);
        }


        List<Cane> dogsNamedRosy = dogDao.findByNome("Rosy");

        List<Cane> dogsByOwner = dogDao.findByPadrone(p);

        List<Cane> dogsByOwnerLastName = dogDao.findByPadrone_CognomeLike("Mast%");
        List<Cane> dogsByOwnerLastNameNoResults = dogDao.findByPadrone_CognomeLike("Unknown%");


    }

    public Cane createDog(@NotNull Cane c) {
        return dogDao.save(c);
    }

    public Cane loadDogById(@NotNull Long idCane) {
        return dogDao.getOne(idCane);
    }

    public List<Cane> loadDogs() {
        return dogDao.findAll();
    }

    /**
     *
     * @param name must be not null
     * @return
     */
    public List<Cane> searchDogsByName(@NotNull String name) {
        return dogDao.findByNome(name);
    }

    public List<Cane> searchDogsByOwner(@NotNull Long idOwner) throws Exception {
        Persona owner = personDao.getOne(idOwner);
        if(owner==null) {
            throw new Exception("No owner found for id " + idOwner);
        }
        return dogDao.findByPadrone(owner);
    }
}
