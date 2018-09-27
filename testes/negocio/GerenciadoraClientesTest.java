package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest {

	private GerenciadoraClientes gerClientes;
	private int idCliente1 = 3;
	private int idCliente2 = 4;

	@Before
	public void setUp() {
		Cliente cliente1 = new Cliente(idCliente1, "Isadora Giongo", 38, "isagiongo@gmail.com", 3, true);
		Cliente cliente2 = new Cliente(idCliente2, "Joana Dávila", 36, "joana@gmail.com", 4, true);

		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente1);
		clientes.add(cliente2);

		gerClientes = new GerenciadoraClientes(clientes);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	@Test
	public void testPesquisaCliente() {

		Cliente cliente = gerClientes.pesquisaCliente(idCliente1);

		assertThat(cliente.getId(), is(idCliente1));
		assertThat(cliente.getEmail(), is("isagiongo@gmail.com"));
	}

	@Test
	public void testPesquisaClienteInexistente() {

		Cliente cliente = gerClientes.pesquisaCliente(10001);

		assertNull(cliente);
	}

	@Test
	public void testRemoveCliente() {

		boolean clienteRemovido = gerClientes.removeCliente(idCliente1);

		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente1));
	}

	@Test
	public void testRemoveClienteInexistente() {
		boolean clienteRemovido = gerClientes.removeCliente(10001);

		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}

	@Test
	public void testClienteIdadeValida() throws IdadeNaoPermitidaException {

		Cliente cliente = new Cliente(4, "Manuela Torres", 33, "manuela@gmail.com", 3434, true);

		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeValida_2() throws IdadeNaoPermitidaException {

		Cliente cliente = new Cliente(4, "Manuela Torres", 18, "manuela@gmail.com", 3434, true);

		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeValida_3() throws IdadeNaoPermitidaException {

		Cliente cliente = new Cliente(4, "Manuela Torres", 65, "manuela@gmail.com", 3434, true);

		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeInvalida() throws IdadeNaoPermitidaException {

		Cliente cliente = new Cliente(4, "Manuela Torres", 17, "manuela@gmail.com", 3434, true);

		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}
	
	@Test
	public void testClienteIdadeInvalida_2() throws IdadeNaoPermitidaException {

		Cliente cliente = new Cliente(4, "Manuela Torres", 66, "manuela@gmail.com", 3434, true);

		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}
}
