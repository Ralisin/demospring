package it.uniroma2.sc.demospringhibernate.rest;

import it.uniroma2.sc.demospringhibernate.control.ICreationAndRetrievalController;
import it.uniroma2.sc.demospringhibernate.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona/")
public class PersonaRESTEndpoint {

    @Autowired
    private ICreationAndRetrievalController controllerDiCreazioneERetrieval;


    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<?> leggiPersone() {
        List<Persona> allPeople = controllerDiCreazioneERetrieval.loadPeople();
        // mapping dto/entity & viceversa
        return new ResponseEntity<>(allPeople, HttpStatus.OK);
    }
}