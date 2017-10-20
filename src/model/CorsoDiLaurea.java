package model;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class CorsoDiLaurea {
	Long id;
	String nome;
	Dipartimento dipartimento;
	Set<Corso> corsi;

	public CorsoDiLaurea() {
		nome = "";
		corsi = new HashSet<>();
	}

	public CorsoDiLaurea(String _nome, Dipartimento _dipartimento) {
		nome = _nome;
		dipartimento = _dipartimento;
		corsi = new HashSet<>();
	}

	public void addCorso(Corso corso) {
		corsi.add(corso);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dipartimento getDipartimento() {
		return dipartimento;
	}

	public void setDipartimento(Dipartimento dipartimento) {
		this.dipartimento = dipartimento;
	}

	public Set<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(Set<Corso> corsi) {
		this.corsi = corsi;
	}

	public String toString() {

		return "[" + this.getId() + ", " + this.getNome() + " " + this.getDipartimento().toString() + "]";
	}

}
