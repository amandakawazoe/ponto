package br.com.itau.ponto.dtos;

import java.util.List;

import br.com.itau.ponto.models.Usuario;

public class PontosUsuarioDTO {
	
	private List<PontoDto> listaPontos;
	private Long horasTrabalhadas;
	
	public PontosUsuarioDTO(List<PontoDto> lista, Usuario usuario) {
		this.listaPontos = lista;
		this.horasTrabalhadas = usuario.getTotalHoras();
	}
	
	public List<PontoDto> getListaPontos() {
		return listaPontos;
	}
	public void setListaPontos(List<PontoDto> listaPontos) {
		this.listaPontos = listaPontos;
	}
	public Long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(Long horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

}
