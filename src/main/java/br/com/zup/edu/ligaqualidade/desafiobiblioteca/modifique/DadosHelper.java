package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

import java.util.Set;

public class DadosHelper {

    private static final String ERROR_MESSAGE = "Não encontrei esse motherfucker";

    private DadosHelper() {}

    // CDD 1 ponto
    static DadosUsuario buscaUsuario(int idUsuario, Set<DadosUsuario> usuarios) {
        // CDD 1 ponto
        return usuarios.stream().filter(usuario -> usuario.idUsuario == idUsuario).findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    // CDD 1 ponto
    static DadosExemplar buscaExemplar(int idLivro, Set<DadosExemplar> exemplares) {
        // CDD 1 ponto
        return exemplares.stream().filter(exemplar -> exemplar.idLivro == idLivro).findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }
}

// Classe OK - CDD - 4 < 7
