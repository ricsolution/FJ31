package br.com.caelum.loja.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double preco;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@ManyToMany(fetch = FetchType.EAGER)
	private final List<Autor> autores = new ArrayList<Autor>();

	public List<Autor> getAutores() {
		return autores;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@PrePersist
	@PreUpdate
	public void preAltera(){
		System.out.println("CALLBACK preAltera(): atualizando data automaticamente");
		this.dataAlteracao = new Date();
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
