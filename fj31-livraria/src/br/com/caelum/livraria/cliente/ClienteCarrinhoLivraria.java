package br.com.caelum.livraria.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.livraria.ejb.Carrinho;
import br.com.caelum.livraria.modelo.Livro;

public class ClienteCarrinhoLivraria {
public static void main(String[] args) throws NamingException {
	InitialContext ic = new InitialContext();
	
	Carrinho carrinho = (Carrinho) ic.lookup("ejb:/livraria/CarrinhoBean!br.com.caelum.livraria.ejb.Carrinho?stateful");
	
	Livro livro = new Livro();
	livro.setNome("Alice no pais da maravilha");
	livro.setPreco(15.0);
	
	carrinho.addLivro(livro);
	
	System.out.println(carrinho.getTotal());
	
	carrinho.finalizaCompra();
	
}
}
