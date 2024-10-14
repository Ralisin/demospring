package it.uniroma2.sc.demospringhibernate.rest;

import it.uniroma2.sc.demospringhibernate.control.ICreationAndRetrievalController;
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

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<?> creaCane(@RequestBody(required = true) Cane c) {
        if (c != null) {
            Cane newCane = controllerDiCreazioneERetrieval.createDog(c);
            ResponseEntity<Cane> response = new ResponseEntity<>(newCane, HttpStatus.CREATED);
            return response;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<?> leggiCani() {
        List<Cane> tuttiICani = controllerDiCreazioneERetrieval.loadDogs();
        // mapping dto/entità e viceversa
        return new ResponseEntity<>(tuttiICani, HttpStatus.OK);
    }

    /*@RequestMapping(method = RequestMethod.GET, path = "")
    public List<Cane> leggiCani() {
        return controllerDiCreazioneERetrieval.leggiCani();
    }*/

    @RequestMapping(method = RequestMethod.GET, path = "search") // /api/cane/search?nomeCane=Bobby
    public ResponseEntity<?> cercaCaniPerNome(@RequestParam(name = "nomeCane", required = false) String nomeCane) {
        if (nomeCane != null) {
            return new ResponseEntity<>(controllerDiCreazioneERetrieval.searchDogsByName(nomeCane), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, path = "padrone/{id}")
    public ResponseEntity<?> cercaCaniPerPadrone(@PathVariable(name = "id") Long idPadrone) {
        try {
            return new ResponseEntity<>(controllerDiCreazioneERetrieval.searchDogsByOwner(idPadrone), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "generate")
    public ResponseEntity<Void> generateSampleData() {
        controllerDiCreazioneERetrieval.createSampleData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
