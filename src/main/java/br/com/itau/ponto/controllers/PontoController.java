package br.com.itau.ponto.controllers;

import java.time.Duration;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.ponto.dtos.PontoDto;
import br.com.itau.ponto.forms.PontoForm;
import br.com.itau.ponto.models.Ponto;
import br.com.itau.ponto.models.TipoBatida;
import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.PontoRepository;
import br.com.itau.ponto.repositories.UsuarioRepository;

@RestController
public class PontoController {

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/ponto")
	@Transactional
	public ResponseEntity<PontoDto> registrar(@RequestBody @Valid PontoForm form){
		Optional<Usuario> optional = usuarioRepository.findById(form.getIdUsuario());
		if(optional.isPresent()) {
			Usuario usuario = optional.get();
			Ponto ponto = form.converter(usuarioRepository);
			Ponto ultimoPonto = pontoRepository.findFirstByUsuarioIdOrderByDataBatidaDesc(form.getIdUsuario());
			if(ultimoPonto != null) {
				if(ultimoPonto.getTipoBatida() == TipoBatida.ENTRADA) {
					usuario.adicionarHoras(Duration.between(ultimoPonto.getDataBatida(), ponto.getDataBatida()).toHours());
					ponto.setTipoBatida(TipoBatida.SAIDA);
				}
			}
			pontoRepository.save(ponto);
			
			return ResponseEntity.ok(new PontoDto(ponto));
		}
		return ResponseEntity.notFound().build();
	}
	
}
