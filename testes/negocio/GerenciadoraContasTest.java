package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraContasTest {

	private GerenciadoraContas gerContas;
	
	@Before
	public void setUp() {
	ContaCorrente conta1 = new ContaCorrente(1, 200, true);
	ContaCorrente conta2 = new ContaCorrente(2, 300, true);
	
	List<ContaCorrente> contas = new ArrayList<>();
	contas.add(conta1);
	contas.add(conta2);
	
	gerContas = new GerenciadoraContas(contas);

	}
	
	@After
	public void tearDown() {
		gerContas.limpa();
	}
	
	@Test
	public void testTransfereValor() {
		
		ContaCorrente conta1 = gerContas.pesquisaConta(1);
		ContaCorrente conta2 = gerContas.pesquisaConta(2);
		
		gerContas.transfereValor(1, 100, 2);
		
		assertThat(conta2.getSaldo(), is(400.0));
		assertThat(conta1.getSaldo(), is(100.0));
	}
	
	@Test
	public void testTransfereComSaldoInsuficiente() {
		ContaCorrente conta1 = gerContas.pesquisaConta(1);
		ContaCorrente conta2 = gerContas.pesquisaConta(2);
			
		boolean sucesso = gerContas.transfereValor(1, 300, 2);
		
		assertFalse(sucesso);
		assertThat(conta1.getSaldo(), is(200.0));
		assertThat(conta2.getSaldo(), is(300.0));
	}
}
