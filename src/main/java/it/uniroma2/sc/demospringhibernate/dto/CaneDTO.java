package it.uniroma2.sc.demospringhibernate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CaneDTO {
    @Setter
    private Long id;

    @Setter
    private String nome;

    @Setter
    private String nomePadrone;

    @Setter
    private String cognomePadrone;

    public CaneDTO(Long id, String nome, String nomePadrone, String cognomePadrone) {
        this.id = id;
        this.nome = nome;
        this.nomePadrone = nomePadrone;
        this.cognomePadrone = cognomePadrone;
    }
}
