package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import java.time.LocalDate;
import java.time.Period;

public class DataUtils {

    private DataUtils() {}

    private static final int LIMITE_DIAS_POR_EMPRESTIMO = 60;

    static boolean validarLimiteDeDiasPorEmprestimo(LocalDate dataDevolucao) {
        // CDD 1 ponto
        return Period.between(LocalDate.now(), dataDevolucao).getDays() < LIMITE_DIAS_POR_EMPRESTIMO;
    }
}

// Classe Ok CDD - 1 < 7