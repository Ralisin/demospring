package it.uniroma2.sc.demospringhibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cane {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "nomeCane")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private Persona padrone;

    public Persona getPadrone() {
        return padrone;
    }

    protected Cane() {

    }

    public Cane(String nome, Persona padrone) {
        this.nome = nome;
        this.padrone = padrone;
    }

    @Override
    public String toString() {
        return "Cane{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", padrone=" + padrone +
                '}';
    }
}
