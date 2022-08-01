package aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import conversao.Conversor;
import entidade.Biblioteca;
import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Livro;
import util.MyException;

public class Programa {

	private static List<Livro> lerLivros(String path) {
		List<Livro> livros = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String split[] = line.split(";");
				int codigo = Integer.parseInt(split[0]);
				String titulo = split[1];
				livros.add(new Livro(codigo, titulo));
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return livros;

	}

	private static List<Cliente> lerClientes(String path) {
		List<Cliente> clientes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String split[] = line.split(";");
				int matricula = Integer.parseInt(split[0]);
				String nome = split[1];
				clientes.add(new Cliente(matricula, nome));
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return clientes;

	}
	
	public static void salvarArquivo(String path , String salvarString) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			
			bw.write(salvarString);
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static String menu() {
		StringBuilder bd = new StringBuilder();
		bd.append("\n");
		bd.append("--------BIBLIOTECA--------\n");
		bd.append("1- Cadastrar um empréstimo\n");
		bd.append("2- Renovar um empréstimo\n");
		bd.append("3- Ver empréstimos cadastrados\n");
		bd.append("4- Sair do programa\n");
		bd.append("--------BIBLIOTECA--------\n");
		return bd.toString();
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		try {
			Biblioteca biblioteca = new Biblioteca();

			System.out.println("Livros : ");
			System.out.println("");
			String pathLivros = "c:\\temp\\trabalho\\livros.txt"; // colocar onde o arquivo livro se encontra no seu computador
			List<Livro> livros = lerLivros(pathLivros);
			for (int i = 0; i < livros.size(); i++) {
				System.out.println("Id : " + (i + 1) + ", " + livros.get(i));
			}
			System.out.println("");
			System.out.println("Clientes : ");
			System.out.println();
			String pathClientes = "c:\\temp\\trabalho\\clientes.txt"; // colocar onde o arquivo cliente se encontra no seu computador
			List<Cliente> clientes = lerClientes(pathClientes); 
			for (int i = 0; i < clientes.size(); i++) {
				System.out.println("Id : " + (i + 1) + ", " + clientes.get(i));
			}
			System.out.println("Se oriente através dos Id's de usuário e livro para manipular o empréstimo!");
			System.out.println("");
			System.out.println(menu());

			int opcaoMenu = sc.nextInt();

			while (opcaoMenu != 4) {
				if (opcaoMenu == 1) {
					System.out.println("Digite o id do cliente : ");
					int idCliente = sc.nextInt();
					Cliente cliente = clientes.get(idCliente - 1);
					System.out.println("Digite o id do Livro : ");
					int idLivro = sc.nextInt();
					Livro livro = livros.get(idLivro - 1);

					biblioteca.addEmprestimo(new Emprestimo(cliente, livro));

				} else if (opcaoMenu == 2) {
					System.out.println(
							"Qual empréstimo deseja renovar? para ver os empréstimos criados, vai na opção 3 no menu! ");
					int idEmprestimo = sc.nextInt();
					Emprestimo emprestimo = biblioteca.getEmprestimos().get(idEmprestimo - 1);
					System.out.println("Nova data : (dd/MM/yyyy)");
					String dataString = sc.next();
					Date data = sdf.parse(dataString);
					emprestimo.renovarOuIniciarData(data);
					System.out.println("Nova data : " + sdf.format(emprestimo.getDataEmprestimo()));

				} else if (opcaoMenu == 3) {
					System.out.println(biblioteca);

				} else {
					System.out.println("Opção incorreta, digite novamente : ");
				}

				System.out.println(menu());
				opcaoMenu = sc.nextInt();
			}

			    String arquivoComum= biblioteca.getEmprestimos().get(0).toString();
			    String arquivoBinario = Conversor.convertStringToBinary(arquivoComum);
               
			    salvarArquivo("c:\\temp\\trabalho\\emprestimo.txt",arquivoBinario); // Onde vai salvar o arquivo!
		    
		    
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Não existe esse id que você passou");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (MyException e) {
			System.out.println(e.getMessage());

		} finally {
			System.out.println("Fim do programa!");
			sc.close();
		}

	}

}
