package br.com.itau.ponto.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ponto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataBatida = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private TipoBatida tipoBatida;
	@ManyToOne
	private Usuario usuario;
	
	public Ponto() {		
	}
	
	public Ponto(LocalDateTime data, Usuario usuario) {
		this.dataBatida = data;
		this.usuario = usuario;
		this.tipoBatida = TipoBatida.ENTRADA;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataBatida() {
		return dataBatida;
	}

	public TipoBatida getTipoBatida() {
		return tipoBatida;
	}

	public void setTipoBatida(TipoBatida tipoBatida) {
		this.tipoBatida = tipoBatida;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
