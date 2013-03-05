package br.com.caelum.loja.rest;

import java.net.URI;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.GerenciadorLoja;

@Path("/loja")
public class LojaResource {
	@SuppressWarnings("unused")
	private GerenciadorLoja lookupGerenciador() {
		try {
			GerenciadorLoja gerenciador = (GerenciadorLoja) new InitialContext()
			.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/GerenciadorLojaBean!br.com.caelum.loja.session.GerenciadorLoja");
			return gerenciador;
		} catch (NamingException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}

	@GET
	@Path("/livros")
	@Produces({ "application/xml", "application/json" })
	public List<Livro> getLivrosXml() {
		GerenciadorLoja gerenciador = lookupGerenciador();
		List<Livro> livros = gerenciador.listaLivros();
		return livros;
	}

	@GET
	@Path("/livro/{id}")
	public Livro getLivro(@PathParam("id") Long id) {
		GerenciadorLoja gerenciador = lookupGerenciador();
		Livro livro = gerenciador.procura(id);
		if (livro == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(livro.getNome());
		return livro;
	}

	@POST
	@Path("/livros")
	@Consumes({ "application/xml", "application/json" })
	public Response cadastra(Livro livro) {
		GerenciadorLoja gerenciador = lookupGerenciador();

		Livro salva = gerenciador.salva(livro);
		System.out.println("Nome: " + livro.getNome());
		return Response.created(URI.create("/loja/livro/" + salva.getId()))
				.build();
	}

}
