package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.time.LocalDate;
import java.util.Map;

public class ValidadorUsuario {

    private ValidadorUsuario() {}

    static boolean validarEmprestimoDeUsuarioPadrao(LocalDate dataExpiracao, DadosUsuario usuario, DadosExemplar exemplar, LocalDate dataDevolucao, Map<Integer, Integer> countEmprestimosPadrao) {
        Integer countEmprestimosFeito = countEmprestimosPadrao.get(usuario.idUsuario);
        boolean estourouMaximoDeLivros = countEmprestimosFeito != null && countEmprestimosFeito >= 5;
        return dataDevolucao.isBefore(dataExpiracao)
                && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao)
                && exemplar.tipo == TipoExemplar.LIVRE
                && !estourouMaximoDeLivros;
    }

    static boolean validarEmprestimoDePesquisador(LocalDate dataExpiracao, LocalDate dataDevolucao) {
        return dataDevolucao == null
                || dataDevolucao.isBefore(dataExpiracao)
                && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao);
    }
}
