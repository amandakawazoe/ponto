package br.com.itau.ponto.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.itau.ponto.models.Ponto;
import br.com.itau.ponto.models.TipoBatida;

public class PontoDto {

	private Long idUsuario;
	private LocalDateTime dataBatida;
	private TipoBatida tipoBatida;
	
	public PontoDto(Ponto ponto) {
		this.idUsuario = ponto.getUsuario().getId();
		this.dataBatida = ponto.getDataBatida();
		this.tipoBatida = ponto.getTipoBatida();
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public LocalDateTime getDataBatida() {
		return dataBatida;
	}
	public TipoBatida getTipoBatida() {
		return tipoBatida;
	}

	public static List<PontoDto> converter(List<Ponto> pontos) {
		return pontos.stream().map(PontoDto::new).collect(Collectors.toList());
	}
	
	
}
