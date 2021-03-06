package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosDevolucao;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// CDD - Contagem da métrica

public class Solucao {

	/**
	 * Você precisa implementar o código para executar o fluxo
	 * o completo de empréstimo e devoluções a partir dos dados
	 * que chegam como argumento. 
	 * 
	 * Caso você queira pode adicionar coisas nas classes que já existem,
	 * mas não pode alterar nada.
	 */
	
	/**
	 * 
	 * @param livros dados necessários dos livros
	 * @param exemplares tipos de exemplares para cada livro
	 * @param usuarios tipos de usuarios
	 * @param emprestimos informações de pedidos de empréstimos
	 * @param devolucoes informações de devoluções, caso exista. 
	 * @param dataExpiracao aqui é a data que deve ser utilizada para verificar expiração
	 * @return
	 */
	public static Set<EmprestimoConcedido> executa(Set<DadosLivro> livros, // CDD 1 ponto
			Set<DadosExemplar> exemplares, // CDD 1 ponto
			Set<DadosUsuario> usuarios, Set<DadosEmprestimo> emprestimos, // CDD 2 pontos
			Set<DadosDevolucao> devolucoes, LocalDate dataExpiracao) { // CDD 1 ponto

		Set<EmprestimoConcedido> emprestimosConcedidos = new HashSet<>(); // CDD 0 ponto já contou
		Map<Integer, Integer> countEmprestimosPadrao = new HashMap<>();

		// CDD 1 ponto (pelo for)
		for (DadosEmprestimo emprestimo : emprestimos) { // CDD 0 ponto já contou
			DadosUsuario usuario = DadosHelper.buscaUsuario(emprestimo.idUsuario, usuarios); // CDD 0 ponto já contou
			DadosExemplar exemplar = DadosHelper.buscaExemplar(emprestimo.idLivro, exemplares); // CDD 0 ponto já contou
			LocalDate dataDevolucaoEstimada = LocalDate.now().plusDays(emprestimo.tempo);
			// CDD 1 ponto
			if (livroEmprestavelEDevolvidoAntesDaDataConsiderada(dataExpiracao, usuario, exemplar, dataDevolucaoEstimada, countEmprestimosPadrao)) {
				registrarEmprestimo(emprestimosConcedidos, usuario, exemplar, dataDevolucaoEstimada, countEmprestimosPadrao);
			}
		}

		return emprestimosConcedidos;
	}

	private static void registrarEmprestimo(Set<EmprestimoConcedido> emprestimosConcedidos, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucaoEstimada, Map<Integer, Integer> countEmprestimosPadrao) {
		EmprestimoConcedido emprestimoConcedido = new EmprestimoConcedido(usuario.idUsuario, exemplar.idExemplar, dataDevolucaoEstimada);
		emprestimosConcedidos.add(emprestimoConcedido);
		// CDD 1 ponto
		if (usuario.padrao == TipoUsuario.PADRAO) {
			countEmprestimosPadrao.putIfAbsent(usuario.idUsuario, 0);
			countEmprestimosPadrao.put(usuario.idUsuario, countEmprestimosPadrao.get(usuario.idUsuario) + 1);
		}
	}

	private static boolean livroEmprestavelEDevolvidoAntesDaDataConsiderada(LocalDate dataExpiracao, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucao, Map<Integer, Integer> countEmprestimosPadrao) {
		// CDD 1 ponto
		if (usuario.padrao == TipoUsuario.PADRAO) {
			return ValidadorUsuario.validarEmprestimoDeUsuarioPadrao(dataExpiracao, usuario, exemplar, dataDevolucao, countEmprestimosPadrao);
			// CDD 1 ponto
		} else { //Pesquisador
			return ValidadorUsuario.validarEmprestimoDePesquisador(dataExpiracao, dataDevolucao);
		}
	}

// Atingiu 10 pontos
// 10 > 7 => Pela métrica, devemos fatiar essa classe

}
