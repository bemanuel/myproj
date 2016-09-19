/**
 * 
 */
package br.ufma.msc.vo;

/**
 * @author jwalker
 *
 */
public class Projeto {

	String nome;
	int id;
	int tempo;
	
	public Projeto(int id, String nome, int tempo) {
		this.id = id;
		this.nome = nome;
		this.tempo = tempo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

}
