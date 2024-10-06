package it.uniroma2.sc.demospringhibernate.entity;

import lombok.Data;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Data
public class Indirizzo {
    @Id
    @GeneratedValue
    private Long id;

    private String viaENumero;
    //private CAP cap;
    private String cap;

    @Embedded
    private Citta citta;

    protected Indirizzo() {

    }

    public Indirizzo(String viaENumero, String cap) {
        this.viaENumero = viaENumero;
        this.cap = cap;
    }
}
