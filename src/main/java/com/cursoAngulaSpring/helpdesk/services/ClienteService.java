package com.cursoAngulaSpring.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursoAngulaSpring.helpdesk.domain.Pessoa;
import com.cursoAngulaSpring.helpdesk.domain.Cliente;
import com.cursoAngulaSpring.helpdesk.dtos.ClienteDTO;
import com.cursoAngulaSpring.helpdesk.repositories.PessoaReposiroty;
import com.cursoAngulaSpring.helpdesk.repositories.ClienteReposiroty;
import com.cursoAngulaSpring.helpdesk.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
	private ClienteReposiroty repositorio;
	@Autowired
	private PessoaReposiroty pessoaRepositorio;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<Cliente> findAll() {
		return repositorio.findAll();
	}

	public Cliente create(ClienteDTO cliDTO) {
		cliDTO.setId(null);
		cliDTO.setSenha(encoder.encode(cliDTO.getSenha()));
		validaPorCPFeEmail(cliDTO);
		Cliente newTec = new Cliente(cliDTO);
		return repositorio.save(newTec);
	}

	public Cliente update(Integer id, @Valid ClienteDTO cliDTO) {
		cliDTO.setId(id);
		Cliente oldTec = findById(id);
		validaPorCPFeEmail(cliDTO);
		oldTec = new Cliente(cliDTO);
		return repositorio.save(oldTec);
	}

	private void validaPorCPFeEmail(ClienteDTO cliDTO) {
		Optional<Pessoa> obj = pessoaRepositorio.findByCpf(cliDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() !=cliDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!!");
		}

		obj = pessoaRepositorio.findByEmail(cliDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() !=cliDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!!");
		}
	}

	public void delete(Integer id) {
		Cliente cli = findById(id);
		if (cli.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Cliente possui ordens de serviço e não pode ser deletado!");
		}
		repositorio.deleteById(id);
	}

}
