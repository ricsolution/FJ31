package br.com.caelum.loja.entity;

import java.io.Serializable;

public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private double preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}
}
