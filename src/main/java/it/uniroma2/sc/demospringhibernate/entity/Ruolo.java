package it.uniroma2.sc.demospringhibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Ruolo {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @Setter
    @Getter
    private Persona persona;

    private enum RuoloEnum {
        VETERINARIO,
        PADRONE
    };

    private RuoloEnum ruolo;

    protected Ruolo() {}
}
