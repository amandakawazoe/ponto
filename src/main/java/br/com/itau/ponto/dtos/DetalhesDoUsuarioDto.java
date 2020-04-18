package br.com.itau.ponto.dtos;

import java.time.LocalDateTime;

import br.com.itau.ponto.models.Usuario;

public class DetalhesDoUsuarioDto {

	private Long id;
	private String nomeCompleto;
	private String cpf;
	private String email;
	private LocalDateTime dataCadastro;
	
	public DetalhesDoUsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nomeCompleto = String.join(" ", usuario.getNome(), usuario.getSobrenome());
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
		this.dataCadastro = usuario.getDataCadastro();
	}
	
	public Long getId() {
		return id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	
}
