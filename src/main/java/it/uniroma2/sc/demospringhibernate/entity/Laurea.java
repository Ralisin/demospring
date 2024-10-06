package it.uniroma2.sc.demospringhibernate.entity;

import lombok.Data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("L")
@Data
public class Laurea extends TitoloDiStudio {
    private boolean cicloUnico;

    protected Laurea() {
        super();
    }
    public Laurea(String nomeTitolo, int annoConseguimento, boolean cicloUnico) {
        super(nomeTitolo, annoConseguimento);
        this.cicloUnico = cicloUnico;
    }
}
