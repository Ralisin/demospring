package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.DogDao;
import it.uniroma2.sc.demospringhibernate.dao.DiplomaDao;
import it.uniroma2.sc.demospringhibernate.dao.DegreeDao;
import it.uniroma2.sc.demospringhibernate.dao.PersonDao;
import it.uniroma2.sc.demospringhibernate.dto.CaneDTO;
import it.uniroma2.sc.demospringhibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
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

    /**
     * This method creates sample data in the database.
     * It creates an address, a person (with a degree and a diploma),
     * two dogs, and saves them in the corresponding tables.
     * It also prints all saved dogs and demonstrates different queries.
     */
    @Transactional
    public void createSampleData() {
        // Persona 1
        Indirizzo indirizzo = new Indirizzo("Via Roma 20", "00100");
        Persona p = new Persona("Manuel", "Mastrofini", indirizzo);
        Laurea l = new Laurea("Degree", 2010, false);
        Diploma d = new Diploma("High school diploma", 2010, "Tourism");
        p.addQualification(l);
        p.addQualification(d);

        // Save the person in the database
        p = personDao.save(p);

        // Create and save two dogs associated with the person
        Cane c0 = new Cane("Rosy", p);
        dogDao.save(c0);

        Cane c1 = new Cane("Brian", p);
        dogDao.save(c1);

        // Retrieve and print all saved dogs
        List<Cane> allSavedDogs = dogDao.findAll();
        for (Cane c : allSavedDogs) {
            System.out.println(c);
        }

        // Create and save more people
        Indirizzo indirizzo2 = new Indirizzo("Via Firenze 10", "00500");
        Persona p2 = new Persona("John", "Doe", indirizzo2);
        p2 = personDao.save(p2);

        Cane c2 = new Cane("Mary", p2);
        dogDao.save(c2);

        Indirizzo indirizzo3 = new Indirizzo("Via Venezia 30", "00300");
        Persona p3 = new Persona("Mary", "Jane", indirizzo3);
        p3 = personDao.save(p3);

        Cane c3 = new Cane("Marge", p3);
        dogDao.save(c3);

//        // Query for dogs by specific criteria
//        List<Cane> dogsNamedRosy = dogDao.findByNome("Rosy");
//        List<Cane> dogsByOwner = dogDao.findByPadrone(p);
//        List<Cane> dogsByOwnerLastName = dogDao.findByPadrone_CognomeLike("Mast%");
//        List<Cane> dogsByOwnerLastNameNoResults = dogDao.findByPadrone_CognomeLike("Unknown%");
    }

    /**
     * Retrieves all people from the database.
     * @return a list of all people.
     */
    @Override
    public List<Persona> loadPeople() {
        return personDao.findAll();
    }

    /**
     * Saves a new dog in the database.
     * @param c the dog to be created, must not be null.
     * @return the created dog.
     */
    public Cane createDog(@NotNull Cane c) {
        return dogDao.save(c);
    }

    /**
     * Retrieves a dog by its ID.
     * @param idCane the ID of the dog, must not be null.
     * @return the dog with the given ID.
     */
    public CaneDTO loadDogById(@NotNull Long idCane) {
        Cane c = dogDao.getOne(idCane);

        return new CaneDTO(c.getId(), c.getNome(), c.getPadrone().getNome(), c.getPadrone().getCognome());
    }

    /**
     * Retrieves all dogs from the database.
     * @return a list of all dogs.
     */
    public List<CaneDTO> loadDogs() {
        List<Cane> listDogs = dogDao.findAll();

        List<CaneDTO> listDogsDTO = new ArrayList<CaneDTO>();

        listDogs.forEach(d -> {
            CaneDTO caneDTO = new CaneDTO(d.getId(), d.getNome(), d.getPadrone().getNome(), d.getPadrone().getCognome());

            listDogsDTO.add(caneDTO);
        });

        return listDogsDTO;
    }

    /**
     * Searches for dogs by their name.
     * @param name the name of the dog, must not be null.
     * @return a list of dogs with the given name.
     */
    public List<CaneDTO> searchDogsByName(@NotNull String name) {
        List<Cane> listDogs = dogDao.findByNome(name);

        List<CaneDTO> listDogsDTO = new ArrayList<CaneDTO>();

        listDogs.forEach(d -> {
            CaneDTO caneDTO = new CaneDTO(d.getId(), d.getNome(), d.getPadrone().getNome(), d.getPadrone().getCognome());

            listDogsDTO.add(caneDTO);
        });

        return listDogsDTO;
    }

    /**
     * Searches for dogs by the owner's ID.
     * @param idOwner the ID of the owner, must not be null.
     * @return a list of dogs belonging to the owner.
     * @throws Exception if no owner is found with the given ID.
     */
    public List<CaneDTO> searchDogsByOwner(@NotNull Long idOwner) throws Exception {
        Persona owner = personDao.getOne(idOwner);
        if (owner == null) {
            throw new Exception("No owner found for id " + idOwner);
        }

        List<Cane> listDogs = dogDao.findByPadrone(owner);

        List<CaneDTO> listDogsDTO = new ArrayList<CaneDTO>();

        listDogs.forEach(d -> {
            CaneDTO caneDTO = new CaneDTO(d.getId(), d.getNome(), d.getPadrone().getNome(), d.getPadrone().getCognome());

            listDogsDTO.add(caneDTO);
        });

        return listDogsDTO;
    }
}
