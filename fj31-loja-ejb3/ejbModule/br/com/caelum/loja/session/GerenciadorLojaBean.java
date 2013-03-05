package br.com.caelum.loja.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.interceptor.AuditoriaInterceptor;

@Stateless
@Remote(GerenciadorLoja.class)
@Interceptors(AuditoriaInterceptor.class)
public class GerenciadorLojaBean implements GerenciadorLoja {
	private final Map<String, Livro> repositorio;
	@PersistenceContext
	private EntityManager manager;

	public GerenciadorLojaBean() {
		// TODO Auto-generated constructor stub
		this.repositorio = new HashMap<String, Livro>();

		Livro l1 = new Livro();
		l1.setNome("Livro 1");
		l1.setPreco(100.0);

		Livro l2 = new Livro();
		l2.setNome("Livro 2");
		l2.setPreco(200.0);

		this.repositorio.put("1111", l1);
		this.repositorio.put("2222", l2);

	}

	@Override
	public Livro procura(String isbn) {
		// TODO Auto-generated method stub

		return this.repositorio.get(isbn);
	}

	@Override
	public Livro salva(Livro livro) {
		this.manager.persist(livro);

		System.out.println("Livro salvo! Id: " + livro.getId());
		// throw new RuntimeException("Deu erro!");
		return livro;
	}

	@Override
	public Autor salva(Autor autor) {
		// TODO Auto-generated method stub
		this.manager.persist(autor);

		System.out.println(autor.getId());
		return autor;
	}

	@Override
	public Livro procura(Long id) {
		// TODO Auto-generated method stub

		return this.manager.find(Livro.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> listaLivros() {

		return this.manager.createQuery(
				"select livro from Livro as livro join fetch livro.autores")
				.getResultList();
	}

}
