package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MadreTablePerClass {
    /**
     * MadreTablePerClass
     *  - FigliaConcretaTablePerClass
     *  - FigliaAstrattaTablePerClass
     *      -- NipoteConcretaTablePerClass
     */

    @Id
    @GeneratedValue
    private Long id;

    private int madreTablePerClass;
}
