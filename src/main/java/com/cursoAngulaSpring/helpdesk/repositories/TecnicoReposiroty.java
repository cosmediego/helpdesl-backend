package com.cursoAngulaSpring.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoAngulaSpring.helpdesk.domain.Tecnico;

public interface TecnicoReposiroty  extends JpaRepository<Tecnico, Integer>{

}
