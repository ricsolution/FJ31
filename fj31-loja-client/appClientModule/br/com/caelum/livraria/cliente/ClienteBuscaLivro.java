package br.com.caelum.livraria.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.GerenciadorLoja;

public class ClienteBuscaLivro {
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();

		GerenciadorLoja gerenciador = (GerenciadorLoja) ic
				.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/GerenciadorLojaBean!br.com.caelum.loja.session.GerenciadorLoja");

		Livro livro = gerenciador.procura(9L);
		for (Autor autor : livro.getAutores()) {
			System.out.println("Autor: " + autor.getId());
		}
	}

}
