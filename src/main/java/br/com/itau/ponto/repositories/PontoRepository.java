package br.com.itau.ponto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itau.ponto.models.Ponto;

public interface PontoRepository extends JpaRepository<Ponto, Long> {

	Ponto findFirstByUsuarioIdOrderByDataBatidaDesc(Long idUsuario);

	List<Ponto> findByUsuarioId(Long idUsuario);

}
