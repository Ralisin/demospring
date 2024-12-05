package it.uniroma2.sc.demospringhibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore properties related to Hibernate's lazy loading mechanism.
public class Cane {

    @Id
    @GeneratedValue
    private Long id;  // Unique identifier for the dog entity, automatically generated.

    @JsonProperty(value = "nomeCane")
    private String nome;  // The name of the dog, exposed as "nomeCane" in JSON.

    @ManyToOne(fetch = FetchType.LAZY)  // Defines a many-to-one relationship with Persona, using lazy loading.
    private Persona padrone;  // The owner (Persona) of the dog.

    /**
     * Default constructor required by JPA.
     * It is protected to prevent direct instantiation without parameters.
     */
    protected Cane() {
    }

    /**
     * Constructor to create a new dog with a name and owner.
     *
     * @param nome The name of the dog.
     * @param padrone The owner of the dog.
     */
    public Cane(String nome, Persona padrone) {
        this.nome = nome;
        this.padrone = padrone;
    }

    /**
     * Overrides the default `toString` method to provide a string representation of the dog object.
     *
     * @return a string representation of the dog, including its id, name, and owner.
     */
    @Override
    public String toString() {
        return "Cane{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", padrone=" + padrone +
                '}';
    }
}
