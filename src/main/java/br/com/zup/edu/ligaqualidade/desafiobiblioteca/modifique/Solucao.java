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
	public static Set<EmprestimoConcedido> executa(Set<DadosLivro> livros,
			Set<DadosExemplar> exemplares,
			Set<DadosUsuario> usuarios, Set<DadosEmprestimo> emprestimos,
			Set<DadosDevolucao> devolucoes, LocalDate dataExpiracao) {

		Set<EmprestimoConcedido> emprestimosConcedidos = new HashSet<>();
		Map<Integer, Integer> countEmprestimosPadrao = new HashMap<>();

		for (DadosEmprestimo emprestimo : emprestimos) {
			DadosUsuario usuario = DadosHelper.buscaUsuario(emprestimo.idUsuario, usuarios);
			DadosExemplar exemplar = DadosHelper.buscaExemplar(emprestimo.idLivro, exemplares);
			LocalDate dataDevolucaoEstimada = LocalDate.now().plusDays(emprestimo.tempo);
			if (livroEmprestavelEDevolvidoAntesDaDataConsiderada(dataExpiracao, usuario, exemplar, dataDevolucaoEstimada, countEmprestimosPadrao)) {
				registrarEmprestimo(emprestimosConcedidos, usuario, exemplar, dataDevolucaoEstimada, countEmprestimosPadrao);
			}
		}

		return emprestimosConcedidos;
	}

	private static void registrarEmprestimo(Set<EmprestimoConcedido> emprestimosConcedidos, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucaoEstimada, Map<Integer, Integer> countEmprestimosPadrao) {
		EmprestimoConcedido emprestimoConcedido = new EmprestimoConcedido(usuario.idUsuario, exemplar.idExemplar, dataDevolucaoEstimada);
		emprestimosConcedidos.add(emprestimoConcedido);
		if (usuario.padrao == TipoUsuario.PADRAO) {
			countEmprestimosPadrao.putIfAbsent(usuario.idUsuario, 0);
			countEmprestimosPadrao.put(usuario.idUsuario, countEmprestimosPadrao.get(usuario.idUsuario) + 1);
		}
	}

	private static boolean livroEmprestavelEDevolvidoAntesDaDataConsiderada(LocalDate dataExpiracao, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucao, Map<Integer, Integer> countEmprestimosPadrao) {
		if (usuario.padrao == TipoUsuario.PADRAO) {
			return ValidadorUsuario.validarEmprestimoDeUsuarioPadrao(dataExpiracao, usuario, exemplar, dataDevolucao, countEmprestimosPadrao);
		} else { //Pesquisador
			return ValidadorUsuario.validarEmprestimoDePesquisador(dataExpiracao, dataDevolucao);
		}
	}



}
