package com.cursoAngulaSpring.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoAngulaSpring.helpdesk.domain.Chamado;

public interface ChamadoReposiroty  extends JpaRepository<Chamado, Integer>{

}
