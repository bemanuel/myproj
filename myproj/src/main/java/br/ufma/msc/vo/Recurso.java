package br.ufma.msc.vo;

import java.math.BigDecimal;

public class Recurso {

	int id;
	String nome;
	int tipo_recurso;
	BigDecimal valor;

	public Recurso(int id, String nome, int tipo_recurso, BigDecimal valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo_recurso = tipo_recurso;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo_recurso() {
		return tipo_recurso;
	}

	public void setTipo_recurso(int tipo_recurso) {
		this.tipo_recurso = tipo_recurso;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
