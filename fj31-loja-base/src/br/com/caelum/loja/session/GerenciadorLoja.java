package br.com.caelum.loja.session;

import java.util.List;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;

public interface GerenciadorLoja {
	Livro procura(String isbn);
	Livro salva(Livro livro);

	Autor salva(Autor autor);

	Livro procura(Long id);

	List<Livro> listaLivros();
}
