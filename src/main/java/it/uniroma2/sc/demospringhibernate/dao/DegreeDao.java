package it.uniroma2.sc.demospringhibernate.dao;

import it.uniroma2.sc.demospringhibernate.entity.Laurea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeDao extends JpaRepository<Laurea,Long> {
}
