package entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import util.MyException;

public class Emprestimo {

	private Cliente cliente;
	private Livro livroEmprestado;
	private Date dataEmprestimo;

	public Emprestimo(Cliente cliente, Livro livroEmprestado) {
		this.cliente = cliente;
		this.livroEmprestado = livroEmprestado;
		renovarOuIniciarData(null);
	}

	public void renovarOuIniciarData(Date data) {

		Integer diaRenovacao = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		if (data == null) {
			diaRenovacao = 7;
			cal.add(Calendar.DAY_OF_MONTH, diaRenovacao);
		} else {
			if (data.before(dataEmprestimo)) {
				throw new MyException("Data não pode ser antecipada, apenas adiada!");
			}
			cal.setTime(data);
		}

		this.dataEmprestimo = cal.getTime();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Livro getLivroEmprestado() {
		return livroEmprestado;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setLivroEmprestado(Livro livroEmprestado) {
		this.livroEmprestado = livroEmprestado;
	}

	@Override
	public String toString() {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder bd = new StringBuilder();
      bd.append("Livro : "+livroEmprestado+"\n");
      bd.append("Cliente : "+cliente+"\n");
      bd.append("Data de devolução : "+sdf.format(dataEmprestimo)+"\n");
      return bd.toString();
	}

}
