package br.com.itau.ponto.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.itau.ponto.models.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nomeCompleto;
	private String cpf;
	private String email;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nomeCompleto = String.join(" ", usuario.getNome(), usuario.getSobrenome());
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
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

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
}
