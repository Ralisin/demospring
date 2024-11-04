package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MadreSingleTable {
    /**
     * MadreSingleTable
     *  - FigliaConcretaSingleTable
     *  - FigliaAstrattaSingleTable
     *      -- NipoteConcretaSingleTable
     */

    @Id
    @GeneratedValue
    private Long id;

    private int madreSingleTable;
}
