package br.com.itau.ponto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.itau.ponto.controllers.PontoController;
import br.com.itau.ponto.dtos.PontoDto;
import br.com.itau.ponto.forms.PontoForm;
import br.com.itau.ponto.models.Ponto;
import br.com.itau.ponto.models.TipoBatida;
import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.PontoRepository;
import br.com.itau.ponto.repositories.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PontoControllerTests {

	@InjectMocks
	PontoController pontoController;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	PontoRepository pontoRepository;
	
	@Test
	public void testRegistrarPonto() {
		
		Usuario usuario1 = new Usuario("Aline", "Silva", "11122233344", "aline@email.com", LocalDateTime.parse("2020-04-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		Ponto ultimoPonto = new Ponto(LocalDateTime.parse("2020-04-10T08:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), usuario1);
		PontoForm pontoForm = new PontoForm(0L, "2020-04-10T12:00:00");

		when(usuarioRepository.findById(0L)).thenReturn(Optional.of(usuario1));
		when(usuarioRepository.getOne(0L)).thenReturn(usuario1);

		when(pontoRepository.findFirstByUsuarioIdOrderByDataBatidaDesc(0L)).thenReturn(ultimoPonto);

		ResponseEntity<PontoDto> responsePonto = pontoController.registrar(pontoForm);
		assertEquals("2020-04-10T12:00", responsePonto.getBody().getDataBatida().toString());
		assertEquals(TipoBatida.SAIDA, responsePonto.getBody().getTipoBatida());
	}
}
