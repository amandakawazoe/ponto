package br.com.itau.ponto.forms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.itau.ponto.models.Ponto;
import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.UsuarioRepository;

public class PontoForm {

	@NotNull
	private Long idUsuario;
	@NotNull @NotEmpty
	private String dataBatida;
	
	public PontoForm() {
		
	}
	
	public PontoForm(Long idUsuario, String dataBatida) {
		this.idUsuario = idUsuario;
		this.dataBatida = dataBatida;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDataBatida() {
		return dataBatida;
	}
	public void setDataBatida(String dataBatida) {
		this.dataBatida = dataBatida;
	}
	
	public Ponto converter(UsuarioRepository usuarioRepository) {
		LocalDateTime data = LocalDateTime.parse(this.dataBatida, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Usuario usuario = usuarioRepository.getOne(this.idUsuario);
		return new Ponto(data, usuario);
	}
}
