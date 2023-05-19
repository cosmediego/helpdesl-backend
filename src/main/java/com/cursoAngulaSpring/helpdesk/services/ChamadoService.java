package com.cursoAngulaSpring.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Chamado;
import com.cursoAngulaSpring.helpdesk.domain.Cliente;
import com.cursoAngulaSpring.helpdesk.domain.Tecnico;
import com.cursoAngulaSpring.helpdesk.domain.enums.Prioridade;
import com.cursoAngulaSpring.helpdesk.domain.enums.Status;
import com.cursoAngulaSpring.helpdesk.dtos.ChamadoDTO;
import com.cursoAngulaSpring.helpdesk.repositories.ChamadoReposiroty;
import com.cursoAngulaSpring.helpdesk.services.exceptions.ObjectNotFoundException;



@Service
public class ChamadoService {

	@Autowired
	private ChamadoReposiroty repositorio;
	@Autowired
	private TecnicoService tecService;
	@Autowired
	private ClienteService cliService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
	}

	public List<Chamado> findAll() {
		return repositorio.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return repositorio.save(newChamado(objDTO));
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repositorio.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecService.findById(obj.getTecnico());
		Cliente cliente = cliService.findById(obj.getCliente());

		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

	

	

}
