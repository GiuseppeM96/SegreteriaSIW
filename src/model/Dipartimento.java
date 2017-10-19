package model;

public class Dipartimento {
	Long id;
	String nome;
	
	public Dipartimento() {
		nome="";
	}
	
	public Dipartimento(String _nome){
		nome=_nome;
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
	
	
}
