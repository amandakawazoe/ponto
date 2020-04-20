package br.com.itau.ponto.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.UsuarioRepository;

public class AlteracaoUsuarioForm {

	@NotNull @NotEmpty @Length(max = 20)
	private String nome;
	@NotNull @NotEmpty @Length(max = 100)
	private String sobrenome;
	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;
	@NotNull @NotEmpty
	private String email;
	
	public AlteracaoUsuarioForm() {
		
	}
	
	public AlteracaoUsuarioForm(String nome, String sobrenome, String cpf, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(this.nome);
		usuario.setSobrenome(this.sobrenome);
		usuario.setCpf(this.cpf);
		usuario.setEmail(this.email);
		return usuario;
	}
}
