package com.cursoAngulaSpring.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Chamado;
import com.cursoAngulaSpring.helpdesk.repositories.ChamadoReposiroty;
import com.cursoAngulaSpring.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoReposiroty repositorio;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repositorio.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
	}
}
