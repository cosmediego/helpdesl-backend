package com.cursoAngulaSpring.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Chamado;
import com.cursoAngulaSpring.helpdesk.domain.Cliente;
import com.cursoAngulaSpring.helpdesk.domain.Tecnico;
import com.cursoAngulaSpring.helpdesk.domain.enums.Perfil;
import com.cursoAngulaSpring.helpdesk.domain.enums.Prioridade;
import com.cursoAngulaSpring.helpdesk.domain.enums.Status;
import com.cursoAngulaSpring.helpdesk.repositories.ChamadoReposiroty;
import com.cursoAngulaSpring.helpdesk.repositories.ClienteReposiroty;
import com.cursoAngulaSpring.helpdesk.repositories.TecnicoReposiroty;

@Service
public class DBService {
	@Autowired
	private TecnicoReposiroty tecnicoReposiroty;
	@Autowired
	private ClienteReposiroty clienteReposiroty;
	@Autowired
	private ChamadoReposiroty chamadoReposiroty;
	
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Cosme Diego", "00000000000", "cosme@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "João Paulo", "58367373120", "jp@gmail.com", "123");
		tec2.addPerfil(Perfil.TECNICO);
		
		Cliente cli1 = new Cliente(null, "Linus Torval", "81415473480", "torval@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado_01", "Primeiro chamado", tec1, cli1);
		
		
	
		tecnicoReposiroty.saveAll(Arrays.asList(tec1, tec2));
		clienteReposiroty.saveAll(Arrays.asList(cli1));
		chamadoReposiroty.saveAll(Arrays.asList(c1));
	}

}
