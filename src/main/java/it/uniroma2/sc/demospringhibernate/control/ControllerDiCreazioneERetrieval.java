package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.CaneDao;
import it.uniroma2.sc.demospringhibernate.dao.DiplomaDao;
import it.uniroma2.sc.demospringhibernate.dao.LaureaDao;
import it.uniroma2.sc.demospringhibernate.dao.PersonaDao;
import it.uniroma2.sc.demospringhibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Service
public class ControllerDiCreazioneERetrieval implements IControllerDiCreazioneERetrieval {

    @Autowired
    private CaneDao caneDao;

    @Autowired
    private DiplomaDao diplomaDao;

    @Autowired
    private LaureaDao laureaDao;

    @Autowired
    private PersonaDao personaDao;

    @Transactional
    public void creazioniDiProva() {
        Indirizzo indirizzo = new Indirizzo("via e numero", "00100");
        Persona p = new Persona("nome", "cognome", indirizzo);

        Laurea l = new Laurea("nome titolo", 2010, false);
        Diploma d = new Diploma("nome titolo", 2010, "classe titolo");

        p.aggiungiTitolo(l);
        p.aggiungiTitolo(d);

        p = personaDao.save(p);

        //caneDao = new CaneDao();

        Cane c0 = new Cane("Bobby2", p);
        caneDao.save(c0);

        Cane c1 = new Cane("Bobby3", p);
        caneDao.save(c1);

        List<Cane> caniSalvati = caneDao.findAll();
        for (Cane c : caniSalvati) {
            System.out.println(c);
        }


        List<Cane> caniBobby = caneDao.findByNome("Bobby2");

        List<Cane> caniPerPadrone = caneDao.findByPadrone(p);

        List<Cane> caniPerCognomePadroneEsistente = caneDao.findByPadrone_CognomeLike("cogn%");
        List<Cane> caniPerCognomePadroneNonEsistente = caneDao.findByPadrone_CognomeLike("Bobby%");


    }

    public Cane creaCane(@NotNull Cane c) {
        return caneDao.save(c);
    }

    public Cane leggiCanePerId(@NotNull Long idCane) {
        return caneDao.getOne(idCane);
    }

    public List<Cane> leggiCani() {
        return caneDao.findAll();
    }

    /**
     *
     * @param nome non deve essere null
     * @return
     */
    public List<Cane> cercaCaniPerNome(@NotNull String nome) {
        return caneDao.findByNome(nome);
    }

    public List<Cane> cercaCaniPerPadrone(@NotNull Long idPadrone) throws Exception {
        Persona padrone = personaDao.getOne(idPadrone);
        if(padrone==null) {
            throw new Exception("Nessun padrone presente con id " + idPadrone);
        }
        return caneDao.findByPadrone(padrone);
    }
}
