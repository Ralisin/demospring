package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.Entity;

@Entity
public abstract class FigliaAstrattaJoined extends MadreJoined {

    private int altroNumero;
}
