package br.com.caelum.livraria.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.Carrinho;

public class ClienteCarrinhoAcessandoBeanRemovido {
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		Carrinho carrinho = (Carrinho) ic.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/CarrinhoBean!br.com.caelum.loja.session.Carrinho?stateful");
		
		Livro livro = new Livro();
		livro.setNome("Fausto");
		
		livro.setPreco(45.0);
		
		carrinho.addLivro(livro);
		
		carrinho.finalizaCompra();
		
		//Invocando metodo de nogocio depois de bean removido!
		
		carrinho.addLivro(livro);
		
	}

}
