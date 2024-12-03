package it.uniroma2.sc.demospringhibernate.rest;

import it.uniroma2.sc.demospringhibernate.control.ICreationAndRetrievalController;
import it.uniroma2.sc.demospringhibernate.control.IDeleteController;
import it.uniroma2.sc.demospringhibernate.entity.Cane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cane/")
public class CaneRESTEndpoint {

    @Autowired
    private ICreationAndRetrievalController controllerDiCreazioneERetrieval;

    @Autowired
    private IDeleteController deleteController;

    /**
     * Creates a new dog.
     *
     * @param c The dog entity to be created. It is required in the request body.
     * @return ResponseEntity with the created dog and HttpStatus.CREATED if successful,
     *         or HttpStatus.BAD_REQUEST if the dog entity is null.
     */
    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<?> creaCane(@RequestBody(required = true) Cane c) {
        if (c != null) {
            Cane newCane = controllerDiCreazioneERetrieval.createDog(c);
            ResponseEntity<Cane> response = new ResponseEntity<>(newCane, HttpStatus.CREATED);
            return response;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves a dog by its ID.
     *
     * @param dogId The ID of the dog to be retrieved.
     * @return ResponseEntity with the dog and HttpStatus.OK if found,
     *         HttpStatus.NOT_FOUND if the dog is not found, or
     *         HttpStatus.BAD_REQUEST if the ID is null.
     */
    @RequestMapping(method = RequestMethod.GET, path = "{dogId}")
    public ResponseEntity<?> leggiCane(@PathVariable Long dogId) {
        if (dogId != null) {
            Cane c = controllerDiCreazioneERetrieval.loadDogById(dogId);
            if (c == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all dogs.
     *
     * @return ResponseEntity with a list of all dogs and HttpStatus.OK.
     */
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<?> leggiCani() {
        List<Cane> tuttiICani = controllerDiCreazioneERetrieval.loadDogs();
        return new ResponseEntity<>(tuttiICani, HttpStatus.OK);
    }

    /**
     * Searches for dogs by their name.
     *
     * @param nomeCane The name of the dog to search for, passed as a query parameter.
     * @return ResponseEntity with a list of matching dogs and HttpStatus.OK if the name is provided,
     *         or HttpStatus.BAD_REQUEST if the name is null.
     */
    @RequestMapping(method = RequestMethod.GET, path = "search") // /api/cane/search?nomeCane=Bobby
    public ResponseEntity<?> cercaCaniPerNome(@RequestParam(name = "nomeCane", required = false) String nomeCane) {
        if (nomeCane != null) {
            return new ResponseEntity<>(controllerDiCreazioneERetrieval.searchDogsByName(nomeCane), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Searches for dogs by their owner's ID.
     *
     * @param idPadrone The ID of the owner to search dogs for, passed as a path variable.
     * @return ResponseEntity with a list of matching dogs and HttpStatus.OK if successful,
     *         or HttpStatus.NOT_FOUND if no owner is found or another error occurs.
     */
    @RequestMapping(method = RequestMethod.GET, path = "padrone/{id}")
    public ResponseEntity<?> cercaCaniPerPadrone(@PathVariable(name = "id") Long idPadrone) {
        try {
            return new ResponseEntity<>(controllerDiCreazioneERetrieval.searchDogsByOwner(idPadrone), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{dogId}")
    public ResponseEntity<?> cancellaCane(@PathVariable Long dogId) {
        if (dogId == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(deleteController.deleteDogById(dogId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Generates sample data for testing purposes.
     *
     * @return ResponseEntity with HttpStatus.OK after generating the sample data.
     */
    @RequestMapping(method = RequestMethod.PUT, path = "generate")
    public ResponseEntity<Void> generateSampleData() {
        controllerDiCreazioneERetrieval.createSampleData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
