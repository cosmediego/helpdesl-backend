package com.cursoAngulaSpring.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoAngulaSpring.helpdesk.domain.Pessoa;

public interface PessoaReposiroty  extends JpaRepository<Pessoa, Integer>{

}
