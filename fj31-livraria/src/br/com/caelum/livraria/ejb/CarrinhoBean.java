package br.com.caelum.livraria.ejb;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.livraria.modelo.Livro;

public class CarrinhoBean implements Carrinho{
private List<Livro> livros = new ArrayList<>();
private double total;
	@Override
	public void addLivro(Livro livro) {
		// TODO Auto-generated method stub
		System.out.println("Adicionando o livro " + livro.getNome() + " ao carrinho");
		this.livros.add(livro);
		this.total += livro.getPreco();
	}

	@Override
	public List<Livro> getLivros() {
		// TODO Auto-generated method stub
		System.out.println("Carrinho devolvendo a lista de livros: ");
		
		return this.livros;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		System.out.println("Carrinho devolvendo o total: " + total);
		return this.total;
	}

	@Override
	public void finalizaCompra() {
		// TODO Auto-generated method stub
		System.out.println("Finalizando a compra de: ");
		for (Livro livro : this.livros) {
			System.out.println(livro.getPreco() + " - " + livro.getNome());
		}
	}

}
