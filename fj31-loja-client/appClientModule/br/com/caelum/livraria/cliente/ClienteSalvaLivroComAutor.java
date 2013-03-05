package br.com.caelum.livraria.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.GerenciadorLoja;

public class ClienteSalvaLivroComAutor {
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		GerenciadorLoja gerenciador = (GerenciadorLoja) ic
				.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/GerenciadorLojaBean!br.com.caelum.loja.session.GerenciadorLoja");

		Autor autor = new Autor();

		Livro livro = new Livro();

		autor = gerenciador.salva(autor);// Esta no manged??
		System.out.println("Id do autor: " + autor.getId());

		livro.getAutores().add(autor); // Ligando livro a um autor

		gerenciador.salva(livro);

	}

}
