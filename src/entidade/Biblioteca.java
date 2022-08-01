package entidade;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

	private List<Emprestimo> emprestimos = new ArrayList<>();

	public void addEmprestimo(Emprestimo emprestimo) {
		emprestimos.add(emprestimo);
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	@Override
	public String toString() {
		StringBuilder bd = new StringBuilder();
		bd.append("----EMPRÉSTIMOS---\n");
		for (int i = 0; i < emprestimos.size(); i++) {
			bd.append("\n");
			bd.append("Id :" + (i + 1)+"\n");
			bd.append(emprestimos.get(i) + "\n");
			bd.append("\n");
		}
		bd.append("----EMPRÉSTIMOS---\n");
		return bd.toString();
	}

}
