package br.com.caelum.loja.session;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.loja.entity.Livro;


@Stateless
@Remote(GerenciadorLoja.class)
public class GerenciadorLojaBean implements GerenciadorLoja{
	private Map<String, Livro> repositorio;
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
	public void salva(Livro livro) {
		this.manager.persist(livro);
		
		System.out.println("Livro salvo! Id: "+ livro.getId());
	}

}
