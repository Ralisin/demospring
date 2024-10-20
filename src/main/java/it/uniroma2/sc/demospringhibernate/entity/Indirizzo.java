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
    private Long id;  // Unique identifier for the address entity, automatically generated.

    private String viaENumero;  // The street name and house number of the address.
    //private CAP cap;
    private String cap;  // The postal code (CAP) of the address.

    @Embedded
    private Citta citta;  // The city associated with the address, embedded as part of this entity.

    /**
     * Default constructor required by JPA.
     * It is protected to prevent direct instantiation without parameters.
     */
    protected Indirizzo() {
    }

    /**
     * Constructor for creating a new address with street and postal code.
     *
     * @param viaENumero The street name and house number.
     * @param cap The postal code (CAP) for the address.
     */
    public Indirizzo(String viaENumero, String cap) {
        this.viaENumero = viaENumero;
        this.cap = cap;
    }
}
