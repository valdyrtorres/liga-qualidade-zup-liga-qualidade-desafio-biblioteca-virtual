package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.time.LocalDate;
import java.util.Map;

public class ValidadorUsuario {

    private ValidadorUsuario() {}

    // CDD 2 pontos
    static boolean validarEmprestimoDeUsuarioPadrao(LocalDate dataExpiracao, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucao, Map<Integer, Integer> countEmprestimosPadrao) {
        Integer countEmprestimosFeito = countEmprestimosPadrao.get(usuario.idUsuario);
        // CDD 1 ponto
        boolean estourouMaximoDeLivros = countEmprestimosFeito != null && countEmprestimosFeito >= 5;
        // CDD 1 ponto
        return dataDevolucao.isBefore(dataExpiracao)
                && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao)
                && exemplar.tipo == TipoExemplar.LIVRE
                && !estourouMaximoDeLivros;
    }

    static boolean validarEmprestimoDePesquisador(LocalDate dataExpiracao, LocalDate dataDevolucao) {
        // CDD 1 ponto
        return dataDevolucao == null
                || dataDevolucao.isBefore(dataExpiracao)
                && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao);
    }
}

// Classe OK CDD 5 < 7
