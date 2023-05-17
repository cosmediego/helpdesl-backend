package com.cursoAngulaSpring.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoAngulaSpring.helpdesk.domain.Cliente;
import com.cursoAngulaSpring.helpdesk.domain.Pessoa;

@Repository
public interface ClienteReposiroty  extends JpaRepository<Cliente, Integer>{

}
