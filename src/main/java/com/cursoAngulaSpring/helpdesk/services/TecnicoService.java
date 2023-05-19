package com.cursoAngulaSpring.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Pessoa;
import com.cursoAngulaSpring.helpdesk.domain.Tecnico;
import com.cursoAngulaSpring.helpdesk.dtos.TecnicoDTO;
import com.cursoAngulaSpring.helpdesk.repositories.PessoaReposiroty;
import com.cursoAngulaSpring.helpdesk.repositories.TecnicoReposiroty;
import com.cursoAngulaSpring.helpdesk.services.exceptions.ObjectNotFoundException;



@Service
public class TecnicoService {
	@Autowired
	private TecnicoReposiroty repositorio;
	@Autowired
	private PessoaReposiroty pessoaRepositorio;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<Tecnico> findAll() {
		return repositorio.findAll();
	}

	public Tecnico create(TecnicoDTO tecDTO) {
		tecDTO.setId(null);
		tecDTO.setSenha(encoder.encode(tecDTO.getSenha()));
		validaPorCPFeEmail(tecDTO);
		Tecnico newTec = new Tecnico(tecDTO);
		return repositorio.save(newTec);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecDTO) {
		tecDTO.setId(id);
		Tecnico oldTec = findById(id);
		validaPorCPFeEmail(tecDTO);
		oldTec = new Tecnico(tecDTO);
		return repositorio.save(oldTec);
	}

	private void validaPorCPFeEmail(TecnicoDTO tecDTO) {
		Optional<Pessoa> obj = pessoaRepositorio.findByCpf(tecDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != tecDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!!");
		}

		obj = pessoaRepositorio.findByEmail(tecDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != tecDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!!");
		}
	}

	public void delete(Integer id) {
		Tecnico tec = findById(id);
		if (tec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Técnico possui ordens de serviço e não pode ser deletado!");
		}
		repositorio.deleteById(id);
	}

}
