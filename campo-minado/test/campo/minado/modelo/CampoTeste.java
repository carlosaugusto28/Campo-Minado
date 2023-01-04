package campo.minado.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import campo.minado.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Emcima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Embaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, ()-> {
			campo.abrir();
		});
		
	}
	
	@Test
	void testeAbrirComVizinhos1() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@SuppressWarnings("unused")
	@Test
	void testeTostring() {
		
		Campo campo1_5 = new Campo(1, 5);
		Campo campo2_2 = new Campo(2, 2);
		Campo campo2_3 = new Campo (2, 3);
		Campo campo2_4 = new Campo (2, 4);
		Campo campo3_2 = new Campo (3, 2);
		Campo campo3_4 = new Campo (3, 4);
		Campo campo4_2 = new Campo (4, 2);
		Campo campo4_4 = new Campo (4, 4);
		
		campo2_4.adicionarVizinho(campo1_5);
		campo2_4.adicionarVizinho(campo2_3);
		campo3_2.adicionarVizinho(campo2_3);
		campo3_2.adicionarVizinho(campo2_4);
		campo3_2.adicionarVizinho(campo3_2);
		campo3_2.adicionarVizinho(campo3_4);
		campo3_2.adicionarVizinho(campo4_2);
		campo3_2.adicionarVizinho(campo4_4);
		
		campo1_5.minar();
		campo3_2.abrir();
		
		char c2_2 = campo2_2.toString().charAt(0);
		char c2_3 = campo2_3.toString().charAt(0);
		char c2_4 = campo2_4.toString().charAt(0);
		char c3_3 = campo3_2.toString().charAt(0);
		char c4_4 = campo3_4.toString().charAt(0);
		
		char[] inicializados = new char[] {c2_2, c2_3, c2_4,c3_3,c4_4};
		char[] esperados = new char[] {' ',' ','1',' ',' '};
		
	}
}
