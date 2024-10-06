package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
public class FigliaConcretaTablePerClass extends FigliaAstrattaTablePerClass{

    private int altroNumeroAncora;
}
