package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@Entity
public class FigliaMappedSuperclass extends MadreMappedSuperclass {

    private int numero;
}
