package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
}
