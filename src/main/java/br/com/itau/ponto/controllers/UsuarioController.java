package br.com.itau.ponto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.ponto.dtos.DetalhesDoUsuarioDto;
import br.com.itau.ponto.dtos.PontoDto;
import br.com.itau.ponto.dtos.PontosUsuarioDTO;
import br.com.itau.ponto.dtos.UsuarioDto;
import br.com.itau.ponto.forms.AlteracaoUsuarioForm;
import br.com.itau.ponto.forms.UsuarioForm;
import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.PontoRepository;
import br.com.itau.ponto.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PontoRepository pontoRepository;
	
	@GetMapping("/usuarios")
	public List<UsuarioDto> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<DetalhesDoUsuarioDto> consultar(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoUsuarioDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/usuario")
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/usuario/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> alterar(@PathVariable Long id, @RequestBody @Valid AlteracaoUsuarioForm form){
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(form.atualizar(id, usuarioRepository)));
		}
		return ResponseEntity.notFound().build();
	}
	

	@GetMapping("/usuario/{id}/pontos")
	public ResponseEntity<PontosUsuarioDTO> listar(@PathVariable Long id){
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if(optional.isPresent()) {
			List<PontoDto> pontosDto = PontoDto.converter(pontoRepository.findByUsuarioId(id));
			return ResponseEntity.ok(new PontosUsuarioDTO(pontosDto, optional.get()));
		} 
		return ResponseEntity.notFound().build();
	}
}
