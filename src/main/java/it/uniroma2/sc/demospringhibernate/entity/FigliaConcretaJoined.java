package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.Entity;

@Entity
public abstract class FigliaConcretaJoined extends MadreJoined {

    private int figliaConcretaColumn;
}
