package it.uniroma2.sc.demospringhibernate.dto;

import it.uniroma2.sc.demospringhibernate.entity.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaneDTO {
    private Long id;

    private String nomeCane;

    private Persona padrone;

    public CaneDTO(Long id, String nome, Persona padrone) {
        this.id = id;
        this.nomeCane = nome;
        this.padrone = padrone;
    }

    @Override
    public String toString() {
        return "CaneDTO{" +
                "id=" + id +
                ", nomeCane='" + nomeCane + '\'' +
                ", padrone=" + padrone +
                '}';
    }
}
