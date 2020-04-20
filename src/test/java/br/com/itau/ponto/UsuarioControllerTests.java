package br.com.itau.ponto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.itau.ponto.controllers.UsuarioController;
import br.com.itau.ponto.dtos.DetalhesDoUsuarioDto;
import br.com.itau.ponto.dtos.PontosUsuarioDto;
import br.com.itau.ponto.dtos.UsuarioDto;
import br.com.itau.ponto.forms.AlteracaoUsuarioForm;
import br.com.itau.ponto.forms.UsuarioForm;
import br.com.itau.ponto.models.Ponto;
import br.com.itau.ponto.models.Usuario;
import br.com.itau.ponto.repositories.PontoRepository;
import br.com.itau.ponto.repositories.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UsuarioControllerTests {

	@InjectMocks
	UsuarioController usuarioController;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	PontoRepository pontoRepository;
	
	@Test
	public void testListarUsuarios() {
		
		List<Usuario> lista = getListaMock();
		
		when(usuarioRepository.findAll()).thenReturn(lista);
		
		List<UsuarioDto> listaTeste = usuarioController.listar();
		assertEquals(3, listaTeste.size());
		assertEquals("Aline Silva", listaTeste.get(0).getNomeCompleto());
		assertEquals("99999999999", listaTeste.get(1).getCpf());
		assertEquals("mario@email.com", listaTeste.get(2).getEmail());
	}

	@Test
	public void testConsultarUsuario() {

		List<Usuario> lista = getListaMock();

		when(usuarioRepository.findById(0L)).thenReturn(Optional.of(lista.get(0)));
		
		ResponseEntity<DetalhesDoUsuarioDto> responseUsuario = usuarioController.consultar(0L);
		assertEquals("Aline Silva", responseUsuario.getBody().getNomeCompleto());
		assertEquals("11122233344", responseUsuario.getBody().getCpf());
		assertEquals("aline@email.com", responseUsuario.getBody().getEmail());
		assertEquals("2020-04-03T10:15:30", responseUsuario.getBody().getDataCadastro().toString());
		
	}

	@Test
	public void testCadastrarUsuario() {
		
		UsuarioForm form = new UsuarioForm("Aline", "Silva", "11122233344", "aline@email.com", "2020-04-03T10:15:30");

		ResponseEntity<UsuarioDto> responseUsuario = usuarioController.cadastrar(form);
		assertEquals("Aline Silva", responseUsuario.getBody().getNomeCompleto());
		assertEquals("11122233344", responseUsuario.getBody().getCpf());
		assertEquals("aline@email.com", responseUsuario.getBody().getEmail());
	}
	
	@Test
	public void testAlterarUsuario() {
		
		List<Usuario> lista = getListaMock();
		
		when(usuarioRepository.findById(0L)).thenReturn(Optional.of(lista.get(0)));
		when(usuarioRepository.getOne(0L)).thenReturn(lista.get(0));

		AlteracaoUsuarioForm alteracaoForm = new AlteracaoUsuarioForm("Amanda", "Leme", "00000011122", "amanda@email.com");

		ResponseEntity<UsuarioDto> responseUsuario = usuarioController.alterar(0L, alteracaoForm);
		assertEquals("Amanda Leme", responseUsuario.getBody().getNomeCompleto());
		assertEquals("00000011122", responseUsuario.getBody().getCpf());
		assertEquals("amanda@email.com", responseUsuario.getBody().getEmail());
	}
	
	@Test
	public void testListarPontosUsuario() {
		
		List<Usuario> lista = getListaMock();
		Usuario usuario = lista.get(1);
		usuario.setTotalHoras(15L);
		
		List<Ponto> listaPontos = new ArrayList<Ponto>();
		Ponto ponto1 = new Ponto(LocalDateTime.parse("2020-04-10T08:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), usuario);
		Ponto ponto2 = new Ponto(LocalDateTime.parse("2020-04-10T17:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), usuario);
		Ponto ponto3 = new Ponto(LocalDateTime.parse("2020-04-12T09:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), usuario);
		Ponto ponto4 = new Ponto(LocalDateTime.parse("2020-04-12T15:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), usuario);
		listaPontos.add(ponto1);
		listaPontos.add(ponto2);
		listaPontos.add(ponto3);
		listaPontos.add(ponto4);
		
		when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
		when(pontoRepository.findByUsuarioId(1L)).thenReturn(listaPontos);
		
		ResponseEntity<PontosUsuarioDto> responsePontosUsuario = usuarioController.listarPontos(1L);
		assertEquals(4, responsePontosUsuario.getBody().getListaPontos().size());
		assertEquals(15, responsePontosUsuario.getBody().getHorasTrabalhadas().intValue());
	}
	
	private List<Usuario> getListaMock() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario("Aline", "Silva", "11122233344", "aline@email.com", LocalDateTime.parse("2020-04-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Usuario usuario2 = new Usuario("Rafael", "Lopes", "99999999999", "rafael@outroemail.com", LocalDateTime.parse("2020-02-15T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Usuario usuario3 = new Usuario("Mario", "Pereira", "33333333377", "mario@email.com", LocalDateTime.parse("2020-01-03T15:20:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		lista.add(usuario1);
		lista.add(usuario2);
		lista.add(usuario3);
		return lista;
	}
	
}
