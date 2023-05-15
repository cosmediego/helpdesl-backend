package com.cursoAngulaSpring.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoAngulaSpring.helpdesk.domain.Chamado;

@Repository
public interface ChamadoReposiroty  extends JpaRepository<Chamado, Integer>{

}
