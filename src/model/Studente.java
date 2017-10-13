package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Studente {

	private String matricola;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Set<Corso> corsi; 
	public Studente() {
		corsi=new HashSet<>();
	}

	public Studente(String matricola, String nome, String cognome, Date dataNascita) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		corsi=new HashSet<>();
	}

	public Set<Corso> getCorsi(){
		return corsi;
	}
	public void setCorsi(Set<Corso> _corsi){
		corsi=_corsi;
	}
	
	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public int hashCode() {
		return this.matricola.hashCode();
	}

	public boolean equals(Object object) {
		Studente studente = (Studente) object;
		return (this.getMatricola() == studente.getMatricola());
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "[" + this.getMatricola() + ", " + this.getNome() + ", " + this.getCognome() + ", "
				+ sdf.format(this.getDataNascita()) + "]";
	}
}
