package br.com.itau.ponto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itau.ponto.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
