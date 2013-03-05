package br.com.caelum.loja.session;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;

public interface GerenciadorLoja {
	Livro procura(String isbn);
	void salva(Livro livro);

	Autor salva(Autor autor);

	Livro procura(Long id);

}
