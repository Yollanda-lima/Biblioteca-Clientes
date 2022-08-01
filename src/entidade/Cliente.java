package entidade;
public class Cliente {

	private int matricula;
	private String nome;	

	public Cliente(int matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}


	public int getMatricula() {
		return matricula;
	}


	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	


	@Override
	public String toString() {
		return "Matricula = " + matricula + ", Nome = " + nome;
	}
	
	
	
}
