package model;
public class Corso {
	
	private Long id;
	private String nome;
	
	public Corso(){}
	
	public Corso(String _nome) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ "+id+" , "+nome+" ]";
	}
}
