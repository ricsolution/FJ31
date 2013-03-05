package br.com.caelum.livraria.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.loja.session.Carrinho;

public class ClienteFuzilandoServidor {
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		Carrinho carrinhoRemoto = (Carrinho) ic.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/CarrinhoBean!br.com.caelum.loja.session.Carrinho?stateful");
		
		long inicio = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			carrinhoRemoto.getTotal();
		}
		long fim = System.currentTimeMillis();
		System.out.println(fim - inicio);
		
	}

}
