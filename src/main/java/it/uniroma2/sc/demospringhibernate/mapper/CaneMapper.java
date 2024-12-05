package it.uniroma2.sc.demospringhibernate.mapper;

import it.uniroma2.sc.demospringhibernate.dto.CaneDTO;
import it.uniroma2.sc.demospringhibernate.entity.Cane;

public class CaneMapper {
    public static CaneDTO toDTO(Cane cane) {
        return new CaneDTO(cane.getId(), cane.getNome(), cane.getPadrone());
    }

    public static Cane toEntity(CaneDTO dto) {
        return new Cane(dto.getNomeCane(), dto.getPadrone());
    }
}
