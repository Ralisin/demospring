package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.*;

@MappedSuperclass
public class MadreMappedSuperclass {

    @Id
    @GeneratedValue
    private Long id;

    private int madreMappedSuperclass;
}
