package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MadreJoined {


    /**
     * MadreJoined
     *  - FigliaConcretaJoined
     *  - FigliaAstrattaJoined
     *      -- NipoteConcretaJoined
     */

    @Id
    @GeneratedValue
    private Long id;

    private int numero;
}
