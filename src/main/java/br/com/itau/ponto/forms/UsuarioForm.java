package br.com.itau.ponto.forms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.itau.ponto.models.Usuario;

public class UsuarioForm {

	@NotNull @NotEmpty @Length(max = 20)
	private String nome;
	@NotNull @NotEmpty @Length(max = 100)
	private String sobrenome;
	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String dataCadastro;
	
	public UsuarioForm() {
		
	}
	
	public UsuarioForm(String nome, String sobrenome, String cpf, String email, String dataCadastro) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.dataCadastro = dataCadastro;
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
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Usuario converter() {
		LocalDateTime data = LocalDateTime.parse(this.dataCadastro, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return new Usuario(this.nome, this.sobrenome, this.cpf, this.email, data);
	}
}
