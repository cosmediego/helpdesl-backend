package com.cursoAngulaSpring.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cursoAngulaSpring.helpdesk.domain.enums.Perfil;
import com.cursoAngulaSpring.helpdesk.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	public Tecnico(TecnicoDTO tecDTO) {
		super();
		this.id = tecDTO.getId();
		this.nome = tecDTO.getNome();
		this.cpf = tecDTO.getCpf();
		this.email = tecDTO.getEmail();
		this.senha = tecDTO.getSenha();
		this.perfis = tecDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = tecDTO.getDataCriacao();
	}
	
}
