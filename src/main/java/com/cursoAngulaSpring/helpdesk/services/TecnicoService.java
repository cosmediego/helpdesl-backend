package com.cursoAngulaSpring.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Tecnico;
import com.cursoAngulaSpring.helpdesk.repositories.TecnicoReposiroty;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoReposiroty repositorio;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repositorio.findById(id);
		return obj.orElse(null);
	}
}
